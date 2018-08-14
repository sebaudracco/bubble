package mf.org.apache.xerces.jaxp.validation;

import mf.javax.xml.stream.XMLStreamException;
import mf.javax.xml.stream.XMLStreamReader;
import mf.javax.xml.stream.events.Characters;
import mf.javax.xml.stream.events.Comment;
import mf.javax.xml.stream.events.DTD;
import mf.javax.xml.stream.events.EndDocument;
import mf.javax.xml.stream.events.EntityReference;
import mf.javax.xml.stream.events.ProcessingInstruction;
import mf.javax.xml.stream.events.StartDocument;
import mf.javax.xml.transform.stax.StAXResult;
import mf.org.apache.xerces.xni.XMLDocumentHandler;

interface StAXDocumentHandler extends XMLDocumentHandler {
    void cdata(Characters characters) throws XMLStreamException;

    void characters(Characters characters) throws XMLStreamException;

    void comment(XMLStreamReader xMLStreamReader) throws XMLStreamException;

    void comment(Comment comment) throws XMLStreamException;

    void doctypeDecl(DTD dtd) throws XMLStreamException;

    void endDocument(XMLStreamReader xMLStreamReader) throws XMLStreamException;

    void endDocument(EndDocument endDocument) throws XMLStreamException;

    void entityReference(XMLStreamReader xMLStreamReader) throws XMLStreamException;

    void entityReference(EntityReference entityReference) throws XMLStreamException;

    void processingInstruction(XMLStreamReader xMLStreamReader) throws XMLStreamException;

    void processingInstruction(ProcessingInstruction processingInstruction) throws XMLStreamException;

    void setIgnoringCharacters(boolean z);

    void setStAXResult(StAXResult stAXResult);

    void startDocument(XMLStreamReader xMLStreamReader) throws XMLStreamException;

    void startDocument(StartDocument startDocument) throws XMLStreamException;
}
