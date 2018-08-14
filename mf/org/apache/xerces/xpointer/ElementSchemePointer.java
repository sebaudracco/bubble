package mf.org.apache.xerces.xpointer;

import java.util.HashMap;
import mf.org.apache.xerces.impl.XMLErrorReporter;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.xni.Augmentations;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xni.XMLAttributes;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.parser.XMLErrorHandler;

final class ElementSchemePointer implements XPointerPart {
    private int[] fChildSequence;
    private int fCurrentChildDepth = 0;
    private int fCurrentChildPosition = 1;
    private int[] fCurrentChildSequence;
    protected XMLErrorHandler fErrorHandler;
    protected XMLErrorReporter fErrorReporter;
    int fFoundDepth = 0;
    private boolean fIsElementFound = false;
    private boolean fIsFragmentResolved = false;
    private boolean fIsResolveElement = false;
    boolean fIsShortHand = false;
    private String fSchemeData;
    private String fSchemeName;
    private ShortHandPointer fShortHandPointer;
    private String fShortHandPointerName;
    private SymbolTable fSymbolTable;
    private boolean fWasOnlyEmptyElementFound = false;

    private class Scanner {
        private static final byte CHARTYPE_DIGIT = (byte) 5;
        private static final byte CHARTYPE_INVALID = (byte) 0;
        private static final byte CHARTYPE_LETTER = (byte) 6;
        private static final byte CHARTYPE_MINUS = (byte) 2;
        private static final byte CHARTYPE_NONASCII = (byte) 8;
        private static final byte CHARTYPE_OTHER = (byte) 1;
        private static final byte CHARTYPE_PERIOD = (byte) 3;
        private static final byte CHARTYPE_SLASH = (byte) 4;
        private static final byte CHARTYPE_UNDERSCORE = (byte) 7;
        private final byte[] fASCIICharMap;
        private SymbolTable fSymbolTable;

