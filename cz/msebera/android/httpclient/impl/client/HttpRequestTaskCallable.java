package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.ResponseHandler;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.concurrent.FutureCallback;
import cz.msebera.android.httpclient.protocol.HttpContext;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

class HttpRequestTaskCallable<V> implements Callable<V> {
    private final FutureCallback<V> callback;
    private final AtomicBoolean cancelled = new AtomicBoolean(false);
    private final HttpContext context;
    private long ended = -1;
    private final HttpClient httpclient;
    private final FutureRequestExecutionMetrics metrics;
    private final HttpUriRequest request;
    private final ResponseHandler<V> responseHandler;
    private final long scheduled = System.currentTimeMillis();
    private long started = -1;

    HttpRequestTaskCallable(HttpClient httpClient, HttpUriRequest request, HttpContext context, ResponseHandler<V> responseHandler, FutureCallback<V> callback, FutureRequestExecutionMetrics metrics) {
        this.httpclient = httpClient;
        this.responseHandler = responseHandler;
        this.request = request;
        this.context = context;
        this.callback = callback;
        this.metrics = metrics;
    }

    public long getScheduled() {
        return this.scheduled;
    }

    public long getStarted() {
        return this.started;
    }

    public long getEnded() {
        return this.ended;
    }

    public V call() throws Exception {
        if (this.cancelled.get()) {
            throw new IllegalStateException("call has been cancelled for request " + this.request.getURI());
        }
        try {
            this.metrics.getActiveConnections().incrementAndGet();
            this.started = System.currentTimeMillis();
            this.metrics.getScheduledConnections().decrementAndGet();
            V result = this.httpclient.execute(this.request, this.responseHandler, this.context);
            this.ended = System.currentTimeMillis();
            this.metrics.getSuccessfulConnections().increment(this.started);
            if (this.callback != null) {
                this.callback.completed(result);
            }
            this.metrics.getRequests().increment(this.started);
            this.metrics.getTasks().increment(this.started);
            this.metrics.getActiveConnections().decrementAndGet();
            return result;
        } catch (Exception e) {
            this.metrics.getFailedConnections().increment(this.started);
            this.ended = System.currentTimeMillis();
            if (this.callback != null) {
                this.callback.failed(e);
            }
            throw e;
        } catch (Throwable th) {
            this.metrics.getRequests().increment(this.started);
            this.metrics.getTasks().increment(this.started);
            this.metrics.getActiveConnections().decrementAndGet();
        }
    }

    public void cancel() {
        this.cancelled.set(true);
        if (this.callback != null) {
            this.callback.cancelled();
        }
    }
}
