package mf.org.apache.xerces.impl;

import java.io.IOException;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.util.XMLStringBuffer;
import mf.org.apache.xerces.util.XMLSymbols;
import mf.org.apache.xerces.xni.Augmentations;
import mf.org.apache.xerces.xni.XMLDTDContentModelHandler;
import mf.org.apache.xerces.xni.XMLDTDHandler;
import mf.org.apache.xerces.xni.XMLResourceIdentifier;
import mf.org.apache.xerces.xni.XMLString;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.parser.XMLComponent;
import mf.org.apache.xerces.xni.parser.XMLComponentManager;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;
import mf.org.apache.xerces.xni.parser.XMLDTDScanner;
import mf.org.apache.xerces.xni.parser.XMLInputSource;

public class XMLDTDScannerImpl extends XMLScanner implements XMLDTDScanner, XMLComponent, XMLEntityHandler {
    private static final boolean DEBUG_SCANNER_STATE = false;
    private static final Boolean[] FEATURE_DEFAULTS;
    private static final Object[] PROPERTY_DEFAULTS = new Object[3];
    private static final String[] RECOGNIZED_FEATURES = new String[]{"http://xml.org/sax/features/validation", "http://apache.org/xml/features/scanner/notify-char-refs"};
    private static final String[] RECOGNIZED_PROPERTIES = new String[]{"http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/entity-manager"};
    protected static final int SCANNER_STATE_END_OF_INPUT = 0;
    protected static final int SCANNER_STATE_MARKUP_DECL = 2;
    protected static final int SCANNER_STATE_TEXT_DECL = 1;
    private int fContentDepth;
    private int[] fContentStack;
    protected XMLDTDContentModelHandler fDTDContentModelHandler;
    protected XMLDTDHandler fDTDHandler;
    private String[] fEnumeration;
    private int fEnumerationCount;
    private int fExtEntityDepth;
    private final XMLStringBuffer fIgnoreConditionalBuffer;
    private int fIncludeSectDepth;
    private final XMLString fLiteral;
    private final XMLString fLiteral2;
    private int fMarkUpDepth;
    private int fPEDepth;
    private boolean[] fPEReport;
    private int[] fPEStack;
    protected int fScannerState;
    protected boolean fSeenExternalDTD;
    protected boolean fSeenPEReferences;
    protected boolean fStandalone;
    private boolean fStartDTDCalled;
    private final XMLString fString;
    private final XMLStringBuffer fStringBuffer;
    private final XMLStringBuffer fStringBuffer2;
    private final String[] fStrings;

    static {
        Boolean[] boolArr = new Boolean[2];
        boolArr[1] = Boolean.FALSE;
        FEATURE_DEFAULTS = boolArr;
    }

    public XMLDTDScannerImpl() {
        this.fContentStack = new int[5];
        this.fPEStack = new int[5];
        this.fPEReport = new boolean[5];
        this.fStrings = new String[3];
        this.fString = new XMLString();
        this.fStringBuffer = new XMLStringBuffer();
        this.fStringBuffer2 = new XMLStringBuffer();
        this.fLiteral = new XMLString();
        this.fLiteral2 = new XMLString();
        this.fEnumeration = new String[5];
        this.fIgnoreConditionalBuffer = new XMLStringBuffer(128);
    }

    public XMLDTDScannerImpl(SymbolTable symbolTable, XMLErrorReporter errorReporter, XMLEntityManager entityManager) {
        this.fContentStack = new int[5];
        this.fPEStack = new int[5];
        this.fPEReport = new boolean[5];
        this.fStrings = new String[3];
        this.fString = new XMLString();
        this.fStringBuffer = new XMLStringBuffer();
        this.fStringBuffer2 = new XMLStringBuffer();
        this.fLiteral = new XMLString();
        this.fLiteral2 = new XMLString();
        this.fEnumeration = new String[5];
        this.fIgnoreConditionalBuffer = new XMLStringBuffer(128);
        this.fSymbolTable = symbolTable;
        this.fErrorReporter = errorReporter;
        this.fEntityManager = entityManager;
        entityManager.setProperty("http://apache.org/xml/properties/internal/symbol-table", this.fSymbolTable);
    }

    public void setInputSource(XMLInputSource inputSource) throws IOException {
        if (inputSource != null) {
            this.fEntityManager.setEntityHandler(this);
            this.fEntityManager.startDTDEntity(inputSource);
        } else if (this.fDTDHandler != null) {
            this.fDTDHandler.startDTD(null, null);
            this.fDTDHandler.endDTD(null);
        }
    }

    public boolean scanDTDExternalSubset(boolean complete) throws IOException, XNIException {
        this.fEntityManager.setEntityHandler(this);
        if (this.fScannerState == 1) {
            this.fSeenExternalDTD = true;
            boolean textDecl = scanTextDecl();
            if (this.fScannerState == 0) {
                return false;
            }
            setScannerState(2);
            if (textDecl && !complete) {
                return true;
            }
        }
        while (scanDecls(complete)) {
            if (!complete) {
                return true;
            }
        }
        return false;
    }

    public boolean scanDTDInternalSubset(boolean complete, boolean standalone, boolean hasExternalSubset) throws IOException, XNIException {
        this.fEntityScanner = this.fEntityManager.getEntityScanner();
        this.fEntityManager.setEntityHandler(this);
        this.fStandalone = standalone;
        if (this.fScannerState == 1) {
            if (this.fDTDHandler != null) {
                this.fDTDHandler.startDTD(this.fEntityScanner, null);
                this.fStartDTDCalled = true;
            }
            setScannerState(2);
        }
        while (scanDecls(complete)) {
            if (!complete) {
                return true;
            }
        }
        if (!(this.fDTDHandler == null || hasExternalSubset)) {
            this.fDTDHandler.endDTD(null);
        }
        setScannerState(1);
        return false;
    }

    public void reset(XMLComponentManager componentManager) throws XMLConfigurationException {
        super.reset(componentManager);
        init();
    }

    public void reset() {
        super.reset();
        init();
    }

    public String[] getRecognizedFeatures() {
        return (String[]) RECOGNIZED_FEATURES.clone();
    }

