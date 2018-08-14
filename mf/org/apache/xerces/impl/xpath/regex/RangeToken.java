package mf.org.apache.xerces.impl.xpath.regex;

import android.support.v4.internal.view.SupportMenu;
import java.io.Serializable;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

final class RangeToken extends Token implements Serializable {
    private static final int MAPSIZE = 256;
    private static final long serialVersionUID = -553983121197679934L;
    boolean compacted;
    RangeToken icaseCache = null;
    int[] map = null;
    int nonMapIndex;
    int[] ranges;
    boolean sorted;

    RangeToken(int type) {
        super(type);
        setSorted(false);
    }

    protected void addRange(int start, int end) {
        int r1;
        int r2;
        this.icaseCache = null;
        if (start <= end) {
            r1 = start;
            r2 = end;
        } else {
            r1 = end;
            r2 = start;
        }
        if (this.ranges == null) {
            this.ranges = new int[2];
            this.ranges[0] = r1;
            this.ranges[1] = r2;
            setSorted(true);
            return;
        }
        int pos = this.ranges.length;
        if (this.ranges[pos - 1] + 1 == r1) {
            this.ranges[pos - 1] = r2;
            return;
        }
        int[] temp = new int[(pos + 2)];
        System.arraycopy(this.ranges, 0, temp, 0, pos);
        this.ranges = temp;
        if (this.ranges[pos - 1] >= r1) {
            setSorted(false);
        }
        int pos2 = pos + 1;
        this.ranges[pos] = r1;
        this.ranges[pos2] = r2;
        if (!this.sorted) {
            sortRanges();
        }
        pos = pos2;
    }

    private final boolean isSorted() {
        return this.sorted;
    }

    private final void setSorted(boolean sort) {
        this.sorted = sort;
        if (!sort) {
            this.compacted = false;
        }
    }

    private final boolean isCompacted() {
        return this.compacted;
    }

    private final void setCompacted() {
        this.compacted = true;
    }

    protected void sortRanges() {
        if (!isSorted() && this.ranges != null) {
            for (int i = this.ranges.length - 4; i >= 0; i -= 2) {
                int j = 0;
                while (j <= i) {
                    if (this.ranges[j] > this.ranges[j + 2] || (this.ranges[j] == this.ranges[j + 2] && this.ranges[j + 1] > this.ranges[j + 3])) {
                        int tmp = this.ranges[j + 2];
                        this.ranges[j + 2] = this.ranges[j];
                        this.ranges[j] = tmp;
                        tmp = this.ranges[j + 3];
                        this.ranges[j + 3] = this.ranges[j + 1];
                        this.ranges[j + 1] = tmp;
                    }
                    j += 2;
                }
            }
            setSorted(true);
        }
    }

    protected void compactRanges() {
        if (this.ranges != null && this.ranges.length > 2 && !isCompacted()) {
            int base = 0;
            int target = 0;
            while (target < this.ranges.length) {
                if (base != target) {
                    int target2 = target + 1;
                    this.ranges[base] = this.ranges[target];
                    target = target2 + 1;
                    this.ranges[base + 1] = this.ranges[target2];
                } else {
                    target += 2;
                }
                int baseend = this.ranges[base + 1];
                while (target < this.ranges.length && baseend + 1 >= this.ranges[target]) {
                    if (baseend + 1 == this.ranges[target]) {
                        if (null != null) {
                            System.err.println("Token#compactRanges(): Compaction: [" + this.ranges[base] + ", " + this.ranges[base + 1] + "], [" + this.ranges[target] + ", " + this.ranges[target + 1] + "] -> [" + this.ranges[base] + ", " + this.ranges[target + 1] + "]");
                        }
                        this.ranges[base + 1] = this.ranges[target + 1];
                        baseend = this.ranges[base + 1];
                        target += 2;
                    } else if (baseend >= this.ranges[target + 1]) {
                        if (null != null) {
                            System.err.println("Token#compactRanges(): Compaction: [" + this.ranges[base] + ", " + this.ranges[base + 1] + "], [" + this.ranges[target] + ", " + this.ranges[target + 1] + "] -> [" + this.ranges[base] + ", " + this.ranges[base + 1] + "]");
                        }
                        target += 2;
                    } else if (baseend < this.ranges[target + 1]) {
                        if (null != null) {
                            System.err.println("Token#compactRanges(): Compaction: [" + this.ranges[base] + ", " + this.ranges[base + 1] + "], [" + this.ranges[target] + ", " + this.ranges[target + 1] + "] -> [" + this.ranges[base] + ", " + this.ranges[target + 1] + "]");
                        }
                        this.ranges[base + 1] = this.ranges[target + 1];
                        baseend = this.ranges[base + 1];
                        target += 2;
                    } else {
                        throw new RuntimeException("Token#compactRanges(): Internel Error: [" + this.ranges[base] + "," + this.ranges[base + 1] + "] [" + this.ranges[target] + "," + this.ranges[target + 1] + "]");
                    }
                }
                base += 2;
            }
            if (base != this.ranges.length) {
                int[] result = new int[base];
                System.arraycopy(this.ranges, 0, result, 0, base);
                this.ranges = result;
            }
            setCompacted();
        }
    }

