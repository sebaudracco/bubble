package mf.org.apache.xerces.stax.events;

import java.io.Writer;
import mf.javax.xml.stream.Location;
import mf.javax.xml.stream.XMLStreamException;
import mf.javax.xml.stream.events.EntityDeclaration;
import mf.javax.xml.stream.events.EntityReference;

public final class EntityReferenceImpl extends XMLEventImpl implements EntityReference {
    private final EntityDeclaration fDecl;
    private final String fName;

    public EntityReferenceImpl(EntityDeclaration decl, Location location) {
        this(decl != null ? decl.getName() : "", decl, location);
    }

    public EntityReferenceImpl(String name, EntityDeclaration decl, Location location) {
        super(9, location);
        if (name == null) {
            name = "";
        }
        this.fName = name;
        this.fDecl = decl;
    }

    public EntityDeclaration getDeclaration() {
        return this.fDecl;
    }

    public String getName() {
        return this.fName;
    }

    public void writeAsEncodedUnicode(Writer writer) throws XMLStreamException {
        try {
            writer.write(38);
            writer.write(this.fName);
            writer.write(59);
        } catch (Throwable ioe) {
            throw new XMLStreamException(ioe);
        }
    }
}
