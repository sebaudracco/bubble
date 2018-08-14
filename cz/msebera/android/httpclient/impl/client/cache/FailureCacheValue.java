package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.annotation.Immutable;

@Immutable
public class FailureCacheValue {
    private final long creationTimeInNanos = System.nanoTime();
    private final int errorCount;
    private final String key;

    public FailureCacheValue(String key, int errorCount) {
        this.key = key;
        this.errorCount = errorCount;
    }

    public long getCreationTimeInNanos() {
        return this.creationTimeInNanos;
    }

    public String getKey() {
        return this.key;
    }

    public int getErrorCount() {
        return this.errorCount;
    }

    public String toString() {
        return "[entry creationTimeInNanos=" + this.creationTimeInNanos + "; " + "key=" + this.key + "; errorCount=" + this.errorCount + ']';
    }
}
