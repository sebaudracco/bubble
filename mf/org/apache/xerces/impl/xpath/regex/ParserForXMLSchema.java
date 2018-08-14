package mf.org.apache.xerces.impl.xpath.regex;

import android.support.v4.internal.view.SupportMenu;
import java.util.Hashtable;
import java.util.Locale;

class ParserForXMLSchema extends RegexParser {
    private static final String DIGITS = "09٠٩۰۹०९০৯੦੯૦૯୦୯௧௯౦౯೦೯൦൯๐๙໐໙༠༩";
    private static final String LETTERS = "AZazÀÖØöøıĴľŁňŊžƀǃǍǰǴǵǺȗɐʨʻˁΆΆΈΊΌΌΎΡΣώϐϖϚϚϜϜϞϞϠϠϢϳЁЌЎяёќўҁҐӄӇӈӋӌӐӫӮӵӸӹԱՖՙՙաֆאתװײءغفيٱڷںھۀێېۓەەۥۦअहऽऽक़ॡঅঌএঐওনপরললশহড়ঢ়য়ৡৰৱਅਊਏਐਓਨਪਰਲਲ਼ਵਸ਼ਸਹਖ਼ੜਫ਼ਫ਼ੲੴઅઋઍઍએઑઓનપરલળવહઽઽૠૠଅଌଏଐଓନପରଲଳଶହଽଽଡ଼ଢ଼ୟୡஅஊஎஐஒகஙசஜஜஞடணதநபமவஷஹఅఌఎఐఒనపళవహౠౡಅಌಎಐಒನಪಳವಹೞೞೠೡഅഌഎഐഒനപഹൠൡกฮะะาำเๅກຂຄຄງຈຊຊຍຍດທນຟມຣລລວວສຫອຮະະາຳຽຽເໄཀཇཉཀྵႠჅაჶᄀᄀᄂᄃᄅᄇᄉᄉᄋᄌᄎᄒᄼᄼᄾᄾᅀᅀᅌᅌᅎᅎᅐᅐᅔᅕᅙᅙᅟᅡᅣᅣᅥᅥᅧᅧᅩᅩᅭᅮᅲᅳᅵᅵᆞᆞᆨᆨᆫᆫᆮᆯᆷᆸᆺᆺᆼᇂᇫᇫᇰᇰᇹᇹḀẛẠỹἀἕἘἝἠὅὈὍὐὗὙὙὛὛὝὝὟώᾀᾴᾶᾼιιῂῄῆῌῐΐῖΊῠῬῲῴῶῼΩΩKÅ℮℮ↀↂ〇〇〡〩ぁゔァヺㄅㄬ一龥가힣";
    private static final String NAMECHARS = "-.0:AZ__az··ÀÖØöøıĴľŁňŊžƀǃǍǰǴǵǺȗɐʨʻˁːˑ̀͠͡ͅΆΊΌΌΎΡΣώϐϖϚϚϜϜϞϞϠϠϢϳЁЌЎяёќўҁ҃҆ҐӄӇӈӋӌӐӫӮӵӸӹԱՖՙՙաֆֹֻֽֿֿׁׂ֑֣֡ׄׄאתװײءغـْ٠٩ٰڷںھۀێېۓە۪ۭۨ۰۹ँःअह़्॑॔क़ॣ०९ঁঃঅঌএঐওনপরললশহ়়াৄেৈো্ৗৗড়ঢ়য়ৣ০ৱਂਂਅਊਏਐਓਨਪਰਲਲ਼ਵਸ਼ਸਹ਼਼ਾੂੇੈੋ੍ਖ਼ੜਫ਼ਫ਼੦ੴઁઃઅઋઍઍએઑઓનપરલળવહ઼ૅેૉો્ૠૠ૦૯ଁଃଅଌଏଐଓନପରଲଳଶହ଼ୃେୈୋ୍ୖୗଡ଼ଢ଼ୟୡ୦୯ஂஃஅஊஎஐஒகஙசஜஜஞடணதநபமவஷஹாூெைொ்ௗௗ௧௯ఁఃఅఌఎఐఒనపళవహాౄెైొ్ౕౖౠౡ౦౯ಂಃಅಌಎಐಒನಪಳವಹಾೄೆೈೊ್ೕೖೞೞೠೡ೦೯ംഃഅഌഎഐഒനപഹാൃെൈൊ്ൗൗൠൡ൦൯กฮะฺเ๎๐๙ກຂຄຄງຈຊຊຍຍດທນຟມຣລລວວສຫອຮະູົຽເໄໆໆ່ໍ໐໙༘༙༠༩༹༹༵༵༷༷༾ཇཉཀྵ྄ཱ྆ྋྐྕྗྗྙྭྱྷྐྵྐྵႠჅაჶᄀᄀᄂᄃᄅᄇᄉᄉᄋᄌᄎᄒᄼᄼᄾᄾᅀᅀᅌᅌᅎᅎᅐᅐᅔᅕᅙᅙᅟᅡᅣᅣᅥᅥᅧᅧᅩᅩᅭᅮᅲᅳᅵᅵᆞᆞᆨᆨᆫᆫᆮᆯᆷᆸᆺᆺᆼᇂᇫᇫᇰᇰᇹᇹḀẛẠỹἀἕἘἝἠὅὈὍὐὗὙὙὛὛὝὝὟώᾀᾴᾶᾼιιῂῄῆῌῐΐῖΊῠῬῲῴῶῼ⃐⃜⃡⃡ΩΩKÅ℮℮ↀↂ々々〇〇〡〯〱〵ぁゔ゙゚ゝゞァヺーヾㄅㄬ一龥가힣";
    private static final String SPACES = "\t\n\r\r  ";
    private static Hashtable ranges = null;
    private static Hashtable ranges2 = null;

