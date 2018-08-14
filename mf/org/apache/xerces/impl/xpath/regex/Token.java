package mf.org.apache.xerces.impl.xpath.regex;

import com.google.android.gms.stats.netstats.NetstatsParserPatterns;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Vector;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.slf4j.Marker;

class Token implements Serializable {
    static final int ANCHOR = 8;
    static final int BACKREFERENCE = 12;
    static final int CHAR = 0;
    static final int CHAR_FINAL_QUOTE = 30;
    static final int CHAR_INIT_QUOTE = 29;
    static final int CHAR_LETTER = 31;
    static final int CHAR_MARK = 32;
    static final int CHAR_NUMBER = 33;
    static final int CHAR_OTHER = 35;
    static final int CHAR_PUNCTUATION = 36;
    static final int CHAR_SEPARATOR = 34;
    static final int CHAR_SYMBOL = 37;
    static final int CLOSURE = 3;
    static final int CONCAT = 1;
    static final int CONDITION = 26;
    static final boolean COUNTTOKENS = true;
    static final int DOT = 11;
    static final int EMPTY = 7;
    static final int FC_ANY = 2;
    static final int FC_CONTINUE = 0;
    static final int FC_TERMINAL = 1;
    static final int INDEPENDENT = 24;
    static final int LOOKAHEAD = 20;
    static final int LOOKBEHIND = 22;
    static final int MODIFIERGROUP = 25;
    static final int NEGATIVELOOKAHEAD = 21;
    static final int NEGATIVELOOKBEHIND = 23;
    private static final int NONBMP_BLOCK_START = 84;
    static final int NONGREEDYCLOSURE = 9;
    static final int NRANGE = 5;
    static final int PAREN = 6;
    static final int RANGE = 4;
    static final int STRING = 10;
    static final int UNION = 2;
    static final int UTF16_MAX = 1114111;
    private static final String[] blockNames = new String[]{"Basic Latin", "Latin-1 Supplement", "Latin Extended-A", "Latin Extended-B", "IPA Extensions", "Spacing Modifier Letters", "Combining Diacritical Marks", "Greek", "Cyrillic", "Armenian", "Hebrew", "Arabic", "Syriac", "Thaana", "Devanagari", "Bengali", "Gurmukhi", "Gujarati", "Oriya", "Tamil", "Telugu", "Kannada", "Malayalam", "Sinhala", "Thai", "Lao", "Tibetan", "Myanmar", "Georgian", "Hangul Jamo", "Ethiopic", "Cherokee", "Unified Canadian Aboriginal Syllabics", "Ogham", "Runic", "Khmer", "Mongolian", "Latin Extended Additional", "Greek Extended", "General Punctuation", "Superscripts and Subscripts", "Currency Symbols", "Combining Marks for Symbols", "Letterlike Symbols", "Number Forms", "Arrows", "Mathematical Operators", "Miscellaneous Technical", "Control Pictures", "Optical Character Recognition", "Enclosed Alphanumerics", "Box Drawing", "Block Elements", "Geometric Shapes", "Miscellaneous Symbols", "Dingbats", "Braille Patterns", "CJK Radicals Supplement", "Kangxi Radicals", "Ideographic Description Characters", "CJK Symbols and Punctuation", "Hiragana", "Katakana", "Bopomofo", "Hangul Compatibility Jamo", "Kanbun", "Bopomofo Extended", "Enclosed CJK Letters and Months", "CJK Compatibility", "CJK Unified Ideographs Extension A", "CJK Unified Ideographs", "Yi Syllables", "Yi Radicals", "Hangul Syllables", "Private Use", "CJK Compatibility Ideographs", "Alphabetic Presentation Forms", "Arabic Presentation Forms-A", "Combining Half Marks", "CJK Compatibility Forms", "Small Form Variants", "Arabic Presentation Forms-B", "Specials", "Halfwidth and Fullwidth Forms", "Old Italic", "Gothic", "Deseret", "Byzantine Musical Symbols", "Musical Symbols", "Mathematical Alphanumeric Symbols", "CJK Unified Ideographs Extension B", "CJK Compatibility Ideographs Supplement", "Tags"};
    static final String blockRanges = "\u0000ÿĀſƀɏɐʯʰ˿̀ͯͰϿЀӿ԰֏֐׿؀ۿ܀ݏހ޿ऀॿঀ৿਀੿઀૿଀୿஀௿ఀ౿ಀ೿ഀൿ඀෿฀๿຀໿ༀ࿿က႟Ⴀჿᄀᇿሀ፿Ꭰ᏿᐀ᙿ ᚟ᚠ᛿ក៿᠀᢯Ḁỿἀ῿ ⁯⁰₟₠⃏⃐⃿℀⅏⅐↏←⇿∀⋿⌀⏿␀␿⑀⑟①⓿─╿▀▟■◿☀⛿✀➿⠀⣿⺀⻿⼀⿟⿰⿿　〿぀ゟ゠ヿ㄀ㄯ㄰㆏㆐㆟ㆠㆿ㈀㋿㌀㏿㐀䶵一鿿ꀀ꒏꒐꓏가힣豈﫿ﬀﭏﭐ﷿︠︯︰﹏﹐﹯ﹰ﻾﻿﻿＀￯";
    private static final Hashtable categories = new Hashtable();
    private static final Hashtable categories2 = new Hashtable();
    private static final String[] categoryNames;
    static final int[] nonBMPBlockRanges = new int[]{66304, 66351, 66352, 66383, 66560, 66639, 118784, 119039, 119040, 119295, 119808, 120831, 131072, 173782, 194560, 195103, 917504, 917631};
    static Hashtable nonxs = null;
    private static final long serialVersionUID = 8484976002585487481L;
    static Token token_0to9 = createRange();
    private static Token token_ccs = null;
    static Token token_dot = new Token(11);
    static Token token_empty = new Token(7);
    private static Token token_grapheme = null;
    static Token token_linebeginning = createAnchor(94);
    static Token token_linebeginning2 = createAnchor(64);
    static Token token_lineend = createAnchor(36);
    static Token token_not_0to9 = complementRanges(token_0to9);
    static Token token_not_spaces = complementRanges(token_spaces);
    static Token token_not_wordchars = complementRanges(token_wordchars);
    static Token token_not_wordedge = createAnchor(66);
    static Token token_spaces = createRange();
    static Token token_stringbeginning = createAnchor(65);
    static Token token_stringend = createAnchor(122);
    static Token token_stringend2 = createAnchor(90);
    static Token token_wordbeginning = createAnchor(60);
    static Token token_wordchars = createRange();
    static Token token_wordedge = createAnchor(98);
    static Token token_wordend = createAnchor(62);
    static int tokens = 0;
    static final String viramaString = "्্੍્୍்్್്ฺ྄";
    final int type;

