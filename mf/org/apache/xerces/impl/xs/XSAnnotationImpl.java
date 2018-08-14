package mf.org.apache.xerces.impl.xs;

import java.io.IOException;
import java.io.StringReader;
import mf.org.apache.xerces.dom.CoreDocumentImpl;
import mf.org.apache.xerces.parsers.DOMParser;
import mf.org.apache.xerces.parsers.SAXParser;
import mf.org.apache.xerces.xs.XSAnnotation;
import mf.org.apache.xerces.xs.XSNamespaceItem;
import mf.org.w3c.dom.Document;
import mf.org.w3c.dom.Element;
import mf.org.w3c.dom.Node;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XSAnnotationImpl implements XSAnnotation {
    private String fData = null;
    private SchemaGrammar fGrammar = null;

    public XSAnnotationImpl(String contents, SchemaGrammar grammar) {
        this.fData = contents;
        this.fGrammar = grammar;
    }

    public boolean writeAnnotation(Object target, short targetType) {
        if (targetType == (short) 1 || targetType == (short) 3) {
            writeToDOM((Node) target, targetType);
            return true;
        } else if (targetType != (short) 2) {
            return false;
        } else {
            writeToSAX((ContentHandler) target);
            return true;
        }
    }

    public String getAnnotationString() {
        return this.fData;
    }

    public short getType() {
        return (short) 12;
    }

    public String getName() {
        return null;
    }

    public String getNamespace() {
        return null;
    }

    public XSNamespaceItem getNamespaceItem() {
        return null;
    }

    private synchronized void writeToSAX(ContentHandler handler) {
        SAXParser parser = this.fGrammar.getSAXParser();
        InputSource aSource = new InputSource(new StringReader(this.fData));
        parser.setContentHandler(handler);
        try {
            parser.parse(aSource);
        } catch (SAXException e) {
        } catch (IOException e2) {
        }
        parser.setContentHandler(null);
    }

    private synchronized void writeToDOM(Node target, short type) {
        Document futureOwner;
        Node newElem;
        if (type == (short) 1) {
            futureOwner = target.getOwnerDocument();
        } else {
            futureOwner = (Document) target;
        }
        DOMParser parser = this.fGrammar.getDOMParser();
        try {
            parser.parse(new InputSource(new StringReader(this.fData)));
        } catch (SAXException e) {
        } catch (IOException e2) {
        }
        Document aDocument = parser.getDocument();
        parser.dropDocumentReferences();
        Element annotation = aDocument.getDocumentElement();
        if (futureOwner instanceof CoreDocumentImpl) {
            newElem = futureOwner.adoptNode(annotation);
            if (newElem == null) {
                newElem = futureOwner.importNode(annotation, true);
            }
        } else {
            newElem = futureOwner.importNode(annotation, true);
        }
        target.insertBefore(newElem, target.getFirstChild());
    }
}
