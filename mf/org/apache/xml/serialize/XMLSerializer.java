package mf.org.apache.xml.serialize;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Map.Entry;
import mf.javax.xml.XMLConstants;
import mf.org.apache.xerces.dom.DOMMessageFormatter;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.util.NamespaceSupport;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.util.XMLSymbols;
import mf.org.apache.xerces.xni.NamespaceContext;
import mf.org.w3c.dom.Attr;
import mf.org.w3c.dom.Element;
import mf.org.w3c.dom.NamedNodeMap;
import mf.org.w3c.dom.Node;
import org.xml.sax.AttributeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class XMLSerializer extends BaseMarkupSerializer {
    protected static final boolean DEBUG = false;
    protected static final String PREFIX = "NS";
    protected NamespaceSupport fLocalNSBinder;
    protected NamespaceSupport fNSBinder;
    protected boolean fNamespacePrefixes;
    protected boolean fNamespaces;
    private boolean fPreserveSpace;
    protected SymbolTable fSymbolTable;

    public XMLSerializer() {
        super(new OutputFormat("xml", null, false));
        this.fNamespaces = false;
        this.fNamespacePrefixes = true;
    }

    public XMLSerializer(OutputFormat format) {
        if (format == null) {
            format = new OutputFormat("xml", null, false);
        }
        super(format);
        this.fNamespaces = false;
        this.fNamespacePrefixes = true;
        this._format.setMethod("xml");
    }

    public XMLSerializer(Writer writer, OutputFormat format) {
        if (format == null) {
            format = new OutputFormat("xml", null, false);
        }
        super(format);
        this.fNamespaces = false;
        this.fNamespacePrefixes = true;
        this._format.setMethod("xml");
        setOutputCharStream(writer);
    }

    public XMLSerializer(OutputStream output, OutputFormat format) {
        if (format == null) {
            format = new OutputFormat("xml", null, false);
        }
        super(format);
        this.fNamespaces = false;
        this.fNamespacePrefixes = true;
        this._format.setMethod("xml");
        setOutputByteStream(output);
    }

    public void setOutputFormat(OutputFormat format) {
        if (format == null) {
            format = new OutputFormat("xml", null, false);
        }
        super.setOutputFormat(format);
    }

    public void setNamespaces(boolean namespaces) {
        this.fNamespaces = namespaces;
        if (this.fNSBinder == null) {
            this.fNSBinder = new NamespaceSupport();
            this.fLocalNSBinder = new NamespaceSupport();
            this.fSymbolTable = new SymbolTable();
        }
    }

    public void startElement(String namespaceURI, String localName, String rawName, Attributes attrs) throws SAXException {
        try {
            if (this._printer == null) {
                throw new IllegalStateException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "NoWriterSupplied", null));
            }
            String prefix;
            String name;
            String value;
            ElementState state = getElementState();
            if (!isDocumentState()) {
                if (state.empty) {
                    this._printer.printText('>');
                }
                if (state.inCData) {
                    this._printer.printText("]]>");
                    state.inCData = false;
                }
                if (this._indenting && !state.preserveSpace && (state.empty || state.afterElement || state.afterComment)) {
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
            attrs = extractNamespaces(attrs);
            if (rawName == null || rawName.length() == 0) {
                if (localName == null) {
                    throw new SAXException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "NoName", null));
                }
                if (namespaceURI != null) {
                    if (!namespaceURI.equals("")) {
                        prefix = getPrefix(namespaceURI);
                        if (prefix == null || prefix.length() <= 0) {
                            rawName = localName;
                        } else {
                            rawName = new StringBuilder(String.valueOf(prefix)).append(":").append(localName).toString();
                        }
                    }
                }
                rawName = localName;
            }
            this._printer.printText('<');
            this._printer.printText(rawName);
            this._printer.indent();
            if (attrs != null) {
                for (int i = 0; i < attrs.getLength(); i++) {
                    this._printer.printSpace();
                    name = attrs.getQName(i);
                    if (name != null && name.length() == 0) {
                        name = attrs.getLocalName(i);
                        String attrURI = attrs.getURI(i);
                        if (!(attrURI == null || attrURI.length() == 0 || (namespaceURI != null && namespaceURI.length() != 0 && attrURI.equals(namespaceURI)))) {
                            prefix = getPrefix(attrURI);
                            if (prefix != null && prefix.length() > 0) {
                                name = new StringBuilder(String.valueOf(prefix)).append(":").append(name).toString();
                            }
                        }
                    }
                    value = attrs.getValue(i);
                    if (value == null) {
                        value = "";
                    }
                    this._printer.printText(name);
                    this._printer.printText("=\"");
                    printEscaped(value);
                    this._printer.printText('\"');
                    if (name.equals("xml:space")) {
                        if (value.equals(SchemaSymbols.ATTVAL_PRESERVE)) {
                            preserveSpace = true;
                        } else {
                            preserveSpace = this._format.getPreserveSpace();
                        }
                    }
                }
            }
            if (this._prefixes != null) {
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
            name = (localName == null || localName.length() == 0) ? rawName : new StringBuilder(String.valueOf(namespaceURI)).append("^").append(localName).toString();
            state.doCData = this._format.isCDataElement(name);
            state.unescaped = this._format.isNonEscapingElement(name);
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
        this._printer.unindent();
        ElementState state = getElementState();
        if (state.empty) {
            this._printer.printText("/>");
        } else {
            if (state.inCData) {
                this._printer.printText("]]>");
            }
            if (this._indenting && !state.preserveSpace && (state.afterElement || state.afterComment)) {
                this._printer.breakLine();
            }
            this._printer.printText("</");
            this._printer.printText(state.rawName);
            this._printer.printText('>');
        }
        state = leaveElementState();
        state.afterElement = true;
        state.afterComment = false;
        state.empty = false;
        if (isDocumentState()) {
            this._printer.flush();
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
                if (state.inCData) {
                    this._printer.printText("]]>");
                    state.inCData = false;
                }
                if (this._indenting && !state.preserveSpace && (state.empty || state.afterElement || state.afterComment)) {
                    this._printer.breakLine();
                }
            } else if (!this._started) {
                startDocument(tagName);
            }
            boolean preserveSpace = state.preserveSpace;
            this._printer.printText('<');
            this._printer.printText(tagName);
            this._printer.indent();
            if (attrs != null) {
                for (int i = 0; i < attrs.getLength(); i++) {
                    this._printer.printSpace();
                    String name = attrs.getName(i);
                    String value = attrs.getValue(i);
                    if (value != null) {
                        this._printer.printText(name);
                        this._printer.printText("=\"");
                        printEscaped(value);
                        this._printer.printText('\"');
                    }
                    if (name.equals("xml:space")) {
                        if (value.equals(SchemaSymbols.ATTVAL_PRESERVE)) {
                            preserveSpace = true;
                        } else {
                            preserveSpace = this._format.getPreserveSpace();
                        }
                    }
                }
            }
            state = enterElementState(null, null, tagName, preserveSpace);
            state.doCData = this._format.isCDataElement(tagName);
            state.unescaped = this._format.isNonEscapingElement(tagName);
        } catch (IOException except) {
            throw new SAXException(except);
        }
    }

    public void endElement(String tagName) throws SAXException {
        endElement(null, null, tagName);
    }

    protected void startDocument(String rootTagName) throws IOException {
        String dtd = this._printer.leaveDTD();
        if (!this._started) {
            if (!this._format.getOmitXMLDeclaration()) {
                StringBuffer buffer = new StringBuffer("<?xml version=\"");
                if (this._format.getVersion() != null) {
                    buffer.append(this._format.getVersion());
                } else {
                    buffer.append("1.0");
                }
                buffer.append('\"');
                String format_encoding = this._format.getEncoding();
                if (format_encoding != null) {
                    buffer.append(" encoding=\"");
                    buffer.append(format_encoding);
                    buffer.append('\"');
                }
                if (this._format.getStandalone() && this._docTypeSystemId == null && this._docTypePublicId == null) {
                    buffer.append(" standalone=\"yes\"");
                }
                buffer.append("?>");
                this._printer.printText(buffer);
                this._printer.breakLine();
            }
            if (!this._format.getOmitDocumentType()) {
                if (this._docTypeSystemId != null) {
                    this._printer.printText("<!DOCTYPE ");
                    this._printer.printText(rootTagName);
                    if (this._docTypePublicId != null) {
                        this._printer.printText(" PUBLIC ");
                        printDoctypeURL(this._docTypePublicId);
                        if (this._indenting) {
                            this._printer.breakLine();
                            for (int i = 0; i < rootTagName.length() + 18; i++) {
                                this._printer.printText(" ");
                            }
                        } else {
                            this._printer.printText(" ");
                        }
                        printDoctypeURL(this._docTypeSystemId);
                    } else {
                        this._printer.printText(" SYSTEM ");
                        printDoctypeURL(this._docTypeSystemId);
                    }
                    if (dtd != null && dtd.length() > 0) {
                        this._printer.printText(" [");
                        printText(dtd, true, true);
                        this._printer.printText(']');
                    }
                    this._printer.printText(">");
                    this._printer.breakLine();
                } else if (dtd != null && dtd.length() > 0) {
                    this._printer.printText("<!DOCTYPE ");
                    this._printer.printText(rootTagName);
                    this._printer.printText(" [");
                    printText(dtd, true, true);
                    this._printer.printText("]>");
                    this._printer.breakLine();
                }
            }
        }
        this._started = true;
        serializePreRoot();
    }

    protected void serializeElement(Element elem) throws IOException {
        if (this.fNamespaces) {
            this.fLocalNSBinder.reset();
            this.fNSBinder.pushContext();
        }
        String tagName = elem.getTagName();
        ElementState state = getElementState();
        if (!isDocumentState()) {
            if (state.empty) {
                this._printer.printText('>');
            }
            if (state.inCData) {
                this._printer.printText("]]>");
                state.inCData = false;
            }
            if (this._indenting && !state.preserveSpace && (state.empty || state.afterElement || state.afterComment)) {
                this._printer.breakLine();
            }
        } else if (!this._started) {
            startDocument(tagName);
        }
        this.fPreserveSpace = state.preserveSpace;
        int length = 0;
        NamedNodeMap attrMap = null;
        if (elem.hasAttributes()) {
            attrMap = elem.getAttributes();
            length = attrMap.getLength();
        }
        int i;
        Attr attr;
        String value;
        String name;
        if (this.fNamespaces) {
            String uri;
            String prefix;
            String localpart;
            for (i = 0; i < length; i++) {
                attr = (Attr) attrMap.item(i);
                uri = attr.getNamespaceURI();
                if (uri != null && uri.equals(NamespaceContext.XMLNS_URI)) {
                    value = attr.getNodeValue();
                    if (value == null) {
                        value = XMLSymbols.EMPTY_STRING;
                    }
                    if (!value.equals(NamespaceContext.XMLNS_URI)) {
                        prefix = attr.getPrefix();
                        if (prefix == null || prefix.length() == 0) {
                            prefix = XMLSymbols.EMPTY_STRING;
                        } else {
                            prefix = this.fSymbolTable.addSymbol(prefix);
                        }
                        localpart = this.fSymbolTable.addSymbol(attr.getLocalName());
                        if (prefix == XMLSymbols.PREFIX_XMLNS) {
                            value = this.fSymbolTable.addSymbol(value);
                            if (value.length() != 0) {
                                this.fNSBinder.declarePrefix(localpart, value);
                            }
                        } else {
                            value = this.fSymbolTable.addSymbol(value);
                            this.fNSBinder.declarePrefix(XMLSymbols.EMPTY_STRING, value);
                        }
                    } else if (this.fDOMErrorHandler != null) {
                        modifyDOMError(DOMMessageFormatter.formatMessage("http://www.w3.org/TR/1998/REC-xml-19980210", "CantBindXMLNS", null), (short) 2, null, attr);
                        if (!this.fDOMErrorHandler.handleError(this.fDOMError)) {
                            throw new RuntimeException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "SerializationStopped", null));
                        }
                    } else {
                        continue;
                    }
                }
            }
            uri = elem.getNamespaceURI();
            prefix = elem.getPrefix();
            if (uri == null || prefix == null || uri.length() != 0 || prefix.length() == 0) {
                this._printer.printText('<');
                this._printer.printText(tagName);
                this._printer.indent();
            } else {
                prefix = null;
                this._printer.printText('<');
                this._printer.printText(elem.getLocalName());
                this._printer.indent();
            }
            if (uri != null) {
                uri = this.fSymbolTable.addSymbol(uri);
                if (prefix == null || prefix.length() == 0) {
                    prefix = XMLSymbols.EMPTY_STRING;
                } else {
                    prefix = this.fSymbolTable.addSymbol(prefix);
                }
                if (this.fNSBinder.getURI(prefix) != uri) {
                    if (this.fNamespacePrefixes) {
                        printNamespaceAttr(prefix, uri);
                    }
                    this.fLocalNSBinder.declarePrefix(prefix, uri);
                    this.fNSBinder.declarePrefix(prefix, uri);
                }
            } else if (elem.getLocalName() != null) {
                uri = this.fNSBinder.getURI(XMLSymbols.EMPTY_STRING);
                if (uri != null && uri.length() > 0) {
                    if (this.fNamespacePrefixes) {
                        printNamespaceAttr(XMLSymbols.EMPTY_STRING, XMLSymbols.EMPTY_STRING);
                    }
                    this.fLocalNSBinder.declarePrefix(XMLSymbols.EMPTY_STRING, XMLSymbols.EMPTY_STRING);
                    this.fNSBinder.declarePrefix(XMLSymbols.EMPTY_STRING, XMLSymbols.EMPTY_STRING);
                }
            } else if (this.fDOMErrorHandler != null) {
                modifyDOMError(DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NullLocalElementName", new Object[]{elem.getNodeName()}), (short) 2, null, elem);
                if (!this.fDOMErrorHandler.handleError(this.fDOMError)) {
                    throw new RuntimeException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "SerializationStopped", null));
                }
            }
            for (i = 0; i < length; i++) {
                attr = (Attr) attrMap.item(i);
                value = attr.getValue();
                name = attr.getNodeName();
                uri = attr.getNamespaceURI();
                if (uri != null && uri.length() == 0) {
                    uri = null;
                    name = attr.getLocalName();
                }
                if (value == null) {
                    value = XMLSymbols.EMPTY_STRING;
                }
                if (uri != null) {
                    prefix = attr.getPrefix();
                    if (prefix == null) {
                        prefix = XMLSymbols.EMPTY_STRING;
                    } else {
                        prefix = this.fSymbolTable.addSymbol(prefix);
                    }
                    localpart = this.fSymbolTable.addSymbol(attr.getLocalName());
                    if (uri == null || !uri.equals(NamespaceContext.XMLNS_URI)) {
                        String str;
                        uri = this.fSymbolTable.addSymbol(uri);
                        String declaredURI = this.fNSBinder.getURI(prefix);
                        if (prefix == XMLSymbols.EMPTY_STRING || declaredURI != uri) {
                            name = attr.getNodeName();
                            String declaredPrefix = this.fNSBinder.getPrefix(uri);
                            if (declaredPrefix == null || declaredPrefix == XMLSymbols.EMPTY_STRING) {
                                if (prefix == XMLSymbols.EMPTY_STRING || this.fLocalNSBinder.getURI(prefix) != null) {
                                    int counter = 1 + 1;
                                    prefix = this.fSymbolTable.addSymbol(new StringBuilder(PREFIX).append(1).toString());
                                    int counter2 = counter;
                                    while (this.fLocalNSBinder.getURI(prefix) != null) {
                                        counter = counter2 + 1;
                                        prefix = this.fSymbolTable.addSymbol(new StringBuilder(PREFIX).append(counter2).toString());
                                        counter2 = counter;
                                    }
                                    name = new StringBuilder(String.valueOf(prefix)).append(":").append(localpart).toString();
                                }
                                if (this.fNamespacePrefixes) {
                                    printNamespaceAttr(prefix, uri);
                                }
                                value = this.fSymbolTable.addSymbol(value);
                                this.fLocalNSBinder.declarePrefix(prefix, value);
                                this.fNSBinder.declarePrefix(prefix, uri);
                            } else {
                                name = new StringBuilder(String.valueOf(declaredPrefix)).append(":").append(localpart).toString();
                            }
                        }
                        if (value == null) {
                            str = XMLSymbols.EMPTY_STRING;
                        } else {
                            str = value;
                        }
                        printAttribute(name, str, attr.getSpecified(), attr);
                    } else {
                        prefix = attr.getPrefix();
                        if (prefix == null || prefix.length() == 0) {
                            prefix = XMLSymbols.EMPTY_STRING;
                        } else {
                            prefix = this.fSymbolTable.addSymbol(prefix);
                        }
                        localpart = this.fSymbolTable.addSymbol(attr.getLocalName());
                        String localUri;
                        if (prefix == XMLSymbols.PREFIX_XMLNS) {
                            localUri = this.fLocalNSBinder.getURI(localpart);
                            value = this.fSymbolTable.addSymbol(value);
                            if (value.length() != 0 && localUri == null) {
                                if (this.fNamespacePrefixes) {
                                    printNamespaceAttr(localpart, value);
                                }
                                this.fLocalNSBinder.declarePrefix(localpart, value);
                            }
                        } else {
                            uri = this.fNSBinder.getURI(XMLSymbols.EMPTY_STRING);
                            localUri = this.fLocalNSBinder.getURI(XMLSymbols.EMPTY_STRING);
                            value = this.fSymbolTable.addSymbol(value);
                            if (localUri == null && this.fNamespacePrefixes) {
                                printNamespaceAttr(XMLSymbols.EMPTY_STRING, value);
                            }
                        }
                    }
                } else if (attr.getLocalName() == null) {
                    if (this.fDOMErrorHandler != null) {
                        modifyDOMError(DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NullLocalAttrName", new Object[]{attr.getNodeName()}), (short) 2, null, attr);
                        if (!this.fDOMErrorHandler.handleError(this.fDOMError)) {
                            throw new RuntimeException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "SerializationStopped", null));
                        }
                    }
                    printAttribute(name, value, attr.getSpecified(), attr);
                } else {
                    printAttribute(name, value, attr.getSpecified(), attr);
                }
            }
        } else {
            this._printer.printText('<');
            this._printer.printText(tagName);
            this._printer.indent();
            for (i = 0; i < length; i++) {
                attr = (Attr) attrMap.item(i);
                name = attr.getName();
                value = attr.getValue();
                if (value == null) {
                    value = "";
                }
                printAttribute(name, value, attr.getSpecified(), attr);
            }
        }
        if (elem.hasChildNodes()) {
            state = enterElementState(null, null, tagName, this.fPreserveSpace);
            state.doCData = this._format.isCDataElement(tagName);
            state.unescaped = this._format.isNonEscapingElement(tagName);
            for (Node child = elem.getFirstChild(); child != null; child = child.getNextSibling()) {
                serializeNode(child);
            }
            if (this.fNamespaces) {
                this.fNSBinder.popContext();
            }
            endElementIO(null, null, tagName);
            return;
        }
        if (this.fNamespaces) {
            this.fNSBinder.popContext();
        }
        this._printer.unindent();
        this._printer.printText("/>");
        state.afterElement = true;
        state.afterComment = false;
        state.empty = false;
        if (isDocumentState()) {
            this._printer.flush();
        }
    }

    private void printNamespaceAttr(String prefix, String uri) throws IOException {
        this._printer.printSpace();
        if (prefix == XMLSymbols.EMPTY_STRING) {
            this._printer.printText(XMLSymbols.PREFIX_XMLNS);
        } else {
            this._printer.printText("xmlns:" + prefix);
        }
        this._printer.printText("=\"");
        printEscaped(uri);
        this._printer.printText('\"');
    }

    private void printAttribute(String name, String value, boolean isSpecified, Attr attr) throws IOException {
        if (isSpecified || (this.features & 64) == 0) {
            if (!(this.fDOMFilter == null || (this.fDOMFilter.getWhatToShow() & 2) == 0)) {
                switch (this.fDOMFilter.acceptNode(attr)) {
                    case (short) 2:
                    case (short) 3:
                        return;
                }
            }
            this._printer.printSpace();
            this._printer.printText(name);
            this._printer.printText("=\"");
            printEscaped(value);
            this._printer.printText('\"');
        }
        if (!name.equals("xml:space")) {
            return;
        }
        if (value.equals(SchemaSymbols.ATTVAL_PRESERVE)) {
            this.fPreserveSpace = true;
        } else {
            this.fPreserveSpace = this._format.getPreserveSpace();
        }
    }

    protected String getEntityRef(int ch) {
        switch (ch) {
            case 34:
                return "quot";
            case 38:
                return "amp";
            case 39:
                return "apos";
            case 60:
                return "lt";
            case 62:
                return "gt";
            default:
                return null;
        }
    }

    private Attributes extractNamespaces(Attributes attrs) throws SAXException {
        if (attrs == null) {
            return null;
        }
        int length = attrs.getLength();
        Attributes attrsOnly = new AttributesImpl(attrs);
        for (int i = length - 1; i >= 0; i--) {
            String rawName = attrsOnly.getQName(i);
            if (rawName.startsWith(XMLConstants.XMLNS_ATTRIBUTE)) {
                if (rawName.length() == 5) {
                    startPrefixMapping("", attrs.getValue(i));
                    attrsOnly.removeAttribute(i);
                } else if (rawName.charAt(5) == ':') {
                    startPrefixMapping(rawName.substring(6), attrs.getValue(i));
                    attrsOnly.removeAttribute(i);
                }
            }
        }
        return attrsOnly;
    }

    protected void printEscaped(String source) throws IOException {
        int length = source.length();
        int i = 0;
        while (i < length) {
            int ch = source.charAt(i);
            if (!XMLChar.isValid(ch)) {
                i++;
                if (i < length) {
                    surrogates(ch, source.charAt(i), false);
                } else {
                    fatalError("The character '" + ((char) ch) + "' is an invalid XML character");
                }
            } else if (ch == 10 || ch == 13 || ch == 9) {
                printHex(ch);
            } else if (ch == 60) {
                this._printer.printText("&lt;");
            } else if (ch == 38) {
                this._printer.printText("&amp;");
            } else if (ch == 34) {
                this._printer.printText("&quot;");
            } else if (ch < 32 || !this._encodingInfo.isPrintable((char) ch)) {
                printHex(ch);
            } else {
                this._printer.printText((char) ch);
            }
            i++;
        }
    }

    protected void printXMLChar(int ch) throws IOException {
        if (ch == 13) {
            printHex(ch);
        } else if (ch == 60) {
            this._printer.printText("&lt;");
        } else if (ch == 38) {
            this._printer.printText("&amp;");
        } else if (ch == 62) {
            this._printer.printText("&gt;");
        } else if (ch == 10 || ch == 9 || (ch >= 32 && this._encodingInfo.isPrintable((char) ch))) {
            this._printer.printText((char) ch);
        } else {
            printHex(ch);
        }
    }

    protected void printText(String text, boolean preserveSpace, boolean unescaped) throws IOException {
        int length = text.length();
        int index;
        char ch;
        if (preserveSpace) {
            index = 0;
            while (index < length) {
                ch = text.charAt(index);
                if (!XMLChar.isValid(ch)) {
                    index++;
                    if (index < length) {
                        surrogates(ch, text.charAt(index), true);
                    } else {
                        fatalError("The character '" + ch + "' is an invalid XML character");
                    }
                } else if (unescaped) {
                    this._printer.printText(ch);
                } else {
                    printXMLChar(ch);
                }
                index++;
            }
            return;
        }
        index = 0;
        while (index < length) {
            ch = text.charAt(index);
            if (!XMLChar.isValid(ch)) {
                index++;
                if (index < length) {
                    surrogates(ch, text.charAt(index), true);
                } else {
                    fatalError("The character '" + ch + "' is an invalid XML character");
                }
            } else if (unescaped) {
                this._printer.printText(ch);
            } else {
                printXMLChar(ch);
            }
            index++;
        }
    }

    protected void printText(char[] chars, int start, int length, boolean preserveSpace, boolean unescaped) throws IOException {
        int length2;
        int start2;
        char ch;
        if (preserveSpace) {
            length2 = length;
            start2 = start;
            while (true) {
                length = length2 - 1;
                if (length2 <= 0) {
                    start = start2;
                    return;
                }
                start = start2 + 1;
                ch = chars[start2];
                if (!XMLChar.isValid(ch)) {
                    length2 = length - 1;
                    if (length > 0) {
                        start2 = start + 1;
                        surrogates(ch, chars[start], true);
                    } else {
                        fatalError("The character '" + ch + "' is an invalid XML character");
                        start2 = start;
                    }
                } else if (unescaped) {
                    this._printer.printText(ch);
                    length2 = length;
                    start2 = start;
                } else {
                    printXMLChar(ch);
                    length2 = length;
                    start2 = start;
                }
            }
        } else {
            while (true) {
                length2 = length;
                start2 = start;
                while (true) {
                    length = length2 - 1;
                    if (length2 > 0) {
                        start = start2 + 1;
                        ch = chars[start2];
                        if (XMLChar.isValid(ch)) {
                            if (!unescaped) {
                                break;
                            }
                            this._printer.printText(ch);
                            length2 = length;
                            start2 = start;
                        } else {
                            length2 = length - 1;
                            if (length > 0) {
                                start2 = start + 1;
                                surrogates(ch, chars[start], true);
                            } else {
                                fatalError("The character '" + ch + "' is an invalid XML character");
                                start2 = start;
                            }
                        }
                    } else {
                        start = start2;
                        return;
                    }
                }
                printXMLChar(ch);
            }
        }
    }

    protected void checkUnboundNamespacePrefixedNode(Node node) throws IOException {
        if (this.fNamespaces) {
            Node child = node.getFirstChild();
            while (child != null) {
                Node next = child.getNextSibling();
                String prefix = child.getPrefix();
                if (prefix == null || prefix.length() == 0) {
                    prefix = XMLSymbols.EMPTY_STRING;
                } else {
                    prefix = this.fSymbolTable.addSymbol(prefix);
                }
                if (this.fNSBinder.getURI(prefix) == null && prefix != null) {
                    fatalError("The replacement text of the entity node '" + node.getNodeName() + "' contains an element node '" + child.getNodeName() + "' with an undeclared prefix '" + prefix + "'.");
                }
                if (child.getNodeType() == (short) 1) {
                    NamedNodeMap attrs = child.getAttributes();
                    for (int i = 0; i < attrs.getLength(); i++) {
                        String attrPrefix = attrs.item(i).getPrefix();
                        attrPrefix = (attrPrefix == null || attrPrefix.length() == 0) ? XMLSymbols.EMPTY_STRING : this.fSymbolTable.addSymbol(attrPrefix);
                        if (this.fNSBinder.getURI(attrPrefix) == null && attrPrefix != null) {
                            fatalError("The replacement text of the entity node '" + node.getNodeName() + "' contains an element node '" + child.getNodeName() + "' with an attribute '" + attrs.item(i).getNodeName() + "' an undeclared prefix '" + attrPrefix + "'.");
                        }
                    }
                }
                if (child.hasChildNodes()) {
                    checkUnboundNamespacePrefixedNode(child);
                }
                child = next;
            }
        }
    }

    public boolean reset() {
        super.reset();
        if (this.fNSBinder != null) {
            this.fNSBinder.reset();
            this.fNSBinder.declarePrefix(XMLSymbols.EMPTY_STRING, XMLSymbols.EMPTY_STRING);
        }
        return true;
    }
}
