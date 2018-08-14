package mf.org.apache.xerces.util;

import mf.org.apache.xerces.xni.parser.XMLErrorHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public abstract class ErrorHandlerProxy implements ErrorHandler {
    protected abstract XMLErrorHandler getErrorHandler();

    public void error(SAXParseException e) throws SAXException {
        XMLErrorHandler eh = getErrorHandler();
        if (eh instanceof ErrorHandlerWrapper) {
            ((ErrorHandlerWrapper) eh).fErrorHandler.error(e);
        } else {
            eh.error("", "", ErrorHandlerWrapper.createXMLParseException(e));
        }
    }

    public void fatalError(SAXParseException e) throws SAXException {
        XMLErrorHandler eh = getErrorHandler();
        if (eh instanceof ErrorHandlerWrapper) {
            ((ErrorHandlerWrapper) eh).fErrorHandler.fatalError(e);
        } else {
            eh.fatalError("", "", ErrorHandlerWrapper.createXMLParseException(e));
        }
    }

    public void warning(SAXParseException e) throws SAXException {
        XMLErrorHandler eh = getErrorHandler();
        if (eh instanceof ErrorHandlerWrapper) {
            ((ErrorHandlerWrapper) eh).fErrorHandler.warning(e);
        } else {
            eh.warning("", "", ErrorHandlerWrapper.createXMLParseException(e));
        }
    }
}
