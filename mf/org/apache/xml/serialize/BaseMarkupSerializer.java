package mf.org.apache.xml.serialize;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Vector;
import mf.org.apache.xerces.dom.DOMErrorImpl;
import mf.org.apache.xerces.dom.DOMLocatorImpl;
import mf.org.apache.xerces.dom.DOMMessageFormatter;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.w3c.dom.DOMError;
import mf.org.w3c.dom.DOMErrorHandler;
import mf.org.w3c.dom.Document;
import mf.org.w3c.dom.DocumentFragment;
import mf.org.w3c.dom.DocumentType;
import mf.org.w3c.dom.Element;
import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.ls.LSException;
import mf.org.w3c.dom.ls.LSSerializerFilter;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.DocumentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.LexicalHandler;

public abstract class BaseMarkupSerializer implements ContentHandler, DocumentHandler, LexicalHandler, DTDHandler, DeclHandler, DOMSerializer, Serializer {
    protected String _docTypePublicId;
    protected String _docTypeSystemId;
    private int _elementStateCount;
    private ElementState[] _elementStates = new ElementState[10];
    protected EncodingInfo _encodingInfo;
    protected OutputFormat _format;
    protected boolean _indenting;
    private OutputStream _output;
    private Vector _preRoot;
    protected Hashtable _prefixes;
    private boolean _prepared;
    protected Printer _printer;
    protected boolean _started;
    private Writer _writer;
    protected Node fCurrentNode = null;
    protected final DOMErrorImpl fDOMError = new DOMErrorImpl();
    protected DOMErrorHandler fDOMErrorHandler;
    protected LSSerializerFilter fDOMFilter;
    protected final StringBuffer fStrBuffer = new StringBuffer(40);
    protected short features = (short) -1;

    protected abstract String getEntityRef(int i);

    protected abstract void serializeElement(Element element) throws IOException;

    protected BaseMarkupSerializer(OutputFormat format) {
        for (int i = 0; i < this._elementStates.length; i++) {
            this._elementStates[i] = new ElementState();
        }
        this._format = format;
    }

    public DocumentHandler asDocumentHandler() throws IOException {
        prepare();
        return this;
    }

    public ContentHandler asContentHandler() throws IOException {
        prepare();
        return this;
    }

    public DOMSerializer asDOMSerializer() throws IOException {
        prepare();
        return this;
    }

