package mf.org.apache.xerces.dom;

import mf.org.apache.xerces.xni.parser.XMLParseException;
import mf.org.w3c.dom.DOMError;
import mf.org.w3c.dom.DOMLocator;

public class DOMErrorImpl implements DOMError {
    public Exception fException = null;
    public DOMLocatorImpl fLocator = new DOMLocatorImpl();
    public String fMessage = null;
    public Object fRelatedData;
    public short fSeverity = (short) 1;
    public String fType;

    public DOMErrorImpl(short severity, XMLParseException exception) {
        this.fSeverity = severity;
        this.fException = exception;
        this.fLocator = createDOMLocator(exception);
    }

    public short getSeverity() {
        return this.fSeverity;
    }

    public String getMessage() {
        return this.fMessage;
    }

    public DOMLocator getLocation() {
        return this.fLocator;
    }

    private DOMLocatorImpl createDOMLocator(XMLParseException exception) {
        return new DOMLocatorImpl(exception.getLineNumber(), exception.getColumnNumber(), exception.getCharacterOffset(), exception.getExpandedSystemId());
    }

    public Object getRelatedException() {
        return this.fException;
    }

    public void reset() {
        this.fSeverity = (short) 1;
        this.fException = null;
    }

    public String getType() {
        return this.fType;
    }

    public Object getRelatedData() {
        return this.fRelatedData;
    }
}
