package mf.org.apache.xerces.dom;

import java.util.ArrayList;
import java.util.Vector;
import mf.org.w3c.dom.DOMStringList;

public class DOMStringListImpl implements DOMStringList {
    private final ArrayList fStrings;

    public DOMStringListImpl() {
        this.fStrings = new ArrayList();
    }

    public DOMStringListImpl(ArrayList params) {
        this.fStrings = params;
    }

    public DOMStringListImpl(Vector params) {
        this.fStrings = new ArrayList(params);
    }

    public String item(int index) {
        int length = getLength();
        if (index < 0 || index >= length) {
            return null;
        }
        return (String) this.fStrings.get(index);
    }

    public int getLength() {
        return this.fStrings.size();
    }

    public boolean contains(String param) {
        return this.fStrings.contains(param);
    }

    public void add(String param) {
        this.fStrings.add(param);
    }
}
