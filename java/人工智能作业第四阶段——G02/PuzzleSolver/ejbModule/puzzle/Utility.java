package puzzle;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.StringTokenizer;

public class Utility {
    public static final DecimalFormat INT_FORMATTER = new DecimalFormat("###,###");
    public static final DecimalFormat DEC_FORMATTER = new DecimalFormat("###,##0.000");

    public static byte[] getRandomArray(final int numOfTiles, final boolean keepZeroInCorner) {
        final byte[] tiles = new byte[numOfTiles];
        for (int i = numOfTiles - 2; i >= 0; --i) {
            tiles[i] = (byte)(i + 1);
        }
        tiles[numOfTiles - 1] = 0;

        int maxTilesToSwap;
        if (keepZeroInCorner) {
            maxTilesToSwap = numOfTiles - 1;
        } else {
            maxTilesToSwap = numOfTiles;
        }
        final Random random = new Random();
        for (int i = 49; i >= 0; --i) {
            final int rand1 = random.nextInt(maxTilesToSwap);
            int rand2 = random.nextInt(maxTilesToSwap);
            if (rand1 == rand2) {
                if (rand1 < (maxTilesToSwap << 1)) {
                    rand2 = random.nextInt(maxTilesToSwap - rand1) + rand1;
                } else {
                    rand2 = random.nextInt(rand1);
                }
            }
            swap(rand1, rand2, tiles);
        }
        if (!isValidPermutation(tiles)) {
            if (tiles[0] != 0 && tiles[1] != 0) {
                swap(0, 1, tiles);
            } else {
                swap(2, 3, tiles);
            }
        }
        return tiles;
    }

    public static boolean isValidPermutation(final byte[] state) {
        final int numOfTiles = state.length,
                  dim = (int)Math.sqrt(numOfTiles);
        int inversions = 0;

        for (int i = 0; i < numOfTiles; ++i) {
            final byte iTile = state[i];
            if (iTile != 0) {
                for (int j = i + 1; j < numOfTiles; ++j) {
                    final byte jTile = state[j];
                    if (jTile != 0 && jTile < iTile) {
                        ++inversions;
                    }
                }
            } else {
                if ((dim & 0x1) == 0) {
                    inversions += (1 + i / dim);
                }
            }
        }
        if ((inversions & 0x1) == 1) return false;
        return true;
    }

