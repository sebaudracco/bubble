package mf.org.apache.xerces.stax.events;

import java.io.Writer;
import mf.javax.xml.stream.Location;
import mf.javax.xml.stream.XMLStreamException;
import mf.javax.xml.stream.events.EndDocument;

public final class EndDocumentImpl extends XMLEventImpl implements EndDocument {
    public EndDocumentImpl(Location location) {
        super(8, location);
    }

    public void writeAsEncodedUnicode(Writer writer) throws XMLStreamException {
    }
}
