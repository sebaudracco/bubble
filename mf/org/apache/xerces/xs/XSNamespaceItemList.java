package mf.org.apache.xerces.xs;

import java.util.List;

public interface XSNamespaceItemList extends List {
    int getLength();

    XSNamespaceItem item(int i);
}
