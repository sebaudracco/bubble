package mf.org.apache.html.dom;

import java.util.Vector;
import mf.org.apache.xerces.dom.ElementImpl;
import mf.org.apache.xerces.dom.ProcessingInstructionImpl;
import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.html.HTMLDocument;
import org.xml.sax.AttributeList;
import org.xml.sax.DocumentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class HTMLBuilder implements DocumentHandler {
    protected ElementImpl _current;
    protected HTMLDocumentImpl _document;
    private boolean _done = true;
    private boolean _ignoreWhitespace = true;
    protected Vector _preRootNodes;

    public void startDocument() throws SAXException {
        if (this._done) {
            this._document = null;
            this._done = false;
            return;
        }
        throw new SAXException("HTM001 State error: startDocument fired twice on one builder.");
    }

    public void endDocument() throws SAXException {
        if (this._document == null) {
            throw new SAXException("HTM002 State error: document never started or missing document element.");
        } else if (this._current != null) {
            throw new SAXException("HTM003 State error: document ended before end of document element.");
        } else {
            this._current = null;
            this._done = true;
        }
    }

    public synchronized void startElement(String tagName, AttributeList attrList) throws SAXException {
        if (tagName == null) {
            throw new SAXException("HTM004 Argument 'tagName' is null.");
        }
        ElementImpl elem;
        int i;
        if (this._document == null) {
            this._document = new HTMLDocumentImpl();
            elem = (ElementImpl) this._document.getDocumentElement();
            this._current = elem;
            if (this._current == null) {
                throw new SAXException("HTM005 State error: Document.getDocumentElement returns null.");
            } else if (this._preRootNodes != null) {
                int i2 = this._preRootNodes.size();
                while (true) {
                    i = i2 - 1;
                    if (i2 <= 0) {
                        break;
                    }
                    this._document.insertBefore((Node) this._preRootNodes.elementAt(i), elem);
                    i2 = i;
                }
                this._preRootNodes = null;
            }
        } else if (this._current == null) {
            throw new SAXException("HTM006 State error: startElement called after end of document element.");
        } else {
            elem = (ElementImpl) this._document.createElement(tagName);
            this._current.appendChild(elem);
            this._current = elem;
        }
        if (attrList != null) {
            for (i = 0; i < attrList.getLength(); i++) {
                elem.setAttribute(attrList.getName(i), attrList.getValue(i));
            }
        }
    }

    public void endElement(String tagName) throws SAXException {
        if (this._current == null) {
            throw new SAXException("HTM007 State error: endElement called with no current node.");
        } else if (!this._current.getNodeName().equalsIgnoreCase(tagName)) {
            throw new SAXException("HTM008 State error: mismatch in closing tag name " + tagName + "\n" + tagName);
        } else if (this._current.getParentNode() == this._current.getOwnerDocument()) {
            this._current = null;
        } else {
            this._current = (ElementImpl) this._current.getParentNode();
        }
    }

    public void characters(String text) throws SAXException {
        if (this._current == null) {
            throw new SAXException("HTM009 State error: character data found outside of root element.");
        }
        this._current.appendChild(this._document.createTextNode(text));
    }

    public void characters(char[] text, int start, int length) throws SAXException {
        if (this._current == null) {
            throw new SAXException("HTM010 State error: character data found outside of root element.");
        }
        this._current.appendChild(this._document.createTextNode(new String(text, start, length)));
    }

    public void ignorableWhitespace(char[] text, int start, int length) throws SAXException {
        if (!this._ignoreWhitespace) {
            this._current.appendChild(this._document.createTextNode(new String(text, start, length)));
        }
    }

    public void processingInstruction(String target, String instruction) throws SAXException {
        if (this._current == null && this._document == null) {
            if (this._preRootNodes == null) {
                this._preRootNodes = new Vector();
            }
            this._preRootNodes.addElement(new ProcessingInstructionImpl(null, target, instruction));
        } else if (this._current != null || this._document == null) {
            this._current.appendChild(this._document.createProcessingInstruction(target, instruction));
        } else {
            this._document.appendChild(this._document.createProcessingInstruction(target, instruction));
        }
    }

    public HTMLDocument getHTMLDocument() {
        return this._document;
    }

    public void setDocumentLocator(Locator locator) {
    }
}
