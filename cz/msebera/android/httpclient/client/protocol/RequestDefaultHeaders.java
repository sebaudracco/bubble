package cz.msebera.android.httpclient.client.protocol;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpRequestInterceptor;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.params.ClientPNames;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import java.util.Collection;

@Immutable
public class RequestDefaultHeaders implements HttpRequestInterceptor {
    private final Collection<? extends Header> defaultHeaders;

    public RequestDefaultHeaders(Collection<? extends Header> defaultHeaders) {
        this.defaultHeaders = defaultHeaders;
    }

    public RequestDefaultHeaders() {
        this(null);
    }

    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        Args.notNull(request, "HTTP request");
        if (!request.getRequestLine().getMethod().equalsIgnoreCase("CONNECT")) {
            Collection<? extends Header> defHeaders = (Collection) request.getParams().getParameter(ClientPNames.DEFAULT_HEADERS);
            if (defHeaders == null) {
                defHeaders = this.defaultHeaders;
            }
            if (defHeaders != null) {
                for (Header defHeader : defHeaders) {
                    request.addHeader(defHeader);
                }
            }
        }
    }
}
