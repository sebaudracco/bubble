package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.client.ConnectionBackoffStrategy;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

public class DefaultBackoffStrategy implements ConnectionBackoffStrategy {
    public boolean shouldBackoff(Throwable t) {
        return (t instanceof SocketTimeoutException) || (t instanceof ConnectException);
    }

    public boolean shouldBackoff(HttpResponse resp) {
        return resp.getStatusLine().getStatusCode() == HttpStatus.SC_SERVICE_UNAVAILABLE;
    }
}
