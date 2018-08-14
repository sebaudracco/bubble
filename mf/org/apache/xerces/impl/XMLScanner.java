package mf.org.apache.xerces.impl;

import java.io.IOException;
import mf.javax.xml.transform.OutputKeys;
import mf.org.apache.xerces.impl.XMLEntityManager.ScannedEntity;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.util.XMLResourceIdentifierImpl;
import mf.org.apache.xerces.util.XMLStringBuffer;
import mf.org.apache.xerces.xni.Augmentations;
import mf.org.apache.xerces.xni.XMLResourceIdentifier;
import mf.org.apache.xerces.xni.XMLString;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.parser.XMLComponent;
import mf.org.apache.xerces.xni.parser.XMLComponentManager;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;

public abstract class XMLScanner implements XMLComponent {
    protected static final boolean DEBUG_ATTR_NORMALIZATION = false;
    protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    protected static final String NOTIFY_CHAR_REFS = "http://apache.org/xml/features/scanner/notify-char-refs";
    protected static final String PARSER_SETTINGS = "http://apache.org/xml/features/internal/parser-settings";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String VALIDATION = "http://xml.org/sax/features/validation";
    protected static final String fAmpSymbol = "amp".intern();
    protected static final String fAposSymbol = "apos".intern();
    protected static final String fEncodingSymbol = OutputKeys.ENCODING.intern();
    protected static final String fGtSymbol = "gt".intern();
    protected static final String fLtSymbol = "lt".intern();
    protected static final String fQuotSymbol = "quot".intern();
    protected static final String fStandaloneSymbol = OutputKeys.STANDALONE.intern();
    protected static final String fVersionSymbol = "version".intern();
    protected String fCharRefLiteral = null;
    protected int fEntityDepth;
    protected XMLEntityManager fEntityManager;
    protected XMLEntityScanner fEntityScanner;
    protected XMLErrorReporter fErrorReporter;
    protected boolean fNamespaces;
    protected boolean fNotifyCharRefs = false;
    protected boolean fParserSettings = true;
    protected boolean fReportEntity;
    protected final XMLResourceIdentifierImpl fResourceIdentifier = new XMLResourceIdentifierImpl();
    protected boolean fScanningAttribute;
    private final XMLString fString = new XMLString();
    private final XMLStringBuffer fStringBuffer = new XMLStringBuffer();
    private final XMLStringBuffer fStringBuffer2 = new XMLStringBuffer();
    private final XMLStringBuffer fStringBuffer3 = new XMLStringBuffer();
    protected SymbolTable fSymbolTable;
    protected boolean fValidation = false;

    public void reset(XMLComponentManager componentManager) throws XMLConfigurationException {
        try {
            this.fParserSettings = componentManager.getFeature(PARSER_SETTINGS);
        } catch (XMLConfigurationException e) {
            this.fParserSettings = true;
        }
        if (this.fParserSettings) {
            this.fSymbolTable = (SymbolTable) componentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
            this.fErrorReporter = (XMLErrorReporter) componentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
            this.fEntityManager = (XMLEntityManager) componentManager.getProperty(ENTITY_MANAGER);
            try {
                this.fValidation = componentManager.getFeature(VALIDATION);
            } catch (XMLConfigurationException e2) {
                this.fValidation = false;
            }
            try {
                this.fNamespaces = componentManager.getFeature(NAMESPACES);
            } catch (XMLConfigurationException e3) {
                this.fNamespaces = true;
            }
            try {
                this.fNotifyCharRefs = componentManager.getFeature(NOTIFY_CHAR_REFS);
            } catch (XMLConfigurationException e4) {
                this.fNotifyCharRefs = false;
            }
            init();
            return;
        }
        init();
    }

    public void setProperty(String propertyId, Object value) throws XMLConfigurationException {
        if (propertyId.startsWith(Constants.XERCES_PROPERTY_PREFIX)) {
            int suffixLength = propertyId.length() - Constants.XERCES_PROPERTY_PREFIX.length();
            if (suffixLength == Constants.SYMBOL_TABLE_PROPERTY.length() && propertyId.endsWith(Constants.SYMBOL_TABLE_PROPERTY)) {
                this.fSymbolTable = (SymbolTable) value;
            } else if (suffixLength == Constants.ERROR_REPORTER_PROPERTY.length() && propertyId.endsWith(Constants.ERROR_REPORTER_PROPERTY)) {
                this.fErrorReporter = (XMLErrorReporter) value;
            } else if (suffixLength == Constants.ENTITY_MANAGER_PROPERTY.length() && propertyId.endsWith(Constants.ENTITY_MANAGER_PROPERTY)) {
                this.fEntityManager = (XMLEntityManager) value;
            }
        }
    }

