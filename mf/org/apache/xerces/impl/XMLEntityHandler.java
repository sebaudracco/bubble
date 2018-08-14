package mf.org.apache.xerces.impl;

import mf.org.apache.xerces.xni.Augmentations;
import mf.org.apache.xerces.xni.XMLResourceIdentifier;
import mf.org.apache.xerces.xni.XNIException;

public interface XMLEntityHandler {
    void endEntity(String str, Augmentations augmentations) throws XNIException;

    void startEntity(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2, Augmentations augmentations) throws XNIException;
}
