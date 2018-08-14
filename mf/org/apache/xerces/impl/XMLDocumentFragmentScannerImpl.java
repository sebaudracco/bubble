package mf.org.apache.xerces.impl;

import java.io.EOFException;
import java.io.IOException;
import mf.org.apache.xerces.util.AugmentationsImpl;
import mf.org.apache.xerces.util.XMLAttributesImpl;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.util.XMLStringBuffer;
import mf.org.apache.xerces.util.XMLSymbols;
import mf.org.apache.xerces.xni.Augmentations;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xni.XMLAttributes;
import mf.org.apache.xerces.xni.XMLDocumentHandler;
import mf.org.apache.xerces.xni.XMLResourceIdentifier;
import mf.org.apache.xerces.xni.XMLString;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.parser.XMLComponent;
import mf.org.apache.xerces.xni.parser.XMLComponentManager;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;
import mf.org.apache.xerces.xni.parser.XMLDocumentScanner;
import mf.org.apache.xerces.xni.parser.XMLInputSource;

public class XMLDocumentFragmentScannerImpl extends XMLScanner implements XMLDocumentScanner, XMLComponent, XMLEntityHandler {
    protected static final boolean DEBUG_CONTENT_SCANNING = false;
    private static final boolean DEBUG_DISPATCHER = false;
    private static final boolean DEBUG_SCANNER_STATE = false;
    protected static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    private static final Boolean[] FEATURE_DEFAULTS;
    protected static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    protected static final String NOTIFY_BUILTIN_REFS = "http://apache.org/xml/features/scanner/notify-builtin-refs";
    private static final Object[] PROPERTY_DEFAULTS = new Object[4];
    private static final String[] RECOGNIZED_FEATURES = new String[]{NAMESPACES, "http://xml.org/sax/features/validation", NOTIFY_BUILTIN_REFS, "http://apache.org/xml/features/scanner/notify-char-refs"};
    private static final String[] RECOGNIZED_PROPERTIES = new String[]{"http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/entity-manager", "http://apache.org/xml/properties/internal/entity-resolver"};
    protected static final int SCANNER_STATE_CDATA = 15;
    protected static final int SCANNER_STATE_COMMENT = 2;
    protected static final int SCANNER_STATE_CONTENT = 7;
    protected static final int SCANNER_STATE_DOCTYPE = 4;
    protected static final int SCANNER_STATE_END_OF_INPUT = 13;
    protected static final int SCANNER_STATE_PI = 3;
    protected static final int SCANNER_STATE_REFERENCE = 8;
    protected static final int SCANNER_STATE_ROOT_ELEMENT = 6;
    protected static final int SCANNER_STATE_START_OF_MARKUP = 1;
    protected static final int SCANNER_STATE_TERMINATED = 14;
    protected static final int SCANNER_STATE_TEXT_DECL = 16;
    protected final QName fAttributeQName = new QName();
    protected final XMLAttributesImpl fAttributes = new XMLAttributesImpl();
    protected final Dispatcher fContentDispatcher = createContentDispatcher();
    protected QName fCurrentElement;
    protected Dispatcher fDispatcher;
    protected XMLDocumentHandler fDocumentHandler;
    protected final QName fElementQName = new QName();
    protected final ElementStack fElementStack = new ElementStack();
    protected int[] fEntityStack = new int[4];
    protected ExternalSubsetResolver fExternalSubsetResolver;
    protected boolean fHasExternalDTD;
    protected boolean fInScanContent = false;
    protected boolean fIsEntityDeclaredVC;
    protected int fMarkupDepth;
    protected boolean fNotifyBuiltInRefs = false;
    private final QName fQName = new QName();
    private boolean fSawSpace;
    protected int fScannerState;
    private final char[] fSingleChar = new char[1];
    protected boolean fStandalone;
    private final XMLStringBuffer fStringBuffer = new XMLStringBuffer();
    private final XMLStringBuffer fStringBuffer2 = new XMLStringBuffer();
    private final String[] fStrings = new String[3];
    private Augmentations fTempAugmentations = null;
    protected final XMLString fTempString = new XMLString();
    protected final XMLString fTempString2 = new XMLString();

    protected interface Dispatcher {
        boolean dispatch(boolean z) throws IOException, XNIException;
    }

    protected class FragmentContentDispatcher implements Dispatcher {
        protected FragmentContentDispatcher() {
        }

