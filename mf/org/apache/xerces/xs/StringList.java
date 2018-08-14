package mf.org.apache.xerces.xs;

import java.util.List;

public interface StringList extends List {
    boolean contains(String str);

    int getLength();

    String item(int i);
}
