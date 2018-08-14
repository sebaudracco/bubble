package cz.msebera.android.httpclient.impl.conn.tsccm;

import cz.msebera.android.httpclient.conn.params.ConnPerRoute;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;

class RouteSpecificPool$1 implements ConnPerRoute {
    final /* synthetic */ RouteSpecificPool this$0;

    RouteSpecificPool$1(RouteSpecificPool this$0) {
        this.this$0 = this$0;
    }

    public int getMaxForRoute(HttpRoute route) {
        return this.this$0.maxEntries;
    }
}