    static class CharToken extends Token implements Serializable {
        private static final long serialVersionUID = -4394272816279496989L;
        final int chardata;

        CharToken(int type, int ch) {
            super(type);
            this.chardata = ch;
        }

        int getChar() {
            return this.chardata;
        }

        public String toString(int options) {
            switch (this.type) {
                case 0:
                    switch (this.chardata) {
                        case 9:
                            return "\\t";
                        case 10:
                            return "\\n";
                        case 12:
                            return "\\f";
                        case 13:
                            return "\\r";
                        case 27:
                            return "\\e";
                        case 40:
                        case 41:
                        case 42:
                        case 43:
                        case 46:
                        case 63:
                        case 91:
                        case 92:
                        case 123:
                        case 124:
                            return "\\" + ((char) this.chardata);
                        default:
                            if (this.chardata < 65536) {
                                return ((char) this.chardata);
                            }
                            String pre = new StringBuilder(SchemaSymbols.ATTVAL_FALSE_0).append(Integer.toHexString(this.chardata)).toString();
                            return "\\v" + pre.substring(pre.length() - 6, pre.length());
                    }
                case 8:
                    if (this == Token.token_linebeginning || this == Token.token_lineend) {
                        return ((char) this.chardata);
                    }
                    return "\\" + ((char) this.chardata);
                default:
                    return null;
            }
        }

        boolean match(int ch) {
            if (this.type == 0) {
                return ch == this.chardata;
            } else {
                throw new RuntimeException("NFAArrow#match(): Internal error: " + this.type);
            }
        }
    }

    static class ClosureToken extends Token implements Serializable {
        private static final long serialVersionUID = 1308971930673997452L;
        final Token child;
        int max;
        int min;

        ClosureToken(int type, Token tok) {
            super(type);
            this.child = tok;
            setMin(-1);
            setMax(-1);
        }

        int size() {
            return 1;
        }

        Token getChild(int index) {
            return this.child;
        }

        final void setMin(int min) {
            this.min = min;
        }

        final void setMax(int max) {
            this.max = max;
        }

        final int getMin() {
            return this.min;
        }

        final int getMax() {
            return this.max;
        }

        public String toString(int options) {
            if (this.type == 3) {
                if (getMin() < 0 && getMax() < 0) {
                    return new StringBuilder(String.valueOf(this.child.toString(options))).append(Marker.ANY_MARKER).toString();
                }
                if (getMin() == getMax()) {
                    return new StringBuilder(String.valueOf(this.child.toString(options))).append("{").append(getMin()).append("}").toString();
                }
                if (getMin() >= 0 && getMax() >= 0) {
                    return new StringBuilder(String.valueOf(this.child.toString(options))).append("{").append(getMin()).append(",").append(getMax()).append("}").toString();
                }
                if (getMin() >= 0 && getMax() < 0) {
                    return new StringBuilder(String.valueOf(this.child.toString(options))).append("{").append(getMin()).append(",}").toString();
                }
                throw new RuntimeException("Token#toString(): CLOSURE " + getMin() + ", " + getMax());
            } else if (getMin() < 0 && getMax() < 0) {
                return new StringBuilder(String.valueOf(this.child.toString(options))).append("*?").toString();
            } else {
                if (getMin() == getMax()) {
                    return new StringBuilder(String.valueOf(this.child.toString(options))).append("{").append(getMin()).append("}?").toString();
                }
                if (getMin() >= 0 && getMax() >= 0) {
                    return new StringBuilder(String.valueOf(this.child.toString(options))).append("{").append(getMin()).append(",").append(getMax()).append("}?").toString();
                }
                if (getMin() >= 0 && getMax() < 0) {
                    return new StringBuilder(String.valueOf(this.child.toString(options))).append("{").append(getMin()).append(",}?").toString();
                }
                throw new RuntimeException("Token#toString(): NONGREEDYCLOSURE " + getMin() + ", " + getMax());
            }
        }
    }