        private Scanner(SymbolTable symbolTable) {
            byte[] bArr = new byte[128];
            bArr[9] = CHARTYPE_OTHER;
            bArr[10] = CHARTYPE_OTHER;
            bArr[13] = CHARTYPE_OTHER;
            bArr[32] = CHARTYPE_OTHER;
            bArr[33] = CHARTYPE_OTHER;
            bArr[34] = CHARTYPE_OTHER;
            bArr[35] = CHARTYPE_OTHER;
            bArr[36] = CHARTYPE_OTHER;
            bArr[37] = CHARTYPE_OTHER;
            bArr[38] = CHARTYPE_OTHER;
            bArr[39] = CHARTYPE_OTHER;
            bArr[40] = CHARTYPE_OTHER;
            bArr[41] = CHARTYPE_OTHER;
            bArr[42] = CHARTYPE_OTHER;
            bArr[43] = CHARTYPE_OTHER;
            bArr[44] = CHARTYPE_OTHER;
            bArr[45] = CHARTYPE_MINUS;
            bArr[46] = CHARTYPE_MINUS;
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
            bArr[58] = CHARTYPE_OTHER;
            bArr[59] = CHARTYPE_OTHER;
            bArr[60] = CHARTYPE_OTHER;
            bArr[61] = CHARTYPE_OTHER;
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
            bArr[94] = CHARTYPE_OTHER;
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
            while (currentOffset != endOffset) {
                byte chartype;
                int ch = data.charAt(currentOffset);
                if (ch >= 128) {
                    chartype = CHARTYPE_NONASCII;
                } else {
                    chartype = this.fASCIICharMap[ch];
                }
                switch (chartype) {
                    case (byte) 1:
                    case (byte) 2:
                    case (byte) 3:
                    case (byte) 5:
                    case (byte) 6:
                    case (byte) 7:
                    case (byte) 8:
                        int nameOffset = currentOffset;
                        currentOffset = scanNCName(data, endOffset, currentOffset);
                        if (currentOffset != nameOffset) {
                            if (currentOffset < endOffset) {
                                ch = data.charAt(currentOffset);
                            }
                            String nameHandle = symbolTable.addSymbol(data.substring(nameOffset, currentOffset));
                            addToken(tokens, 0);
                            tokens.addToken(nameHandle);
                            break;
                        }
                        ElementSchemePointer.this.reportError("InvalidNCNameInElementSchemeData", new Object[]{data});
                        return false;
                    case (byte) 4:
                        currentOffset++;
                        if (currentOffset != endOffset) {
                            addToken(tokens, 1);
                            ch = data.charAt(currentOffset);
                            int child = 0;
                            while (ch >= 48 && ch <= 57) {
                                child = (child * 10) + (ch - 48);
                                currentOffset++;
                                if (currentOffset != endOffset) {
                                    ch = data.charAt(currentOffset);
                                }
                            }
                            if (child != 0) {
                                tokens.addToken(child);
                                break;
                            }
                            ElementSchemePointer.this.reportError("InvalidChildSequenceCharacter", new Object[]{new Character((char) ch)});
                            return false;
                        }
                        return false;
                    default:
                        break;
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

        protected void addToken(Tokens tokens, int token) throws XNIException {
            tokens.addToken(token);
        }
    }

    private final class Tokens {
        private static final int INITIAL_TOKEN_COUNT = 256;
        private static final int XPTRTOKEN_ELEM_CHILD = 1;
        private static final int XPTRTOKEN_ELEM_NCNAME = 0;
        private int fCurrentTokenIndex;
        private SymbolTable fSymbolTable;
        private int fTokenCount;
        private HashMap fTokenNames;
        private int[] fTokens;
        private final String[] fgTokenNames;

        private Tokens(SymbolTable symbolTable) {
            this.fgTokenNames = new String[]{"XPTRTOKEN_ELEM_NCNAME", "XPTRTOKEN_ELEM_CHILD"};
            this.fTokens = new int[256];
            this.fTokenCount = 0;
            this.fTokenNames = new HashMap();
            this.fSymbolTable = symbolTable;
            this.fTokenNames.put(new Integer(0), "XPTRTOKEN_ELEM_NCNAME");
            this.fTokenNames.put(new Integer(1), "XPTRTOKEN_ELEM_CHILD");
        }

        private String getTokenString(int token) {
            return (String) this.fTokenNames.get(new Integer(token));
        }

        private Integer getToken(int token) {
            return (Integer) this.fTokenNames.get(new Integer(token));
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
                ElementSchemePointer.this.reportError("XPointerElementSchemeProcessingError", null);
            }
            int[] iArr = this.fTokens;
            int i = this.fCurrentTokenIndex;
            this.fCurrentTokenIndex = i + 1;
            return iArr[i];
        }

        private int peekToken() throws XNIException {
            if (this.fCurrentTokenIndex == this.fTokenCount) {
                ElementSchemePointer.this.reportError("XPointerElementSchemeProcessingError", null);
            }
            return this.fTokens[this.fCurrentTokenIndex];
        }

        private String nextTokenAsString() throws XNIException {
            String s = getTokenString(nextToken());
            if (s == null) {
                ElementSchemePointer.this.reportError("XPointerElementSchemeProcessingError", null);
            }
            return s;
        }

        private int getTokenCount() {
            return this.fTokenCount;
        }
    }

    public ElementSchemePointer(SymbolTable symbolTable) {
        this.fSymbolTable = symbolTable;
    }

    public ElementSchemePointer(SymbolTable symbolTable, XMLErrorReporter errorReporter) {
        this.fSymbolTable = symbolTable;
        this.fErrorReporter = errorReporter;
    }

    public void parseXPointer(String xpointer) throws XNIException {
        init();
        Tokens tokens = new Tokens(this.fSymbolTable);
        if (!new Scanner(this.fSymbolTable) {
            protected void addToken(Tokens tokens, int token) throws XNIException {
                if (token == 1 || token == 0) {
                    super.addToken(tokens, token);
                    return;
                }
                ElementSchemePointer.this.reportError("InvalidElementSchemeToken", new Object[]{tokens.getTokenString(token)});
            }
        }.scanExpr(this.fSymbolTable, tokens, xpointer, 0, xpointer.length())) {
            reportError("InvalidElementSchemeXPointer", new Object[]{xpointer});
        }
        int[] tmpChildSequence = new int[((tokens.getTokenCount() / 2) + 1)];
        int i = 0;
        while (tokens.hasMore()) {
            switch (tokens.nextToken()) {
                case 0:
                    this.fShortHandPointerName = tokens.getTokenString(tokens.nextToken());
                    this.fShortHandPointer = new ShortHandPointer(this.fSymbolTable);
                    this.fShortHandPointer.setSchemeName(this.fShortHandPointerName);
                    break;
                case 1:
                    tmpChildSequence[i] = tokens.nextToken();
                    i++;
                    break;
                default:
                    reportError("InvalidElementSchemeXPointer", new Object[]{xpointer});
                    break;
            }
        }
        this.fChildSequence = new int[i];
        this.fCurrentChildSequence = new int[i];
        System.arraycopy(tmpChildSequence, 0, this.fChildSequence, 0, i);
    }

    public String getSchemeName() {
        return this.fSchemeName;
    }

    public String getSchemeData() {
        return this.fSchemeData;
    }

    public void setSchemeName(String schemeName) {
        this.fSchemeName = schemeName;
    }

    public void setSchemeData(String schemeData) {
        this.fSchemeData = schemeData;
    }

    public boolean resolveXPointer(QName element, XMLAttributes attributes, Augmentations augs, int event) throws XNIException {
        boolean isShortHandPointerResolved = false;
        if (this.fShortHandPointerName != null) {
            isShortHandPointerResolved = this.fShortHandPointer.resolveXPointer(element, attributes, augs, event);
            if (isShortHandPointerResolved) {
                this.fIsResolveElement = true;
                this.fIsShortHand = true;
            } else {
                this.fIsResolveElement = false;
            }
        } else {
            this.fIsResolveElement = true;
        }
        if (this.fChildSequence.length > 0) {
            this.fIsFragmentResolved = matchChildSequence(element, event);
        } else if (!isShortHandPointerResolved || this.fChildSequence.length > 0) {
            this.fIsFragmentResolved = false;
        } else {
            this.fIsFragmentResolved = isShortHandPointerResolved;
        }
        return this.fIsFragmentResolved;
    }

    protected boolean matchChildSequence(QName element, int event) throws XNIException {
        if (this.fCurrentChildDepth >= this.fCurrentChildSequence.length) {
            int[] tmpCurrentChildSequence = new int[this.fCurrentChildSequence.length];
            System.arraycopy(this.fCurrentChildSequence, 0, tmpCurrentChildSequence, 0, this.fCurrentChildSequence.length);
            this.fCurrentChildSequence = new int[(this.fCurrentChildDepth * 2)];
            System.arraycopy(tmpCurrentChildSequence, 0, this.fCurrentChildSequence, 0, tmpCurrentChildSequence.length);
        }
        if (this.fIsResolveElement) {
            if (event == 0) {
                this.fCurrentChildSequence[this.fCurrentChildDepth] = this.fCurrentChildPosition;
                this.fCurrentChildDepth++;
                this.fCurrentChildPosition = 1;
                if (this.fCurrentChildDepth <= this.fFoundDepth || this.fFoundDepth == 0) {
                    if (checkMatch()) {
                        this.fIsElementFound = true;
                        this.fFoundDepth = this.fCurrentChildDepth;
                    } else {
                        this.fIsElementFound = false;
                        this.fFoundDepth = 0;
                    }
                }
            } else if (event == 1) {
                if (this.fCurrentChildDepth == this.fFoundDepth) {
                    this.fIsElementFound = true;
                } else if ((this.fCurrentChildDepth < this.fFoundDepth && this.fFoundDepth != 0) || (this.fCurrentChildDepth > this.fFoundDepth && this.fFoundDepth == 0)) {
                    this.fIsElementFound = false;
                }
                this.fCurrentChildSequence[this.fCurrentChildDepth] = 0;
                this.fCurrentChildDepth--;
                this.fCurrentChildPosition = this.fCurrentChildSequence[this.fCurrentChildDepth] + 1;
            } else if (event == 2) {
                this.fCurrentChildSequence[this.fCurrentChildDepth] = this.fCurrentChildPosition;
                this.fCurrentChildPosition++;
                if (checkMatch()) {
                    if (this.fIsElementFound) {
                        this.fWasOnlyEmptyElementFound = false;
                    } else {
                        this.fWasOnlyEmptyElementFound = true;
                    }
                    this.fIsElementFound = true;
                } else {
                    this.fIsElementFound = false;
                    this.fWasOnlyEmptyElementFound = false;
                }
            }
        }
        return this.fIsElementFound;
    }

    protected boolean checkMatch() {
        int i;
        if (this.fIsShortHand) {
            if (this.fChildSequence.length > this.fCurrentChildDepth + 1) {
                return false;
            }
            i = 0;
            while (i < this.fChildSequence.length) {
                if (this.fCurrentChildSequence.length < i + 2 || this.fChildSequence[i] != this.fCurrentChildSequence[i + 1]) {
                    return false;
                }
                i++;
            }
        } else if (this.fChildSequence.length > this.fCurrentChildDepth + 1) {
            return false;
        } else {
            for (i = 0; i < this.fChildSequence.length; i++) {
                if (this.fChildSequence[i] != this.fCurrentChildSequence[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isFragmentResolved() throws XNIException {
        return this.fIsFragmentResolved;
    }

    public boolean isChildFragmentResolved() {
        if (this.fIsShortHand && this.fShortHandPointer != null && this.fChildSequence.length <= 0) {
            return this.fShortHandPointer.isChildFragmentResolved();
        }
        if (this.fWasOnlyEmptyElementFound) {
            if (this.fWasOnlyEmptyElementFound) {
                return false;
            }
            return true;
        } else if (!this.fIsFragmentResolved || this.fCurrentChildDepth < this.fFoundDepth) {
            return false;
        } else {
            return true;
        }
    }

    protected void reportError(String key, Object[] arguments) throws XNIException {
        throw new XNIException(this.fErrorReporter.getMessageFormatter(XPointerMessageFormatter.XPOINTER_DOMAIN).formatMessage(this.fErrorReporter.getLocale(), key, arguments));
    }

    protected void initErrorReporter() {
        if (this.fErrorReporter == null) {
            this.fErrorReporter = new XMLErrorReporter();
        }
        if (this.fErrorHandler == null) {
            this.fErrorHandler = new XPointerErrorHandler();
        }
        this.fErrorReporter.putMessageFormatter(XPointerMessageFormatter.XPOINTER_DOMAIN, new XPointerMessageFormatter());
    }

    protected void init() {
        this.fSchemeName = null;
        this.fSchemeData = null;
        this.fShortHandPointerName = null;
        this.fIsResolveElement = false;
        this.fIsElementFound = false;
        this.fWasOnlyEmptyElementFound = false;
        this.fFoundDepth = 0;
        this.fCurrentChildPosition = 1;
        this.fCurrentChildDepth = 0;
        this.fIsFragmentResolved = false;
        this.fShortHandPointer = null;
        initErrorReporter();
    }
}
