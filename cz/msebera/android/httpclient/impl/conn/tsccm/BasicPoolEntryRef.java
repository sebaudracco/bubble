package cz.msebera.android.httpclient.impl.conn.tsccm;

import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.util.Args;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

@Deprecated
public class BasicPoolEntryRef extends WeakReference<BasicPoolEntry> {
    private final HttpRoute route;

    public BasicPoolEntryRef(BasicPoolEntry entry, ReferenceQueue<Object> queue) {
        super(entry, queue);
        Args.notNull(entry, "Pool entry");
        this.route = entry.getPlannedRoute();
    }

    public final HttpRoute getRoute() {
        return this.route;
    }
}