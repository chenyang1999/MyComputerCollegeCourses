package puzzle;
final class BFSNode extends Node {
    long boardConfig;
    char direction = 'X';
    byte cost;
    StringBuilder path;
    final boolean storePath;

    public BFSNode(final long boardConfig) {
        this.boardConfig = boardConfig;
        this.storePath = false;
    }

    public BFSNode(final long boardConfig, final boolean storePath) {
        this.boardConfig = boardConfig;
        this.storePath = storePath;
        if (storePath) {
            this.path = new StringBuilder(String.valueOf(direction));
        }
    }

    public BFSNode(final BFSNode node) {
        this.boardConfig = node.boardConfig;
        this.direction = node.direction;
        this.cost = node.cost;
        this.storePath = node.storePath;
        if (storePath) {
            this.path = new StringBuilder(node.path.toString());
        }
    }

    public int hashCode() {
        return Entry.keyHashCode(boardConfig);
    }

    public boolean equals(final Object o) {
        if (o instanceof BFSNode) {
            final BFSNode n = (BFSNode)o;
            if (this.boardConfig == n.boardConfig) return true;
        }
        return false;
    }

    public String getShortestPath() {
        return path.substring(1);
    }

    public String getPath() {
        return path.toString();
    }

    public BFSNode moveLeftNode(final boolean[] tilesInSubset) {
        final int posOfSpace = posOfSpace();
        if (posOfSpace % dimension == 0) {
            return null;
        }
        final BFSNode copy = new BFSNode(this);

        // Swap tile with space.
        final int posTimes4 = posOfSpace << 2,
                  posMinusOneTimes4 = (posOfSpace - 1) << 2;
        final long space = (copy.boardConfig >> posTimes4) & 0xF,
                   tile = (copy.boardConfig >> posMinusOneTimes4) & 0xF;

        final long zeroBitTile = (long)0xF << posMinusOneTimes4;
        copy.boardConfig = copy.boardConfig & ~zeroBitTile |
            (tile << posTimes4) | (space << posMinusOneTimes4);
        copy.direction = 'L';
        if (storePath) {
            copy.path.append('L');
        }

        if (tilesInSubset == null || tilesInSubset[(int)tile]) {
            ++copy.cost;
        }

        return copy;
    }

    public BFSNode moveRightNode(final boolean[] tilesInSubset) {
        final int posOfSpace = posOfSpace(),
            posPlusOne = posOfSpace + 1;
        if (posPlusOne % dimension == 0) {
            return null;
        }
        final BFSNode copy = new BFSNode(this);

        // Swap tile with space.
        final int posTimes4 = posOfSpace << 2,
                  posPlusOneTimes4 = posPlusOne << 2;
        final long space = (copy.boardConfig >> posTimes4) & 0xF,
                   tile = (copy.boardConfig >> posPlusOneTimes4) & 0xF;

        final long zeroBitTile = (long)0xF << posPlusOneTimes4;
        copy.boardConfig = copy.boardConfig & ~zeroBitTile |
            (tile << posTimes4) | (space << posPlusOneTimes4);
        copy.direction = 'R';
        if (storePath) {
            copy.path.append('R');
        }

        if (tilesInSubset == null || tilesInSubset[(int)tile]) {
            ++copy.cost;
        }

        return copy;
    }

    public BFSNode moveUpNode(final boolean[] tilesInSubset) {
        final int posOfSpace = posOfSpace();
        if (posOfSpace < dimension) {
            return null;
        }
        final BFSNode copy = new BFSNode(this);

        // Swap tile with space.
        final int posTimes4 = posOfSpace << 2,
                  posMinusDimTimes4 = (posOfSpace - dimension) << 2;
        final long space = (copy.boardConfig >> posTimes4) & 0xF,
                   tile = (copy.boardConfig >> posMinusDimTimes4) & 0xF;

        final long zeroBitTile = (long)0xF << posMinusDimTimes4;
        copy.boardConfig = copy.boardConfig & ~zeroBitTile |
             (tile << posTimes4) | (space << posMinusDimTimes4);
        copy.direction = 'U';
        if (storePath) {
            copy.path.append('U');
        }

        if (tilesInSubset == null || tilesInSubset[(int)tile]) {
            ++copy.cost;
        }

        return copy;
    }

    public BFSNode moveDownNode(final boolean[] tilesInSubset) {
        final int posOfSpace = posOfSpace();
        if (posOfSpace >= numOfTiles - dimension) {
            return null;
        }
        final BFSNode copy = new BFSNode(this);

        // Swap tile with space.
        final int posTimes4 = posOfSpace << 2,
                  posPlusDimTimes4 = (posOfSpace + dimension) << 2;
        final long space = (copy.boardConfig >> posTimes4) & 0xF,
                   tile = (copy.boardConfig >> posPlusDimTimes4) & 0xF;

        final long zeroBitTile = (long)0xF << posPlusDimTimes4;
        copy.boardConfig = copy.boardConfig & ~zeroBitTile |
            (tile << posTimes4) | (space << posPlusDimTimes4);
        copy.direction = 'D';
        if (storePath) {
            copy.path.append('D');
        }

        if (tilesInSubset == null || tilesInSubset[(int)tile]) {
            ++copy.cost;
        }

        return copy;
    }

    private int posOfSpace() {
        for (int i = numOfTiles - 1; i >= 0; i--) {
            if ((byte)((boardConfig >> (i << 2)) & 0xF) == 0) {
                return i;
            }
        }
        return -1;
    }
}