        public boolean dispatch(boolean complete) throws IOException, XNIException {
            while (true) {
                boolean again = false;
                try {
                    XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl;
                    switch (XMLDocumentFragmentScannerImpl.this.fScannerState) {
                        case 1:
                            xMLDocumentFragmentScannerImpl = XMLDocumentFragmentScannerImpl.this;
                            xMLDocumentFragmentScannerImpl.fMarkupDepth++;
                            if (!XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(47)) {
                                if (!XMLDocumentFragmentScannerImpl.this.isValidNameStartChar(XMLDocumentFragmentScannerImpl.this.fEntityScanner.peekChar())) {
                                    if (!XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(33)) {
                                        if (!XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(63)) {
                                            if (!XMLDocumentFragmentScannerImpl.this.isValidNameStartHighSurrogate(XMLDocumentFragmentScannerImpl.this.fEntityScanner.peekChar())) {
                                                XMLDocumentFragmentScannerImpl.this.reportFatalError("MarkupNotRecognizedInContent", null);
                                                XMLDocumentFragmentScannerImpl.this.setScannerState(7);
                                                break;
                                            }
                                            XMLDocumentFragmentScannerImpl.this.scanStartElement();
                                            XMLDocumentFragmentScannerImpl.this.setScannerState(7);
                                            break;
                                        }
                                        XMLDocumentFragmentScannerImpl.this.setScannerState(3);
                                        again = true;
                                        break;
                                    } else if (!XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(45)) {
                                        if (!XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipString("[CDATA[")) {
                                            if (!scanForDoctypeHook()) {
                                                XMLDocumentFragmentScannerImpl.this.reportFatalError("MarkupNotRecognizedInContent", null);
                                                break;
                                            }
                                        }
                                        XMLDocumentFragmentScannerImpl.this.setScannerState(15);
                                        again = true;
                                        break;
                                    } else {
                                        if (!XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(45)) {
                                            XMLDocumentFragmentScannerImpl.this.reportFatalError("InvalidCommentStart", null);
                                        }
                                        XMLDocumentFragmentScannerImpl.this.setScannerState(2);
                                        again = true;
                                        break;
                                    }
                                }
                                XMLDocumentFragmentScannerImpl.this.scanStartElement();
                                XMLDocumentFragmentScannerImpl.this.setScannerState(7);
                                break;
                            } else if (XMLDocumentFragmentScannerImpl.this.scanEndElement() != 0 || !elementDepthIsZeroHook()) {
                                XMLDocumentFragmentScannerImpl.this.setScannerState(7);
                                break;
                            } else {
                                return true;
                            }
                            break;
                        case 2:
                            XMLDocumentFragmentScannerImpl.this.scanComment();
                            XMLDocumentFragmentScannerImpl.this.setScannerState(7);
                            break;
                        case 3:
                            XMLDocumentFragmentScannerImpl.this.scanPI();
                            XMLDocumentFragmentScannerImpl.this.setScannerState(7);
                            break;
                        case 4:
                            XMLDocumentFragmentScannerImpl.this.reportFatalError("DoctypeIllegalInContent", null);
                            XMLDocumentFragmentScannerImpl.this.setScannerState(7);
                            break;
                        case 6:
                            if (!scanRootElementHook()) {
                                XMLDocumentFragmentScannerImpl.this.setScannerState(7);
                                break;
                            }
                            return true;
                        case 7:
                            if (!XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(60)) {
                                if (!XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(38)) {
                                    do {
                                        int c = XMLDocumentFragmentScannerImpl.this.scanContent();
                                        if (c == 60) {
                                            XMLDocumentFragmentScannerImpl.this.fEntityScanner.scanChar();
                                            XMLDocumentFragmentScannerImpl.this.setScannerState(1);
                                            break;
                                        } else if (c == 38) {
                                            XMLDocumentFragmentScannerImpl.this.fEntityScanner.scanChar();
                                            XMLDocumentFragmentScannerImpl.this.setScannerState(8);
                                            break;
                                        } else if (c != -1) {
                                            if (!XMLDocumentFragmentScannerImpl.this.isInvalidLiteral(c)) {
                                                continue;
                                            } else if (XMLChar.isHighSurrogate(c)) {
                                                XMLDocumentFragmentScannerImpl.this.fStringBuffer.clear();
                                                if (XMLDocumentFragmentScannerImpl.this.scanSurrogates(XMLDocumentFragmentScannerImpl.this.fStringBuffer) && XMLDocumentFragmentScannerImpl.this.fDocumentHandler != null) {
                                                    XMLDocumentFragmentScannerImpl.this.fDocumentHandler.characters(XMLDocumentFragmentScannerImpl.this.fStringBuffer, null);
                                                    continue;
                                                }
                                            } else {
                                                XMLDocumentFragmentScannerImpl.this.reportFatalError("InvalidCharInContent", new Object[]{Integer.toString(c, 16)});
                                                XMLDocumentFragmentScannerImpl.this.fEntityScanner.scanChar();
                                                continue;
                                            }
                                        }
                                    } while (complete);
                                    break;
                                }
                                XMLDocumentFragmentScannerImpl.this.setScannerState(8);
                                again = true;
                                break;
                            }
                            XMLDocumentFragmentScannerImpl.this.setScannerState(1);
                            again = true;
                            break;
                        case 8:
                            xMLDocumentFragmentScannerImpl = XMLDocumentFragmentScannerImpl.this;
                            xMLDocumentFragmentScannerImpl.fMarkupDepth++;
                            XMLDocumentFragmentScannerImpl.this.setScannerState(7);
                            if (!XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(35)) {
                                XMLDocumentFragmentScannerImpl.this.scanEntityReference();
                                break;
                            }
                            XMLDocumentFragmentScannerImpl.this.scanCharReference();
                            break;
                        case 15:
                            XMLDocumentFragmentScannerImpl.this.scanCDATASection(complete);
                            XMLDocumentFragmentScannerImpl.this.setScannerState(7);
                            break;
                        case 16:
                            if (XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipString("<?xml")) {
                                xMLDocumentFragmentScannerImpl = XMLDocumentFragmentScannerImpl.this;
                                xMLDocumentFragmentScannerImpl.fMarkupDepth++;
                                if (XMLDocumentFragmentScannerImpl.this.isValidNameChar(XMLDocumentFragmentScannerImpl.this.fEntityScanner.peekChar())) {
                                    XMLDocumentFragmentScannerImpl.this.fStringBuffer.clear();
                                    XMLDocumentFragmentScannerImpl.this.fStringBuffer.append("xml");
                                    if (XMLDocumentFragmentScannerImpl.this.fNamespaces) {
                                        while (XMLDocumentFragmentScannerImpl.this.isValidNCName(XMLDocumentFragmentScannerImpl.this.fEntityScanner.peekChar())) {
                                            XMLDocumentFragmentScannerImpl.this.fStringBuffer.append((char) XMLDocumentFragmentScannerImpl.this.fEntityScanner.scanChar());
                                        }
                                    } else {
                                        while (XMLDocumentFragmentScannerImpl.this.isValidNameChar(XMLDocumentFragmentScannerImpl.this.fEntityScanner.peekChar())) {
                                            XMLDocumentFragmentScannerImpl.this.fStringBuffer.append((char) XMLDocumentFragmentScannerImpl.this.fEntityScanner.scanChar());
                                        }
                                    }
                                    XMLDocumentFragmentScannerImpl.this.scanPIData(XMLDocumentFragmentScannerImpl.this.fSymbolTable.addSymbol(XMLDocumentFragmentScannerImpl.this.fStringBuffer.ch, XMLDocumentFragmentScannerImpl.this.fStringBuffer.offset, XMLDocumentFragmentScannerImpl.this.fStringBuffer.length), XMLDocumentFragmentScannerImpl.this.fTempString);
                                } else {
                                    XMLDocumentFragmentScannerImpl.this.scanXMLDeclOrTextDecl(true);
                                }
                            }
                            XMLDocumentFragmentScannerImpl.this.fEntityManager.fCurrentEntity.mayReadChunks = true;
                            XMLDocumentFragmentScannerImpl.this.setScannerState(7);
                            break;
                    }
                    if (!complete && !again) {
                        return true;
                    }
                } catch (Exception e) {
                    XMLDocumentFragmentScannerImpl.this.fErrorReporter.reportError(e.getDomain(), e.getKey(), e.getArguments(), (short) 2, e);
                    return false;
                } catch (Exception e2) {
                    XMLDocumentFragmentScannerImpl.this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "CharConversionFailure", null, (short) 2, e2);
                    return false;
                } catch (EOFException e3) {
                    endOfFileHook(e3);
                    return false;
                }
            }
        }

