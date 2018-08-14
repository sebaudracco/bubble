package mf.org.apache.xerces.impl.xs.opti;

import java.util.ArrayList;
import java.util.Enumeration;
import mf.javax.xml.XMLConstants;
import mf.org.apache.xerces.util.XMLSymbols;
import mf.org.apache.xerces.xni.NamespaceContext;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xni.XMLAttributes;
import mf.org.apache.xerces.xni.XMLString;
import mf.org.w3c.dom.Attr;
import mf.org.w3c.dom.DOMImplementation;
import mf.org.w3c.dom.Element;
import mf.org.w3c.dom.NamedNodeMap;
import mf.org.w3c.dom.Node;

public class SchemaDOM extends DefaultDocument {
    static final int relationsColResizeFactor = 10;
    static final int relationsRowResizeFactor = 15;
    int currLoc;
    private StringBuffer fAnnotationBuffer = null;
    boolean hidden;
    boolean inCDATA;
    int nextFreeLoc;
    ElementImpl parent;
    NodeImpl[][] relations;

    public SchemaDOM() {
        reset();
    }

    public ElementImpl startElement(QName element, XMLAttributes attributes, int line, int column, int offset) {
        ElementImpl node = new ElementImpl(line, column, offset);
        processElement(element, attributes, node);
        this.parent = node;
        return node;
    }

    public ElementImpl emptyElement(QName element, XMLAttributes attributes, int line, int column, int offset) {
        ElementImpl node = new ElementImpl(line, column, offset);
        processElement(element, attributes, node);
        return node;
    }

    public ElementImpl startElement(QName element, XMLAttributes attributes, int line, int column) {
        return startElement(element, attributes, line, column, -1);
    }

    public ElementImpl emptyElement(QName element, XMLAttributes attributes, int line, int column) {
        return emptyElement(element, attributes, line, column, -1);
    }

    private void processElement(QName element, XMLAttributes attributes, ElementImpl node) {
        int i;
        node.prefix = element.prefix;
        node.localpart = element.localpart;
        node.rawname = element.rawname;
        node.uri = element.uri;
        node.schemaDOM = this;
        Attr[] attrs = new Attr[attributes.getLength()];
        for (i = 0; i < attributes.getLength(); i++) {
            attrs[i] = new AttrImpl(node, attributes.getPrefix(i), attributes.getLocalName(i), attributes.getQName(i), attributes.getURI(i), attributes.getValue(i));
        }
        node.attrs = attrs;
        if (this.nextFreeLoc == this.relations.length) {
            resizeRelations();
        }
        if (this.relations[this.currLoc][0] != this.parent) {
            this.relations[this.nextFreeLoc][0] = this.parent;
            int i2 = this.nextFreeLoc;
            this.nextFreeLoc = i2 + 1;
            this.currLoc = i2;
        }
        boolean foundPlace = false;
        i = 1;
        while (i < this.relations[this.currLoc].length) {
            if (this.relations[this.currLoc][i] == null) {
                foundPlace = true;
                break;
            }
            i++;
        }
        if (!foundPlace) {
            resizeRelations(this.currLoc);
        }
        this.relations[this.currLoc][i] = node;
        this.parent.parentRow = this.currLoc;
        node.row = this.currLoc;
        node.col = i;
    }

    public void endElement() {
        this.currLoc = this.parent.row;
        this.parent = (ElementImpl) this.relations[this.currLoc][0];
    }

    void comment(XMLString text) {
        this.fAnnotationBuffer.append("<!--");
        if (text.length > 0) {
            this.fAnnotationBuffer.append(text.ch, text.offset, text.length);
        }
        this.fAnnotationBuffer.append("-->");
    }

    void processingInstruction(String target, XMLString data) {
        this.fAnnotationBuffer.append("<?").append(target);
        if (data.length > 0) {
            this.fAnnotationBuffer.append(' ').append(data.ch, data.offset, data.length);
        }
        this.fAnnotationBuffer.append("?>");
    }

