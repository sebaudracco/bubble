package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpExecutionAware;
import cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import java.io.IOException;

class AsynchronousValidationRequest implements Runnable {
    private final HttpCacheEntry cacheEntry;
    private final CachingExec cachingExec;
    private final int consecutiveFailedAttempts;
    private final HttpClientContext context;
    private final HttpExecutionAware execAware;
    private final String identifier;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());
    private final AsynchronousValidator parent;
    private final HttpRequestWrapper request;
    private final HttpRoute route;

    AsynchronousValidationRequest(AsynchronousValidator parent, CachingExec cachingExec, HttpRoute route, HttpRequestWrapper request, HttpClientContext context, HttpExecutionAware execAware, HttpCacheEntry cacheEntry, String identifier, int consecutiveFailedAttempts) {
        this.parent = parent;
        this.cachingExec = cachingExec;
        this.route = route;
        this.request = request;
        this.context = context;
        this.execAware = execAware;
        this.cacheEntry = cacheEntry;
        this.identifier = identifier;
        this.consecutiveFailedAttempts = consecutiveFailedAttempts;
    }

    public void run() {
        try {
            if (revalidateCacheEntry()) {
                this.parent.jobSuccessful(this.identifier);
            } else {
                this.parent.jobFailed(this.identifier);
            }
            this.parent.markComplete(this.identifier);
        } catch (Throwable th) {
            this.parent.markComplete(this.identifier);
        }
    }

    protected boolean revalidateCacheEntry() {
        CloseableHttpResponse httpResponse;
        try {
            boolean z;
            httpResponse = this.cachingExec.revalidateCacheEntry(this.route, this.request, this.context, this.execAware, this.cacheEntry);
            if (isNotServerError(httpResponse.getStatusLine().getStatusCode()) && isNotStale(httpResponse)) {
                z = true;
            } else {
                z = false;
            }
            httpResponse.close();
            return z;
        } catch (IOException ioe) {
            this.log.debug("Asynchronous revalidation failed due to I/O error", ioe);
            return false;
        } catch (HttpException pe) {
            this.log.error("HTTP protocol exception during asynchronous revalidation", pe);
            return false;
        } catch (RuntimeException re) {
            this.log.error("RuntimeException thrown during asynchronous revalidation: " + re);
            return false;
        } catch (Throwable th) {
            httpResponse.close();
        }
    }

    private boolean isNotServerError(int statusCode) {
        return statusCode < HttpStatus.SC_INTERNAL_SERVER_ERROR;
    }

    private boolean isNotStale(HttpResponse httpResponse) {
        Header[] warnings = httpResponse.getHeaders("Warning");
        if (warnings != null) {
            for (Header warning : warnings) {
                String warningValue = warning.getValue();
                if (warningValue.startsWith("110") || warningValue.startsWith("111")) {
                    return false;
                }
            }
        }
        return true;
    }

    String getIdentifier() {
        return this.identifier;
    }

    public int getConsecutiveFailedAttempts() {
        return this.consecutiveFailedAttempts;
    }
}
