package mf.org.apache.xerces.xpointer;

import java.util.ArrayList;
import java.util.HashMap;
import mf.org.apache.xerces.impl.XMLErrorReporter;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.util.XMLSymbols;
import mf.org.apache.xerces.xinclude.XIncludeHandler;
import mf.org.apache.xerces.xinclude.XIncludeNamespaceSupport;
import mf.org.apache.xerces.xni.Augmentations;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xni.XMLAttributes;
import mf.org.apache.xerces.xni.XMLDocumentHandler;
import mf.org.apache.xerces.xni.XMLString;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;
import mf.org.apache.xerces.xni.parser.XMLErrorHandler;

public final class XPointerHandler extends XIncludeHandler implements XPointerProcessor {
    private final String ELEMENT_SCHEME_NAME;
    protected XMLErrorHandler fErrorHandler;
    protected boolean fFixupBase;
    protected boolean fFixupLang;
    protected boolean fFoundMatchingPtrPart;
    protected boolean fIsXPointerResolved;
    protected SymbolTable fSymbolTable;
    protected XMLErrorReporter fXPointerErrorReporter;
    protected XPointerPart fXPointerPart;
    protected ArrayList fXPointerParts;

    private class Scanner {
        private static final byte CHARTYPE_CARRET = (byte) 3;
        private static final byte CHARTYPE_CLOSE_PAREN = (byte) 5;
        private static final byte CHARTYPE_COLON = (byte) 10;
        private static final byte CHARTYPE_DIGIT = (byte) 9;
        private static final byte CHARTYPE_EQUAL = (byte) 11;
        private static final byte CHARTYPE_INVALID = (byte) 0;
        private static final byte CHARTYPE_LETTER = (byte) 12;
        private static final byte CHARTYPE_MINUS = (byte) 6;
        private static final byte CHARTYPE_NONASCII = (byte) 14;
        private static final byte CHARTYPE_OPEN_PAREN = (byte) 4;
        private static final byte CHARTYPE_OTHER = (byte) 1;
        private static final byte CHARTYPE_PERIOD = (byte) 7;
        private static final byte CHARTYPE_SLASH = (byte) 8;
        private static final byte CHARTYPE_UNDERSCORE = (byte) 13;
        private static final byte CHARTYPE_WHITESPACE = (byte) 2;
        private final byte[] fASCIICharMap;
        private SymbolTable fSymbolTable;

