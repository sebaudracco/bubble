package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.annotation.ThreadSafe;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@ThreadSafe
public class ExponentialBackOffSchedulingStrategy implements SchedulingStrategy {
    public static final long DEFAULT_BACK_OFF_RATE = 10;
    public static final long DEFAULT_INITIAL_EXPIRY_IN_MILLIS = TimeUnit.SECONDS.toMillis(6);
    public static final long DEFAULT_MAX_EXPIRY_IN_MILLIS = TimeUnit.SECONDS.toMillis(86400);
    private final long backOffRate;
    private final ScheduledExecutorService executor;
    private final long initialExpiryInMillis;
    private final long maxExpiryInMillis;

    public ExponentialBackOffSchedulingStrategy(CacheConfig cacheConfig) {
        this(cacheConfig, 10, DEFAULT_INITIAL_EXPIRY_IN_MILLIS, DEFAULT_MAX_EXPIRY_IN_MILLIS);
    }

    public ExponentialBackOffSchedulingStrategy(CacheConfig cacheConfig, long backOffRate, long initialExpiryInMillis, long maxExpiryInMillis) {
        this(createThreadPoolFromCacheConfig(cacheConfig), backOffRate, initialExpiryInMillis, maxExpiryInMillis);
    }

    private static ScheduledThreadPoolExecutor createThreadPoolFromCacheConfig(CacheConfig cacheConfig) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(cacheConfig.getAsynchronousWorkersMax());
        scheduledThreadPoolExecutor.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
        return scheduledThreadPoolExecutor;
    }

    ExponentialBackOffSchedulingStrategy(ScheduledExecutorService executor, long backOffRate, long initialExpiryInMillis, long maxExpiryInMillis) {
        this.executor = (ScheduledExecutorService) checkNotNull("executor", executor);
        this.backOffRate = checkNotNegative("backOffRate", backOffRate);
        this.initialExpiryInMillis = checkNotNegative("initialExpiryInMillis", initialExpiryInMillis);
        this.maxExpiryInMillis = checkNotNegative("maxExpiryInMillis", maxExpiryInMillis);
    }

    public void schedule(AsynchronousValidationRequest revalidationRequest) {
        checkNotNull("revalidationRequest", revalidationRequest);
        this.executor.schedule(revalidationRequest, calculateDelayInMillis(revalidationRequest.getConsecutiveFailedAttempts()), TimeUnit.MILLISECONDS);
    }

    public void close() {
        this.executor.shutdown();
    }

    public long getBackOffRate() {
        return this.backOffRate;
    }

    public long getInitialExpiryInMillis() {
        return this.initialExpiryInMillis;
    }

    public long getMaxExpiryInMillis() {
        return this.maxExpiryInMillis;
    }

    protected long calculateDelayInMillis(int consecutiveFailedAttempts) {
        if (consecutiveFailedAttempts > 0) {
            return Math.min((long) (((double) this.initialExpiryInMillis) * Math.pow((double) this.backOffRate, (double) (consecutiveFailedAttempts - 1))), this.maxExpiryInMillis);
        }
        return 0;
    }

    protected static <T> T checkNotNull(String parameterName, T value) {
        if (value != null) {
            return value;
        }
        throw new IllegalArgumentException(parameterName + " may not be null");
    }

    protected static long checkNotNegative(String parameterName, long value) {
        if (value >= 0) {
            return value;
        }
        throw new IllegalArgumentException(parameterName + " may not be negative");
    }
}