    static class ConcatToken extends Token implements Serializable {
        private static final long serialVersionUID = 8717321425541346381L;
        final Token child;
        final Token child2;

        ConcatToken(Token t1, Token t2) {
            super(1);
            this.child = t1;
            this.child2 = t2;
        }

        int size() {
            return 2;
        }

        Token getChild(int index) {
            return index == 0 ? this.child : this.child2;
        }

        public String toString(int options) {
            if (this.child2.type == 3 && this.child2.getChild(0) == this.child) {
                return new StringBuilder(String.valueOf(this.child.toString(options))).append(Marker.ANY_NON_NULL_MARKER).toString();
            }
            if (this.child2.type == 9 && this.child2.getChild(0) == this.child) {
                return new StringBuilder(String.valueOf(this.child.toString(options))).append("+?").toString();
            }
            return new StringBuilder(String.valueOf(this.child.toString(options))).append(this.child2.toString(options)).toString();
        }
    }

    static class ConditionToken extends Token implements Serializable {
        private static final long serialVersionUID = 4353765277910594411L;
        final Token condition;
        final Token no;
        final int refNumber;
        final Token yes;

        ConditionToken(int refno, Token cond, Token yespat, Token nopat) {
            super(26);
            this.refNumber = refno;
            this.condition = cond;
            this.yes = yespat;
            this.no = nopat;
        }

        int size() {
            return this.no == null ? 1 : 2;
        }

        Token getChild(int index) {
            if (index == 0) {
                return this.yes;
            }
            if (index == 1) {
                return this.no;
            }
            throw new RuntimeException("Internal Error: " + index);
        }

        public String toString(int options) {
            String ret;
            if (this.refNumber > 0) {
                ret = "(?(" + this.refNumber + ")";
            } else if (this.condition.type == 8) {
                ret = "(?(" + this.condition + ")";
            } else {
                ret = "(?" + this.condition;
            }
            if (this.no == null) {
                return new StringBuilder(String.valueOf(ret)).append(this.yes).append(")").toString();
            }
            return new StringBuilder(String.valueOf(ret)).append(this.yes).append("|").append(this.no).append(")").toString();
        }
    }

    static class FixedStringContainer {
        int options = 0;
        Token token = null;

        FixedStringContainer() {
        }
    }

    static class ModifierToken extends Token implements Serializable {
        private static final long serialVersionUID = -9114536559696480356L;
        final int add;
        final Token child;
        final int mask;

        ModifierToken(Token tok, int add, int mask) {
            super(25);
            this.child = tok;
            this.add = add;
            this.mask = mask;
        }

        int size() {
            return 1;
        }

        Token getChild(int index) {
            return this.child;
        }

        int getOptions() {
            return this.add;
        }

        int getOptionsMask() {
            return this.mask;
        }

        public String toString(int options) {
            return "(?" + (this.add == 0 ? "" : REUtil.createOptionString(this.add)) + (this.mask == 0 ? "" : REUtil.createOptionString(this.mask)) + ":" + this.child.toString(options) + ")";
        }
    }

    static class ParenToken extends Token implements Serializable {
        private static final long serialVersionUID = -5938014719827987704L;
        final Token child;
        final int parennumber;

        ParenToken(int type, Token tok, int paren) {
            super(type);
            this.child = tok;
            this.parennumber = paren;
        }

        int size() {
            return 1;
        }

        Token getChild(int index) {
            return this.child;
        }

        int getParenNumber() {
            return this.parennumber;
        }

        public String toString(int options) {
            switch (this.type) {
                case 6:
                    if (this.parennumber == 0) {
                        return "(?:" + this.child.toString(options) + ")";
                    }
                    return "(" + this.child.toString(options) + ")";
                case 20:
                    return "(?=" + this.child.toString(options) + ")";
                case 21:
                    return "(?!" + this.child.toString(options) + ")";
                case 22:
                    return "(?<=" + this.child.toString(options) + ")";
                case 23:
                    return "(?<!" + this.child.toString(options) + ")";
                case 24:
                    return "(?>" + this.child.toString(options) + ")";
                default:
                    return null;
            }
        }
    }

    static class StringToken extends Token implements Serializable {
        private static final long serialVersionUID = -4614366944218504172L;
        final int refNumber;
        String string;

        StringToken(int type, String str, int n) {
            super(type);
            this.string = str;
            this.refNumber = n;
        }

        int getReferenceNumber() {
            return this.refNumber;
        }

        String getString() {
            return this.string;
        }

        public String toString(int options) {
            if (this.type == 12) {
                return "\\" + this.refNumber;
            }
            return REUtil.quoteMeta(this.string);
        }
    }

