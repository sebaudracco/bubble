package mf.org.apache.xerces.util;

import mf.org.apache.xerces.xni.XMLLocator;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.parser.XMLErrorHandler;
import mf.org.apache.xerces.xni.parser.XMLParseException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ErrorHandlerWrapper implements XMLErrorHandler {
    protected ErrorHandler fErrorHandler;

    class C46611 implements XMLLocator {
        private final /* synthetic */ int val$fColumnNumber;
        private final /* synthetic */ String val$fExpandedSystemId;
        private final /* synthetic */ int val$fLineNumber;
        private final /* synthetic */ String val$fPublicId;

        C46611(String str, String str2, int i, int i2) {
            this.val$fPublicId = str;
            this.val$fExpandedSystemId = str2;
            this.val$fColumnNumber = i;
            this.val$fLineNumber = i2;
        }

        public String getPublicId() {
            return this.val$fPublicId;
        }

        public String getExpandedSystemId() {
            return this.val$fExpandedSystemId;
        }

        public String getBaseSystemId() {
            return null;
        }

        public String getLiteralSystemId() {
            return null;
        }

        public int getColumnNumber() {
            return this.val$fColumnNumber;
        }

        public int getLineNumber() {
            return this.val$fLineNumber;
        }

        public int getCharacterOffset() {
            return -1;
        }

        public String getEncoding() {
            return null;
        }

        public String getXMLVersion() {
            return null;
        }
    }

    public ErrorHandlerWrapper(ErrorHandler errorHandler) {
        setErrorHandler(errorHandler);
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.fErrorHandler = errorHandler;
    }

    public ErrorHandler getErrorHandler() {
        return this.fErrorHandler;
    }

    public void warning(String domain, String key, XMLParseException exception) throws XNIException {
        if (this.fErrorHandler != null) {
            try {
                this.fErrorHandler.warning(createSAXParseException(exception));
            } catch (SAXParseException e) {
                throw createXMLParseException(e);
            } catch (SAXException e2) {
                throw createXNIException(e2);
            }
        }
    }

    public void error(String domain, String key, XMLParseException exception) throws XNIException {
        if (this.fErrorHandler != null) {
            try {
                this.fErrorHandler.error(createSAXParseException(exception));
            } catch (SAXParseException e) {
                throw createXMLParseException(e);
            } catch (SAXException e2) {
                throw createXNIException(e2);
            }
        }
    }

    public void fatalError(String domain, String key, XMLParseException exception) throws XNIException {
        if (this.fErrorHandler != null) {
            try {
                this.fErrorHandler.fatalError(createSAXParseException(exception));
            } catch (SAXParseException e) {
                throw createXMLParseException(e);
            } catch (SAXException e2) {
                throw createXNIException(e2);
            }
        }
    }

    protected static SAXParseException createSAXParseException(XMLParseException exception) {
        return new SAXParseException(exception.getMessage(), exception.getPublicId(), exception.getExpandedSystemId(), exception.getLineNumber(), exception.getColumnNumber(), exception.getException());
    }

    protected static XMLParseException createXMLParseException(SAXParseException exception) {
        return new XMLParseException(new C46611(exception.getPublicId(), exception.getSystemId(), exception.getColumnNumber(), exception.getLineNumber()), exception.getMessage(), exception);
    }

    protected static XNIException createXNIException(SAXException exception) {
        return new XNIException(exception.getMessage(), exception);
    }
}
