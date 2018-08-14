package mf.org.apache.xerces.impl.xs;

import mf.org.apache.xerces.util.XMLResourceIdentifierImpl;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xni.XMLAttributes;
import mf.org.apache.xerces.xni.grammars.XMLSchemaDescription;

public class XSDDescription extends XMLResourceIdentifierImpl implements XMLSchemaDescription {
    public static final short CONTEXT_ATTRIBUTE = (short) 6;
    public static final short CONTEXT_ELEMENT = (short) 5;
    public static final short CONTEXT_IMPORT = (short) 2;
    public static final short CONTEXT_INCLUDE = (short) 0;
    public static final short CONTEXT_INITIALIZE = (short) -1;
    public static final short CONTEXT_INSTANCE = (short) 4;
    public static final short CONTEXT_PREPARSE = (short) 3;
    public static final short CONTEXT_REDEFINE = (short) 1;
    public static final short CONTEXT_XSITYPE = (short) 7;
    protected XMLAttributes fAttributes;
    protected short fContextType;
    protected QName fEnclosedElementName;
    protected String[] fLocationHints;
    protected QName fTriggeringComponent;

    public String getGrammarType() {
        return "http://www.w3.org/2001/XMLSchema";
    }

    public short getContextType() {
        return this.fContextType;
    }

    public String getTargetNamespace() {
        return this.fNamespace;
    }

    public String[] getLocationHints() {
        return this.fLocationHints;
    }

    public QName getTriggeringComponent() {
        return this.fTriggeringComponent;
    }

    public QName getEnclosingElementName() {
        return this.fEnclosedElementName;
    }

    public XMLAttributes getAttributes() {
        return this.fAttributes;
    }

    public boolean fromInstance() {
        return this.fContextType == (short) 6 || this.fContextType == (short) 5 || this.fContextType == (short) 4 || this.fContextType == (short) 7;
    }

    public boolean equals(Object descObj) {
        if (!(descObj instanceof XMLSchemaDescription)) {
            return false;
        }
        XMLSchemaDescription desc = (XMLSchemaDescription) descObj;
        if (this.fNamespace != null) {
            return this.fNamespace.equals(desc.getTargetNamespace());
        }
        if (desc.getTargetNamespace() == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.fNamespace == null ? 0 : this.fNamespace.hashCode();
    }

    public void setContextType(short contextType) {
        this.fContextType = contextType;
    }

    public void setTargetNamespace(String targetNamespace) {
        this.fNamespace = targetNamespace;
    }

    public void setLocationHints(String[] locationHints) {
        int length = locationHints.length;
        this.fLocationHints = new String[length];
        System.arraycopy(locationHints, 0, this.fLocationHints, 0, length);
    }

    public void setTriggeringComponent(QName triggeringComponent) {
        this.fTriggeringComponent = triggeringComponent;
    }

    public void setEnclosingElementName(QName enclosedElementName) {
        this.fEnclosedElementName = enclosedElementName;
    }

    public void setAttributes(XMLAttributes attributes) {
        this.fAttributes = attributes;
    }

    public void reset() {
        super.clear();
        this.fContextType = (short) -1;
        this.fLocationHints = null;
        this.fTriggeringComponent = null;
        this.fEnclosedElementName = null;
        this.fAttributes = null;
    }

    public XSDDescription makeClone() {
        XSDDescription desc = new XSDDescription();
        desc.fAttributes = this.fAttributes;
        desc.fBaseSystemId = this.fBaseSystemId;
        desc.fContextType = this.fContextType;
        desc.fEnclosedElementName = this.fEnclosedElementName;
        desc.fExpandedSystemId = this.fExpandedSystemId;
        desc.fLiteralSystemId = this.fLiteralSystemId;
        desc.fLocationHints = this.fLocationHints;
        desc.fPublicId = this.fPublicId;
        desc.fNamespace = this.fNamespace;
        desc.fTriggeringComponent = this.fTriggeringComponent;
        return desc;
    }
}
