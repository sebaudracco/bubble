package mf.org.apache.xerces.stax.events;

import java.io.Writer;
import mf.javax.xml.stream.Location;
import mf.javax.xml.stream.XMLStreamException;
import mf.javax.xml.stream.events.EntityDeclaration;

public final class EntityDeclarationImpl extends XMLEventImpl implements EntityDeclaration {
    private final String fName;
    private final String fNotationName;
    private final String fPublicId;
    private final String fSystemId;

    public EntityDeclarationImpl(String publicId, String systemId, String name, String notationName, Location location) {
        super(15, location);
        this.fPublicId = publicId;
        this.fSystemId = systemId;
        this.fName = name;
        this.fNotationName = notationName;
    }

    public String getPublicId() {
        return this.fPublicId;
    }

    public String getSystemId() {
        return this.fSystemId;
    }

    public String getName() {
        return this.fName;
    }

    public String getNotationName() {
        return this.fNotationName;
    }

    public String getReplacementText() {
        return null;
    }

    public String getBaseURI() {
        return null;
    }

    public void writeAsEncodedUnicode(Writer writer) throws XMLStreamException {
        try {
            writer.write("<!ENTITY ");
            writer.write(this.fName);
            if (this.fPublicId != null) {
                writer.write(" PUBLIC \"");
                writer.write(this.fPublicId);
                writer.write("\" \"");
                writer.write(this.fSystemId);
                writer.write(34);
            } else {
                writer.write(" SYSTEM \"");
                writer.write(this.fSystemId);
                writer.write(34);
            }
            if (this.fNotationName != null) {
                writer.write(" NDATA ");
                writer.write(this.fNotationName);
            }
            writer.write(62);
        } catch (Throwable ioe) {
            throw new XMLStreamException(ioe);
        }
    }
}