    public void setOutputByteStream(OutputStream output) {
        if (output == null) {
            throw new NullPointerException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "ArgumentIsNull", new Object[]{"output"}));
        }
        this._output = output;
        this._writer = null;
        reset();
    }

    public void setOutputCharStream(Writer writer) {
        if (writer == null) {
            throw new NullPointerException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "ArgumentIsNull", new Object[]{"writer"}));
        }
        this._writer = writer;
        this._output = null;
        reset();
    }

    public void setOutputFormat(OutputFormat format) {
        if (format == null) {
            throw new NullPointerException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "ArgumentIsNull", new Object[]{"format"}));
        }
        this._format = format;
        reset();
    }

    public boolean reset() {
        if (this._elementStateCount > 1) {
            throw new IllegalStateException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "ResetInMiddle", null));
        }
        this._prepared = false;
        this.fCurrentNode = null;
        this.fStrBuffer.setLength(0);
        return true;
    }

    protected void cleanup() {
        this.fCurrentNode = null;
    }

    protected void prepare() throws IOException {
        if (!this._prepared) {
            if (this._writer == null && this._output == null) {
                throw new IOException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "NoWriterSupplied", null));
            }
            this._encodingInfo = this._format.getEncodingInfo();
            if (this._output != null) {
                this._writer = this._encodingInfo.getWriter(this._output);
            }
            if (this._format.getIndenting()) {
                this._indenting = true;
                this._printer = new IndentPrinter(this._writer, this._format);
            } else {
                this._indenting = false;
                this._printer = new Printer(this._writer, this._format);
            }
            this._elementStateCount = 0;
            ElementState state = this._elementStates[0];
            state.namespaceURI = null;
            state.localName = null;
            state.rawName = null;
            state.preserveSpace = this._format.getPreserveSpace();
            state.empty = true;
            state.afterElement = false;
            state.afterComment = false;
            state.inCData = false;
            state.doCData = false;
            state.prefixes = null;
            this._docTypePublicId = this._format.getDoctypePublic();
            this._docTypeSystemId = this._format.getDoctypeSystem();
            this._started = false;
            this._prepared = true;
        }
    }

    public void serialize(Element elem) throws IOException {
        reset();
        prepare();
        serializeNode(elem);
        cleanup();
        this._printer.flush();
        if (this._printer.getException() != null) {
            throw this._printer.getException();
        }
    }

    public void serialize(DocumentFragment frag) throws IOException {
        reset();
        prepare();
        serializeNode(frag);
        cleanup();
        this._printer.flush();
        if (this._printer.getException() != null) {
            throw this._printer.getException();
        }
    }

    public void serialize(Document doc) throws IOException {
        reset();
        prepare();
        serializeNode(doc);
        serializePreRoot();
        cleanup();
        this._printer.flush();
        if (this._printer.getException() != null) {
            throw this._printer.getException();
        }
    }

    public void startDocument() throws SAXException {
        try {
            prepare();
        } catch (IOException except) {
            throw new SAXException(except.toString());
        }
    }

    public void characters(char[] chars, int start, int length) throws SAXException {
        try {
            ElementState state = content();
            int saveIndent;
            if (state.inCData || state.doCData) {
                if (!state.inCData) {
                    this._printer.printText("<![CDATA[");
                    state.inCData = true;
                }
                saveIndent = this._printer.getNextIndent();
                this._printer.setNextIndent(0);
                int end = start + length;
                int index = start;
                while (index < end) {
                    char ch = chars[index];
                    if (ch == ']' && index + 2 < end && chars[index + 1] == ']' && chars[index + 2] == '>') {
                        this._printer.printText("]]]]><![CDATA[>");
                        index += 2;
                    } else if (!XMLChar.isValid(ch)) {
                        index++;
                        if (index < end) {
                            surrogates(ch, chars[index], true);
                        } else {
                            fatalError("The character '" + ch + "' is an invalid XML character");
                        }
                    } else if ((ch >= ' ' && this._encodingInfo.isPrintable(ch) && ch != '') || ch == '\n' || ch == '\r' || ch == '\t') {
                        this._printer.printText(ch);
                    } else {
                        this._printer.printText("]]>&#x");
                        this._printer.printText(Integer.toHexString(ch));
                        this._printer.printText(";<![CDATA[");
                    }
                    index++;
                }
                this._printer.setNextIndent(saveIndent);
            } else if (state.preserveSpace) {
                saveIndent = this._printer.getNextIndent();
                this._printer.setNextIndent(0);
                printText(chars, start, length, true, state.unescaped);
                this._printer.setNextIndent(saveIndent);
            } else {
                printText(chars, start, length, false, state.unescaped);
            }
        } catch (IOException except) {
            throw new SAXException(except);
        }
    }

    public void ignorableWhitespace(char[] chars, int start, int length) throws SAXException {
        try {
            content();
            if (this._indenting) {
                this._printer.setThisIndent(0);
                int i = start;
                int length2 = length;
                while (true) {
                    length = length2 - 1;
                    if (length2 > 0) {
                        this._printer.printText(chars[i]);
                        i++;
                        length2 = length;
                    } else {
                        return;
                    }
                }
            }
        } catch (IOException except) {
            throw new SAXException(except);
        }
    }

    public final void processingInstruction(String target, String code) throws SAXException {
        try {
            processingInstructionIO(target, code);
        } catch (IOException except) {
            throw new SAXException(except);
        }
    }

    public void processingInstructionIO(String target, String code) throws IOException {
        ElementState state = content();
        int index = target.indexOf("?>");
        if (index >= 0) {
            this.fStrBuffer.append("<?").append(target.substring(0, index));
        } else {
            this.fStrBuffer.append("<?").append(target);
        }
        if (code != null) {
            this.fStrBuffer.append(' ');
            index = code.indexOf("?>");
            if (index >= 0) {
                this.fStrBuffer.append(code.substring(0, index));
            } else {
                this.fStrBuffer.append(code);
            }
        }
        this.fStrBuffer.append("?>");
        if (isDocumentState()) {
            if (this._preRoot == null) {
                this._preRoot = new Vector();
            }
            this._preRoot.addElement(this.fStrBuffer.toString());
        } else {
            this._printer.indent();
            printText(this.fStrBuffer.toString(), true, true);
            this._printer.unindent();
            if (this._indenting) {
                state.afterElement = true;
            }
        }
        this.fStrBuffer.setLength(0);
    }

    public void comment(char[] chars, int start, int length) throws SAXException {
        try {
            comment(new String(chars, start, length));
        } catch (IOException except) {
            throw new SAXException(except);
        }
    }

    public void comment(String text) throws IOException {
        if (!this._format.getOmitComments()) {
            ElementState state = content();
            int index = text.indexOf("-->");
            if (index >= 0) {
                this.fStrBuffer.append("<!--").append(text.substring(0, index)).append("-->");
            } else {
                this.fStrBuffer.append("<!--").append(text).append("-->");
            }
            if (isDocumentState()) {
                if (this._preRoot == null) {
                    this._preRoot = new Vector();
                }
                this._preRoot.addElement(this.fStrBuffer.toString());
            } else {
                if (this._indenting && !state.preserveSpace) {
                    this._printer.breakLine();
                }
                this._printer.indent();
                printText(this.fStrBuffer.toString(), true, true);
                this._printer.unindent();
                if (this._indenting) {
                    state.afterElement = true;
                }
            }
            this.fStrBuffer.setLength(0);
            state.afterComment = true;
            state.afterElement = false;
        }
    }

    public void startCDATA() {
        getElementState().doCData = true;
    }

    public void endCDATA() {
        getElementState().doCData = false;
    }

    public void startNonEscaping() {
        getElementState().unescaped = true;
    }

    public void endNonEscaping() {
        getElementState().unescaped = false;
    }

    public void startPreserving() {
        getElementState().preserveSpace = true;
    }

    public void endPreserving() {
        getElementState().preserveSpace = false;
    }

    public void endDocument() throws SAXException {
        try {
            serializePreRoot();
            this._printer.flush();
        } catch (IOException except) {
            throw new SAXException(except);
        }
    }

    public void startEntity(String name) {
    }

    public void endEntity(String name) {
    }

    public void setDocumentLocator(Locator locator) {
    }

    public void skippedEntity(String name) throws SAXException {
        try {
            endCDATA();
            content();
            this._printer.printText('&');
            this._printer.printText(name);
            this._printer.printText(';');
        } catch (IOException except) {
            throw new SAXException(except);
        }
    }

    public void startPrefixMapping(String prefix, String uri) throws SAXException {
        if (this._prefixes == null) {
            this._prefixes = new Hashtable();
        }
        Hashtable hashtable = this._prefixes;
        if (prefix == null) {
            prefix = "";
        }
        hashtable.put(uri, prefix);
    }

    public void endPrefixMapping(String prefix) throws SAXException {
    }

    public final void startDTD(String name, String publicId, String systemId) throws SAXException {
        try {
            this._printer.enterDTD();
            this._docTypePublicId = publicId;
            this._docTypeSystemId = systemId;
        } catch (IOException except) {
            throw new SAXException(except);
        }
    }

    public void endDTD() {
    }

    public void elementDecl(String name, String model) throws SAXException {
        try {
            this._printer.enterDTD();
            this._printer.printText("<!ELEMENT ");
            this._printer.printText(name);
            this._printer.printText(' ');
            this._printer.printText(model);
            this._printer.printText('>');
            if (this._indenting) {
                this._printer.breakLine();
            }
        } catch (IOException except) {
            throw new SAXException(except);
        }
    }

    public void attributeDecl(String eName, String aName, String type, String valueDefault, String value) throws SAXException {
        try {
            this._printer.enterDTD();
            this._printer.printText("<!ATTLIST ");
            this._printer.printText(eName);
            this._printer.printText(' ');
            this._printer.printText(aName);
            this._printer.printText(' ');
            this._printer.printText(type);
            if (valueDefault != null) {
                this._printer.printText(' ');
                this._printer.printText(valueDefault);
            }
            if (value != null) {
                this._printer.printText(" \"");
                printEscaped(value);
                this._printer.printText('\"');
            }
            this._printer.printText('>');
            if (this._indenting) {
                this._printer.breakLine();
            }
        } catch (IOException except) {
            throw new SAXException(except);
        }
    }

    public void internalEntityDecl(String name, String value) throws SAXException {
        try {
            this._printer.enterDTD();
            this._printer.printText("<!ENTITY ");
            this._printer.printText(name);
            this._printer.printText(" \"");
            printEscaped(value);
            this._printer.printText("\">");
            if (this._indenting) {
                this._printer.breakLine();
            }
        } catch (IOException except) {
            throw new SAXException(except);
        }
    }

    public void externalEntityDecl(String name, String publicId, String systemId) throws SAXException {
        try {
            this._printer.enterDTD();
            unparsedEntityDecl(name, publicId, systemId, null);
        } catch (IOException except) {
            throw new SAXException(except);
        }
    }

    public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) throws SAXException {
        try {
            this._printer.enterDTD();
            if (publicId == null) {
                this._printer.printText("<!ENTITY ");
                this._printer.printText(name);
                this._printer.printText(" SYSTEM ");
                printDoctypeURL(systemId);
            } else {
                this._printer.printText("<!ENTITY ");
                this._printer.printText(name);
                this._printer.printText(" PUBLIC ");
                printDoctypeURL(publicId);
                this._printer.printText(' ');
                printDoctypeURL(systemId);
            }
            if (notationName != null) {
                this._printer.printText(" NDATA ");
                this._printer.printText(notationName);
            }
            this._printer.printText('>');
            if (this._indenting) {
                this._printer.breakLine();
            }
        } catch (IOException except) {
            throw new SAXException(except);
        }
    }

    public void notationDecl(String name, String publicId, String systemId) throws SAXException {
        try {
            this._printer.enterDTD();
            if (publicId != null) {
                this._printer.printText("<!NOTATION ");
                this._printer.printText(name);
                this._printer.printText(" PUBLIC ");
                printDoctypeURL(publicId);
                if (systemId != null) {
                    this._printer.printText(' ');
                    printDoctypeURL(systemId);
                }
            } else {
                this._printer.printText("<!NOTATION ");
                this._printer.printText(name);
                this._printer.printText(" SYSTEM ");
                printDoctypeURL(systemId);
            }
            this._printer.printText('>');
            if (this._indenting) {
                this._printer.breakLine();
            }
        } catch (IOException except) {
            throw new SAXException(except);
        }
    }

    protected void serializeNode(Node node) throws IOException {
        Node child;
        this.fCurrentNode = node;
        String text;
        switch (node.getNodeType()) {
            case (short) 1:
                if (!(this.fDOMFilter == null || (this.fDOMFilter.getWhatToShow() & 1) == 0)) {
                    switch (this.fDOMFilter.acceptNode(node)) {
                        case (short) 2:
                            return;
                        case (short) 3:
                            for (child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
                                serializeNode(child);
                            }
                            return;
                    }
                }
                serializeElement((Element) node);
                return;
            case (short) 3:
                text = node.getNodeValue();
                if (text == null) {
                    return;
                }
                if (this.fDOMFilter != null && (this.fDOMFilter.getWhatToShow() & 4) != 0) {
                    switch (this.fDOMFilter.acceptNode(node)) {
                        case (short) 2:
                        case (short) 3:
                            return;
                        default:
                            characters(text);
                            return;
                    }
                } else if (!this._indenting || getElementState().preserveSpace || text.replace('\n', ' ').trim().length() != 0) {
                    characters(text);
                    return;
                } else {
                    return;
                }
            case (short) 4:
                text = node.getNodeValue();
                if ((this.features & 8) == 0) {
                    characters(text);
                    return;
                } else if (text != null) {
                    if (!(this.fDOMFilter == null || (this.fDOMFilter.getWhatToShow() & 8) == 0)) {
                        switch (this.fDOMFilter.acceptNode(node)) {
                            case (short) 2:
                            case (short) 3:
                                return;
                        }
                    }
                    startCDATA();
                    characters(text);
                    endCDATA();
                    return;
                } else {
                    return;
                }
            case (short) 5:
                endCDATA();
                content();
                if ((this.features & 4) != 0 || node.getFirstChild() == null) {
                    if (!(this.fDOMFilter == null || (this.fDOMFilter.getWhatToShow() & 16) == 0)) {
                        switch (this.fDOMFilter.acceptNode(node)) {
                            case (short) 2:
                                return;
                            case (short) 3:
                                for (child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
                                    serializeNode(child);
                                }
                                return;
                        }
                    }
                    checkUnboundNamespacePrefixedNode(node);
                    this._printer.printText("&");
                    this._printer.printText(node.getNodeName());
                    this._printer.printText(";");
                    return;
                }
                for (child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
                    serializeNode(child);
                }
                return;
            case (short) 7:
                if (!(this.fDOMFilter == null || (this.fDOMFilter.getWhatToShow() & 64) == 0)) {
                    switch (this.fDOMFilter.acceptNode(node)) {
                        case (short) 2:
                        case (short) 3:
                            return;
                    }
                }
                processingInstructionIO(node.getNodeName(), node.getNodeValue());
                return;
            case (short) 8:
                if (!this._format.getOmitComments()) {
                    text = node.getNodeValue();
                    if (text != null) {
                        if (!(this.fDOMFilter == null || (this.fDOMFilter.getWhatToShow() & 128) == 0)) {
                            switch (this.fDOMFilter.acceptNode(node)) {
                                case (short) 2:
                                case (short) 3:
                                    return;
                            }
                        }
                        comment(text);
                        return;
                    }
                    return;
                }
                return;
            case (short) 9:
                DocumentType docType = ((Document) node).getDoctype();
                if (docType != null) {
                    try {
                        this._printer.enterDTD();
                        this._docTypePublicId = docType.getPublicId();
                        this._docTypeSystemId = docType.getSystemId();
                        String internal = docType.getInternalSubset();
                        if (internal != null && internal.length() > 0) {
                            this._printer.printText(internal);
                        }
                        endDTD();
                        break;
                    } catch (NoSuchMethodError e) {
                        Class docTypeClass = docType.getClass();
                        String docTypePublicId = null;
                        String docTypeSystemId = null;
                        try {
                            Method getPublicId = docTypeClass.getMethod("getPublicId", null);
                            if (getPublicId.getReturnType().equals(String.class)) {
                                docTypePublicId = (String) getPublicId.invoke(docType, null);
                            }
                        } catch (Exception e2) {
                        }
                        try {
                            Method getSystemId = docTypeClass.getMethod("getSystemId", null);
                            if (getSystemId.getReturnType().equals(String.class)) {
                                docTypeSystemId = (String) getSystemId.invoke(docType, null);
                            }
                        } catch (Exception e3) {
                        }
                        this._printer.enterDTD();
                        this._docTypePublicId = docTypePublicId;
                        this._docTypeSystemId = docTypeSystemId;
                        endDTD();
                        break;
                    }
                }
                break;
            case (short) 11:
                break;
            default:
                return;
        }
        for (child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
            serializeNode(child);
        }
    }

    protected ElementState content() throws IOException {
        ElementState state = getElementState();
        if (!isDocumentState()) {
            if (state.inCData && !state.doCData) {
                this._printer.printText("]]>");
                state.inCData = false;
            }
            if (state.empty) {
                this._printer.printText('>');
                state.empty = false;
            }
            state.afterElement = false;
            state.afterComment = false;
        }
        return state;
    }

    protected void characters(String text) throws IOException {
        ElementState state = content();
        int saveIndent;
        if (state.inCData || state.doCData) {
            if (!state.inCData) {
                this._printer.printText("<![CDATA[");
                state.inCData = true;
            }
            saveIndent = this._printer.getNextIndent();
            this._printer.setNextIndent(0);
            printCDATAText(text);
            this._printer.setNextIndent(saveIndent);
        } else if (state.preserveSpace) {
            saveIndent = this._printer.getNextIndent();
            this._printer.setNextIndent(0);
            printText(text, true, state.unescaped);
            this._printer.setNextIndent(saveIndent);
        } else {
            printText(text, false, state.unescaped);
        }
    }

    protected void serializePreRoot() throws IOException {
        if (this._preRoot != null) {
            for (int i = 0; i < this._preRoot.size(); i++) {
                printText((String) this._preRoot.elementAt(i), true, true);
                if (this._indenting) {
                    this._printer.breakLine();
                }
            }
            this._preRoot.removeAllElements();
        }
    }

    protected void printCDATAText(String text) throws IOException {
        int length = text.length();
        int index = 0;
        while (index < length) {
            char ch = text.charAt(index);
            if (ch == ']' && index + 2 < length && text.charAt(index + 1) == ']' && text.charAt(index + 2) == '>') {
                if (this.fDOMErrorHandler != null) {
                    if ((this.features & 16) == 0) {
                        String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "EndingCDATA", null);
                        if ((this.features & 2) != 0) {
                            modifyDOMError(msg, (short) 3, "wf-invalid-character", this.fCurrentNode);
                            this.fDOMErrorHandler.handleError(this.fDOMError);
                            throw new LSException((short) 82, msg);
                        }
                        modifyDOMError(msg, (short) 2, "cdata-section-not-splitted", this.fCurrentNode);
                        if (!this.fDOMErrorHandler.handleError(this.fDOMError)) {
                            throw new LSException((short) 82, msg);
                        }
                    }
                    modifyDOMError(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "SplittingCDATA", null), (short) 1, null, this.fCurrentNode);
                    this.fDOMErrorHandler.handleError(this.fDOMError);
                }
                this._printer.printText("]]]]><![CDATA[>");
                index += 2;
            } else if (!XMLChar.isValid(ch)) {
                index++;
                if (index < length) {
                    surrogates(ch, text.charAt(index), true);
                } else {
                    fatalError("The character '" + ch + "' is an invalid XML character");
                }
            } else if ((ch >= ' ' && this._encodingInfo.isPrintable(ch) && ch != '') || ch == '\n' || ch == '\r' || ch == '\t') {
                this._printer.printText(ch);
            } else {
                this._printer.printText("]]>&#x");
                this._printer.printText(Integer.toHexString(ch));
                this._printer.printText(";<![CDATA[");
            }
            index++;
        }
    }

    protected void surrogates(int high, int low, boolean inContent) throws IOException {
        if (!XMLChar.isHighSurrogate(high)) {
            fatalError("The character '" + ((char) high) + "' is an invalid XML character");
        } else if (XMLChar.isLowSurrogate(low)) {
            int supplemental = XMLChar.supplemental((char) high, (char) low);
            if (!XMLChar.isValid(supplemental)) {
                fatalError("The character '" + ((char) supplemental) + "' is an invalid XML character");
            } else if (inContent && content().inCData) {
                this._printer.printText("]]>&#x");
                this._printer.printText(Integer.toHexString(supplemental));
                this._printer.printText(";<![CDATA[");
            } else {
                printHex(supplemental);
            }
        } else {
            fatalError("The character '" + ((char) low) + "' is an invalid XML character");
        }
    }

    protected void printText(char[] chars, int start, int length, boolean preserveSpace, boolean unescaped) throws IOException {
        int length2;
        int ch;
        if (preserveSpace) {
            length2 = length;
            while (true) {
                length = length2 - 1;
                if (length2 > 0) {
                    ch = chars[start];
                    start++;
                    if (ch == '\n' || ch == '\r' || unescaped) {
                        this._printer.printText((char) ch);
                        length2 = length;
                    } else {
                        printEscaped(ch);
                        length2 = length;
                    }
                } else {
                    return;
                }
            }
        }
        while (true) {
            length2 = length;
            while (true) {
                length = length2 - 1;
                if (length2 > 0) {
                    ch = chars[start];
                    start++;
                    if (ch != ' ' && ch != '\f' && ch != '\t' && ch != '\n' && ch != '\r') {
                        if (!unescaped) {
                            break;
                        }
                        this._printer.printText((char) ch);
                        length2 = length;
                    } else {
                        this._printer.printSpace();
                        length2 = length;
                    }
                } else {
                    return;
                }
            }
            printEscaped(ch);
        }
    }

    protected void printText(String text, boolean preserveSpace, boolean unescaped) throws IOException {
        int index;
        int ch;
        if (preserveSpace) {
            for (index = 0; index < text.length(); index++) {
                ch = text.charAt(index);
                if (ch == '\n' || ch == '\r' || unescaped) {
                    this._printer.printText((char) ch);
                } else {
                    printEscaped(ch);
                }
            }
            return;
        }
        for (index = 0; index < text.length(); index++) {
            ch = text.charAt(index);
            if (ch == ' ' || ch == '\f' || ch == '\t' || ch == '\n' || ch == '\r') {
                this._printer.printSpace();
            } else if (unescaped) {
                this._printer.printText((char) ch);
            } else {
                printEscaped(ch);
            }
        }
    }

    protected void printDoctypeURL(String url) throws IOException {
        this._printer.printText('\"');
        int i = 0;
        while (i < url.length()) {
            if (url.charAt(i) == '\"' || url.charAt(i) < ' ' || url.charAt(i) > '') {
                this._printer.printText('%');
                this._printer.printText(Integer.toHexString(url.charAt(i)));
            } else {
                this._printer.printText(url.charAt(i));
            }
            i++;
        }
        this._printer.printText('\"');
    }

    protected void printEscaped(int ch) throws IOException {
        String charRef = getEntityRef(ch);
        if (charRef != null) {
            this._printer.printText('&');
            this._printer.printText(charRef);
            this._printer.printText(';');
        } else if ((ch < 32 || !this._encodingInfo.isPrintable((char) ch) || ch == 127) && ch != 10 && ch != 13 && ch != 9) {
            printHex(ch);
        } else if (ch < 65536) {
            this._printer.printText((char) ch);
        } else {
            this._printer.printText((char) (((ch - 65536) >> 10) + 55296));
            this._printer.printText((char) (((ch - 65536) & 1023) + 56320));
        }
    }

    final void printHex(int ch) throws IOException {
        this._printer.printText("&#x");
        this._printer.printText(Integer.toHexString(ch));
        this._printer.printText(';');
    }

    protected void printEscaped(String source) throws IOException {
        int i = 0;
        while (i < source.length()) {
            int ch = source.charAt(i);
            if ((ch & 64512) == 55296 && i + 1 < source.length()) {
                int lowch = source.charAt(i + 1);
                if ((lowch & 64512) == 56320) {
                    ch = ((65536 + ((ch - 55296) << 10)) + lowch) - 56320;
                    i++;
                }
            }
            printEscaped(ch);
            i++;
        }
    }

    protected ElementState getElementState() {
        return this._elementStates[this._elementStateCount];
    }

    protected ElementState enterElementState(String namespaceURI, String localName, String rawName, boolean preserveSpace) {
        if (this._elementStateCount + 1 == this._elementStates.length) {
            int i;
            ElementState[] newStates = new ElementState[(this._elementStates.length + 10)];
            for (i = 0; i < this._elementStates.length; i++) {
                newStates[i] = this._elementStates[i];
            }
            for (i = this._elementStates.length; i < newStates.length; i++) {
                newStates[i] = new ElementState();
            }
            this._elementStates = newStates;
        }
        this._elementStateCount++;
        ElementState state = this._elementStates[this._elementStateCount];
        state.namespaceURI = namespaceURI;
        state.localName = localName;
        state.rawName = rawName;
        state.preserveSpace = preserveSpace;
        state.empty = true;
        state.afterElement = false;
        state.afterComment = false;
        state.inCData = false;
        state.doCData = false;
        state.unescaped = false;
        state.prefixes = this._prefixes;
        this._prefixes = null;
        return state;
    }

    protected ElementState leaveElementState() {
        if (this._elementStateCount > 0) {
            this._prefixes = null;
            this._elementStateCount--;
            return this._elementStates[this._elementStateCount];
        }
        throw new IllegalStateException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "Internal", null));
    }

    protected boolean isDocumentState() {
        return this._elementStateCount == 0;
    }

    final void clearDocumentState() {
        this._elementStateCount = 0;
    }

    protected String getPrefix(String namespaceURI) {
        String prefix;
        if (this._prefixes != null) {
            prefix = (String) this._prefixes.get(namespaceURI);
            if (prefix != null) {
                return prefix;
            }
        }
        if (this._elementStateCount == 0) {
            return null;
        }
        for (int i = this._elementStateCount; i > 0; i--) {
            if (this._elementStates[i].prefixes != null) {
                prefix = (String) this._elementStates[i].prefixes.get(namespaceURI);
                if (prefix != null) {
                    return prefix;
                }
            }
        }
        return null;
    }

    protected DOMError modifyDOMError(String message, short severity, String type, Node node) {
        this.fDOMError.reset();
        this.fDOMError.fMessage = message;
        this.fDOMError.fType = type;
        this.fDOMError.fSeverity = severity;
        this.fDOMError.fLocator = new DOMLocatorImpl(-1, -1, -1, node, null);
        return this.fDOMError;
    }

    protected void fatalError(String message) throws IOException {
        if (this.fDOMErrorHandler != null) {
            modifyDOMError(message, (short) 3, null, this.fCurrentNode);
            this.fDOMErrorHandler.handleError(this.fDOMError);
            return;
        }
        throw new IOException(message);
    }

    protected void checkUnboundNamespacePrefixedNode(Node node) throws IOException {
    }
}
