package mf.javax.xml.stream.events;

import java.io.Writer;
import mf.javax.xml.namespace.QName;
import mf.javax.xml.stream.Location;
import mf.javax.xml.stream.XMLStreamConstants;
import mf.javax.xml.stream.XMLStreamException;

public interface XMLEvent extends XMLStreamConstants {
    Characters asCharacters();

    EndElement asEndElement();

    StartElement asStartElement();

    int getEventType();

    Location getLocation();

    QName getSchemaType();

    boolean isAttribute();

    boolean isCharacters();

    boolean isEndDocument();

    boolean isEndElement();

    boolean isEntityReference();

    boolean isNamespace();

    boolean isProcessingInstruction();

    boolean isStartDocument();

    boolean isStartElement();

    void writeAsEncodedUnicode(Writer writer) throws XMLStreamException;
}
