package puzzle;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Examples:
 * When generating a complete pattern database (i.e. no dummy tiles):
 *  java PatternDatabaseGenerator 8 1,2,3,4,5,6,7,8,0 8-puzzle.db
 *
 * When generating a disjoint additive pattern database (i.e. dummy tiles 'x'):
 *  java PatternDatabaseGenerator 15 0,2,3,4,x,x,x,x,x,x,x,x,x,x,x,0     15-puzzle-663-0.db
 *  java PatternDatabaseGenerator 15 1,x,x,x,5,6,x,x,9,10,x,x,13,x,x,0   15-puzzle-663-1.db
 *  java PatternDatabaseGenerator 15 x,x,x,x,x,x,7,8,x,x,11,12,x,14,15,0 15-puzzle-663-2.db
 */
public final class PatternDatabaseGenerator {
    public static final byte KEY_NOT_FOUND = -1;

    final int numOfTiles, numOfTilesMinusOne, dimension;

    PrimitiveHashMap tempMap;
    List<Entry> stateToCostEntries_8_puzzle;
    byte[] costTable_15_puzzle;
    long[] configTable_15_puzzle;

    public PatternDatabaseGenerator(final int numOfTiles, final long boardConfig,
                                    final byte dummyTile, final String filename) {

        this.numOfTiles = Node.numOfTiles = numOfTiles;
        this.numOfTilesMinusOne = numOfTiles - 1;
        this.dimension = Node.dimension = (int)Math.sqrt(numOfTiles);

        DataOutputStream dos = null;
        if (filename != null) {
            try {
                dos = new DataOutputStream(new BufferedOutputStream(
                                           new FileOutputStream(filename)));
            } catch (final FileNotFoundException fnfe) {
                System.err.println("Error: Cannot open file '" + filename + "' for output.");
                System.exit(1);
            }
        }

        if (numOfTilesMinusOne == 15) {
            generateFifteenPuzzleDB(dummyTile, boardConfig);
            outputFifteenPuzzleData(filename, dos);
        } else {
            generateEightPuzzleDB(boardConfig);
            outputEightPuzzleData(filename, dos);
        }
    }

    private void generateFifteenPuzzleDB(final byte dummyTile,
                                         final long boardConfig) {

        final boolean[] tilesInSubset = computeSubset(dummyTile, boardConfig);
        final int[] tilePositions = new int[tilesInSubset.length];
        int numTilesInSubset = 0;
        for (int i = 0; i < tilePositions.length; ++i) {
            if (tilesInSubset[i]) {
                tilePositions[i] = numTilesInSubset++;
            }
        }
        breadthFirstSearch(boardConfig, tilesInSubset);
        final int tableLength = (int)Math.pow(2, numTilesInSubset * 4);
        costTable_15_puzzle = new byte[tableLength];
        configTable_15_puzzle = new long[tableLength];
        for (int i = tableLength - 1; i >= 0; --i) {
            costTable_15_puzzle[i] = KEY_NOT_FOUND;
        }

        System.err.println("Total states visited: " + tempMap.size());
        System.err.print("Removing duplicates...");
        final Set<Entry> entries = tempMap.entrySet();
        final Iterator<Entry> it = entries.iterator();
        while (it.hasNext()) {
            final Entry entry = it.next();
            final long config = entry.getKey();
            final byte movesRequired = entry.getValue();

            final int index = indexFor(config, true, tilesInSubset, tilePositions);
            final byte moves = costTable_15_puzzle[index];
            if (moves == KEY_NOT_FOUND || movesRequired < moves) {
                configTable_15_puzzle[index] = config;
                costTable_15_puzzle[index] = movesRequired;
            }
        }

        int numElements = 0;
        for (int i = tableLength - 1; i >= 0; --i) {
            if (costTable_15_puzzle[i] != KEY_NOT_FOUND) {
                ++numElements;
            }
        }

        System.err.println("done");
        System.err.println("States in subset: " + numElements);
    }

    private void generateEightPuzzleDB(final long boardConfig) {
        breadthFirstSearch(boardConfig, null);
        final PrimitiveHashMap costMap_8_puzzle = new PrimitiveHashMap();

        System.err.println("Total states visited: " + tempMap.size());
        System.err.print("Removing duplicates...");
        final Set<Entry> entries = tempMap.entrySet();
        final Iterator<Entry> it = entries.iterator();
        while (it.hasNext()) {
            final Entry entry = it.next();
            final long config = entry.getKey();
            final byte movesRequired = entry.getValue();

            final byte moves = costMap_8_puzzle.get(config);
            if (moves == PrimitiveHashMap.KEY_NOT_FOUND || movesRequired < moves) {
                costMap_8_puzzle.put(config, movesRequired);
            }
            it.remove();
        }
        System.err.println("done");

        final int numElements = costMap_8_puzzle.size();
        stateToCostEntries_8_puzzle = new LinkedList<Entry>(costMap_8_puzzle.entrySet());
        System.err.print("Sorting entries...");
        Collections.sort(stateToCostEntries_8_puzzle, new Comparator<Entry>() {
            public int compare(final Entry e1, final Entry e2) {
                return e1.getValue() - e2.getValue();
            }
        });
        System.err.println("done");

        System.err.println("States in subset: " + numElements);
    }