        private Scanner(SymbolTable symbolTable) {
            byte[] bArr = new byte[128];
            bArr[9] = CHARTYPE_WHITESPACE;
            bArr[10] = CHARTYPE_WHITESPACE;
            bArr[13] = CHARTYPE_WHITESPACE;
            bArr[32] = CHARTYPE_WHITESPACE;
            bArr[33] = CHARTYPE_OTHER;
            bArr[34] = CHARTYPE_OTHER;
            bArr[35] = CHARTYPE_OTHER;
            bArr[36] = CHARTYPE_OTHER;
            bArr[37] = CHARTYPE_OTHER;
            bArr[38] = CHARTYPE_OTHER;
            bArr[39] = CHARTYPE_OTHER;
            bArr[40] = CHARTYPE_OPEN_PAREN;
            bArr[41] = CHARTYPE_CLOSE_PAREN;
            bArr[42] = CHARTYPE_OTHER;
            bArr[43] = CHARTYPE_OTHER;
            bArr[44] = CHARTYPE_OTHER;
            bArr[45] = CHARTYPE_MINUS;
            bArr[46] = CHARTYPE_PERIOD;
            bArr[47] = CHARTYPE_SLASH;
            bArr[48] = CHARTYPE_DIGIT;
            bArr[49] = CHARTYPE_DIGIT;
            bArr[50] = CHARTYPE_DIGIT;
            bArr[51] = CHARTYPE_DIGIT;
            bArr[52] = CHARTYPE_DIGIT;
            bArr[53] = CHARTYPE_DIGIT;
            bArr[54] = CHARTYPE_DIGIT;
            bArr[55] = CHARTYPE_DIGIT;
            bArr[56] = CHARTYPE_DIGIT;
            bArr[57] = CHARTYPE_DIGIT;
            bArr[58] = CHARTYPE_COLON;
            bArr[59] = CHARTYPE_OTHER;
            bArr[60] = CHARTYPE_OTHER;
            bArr[61] = CHARTYPE_EQUAL;
            bArr[62] = CHARTYPE_OTHER;
            bArr[63] = CHARTYPE_OTHER;
            bArr[64] = CHARTYPE_OTHER;
            bArr[65] = CHARTYPE_LETTER;
            bArr[66] = CHARTYPE_LETTER;
            bArr[67] = CHARTYPE_LETTER;
            bArr[68] = CHARTYPE_LETTER;
            bArr[69] = CHARTYPE_LETTER;
            bArr[70] = CHARTYPE_LETTER;
            bArr[71] = CHARTYPE_LETTER;
            bArr[72] = CHARTYPE_LETTER;
            bArr[73] = CHARTYPE_LETTER;
            bArr[74] = CHARTYPE_LETTER;
            bArr[75] = CHARTYPE_LETTER;
            bArr[76] = CHARTYPE_LETTER;
            bArr[77] = CHARTYPE_LETTER;
            bArr[78] = CHARTYPE_LETTER;
            bArr[79] = CHARTYPE_LETTER;
            bArr[80] = CHARTYPE_LETTER;
            bArr[81] = CHARTYPE_LETTER;
            bArr[82] = CHARTYPE_LETTER;
            bArr[83] = CHARTYPE_LETTER;
            bArr[84] = CHARTYPE_LETTER;
            bArr[85] = CHARTYPE_LETTER;
            bArr[86] = CHARTYPE_LETTER;
            bArr[87] = CHARTYPE_LETTER;
            bArr[88] = CHARTYPE_LETTER;
            bArr[89] = CHARTYPE_LETTER;
            bArr[90] = CHARTYPE_LETTER;
            bArr[91] = CHARTYPE_OTHER;
            bArr[92] = CHARTYPE_OTHER;
            bArr[93] = CHARTYPE_OTHER;
            bArr[94] = CHARTYPE_CARRET;
            bArr[95] = CHARTYPE_UNDERSCORE;
            bArr[96] = CHARTYPE_OTHER;
            bArr[97] = CHARTYPE_LETTER;
            bArr[98] = CHARTYPE_LETTER;
            bArr[99] = CHARTYPE_LETTER;
            bArr[100] = CHARTYPE_LETTER;
            bArr[101] = CHARTYPE_LETTER;
            bArr[102] = CHARTYPE_LETTER;
            bArr[103] = CHARTYPE_LETTER;
            bArr[104] = CHARTYPE_LETTER;
            bArr[105] = CHARTYPE_LETTER;
            bArr[106] = CHARTYPE_LETTER;
            bArr[107] = CHARTYPE_LETTER;
            bArr[108] = CHARTYPE_LETTER;
            bArr[109] = CHARTYPE_LETTER;
            bArr[110] = CHARTYPE_LETTER;
            bArr[111] = CHARTYPE_LETTER;
            bArr[112] = CHARTYPE_LETTER;
            bArr[113] = CHARTYPE_LETTER;
            bArr[114] = CHARTYPE_LETTER;
            bArr[115] = CHARTYPE_LETTER;
            bArr[116] = CHARTYPE_LETTER;
            bArr[117] = CHARTYPE_LETTER;
            bArr[118] = CHARTYPE_LETTER;
            bArr[119] = CHARTYPE_LETTER;
            bArr[120] = CHARTYPE_LETTER;
            bArr[121] = CHARTYPE_LETTER;
            bArr[122] = CHARTYPE_LETTER;
            bArr[123] = CHARTYPE_OTHER;
            bArr[124] = CHARTYPE_OTHER;
            bArr[125] = CHARTYPE_OTHER;
            bArr[126] = CHARTYPE_OTHER;
            bArr[127] = CHARTYPE_OTHER;
            this.fASCIICharMap = bArr;
            this.fSymbolTable = symbolTable;
        }

