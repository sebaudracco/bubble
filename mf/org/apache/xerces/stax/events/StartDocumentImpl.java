package mf.org.apache.xerces.stax.events;

import java.io.Writer;
import mf.javax.xml.stream.Location;
import mf.javax.xml.stream.XMLStreamException;
import mf.javax.xml.stream.events.StartDocument;

public final class StartDocumentImpl extends XMLEventImpl implements StartDocument {
    private final String fCharEncoding;
    private final boolean fEncodingSet;
    private final boolean fIsStandalone;
    private final boolean fStandaloneSet;
    private final String fVersion;

    public StartDocumentImpl(String charEncoding, boolean encodingSet, boolean isStandalone, boolean standaloneSet, String version, Location location) {
        super(7, location);
        this.fCharEncoding = charEncoding;
        this.fEncodingSet = encodingSet;
        this.fIsStandalone = isStandalone;
        this.fStandaloneSet = standaloneSet;
        this.fVersion = version;
    }

    public String getSystemId() {
        return getLocation().getSystemId();
    }

    public String getCharacterEncodingScheme() {
        return this.fCharEncoding;
    }

    public boolean encodingSet() {
        return this.fEncodingSet;
    }

    public boolean isStandalone() {
        return this.fIsStandalone;
    }

    public boolean standaloneSet() {
        return this.fStandaloneSet;
    }

    public String getVersion() {
        return this.fVersion;
    }

    public void writeAsEncodedUnicode(Writer writer) throws XMLStreamException {
        try {
            writer.write("<?xml version=\"");
            String str = (this.fVersion == null || this.fVersion.length() <= 0) ? "1.0" : this.fVersion;
            writer.write(str);
            writer.write(34);
            if (encodingSet()) {
                writer.write(" encoding=\"");
                writer.write(this.fCharEncoding);
                writer.write(34);
            }
            if (standaloneSet()) {
                writer.write(" standalone=\"");
                writer.write(this.fIsStandalone ? "yes" : "no");
                writer.write(34);
            }
            writer.write("?>");
        } catch (Throwable ioe) {
            throw new XMLStreamException(ioe);
        }
    }
}
