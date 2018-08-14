package mf.org.apache.xerces.xs.datatypes;

import java.util.List;

public interface ObjectList extends List {
    boolean contains(Object obj);

    int getLength();

    Object item(int i);
}
