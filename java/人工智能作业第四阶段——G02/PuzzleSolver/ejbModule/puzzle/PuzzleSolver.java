/**

 * Command line application
 *
 * Some example configurations:
 *    0,4,2,3,13,8,7,6,5,10,11,1,9,12,15,14 - 52 moves
 *    12,8,3,13,10,6,15,2,4,5,1,9,11,14,7,0 - 62 moves
 *    1,5,9,13,2,6,10,14,3,7,11,15,4,8,12,0 - 72 moves - difficult!
 *    0,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1 - 78 moves
 *    0,12,9,13,15,11,10,14,7,8,5,6,4,3,2,1 - 80 moves
 */
package puzzle;
public final class PuzzleSolver {

    public PuzzleSolver(final byte[] initState, final int algorithmType,
                        final int heuristic, final int numOfThreads) {
        final int puzzleType = initState.length == 16 ? PuzzleConfiguration.PUZZLE_15 :
                                                        PuzzleConfiguration.PUZZLE_8;
        PuzzleConfiguration.setVerbose(true);
        PuzzleConfiguration.initialize(puzzleType, algorithmType, heuristic, numOfThreads);
        PuzzleConfiguration.getAlgorithm().solve(
            Utility.arrayToLong(initState), numOfThreads);
        Utility.displayStats(initState);
    }

    public static void main(final String[] args) {
        if (args.length < 4) {
            System.err.println(
                "Usage: java PuzzleSolver <num of tiles> <algorithm> <heuristic> " +
                "[num threads] <tile order>");
            System.exit(1);
        }

        int puzzleSize = 0;
        try {
            puzzleSize = Integer.parseInt(args[0]);
            if ((puzzleSize != 8) && (puzzleSize != 15)) {
                System.err.println("Error: Puzzle size must be either 8 or 15.");
                System.exit(1);
            }
            ++puzzleSize;
        } catch (final NumberFormatException nfe) {
            System.err.println("Error: Puzzle size must be either 8 or 15.");
            System.exit(1);
        }

        final String algorithmStr = args[1].toLowerCase();
        int algorithm = PuzzleConfiguration.ALGORITHM_ASTAR;
        if (algorithmStr.equals("i")) {
            algorithm = PuzzleConfiguration.ALGORITHM_IDASTAR;
        } else if (algorithmStr.equals("a")) {
            if (puzzleSize == 15) {
                System.err.println("Error: A* cannot be used with the 15-puzzle.");
                System.exit(1);
            }
        } else {
            System.err.println("Error: Algorithm type must be 'A' or 'I'.");
            System.exit(1);
        }

        final String heuristicStr = args[2].toLowerCase();
        int heuristic = PuzzleConfiguration.HEURISTIC_PD;
        if (heuristicStr.equals("l")) {
            heuristic = PuzzleConfiguration.HEURISTIC_LC;
        } else if (heuristicStr.equals("m")) {
            heuristic = PuzzleConfiguration.HEURISTIC_MD;
        } else if (!heuristicStr.equals("p")){
            System.err.println("Error: Heuristic type must be 'P', 'M', or 'L'.");
            System.exit(1);
        }

        int numOfThreads = Utility.getDefaultNumOfThreads(), lastArgIndex = 3;
        if (algorithmStr.equals("i")) {
            try {
                numOfThreads = Integer.parseInt(args[lastArgIndex]);
                ++lastArgIndex;
            } catch (final NumberFormatException nfe) { }
        }

        final String tileOrder = args[lastArgIndex].trim();
        byte[] tiles = null;
        if (tileOrder.toLowerCase().equals("r0")) {
            tiles = Utility.getRandomArray(puzzleSize, true);
        } else if (tileOrder.toLowerCase().equals("r")) {
            tiles = Utility.getRandomArray(puzzleSize, false);
        } else {
            try {
                tiles = Utility.getArray(tileOrder, puzzleSize);
            } catch (final IllegalArgumentException iae) {
                System.err.println("Error: " + iae.getMessage());
                System.exit(1);
            }
        }
        if (Utility.isValidPermutation(tiles)) {
            new PuzzleSolver(tiles, algorithm, heuristic, numOfThreads);
        } else {
            System.out.println("Puzzle configuration is unsolvable.");
        }
    }
}