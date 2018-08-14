package mf.org.apache.xerces.impl.xs.util;

import java.lang.reflect.Array;
import java.util.AbstractList;
import mf.org.apache.xerces.xs.LSInputList;
import mf.org.w3c.dom.ls.LSInput;

public final class LSInputListImpl extends AbstractList implements LSInputList {
    public static final LSInputListImpl EMPTY_LIST = new LSInputListImpl(new LSInput[0], 0);
    private final LSInput[] fArray;
    private final int fLength;

    public LSInputListImpl(LSInput[] array, int length) {
        this.fArray = array;
        this.fLength = length;
    }

    public int getLength() {
        return this.fLength;
    }

    public LSInput item(int index) {
        if (index < 0 || index >= this.fLength) {
            return null;
        }
        return this.fArray[index];
    }

    public Object get(int index) {
        if (index >= 0 && index < this.fLength) {
            return this.fArray[index];
        }
        throw new IndexOutOfBoundsException("Index: " + index);
    }

    public int size() {
        return getLength();
    }

    public Object[] toArray() {
        Object[] a = new Object[this.fLength];
        toArray0(a);
        return a;
    }

    public Object[] toArray(Object[] a) {
        if (a.length < this.fLength) {
            a = (Object[]) Array.newInstance(a.getClass().getComponentType(), this.fLength);
        }
        toArray0(a);
        if (a.length > this.fLength) {
            a[this.fLength] = null;
        }
        return a;
    }

    private void toArray0(Object[] a) {
        if (this.fLength > 0) {
            System.arraycopy(this.fArray, 0, a, 0, this.fLength);
        }
    }
}
