package mf.org.apache.xerces.xni.parser;

import mf.org.apache.xerces.xni.XNIException;

public class XMLConfigurationException extends XNIException {
    public static final short NOT_RECOGNIZED = (short) 0;
    public static final short NOT_SUPPORTED = (short) 1;
    static final long serialVersionUID = -5437427404547669188L;
    protected String fIdentifier;
    protected short fType;

    public XMLConfigurationException(short type, String identifier) {
        super(identifier);
        this.fType = type;
        this.fIdentifier = identifier;
    }

    public XMLConfigurationException(short type, String identifier, String message) {
        super(message);
        this.fType = type;
        this.fIdentifier = identifier;
    }

    public short getType() {
        return this.fType;
    }

    public String getIdentifier() {
        return this.fIdentifier;
    }
}
