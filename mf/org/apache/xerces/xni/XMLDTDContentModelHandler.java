package mf.org.apache.xerces.xni;

import mf.org.apache.xerces.xni.parser.XMLDTDContentModelSource;

public interface XMLDTDContentModelHandler {
    public static final short OCCURS_ONE_OR_MORE = (short) 4;
    public static final short OCCURS_ZERO_OR_MORE = (short) 3;
    public static final short OCCURS_ZERO_OR_ONE = (short) 2;
    public static final short SEPARATOR_CHOICE = (short) 0;
    public static final short SEPARATOR_SEQUENCE = (short) 1;

    void any(Augmentations augmentations) throws XNIException;

    void element(String str, Augmentations augmentations) throws XNIException;

    void empty(Augmentations augmentations) throws XNIException;

    void endContentModel(Augmentations augmentations) throws XNIException;

    void endGroup(Augmentations augmentations) throws XNIException;

    XMLDTDContentModelSource getDTDContentModelSource();

    void occurrence(short s, Augmentations augmentations) throws XNIException;

    void pcdata(Augmentations augmentations) throws XNIException;

    void separator(short s, Augmentations augmentations) throws XNIException;

    void setDTDContentModelSource(XMLDTDContentModelSource xMLDTDContentModelSource);

    void startContentModel(String str, Augmentations augmentations) throws XNIException;

    void startGroup(Augmentations augmentations) throws XNIException;
}
