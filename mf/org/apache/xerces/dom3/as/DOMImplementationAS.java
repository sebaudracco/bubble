package mf.org.apache.xerces.dom3.as;

public interface DOMImplementationAS {
    ASModel createAS(boolean z);

    DOMASBuilder createDOMASBuilder();

    DOMASWriter createDOMASWriter();
}
