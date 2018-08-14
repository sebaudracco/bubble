package mf.org.apache.xerces.impl.xs.traversers;

/* compiled from: XSAttributeChecker */
abstract class Container {
    static final int THRESHOLD = 5;
    int pos = 0;
    OneAttr[] values;

    abstract OneAttr get(String str);

    abstract void put(String str, OneAttr oneAttr);

    Container() {
    }

    static Container getContainer(int size) {
        if (size > 5) {
            return new LargeContainer(size);
        }
        return new SmallContainer(size);
    }
}
