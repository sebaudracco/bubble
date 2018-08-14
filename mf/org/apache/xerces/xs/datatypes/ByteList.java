package mf.org.apache.xerces.xs.datatypes;

import java.util.List;
import mf.org.apache.xerces.xs.XSException;

public interface ByteList extends List {
    boolean contains(byte b);

    int getLength();

    byte item(int i) throws XSException;

    byte[] toByteArray();
}
