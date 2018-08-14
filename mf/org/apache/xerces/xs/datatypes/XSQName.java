package mf.org.apache.xerces.xs.datatypes;

import javax.xml.namespace.QName;

public interface XSQName {
    QName getJAXPQName();

    mf.org.apache.xerces.xni.QName getXNIQName();
}
