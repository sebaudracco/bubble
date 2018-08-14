package mf.org.apache.xerces.stax.events;

import java.io.Writer;
import mf.javax.xml.stream.Location;
import mf.javax.xml.stream.XMLStreamException;
import mf.javax.xml.stream.events.ProcessingInstruction;

public final class ProcessingInstructionImpl extends XMLEventImpl implements ProcessingInstruction {
    private final String fData;
    private final String fTarget;

    public ProcessingInstructionImpl(String target, String data, Location location) {
        super(3, location);
        if (target == null) {
            target = "";
        }
        this.fTarget = target;
        this.fData = data;
    }

    public String getTarget() {
        return this.fTarget;
    }

    public String getData() {
        return this.fData;
    }

    public void writeAsEncodedUnicode(Writer writer) throws XMLStreamException {
        try {
            writer.write("<?");
            writer.write(this.fTarget);
            if (this.fData != null && this.fData.length() > 0) {
                writer.write(32);
                writer.write(this.fData);
            }
            writer.write("?>");
        } catch (Throwable ioe) {
            throw new XMLStreamException(ioe);
        }
    }
}
