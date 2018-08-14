package mf.org.apache.xerces.stax.events;

import java.io.Writer;
import mf.javax.xml.stream.Location;
import mf.javax.xml.stream.XMLStreamException;
import mf.javax.xml.stream.events.NotationDeclaration;

public final class NotationDeclarationImpl extends XMLEventImpl implements NotationDeclaration {
    private final String fName;
    private final String fPublicId;
    private final String fSystemId;

    public NotationDeclarationImpl(String name, String publicId, String systemId, Location location) {
        super(14, location);
        this.fName = name;
        this.fPublicId = publicId;
        this.fSystemId = systemId;
    }

    public String getName() {
        return this.fName;
    }

    public String getPublicId() {
        return this.fPublicId;
    }

    public String getSystemId() {
        return this.fSystemId;
    }

    public void writeAsEncodedUnicode(Writer writer) throws XMLStreamException {
        try {
            writer.write("<!NOTATION ");
            if (this.fPublicId != null) {
                writer.write("PUBLIC \"");
                writer.write(this.fPublicId);
                writer.write(34);
                if (this.fSystemId != null) {
                    writer.write(" \"");
                    writer.write(this.fSystemId);
                    writer.write(34);
                }
            } else {
                writer.write("SYSTEM \"");
                writer.write(this.fSystemId);
                writer.write(34);
            }
            writer.write(this.fName);
            writer.write(62);
        } catch (Throwable ioe) {
            throw new XMLStreamException(ioe);
        }
    }
}