    static class UnionToken extends Token implements Serializable {
        private static final long serialVersionUID = -2568843945989489861L;
        Vector children;

        UnionToken(int type) {
            super(type);
        }

        void addChild(Token tok) {
            int nextMaxLength = 2;
            if (tok != null) {
                if (this.children == null) {
                    this.children = new Vector();
                }
                if (this.type == 2) {
                    this.children.addElement(tok);
                } else if (tok.type == 1) {
                    for (int i = 0; i < tok.size(); i++) {
                        addChild(tok.getChild(i));
                    }
                } else {
                    int size = this.children.size();
                    if (size == 0) {
                        this.children.addElement(tok);
                        return;
                    }
                    Token previous = (Token) this.children.elementAt(size - 1);
                    if ((previous.type == 0 || previous.type == 10) && (tok.type == 0 || tok.type == 10)) {
                        StringBuffer buffer;
                        int ch;
                        if (tok.type != 0) {
                            nextMaxLength = tok.getString().length();
                        }
                        if (previous.type == 0) {
                            buffer = new StringBuffer(nextMaxLength + 2);
                            ch = previous.getChar();
                            if (ch >= 65536) {
                                buffer.append(REUtil.decomposeToSurrogates(ch));
                            } else {
                                buffer.append((char) ch);
                            }
                            previous = Token.createString(null);
                            this.children.setElementAt(previous, size - 1);
                        } else {
                            buffer = new StringBuffer(previous.getString().length() + nextMaxLength);
                            buffer.append(previous.getString());
                        }
                        if (tok.type == 0) {
                            ch = tok.getChar();
                            if (ch >= 65536) {
                                buffer.append(REUtil.decomposeToSurrogates(ch));
                            } else {
                                buffer.append((char) ch);
                            }
                        } else {
                            buffer.append(tok.getString());
                        }
                        ((StringToken) previous).string = new String(buffer);
                        return;
                    }
                    this.children.addElement(tok);
                }
            }
        }

        int size() {
            return this.children == null ? 0 : this.children.size();
        }

        Token getChild(int index) {
            return (Token) this.children.elementAt(index);
        }

        public String toString(int options) {
            String ret;
            StringBuffer sb;
            int i;
            if (this.type == 1) {
                if (this.children.size() == 2) {
                    Token ch = getChild(0);
                    Token ch2 = getChild(1);
                    if (ch2.type == 3 && ch2.getChild(0) == ch) {
                        ret = ch.toString(options) + Marker.ANY_NON_NULL_MARKER;
                    } else if (ch2.type == 9 && ch2.getChild(0) == ch) {
                        ret = ch.toString(options) + "+?";
                    } else {
                        ret = ch.toString(options) + ch2.toString(options);
                    }
                } else {
                    sb = new StringBuffer();
                    for (i = 0; i < this.children.size(); i++) {
                        sb.append(((Token) this.children.elementAt(i)).toString(options));
                    }
                    ret = new String(sb);
                }
                return ret;
            }
            if (this.children.size() == 2 && getChild(1).type == 7) {
                ret = new StringBuilder(String.valueOf(getChild(0).toString(options))).append("?").toString();
            } else if (this.children.size() == 2 && getChild(0).type == 7) {
                ret = new StringBuilder(String.valueOf(getChild(1).toString(options))).append("??").toString();
            } else {
                sb = new StringBuffer();
                sb.append(((Token) this.children.elementAt(0)).toString(options));
                for (i = 1; i < this.children.size(); i++) {
                    sb.append('|');
                    sb.append(((Token) this.children.elementAt(i)).toString(options));
                }
                ret = new String(sb);
            }
            return ret;
        }
    }

    static {
        token_0to9.addRange(48, 57);
        token_wordchars.addRange(48, 57);
        token_wordchars.addRange(65, 90);
        token_wordchars.addRange(95, 95);
        token_wordchars.addRange(97, 122);
        token_spaces.addRange(9, 9);
        token_spaces.addRange(10, 10);
        token_spaces.addRange(12, 12);
        token_spaces.addRange(13, 13);
        token_spaces.addRange(32, 32);
        String[] strArr = new String[38];
        strArr[0] = "Cn";
        strArr[1] = "Lu";
        strArr[2] = "Ll";
        strArr[3] = "Lt";
        strArr[4] = "Lm";
        strArr[5] = "Lo";
        strArr[6] = "Mn";
        strArr[7] = "Me";
        strArr[8] = "Mc";
        strArr[9] = "Nd";
        strArr[10] = "Nl";
        strArr[11] = "No";
        strArr[12] = "Zs";
        strArr[13] = "Zl";
        strArr[14] = "Zp";
        strArr[15] = "Cc";
        strArr[16] = "Cf";
        strArr[18] = "Co";
        strArr[19] = "Cs";
        strArr[20] = "Pd";
        strArr[21] = "Ps";
        strArr[22] = "Pe";
        strArr[23] = "Pc";
        strArr[24] = "Po";
        strArr[25] = "Sm";
        strArr[26] = "Sc";
        strArr[27] = "Sk";
        strArr[28] = "So";
        strArr[29] = "Pi";
        strArr[30] = "Pf";
        strArr[31] = "L";
        strArr[32] = "M";
        strArr[33] = "N";
        strArr[34] = "Z";
        strArr[35] = "C";
        strArr[36] = "P";
        strArr[37] = "S";
        categoryNames = strArr;
    }

