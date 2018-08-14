package mf.org.apache.xerces.impl.xpath.regex;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Vector;

class RegexParser {
    protected static final int S_INBRACKETS = 1;
    protected static final int S_INXBRACKETS = 2;
    protected static final int S_NORMAL = 0;
    static final int T_BACKSOLIDUS = 10;
    static final int T_CARET = 11;
    static final int T_CHAR = 0;
    static final int T_COMMENT = 21;
    static final int T_CONDITION = 23;
    static final int T_DOLLAR = 12;
    static final int T_DOT = 8;
    static final int T_EOF = 1;
    static final int T_INDEPENDENT = 18;
    static final int T_LBRACKET = 9;
    static final int T_LOOKAHEAD = 14;
    static final int T_LOOKBEHIND = 16;
    static final int T_LPAREN = 6;
    static final int T_LPAREN2 = 13;
    static final int T_MODIFIERS = 22;
    static final int T_NEGATIVELOOKAHEAD = 15;
    static final int T_NEGATIVELOOKBEHIND = 17;
    static final int T_OR = 2;
    static final int T_PLUS = 4;
    static final int T_POSIX_CHARCLASS_START = 20;
    static final int T_QUESTION = 5;
    static final int T_RPAREN = 7;
    static final int T_SET_OPERATIONS = 19;
    static final int T_STAR = 3;
    static final int T_XMLSCHEMA_CC_SUBTRACTION = 24;
    int chardata;
    int context = 0;
    boolean hasBackReferences;
    int nexttoken;
    int offset;
    int options;
    int parenOpened = 1;
    int parennumber = 1;
    Vector references = null;
    String regex;
    int regexlen;
    ResourceBundle resources;

    static class ReferencePosition {
        int position;
        int refNumber;

        ReferencePosition(int n, int pos) {
            this.refNumber = n;
            this.position = pos;
        }
    }

    public RegexParser() {
        setLocale(Locale.getDefault());
    }

    public RegexParser(Locale locale) {
        setLocale(locale);
    }

    public void setLocale(Locale locale) {
        if (locale != null) {
            try {
                this.resources = ResourceBundle.getBundle("mf.org.apache.xerces.impl.xpath.regex.message", locale);
                return;
            } catch (MissingResourceException mre) {
                throw new RuntimeException("Installation Problem???  Couldn't load messages: " + mre.getMessage());
            }
        }
        this.resources = ResourceBundle.getBundle("mf.org.apache.xerces.impl.xpath.regex.message");
    }

    final ParseException ex(String key, int loc) {
        return new ParseException(this.resources.getString(key), loc);
    }

    protected final boolean isSet(int flag) {
        return (this.options & flag) == flag;
    }

    synchronized Token parse(String regex, int options) throws ParseException {
        Token ret;
        this.options = options;
        this.offset = 0;
        setContext(0);
        this.parennumber = 1;
        this.parenOpened = 1;
        this.hasBackReferences = false;
        this.regex = regex;
        if (isSet(16)) {
            this.regex = REUtil.stripExtendedComment(this.regex);
        }
        this.regexlen = this.regex.length();
        next();
        ret = parseRegex();
        if (this.offset != this.regexlen) {
            throw ex("parser.parse.1", this.offset);
        } else if (this.references != null) {
            for (int i = 0; i < this.references.size(); i++) {
                ReferencePosition position = (ReferencePosition) this.references.elementAt(i);
                if (this.parennumber <= position.refNumber) {
                    throw ex("parser.parse.2", position.position);
                }
            }
            this.references.removeAllElements();
        }
        return ret;
    }

    protected final void setContext(int con) {
        this.context = con;
    }

