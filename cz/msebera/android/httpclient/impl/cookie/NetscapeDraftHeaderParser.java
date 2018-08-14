package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.ParseException;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.message.BasicHeaderElement;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.message.ParserCursor;
import cz.msebera.android.httpclient.protocol.HTTP;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.util.ArrayList;
import java.util.List;

@Immutable
public class NetscapeDraftHeaderParser {
    public static final NetscapeDraftHeaderParser DEFAULT = new NetscapeDraftHeaderParser();

    public HeaderElement parseHeader(CharArrayBuffer buffer, ParserCursor cursor) throws ParseException {
        Args.notNull(buffer, "Char array buffer");
        Args.notNull(cursor, "Parser cursor");
        NameValuePair nvp = parseNameValuePair(buffer, cursor);
        List<NameValuePair> params = new ArrayList();
        while (!cursor.atEnd()) {
            params.add(parseNameValuePair(buffer, cursor));
        }
        return new BasicHeaderElement(nvp.getName(), nvp.getValue(), (NameValuePair[]) params.toArray(new NameValuePair[params.size()]));
    }

    private NameValuePair parseNameValuePair(CharArrayBuffer buffer, ParserCursor cursor) {
        String name;
        boolean terminated = false;
        int pos = cursor.getPos();
        int indexFrom = cursor.getPos();
        int indexTo = cursor.getUpperBound();
        while (pos < indexTo) {
            char ch = buffer.charAt(pos);
            if (ch == '=') {
                break;
            } else if (ch == ';') {
                terminated = true;
                break;
            } else {
                pos++;
            }
        }
        if (pos == indexTo) {
            terminated = true;
            name = buffer.substringTrimmed(indexFrom, indexTo);
        } else {
            name = buffer.substringTrimmed(indexFrom, pos);
            pos++;
        }
        if (terminated) {
            cursor.updatePos(pos);
            return new BasicNameValuePair(name, null);
        }
        int i1 = pos;
        while (pos < indexTo) {
            if (buffer.charAt(pos) == ';') {
                terminated = true;
                break;
            }
            pos++;
        }
        int i2 = pos;
        while (i1 < i2 && HTTP.isWhitespace(buffer.charAt(i1))) {
            i1++;
        }
        while (i2 > i1 && HTTP.isWhitespace(buffer.charAt(i2 - 1))) {
            i2--;
        }
        String value = buffer.substring(i1, i2);
        if (terminated) {
            pos++;
        }
        cursor.updatePos(pos);
        return new BasicNameValuePair(name, value);
    }
}