    static ParenToken createLook(int type, Token child) {
        tokens++;
        return new ParenToken(type, child, 0);
    }

    static ParenToken createParen(Token child, int pnumber) {
        tokens++;
        return new ParenToken(6, child, pnumber);
    }

    static ClosureToken createClosure(Token tok) {
        tokens++;
        return new ClosureToken(3, tok);
    }

    static ClosureToken createNGClosure(Token tok) {
        tokens++;
        return new ClosureToken(9, tok);
    }

    static ConcatToken createConcat(Token tok1, Token tok2) {
        tokens++;
        return new ConcatToken(tok1, tok2);
    }

    static UnionToken createConcat() {
        tokens++;
        return new UnionToken(1);
    }

    static UnionToken createUnion() {
        tokens++;
        return new UnionToken(2);
    }

    static Token createEmpty() {
        return token_empty;
    }

    static RangeToken createRange() {
        tokens++;
        return new RangeToken(4);
    }

    static RangeToken createNRange() {
        tokens++;
        return new RangeToken(5);
    }

    static CharToken createChar(int ch) {
        tokens++;
        return new CharToken(0, ch);
    }

    private static CharToken createAnchor(int ch) {
        tokens++;
        return new CharToken(8, ch);
    }

    static StringToken createBackReference(int refno) {
        tokens++;
        return new StringToken(12, null, refno);
    }

    static StringToken createString(String str) {
        tokens++;
        return new StringToken(10, str, 0);
    }

    static ModifierToken createModifierGroup(Token child, int add, int mask) {
        tokens++;
        return new ModifierToken(child, add, mask);
    }

    static ConditionToken createCondition(int refno, Token condition, Token yespat, Token nopat) {
        tokens++;
        return new ConditionToken(refno, condition, yespat, nopat);
    }

    protected Token(int type) {
        this.type = type;
    }

    int size() {
        return 0;
    }

    Token getChild(int index) {
        return null;
    }

    void addChild(Token tok) {
        throw new RuntimeException("Not supported.");
    }

    protected void addRange(int start, int end) {
        throw new RuntimeException("Not supported.");
    }

    protected void sortRanges() {
        throw new RuntimeException("Not supported.");
    }

    protected void compactRanges() {
        throw new RuntimeException("Not supported.");
    }

    protected void mergeRanges(Token tok) {
        throw new RuntimeException("Not supported.");
    }

    protected void subtractRanges(Token tok) {
        throw new RuntimeException("Not supported.");
    }

    protected void intersectRanges(Token tok) {
        throw new RuntimeException("Not supported.");
    }

    static Token complementRanges(Token tok) {
        return RangeToken.complementRanges(tok);
    }

    void setMin(int min) {
    }

    void setMax(int max) {
    }

    int getMin() {
        return -1;
    }

    int getMax() {
        return -1;
    }

    int getReferenceNumber() {
        return 0;
    }

    String getString() {
        return null;
    }

    int getParenNumber() {
        return 0;
    }

    int getChar() {
        return -1;
    }

    public String toString() {
        return toString(0);
    }

    public String toString(int options) {
        return this.type == 11 ? "." : "";
    }

    final int getMinLength() {
        int i;
        switch (this.type) {
            case 0:
            case 4:
            case 5:
            case 11:
                return 1;
            case 1:
                int sum = 0;
                for (i = 0; i < size(); i++) {
                    sum += getChild(i).getMinLength();
                }
                return sum;
            case 2:
            case 26:
                if (size() == 0) {
                    return 0;
                }
                int ret = getChild(0).getMinLength();
                for (i = 1; i < size(); i++) {
                    int min = getChild(i).getMinLength();
                    if (min < ret) {
                        ret = min;
                    }
                }
                return ret;
            case 3:
            case 9:
                if (getMin() >= 0) {
                    return getMin() * getChild(0).getMinLength();
                }
                return 0;
            case 6:
            case 24:
            case 25:
                return getChild(0).getMinLength();
            case 7:
            case 8:
            case 12:
            case 20:
            case 21:
            case 22:
            case 23:
                return 0;
            case 10:
                return getString().length();
            default:
                throw new RuntimeException("Token#getMinLength(): Invalid Type: " + this.type);
        }
    }

    final int getMaxLength() {
        int i;
        switch (this.type) {
            case 0:
                return 1;
            case 1:
                int sum = 0;
                for (i = 0; i < size(); i++) {
                    int d = getChild(i).getMaxLength();
                    if (d < 0) {
                        return -1;
                    }
                    sum += d;
                }
                return sum;
            case 2:
            case 26:
                if (size() == 0) {
                    return 0;
                }
                int ret = getChild(0).getMaxLength();
                i = 1;
                while (ret >= 0 && i < size()) {
                    int max = getChild(i).getMaxLength();
                    if (max < 0) {
                        ret = -1;
                    } else {
                        if (max > ret) {
                            ret = max;
                        }
                        i++;
                    }
                }
                return ret;
            case 3:
            case 9:
                return getMax() >= 0 ? getMax() * getChild(0).getMaxLength() : -1;
            case 4:
            case 5:
            case 11:
                return 2;
            case 6:
            case 24:
            case 25:
                return getChild(0).getMaxLength();
            case 7:
            case 8:
            case 20:
            case 21:
            case 22:
            case 23:
                return 0;
            case 10:
                return getString().length();
            case 12:
                return -1;
            default:
                throw new RuntimeException("Token#getMaxLength(): Invalid Type: " + this.type);
        }
    }

