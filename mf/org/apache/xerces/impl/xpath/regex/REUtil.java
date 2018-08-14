package mf.org.apache.xerces.impl.xpath.regex;

import com.mobfox.sdk.networking.RequestParams;
import java.text.CharacterIterator;

public final class REUtil {
    static final int CACHESIZE = 20;
    static final RegularExpression[] regexCache = new RegularExpression[20];

    private REUtil() {
    }

    static final int composeFromSurrogates(int high, int low) {
        return ((65536 + ((high - 55296) << 10)) + low) - 56320;
    }

    static final boolean isLowSurrogate(int ch) {
        return (64512 & ch) == 56320;
    }

    static final boolean isHighSurrogate(int ch) {
        return (64512 & ch) == 55296;
    }

    static final String decomposeToSurrogates(int ch) {
        chs = new char[2];
        ch -= 65536;
        chs[0] = (char) ((ch >> 10) + 55296);
        chs[1] = (char) ((ch & 1023) + 56320);
        return new String(chs);
    }

    static final String substring(CharacterIterator iterator, int begin, int end) {
        char[] src = new char[(end - begin)];
        for (int i = 0; i < src.length; i++) {
            src[i] = iterator.setIndex(i + begin);
        }
        return new String(src);
    }

    static final int getOptionValue(int ch) {
        switch (ch) {
            case 44:
                return 1024;
            case 70:
                return 256;
            case 72:
                return 128;
            case 88:
                return 512;
            case 105:
                return 2;
            case 109:
                return 8;
            case 115:
                return 4;
            case 117:
                return 32;
            case 119:
                return 64;
            case 120:
                return 16;
            default:
                return 0;
        }
    }

    static final int parseOptions(String opts) throws ParseException {
        if (opts == null) {
            return 0;
        }
        int options = 0;
        for (int i = 0; i < opts.length(); i++) {
            int v = getOptionValue(opts.charAt(i));
            if (v == 0) {
                throw new ParseException("Unknown Option: " + opts.substring(i), -1);
            }
            options |= v;
        }
        return options;
    }

    static final String createOptionString(int options) {
        StringBuffer sb = new StringBuffer(9);
        if ((options & 256) != 0) {
            sb.append('F');
        }
        if ((options & 128) != 0) {
            sb.append('H');
        }
        if ((options & 512) != 0) {
            sb.append('X');
        }
        if ((options & 2) != 0) {
            sb.append('i');
        }
        if ((options & 8) != 0) {
            sb.append('m');
        }
        if ((options & 4) != 0) {
            sb.append('s');
        }
        if ((options & 32) != 0) {
            sb.append('u');
        }
        if ((options & 64) != 0) {
            sb.append('w');
        }
        if ((options & 16) != 0) {
            sb.append('x');
        }
        if ((options & 1024) != 0) {
            sb.append(',');
        }
        return sb.toString().intern();
    }

    static String stripExtendedComment(String regex) {
        int len = regex.length();
        StringBuffer buffer = new StringBuffer(len);
        int charClass = 0;
        int offset = 0;
        while (offset < len) {
            int offset2 = offset + 1;
            int ch = regex.charAt(offset);
            if (ch == 9 || ch == 10 || ch == 12 || ch == 13 || ch == 32) {
                if (charClass > 0) {
                    buffer.append((char) ch);
                    offset = offset2;
                }
            } else if (ch == 35) {
                offset = offset2;
                while (offset < len) {
                    offset2 = offset + 1;
                    ch = regex.charAt(offset);
                    if (ch != 13) {
                        if (ch == 10) {
                            offset = offset2;
                            break;
                        }
                        offset = offset2;
                    }
                }
            } else if (ch == 92 && offset2 < len) {
                next = regex.charAt(offset2);
                if (next == 35 || next == 9 || next == 10 || next == 12 || next == 13 || next == 32) {
                    buffer.append((char) next);
                    offset = offset2 + 1;
                } else {
                    buffer.append('\\');
                    buffer.append((char) next);
                    offset = offset2 + 1;
                }
            } else if (ch == 91) {
                charClass++;
                buffer.append((char) ch);
                if (offset2 < len) {
                    next = regex.charAt(offset2);
                    if (next == 91 || next == 93) {
                        buffer.append((char) next);
                        offset = offset2 + 1;
                    } else if (next == 94 && offset2 + 1 < len) {
                        next = regex.charAt(offset2 + 1);
                        if (next == 91 || next == 93) {
                            buffer.append('^');
                            buffer.append((char) next);
                            offset = offset2 + 2;
                        }
                    }
                }
            } else {
                if (charClass > 0 && ch == 93) {
                    charClass--;
                }
                buffer.append((char) ch);
            }
            offset = offset2;
        }
        return buffer.toString();
    }

