package mf.org.apache.xerces.stax;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import mf.javax.xml.XMLConstants;
import mf.javax.xml.namespace.NamespaceContext;

public final class DefaultNamespaceContext implements NamespaceContext {
    private static final DefaultNamespaceContext DEFAULT_NAMESPACE_CONTEXT_INSTANCE = new DefaultNamespaceContext();

    class C46581 implements Iterator {
        boolean more = true;

        C46581() {
        }

        public boolean hasNext() {
            return this.more;
        }

        public Object next() {
            if (hasNext()) {
                this.more = false;
                return "xml";
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    class C46592 implements Iterator {
        boolean more = true;

        C46592() {
        }

        public boolean hasNext() {
            return this.more;
        }

        public Object next() {
            if (hasNext()) {
                this.more = false;
                return XMLConstants.XMLNS_ATTRIBUTE;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private DefaultNamespaceContext() {
    }

    public static DefaultNamespaceContext getInstance() {
        return DEFAULT_NAMESPACE_CONTEXT_INSTANCE;
    }

    public String getNamespaceURI(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("Prefix cannot be null.");
        } else if ("xml".equals(prefix)) {
            return XMLConstants.XML_NS_URI;
        } else {
            if (XMLConstants.XMLNS_ATTRIBUTE.equals(prefix)) {
                return XMLConstants.XMLNS_ATTRIBUTE_NS_URI;
            }
            return "";
        }
    }

    public String getPrefix(String namespaceURI) {
        if (namespaceURI == null) {
            throw new IllegalArgumentException("Namespace URI cannot be null.");
        } else if (XMLConstants.XML_NS_URI.equals(namespaceURI)) {
            return "xml";
        } else {
            if (XMLConstants.XMLNS_ATTRIBUTE_NS_URI.equals(namespaceURI)) {
                return XMLConstants.XMLNS_ATTRIBUTE;
            }
            return null;
        }
    }

    public Iterator getPrefixes(String namespaceURI) {
        if (namespaceURI == null) {
            throw new IllegalArgumentException("Namespace URI cannot be null.");
        } else if (XMLConstants.XML_NS_URI.equals(namespaceURI)) {
            return new C46581();
        } else {
            return XMLConstants.XMLNS_ATTRIBUTE_NS_URI.equals(namespaceURI) ? new C46592() : Collections.EMPTY_LIST.iterator();
        }
    }
}
