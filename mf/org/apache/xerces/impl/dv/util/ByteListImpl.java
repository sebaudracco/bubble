package mf.org.apache.xerces.impl.dv.util;

import java.util.AbstractList;
import mf.org.apache.xerces.xs.XSException;
import mf.org.apache.xerces.xs.datatypes.ByteList;

public class ByteListImpl extends AbstractList implements ByteList {
    protected String canonical;
    protected final byte[] data;

    public ByteListImpl(byte[] data) {
        this.data = data;
    }

    public int getLength() {
        return this.data.length;
    }

    public boolean contains(byte item) {
        for (byte b : this.data) {
            if (b == item) {
                return true;
            }
        }
        return false;
    }

    public byte item(int index) throws XSException {
        if (index >= 0 && index <= this.data.length - 1) {
            return this.data[index];
        }
        throw new XSException((short) 2, null);
    }

    public Object get(int index) {
        if (index >= 0 && index < this.data.length) {
            return new Byte(this.data[index]);
        }
        throw new IndexOutOfBoundsException("Index: " + index);
    }

    public int size() {
        return getLength();
    }

    public byte[] toByteArray() {
        byte[] ret = new byte[this.data.length];
        System.arraycopy(this.data, 0, ret, 0, this.data.length);
        return ret;
    }
}