        private boolean scanExpr(SymbolTable symbolTable, Tokens tokens, String data, int currentOffset, int endOffset) throws XNIException {
            int openParen = 0;
            int closeParen = 0;
            String name = null;
            StringBuffer schemeDataBuff = new StringBuffer();
            while (currentOffset != endOffset) {
                int ch = data.charAt(currentOffset);
                while (true) {
                    if (ch == 32 || ch == 10 || ch == 9 || ch == 13) {
                        currentOffset++;
                        if (currentOffset != endOffset) {
                            ch = data.charAt(currentOffset);
                        }
                    }
                    if (currentOffset != endOffset) {
                        byte chartype;
                        if (ch >= 128) {
                            chartype = CHARTYPE_NONASCII;
                        } else {
                            chartype = this.fASCIICharMap[ch];
                        }
                        switch (chartype) {
                            case (byte) 1:
                            case (byte) 2:
                            case (byte) 3:
                            case (byte) 6:
                            case (byte) 7:
                            case (byte) 8:
                            case (byte) 9:
                            case (byte) 10:
                            case (byte) 11:
                            case (byte) 12:
                            case (byte) 13:
                            case (byte) 14:
                                if (openParen != 0) {
                                    if (openParen > 0 && closeParen == 0 && name != null) {
                                        int dataOffset = currentOffset;
                                        currentOffset = scanData(data, schemeDataBuff, endOffset, currentOffset);
                                        if (currentOffset != dataOffset) {
                                            if (currentOffset < endOffset) {
                                                ch = data.charAt(currentOffset);
                                            }
                                            String schemeData = symbolTable.addSymbol(schemeDataBuff.toString());
                                            addToken(tokens, 4);
                                            tokens.addToken(schemeData);
                                            openParen = 0;
                                            schemeDataBuff.delete(0, schemeDataBuff.length());
                                            break;
                                        }
                                        XPointerHandler.this.reportError("InvalidSchemeDataInXPointer", new Object[]{data});
                                        return false;
                                    }
                                    return false;
                                }
                                int nameOffset = currentOffset;
                                currentOffset = scanNCName(data, endOffset, currentOffset);
                                if (currentOffset != nameOffset) {
                                    if (currentOffset < endOffset) {
                                        ch = data.charAt(currentOffset);
                                    } else {
                                        ch = -1;
                                    }
                                    name = symbolTable.addSymbol(data.substring(nameOffset, currentOffset));
                                    String prefix = XMLSymbols.EMPTY_STRING;
                                    if (ch == 58) {
                                        currentOffset++;
                                        if (currentOffset == endOffset) {
                                            return false;
                                        }
                                        ch = data.charAt(currentOffset);
                                        prefix = name;
                                        nameOffset = currentOffset;
                                        currentOffset = scanNCName(data, endOffset, currentOffset);
                                        if (currentOffset == nameOffset) {
                                            return false;
                                        }
                                        if (currentOffset < endOffset) {
                                            ch = data.charAt(currentOffset);
                                        }
                                        name = symbolTable.addSymbol(data.substring(nameOffset, currentOffset));
                                    }
                                    if (currentOffset != endOffset) {
                                        addToken(tokens, 3);
                                        tokens.addToken(prefix);
                                        tokens.addToken(name);
                                    } else if (currentOffset == endOffset) {
                                        addToken(tokens, 2);
                                        tokens.addToken(name);
                                    }
                                    closeParen = 0;
                                    break;
                                }
                                XPointerHandler.this.reportError("InvalidShortHandPointer", new Object[]{data});
                                return false;
                            case (byte) 4:
                                addToken(tokens, 0);
                                openParen++;
                                currentOffset++;
                                break;
                            case (byte) 5:
                                addToken(tokens, 1);
                                closeParen++;
                                currentOffset++;
                                break;
                            default:
                                break;
                        }
                    }
                    return true;
                }
            }
            return true;
        }

