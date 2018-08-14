package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.ConnectionBackoffStrategy;

public class NullBackoffStrategy implements ConnectionBackoffStrategy {
    public boolean shouldBackoff(Throwable t) {
        return false;
    }

    public boolean shouldBackoff(HttpResponse resp) {
        return false;
    }
}
