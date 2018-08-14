package mf.org.apache.html.dom;

import com.mobfox.sdk.utils.Utils;
import java.io.StringWriter;
import java.util.Hashtable;
import java.util.Locale;
import mf.org.apache.xerces.dom.DocumentImpl;
import mf.org.apache.xerces.dom.ElementImpl;
import mf.org.w3c.dom.Attr;
import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.Element;
import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.NodeList;
import mf.org.w3c.dom.html.HTMLBodyElement;
import mf.org.w3c.dom.html.HTMLCollection;
import mf.org.w3c.dom.html.HTMLDocument;
import mf.org.w3c.dom.html.HTMLElement;
import mf.org.w3c.dom.html.HTMLFrameSetElement;
import mf.org.w3c.dom.html.HTMLHeadElement;
import mf.org.w3c.dom.html.HTMLHtmlElement;
import mf.org.w3c.dom.html.HTMLTitleElement;

public class HTMLDocumentImpl extends DocumentImpl implements HTMLDocument {
    private static final Class[] _elemClassSigHTML = new Class[]{HTMLDocumentImpl.class, String.class};
    private static Hashtable _elementTypesHTML = null;
    private static final long serialVersionUID = 4285791750126227180L;
    private HTMLCollectionImpl _anchors;
    private HTMLCollectionImpl _applets;
    private HTMLCollectionImpl _forms;
    private HTMLCollectionImpl _images;
    private HTMLCollectionImpl _links;
    private StringWriter _writer;

    public HTMLDocumentImpl() {
        populateElementTypes();
    }

    public synchronized Element getDocumentElement() {
        Element element;
        Node html;
        for (html = getFirstChild(); html != null; html = html.getNextSibling()) {
            if (html instanceof HTMLHtmlElement) {
                Object obj = (HTMLElement) html;
                break;
            }
        }
        html = new HTMLHtmlElementImpl(this, "HTML");
        Node child = getFirstChild();
        while (child != null) {
            Node next = child.getNextSibling();
            html.appendChild(child);
            child = next;
        }
        appendChild(html);
        element = (HTMLElement) html;
        return element;
    }

    public synchronized HTMLElement getHead() {
        HTMLElement hTMLElement;
        Node html = getDocumentElement();
        synchronized (html) {
            Node head = html.getFirstChild();
            while (head != null && !(head instanceof HTMLHeadElement)) {
                head = head.getNextSibling();
            }
            if (head != null) {
                synchronized (head) {
                    Node child = html.getFirstChild();
                    while (child != null && child != head) {
                        Node next = child.getNextSibling();
                        head.insertBefore(child, head.getFirstChild());
                        child = next;
                    }
                }
                hTMLElement = (HTMLElement) head;
            } else {
                head = new HTMLHeadElementImpl(this, "HEAD");
                html.insertBefore(head, html.getFirstChild());
                hTMLElement = (HTMLElement) head;
            }
        }
        return hTMLElement;
    }

    public synchronized String getTitle() {
        String text;
        NodeList list = getHead().getElementsByTagName("TITLE");
        if (list.getLength() > 0) {
            text = ((HTMLTitleElement) list.item(0)).getText();
        } else {
            text = "";
        }
        return text;
    }

    public synchronized void setTitle(String newTitle) {
        Node head = getHead();
        NodeList list = head.getElementsByTagName("TITLE");
        Node title;
        if (list.getLength() > 0) {
            title = list.item(0);
            if (title.getParentNode() != head) {
                head.appendChild(title);
            }
            ((HTMLTitleElement) title).setText(newTitle);
        } else {
            title = new HTMLTitleElementImpl(this, "TITLE");
            ((HTMLTitleElement) title).setText(newTitle);
            head.appendChild(title);
        }
    }

    public synchronized HTMLElement getBody() {
        HTMLElement hTMLElement;
        Node html = getDocumentElement();
        Node head = getHead();
        synchronized (html) {
            Node body = head.getNextSibling();
            while (body != null && !(body instanceof HTMLBodyElement) && !(body instanceof HTMLFrameSetElement)) {
                body = body.getNextSibling();
            }
            if (body != null) {
                synchronized (body) {
                    Node child = head.getNextSibling();
                    while (child != null && child != body) {
                        Node next = child.getNextSibling();
                        body.insertBefore(child, body.getFirstChild());
                        child = next;
                    }
                }
                hTMLElement = (HTMLElement) body;
            } else {
                body = new HTMLBodyElementImpl(this, "BODY");
                html.appendChild(body);
                hTMLElement = (HTMLElement) body;
            }
        }
        return hTMLElement;
    }

