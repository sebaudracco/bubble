package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.HttpRequestRetryHandler;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.net.ssl.SSLException;

@Immutable
public class DefaultHttpRequestRetryHandler implements HttpRequestRetryHandler {
    public static final DefaultHttpRequestRetryHandler INSTANCE = new DefaultHttpRequestRetryHandler();
    private final Set<Class<? extends IOException>> nonRetriableClasses;
    private final boolean requestSentRetryEnabled;
    private final int retryCount;

    protected DefaultHttpRequestRetryHandler(int retryCount, boolean requestSentRetryEnabled, Collection<Class<? extends IOException>> clazzes) {
        this.retryCount = retryCount;
        this.requestSentRetryEnabled = requestSentRetryEnabled;
        this.nonRetriableClasses = new HashSet();
        for (Class<? extends IOException> clazz : clazzes) {
            this.nonRetriableClasses.add(clazz);
        }
    }

    public DefaultHttpRequestRetryHandler(int retryCount, boolean requestSentRetryEnabled) {
        this(retryCount, requestSentRetryEnabled, Arrays.asList(new Class[]{InterruptedIOException.class, UnknownHostException.class, ConnectException.class, SSLException.class}));
    }

    public DefaultHttpRequestRetryHandler() {
        this(3, false);
    }

    public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
        Args.notNull(exception, "Exception parameter");
        Args.notNull(context, "HTTP context");
        if (executionCount > this.retryCount || this.nonRetriableClasses.contains(exception.getClass())) {
            return false;
        }
        for (Class<? extends IOException> rejectException : this.nonRetriableClasses) {
            if (rejectException.isInstance(exception)) {
                return false;
            }
        }
        HttpClientContext clientContext = HttpClientContext.adapt(context);
        HttpRequest request = clientContext.getRequest();
        if (requestIsAborted(request)) {
            return false;
        }
        if (handleAsIdempotent(request)) {
            return true;
        }
        if (!clientContext.isRequestSent() || this.requestSentRetryEnabled) {
            return true;
        }
        return false;
    }

    public boolean isRequestSentRetryEnabled() {
        return this.requestSentRetryEnabled;
    }

    public int getRetryCount() {
        return this.retryCount;
    }

    protected boolean handleAsIdempotent(HttpRequest request) {
        return !(request instanceof HttpEntityEnclosingRequest);
    }

    @Deprecated
    protected boolean requestIsAborted(HttpRequest request) {
        HttpRequest req = request;
        if (request instanceof RequestWrapper) {
            req = ((RequestWrapper) request).getOriginal();
        }
        return (req instanceof HttpUriRequest) && ((HttpUriRequest) req).isAborted();
    }
}