    void characters(XMLString text) {
        if (this.inCDATA) {
            this.fAnnotationBuffer.append(text.ch, text.offset, text.length);
            return;
        }
        StringBuffer annotationBuffer = this.fAnnotationBuffer;
        for (int i = text.offset; i < text.offset + text.length; i++) {
            char ch = text.ch[i];
            if (ch == '&') {
                annotationBuffer.append("&amp;");
            } else if (ch == '<') {
                annotationBuffer.append("&lt;");
            } else if (ch == '>') {
                annotationBuffer.append("&gt;");
            } else if (ch == '\r') {
                annotationBuffer.append("&#xD;");
            } else {
                annotationBuffer.append(ch);
            }
        }
    }

    void charactersRaw(String text) {
        this.fAnnotationBuffer.append(text);
    }

    void endAnnotation(QName elemName, ElementImpl annotation) {
        this.fAnnotationBuffer.append("\n</").append(elemName.rawname).append(">");
        annotation.fAnnotation = this.fAnnotationBuffer.toString();
        this.fAnnotationBuffer = null;
    }

    void endAnnotationElement(QName elemName) {
        endAnnotationElement(elemName.rawname);
    }

    void endAnnotationElement(String elemRawName) {
        this.fAnnotationBuffer.append("</").append(elemRawName).append(">");
    }

    void endSyntheticAnnotationElement(QName elemName, boolean complete) {
        endSyntheticAnnotationElement(elemName.rawname, complete);
    }

    void endSyntheticAnnotationElement(String elemRawName, boolean complete) {
        if (complete) {
            this.fAnnotationBuffer.append("\n</").append(elemRawName).append(">");
            this.parent.fSyntheticAnnotation = this.fAnnotationBuffer.toString();
            this.fAnnotationBuffer = null;
            return;
        }
        this.fAnnotationBuffer.append("</").append(elemRawName).append(">");
    }

    void startAnnotationCDATA() {
        this.inCDATA = true;
        this.fAnnotationBuffer.append("<![CDATA[");
    }

    void endAnnotationCDATA() {
        this.fAnnotationBuffer.append("]]>");
        this.inCDATA = false;
    }

    private void resizeRelations() {
        NodeImpl[][] temp = new NodeImpl[(this.relations.length + 15)][];
        System.arraycopy(this.relations, 0, temp, 0, this.relations.length);
        for (int i = this.relations.length; i < temp.length; i++) {
            temp[i] = new NodeImpl[10];
        }
        this.relations = temp;
    }

    private void resizeRelations(int i) {
        NodeImpl[] temp = new NodeImpl[(this.relations[i].length + 10)];
        System.arraycopy(this.relations[i], 0, temp, 0, this.relations[i].length);
        this.relations[i] = temp;
    }

    public void reset() {
        int i;
        if (this.relations != null) {
            for (i = 0; i < this.relations.length; i++) {
                for (int j = 0; j < this.relations[i].length; j++) {
                    this.relations[i][j] = null;
                }
            }
        }
        this.relations = new NodeImpl[15][];
        this.parent = new ElementImpl(0, 0, 0);
        this.parent.rawname = "DOCUMENT_NODE";
        this.currLoc = 0;
        this.nextFreeLoc = 1;
        this.inCDATA = false;
        for (i = 0; i < 15; i++) {
            this.relations[i] = new NodeImpl[10];
        }
        this.relations[this.currLoc][0] = this.parent;
    }

    public void printDOM() {
    }