    public void setFeature(String featureId, boolean value) throws XMLConfigurationException {
        if (VALIDATION.equals(featureId)) {
            this.fValidation = value;
        } else if (NOTIFY_CHAR_REFS.equals(featureId)) {
            this.fNotifyCharRefs = value;
        }
    }

    public boolean getFeature(String featureId) throws XMLConfigurationException {
        if (VALIDATION.equals(featureId)) {
            return this.fValidation;
        }
        if (NOTIFY_CHAR_REFS.equals(featureId)) {
            return this.fNotifyCharRefs;
        }
        throw new XMLConfigurationException((short) 0, featureId);
    }

    protected void reset() {
        init();
        this.fValidation = true;
        this.fNotifyCharRefs = false;
    }

    protected void scanXMLDeclOrTextDecl(boolean scanningTextDecl, String[] pseudoAttributeValues) throws IOException, XNIException {
        String version = null;
        String encoding = null;
        String standalone = null;
        int state = 0;
        boolean dataFoundForTarget = false;
        boolean sawSpace = this.fEntityScanner.skipDeclSpaces();
        ScannedEntity currEnt = this.fEntityManager.getCurrentEntity();
        boolean currLiteral = currEnt.literal;
        currEnt.literal = false;
        while (this.fEntityScanner.peekChar() != 63) {
            dataFoundForTarget = true;
            String name = scanPseudoAttribute(scanningTextDecl, this.fString);
            String str;
            switch (state) {
                case 0:
                    if (name != fVersionSymbol) {
                        if (name != fEncodingSymbol) {
                            if (!scanningTextDecl) {
                                reportFatalError("VersionInfoRequired", null);
                                break;
                            } else {
                                reportFatalError("EncodingDeclRequired", null);
                                break;
                            }
                        }
                        if (!scanningTextDecl) {
                            reportFatalError("VersionInfoRequired", null);
                        }
                        if (!sawSpace) {
                            if (scanningTextDecl) {
                                str = "SpaceRequiredBeforeEncodingInTextDecl";
                            } else {
                                str = "SpaceRequiredBeforeEncodingInXMLDecl";
                            }
                            reportFatalError(str, null);
                        }
                        encoding = this.fString.toString();
                        state = scanningTextDecl ? 3 : 2;
                        break;
                    }
                    if (!sawSpace) {
                        if (scanningTextDecl) {
                            str = "SpaceRequiredBeforeVersionInTextDecl";
                        } else {
                            str = "SpaceRequiredBeforeVersionInXMLDecl";
                        }
                        reportFatalError(str, null);
                    }
                    version = this.fString.toString();
                    state = 1;
                    if (!versionSupported(version)) {
                        reportFatalError(getVersionNotSupportedKey(), new Object[]{version});
                        break;
                    }
                    break;
                case 1:
                    if (name != fEncodingSymbol) {
                        if (!scanningTextDecl && name == fStandaloneSymbol) {
                            if (!sawSpace) {
                                reportFatalError("SpaceRequiredBeforeStandalone", null);
                            }
                            standalone = this.fString.toString();
                            state = 3;
                            if (!(standalone.equals("yes") || standalone.equals("no"))) {
                                reportFatalError("SDDeclInvalid", new Object[]{standalone});
                                break;
                            }
                        }
                        reportFatalError("EncodingDeclRequired", null);
                        break;
                    }
                    if (!sawSpace) {
                        if (scanningTextDecl) {
                            str = "SpaceRequiredBeforeEncodingInTextDecl";
                        } else {
                            str = "SpaceRequiredBeforeEncodingInXMLDecl";
                        }
                        reportFatalError(str, null);
                    }
                    encoding = this.fString.toString();
                    state = scanningTextDecl ? 3 : 2;
                    break;
                    break;
                case 2:
                    if (name != fStandaloneSymbol) {
                        reportFatalError("EncodingDeclRequired", null);
                        break;
                    }
                    if (!sawSpace) {
                        reportFatalError("SpaceRequiredBeforeStandalone", null);
                    }
                    standalone = this.fString.toString();
                    state = 3;
                    if (!(standalone.equals("yes") || standalone.equals("no"))) {
                        reportFatalError("SDDeclInvalid", new Object[]{standalone});
                        break;
                    }
                default:
                    reportFatalError("NoMorePseudoAttributes", null);
                    break;
            }
            sawSpace = this.fEntityScanner.skipDeclSpaces();
        }
        if (currLiteral) {
            currEnt.literal = true;
        }
        if (scanningTextDecl && state != 3) {
            reportFatalError("MorePseudoAttributes", null);
        }
        if (scanningTextDecl) {
            if (!dataFoundForTarget && encoding == null) {
                reportFatalError("EncodingDeclRequired", null);
            }
        } else if (!dataFoundForTarget && version == null) {
            reportFatalError("VersionInfoRequired", null);
        }
        if (!this.fEntityScanner.skipChar(63)) {
            reportFatalError("XMLDeclUnterminated", null);
        }
        if (!this.fEntityScanner.skipChar(62)) {
            reportFatalError("XMLDeclUnterminated", null);
        }
        pseudoAttributeValues[0] = version;
        pseudoAttributeValues[1] = encoding;
        pseudoAttributeValues[2] = standalone;
    }

