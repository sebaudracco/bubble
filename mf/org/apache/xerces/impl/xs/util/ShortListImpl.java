package mf.org.apache.xerces.impl.xs.util;

import java.util.AbstractList;
import mf.org.apache.xerces.xs.ShortList;
import mf.org.apache.xerces.xs.XSException;

public final class ShortListImpl extends AbstractList implements ShortList {
    public static final ShortListImpl EMPTY_LIST = new ShortListImpl(new short[0], 0);
    private final short[] fArray;
    private final int fLength;

    public ShortListImpl(short[] array, int length) {
        this.fArray = array;
        this.fLength = length;
    }

    public int getLength() {
        return this.fLength;
    }

    public boolean contains(short item) {
        for (int i = 0; i < this.fLength; i++) {
            if (this.fArray[i] == item) {
                return true;
            }
        }
        return false;
    }

    public short item(int index) throws XSException {
        if (index >= 0 && index < this.fLength) {
            return this.fArray[index];
        }
        throw new XSException((short) 2, null);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ShortList)) {
            return false;
        }
        ShortList rhs = (ShortList) obj;
        if (this.fLength != rhs.getLength()) {
            return false;
        }
        for (int i = 0; i < this.fLength; i++) {
            if (this.fArray[i] != rhs.item(i)) {
                return false;
            }
        }
        return true;
    }

    public Object get(int index) {
        if (index >= 0 && index < this.fLength) {
            return new Short(this.fArray[index]);
        }
        throw new IndexOutOfBoundsException("Index: " + index);
    }

    public int size() {
        return getLength();
    }
}
