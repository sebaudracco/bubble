package mf.org.apache.xerces.dom;

import java.util.Hashtable;

class LCount {
    static Hashtable lCounts = new Hashtable();
    public int bubbles = 0;
    public int captures = 0;
    public int defaults;
    public int total = 0;

    LCount() {
    }

    static LCount lookup(String evtName) {
        LCount lc = (LCount) lCounts.get(evtName);
        if (lc != null) {
            return lc;
        }
        Hashtable hashtable = lCounts;
        lc = new LCount();
        hashtable.put(evtName, lc);
        return lc;
    }
}