    public String scanPseudoAttribute(boolean scanningTextDecl, XMLString value) throws IOException, XNIException {
        String str;
        String name = this.fEntityScanner.scanName();
        XMLEntityManager.print(this.fEntityManager.getCurrentEntity());
        if (name == null) {
            reportFatalError("PseudoAttrNameExpected", null);
        }
        this.fEntityScanner.skipDeclSpaces();
        if (!this.fEntityScanner.skipChar(61)) {
            if (scanningTextDecl) {
                str = "EqRequiredInTextDecl";
            } else {
                str = "EqRequiredInXMLDecl";
            }
            reportFatalError(str, new Object[]{name});
        }
        this.fEntityScanner.skipDeclSpaces();
        int quote = this.fEntityScanner.peekChar();
        if (!(quote == 39 || quote == 34)) {
            if (scanningTextDecl) {
                str = "QuoteRequiredInTextDecl";
            } else {
                str = "QuoteRequiredInXMLDecl";
            }
            reportFatalError(str, new Object[]{name});
        }
        this.fEntityScanner.scanChar();
        int c = this.fEntityScanner.scanLiteral(quote, value);
        if (c != quote) {
            this.fStringBuffer2.clear();
            do {
                this.fStringBuffer2.append(value);
                if (c != -1) {
                    if (c == 38 || c == 37 || c == 60 || c == 93) {
                        this.fStringBuffer2.append((char) this.fEntityScanner.scanChar());
                    } else if (XMLChar.isHighSurrogate(c)) {
                        scanSurrogates(this.fStringBuffer2);
                    } else if (isInvalidLiteral(c)) {
                        reportFatalError(scanningTextDecl ? "InvalidCharInTextDecl" : "InvalidCharInXMLDecl", new Object[]{Integer.toString(c, 16)});
                        this.fEntityScanner.scanChar();
                    }
                }
                c = this.fEntityScanner.scanLiteral(quote, value);
            } while (c != quote);
            this.fStringBuffer2.append(value);
            value.setValues(this.fStringBuffer2);
        }
        if (!this.fEntityScanner.skipChar(quote)) {
            if (scanningTextDecl) {
                str = "CloseQuoteMissingInTextDecl";
            } else {
                str = "CloseQuoteMissingInXMLDecl";
            }
            reportFatalError(str, new Object[]{name});
        }
        return name;
    }

    protected void scanPI() throws IOException, XNIException {
        String target;
        this.fReportEntity = false;
        if (this.fNamespaces) {
            target = this.fEntityScanner.scanNCName();
        } else {
            target = this.fEntityScanner.scanName();
        }
        if (target == null) {
            reportFatalError("PITargetRequired", null);
        }
        scanPIData(target, this.fString);
        this.fReportEntity = true;
    }

    protected void scanPIData(String target, XMLString data) throws IOException, XNIException {
        if (target.length() == 3) {
            char c0 = Character.toLowerCase(target.charAt(0));
            char c1 = Character.toLowerCase(target.charAt(1));
            char c2 = Character.toLowerCase(target.charAt(2));
            if (c0 == 'x' && c1 == 'm' && c2 == 'l') {
                reportFatalError("ReservedPITarget", null);
            }
        }
        if (!this.fEntityScanner.skipSpaces()) {
            if (this.fEntityScanner.skipString("?>")) {
                data.clear();
                return;
            } else if (this.fNamespaces && this.fEntityScanner.peekChar() == 58) {
                this.fEntityScanner.scanChar();
                XMLStringBuffer colonName = new XMLStringBuffer(target);
                colonName.append(':');
                String str = this.fEntityScanner.scanName();
                if (str != null) {
                    colonName.append(str);
                }
                reportFatalError("ColonNotLegalWithNS", new Object[]{colonName.toString()});
                this.fEntityScanner.skipSpaces();
            } else {
                reportFatalError("SpaceRequiredInPI", null);
            }
        }
        this.fStringBuffer.clear();
        if (this.fEntityScanner.scanData("?>", this.fStringBuffer)) {
            do {
                int c = this.fEntityScanner.peekChar();
                if (c != -1) {
                    if (XMLChar.isHighSurrogate(c)) {
                        scanSurrogates(this.fStringBuffer);
                    } else if (isInvalidLiteral(c)) {
                        reportFatalError("InvalidCharInPI", new Object[]{Integer.toHexString(c)});
                        this.fEntityScanner.scanChar();
                    }
                }
            } while (this.fEntityScanner.scanData("?>", this.fStringBuffer));
        }
        data.setValues(this.fStringBuffer);
    }

