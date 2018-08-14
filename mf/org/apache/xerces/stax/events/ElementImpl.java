package mf.org.apache.xerces.stax.events;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import mf.javax.xml.namespace.QName;
import mf.javax.xml.stream.Location;
import mf.javax.xml.stream.events.Namespace;

abstract class ElementImpl extends XMLEventImpl {
    private final QName fName;
    private final List fNamespaces;

    private static final class NoRemoveIterator implements Iterator {
        private final Iterator fWrapped;

        public NoRemoveIterator(Iterator wrapped) {
            this.fWrapped = wrapped;
        }

        public boolean hasNext() {
            return this.fWrapped.hasNext();
        }

        public Object next() {
            return this.fWrapped.next();
        }

        public void remove() {
            throw new UnsupportedOperationException("Attributes iterator is read-only.");
        }
    }

    ElementImpl(QName name, boolean isStartElement, Iterator namespaces, Location location) {
        super(isStartElement ? 1 : 2, location);
        this.fName = name;
        if (namespaces == null || !namespaces.hasNext()) {
            this.fNamespaces = Collections.EMPTY_LIST;
            return;
        }
        this.fNamespaces = new ArrayList();
        do {
            this.fNamespaces.add((Namespace) namespaces.next());
        } while (namespaces.hasNext());
    }

    public final QName getName() {
        return this.fName;
    }

    public final Iterator getNamespaces() {
        return createImmutableIterator(this.fNamespaces.iterator());
    }

    static Iterator createImmutableIterator(Iterator iter) {
        return new NoRemoveIterator(iter);
    }
}
