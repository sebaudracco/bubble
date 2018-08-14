package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.ResponseHandler;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.concurrent.FutureCallback;
import cz.msebera.android.httpclient.protocol.HttpContext;
import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

@ThreadSafe
public class FutureRequestExecutionService implements Closeable {
    private final AtomicBoolean closed = new AtomicBoolean(false);
    private final ExecutorService executorService;
    private final HttpClient httpclient;
    private final FutureRequestExecutionMetrics metrics = new FutureRequestExecutionMetrics();

    public FutureRequestExecutionService(HttpClient httpclient, ExecutorService executorService) {
        this.httpclient = httpclient;
        this.executorService = executorService;
    }

    public <T> HttpRequestFutureTask<T> execute(HttpUriRequest request, HttpContext context, ResponseHandler<T> responseHandler) {
        return execute(request, context, responseHandler, null);
    }

    public <T> HttpRequestFutureTask<T> execute(HttpUriRequest request, HttpContext context, ResponseHandler<T> responseHandler, FutureCallback<T> callback) {
        if (this.closed.get()) {
            throw new IllegalStateException("Close has been called on this httpclient instance.");
        }
        this.metrics.getScheduledConnections().incrementAndGet();
        HttpRequestFutureTask<T> httpRequestFutureTask = new HttpRequestFutureTask(request, new HttpRequestTaskCallable(this.httpclient, request, context, responseHandler, callback, this.metrics));
        this.executorService.execute(httpRequestFutureTask);
        return httpRequestFutureTask;
    }

    public FutureRequestExecutionMetrics metrics() {
        return this.metrics;
    }

    public void close() throws IOException {
        this.closed.set(true);
        this.executorService.shutdownNow();
        if (this.httpclient instanceof Closeable) {
            ((Closeable) this.httpclient).close();
        }
    }
}
