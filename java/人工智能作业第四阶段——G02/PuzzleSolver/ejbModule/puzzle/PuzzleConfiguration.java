package puzzle;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public final class PuzzleConfiguration {
    public static final int PUZZLE_8  = 0,
                            PUZZLE_15 = 1,
                            ALGORITHM_ASTAR   = 0,
                            ALGORITHM_IDASTAR = 1,
                            HEURISTIC_PD = 0x1,
                            HEURISTIC_MD = 0x2,
                            HEURISTIC_LC = 0x4;

    public static final byte[] costTable_15_puzzle_0 = new byte[4096],
                               costTable_15_puzzle_1 = new byte[16777216],
                               costTable_15_puzzle_2 = new byte[16777216];
    public static final PrimitiveHashMap patternDatabase_8_puzzle =
        new PrimitiveHashMap(262144, 1.0f);

    private static int numOfTiles /* 9, 16 */,
                       dimension,
                       algorithmType,
                       heuristicType,
                       numOfThreads;
    private static long goalState, goalStatePositions;
    private static Algorithm algorithm;
    private static boolean isVerbose = false;

    static {
        loadStreamCostTable("databases/15-puzzle-663-0.db", costTable_15_puzzle_0);
        loadStreamCostTable("databases/15-puzzle-663-1.db", costTable_15_puzzle_1);
        loadStreamCostTable("databases/15-puzzle-663-2.db", costTable_15_puzzle_2);
        loadStreamPatternDatabase("databases/8-puzzle.db", patternDatabase_8_puzzle);
    }

    private PuzzleConfiguration() { }

    public static void initialize(
            final int puzzleType, final int algorithmType, final int heuristicType,
            final int numOfThreads) {
    	IDAStar ida = null;
        PuzzleConfiguration.numOfTiles = puzzleType == PUZZLE_15 ? 16: 9;
        PuzzleConfiguration.algorithmType = algorithmType;
		if(algorithmType != ALGORITHM_ASTAR)
        	ida = new IDAStar();
		PuzzleConfiguration.algorithm = ida;
        PuzzleConfiguration.heuristicType = heuristicType;
        PuzzleConfiguration.numOfThreads = numOfThreads;
        PuzzleConfiguration.dimension = (int)Math.sqrt(numOfTiles);
        initializeGoalState(numOfTiles);
    }

    public static void setVerbose(final boolean isVerbose) {
        PuzzleConfiguration.isVerbose = isVerbose;
    }

    public static void setNumOfThreads(final int numOfThreads) {
        PuzzleConfiguration.numOfThreads = numOfThreads;
    }

    public static boolean isVerbose() {
        return isVerbose;
    }

    public static Algorithm getAlgorithm() {
        return algorithm;
    }

    public static int getHeuristic() {
        return heuristicType;
    }

    public static int getNumOfTiles() {
        return numOfTiles;
    }

    public static int getNumOfThreads() {
        return numOfThreads;
    }

    public static int getDimension() {
        return dimension;
    }

    public static long getGoalState() {
        return goalState;
    }

    public static long getGoalStatePositions() {
        return goalStatePositions;
    }

    public static String stringRepresentation() {
        final StringBuilder builder = new StringBuilder();
        switch (PuzzleConfiguration.numOfTiles) {
            case 9:
                builder.append("8-puzzle");
                break;
            case 16:
                builder.append("15-puzzle");
                break;
            default:
                return "Unknown puzzle type";
        }
        switch (PuzzleConfiguration.algorithmType) {
            case ALGORITHM_ASTAR:
                builder.append(", A*");
                break;
            case ALGORITHM_IDASTAR:
                builder.append(", IDA*");
                break;
            default:
                return "Unknown algorithm type";
        }
        switch (PuzzleConfiguration.heuristicType) {
            case HEURISTIC_PD:
                builder.append(" / Pattern Database");
                break;
            case HEURISTIC_MD:
                builder.append(" / Manhattan Distance");
                break;
            case HEURISTIC_LC:
                builder.append(" / Manhattan Distance + Linear Conflict");
                break;
            default:
                return "Unknown heuristic type";
        }
        if (PuzzleConfiguration.algorithmType == ALGORITHM_IDASTAR) {
            final int numOfThreads = PuzzleConfiguration.getNumOfThreads();
            if (numOfThreads == 1) {
                builder.append(" / Single-threaded");
            } else {
                builder.append(" / Multi-threaded (" + numOfThreads + ")");
            }
        }

        return builder.toString();
    }

    public static void initializeGoalState(final int numOfTiles) {
        goalState = 0;
        final int numOfTilesMinusOne = numOfTiles - 1;
        for (int i = 0; i < numOfTiles; ++i) {
            if (i != numOfTilesMinusOne) {
                final int iPlusOne = i + 1;
                goalState |= ((long)iPlusOne << (i << 2));
            } else {
                goalState |= ((long)0 << (i << 2));
            }
        }

        goalStatePositions =
        	Utility.getPositionsAsLong(goalState, numOfTilesMinusOne);
    }

    private static void loadStreamPatternDatabase(final String filename,
                                                  final PrimitiveHashMap patternDatabase) {
        InputStream is = PuzzleConfiguration.class.getResourceAsStream(filename);
        DataInputStream dis = null;
        try {
            if (is == null) {
                is = new FileInputStream(filename);
            }
            dis = new DataInputStream(new BufferedInputStream(is));
            while (true) {
                final long pattern = dis.readLong();
                final byte cost = dis.readByte();
                patternDatabase.put(pattern, cost);
            }
        } catch (final EOFException eofe) {

        } catch (final FileNotFoundException fnfe) {
            System.err.println("Error: Cannot find file " + filename + ".");
            System.exit(1);
        } catch (final IOException ioe) {
            System.err.println("Error: Cannot read from file " + filename + ".");
            System.exit(1);
        } finally {
            try {
                if (dis != null) {
                    dis.close();
                }
            } catch (final IOException ioe) { }
        }
    }

    private static void loadStreamCostTable(final String filename,
                                            final byte[] costTable) {
        InputStream is = PuzzleConfiguration.class.getResourceAsStream(filename);
        DataInputStream dis = null;
        try {
            if (is == null) {
                is = new FileInputStream(filename);
            }
            dis = new DataInputStream(new BufferedInputStream(is));
            int i = 0;
            while (true) {
                costTable[i++] = dis.readByte();
            }
        } catch (final EOFException eofe) {

        } catch (final FileNotFoundException fnfe) {
            System.err.println("Error: Cannot find file " + filename + ".");
            System.exit(1);
        } catch (final IOException ioe) {
            System.err.println("Error: Cannot read from file " + filename + ".");
            System.exit(1);
        } finally {
            try {
                if (dis != null) {
                    dis.close();
                }
            } catch (final IOException ioe) { }
        }
    }
}