    protected void mergeRanges(Token token) {
        RangeToken tok = (RangeToken) token;
        sortRanges();
        tok.sortRanges();
        if (tok.ranges != null) {
            this.icaseCache = null;
            setSorted(true);
            if (this.ranges == null) {
                this.ranges = new int[tok.ranges.length];
                System.arraycopy(tok.ranges, 0, this.ranges, 0, tok.ranges.length);
                return;
            }
            int[] result = new int[(this.ranges.length + tok.ranges.length)];
            int i = 0;
            int j = 0;
            int k = 0;
            while (true) {
                if (i >= this.ranges.length && j >= tok.ranges.length) {
                    this.ranges = result;
                    return;
                } else if (i >= this.ranges.length) {
                    r5 = k + 1;
                    j = j + 1;
                    result[k] = tok.ranges[j];
                    k = r5 + 1;
                    j = j + 1;
                    result[r5] = tok.ranges[j];
                } else if (j >= tok.ranges.length) {
                    r5 = k + 1;
                    i = i + 1;
                    result[k] = this.ranges[i];
                    k = r5 + 1;
                    i = i + 1;
                    result[r5] = this.ranges[i];
                } else if (tok.ranges[j] < this.ranges[i] || (tok.ranges[j] == this.ranges[i] && tok.ranges[j + 1] < this.ranges[i + 1])) {
                    r5 = k + 1;
                    j = j + 1;
                    result[k] = tok.ranges[j];
                    k = r5 + 1;
                    j = j + 1;
                    result[r5] = tok.ranges[j];
                } else {
                    r5 = k + 1;
                    i = i + 1;
                    result[k] = this.ranges[i];
                    k = r5 + 1;
                    i = i + 1;
                    result[r5] = this.ranges[i];
                }
            }
        }
    }

    protected void subtractRanges(Token token) {
        if (token.type == 5) {
            intersectRanges(token);
            return;
        }
        RangeToken tok = (RangeToken) token;
        if (tok.ranges != null && this.ranges != null) {
            int i;
            int src;
            this.icaseCache = null;
            sortRanges();
            compactRanges();
            tok.sortRanges();
            tok.compactRanges();
            int[] result = new int[(this.ranges.length + tok.ranges.length)];
            int wp = 0;
            int src2 = 0;
            int sub = 0;
            while (src2 < this.ranges.length && sub < tok.ranges.length) {
                int srcbegin = this.ranges[src2];
                int srcend = this.ranges[src2 + 1];
                int subbegin = tok.ranges[sub];
                int subend = tok.ranges[sub + 1];
                if (srcend < subbegin) {
                    i = wp + 1;
                    src = src2 + 1;
                    result[wp] = this.ranges[src2];
                    wp = i + 1;
                    src2 = src + 1;
                    result[i] = this.ranges[src];
                } else if (srcend < subbegin || srcbegin > subend) {
                    if (subend < srcbegin) {
                        sub += 2;
                    } else {
                        throw new RuntimeException("Token#subtractRanges(): Internal Error: [" + this.ranges[src2] + "," + this.ranges[src2 + 1] + "] - [" + tok.ranges[sub] + "," + tok.ranges[sub + 1] + "]");
                    }
                } else if (subbegin <= srcbegin && srcend <= subend) {
                    src2 += 2;
                } else if (subbegin <= srcbegin) {
                    this.ranges[src2] = subend + 1;
                    sub += 2;
                } else if (srcend <= subend) {
                    i = wp + 1;
                    result[wp] = srcbegin;
                    wp = i + 1;
                    result[i] = subbegin - 1;
                    src2 += 2;
                } else {
                    i = wp + 1;
                    result[wp] = srcbegin;
                    wp = i + 1;
                    result[i] = subbegin - 1;
                    this.ranges[src2] = subend + 1;
                    sub += 2;
                }
            }
            while (src2 < this.ranges.length) {
                i = wp + 1;
                src = src2 + 1;
                result[wp] = this.ranges[src2];
                wp = i + 1;
                src2 = src + 1;
                result[i] = this.ranges[src];
            }
            this.ranges = new int[wp];
            System.arraycopy(result, 0, this.ranges, 0, wp);
        }
    }

