package mf.org.apache.xerces.xs;

import java.util.List;

public interface ShortList extends List {
    boolean contains(short s);

    int getLength();

    short item(int i) throws XSException;
}
