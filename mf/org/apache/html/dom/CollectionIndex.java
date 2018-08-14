package mf.org.apache.html.dom;

/* compiled from: HTMLCollectionImpl */
class CollectionIndex {
    private int _index;

    int getIndex() {
        return this._index;
    }

    void decrement() {
        this._index--;
    }

    boolean isZero() {
        return this._index <= 0;
    }

    CollectionIndex(int index) {
        this._index = index;
    }
}
