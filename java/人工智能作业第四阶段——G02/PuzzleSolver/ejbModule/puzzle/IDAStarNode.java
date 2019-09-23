package puzzle;
public final class IDAStarNode extends Node {

    public static long moveLeft(final long boardConfig, final int posOfSpace) {
        if (posOfSpace % dimension == 0) {
            return 0;
        }
        // Swap tile with space.
        final int posTimes4 = posOfSpace << 2,
                  posMinusOneTimes4 = (posOfSpace - 1) << 2;
        final long space = (boardConfig >> posTimes4) & 0xF,
                   tile = (boardConfig >> posMinusOneTimes4) & 0xF;

        final long zeroBitTile = (long)0xF << posMinusOneTimes4;
        return (boardConfig & ~zeroBitTile) | (tile << posTimes4) |
               (space << posMinusOneTimes4);
    }

    public static long moveRight(final long boardConfig, final int posOfSpace) {
        final int posPlusOne = posOfSpace + 1;
        if (posPlusOne % dimension == 0) {
            return 0;
        }
        // Swap tile with space.
        final int posTimes4 = posOfSpace << 2,
                  posPlusOneTimes4 = posPlusOne << 2;
        final long space = (boardConfig >> posTimes4) & 0xF,
                   tile = (boardConfig >> posPlusOneTimes4) & 0xF;

        final long zeroBitTile = (long)0xF << posPlusOneTimes4;
        return (boardConfig & ~zeroBitTile) | (tile << posTimes4) |
               (space << posPlusOneTimes4);
    }

    public static long moveUp(final long boardConfig, final int posOfSpace) {
        if (posOfSpace < dimension) {
            return 0;
        }
        // Swap tile with space.
        final int posTimes4 = posOfSpace << 2,
                  posMinusDimTimes4 = (posOfSpace - dimension) << 2;
        final long space = (boardConfig >> posTimes4) & 0xF,
                   tile = (boardConfig >> posMinusDimTimes4) & 0xF;

        final long zeroBitTile = (long)0xF << posMinusDimTimes4;
        return (boardConfig & ~zeroBitTile) | (tile << posTimes4) |
               (space << posMinusDimTimes4);
    }

    public static long moveDown(final long boardConfig, final int posOfSpace) {
        if (posOfSpace >= numOfTiles - dimension) {
            return 0;
        }
        // Swap tile with space.
        final int posTimes4 = posOfSpace << 2,
                  posPlusDimTimes4 = (posOfSpace + dimension) << 2;
        final long space = (boardConfig >> posTimes4) & 0xF,
                   tile = (boardConfig >> posPlusDimTimes4) & 0xF;

        final long zeroBitTile = (long)0xF << posPlusDimTimes4;
        return (boardConfig & ~zeroBitTile) | (tile << posTimes4) |
               (space << posPlusDimTimes4);
    }
}