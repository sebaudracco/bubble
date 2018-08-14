package mf.org.apache.xerces.stax.events;

import java.io.Writer;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import mf.javax.xml.namespace.NamespaceContext;
import mf.javax.xml.namespace.QName;
import mf.javax.xml.stream.Location;
import mf.javax.xml.stream.XMLStreamException;
import mf.javax.xml.stream.events.Attribute;
import mf.javax.xml.stream.events.Namespace;
import mf.javax.xml.stream.events.StartElement;
import mf.org.apache.xerces.stax.DefaultNamespaceContext;

public final class StartElementImpl extends ElementImpl implements StartElement {
    private static final Comparator QNAME_COMPARATOR = new C46601();
    private final Map fAttributes;
    private final NamespaceContext fNamespaceContext;

    class C46601 implements Comparator {
        C46601() {
        }

        public int compare(Object o1, Object o2) {
            if (o1.equals(o2)) {
                return 0;
            }
            return ((QName) o1).toString().compareTo(((QName) o2).toString());
        }
    }

    public StartElementImpl(QName name, Iterator attributes, Iterator namespaces, NamespaceContext namespaceContext, Location location) {
        super(name, true, namespaces, location);
        if (attributes == null || !attributes.hasNext()) {
            this.fAttributes = Collections.EMPTY_MAP;
        } else {
            this.fAttributes = new TreeMap(QNAME_COMPARATOR);
            do {
                Attribute attr = (Attribute) attributes.next();
                this.fAttributes.put(attr.getName(), attr);
            } while (attributes.hasNext());
        }
        if (namespaceContext == null) {
            namespaceContext = DefaultNamespaceContext.getInstance();
        }
        this.fNamespaceContext = namespaceContext;
    }

    public Iterator getAttributes() {
        return ElementImpl.createImmutableIterator(this.fAttributes.values().iterator());
    }

    public Attribute getAttributeByName(QName name) {
        return (Attribute) this.fAttributes.get(name);
    }

    public NamespaceContext getNamespaceContext() {
        return this.fNamespaceContext;
    }

    public String getNamespaceURI(String prefix) {
        return this.fNamespaceContext.getNamespaceURI(prefix);
    }

    public void writeAsEncodedUnicode(Writer writer) throws XMLStreamException {
        try {
            writer.write(60);
            QName name = getName();
            String prefix = name.getPrefix();
            if (prefix != null && prefix.length() > 0) {
                writer.write(prefix);
                writer.write(58);
            }
            writer.write(name.getLocalPart());
            Iterator nsIter = getNamespaces();
            while (nsIter.hasNext()) {
                Namespace ns = (Namespace) nsIter.next();
                writer.write(32);
                ns.writeAsEncodedUnicode(writer);
            }
            Iterator attrIter = getAttributes();
            while (attrIter.hasNext()) {
                Attribute attr = (Attribute) attrIter.next();
                writer.write(32);
                attr.writeAsEncodedUnicode(writer);
            }
            writer.write(62);
        } catch (Throwable ioe) {
            throw new XMLStreamException(ioe);
        }
    }
}
