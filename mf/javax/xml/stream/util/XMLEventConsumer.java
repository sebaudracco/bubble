package mf.javax.xml.stream.util;

import mf.javax.xml.stream.XMLStreamException;
import mf.javax.xml.stream.events.XMLEvent;

public interface XMLEventConsumer {
    void add(XMLEvent xMLEvent) throws XMLStreamException;
}
