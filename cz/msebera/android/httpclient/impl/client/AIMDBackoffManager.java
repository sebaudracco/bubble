package cz.msebera.android.httpclient.impl.client;

import com.mopub.mobileads.dfp.adapters.MoPubAdapter;
import cz.msebera.android.httpclient.client.BackoffManager;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.pool.ConnPoolControl;
import cz.msebera.android.httpclient.util.Args;
import java.util.HashMap;
import java.util.Map;

public class AIMDBackoffManager implements BackoffManager {
    private double backoffFactor;
    private int cap;
    private final Clock clock;
    private final ConnPoolControl<HttpRoute> connPerRoute;
    private long coolDown;
    private final Map<HttpRoute, Long> lastRouteBackoffs;
    private final Map<HttpRoute, Long> lastRouteProbes;

    public AIMDBackoffManager(ConnPoolControl<HttpRoute> connPerRoute) {
        this(connPerRoute, new SystemClock());
    }

    AIMDBackoffManager(ConnPoolControl<HttpRoute> connPerRoute, Clock clock) {
        this.coolDown = 5000;
        this.backoffFactor = 0.5d;
        this.cap = 2;
        this.clock = clock;
        this.connPerRoute = connPerRoute;
        this.lastRouteProbes = new HashMap();
        this.lastRouteBackoffs = new HashMap();
    }

    public void backOff(HttpRoute route) {
        synchronized (this.connPerRoute) {
            int curr = this.connPerRoute.getMaxPerRoute(route);
            Long lastUpdate = getLastUpdate(this.lastRouteBackoffs, route);
            long now = this.clock.getCurrentTime();
            if (now - lastUpdate.longValue() < this.coolDown) {
                return;
            }
            this.connPerRoute.setMaxPerRoute(route, getBackedOffPoolSize(curr));
            this.lastRouteBackoffs.put(route, Long.valueOf(now));
        }
    }

    private int getBackedOffPoolSize(int curr) {
        if (curr <= 1) {
            return 1;
        }
        return (int) Math.floor(this.backoffFactor * ((double) curr));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void probe(cz.msebera.android.httpclient.conn.routing.HttpRoute r13) {
        /*
        r12 = this;
        r7 = r12.connPerRoute;
        monitor-enter(r7);
        r6 = r12.connPerRoute;	 Catch:{ all -> 0x004e }
        r0 = r6.getMaxPerRoute(r13);	 Catch:{ all -> 0x004e }
        r6 = r12.cap;	 Catch:{ all -> 0x004e }
        if (r0 < r6) goto L_0x003b;
    L_0x000d:
        r3 = r12.cap;	 Catch:{ all -> 0x004e }
    L_0x000f:
        r6 = r12.lastRouteProbes;	 Catch:{ all -> 0x004e }
        r2 = r12.getLastUpdate(r6, r13);	 Catch:{ all -> 0x004e }
        r6 = r12.lastRouteBackoffs;	 Catch:{ all -> 0x004e }
        r1 = r12.getLastUpdate(r6, r13);	 Catch:{ all -> 0x004e }
        r6 = r12.clock;	 Catch:{ all -> 0x004e }
        r4 = r6.getCurrentTime();	 Catch:{ all -> 0x004e }
        r8 = r2.longValue();	 Catch:{ all -> 0x004e }
        r8 = r4 - r8;
        r10 = r12.coolDown;	 Catch:{ all -> 0x004e }
        r6 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r6 < 0) goto L_0x0039;
    L_0x002d:
        r8 = r1.longValue();	 Catch:{ all -> 0x004e }
        r8 = r4 - r8;
        r10 = r12.coolDown;	 Catch:{ all -> 0x004e }
        r6 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r6 >= 0) goto L_0x003e;
    L_0x0039:
        monitor-exit(r7);	 Catch:{ all -> 0x004e }
    L_0x003a:
        return;
    L_0x003b:
        r3 = r0 + 1;
        goto L_0x000f;
    L_0x003e:
        r6 = r12.connPerRoute;	 Catch:{ all -> 0x004e }
        r6.setMaxPerRoute(r13, r3);	 Catch:{ all -> 0x004e }
        r6 = r12.lastRouteProbes;	 Catch:{ all -> 0x004e }
        r8 = java.lang.Long.valueOf(r4);	 Catch:{ all -> 0x004e }
        r6.put(r13, r8);	 Catch:{ all -> 0x004e }
        monitor-exit(r7);	 Catch:{ all -> 0x004e }
        goto L_0x003a;
    L_0x004e:
        r6 = move-exception;
        monitor-exit(r7);	 Catch:{ all -> 0x004e }
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.httpclient.impl.client.AIMDBackoffManager.probe(cz.msebera.android.httpclient.conn.routing.HttpRoute):void");
    }

    private Long getLastUpdate(Map<HttpRoute, Long> updates, HttpRoute route) {
        Long lastUpdate = (Long) updates.get(route);
        if (lastUpdate == null) {
            return Long.valueOf(0);
        }
        return lastUpdate;
    }

    public void setBackoffFactor(double d) {
        boolean z = d > 0.0d && d < MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE;
        Args.check(z, "Backoff factor must be 0.0 < f < 1.0");
        this.backoffFactor = d;
    }

    public void setCooldownMillis(long l) {
        Args.positive(this.coolDown, "Cool down");
        this.coolDown = l;
    }

    public void setPerHostConnectionCap(int cap) {
        Args.positive(cap, "Per host connection cap");
        this.cap = cap;
    }
}
