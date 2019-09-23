package puzzle;
public abstract class Node {

    // The tile positions array contains the 4-bit position of the tile's location
    // within a 32-bit integer.
    // Example: CostTable0 contains only the positions of tiles 2, 3, and 4.
    //   So, when mapping the configuration 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0
    //   to an index in the table, you will get:
    //   0000..0000[4 bits for location of tile 4]
    //   [4 bits for location of tile 3][4 bits for location of tile 2]
    // The same technique is applied for the other two cost tables.
    static final int[] tilePositions = {-1, 0, 0, 1, 2, 1, 2, 0, 1, 3, 4, 2, 3, 5, 4, 5};
    // The subset number of a tile
    //   Tiles 2 - 4 are in subset 0
    //   Tiles 1, 5, 6, 9, 10, 13 are in subset 1
    //   The rest are in subset 2
    static final int[] tileSubsets = {-1, 1, 0, 0, 0, 1, 1, 2, 2, 1, 1, 2, 2, 1, 2, 2};

    static int dimension, numOfTiles, numOfTilesMinusOne, heuristic;
    static long goalState, goalStatePositions;

    static void initialize() {
        dimension = PuzzleConfiguration.getDimension();
        numOfTiles = PuzzleConfiguration.getNumOfTiles();
        numOfTilesMinusOne = Node.numOfTiles - 1;
        goalState = PuzzleConfiguration.getGoalState();
        goalStatePositions = PuzzleConfiguration.getGoalStatePositions();
        heuristic = PuzzleConfiguration.getHeuristic();
    }

    public static int h(final long boardConfig) {
        if (heuristic == PuzzleConfiguration.HEURISTIC_PD) {
            if (numOfTiles == 16) {
                // Create three different indexes that contain only the positions of
                // tiles applicable to the corresponding pattern database.
                int index0 = 0, index1 = 0, index2 = 0;
                for (int pos = numOfTilesMinusOne; pos >= 0; --pos) {
                    final int tile = (int)((boardConfig >> (pos << 2)) & 0xF);
                    if (tile != 0) {
                        final int subsetNumber = tileSubsets[tile];
                        switch (subsetNumber) {
                            case 2:
                                index2 |= pos << (tilePositions[tile] << 2);
                                break;
                            case 1:
                                index1 |= pos << (tilePositions[tile] << 2);
                                break;
                            default:
                                index0 |= pos << (tilePositions[tile] << 2);
                                break;
                        }
                    }
                }

                return PuzzleConfiguration.costTable_15_puzzle_0[index0] +
                       PuzzleConfiguration.costTable_15_puzzle_1[index1] +
                       PuzzleConfiguration.costTable_15_puzzle_2[index2];

            } else {
                return PuzzleConfiguration.patternDatabase_8_puzzle.get(boardConfig);
            }
        }

        // Implements the Manhattan Distance heuristic
        int distance = 0;
        final long currentPositions =
            Utility.getPositionsAsLong(boardConfig, numOfTilesMinusOne);
        for (int pos = numOfTilesMinusOne; pos >= 0; --pos) {
            final int posTimes4 = pos << 2,
                      goalStateTile = (int)((goalState >> posTimes4) & 0xF),
                      currentTile = (int)((boardConfig >> posTimes4) & 0xF);
            if (currentTile != 0 && currentTile != goalStateTile) {
                final int
                    currentPosition =
                        Utility.getElementAt(currentPositions, currentTile),
                    goalStatePosition =
                        Utility.getElementAt(goalStatePositions, currentTile),
                       
                    currentX = currentPosition / dimension,
                    goalStateX = goalStatePosition / dimension,
                    currentY = currentPosition % dimension,
                    goalStateY = goalStatePosition % dimension;
                int val1 = currentX - goalStateX,
                    val2 = currentY - goalStateY;
                if (val1 < 0) val1 *= -1;
                if (val2 < 0) val2 *= -1;
                distance += (val1 + val2);
            }
        }

        // Add linear conflicts to the Manhattan Distance, if chosen
        if (heuristic == PuzzleConfiguration.HEURISTIC_LC) {
            final int dimMinusOne = dimension - 1,
                      lastIndexInColumn = dimension * dimMinusOne,
                      conflictsArraySize = numOfTiles + 1;

            // Represents a bit vector to keep track of the tiles in conflict
            int linearConflicts = 0;

            // Linear row conflicts
            for (int row = 0; row < dimension; ++row) {
                final int lowerIndex = row * dimension,
                          upperIndex = lowerIndex + dimMinusOne,
                          lowerBound = lowerIndex + 1,
                          upperBound = upperIndex + 1;
                for (int i = lowerIndex; i < upperIndex; ++i) {
                    final byte iValue = (byte)((boardConfig >> (i << 2)) & 0xF);
                    final int
                        iPosition =
                            Utility.getElementAt(currentPositions, iValue),
                        iGoalStatePosition =
                            Utility.getElementAt(goalStatePositions, iValue);
                    if ((iPosition != iGoalStatePosition) &&
                        (iValue >= lowerBound) && (iValue <= upperBound)) {
                        for (int j = i + 1; j <= upperIndex; ++j) {
                            final byte jValue = (byte)((boardConfig >> (j << 2)) & 0xF);
                            if ((jValue >= lowerBound) && (jValue <= upperBound) &&
                                (iValue > jValue)) {

                                // linearConflicts[jValue] = true
                                linearConflicts |= (0x1 << jValue);
                            }
                        }
                    }
                }
            }

            // Linear column conflicts
            for (int col = 0; col < dimension; ++col) {
                final int lowerIndex = col,
                          upperIndex = lowerIndex + lastIndexInColumn,
                          lowerBound = lowerIndex + 1,
                          upperBound = upperIndex + 1;

                // Represents a bit vector to keep track of the tiles in a column
                int set = 0;

                for (int i = lowerBound; i <= upperBound; i += dimension) {
                    // set[i] = true
                    set |= (0x1 << i);
                }
                for (int i = lowerIndex; i <= upperIndex; i += dimension) {
                    final byte iValue = (byte)((boardConfig >> (i << 2)) & 0xF);
                    final int
                        iPosition =
                            Utility.getElementAt(currentPositions, iValue),
                        iGoalStatePosition =
                            Utility.getElementAt(goalStatePositions, iValue);
                    if (iPosition != iGoalStatePosition) {
                        for (int j = i + dimension; j <= upperIndex; j += dimension) {
                            final byte jValue = (byte)((boardConfig >> (j << 2)) & 0xF);
                            if (((set >> iValue) & 0x1) == 1 && // set[iValue]
                                ((set >> jValue) & 0x1) == 1 && // set[jValue]
                                (iValue > jValue)) {

                                // linearConflicts[jValue] = true
                                linearConflicts |= (0x1 << jValue);
                            }
                        }
                    }
                }
            }

            // Add all conflicts to distance
            for (int i = conflictsArraySize - 1; i >= 0; --i) {
                if (((linearConflicts >> i) & 0x1) == 1) {
                    distance += 2;
                }
            }
        }

        return distance;
    }

    public static int posOfSpace(final long boardConfig) {
        for (int i = numOfTiles - 1; i >= 0; --i) {
            if ((byte)((boardConfig >> (i << 2)) & 0xF) == 0) {
                return i;
            }
        }
        return -1;
    }
}