        private int scanNCName(String data, int endOffset, int currentOffset) {
            byte chartype;
            int ch = data.charAt(currentOffset);
            if (ch < 128) {
                chartype = this.fASCIICharMap[ch];
                if (!(chartype == CHARTYPE_LETTER || chartype == CHARTYPE_UNDERSCORE)) {
                    return currentOffset;
                }
            } else if (!XMLChar.isNameStart(ch)) {
                return currentOffset;
            }
            while (true) {
                currentOffset++;
                if (currentOffset < endOffset) {
                    ch = data.charAt(currentOffset);
                    if (ch < 128) {
                        chartype = this.fASCIICharMap[ch];
                        if (!(chartype == CHARTYPE_LETTER || chartype == CHARTYPE_DIGIT || chartype == CHARTYPE_PERIOD || chartype == CHARTYPE_MINUS || chartype == CHARTYPE_UNDERSCORE)) {
                            break;
                        }
                    } else if (!XMLChar.isName(ch)) {
                        break;
                    }
                } else {
                    break;
                }
            }
            return currentOffset;
        }

        private int scanData(String data, StringBuffer schemeData, int endOffset, int currentOffset) {
            while (currentOffset != endOffset) {
                int ch = data.charAt(currentOffset);
                byte chartype = ch >= 128 ? CHARTYPE_NONASCII : this.fASCIICharMap[ch];
                if (chartype == CHARTYPE_OPEN_PAREN) {
                    schemeData.append(ch);
                    currentOffset = scanData(data, schemeData, endOffset, currentOffset + 1);
                    if (currentOffset == endOffset) {
                        return currentOffset;
                    }
                    ch = data.charAt(currentOffset);
                    if ((ch >= 128 ? CHARTYPE_NONASCII : this.fASCIICharMap[ch]) != CHARTYPE_CLOSE_PAREN) {
                        return endOffset;
                    }
                    schemeData.append((char) ch);
                    currentOffset++;
                } else if (chartype == CHARTYPE_CLOSE_PAREN) {
                    return currentOffset;
                } else {
                    if (chartype == CHARTYPE_CARRET) {
                        currentOffset++;
                        ch = data.charAt(currentOffset);
                        chartype = ch >= 128 ? CHARTYPE_NONASCII : this.fASCIICharMap[ch];
                        if (chartype != CHARTYPE_CARRET && chartype != CHARTYPE_OPEN_PAREN && chartype != CHARTYPE_CLOSE_PAREN) {
                            break;
                        }
                        schemeData.append((char) ch);
                        currentOffset++;
                    } else {
                        schemeData.append((char) ch);
                        currentOffset++;
                    }
                }
            }
            return currentOffset;
        }

        protected void addToken(Tokens tokens, int token) throws XNIException {
            tokens.addToken(token);
        }
    }

    private final class Tokens {
        private static final int INITIAL_TOKEN_COUNT = 256;
        private static final int XPTRTOKEN_CLOSE_PAREN = 1;
        private static final int XPTRTOKEN_OPEN_PAREN = 0;
        private static final int XPTRTOKEN_SCHEMEDATA = 4;
        private static final int XPTRTOKEN_SCHEMENAME = 3;
        private static final int XPTRTOKEN_SHORTHAND = 2;
        private int fCurrentTokenIndex;
        private SymbolTable fSymbolTable;
        private int fTokenCount;
        private HashMap fTokenNames;
        private int[] fTokens;
        private final String[] fgTokenNames;

        private Tokens(SymbolTable symbolTable) {
            this.fgTokenNames = new String[]{"XPTRTOKEN_OPEN_PAREN", "XPTRTOKEN_CLOSE_PAREN", "XPTRTOKEN_SHORTHAND", "XPTRTOKEN_SCHEMENAME", "XPTRTOKEN_SCHEMEDATA"};
            this.fTokens = new int[256];
            this.fTokenCount = 0;
            this.fTokenNames = new HashMap();
            this.fSymbolTable = symbolTable;
            this.fTokenNames.put(new Integer(0), "XPTRTOKEN_OPEN_PAREN");
            this.fTokenNames.put(new Integer(1), "XPTRTOKEN_CLOSE_PAREN");
            this.fTokenNames.put(new Integer(2), "XPTRTOKEN_SHORTHAND");
            this.fTokenNames.put(new Integer(3), "XPTRTOKEN_SCHEMENAME");
            this.fTokenNames.put(new Integer(4), "XPTRTOKEN_SCHEMEDATA");
        }

        private String getTokenString(int token) {
            return (String) this.fTokenNames.get(new Integer(token));
        }

