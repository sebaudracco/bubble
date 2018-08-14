package mf.javax.xml.stream.events;

import java.util.Iterator;
import mf.javax.xml.namespace.QName;

public interface EndElement extends XMLEvent {
    QName getName();

    Iterator getNamespaces();
}
