package mf.org.apache.xerces.util;

public class SymbolHash {
    protected Entry[] fBuckets;
    protected int fNum;
    protected int fTableSize;

    protected static final class Entry {
        public Object key;
        public Entry next;
        public Object value;

        public Entry() {
            this.key = null;
            this.value = null;
            this.next = null;
        }

        public Entry(Object key, Object value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Entry makeClone() {
            Entry entry = new Entry();
            entry.key = this.key;
            entry.value = this.value;
            if (this.next != null) {
                entry.next = this.next.makeClone();
            }
            return entry;
        }
    }

    public SymbolHash() {
        this.fTableSize = 101;
        this.fNum = 0;
        this.fBuckets = new Entry[this.fTableSize];
    }

    public SymbolHash(int size) {
        this.fTableSize = 101;
        this.fNum = 0;
        this.fTableSize = size;
        this.fBuckets = new Entry[this.fTableSize];
    }

    public void put(Object key, Object value) {
        int bucket = (key.hashCode() & Integer.MAX_VALUE) % this.fTableSize;
        Entry entry = search(key, bucket);
        if (entry != null) {
            entry.value = value;
            return;
        }
        this.fBuckets[bucket] = new Entry(key, value, this.fBuckets[bucket]);
        this.fNum++;
    }

    public Object get(Object key) {
        Entry entry = search(key, (key.hashCode() & Integer.MAX_VALUE) % this.fTableSize);
        if (entry != null) {
            return entry.value;
        }
        return null;
    }

    public int getLength() {
        return this.fNum;
    }

    public int getValues(Object[] elements, int from) {
        int j = 0;
        for (int i = 0; i < this.fTableSize && j < this.fNum; i++) {
            for (Entry entry = this.fBuckets[i]; entry != null; entry = entry.next) {
                elements[from + j] = entry.value;
                j++;
            }
        }
        return this.fNum;
    }

    public Object[] getEntries() {
        Object[] entries = new Object[(this.fNum << 1)];
        int j = 0;
        for (int i = 0; i < this.fTableSize && j < (this.fNum << 1); i++) {
            for (Entry entry = this.fBuckets[i]; entry != null; entry = entry.next) {
                entries[j] = entry.key;
                j++;
                entries[j] = entry.value;
                j++;
            }
        }
        return entries;
    }

    public SymbolHash makeClone() {
        SymbolHash newTable = new SymbolHash(this.fTableSize);
        newTable.fNum = this.fNum;
        for (int i = 0; i < this.fTableSize; i++) {
            if (this.fBuckets[i] != null) {
                newTable.fBuckets[i] = this.fBuckets[i].makeClone();
            }
        }
        return newTable;
    }

    public void clear() {
        for (int i = 0; i < this.fTableSize; i++) {
            this.fBuckets[i] = null;
        }
        this.fNum = 0;
    }

    protected Entry search(Object key, int bucket) {
        for (Entry entry = this.fBuckets[bucket]; entry != null; entry = entry.next) {
            if (key.equals(entry.key)) {
                return entry;
            }
        }
        return null;
    }
}
