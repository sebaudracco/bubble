package mf.javax.xml.stream.util;

import mf.javax.xml.stream.XMLEventReader;
import mf.javax.xml.stream.XMLStreamException;
import mf.javax.xml.stream.events.XMLEvent;

public class EventReaderDelegate implements XMLEventReader {
    private XMLEventReader reader;

    public EventReaderDelegate(XMLEventReader reader) {
        this.reader = reader;
    }

    public void setParent(XMLEventReader reader) {
        this.reader = reader;
    }

    public XMLEventReader getParent() {
        return this.reader;
    }

    public XMLEvent nextEvent() throws XMLStreamException {
        return this.reader.nextEvent();
    }

    public Object next() {
        return this.reader.next();
    }

    public boolean hasNext() {
        return this.reader.hasNext();
    }

    public XMLEvent peek() throws XMLStreamException {
        return this.reader.peek();
    }

    public void close() throws XMLStreamException {
        this.reader.close();
    }

    public String getElementText() throws XMLStreamException {
        return this.reader.getElementText();
    }

    public XMLEvent nextTag() throws XMLStreamException {
        return this.reader.nextTag();
    }

    public Object getProperty(String name) throws IllegalArgumentException {
        return this.reader.getProperty(name);
    }

    public void remove() {
        this.reader.remove();
    }
}
