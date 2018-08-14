package mf.org.apache.xerces.impl.xpath.regex;

import android.support.v4.internal.view.SupportMenu;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import java.io.Serializable;
import java.text.CharacterIterator;
import java.util.Locale;

public class RegularExpression implements Serializable {
    static final int CARRIAGE_RETURN = 13;
    static final boolean DEBUG = false;
    static final int EXTENDED_COMMENT = 16;
    static final int IGNORE_CASE = 2;
    static final int LINE_FEED = 10;
    static final int LINE_SEPARATOR = 8232;
    static final int MULTIPLE_LINES = 8;
    static final int PARAGRAPH_SEPARATOR = 8233;
    static final int PROHIBIT_FIXED_STRING_OPTIMIZATION = 256;
    static final int PROHIBIT_HEAD_CHARACTER_OPTIMIZATION = 128;
    static final int SINGLE_LINE = 4;
    static final int SPECIAL_COMMA = 1024;
    static final int UNICODE_WORD_BOUNDARY = 64;
    static final int USE_UNICODE_CATEGORY = 32;
    private static final int WT_IGNORE = 0;
    private static final int WT_LETTER = 1;
    private static final int WT_OTHER = 2;
    static final int XMLSCHEMA_MODE = 512;
    private static final long serialVersionUID = 6242499334195006401L;
    transient Context context;
    transient RangeToken firstChar;
    transient String fixedString;
    transient boolean fixedStringOnly;
    transient int fixedStringOptions;
    transient BMPattern fixedStringTable;
    boolean hasBackReferences;
    transient int minlength;
    int nofparen;
    transient int numberOfClosures;
    transient Op operations;
    int options;
    String regex;
    Token tokentree;

    static abstract class ExpressionTarget {
        abstract char charAt(int i);

        abstract boolean regionMatches(boolean z, int i, int i2, int i3, int i4);

        abstract boolean regionMatches(boolean z, int i, int i2, String str, int i3);

        ExpressionTarget() {
        }
    }

    static final class CharArrayTarget extends ExpressionTarget {
        char[] target;

        CharArrayTarget(char[] target) {
            this.target = target;
        }

        final void resetTarget(char[] target) {
            this.target = target;
        }

        char charAt(int index) {
            return this.target[index];
        }

        final boolean regionMatches(boolean ignoreCase, int offset, int limit, String part, int partlen) {
            if (offset < 0 || limit - offset < partlen) {
                return false;
            }
            if (ignoreCase) {
                return regionMatchesIgnoreCase(offset, limit, part, partlen);
            }
            return regionMatches(offset, limit, part, partlen);
        }

        private final boolean regionMatches(int offset, int limit, String part, int partlen) {
            int i = 0;
            int partlen2 = partlen;
            int offset2 = offset;
            while (true) {
                partlen = partlen2 - 1;
                if (partlen2 <= 0) {
                    int i2 = i;
                    offset = offset2;
                    return true;
                }
                offset = offset2 + 1;
                i2 = i + 1;
                if (this.target[offset2] != part.charAt(i)) {
                    return false;
                }
                i = i2;
                partlen2 = partlen;
                offset2 = offset;
            }
        }

        private final boolean regionMatchesIgnoreCase(int offset, int limit, String part, int partlen) {
            int i = 0;
            int partlen2 = partlen;
            int offset2 = offset;
            while (true) {
                partlen = partlen2 - 1;
                if (partlen2 <= 0) {
                    int i2 = i;
                    offset = offset2;
                    return true;
                }
                offset = offset2 + 1;
                char ch1 = this.target[offset2];
                i2 = i + 1;
                char ch2 = part.charAt(i);
                if (ch1 == ch2) {
                    i = i2;
                    partlen2 = partlen;
                    offset2 = offset;
                } else {
                    char uch1 = Character.toUpperCase(ch1);
                    char uch2 = Character.toUpperCase(ch2);
                    if (uch1 == uch2) {
                        i = i2;
                        partlen2 = partlen;
                        offset2 = offset;
                    } else if (Character.toLowerCase(uch1) != Character.toLowerCase(uch2)) {
                        return false;
                    } else {
                        i = i2;
                        partlen2 = partlen;
                        offset2 = offset;
                    }
                }
            }
        }

        final boolean regionMatches(boolean ignoreCase, int offset, int limit, int offset2, int partlen) {
            if (offset < 0 || limit - offset < partlen) {
                return false;
            }
            if (ignoreCase) {
                return regionMatchesIgnoreCase(offset, limit, offset2, partlen);
            }
            return regionMatches(offset, limit, offset2, partlen);
        }

        private final boolean regionMatches(int offset, int limit, int offset2, int partlen) {
            int i = offset2;
            int partlen2 = partlen;
            int offset3 = offset;
            while (true) {
                partlen = partlen2 - 1;
                if (partlen2 <= 0) {
                    int i2 = i;
                    offset = offset3;
                    return true;
                }
                offset = offset3 + 1;
                i2 = i + 1;
                if (this.target[offset3] != this.target[i]) {
                    return false;
                }
                i = i2;
                partlen2 = partlen;
                offset3 = offset;
            }
        }

        private final boolean regionMatchesIgnoreCase(int offset, int limit, int offset2, int partlen) {
            int i = offset2;
            int partlen2 = partlen;
            int offset3 = offset;
            while (true) {
                partlen = partlen2 - 1;
                if (partlen2 <= 0) {
                    int i2 = i;
                    offset = offset3;
                    return true;
                }
                offset = offset3 + 1;
                char ch1 = this.target[offset3];
                i2 = i + 1;
                char ch2 = this.target[i];
                if (ch1 == ch2) {
                    i = i2;
                    partlen2 = partlen;
                    offset3 = offset;
                } else {
                    char uch1 = Character.toUpperCase(ch1);
                    char uch2 = Character.toUpperCase(ch2);
                    if (uch1 == uch2) {
                        i = i2;
                        partlen2 = partlen;
                        offset3 = offset;
                    } else if (Character.toLowerCase(uch1) != Character.toLowerCase(uch2)) {
                        return false;
                    } else {
                        i = i2;
                        partlen2 = partlen;
                        offset3 = offset;
                    }
                }
            }
        }
    }

    static final class CharacterIteratorTarget extends ExpressionTarget {
        CharacterIterator target;

        CharacterIteratorTarget(CharacterIterator target) {
            this.target = target;
        }

        final void resetTarget(CharacterIterator target) {
            this.target = target;
        }

        final char charAt(int index) {
            return this.target.setIndex(index);
        }

        final boolean regionMatches(boolean ignoreCase, int offset, int limit, String part, int partlen) {
            if (offset < 0 || limit - offset < partlen) {
                return false;
            }
            if (ignoreCase) {
                return regionMatchesIgnoreCase(offset, limit, part, partlen);
            }
            return regionMatches(offset, limit, part, partlen);
        }

        private final boolean regionMatches(int offset, int limit, String part, int partlen) {
            int i = 0;
            int partlen2 = partlen;
            int offset2 = offset;
            while (true) {
                partlen = partlen2 - 1;
                if (partlen2 <= 0) {
                    int i2 = i;
                    offset = offset2;
                    return true;
                }
                offset = offset2 + 1;
                i2 = i + 1;
                if (this.target.setIndex(offset2) != part.charAt(i)) {
                    return false;
                }
                i = i2;
                partlen2 = partlen;
                offset2 = offset;
            }
        }