    private void outputFifteenPuzzleData(final String filename,
                                         final DataOutputStream dos) {
        System.err.print("Writing file...");
        if (filename == null) {
            // Write values to stdout. User can redirect stdout to a file, if desired.
            for (int i = 0; i < configTable_15_puzzle.length; ++i) {
                final long config = configTable_15_puzzle[i];
                System.out.println((i + 1) + "," + config + "," +
                    costTable_15_puzzle[i] + "," +
                    Utility.longToString(config, numOfTiles));
            }
        } else {
            int i = 0;
            try {
                for ( ; i < costTable_15_puzzle.length; ++i) {
                    dos.writeByte(costTable_15_puzzle[i]);
                }
            } catch (final IOException ioe) {
                System.err.println("Error: Cannot write entry " + i + " to file.");
                System.exit(1);
            } finally {
                try {
                    if (dos != null) {
                        dos.close();
                    }
                } catch (final IOException ioe) { }
            }
        }
        System.err.println("done");
    }

    private void outputEightPuzzleData(final String filename,
                                       final DataOutputStream dos) {
        System.err.print("Writing file...");
        final Iterator<Entry> listIter = stateToCostEntries_8_puzzle.iterator();
        if (filename == null) {
            // Write values to stdout. User can redirect stdout to a file, if desired.
            int i = 1;
            while (listIter.hasNext()) {
                final Entry entry = listIter.next();
                final long config = entry.getKey();
                System.out.println(i + "," + config + "," + entry.getValue() +
                    "," + Utility.longToString(config, numOfTiles));
                ++i;
            }
        } else {
            int i = 0;
            try {
                while (listIter.hasNext()) {
                    final Entry entry = listIter.next();
                    dos.writeLong(((Long)entry.getKey()).longValue());
                    dos.writeByte(((Byte)entry.getValue()).byteValue());
                    ++i;
                }
            } catch (final IOException ioe) {
                System.err.println("Error: Cannot write entry " + i + " to file.");
                System.exit(1);
            } finally {
                try {
                    if (dos != null) {
                        dos.close();
                    }
                } catch (final IOException ioe) { }
            }
        }
        System.err.println("done");
    }

    private void breadthFirstSearch(final long boardConfig,
                                    final boolean[] tilesInSubset) {
        BFSNode currentState = new BFSNode(boardConfig);
        currentState.cost = 0;

        tempMap = new PrimitiveHashMap();
        tempMap.put(boardConfig, (byte)0);

        final Queue<BFSNode> queue = new LinkedList<BFSNode>();
        queue.add(currentState);

        byte previous = 1;
        while (true) {
            final char fromDirection = currentState.direction;

            if (fromDirection != 'R') {
                final BFSNode left = currentState.moveLeftNode(tilesInSubset);
                if (left != null) {
                    final byte moves = tempMap.get(left.boardConfig);
                    if (moves == PrimitiveHashMap.KEY_NOT_FOUND || left.cost < moves) {
                        tempMap.put(left.boardConfig, left.cost);
                        queue.add(left);
                    }
                }
            }

            if (fromDirection != 'L') {
                final BFSNode right = currentState.moveRightNode(tilesInSubset);
                if (right != null) {
                    final byte moves = tempMap.get(right.boardConfig);
                    if (moves == PrimitiveHashMap.KEY_NOT_FOUND || right.cost < moves) {
                        tempMap.put(right.boardConfig, right.cost);
                        queue.add(right);
                    }
                }
            }

            if (fromDirection != 'D') {
                final BFSNode up = currentState.moveUpNode(tilesInSubset);
                if (up != null) {
                    final byte moves = tempMap.get(up.boardConfig);
                    if (moves == PrimitiveHashMap.KEY_NOT_FOUND || up.cost < moves) {
                        tempMap.put(up.boardConfig, up.cost);
                        queue.add(up);
                    }
                }
            }

            if (fromDirection != 'U') {
                final BFSNode down = currentState.moveDownNode(tilesInSubset);
                if (down != null) {
                    final byte moves = tempMap.get(down.boardConfig);
                    if (moves == PrimitiveHashMap.KEY_NOT_FOUND || down.cost < moves) {
                        tempMap.put(down.boardConfig, down.cost);
                        queue.add(down);
                    }
                }
            }

            if (!queue.isEmpty()) {
                currentState = queue.remove();
                final byte movesRequired = currentState.cost;
                if (movesRequired > previous) {
                    System.err.println("Generating boards with up to " + previous +
                        " moves; map size: " + tempMap.size());
                    previous = movesRequired;
                }
            } else {
                break;
            }
        }
    }

