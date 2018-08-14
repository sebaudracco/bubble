package mf.javax.xml.transform.stax;

import mf.javax.xml.stream.XMLEventReader;
import mf.javax.xml.stream.XMLStreamException;
import mf.javax.xml.stream.XMLStreamReader;
import mf.javax.xml.stream.events.XMLEvent;
import mf.javax.xml.transform.Source;

public class StAXSource implements Source {
    public static final String FEATURE = "http://javax.xml.transform.stax.StAXSource/feature";
    private String systemId = null;
    private XMLEventReader xmlEventReader = null;
    private XMLStreamReader xmlStreamReader = null;

    public StAXSource(XMLEventReader xmlEventReader) throws XMLStreamException {
        if (xmlEventReader == null) {
            throw new IllegalArgumentException("StAXSource(XMLEventReader) with XMLEventReader == null");
        }
        XMLEvent event = xmlEventReader.peek();
        int eventType = event.getEventType();
        if (eventType == 7 || eventType == 1) {
            this.xmlEventReader = xmlEventReader;
            this.systemId = event.getLocation().getSystemId();
            return;
        }
        throw new IllegalStateException("StAXSource(XMLEventReader) with XMLEventReader not in XMLStreamConstants.START_DOCUMENT or XMLStreamConstants.START_ELEMENT state");
    }

    public StAXSource(XMLStreamReader xmlStreamReader) {
        if (xmlStreamReader == null) {
            throw new IllegalArgumentException("StAXSource(XMLStreamReader) with XMLStreamReader == null");
        }
        int eventType = xmlStreamReader.getEventType();
        if (eventType == 7 || eventType == 1) {
            this.xmlStreamReader = xmlStreamReader;
            this.systemId = xmlStreamReader.getLocation().getSystemId();
            return;
        }
        throw new IllegalStateException("StAXSource(XMLStreamReader) with XMLStreamReadernot in XMLStreamConstants.START_DOCUMENT or XMLStreamConstants.START_ELEMENT state");
    }

    public XMLEventReader getXMLEventReader() {
        return this.xmlEventReader;
    }

    public XMLStreamReader getXMLStreamReader() {
        return this.xmlStreamReader;
    }

    public void setSystemId(String systemId) {
        throw new UnsupportedOperationException("StAXSource#setSystemId(systemId) cannot set the system identifier for a StAXSource");
    }

    public String getSystemId() {
        return this.systemId;
    }
}