    public ParserForXMLSchema(Locale locale) {
        super(locale);
    }

    Token processCaret() throws ParseException {
        next();
        return Token.createChar(94);
    }

    Token processDollar() throws ParseException {
        next();
        return Token.createChar(36);
    }

    Token processLookahead() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    Token processNegativelookahead() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    Token processLookbehind() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    Token processNegativelookbehind() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    Token processBacksolidus_A() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    Token processBacksolidus_Z() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    Token processBacksolidus_z() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    Token processBacksolidus_b() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    Token processBacksolidus_B() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    Token processBacksolidus_lt() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    Token processBacksolidus_gt() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    Token processStar(Token tok) throws ParseException {
        next();
        return Token.createClosure(tok);
    }

    Token processPlus(Token tok) throws ParseException {
        next();
        return Token.createConcat(tok, Token.createClosure(tok));
    }

    Token processQuestion(Token tok) throws ParseException {
        next();
        Token par = Token.createUnion();
        par.addChild(tok);
        par.addChild(Token.createEmpty());
        return par;
    }

    boolean checkQuestion(int off) {
        return false;
    }

    Token processParen() throws ParseException {
        next();
        Token tok = Token.createParen(parseRegex(), 0);
        if (read() != 7) {
            throw ex("parser.factor.1", this.offset - 1);
        }
        next();
        return tok;
    }

    Token processParen2() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    Token processCondition() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    Token processModifiers() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    Token processIndependent() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    Token processBacksolidus_c() throws ParseException {
        next();
        return getTokenForShorthand(99);
    }

    Token processBacksolidus_C() throws ParseException {
        next();
        return getTokenForShorthand(67);
    }

    Token processBacksolidus_i() throws ParseException {
        next();
        return getTokenForShorthand(105);
    }

    Token processBacksolidus_I() throws ParseException {
        next();
        return getTokenForShorthand(73);
    }

    Token processBacksolidus_g() throws ParseException {
        throw ex("parser.process.1", this.offset - 2);
    }

    Token processBacksolidus_X() throws ParseException {
        throw ex("parser.process.1", this.offset - 2);
    }

    Token processBackreference() throws ParseException {
        throw ex("parser.process.1", this.offset - 4);
    }

    int processCIinCharacterClass(RangeToken tok, int c) {
        tok.mergeRanges(getTokenForShorthand(c));
        return -1;
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
            base = Token.createRange();
            base.addRange(0, 1114111);
            tok = Token.createRange();
        } else {
            tok = Token.createRange();
        }
        boolean firstloop = true;
        while (true) {
            int type = read();
            if (type != 1) {
                if (type != 0 || this.chardata != 93 || firstloop) {
                    int c = this.chardata;
                    boolean end = false;
                    if (type == 10) {
                        switch (c) {
                            case 45:
                                c = decodeEscaped();
                                break;
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
                    } else if (type == 24 && !firstloop) {
                        if (nrange) {
                            base.subtractRanges(tok);
                            tok = base;
                        }
                        tok.subtractRanges(parseCharacterClass(false));
                        if (!(read() == 0 && this.chardata == 93)) {
                            throw ex("parser.cc.5", this.offset);
                        }
                    }
                    next();
                    if (!end) {
                        if (type == 0) {
                            if (c == 91) {
                                throw ex("parser.cc.6", this.offset - 2);
                            } else if (c == 93) {
                                throw ex("parser.cc.7", this.offset - 2);
                            } else if (!(c != 45 || this.chardata == 93 || firstloop)) {
                                throw ex("parser.cc.8", this.offset - 2);
                            }
                        }
                        if (read() == 0 && this.chardata == 45 && (c != 45 || !firstloop)) {
                            next();
                            type = read();
                            if (type == 1) {
                                throw ex("parser.cc.2", this.offset);
                            } else if (type == 0 && this.chardata == 93) {
                                if (!isSet(2) || c > SupportMenu.USER_MASK) {
                                    tok.addRange(c, c);
                                } else {
                                    RegexParser.addCaseInsensitiveChar(tok, c);
                                }
                                tok.addRange(45, 45);
                            } else if (type == 24) {
                                throw ex("parser.cc.8", this.offset - 1);
                            } else {
                                int rangeend = this.chardata;
                                if (type == 0) {
                                    if (rangeend == 91) {
                                        throw ex("parser.cc.6", this.offset - 1);
                                    } else if (rangeend == 93) {
                                        throw ex("parser.cc.7", this.offset - 1);
                                    } else if (rangeend == 45) {
                                        throw ex("parser.cc.8", this.offset - 2);
                                    }
                                } else if (type == 10) {
                                    rangeend = decodeEscaped();
                                }
                                next();
                                if (c > rangeend) {
                                    throw ex("parser.ope.3", this.offset - 1);
                                } else if (!isSet(2) || (c > SupportMenu.USER_MASK && rangeend > SupportMenu.USER_MASK)) {
                                    tok.addRange(c, rangeend);
                                } else {
                                    RegexParser.addCaseInsensitiveCharRange(tok, c, rangeend);
                                }
                            }
                        } else if (!isSet(2) || c > SupportMenu.USER_MASK) {
                            tok.addRange(c, c);
                        } else {
                            RegexParser.addCaseInsensitiveChar(tok, c);
                        }
                    }
                    firstloop = false;
                } else if (nrange) {
                    base.subtractRanges(tok);
                    tok = base;
                }
            }
            if (read() == 1) {
                throw ex("parser.cc.2", this.offset);
            }
            tok.sortRanges();
            tok.compactRanges();
            setContext(0);
            next();
            return tok;
        }
    }