    public static void main(String[] argv) {
        String pattern = null;
        int i;
        try {
            String options = "";
            String target = null;
            if (argv.length == 0) {
                System.out.println("Error:Usage: java REUtil -i|-m|-s|-u|-w|-X regularExpression String");
                System.exit(0);
            }
            i = 0;
            while (i < argv.length) {
                if (argv[i].length() == 0 || argv[i].charAt(0) != '-') {
                    if (pattern == null) {
                        pattern = argv[i];
                    } else if (target == null) {
                        target = argv[i];
                    } else {
                        System.err.println("Unnecessary: " + argv[i]);
                    }
                } else if (argv[i].equals("-i")) {
                    options = new StringBuilder(String.valueOf(options)).append(RequestParams.IP).toString();
                } else if (argv[i].equals("-m")) {
                    options = new StringBuilder(String.valueOf(options)).append(RequestParams.f9036M).toString();
                } else if (argv[i].equals("-s")) {
                    options = new StringBuilder(String.valueOf(options)).append("s").toString();
                } else if (argv[i].equals("-u")) {
                    options = new StringBuilder(String.valueOf(options)).append(RequestParams.f9038U).toString();
                } else if (argv[i].equals("-w")) {
                    options = new StringBuilder(String.valueOf(options)).append("w").toString();
                } else if (argv[i].equals("-X")) {
                    options = new StringBuilder(String.valueOf(options)).append("X").toString();
                } else {
                    System.err.println("Unknown option: " + argv[i]);
                }
                i++;
            }
            RegularExpression reg = new RegularExpression(pattern, options);
            System.out.println("RegularExpression: " + reg);
            Match match = new Match();
            reg.matches(target, match);
            for (i = 0; i < match.getNumberOfGroups(); i++) {
                if (i == 0) {
                    System.out.print("Matched range for the whole pattern: ");
                } else {
                    System.out.print("[" + i + "]: ");
                }
                if (match.getBeginning(i) < 0) {
                    System.out.println("-1");
                } else {
                    System.out.print(match.getBeginning(i) + ", " + match.getEnd(i) + ", ");
                    System.out.println("\"" + match.getCapturedText(i) + "\"");
                }
            }
        } catch (ParseException pe) {
            if (pattern == null) {
                pe.printStackTrace();
                return;
            }
            System.err.println("mf.org.apache.xerces.utils.regex.ParseException: " + pe.getMessage());
            String indent = "        ";
            System.err.println(new StringBuilder(String.valueOf(indent)).append(pattern).toString());
            int loc = pe.getLocation();
            if (loc >= 0) {
                System.err.print(indent);
                for (i = 0; i < loc; i++) {
                    System.err.print("-");
                }
                System.err.println("^");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static RegularExpression createRegex(String pattern, String options) throws ParseException {
        int intOptions = parseOptions(options);
        synchronized (regexCache) {
            RegularExpression re;
            RegularExpression re2;
            int i = 0;
            while (i < 20) {
                RegularExpression cached = regexCache[i];
                if (cached == null) {
                    i = -1;
                    re = null;
                    break;
                } else if (cached.equals(pattern, intOptions)) {
                    re = cached;
                    break;
                } else {
                    i++;
                }
            }
            re = null;
            if (re == null) {
                re2 = new RegularExpression(pattern, options);
                System.arraycopy(regexCache, 0, regexCache, 1, 19);
                regexCache[0] = re2;
            } else if (i != 0) {
                try {
                    System.arraycopy(regexCache, 0, regexCache, 1, i);
                    regexCache[0] = re;
                    re2 = re;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    re2 = re;
                    throw th2;
                }
            } else {
                re2 = re;
            }
            try {
                return re2;
            } catch (Throwable th3) {
                th2 = th3;
                throw th2;
            }
        }
    }

    public static boolean matches(String regex, String target) throws ParseException {
        return createRegex(regex, null).matches(target);
    }

    public static boolean matches(String regex, String options, String target) throws ParseException {
        return createRegex(regex, options).matches(target);
    }

    public static String quoteMeta(String literal) {
        int len = literal.length();
        StringBuffer buffer = null;
        for (int i = 0; i < len; i++) {
            int ch = literal.charAt(i);
            if (".*+?{[()|\\^$".indexOf(ch) >= 0) {
                if (buffer == null) {
                    buffer = new StringBuffer(((len - i) * 2) + i);
                    if (i > 0) {
                        buffer.append(literal.substring(0, i));
                    }
                }
                buffer.append('\\');
                buffer.append((char) ch);
            } else if (buffer != null) {
                buffer.append((char) ch);
            }
        }
        return buffer != null ? buffer.toString() : literal;
    }

    static void dumpString(String v) {
        for (int i = 0; i < v.length(); i++) {
            System.out.print(Integer.toHexString(v.charAt(i)));
            System.out.print(" ");
        }
        System.out.println();
    }
}
