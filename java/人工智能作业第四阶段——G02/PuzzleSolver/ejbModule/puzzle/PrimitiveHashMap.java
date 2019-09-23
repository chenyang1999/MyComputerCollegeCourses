package puzzle;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public final class PrimitiveHashMap {
    public static final byte KEY_NOT_FOUND = -1;

    /**
     * The default initial capacity - MUST be a power of two.
     */
    static final int DEFAULT_INITIAL_CAPACITY = 16;

    /**
     * The maximum capacity, used if a higher value is implicitly specified
     * by either of the constructors with arguments.
     * MUST be a power of two <= 1<<30.
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    transient Entry[] table;
    transient int size;
    // Keep this count updated in memory to reduce operations when computing
    // the index for a specified hash.
    transient int tableSizeMinusOne;
    int threshold;
    boolean resizable = true;
    final float loadFactor;

    private transient Set<Entry> entrySet = null;

    public PrimitiveHashMap(int initialCapacity, final float loadFactor) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " +
                                               initialCapacity);
        }
        if (initialCapacity > MAXIMUM_CAPACITY) {
            initialCapacity = MAXIMUM_CAPACITY;
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Illegal load factor: " +
                                               loadFactor);
        }

        // Find a power of 2 >= initialCapacity
        int capacity = 1;
        while (capacity < initialCapacity) {
            capacity <<= 1;
        }

        this.loadFactor = loadFactor;
        threshold = (int)(capacity * loadFactor);
        table = new Entry[capacity];
        tableSizeMinusOne = capacity - 1;
    }

    public PrimitiveHashMap(final int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public PrimitiveHashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        threshold = (int)(DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
        table = new Entry[DEFAULT_INITIAL_CAPACITY];
        tableSizeMinusOne = DEFAULT_INITIAL_CAPACITY - 1;
    }

    public void setResizable(final boolean resizable) {
        this.resizable = resizable;
    }

    public int size() {
        return size;
    }

    public int tableSize() {
        return table.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getCountOfEmptyCells() {
        int emptyCount = 0;
        for (int i = table.length - 1; i >= 0; --i) {
            if (table[i] == null) {
                ++emptyCount;
            }
        }
        return emptyCount;
    }

    public byte get(final long key) {
        for (Entry e = table[Entry.keyHashCode(key) & tableSizeMinusOne];
             e != null;
             e = e.next) {
            if (e.key == key) {
                return e.value;
            }
        }
        return KEY_NOT_FOUND;
    }

    public void put(final long key, final byte value) {
        final int hash = Entry.keyHashCode(key);
        final int index = hash & tableSizeMinusOne;
        for (Entry e = table[index]; e != null; e = e.next) {
            if (e.key == key) {
                e.value = value;
                return;
            }
        }
        addEntry(hash, key, value, index);
    }

    public Set<Entry> entrySet() {
    	return getEntrySet();
    }

    public void clear() {
        final Entry[] tab = table;
        for (int i = tab.length - 1; i >= 0; --i) {
            tab[i] = null;
        }
        size = 0;
    }

    Entry getEntry(final long key) {
        for (Entry e = table[Entry.keyHashCode(key) & tableSizeMinusOne];
             e != null;
             e = e.next) {
            if (e.key == key) {
                return e;
            }
        }
        return null;
    }

    Entry removeMapping(final Object o) {
        if (!(o instanceof Entry)) {
            return null;
        }

        final Entry entry = (Entry)o;
        final long key = entry.getKey();
        final int hash = Entry.keyHashCode(key);
        final int i = hash & tableSizeMinusOne;
        Entry prev = table[i];
        Entry e = prev;

        while (e != null) {
            final Entry next = e.next;
            if (e.hash == hash && e.equals(entry)) {
                --size;
                if (prev == e) {
                    table[i] = next;
                } else {
                    prev.next = next;
                }
                return e;
            }
            prev = e;
            e = next;
        }

        return e;
    }

    Entry removeEntryForKey(final long key) {
        final int hash = Entry.keyHashCode(key);
        final int i = hash & tableSizeMinusOne;
        Entry prev = table[i];
        Entry e = prev;

        while (e != null) {
            final Entry next = e.next;
            if (e.hash == hash && e.key == key) {
                --size;
                if (prev == e) {
                    table[i] = next;
                } else {
                    prev.next = next;
                }
                return e;
            }
            prev = e;
            e = next;
        }

        return e;
    }

    private void addEntry(final int hash,
                          final long key,
                          final byte value,
                          final int bucketIndex) {
        final Entry e = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, e);
        if (size++ >= threshold && resizable) {
            resize(2 * table.length);
        }
    }

    private void resize(final int newCapacity) {
        final Entry[] oldTable = table;
        final int oldCapacity = oldTable.length;
        if (oldCapacity == MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return;
        }

        final Entry[] newTable = new Entry[newCapacity];
        tableSizeMinusOne = newCapacity - 1;
        transfer(newTable);
        table = newTable;
        threshold = (int)(newCapacity * loadFactor);
    }

    private void transfer(final Entry[] newTable) {
        final Entry[] src = table;
        for (int j = 0; j < src.length; ++j) {
            Entry e = src[j];
            if (e != null) {
                src[j] = null;
                do {
                    final Entry next = e.next;
                    final int i = e.hash & tableSizeMinusOne;
                    e.next = newTable[i];
                    newTable[i] = e;
                    e = next;
                } while (e != null);
            }
        }
    }

    private Set<Entry> getEntrySet() {
        final Set<Entry> es = entrySet;
        return es != null ? es : (entrySet = new EntrySet());
    }

    Iterator<Entry> newEntryIterator() {
        return new EntryIterator();
    }

    private final class EntrySet extends AbstractSet<Entry> {

        public Iterator<Entry> iterator() {
            return newEntryIterator();
        }

        public boolean contains(final Object o) {
            if (!(o instanceof Entry)) {
                return false;
            }
            final Entry e = (Entry)o;
            final Entry candidate = getEntry(e.getKey());
            return candidate != null && candidate.equals(e);
        }

        public boolean remove(final Object o) {
            return removeMapping(o) != null;
        }

        public int size() {
            return size;
        }

        public void clear() {
            PrimitiveHashMap.this.clear();
        }
    }

    private final class EntryIterator extends HashIterator<Entry> {
        public Entry next() {
            return nextEntry();
        }
    }

    private abstract class HashIterator<E> implements Iterator<E> {
        Entry current;         // current entry
        Entry next;            // next entry to return
        int index;             // current slot

        HashIterator() {
            if (size > 0) { // advance to first entry
                final Entry[] t = table;
                while (index < t.length && (next = t[index++]) == null) { }
            }
        }

        public final boolean hasNext() {
            return next != null;
        }

        final Entry nextEntry() {
            final Entry e = next;
            if (e == null) {
                throw new NoSuchElementException();
            }
            if ((next = e.next) == null) {
                final Entry[] t = table;
                while (index < t.length && (next = t[index++]) == null) { }
            }
            current = e;
            return e;
        }

        public void remove() {
            if (current == null) {
                throw new IllegalStateException();
            }
            final long k = current.getKey();
            current = null;
            PrimitiveHashMap.this.removeEntryForKey(k);
        }
    }
}