        private final boolean regionMatchesIgnoreCase(int offset, int limit, String part, int partlen) {
            int i = 0;
            int partlen2 = partlen;
            int offset2 = offset;
            while (true) {
                partlen = partlen2 - 1;
                if (partlen2 <= 0) {
                    int i2 = i;
                    offset = offset2;
                    return true;
                }
                offset = offset2 + 1;
                char ch1 = this.target.setIndex(offset2);
                i2 = i + 1;
                char ch2 = part.charAt(i);
                if (ch1 == ch2) {
                    i = i2;
                    partlen2 = partlen;
                    offset2 = offset;
                } else {
                    char uch1 = Character.toUpperCase(ch1);
                    char uch2 = Character.toUpperCase(ch2);
                    if (uch1 == uch2) {
                        i = i2;
                        partlen2 = partlen;
                        offset2 = offset;
                    } else if (Character.toLowerCase(uch1) != Character.toLowerCase(uch2)) {
                        return false;
                    } else {
                        i = i2;
                        partlen2 = partlen;
                        offset2 = offset;
                    }
                }
            }
        }

        final boolean regionMatches(boolean ignoreCase, int offset, int limit, int offset2, int partlen) {
            if (offset < 0 || limit - offset < partlen) {
                return false;
            }
            if (ignoreCase) {
                return regionMatchesIgnoreCase(offset, limit, offset2, partlen);
            }
            return regionMatches(offset, limit, offset2, partlen);
        }

        private final boolean regionMatches(int offset, int limit, int offset2, int partlen) {
            int i = offset2;
            int partlen2 = partlen;
            int offset3 = offset;
            while (true) {
                partlen = partlen2 - 1;
                if (partlen2 <= 0) {
                    int i2 = i;
                    offset = offset3;
                    return true;
                }
                offset = offset3 + 1;
                i2 = i + 1;
                if (this.target.setIndex(offset3) != this.target.setIndex(i)) {
                    return false;
                }
                i = i2;
                partlen2 = partlen;
                offset3 = offset;
            }
        }

        private final boolean regionMatchesIgnoreCase(int offset, int limit, int offset2, int partlen) {
            int i = offset2;
            int partlen2 = partlen;
            int offset3 = offset;
            while (true) {
                partlen = partlen2 - 1;
                if (partlen2 <= 0) {
                    int i2 = i;
                    offset = offset3;
                    return true;
                }
                offset = offset3 + 1;
                char ch1 = this.target.setIndex(offset3);
                i2 = i + 1;
                char ch2 = this.target.setIndex(i);
                if (ch1 == ch2) {
                    i = i2;
                    partlen2 = partlen;
                    offset3 = offset;
                } else {
                    char uch1 = Character.toUpperCase(ch1);
                    char uch2 = Character.toUpperCase(ch2);
                    if (uch1 == uch2) {
                        i = i2;
                        partlen2 = partlen;
                        offset3 = offset;
                    } else if (Character.toLowerCase(uch1) != Character.toLowerCase(uch2)) {
                        return false;
                    } else {
                        i = i2;
                        partlen2 = partlen;
                        offset3 = offset;
                    }
                }
            }
        }
    }

    static final class ClosureContext {
        int currentIndex = 0;
        int[] offsets = new int[4];

        ClosureContext() {
        }

        boolean contains(int offset) {
            for (int i = 0; i < this.currentIndex; i++) {
                if (this.offsets[i] == offset) {
                    return true;
                }
            }
            return false;
        }

        void reset() {
            this.currentIndex = 0;
        }

        void addOffset(int offset) {
            if (this.currentIndex == this.offsets.length) {
                this.offsets = expandOffsets();
            }
            int[] iArr = this.offsets;
            int i = this.currentIndex;
            this.currentIndex = i + 1;
            iArr[i] = offset;
        }

        private int[] expandOffsets() {
            int[] newOffsets = new int[(this.offsets.length << 1)];
            System.arraycopy(this.offsets, 0, newOffsets, 0, this.currentIndex);
            return newOffsets;
        }
    }

    static final class Context {
        private CharArrayTarget charArrayTarget;
        private CharacterIteratorTarget characterIteratorTarget;
        ClosureContext[] closureContexts;
        boolean inuse = false;
        int length;
        int limit;
        Match match;
        int start;
        private StringTarget stringTarget;
        ExpressionTarget target;

        Context() {
        }

        private void resetCommon(int nofclosures) {
            this.length = this.limit - this.start;
            setInUse(true);
            this.match = null;
            if (this.closureContexts == null || this.closureContexts.length != nofclosures) {
                this.closureContexts = new ClosureContext[nofclosures];
            }
            for (int i = 0; i < nofclosures; i++) {
                if (this.closureContexts[i] == null) {
                    this.closureContexts[i] = new ClosureContext();
                } else {
                    this.closureContexts[i].reset();
                }
            }
        }

        void reset(CharacterIterator target, int start, int limit, int nofclosures) {
            if (this.characterIteratorTarget == null) {
                this.characterIteratorTarget = new CharacterIteratorTarget(target);
            } else {
                this.characterIteratorTarget.resetTarget(target);
            }
            this.target = this.characterIteratorTarget;
            this.start = start;
            this.limit = limit;
            resetCommon(nofclosures);
        }

        void reset(String target, int start, int limit, int nofclosures) {
            if (this.stringTarget == null) {
                this.stringTarget = new StringTarget(target);
            } else {
                this.stringTarget.resetTarget(target);
            }
            this.target = this.stringTarget;
            this.start = start;
            this.limit = limit;
            resetCommon(nofclosures);
        }

        void reset(char[] target, int start, int limit, int nofclosures) {
            if (this.charArrayTarget == null) {
                this.charArrayTarget = new CharArrayTarget(target);
            } else {
                this.charArrayTarget.resetTarget(target);
            }
            this.target = this.charArrayTarget;
            this.start = start;
            this.limit = limit;
            resetCommon(nofclosures);
        }

        synchronized void setInUse(boolean inUse) {
            this.inuse = inUse;
        }
    }

    static final class StringTarget extends ExpressionTarget {
        private String target;

        StringTarget(String target) {
            this.target = target;
        }

        final void resetTarget(String target) {
            this.target = target;
        }

        final char charAt(int index) {
            return this.target.charAt(index);
        }

        final boolean regionMatches(boolean ignoreCase, int offset, int limit, String part, int partlen) {
            if (limit - offset < partlen) {
                return false;
            }
            return ignoreCase ? this.target.regionMatches(true, offset, part, 0, partlen) : this.target.regionMatches(offset, part, 0, partlen);
        }

        final boolean regionMatches(boolean ignoreCase, int offset, int limit, int offset2, int partlen) {
            if (limit - offset < partlen) {
                return false;
            }
            if (!ignoreCase) {
                return this.target.regionMatches(offset, this.target, offset2, partlen);
            }
            return this.target.regionMatches(true, offset, this.target, offset2, partlen);
        }
    }

    private synchronized void compile(Token tok) {
        if (this.operations == null) {
            this.numberOfClosures = 0;
            this.operations = compile(tok, null, false);
        }
    }

