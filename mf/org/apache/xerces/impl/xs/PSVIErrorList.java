package mf.org.apache.xerces.impl.xs;

import java.util.AbstractList;
import mf.org.apache.xerces.xs.StringList;

final class PSVIErrorList extends AbstractList implements StringList {
    private final String[] fArray;
    private final int fLength = (this.fArray.length >> 1);
    private final int fOffset;

    public PSVIErrorList(String[] array, boolean even) {
        this.fArray = array;
        this.fOffset = even ? 0 : 1;
    }

    public boolean contains(String item) {
        int i;
        if (item == null) {
            for (i = 0; i < this.fLength; i++) {
                if (this.fArray[(i << 1) + this.fOffset] == null) {
                    return true;
                }
            }
        } else {
            for (i = 0; i < this.fLength; i++) {
                if (item.equals(this.fArray[(i << 1) + this.fOffset])) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getLength() {
        return this.fLength;
    }

    public String item(int index) {
        if (index < 0 || index >= this.fLength) {
            return null;
        }
        return this.fArray[(index << 1) + this.fOffset];
    }

    public Object get(int index) {
        if (index >= 0 && index < this.fLength) {
            return this.fArray[(index << 1) + this.fOffset];
        }
        throw new IndexOutOfBoundsException("Index: " + index);
    }

    public int size() {
        return getLength();
    }
}
