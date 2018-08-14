package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.client.utils.DateUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class WarningValue {
    private static final String ASCTIME_DATE = "(Mon|Tue|Wed|Thu|Fri|Sat|Sun) ((Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) ( |\\d)\\d) (\\d{2}:\\d{2}:\\d{2}) \\d{4}";
    private static final String DATE1 = "\\d{2} (Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) \\d{4}";
    private static final String DATE2 = "\\d{2}-(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)-\\d{2}";
    private static final String DATE3 = "(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) ( |\\d)\\d";
    private static final String DOMAINLABEL = "\\p{Alnum}([\\p{Alnum}-]*\\p{Alnum})?";
    private static final String HOST = "((\\p{Alnum}([\\p{Alnum}-]*\\p{Alnum})?\\.)*\\p{Alpha}([\\p{Alnum}-]*\\p{Alnum})?\\.?)|(\\d+\\.\\d+\\.\\d+\\.\\d+)";
    private static final String HOSTNAME = "(\\p{Alnum}([\\p{Alnum}-]*\\p{Alnum})?\\.)*\\p{Alpha}([\\p{Alnum}-]*\\p{Alnum})?\\.?";
    private static final String HOSTPORT = "(((\\p{Alnum}([\\p{Alnum}-]*\\p{Alnum})?\\.)*\\p{Alpha}([\\p{Alnum}-]*\\p{Alnum})?\\.?)|(\\d+\\.\\d+\\.\\d+\\.\\d+))(\\:\\d*)?";
    private static final Pattern HOSTPORT_PATTERN = Pattern.compile(HOSTPORT);
    private static final String HTTP_DATE = "((Mon|Tue|Wed|Thu|Fri|Sat|Sun), (\\d{2} (Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) \\d{4}) (\\d{2}:\\d{2}:\\d{2}) GMT)|((Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday), (\\d{2}-(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)-\\d{2}) (\\d{2}:\\d{2}:\\d{2}) GMT)|((Mon|Tue|Wed|Thu|Fri|Sat|Sun) ((Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) ( |\\d)\\d) (\\d{2}:\\d{2}:\\d{2}) \\d{4})";
    private static final String IPV4ADDRESS = "\\d+\\.\\d+\\.\\d+\\.\\d+";
    private static final String MONTH = "Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec";
    private static final String PORT = "\\d*";
    private static final String RFC1123_DATE = "(Mon|Tue|Wed|Thu|Fri|Sat|Sun), (\\d{2} (Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) \\d{4}) (\\d{2}:\\d{2}:\\d{2}) GMT";
    private static final String RFC850_DATE = "(Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday), (\\d{2}-(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)-\\d{2}) (\\d{2}:\\d{2}:\\d{2}) GMT";
    private static final String TIME = "\\d{2}:\\d{2}:\\d{2}";
    private static final String TOPLABEL = "\\p{Alpha}([\\p{Alnum}-]*\\p{Alnum})?";
    private static final String WARN_DATE = "\"(((Mon|Tue|Wed|Thu|Fri|Sat|Sun), (\\d{2} (Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) \\d{4}) (\\d{2}:\\d{2}:\\d{2}) GMT)|((Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday), (\\d{2}-(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)-\\d{2}) (\\d{2}:\\d{2}:\\d{2}) GMT)|((Mon|Tue|Wed|Thu|Fri|Sat|Sun) ((Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) ( |\\d)\\d) (\\d{2}:\\d{2}:\\d{2}) \\d{4}))\"";
    private static final Pattern WARN_DATE_PATTERN = Pattern.compile(WARN_DATE);
    private static final String WEEKDAY = "Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday";
    private static final String WKDAY = "Mon|Tue|Wed|Thu|Fri|Sat|Sun";
    private int init_offs;
    private int offs;
    private final String src;
    private String warnAgent;
    private int warnCode;
    private Date warnDate;
    private String warnText;

    WarningValue(String s) {
        this(s, 0);
    }

    WarningValue(String s, int offs) {
        this.init_offs = offs;
        this.offs = offs;
        this.src = s;
        consumeWarnValue();
    }

    public static WarningValue[] getWarningValues(Header h) {
        List<WarningValue> out = new ArrayList();
        String src = h.getValue();
        int offs = 0;
        while (offs < src.length()) {
            try {
                WarningValue wv = new WarningValue(src, offs);
                out.add(wv);
                offs = wv.offs;
            } catch (IllegalArgumentException e) {
                int nextComma = src.indexOf(44, offs);
                if (nextComma == -1) {
                    break;
                }
                offs = nextComma + 1;
            }
        }
        return (WarningValue[]) out.toArray(new WarningValue[0]);
    }

    protected void consumeLinearWhitespace() {
        while (this.offs < this.src.length()) {
            switch (this.src.charAt(this.offs)) {
                case '\t':
                case ' ':
                    break;
                case '\r':
                    if (this.offs + 2 < this.src.length() && this.src.charAt(this.offs + 1) == '\n') {
                        if (this.src.charAt(this.offs + 2) == ' ' || this.src.charAt(this.offs + 2) == '\t') {
                            this.offs += 2;
                            break;
                        }
                        return;
                    }
                    return;
                    break;
                default:
                    return;
            }
            this.offs++;
        }
    }

    private boolean isChar(char c) {
        char i = c;
        return i >= '\u0000' && i <= '';
    }

    private boolean isControl(char c) {
        char i = c;
        return i == '' || (i >= '\u0000' && i <= '\u001f');
    }

    private boolean isSeparator(char c) {
        return c == '(' || c == ')' || c == '<' || c == '>' || c == '@' || c == ',' || c == ';' || c == ':' || c == '\\' || c == '\"' || c == '/' || c == '[' || c == ']' || c == '?' || c == '=' || c == '{' || c == '}' || c == ' ' || c == '\t';
    }

    protected void consumeToken() {
        if (!isTokenChar(this.src.charAt(this.offs))) {
            parseError();
        }
        while (this.offs < this.src.length() && isTokenChar(this.src.charAt(this.offs))) {
            this.offs++;
        }
    }

    private boolean isTokenChar(char c) {
        return (!isChar(c) || isControl(c) || isSeparator(c)) ? false : true;
    }

    protected void consumeHostPort() {
        Matcher m = HOSTPORT_PATTERN.matcher(this.src.substring(this.offs));
        if (!m.find()) {
            parseError();
        }
        if (m.start() != 0) {
            parseError();
        }
        this.offs += m.end();
    }

    protected void consumeWarnAgent() {
        int curr_offs = this.offs;
        try {
            consumeHostPort();
            this.warnAgent = this.src.substring(curr_offs, this.offs);
            consumeCharacter(' ');
        } catch (IllegalArgumentException e) {
            this.offs = curr_offs;
            consumeToken();
            this.warnAgent = this.src.substring(curr_offs, this.offs);
            consumeCharacter(' ');
        }
    }

    protected void consumeQuotedString() {
        if (this.src.charAt(this.offs) != '\"') {
            parseError();
        }
        this.offs++;
        boolean foundEnd = false;
        while (this.offs < this.src.length() && !foundEnd) {
            char c = this.src.charAt(this.offs);
            if (this.offs + 1 < this.src.length() && c == '\\' && isChar(this.src.charAt(this.offs + 1))) {
                this.offs += 2;
            } else if (c == '\"') {
                foundEnd = true;
                this.offs++;
            } else if (c == '\"' || isControl(c)) {
                parseError();
            } else {
                this.offs++;
            }
        }
        if (!foundEnd) {
            parseError();
        }
    }

    protected void consumeWarnText() {
        int curr = this.offs;
        consumeQuotedString();
        this.warnText = this.src.substring(curr, this.offs);
    }

    protected void consumeWarnDate() {
        int curr = this.offs;
        Matcher m = WARN_DATE_PATTERN.matcher(this.src.substring(this.offs));
        if (!m.lookingAt()) {
            parseError();
        }
        this.offs += m.end();
        this.warnDate = DateUtils.parseDate(this.src.substring(curr + 1, this.offs - 1));
    }

    protected void consumeWarnValue() {
        consumeLinearWhitespace();
        consumeWarnCode();
        consumeWarnAgent();
        consumeWarnText();
        if (this.offs + 1 < this.src.length() && this.src.charAt(this.offs) == ' ' && this.src.charAt(this.offs + 1) == '\"') {
            consumeCharacter(' ');
            consumeWarnDate();
        }
        consumeLinearWhitespace();
        if (this.offs != this.src.length()) {
            consumeCharacter(',');
        }
    }

    protected void consumeCharacter(char c) {
        if (this.offs + 1 > this.src.length() || c != this.src.charAt(this.offs)) {
            parseError();
        }
        this.offs++;
    }

    protected void consumeWarnCode() {
        if (!(this.offs + 4 <= this.src.length() && Character.isDigit(this.src.charAt(this.offs)) && Character.isDigit(this.src.charAt(this.offs + 1)) && Character.isDigit(this.src.charAt(this.offs + 2)) && this.src.charAt(this.offs + 3) == ' ')) {
            parseError();
        }
        this.warnCode = Integer.parseInt(this.src.substring(this.offs, this.offs + 3));
        this.offs += 4;
    }

    private void parseError() {
        throw new IllegalArgumentException("Bad warn code \"" + this.src.substring(this.init_offs) + "\"");
    }

    public int getWarnCode() {
        return this.warnCode;
    }

    public String getWarnAgent() {
        return this.warnAgent;
    }

    public String getWarnText() {
        return this.warnText;
    }

    public Date getWarnDate() {
        return this.warnDate;
    }

    public String toString() {
        if (this.warnDate != null) {
            return String.format("%d %s %s \"%s\"", new Object[]{Integer.valueOf(this.warnCode), this.warnAgent, this.warnText, DateUtils.formatDate(this.warnDate)});
        }
        return String.format("%d %s %s", new Object[]{Integer.valueOf(this.warnCode), this.warnAgent, this.warnText});
    }
}
