package mf.org.apache.xerces.impl;

import mf.org.apache.xerces.xni.Augmentations;
import mf.org.apache.xerces.xni.parser.XMLDocumentFilter;

public interface RevalidationHandler extends XMLDocumentFilter {
    boolean characterData(String str, Augmentations augmentations);
}
