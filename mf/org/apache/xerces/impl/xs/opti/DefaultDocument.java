package mf.org.apache.xerces.impl.xs.opti;

import mf.org.w3c.dom.Attr;
import mf.org.w3c.dom.CDATASection;
import mf.org.w3c.dom.Comment;
import mf.org.w3c.dom.DOMConfiguration;
import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.DOMImplementation;
import mf.org.w3c.dom.Document;
import mf.org.w3c.dom.DocumentFragment;
import mf.org.w3c.dom.DocumentType;
import mf.org.w3c.dom.Element;
import mf.org.w3c.dom.EntityReference;
import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.NodeList;
import mf.org.w3c.dom.ProcessingInstruction;
import mf.org.w3c.dom.Text;

public class DefaultDocument extends NodeImpl implements Document {
    private String fDocumentURI;

    public DefaultDocument() {
        this.fDocumentURI = null;
        this.nodeType = (short) 9;
    }

    public String getNodeName() {
        return "#document";
    }

    public DocumentType getDoctype() {
        return null;
    }

    public DOMImplementation getImplementation() {
        return null;
    }

    public Element getDocumentElement() {
        return null;
    }

    public NodeList getElementsByTagName(String tagname) {
        return null;
    }

    public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
        return null;
    }

    public Element getElementById(String elementId) {
        return null;
    }

    public Node importNode(Node importedNode, boolean deep) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    public Element createElement(String tagName) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    public DocumentFragment createDocumentFragment() {
        return null;
    }

    public Text createTextNode(String data) {
        return null;
    }

    public Comment createComment(String data) {
        return null;
    }

    public CDATASection createCDATASection(String data) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    public ProcessingInstruction createProcessingInstruction(String target, String data) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    public Attr createAttribute(String name) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    public EntityReference createEntityReference(String name) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    public Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    public Attr createAttributeNS(String namespaceURI, String qualifiedName) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    public String getInputEncoding() {
        return null;
    }

    public String getXmlEncoding() {
        return null;
    }

    public boolean getXmlStandalone() {
        throw new DOMException((short) 9, "Method not supported");
    }

    public void setXmlStandalone(boolean standalone) {
        throw new DOMException((short) 9, "Method not supported");
    }

    public String getXmlVersion() {
        return null;
    }

    public void setXmlVersion(String version) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    public boolean getStrictErrorChecking() {
        return false;
    }

    public void setStrictErrorChecking(boolean strictErrorChecking) {
        throw new DOMException((short) 9, "Method not supported");
    }

    public String getDocumentURI() {
        return this.fDocumentURI;
    }

    public void setDocumentURI(String documentURI) {
        this.fDocumentURI = documentURI;
    }

    public Node adoptNode(Node source) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    public void normalizeDocument() {
        throw new DOMException((short) 9, "Method not supported");
    }

    public DOMConfiguration getDomConfig() {
        throw new DOMException((short) 9, "Method not supported");
    }

    public Node renameNode(Node n, String namespaceURI, String name) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }
}