        private void addToken(String tokenStr) {
            Integer tokenInt = (Integer) this.fTokenNames.get(tokenStr);
            if (tokenInt == null) {
                tokenInt = new Integer(this.fTokenNames.size());
                this.fTokenNames.put(tokenInt, tokenStr);
            }
            addToken(tokenInt.intValue());
        }

        private void addToken(int token) {
            try {
                this.fTokens[this.fTokenCount] = token;
            } catch (ArrayIndexOutOfBoundsException e) {
                int[] oldList = this.fTokens;
                this.fTokens = new int[(this.fTokenCount << 1)];
                System.arraycopy(oldList, 0, this.fTokens, 0, this.fTokenCount);
                this.fTokens[this.fTokenCount] = token;
            }
            this.fTokenCount++;
        }

        private void rewind() {
            this.fCurrentTokenIndex = 0;
        }

        private boolean hasMore() {
            return this.fCurrentTokenIndex < this.fTokenCount;
        }

        private int nextToken() throws XNIException {
            if (this.fCurrentTokenIndex == this.fTokenCount) {
                XPointerHandler.this.reportError("XPointerProcessingError", null);
            }
            int[] iArr = this.fTokens;
            int i = this.fCurrentTokenIndex;
            this.fCurrentTokenIndex = i + 1;
            return iArr[i];
        }

        private int peekToken() throws XNIException {
            if (this.fCurrentTokenIndex == this.fTokenCount) {
                XPointerHandler.this.reportError("XPointerProcessingError", null);
            }
            return this.fTokens[this.fCurrentTokenIndex];
        }

        private String nextTokenAsString() throws XNIException {
            String tokenStrint = getTokenString(nextToken());
            if (tokenStrint == null) {
                XPointerHandler.this.reportError("XPointerProcessingError", null);
            }
            return tokenStrint;
        }
    }

    public XPointerHandler() {
        this.fXPointerParts = null;
        this.fXPointerPart = null;
        this.fFoundMatchingPtrPart = false;
        this.fSymbolTable = null;
        this.ELEMENT_SCHEME_NAME = "element";
        this.fIsXPointerResolved = false;
        this.fFixupBase = false;
        this.fFixupLang = false;
        this.fXPointerParts = new ArrayList();
        this.fSymbolTable = new SymbolTable();
    }

    public XPointerHandler(SymbolTable symbolTable, XMLErrorHandler errorHandler, XMLErrorReporter errorReporter) {
        this.fXPointerParts = null;
        this.fXPointerPart = null;
        this.fFoundMatchingPtrPart = false;
        this.fSymbolTable = null;
        this.ELEMENT_SCHEME_NAME = "element";
        this.fIsXPointerResolved = false;
        this.fFixupBase = false;
        this.fFixupLang = false;
        this.fXPointerParts = new ArrayList();
        this.fSymbolTable = symbolTable;
        this.fErrorHandler = errorHandler;
        this.fXPointerErrorReporter = errorReporter;
    }

    public void setDocumentHandler(XMLDocumentHandler handler) {
        this.fDocumentHandler = handler;
    }