    final int read() {
        return this.nexttoken;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final void next() {
        /*
        r9 = this;
        r8 = 41;
        r7 = 1;
        r6 = 512; // 0x200 float:7.175E-43 double:2.53E-321;
        r3 = r9.offset;
        r4 = r9.regexlen;
        if (r3 < r4) goto L_0x0011;
    L_0x000b:
        r3 = -1;
        r9.chardata = r3;
        r9.nexttoken = r7;
    L_0x0010:
        return;
    L_0x0011:
        r3 = r9.regex;
        r4 = r9.offset;
        r5 = r4 + 1;
        r9.offset = r5;
        r0 = r3.charAt(r4);
        r9.chardata = r0;
        r3 = r9.context;
        if (r3 != r7) goto L_0x00b1;
    L_0x0023:
        switch(r0) {
            case 45: goto L_0x0073;
            case 91: goto L_0x0090;
            case 92: goto L_0x0050;
            default: goto L_0x0026;
        };
    L_0x0026:
        r3 = mf.org.apache.xerces.impl.xpath.regex.REUtil.isHighSurrogate(r0);
        if (r3 == 0) goto L_0x004c;
    L_0x002c:
        r3 = r9.offset;
        r4 = r9.regexlen;
        if (r3 >= r4) goto L_0x004c;
    L_0x0032:
        r3 = r9.regex;
        r4 = r9.offset;
        r1 = r3.charAt(r4);
        r3 = mf.org.apache.xerces.impl.xpath.regex.REUtil.isLowSurrogate(r1);
        if (r3 == 0) goto L_0x004c;
    L_0x0040:
        r3 = mf.org.apache.xerces.impl.xpath.regex.REUtil.composeFromSurrogates(r0, r1);
        r9.chardata = r3;
        r3 = r9.offset;
        r3 = r3 + 1;
        r9.offset = r3;
    L_0x004c:
        r2 = 0;
    L_0x004d:
        r9.nexttoken = r2;
        goto L_0x0010;
    L_0x0050:
        r2 = 10;
        r3 = r9.offset;
        r4 = r9.regexlen;
        if (r3 < r4) goto L_0x0064;
    L_0x0058:
        r3 = "parser.next.1";
        r4 = r9.offset;
        r4 = r4 + -1;
        r3 = r9.ex(r3, r4);
        throw r3;
    L_0x0064:
        r3 = r9.regex;
        r4 = r9.offset;
        r5 = r4 + 1;
        r9.offset = r5;
        r3 = r3.charAt(r4);
        r9.chardata = r3;
        goto L_0x004d;
    L_0x0073:
        r3 = r9.offset;
        r4 = r9.regexlen;
        if (r3 >= r4) goto L_0x008e;
    L_0x0079:
        r3 = r9.regex;
        r4 = r9.offset;
        r3 = r3.charAt(r4);
        r4 = 91;
        if (r3 != r4) goto L_0x008e;
    L_0x0085:
        r3 = r9.offset;
        r3 = r3 + 1;
        r9.offset = r3;
        r2 = 24;
        goto L_0x004d;
    L_0x008e:
        r2 = 0;
        goto L_0x004d;
    L_0x0090:
        r3 = r9.isSet(r6);
        if (r3 != 0) goto L_0x0026;
    L_0x0096:
        r3 = r9.offset;
        r4 = r9.regexlen;
        if (r3 >= r4) goto L_0x0026;
    L_0x009c:
        r3 = r9.regex;
        r4 = r9.offset;
        r3 = r3.charAt(r4);
        r4 = 58;
        if (r3 != r4) goto L_0x0026;
    L_0x00a8:
        r3 = r9.offset;
        r3 = r3 + 1;
        r9.offset = r3;
        r2 = 20;
        goto L_0x004d;
    L_0x00b1:
        switch(r0) {
            case 36: goto L_0x00d4;
            case 40: goto L_0x00df;
            case 41: goto L_0x00c1;
            case 42: goto L_0x00bb;
            case 43: goto L_0x00bd;
            case 46: goto L_0x00c3;
            case 63: goto L_0x00bf;
            case 91: goto L_0x00c6;
            case 92: goto L_0x01bd;
            case 94: goto L_0x00c9;
            case 124: goto L_0x00b9;
            default: goto L_0x00b4;
        };
    L_0x00b4:
        r2 = 0;
    L_0x00b5:
        r9.nexttoken = r2;
        goto L_0x0010;
    L_0x00b9:
        r2 = 2;
        goto L_0x00b5;
    L_0x00bb:
        r2 = 3;
        goto L_0x00b5;
    L_0x00bd:
        r2 = 4;
        goto L_0x00b5;
    L_0x00bf:
        r2 = 5;
        goto L_0x00b5;
    L_0x00c1:
        r2 = 7;
        goto L_0x00b5;
    L_0x00c3:
        r2 = 8;
        goto L_0x00b5;
    L_0x00c6:
        r2 = 9;
        goto L_0x00b5;
    L_0x00c9:
        r3 = r9.isSet(r6);
        if (r3 == 0) goto L_0x00d1;
    L_0x00cf:
        r2 = 0;
        goto L_0x00b5;
    L_0x00d1:
        r2 = 11;
        goto L_0x00b5;
    L_0x00d4:
        r3 = r9.isSet(r6);
        if (r3 == 0) goto L_0x00dc;
    L_0x00da:
        r2 = 0;
        goto L_0x00b5;
    L_0x00dc:
        r2 = 12;
        goto L_0x00b5;
    L_0x00df:
        r2 = 6;
        r3 = r9.offset;
        r4 = r9.regexlen;
        if (r3 >= r4) goto L_0x00b5;
    L_0x00e6:
        r3 = r9.regex;
        r4 = r9.offset;
        r3 = r3.charAt(r4);
        r4 = 63;
        if (r3 != r4) goto L_0x00b5;
    L_0x00f2:
        r3 = r9.offset;
        r3 = r3 + 1;
        r9.offset = r3;
        r4 = r9.regexlen;
        if (r3 < r4) goto L_0x0108;
    L_0x00fc:
        r3 = "parser.next.2";
        r4 = r9.offset;
        r4 = r4 + -1;
        r3 = r9.ex(r3, r4);
        throw r3;
    L_0x0108:
        r3 = r9.regex;
        r4 = r9.offset;
        r5 = r4 + 1;
        r9.offset = r5;
        r0 = r3.charAt(r4);
        switch(r0) {
            case 33: goto L_0x013c;
            case 35: goto L_0x019e;
            case 58: goto L_0x0134;
            case 60: goto L_0x0148;
            case 61: goto L_0x0138;
            case 62: goto L_0x0144;
            case 91: goto L_0x0140;
            default: goto L_0x0117;
        };
    L_0x0117:
        r3 = 45;
        if (r0 == r3) goto L_0x012b;
    L_0x011b:
        r3 = 97;
        if (r3 > r0) goto L_0x0123;
    L_0x011f:
        r3 = 122; // 0x7a float:1.71E-43 double:6.03E-322;
        if (r0 <= r3) goto L_0x012b;
    L_0x0123:
        r3 = 65;
        if (r3 > r0) goto L_0x01a9;
    L_0x0127:
        r3 = 90;
        if (r0 > r3) goto L_0x01a9;
    L_0x012b:
        r3 = r9.offset;
        r3 = r3 + -1;
        r9.offset = r3;
        r2 = 22;
        goto L_0x00b5;
    L_0x0134:
        r2 = 13;
        goto L_0x00b5;
    L_0x0138:
        r2 = 14;
        goto L_0x00b5;
    L_0x013c:
        r2 = 15;
        goto L_0x00b5;
    L_0x0140:
        r2 = 19;
        goto L_0x00b5;
    L_0x0144:
        r2 = 18;
        goto L_0x00b5;
    L_0x0148:
        r3 = r9.offset;
        r4 = r9.regexlen;
        if (r3 < r4) goto L_0x015a;
    L_0x014e:
        r3 = "parser.next.2";
        r4 = r9.offset;
        r4 = r4 + -3;
        r3 = r9.ex(r3, r4);
        throw r3;
    L_0x015a:
        r3 = r9.regex;
        r4 = r9.offset;
        r5 = r4 + 1;
        r9.offset = r5;
        r0 = r3.charAt(r4);
        r3 = 61;
        if (r0 != r3) goto L_0x016e;
    L_0x016a:
        r2 = 16;
        goto L_0x00b5;
    L_0x016e:
        r3 = 33;
        if (r0 != r3) goto L_0x0176;
    L_0x0172:
        r2 = 17;
        goto L_0x00b5;
    L_0x0176:
        r3 = "parser.next.3";
        r4 = r9.offset;
        r4 = r4 + -3;
        r3 = r9.ex(r3, r4);
        throw r3;
    L_0x0182:
        r3 = r9.regex;
        r4 = r9.offset;
        r5 = r4 + 1;
        r9.offset = r5;
        r0 = r3.charAt(r4);
        if (r0 != r8) goto L_0x019e;
    L_0x0190:
        if (r0 == r8) goto L_0x01a5;
    L_0x0192:
        r3 = "parser.next.4";
        r4 = r9.offset;
        r4 = r4 + -1;
        r3 = r9.ex(r3, r4);
        throw r3;
    L_0x019e:
        r3 = r9.offset;
        r4 = r9.regexlen;
        if (r3 < r4) goto L_0x0182;
    L_0x01a4:
        goto L_0x0190;
    L_0x01a5:
        r2 = 21;
        goto L_0x00b5;
    L_0x01a9:
        r3 = 40;
        if (r0 != r3) goto L_0x01b1;
    L_0x01ad:
        r2 = 23;
        goto L_0x00b5;
    L_0x01b1:
        r3 = "parser.next.2";
        r4 = r9.offset;
        r4 = r4 + -2;
        r3 = r9.ex(r3, r4);
        throw r3;
    L_0x01bd:
        r2 = 10;
        r3 = r9.offset;
        r4 = r9.regexlen;
        if (r3 < r4) goto L_0x01d1;
    L_0x01c5:
        r3 = "parser.next.1";
        r4 = r9.offset;
        r4 = r4 + -1;
        r3 = r9.ex(r3, r4);
        throw r3;
    L_0x01d1:
        r3 = r9.regex;
        r4 = r9.offset;
        r5 = r4 + 1;
        r9.offset = r5;
        r3 = r3.charAt(r4);
        r9.chardata = r3;
        goto L_0x00b5;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.xpath.regex.RegexParser.next():void");
    }

    Token parseRegex() throws ParseException {
        Token tok = parseTerm();
        Token parent = null;
        while (read() == 2) {
            next();
            if (parent == null) {
                parent = Token.createUnion();
                parent.addChild(tok);
                tok = parent;
            }
            tok.addChild(parseTerm());
        }
        return tok;
    }

    Token parseTerm() throws ParseException {
        int ch = read();
        if (ch == 2 || ch == 7 || ch == 1) {
            return Token.createEmpty();
        }
        Token tok = parseFactor();
        Token concat = null;
        while (true) {
            ch = read();
            if (ch == 2 || ch == 7 || ch == 1) {
                return tok;
            }
            if (concat == null) {
                concat = Token.createConcat();
                concat.addChild(tok);
                tok = concat;
            }
            concat.addChild(parseFactor());
        }
    }

    Token processCaret() throws ParseException {
        next();
        return Token.token_linebeginning;
    }

    Token processDollar() throws ParseException {
        next();
        return Token.token_lineend;
    }

    Token processLookahead() throws ParseException {
        next();
        Token tok = Token.createLook(20, parseRegex());
        if (read() != 7) {
            throw ex("parser.factor.1", this.offset - 1);
        }
        next();
        return tok;
    }

    Token processNegativelookahead() throws ParseException {
        next();
        Token tok = Token.createLook(21, parseRegex());
        if (read() != 7) {
            throw ex("parser.factor.1", this.offset - 1);
        }
        next();
        return tok;
    }

    Token processLookbehind() throws ParseException {
        next();
        Token tok = Token.createLook(22, parseRegex());
        if (read() != 7) {
            throw ex("parser.factor.1", this.offset - 1);
        }
        next();
        return tok;
    }

    Token processNegativelookbehind() throws ParseException {
        next();
        Token tok = Token.createLook(23, parseRegex());
        if (read() != 7) {
            throw ex("parser.factor.1", this.offset - 1);
        }
        next();
        return tok;
    }

    Token processBacksolidus_A() throws ParseException {
        next();
        return Token.token_stringbeginning;
    }

    Token processBacksolidus_Z() throws ParseException {
        next();
        return Token.token_stringend2;
    }

    Token processBacksolidus_z() throws ParseException {
        next();
        return Token.token_stringend;
    }

    Token processBacksolidus_b() throws ParseException {
        next();
        return Token.token_wordedge;
    }

    Token processBacksolidus_B() throws ParseException {
        next();
        return Token.token_not_wordedge;
    }

    Token processBacksolidus_lt() throws ParseException {
        next();
        return Token.token_wordbeginning;
    }

    Token processBacksolidus_gt() throws ParseException {
        next();
        return Token.token_wordend;
    }

    Token processStar(Token tok) throws ParseException {
        next();
        if (read() != 5) {
            return Token.createClosure(tok);
        }
        next();
        return Token.createNGClosure(tok);
    }

    Token processPlus(Token tok) throws ParseException {
        next();
        if (read() != 5) {
            return Token.createConcat(tok, Token.createClosure(tok));
        }
        next();
        return Token.createConcat(tok, Token.createNGClosure(tok));
    }

    Token processQuestion(Token tok) throws ParseException {
        next();
        Token par = Token.createUnion();
        if (read() == 5) {
            next();
            par.addChild(Token.createEmpty());
            par.addChild(tok);
        } else {
            par.addChild(tok);
            par.addChild(Token.createEmpty());
        }
        return par;
    }

    boolean checkQuestion(int off) {
        return off < this.regexlen && this.regex.charAt(off) == '?';
    }

    Token processParen() throws ParseException {
        next();
        int p = this.parenOpened;
        this.parenOpened = p + 1;
        Token tok = Token.createParen(parseRegex(), p);
        if (read() != 7) {
            throw ex("parser.factor.1", this.offset - 1);
        }
        this.parennumber++;
        next();
        return tok;
    }

    Token processParen2() throws ParseException {
        next();
        Token tok = Token.createParen(parseRegex(), 0);
        if (read() != 7) {
            throw ex("parser.factor.1", this.offset - 1);
        }
        next();
        return tok;
    }

    Token processCondition() throws ParseException {
        if (this.offset + 1 >= this.regexlen) {
            throw ex("parser.factor.4", this.offset);
        }
        int refno = -1;
        Token condition = null;
        int ch = this.regex.charAt(this.offset);
        if (49 > ch || ch > 57) {
            if (ch == 63) {
                this.offset--;
            }
            next();
            condition = parseFactor();
            switch (condition.type) {
                case 8:
                    if (read() != 7) {
                        throw ex("parser.factor.1", this.offset - 1);
                    }
                    break;
                case 20:
                case 21:
                case 22:
                case 23:
                    break;
                default:
                    throw ex("parser.factor.5", this.offset);
            }
        }
        refno = ch - 48;
        int finalRefno = refno;
        if (this.parennumber <= refno) {
            throw ex("parser.parse.2", this.offset);
        }
        while (this.offset + 1 < this.regexlen) {
            ch = this.regex.charAt(this.offset + 1);
            if (48 > ch || ch > 57) {
                break;
            }
            refno = (refno * 10) + (ch - 48);
            if (refno >= this.parennumber) {
                break;
            }
            finalRefno = refno;
            this.offset++;
        }
        this.hasBackReferences = true;
        if (this.references == null) {
            this.references = new Vector();
        }
        this.references.addElement(new ReferencePosition(finalRefno, this.offset));
        this.offset++;
        if (this.regex.charAt(this.offset) != ')') {
            throw ex("parser.factor.1", this.offset);
        }
        this.offset++;
        next();
        Token yesPattern = parseRegex();
        Token noPattern = null;
        if (yesPattern.type == 2) {
            if (yesPattern.size() != 2) {
                throw ex("parser.factor.6", this.offset);
            }
            noPattern = yesPattern.getChild(1);
            yesPattern = yesPattern.getChild(0);
        }
        if (read() != 7) {
            throw ex("parser.factor.1", this.offset - 1);
        }
        next();
        return Token.createCondition(refno, condition, yesPattern, noPattern);
    }

    Token processModifiers() throws ParseException {
        int add = 0;
        int mask = 0;
        int ch = -1;
        while (this.offset < this.regexlen) {
            ch = this.regex.charAt(this.offset);
            int v = REUtil.getOptionValue(ch);
            if (v == 0) {
                break;
            }
            add |= v;
            this.offset++;
        }
        if (this.offset >= this.regexlen) {
            throw ex("parser.factor.2", this.offset - 1);
        }
        if (ch == 45) {
            this.offset++;
            while (this.offset < this.regexlen) {
                ch = this.regex.charAt(this.offset);
                v = REUtil.getOptionValue(ch);
                if (v == 0) {
                    break;
                }
                mask |= v;
                this.offset++;
            }
            if (this.offset >= this.regexlen) {
                throw ex("parser.factor.2", this.offset - 1);
            }
        }
        if (ch == 58) {
            this.offset++;
            next();
            Token tok = Token.createModifierGroup(parseRegex(), add, mask);
            if (read() != 7) {
                throw ex("parser.factor.1", this.offset - 1);
            }
            next();
            return tok;
        } else if (ch == 41) {
            this.offset++;
            next();
            return Token.createModifierGroup(parseRegex(), add, mask);
        } else {
            throw ex("parser.factor.3", this.offset);
        }
    }

    Token processIndependent() throws ParseException {
        next();
        Token tok = Token.createLook(24, parseRegex());
        if (read() != 7) {
            throw ex("parser.factor.1", this.offset - 1);
        }
        next();
        return tok;
    }

    Token processBacksolidus_c() throws ParseException {
        if (this.offset < this.regexlen) {
            String str = this.regex;
            int i = this.offset;
            this.offset = i + 1;
            int ch2 = str.charAt(i);
            if ((65504 & ch2) == 64) {
                next();
                return Token.createChar(ch2 - 64);
            }
        }
        throw ex("parser.atom.1", this.offset - 1);
    }

    Token processBacksolidus_C() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    Token processBacksolidus_i() throws ParseException {
        Token tok = Token.createChar(105);
        next();
        return tok;
    }

    Token processBacksolidus_I() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    Token processBacksolidus_g() throws ParseException {
        next();
        return Token.getGraphemePattern();
    }

    Token processBacksolidus_X() throws ParseException {
        next();
        return Token.getCombiningCharacterSequence();
    }

    Token processBackreference() throws ParseException {
        int refnum = this.chardata - 48;
        int finalRefnum = refnum;
        if (this.parennumber <= refnum) {
            throw ex("parser.parse.2", this.offset - 2);
        }
        while (this.offset < this.regexlen) {
            int ch = this.regex.charAt(this.offset);
            if (48 > ch || ch > 57) {
                break;
            }
            refnum = (refnum * 10) + (ch - 48);
            if (refnum >= this.parennumber) {
                break;
            }
            this.offset++;
            finalRefnum = refnum;
            this.chardata = ch;
        }
        Token tok = Token.createBackReference(finalRefnum);
        this.hasBackReferences = true;
        if (this.references == null) {
            this.references = new Vector();
        }
        this.references.addElement(new ReferencePosition(finalRefnum, this.offset - 2));
        next();
        return tok;
    }

    Token parseFactor() throws ParseException {
        switch (read()) {
            case 10:
                switch (this.chardata) {
                    case 60:
                        return processBacksolidus_lt();
                    case 62:
                        return processBacksolidus_gt();
                    case 65:
                        return processBacksolidus_A();
                    case 66:
                        return processBacksolidus_B();
                    case 90:
                        return processBacksolidus_Z();
                    case 98:
                        return processBacksolidus_b();
                    case 122:
                        return processBacksolidus_z();
                    default:
                        break;
                }
            case 11:
                return processCaret();
            case 12:
                return processDollar();
            case 14:
                return processLookahead();
            case 15:
                return processNegativelookahead();
            case 16:
                return processLookbehind();
            case 17:
                return processNegativelookbehind();
            case 21:
                next();
                return Token.createEmpty();
        }
        Token tok = parseAtom();
        switch (read()) {
            case 0:
                if (this.chardata != 123 || this.offset >= this.regexlen) {
                    return tok;
                }
                int off = this.offset;
                int off2 = off + 1;
                int ch = this.regex.charAt(off);
                if (ch < 48 || ch > 57) {
                    throw ex("parser.quantifier.1", this.offset);
                }
                int max;
                int min = ch - 48;
                off = off2;
                while (off < this.regexlen) {
                    off2 = off + 1;
                    ch = this.regex.charAt(off);
                    if (ch >= 48 && ch <= 57) {
                        min = ((min * 10) + ch) - 48;
                        if (min < 0) {
                            throw ex("parser.quantifier.5", this.offset);
                        }
                        off = off2;
                    }
                    max = min;
                    if (ch == 44) {
                        off = off2;
                    } else if (off2 < this.regexlen) {
                        throw ex("parser.quantifier.3", this.offset);
                    } else {
                        off = off2 + 1;
                        ch = this.regex.charAt(off2);
                        if (ch >= 48 || ch > 57) {
                            max = -1;
                        } else {
                            max = ch - 48;
                            while (off < this.regexlen) {
                                off2 = off + 1;
                                ch = this.regex.charAt(off);
                                if (ch < 48 || ch > 57) {
                                    off = off2;
                                    if (min > max) {
                                        throw ex("parser.quantifier.4", this.offset);
                                    }
                                }
                                max = ((max * 10) + ch) - 48;
                                if (max < 0) {
                                    throw ex("parser.quantifier.5", this.offset);
                                }
                                off = off2;
                            }
                            if (min > max) {
                                throw ex("parser.quantifier.4", this.offset);
                            }
                        }
                    }
                    if (ch == 125) {
                        throw ex("parser.quantifier.2", this.offset);
                    }
                    if (checkQuestion(off)) {
                        tok = Token.createClosure(tok);
                        this.offset = off;
                    } else {
                        tok = Token.createNGClosure(tok);
                        this.offset = off + 1;
                    }
                    tok.setMin(min);
                    tok.setMax(max);
                    next();
                    return tok;
                }
                off2 = off;
                max = min;
                if (ch == 44) {
                    off = off2;
                } else if (off2 < this.regexlen) {
                    off = off2 + 1;
                    ch = this.regex.charAt(off2);
                    if (ch >= 48) {
                    }
                    max = -1;
                } else {
                    throw ex("parser.quantifier.3", this.offset);
                }
                if (ch == 125) {
                    if (checkQuestion(off)) {
                        tok = Token.createClosure(tok);
                        this.offset = off;
                    } else {
                        tok = Token.createNGClosure(tok);
                        this.offset = off + 1;
                    }
                    tok.setMin(min);
                    tok.setMax(max);
                    next();
                    return tok;
                }
                throw ex("parser.quantifier.2", this.offset);
            case 3:
                return processStar(tok);
            case 4:
                return processPlus(tok);
            case 5:
                return processQuestion(tok);
            default:
                return tok;
        }
    }

    Token parseAtom() throws ParseException {
        Token tok;
        switch (read()) {
            case 0:
                if (this.chardata != 93 && this.chardata != 123 && this.chardata != 125) {
                    tok = Token.createChar(this.chardata);
                    int high = this.chardata;
                    next();
                    if (REUtil.isHighSurrogate(high) && read() == 0 && REUtil.isLowSurrogate(this.chardata)) {
                        tok = Token.createParen(Token.createString(new String(new char[]{(char) high, (char) this.chardata})), 0);
                        next();
                        break;
                    }
                }
                throw ex("parser.atom.4", this.offset - 1);
                break;
            case 6:
                return processParen();
            case 8:
                next();
                tok = Token.token_dot;
                break;
            case 9:
                return parseCharacterClass(true);
            case 10:
                switch (this.chardata) {
                    case 49:
                    case 50:
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                        return processBackreference();
                    case 67:
                        return processBacksolidus_C();
                    case 68:
                    case 83:
                    case 87:
                    case 100:
                    case 115:
                    case 119:
                        tok = getTokenForShorthand(this.chardata);
                        next();
                        return tok;
                    case 73:
                        return processBacksolidus_I();
                    case 80:
                    case 112:
                        int pstart = this.offset;
                        tok = processBacksolidus_pP(this.chardata);
                        if (tok == null) {
                            throw ex("parser.atom.5", pstart);
                        }
                        break;
                    case 88:
                        return processBacksolidus_X();
                    case 99:
                        return processBacksolidus_c();
                    case 101:
                    case 102:
                    case 110:
                    case 114:
                    case 116:
                    case 117:
                    case 118:
                    case 120:
                        int ch2 = decodeEscaped();
                        if (ch2 >= 65536) {
                            tok = Token.createString(REUtil.decomposeToSurrogates(ch2));
                            break;
                        }
                        tok = Token.createChar(ch2);
                        break;
                    case 103:
                        return processBacksolidus_g();
                    case 105:
                        return processBacksolidus_i();
                    default:
                        tok = Token.createChar(this.chardata);
                        break;
                }
                next();
                break;
            case 13:
                return processParen2();
            case 18:
                return processIndependent();
            case 19:
                return parseSetOperations();
            case 22:
                return processModifiers();
            case 23:
                return processCondition();
            default:
                throw ex("parser.atom.4", this.offset - 1);
        }
        return tok;
    }

    protected RangeToken processBacksolidus_pP(int c) throws ParseException {
        next();
        if (read() == 0 && this.chardata == 123) {
            boolean positive = c == 112;
            int namestart = this.offset;
            int nameend = this.regex.indexOf(125, namestart);
            if (nameend < 0) {
                throw ex("parser.atom.3", this.offset);
            }
            String pname = this.regex.substring(namestart, nameend);
            this.offset = nameend + 1;
            return Token.getRange(pname, positive, isSet(512));
        }
        throw ex("parser.atom.2", this.offset - 1);
    }

    int processCIinCharacterClass(RangeToken tok, int c) {
        return decodeEscaped();
    }

    protected RangeToken parseCharacterClass(boolean useNrange) throws ParseException {
        RangeToken tok;
        setContext(1);
        next();
        boolean nrange = false;
        RangeToken base = null;
        if (read() == 0 && this.chardata == 94) {
            nrange = true;
            next();
            if (useNrange) {
                tok = Token.createNRange();
            } else {
                base = Token.createRange();
                base.addRange(0, 1114111);
                tok = Token.createRange();
            }
        } else {
            tok = Token.createRange();
        }
        boolean firstloop = true;
        while (true) {
            int type = read();
            if (!(type == 1 || (type == 0 && this.chardata == 93 && !firstloop))) {
                int c = this.chardata;
                boolean end = false;
                if (type == 10) {
                    switch (c) {
                        case 67:
                        case 73:
                        case 99:
                        case 105:
                            c = processCIinCharacterClass(tok, c);
                            if (c < 0) {
                                end = true;
                                break;
                            }
                            break;
                        case 68:
                        case 83:
                        case 87:
                        case 100:
                        case 115:
                        case 119:
                            tok.mergeRanges(getTokenForShorthand(c));
                            end = true;
                            break;
                        case 80:
                        case 112:
                            int pstart = this.offset;
                            RangeToken tok2 = processBacksolidus_pP(c);
                            if (tok2 != null) {
                                tok.mergeRanges(tok2);
                                end = true;
                                break;
                            }
                            throw ex("parser.atom.5", pstart);
                        default:
                            c = decodeEscaped();
                            break;
                    }
                } else if (type == 20) {
                    int nameend = this.regex.indexOf(58, this.offset);
                    if (nameend < 0) {
                        throw ex("parser.cc.1", this.offset);
                    }
                    boolean positive = true;
                    if (this.regex.charAt(this.offset) == '^') {
                        this.offset++;
                        positive = false;
                    }
                    RangeToken range = Token.getRange(this.regex.substring(this.offset, nameend), positive, isSet(512));
                    if (range == null) {
                        throw ex("parser.cc.3", this.offset);
                    }
                    tok.mergeRanges(range);
                    end = true;
                    if (nameend + 1 >= this.regexlen || this.regex.charAt(nameend + 1) != ']') {
                        throw ex("parser.cc.1", nameend);
                    }
                    this.offset = nameend + 2;
                } else if (type == 24 && !firstloop) {
                    if (nrange) {
                        nrange = false;
                        if (useNrange) {
                            tok = (RangeToken) Token.complementRanges(tok);
                        } else {
                            base.subtractRanges(tok);
                            tok = base;
                        }
                    }
                    tok.subtractRanges(parseCharacterClass(false));
                    if (!(read() == 0 && this.chardata == 93)) {
                        throw ex("parser.cc.5", this.offset);
                    }
                }
                next();
                if (!end) {
                    if (read() == 0 && this.chardata == 45) {
                        if (type == 24) {
                            throw ex("parser.cc.8", this.offset - 1);
                        }
                        next();
                        type = read();
                        if (type == 1) {
                            throw ex("parser.cc.2", this.offset);
                        } else if (type == 0 && this.chardata == 93) {
                            if (!isSet(2) || c > 65535) {
                                tok.addRange(c, c);
                            } else {
                                addCaseInsensitiveChar(tok, c);
                            }
                            tok.addRange(45, 45);
                        } else {
                            int rangeend = this.chardata;
                            if (type == 10) {
                                rangeend = decodeEscaped();
                            }
                            next();
                            if (c > rangeend) {
                                throw ex("parser.ope.3", this.offset - 1);
                            } else if (!isSet(2) || (c > 65535 && rangeend > 65535)) {
                                tok.addRange(c, rangeend);
                            } else {
                                addCaseInsensitiveCharRange(tok, c, rangeend);
                            }
                        }
                    } else if (!isSet(2) || c > 65535) {
                        tok.addRange(c, c);
                    } else {
                        addCaseInsensitiveChar(tok, c);
                    }
                }
                if (isSet(1024) && read() == 0 && this.chardata == 44) {
                    next();
                }
                firstloop = false;
            }
            if (read() == 1) {
                throw ex("parser.cc.2", this.offset);
            }
            if (!useNrange && nrange) {
                base.subtractRanges(tok);
                tok = base;
            }
            tok.sortRanges();
            tok.compactRanges();
            setContext(0);
            next();
            return tok;
        }
    }

    protected RangeToken parseSetOperations() throws ParseException {
        RangeToken tok = parseCharacterClass(false);
        while (true) {
            int type = read();
            if (type == 7) {
                next();
                return tok;
            }
            int ch = this.chardata;
            if ((type == 0 && (ch == 45 || ch == 38)) || type == 4) {
                next();
                if (read() != 9) {
                    throw ex("parser.ope.1", this.offset - 1);
                }
                RangeToken t2 = parseCharacterClass(false);
                if (type == 4) {
                    tok.mergeRanges(t2);
                } else if (ch == 45) {
                    tok.subtractRanges(t2);
                } else if (ch == 38) {
                    tok.intersectRanges(t2);
                } else {
                    throw new RuntimeException("ASSERT");
                }
            }
            throw ex("parser.ope.2", this.offset - 1);
        }
    }

    Token getTokenForShorthand(int ch) {
        switch (ch) {
            case 68:
                return isSet(32) ? Token.getRange("Nd", false) : Token.token_not_0to9;
            case 83:
                return isSet(32) ? Token.getRange("IsSpace", false) : Token.token_not_spaces;
            case 87:
                return isSet(32) ? Token.getRange("IsWord", false) : Token.token_not_wordchars;
            case 100:
                if (isSet(32)) {
                    return Token.getRange("Nd", true);
                }
                return Token.token_0to9;
            case 115:
                return isSet(32) ? Token.getRange("IsSpace", true) : Token.token_spaces;
            case 119:
                return isSet(32) ? Token.getRange("IsWord", true) : Token.token_wordchars;
            default:
                throw new RuntimeException("Internal Error: shorthands: \\u" + Integer.toString(ch, 16));
        }
    }

    int decodeEscaped() throws ParseException {
        if (read() != 10) {
            throw ex("parser.next.1", this.offset - 1);
        }
        int c = this.chardata;
        int v1;
        int uv;
        switch (c) {
            case 65:
            case 90:
            case 122:
                throw ex("parser.descape.5", this.offset - 2);
            case 101:
                return 27;
            case 102:
                return 12;
            case 110:
                return 10;
            case 114:
                return 13;
            case 116:
                return 9;
            case 117:
                next();
                if (read() == 0) {
                    v1 = hexChar(this.chardata);
                    if (v1 >= 0) {
                        uv = v1;
                        next();
                        if (read() == 0) {
                            v1 = hexChar(this.chardata);
                            if (v1 >= 0) {
                                uv = (uv * 16) + v1;
                                next();
                                if (read() == 0) {
                                    v1 = hexChar(this.chardata);
                                    if (v1 >= 0) {
                                        uv = (uv * 16) + v1;
                                        next();
                                        if (read() == 0) {
                                            v1 = hexChar(this.chardata);
                                            if (v1 >= 0) {
                                                return (uv * 16) + v1;
                                            }
                                        }
                                        throw ex("parser.descape.1", this.offset - 1);
                                    }
                                }
                                throw ex("parser.descape.1", this.offset - 1);
                            }
                        }
                        throw ex("parser.descape.1", this.offset - 1);
                    }
                }
                throw ex("parser.descape.1", this.offset - 1);
            case 118:
                next();
                if (read() == 0) {
                    v1 = hexChar(this.chardata);
                    if (v1 >= 0) {
                        uv = v1;
                        next();
                        if (read() == 0) {
                            v1 = hexChar(this.chardata);
                            if (v1 >= 0) {
                                uv = (uv * 16) + v1;
                                next();
                                if (read() == 0) {
                                    v1 = hexChar(this.chardata);
                                    if (v1 >= 0) {
                                        uv = (uv * 16) + v1;
                                        next();
                                        if (read() == 0) {
                                            v1 = hexChar(this.chardata);
                                            if (v1 >= 0) {
                                                uv = (uv * 16) + v1;
                                                next();
                                                if (read() == 0) {
                                                    v1 = hexChar(this.chardata);
                                                    if (v1 >= 0) {
                                                        uv = (uv * 16) + v1;
                                                        next();
                                                        if (read() == 0) {
                                                            v1 = hexChar(this.chardata);
                                                            if (v1 >= 0) {
                                                                uv = (uv * 16) + v1;
                                                                if (uv <= 1114111) {
                                                                    return uv;
                                                                }
                                                                throw ex("parser.descappe.4", this.offset - 1);
                                                            }
                                                        }
                                                        throw ex("parser.descape.1", this.offset - 1);
                                                    }
                                                }
                                                throw ex("parser.descape.1", this.offset - 1);
                                            }
                                        }
                                        throw ex("parser.descape.1", this.offset - 1);
                                    }
                                }
                                throw ex("parser.descape.1", this.offset - 1);
                            }
                        }
                        throw ex("parser.descape.1", this.offset - 1);
                    }
                }
                throw ex("parser.descape.1", this.offset - 1);
            case 120:
                next();
                if (read() != 0) {
                    throw ex("parser.descape.1", this.offset - 1);
                } else if (this.chardata == 123) {
                    uv = 0;
                    while (true) {
                        next();
                        if (read() != 0) {
                            throw ex("parser.descape.1", this.offset - 1);
                        }
                        v1 = hexChar(this.chardata);
                        if (v1 < 0) {
                            if (this.chardata != 125) {
                                throw ex("parser.descape.3", this.offset - 1);
                            } else if (uv <= 1114111) {
                                return uv;
                            } else {
                                throw ex("parser.descape.4", this.offset - 1);
                            }
                        } else if (uv > uv * 16) {
                            throw ex("parser.descape.2", this.offset - 1);
                        } else {
                            uv = (uv * 16) + v1;
                        }
                    }
                } else {
                    if (read() == 0) {
                        v1 = hexChar(this.chardata);
                        if (v1 >= 0) {
                            uv = v1;
                            next();
                            if (read() == 0) {
                                v1 = hexChar(this.chardata);
                                if (v1 >= 0) {
                                    return (uv * 16) + v1;
                                }
                            }
                            throw ex("parser.descape.1", this.offset - 1);
                        }
                    }
                    throw ex("parser.descape.1", this.offset - 1);
                }
            default:
                return c;
        }
    }

    private static final int hexChar(int ch) {
        if (ch < 48 || ch > 102) {
            return -1;
        }
        if (ch <= 57) {
            return ch - 48;
        }
        if (ch < 65) {
            return -1;
        }
        if (ch <= 70) {
            return (ch - 65) + 10;
        }
        if (ch >= 97) {
            return (ch - 97) + 10;
        }
        return -1;
    }

    protected static final void addCaseInsensitiveChar(RangeToken tok, int c) {
        int[] caseMap = CaseInsensitiveMap.get(c);
        tok.addRange(c, c);
        if (caseMap != null) {
            for (int i = 0; i < caseMap.length; i += 2) {
                tok.addRange(caseMap[i], caseMap[i]);
            }
        }
    }

    protected static final void addCaseInsensitiveCharRange(RangeToken tok, int start, int end) {
        int r1;
        int r2;
        if (start <= end) {
            r1 = start;
            r2 = end;
        } else {
            r1 = end;
            r2 = start;
        }
        tok.addRange(r1, r2);
        for (int ch = r1; ch <= r2; ch++) {
            int[] caseMap = CaseInsensitiveMap.get(ch);
            if (caseMap != null) {
                for (int i = 0; i < caseMap.length; i += 2) {
                    tok.addRange(caseMap[i], caseMap[i]);
                }
            }
        }
    }
}