    private Op compile(Token tok, Op next, boolean reverse) {
        Op ret;
        int i;
        switch (tok.type) {
            case 0:
                ret = Op.createChar(tok.getChar());
                ret.next = next;
                return ret;
            case 1:
                ret = next;
                if (reverse) {
                    for (i = 0; i < tok.size(); i++) {
                        ret = compile(tok.getChild(i), ret, true);
                    }
                    return ret;
                }
                for (i = tok.size() - 1; i >= 0; i--) {
                    ret = compile(tok.getChild(i), ret, false);
                }
                return ret;
            case 2:
                Op uni = Op.createUnion(tok.size());
                for (i = 0; i < tok.size(); i++) {
                    uni.addElement(compile(tok.getChild(i), next, reverse));
                }
                return uni;
            case 3:
            case 9:
                Token child = tok.getChild(0);
                int min = tok.getMin();
                int max = tok.getMax();
                if (min < 0 || min != max) {
                    if (min > 0 && max > 0) {
                        max -= min;
                    }
                    if (max > 0) {
                        ret = next;
                        for (i = 0; i < max; i++) {
                            Op q = Op.createQuestion(tok.type == 9);
                            q.next = next;
                            q.setChild(compile(child, ret, reverse));
                            ret = q;
                        }
                    } else {
                        Op op;
                        if (tok.type == 9) {
                            op = Op.createNonGreedyClosure();
                        } else {
                            int i2 = this.numberOfClosures;
                            this.numberOfClosures = i2 + 1;
                            op = Op.createClosure(i2);
                        }
                        op.next = next;
                        op.setChild(compile(child, op, reverse));
                        ret = op;
                    }
                    if (min <= 0) {
                        return ret;
                    }
                    for (i = 0; i < min; i++) {
                        ret = compile(child, ret, reverse);
                    }
                    return ret;
                }
                ret = next;
                for (i = 0; i < min; i++) {
                    ret = compile(child, ret, reverse);
                }
                return ret;
            case 4:
            case 5:
                ret = Op.createRange(tok);
                ret.next = next;
                return ret;
            case 6:
                if (tok.getParenNumber() == 0) {
                    return compile(tok.getChild(0), next, reverse);
                } else if (reverse) {
                    return Op.createCapture(-tok.getParenNumber(), compile(tok.getChild(0), Op.createCapture(tok.getParenNumber(), next), reverse));
                } else {
                    return Op.createCapture(tok.getParenNumber(), compile(tok.getChild(0), Op.createCapture(-tok.getParenNumber(), next), reverse));
                }
            case 7:
                return next;
            case 8:
                ret = Op.createAnchor(tok.getChar());
                ret.next = next;
                return ret;
            case 10:
                ret = Op.createString(tok.getString());
                ret.next = next;
                return ret;
            case 11:
                ret = Op.createDot();
                ret.next = next;
                return ret;
            case 12:
                ret = Op.createBackReference(tok.getReferenceNumber());
                ret.next = next;
                return ret;
            case 20:
                return Op.createLook(20, next, compile(tok.getChild(0), null, false));
            case 21:
                return Op.createLook(21, next, compile(tok.getChild(0), null, false));
            case 22:
                return Op.createLook(22, next, compile(tok.getChild(0), null, true));
            case 23:
                return Op.createLook(23, next, compile(tok.getChild(0), null, true));
            case 24:
                return Op.createIndependent(next, compile(tok.getChild(0), null, reverse));
            case 25:
                return Op.createModifier(next, compile(tok.getChild(0), null, reverse), ((ModifierToken) tok).getOptions(), ((ModifierToken) tok).getOptionsMask());
            case 26:
                Op no;
                ConditionToken ctok = (ConditionToken) tok;
                int ref = ctok.refNumber;
                Op condition = ctok.condition == null ? null : compile(ctok.condition, null, reverse);
                Op yes = compile(ctok.yes, next, reverse);
                if (ctok.no == null) {
                    no = null;
                } else {
                    no = compile(ctok.no, next, reverse);
                }
                return Op.createCondition(next, ref, condition, yes, no);
            default:
                throw new RuntimeException("Unknown token type: " + tok.type);
        }
    }

    public boolean matches(char[] target) {
        return matches(target, 0, target.length, null);
    }

    public boolean matches(char[] target, int start, int end) {
        return matches(target, start, end, null);
    }

    public boolean matches(char[] target, Match match) {
        return matches(target, 0, target.length, match);
    }

    public boolean matches(char[] target, int start, int end, Match match) {
        Context con;
        synchronized (this) {
            if (this.operations == null) {
                prepare();
            }
            if (this.context == null) {
                this.context = new Context();
            }
        }
        synchronized (this.context) {
            con = this.context.inuse ? new Context() : this.context;
            con.reset(target, start, end, this.numberOfClosures);
        }
        if (match != null) {
            match.setNumberOfGroups(this.nofparen);
            match.setSource(target);
        } else if (this.hasBackReferences) {
            match = new Match();
            match.setNumberOfGroups(this.nofparen);
        }
        con.match = match;
        int matchEnd;
        if (isSet(this.options, 512)) {
            matchEnd = match(con, this.operations, con.start, 1, this.options);
            if (matchEnd != con.limit) {
                return false;
            }
            if (con.match != null) {
                con.match.setBeginning(0, con.start);
                con.match.setEnd(0, matchEnd);
            }
            con.setInUse(false);
            return true;
        } else if (this.fixedStringOnly) {
            int o = this.fixedStringTable.matches(target, con.start, con.limit);
            if (o >= 0) {
                if (con.match != null) {
                    con.match.setBeginning(0, o);
                    con.match.setEnd(0, this.fixedString.length() + o);
                }
                con.setInUse(false);
                return true;
            }
            con.setInUse(false);
            return false;
        } else {
            int matchStart;
            if (this.fixedString != null) {
                if (this.fixedStringTable.matches(target, con.start, con.limit) < 0) {
                    con.setInUse(false);
                    return false;
                }
            }
            int limit = con.limit - this.minlength;
            matchEnd = -1;
            if (this.operations == null || this.operations.type != 7 || this.operations.getChild().type != 0) {
                if (this.firstChar == null) {
                    matchStart = con.start;
                    while (matchStart <= limit) {
                        matchEnd = match(con, this.operations, matchStart, 1, this.options);
                        if (matchEnd >= 0) {
                            break;
                        }
                        matchStart++;
                    }
                } else {
                    RangeToken range = this.firstChar;
                    matchStart = con.start;
                    while (matchStart <= limit) {
                        int ch = target[matchStart];
                        if (REUtil.isHighSurrogate(ch) && matchStart + 1 < con.limit) {
                            ch = REUtil.composeFromSurrogates(ch, target[matchStart + 1]);
                        }
                        if (range.match(ch)) {
                            matchEnd = match(con, this.operations, matchStart, 1, this.options);
                            if (matchEnd >= 0) {
                                break;
                            }
                        }
                        matchStart++;
                    }
                }
            } else if (isSet(this.options, 4)) {
                int matchStart2 = con.start;
                matchEnd = match(con, this.operations, con.start, 1, this.options);
                matchStart = matchStart2;
            } else {
                boolean previousIsEOL = true;
                matchStart = con.start;
                while (matchStart <= limit) {
                    if (isEOLChar(target[matchStart])) {
                        previousIsEOL = true;
                    } else {
                        if (previousIsEOL) {
                            matchEnd = match(con, this.operations, matchStart, 1, this.options);
                            if (matchEnd >= 0) {
                                break;
                            }
                        }
                        previousIsEOL = false;
                    }
                    matchStart++;
                }
            }
            if (matchEnd >= 0) {
                if (con.match != null) {
                    con.match.setBeginning(0, matchStart);
                    con.match.setEnd(0, matchEnd);
                }
                con.setInUse(false);
                return true;
            }
            con.setInUse(false);
            return false;
        }
    }

    public boolean matches(String target) {
        return matches(target, 0, target.length(), null);
    }

    public boolean matches(String target, int start, int end) {
        return matches(target, start, end, null);
    }

    public boolean matches(String target, Match match) {
        return matches(target, 0, target.length(), match);
    }

