package mf.javax.xml.stream.events;

import mf.javax.xml.namespace.QName;

public interface Attribute extends XMLEvent {
    String getDTDType();

    QName getName();

    String getValue();

    boolean isSpecified();
}
