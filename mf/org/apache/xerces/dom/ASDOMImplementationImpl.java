package mf.org.apache.xerces.dom;

import mf.org.apache.xerces.dom3.as.ASModel;
import mf.org.apache.xerces.dom3.as.DOMASBuilder;
import mf.org.apache.xerces.dom3.as.DOMASWriter;
import mf.org.apache.xerces.dom3.as.DOMImplementationAS;
import mf.org.apache.xerces.parsers.DOMASBuilderImpl;
import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.DOMImplementation;

public class ASDOMImplementationImpl extends DOMImplementationImpl implements DOMImplementationAS {
    static final ASDOMImplementationImpl singleton = new ASDOMImplementationImpl();

    public static DOMImplementation getDOMImplementation() {
        return singleton;
    }

    public ASModel createAS(boolean isNamespaceAware) {
        return new ASModelImpl(isNamespaceAware);
    }

    public DOMASBuilder createDOMASBuilder() {
        return new DOMASBuilderImpl();
    }

    public DOMASWriter createDOMASWriter() {
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }
}
