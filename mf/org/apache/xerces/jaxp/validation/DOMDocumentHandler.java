package mf.org.apache.xerces.jaxp.validation;

import mf.javax.xml.transform.dom.DOMResult;
import mf.org.apache.xerces.xni.XMLDocumentHandler;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.w3c.dom.CDATASection;
import mf.org.w3c.dom.Comment;
import mf.org.w3c.dom.DocumentType;
import mf.org.w3c.dom.ProcessingInstruction;
import mf.org.w3c.dom.Text;

interface DOMDocumentHandler extends XMLDocumentHandler {
    void cdata(CDATASection cDATASection) throws XNIException;

    void characters(Text text) throws XNIException;

    void comment(Comment comment) throws XNIException;

    void doctypeDecl(DocumentType documentType) throws XNIException;

    void processingInstruction(ProcessingInstruction processingInstruction) throws XNIException;

    void setDOMResult(DOMResult dOMResult);

    void setIgnoringCharacters(boolean z);
}