    public void parseXPointer(String xpointer) throws XNIException {
        init();
        Tokens tokens = new Tokens(this.fSymbolTable);
        if (!new Scanner(this.fSymbolTable) {
            protected void addToken(Tokens tokens, int token) throws XNIException {
                if (token == 0 || token == 1 || token == 3 || token == 4 || token == 2) {
                    super.addToken(tokens, token);
                    return;
                }
                XPointerHandler.this.reportError("InvalidXPointerToken", new Object[]{tokens.getTokenString(token)});
            }
        }.scanExpr(this.fSymbolTable, tokens, xpointer, 0, xpointer.length())) {
            reportError("InvalidXPointerExpression", new Object[]{xpointer});
        }
        while (tokens.hasMore()) {
            switch (tokens.nextToken()) {
                case 2:
                    String shortHandPointerName = tokens.getTokenString(tokens.nextToken());
                    if (shortHandPointerName == null) {
                        reportError("InvalidXPointerExpression", new Object[]{xpointer});
                    }
                    XPointerPart shortHandPointer = new ShortHandPointer(this.fSymbolTable);
                    shortHandPointer.setSchemeName(shortHandPointerName);
                    this.fXPointerParts.add(shortHandPointer);
                    break;
                case 3:
                    String prefix = tokens.getTokenString(tokens.nextToken());
                    String schemeName = new StringBuilder(String.valueOf(prefix)).append(tokens.getTokenString(tokens.nextToken())).toString();
                    int token = tokens.nextToken();
                    if (tokens.getTokenString(token) != "XPTRTOKEN_OPEN_PAREN") {
                        if (token == 2) {
                            reportError("MultipleShortHandPointers", new Object[]{xpointer});
                        } else {
                            reportError("InvalidXPointerExpression", new Object[]{xpointer});
                        }
                    }
                    int openParenCount = 0 + 1;
                    while (tokens.hasMore() && tokens.getTokenString(tokens.nextToken()) == "XPTRTOKEN_OPEN_PAREN") {
                        openParenCount++;
                    }
                    String schemeData = tokens.getTokenString(tokens.nextToken());
                    if (tokens.getTokenString(tokens.nextToken()) != "XPTRTOKEN_CLOSE_PAREN") {
                        reportError("SchemeDataNotFollowedByCloseParenthesis", new Object[]{xpointer});
                    }
                    int closeParenCount = 0 + 1;
                    while (tokens.hasMore() && tokens.getTokenString(tokens.peekToken()) == "XPTRTOKEN_OPEN_PAREN") {
                        closeParenCount++;
                    }
                    if (openParenCount != closeParenCount) {
                        reportError("UnbalancedParenthesisInXPointerExpression", new Object[]{xpointer, new Integer(openParenCount), new Integer(closeParenCount)});
                    }
                    if (!schemeName.equals("element")) {
                        reportWarning("SchemeUnsupported", new Object[]{schemeName});
                        break;
                    }
                    XPointerPart elementSchemePointer = new ElementSchemePointer(this.fSymbolTable, this.fErrorReporter);
                    elementSchemePointer.setSchemeName(schemeName);
                    elementSchemePointer.setSchemeData(schemeData);
                    try {
                        elementSchemePointer.parseXPointer(schemeData);
                        this.fXPointerParts.add(elementSchemePointer);
                        break;
                    } catch (Exception e) {
                        throw new XNIException(e);
                    }
                    break;
                default:
                    reportError("InvalidXPointerExpression", new Object[]{xpointer});
                    break;
            }
        }
    }

    public boolean resolveXPointer(QName element, XMLAttributes attributes, Augmentations augs, int event) throws XNIException {
        boolean resolved = false;
        if (!this.fFoundMatchingPtrPart) {
            for (int i = 0; i < this.fXPointerParts.size(); i++) {
                this.fXPointerPart = (XPointerPart) this.fXPointerParts.get(i);
                if (this.fXPointerPart.resolveXPointer(element, attributes, augs, event)) {
                    this.fFoundMatchingPtrPart = true;
                    resolved = true;
                }
            }
        } else if (this.fXPointerPart.resolveXPointer(element, attributes, augs, event)) {
            resolved = true;
        }
        if (!this.fIsXPointerResolved) {
            this.fIsXPointerResolved = resolved;
        }
        return resolved;
    }

    public boolean isFragmentResolved() throws XNIException {
        boolean resolved;
        if (this.fXPointerPart != null) {
            resolved = this.fXPointerPart.isFragmentResolved();
        } else {
            resolved = false;
        }
        if (!this.fIsXPointerResolved) {
            this.fIsXPointerResolved = resolved;
        }
        return resolved;
    }

    public boolean isChildFragmentResolved() throws XNIException {
        return this.fXPointerPart != null ? this.fXPointerPart.isChildFragmentResolved() : false;
    }

    public boolean isXPointerResolved() throws XNIException {
        return this.fIsXPointerResolved;
    }

    public XPointerPart getXPointerPart() {
        return this.fXPointerPart;
    }

    private void reportError(String key, Object[] arguments) throws XNIException {
        throw new XNIException(this.fErrorReporter.getMessageFormatter(XPointerMessageFormatter.XPOINTER_DOMAIN).formatMessage(this.fErrorReporter.getLocale(), key, arguments));
    }

