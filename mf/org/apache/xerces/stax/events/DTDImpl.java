package mf.org.apache.xerces.stax.events;

import java.io.Writer;
import java.util.Collections;
import java.util.List;
import mf.javax.xml.stream.Location;
import mf.javax.xml.stream.XMLStreamException;
import mf.javax.xml.stream.events.DTD;

public final class DTDImpl extends XMLEventImpl implements DTD {
    private final String fDTD;

    public DTDImpl(String dtd, Location location) {
        super(11, location);
        if (dtd == null) {
            dtd = null;
        }
        this.fDTD = dtd;
    }

    public String getDocumentTypeDeclaration() {
        return this.fDTD;
    }

    public Object getProcessedDTD() {
        return null;
    }

    public List getNotations() {
        return Collections.EMPTY_LIST;
    }

    public List getEntities() {
        return Collections.EMPTY_LIST;
    }

    public void writeAsEncodedUnicode(Writer writer) throws XMLStreamException {
        try {
            writer.write(this.fDTD);
        } catch (Throwable ioe) {
            throw new XMLStreamException(ioe);
        }
    }
}