    private static final boolean isSet(int options, int flag) {
        return (options & flag) == flag;
    }

    final int analyzeFirstCharacter(RangeToken result, int options) {
        int i;
        switch (this.type) {
            case 0:
                int ch = getChar();
                result.addRange(ch, ch);
                if (ch < 65536 && isSet(options, 2)) {
                    ch = Character.toUpperCase((char) ch);
                    result.addRange(ch, ch);
                    ch = Character.toLowerCase((char) ch);
                    result.addRange(ch, ch);
                }
                return 1;
            case 1:
                int ret = 0;
                for (i = 0; i < size(); i++) {
                    ret = getChild(i).analyzeFirstCharacter(result, options);
                    if (ret != 0) {
                        return ret;
                    }
                }
                return ret;
            case 2:
                if (size() == 0) {
                    return 0;
                }
                int ret2 = 0;
                boolean hasEmpty = false;
                i = 0;
                while (i < size()) {
                    ret2 = getChild(i).analyzeFirstCharacter(result, options);
                    if (ret2 != 2) {
                        if (ret2 == 0) {
                            hasEmpty = true;
                        }
                        i++;
                    } else {
                        if (hasEmpty) {
                            ret2 = 0;
                        }
                        return ret2;
                    }
                }
                if (hasEmpty) {
                    ret2 = 0;
                }
                return ret2;
            case 3:
            case 9:
                getChild(0).analyzeFirstCharacter(result, options);
                return 0;
            case 4:
                result.mergeRanges(this);
                return 1;
            case 5:
                result.mergeRanges(complementRanges(this));
                return 1;
            case 6:
            case 24:
                return getChild(0).analyzeFirstCharacter(result, options);
            case 7:
            case 8:
            case 20:
            case 21:
            case 22:
            case 23:
                return 0;
            case 10:
                int cha = getString().charAt(0);
                if (REUtil.isHighSurrogate(cha) && getString().length() >= 2) {
                    int ch2 = getString().charAt(1);
                    if (REUtil.isLowSurrogate(ch2)) {
                        cha = REUtil.composeFromSurrogates(cha, ch2);
                    }
                }
                result.addRange(cha, cha);
                if (cha < 65536 && isSet(options, 2)) {
                    cha = Character.toUpperCase((char) cha);
                    result.addRange(cha, cha);
                    cha = Character.toLowerCase((char) cha);
                    result.addRange(cha, cha);
                }
                return 1;
            case 11:
                return 2;
            case 12:
                result.addRange(0, UTF16_MAX);
                return 2;
            case 25:
                return getChild(0).analyzeFirstCharacter(result, (options | ((ModifierToken) this).getOptions()) & (((ModifierToken) this).getOptionsMask() ^ -1));
            case 26:
                int ret3 = getChild(0).analyzeFirstCharacter(result, options);
                if (size() == 1) {
                    return 0;
                }
                if (ret3 == 2) {
                    return ret3;
                }
                int ret4 = getChild(1).analyzeFirstCharacter(result, options);
                if (ret4 == 2) {
                    return ret4;
                }
                if (ret3 == 0 || ret4 == 0) {
                    return 0;
                }
                return 1;
            default:
                throw new RuntimeException("Token#analyzeHeadCharacter(): Invalid Type: " + this.type);
        }
    }

    private final boolean isShorterThan(Token tok) {
        if (tok == null) {
            return false;
        }
        if (this.type == 10) {
            int mylength = getString().length();
            if (tok.type != 10) {
                throw new RuntimeException("Internal Error: Illegal type: " + tok.type);
            } else if (mylength < tok.getString().length()) {
                return true;
            } else {
                return false;
            }
        }
        throw new RuntimeException("Internal Error: Illegal type: " + this.type);
    }

    final void findFixedString(FixedStringContainer container, int options) {
        switch (this.type) {
            case 0:
                container.token = null;
                return;
            case 1:
                Token prevToken = null;
                int prevOptions = 0;
                for (int i = 0; i < size(); i++) {
                    getChild(i).findFixedString(container, options);
                    if (prevToken == null || prevToken.isShorterThan(container.token)) {
                        prevToken = container.token;
                        prevOptions = container.options;
                    }
                }
                container.token = prevToken;
                container.options = prevOptions;
                return;
            case 2:
            case 3:
            case 4:
            case 5:
            case 7:
            case 8:
            case 9:
            case 11:
            case 12:
            case 20:
            case 21:
            case 22:
            case 23:
            case 26:
                container.token = null;
                return;
            case 6:
            case 24:
                getChild(0).findFixedString(container, options);
                return;
            case 10:
                container.token = this;
                container.options = options;
                return;
            case 25:
                getChild(0).findFixedString(container, (options | ((ModifierToken) this).getOptions()) & (((ModifierToken) this).getOptionsMask() ^ -1));
                return;
            default:
                throw new RuntimeException("Token#findFixedString(): Invalid Type: " + this.type);
        }
    }