    private void reportWarning(String key, Object[] arguments) throws XNIException {
        this.fXPointerErrorReporter.reportError(XPointerMessageFormatter.XPOINTER_DOMAIN, key, arguments, (short) 0);
    }

    protected void initErrorReporter() {
        if (this.fXPointerErrorReporter == null) {
            this.fXPointerErrorReporter = new XMLErrorReporter();
        }
        if (this.fErrorHandler == null) {
            this.fErrorHandler = new XPointerErrorHandler();
        }
        this.fXPointerErrorReporter.putMessageFormatter(XPointerMessageFormatter.XPOINTER_DOMAIN, new XPointerMessageFormatter());
    }

    protected void init() {
        this.fXPointerParts.clear();
        this.fXPointerPart = null;
        this.fFoundMatchingPtrPart = false;
        this.fIsXPointerResolved = false;
        initErrorReporter();
    }

    public ArrayList getPointerParts() {
        return this.fXPointerParts;
    }

    public void comment(XMLString text, Augmentations augs) throws XNIException {
        if (isChildFragmentResolved()) {
            super.comment(text, augs);
        }
    }

    public void processingInstruction(String target, XMLString data, Augmentations augs) throws XNIException {
        if (isChildFragmentResolved()) {
            super.processingInstruction(target, data, augs);
        }
    }

    public void startElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
        if (resolveXPointer(element, attributes, augs, 0)) {
            super.startElement(element, attributes, augs);
            return;
        }
        if (this.fFixupBase) {
            processXMLBaseAttributes(attributes);
        }
        if (this.fFixupLang) {
            processXMLLangAttributes(attributes);
        }
        this.fNamespaceContext.setContextInvalid();
    }

    public void emptyElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
        if (resolveXPointer(element, attributes, augs, 2)) {
            super.emptyElement(element, attributes, augs);
            return;
        }
        if (this.fFixupBase) {
            processXMLBaseAttributes(attributes);
        }
        if (this.fFixupLang) {
            processXMLLangAttributes(attributes);
        }
        this.fNamespaceContext.setContextInvalid();
    }

    public void characters(XMLString text, Augmentations augs) throws XNIException {
        if (isChildFragmentResolved()) {
            super.characters(text, augs);
        }
    }

    public void ignorableWhitespace(XMLString text, Augmentations augs) throws XNIException {
        if (isChildFragmentResolved()) {
            super.ignorableWhitespace(text, augs);
        }
    }

    public void endElement(QName element, Augmentations augs) throws XNIException {
        if (resolveXPointer(element, null, augs, 1)) {
            super.endElement(element, augs);
        }
    }

    public void startCDATA(Augmentations augs) throws XNIException {
        if (isChildFragmentResolved()) {
            super.startCDATA(augs);
        }
    }

    public void endCDATA(Augmentations augs) throws XNIException {
        if (isChildFragmentResolved()) {
            super.endCDATA(augs);
        }
    }

    public void setProperty(String propertyId, Object value) throws XMLConfigurationException {
        if (propertyId == "http://apache.org/xml/properties/internal/error-reporter") {
            if (value != null) {
                this.fXPointerErrorReporter = (XMLErrorReporter) value;
            } else {
                this.fXPointerErrorReporter = null;
            }
        }
        if (propertyId == "http://apache.org/xml/properties/internal/error-handler") {
            if (value != null) {
                this.fErrorHandler = (XMLErrorHandler) value;
            } else {
                this.fErrorHandler = null;
            }
        }
        if (propertyId == "http://apache.org/xml/features/xinclude/fixup-language") {
            if (value != null) {
                this.fFixupLang = ((Boolean) value).booleanValue();
            } else {
                this.fFixupLang = false;
            }
        }
        if (propertyId == "http://apache.org/xml/features/xinclude/fixup-base-uris") {
            if (value != null) {
                this.fFixupBase = ((Boolean) value).booleanValue();
            } else {
                this.fFixupBase = false;
            }
        }
        if (propertyId == "http://apache.org/xml/properties/internal/namespace-context") {
            this.fNamespaceContext = (XIncludeNamespaceSupport) value;
        }
        super.setProperty(propertyId, value);
    }
}
