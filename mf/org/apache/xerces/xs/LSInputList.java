package mf.org.apache.xerces.xs;

import java.util.List;
import mf.org.w3c.dom.ls.LSInput;

public interface LSInputList extends List {
    int getLength();

    LSInput item(int i);
}
