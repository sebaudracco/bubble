package mf.org.apache.xerces.dom;

import java.util.ArrayList;
import java.util.Vector;
import mf.org.w3c.dom.DOMImplementation;
import mf.org.w3c.dom.DOMImplementationList;

public class DOMImplementationListImpl implements DOMImplementationList {
    private final ArrayList fImplementations;

    public DOMImplementationListImpl() {
        this.fImplementations = new ArrayList();
    }

    public DOMImplementationListImpl(ArrayList params) {
        this.fImplementations = params;
    }

    public DOMImplementationListImpl(Vector params) {
        this.fImplementations = new ArrayList(params);
    }

    public DOMImplementation item(int index) {
        int length = getLength();
        if (index < 0 || index >= length) {
            return null;
        }
        return (DOMImplementation) this.fImplementations.get(index);
    }

    public int getLength() {
        return this.fImplementations.size();
    }
}
