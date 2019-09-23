package puzzle;
public final class Entry {
    final long key;
    final int hash;
    byte value;
    Entry next;

    Entry(final int h, final long k, final byte v, final Entry n) {
        value = v;
        next = n;
        key = k;
        hash = h;
    }

    public long getKey() {
        return key;
    }

    public byte getValue() {
        return value;
    }

    public byte setValue(final byte newValue) {
        final byte oldValue = value;
        value = newValue;
        return oldValue;
    }

    public boolean equals(final Object o) {
        if (!(o instanceof Entry)) return false;
        final Entry e = (Entry)o;
        if (key == e.key) {
            if (value == e.value) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return keyHashCode(key);
    }

    public String toString() {
        return key + " = " + value;
    }

    /**
     * http://www.concentric.net/~ttwang/tech/inthash.htm
     */
    public static int keyHashCode(long key) {
        key = (~key) + (key << 18); // key = (key << 18) - key - 1;
        key = key ^ (key >>> 31);
        key = key * 21;             // key = (key + (key << 2)) + (key << 4);
        key = key ^ (key >>> 11);
        key = key + (key << 6);
        key = key ^ (key >>> 22);
        return (int)key;
    }

    public static int valueHashCode(final byte value) {
        return value;
    }
}