    protected void intersectRanges(Token token) {
        RangeToken tok = (RangeToken) token;
        if (tok.ranges != null && this.ranges != null) {
            int wp;
            this.icaseCache = null;
            sortRanges();
            compactRanges();
            tok.sortRanges();
            tok.compactRanges();
            int[] result = new int[(this.ranges.length + tok.ranges.length)];
            int wp2 = 0;
            int src1 = 0;
            int src2 = 0;
            while (src1 < this.ranges.length && src2 < tok.ranges.length) {
                int src1begin = this.ranges[src1];
                int src1end = this.ranges[src1 + 1];
                int src2begin = tok.ranges[src2];
                int src2end = tok.ranges[src2 + 1];
                if (src1end < src2begin) {
                    src1 += 2;
                } else if (src1end < src2begin || src1begin > src2end) {
                    if (src2end < src1begin) {
                        src2 += 2;
                    } else {
                        throw new RuntimeException("Token#intersectRanges(): Internal Error: [" + this.ranges[src1] + "," + this.ranges[src1 + 1] + "] & [" + tok.ranges[src2] + "," + tok.ranges[src2 + 1] + "]");
                    }
                } else if (src2begin <= src1begin && src1end <= src2end) {
                    wp = wp2 + 1;
                    result[wp2] = src1begin;
                    wp2 = wp + 1;
                    result[wp] = src1end;
                    src1 += 2;
                } else if (src2begin <= src1begin) {
                    wp = wp2 + 1;
                    result[wp2] = src1begin;
                    wp2 = wp + 1;
                    result[wp] = src2end;
                    this.ranges[src1] = src2end + 1;
                    src2 += 2;
                } else if (src1end <= src2end) {
                    wp = wp2 + 1;
                    result[wp2] = src2begin;
                    wp2 = wp + 1;
                    result[wp] = src1end;
                    src1 += 2;
                } else {
                    wp = wp2 + 1;
                    result[wp2] = src2begin;
                    wp2 = wp + 1;
                    result[wp] = src2end;
                    this.ranges[src1] = src2end + 1;
                }
            }
            while (src1 < this.ranges.length) {
                wp = wp2 + 1;
                int src12 = src1 + 1;
                result[wp2] = this.ranges[src1];
                wp2 = wp + 1;
                src1 = src12 + 1;
                result[wp] = this.ranges[src12];
            }
            this.ranges = new int[wp2];
            System.arraycopy(result, 0, this.ranges, 0, wp2);
        }
    }

    static Token complementRanges(Token token) {
        if (token.type == 4 || token.type == 5) {
            int i;
            RangeToken tok = (RangeToken) token;
            tok.sortRanges();
            tok.compactRanges();
            int len = tok.ranges.length + 2;
            if (tok.ranges[0] == 0) {
                len -= 2;
            }
            int last = tok.ranges[tok.ranges.length - 1];
            if (last == 1114111) {
                len -= 2;
            }
            RangeToken ret = Token.createRange();
            ret.ranges = new int[len];
            int i2 = 0;
            if (tok.ranges[0] > 0) {
                i = 0 + 1;
                ret.ranges[0] = 0;
                i2 = i + 1;
                ret.ranges[i] = tok.ranges[0] - 1;
            }
            for (int i3 = 1; i3 < tok.ranges.length - 2; i3 += 2) {
                i = i2 + 1;
                ret.ranges[i2] = tok.ranges[i3] + 1;
                i2 = i + 1;
                ret.ranges[i] = tok.ranges[i3 + 1] - 1;
            }
            if (last != 1114111) {
                i = i2 + 1;
                ret.ranges[i2] = last + 1;
                ret.ranges[i] = 1114111;
                i2 = i;
            }
            ret.setCompacted();
            return ret;
        }
        throw new IllegalArgumentException("Token#complementRanges(): must be RANGE: " + token.type);
    }

    synchronized RangeToken getCaseInsensitiveToken() {
        RangeToken rangeToken;
        if (this.icaseCache != null) {
            rangeToken = this.icaseCache;
        } else {
            int i;
            int ch;
            char uch;
            RangeToken uppers = this.type == 4 ? Token.createRange() : Token.createNRange();
            for (i = 0; i < this.ranges.length; i += 2) {
                for (ch = this.ranges[i]; ch <= this.ranges[i + 1]; ch++) {
                    if (ch > SupportMenu.USER_MASK) {
                        uppers.addRange(ch, ch);
                    } else {
                        uch = Character.toUpperCase((char) ch);
                        uppers.addRange(uch, uch);
                    }
                }
            }
            rangeToken = this.type == 4 ? Token.createRange() : Token.createNRange();
            for (i = 0; i < uppers.ranges.length; i += 2) {
                for (ch = uppers.ranges[i]; ch <= uppers.ranges[i + 1]; ch++) {
                    if (ch > SupportMenu.USER_MASK) {
                        rangeToken.addRange(ch, ch);
                    } else {
                        uch = Character.toLowerCase((char) ch);
                        rangeToken.addRange(uch, uch);
                    }
                }
            }
            rangeToken.mergeRanges(uppers);
            rangeToken.mergeRanges(this);
            rangeToken.compactRanges();
            this.icaseCache = rangeToken;
        }
        return rangeToken;
    }

