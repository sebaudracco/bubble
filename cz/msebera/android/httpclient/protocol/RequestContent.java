package cz.msebera.android.httpclient.protocol;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpRequestInterceptor;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.ProtocolException;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

@Immutable
public class RequestContent implements HttpRequestInterceptor {
    private final boolean overwrite;

    public RequestContent() {
        this(false);
    }

    public RequestContent(boolean overwrite) {
        this.overwrite = overwrite;
    }

    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        Args.notNull(request, "HTTP request");
        if (request instanceof HttpEntityEnclosingRequest) {
            if (this.overwrite) {
                request.removeHeaders("Transfer-Encoding");
                request.removeHeaders("Content-Length");
            } else if (request.containsHeader("Transfer-Encoding")) {
                throw new ProtocolException("Transfer-encoding header already present");
            } else if (request.containsHeader("Content-Length")) {
                throw new ProtocolException("Content-Length header already present");
            }
            ProtocolVersion ver = request.getRequestLine().getProtocolVersion();
            HttpEntity entity = ((HttpEntityEnclosingRequest) request).getEntity();
            if (entity == null) {
                request.addHeader("Content-Length", SchemaSymbols.ATTVAL_FALSE_0);
                return;
            }
            if (!entity.isChunked() && entity.getContentLength() >= 0) {
                request.addHeader("Content-Length", Long.toString(entity.getContentLength()));
            } else if (ver.lessEquals(HttpVersion.HTTP_1_0)) {
                throw new ProtocolException("Chunked transfer encoding not allowed for " + ver);
            } else {
                request.addHeader("Transfer-Encoding", HTTP.CHUNK_CODING);
            }
            if (!(entity.getContentType() == null || request.containsHeader("Content-Type"))) {
                request.addHeader(entity.getContentType());
            }
            if (entity.getContentEncoding() != null && !request.containsHeader("Content-Encoding")) {
                request.addHeader(entity.getContentEncoding());
            }
        }
    }
}
