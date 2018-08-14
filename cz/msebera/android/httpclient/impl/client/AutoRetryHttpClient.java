package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.ResponseHandler;
import cz.msebera.android.httpclient.client.ServiceUnavailableRetryStrategy;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.conn.ClientConnectionManager;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.EntityUtils;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URI;

@ThreadSafe
@Deprecated
public class AutoRetryHttpClient implements HttpClient {
    private final HttpClient backend;
    public HttpClientAndroidLog log;
    private final ServiceUnavailableRetryStrategy retryStrategy;

    public AutoRetryHttpClient(HttpClient client, ServiceUnavailableRetryStrategy retryStrategy) {
        this.log = new HttpClientAndroidLog(getClass());
        Args.notNull(client, "HttpClient");
        Args.notNull(retryStrategy, "ServiceUnavailableRetryStrategy");
        this.backend = client;
        this.retryStrategy = retryStrategy;
    }

    public AutoRetryHttpClient() {
        this(new DefaultHttpClient(), new DefaultServiceUnavailableRetryStrategy());
    }

    public AutoRetryHttpClient(ServiceUnavailableRetryStrategy config) {
        this(new DefaultHttpClient(), config);
    }

    public AutoRetryHttpClient(HttpClient client) {
        this(client, new DefaultServiceUnavailableRetryStrategy());
    }

    public HttpResponse execute(HttpHost target, HttpRequest request) throws IOException {
        return execute(target, request, null);
    }

    public <T> T execute(HttpHost target, HttpRequest request, ResponseHandler<? extends T> responseHandler) throws IOException {
        return execute(target, request, responseHandler, null);
    }

    public <T> T execute(HttpHost target, HttpRequest request, ResponseHandler<? extends T> responseHandler, HttpContext context) throws IOException {
        return responseHandler.handleResponse(execute(target, request, context));
    }

    public HttpResponse execute(HttpUriRequest request) throws IOException {
        return execute(request, null);
    }

    public HttpResponse execute(HttpUriRequest request, HttpContext context) throws IOException {
        URI uri = request.getURI();
        return execute(new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme()), (HttpRequest) request, context);
    }

    public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler) throws IOException {
        return execute(request, (ResponseHandler) responseHandler, null);
    }

    public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler, HttpContext context) throws IOException {
        return responseHandler.handleResponse(execute(request, context));
    }

    public HttpResponse execute(HttpHost target, HttpRequest request, HttpContext context) throws IOException {
        int c = 1;
        while (true) {
            HttpResponse response = this.backend.execute(target, request, context);
            try {
                if (!this.retryStrategy.retryRequest(response, c, context)) {
                    return response;
                }
                EntityUtils.consume(response.getEntity());
                long nextInterval = this.retryStrategy.getRetryInterval();
                this.log.trace("Wait for " + nextInterval);
                Thread.sleep(nextInterval);
                c++;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new InterruptedIOException();
            } catch (RuntimeException ex) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException ioex) {
                    this.log.warn("I/O error consuming response content", ioex);
                }
                throw ex;
            }
        }
    }

    public ClientConnectionManager getConnectionManager() {
        return this.backend.getConnectionManager();
    }

    public HttpParams getParams() {
        return this.backend.getParams();
    }
}
