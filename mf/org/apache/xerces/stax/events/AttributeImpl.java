package mf.org.apache.xerces.stax.events;

import java.io.Writer;
import mf.javax.xml.namespace.QName;
import mf.javax.xml.stream.Location;
import mf.javax.xml.stream.XMLStreamException;
import mf.javax.xml.stream.events.Attribute;

public class AttributeImpl extends XMLEventImpl implements Attribute {
    private final String fDtdType;
    private final boolean fIsSpecified;
    private final QName fName;
    private final String fValue;

    public AttributeImpl(QName name, String value, String dtdType, boolean isSpecified, Location location) {
        this(10, name, value, dtdType, isSpecified, location);
    }

    protected AttributeImpl(int type, QName name, String value, String dtdType, boolean isSpecified, Location location) {
        super(type, location);
        this.fName = name;
        this.fValue = value;
        this.fDtdType = dtdType;
        this.fIsSpecified = isSpecified;
    }

    public final QName getName() {
        return this.fName;
    }

    public final String getValue() {
        return this.fValue;
    }

    public final String getDTDType() {
        return this.fDtdType;
    }

    public final boolean isSpecified() {
        return this.fIsSpecified;
    }

    public final void writeAsEncodedUnicode(Writer writer) throws XMLStreamException {
        try {
            String prefix = this.fName.getPrefix();
            if (prefix != null && prefix.length() > 0) {
                writer.write(prefix);
                writer.write(58);
            }
            writer.write(this.fName.getLocalPart());
            writer.write("=\"");
            writer.write(this.fValue);
            writer.write(34);
        } catch (Throwable ioe) {
            throw new XMLStreamException(ioe);
        }
    }
}