    boolean match(int ch) {
        throw new RuntimeException("NFAArrow#match(): Internal error: " + this.type);
    }

    protected static RangeToken getRange(String name, boolean positive) {
        if (categories.size() == 0) {
            synchronized (categories) {
                int i;
                Token[] ranges = new Token[categoryNames.length];
                for (i = 0; i < ranges.length; i++) {
                    ranges[i] = createRange();
                }
                i = 0;
                while (i < 65536) {
                    int type = Character.getType((char) i);
                    if (type == 21 || type == 22) {
                        if (i == 171 || i == 8216 || i == 8219 || i == 8220 || i == 8223 || i == 8249) {
                            type = 29;
                        }
                        if (i == 187 || i == 8217 || i == 8221 || i == 8250) {
                            type = 30;
                        }
                    }
                    ranges[type].addRange(i, i);
                    switch (type) {
                        case 0:
                        case 15:
                        case 16:
                        case 18:
                        case 19:
                            type = 35;
                            break;
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                            type = 31;
                            break;
                        case 6:
                        case 7:
                        case 8:
                            type = 32;
                            break;
                        case 9:
                        case 10:
                        case 11:
                            type = 33;
                            break;
                        case 12:
                        case 13:
                        case 14:
                            type = 34;
                            break;
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                        case 24:
                        case 29:
                        case 30:
                            type = 36;
                            break;
                        case 25:
                        case 26:
                        case 27:
                        case 28:
                            type = 37;
                            break;
                        default:
                            throw new RuntimeException("mf.org.apache.xerces.utils.regex.Token#getRange(): Unknown Unicode category: " + type);
                    }
                    ranges[type].addRange(i, i);
                    i++;
                }
                ranges[0].addRange(65536, UTF16_MAX);
                for (i = 0; i < ranges.length; i++) {
                    if (categoryNames[i] != null) {
                        if (i == 0) {
                            ranges[i].addRange(65536, UTF16_MAX);
                        }
                        categories.put(categoryNames[i], ranges[i]);
                        categories2.put(categoryNames[i], complementRanges(ranges[i]));
                    }
                }
                StringBuffer buffer = new StringBuffer(50);
                for (i = 0; i < blockNames.length; i++) {
                    Token r1 = createRange();
                    int location;
                    if (i < 84) {
                        location = i * 2;
                        r1.addRange(blockRanges.charAt(location), blockRanges.charAt(location + 1));
                    } else {
                        location = (i - 84) * 2;
                        r1.addRange(nonBMPBlockRanges[location], nonBMPBlockRanges[location + 1]);
                    }
                    String n = blockNames[i];
                    if (n.equals("Specials")) {
                        r1.addRange(65520, 65533);
                    }
                    if (n.equals("Private Use")) {
                        r1.addRange(983040, 1048573);
                        r1.addRange(1048576, 1114109);
                    }
                    categories.put(n, r1);
                    categories2.put(n, complementRanges(r1));
                    buffer.setLength(0);
                    buffer.append("Is");
                    if (n.indexOf(32) >= 0) {
                        for (int ci = 0; ci < n.length(); ci++) {
                            if (n.charAt(ci) != ' ') {
                                buffer.append(n.charAt(ci));
                            }
                        }
                    } else {
                        buffer.append(n);
                    }
                    setAlias(buffer.toString(), n, true);
                }
                setAlias("ASSIGNED", "Cn", false);
                setAlias("UNASSIGNED", "Cn", true);
                Token all = createRange();
                all.addRange(0, UTF16_MAX);
                categories.put(NetstatsParserPatterns.TYPE_BOTH_PATTERN, all);
                categories2.put(NetstatsParserPatterns.TYPE_BOTH_PATTERN, complementRanges(all));
                registerNonXS("ASSIGNED");
                registerNonXS("UNASSIGNED");
                registerNonXS(NetstatsParserPatterns.TYPE_BOTH_PATTERN);
                Token isalpha = createRange();
                isalpha.mergeRanges(ranges[1]);
                isalpha.mergeRanges(ranges[2]);
                isalpha.mergeRanges(ranges[5]);
                categories.put("IsAlpha", isalpha);
                categories2.put("IsAlpha", complementRanges(isalpha));
                registerNonXS("IsAlpha");
                Token isalnum = createRange();
                isalnum.mergeRanges(isalpha);
                isalnum.mergeRanges(ranges[9]);
                categories.put("IsAlnum", isalnum);
                categories2.put("IsAlnum", complementRanges(isalnum));
                registerNonXS("IsAlnum");
                Token isspace = createRange();
                isspace.mergeRanges(token_spaces);
                isspace.mergeRanges(ranges[34]);
                categories.put("IsSpace", isspace);
                categories2.put("IsSpace", complementRanges(isspace));
                registerNonXS("IsSpace");
                Token isword = createRange();
                isword.mergeRanges(isalnum);
                isword.addRange(95, 95);
                categories.put("IsWord", isword);
                categories2.put("IsWord", complementRanges(isword));
                registerNonXS("IsWord");
                Token isascii = createRange();
                isascii.addRange(0, 127);
                categories.put("IsASCII", isascii);
                categories2.put("IsASCII", complementRanges(isascii));
                registerNonXS("IsASCII");
                Token isnotgraph = createRange();
                isnotgraph.mergeRanges(ranges[35]);
                isnotgraph.addRange(32, 32);
                categories.put("IsGraph", complementRanges(isnotgraph));
                categories2.put("IsGraph", isnotgraph);
                registerNonXS("IsGraph");
                Token isxdigit = createRange();
                isxdigit.addRange(48, 57);
                isxdigit.addRange(65, 70);
                isxdigit.addRange(97, 102);
                categories.put("IsXDigit", complementRanges(isxdigit));
                categories2.put("IsXDigit", isxdigit);
                registerNonXS("IsXDigit");
                setAlias("IsDigit", "Nd", true);
                setAlias("IsUpper", "Lu", true);
                setAlias("IsLower", "Ll", true);
                setAlias("IsCntrl", "C", true);
                setAlias("IsPrint", "C", false);
                setAlias("IsPunct", "P", true);
                registerNonXS("IsDigit");
                registerNonXS("IsUpper");
                registerNonXS("IsLower");
                registerNonXS("IsCntrl");
                registerNonXS("IsPrint");
                registerNonXS("IsPunct");
                setAlias("alpha", "IsAlpha", true);
                setAlias("alnum", "IsAlnum", true);
                setAlias("ascii", "IsASCII", true);
                setAlias("cntrl", "IsCntrl", true);
                setAlias("digit", "IsDigit", true);
                setAlias("graph", "IsGraph", true);
                setAlias("lower", "IsLower", true);
                setAlias("print", "IsPrint", true);
                setAlias("punct", "IsPunct", true);
                setAlias("space", "IsSpace", true);
                setAlias("upper", "IsUpper", true);
                setAlias("word", "IsWord", true);
                setAlias("xdigit", "IsXDigit", true);
                registerNonXS("alpha");
                registerNonXS("alnum");
                registerNonXS("ascii");
                registerNonXS("cntrl");
                registerNonXS("digit");
                registerNonXS("graph");
                registerNonXS("lower");
                registerNonXS("print");
                registerNonXS("punct");
                registerNonXS("space");
                registerNonXS("upper");
                registerNonXS("word");
                registerNonXS("xdigit");
            }
        }
        if (positive) {
            return (RangeToken) categories.get(name);
        }
        return (RangeToken) categories2.get(name);
    }