    public boolean matches(String target, int start, int end, Match match) {
        Context con;
        synchronized (this) {
            if (this.operations == null) {
                prepare();
            }
            if (this.context == null) {
                this.context = new Context();
            }
        }
        synchronized (this.context) {
            con = this.context.inuse ? new Context() : this.context;
            con.reset(target, start, end, this.numberOfClosures);
        }
        if (match != null) {
            match.setNumberOfGroups(this.nofparen);
            match.setSource(target);
        } else if (this.hasBackReferences) {
            match = new Match();
            match.setNumberOfGroups(this.nofparen);
        }
        con.match = match;
        int matchEnd;
        if (isSet(this.options, 512)) {
            matchEnd = match(con, this.operations, con.start, 1, this.options);
            if (matchEnd != con.limit) {
                return false;
            }
            if (con.match != null) {
                con.match.setBeginning(0, con.start);
                con.match.setEnd(0, matchEnd);
            }
            con.setInUse(false);
            return true;
        } else if (this.fixedStringOnly) {
            int o = this.fixedStringTable.matches(target, con.start, con.limit);
            if (o >= 0) {
                if (con.match != null) {
                    con.match.setBeginning(0, o);
                    con.match.setEnd(0, this.fixedString.length() + o);
                }
                con.setInUse(false);
                return true;
            }
            con.setInUse(false);
            return false;
        } else {
            int matchStart;
            if (this.fixedString != null) {
                if (this.fixedStringTable.matches(target, con.start, con.limit) < 0) {
                    con.setInUse(false);
                    return false;
                }
            }
            int limit = con.limit - this.minlength;
            matchEnd = -1;
            if (this.operations == null || this.operations.type != 7 || this.operations.getChild().type != 0) {
                if (this.firstChar == null) {
                    matchStart = con.start;
                    while (matchStart <= limit) {
                        matchEnd = match(con, this.operations, matchStart, 1, this.options);
                        if (matchEnd >= 0) {
                            break;
                        }
                        matchStart++;
                    }
                } else {
                    RangeToken range = this.firstChar;
                    matchStart = con.start;
                    while (matchStart <= limit) {
                        int ch = target.charAt(matchStart);
                        if (REUtil.isHighSurrogate(ch) && matchStart + 1 < con.limit) {
                            ch = REUtil.composeFromSurrogates(ch, target.charAt(matchStart + 1));
                        }
                        if (range.match(ch)) {
                            matchEnd = match(con, this.operations, matchStart, 1, this.options);
                            if (matchEnd >= 0) {
                                break;
                            }
                        }
                        matchStart++;
                    }
                }
            } else if (isSet(this.options, 4)) {
                int matchStart2 = con.start;
                matchEnd = match(con, this.operations, con.start, 1, this.options);
                matchStart = matchStart2;
            } else {
                boolean previousIsEOL = true;
                matchStart = con.start;
                while (matchStart <= limit) {
                    if (isEOLChar(target.charAt(matchStart))) {
                        previousIsEOL = true;
                    } else {
                        if (previousIsEOL) {
                            matchEnd = match(con, this.operations, matchStart, 1, this.options);
                            if (matchEnd >= 0) {
                                break;
                            }
                        }
                        previousIsEOL = false;
                    }
                    matchStart++;
                }
            }
            if (matchEnd >= 0) {
                if (con.match != null) {
                    con.match.setBeginning(0, matchStart);
                    con.match.setEnd(0, matchEnd);
                }
                con.setInUse(false);
                return true;
            }
            con.setInUse(false);
            return false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int match(mf.org.apache.xerces.impl.xpath.regex.RegularExpression.Context r30, mf.org.apache.xerces.impl.xpath.regex.Op r31, int r32, int r33, int r34) {
        /*
        r29 = this;
        r0 = r30;
        r3 = r0.target;
        r22 = new java.util.Stack;
        r22.<init>();
        r17 = new mf.org.apache.xerces.util.IntStack;
        r17.<init>();
        r2 = 2;
        r0 = r34;
        r10 = isSet(r0, r2);
        r24 = -1;
        r25 = 0;
    L_0x0019:
        if (r31 == 0) goto L_0x002b;
    L_0x001b:
        r0 = r30;
        r2 = r0.limit;
        r0 = r32;
        if (r0 > r2) goto L_0x002b;
    L_0x0023:
        r0 = r30;
        r2 = r0.start;
        r0 = r32;
        if (r0 >= r2) goto L_0x0052;
    L_0x002b:
        if (r31 != 0) goto L_0x004f;
    L_0x002d:
        r2 = 512; // 0x200 float:7.175E-43 double:2.53E-321;
        r0 = r34;
        r2 = isSet(r0, r2);
        if (r2 == 0) goto L_0x004c;
    L_0x0037:
        r0 = r30;
        r2 = r0.limit;
        r0 = r32;
        if (r0 == r2) goto L_0x004c;
    L_0x003f:
        r24 = -1;
    L_0x0041:
        r25 = 1;
    L_0x0043:
        if (r25 == 0) goto L_0x0019;
    L_0x0045:
        r2 = r22.isEmpty();
        if (r2 == 0) goto L_0x040d;
    L_0x004b:
        return r24;
    L_0x004c:
        r24 = r32;
        goto L_0x0041;
    L_0x004f:
        r24 = -1;
        goto L_0x0041;
    L_0x0052:
        r24 = -1;
        r0 = r31;
        r2 = r0.type;
        switch(r2) {
            case 0: goto L_0x00a4;
            case 1: goto L_0x0075;
            case 2: goto L_0x005b;
            case 3: goto L_0x0118;
            case 4: goto L_0x0118;
            case 5: goto L_0x0170;
            case 6: goto L_0x0215;
            case 7: goto L_0x0250;
            case 8: goto L_0x0285;
            case 9: goto L_0x0271;
            case 10: goto L_0x0285;
            case 11: goto L_0x029b;
            case 12: goto L_0x005b;
            case 13: goto L_0x005b;
            case 14: goto L_0x005b;
            case 15: goto L_0x02c2;
            case 16: goto L_0x018c;
            case 17: goto L_0x005b;
            case 18: goto L_0x005b;
            case 19: goto L_0x005b;
            case 20: goto L_0x031e;
            case 21: goto L_0x031e;
            case 22: goto L_0x031e;
            case 23: goto L_0x031e;
            case 24: goto L_0x034e;
            case 25: goto L_0x0362;
            case 26: goto L_0x038f;
            default: goto L_0x005b;
        };
    L_0x005b:
        r2 = new java.lang.RuntimeException;
        r4 = new java.lang.StringBuilder;
        r5 = "Unknown operation type: ";
        r4.<init>(r5);
        r0 = r31;
        r5 = r0.type;
        r4 = r4.append(r5);
        r4 = r4.toString();
        r2.<init>(r4);
        throw r2;
    L_0x0075:
        if (r33 <= 0) goto L_0x0098;
    L_0x0077:
        r21 = r32;
    L_0x0079:
        r0 = r30;
        r2 = r0.limit;
        r0 = r21;
        if (r0 >= r2) goto L_0x0095;
    L_0x0081:
        if (r21 < 0) goto L_0x0095;
    L_0x0083:
        r2 = r31.getData();
        r0 = r21;
        r4 = r3.charAt(r0);
        r0 = r29;
        r2 = r0.matchChar(r2, r4, r10);
        if (r2 != 0) goto L_0x009b;
    L_0x0095:
        r25 = 1;
        goto L_0x0043;
    L_0x0098:
        r21 = r32 + -1;
        goto L_0x0079;
    L_0x009b:
        r32 = r32 + r33;
        r0 = r31;
        r0 = r0.next;
        r31 = r0;
        goto L_0x0043;
    L_0x00a4:
        if (r33 <= 0) goto L_0x00b5;
    L_0x00a6:
        r21 = r32;
    L_0x00a8:
        r0 = r30;
        r2 = r0.limit;
        r0 = r21;
        if (r0 >= r2) goto L_0x00b2;
    L_0x00b0:
        if (r21 >= 0) goto L_0x00b8;
    L_0x00b2:
        r25 = 1;
        goto L_0x0043;
    L_0x00b5:
        r21 = r32 + -1;
        goto L_0x00a8;
    L_0x00b8:
        r2 = 4;
        r0 = r34;
        r2 = isSet(r0, r2);
        if (r2 == 0) goto L_0x00e7;
    L_0x00c1:
        r0 = r21;
        r2 = r3.charAt(r0);
        r2 = mf.org.apache.xerces.impl.xpath.regex.REUtil.isHighSurrogate(r2);
        if (r2 == 0) goto L_0x00db;
    L_0x00cd:
        r2 = r21 + r33;
        if (r2 < 0) goto L_0x00db;
    L_0x00d1:
        r2 = r21 + r33;
        r0 = r30;
        r4 = r0.limit;
        if (r2 >= r4) goto L_0x00db;
    L_0x00d9:
        r21 = r21 + r33;
    L_0x00db:
        if (r33 <= 0) goto L_0x0115;
    L_0x00dd:
        r32 = r21 + 1;
    L_0x00df:
        r0 = r31;
        r0 = r0.next;
        r31 = r0;
        goto L_0x0043;
    L_0x00e7:
        r0 = r21;
        r15 = r3.charAt(r0);
        r2 = mf.org.apache.xerces.impl.xpath.regex.REUtil.isHighSurrogate(r15);
        if (r2 == 0) goto L_0x010b;
    L_0x00f3:
        r2 = r21 + r33;
        if (r2 < 0) goto L_0x010b;
    L_0x00f7:
        r2 = r21 + r33;
        r0 = r30;
        r4 = r0.limit;
        if (r2 >= r4) goto L_0x010b;
    L_0x00ff:
        r21 = r21 + r33;
        r0 = r21;
        r2 = r3.charAt(r0);
        r15 = mf.org.apache.xerces.impl.xpath.regex.REUtil.composeFromSurrogates(r15, r2);
    L_0x010b:
        r2 = isEOLChar(r15);
        if (r2 == 0) goto L_0x00db;
    L_0x0111:
        r25 = 1;
        goto L_0x0043;
    L_0x0115:
        r32 = r21;
        goto L_0x00df;
    L_0x0118:
        if (r33 <= 0) goto L_0x012a;
    L_0x011a:
        r21 = r32;
    L_0x011c:
        r0 = r30;
        r2 = r0.limit;
        r0 = r21;
        if (r0 >= r2) goto L_0x0126;
    L_0x0124:
        if (r21 >= 0) goto L_0x012d;
    L_0x0126:
        r25 = 1;
        goto L_0x0043;
    L_0x012a:
        r21 = r32 + -1;
        goto L_0x011c;
    L_0x012d:
        r0 = r32;
        r15 = r3.charAt(r0);
        r2 = mf.org.apache.xerces.impl.xpath.regex.REUtil.isHighSurrogate(r15);
        if (r2 == 0) goto L_0x0151;
    L_0x0139:
        r2 = r21 + r33;
        r0 = r30;
        r4 = r0.limit;
        if (r2 >= r4) goto L_0x0151;
    L_0x0141:
        r2 = r21 + r33;
        if (r2 < 0) goto L_0x0151;
    L_0x0145:
        r21 = r21 + r33;
        r0 = r21;
        r2 = r3.charAt(r0);
        r15 = mf.org.apache.xerces.impl.xpath.regex.REUtil.composeFromSurrogates(r15, r2);
    L_0x0151:
        r27 = r31.getToken();
        r0 = r27;
        r2 = r0.match(r15);
        if (r2 != 0) goto L_0x0161;
    L_0x015d:
        r25 = 1;
        goto L_0x0043;
    L_0x0161:
        if (r33 <= 0) goto L_0x016d;
    L_0x0163:
        r32 = r21 + 1;
    L_0x0165:
        r0 = r31;
        r0 = r0.next;
        r31 = r0;
        goto L_0x0043;
    L_0x016d:
        r32 = r21;
        goto L_0x0165;
    L_0x0170:
        r2 = r29;
        r4 = r31;
        r5 = r30;
        r6 = r32;
        r7 = r34;
        r2 = r2.matchAnchor(r3, r4, r5, r6, r7);
        if (r2 != 0) goto L_0x0184;
    L_0x0180:
        r25 = 1;
        goto L_0x0043;
    L_0x0184:
        r0 = r31;
        r0 = r0.next;
        r31 = r0;
        goto L_0x0043;
    L_0x018c:
        r23 = r31.getData();
        if (r23 <= 0) goto L_0x019a;
    L_0x0192:
        r0 = r29;
        r2 = r0.nofparen;
        r0 = r23;
        if (r0 < r2) goto L_0x01b2;
    L_0x019a:
        r2 = new java.lang.RuntimeException;
        r4 = new java.lang.StringBuilder;
        r5 = "Internal Error: Reference number must be more than zero: ";
        r4.<init>(r5);
        r0 = r23;
        r4 = r4.append(r0);
        r4 = r4.toString();
        r2.<init>(r4);
        throw r2;
    L_0x01b2:
        r0 = r30;
        r2 = r0.match;
        r0 = r23;
        r2 = r2.getBeginning(r0);
        if (r2 < 0) goto L_0x01ca;
    L_0x01be:
        r0 = r30;
        r2 = r0.match;
        r0 = r23;
        r2 = r2.getEnd(r0);
        if (r2 >= 0) goto L_0x01ce;
    L_0x01ca:
        r25 = 1;
        goto L_0x0043;
    L_0x01ce:
        r0 = r30;
        r2 = r0.match;
        r0 = r23;
        r7 = r2.getBeginning(r0);
        r0 = r30;
        r2 = r0.match;
        r0 = r23;
        r2 = r2.getEnd(r0);
        r8 = r2 - r7;
        if (r33 <= 0) goto L_0x0201;
    L_0x01e6:
        r0 = r30;
        r6 = r0.limit;
        r4 = r10;
        r5 = r32;
        r2 = r3.regionMatches(r4, r5, r6, r7, r8);
        if (r2 != 0) goto L_0x01f7;
    L_0x01f3:
        r25 = 1;
        goto L_0x0043;
    L_0x01f7:
        r32 = r32 + r8;
    L_0x01f9:
        r0 = r31;
        r0 = r0.next;
        r31 = r0;
        goto L_0x0043;
    L_0x0201:
        r5 = r32 - r8;
        r0 = r30;
        r6 = r0.limit;
        r4 = r10;
        r2 = r3.regionMatches(r4, r5, r6, r7, r8);
        if (r2 != 0) goto L_0x0212;
    L_0x020e:
        r25 = 1;
        goto L_0x0043;
    L_0x0212:
        r32 = r32 - r8;
        goto L_0x01f9;
    L_0x0215:
        r13 = r31.getString();
        r8 = r13.length();
        if (r33 <= 0) goto L_0x023b;
    L_0x021f:
        r0 = r30;
        r12 = r0.limit;
        r9 = r3;
        r11 = r32;
        r14 = r8;
        r2 = r9.regionMatches(r10, r11, r12, r13, r14);
        if (r2 != 0) goto L_0x0231;
    L_0x022d:
        r25 = 1;
        goto L_0x0043;
    L_0x0231:
        r32 = r32 + r8;
    L_0x0233:
        r0 = r31;
        r0 = r0.next;
        r31 = r0;
        goto L_0x0043;
    L_0x023b:
        r11 = r32 - r8;
        r0 = r30;
        r12 = r0.limit;
        r9 = r3;
        r14 = r8;
        r2 = r9.regionMatches(r10, r11, r12, r13, r14);
        if (r2 != 0) goto L_0x024d;
    L_0x0249:
        r25 = 1;
        goto L_0x0043;
    L_0x024d:
        r32 = r32 - r8;
        goto L_0x0233;
    L_0x0250:
        r18 = r31.getData();
        r0 = r30;
        r2 = r0.closureContexts;
        r2 = r2[r18];
        r0 = r32;
        r2 = r2.contains(r0);
        if (r2 == 0) goto L_0x0266;
    L_0x0262:
        r25 = 1;
        goto L_0x0043;
    L_0x0266:
        r0 = r30;
        r2 = r0.closureContexts;
        r2 = r2[r18];
        r0 = r32;
        r2.addOffset(r0);
    L_0x0271:
        r0 = r22;
        r1 = r31;
        r0.push(r1);
        r0 = r17;
        r1 = r32;
        r0.push(r1);
        r31 = r31.getChild();
        goto L_0x0043;
    L_0x0285:
        r0 = r22;
        r1 = r31;
        r0.push(r1);
        r0 = r17;
        r1 = r32;
        r0.push(r1);
        r0 = r31;
        r0 = r0.next;
        r31 = r0;
        goto L_0x0043;
    L_0x029b:
        r2 = r31.size();
        if (r2 != 0) goto L_0x02a5;
    L_0x02a1:
        r25 = 1;
        goto L_0x0043;
    L_0x02a5:
        r0 = r22;
        r1 = r31;
        r0.push(r1);
        r2 = 0;
        r0 = r17;
        r0.push(r2);
        r0 = r17;
        r1 = r32;
        r0.push(r1);
        r2 = 0;
        r0 = r31;
        r31 = r0.elementAt(r2);
        goto L_0x0043;
    L_0x02c2:
        r23 = r31.getData();
        r0 = r30;
        r2 = r0.match;
        if (r2 == 0) goto L_0x02f6;
    L_0x02cc:
        if (r23 <= 0) goto L_0x02fe;
    L_0x02ce:
        r0 = r30;
        r2 = r0.match;
        r0 = r23;
        r2 = r2.getBeginning(r0);
        r0 = r17;
        r0.push(r2);
        r0 = r30;
        r2 = r0.match;
        r0 = r23;
        r1 = r32;
        r2.setBeginning(r0, r1);
    L_0x02e8:
        r0 = r22;
        r1 = r31;
        r0.push(r1);
        r0 = r17;
        r1 = r32;
        r0.push(r1);
    L_0x02f6:
        r0 = r31;
        r0 = r0.next;
        r31 = r0;
        goto L_0x0043;
    L_0x02fe:
        r0 = r23;
        r0 = -r0;
        r19 = r0;
        r0 = r30;
        r2 = r0.match;
        r0 = r19;
        r2 = r2.getEnd(r0);
        r0 = r17;
        r0.push(r2);
        r0 = r30;
        r2 = r0.match;
        r0 = r19;
        r1 = r32;
        r2.setEnd(r0, r1);
        goto L_0x02e8;
    L_0x031e:
        r0 = r22;
        r1 = r31;
        r0.push(r1);
        r0 = r17;
        r1 = r33;
        r0.push(r1);
        r0 = r17;
        r1 = r32;
        r0.push(r1);
        r0 = r31;
        r2 = r0.type;
        r4 = 20;
        if (r2 == r4) goto L_0x0343;
    L_0x033b:
        r0 = r31;
        r2 = r0.type;
        r4 = 21;
        if (r2 != r4) goto L_0x034b;
    L_0x0343:
        r33 = 1;
    L_0x0345:
        r31 = r31.getChild();
        goto L_0x0043;
    L_0x034b:
        r33 = -1;
        goto L_0x0345;
    L_0x034e:
        r0 = r22;
        r1 = r31;
        r0.push(r1);
        r0 = r17;
        r1 = r32;
        r0.push(r1);
        r31 = r31.getChild();
        goto L_0x0043;
    L_0x0362:
        r20 = r34;
        r2 = r31.getData();
        r20 = r20 | r2;
        r2 = r31.getData2();
        r2 = r2 ^ -1;
        r20 = r20 & r2;
        r0 = r22;
        r1 = r31;
        r0.push(r1);
        r0 = r17;
        r1 = r34;
        r0.push(r1);
        r0 = r17;
        r1 = r32;
        r0.push(r1);
        r34 = r20;
        r31 = r31.getChild();
        goto L_0x0043;
    L_0x038f:
        r16 = r31;
        r16 = (mf.org.apache.xerces.impl.xpath.regex.Op.ConditionOp) r16;
        r0 = r16;
        r2 = r0.refNumber;
        if (r2 <= 0) goto L_0x03f7;
    L_0x0399:
        r0 = r16;
        r2 = r0.refNumber;
        r0 = r29;
        r4 = r0.nofparen;
        if (r2 < r4) goto L_0x03bd;
    L_0x03a3:
        r2 = new java.lang.RuntimeException;
        r4 = new java.lang.StringBuilder;
        r5 = "Internal Error: Reference number must be more than zero: ";
        r4.<init>(r5);
        r0 = r16;
        r5 = r0.refNumber;
        r4 = r4.append(r5);
        r4 = r4.toString();
        r2.<init>(r4);
        throw r2;
    L_0x03bd:
        r0 = r30;
        r2 = r0.match;
        r0 = r16;
        r4 = r0.refNumber;
        r2 = r2.getBeginning(r4);
        if (r2 < 0) goto L_0x03e1;
    L_0x03cb:
        r0 = r30;
        r2 = r0.match;
        r0 = r16;
        r4 = r0.refNumber;
        r2 = r2.getEnd(r4);
        if (r2 < 0) goto L_0x03e1;
    L_0x03d9:
        r0 = r16;
        r0 = r0.yes;
        r31 = r0;
        goto L_0x0043;
    L_0x03e1:
        r0 = r16;
        r2 = r0.no;
        if (r2 == 0) goto L_0x03ef;
    L_0x03e7:
        r0 = r16;
        r0 = r0.no;
        r31 = r0;
        goto L_0x0043;
    L_0x03ef:
        r0 = r16;
        r0 = r0.next;
        r31 = r0;
        goto L_0x0043;
    L_0x03f7:
        r0 = r22;
        r1 = r31;
        r0.push(r1);
        r0 = r17;
        r1 = r32;
        r0.push(r1);
        r0 = r16;
        r0 = r0.condition;
        r31 = r0;
        goto L_0x0043;
    L_0x040d:
        r31 = r22.pop();
        r31 = (mf.org.apache.xerces.impl.xpath.regex.Op) r31;
        r32 = r17.pop();
        r0 = r31;
        r2 = r0.type;
        switch(r2) {
            case 7: goto L_0x0420;
            case 8: goto L_0x042c;
            case 9: goto L_0x0420;
            case 10: goto L_0x042c;
            case 11: goto L_0x0436;
            case 12: goto L_0x041e;
            case 13: goto L_0x041e;
            case 14: goto L_0x041e;
            case 15: goto L_0x046b;
            case 16: goto L_0x041e;
            case 17: goto L_0x041e;
            case 18: goto L_0x041e;
            case 19: goto L_0x041e;
            case 20: goto L_0x0492;
            case 21: goto L_0x04a4;
            case 22: goto L_0x0492;
            case 23: goto L_0x04a4;
            case 24: goto L_0x04ba;
            case 25: goto L_0x04b6;
            case 26: goto L_0x04c8;
            default: goto L_0x041e;
        };
    L_0x041e:
        goto L_0x0043;
    L_0x0420:
        if (r24 >= 0) goto L_0x0043;
    L_0x0422:
        r0 = r31;
        r0 = r0.next;
        r31 = r0;
        r25 = 0;
        goto L_0x0043;
    L_0x042c:
        if (r24 >= 0) goto L_0x0043;
    L_0x042e:
        r31 = r31.getChild();
        r25 = 0;
        goto L_0x0043;
    L_0x0436:
        r28 = r17.pop();
        if (r24 >= 0) goto L_0x0043;
    L_0x043c:
        r28 = r28 + 1;
        r2 = r31.size();
        r0 = r28;
        if (r0 >= r2) goto L_0x0467;
    L_0x0446:
        r0 = r22;
        r1 = r31;
        r0.push(r1);
        r0 = r17;
        r1 = r28;
        r0.push(r1);
        r0 = r17;
        r1 = r32;
        r0.push(r1);
        r0 = r31;
        r1 = r28;
        r31 = r0.elementAt(r1);
        r25 = 0;
        goto L_0x0043;
    L_0x0467:
        r24 = -1;
        goto L_0x0043;
    L_0x046b:
        r23 = r31.getData();
        r26 = r17.pop();
        if (r24 >= 0) goto L_0x0043;
    L_0x0475:
        if (r23 <= 0) goto L_0x0484;
    L_0x0477:
        r0 = r30;
        r2 = r0.match;
        r0 = r23;
        r1 = r26;
        r2.setBeginning(r0, r1);
        goto L_0x0043;
    L_0x0484:
        r0 = r30;
        r2 = r0.match;
        r0 = r23;
        r4 = -r0;
        r0 = r26;
        r2.setEnd(r4, r0);
        goto L_0x0043;
    L_0x0492:
        r33 = r17.pop();
        if (r24 < 0) goto L_0x04a0;
    L_0x0498:
        r0 = r31;
        r0 = r0.next;
        r31 = r0;
        r25 = 0;
    L_0x04a0:
        r24 = -1;
        goto L_0x0043;
    L_0x04a4:
        r33 = r17.pop();
        if (r24 >= 0) goto L_0x04b2;
    L_0x04aa:
        r0 = r31;
        r0 = r0.next;
        r31 = r0;
        r25 = 0;
    L_0x04b2:
        r24 = -1;
        goto L_0x0043;
    L_0x04b6:
        r34 = r17.pop();
    L_0x04ba:
        if (r24 < 0) goto L_0x0043;
    L_0x04bc:
        r32 = r24;
        r0 = r31;
        r0 = r0.next;
        r31 = r0;
        r25 = 0;
        goto L_0x0043;
    L_0x04c8:
        r16 = r31;
        r16 = (mf.org.apache.xerces.impl.xpath.regex.Op.ConditionOp) r16;
        if (r24 < 0) goto L_0x04d8;
    L_0x04ce:
        r0 = r16;
        r0 = r0.yes;
        r31 = r0;
    L_0x04d4:
        r25 = 0;
        goto L_0x0043;
    L_0x04d8:
        r0 = r16;
        r2 = r0.no;
        if (r2 == 0) goto L_0x04e5;
    L_0x04de:
        r0 = r16;
        r0 = r0.no;
        r31 = r0;
        goto L_0x04d4;
    L_0x04e5:
        r0 = r16;
        r0 = r0.next;
        r31 = r0;
        goto L_0x04d4;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.xpath.regex.RegularExpression.match(mf.org.apache.xerces.impl.xpath.regex.RegularExpression$Context, mf.org.apache.xerces.impl.xpath.regex.Op, int, int, int):int");
    }

    private boolean matchChar(int ch, int other, boolean ignoreCase) {
        if (ignoreCase) {
            return matchIgnoreCase(ch, other);
        }
        return ch == other;
    }

    boolean matchAnchor(ExpressionTarget target, Op op, Context con, int offset, int opts) {
        int after;
        switch (op.getData()) {
            case 36:
                if (isSet(opts, 8)) {
                    if (offset != con.limit) {
                        if (offset >= con.limit) {
                            return false;
                        }
                        if (!isEOLChar(target.charAt(offset))) {
                            return false;
                        }
                    }
                } else if (!(offset == con.limit || (offset + 1 == con.limit && isEOLChar(target.charAt(offset))))) {
                    if (offset + 2 != con.limit || target.charAt(offset) != '\r') {
                        return false;
                    }
                    if (target.charAt(offset + 1) != '\n') {
                        return false;
                    }
                }
                break;
            case 60:
                if (con.length == 0 || offset == con.limit || getWordType(target, con.start, con.limit, offset, opts) != 1) {
                    return false;
                }
                if (getPreviousWordType(target, con.start, con.limit, offset, opts) != 2) {
                    return false;
                }
                break;
            case 62:
                if (con.length == 0 || offset == con.start || getWordType(target, con.start, con.limit, offset, opts) != 2) {
                    return false;
                }
                if (getPreviousWordType(target, con.start, con.limit, offset, opts) != 1) {
                    return false;
                }
                break;
            case 64:
                if (offset != con.start) {
                    if (offset <= con.start) {
                        return false;
                    }
                    if (!isEOLChar(target.charAt(offset - 1))) {
                        return false;
                    }
                }
                break;
            case 65:
                if (offset != con.start) {
                    return false;
                }
                break;
            case 66:
                boolean go;
                if (con.length == 0) {
                    go = true;
                } else {
                    after = getWordType(target, con.start, con.limit, offset, opts);
                    if (after == 0 || after == getPreviousWordType(target, con.start, con.limit, offset, opts)) {
                        go = true;
                    } else {
                        go = false;
                    }
                }
                if (!go) {
                    return false;
                }
                break;
            case 90:
                if (!(offset == con.limit || (offset + 1 == con.limit && isEOLChar(target.charAt(offset))))) {
                    if (offset + 2 != con.limit || target.charAt(offset) != '\r') {
                        return false;
                    }
                    if (target.charAt(offset + 1) != '\n') {
                        return false;
                    }
                }
                break;
            case 94:
                if (isSet(opts, 8)) {
                    if (offset != con.start) {
                        if (offset <= con.start || offset >= con.limit) {
                            return false;
                        }
                        if (!isEOLChar(target.charAt(offset - 1))) {
                            return false;
                        }
                    }
                } else if (offset != con.start) {
                    return false;
                }
                break;
            case 98:
                if (con.length == 0) {
                    return false;
                }
                after = getWordType(target, con.start, con.limit, offset, opts);
                if (after == 0) {
                    return false;
                }
                if (after == getPreviousWordType(target, con.start, con.limit, offset, opts)) {
                    return false;
                }
                break;
            case 122:
                if (offset != con.limit) {
                    return false;
                }
                break;
        }
        return true;
    }

    private static final int getPreviousWordType(ExpressionTarget target, int begin, int end, int offset, int opts) {
        offset--;
        int ret = getWordType(target, begin, end, offset, opts);
        while (ret == 0) {
            offset--;
            ret = getWordType(target, begin, end, offset, opts);
        }
        return ret;
    }

    private static final int getWordType(ExpressionTarget target, int begin, int end, int offset, int opts) {
        if (offset < begin || offset >= end) {
            return 2;
        }
        return getWordType0(target.charAt(offset), opts);
    }

    public boolean matches(CharacterIterator target) {
        return matches(target, null);
    }

    public boolean matches(CharacterIterator target, Match match) {
        Context con;
        int start = target.getBeginIndex();
        int end = target.getEndIndex();
        synchronized (this) {
            if (this.operations == null) {
                prepare();
            }
            if (this.context == null) {
                this.context = new Context();
            }
        }
        synchronized (this.context) {
            con = this.context.inuse ? new Context() : this.context;
            con.reset(target, start, end, this.numberOfClosures);
        }
        if (match != null) {
            match.setNumberOfGroups(this.nofparen);
            match.setSource(target);
        } else if (this.hasBackReferences) {
            match = new Match();
            match.setNumberOfGroups(this.nofparen);
        }
        con.match = match;
        int matchEnd;
        if (isSet(this.options, 512)) {
            matchEnd = match(con, this.operations, con.start, 1, this.options);
            if (matchEnd != con.limit) {
                return false;
            }
            if (con.match != null) {
                con.match.setBeginning(0, con.start);
                con.match.setEnd(0, matchEnd);
            }
            con.setInUse(false);
            return true;
        } else if (this.fixedStringOnly) {
            int o = this.fixedStringTable.matches(target, con.start, con.limit);
            if (o >= 0) {
                if (con.match != null) {
                    con.match.setBeginning(0, o);
                    con.match.setEnd(0, this.fixedString.length() + o);
                }
                con.setInUse(false);
                return true;
            }
            con.setInUse(false);
            return false;
        } else {
            int matchStart;
            if (this.fixedString != null) {
                if (this.fixedStringTable.matches(target, con.start, con.limit) < 0) {
                    con.setInUse(false);
                    return false;
                }
            }
            int limit = con.limit - this.minlength;
            matchEnd = -1;
            if (this.operations == null || this.operations.type != 7 || this.operations.getChild().type != 0) {
                if (this.firstChar == null) {
                    matchStart = con.start;
                    while (matchStart <= limit) {
                        matchEnd = match(con, this.operations, matchStart, 1, this.options);
                        if (matchEnd >= 0) {
                            break;
                        }
                        matchStart++;
                    }
                } else {
                    RangeToken range = this.firstChar;
                    matchStart = con.start;
                    while (matchStart <= limit) {
                        int ch = target.setIndex(matchStart);
                        if (REUtil.isHighSurrogate(ch) && matchStart + 1 < con.limit) {
                            ch = REUtil.composeFromSurrogates(ch, target.setIndex(matchStart + 1));
                        }
                        if (range.match(ch)) {
                            matchEnd = match(con, this.operations, matchStart, 1, this.options);
                            if (matchEnd >= 0) {
                                break;
                            }
                        }
                        matchStart++;
                    }
                }
            } else if (isSet(this.options, 4)) {
                int matchStart2 = con.start;
                matchEnd = match(con, this.operations, con.start, 1, this.options);
                matchStart = matchStart2;
            } else {
                boolean previousIsEOL = true;
                matchStart = con.start;
                while (matchStart <= limit) {
                    if (isEOLChar(target.setIndex(matchStart))) {
                        previousIsEOL = true;
                    } else {
                        if (previousIsEOL) {
                            matchEnd = match(con, this.operations, matchStart, 1, this.options);
                            if (matchEnd >= 0) {
                                break;
                            }
                        }
                        previousIsEOL = false;
                    }
                    matchStart++;
                }
            }
            if (matchEnd >= 0) {
                if (con.match != null) {
                    con.match.setBeginning(0, matchStart);
                    con.match.setEnd(0, matchEnd);
                }
                con.setInUse(false);
                return true;
            }
            con.setInUse(false);
            return false;
        }
    }

    void prepare() {
        compile(this.tokentree);
        this.minlength = this.tokentree.getMinLength();
        this.firstChar = null;
        if (!(isSet(this.options, 128) || isSet(this.options, 512))) {
            RangeToken firstChar = Token.createRange();
            if (this.tokentree.analyzeFirstCharacter(firstChar, this.options) == 1) {
                firstChar.compactRanges();
                this.firstChar = firstChar;
            }
        }
        if (this.operations != null && ((this.operations.type == 6 || this.operations.type == 1) && this.operations.next == null)) {
            this.fixedStringOnly = true;
            if (this.operations.type == 6) {
                this.fixedString = this.operations.getString();
            } else if (this.operations.getData() >= 65536) {
                this.fixedString = REUtil.decomposeToSurrogates(this.operations.getData());
            } else {
                this.fixedString = new String(new char[]{(char) this.operations.getData()});
            }
            this.fixedStringOptions = this.options;
            this.fixedStringTable = new BMPattern(this.fixedString, 256, isSet(this.fixedStringOptions, 2));
        } else if (!isSet(this.options, 256) && !isSet(this.options, 512)) {
            FixedStringContainer container = new FixedStringContainer();
            this.tokentree.findFixedString(container, this.options);
            this.fixedString = container.token == null ? null : container.token.getString();
            this.fixedStringOptions = container.options;
            if (this.fixedString != null && this.fixedString.length() < 2) {
                this.fixedString = null;
            }
            if (this.fixedString != null) {
                this.fixedStringTable = new BMPattern(this.fixedString, 256, isSet(this.fixedStringOptions, 2));
            }
        }
    }

    private static final boolean isSet(int options, int flag) {
        return (options & flag) == flag;
    }

    public RegularExpression(String regex) throws ParseException {
        this(regex, null);
    }

    public RegularExpression(String regex, String options) throws ParseException {
        this.hasBackReferences = false;
        this.operations = null;
        this.context = null;
        this.firstChar = null;
        this.fixedString = null;
        this.fixedStringTable = null;
        this.fixedStringOnly = false;
        setPattern(regex, options);
    }

    public RegularExpression(String regex, String options, Locale locale) throws ParseException {
        this.hasBackReferences = false;
        this.operations = null;
        this.context = null;
        this.firstChar = null;
        this.fixedString = null;
        this.fixedStringTable = null;
        this.fixedStringOnly = false;
        setPattern(regex, options, locale);
    }

    RegularExpression(String regex, Token tok, int parens, boolean hasBackReferences, int options) {
        this.hasBackReferences = false;
        this.operations = null;
        this.context = null;
        this.firstChar = null;
        this.fixedString = null;
        this.fixedStringTable = null;
        this.fixedStringOnly = false;
        this.regex = regex;
        this.tokentree = tok;
        this.nofparen = parens;
        this.options = options;
        this.hasBackReferences = hasBackReferences;
    }

    public void setPattern(String newPattern) throws ParseException {
        setPattern(newPattern, Locale.getDefault());
    }

    public void setPattern(String newPattern, Locale locale) throws ParseException {
        setPattern(newPattern, this.options, locale);
    }

    private void setPattern(String newPattern, int options, Locale locale) throws ParseException {
        this.regex = newPattern;
        this.options = options;
        RegexParser rp = isSet(this.options, 512) ? new ParserForXMLSchema(locale) : new RegexParser(locale);
        this.tokentree = rp.parse(this.regex, this.options);
        this.nofparen = rp.parennumber;
        this.hasBackReferences = rp.hasBackReferences;
        this.operations = null;
        this.context = null;
    }

    public void setPattern(String newPattern, String options) throws ParseException {
        setPattern(newPattern, options, Locale.getDefault());
    }

    public void setPattern(String newPattern, String options, Locale locale) throws ParseException {
        setPattern(newPattern, REUtil.parseOptions(options), locale);
    }

    public String getPattern() {
        return this.regex;
    }

    public String toString() {
        return this.tokentree.toString(this.options);
    }

    public String getOptions() {
        return REUtil.createOptionString(this.options);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof RegularExpression)) {
            return false;
        }
        RegularExpression r = (RegularExpression) obj;
        if (this.regex.equals(r.regex) && this.options == r.options) {
            return true;
        }
        return false;
    }

    boolean equals(String pattern, int options) {
        return this.regex.equals(pattern) && this.options == options;
    }

    public int hashCode() {
        return (this.regex + BridgeUtil.SPLIT_MARK + getOptions()).hashCode();
    }

    public int getNumberOfGroups() {
        return this.nofparen;
    }

    private static final int getWordType0(char ch, int opts) {
        if (isSet(opts, 64)) {
            switch (Character.getType(ch)) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 8:
                case 9:
                case 10:
                case 11:
                    return 1;
                case 6:
                case 7:
                case 16:
                    return 0;
                case 15:
                    switch (ch) {
                        case '\t':
                        case '\n':
                        case '\u000b':
                        case '\f':
                        case '\r':
                            return 2;
                        default:
                            return 0;
                    }
                default:
                    return 2;
            }
        } else if (isSet(opts, 32)) {
            if (Token.getRange("IsWord", true).match(ch)) {
                return 1;
            }
            return 2;
        } else if (isWordChar(ch)) {
            return 1;
        } else {
            return 2;
        }
    }

    private static final boolean isEOLChar(int ch) {
        return ch == 10 || ch == 13 || ch == LINE_SEPARATOR || ch == PARAGRAPH_SEPARATOR;
    }

    private static final boolean isWordChar(int ch) {
        if (ch == 95) {
            return true;
        }
        if (ch < 48) {
            return false;
        }
        if (ch > 122) {
            return false;
        }
        if (ch <= 57) {
            return true;
        }
        if (ch < 65) {
            return false;
        }
        if (ch <= 90 || ch >= 97) {
            return true;
        }
        return false;
    }

    private static final boolean matchIgnoreCase(int chardata, int ch) {
        if (chardata == ch) {
            return true;
        }
        if (chardata > SupportMenu.USER_MASK || ch > SupportMenu.USER_MASK) {
            return false;
        }
        char uch1 = Character.toUpperCase((char) chardata);
        char uch2 = Character.toUpperCase((char) ch);
        if (uch1 == uch2 || Character.toLowerCase(uch1) == Character.toLowerCase(uch2)) {
            return true;
        }
        return false;
    }
}
