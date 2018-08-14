package mf.org.apache.xml.serialize;

import java.io.IOException;
import mf.org.w3c.dom.Element;
import mf.org.w3c.dom.Node;
import org.xml.sax.AttributeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class TextSerializer extends BaseMarkupSerializer {
    public TextSerializer() {
        super(new OutputFormat("text", null, false));
    }

    public void setOutputFormat(OutputFormat format) {
        if (format == null) {
            format = new OutputFormat("text", null, false);
        }
        super.setOutputFormat(format);
    }

    public void startElement(String namespaceURI, String localName, String rawName, Attributes attrs) throws SAXException {
        if (rawName != null) {
            localName = rawName;
        }
        startElement(localName, null);
    }

    public void endElement(String namespaceURI, String localName, String rawName) throws SAXException {
        if (rawName != null) {
            localName = rawName;
        }
        endElement(localName);
    }

    public void startElement(String tagName, AttributeList attrs) throws SAXException {
        try {
            ElementState state = getElementState();
            if (isDocumentState() && !this._started) {
                startDocument(tagName);
            }
            state = enterElementState(null, null, tagName, state.preserveSpace);
        } catch (IOException except) {
            throw new SAXException(except);
        }
    }

    public void endElement(String tagName) throws SAXException {
        try {
            endElementIO(tagName);
        } catch (IOException except) {
            throw new SAXException(except);
        }
    }

    public void endElementIO(String tagName) throws IOException {
        ElementState elementState = getElementState();
        elementState = leaveElementState();
        elementState.afterElement = true;
        elementState.empty = false;
        if (isDocumentState()) {
            this._printer.flush();
        }
    }

    public void processingInstructionIO(String target, String code) throws IOException {
    }

    public void comment(String text) {
    }

    public void comment(char[] chars, int start, int length) {
    }

    public void characters(char[] chars, int start, int length) throws SAXException {
        try {
            ElementState state = content();
            state.inCData = false;
            state.doCData = false;
            printText(chars, start, length, true, true);
        } catch (IOException except) {
            throw new SAXException(except);
        }
    }

    protected void characters(String text, boolean unescaped) throws IOException {
        ElementState state = content();
        state.inCData = false;
        state.doCData = false;
        printText(text, true, true);
    }

    protected void startDocument(String rootTagName) throws IOException {
        this._printer.leaveDTD();
        this._started = true;
        serializePreRoot();
    }

    protected void serializeElement(Element elem) throws IOException {
        String tagName = elem.getTagName();
        ElementState state = getElementState();
        if (isDocumentState() && !this._started) {
            startDocument(tagName);
        }
        boolean preserveSpace = state.preserveSpace;
        if (elem.hasChildNodes()) {
            state = enterElementState(null, null, tagName, preserveSpace);
            for (Node child = elem.getFirstChild(); child != null; child = child.getNextSibling()) {
                serializeNode(child);
            }
            endElementIO(tagName);
        } else if (!isDocumentState()) {
            state.afterElement = true;
            state.empty = false;
        }
    }

    protected void serializeNode(Node node) throws IOException {
        switch (node.getNodeType()) {
            case (short) 1:
                serializeElement((Element) node);
                return;
            case (short) 3:
                if (node.getNodeValue() != null) {
                    characters(node.getNodeValue(), true);
                    return;
                }
                return;
            case (short) 4:
                if (node.getNodeValue() != null) {
                    characters(node.getNodeValue(), true);
                    return;
                }
                return;
            case (short) 9:
            case (short) 11:
                for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
                    serializeNode(child);
                }
                return;
            default:
                return;
        }
    }

    protected ElementState content() {
        ElementState state = getElementState();
        if (!isDocumentState()) {
            if (state.empty) {
                state.empty = false;
            }
            state.afterElement = false;
        }
        return state;
    }

    protected String getEntityRef(int ch) {
        return null;
    }
}