    public static void traverse(Node node, int depth) {
        indent(depth);
        System.out.print("<" + node.getNodeName());
        if (node.hasAttributes()) {
            NamedNodeMap attrs = node.getAttributes();
            for (int i = 0; i < attrs.getLength(); i++) {
                System.out.print("  " + ((Attr) attrs.item(i)).getName() + "=\"" + ((Attr) attrs.item(i)).getValue() + "\"");
            }
        }
        if (node.hasChildNodes()) {
            System.out.println(">");
            depth += 4;
            for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
                traverse(child, depth);
            }
            indent(depth - 4);
            System.out.println("</" + node.getNodeName() + ">");
            return;
        }
        System.out.println("/>");
    }

    public static void indent(int amount) {
        for (int i = 0; i < amount; i++) {
            System.out.print(' ');
        }
    }

    public Element getDocumentElement() {
        return (ElementImpl) this.relations[0][1];
    }

    public DOMImplementation getImplementation() {
        return SchemaDOMImplementation.getDOMImplementation();
    }

    void startAnnotation(QName elemName, XMLAttributes attributes, NamespaceContext namespaceContext) {
        startAnnotation(elemName.rawname, attributes, namespaceContext);
    }

    void startAnnotation(String elemRawName, XMLAttributes attributes, NamespaceContext namespaceContext) {
        if (this.fAnnotationBuffer == null) {
            this.fAnnotationBuffer = new StringBuffer(256);
        }
        this.fAnnotationBuffer.append("<").append(elemRawName).append(" ");
        ArrayList namespaces = new ArrayList();
        int i = 0;
        while (i < attributes.getLength()) {
            String aValue = attributes.getValue(i);
            String aPrefix = attributes.getPrefix(i);
            String aQName = attributes.getQName(i);
            if (aPrefix == XMLSymbols.PREFIX_XMLNS || aQName == XMLSymbols.PREFIX_XMLNS) {
                namespaces.add(aPrefix == XMLSymbols.PREFIX_XMLNS ? attributes.getLocalName(i) : XMLSymbols.EMPTY_STRING);
            }
            this.fAnnotationBuffer.append(aQName).append("=\"").append(processAttValue(aValue)).append("\" ");
            i++;
        }
        Enumeration currPrefixes = namespaceContext.getAllPrefixes();
        while (currPrefixes.hasMoreElements()) {
            String prefix = (String) currPrefixes.nextElement();
            String uri = namespaceContext.getURI(prefix);
            if (uri == null) {
                uri = XMLSymbols.EMPTY_STRING;
            }
            if (!namespaces.contains(prefix)) {
                if (prefix == XMLSymbols.EMPTY_STRING) {
                    this.fAnnotationBuffer.append(XMLConstants.XMLNS_ATTRIBUTE).append("=\"").append(processAttValue(uri)).append("\" ");
                } else {
                    this.fAnnotationBuffer.append("xmlns:").append(prefix).append("=\"").append(processAttValue(uri)).append("\" ");
                }
            }
        }
        this.fAnnotationBuffer.append(">\n");
    }

    void startAnnotationElement(QName elemName, XMLAttributes attributes) {
        startAnnotationElement(elemName.rawname, attributes);
    }

    void startAnnotationElement(String elemRawName, XMLAttributes attributes) {
        this.fAnnotationBuffer.append("<").append(elemRawName);
        for (int i = 0; i < attributes.getLength(); i++) {
            this.fAnnotationBuffer.append(" ").append(attributes.getQName(i)).append("=\"").append(processAttValue(attributes.getValue(i))).append("\"");
        }
        this.fAnnotationBuffer.append(">");
    }

    private static String processAttValue(String original) {
        int length = original.length();
        for (int i = 0; i < length; i++) {
            char currChar = original.charAt(i);
            if (currChar == '\"' || currChar == '<' || currChar == '&' || currChar == '\t' || currChar == '\n' || currChar == '\r') {
                return escapeAttValue(original, i);
            }
        }
        return original;
    }

    private static String escapeAttValue(String original, int from) {
        int length = original.length();
        StringBuffer newVal = new StringBuffer(length);
        newVal.append(original.substring(0, from));
        for (int i = from; i < length; i++) {
            char currChar = original.charAt(i);
            if (currChar == '\"') {
                newVal.append("&quot;");
            } else if (currChar == '<') {
                newVal.append("&lt;");
            } else if (currChar == '&') {
                newVal.append("&amp;");
            } else if (currChar == '\t') {
                newVal.append("&#x9;");
            } else if (currChar == '\n') {
                newVal.append("&#xA;");
            } else if (currChar == '\r') {
                newVal.append("&#xD;");
            } else {
                newVal.append(currChar);
            }
        }
        return newVal.toString();
    }
}