    protected static RangeToken getRange(String name, boolean positive, boolean xs) {
        RangeToken range = getRange(name, positive);
        if (xs && range != null && isRegisterNonXS(name)) {
            return null;
        }
        return range;
    }

    protected static void registerNonXS(String name) {
        if (nonxs == null) {
            nonxs = new Hashtable();
        }
        nonxs.put(name, name);
    }

    protected static boolean isRegisterNonXS(String name) {
        if (nonxs == null) {
            return false;
        }
        return nonxs.containsKey(name);
    }

    private static void setAlias(String newName, String name, boolean positive) {
        Token t1 = (Token) categories.get(name);
        Token t2 = (Token) categories2.get(name);
        if (positive) {
            categories.put(newName, t1);
            categories2.put(newName, t2);
            return;
        }
        categories2.put(newName, t1);
        categories.put(newName, t2);
    }

    static synchronized Token getGraphemePattern() {
        Token token;
        synchronized (Token.class) {
            if (token_grapheme != null) {
                token = token_grapheme;
            } else {
                Token base_char = createRange();
                base_char.mergeRanges(getRange("ASSIGNED", true));
                base_char.subtractRanges(getRange("M", true));
                base_char.subtractRanges(getRange("C", true));
                Token virama = createRange();
                for (int i = 0; i < viramaString.length(); i++) {
                    virama.addRange(i, i);
                }
                Token combiner_wo_virama = createRange();
                combiner_wo_virama.mergeRanges(getRange("M", true));
                combiner_wo_virama.addRange(4448, 4607);
                combiner_wo_virama.addRange(65438, 65439);
                Token left = createUnion();
                left.addChild(base_char);
                left.addChild(token_empty);
                Token foo = createUnion();
                foo.addChild(createConcat(virama, getRange("L", true)));
                foo.addChild(combiner_wo_virama);
                token_grapheme = createConcat(left, createClosure(foo));
                token = token_grapheme;
            }
        }
        return token;
    }

    static synchronized Token getCombiningCharacterSequence() {
        Token token;
        synchronized (Token.class) {
            if (token_ccs != null) {
                token = token_ccs;
            } else {
                token_ccs = createConcat(getRange("M", false), createClosure(getRange("M", true)));
                token = token_ccs;
            }
        }
        return token;
    }
}
