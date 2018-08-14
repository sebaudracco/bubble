package mf.org.apache.xerces.stax.events;

import java.io.StringWriter;
import mf.javax.xml.namespace.QName;
import mf.javax.xml.stream.Location;
import mf.javax.xml.stream.XMLStreamException;
import mf.javax.xml.stream.events.Characters;
import mf.javax.xml.stream.events.EndElement;
import mf.javax.xml.stream.events.StartElement;
import mf.javax.xml.stream.events.XMLEvent;
import mf.org.apache.xerces.stax.EmptyLocation;
import mf.org.apache.xerces.stax.ImmutableLocation;

abstract class XMLEventImpl implements XMLEvent {
    private int fEventType;
    private Location fLocation;

    XMLEventImpl(int eventType, Location location) {
        this.fEventType = eventType;
        if (location != null) {
            this.fLocation = new ImmutableLocation(location);
        } else {
            this.fLocation = EmptyLocation.getInstance();
        }
    }

    public final int getEventType() {
        return this.fEventType;
    }

    public final Location getLocation() {
        return this.fLocation;
    }

    public final boolean isStartElement() {
        return 1 == this.fEventType;
    }

    public final boolean isAttribute() {
        return 10 == this.fEventType;
    }

    public final boolean isNamespace() {
        return 13 == this.fEventType;
    }

    public final boolean isEndElement() {
        return 2 == this.fEventType;
    }

    public final boolean isEntityReference() {
        return 9 == this.fEventType;
    }

    public final boolean isProcessingInstruction() {
        return 3 == this.fEventType;
    }

    public final boolean isCharacters() {
        return 4 == this.fEventType || 12 == this.fEventType || 6 == this.fEventType;
    }

    public final boolean isStartDocument() {
        return 7 == this.fEventType;
    }

    public final boolean isEndDocument() {
        return 8 == this.fEventType;
    }

    public final StartElement asStartElement() {
        return (StartElement) this;
    }

    public final EndElement asEndElement() {
        return (EndElement) this;
    }

    public final Characters asCharacters() {
        return (Characters) this;
    }

    public final QName getSchemaType() {
        return null;
    }

    public final String toString() {
        StringWriter writer = new StringWriter();
        try {
            writeAsEncodedUnicode(writer);
        } catch (XMLStreamException e) {
        }
        return writer.toString();
    }
}