    public String[] getRecognizedProperties() {
        return (String[]) RECOGNIZED_PROPERTIES.clone();
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

    public void setDTDHandler(XMLDTDHandler dtdHandler) {
        this.fDTDHandler = dtdHandler;
    }

    public XMLDTDHandler getDTDHandler() {
        return this.fDTDHandler;
    }

    public void setDTDContentModelHandler(XMLDTDContentModelHandler dtdContentModelHandler) {
        this.fDTDContentModelHandler = dtdContentModelHandler;
    }

    public XMLDTDContentModelHandler getDTDContentModelHandler() {
        return this.fDTDContentModelHandler;
    }

    public void startEntity(String name, XMLResourceIdentifier identifier, String encoding, Augmentations augs) throws XNIException {
        super.startEntity(name, identifier, encoding, augs);
        boolean dtdEntity = name.equals("[dtd]");
        if (dtdEntity) {
            if (!(this.fDTDHandler == null || this.fStartDTDCalled)) {
                this.fDTDHandler.startDTD(this.fEntityScanner, null);
            }
            if (this.fDTDHandler != null) {
                this.fDTDHandler.startExternalSubset(identifier, null);
            }
            this.fEntityManager.startExternalSubset();
            this.fExtEntityDepth++;
        } else if (name.charAt(0) == '%') {
            pushPEStack(this.fMarkUpDepth, this.fReportEntity);
            if (this.fEntityScanner.isExternal()) {
                this.fExtEntityDepth++;
            }
        }
        if (this.fDTDHandler != null && !dtdEntity && this.fReportEntity) {
            this.fDTDHandler.startParameterEntity(name, identifier, encoding, augs);
        }
    }

    public void endEntity(String name, Augmentations augs) throws XNIException {
        super.endEntity(name, augs);
        if (this.fScannerState != 0) {
            boolean reportEntity = this.fReportEntity;
            if (name.startsWith("%")) {
                reportEntity = peekReportEntity();
                int startMarkUpDepth = popPEStack();
                if (startMarkUpDepth == 0 && startMarkUpDepth < this.fMarkUpDepth) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "ILL_FORMED_PARAMETER_ENTITY_WHEN_USED_IN_DECL", new Object[]{this.fEntityManager.fCurrentEntity.name}, (short) 2);
                }
                if (startMarkUpDepth != this.fMarkUpDepth) {
                    reportEntity = false;
                    if (this.fValidation) {
                        this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "ImproperDeclarationNesting", new Object[]{name}, (short) 1);
                    }
                }
                if (this.fEntityScanner.isExternal()) {
                    this.fExtEntityDepth--;
                }
                if (this.fDTDHandler != null && reportEntity) {
                    this.fDTDHandler.endParameterEntity(name, augs);
                }
            } else if (name.equals("[dtd]")) {
                if (this.fIncludeSectDepth != 0) {
                    reportFatalError("IncludeSectUnterminated", null);
                }
                this.fScannerState = 0;
                this.fEntityManager.endExternalSubset();
                if (this.fDTDHandler != null) {
                    this.fDTDHandler.endExternalSubset(null);
                    this.fDTDHandler.endDTD(null);
                }
                this.fExtEntityDepth--;
            }
        }
    }

    protected final void setScannerState(int state) {
        this.fScannerState = state;
    }

    private static String getScannerStateName(int state) {
        return "??? (" + state + ')';
    }

    protected final boolean scanningInternalSubset() {
        return this.fExtEntityDepth == 0;
    }

    protected void startPE(String name, boolean literal) throws IOException, XNIException {
        int depth = this.fPEDepth;
        String pName = "%" + name;
        if (!this.fSeenPEReferences) {
            this.fSeenPEReferences = true;
            this.fEntityManager.notifyHasPEReferences();
        }
        if (this.fValidation && !this.fEntityManager.isDeclaredEntity(pName)) {
            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "EntityNotDeclared", new Object[]{name}, (short) 1);
        }
        this.fEntityManager.startEntity(this.fSymbolTable.addSymbol(pName), literal);
        if (depth != this.fPEDepth && this.fEntityScanner.isExternal()) {
            scanTextDecl();
        }
    }

    protected final boolean scanTextDecl() throws IOException, XNIException {
        boolean textDecl = false;
        if (this.fEntityScanner.skipString("<?xml")) {
            this.fMarkUpDepth++;
            if (isValidNameChar(this.fEntityScanner.peekChar())) {
                this.fStringBuffer.clear();
                this.fStringBuffer.append("xml");
                if (this.fNamespaces) {
                    while (isValidNCName(this.fEntityScanner.peekChar())) {
                        this.fStringBuffer.append((char) this.fEntityScanner.scanChar());
                    }
                } else {
                    while (isValidNameChar(this.fEntityScanner.peekChar())) {
                        this.fStringBuffer.append((char) this.fEntityScanner.scanChar());
                    }
                }
                scanPIData(this.fSymbolTable.addSymbol(this.fStringBuffer.ch, this.fStringBuffer.offset, this.fStringBuffer.length), this.fString);
            } else {
                scanXMLDeclOrTextDecl(true, this.fStrings);
                textDecl = true;
                this.fMarkUpDepth--;
                String version = this.fStrings[0];
                String encoding = this.fStrings[1];
                this.fEntityScanner.setXMLVersion(version);
                if (!this.fEntityScanner.fCurrentEntity.isEncodingExternallySpecified()) {
                    this.fEntityScanner.setEncoding(encoding);
                }
                if (this.fDTDHandler != null) {
                    this.fDTDHandler.textDecl(version, encoding, null);
                }
            }
        }
        this.fEntityManager.fCurrentEntity.mayReadChunks = true;
        return textDecl;
    }

    protected final void scanPIData(String target, XMLString data) throws IOException, XNIException {
        super.scanPIData(target, data);
        this.fMarkUpDepth--;
        if (this.fDTDHandler != null) {
            this.fDTDHandler.processingInstruction(target, data, null);
        }
    }

    protected final void scanComment() throws IOException, XNIException {
        this.fReportEntity = false;
        scanComment(this.fStringBuffer);
        this.fMarkUpDepth--;
        if (this.fDTDHandler != null) {
            this.fDTDHandler.comment(this.fStringBuffer, null);
        }
        this.fReportEntity = true;
    }

    protected final void scanElementDecl() throws IOException, XNIException {
        boolean z;
        String contentModel;
        this.fReportEntity = false;
        if (scanningInternalSubset()) {
            z = false;
        } else {
            z = true;
        }
        if (!skipSeparator(true, z)) {
            reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ELEMENT_TYPE_IN_ELEMENTDECL", null);
        }
        String name = this.fEntityScanner.scanName();
        if (name == null) {
            reportFatalError("MSG_ELEMENT_TYPE_REQUIRED_IN_ELEMENTDECL", null);
        }
        if (scanningInternalSubset()) {
            z = false;
        } else {
            z = true;
        }
        if (!skipSeparator(true, z)) {
            reportFatalError("MSG_SPACE_REQUIRED_BEFORE_CONTENTSPEC_IN_ELEMENTDECL", new Object[]{name});
        }
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.startContentModel(name, null);
        }
        this.fReportEntity = true;
        if (this.fEntityScanner.skipString("EMPTY")) {
            contentModel = "EMPTY";
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.empty(null);
            }
        } else if (this.fEntityScanner.skipString("ANY")) {
            contentModel = "ANY";
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.any(null);
            }
        } else {
            if (!this.fEntityScanner.skipChar(40)) {
                reportFatalError("MSG_OPEN_PAREN_OR_ELEMENT_TYPE_REQUIRED_IN_CHILDREN", new Object[]{name});
            }
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.startGroup(null);
            }
            this.fStringBuffer.clear();
            this.fStringBuffer.append('(');
            this.fMarkUpDepth++;
            skipSeparator(false, !scanningInternalSubset());
            if (this.fEntityScanner.skipString("#PCDATA")) {
                scanMixed(name);
            } else {
                scanChildren(name);
            }
            contentModel = this.fStringBuffer.toString();
        }
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.endContentModel(null);
        }
        this.fReportEntity = false;
        skipSeparator(false, !scanningInternalSubset());
        if (!this.fEntityScanner.skipChar(62)) {
            reportFatalError("ElementDeclUnterminated", new Object[]{name});
        }
        this.fReportEntity = true;
        this.fMarkUpDepth--;
        if (this.fDTDHandler != null) {
            this.fDTDHandler.elementDecl(name, contentModel, null);
        }
    }

    private final void scanMixed(String elName) throws IOException, XNIException {
        String childName = null;
        this.fStringBuffer.append("#PCDATA");
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.pcdata(null);
        }
        skipSeparator(false, !scanningInternalSubset());
        while (this.fEntityScanner.skipChar(124)) {
            boolean z;
            this.fStringBuffer.append('|');
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.separator((short) 0, null);
            }
            skipSeparator(false, !scanningInternalSubset());
            childName = this.fEntityScanner.scanName();
            if (childName == null) {
                reportFatalError("MSG_ELEMENT_TYPE_REQUIRED_IN_MIXED_CONTENT", new Object[]{elName});
            }
            this.fStringBuffer.append(childName);
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.element(childName, null);
            }
            if (scanningInternalSubset()) {
                z = false;
            } else {
                z = true;
            }
            skipSeparator(false, z);
        }
        if (this.fEntityScanner.skipString(")*")) {
            this.fStringBuffer.append(")*");
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.endGroup(null);
                this.fDTDContentModelHandler.occurrence((short) 3, null);
            }
        } else if (childName != null) {
            reportFatalError("MixedContentUnterminated", new Object[]{elName});
        } else if (this.fEntityScanner.skipChar(41)) {
            this.fStringBuffer.append(')');
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.endGroup(null);
            }
        } else {
            reportFatalError("MSG_CLOSE_PAREN_REQUIRED_IN_CHILDREN", new Object[]{elName});
        }
        this.fMarkUpDepth--;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void scanChildren(java.lang.String r12) throws java.io.IOException, mf.org.apache.xerces.xni.XNIException {
        /*
        r11 = this;
        r10 = 124; // 0x7c float:1.74E-43 double:6.13E-322;
        r9 = 44;
        r6 = 1;
        r8 = 0;
        r5 = 0;
        r11.fContentDepth = r5;
        r11.pushContentStack(r5);
        r2 = 0;
    L_0x000d:
        r4 = r11.fEntityScanner;
        r7 = 40;
        r4 = r4.skipChar(r7);
        if (r4 == 0) goto L_0x003e;
    L_0x0017:
        r4 = r11.fMarkUpDepth;
        r4 = r4 + 1;
        r11.fMarkUpDepth = r4;
        r4 = r11.fStringBuffer;
        r7 = 40;
        r4.append(r7);
        r4 = r11.fDTDContentModelHandler;
        if (r4 == 0) goto L_0x002d;
    L_0x0028:
        r4 = r11.fDTDContentModelHandler;
        r4.startGroup(r8);
    L_0x002d:
        r11.pushContentStack(r2);
        r2 = 0;
        r4 = r11.scanningInternalSubset();
        if (r4 == 0) goto L_0x003c;
    L_0x0037:
        r4 = r5;
    L_0x0038:
        r11.skipSeparator(r5, r4);
        goto L_0x000d;
    L_0x003c:
        r4 = r6;
        goto L_0x0038;
    L_0x003e:
        r4 = r11.scanningInternalSubset();
        if (r4 == 0) goto L_0x005b;
    L_0x0044:
        r4 = r5;
    L_0x0045:
        r11.skipSeparator(r5, r4);
        r4 = r11.fEntityScanner;
        r1 = r4.scanName();
        if (r1 != 0) goto L_0x005d;
    L_0x0050:
        r4 = "MSG_OPEN_PAREN_OR_ELEMENT_TYPE_REQUIRED_IN_CHILDREN";
        r6 = new java.lang.Object[r6];
        r6[r5] = r12;
        r11.reportFatalError(r4, r6);
    L_0x005a:
        return;
    L_0x005b:
        r4 = r6;
        goto L_0x0045;
    L_0x005d:
        r4 = r11.fDTDContentModelHandler;
        if (r4 == 0) goto L_0x0066;
    L_0x0061:
        r4 = r11.fDTDContentModelHandler;
        r4.element(r1, r8);
    L_0x0066:
        r4 = r11.fStringBuffer;
        r4.append(r1);
        r4 = r11.fEntityScanner;
        r0 = r4.peekChar();
        r4 = 63;
        if (r0 == r4) goto L_0x007d;
    L_0x0075:
        r4 = 42;
        if (r0 == r4) goto L_0x007d;
    L_0x0079:
        r4 = 43;
        if (r0 != r4) goto L_0x0096;
    L_0x007d:
        r4 = r11.fDTDContentModelHandler;
        if (r4 == 0) goto L_0x008b;
    L_0x0081:
        r4 = 63;
        if (r0 != r4) goto L_0x00ca;
    L_0x0085:
        r3 = 2;
    L_0x0086:
        r4 = r11.fDTDContentModelHandler;
        r4.occurrence(r3, r8);
    L_0x008b:
        r4 = r11.fEntityScanner;
        r4.scanChar();
        r4 = r11.fStringBuffer;
        r7 = (char) r0;
        r4.append(r7);
    L_0x0096:
        r4 = r11.scanningInternalSubset();
        if (r4 == 0) goto L_0x00d2;
    L_0x009c:
        r4 = r5;
    L_0x009d:
        r11.skipSeparator(r5, r4);
        r4 = r11.fEntityScanner;
        r0 = r4.peekChar();
        if (r0 != r9) goto L_0x00d4;
    L_0x00a8:
        if (r2 == r10) goto L_0x00d4;
    L_0x00aa:
        r2 = r0;
        r4 = r11.fDTDContentModelHandler;
        if (r4 == 0) goto L_0x00b4;
    L_0x00af:
        r4 = r11.fDTDContentModelHandler;
        r4.separator(r6, r8);
    L_0x00b4:
        r4 = r11.fEntityScanner;
        r4.scanChar();
        r4 = r11.fStringBuffer;
        r4.append(r9);
    L_0x00be:
        r4 = r11.scanningInternalSubset();
        if (r4 == 0) goto L_0x017a;
    L_0x00c4:
        r4 = r5;
    L_0x00c5:
        r11.skipSeparator(r5, r4);
        goto L_0x000d;
    L_0x00ca:
        r4 = 42;
        if (r0 != r4) goto L_0x00d0;
    L_0x00ce:
        r3 = 3;
        goto L_0x0086;
    L_0x00d0:
        r3 = 4;
        goto L_0x0086;
    L_0x00d2:
        r4 = r6;
        goto L_0x009d;
    L_0x00d4:
        if (r0 != r10) goto L_0x00ed;
    L_0x00d6:
        if (r2 == r9) goto L_0x00ed;
    L_0x00d8:
        r2 = r0;
        r4 = r11.fDTDContentModelHandler;
        if (r4 == 0) goto L_0x00e2;
    L_0x00dd:
        r4 = r11.fDTDContentModelHandler;
        r4.separator(r5, r8);
    L_0x00e2:
        r4 = r11.fEntityScanner;
        r4.scanChar();
        r4 = r11.fStringBuffer;
        r4.append(r10);
        goto L_0x00be;
    L_0x00ed:
        r4 = 41;
        if (r0 == r4) goto L_0x00fb;
    L_0x00f1:
        r4 = "MSG_CLOSE_PAREN_REQUIRED_IN_CHILDREN";
        r7 = new java.lang.Object[r6];
        r7[r5] = r12;
        r11.reportFatalError(r4, r7);
    L_0x00fb:
        r4 = r11.fDTDContentModelHandler;
        if (r4 == 0) goto L_0x0104;
    L_0x00ff:
        r4 = r11.fDTDContentModelHandler;
        r4.endGroup(r8);
    L_0x0104:
        r2 = r11.popContentStack();
        r4 = r11.fEntityScanner;
        r7 = ")?";
        r4 = r4.skipString(r7);
        if (r4 == 0) goto L_0x0131;
    L_0x0113:
        r4 = r11.fStringBuffer;
        r7 = ")?";
        r4.append(r7);
        r4 = r11.fDTDContentModelHandler;
        if (r4 == 0) goto L_0x0125;
    L_0x011f:
        r3 = 2;
        r4 = r11.fDTDContentModelHandler;
        r4.occurrence(r3, r8);
    L_0x0125:
        r4 = r11.fMarkUpDepth;
        r4 = r4 + -1;
        r11.fMarkUpDepth = r4;
        r4 = r11.fContentDepth;
        if (r4 != 0) goto L_0x0096;
    L_0x012f:
        goto L_0x005a;
    L_0x0131:
        r4 = r11.fEntityScanner;
        r7 = ")+";
        r4 = r4.skipString(r7);
        if (r4 == 0) goto L_0x014f;
    L_0x013c:
        r4 = r11.fStringBuffer;
        r7 = ")+";
        r4.append(r7);
        r4 = r11.fDTDContentModelHandler;
        if (r4 == 0) goto L_0x0125;
    L_0x0148:
        r3 = 4;
        r4 = r11.fDTDContentModelHandler;
        r4.occurrence(r3, r8);
        goto L_0x0125;
    L_0x014f:
        r4 = r11.fEntityScanner;
        r7 = ")*";
        r4 = r4.skipString(r7);
        if (r4 == 0) goto L_0x016d;
    L_0x015a:
        r4 = r11.fStringBuffer;
        r7 = ")*";
        r4.append(r7);
        r4 = r11.fDTDContentModelHandler;
        if (r4 == 0) goto L_0x0125;
    L_0x0166:
        r3 = 3;
        r4 = r11.fDTDContentModelHandler;
        r4.occurrence(r3, r8);
        goto L_0x0125;
    L_0x016d:
        r4 = r11.fEntityScanner;
        r4.scanChar();
        r4 = r11.fStringBuffer;
        r7 = 41;
        r4.append(r7);
        goto L_0x0125;
    L_0x017a:
        r4 = r6;
        goto L_0x00c5;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.XMLDTDScannerImpl.scanChildren(java.lang.String):void");
    }

    protected final void scanAttlistDecl() throws IOException, XNIException {
        this.fReportEntity = false;
        if (!skipSeparator(true, !scanningInternalSubset())) {
            reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ELEMENT_TYPE_IN_ATTLISTDECL", null);
        }
        String elName = this.fEntityScanner.scanName();
        if (elName == null) {
            reportFatalError("MSG_ELEMENT_TYPE_REQUIRED_IN_ATTLISTDECL", null);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.startAttlist(elName, null);
        }
        if (!skipSeparator(true, !scanningInternalSubset())) {
            if (this.fEntityScanner.skipChar(62)) {
                if (this.fDTDHandler != null) {
                    this.fDTDHandler.endAttlist(null);
                }
                this.fMarkUpDepth--;
                return;
            }
            reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ATTRIBUTE_NAME_IN_ATTDEF", new Object[]{elName});
        }
        while (!this.fEntityScanner.skipChar(62)) {
            boolean z;
            String name = this.fEntityScanner.scanName();
            if (name == null) {
                reportFatalError("AttNameRequiredInAttDef", new Object[]{elName});
            }
            if (!skipSeparator(true, !scanningInternalSubset())) {
                reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ATTTYPE_IN_ATTDEF", new Object[]{elName, name});
            }
            String type = scanAttType(elName, name);
            if (!skipSeparator(true, !scanningInternalSubset())) {
                reportFatalError("MSG_SPACE_REQUIRED_BEFORE_DEFAULTDECL_IN_ATTDEF", new Object[]{elName, name});
            }
            String defaultType = scanAttDefaultDecl(elName, name, type, this.fLiteral, this.fLiteral2);
            if (this.fDTDHandler != null) {
                String[] enumeration = null;
                if (this.fEnumerationCount != 0) {
                    enumeration = new String[this.fEnumerationCount];
                    System.arraycopy(this.fEnumeration, 0, enumeration, 0, this.fEnumerationCount);
                }
                if (defaultType == null || !(defaultType.equals("#REQUIRED") || defaultType.equals("#IMPLIED"))) {
                    this.fDTDHandler.attributeDecl(elName, name, type, enumeration, defaultType, this.fLiteral, this.fLiteral2, null);
                } else {
                    this.fDTDHandler.attributeDecl(elName, name, type, enumeration, defaultType, null, null, null);
                }
            }
            if (scanningInternalSubset()) {
                z = false;
            } else {
                z = true;
            }
            skipSeparator(false, z);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.endAttlist(null);
        }
        this.fMarkUpDepth--;
        this.fReportEntity = true;
    }

    private final String scanAttType(String elName, String atName) throws IOException, XNIException {
        this.fEnumerationCount = 0;
        if (this.fEntityScanner.skipString("CDATA")) {
            return "CDATA";
        }
        if (this.fEntityScanner.skipString(SchemaSymbols.ATTVAL_IDREFS)) {
            return SchemaSymbols.ATTVAL_IDREFS;
        }
        if (this.fEntityScanner.skipString(SchemaSymbols.ATTVAL_IDREF)) {
            return SchemaSymbols.ATTVAL_IDREF;
        }
        if (this.fEntityScanner.skipString(SchemaSymbols.ATTVAL_ID)) {
            return SchemaSymbols.ATTVAL_ID;
        }
        if (this.fEntityScanner.skipString(SchemaSymbols.ATTVAL_ENTITY)) {
            return SchemaSymbols.ATTVAL_ENTITY;
        }
        if (this.fEntityScanner.skipString(SchemaSymbols.ATTVAL_ENTITIES)) {
            return SchemaSymbols.ATTVAL_ENTITIES;
        }
        if (this.fEntityScanner.skipString(SchemaSymbols.ATTVAL_NMTOKENS)) {
            return SchemaSymbols.ATTVAL_NMTOKENS;
        }
        if (this.fEntityScanner.skipString(SchemaSymbols.ATTVAL_NMTOKEN)) {
            return SchemaSymbols.ATTVAL_NMTOKEN;
        }
        boolean z;
        int c;
        if (this.fEntityScanner.skipString(SchemaSymbols.ATTVAL_NOTATION)) {
            String type = SchemaSymbols.ATTVAL_NOTATION;
            if (scanningInternalSubset()) {
                z = false;
            } else {
                z = true;
            }
            if (!skipSeparator(true, z)) {
                reportFatalError("MSG_SPACE_REQUIRED_AFTER_NOTATION_IN_NOTATIONTYPE", new Object[]{elName, atName});
            }
            if (this.fEntityScanner.scanChar() != 40) {
                reportFatalError("MSG_OPEN_PAREN_REQUIRED_IN_NOTATIONTYPE", new Object[]{elName, atName});
            }
            this.fMarkUpDepth++;
            do {
                if (scanningInternalSubset()) {
                    z = false;
                } else {
                    z = true;
                }
                skipSeparator(false, z);
                String aName = this.fEntityScanner.scanName();
                if (aName == null) {
                    reportFatalError("MSG_NAME_REQUIRED_IN_NOTATIONTYPE", new Object[]{elName, atName});
                    c = skipInvalidEnumerationValue();
                    if (c != 124) {
                        break;
                    }
                } else {
                    ensureEnumerationSize(this.fEnumerationCount + 1);
                    String[] strArr = this.fEnumeration;
                    int i = this.fEnumerationCount;
                    this.fEnumerationCount = i + 1;
                    strArr[i] = aName;
                    if (scanningInternalSubset()) {
                        z = false;
                    } else {
                        z = true;
                    }
                    skipSeparator(false, z);
                    c = this.fEntityScanner.scanChar();
                    continue;
                }
            } while (c == 124);
            if (c != 41) {
                reportFatalError("NotationTypeUnterminated", new Object[]{elName, atName});
            }
            this.fMarkUpDepth--;
            return type;
        }
        type = "ENUMERATION";
        if (this.fEntityScanner.scanChar() != 40) {
            reportFatalError("AttTypeRequiredInAttDef", new Object[]{elName, atName});
        }
        this.fMarkUpDepth++;
        do {
            if (scanningInternalSubset()) {
                z = false;
            } else {
                z = true;
            }
            skipSeparator(false, z);
            String token = this.fEntityScanner.scanNmtoken();
            if (token == null) {
                reportFatalError("MSG_NMTOKEN_REQUIRED_IN_ENUMERATION", new Object[]{elName, atName});
                c = skipInvalidEnumerationValue();
                if (c != 124) {
                    break;
                }
            } else {
                ensureEnumerationSize(this.fEnumerationCount + 1);
                strArr = this.fEnumeration;
                i = this.fEnumerationCount;
                this.fEnumerationCount = i + 1;
                strArr[i] = token;
                if (scanningInternalSubset()) {
                    z = false;
                } else {
                    z = true;
                }
                skipSeparator(false, z);
                c = this.fEntityScanner.scanChar();
                continue;
            }
        } while (c == 124);
        if (c != 41) {
            reportFatalError("EnumerationUnterminated", new Object[]{elName, atName});
        }
        this.fMarkUpDepth--;
        return type;
    }

    protected final String scanAttDefaultDecl(String elName, String atName, String type, XMLString defaultVal, XMLString nonNormalizedDefaultVal) throws IOException, XNIException {
        String defaultType = null;
        this.fString.clear();
        defaultVal.clear();
        if (this.fEntityScanner.skipString("#REQUIRED")) {
            return "#REQUIRED";
        }
        if (this.fEntityScanner.skipString("#IMPLIED")) {
            return "#IMPLIED";
        }
        boolean isVC;
        if (this.fEntityScanner.skipString("#FIXED")) {
            boolean z;
            defaultType = "#FIXED";
            if (scanningInternalSubset()) {
                z = false;
            } else {
                z = true;
            }
            if (!skipSeparator(true, z)) {
                reportFatalError("MSG_SPACE_REQUIRED_AFTER_FIXED_IN_DEFAULTDECL", new Object[]{elName, atName});
            }
        }
        if (this.fStandalone || !(this.fSeenExternalDTD || this.fSeenPEReferences)) {
            isVC = false;
        } else {
            isVC = true;
        }
        scanAttributeValue(defaultVal, nonNormalizedDefaultVal, atName, isVC, elName);
        return defaultType;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void scanEntityDecl() throws java.io.IOException, mf.org.apache.xerces.xni.XNIException {
        /*
        r15 = this;
        r7 = 0;
        r9 = 0;
        r0 = 0;
        r15.fReportEntity = r0;
        r0 = r15.fEntityScanner;
        r0 = r0.skipSpaces();
        if (r0 == 0) goto L_0x01eb;
    L_0x000d:
        r0 = r15.fEntityScanner;
        r12 = 37;
        r0 = r0.skipChar(r12);
        if (r0 != 0) goto L_0x01ab;
    L_0x0017:
        r7 = 0;
    L_0x0018:
        if (r9 == 0) goto L_0x0038;
    L_0x001a:
        r0 = r15.fEntityScanner;
        r8 = r0.scanName();
        if (r8 != 0) goto L_0x021a;
    L_0x0022:
        r0 = "NameRequiredInPEReference";
        r12 = 0;
        r15.reportFatalError(r0, r12);
    L_0x0029:
        r0 = r15.fEntityScanner;
        r0.skipSpaces();
        r0 = r15.fEntityScanner;
        r12 = 37;
        r0 = r0.skipChar(r12);
        if (r0 != 0) goto L_0x0238;
    L_0x0038:
        r1 = 0;
        r0 = r15.fNamespaces;
        if (r0 == 0) goto L_0x0257;
    L_0x003d:
        r0 = r15.fEntityScanner;
        r1 = r0.scanNCName();
    L_0x0043:
        if (r1 != 0) goto L_0x004c;
    L_0x0045:
        r0 = "MSG_ENTITY_NAME_REQUIRED_IN_ENTITYDECL";
        r12 = 0;
        r15.reportFatalError(r0, r12);
    L_0x004c:
        r12 = 1;
        r0 = r15.scanningInternalSubset();
        if (r0 == 0) goto L_0x025f;
    L_0x0053:
        r0 = 0;
    L_0x0054:
        r0 = r15.skipSeparator(r12, r0);
        if (r0 != 0) goto L_0x00ac;
    L_0x005a:
        r0 = r15.fNamespaces;
        if (r0 == 0) goto L_0x0265;
    L_0x005e:
        r0 = r15.fEntityScanner;
        r0 = r0.peekChar();
        r12 = 58;
        if (r0 != r12) goto L_0x0265;
    L_0x0068:
        r0 = r15.fEntityScanner;
        r0.scanChar();
        r6 = new mf.org.apache.xerces.util.XMLStringBuffer;
        r6.<init>(r1);
        r0 = 58;
        r6.append(r0);
        r0 = r15.fEntityScanner;
        r11 = r0.scanName();
        if (r11 == 0) goto L_0x0082;
    L_0x007f:
        r6.append(r11);
    L_0x0082:
        r0 = "ColonNotLegalWithNS";
        r12 = 1;
        r12 = new java.lang.Object[r12];
        r13 = 0;
        r14 = r6.toString();
        r12[r13] = r14;
        r15.reportFatalError(r0, r12);
        r12 = 1;
        r0 = r15.scanningInternalSubset();
        if (r0 == 0) goto L_0x0262;
    L_0x0099:
        r0 = 0;
    L_0x009a:
        r0 = r15.skipSeparator(r12, r0);
        if (r0 != 0) goto L_0x00ac;
    L_0x00a0:
        r0 = "MSG_SPACE_REQUIRED_AFTER_ENTITY_NAME_IN_ENTITYDECL";
        r12 = 1;
        r12 = new java.lang.Object[r12];
        r13 = 0;
        r12[r13] = r1;
        r15.reportFatalError(r0, r12);
    L_0x00ac:
        r0 = r15.fStrings;
        r12 = 0;
        r15.scanExternalID(r0, r12);
        r0 = r15.fStrings;
        r12 = 0;
        r3 = r0[r12];
        r0 = r15.fStrings;
        r12 = 1;
        r2 = r0[r12];
        r5 = 0;
        r12 = 1;
        r0 = r15.scanningInternalSubset();
        if (r0 == 0) goto L_0x0273;
    L_0x00c4:
        r0 = 0;
    L_0x00c5:
        r10 = r15.skipSeparator(r12, r0);
        if (r7 != 0) goto L_0x0112;
    L_0x00cb:
        r0 = r15.fEntityScanner;
        r12 = "NDATA";
        r0 = r0.skipString(r12);
        if (r0 == 0) goto L_0x0112;
    L_0x00d6:
        if (r10 != 0) goto L_0x00e4;
    L_0x00d8:
        r0 = "MSG_SPACE_REQUIRED_BEFORE_NDATA_IN_UNPARSED_ENTITYDECL";
        r12 = 1;
        r12 = new java.lang.Object[r12];
        r13 = 0;
        r12[r13] = r1;
        r15.reportFatalError(r0, r12);
    L_0x00e4:
        r12 = 1;
        r0 = r15.scanningInternalSubset();
        if (r0 == 0) goto L_0x0276;
    L_0x00eb:
        r0 = 0;
    L_0x00ec:
        r0 = r15.skipSeparator(r12, r0);
        if (r0 != 0) goto L_0x00fe;
    L_0x00f2:
        r0 = "MSG_SPACE_REQUIRED_BEFORE_NOTATION_NAME_IN_UNPARSED_ENTITYDECL";
        r12 = 1;
        r12 = new java.lang.Object[r12];
        r13 = 0;
        r12[r13] = r1;
        r15.reportFatalError(r0, r12);
    L_0x00fe:
        r0 = r15.fEntityScanner;
        r5 = r0.scanName();
        if (r5 != 0) goto L_0x0112;
    L_0x0106:
        r0 = "MSG_NOTATION_NAME_REQUIRED_FOR_UNPARSED_ENTITYDECL";
        r12 = 1;
        r12 = new java.lang.Object[r12];
        r13 = 0;
        r12[r13] = r1;
        r15.reportFatalError(r0, r12);
    L_0x0112:
        if (r3 != 0) goto L_0x0147;
    L_0x0114:
        r0 = r15.fLiteral;
        r12 = r15.fLiteral2;
        r15.scanEntityValue(r0, r12);
        r0 = r15.fStringBuffer;
        r0.clear();
        r0 = r15.fStringBuffer2;
        r0.clear();
        r0 = r15.fStringBuffer;
        r12 = r15.fLiteral;
        r12 = r12.ch;
        r13 = r15.fLiteral;
        r13 = r13.offset;
        r14 = r15.fLiteral;
        r14 = r14.length;
        r0.append(r12, r13, r14);
        r0 = r15.fStringBuffer2;
        r12 = r15.fLiteral2;
        r12 = r12.ch;
        r13 = r15.fLiteral2;
        r13 = r13.offset;
        r14 = r15.fLiteral2;
        r14 = r14.length;
        r0.append(r12, r13, r14);
    L_0x0147:
        r12 = 0;
        r0 = r15.scanningInternalSubset();
        if (r0 == 0) goto L_0x0279;
    L_0x014e:
        r0 = 0;
    L_0x014f:
        r15.skipSeparator(r12, r0);
        r0 = r15.fEntityScanner;
        r12 = 62;
        r0 = r0.skipChar(r12);
        if (r0 != 0) goto L_0x0168;
    L_0x015c:
        r0 = "EntityDeclUnterminated";
        r12 = 1;
        r12 = new java.lang.Object[r12];
        r13 = 0;
        r12[r13] = r1;
        r15.reportFatalError(r0, r12);
    L_0x0168:
        r0 = r15.fMarkUpDepth;
        r0 = r0 + -1;
        r15.fMarkUpDepth = r0;
        if (r7 == 0) goto L_0x0180;
    L_0x0170:
        r0 = new java.lang.StringBuilder;
        r12 = "%";
        r0.<init>(r12);
        r0 = r0.append(r1);
        r1 = r0.toString();
    L_0x0180:
        if (r3 == 0) goto L_0x028d;
    L_0x0182:
        r0 = r15.fEntityScanner;
        r4 = r0.getBaseSystemId();
        if (r5 == 0) goto L_0x027c;
    L_0x018a:
        r0 = r15.fEntityManager;
        r0.addUnparsedEntity(r1, r2, r3, r4, r5);
    L_0x018f:
        r0 = r15.fDTDHandler;
        if (r0 == 0) goto L_0x01a7;
    L_0x0193:
        r0 = r15.fResourceIdentifier;
        r12 = 0;
        r12 = mf.org.apache.xerces.impl.XMLEntityManager.expandSystemId(r3, r4, r12);
        r0.setValues(r2, r3, r4, r12);
        if (r5 == 0) goto L_0x0283;
    L_0x019f:
        r0 = r15.fDTDHandler;
        r12 = r15.fResourceIdentifier;
        r13 = 0;
        r0.unparsedEntityDecl(r1, r12, r5, r13);
    L_0x01a7:
        r0 = 1;
        r15.fReportEntity = r0;
        return;
    L_0x01ab:
        r12 = 1;
        r0 = r15.scanningInternalSubset();
        if (r0 == 0) goto L_0x01bc;
    L_0x01b2:
        r0 = 0;
    L_0x01b3:
        r0 = r15.skipSeparator(r12, r0);
        if (r0 == 0) goto L_0x01be;
    L_0x01b9:
        r7 = 1;
        goto L_0x0018;
    L_0x01bc:
        r0 = 1;
        goto L_0x01b3;
    L_0x01be:
        r0 = r15.scanningInternalSubset();
        if (r0 == 0) goto L_0x01ce;
    L_0x01c4:
        r0 = "MSG_SPACE_REQUIRED_BEFORE_ENTITY_NAME_IN_PEDECL";
        r12 = 0;
        r15.reportFatalError(r0, r12);
        r7 = 1;
        goto L_0x0018;
    L_0x01ce:
        r0 = r15.fEntityScanner;
        r0 = r0.peekChar();
        r12 = 37;
        if (r0 != r12) goto L_0x01e8;
    L_0x01d8:
        r12 = 0;
        r0 = r15.scanningInternalSubset();
        if (r0 == 0) goto L_0x01e6;
    L_0x01df:
        r0 = 0;
    L_0x01e0:
        r15.skipSeparator(r12, r0);
        r7 = 1;
        goto L_0x0018;
    L_0x01e6:
        r0 = 1;
        goto L_0x01e0;
    L_0x01e8:
        r9 = 1;
        goto L_0x0018;
    L_0x01eb:
        r0 = r15.scanningInternalSubset();
        if (r0 != 0) goto L_0x01fb;
    L_0x01f1:
        r0 = r15.fEntityScanner;
        r12 = 37;
        r0 = r0.skipChar(r12);
        if (r0 != 0) goto L_0x0205;
    L_0x01fb:
        r0 = "MSG_SPACE_REQUIRED_BEFORE_ENTITY_NAME_IN_ENTITYDECL";
        r12 = 0;
        r15.reportFatalError(r0, r12);
        r7 = 0;
        goto L_0x0018;
    L_0x0205:
        r0 = r15.fEntityScanner;
        r0 = r0.skipSpaces();
        if (r0 == 0) goto L_0x0217;
    L_0x020d:
        r0 = "MSG_SPACE_REQUIRED_BEFORE_PERCENT_IN_PEDECL";
        r12 = 0;
        r15.reportFatalError(r0, r12);
        r7 = 0;
        goto L_0x0018;
    L_0x0217:
        r9 = 1;
        goto L_0x0018;
    L_0x021a:
        r0 = r15.fEntityScanner;
        r12 = 59;
        r0 = r0.skipChar(r12);
        if (r0 != 0) goto L_0x0232;
    L_0x0224:
        r0 = "SemicolonRequiredInPEReference";
        r12 = 1;
        r12 = new java.lang.Object[r12];
        r13 = 0;
        r12[r13] = r8;
        r15.reportFatalError(r0, r12);
        goto L_0x0029;
    L_0x0232:
        r0 = 0;
        r15.startPE(r8, r0);
        goto L_0x0029;
    L_0x0238:
        if (r7 != 0) goto L_0x001a;
    L_0x023a:
        r12 = 1;
        r0 = r15.scanningInternalSubset();
        if (r0 == 0) goto L_0x024b;
    L_0x0241:
        r0 = 0;
    L_0x0242:
        r0 = r15.skipSeparator(r12, r0);
        if (r0 == 0) goto L_0x024d;
    L_0x0248:
        r7 = 1;
        goto L_0x0038;
    L_0x024b:
        r0 = 1;
        goto L_0x0242;
    L_0x024d:
        r0 = r15.fEntityScanner;
        r12 = 37;
        r7 = r0.skipChar(r12);
        goto L_0x001a;
    L_0x0257:
        r0 = r15.fEntityScanner;
        r1 = r0.scanName();
        goto L_0x0043;
    L_0x025f:
        r0 = 1;
        goto L_0x0054;
    L_0x0262:
        r0 = 1;
        goto L_0x009a;
    L_0x0265:
        r0 = "MSG_SPACE_REQUIRED_AFTER_ENTITY_NAME_IN_ENTITYDECL";
        r12 = 1;
        r12 = new java.lang.Object[r12];
        r13 = 0;
        r12[r13] = r1;
        r15.reportFatalError(r0, r12);
        goto L_0x00ac;
    L_0x0273:
        r0 = 1;
        goto L_0x00c5;
    L_0x0276:
        r0 = 1;
        goto L_0x00ec;
    L_0x0279:
        r0 = 1;
        goto L_0x014f;
    L_0x027c:
        r0 = r15.fEntityManager;
        r0.addExternalEntity(r1, r2, r3, r4);
        goto L_0x018f;
    L_0x0283:
        r0 = r15.fDTDHandler;
        r12 = r15.fResourceIdentifier;
        r13 = 0;
        r0.externalEntityDecl(r1, r12, r13);
        goto L_0x01a7;
    L_0x028d:
        r0 = r15.fEntityManager;
        r12 = r15.fStringBuffer;
        r12 = r12.toString();
        r0.addInternalEntity(r1, r12);
        r0 = r15.fDTDHandler;
        if (r0 == 0) goto L_0x01a7;
    L_0x029c:
        r0 = r15.fDTDHandler;
        r12 = r15.fStringBuffer;
        r13 = r15.fStringBuffer2;
        r14 = 0;
        r0.internalEntityDecl(r1, r12, r13, r14);
        goto L_0x01a7;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.XMLDTDScannerImpl.scanEntityDecl():void");
    }

    protected final void scanEntityValue(XMLString value, XMLString nonNormalizedValue) throws IOException, XNIException {
        int quote = this.fEntityScanner.scanChar();
        if (!(quote == 39 || quote == 34)) {
            reportFatalError("OpenQuoteMissingInDecl", null);
        }
        int entityDepth = this.fEntityDepth;
        XMLString literal = this.fString;
        XMLString literal2 = this.fString;
        if (this.fEntityScanner.scanLiteral(quote, this.fString) != quote) {
            this.fStringBuffer.clear();
            this.fStringBuffer2.clear();
            do {
                this.fStringBuffer.append(this.fString);
                this.fStringBuffer2.append(this.fString);
                if (this.fEntityScanner.skipChar(38)) {
                    if (this.fEntityScanner.skipChar(35)) {
                        this.fStringBuffer2.append("&#");
                        scanCharReferenceValue(this.fStringBuffer, this.fStringBuffer2);
                    } else {
                        this.fStringBuffer.append('&');
                        this.fStringBuffer2.append('&');
                        String eName = this.fEntityScanner.scanName();
                        if (eName == null) {
                            reportFatalError("NameRequiredInReference", null);
                        } else {
                            this.fStringBuffer.append(eName);
                            this.fStringBuffer2.append(eName);
                        }
                        if (this.fEntityScanner.skipChar(59)) {
                            this.fStringBuffer.append(';');
                            this.fStringBuffer2.append(';');
                        } else {
                            reportFatalError("SemicolonRequiredInReference", new Object[]{eName});
                        }
                    }
                } else if (this.fEntityScanner.skipChar(37)) {
                    do {
                        this.fStringBuffer2.append('%');
                        String peName = this.fEntityScanner.scanName();
                        if (peName == null) {
                            reportFatalError("NameRequiredInPEReference", null);
                        } else if (this.fEntityScanner.skipChar(59)) {
                            if (scanningInternalSubset()) {
                                reportFatalError("PEReferenceWithinMarkup", new Object[]{peName});
                            }
                            this.fStringBuffer2.append(peName);
                            this.fStringBuffer2.append(';');
                        } else {
                            reportFatalError("SemicolonRequiredInPEReference", new Object[]{peName});
                        }
                        startPE(peName, true);
                        this.fEntityScanner.skipSpaces();
                    } while (this.fEntityScanner.skipChar(37));
                } else {
                    int c = this.fEntityScanner.peekChar();
                    if (XMLChar.isHighSurrogate(c)) {
                        scanSurrogates(this.fStringBuffer2);
                    } else if (isInvalidLiteral(c)) {
                        reportFatalError("InvalidCharInLiteral", new Object[]{Integer.toHexString(c)});
                        this.fEntityScanner.scanChar();
                    } else if (!(c == quote && entityDepth == this.fEntityDepth)) {
                        this.fStringBuffer.append((char) c);
                        this.fStringBuffer2.append((char) c);
                        this.fEntityScanner.scanChar();
                    }
                }
            } while (this.fEntityScanner.scanLiteral(quote, this.fString) != quote);
            this.fStringBuffer.append(this.fString);
            this.fStringBuffer2.append(this.fString);
            literal = this.fStringBuffer;
            literal2 = this.fStringBuffer2;
        }
        value.setValues(literal);
        nonNormalizedValue.setValues(literal2);
        if (!this.fEntityScanner.skipChar(quote)) {
            reportFatalError("CloseQuoteMissingInDecl", null);
        }
    }

    private final void scanNotationDecl() throws IOException, XNIException {
        boolean z;
        String name;
        this.fReportEntity = false;
        if (scanningInternalSubset()) {
            z = false;
        } else {
            z = true;
        }
        if (!skipSeparator(true, z)) {
            reportFatalError("MSG_SPACE_REQUIRED_BEFORE_NOTATION_NAME_IN_NOTATIONDECL", null);
        }
        if (this.fNamespaces) {
            name = this.fEntityScanner.scanNCName();
        } else {
            name = this.fEntityScanner.scanName();
        }
        if (name == null) {
            reportFatalError("MSG_NOTATION_NAME_REQUIRED_IN_NOTATIONDECL", null);
        }
        if (scanningInternalSubset()) {
            z = false;
        } else {
            z = true;
        }
        if (!skipSeparator(true, z)) {
            if (this.fNamespaces && this.fEntityScanner.peekChar() == 58) {
                this.fEntityScanner.scanChar();
                XMLStringBuffer colonName = new XMLStringBuffer(name);
                colonName.append(':');
                colonName.append(this.fEntityScanner.scanName());
                reportFatalError("ColonNotLegalWithNS", new Object[]{colonName.toString()});
                if (scanningInternalSubset()) {
                    z = false;
                } else {
                    z = true;
                }
                skipSeparator(true, z);
            } else {
                reportFatalError("MSG_SPACE_REQUIRED_AFTER_NOTATION_NAME_IN_NOTATIONDECL", new Object[]{name});
            }
        }
        scanExternalID(this.fStrings, true);
        String systemId = this.fStrings[0];
        String publicId = this.fStrings[1];
        String baseSystemId = this.fEntityScanner.getBaseSystemId();
        if (systemId == null && publicId == null) {
            reportFatalError("ExternalIDorPublicIDRequired", new Object[]{name});
        }
        if (scanningInternalSubset()) {
            z = false;
        } else {
            z = true;
        }
        skipSeparator(false, z);
        if (!this.fEntityScanner.skipChar(62)) {
            reportFatalError("NotationDeclUnterminated", new Object[]{name});
        }
        this.fMarkUpDepth--;
        if (this.fDTDHandler != null) {
            this.fResourceIdentifier.setValues(publicId, systemId, baseSystemId, XMLEntityManager.expandSystemId(systemId, baseSystemId, false));
            this.fDTDHandler.notationDecl(name, this.fResourceIdentifier, null);
        }
        this.fReportEntity = true;
    }

    private final void scanConditionalSect(int currPEDepth) throws IOException, XNIException {
        this.fReportEntity = false;
        skipSeparator(false, !scanningInternalSubset());
        boolean z;
        if (this.fEntityScanner.skipString("INCLUDE")) {
            if (scanningInternalSubset()) {
                z = false;
            } else {
                z = true;
            }
            skipSeparator(false, z);
            if (currPEDepth != this.fPEDepth && this.fValidation) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "INVALID_PE_IN_CONDITIONAL", new Object[]{this.fEntityManager.fCurrentEntity.name}, (short) 1);
            }
            if (!this.fEntityScanner.skipChar(91)) {
                reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
            }
            if (this.fDTDHandler != null) {
                this.fDTDHandler.startConditional((short) 0, null);
            }
            this.fIncludeSectDepth++;
            this.fReportEntity = true;
        } else if (this.fEntityScanner.skipString("IGNORE")) {
            if (scanningInternalSubset()) {
                z = false;
            } else {
                z = true;
            }
            skipSeparator(false, z);
            if (currPEDepth != this.fPEDepth && this.fValidation) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "INVALID_PE_IN_CONDITIONAL", new Object[]{this.fEntityManager.fCurrentEntity.name}, (short) 1);
            }
            if (this.fDTDHandler != null) {
                this.fDTDHandler.startConditional((short) 1, null);
            }
            if (!this.fEntityScanner.skipChar(91)) {
                reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
            }
            this.fReportEntity = true;
            int initialDepth = this.fIncludeSectDepth + 1;
            this.fIncludeSectDepth = initialDepth;
            if (this.fDTDHandler != null) {
                this.fIgnoreConditionalBuffer.clear();
            }
            while (true) {
                if (this.fEntityScanner.skipChar(60)) {
                    if (this.fDTDHandler != null) {
                        this.fIgnoreConditionalBuffer.append('<');
                    }
                    if (this.fEntityScanner.skipChar(33)) {
                        if (this.fEntityScanner.skipChar(91)) {
                            if (this.fDTDHandler != null) {
                                this.fIgnoreConditionalBuffer.append("![");
                            }
                            this.fIncludeSectDepth++;
                        } else if (this.fDTDHandler != null) {
                            this.fIgnoreConditionalBuffer.append("!");
                        }
                    }
                } else if (this.fEntityScanner.skipChar(93)) {
                    if (this.fDTDHandler != null) {
                        this.fIgnoreConditionalBuffer.append(']');
                    }
                    if (this.fEntityScanner.skipChar(93)) {
                        if (this.fDTDHandler != null) {
                            this.fIgnoreConditionalBuffer.append(']');
                        }
                        while (this.fEntityScanner.skipChar(93)) {
                            if (this.fDTDHandler != null) {
                                this.fIgnoreConditionalBuffer.append(']');
                            }
                        }
                        if (this.fEntityScanner.skipChar(62)) {
                            int i = this.fIncludeSectDepth;
                            this.fIncludeSectDepth = i - 1;
                            if (i == initialDepth) {
                                break;
                            } else if (this.fDTDHandler != null) {
                                this.fIgnoreConditionalBuffer.append('>');
                            }
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                } else {
                    int c = this.fEntityScanner.scanChar();
                    if (this.fScannerState == 0) {
                        reportFatalError("IgnoreSectUnterminated", null);
                        return;
                    } else if (this.fDTDHandler != null) {
                        this.fIgnoreConditionalBuffer.append((char) c);
                    }
                }
            }
            this.fMarkUpDepth--;
            if (this.fDTDHandler != null) {
                this.fLiteral.setValues(this.fIgnoreConditionalBuffer.ch, 0, this.fIgnoreConditionalBuffer.length - 2);
                this.fDTDHandler.ignoredCharacters(this.fLiteral, null);
                this.fDTDHandler.endConditional(null);
            }
        } else {
            reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
        }
    }

    protected final boolean scanDecls(boolean complete) throws IOException, XNIException {
        skipSeparator(false, true);
        boolean again = true;
        while (again && this.fScannerState == 2) {
            again = complete;
            if (this.fEntityScanner.skipChar(60)) {
                this.fMarkUpDepth++;
                if (this.fEntityScanner.skipChar(63)) {
                    scanPI();
                } else if (!this.fEntityScanner.skipChar(33)) {
                    this.fMarkUpDepth--;
                    reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
                } else if (this.fEntityScanner.skipChar(45)) {
                    if (this.fEntityScanner.skipChar(45)) {
                        scanComment();
                    } else {
                        reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
                    }
                } else if (this.fEntityScanner.skipString("ELEMENT")) {
                    scanElementDecl();
                } else if (this.fEntityScanner.skipString("ATTLIST")) {
                    scanAttlistDecl();
                } else if (this.fEntityScanner.skipString(SchemaSymbols.ATTVAL_ENTITY)) {
                    scanEntityDecl();
                } else if (this.fEntityScanner.skipString(SchemaSymbols.ATTVAL_NOTATION)) {
                    scanNotationDecl();
                } else if (!this.fEntityScanner.skipChar(91) || scanningInternalSubset()) {
                    this.fMarkUpDepth--;
                    reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
                } else {
                    scanConditionalSect(this.fPEDepth);
                }
            } else if (this.fIncludeSectDepth > 0 && this.fEntityScanner.skipChar(93)) {
                if (!(this.fEntityScanner.skipChar(93) && this.fEntityScanner.skipChar(62))) {
                    reportFatalError("IncludeSectUnterminated", null);
                }
                if (this.fDTDHandler != null) {
                    this.fDTDHandler.endConditional(null);
                }
                this.fIncludeSectDepth--;
                this.fMarkUpDepth--;
            } else if (scanningInternalSubset() && this.fEntityScanner.peekChar() == 93) {
                return false;
            } else {
                if (!this.fEntityScanner.skipSpaces()) {
                    reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
                    int ch;
                    do {
                        this.fEntityScanner.scanChar();
                        skipSeparator(false, true);
                        ch = this.fEntityScanner.peekChar();
                        if (ch == 60 || ch == 93) {
                            break;
                        }
                    } while (!XMLChar.isSpace(ch));
                }
            }
            skipSeparator(false, true);
        }
        if (this.fScannerState != 0) {
            return true;
        }
        return false;
    }

    private boolean skipSeparator(boolean spaceRequired, boolean lookForPERefs) throws IOException, XNIException {
        int depth = this.fPEDepth;
        boolean sawSpace = this.fEntityScanner.skipSpaces();
        if (lookForPERefs && this.fEntityScanner.skipChar(37)) {
            do {
                String name = this.fEntityScanner.scanName();
                if (name == null) {
                    reportFatalError("NameRequiredInPEReference", null);
                } else if (!this.fEntityScanner.skipChar(59)) {
                    reportFatalError("SemicolonRequiredInPEReference", new Object[]{name});
                }
                startPE(name, false);
                this.fEntityScanner.skipSpaces();
            } while (this.fEntityScanner.skipChar(37));
            return true;
        } else if (spaceRequired && !sawSpace && depth == this.fPEDepth) {
            return false;
        } else {
            return true;
        }
    }

    private final void pushContentStack(int c) {
        if (this.fContentStack.length == this.fContentDepth) {
            int[] newStack = new int[(this.fContentDepth * 2)];
            System.arraycopy(this.fContentStack, 0, newStack, 0, this.fContentDepth);
            this.fContentStack = newStack;
        }
        int[] iArr = this.fContentStack;
        int i = this.fContentDepth;
        this.fContentDepth = i + 1;
        iArr[i] = c;
    }

    private final int popContentStack() {
        int[] iArr = this.fContentStack;
        int i = this.fContentDepth - 1;
        this.fContentDepth = i;
        return iArr[i];
    }

    private final void pushPEStack(int depth, boolean report) {
        if (this.fPEStack.length == this.fPEDepth) {
            int[] newIntStack = new int[(this.fPEDepth * 2)];
            System.arraycopy(this.fPEStack, 0, newIntStack, 0, this.fPEDepth);
            this.fPEStack = newIntStack;
            boolean[] newBooleanStack = new boolean[(this.fPEDepth * 2)];
            System.arraycopy(this.fPEReport, 0, newBooleanStack, 0, this.fPEDepth);
            this.fPEReport = newBooleanStack;
        }
        this.fPEReport[this.fPEDepth] = report;
        int[] iArr = this.fPEStack;
        int i = this.fPEDepth;
        this.fPEDepth = i + 1;
        iArr[i] = depth;
    }

    private final int popPEStack() {
        int[] iArr = this.fPEStack;
        int i = this.fPEDepth - 1;
        this.fPEDepth = i;
        return iArr[i];
    }

    private final boolean peekReportEntity() {
        return this.fPEReport[this.fPEDepth - 1];
    }

    private final void ensureEnumerationSize(int size) {
        if (this.fEnumeration.length == size) {
            String[] newEnum = new String[(size * 2)];
            System.arraycopy(this.fEnumeration, 0, newEnum, 0, size);
            this.fEnumeration = newEnum;
        }
    }

    private void init() {
        this.fStartDTDCalled = false;
        this.fExtEntityDepth = 0;
        this.fIncludeSectDepth = 0;
        this.fMarkUpDepth = 0;
        this.fPEDepth = 0;
        this.fStandalone = false;
        this.fSeenExternalDTD = false;
        this.fSeenPEReferences = false;
        setScannerState(1);
    }

    private int skipInvalidEnumerationValue() throws IOException {
        int c;
        do {
            c = this.fEntityScanner.scanChar();
            if (c == 124) {
                break;
            }
        } while (c != 41);
        ensureEnumerationSize(this.fEnumerationCount + 1);
        String[] strArr = this.fEnumeration;
        int i = this.fEnumerationCount;
        this.fEnumerationCount = i + 1;
        strArr[i] = XMLSymbols.EMPTY_STRING;
        return c;
    }
}