    private int indexFor(final long boardConfig, final boolean isFifteenPuzzle,
                         final boolean[] tilesInSubset, final int[] tilePositions) {
        if (isFifteenPuzzle) {
            int hashCode = 0;
            for (int i = numOfTilesMinusOne; i >= 0; --i) {
                final int tile = (int)((boardConfig >> (i << 2)) & 0xF);
                if (tilesInSubset[tile]) {
                    hashCode |= i << (tilePositions[tile] << 2);
                }
            }
            return hashCode;
        }
        return (int)boardConfig;
    }

    private boolean[] computeSubset(final byte dummyValue, final long boardConfig) {
        final boolean[] tilesInSubset = new boolean[numOfTiles];
        for (int pos = numOfTiles - 1; pos >= 0; --pos) {
            final byte tile = (byte)((boardConfig >> (pos << 2)) & 0xF);
            if (tile != dummyValue && tile != 0) {
                tilesInSubset[tile] = true;
            }
        }
        return tilesInSubset;
    }

    private static byte getArray(final String arrayString, final byte[] tiles,
                                 final int numOfTiles) {

        final StringTokenizer st = new StringTokenizer(arrayString, ",");
        final int numOfTokens = st.countTokens();

        // Validate the number of tiles entered.
        if (numOfTokens < numOfTiles) {
            System.out.println("Error: Input contains only " + numOfTokens +
                " of the " + numOfTiles + " tiles.");
            System.exit(1);
        } else if (numOfTokens > numOfTiles) {
            System.out.println("Error: Input exceeds required " +
                numOfTiles + " tiles.");
            System.exit(1);
        }

        // Create an array of String representations of the tile numbers.
        final String[] numStrings = new String[numOfTokens];
        int i = 0;
        while (st.hasMoreTokens()) {
            numStrings[i++] = st.nextToken();
        }

        // Make sure each string is a number.
        final int[] tilePositions = new int[numOfTiles];
        for (i = 0; i < tiles.length; ++i) {
            tiles[i] = -1;
            tilePositions[i] = -1;
        }
        for (i = 0; i < numStrings.length; ++i) {
            final String s = numStrings[i];
            try {
                final byte tile = Byte.parseByte(s);
                tiles[i] = tile;
                tilePositions[tile] = i;
            } catch (final NumberFormatException nfe) {
                if (!s.trim().toLowerCase().equals("x")) {
                    System.err.println("Error: Expected integer or 'X' at index " + (i + 1) +
                        ", received '" + numStrings[i] + "'.");
                    System.exit(1);
                }
            }
        }

        byte dummyTile = -1;
        // Make sure no tile number is missing from the input.
        for (i = 0; i < numOfTiles; ++i) {
            if (tilePositions[i] == -1) {
                if (i == 0) {
                    System.err.println("Error: Tile 0 is missing for input.");
                    System.exit(1);
                }
                dummyTile = (byte)i;
                break;
            }
        }

        // Replace 'X' (-1) tiles with the dummy value.
        for (i = 0; i < tiles.length; ++i) {
            if (tiles[i] == -1) {
                tiles[i] = dummyTile;
            }
        }

        return dummyTile;
    }

    public static void main(final String[] args) {
        if (args.length < 2 || args.length > 3) {
            System.err.println(
                "Usage: java PatternDatabaseGenerator <num of tiles> <tile order> [filename]");
            System.exit(1);
        }
        int puzzleSize = 0;
        try {
            puzzleSize = Integer.parseInt(args[0].trim());
            if ((puzzleSize != 8) && (puzzleSize != 15)) {
                System.err.println("Error: Puzzle size must be either 8 or 15.");
                System.exit(1);
            }
            ++puzzleSize;
        } catch (final NumberFormatException nfe) {
            System.err.println("Error: Puzzle size must be either 8 or 15.");
            System.exit(1);
        }

        final String tileOrder = args[1].trim();
        final byte[] tiles = new byte[puzzleSize];
        final byte dummyTile = getArray(tileOrder, tiles, puzzleSize);
        System.err.println("Using dummy tile: " + dummyTile);
        String filename = null;
        if (args.length == 3) {
            filename = args[2];
        }
        new PatternDatabaseGenerator(
                puzzleSize, Utility.byteArrayToLong(tiles), dummyTile, filename);
    }
}