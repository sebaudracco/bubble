package mf.org.apache.xerces.impl.xpath;

import com.google.android.gms.common.server.FavaDiagnosticsEntity;
import cz.msebera.android.httpclient.cookie.ClientCookie;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.util.XMLSymbols;
import mf.org.apache.xerces.xni.NamespaceContext;
import mf.org.apache.xerces.xni.QName;
import org.slf4j.Marker;

public class XPath {
    private static final boolean DEBUG_ALL = false;
    private static final boolean DEBUG_ANY = false;
    private static final boolean DEBUG_XPATH_PARSE = false;
    protected final String fExpression;
    protected final LocationPath[] fLocationPaths;
    protected final SymbolTable fSymbolTable;

    private static class Scanner {
        private static final byte CHARTYPE_ATSIGN = (byte) 19;
        private static final byte CHARTYPE_CLOSE_BRACKET = (byte) 22;
        private static final byte CHARTYPE_CLOSE_PAREN = (byte) 7;
        private static final byte CHARTYPE_COLON = (byte) 15;
        private static final byte CHARTYPE_COMMA = (byte) 10;
        private static final byte CHARTYPE_DIGIT = (byte) 14;
        private static final byte CHARTYPE_DOLLAR = (byte) 5;
        private static final byte CHARTYPE_EQUAL = (byte) 17;
        private static final byte CHARTYPE_EXCLAMATION = (byte) 3;
        private static final byte CHARTYPE_GREATER = (byte) 18;
        private static final byte CHARTYPE_INVALID = (byte) 0;
        private static final byte CHARTYPE_LESS = (byte) 16;
        private static final byte CHARTYPE_LETTER = (byte) 20;
        private static final byte CHARTYPE_MINUS = (byte) 11;
        private static final byte CHARTYPE_NONASCII = (byte) 25;
        private static final byte CHARTYPE_OPEN_BRACKET = (byte) 21;
        private static final byte CHARTYPE_OPEN_PAREN = (byte) 6;
        private static final byte CHARTYPE_OTHER = (byte) 1;
        private static final byte CHARTYPE_PERIOD = (byte) 12;
        private static final byte CHARTYPE_PLUS = (byte) 9;
        private static final byte CHARTYPE_QUOTE = (byte) 4;
        private static final byte CHARTYPE_SLASH = (byte) 13;
        private static final byte CHARTYPE_STAR = (byte) 8;
        private static final byte CHARTYPE_UNDERSCORE = (byte) 23;
        private static final byte CHARTYPE_UNION = (byte) 24;
        private static final byte CHARTYPE_WHITESPACE = (byte) 2;
        private static final byte[] fASCIICharMap;
        private static final String fAncestorOrSelfSymbol = "ancestor-or-self".intern();
        private static final String fAncestorSymbol = "ancestor".intern();
        private static final String fAndSymbol = "and".intern();
        private static final String fAttributeSymbol = "attribute".intern();
        private static final String fChildSymbol = "child".intern();
        private static final String fCommentSymbol = ClientCookie.COMMENT_ATTR.intern();
        private static final String fDescendantOrSelfSymbol = "descendant-or-self".intern();
        private static final String fDescendantSymbol = "descendant".intern();
        private static final String fDivSymbol = "div".intern();
        private static final String fFollowingSiblingSymbol = "following-sibling".intern();
        private static final String fFollowingSymbol = "following".intern();
        private static final String fModSymbol = "mod".intern();
        private static final String fNamespaceSymbol = FavaDiagnosticsEntity.EXTRA_NAMESPACE.intern();
        private static final String fNodeSymbol = "node".intern();
        private static final String fOrSymbol = "or".intern();
        private static final String fPISymbol = "processing-instruction".intern();
        private static final String fParentSymbol = "parent".intern();
        private static final String fPrecedingSiblingSymbol = "preceding-sibling".intern();
        private static final String fPrecedingSymbol = "preceding".intern();
        private static final String fSelfSymbol = "self".intern();
        private static final String fTextSymbol = "text".intern();
        private SymbolTable fSymbolTable;

        static {
            byte[] bArr = new byte[128];
            bArr[9] = CHARTYPE_WHITESPACE;
            bArr[10] = CHARTYPE_WHITESPACE;
            bArr[13] = CHARTYPE_WHITESPACE;
            bArr[32] = CHARTYPE_WHITESPACE;
            bArr[33] = CHARTYPE_EXCLAMATION;
            bArr[34] = CHARTYPE_QUOTE;
            bArr[35] = CHARTYPE_OTHER;
            bArr[36] = CHARTYPE_DOLLAR;
            bArr[37] = CHARTYPE_OTHER;
            bArr[38] = CHARTYPE_OTHER;
            bArr[39] = CHARTYPE_QUOTE;
            bArr[40] = CHARTYPE_OPEN_PAREN;
            bArr[41] = CHARTYPE_CLOSE_PAREN;
            bArr[42] = CHARTYPE_STAR;
            bArr[43] = CHARTYPE_PLUS;
            bArr[44] = CHARTYPE_COMMA;
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
            bArr[60] = CHARTYPE_LESS;
            bArr[61] = CHARTYPE_EQUAL;
            bArr[62] = CHARTYPE_GREATER;
            bArr[63] = CHARTYPE_OTHER;
            bArr[64] = CHARTYPE_ATSIGN;
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
            bArr[91] = CHARTYPE_OPEN_BRACKET;
            bArr[92] = CHARTYPE_OTHER;
            bArr[93] = (byte) 22;
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
            bArr[124] = CHARTYPE_UNION;
            bArr[125] = CHARTYPE_OTHER;
            bArr[126] = CHARTYPE_OTHER;
            bArr[127] = CHARTYPE_OTHER;
            fASCIICharMap = bArr;
        }

        public Scanner(SymbolTable symbolTable) {
            this.fSymbolTable = symbolTable;
        }

