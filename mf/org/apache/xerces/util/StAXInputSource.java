package mf.org.apache.xerces.util;

import mf.javax.xml.stream.XMLEventReader;
import mf.javax.xml.stream.XMLStreamException;
import mf.javax.xml.stream.XMLStreamReader;
import mf.org.apache.xerces.xni.parser.XMLInputSource;

public final class StAXInputSource extends XMLInputSource {
    private final boolean fConsumeRemainingContent;
    private final XMLEventReader fEventReader;
    private final XMLStreamReader fStreamReader;

    public StAXInputSource(XMLStreamReader source) {
        this(source, false);
    }

    public StAXInputSource(XMLStreamReader source, boolean consumeRemainingContent) {
        super(null, source.getLocation().getSystemId(), null);
        if (source == null) {
            throw new IllegalArgumentException("XMLStreamReader parameter cannot be null.");
        }
        this.fStreamReader = source;
        this.fEventReader = null;
        this.fConsumeRemainingContent = consumeRemainingContent;
    }

    public StAXInputSource(XMLEventReader source) {
        this(source, false);
    }

    public StAXInputSource(XMLEventReader source, boolean consumeRemainingContent) {
        super(null, getEventReaderSystemId(source), null);
        if (source == null) {
            throw new IllegalArgumentException("XMLEventReader parameter cannot be null.");
        }
        this.fStreamReader = null;
        this.fEventReader = source;
        this.fConsumeRemainingContent = consumeRemainingContent;
    }

    public XMLStreamReader getXMLStreamReader() {
        return this.fStreamReader;
    }

    public XMLEventReader getXMLEventReader() {
        return this.fEventReader;
    }

    public boolean shouldConsumeRemainingContent() {
        return this.fConsumeRemainingContent;
    }

    public void setSystemId(String systemId) {
        throw new UnsupportedOperationException("Cannot set the system ID on a StAXInputSource");
    }

    private static String getEventReaderSystemId(XMLEventReader reader) {
        if (reader != null) {
            try {
                return reader.peek().getLocation().getSystemId();
            } catch (XMLStreamException e) {
            }
        }
        return null;
    }
}
