package mf.org.apache.xml.serialize;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Locale;
import java.util.Map.Entry;
import mf.org.apache.xerces.dom.DOMMessageFormatter;
import mf.org.w3c.dom.Attr;
import mf.org.w3c.dom.Element;
import mf.org.w3c.dom.NamedNodeMap;
import mf.org.w3c.dom.Node;
import org.xml.sax.AttributeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class HTMLSerializer extends BaseMarkupSerializer {
    public static final String XHTMLNamespace = "http://www.w3.org/1999/xhtml";
    private boolean _xhtml;
    private String fUserXHTMLNamespace;

    protected HTMLSerializer(boolean xhtml, OutputFormat format) {
        super(format);
        this.fUserXHTMLNamespace = null;
        this._xhtml = xhtml;
    }

    public HTMLSerializer() {
        this(false, new OutputFormat("html", "ISO-8859-1", false));
    }

    public HTMLSerializer(OutputFormat format) {
        if (format == null) {
            format = new OutputFormat("html", "ISO-8859-1", false);
        }
        this(false, format);
    }

    public HTMLSerializer(Writer writer, OutputFormat format) {
        if (format == null) {
            format = new OutputFormat("html", "ISO-8859-1", false);
        }
        this(false, format);
        setOutputCharStream(writer);
    }

    public HTMLSerializer(OutputStream output, OutputFormat format) {
        if (format == null) {
            format = new OutputFormat("html", "ISO-8859-1", false);
        }
        this(false, format);
        setOutputByteStream(output);
    }

    public void setOutputFormat(OutputFormat format) {
        if (format == null) {
            format = new OutputFormat("html", "ISO-8859-1", false);
        }
        super.setOutputFormat(format);
    }

    public void setXHTMLNamespace(String newNamespace) {
        this.fUserXHTMLNamespace = newNamespace;
    }

    public void startElement(String namespaceURI, String localName, String rawName, Attributes attrs) throws SAXException {
        boolean addNSAttr = false;
        try {
            if (this._printer == null) {
                throw new IllegalStateException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "NoWriterSupplied", null));
            }
            String htmlName;
            String name;
            String value;
            ElementState state = getElementState();
            if (!isDocumentState()) {
                if (state.empty) {
                    this._printer.printText('>');
                }
                if (this._indenting && !state.preserveSpace && (state.empty || state.afterElement)) {
                    this._printer.breakLine();
                }
            } else if (!this._started) {
                String str;
                if (localName == null || localName.length() == 0) {
                    str = rawName;
                } else {
                    str = localName;
                }
                startDocument(str);
            }
            boolean preserveSpace = state.preserveSpace;
            boolean hasNamespaceURI = (namespaceURI == null || namespaceURI.length() == 0) ? false : true;
            if (rawName == null || rawName.length() == 0) {
                rawName = localName;
                if (hasNamespaceURI) {
                    String prefix = getPrefix(namespaceURI);
                    if (!(prefix == null || prefix.length() == 0)) {
                        rawName = new StringBuilder(String.valueOf(prefix)).append(":").append(localName).toString();
                    }
                }
                addNSAttr = true;
            }
            if (!hasNamespaceURI) {
                htmlName = rawName;
            } else if (namespaceURI.equals(XHTMLNamespace) || (this.fUserXHTMLNamespace != null && this.fUserXHTMLNamespace.equals(namespaceURI))) {
                htmlName = localName;
            } else {
                htmlName = null;
            }
            this._printer.printText('<');
            if (this._xhtml) {
                this._printer.printText(rawName.toLowerCase(Locale.ENGLISH));
            } else {
                this._printer.printText(rawName);
            }
            this._printer.indent();
            if (attrs != null) {
                for (int i = 0; i < attrs.getLength(); i++) {
                    this._printer.printSpace();
                    name = attrs.getQName(i).toLowerCase(Locale.ENGLISH);
                    value = attrs.getValue(i);
                    if (!this._xhtml && !hasNamespaceURI) {
                        if (value == null) {
                            value = "";
                        }
                        if (!this._format.getPreserveEmptyAttributes() && value.length() == 0) {
                            this._printer.printText(name);
                        } else if (HTMLdtd.isURI(rawName, name)) {
                            this._printer.printText(name);
                            this._printer.printText("=\"");
                            this._printer.printText(escapeURI(value));
                            this._printer.printText('\"');
                        } else if (HTMLdtd.isBoolean(rawName, name)) {
                            this._printer.printText(name);
                        } else {
                            this._printer.printText(name);
                            this._printer.printText("=\"");
                            printEscaped(value);
                            this._printer.printText('\"');
                        }
                    } else if (value == null) {
                        this._printer.printText(name);
                        this._printer.printText("=\"\"");
                    } else {
                        this._printer.printText(name);
                        this._printer.printText("=\"");
                        printEscaped(value);
                        this._printer.printText('\"');
                    }
                }
            }
            if (htmlName != null && HTMLdtd.isPreserveSpace(htmlName)) {
                preserveSpace = true;
            }
            if (addNSAttr) {
                for (Entry entry : this._prefixes.entrySet()) {
                    this._printer.printSpace();
                    value = (String) entry.getKey();
                    name = (String) entry.getValue();
                    if (name.length() == 0) {
                        this._printer.printText("xmlns=\"");
                        printEscaped(value);
                        this._printer.printText('\"');
                    } else {
                        this._printer.printText("xmlns:");
                        this._printer.printText(name);
                        this._printer.printText("=\"");
                        printEscaped(value);
                        this._printer.printText('\"');
                    }
                }
            }
            state = enterElementState(namespaceURI, localName, rawName, preserveSpace);
            if (htmlName != null && (htmlName.equalsIgnoreCase("A") || htmlName.equalsIgnoreCase("TD"))) {
                state.empty = false;
                this._printer.printText('>');
            }
            if (htmlName == null) {
                return;
            }
            if (!rawName.equalsIgnoreCase("SCRIPT") && !rawName.equalsIgnoreCase("STYLE")) {
                return;
            }
            if (this._xhtml) {
                state.doCData = true;
            } else {
                state.unescaped = true;
            }
        } catch (IOException except) {
            throw new SAXException(except);
        }
    }

    public void endElement(String namespaceURI, String localName, String rawName) throws SAXException {
        try {
            endElementIO(namespaceURI, localName, rawName);
        } catch (IOException except) {
            throw new SAXException(except);
        }
    }

    public void endElementIO(String namespaceURI, String localName, String rawName) throws IOException {
        String htmlName;
        this._printer.unindent();
        ElementState state = getElementState();
        if (state.namespaceURI == null || state.namespaceURI.length() == 0) {
            htmlName = state.rawName;
        } else if (state.namespaceURI.equals(XHTMLNamespace) || (this.fUserXHTMLNamespace != null && this.fUserXHTMLNamespace.equals(state.namespaceURI))) {
            htmlName = state.localName;
        } else {
            htmlName = null;
        }
        if (!this._xhtml) {
            if (state.empty) {
                this._printer.printText('>');
            }
            if (htmlName == null || !HTMLdtd.isOnlyOpening(htmlName)) {
                if (this._indenting && !state.preserveSpace && state.afterElement) {
                    this._printer.breakLine();
                }
                if (state.inCData) {
                    this._printer.printText("]]>");
                }
                this._printer.printText("</");
                this._printer.printText(state.rawName);
                this._printer.printText('>');
            }
        } else if (state.empty) {
            this._printer.printText(" />");
        } else {
            if (state.inCData) {
                this._printer.printText("]]>");
            }
            this._printer.printText("</");
            this._printer.printText(state.rawName.toLowerCase(Locale.ENGLISH));
            this._printer.printText('>');
        }
        state = leaveElementState();
        if (htmlName == null || !(htmlName.equalsIgnoreCase("A") || htmlName.equalsIgnoreCase("TD"))) {
            state.afterElement = true;
        }
        state.empty = false;
        if (isDocumentState()) {
            this._printer.flush();
        }
    }

    public void characters(char[] chars, int start, int length) throws SAXException {
        try {
            content().doCData = false;
            super.characters(chars, start, length);
        } catch (IOException except) {
            throw new SAXException(except);
        }
    }

    public void startElement(String tagName, AttributeList attrs) throws SAXException {
        try {
            if (this._printer == null) {
                throw new IllegalStateException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "NoWriterSupplied", null));
            }
            ElementState state = getElementState();
            if (!isDocumentState()) {
                if (state.empty) {
                    this._printer.printText('>');
                }
                if (this._indenting && !state.preserveSpace && (state.empty || state.afterElement)) {
                    this._printer.breakLine();
                }
            } else if (!this._started) {
                startDocument(tagName);
            }
            boolean preserveSpace = state.preserveSpace;
            this._printer.printText('<');
            if (this._xhtml) {
                this._printer.printText(tagName.toLowerCase(Locale.ENGLISH));
            } else {
                this._printer.printText(tagName);
            }
            this._printer.indent();
            if (attrs != null) {
                for (int i = 0; i < attrs.getLength(); i++) {
                    this._printer.printSpace();
                    String name = attrs.getName(i).toLowerCase(Locale.ENGLISH);
                    String value = attrs.getValue(i);
                    if (!this._xhtml) {
                        if (value == null) {
                            value = "";
                        }
                        if (!this._format.getPreserveEmptyAttributes() && value.length() == 0) {
                            this._printer.printText(name);
                        } else if (HTMLdtd.isURI(tagName, name)) {
                            this._printer.printText(name);
                            this._printer.printText("=\"");
                            this._printer.printText(escapeURI(value));
                            this._printer.printText('\"');
                        } else if (HTMLdtd.isBoolean(tagName, name)) {
                            this._printer.printText(name);
                        } else {
                            this._printer.printText(name);
                            this._printer.printText("=\"");
                            printEscaped(value);
                            this._printer.printText('\"');
                        }
                    } else if (value == null) {
                        this._printer.printText(name);
                        this._printer.printText("=\"\"");
                    } else {
                        this._printer.printText(name);
                        this._printer.printText("=\"");
                        printEscaped(value);
                        this._printer.printText('\"');
                    }
                }
            }
            if (HTMLdtd.isPreserveSpace(tagName)) {
                preserveSpace = true;
            }
            state = enterElementState(null, null, tagName, preserveSpace);
            if (tagName.equalsIgnoreCase("A") || tagName.equalsIgnoreCase("TD")) {
                state.empty = false;
                this._printer.printText('>');
            }
            if (!tagName.equalsIgnoreCase("SCRIPT") && !tagName.equalsIgnoreCase("STYLE")) {
                return;
            }
            if (this._xhtml) {
                state.doCData = true;
            } else {
                state.unescaped = true;
            }
        } catch (IOException except) {
            throw new SAXException(except);
        }
    }

    public void endElement(String tagName) throws SAXException {
        endElement(null, null, tagName);
    }

    protected void startDocument(String rootTagName) throws IOException {
        this._printer.leaveDTD();
        if (!this._started) {
            if (this._docTypePublicId == null && this._docTypeSystemId == null) {
                if (this._xhtml) {
                    this._docTypePublicId = "-//W3C//DTD XHTML 1.0 Strict//EN";
                    this._docTypeSystemId = "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd";
                } else {
                    this._docTypePublicId = "-//W3C//DTD HTML 4.01//EN";
                    this._docTypeSystemId = "http://www.w3.org/TR/html4/strict.dtd";
                }
            }
            if (!this._format.getOmitDocumentType()) {
                if (this._docTypePublicId != null && (!this._xhtml || this._docTypeSystemId != null)) {
                    if (this._xhtml) {
                        this._printer.printText("<!DOCTYPE html PUBLIC ");
                    } else {
                        this._printer.printText("<!DOCTYPE HTML PUBLIC ");
                    }
                    printDoctypeURL(this._docTypePublicId);
                    if (this._docTypeSystemId != null) {
                        if (this._indenting) {
                            this._printer.breakLine();
                            this._printer.printText("                      ");
                        } else {
                            this._printer.printText(' ');
                        }
                        printDoctypeURL(this._docTypeSystemId);
                    }
                    this._printer.printText('>');
                    this._printer.breakLine();
                } else if (this._docTypeSystemId != null) {
                    if (this._xhtml) {
                        this._printer.printText("<!DOCTYPE html SYSTEM ");
                    } else {
                        this._printer.printText("<!DOCTYPE HTML SYSTEM ");
                    }
                    printDoctypeURL(this._docTypeSystemId);
                    this._printer.printText('>');
                    this._printer.breakLine();
                }
            }
        }
        this._started = true;
        serializePreRoot();
    }

    protected void serializeElement(Element elem) throws IOException {
        String tagName = elem.getTagName();
        ElementState state = getElementState();
        if (!isDocumentState()) {
            if (state.empty) {
                this._printer.printText('>');
            }
            if (this._indenting && !state.preserveSpace && (state.empty || state.afterElement)) {
                this._printer.breakLine();
            }
        } else if (!this._started) {
            startDocument(tagName);
        }
        boolean preserveSpace = state.preserveSpace;
        this._printer.printText('<');
        if (this._xhtml) {
            this._printer.printText(tagName.toLowerCase(Locale.ENGLISH));
        } else {
            this._printer.printText(tagName);
        }
        this._printer.indent();
        NamedNodeMap attrMap = elem.getAttributes();
        if (attrMap != null) {
            for (int i = 0; i < attrMap.getLength(); i++) {
                Attr attr = (Attr) attrMap.item(i);
                String name = attr.getName().toLowerCase(Locale.ENGLISH);
                String value = attr.getValue();
                if (attr.getSpecified()) {
                    this._printer.printSpace();
                    if (!this._xhtml) {
                        if (value == null) {
                            value = "";
                        }
                        if (!this._format.getPreserveEmptyAttributes() && value.length() == 0) {
                            this._printer.printText(name);
                        } else if (HTMLdtd.isURI(tagName, name)) {
                            this._printer.printText(name);
                            this._printer.printText("=\"");
                            this._printer.printText(escapeURI(value));
                            this._printer.printText('\"');
                        } else if (HTMLdtd.isBoolean(tagName, name)) {
                            this._printer.printText(name);
                        } else {
                            this._printer.printText(name);
                            this._printer.printText("=\"");
                            printEscaped(value);
                            this._printer.printText('\"');
                        }
                    } else if (value == null) {
                        this._printer.printText(name);
                        this._printer.printText("=\"\"");
                    } else {
                        this._printer.printText(name);
                        this._printer.printText("=\"");
                        printEscaped(value);
                        this._printer.printText('\"');
                    }
                }
            }
        }
        if (HTMLdtd.isPreserveSpace(tagName)) {
            preserveSpace = true;
        }
        if (elem.hasChildNodes() || !HTMLdtd.isEmptyTag(tagName)) {
            state = enterElementState(null, null, tagName, preserveSpace);
            if (tagName.equalsIgnoreCase("A") || tagName.equalsIgnoreCase("TD")) {
                state.empty = false;
                this._printer.printText('>');
            }
            if (tagName.equalsIgnoreCase("SCRIPT") || tagName.equalsIgnoreCase("STYLE")) {
                if (this._xhtml) {
                    state.doCData = true;
                } else {
                    state.unescaped = true;
                }
            }
            for (Node child = elem.getFirstChild(); child != null; child = child.getNextSibling()) {
                serializeNode(child);
            }
            endElementIO(null, null, tagName);
            return;
        }
        this._printer.unindent();
        if (this._xhtml) {
            this._printer.printText(" />");
        } else {
            this._printer.printText('>');
        }
        state.afterElement = true;
        state.empty = false;
        if (isDocumentState()) {
            this._printer.flush();
        }
    }

    protected void characters(String text) throws IOException {
        content();
        super.characters(text);
    }

    protected String getEntityRef(int ch) {
        return HTMLdtd.fromChar(ch);
    }

    protected String escapeURI(String uri) {
        int index = uri.indexOf("\"");
        if (index >= 0) {
            return uri.substring(0, index);
        }
        return uri;
    }
}
