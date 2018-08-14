package mf.javax.xml.stream.events;

import java.util.Iterator;
import mf.javax.xml.namespace.NamespaceContext;
import mf.javax.xml.namespace.QName;

public interface StartElement extends XMLEvent {
    Attribute getAttributeByName(QName qName);

    Iterator getAttributes();

    QName getName();

    NamespaceContext getNamespaceContext();

    String getNamespaceURI(String str);

    Iterator getNamespaces();
}
