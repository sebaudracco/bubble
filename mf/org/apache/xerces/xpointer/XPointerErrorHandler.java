package mf.org.apache.xerces.xpointer;

import java.io.PrintWriter;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.parser.XMLErrorHandler;
import mf.org.apache.xerces.xni.parser.XMLParseException;

final class XPointerErrorHandler implements XMLErrorHandler {
    protected PrintWriter fOut;

    public XPointerErrorHandler() {
        this(new PrintWriter(System.err));
    }

    public XPointerErrorHandler(PrintWriter out) {
        this.fOut = out;
    }

    public void warning(String domain, String key, XMLParseException ex) throws XNIException {
        printError("Warning", ex);
    }

    public void error(String domain, String key, XMLParseException ex) throws XNIException {
        printError("Error", ex);
    }

    public void fatalError(String domain, String key, XMLParseException ex) throws XNIException {
        printError("Fatal Error", ex);
        throw ex;
    }

    private void printError(String type, XMLParseException ex) {
        this.fOut.print("[");
        this.fOut.print(type);
        this.fOut.print("] ");
        String systemId = ex.getExpandedSystemId();
        if (systemId != null) {
            int index = systemId.lastIndexOf(47);
            if (index != -1) {
                systemId = systemId.substring(index + 1);
            }
            this.fOut.print(systemId);
        }
        this.fOut.print(':');
        this.fOut.print(ex.getLineNumber());
        this.fOut.print(':');
        this.fOut.print(ex.getColumnNumber());
        this.fOut.print(": ");
        this.fOut.print(ex.getMessage());
        this.fOut.println();
        this.fOut.flush();
    }
}