        protected boolean scanForDoctypeHook() throws IOException, XNIException {
            return false;
        }

        protected boolean elementDepthIsZeroHook() throws IOException, XNIException {
            return false;
        }

        protected boolean scanRootElementHook() throws IOException, XNIException {
            return false;
        }

        protected void endOfFileHook(EOFException e) throws IOException, XNIException {
            if (XMLDocumentFragmentScannerImpl.this.fMarkupDepth != 0) {
                XMLDocumentFragmentScannerImpl.this.reportFatalError("PrematureEOF", null);
            }
        }
    }

    protected static class ElementStack {
        protected QName[] fElements = new QName[10];
        protected int fSize;

        public ElementStack() {
            for (int i = 0; i < this.fElements.length; i++) {
                this.fElements[i] = new QName();
            }
        }

        public QName pushElement(QName element) {
            if (this.fSize == this.fElements.length) {
                QName[] array = new QName[(this.fElements.length * 2)];
                System.arraycopy(this.fElements, 0, array, 0, this.fSize);
                this.fElements = array;
                for (int i = this.fSize; i < this.fElements.length; i++) {
                    this.fElements[i] = new QName();
                }
            }
            this.fElements[this.fSize].setValues(element);
            QName[] qNameArr = this.fElements;
            int i2 = this.fSize;
            this.fSize = i2 + 1;
            return qNameArr[i2];
        }

        public void popElement(QName element) {
            QName[] qNameArr = this.fElements;
            int i = this.fSize - 1;
            this.fSize = i;
            element.setValues(qNameArr[i]);
        }

        public void clear() {
            this.fSize = 0;
        }
    }

    static {
        Boolean[] boolArr = new Boolean[4];
        boolArr[2] = Boolean.FALSE;
        boolArr[3] = Boolean.FALSE;
        FEATURE_DEFAULTS = boolArr;
    }

    public void setInputSource(XMLInputSource inputSource) throws IOException {
        this.fEntityManager.setEntityHandler(this);
        this.fEntityManager.startEntity("$fragment$", inputSource, false, true);
    }

    public boolean scanDocument(boolean complete) throws IOException, XNIException {
        this.fEntityScanner = this.fEntityManager.getEntityScanner();
        this.fEntityManager.setEntityHandler(this);
        while (this.fDispatcher.dispatch(complete)) {
            if (!complete) {
                return true;
            }
        }
        return false;
    }

    public void reset(XMLComponentManager componentManager) throws XMLConfigurationException {
        super.reset(componentManager);
        this.fAttributes.setNamespaces(this.fNamespaces);
        this.fMarkupDepth = 0;
        this.fCurrentElement = null;
        this.fElementStack.clear();
        this.fHasExternalDTD = false;
        this.fStandalone = false;
        this.fIsEntityDeclaredVC = false;
        this.fInScanContent = false;
        setScannerState(7);
        setDispatcher(this.fContentDispatcher);
        if (this.fParserSettings) {
            try {
                this.fNotifyBuiltInRefs = componentManager.getFeature(NOTIFY_BUILTIN_REFS);
            } catch (XMLConfigurationException e) {
                this.fNotifyBuiltInRefs = false;
            }
            try {
                Object resolver = componentManager.getProperty("http://apache.org/xml/properties/internal/entity-resolver");
                if (resolver instanceof ExternalSubsetResolver) {
                    resolver = (ExternalSubsetResolver) resolver;
                } else {
                    resolver = null;
                }
                this.fExternalSubsetResolver = resolver;
            } catch (XMLConfigurationException e2) {
                this.fExternalSubsetResolver = null;
            }
        }
    }

    public String[] getRecognizedFeatures() {
        return (String[]) RECOGNIZED_FEATURES.clone();
    }

    public void setFeature(String featureId, boolean state) throws XMLConfigurationException {
        super.setFeature(featureId, state);
        if (featureId.startsWith(Constants.XERCES_FEATURE_PREFIX) && featureId.length() - Constants.XERCES_FEATURE_PREFIX.length() == Constants.NOTIFY_BUILTIN_REFS_FEATURE.length() && featureId.endsWith(Constants.NOTIFY_BUILTIN_REFS_FEATURE)) {
            this.fNotifyBuiltInRefs = state;
        }
    }

    public String[] getRecognizedProperties() {
        return (String[]) RECOGNIZED_PROPERTIES.clone();
    }

    public void setProperty(String propertyId, Object value) throws XMLConfigurationException {
        super.setProperty(propertyId, value);
        if (propertyId.startsWith(Constants.XERCES_PROPERTY_PREFIX)) {
            int suffixLength = propertyId.length() - Constants.XERCES_PROPERTY_PREFIX.length();
            if (suffixLength == Constants.ENTITY_MANAGER_PROPERTY.length() && propertyId.endsWith(Constants.ENTITY_MANAGER_PROPERTY)) {
                this.fEntityManager = (XMLEntityManager) value;
            } else if (suffixLength == Constants.ENTITY_RESOLVER_PROPERTY.length() && propertyId.endsWith(Constants.ENTITY_RESOLVER_PROPERTY)) {
                this.fExternalSubsetResolver = value instanceof ExternalSubsetResolver ? (ExternalSubsetResolver) value : null;
            }
        }
    }

    public Boolean getFeatureDefault(String featureId) {
        for (int i = 0; i < RECOGNIZED_FEATURES.length; i++) {
            if (RECOGNIZED_FEATURES[i].equals(featureId)) {
                return FEATURE_DEFAULTS[i];
            }
        }
        return null;
    }

    public Object getPropertyDefault(String propertyId) {
        for (int i = 0; i < RECOGNIZED_PROPERTIES.length; i++) {
            if (RECOGNIZED_PROPERTIES[i].equals(propertyId)) {
                return PROPERTY_DEFAULTS[i];
            }
        }
        return null;
    }

    public void setDocumentHandler(XMLDocumentHandler documentHandler) {
        this.fDocumentHandler = documentHandler;
    }

    public XMLDocumentHandler getDocumentHandler() {
        return this.fDocumentHandler;
    }

    public void startEntity(String name, XMLResourceIdentifier identifier, String encoding, Augmentations augs) throws XNIException {
        if (this.fEntityDepth == this.fEntityStack.length) {
            int[] entityarray = new int[(this.fEntityStack.length * 2)];
            System.arraycopy(this.fEntityStack, 0, entityarray, 0, this.fEntityStack.length);
            this.fEntityStack = entityarray;
        }
        this.fEntityStack[this.fEntityDepth] = this.fMarkupDepth;
        super.startEntity(name, identifier, encoding, augs);
        if (this.fStandalone && this.fEntityManager.isEntityDeclInExternalSubset(name)) {
            reportFatalError("MSG_REFERENCE_TO_EXTERNALLY_DECLARED_ENTITY_WHEN_STANDALONE", new Object[]{name});
        }
        if (this.fDocumentHandler != null && !this.fScanningAttribute && !name.equals("[xml]")) {
            this.fDocumentHandler.startGeneralEntity(name, identifier, encoding, augs);
        }
    }

    public void endEntity(String name, Augmentations augs) throws XNIException {
        if (!(!this.fInScanContent || this.fStringBuffer.length == 0 || this.fDocumentHandler == null)) {
            this.fDocumentHandler.characters(this.fStringBuffer, null);
            this.fStringBuffer.length = 0;
        }
        super.endEntity(name, augs);
        if (this.fMarkupDepth != this.fEntityStack[this.fEntityDepth]) {
            reportFatalError("MarkupEntityMismatch", null);
        }
        if (this.fDocumentHandler != null && !this.fScanningAttribute && !name.equals("[xml]")) {
            this.fDocumentHandler.endGeneralEntity(name, augs);
        }
    }

    protected Dispatcher createContentDispatcher() {
        return new FragmentContentDispatcher();
    }

    protected void scanXMLDeclOrTextDecl(boolean scanningTextDecl) throws IOException, XNIException {
        boolean z = true;
        super.scanXMLDeclOrTextDecl(scanningTextDecl, this.fStrings);
        this.fMarkupDepth--;
        String version = this.fStrings[0];
        String encoding = this.fStrings[1];
        String standalone = this.fStrings[2];
        if (standalone == null || !standalone.equals("yes")) {
            z = false;
        }
        this.fStandalone = z;
        this.fEntityManager.setStandalone(this.fStandalone);
        this.fEntityScanner.setXMLVersion(version);
        if (this.fDocumentHandler != null) {
            if (scanningTextDecl) {
                this.fDocumentHandler.textDecl(version, encoding, null);
            } else {
                this.fDocumentHandler.xmlDecl(version, encoding, standalone, null);
            }
        }
        if (encoding != null && !this.fEntityScanner.fCurrentEntity.isEncodingExternallySpecified()) {
            this.fEntityScanner.setEncoding(encoding);
        }
    }

    protected void scanPIData(String target, XMLString data) throws IOException, XNIException {
        super.scanPIData(target, data);
        this.fMarkupDepth--;
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.processingInstruction(target, data, null);
        }
    }

    protected void scanComment() throws IOException, XNIException {
        scanComment(this.fStringBuffer);
        this.fMarkupDepth--;
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.comment(this.fStringBuffer, null);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected boolean scanStartElement() throws java.io.IOException, mf.org.apache.xerces.xni.XNIException {
        /*
        r11 = this;
        r7 = 62;
        r10 = 1;
        r9 = 0;
        r8 = 0;
        r5 = r11.fNamespaces;
        if (r5 == 0) goto L_0x006e;
    L_0x0009:
        r5 = r11.fEntityScanner;
        r6 = r11.fElementQName;
        r5.scanQName(r6);
    L_0x0010:
        r5 = r11.fElementQName;
        r3 = r5.rawname;
        r5 = r11.fElementStack;
        r6 = r11.fElementQName;
        r5 = r5.pushElement(r6);
        r11.fCurrentElement = r5;
        r1 = 0;
        r5 = r11.fAttributes;
        r5.removeAllAttributes();
    L_0x0024:
        r5 = r11.fEntityScanner;
        r4 = r5.skipSpaces();
        r5 = r11.fEntityScanner;
        r0 = r5.peekChar();
        if (r0 != r7) goto L_0x007a;
    L_0x0032:
        r5 = r11.fEntityScanner;
        r5.scanChar();
    L_0x0037:
        r5 = r11.fDocumentHandler;
        if (r5 == 0) goto L_0x006d;
    L_0x003b:
        if (r1 == 0) goto L_0x00b8;
    L_0x003d:
        r5 = r11.fMarkupDepth;
        r5 = r5 + -1;
        r11.fMarkupDepth = r5;
        r5 = r11.fMarkupDepth;
        r6 = r11.fEntityStack;
        r7 = r11.fEntityDepth;
        r7 = r7 + -1;
        r6 = r6[r7];
        if (r5 >= r6) goto L_0x005d;
    L_0x004f:
        r5 = "ElementEntityMismatch";
        r6 = new java.lang.Object[r10];
        r7 = r11.fCurrentElement;
        r7 = r7.rawname;
        r6[r9] = r7;
        r11.reportFatalError(r5, r6);
    L_0x005d:
        r5 = r11.fDocumentHandler;
        r6 = r11.fElementQName;
        r7 = r11.fAttributes;
        r5.emptyElement(r6, r7, r8);
        r5 = r11.fElementStack;
        r6 = r11.fElementQName;
        r5.popElement(r6);
    L_0x006d:
        return r1;
    L_0x006e:
        r5 = r11.fEntityScanner;
        r2 = r5.scanName();
        r5 = r11.fElementQName;
        r5.setValues(r8, r2, r2, r8);
        goto L_0x0010;
    L_0x007a:
        r5 = 47;
        if (r0 != r5) goto L_0x0097;
    L_0x007e:
        r5 = r11.fEntityScanner;
        r5.scanChar();
        r5 = r11.fEntityScanner;
        r5 = r5.skipChar(r7);
        if (r5 != 0) goto L_0x0095;
    L_0x008b:
        r5 = "ElementUnterminated";
        r6 = new java.lang.Object[r10];
        r6[r9] = r3;
        r11.reportFatalError(r5, r6);
    L_0x0095:
        r1 = 1;
        goto L_0x0037;
    L_0x0097:
        r5 = r11.isValidNameStartChar(r0);
        if (r5 == 0) goto L_0x009f;
    L_0x009d:
        if (r4 != 0) goto L_0x00b1;
    L_0x009f:
        r5 = r11.isValidNameStartHighSurrogate(r0);
        if (r5 == 0) goto L_0x00a7;
    L_0x00a5:
        if (r4 != 0) goto L_0x00b1;
    L_0x00a7:
        r5 = "ElementUnterminated";
        r6 = new java.lang.Object[r10];
        r6[r9] = r3;
        r11.reportFatalError(r5, r6);
    L_0x00b1:
        r5 = r11.fAttributes;
        r11.scanAttribute(r5);
        goto L_0x0024;
    L_0x00b8:
        r5 = r11.fDocumentHandler;
        r6 = r11.fElementQName;
        r7 = r11.fAttributes;
        r5.startElement(r6, r7, r8);
        goto L_0x006d;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.XMLDocumentFragmentScannerImpl.scanStartElement():boolean");
    }

    protected void scanStartElementName() throws IOException, XNIException {
        if (this.fNamespaces) {
            this.fEntityScanner.scanQName(this.fElementQName);
        } else {
            String name = this.fEntityScanner.scanName();
            this.fElementQName.setValues(null, name, name, null);
        }
        this.fSawSpace = this.fEntityScanner.skipSpaces();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected boolean scanStartElementAfterName() throws java.io.IOException, mf.org.apache.xerces.xni.XNIException {
        /*
        r9 = this;
        r8 = 0;
        r5 = 62;
        r7 = 1;
        r6 = 0;
        r3 = r9.fElementQName;
        r2 = r3.rawname;
        r3 = r9.fElementStack;
        r4 = r9.fElementQName;
        r3 = r3.pushElement(r4);
        r9.fCurrentElement = r3;
        r1 = 0;
        r3 = r9.fAttributes;
        r3.removeAllAttributes();
    L_0x0019:
        r3 = r9.fEntityScanner;
        r0 = r3.peekChar();
        if (r0 != r5) goto L_0x005d;
    L_0x0021:
        r3 = r9.fEntityScanner;
        r3.scanChar();
    L_0x0026:
        r3 = r9.fDocumentHandler;
        if (r3 == 0) goto L_0x005c;
    L_0x002a:
        if (r1 == 0) goto L_0x00a7;
    L_0x002c:
        r3 = r9.fMarkupDepth;
        r3 = r3 + -1;
        r9.fMarkupDepth = r3;
        r3 = r9.fMarkupDepth;
        r4 = r9.fEntityStack;
        r5 = r9.fEntityDepth;
        r5 = r5 + -1;
        r4 = r4[r5];
        if (r3 >= r4) goto L_0x004c;
    L_0x003e:
        r3 = "ElementEntityMismatch";
        r4 = new java.lang.Object[r7];
        r5 = r9.fCurrentElement;
        r5 = r5.rawname;
        r4[r6] = r5;
        r9.reportFatalError(r3, r4);
    L_0x004c:
        r3 = r9.fDocumentHandler;
        r4 = r9.fElementQName;
        r5 = r9.fAttributes;
        r3.emptyElement(r4, r5, r8);
        r3 = r9.fElementStack;
        r4 = r9.fElementQName;
        r3.popElement(r4);
    L_0x005c:
        return r1;
    L_0x005d:
        r3 = 47;
        if (r0 != r3) goto L_0x007a;
    L_0x0061:
        r3 = r9.fEntityScanner;
        r3.scanChar();
        r3 = r9.fEntityScanner;
        r3 = r3.skipChar(r5);
        if (r3 != 0) goto L_0x0078;
    L_0x006e:
        r3 = "ElementUnterminated";
        r4 = new java.lang.Object[r7];
        r4[r6] = r2;
        r9.reportFatalError(r3, r4);
    L_0x0078:
        r1 = 1;
        goto L_0x0026;
    L_0x007a:
        r3 = r9.isValidNameStartChar(r0);
        if (r3 == 0) goto L_0x0084;
    L_0x0080:
        r3 = r9.fSawSpace;
        if (r3 != 0) goto L_0x0098;
    L_0x0084:
        r3 = r9.isValidNameStartHighSurrogate(r0);
        if (r3 == 0) goto L_0x008e;
    L_0x008a:
        r3 = r9.fSawSpace;
        if (r3 != 0) goto L_0x0098;
    L_0x008e:
        r3 = "ElementUnterminated";
        r4 = new java.lang.Object[r7];
        r4[r6] = r2;
        r9.reportFatalError(r3, r4);
    L_0x0098:
        r3 = r9.fAttributes;
        r9.scanAttribute(r3);
        r3 = r9.fEntityScanner;
        r3 = r3.skipSpaces();
        r9.fSawSpace = r3;
        goto L_0x0019;
    L_0x00a7:
        r3 = r9.fDocumentHandler;
        r4 = r9.fElementQName;
        r5 = r9.fAttributes;
        r3.startElement(r4, r5, r8);
        goto L_0x005c;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.XMLDocumentFragmentScannerImpl.scanStartElementAfterName():boolean");
    }

    protected void scanAttribute(XMLAttributes attributes) throws IOException, XNIException {
        if (this.fNamespaces) {
            this.fEntityScanner.scanQName(this.fAttributeQName);
        } else {
            String name = this.fEntityScanner.scanName();
            this.fAttributeQName.setValues(null, name, name, null);
        }
        this.fEntityScanner.skipSpaces();
        if (!this.fEntityScanner.skipChar(61)) {
            reportFatalError("EqRequiredInAttribute", new Object[]{this.fCurrentElement.rawname, this.fAttributeQName.rawname});
        }
        this.fEntityScanner.skipSpaces();
        int oldLen = attributes.getLength();
        int attrIndex = attributes.addAttribute(this.fAttributeQName, XMLSymbols.fCDATASymbol, null);
        if (oldLen == attributes.getLength()) {
            reportFatalError("AttributeNotUnique", new Object[]{this.fCurrentElement.rawname, this.fAttributeQName.rawname});
        }
        boolean isSameNormalizedAttr = scanAttributeValue(this.fTempString, this.fTempString2, this.fAttributeQName.rawname, this.fIsEntityDeclaredVC, this.fCurrentElement.rawname);
        attributes.setValue(attrIndex, this.fTempString.toString());
        if (!isSameNormalizedAttr) {
            attributes.setNonNormalizedValue(attrIndex, this.fTempString2.toString());
        }
        attributes.setSpecified(attrIndex, true);
    }

    protected int scanContent() throws IOException, XNIException {
        XMLString content = this.fTempString;
        int c = this.fEntityScanner.scanContent(content);
        if (c == 13) {
            this.fEntityScanner.scanChar();
            this.fStringBuffer.clear();
            this.fStringBuffer.append(this.fTempString);
            this.fStringBuffer.append((char) c);
            content = this.fStringBuffer;
            c = -1;
        }
        if (this.fDocumentHandler != null && content.length > 0) {
            this.fDocumentHandler.characters(content, null);
        }
        if (c != 93 || this.fTempString.length != 0) {
            return c;
        }
        this.fStringBuffer.clear();
        this.fStringBuffer.append((char) this.fEntityScanner.scanChar());
        this.fInScanContent = true;
        if (this.fEntityScanner.skipChar(93)) {
            this.fStringBuffer.append(']');
            while (this.fEntityScanner.skipChar(93)) {
                this.fStringBuffer.append(']');
            }
            if (this.fEntityScanner.skipChar(62)) {
                reportFatalError("CDEndInContent", null);
            }
        }
        if (!(this.fDocumentHandler == null || this.fStringBuffer.length == 0)) {
            this.fDocumentHandler.characters(this.fStringBuffer, null);
        }
        this.fInScanContent = false;
        return -1;
    }

    protected boolean scanCDATASection(boolean complete) throws IOException, XNIException {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.startCDATA(null);
        }
        while (true) {
            this.fStringBuffer.clear();
            if (this.fEntityScanner.scanData("]]", this.fStringBuffer)) {
                if (this.fDocumentHandler != null) {
                    this.fDocumentHandler.characters(this.fStringBuffer, null);
                }
                int c = this.fEntityScanner.peekChar();
                if (c != -1 && isInvalidLiteral(c)) {
                    if (XMLChar.isHighSurrogate(c)) {
                        this.fStringBuffer.clear();
                        scanSurrogates(this.fStringBuffer);
                        if (this.fDocumentHandler != null) {
                            this.fDocumentHandler.characters(this.fStringBuffer, null);
                        }
                    } else {
                        reportFatalError("InvalidCharInCDSect", new Object[]{Integer.toString(c, 16)});
                        this.fEntityScanner.scanChar();
                    }
                }
            } else {
                if (this.fDocumentHandler != null && this.fStringBuffer.length > 0) {
                    this.fDocumentHandler.characters(this.fStringBuffer, null);
                }
                int brackets = 0;
                while (this.fEntityScanner.skipChar(93)) {
                    brackets++;
                }
                if (this.fDocumentHandler != null && brackets > 0) {
                    this.fStringBuffer.clear();
                    int i;
                    if (brackets > 2048) {
                        int chunks = brackets / 2048;
                        int remainder = brackets % 2048;
                        for (i = 0; i < 2048; i++) {
                            this.fStringBuffer.append(']');
                        }
                        for (i = 0; i < chunks; i++) {
                            this.fDocumentHandler.characters(this.fStringBuffer, null);
                        }
                        if (remainder != 0) {
                            this.fStringBuffer.length = remainder;
                            this.fDocumentHandler.characters(this.fStringBuffer, null);
                        }
                    } else {
                        for (i = 0; i < brackets; i++) {
                            this.fStringBuffer.append(']');
                        }
                        this.fDocumentHandler.characters(this.fStringBuffer, null);
                    }
                }
                if (this.fEntityScanner.skipChar(62)) {
                    break;
                } else if (this.fDocumentHandler != null) {
                    this.fStringBuffer.clear();
                    this.fStringBuffer.append("]]");
                    this.fDocumentHandler.characters(this.fStringBuffer, null);
                }
            }
        }
        this.fMarkupDepth--;
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.endCDATA(null);
        }
        return true;
    }

    protected int scanEndElement() throws IOException, XNIException {
        this.fElementStack.popElement(this.fElementQName);
        if (!this.fEntityScanner.skipString(this.fElementQName.rawname)) {
            reportFatalError("ETagRequired", new Object[]{this.fElementQName.rawname});
        }
        this.fEntityScanner.skipSpaces();
        if (!this.fEntityScanner.skipChar(62)) {
            reportFatalError("ETagUnterminated", new Object[]{this.fElementQName.rawname});
        }
        this.fMarkupDepth--;
        this.fMarkupDepth--;
        if (this.fMarkupDepth < this.fEntityStack[this.fEntityDepth - 1]) {
            reportFatalError("ElementEntityMismatch", new Object[]{this.fCurrentElement.rawname});
        }
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.endElement(this.fElementQName, null);
        }
        return this.fMarkupDepth;
    }

    protected void scanCharReference() throws IOException, XNIException {
        this.fStringBuffer2.clear();
        int ch = scanCharReferenceValue(this.fStringBuffer2, null);
        this.fMarkupDepth--;
        if (ch != -1 && this.fDocumentHandler != null) {
            if (this.fNotifyCharRefs) {
                this.fDocumentHandler.startGeneralEntity(this.fCharRefLiteral, null, null, null);
            }
            Augmentations augs = null;
            if (this.fValidation && ch <= 32) {
                if (this.fTempAugmentations != null) {
                    this.fTempAugmentations.removeAllItems();
                } else {
                    this.fTempAugmentations = new AugmentationsImpl();
                }
                augs = this.fTempAugmentations;
                augs.putItem(Constants.CHAR_REF_PROBABLE_WS, Boolean.TRUE);
            }
            this.fDocumentHandler.characters(this.fStringBuffer2, augs);
            if (this.fNotifyCharRefs) {
                this.fDocumentHandler.endGeneralEntity(this.fCharRefLiteral, null);
            }
        }
    }

    protected void scanEntityReference() throws IOException, XNIException {
        String name = this.fEntityScanner.scanName();
        if (name == null) {
            reportFatalError("NameRequiredInReference", null);
            return;
        }
        if (!this.fEntityScanner.skipChar(59)) {
            reportFatalError("SemicolonRequiredInReference", new Object[]{name});
        }
        this.fMarkupDepth--;
        if (name == fAmpSymbol) {
            handleCharacter('&', fAmpSymbol);
        } else if (name == fLtSymbol) {
            handleCharacter('<', fLtSymbol);
        } else if (name == fGtSymbol) {
            handleCharacter('>', fGtSymbol);
        } else if (name == fQuotSymbol) {
            handleCharacter('\"', fQuotSymbol);
        } else if (name == fAposSymbol) {
            handleCharacter('\'', fAposSymbol);
        } else if (this.fEntityManager.isUnparsedEntity(name)) {
            reportFatalError("ReferenceToUnparsedEntity", new Object[]{name});
        } else {
            if (!this.fEntityManager.isDeclaredEntity(name)) {
                if (!this.fIsEntityDeclaredVC) {
                    reportFatalError("EntityNotDeclared", new Object[]{name});
                } else if (this.fValidation) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "EntityNotDeclared", new Object[]{name}, (short) 1);
                }
            }
            this.fEntityManager.startEntity(name, false);
        }
    }

    private void handleCharacter(char c, String entity) throws XNIException {
        if (this.fDocumentHandler != null) {
            if (this.fNotifyBuiltInRefs) {
                this.fDocumentHandler.startGeneralEntity(entity, null, null, null);
            }
            this.fSingleChar[0] = c;
            this.fTempString.setValues(this.fSingleChar, 0, 1);
            this.fDocumentHandler.characters(this.fTempString, null);
            if (this.fNotifyBuiltInRefs) {
                this.fDocumentHandler.endGeneralEntity(entity, null);
            }
        }
    }

    protected int handleEndElement(QName element, boolean isEmpty) throws XNIException {
        this.fMarkupDepth--;
        if (this.fMarkupDepth < this.fEntityStack[this.fEntityDepth - 1]) {
            reportFatalError("ElementEntityMismatch", new Object[]{this.fCurrentElement.rawname});
        }
        QName startElement = this.fQName;
        this.fElementStack.popElement(startElement);
        if (element.rawname != startElement.rawname) {
            reportFatalError("ETagRequired", new Object[]{startElement.rawname});
        }
        if (this.fNamespaces) {
            element.uri = startElement.uri;
        }
        if (!(this.fDocumentHandler == null || isEmpty)) {
            this.fDocumentHandler.endElement(element, null);
        }
        return this.fMarkupDepth;
    }

    protected final void setScannerState(int state) {
        this.fScannerState = state;
    }

    protected final void setDispatcher(Dispatcher dispatcher) {
        this.fDispatcher = dispatcher;
    }

    protected String getScannerStateName(int state) {
        switch (state) {
            case 1:
                return "SCANNER_STATE_START_OF_MARKUP";
            case 2:
                return "SCANNER_STATE_COMMENT";
            case 3:
                return "SCANNER_STATE_PI";
            case 4:
                return "SCANNER_STATE_DOCTYPE";
            case 6:
                return "SCANNER_STATE_ROOT_ELEMENT";
            case 7:
                return "SCANNER_STATE_CONTENT";
            case 8:
                return "SCANNER_STATE_REFERENCE";
            case 13:
                return "SCANNER_STATE_END_OF_INPUT";
            case 14:
                return "SCANNER_STATE_TERMINATED";
            case 15:
                return "SCANNER_STATE_CDATA";
            case 16:
                return "SCANNER_STATE_TEXT_DECL";
            default:
                return "??? (" + state + ')';
        }
    }

    public String getDispatcherName(Dispatcher dispatcher) {
        return "null";
    }
}
