package com.danikula.videocache;

import java.lang.Thread.State;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ProxyCache {
    private static final Logger LOG = LoggerFactory.getLogger("ProxyCache");
    private static final int MAX_READ_SOURCE_ATTEMPTS = 1;
    private final Cache cache;
    private volatile int percentsAvailable = -1;
    private final AtomicInteger readSourceErrorsCount;
    private final Source source;
    private volatile Thread sourceReaderThread;
    private final Object stopLock = new Object();
    private volatile boolean stopped;
    private final Object wc = new Object();

    private class SourceReaderRunnable implements Runnable {
        private SourceReaderRunnable() {
        }

        public void run() {
            ProxyCache.this.readSource();
        }
    }

    public ProxyCache(Source source, Cache cache) {
        this.source = (Source) Preconditions.checkNotNull(source);
        this.cache = (Cache) Preconditions.checkNotNull(cache);
        this.readSourceErrorsCount = new AtomicInteger();
    }

    public int read(byte[] buffer, long offset, int length) throws ProxyCacheException {
        ProxyCacheUtils.assertBuffer(buffer, offset, length);
        while (!this.cache.isCompleted() && this.cache.available() < ((long) length) + offset && !this.stopped) {
            readSourceAsync();
            waitForSourceData();
            checkReadSourceErrorsCount();
        }
        int read = this.cache.read(buffer, offset, length);
        if (this.cache.isCompleted() && this.percentsAvailable != 100) {
            this.percentsAvailable = 100;
            onCachePercentsAvailableChanged(100);
        }
        return read;
    }

    private void checkReadSourceErrorsCount() throws ProxyCacheException {
        int errorsCount = this.readSourceErrorsCount.get();
        if (errorsCount >= 1) {
            this.readSourceErrorsCount.set(0);
            throw new ProxyCacheException("Error reading source " + errorsCount + " times");
        }
    }

    public void shutdown() {
        synchronized (this.stopLock) {
            LOG.debug("Shutdown proxy for " + this.source);
            try {
                this.stopped = true;
                if (this.sourceReaderThread != null) {
                    this.sourceReaderThread.interrupt();
                }
                this.cache.close();
            } catch (ProxyCacheException e) {
                onError(e);
            }
        }
    }

    private synchronized void readSourceAsync() throws ProxyCacheException {
        boolean readingInProgress = (this.sourceReaderThread == null || this.sourceReaderThread.getState() == State.TERMINATED) ? false : true;
        if (!(this.stopped || this.cache.isCompleted() || readingInProgress)) {
            this.sourceReaderThread = new Thread(new SourceReaderRunnable(), "Source reader for " + this.source);
            this.sourceReaderThread.start();
        }
    }

    private void waitForSourceData() throws ProxyCacheException {
        synchronized (this.wc) {
            try {
                this.wc.wait(1000);
            } catch (InterruptedException e) {
                throw new ProxyCacheException("Waiting source data is interrupted!", e);
            }
        }
    }

    private void notifyNewCacheDataAvailable(long cacheAvailable, long sourceAvailable) {
        onCacheAvailable(cacheAvailable, sourceAvailable);
        synchronized (this.wc) {
            this.wc.notifyAll();
        }
    }

    protected void onCacheAvailable(long cacheAvailable, long sourceLength) {
        boolean zeroLengthSource;
        boolean sourceLengthKnown = true;
        if (sourceLength == 0) {
            zeroLengthSource = true;
        } else {
            zeroLengthSource = false;
        }
        int percents = zeroLengthSource ? 100 : (int) ((((float) cacheAvailable) / ((float) sourceLength)) * 100.0f);
        boolean percentsChanged;
        if (percents != this.percentsAvailable) {
            percentsChanged = true;
        } else {
            percentsChanged = false;
        }
        if (sourceLength < 0) {
            sourceLengthKnown = false;
        }
        if (sourceLengthKnown && percentsChanged) {
            onCachePercentsAvailableChanged(percents);
        }
        this.percentsAvailable = percents;
    }

    protected void onCachePercentsAvailableChanged(int percentsAvailable) {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void readSource() {
        /*
        r10 = this;
        r6 = -1;
        r2 = 0;
        r5 = r10.cache;	 Catch:{ Throwable -> 0x003f }
        r2 = r5.available();	 Catch:{ Throwable -> 0x003f }
        r5 = r10.source;	 Catch:{ Throwable -> 0x003f }
        r5.open(r2);	 Catch:{ Throwable -> 0x003f }
        r5 = r10.source;	 Catch:{ Throwable -> 0x003f }
        r6 = r5.length();	 Catch:{ Throwable -> 0x003f }
        r5 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r0 = new byte[r5];	 Catch:{ Throwable -> 0x003f }
    L_0x0019:
        r5 = r10.source;	 Catch:{ Throwable -> 0x003f }
        r4 = r5.read(r0);	 Catch:{ Throwable -> 0x003f }
        r5 = -1;
        if (r4 == r5) goto L_0x005a;
    L_0x0022:
        r8 = r10.stopLock;	 Catch:{ Throwable -> 0x003f }
        monitor-enter(r8);	 Catch:{ Throwable -> 0x003f }
        r5 = r10.isStopped();	 Catch:{ all -> 0x004f }
        if (r5 == 0) goto L_0x0033;
    L_0x002b:
        monitor-exit(r8);	 Catch:{ all -> 0x004f }
        r10.closeSource();
        r10.notifyNewCacheDataAvailable(r2, r6);
    L_0x0032:
        return;
    L_0x0033:
        r5 = r10.cache;	 Catch:{ all -> 0x004f }
        r5.append(r0, r4);	 Catch:{ all -> 0x004f }
        monitor-exit(r8);	 Catch:{ all -> 0x004f }
        r8 = (long) r4;
        r2 = r2 + r8;
        r10.notifyNewCacheDataAvailable(r2, r6);	 Catch:{ Throwable -> 0x003f }
        goto L_0x0019;
    L_0x003f:
        r1 = move-exception;
        r5 = r10.readSourceErrorsCount;	 Catch:{ all -> 0x0052 }
        r5.incrementAndGet();	 Catch:{ all -> 0x0052 }
        r10.onError(r1);	 Catch:{ all -> 0x0052 }
        r10.closeSource();
        r10.notifyNewCacheDataAvailable(r2, r6);
        goto L_0x0032;
    L_0x004f:
        r5 = move-exception;
        monitor-exit(r8);	 Catch:{ all -> 0x004f }
        throw r5;	 Catch:{ Throwable -> 0x003f }
    L_0x0052:
        r5 = move-exception;
        r10.closeSource();
        r10.notifyNewCacheDataAvailable(r2, r6);
        throw r5;
    L_0x005a:
        r10.tryComplete();	 Catch:{ Throwable -> 0x003f }
        r10.onSourceRead();	 Catch:{ Throwable -> 0x003f }
        r10.closeSource();
        r10.notifyNewCacheDataAvailable(r2, r6);
        goto L_0x0032;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.danikula.videocache.ProxyCache.readSource():void");
    }

    private void onSourceRead() {
        this.percentsAvailable = 100;
        onCachePercentsAvailableChanged(this.percentsAvailable);
    }

    private void tryComplete() throws ProxyCacheException {
        synchronized (this.stopLock) {
            if (!isStopped() && this.cache.available() == this.source.length()) {
                this.cache.complete();
            }
        }
    }

    private boolean isStopped() {
        return Thread.currentThread().isInterrupted() || this.stopped;
    }

    private void closeSource() {
        try {
            this.source.close();
        } catch (ProxyCacheException e) {
            onError(new ProxyCacheException("Error closing source " + this.source, e));
        }
    }

    protected final void onError(Throwable e) {
        if (e instanceof InterruptedProxyCacheException) {
            LOG.debug("ProxyCache is interrupted");
        } else {
            LOG.error("ProxyCache error", e);
        }
    }
}