    void dumpRanges() {
        System.err.print("RANGE: ");
        if (this.ranges == null) {
            System.err.println(" NULL");
            return;
        }
        for (int i = 0; i < this.ranges.length; i += 2) {
            System.err.print("[" + this.ranges[i] + "," + this.ranges[i + 1] + "] ");
        }
        System.err.println("");
    }

    boolean match(int ch) {
        if (this.map == null) {
            createMap();
        }
        int i;
        if (this.type == 4) {
            if (ch >= 256) {
                i = this.nonMapIndex;
                while (i < this.ranges.length) {
                    if (this.ranges[i] <= ch && ch <= this.ranges[i + 1]) {
                        return true;
                    }
                    i += 2;
                }
                return false;
            } else if ((this.map[ch / 32] & (1 << (ch & 31))) != 0) {
                return true;
            } else {
                return false;
            }
        } else if (ch < 256) {
            return (this.map[ch / 32] & (1 << (ch & 31))) == 0;
        } else {
            i = this.nonMapIndex;
            while (i < this.ranges.length) {
                if (this.ranges[i] <= ch && ch <= this.ranges[i + 1]) {
                    return false;
                }
                i += 2;
            }
            return true;
        }
    }

    private void createMap() {
        int i;
        int[] map = new int[8];
        int nonMapIndex = this.ranges.length;
        for (i = 0; i < 8; i++) {
            map[i] = 0;
        }
        for (i = 0; i < this.ranges.length; i += 2) {
            int s = this.ranges[i];
            int e = this.ranges[i + 1];
            if (s >= 256) {
                nonMapIndex = i;
                break;
            }
            int j = s;
            while (j <= e && j < 256) {
                int i2 = j / 32;
                map[i2] = map[i2] | (1 << (j & 31));
                j++;
            }
            if (e >= 256) {
                nonMapIndex = i;
                break;
            }
        }
        this.map = map;
        this.nonMapIndex = nonMapIndex;
    }

    public String toString(int options) {
        StringBuffer sb;
        int i;
        if (this.type == 4) {
            if (this == Token.token_dot) {
                return ".";
            }
            if (this == Token.token_0to9) {
                return "\\d";
            }
            if (this == Token.token_wordchars) {
                return "\\w";
            }
            if (this == Token.token_spaces) {
                return "\\s";
            }
            sb = new StringBuffer();
            sb.append('[');
            i = 0;
            while (i < this.ranges.length) {
                if ((options & 1024) != 0 && i > 0) {
                    sb.append(',');
                }
                if (this.ranges[i] == this.ranges[i + 1]) {
                    sb.append(escapeCharInCharClass(this.ranges[i]));
                } else {
                    sb.append(escapeCharInCharClass(this.ranges[i]));
                    sb.append('-');
                    sb.append(escapeCharInCharClass(this.ranges[i + 1]));
                }
                i += 2;
            }
            sb.append(']');
            return sb.toString();
        } else if (this == Token.token_not_0to9) {
            return "\\D";
        } else {
            if (this == Token.token_not_wordchars) {
                return "\\W";
            }
            if (this == Token.token_not_spaces) {
                return "\\S";
            }
            sb = new StringBuffer();
            sb.append("[^");
            i = 0;
            while (i < this.ranges.length) {
                if ((options & 1024) != 0 && i > 0) {
                    sb.append(',');
                }
                if (this.ranges[i] == this.ranges[i + 1]) {
                    sb.append(escapeCharInCharClass(this.ranges[i]));
                } else {
                    sb.append(escapeCharInCharClass(this.ranges[i]));
                    sb.append('-');
                    sb.append(escapeCharInCharClass(this.ranges[i + 1]));
                }
                i += 2;
            }
            sb.append(']');
            return sb.toString();
        }
    }

    private static String escapeCharInCharClass(int ch) {
        switch (ch) {
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
            case 44:
            case 45:
            case 91:
            case 92:
            case 93:
            case 94:
                return "\\" + ((char) ch);
            default:
                String pre;
                if (ch < 32) {
                    pre = new StringBuilder(SchemaSymbols.ATTVAL_FALSE_0).append(Integer.toHexString(ch)).toString();
                    return "\\x" + pre.substring(pre.length() - 2, pre.length());
                } else if (ch < 65536) {
                    return ((char) ch);
                } else {
                    pre = new StringBuilder(SchemaSymbols.ATTVAL_FALSE_0).append(Integer.toHexString(ch)).toString();
                    return "\\v" + pre.substring(pre.length() - 6, pre.length());
                }
        }
    }
}