    protected void scanComment(XMLStringBuffer text) throws IOException, XNIException {
        text.clear();
        while (this.fEntityScanner.scanData("--", text)) {
            int c = this.fEntityScanner.peekChar();
            if (c != -1) {
                if (XMLChar.isHighSurrogate(c)) {
                    scanSurrogates(text);
                } else if (isInvalidLiteral(c)) {
                    reportFatalError("InvalidCharInComment", new Object[]{Integer.toHexString(c)});
                    this.fEntityScanner.scanChar();
                }
            }
        }
        if (!this.fEntityScanner.skipChar(62)) {
            reportFatalError("DashDashInComment", null);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected boolean scanAttributeValue(mf.org.apache.xerces.xni.XMLString r13, mf.org.apache.xerces.xni.XMLString r14, java.lang.String r15, boolean r16, java.lang.String r17) throws java.io.IOException, mf.org.apache.xerces.xni.XNIException {
        /*
        r12 = this;
        r7 = r12.fEntityScanner;
        r6 = r7.peekChar();
        r7 = 39;
        if (r6 == r7) goto L_0x001d;
    L_0x000a:
        r7 = 34;
        if (r6 == r7) goto L_0x001d;
    L_0x000e:
        r7 = "OpenQuoteExpected";
        r8 = 2;
        r8 = new java.lang.Object[r8];
        r9 = 0;
        r8[r9] = r17;
        r9 = 1;
        r8[r9] = r15;
        r12.reportFatalError(r7, r8);
    L_0x001d:
        r7 = r12.fEntityScanner;
        r7.scanChar();
        r3 = r12.fEntityDepth;
        r7 = r12.fEntityScanner;
        r0 = r7.scanLiteral(r6, r13);
        r5 = 0;
        if (r0 != r6) goto L_0x0050;
    L_0x002d:
        r5 = r12.isUnchangedByNormalization(r13);
        r7 = -1;
        if (r5 != r7) goto L_0x0050;
    L_0x0034:
        r14.setValues(r13);
        r7 = r12.fEntityScanner;
        r2 = r7.scanChar();
        if (r2 == r6) goto L_0x004e;
    L_0x003f:
        r7 = "CloseQuoteExpected";
        r8 = 2;
        r8 = new java.lang.Object[r8];
        r9 = 0;
        r8[r9] = r17;
        r9 = 1;
        r8[r9] = r15;
        r12.reportFatalError(r7, r8);
    L_0x004e:
        r7 = 1;
    L_0x004f:
        return r7;
    L_0x0050:
        r7 = r12.fStringBuffer2;
        r7.clear();
        r7 = r12.fStringBuffer2;
        r7.append(r13);
        r12.normalizeWhitespace(r13, r5);
        if (r0 == r6) goto L_0x00c7;
    L_0x005f:
        r7 = 1;
        r12.fScanningAttribute = r7;
        r7 = r12.fStringBuffer;
        r7.clear();
    L_0x0067:
        r7 = r12.fStringBuffer;
        r7.append(r13);
        r7 = 38;
        if (r0 != r7) goto L_0x01b5;
    L_0x0070:
        r7 = r12.fEntityScanner;
        r8 = 38;
        r7.skipChar(r8);
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x0082;
    L_0x007b:
        r7 = r12.fStringBuffer2;
        r8 = 38;
        r7.append(r8);
    L_0x0082:
        r7 = r12.fEntityScanner;
        r8 = 35;
        r7 = r7.skipChar(r8);
        if (r7 == 0) goto L_0x00ef;
    L_0x008c:
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x0097;
    L_0x0090:
        r7 = r12.fStringBuffer2;
        r8 = 35;
        r7.append(r8);
    L_0x0097:
        r7 = r12.fStringBuffer;
        r8 = r12.fStringBuffer2;
        r1 = r12.scanCharReferenceValue(r7, r8);
        r7 = -1;
        if (r1 == r7) goto L_0x00a2;
    L_0x00a2:
        r7 = r12.fEntityScanner;
        r0 = r7.scanLiteral(r6, r13);
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x00b1;
    L_0x00ac:
        r7 = r12.fStringBuffer2;
        r7.append(r13);
    L_0x00b1:
        r12.normalizeWhitespace(r13);
        if (r0 != r6) goto L_0x0067;
    L_0x00b6:
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x0067;
    L_0x00ba:
        r7 = r12.fStringBuffer;
        r7.append(r13);
        r7 = r12.fStringBuffer;
        r13.setValues(r7);
        r7 = 0;
        r12.fScanningAttribute = r7;
    L_0x00c7:
        r7 = r12.fStringBuffer2;
        r14.setValues(r7);
        r7 = r12.fEntityScanner;
        r2 = r7.scanChar();
        if (r2 == r6) goto L_0x00e3;
    L_0x00d4:
        r7 = "CloseQuoteExpected";
        r8 = 2;
        r8 = new java.lang.Object[r8];
        r9 = 0;
        r8[r9] = r17;
        r9 = 1;
        r8[r9] = r15;
        r12.reportFatalError(r7, r8);
    L_0x00e3:
        r7 = r13.ch;
        r8 = r13.offset;
        r9 = r13.length;
        r7 = r14.equals(r7, r8, r9);
        goto L_0x004f;
    L_0x00ef:
        r7 = r12.fEntityScanner;
        r4 = r7.scanName();
        if (r4 != 0) goto L_0x0120;
    L_0x00f7:
        r7 = "NameRequiredInReference";
        r8 = 0;
        r12.reportFatalError(r7, r8);
    L_0x00fe:
        r7 = r12.fEntityScanner;
        r8 = 59;
        r7 = r7.skipChar(r8);
        if (r7 != 0) goto L_0x012a;
    L_0x0108:
        r7 = "SemicolonRequiredInReference";
        r8 = 1;
        r8 = new java.lang.Object[r8];
        r9 = 0;
        r8[r9] = r4;
        r12.reportFatalError(r7, r8);
    L_0x0114:
        r7 = fAmpSymbol;
        if (r4 != r7) goto L_0x0136;
    L_0x0118:
        r7 = r12.fStringBuffer;
        r8 = 38;
        r7.append(r8);
        goto L_0x00a2;
    L_0x0120:
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x00fe;
    L_0x0124:
        r7 = r12.fStringBuffer2;
        r7.append(r4);
        goto L_0x00fe;
    L_0x012a:
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x0114;
    L_0x012e:
        r7 = r12.fStringBuffer2;
        r8 = 59;
        r7.append(r8);
        goto L_0x0114;
    L_0x0136:
        r7 = fAposSymbol;
        if (r4 != r7) goto L_0x0143;
    L_0x013a:
        r7 = r12.fStringBuffer;
        r8 = 39;
        r7.append(r8);
        goto L_0x00a2;
    L_0x0143:
        r7 = fLtSymbol;
        if (r4 != r7) goto L_0x0150;
    L_0x0147:
        r7 = r12.fStringBuffer;
        r8 = 60;
        r7.append(r8);
        goto L_0x00a2;
    L_0x0150:
        r7 = fGtSymbol;
        if (r4 != r7) goto L_0x015d;
    L_0x0154:
        r7 = r12.fStringBuffer;
        r8 = 62;
        r7.append(r8);
        goto L_0x00a2;
    L_0x015d:
        r7 = fQuotSymbol;
        if (r4 != r7) goto L_0x016a;
    L_0x0161:
        r7 = r12.fStringBuffer;
        r8 = 34;
        r7.append(r8);
        goto L_0x00a2;
    L_0x016a:
        r7 = r12.fEntityManager;
        r7 = r7.isExternalEntity(r4);
        if (r7 == 0) goto L_0x0180;
    L_0x0172:
        r7 = "ReferenceToExternalEntity";
        r8 = 1;
        r8 = new java.lang.Object[r8];
        r9 = 0;
        r8[r9] = r4;
        r12.reportFatalError(r7, r8);
        goto L_0x00a2;
    L_0x0180:
        r7 = r12.fEntityManager;
        r7 = r7.isDeclaredEntity(r4);
        if (r7 != 0) goto L_0x01a0;
    L_0x0188:
        if (r16 == 0) goto L_0x01a8;
    L_0x018a:
        r7 = r12.fValidation;
        if (r7 == 0) goto L_0x01a0;
    L_0x018e:
        r7 = r12.fErrorReporter;
        r8 = "http://www.w3.org/TR/1998/REC-xml-19980210";
        r9 = "EntityNotDeclared";
        r10 = 1;
        r10 = new java.lang.Object[r10];
        r11 = 0;
        r10[r11] = r4;
        r11 = 1;
        r7.reportError(r8, r9, r10, r11);
    L_0x01a0:
        r7 = r12.fEntityManager;
        r8 = 1;
        r7.startEntity(r4, r8);
        goto L_0x00a2;
    L_0x01a8:
        r7 = "EntityNotDeclared";
        r8 = 1;
        r8 = new java.lang.Object[r8];
        r9 = 0;
        r8[r9] = r4;
        r12.reportFatalError(r7, r8);
        goto L_0x01a0;
    L_0x01b5:
        r7 = 60;
        if (r0 != r7) goto L_0x01d9;
    L_0x01b9:
        r7 = "LessthanInAttValue";
        r8 = 2;
        r8 = new java.lang.Object[r8];
        r9 = 0;
        r8[r9] = r17;
        r9 = 1;
        r8[r9] = r15;
        r12.reportFatalError(r7, r8);
        r7 = r12.fEntityScanner;
        r7.scanChar();
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x00a2;
    L_0x01d1:
        r7 = r12.fStringBuffer2;
        r8 = (char) r0;
        r7.append(r8);
        goto L_0x00a2;
    L_0x01d9:
        r7 = 37;
        if (r0 == r7) goto L_0x01e1;
    L_0x01dd:
        r7 = 93;
        if (r0 != r7) goto L_0x01f8;
    L_0x01e1:
        r7 = r12.fEntityScanner;
        r7.scanChar();
        r7 = r12.fStringBuffer;
        r8 = (char) r0;
        r7.append(r8);
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x00a2;
    L_0x01f0:
        r7 = r12.fStringBuffer2;
        r8 = (char) r0;
        r7.append(r8);
        goto L_0x00a2;
    L_0x01f8:
        r7 = 10;
        if (r0 == r7) goto L_0x0200;
    L_0x01fc:
        r7 = 13;
        if (r0 != r7) goto L_0x0219;
    L_0x0200:
        r7 = r12.fEntityScanner;
        r7.scanChar();
        r7 = r12.fStringBuffer;
        r8 = 32;
        r7.append(r8);
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x00a2;
    L_0x0210:
        r7 = r12.fStringBuffer2;
        r8 = 10;
        r7.append(r8);
        goto L_0x00a2;
    L_0x0219:
        r7 = -1;
        if (r0 == r7) goto L_0x0243;
    L_0x021c:
        r7 = mf.org.apache.xerces.util.XMLChar.isHighSurrogate(r0);
        if (r7 == 0) goto L_0x0243;
    L_0x0222:
        r7 = r12.fStringBuffer3;
        r7.clear();
        r7 = r12.fStringBuffer3;
        r7 = r12.scanSurrogates(r7);
        if (r7 == 0) goto L_0x00a2;
    L_0x022f:
        r7 = r12.fStringBuffer;
        r8 = r12.fStringBuffer3;
        r7.append(r8);
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x00a2;
    L_0x023a:
        r7 = r12.fStringBuffer2;
        r8 = r12.fStringBuffer3;
        r7.append(r8);
        goto L_0x00a2;
    L_0x0243:
        r7 = -1;
        if (r0 == r7) goto L_0x00a2;
    L_0x0246:
        r7 = r12.isInvalidLiteral(r0);
        if (r7 == 0) goto L_0x00a2;
    L_0x024c:
        r7 = "InvalidCharInAttValue";
        r8 = 3;
        r8 = new java.lang.Object[r8];
        r9 = 0;
        r8[r9] = r17;
        r9 = 1;
        r8[r9] = r15;
        r9 = 2;
        r10 = 16;
        r10 = java.lang.Integer.toString(r0, r10);
        r8[r9] = r10;
        r12.reportFatalError(r7, r8);
        r7 = r12.fEntityScanner;
        r7.scanChar();
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x00a2;
    L_0x026d:
        r7 = r12.fStringBuffer2;
        r8 = (char) r0;
        r7.append(r8);
        goto L_0x00a2;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.XMLScanner.scanAttributeValue(mf.org.apache.xerces.xni.XMLString, mf.org.apache.xerces.xni.XMLString, java.lang.String, boolean, java.lang.String):boolean");
    }

    protected void scanExternalID(String[] identifiers, boolean optionalSystemId) throws IOException, XNIException {
        String systemId = null;
        String publicId = null;
        if (this.fEntityScanner.skipString("PUBLIC")) {
            if (!this.fEntityScanner.skipSpaces()) {
                reportFatalError("SpaceRequiredAfterPUBLIC", null);
            }
            scanPubidLiteral(this.fString);
            publicId = this.fString.toString();
            if (!(this.fEntityScanner.skipSpaces() || optionalSystemId)) {
                reportFatalError("SpaceRequiredBetweenPublicAndSystem", null);
            }
        }
        if (publicId != null || this.fEntityScanner.skipString("SYSTEM")) {
            if (publicId == null && !this.fEntityScanner.skipSpaces()) {
                reportFatalError("SpaceRequiredAfterSYSTEM", null);
            }
            int quote = this.fEntityScanner.peekChar();
            if (!(quote == 39 || quote == 34)) {
                if (publicId == null || !optionalSystemId) {
                    reportFatalError("QuoteRequiredInSystemID", null);
                } else {
                    identifiers[0] = null;
                    identifiers[1] = publicId;
                    return;
                }
            }
            this.fEntityScanner.scanChar();
            XMLString ident = this.fString;
            if (this.fEntityScanner.scanLiteral(quote, ident) != quote) {
                this.fStringBuffer.clear();
                do {
                    this.fStringBuffer.append(ident);
                    int c = this.fEntityScanner.peekChar();
                    if (XMLChar.isMarkup(c) || c == 93) {
                        this.fStringBuffer.append((char) this.fEntityScanner.scanChar());
                    } else if (XMLChar.isHighSurrogate(c)) {
                        scanSurrogates(this.fStringBuffer);
                    } else if (isInvalidLiteral(c)) {
                        reportFatalError("InvalidCharInSystemID", new Object[]{Integer.toHexString(c)});
                        this.fEntityScanner.scanChar();
                    }
                } while (this.fEntityScanner.scanLiteral(quote, ident) != quote);
                this.fStringBuffer.append(ident);
                ident = this.fStringBuffer;
            }
            systemId = ident.toString();
            if (!this.fEntityScanner.skipChar(quote)) {
                reportFatalError("SystemIDUnterminated", null);
            }
        }
        identifiers[0] = systemId;
        identifiers[1] = publicId;
    }

    protected boolean scanPubidLiteral(XMLString literal) throws IOException, XNIException {
        int quote = this.fEntityScanner.scanChar();
        if (quote == 39 || quote == 34) {
            this.fStringBuffer.clear();
            boolean skipSpace = true;
            boolean dataok = true;
            while (true) {
                int c = this.fEntityScanner.scanChar();
                if (c == 32 || c == 10 || c == 13) {
                    if (!skipSpace) {
                        this.fStringBuffer.append(' ');
                        skipSpace = true;
                    }
                } else if (c == quote) {
                    break;
                } else if (XMLChar.isPubid(c)) {
                    this.fStringBuffer.append((char) c);
                    skipSpace = false;
                } else if (c == -1) {
                    reportFatalError("PublicIDUnterminated", null);
                    return false;
                } else {
                    dataok = false;
                    reportFatalError("InvalidCharInPublicID", new Object[]{Integer.toHexString(c)});
                }
            }
            if (skipSpace) {
                XMLString xMLString = this.fStringBuffer;
                xMLString.length = xMLString.length - 1;
            }
            literal.setValues(this.fStringBuffer);
            return dataok;
        }
        reportFatalError("QuoteRequiredInPublicID", null);
        return false;
    }

    protected void normalizeWhitespace(XMLString value) {
        int end = value.offset + value.length;
        for (int i = value.offset; i < end; i++) {
            if (value.ch[i] < 32) {
                value.ch[i] = ' ';
            }
        }
    }

    protected void normalizeWhitespace(XMLString value, int fromIndex) {
        int end = value.offset + value.length;
        for (int i = value.offset + fromIndex; i < end; i++) {
            if (value.ch[i] < 32) {
                value.ch[i] = ' ';
            }
        }
    }

    protected int isUnchangedByNormalization(XMLString value) {
        int end = value.offset + value.length;
        for (int i = value.offset; i < end; i++) {
            if (value.ch[i] < 32) {
                return i - value.offset;
            }
        }
        return -1;
    }

    public void startEntity(String name, XMLResourceIdentifier identifier, String encoding, Augmentations augs) throws XNIException {
        this.fEntityDepth++;
        this.fEntityScanner = this.fEntityManager.getEntityScanner();
    }

    public void endEntity(String name, Augmentations augs) throws XNIException {
        this.fEntityDepth--;
    }

    protected int scanCharReferenceValue(XMLStringBuffer buf, XMLStringBuffer buf2) throws IOException, XNIException {
        boolean hex = false;
        int c;
        boolean digit;
        if (this.fEntityScanner.skipChar(120)) {
            if (buf2 != null) {
                buf2.append('x');
            }
            hex = true;
            this.fStringBuffer3.clear();
            c = this.fEntityScanner.peekChar();
            digit = (c >= 48 && c <= 57) || ((c >= 97 && c <= 102) || (c >= 65 && c <= 70));
            if (digit) {
                if (buf2 != null) {
                    buf2.append((char) c);
                }
                this.fEntityScanner.scanChar();
                this.fStringBuffer3.append((char) c);
                do {
                    c = this.fEntityScanner.peekChar();
                    digit = (c >= 48 && c <= 57) || ((c >= 97 && c <= 102) || (c >= 65 && c <= 70));
                    if (digit) {
                        if (buf2 != null) {
                            buf2.append((char) c);
                        }
                        this.fEntityScanner.scanChar();
                        this.fStringBuffer3.append((char) c);
                        continue;
                    }
                } while (digit);
            } else {
                reportFatalError("HexdigitRequiredInCharRef", null);
            }
        } else {
            this.fStringBuffer3.clear();
            c = this.fEntityScanner.peekChar();
            digit = c >= 48 && c <= 57;
            if (digit) {
                if (buf2 != null) {
                    buf2.append((char) c);
                }
                this.fEntityScanner.scanChar();
                this.fStringBuffer3.append((char) c);
                do {
                    c = this.fEntityScanner.peekChar();
                    digit = c >= 48 && c <= 57;
                    if (digit) {
                        if (buf2 != null) {
                            buf2.append((char) c);
                        }
                        this.fEntityScanner.scanChar();
                        this.fStringBuffer3.append((char) c);
                        continue;
                    }
                } while (digit);
            } else {
                reportFatalError("DigitRequiredInCharRef", null);
            }
        }
        if (!this.fEntityScanner.skipChar(59)) {
            reportFatalError("SemicolonRequiredInCharRef", null);
        }
        if (buf2 != null) {
            buf2.append(';');
        }
        int value = -1;
        StringBuffer errorBuf;
        try {
            value = Integer.parseInt(this.fStringBuffer3.toString(), hex ? 16 : 10);
            if (isInvalid(value)) {
                errorBuf = new StringBuffer(this.fStringBuffer3.length + 1);
                if (hex) {
                    errorBuf.append('x');
                }
                errorBuf.append(this.fStringBuffer3.ch, this.fStringBuffer3.offset, this.fStringBuffer3.length);
                reportFatalError("InvalidCharRef", new Object[]{errorBuf.toString()});
            }
        } catch (NumberFormatException e) {
            errorBuf = new StringBuffer(this.fStringBuffer3.length + 1);
            if (hex) {
                errorBuf.append('x');
            }
            errorBuf.append(this.fStringBuffer3.ch, this.fStringBuffer3.offset, this.fStringBuffer3.length);
            reportFatalError("InvalidCharRef", new Object[]{errorBuf.toString()});
        }
        if (XMLChar.isSupplemental(value)) {
            buf.append(XMLChar.highSurrogate(value));
            buf.append(XMLChar.lowSurrogate(value));
        } else {
            buf.append((char) value);
        }
        if (this.fNotifyCharRefs && value != -1) {
            String literal = "#" + (hex ? "x" : "") + this.fStringBuffer3.toString();
            if (!this.fScanningAttribute) {
                this.fCharRefLiteral = literal;
            }
        }
        return value;
    }

    protected boolean isInvalid(int value) {
        return XMLChar.isInvalid(value);
    }

    protected boolean isInvalidLiteral(int value) {
        return XMLChar.isInvalid(value);
    }

    protected boolean isValidNameChar(int value) {
        return XMLChar.isName(value);
    }

    protected boolean isValidNameStartChar(int value) {
        return XMLChar.isNameStart(value);
    }

    protected boolean isValidNCName(int value) {
        return XMLChar.isNCName(value);
    }

    protected boolean isValidNameStartHighSurrogate(int value) {
        return false;
    }

    protected boolean versionSupported(String version) {
        return version.equals("1.0");
    }

    protected String getVersionNotSupportedKey() {
        return "VersionNotSupported";
    }

    protected boolean scanSurrogates(XMLStringBuffer buf) throws IOException, XNIException {
        int high = this.fEntityScanner.scanChar();
        int low = this.fEntityScanner.peekChar();
        if (XMLChar.isLowSurrogate(low)) {
            this.fEntityScanner.scanChar();
            if (isInvalid(XMLChar.supplemental((char) high, (char) low))) {
                reportFatalError("InvalidCharInContent", new Object[]{Integer.toString(XMLChar.supplemental((char) high, (char) low), 16)});
                return false;
            }
            buf.append((char) high);
            buf.append((char) low);
            return true;
        }
        reportFatalError("InvalidCharInContent", new Object[]{Integer.toString(high, 16)});
        return false;
    }

    protected void reportFatalError(String msgId, Object[] args) throws XNIException {
        this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", msgId, args, (short) 2);
    }

    private void init() {
        this.fEntityScanner = null;
        this.fEntityDepth = 0;
        this.fReportEntity = true;
        this.fResourceIdentifier.clear();
    }
}