        public boolean scanExpr(SymbolTable symbolTable, Tokens tokens, String data, int currentOffset, int endOffset) throws XPathException {
            boolean starIsMultiplyOperator = false;
            while (currentOffset != endOffset) {
                int ch = data.charAt(currentOffset);
                while (true) {
                    if (ch == 32 || ch == 10 || ch == 9 || ch == 13) {
                        currentOffset++;
                        if (currentOffset != endOffset) {
                            ch = data.charAt(currentOffset);
                        }
                    }
                    if (currentOffset == endOffset) {
                        return true;
                    }
                    int nameOffset;
                    String nameHandle;
                    String prefixHandle;
                    switch (ch >= 128 ? CHARTYPE_NONASCII : fASCIICharMap[ch]) {
                        case (byte) 3:
                            currentOffset++;
                            if (currentOffset != endOffset) {
                                if (data.charAt(currentOffset) == 61) {
                                    addToken(tokens, 27);
                                    starIsMultiplyOperator = false;
                                    currentOffset++;
                                    if (currentOffset != endOffset) {
                                        break;
                                    }
                                    break;
                                }
                                return false;
                            }
                            return false;
                        case (byte) 4:
                            int qchar = ch;
                            currentOffset++;
                            if (currentOffset != endOffset) {
                                ch = data.charAt(currentOffset);
                                int litOffset = currentOffset;
                                while (ch != qchar) {
                                    currentOffset++;
                                    if (currentOffset == endOffset) {
                                        return false;
                                    }
                                    ch = data.charAt(currentOffset);
                                }
                                int litLength = currentOffset - litOffset;
                                addToken(tokens, 46);
                                starIsMultiplyOperator = true;
                                tokens.addToken(symbolTable.addSymbol(data.substring(litOffset, litOffset + litLength)));
                                currentOffset++;
                                if (currentOffset != endOffset) {
                                    break;
                                }
                                break;
                            }
                            return false;
                        case (byte) 5:
                            currentOffset++;
                            if (currentOffset != endOffset) {
                                nameOffset = currentOffset;
                                currentOffset = scanNCName(data, endOffset, currentOffset);
                                if (currentOffset != nameOffset) {
                                    if (currentOffset < endOffset) {
                                        ch = data.charAt(currentOffset);
                                    } else {
                                        ch = -1;
                                    }
                                    nameHandle = symbolTable.addSymbol(data.substring(nameOffset, currentOffset));
                                    if (ch != 58) {
                                        prefixHandle = XMLSymbols.EMPTY_STRING;
                                    } else {
                                        prefixHandle = nameHandle;
                                        currentOffset++;
                                        if (currentOffset == endOffset) {
                                            return false;
                                        }
                                        nameOffset = currentOffset;
                                        currentOffset = scanNCName(data, endOffset, currentOffset);
                                        if (currentOffset == nameOffset) {
                                            return false;
                                        }
                                        if (currentOffset < endOffset) {
                                            ch = data.charAt(currentOffset);
                                        }
                                        nameHandle = symbolTable.addSymbol(data.substring(nameOffset, currentOffset));
                                    }
                                    addToken(tokens, 48);
                                    starIsMultiplyOperator = true;
                                    tokens.addToken(prefixHandle);
                                    tokens.addToken(nameHandle);
                                    break;
                                }
                                return false;
                            }
                            return false;
                        case (byte) 6:
                            addToken(tokens, 0);
                            starIsMultiplyOperator = false;
                            currentOffset++;
                            if (currentOffset != endOffset) {
                                break;
                            }
                            break;
                        case (byte) 7:
                            addToken(tokens, 1);
                            starIsMultiplyOperator = true;
                            currentOffset++;
                            if (currentOffset != endOffset) {
                                break;
                            }
                            break;
                        case (byte) 8:
                            if (starIsMultiplyOperator) {
                                addToken(tokens, 20);
                                starIsMultiplyOperator = false;
                            } else {
                                addToken(tokens, 9);
                                starIsMultiplyOperator = true;
                            }
                            currentOffset++;
                            if (currentOffset != endOffset) {
                                break;
                            }
                            break;
                        case (byte) 9:
                            addToken(tokens, 24);
                            starIsMultiplyOperator = false;
                            currentOffset++;
                            if (currentOffset != endOffset) {
                                break;
                            }
                            break;
                        case (byte) 10:
                            addToken(tokens, 7);
                            starIsMultiplyOperator = false;
                            currentOffset++;
                            if (currentOffset != endOffset) {
                                break;
                            }
                            break;
                        case (byte) 11:
                            addToken(tokens, 25);
                            starIsMultiplyOperator = false;
                            currentOffset++;
                            if (currentOffset != endOffset) {
                                break;
                            }
                            break;
                        case (byte) 12:
                            if (currentOffset + 1 != endOffset) {
                                ch = data.charAt(currentOffset + 1);
                                if (ch == 46) {
                                    addToken(tokens, 5);
                                    starIsMultiplyOperator = true;
                                    currentOffset += 2;
                                } else if (ch >= 48 && ch <= 57) {
                                    addToken(tokens, 47);
                                    starIsMultiplyOperator = true;
                                    currentOffset = scanNumber(tokens, data, endOffset, currentOffset);
                                } else if (ch == 47) {
                                    addToken(tokens, 4);
                                    starIsMultiplyOperator = true;
                                    currentOffset++;
                                } else if (ch == 124) {
                                    addToken(tokens, 4);
                                    starIsMultiplyOperator = true;
                                    currentOffset++;
                                    break;
                                } else if (ch == 32 || ch == 10 || ch == 9 || ch == 13) {
                                    while (true) {
                                        currentOffset++;
                                        if (currentOffset != endOffset) {
                                            ch = data.charAt(currentOffset);
                                            if (ch == 32 || ch == 10 || ch == 9 || ch == 13) {
                                            }
                                        }
                                        if (currentOffset == endOffset || ch == 124) {
                                            addToken(tokens, 4);
                                            starIsMultiplyOperator = true;
                                            break;
                                        }
                                        throw new XPathException("c-general-xpath");
                                    }
                                } else {
                                    throw new XPathException("c-general-xpath");
                                }
                                if (currentOffset != endOffset) {
                                    break;
                                }
                                break;
                            }
                            addToken(tokens, 4);
                            starIsMultiplyOperator = true;
                            currentOffset++;
                            break;
                        case (byte) 13:
                            currentOffset++;
                            if (currentOffset != endOffset) {
                                if (data.charAt(currentOffset) != 47) {
                                    addToken(tokens, 21);
                                    starIsMultiplyOperator = false;
                                    break;
                                }
                                addToken(tokens, 22);
                                starIsMultiplyOperator = false;
                                currentOffset++;
                                if (currentOffset != endOffset) {
                                    break;
                                }
                                break;
                            }
                            addToken(tokens, 21);
                            starIsMultiplyOperator = false;
                            break;
                        case (byte) 14:
                            addToken(tokens, 47);
                            starIsMultiplyOperator = true;
                            currentOffset = scanNumber(tokens, data, endOffset, currentOffset);
                            break;
                        case (byte) 15:
                            currentOffset++;
                            if (currentOffset != endOffset) {
                                if (data.charAt(currentOffset) == 58) {
                                    addToken(tokens, 8);
                                    starIsMultiplyOperator = false;
                                    currentOffset++;
                                    if (currentOffset != endOffset) {
                                        break;
                                    }
                                    break;
                                }
                                return false;
                            }
                            return false;
                        case (byte) 16:
                            currentOffset++;
                            if (currentOffset != endOffset) {
                                if (data.charAt(currentOffset) != 61) {
                                    addToken(tokens, 28);
                                    starIsMultiplyOperator = false;
                                    break;
                                }
                                addToken(tokens, 29);
                                starIsMultiplyOperator = false;
                                currentOffset++;
                                if (currentOffset != endOffset) {
                                    break;
                                }
                                break;
                            }
                            addToken(tokens, 28);
                            starIsMultiplyOperator = false;
                            break;
                        case (byte) 17:
                            addToken(tokens, 26);
                            starIsMultiplyOperator = false;
                            currentOffset++;
                            if (currentOffset != endOffset) {
                                break;
                            }
                            break;
                        case (byte) 18:
                            currentOffset++;
                            if (currentOffset != endOffset) {
                                if (data.charAt(currentOffset) != 61) {
                                    addToken(tokens, 30);
                                    starIsMultiplyOperator = false;
                                    break;
                                }
                                addToken(tokens, 31);
                                starIsMultiplyOperator = false;
                                currentOffset++;
                                if (currentOffset != endOffset) {
                                    break;
                                }
                                break;
                            }
                            addToken(tokens, 30);
                            starIsMultiplyOperator = false;
                            break;
                        case (byte) 19:
                            addToken(tokens, 6);
                            starIsMultiplyOperator = false;
                            currentOffset++;
                            if (currentOffset != endOffset) {
                                break;
                            }
                            break;
                        case (byte) 20:
                        case (byte) 23:
                        case (byte) 25:
                            nameOffset = currentOffset;
                            currentOffset = scanNCName(data, endOffset, currentOffset);
                            if (currentOffset != nameOffset) {
                                if (currentOffset < endOffset) {
                                    ch = data.charAt(currentOffset);
                                } else {
                                    ch = -1;
                                }
                                nameHandle = symbolTable.addSymbol(data.substring(nameOffset, currentOffset));
                                boolean isNameTestNCName = false;
                                boolean isAxisName = false;
                                prefixHandle = XMLSymbols.EMPTY_STRING;
                                if (ch == 58) {
                                    currentOffset++;
                                    if (currentOffset == endOffset) {
                                        return false;
                                    }
                                    ch = data.charAt(currentOffset);
                                    if (ch == 42) {
                                        currentOffset++;
                                        if (currentOffset < endOffset) {
                                            ch = data.charAt(currentOffset);
                                        }
                                        isNameTestNCName = true;
                                    } else if (ch == 58) {
                                        currentOffset++;
                                        if (currentOffset < endOffset) {
                                            ch = data.charAt(currentOffset);
                                        }
                                        isAxisName = true;
                                    } else {
                                        prefixHandle = nameHandle;
                                        nameOffset = currentOffset;
                                        currentOffset = scanNCName(data, endOffset, currentOffset);
                                        if (currentOffset == nameOffset) {
                                            return false;
                                        }
                                        if (currentOffset < endOffset) {
                                            ch = data.charAt(currentOffset);
                                        } else {
                                            ch = -1;
                                        }
                                        nameHandle = symbolTable.addSymbol(data.substring(nameOffset, currentOffset));
                                    }
                                }
                                while (true) {
                                    if (ch == 32 || ch == 10 || ch == 9 || ch == 13) {
                                        currentOffset++;
                                        if (currentOffset != endOffset) {
                                            ch = data.charAt(currentOffset);
                                        }
                                    }
                                    if (!starIsMultiplyOperator) {
                                        if (ch != 40 || isNameTestNCName || isAxisName) {
                                            if (!isAxisName && (ch != 58 || currentOffset + 1 >= endOffset || data.charAt(currentOffset + 1) != ':')) {
                                                if (!isNameTestNCName) {
                                                    addToken(tokens, 11);
                                                    starIsMultiplyOperator = true;
                                                    tokens.addToken(prefixHandle);
                                                    tokens.addToken(nameHandle);
                                                    break;
                                                }
                                                addToken(tokens, 10);
                                                starIsMultiplyOperator = true;
                                                tokens.addToken(nameHandle);
                                                break;
                                            }
                                            if (nameHandle == fAncestorSymbol) {
                                                addToken(tokens, 33);
                                            } else if (nameHandle == fAncestorOrSelfSymbol) {
                                                addToken(tokens, 34);
                                            } else if (nameHandle == fAttributeSymbol) {
                                                addToken(tokens, 35);
                                            } else if (nameHandle == fChildSymbol) {
                                                addToken(tokens, 36);
                                            } else if (nameHandle == fDescendantSymbol) {
                                                addToken(tokens, 37);
                                            } else if (nameHandle == fDescendantOrSelfSymbol) {
                                                addToken(tokens, 38);
                                            } else if (nameHandle == fFollowingSymbol) {
                                                addToken(tokens, 39);
                                            } else if (nameHandle == fFollowingSiblingSymbol) {
                                                addToken(tokens, 40);
                                            } else if (nameHandle == fNamespaceSymbol) {
                                                addToken(tokens, 41);
                                            } else if (nameHandle == fParentSymbol) {
                                                addToken(tokens, 42);
                                            } else if (nameHandle == fPrecedingSymbol) {
                                                addToken(tokens, 43);
                                            } else if (nameHandle == fPrecedingSiblingSymbol) {
                                                addToken(tokens, 44);
                                            } else if (nameHandle != fSelfSymbol) {
                                                return false;
                                            } else {
                                                addToken(tokens, 45);
                                            }
                                            if (!isNameTestNCName) {
                                                addToken(tokens, 8);
                                                starIsMultiplyOperator = false;
                                                if (!isAxisName) {
                                                    currentOffset = (currentOffset + 1) + 1;
                                                    if (currentOffset != endOffset) {
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            return false;
                                        }
                                        if (nameHandle == fCommentSymbol) {
                                            addToken(tokens, 12);
                                        } else if (nameHandle == fTextSymbol) {
                                            addToken(tokens, 13);
                                        } else if (nameHandle == fPISymbol) {
                                            addToken(tokens, 14);
                                        } else if (nameHandle == fNodeSymbol) {
                                            addToken(tokens, 15);
                                        } else {
                                            addToken(tokens, 32);
                                            tokens.addToken(prefixHandle);
                                            tokens.addToken(nameHandle);
                                        }
                                        addToken(tokens, 0);
                                        starIsMultiplyOperator = false;
                                        currentOffset++;
                                        if (currentOffset != endOffset) {
                                            break;
                                        }
                                        break;
                                    }
                                    if (nameHandle == fAndSymbol) {
                                        addToken(tokens, 16);
                                        starIsMultiplyOperator = false;
                                    } else if (nameHandle == fOrSymbol) {
                                        addToken(tokens, 17);
                                        starIsMultiplyOperator = false;
                                    } else if (nameHandle == fModSymbol) {
                                        addToken(tokens, 18);
                                        starIsMultiplyOperator = false;
                                    } else if (nameHandle != fDivSymbol) {
                                        return false;
                                    } else {
                                        addToken(tokens, 19);
                                        starIsMultiplyOperator = false;
                                    }
                                    if (!isNameTestNCName) {
                                        if (!isAxisName) {
                                            break;
                                        }
                                        return false;
                                    }
                                    return false;
                                }
                            }
                            return false;
                        case (byte) 21:
                            addToken(tokens, 2);
                            starIsMultiplyOperator = false;
                            currentOffset++;
                            if (currentOffset != endOffset) {
                                break;
                            }
                            break;
                        case (byte) 22:
                            addToken(tokens, 3);
                            starIsMultiplyOperator = true;
                            currentOffset++;
                            if (currentOffset != endOffset) {
                                break;
                            }
                            break;
                        case (byte) 24:
                            addToken(tokens, 23);
                            starIsMultiplyOperator = false;
                            currentOffset++;
                            if (currentOffset != endOffset) {
                                break;
                            }
                            break;
                        default:
                            return false;
                    }
                }
            }
            return true;
        }

        int scanNCName(String data, int endOffset, int currentOffset) {
            byte chartype;
            int ch = data.charAt(currentOffset);
            if (ch < 128) {
                chartype = fASCIICharMap[ch];
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
                        chartype = fASCIICharMap[ch];
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

        private int scanNumber(Tokens tokens, String data, int endOffset, int currentOffset) {
            int ch = data.charAt(currentOffset);
            int whole = 0;
            int part = 0;
            while (ch >= 48 && ch <= 57) {
                whole = (whole * 10) + (ch - 48);
                currentOffset++;
                if (currentOffset == endOffset) {
                    break;
                }
                ch = data.charAt(currentOffset);
            }
            if (ch == 46) {
                currentOffset++;
                if (currentOffset < endOffset) {
                    ch = data.charAt(currentOffset);
                    while (ch >= 48 && ch <= 57) {
                        part = (part * 10) + (ch - 48);
                        currentOffset++;
                        if (currentOffset == endOffset) {
                            break;
                        }
                        ch = data.charAt(currentOffset);
                    }
                    if (part != 0) {
                        throw new RuntimeException("find a solution!");
                    }
                }
            }
            tokens.addToken(whole);
            tokens.addToken(part);
            return currentOffset;
        }

        protected void addToken(Tokens tokens, int token) throws XPathException {
            tokens.addToken(token);
        }
    }

    public static class Axis implements Cloneable {
        public static final short ATTRIBUTE = (short) 2;
        public static final short CHILD = (short) 1;
        public static final short DESCENDANT = (short) 4;
        public static final short SELF = (short) 3;
        public final short type;

        public Axis(short type) {
            this.type = type;
        }

        protected Axis(Axis axis) {
            this.type = axis.type;
        }

        public String toString() {
            switch (this.type) {
                case (short) 1:
                    return "child";
                case (short) 2:
                    return "attribute";
                case (short) 3:
                    return "self";
                case (short) 4:
                    return "descendant";
                default:
                    return "???";
            }
        }

        public Object clone() {
            return new Axis(this);
        }
    }

    public static class LocationPath implements Cloneable {
        public final Step[] steps;

        public LocationPath(Step[] steps) {
            this.steps = steps;
        }

        protected LocationPath(LocationPath path) {
            this.steps = new Step[path.steps.length];
            for (int i = 0; i < this.steps.length; i++) {
                this.steps[i] = (Step) path.steps[i].clone();
            }
        }

        public String toString() {
            StringBuffer str = new StringBuffer();
            int i = 0;
            while (i < this.steps.length) {
                if (!(i <= 0 || this.steps[i - 1].axis.type == (short) 4 || this.steps[i].axis.type == (short) 4)) {
                    str.append('/');
                }
                str.append(this.steps[i].toString());
                i++;
            }
            return str.toString();
        }

        public Object clone() {
            return new LocationPath(this);
        }
    }

    public static class NodeTest implements Cloneable {
        public static final short NAMESPACE = (short) 4;
        public static final short NODE = (short) 3;
        public static final short QNAME = (short) 1;
        public static final short WILDCARD = (short) 2;
        public final QName name;
        public final short type;

        public NodeTest(short type) {
            this.name = new QName();
            this.type = type;
        }

        public NodeTest(QName name) {
            this.name = new QName();
            this.type = (short) 1;
            this.name.setValues(name);
        }

        public NodeTest(String prefix, String uri) {
            this.name = new QName();
            this.type = (short) 4;
            this.name.setValues(prefix, null, null, uri);
        }

        public NodeTest(NodeTest nodeTest) {
            this.name = new QName();
            this.type = nodeTest.type;
            this.name.setValues(nodeTest.name);
        }

        public String toString() {
            switch (this.type) {
                case (short) 1:
                    if (this.name.prefix.length() == 0) {
                        return this.name.localpart;
                    }
                    if (this.name.uri != null) {
                        return new StringBuilder(String.valueOf(this.name.prefix)).append(':').append(this.name.localpart).toString();
                    }
                    return "{" + this.name.uri + '}' + this.name.prefix + ':' + this.name.localpart;
                case (short) 2:
                    return Marker.ANY_MARKER;
                case (short) 3:
                    return "node()";
                case (short) 4:
                    if (this.name.prefix.length() == 0) {
                        return "???:*";
                    }
                    if (this.name.uri != null) {
                        return new StringBuilder(String.valueOf(this.name.prefix)).append(":*").toString();
                    }
                    return "{" + this.name.uri + '}' + this.name.prefix + ":*";
                default:
                    return "???";
            }
        }

        public Object clone() {
            return new NodeTest(this);
        }
    }

    public static class Step implements Cloneable {
        public final Axis axis;
        public final NodeTest nodeTest;

        public Step(Axis axis, NodeTest nodeTest) {
            this.axis = axis;
            this.nodeTest = nodeTest;
        }

        protected Step(Step step) {
            this.axis = (Axis) step.axis.clone();
            this.nodeTest = (NodeTest) step.nodeTest.clone();
        }

        public String toString() {
            if (this.axis.type == (short) 3) {
                return ".";
            }
            if (this.axis.type == (short) 2) {
                return "@" + this.nodeTest.toString();
            }
            if (this.axis.type == (short) 1) {
                return this.nodeTest.toString();
            }
            if (this.axis.type == (short) 4) {
                return "//";
            }
            return "??? (" + this.axis.type + ')';
        }

        public Object clone() {
            return new Step(this);
        }
    }

    private static final class Tokens {
        static final boolean DUMP_TOKENS = false;
        public static final int EXPRTOKEN_ATSIGN = 6;
        public static final int EXPRTOKEN_AXISNAME_ANCESTOR = 33;
        public static final int EXPRTOKEN_AXISNAME_ANCESTOR_OR_SELF = 34;
        public static final int EXPRTOKEN_AXISNAME_ATTRIBUTE = 35;
        public static final int EXPRTOKEN_AXISNAME_CHILD = 36;
        public static final int EXPRTOKEN_AXISNAME_DESCENDANT = 37;
        public static final int EXPRTOKEN_AXISNAME_DESCENDANT_OR_SELF = 38;
        public static final int EXPRTOKEN_AXISNAME_FOLLOWING = 39;
        public static final int EXPRTOKEN_AXISNAME_FOLLOWING_SIBLING = 40;
        public static final int EXPRTOKEN_AXISNAME_NAMESPACE = 41;
        public static final int EXPRTOKEN_AXISNAME_PARENT = 42;
        public static final int EXPRTOKEN_AXISNAME_PRECEDING = 43;
        public static final int EXPRTOKEN_AXISNAME_PRECEDING_SIBLING = 44;
        public static final int EXPRTOKEN_AXISNAME_SELF = 45;
        public static final int EXPRTOKEN_CLOSE_BRACKET = 3;
        public static final int EXPRTOKEN_CLOSE_PAREN = 1;
        public static final int EXPRTOKEN_COMMA = 7;
        public static final int EXPRTOKEN_DOUBLE_COLON = 8;
        public static final int EXPRTOKEN_DOUBLE_PERIOD = 5;
        public static final int EXPRTOKEN_FUNCTION_NAME = 32;
        public static final int EXPRTOKEN_LITERAL = 46;
        public static final int EXPRTOKEN_NAMETEST_ANY = 9;
        public static final int EXPRTOKEN_NAMETEST_NAMESPACE = 10;
        public static final int EXPRTOKEN_NAMETEST_QNAME = 11;
        public static final int EXPRTOKEN_NODETYPE_COMMENT = 12;
        public static final int EXPRTOKEN_NODETYPE_NODE = 15;
        public static final int EXPRTOKEN_NODETYPE_PI = 14;
        public static final int EXPRTOKEN_NODETYPE_TEXT = 13;
        public static final int EXPRTOKEN_NUMBER = 47;
        public static final int EXPRTOKEN_OPEN_BRACKET = 2;
        public static final int EXPRTOKEN_OPEN_PAREN = 0;
        public static final int EXPRTOKEN_OPERATOR_AND = 16;
        public static final int EXPRTOKEN_OPERATOR_DIV = 19;
        public static final int EXPRTOKEN_OPERATOR_DOUBLE_SLASH = 22;
        public static final int EXPRTOKEN_OPERATOR_EQUAL = 26;
        public static final int EXPRTOKEN_OPERATOR_GREATER = 30;
        public static final int EXPRTOKEN_OPERATOR_GREATER_EQUAL = 31;
        public static final int EXPRTOKEN_OPERATOR_LESS = 28;
        public static final int EXPRTOKEN_OPERATOR_LESS_EQUAL = 29;
        public static final int EXPRTOKEN_OPERATOR_MINUS = 25;
        public static final int EXPRTOKEN_OPERATOR_MOD = 18;
        public static final int EXPRTOKEN_OPERATOR_MULT = 20;
        public static final int EXPRTOKEN_OPERATOR_NOT_EQUAL = 27;
        public static final int EXPRTOKEN_OPERATOR_OR = 17;
        public static final int EXPRTOKEN_OPERATOR_PLUS = 24;
        public static final int EXPRTOKEN_OPERATOR_SLASH = 21;
        public static final int EXPRTOKEN_OPERATOR_UNION = 23;
        public static final int EXPRTOKEN_PERIOD = 4;
        public static final int EXPRTOKEN_VARIABLE_REFERENCE = 48;
        private static final int INITIAL_TOKEN_COUNT = 256;
        private static final String[] fgTokenNames = new String[]{"EXPRTOKEN_OPEN_PAREN", "EXPRTOKEN_CLOSE_PAREN", "EXPRTOKEN_OPEN_BRACKET", "EXPRTOKEN_CLOSE_BRACKET", "EXPRTOKEN_PERIOD", "EXPRTOKEN_DOUBLE_PERIOD", "EXPRTOKEN_ATSIGN", "EXPRTOKEN_COMMA", "EXPRTOKEN_DOUBLE_COLON", "EXPRTOKEN_NAMETEST_ANY", "EXPRTOKEN_NAMETEST_NAMESPACE", "EXPRTOKEN_NAMETEST_QNAME", "EXPRTOKEN_NODETYPE_COMMENT", "EXPRTOKEN_NODETYPE_TEXT", "EXPRTOKEN_NODETYPE_PI", "EXPRTOKEN_NODETYPE_NODE", "EXPRTOKEN_OPERATOR_AND", "EXPRTOKEN_OPERATOR_OR", "EXPRTOKEN_OPERATOR_MOD", "EXPRTOKEN_OPERATOR_DIV", "EXPRTOKEN_OPERATOR_MULT", "EXPRTOKEN_OPERATOR_SLASH", "EXPRTOKEN_OPERATOR_DOUBLE_SLASH", "EXPRTOKEN_OPERATOR_UNION", "EXPRTOKEN_OPERATOR_PLUS", "EXPRTOKEN_OPERATOR_MINUS", "EXPRTOKEN_OPERATOR_EQUAL", "EXPRTOKEN_OPERATOR_NOT_EQUAL", "EXPRTOKEN_OPERATOR_LESS", "EXPRTOKEN_OPERATOR_LESS_EQUAL", "EXPRTOKEN_OPERATOR_GREATER", "EXPRTOKEN_OPERATOR_GREATER_EQUAL", "EXPRTOKEN_FUNCTION_NAME", "EXPRTOKEN_AXISNAME_ANCESTOR", "EXPRTOKEN_AXISNAME_ANCESTOR_OR_SELF", "EXPRTOKEN_AXISNAME_ATTRIBUTE", "EXPRTOKEN_AXISNAME_CHILD", "EXPRTOKEN_AXISNAME_DESCENDANT", "EXPRTOKEN_AXISNAME_DESCENDANT_OR_SELF", "EXPRTOKEN_AXISNAME_FOLLOWING", "EXPRTOKEN_AXISNAME_FOLLOWING_SIBLING", "EXPRTOKEN_AXISNAME_NAMESPACE", "EXPRTOKEN_AXISNAME_PARENT", "EXPRTOKEN_AXISNAME_PRECEDING", "EXPRTOKEN_AXISNAME_PRECEDING_SIBLING", "EXPRTOKEN_AXISNAME_SELF", "EXPRTOKEN_LITERAL", "EXPRTOKEN_NUMBER", "EXPRTOKEN_VARIABLE_REFERENCE"};
        private int fCurrentTokenIndex;
        private Hashtable fSymbolMapping = new Hashtable();
        private SymbolTable fSymbolTable;
        private int fTokenCount = 0;
        private Hashtable fTokenNames = new Hashtable();
        private int[] fTokens = new int[256];

        public Tokens(SymbolTable symbolTable) {
            this.fSymbolTable = symbolTable;
            String[] symbols = new String[]{"ancestor", "ancestor-or-self", "attribute", "child", "descendant", "descendant-or-self", "following", "following-sibling", FavaDiagnosticsEntity.EXTRA_NAMESPACE, "parent", "preceding", "preceding-sibling", "self"};
            for (int i = 0; i < symbols.length; i++) {
                this.fSymbolMapping.put(this.fSymbolTable.addSymbol(symbols[i]), new Integer(i));
            }
            this.fTokenNames.put(new Integer(0), "EXPRTOKEN_OPEN_PAREN");
            this.fTokenNames.put(new Integer(1), "EXPRTOKEN_CLOSE_PAREN");
            this.fTokenNames.put(new Integer(2), "EXPRTOKEN_OPEN_BRACKET");
            this.fTokenNames.put(new Integer(3), "EXPRTOKEN_CLOSE_BRACKET");
            this.fTokenNames.put(new Integer(4), "EXPRTOKEN_PERIOD");
            this.fTokenNames.put(new Integer(5), "EXPRTOKEN_DOUBLE_PERIOD");
            this.fTokenNames.put(new Integer(6), "EXPRTOKEN_ATSIGN");
            this.fTokenNames.put(new Integer(7), "EXPRTOKEN_COMMA");
            this.fTokenNames.put(new Integer(8), "EXPRTOKEN_DOUBLE_COLON");
            this.fTokenNames.put(new Integer(9), "EXPRTOKEN_NAMETEST_ANY");
            this.fTokenNames.put(new Integer(10), "EXPRTOKEN_NAMETEST_NAMESPACE");
            this.fTokenNames.put(new Integer(11), "EXPRTOKEN_NAMETEST_QNAME");
            this.fTokenNames.put(new Integer(12), "EXPRTOKEN_NODETYPE_COMMENT");
            this.fTokenNames.put(new Integer(13), "EXPRTOKEN_NODETYPE_TEXT");
            this.fTokenNames.put(new Integer(14), "EXPRTOKEN_NODETYPE_PI");
            this.fTokenNames.put(new Integer(15), "EXPRTOKEN_NODETYPE_NODE");
            this.fTokenNames.put(new Integer(16), "EXPRTOKEN_OPERATOR_AND");
            this.fTokenNames.put(new Integer(17), "EXPRTOKEN_OPERATOR_OR");
            this.fTokenNames.put(new Integer(18), "EXPRTOKEN_OPERATOR_MOD");
            this.fTokenNames.put(new Integer(19), "EXPRTOKEN_OPERATOR_DIV");
            this.fTokenNames.put(new Integer(20), "EXPRTOKEN_OPERATOR_MULT");
            this.fTokenNames.put(new Integer(21), "EXPRTOKEN_OPERATOR_SLASH");
            this.fTokenNames.put(new Integer(22), "EXPRTOKEN_OPERATOR_DOUBLE_SLASH");
            this.fTokenNames.put(new Integer(23), "EXPRTOKEN_OPERATOR_UNION");
            this.fTokenNames.put(new Integer(24), "EXPRTOKEN_OPERATOR_PLUS");
            this.fTokenNames.put(new Integer(25), "EXPRTOKEN_OPERATOR_MINUS");
            this.fTokenNames.put(new Integer(26), "EXPRTOKEN_OPERATOR_EQUAL");
            this.fTokenNames.put(new Integer(27), "EXPRTOKEN_OPERATOR_NOT_EQUAL");
            this.fTokenNames.put(new Integer(28), "EXPRTOKEN_OPERATOR_LESS");
            this.fTokenNames.put(new Integer(29), "EXPRTOKEN_OPERATOR_LESS_EQUAL");
            this.fTokenNames.put(new Integer(30), "EXPRTOKEN_OPERATOR_GREATER");
            this.fTokenNames.put(new Integer(31), "EXPRTOKEN_OPERATOR_GREATER_EQUAL");
            this.fTokenNames.put(new Integer(32), "EXPRTOKEN_FUNCTION_NAME");
            this.fTokenNames.put(new Integer(33), "EXPRTOKEN_AXISNAME_ANCESTOR");
            this.fTokenNames.put(new Integer(34), "EXPRTOKEN_AXISNAME_ANCESTOR_OR_SELF");
            this.fTokenNames.put(new Integer(35), "EXPRTOKEN_AXISNAME_ATTRIBUTE");
            this.fTokenNames.put(new Integer(36), "EXPRTOKEN_AXISNAME_CHILD");
            this.fTokenNames.put(new Integer(37), "EXPRTOKEN_AXISNAME_DESCENDANT");
            this.fTokenNames.put(new Integer(38), "EXPRTOKEN_AXISNAME_DESCENDANT_OR_SELF");
            this.fTokenNames.put(new Integer(39), "EXPRTOKEN_AXISNAME_FOLLOWING");
            this.fTokenNames.put(new Integer(40), "EXPRTOKEN_AXISNAME_FOLLOWING_SIBLING");
            this.fTokenNames.put(new Integer(41), "EXPRTOKEN_AXISNAME_NAMESPACE");
            this.fTokenNames.put(new Integer(42), "EXPRTOKEN_AXISNAME_PARENT");
            this.fTokenNames.put(new Integer(43), "EXPRTOKEN_AXISNAME_PRECEDING");
            this.fTokenNames.put(new Integer(44), "EXPRTOKEN_AXISNAME_PRECEDING_SIBLING");
            this.fTokenNames.put(new Integer(45), "EXPRTOKEN_AXISNAME_SELF");
            this.fTokenNames.put(new Integer(46), "EXPRTOKEN_LITERAL");
            this.fTokenNames.put(new Integer(47), "EXPRTOKEN_NUMBER");
            this.fTokenNames.put(new Integer(48), "EXPRTOKEN_VARIABLE_REFERENCE");
        }

        public String getTokenString(int token) {
            return (String) this.fTokenNames.get(new Integer(token));
        }

        public void addToken(String tokenStr) {
            Integer tokenInt = (Integer) this.fTokenNames.get(tokenStr);
            if (tokenInt == null) {
                tokenInt = new Integer(this.fTokenNames.size());
                this.fTokenNames.put(tokenInt, tokenStr);
            }
            addToken(tokenInt.intValue());
        }

        public void addToken(int token) {
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

        public void rewind() {
            this.fCurrentTokenIndex = 0;
        }

        public boolean hasMore() {
            return this.fCurrentTokenIndex < this.fTokenCount;
        }

        public int nextToken() throws XPathException {
            if (this.fCurrentTokenIndex == this.fTokenCount) {
                throw new XPathException("c-general-xpath");
            }
            int[] iArr = this.fTokens;
            int i = this.fCurrentTokenIndex;
            this.fCurrentTokenIndex = i + 1;
            return iArr[i];
        }

        public int peekToken() throws XPathException {
            if (this.fCurrentTokenIndex != this.fTokenCount) {
                return this.fTokens[this.fCurrentTokenIndex];
            }
            throw new XPathException("c-general-xpath");
        }

        public String nextTokenAsString() throws XPathException {
            String s = getTokenString(nextToken());
            if (s != null) {
                return s;
            }
            throw new XPathException("c-general-xpath");
        }

        public void dumpTokens() {
            int i = 0;
            while (i < this.fTokenCount) {
                switch (this.fTokens[i]) {
                    case 0:
                        System.out.print("<OPEN_PAREN/>");
                        break;
                    case 1:
                        System.out.print("<CLOSE_PAREN/>");
                        break;
                    case 2:
                        System.out.print("<OPEN_BRACKET/>");
                        break;
                    case 3:
                        System.out.print("<CLOSE_BRACKET/>");
                        break;
                    case 4:
                        System.out.print("<PERIOD/>");
                        break;
                    case 5:
                        System.out.print("<DOUBLE_PERIOD/>");
                        break;
                    case 6:
                        System.out.print("<ATSIGN/>");
                        break;
                    case 7:
                        System.out.print("<COMMA/>");
                        break;
                    case 8:
                        System.out.print("<DOUBLE_COLON/>");
                        break;
                    case 9:
                        System.out.print("<NAMETEST_ANY/>");
                        break;
                    case 10:
                        System.out.print("<NAMETEST_NAMESPACE");
                        i++;
                        System.out.print(" prefix=\"" + getTokenString(this.fTokens[i]) + "\"");
                        System.out.print("/>");
                        break;
                    case 11:
                        System.out.print("<NAMETEST_QNAME");
                        i++;
                        if (this.fTokens[i] != -1) {
                            System.out.print(" prefix=\"" + getTokenString(this.fTokens[i]) + "\"");
                        }
                        i++;
                        System.out.print(" localpart=\"" + getTokenString(this.fTokens[i]) + "\"");
                        System.out.print("/>");
                        break;
                    case 12:
                        System.out.print("<NODETYPE_COMMENT/>");
                        break;
                    case 13:
                        System.out.print("<NODETYPE_TEXT/>");
                        break;
                    case 14:
                        System.out.print("<NODETYPE_PI/>");
                        break;
                    case 15:
                        System.out.print("<NODETYPE_NODE/>");
                        break;
                    case 16:
                        System.out.print("<OPERATOR_AND/>");
                        break;
                    case 17:
                        System.out.print("<OPERATOR_OR/>");
                        break;
                    case 18:
                        System.out.print("<OPERATOR_MOD/>");
                        break;
                    case 19:
                        System.out.print("<OPERATOR_DIV/>");
                        break;
                    case 20:
                        System.out.print("<OPERATOR_MULT/>");
                        break;
                    case 21:
                        System.out.print("<OPERATOR_SLASH/>");
                        if (i + 1 >= this.fTokenCount) {
                            break;
                        }
                        System.out.println();
                        System.out.print("  ");
                        break;
                    case 22:
                        System.out.print("<OPERATOR_DOUBLE_SLASH/>");
                        break;
                    case 23:
                        System.out.print("<OPERATOR_UNION/>");
                        break;
                    case 24:
                        System.out.print("<OPERATOR_PLUS/>");
                        break;
                    case 25:
                        System.out.print("<OPERATOR_MINUS/>");
                        break;
                    case 26:
                        System.out.print("<OPERATOR_EQUAL/>");
                        break;
                    case 27:
                        System.out.print("<OPERATOR_NOT_EQUAL/>");
                        break;
                    case 28:
                        System.out.print("<OPERATOR_LESS/>");
                        break;
                    case 29:
                        System.out.print("<OPERATOR_LESS_EQUAL/>");
                        break;
                    case 30:
                        System.out.print("<OPERATOR_GREATER/>");
                        break;
                    case 31:
                        System.out.print("<OPERATOR_GREATER_EQUAL/>");
                        break;
                    case 32:
                        System.out.print("<FUNCTION_NAME");
                        i++;
                        if (this.fTokens[i] != -1) {
                            System.out.print(" prefix=\"" + getTokenString(this.fTokens[i]) + "\"");
                        }
                        i++;
                        System.out.print(" localpart=\"" + getTokenString(this.fTokens[i]) + "\"");
                        System.out.print("/>");
                        break;
                    case 33:
                        System.out.print("<AXISNAME_ANCESTOR/>");
                        break;
                    case 34:
                        System.out.print("<AXISNAME_ANCESTOR_OR_SELF/>");
                        break;
                    case 35:
                        System.out.print("<AXISNAME_ATTRIBUTE/>");
                        break;
                    case 36:
                        System.out.print("<AXISNAME_CHILD/>");
                        break;
                    case 37:
                        System.out.print("<AXISNAME_DESCENDANT/>");
                        break;
                    case 38:
                        System.out.print("<AXISNAME_DESCENDANT_OR_SELF/>");
                        break;
                    case 39:
                        System.out.print("<AXISNAME_FOLLOWING/>");
                        break;
                    case 40:
                        System.out.print("<AXISNAME_FOLLOWING_SIBLING/>");
                        break;
                    case 41:
                        System.out.print("<AXISNAME_NAMESPACE/>");
                        break;
                    case 42:
                        System.out.print("<AXISNAME_PARENT/>");
                        break;
                    case 43:
                        System.out.print("<AXISNAME_PRECEDING/>");
                        break;
                    case 44:
                        System.out.print("<AXISNAME_PRECEDING_SIBLING/>");
                        break;
                    case 45:
                        System.out.print("<AXISNAME_SELF/>");
                        break;
                    case 46:
                        System.out.print("<LITERAL");
                        i++;
                        System.out.print(" value=\"" + getTokenString(this.fTokens[i]) + "\"");
                        System.out.print("/>");
                        break;
                    case 47:
                        System.out.print("<NUMBER");
                        i++;
                        System.out.print(" whole=\"" + getTokenString(this.fTokens[i]) + "\"");
                        i++;
                        System.out.print(" part=\"" + getTokenString(this.fTokens[i]) + "\"");
                        System.out.print("/>");
                        break;
                    case 48:
                        System.out.print("<VARIABLE_REFERENCE");
                        i++;
                        if (this.fTokens[i] != -1) {
                            System.out.print(" prefix=\"" + getTokenString(this.fTokens[i]) + "\"");
                        }
                        i++;
                        System.out.print(" localpart=\"" + getTokenString(this.fTokens[i]) + "\"");
                        System.out.print("/>");
                        break;
                    default:
                        System.out.println("<???/>");
                        break;
                }
                i++;
            }
            System.out.println();
        }
    }

    public XPath(String xpath, SymbolTable symbolTable, NamespaceContext context) throws XPathException {
        this.fExpression = xpath;
        this.fSymbolTable = symbolTable;
        this.fLocationPaths = parseExpression(context);
    }

    public LocationPath[] getLocationPaths() {
        LocationPath[] ret = new LocationPath[this.fLocationPaths.length];
        for (int i = 0; i < this.fLocationPaths.length; i++) {
            ret[i] = (LocationPath) this.fLocationPaths[i].clone();
        }
        return ret;
    }

    public LocationPath getLocationPath() {
        return (LocationPath) this.fLocationPaths[0].clone();
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < this.fLocationPaths.length; i++) {
            if (i > 0) {
                buf.append('|');
            }
            buf.append(this.fLocationPaths[i].toString());
        }
        return buf.toString();
    }

    private static void check(boolean b) throws XPathException {
        if (!b) {
            throw new XPathException("c-general-xpath");
        }
    }

    private LocationPath buildLocationPath(Vector stepsVector) throws XPathException {
        int size = stepsVector.size();
        check(size != 0);
        Step[] steps = new Step[size];
        stepsVector.copyInto(steps);
        stepsVector.removeAllElements();
        return new LocationPath(steps);
    }

    private LocationPath[] parseExpression(NamespaceContext context) throws XPathException {
        Tokens xtokens = new Tokens(this.fSymbolTable);
        if (new Scanner(this.fSymbolTable) {
            protected void addToken(Tokens tokens, int token) throws XPathException {
                if (token == 6 || token == 11 || token == 21 || token == 4 || token == 9 || token == 10 || token == 22 || token == 23 || token == 36 || token == 35 || token == 8) {
                    super.addToken(tokens, token);
                    return;
                }
                throw new XPathException("c-general-xpath");
            }
        }.scanExpr(this.fSymbolTable, xtokens, this.fExpression, 0, this.fExpression.length())) {
            Vector stepsVector = new Vector();
            ArrayList locationPathsVector = new ArrayList();
            boolean expectingStep = true;
            while (xtokens.hasMore()) {
                int token = xtokens.nextToken();
                switch (token) {
                    case 4:
                        check(expectingStep);
                        expectingStep = false;
                        if (stepsVector.size() != 0) {
                            break;
                        }
                        stepsVector.addElement(new Step(new Axis((short) 3), new NodeTest((short) 3)));
                        if (xtokens.hasMore() && xtokens.peekToken() == 22) {
                            xtokens.nextToken();
                            stepsVector.addElement(new Step(new Axis((short) 4), new NodeTest((short) 3)));
                            expectingStep = true;
                            break;
                        }
                    case 6:
                        check(expectingStep);
                        stepsVector.addElement(new Step(new Axis((short) 2), parseNodeTest(xtokens.nextToken(), xtokens, context)));
                        expectingStep = false;
                        break;
                    case 8:
                        throw new XPathException("c-general-xpath");
                    case 9:
                    case 10:
                    case 11:
                        check(expectingStep);
                        stepsVector.addElement(new Step(new Axis((short) 1), parseNodeTest(token, xtokens, context)));
                        expectingStep = false;
                        break;
                    case 21:
                        check(!expectingStep);
                        expectingStep = true;
                        break;
                    case 22:
                        throw new XPathException("c-general-xpath");
                    case 23:
                        check(!expectingStep);
                        locationPathsVector.add(buildLocationPath(stepsVector));
                        expectingStep = true;
                        break;
                    case 35:
                        check(expectingStep);
                        if (xtokens.nextToken() == 8) {
                            stepsVector.addElement(new Step(new Axis((short) 2), parseNodeTest(xtokens.nextToken(), xtokens, context)));
                            expectingStep = false;
                            break;
                        }
                        throw new XPathException("c-general-xpath");
                    case 36:
                        check(expectingStep);
                        if (xtokens.nextToken() == 8) {
                            stepsVector.addElement(new Step(new Axis((short) 1), parseNodeTest(xtokens.nextToken(), xtokens, context)));
                            expectingStep = false;
                            break;
                        }
                        throw new XPathException("c-general-xpath");
                    default:
                        throw new InternalError();
                }
            }
            check(!expectingStep);
            locationPathsVector.add(buildLocationPath(stepsVector));
            return (LocationPath[]) locationPathsVector.toArray(new LocationPath[locationPathsVector.size()]);
        }
        throw new XPathException("c-general-xpath");
    }

    private NodeTest parseNodeTest(int typeToken, Tokens xtokens, NamespaceContext context) throws XPathException {
        switch (typeToken) {
            case 9:
                return new NodeTest((short) 2);
            case 10:
            case 11:
                String prefix = xtokens.nextTokenAsString();
                String uri = null;
                if (!(context == null || prefix == XMLSymbols.EMPTY_STRING)) {
                    uri = context.getURI(prefix);
                }
                if (prefix != XMLSymbols.EMPTY_STRING && context != null && uri == null) {
                    throw new XPathException("c-general-xpath-ns");
                } else if (typeToken == 10) {
                    return new NodeTest(prefix, uri);
                } else {
                    String rawname;
                    String localpart = xtokens.nextTokenAsString();
                    if (prefix != XMLSymbols.EMPTY_STRING) {
                        rawname = this.fSymbolTable.addSymbol(new StringBuilder(String.valueOf(prefix)).append(':').append(localpart).toString());
                    } else {
                        rawname = localpart;
                    }
                    return new NodeTest(new QName(prefix, localpart, rawname, uri));
                }
            default:
                throw new XPathException("c-general-xpath");
        }
    }

    public static void main(String[] argv) throws Exception {
        for (String expression : argv) {
            System.out.println("# XPath expression: \"" + expression + '\"');
            try {
                System.out.println("expanded xpath: \"" + new XPath(expression, new SymbolTable(), null).toString() + '\"');
            } catch (XPathException e) {
                System.out.println("error: " + e.getMessage());
            }
        }
    }
}