    public static String byteArrayToString(final byte[] state) {
        final int numOfTiles = state.length;
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numOfTiles; ++i) {
            if (i != 0) {
                builder.append(",");
            }
            builder.append(state[i]);
        }
        return builder.toString();
    }

    public static long byteArrayToLong(final byte[] byteArray) {
        long arrayAsLong = 0;
        for (int i = 0; i < byteArray.length; ++i) {
            arrayAsLong |= ((long)byteArray[i] << (i << 2));
        }
        return arrayAsLong;
    }

    public static String longToString(final long boardConfig, final int numOfTiles) {
        final StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < numOfTiles; ++i) {
            final byte tile = (byte)((boardConfig >> (i << 2)) & 0xF);
            if (i != 0) {
                sb.append(" ");
            }
            sb.append(tile);
        }
        sb.append("]");
        return sb.toString();
    }

    public static long getPositionsAsLong(final long boardConfig,
                                          final int numOfTilesMinusOne) {
    	// Creates a long that maps tiles(i.e. array index) to position in puzzle.
    	long positions = 0;
        for (int pos = numOfTilesMinusOne; pos >= 0; --pos) {
            final int tile = (int)((boardConfig >> (pos << 2)) & 0xF);
            positions |= ((long)pos << (tile << 2));
        }
        return positions;
    }

    public static long arrayToLong(final byte[] boardConfig) {
        long value = 0;
        for (int i = 0; i < boardConfig.length; ++i) {
            value |= ((long)boardConfig[i] << (i << 2));
        }
        return value;
    }

    public static int getElementAt(final long boardConfig, final int index) {
    	return (int)((boardConfig >> (index << 2)) & 0xF);
    }

    public static void displayStats(final byte[] initState) {
        final int numOfTiles = initState.length;
        System.out.println();
        System.out.println("Puzzle type:          " + (numOfTiles - 1) +
            "-puzzle");
        System.out.print("Initial permutation:  ");
        System.out.println(byteArrayToString(initState));
        System.out.print("Goal state:           ");
        for (int i = 0; i < numOfTiles; ++i) {
            if (i != 0) {
                System.out.print(",");
            }
            System.out.print((byte)((PuzzleConfiguration.getGoalState() >> (i << 2)) & 0xF));
        }
        System.out.println();
        System.out.println(
            "Elapsed time:         " +
            DEC_FORMATTER.format(Algorithm.getRunningTimeInSeconds()) + " s");
        System.out.println(
            "Paths visited:        " + INT_FORMATTER.format(Algorithm.numberVisited));
        System.out.println(
            "States explored:      " + INT_FORMATTER.format(Algorithm.numberExpanded));
        final int numOfMoves = Algorithm.shortestPath.length();
        if (numOfMoves == 1) {
            System.out.println(
                "Shortest path:        " + numOfMoves + " move");
        } else {
            System.out.println(
                "Shortest path:        " + numOfMoves + " moves");
        }
        final String[] directions = getDirections(initState);
        for (int i = 0; i < directions.length; ++i) {
            System.out.println(directions[i]);
        }
    }

    public static String[] getDirections(final byte[] initState) {
        final int pathLength = Algorithm.shortestPath.length();
        final String[] directions = new String[pathLength];
        if (pathLength != 0) {
            final byte[] tiles = getMovedTiles(Algorithm.shortestPath, initState);
            for (int i = 0; i < pathLength; ++i) {
                final byte tile = tiles[i];
                final char dir = Algorithm.shortestPath.charAt(i);
                String direction;
                if (dir == 'L') {
                    direction = "right";
                } else if (dir == 'R') {
                    direction = "left";
                } else if (dir == 'U') {
                    direction = "down";
                } else {
                    direction = "up";
                }
                final StringBuilder builder = new StringBuilder();
                final int iPlusOne = i + 1;
                if (iPlusOne < 10) {
                    builder.append(" ");
                }
                if (tile < 10) {
                    builder.append(iPlusOne + ".  " + tile + " - " + direction);
                } else {
                    builder.append(iPlusOne + ". " + tile + " - " + direction);
                }
                directions[i] = builder.toString();
            }
        }
        return directions;
    }

    public static byte[] getArray(final String arrayString, final int numOfTiles)
            throws IllegalArgumentException {

        final StringTokenizer st = new StringTokenizer(arrayString, " ,");
        final int numOfTokens = st.countTokens();

        // Create an array of String representations of the tile numbers.
        final String[] numStrings = new String[numOfTokens];
        int i = 0;
        while (st.hasMoreTokens()) {
            final String s = st.nextToken().trim();
            if (s.length() > 0) {
                numStrings[i++] = s;
            }
        }

        // Validate the number of tiles entered.
        if (i == 0) {
            throw new IllegalArgumentException("Input contains no tiles.");
        } else if (i < numOfTiles) {
            throw new IllegalArgumentException("Input contains only " + i +
                " of the " + numOfTiles + " tiles.");
        } else if (i > numOfTiles) {
            throw new IllegalArgumentException("Input exceeds required " +
                numOfTiles + " tiles.");
        }

        // Make sure each string is a number.
        final byte[] tiles = new byte[numOfTiles];
        final int[] tilePositions = new int[numOfTiles];
        for (i = 0; i < tiles.length; ++i) {
            tiles[i] = -1;
            tilePositions[i] = -1;
        }
        for (i = 0; i < numStrings.length; ++i) {
            try {
                final byte tile = Byte.parseByte(numStrings[i]);
                if (tile < 0 || tile >= numOfTiles) {
                    throw new IllegalArgumentException("Tile " + tile +
                        " at index " + i + " is out of range.");
                }
                tiles[i] = tile;
                tilePositions[tile] = i;
            } catch (final NumberFormatException nfe) {
                throw new IllegalArgumentException("Expected integer at index " + (i + 1) +
                    ", received '" + numStrings[i] + "'.");
            }
        }

        // Make sure no tile number is missing from the input.
        for (i = 0; i < numOfTiles; ++i) {
            if (tilePositions[i] == -1) {
                throw new IllegalArgumentException("Tile " + i + " is missing from input.");
            }
        }

        return tiles;
    }

    public static void generateNextState(final char direction, final byte[] state) {
        final int p = Utility.posOfElement((byte)0, state),
                  dimension = (int)Math.sqrt(state.length);
        switch (direction) {
            case 'U':
                swap(p, p - dimension, state);
                break;
            case 'D':
                swap(p, p + dimension, state);
                break;
            case 'L':
                swap(p, p - 1, state);
                break;
            case 'R':
                swap(p, p + 1, state);
                break;
            default:
                break;
        }
    }

    public static int getDefaultNumOfThreads() {
        // If there's only one processor, there is no sense in creating extra
        // threads. If there are multiple processors, creating more threads than
        // processors tends to keep the utilization higher during each iteration
        // of IDA*, since we are less likely to run into the case where some of
        // the threads for a given iteration have completed and processors are
        // sitting idle.
        final Runtime runtime = Runtime.getRuntime();
        final int numOfProcessors = runtime.availableProcessors();
        return numOfProcessors == 1 ? 1 : numOfProcessors * 2;
    }

    public static boolean getHasFullPermission() {
        final SecurityManager manager = System.getSecurityManager();
        if (manager == null) {
            return false;
        }

        try {
            manager.checkPermission(new java.security.AllPermission());
        } catch (final SecurityException se) {
            return false;
        } catch (final NullPointerException npe) {
            return false;
        }

        return true;
    }

    private static int posOfElement(final byte element, final byte[] state) {
        for (int i = state.length - 1; i >= 0; --i) {
            if (state[i] == element) {
                return i;
            }
        }
        return -1;
    }

    private static byte[] getMovedTiles(final String pathStr, final byte[] initState) {
        final int pathLength = pathStr.length(),
                  boardLength = initState.length,
                  dimension = PuzzleConfiguration.getDimension();
        final byte[] movedTiles = new byte[pathLength],
                     boardConfig = new byte[boardLength];

        int posOfSpace = -1;
        for (int i = boardLength - 1; i >= 0; --i) {
            final byte tile = initState[i];
            if (tile == 0) {
                posOfSpace = i;
            }
            boardConfig[i] = tile;
        }

        for (int i = 0; i < pathLength; ++i) {
            final char dir = pathStr.charAt(i);
            int posOfTile;
            if (dir == 'L') {
                posOfTile = posOfSpace - 1;
            } else if (dir == 'R') {
                posOfTile = posOfSpace + 1;
            } else if (dir == 'U') {
                posOfTile = posOfSpace - dimension;
            } else {
                posOfTile = posOfSpace + dimension;
            }
            movedTiles[i] = boardConfig[posOfTile];
            swap(posOfSpace, posOfTile, boardConfig);
            posOfSpace = posOfTile;
        }
        return movedTiles;
    }

    private static void swap(final int i, final int j, final byte[] A) {
        final byte temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}