    public synchronized void setBody(HTMLElement newBody) {
        synchronized (newBody) {
            Node html = getDocumentElement();
            Node head = getHead();
            synchronized (html) {
                NodeList list = getElementsByTagName("BODY");
                if (list.getLength() > 0) {
                    Node body = list.item(0);
                    synchronized (body) {
                        Node child = head;
                        while (child != null) {
                            if (child instanceof Element) {
                                if (child != body) {
                                    html.insertBefore(newBody, child);
                                } else {
                                    html.replaceChild(newBody, body);
                                }
                            } else {
                                child = child.getNextSibling();
                            }
                        }
                        html.appendChild(newBody);
                    }
                } else {
                    html.appendChild(newBody);
                }
            }
        }
    }

    public synchronized Element getElementById(String elementId) {
        Element idElement;
        idElement = super.getElementById(elementId);
        if (idElement == null) {
            idElement = getElementById(elementId, this);
        }
        return idElement;
    }

    public NodeList getElementsByName(String elementName) {
        return new NameNodeListImpl(this, elementName);
    }

    public final NodeList getElementsByTagName(String tagName) {
        return super.getElementsByTagName(tagName.toUpperCase(Locale.ENGLISH));
    }

    public final NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
        if (namespaceURI == null || namespaceURI.length() <= 0) {
            return super.getElementsByTagName(localName.toUpperCase(Locale.ENGLISH));
        }
        return super.getElementsByTagNameNS(namespaceURI, localName.toUpperCase(Locale.ENGLISH));
    }

    public Element createElementNS(String namespaceURI, String qualifiedName, String localpart) throws DOMException {
        return createElementNS(namespaceURI, qualifiedName);
    }

    public Element createElementNS(String namespaceURI, String qualifiedName) {
        if (namespaceURI == null || namespaceURI.length() == 0) {
            return createElement(qualifiedName);
        }
        return super.createElementNS(namespaceURI, qualifiedName);
    }

    public Element createElement(String tagName) throws DOMException {
        tagName = tagName.toUpperCase(Locale.ENGLISH);
        Class elemClass = (Class) _elementTypesHTML.get(tagName);
        if (elemClass == null) {
            return new HTMLElementImpl(this, tagName);
        }
        try {
            return (Element) elemClass.getConstructor(_elemClassSigHTML).newInstance(new Object[]{this, tagName});
        } catch (Exception e) {
            throw new IllegalStateException("HTM15 Tag '" + tagName + "' associated with an Element class that failed to construct.\n" + tagName);
        }
    }

    public Attr createAttribute(String name) throws DOMException {
        return super.createAttribute(name.toLowerCase(Locale.ENGLISH));
    }

    public String getReferrer() {
        return null;
    }

    public String getDomain() {
        return null;
    }

    public String getURL() {
        return null;
    }

    public String getCookie() {
        return null;
    }

    public void setCookie(String cookie) {
    }

    public HTMLCollection getImages() {
        if (this._images == null) {
            this._images = new HTMLCollectionImpl(getBody(), (short) 3);
        }
        return this._images;
    }

    public HTMLCollection getApplets() {
        if (this._applets == null) {
            this._applets = new HTMLCollectionImpl(getBody(), (short) 4);
        }
        return this._applets;
    }

    public HTMLCollection getLinks() {
        if (this._links == null) {
            this._links = new HTMLCollectionImpl(getBody(), (short) 5);
        }
        return this._links;
    }

    public HTMLCollection getForms() {
        if (this._forms == null) {
            this._forms = new HTMLCollectionImpl(getBody(), (short) 2);
        }
        return this._forms;
    }

    public HTMLCollection getAnchors() {
        if (this._anchors == null) {
            this._anchors = new HTMLCollectionImpl(getBody(), (short) 1);
        }
        return this._anchors;
    }

    public void open() {
        if (this._writer == null) {
            this._writer = new StringWriter();
        }
    }

    public void close() {
        if (this._writer != null) {
            this._writer = null;
        }
    }

    public void write(String text) {
        if (this._writer != null) {
            this._writer.write(text);
        }
    }

    public void writeln(String text) {
        if (this._writer != null) {
            this._writer.write(new StringBuilder(String.valueOf(text)).append("\n").toString());
        }
    }

    public Node cloneNode(boolean deep) {
        HTMLDocumentImpl newdoc = new HTMLDocumentImpl();
        callUserDataHandlers(this, newdoc, (short) 1);
        cloneNode(newdoc, deep);
        return newdoc;
    }

    protected boolean canRenameElements(String newNamespaceURI, String newNodeName, ElementImpl el) {
        if (el.getNamespaceURI() != null) {
            if (newNamespaceURI != null) {
                return true;
            }
            return false;
        } else if (((Class) _elementTypesHTML.get(newNodeName.toUpperCase(Locale.ENGLISH))) != ((Class) _elementTypesHTML.get(el.getTagName()))) {
            return false;
        } else {
            return true;
        }
    }

    private Element getElementById(String elementId, Node node) {
        for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child instanceof Element) {
                if (elementId.equals(((Element) child).getAttribute("id"))) {
                    return (Element) child;
                }
                Element result = getElementById(elementId, child);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    private static synchronized void populateElementTypes() {
        synchronized (HTMLDocumentImpl.class) {
            if (_elementTypesHTML == null) {
                _elementTypesHTML = new Hashtable(63);
                populateElementType("A", "HTMLAnchorElementImpl");
                populateElementType("APPLET", "HTMLAppletElementImpl");
                populateElementType("AREA", "HTMLAreaElementImpl");
                populateElementType("BASE", "HTMLBaseElementImpl");
                populateElementType("BASEFONT", "HTMLBaseFontElementImpl");
                populateElementType("BLOCKQUOTE", "HTMLQuoteElementImpl");
                populateElementType("BODY", "HTMLBodyElementImpl");
                populateElementType("BR", "HTMLBRElementImpl");
                populateElementType("BUTTON", "HTMLButtonElementImpl");
                populateElementType("DEL", "HTMLModElementImpl");
                populateElementType("DIR", "HTMLDirectoryElementImpl");
                populateElementType("DIV", "HTMLDivElementImpl");
                populateElementType("DL", "HTMLDListElementImpl");
                populateElementType("FIELDSET", "HTMLFieldSetElementImpl");
                populateElementType("FONT", "HTMLFontElementImpl");
                populateElementType("FORM", "HTMLFormElementImpl");
                populateElementType("FRAME", "HTMLFrameElementImpl");
                populateElementType("FRAMESET", "HTMLFrameSetElementImpl");
                populateElementType("HEAD", "HTMLHeadElementImpl");
                populateElementType("H1", "HTMLHeadingElementImpl");
                populateElementType("H2", "HTMLHeadingElementImpl");
                populateElementType("H3", "HTMLHeadingElementImpl");
                populateElementType("H4", "HTMLHeadingElementImpl");
                populateElementType("H5", "HTMLHeadingElementImpl");
                populateElementType("H6", "HTMLHeadingElementImpl");
                populateElementType("HR", "HTMLHRElementImpl");
                populateElementType("HTML", "HTMLHtmlElementImpl");
                populateElementType("IFRAME", "HTMLIFrameElementImpl");
                populateElementType("IMG", "HTMLImageElementImpl");
                populateElementType("INPUT", "HTMLInputElementImpl");
                populateElementType("INS", "HTMLModElementImpl");
                populateElementType("ISINDEX", "HTMLIsIndexElementImpl");
                populateElementType("LABEL", "HTMLLabelElementImpl");
                populateElementType("LEGEND", "HTMLLegendElementImpl");
                populateElementType("LI", "HTMLLIElementImpl");
                populateElementType("LINK", "HTMLLinkElementImpl");
                populateElementType("MAP", "HTMLMapElementImpl");
                populateElementType("MENU", "HTMLMenuElementImpl");
                populateElementType("META", "HTMLMetaElementImpl");
                populateElementType("OBJECT", "HTMLObjectElementImpl");
                populateElementType("OL", "HTMLOListElementImpl");
                populateElementType("OPTGROUP", "HTMLOptGroupElementImpl");
                populateElementType("OPTION", "HTMLOptionElementImpl");
                populateElementType("P", "HTMLParagraphElementImpl");
                populateElementType("PARAM", "HTMLParamElementImpl");
                populateElementType("PRE", "HTMLPreElementImpl");
                populateElementType("Q", "HTMLQuoteElementImpl");
                populateElementType("SCRIPT", "HTMLScriptElementImpl");
                populateElementType("SELECT", "HTMLSelectElementImpl");
                populateElementType("STYLE", "HTMLStyleElementImpl");
                populateElementType("TABLE", "HTMLTableElementImpl");
                populateElementType("CAPTION", "HTMLTableCaptionElementImpl");
                populateElementType("TD", "HTMLTableCellElementImpl");
                populateElementType("TH", "HTMLTableCellElementImpl");
                populateElementType("COL", "HTMLTableColElementImpl");
                populateElementType("COLGROUP", "HTMLTableColElementImpl");
                populateElementType("TR", "HTMLTableRowElementImpl");
                populateElementType("TBODY", "HTMLTableSectionElementImpl");
                populateElementType("THEAD", "HTMLTableSectionElementImpl");
                populateElementType("TFOOT", "HTMLTableSectionElementImpl");
                populateElementType("TEXTAREA", "HTMLTextAreaElementImpl");
                populateElementType("TITLE", "HTMLTitleElementImpl");
                populateElementType("UL", "HTMLUListElementImpl");
            }
        }
    }

    private static void populateElementType(String tagName, String className) {
        try {
            _elementTypesHTML.put(tagName, ObjectFactory.findProviderClass("org.apache.html.dom." + className, HTMLDocumentImpl.class.getClassLoader(), true));
        } catch (Exception e) {
            throw new RuntimeException("HTM019 OpenXML Error: Could not find or execute class " + className + " implementing HTML element " + tagName + "\n" + className + Utils.FILE_SEPARATOR + tagName);
        }
    }
}
