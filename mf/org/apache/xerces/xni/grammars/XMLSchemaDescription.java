package mf.org.apache.xerces.xni.grammars;

import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xni.XMLAttributes;

public interface XMLSchemaDescription extends XMLGrammarDescription {
    public static final short CONTEXT_ATTRIBUTE = (short) 6;
    public static final short CONTEXT_ELEMENT = (short) 5;
    public static final short CONTEXT_IMPORT = (short) 2;
    public static final short CONTEXT_INCLUDE = (short) 0;
    public static final short CONTEXT_INSTANCE = (short) 4;
    public static final short CONTEXT_PREPARSE = (short) 3;
    public static final short CONTEXT_REDEFINE = (short) 1;
    public static final short CONTEXT_XSITYPE = (short) 7;

    XMLAttributes getAttributes();

    short getContextType();

    QName getEnclosingElementName();

    String[] getLocationHints();

    String getTargetNamespace();

    QName getTriggeringComponent();
}
