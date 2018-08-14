package mf.org.apache.xerces.impl.xs.traversers;

/* compiled from: XSAttributeChecker */
class SmallContainer extends Container {
    String[] keys;

    SmallContainer(int size) {
        this.keys = new String[size];
        this.values = new OneAttr[size];
    }

    void put(String key, OneAttr value) {
        this.keys[this.pos] = key;
        OneAttr[] oneAttrArr = this.values;
        int i = this.pos;
        this.pos = i + 1;
        oneAttrArr[i] = value;
    }

    OneAttr get(String key) {
        for (int i = 0; i < this.pos; i++) {
            if (this.keys[i].equals(key)) {
                return this.values[i];
            }
        }
        return null;
    }
}
