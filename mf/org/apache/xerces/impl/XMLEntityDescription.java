package mf.org.apache.xerces.impl;

import mf.org.apache.xerces.xni.XMLResourceIdentifier;

public interface XMLEntityDescription extends XMLResourceIdentifier {
    String getEntityName();

    void setEntityName(String str);
}
