package cz.msebera.android.httpclient.impl.entity;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpMessage;
import cz.msebera.android.httpclient.ParseException;
import cz.msebera.android.httpclient.ProtocolException;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.entity.ContentLengthStrategy;
import cz.msebera.android.httpclient.protocol.HTTP;
import cz.msebera.android.httpclient.util.Args;

@Immutable
public class LaxContentLengthStrategy implements ContentLengthStrategy {
    public static final LaxContentLengthStrategy INSTANCE = new LaxContentLengthStrategy();
    private final int implicitLen;

    public LaxContentLengthStrategy(int implicitLen) {
        this.implicitLen = implicitLen;
    }

    public LaxContentLengthStrategy() {
        this(-1);
    }

    public long determineLength(HttpMessage message) throws HttpException {
        Args.notNull(message, "HTTP message");
        Header transferEncodingHeader = message.getFirstHeader("Transfer-Encoding");
        if (transferEncodingHeader != null) {
            try {
                HeaderElement[] encodings = transferEncodingHeader.getElements();
                int len = encodings.length;
                if (HTTP.IDENTITY_CODING.equalsIgnoreCase(transferEncodingHeader.getValue())) {
                    return -1;
                }
                return (len <= 0 || !HTTP.CHUNK_CODING.equalsIgnoreCase(encodings[len - 1].getName())) ? -1 : -2;
            } catch (ParseException px) {
                throw new ProtocolException("Invalid Transfer-Encoding header value: " + transferEncodingHeader, px);
            }
        } else if (message.getFirstHeader("Content-Length") == null) {
            return (long) this.implicitLen;
        } else {
            long contentlen = -1;
            Header[] headers = message.getHeaders("Content-Length");
            int i = headers.length - 1;
            while (i >= 0) {
                try {
                    contentlen = Long.parseLong(headers[i].getValue());
                    break;
                } catch (NumberFormatException e) {
                    i--;
                }
            }
            if (contentlen < 0) {
                return -1;
            }
            return contentlen;
        }
    }
}