    protected RangeToken parseSetOperations() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    Token getTokenForShorthand(int ch) {
        switch (ch) {
            case 67:
                return getRange("xml:isNameChar", false);
            case 68:
                return getRange("xml:isDigit", false);
            case 73:
                return getRange("xml:isInitialNameChar", false);
            case 83:
                return getRange("xml:isSpace", false);
            case 87:
                return getRange("xml:isWord", false);
            case 99:
                return getRange("xml:isNameChar", true);
            case 100:
                return getRange("xml:isDigit", true);
            case 105:
                return getRange("xml:isInitialNameChar", true);
            case 115:
                return getRange("xml:isSpace", true);
            case 119:
                return getRange("xml:isWord", true);
            default:
                throw new RuntimeException("Internal Error: shorthands: \\u" + Integer.toString(ch, 16));
        }
    }

    int decodeEscaped() throws ParseException {
        if (read() != 10) {
            throw ex("parser.next.1", this.offset - 1);
        }
        int c = this.chardata;
        switch (c) {
            case 40:
            case 41:
            case 42:
            case 43:
            case 45:
            case 46:
            case 63:
            case 91:
            case 92:
            case 93:
            case 94:
            case 123:
            case 124:
            case 125:
                return c;
            case 110:
                return 10;
            case 114:
                return 13;
            case 116:
                return 9;
            default:
                throw ex("parser.process.1", this.offset - 2);
        }
    }

    protected static synchronized RangeToken getRange(String name, boolean positive) {
        RangeToken tok;
        synchronized (ParserForXMLSchema.class) {
            if (ranges == null) {
                ranges = new Hashtable();
                ranges2 = new Hashtable();
                Token tok2 = Token.createRange();
                setupRange(tok2, SPACES);
                ranges.put("xml:isSpace", tok2);
                ranges2.put("xml:isSpace", Token.complementRanges(tok2));
                tok2 = Token.createRange();
                setupRange(tok2, DIGITS);
                ranges.put("xml:isDigit", tok2);
                ranges2.put("xml:isDigit", Token.complementRanges(tok2));
                tok2 = Token.createRange();
                setupRange(tok2, DIGITS);
                ranges.put("xml:isDigit", tok2);
                ranges2.put("xml:isDigit", Token.complementRanges(tok2));
                tok2 = Token.createRange();
                setupRange(tok2, LETTERS);
                tok2.mergeRanges((Token) ranges.get("xml:isDigit"));
                ranges.put("xml:isWord", tok2);
                ranges2.put("xml:isWord", Token.complementRanges(tok2));
                tok2 = Token.createRange();
                setupRange(tok2, NAMECHARS);
                ranges.put("xml:isNameChar", tok2);
                ranges2.put("xml:isNameChar", Token.complementRanges(tok2));
                tok2 = Token.createRange();
                setupRange(tok2, LETTERS);
                tok2.addRange(95, 95);
                tok2.addRange(58, 58);
                ranges.put("xml:isInitialNameChar", tok2);
                ranges2.put("xml:isInitialNameChar", Token.complementRanges(tok2));
            }
            if (positive) {
                tok = (RangeToken) ranges.get(name);
            } else {
                tok = (RangeToken) ranges2.get(name);
            }
        }
        return tok;
    }

    static void setupRange(Token range, String src) {
        int len = src.length();
        for (int i = 0; i < len; i += 2) {
            range.addRange(src.charAt(i), src.charAt(i + 1));
        }
    }
}
