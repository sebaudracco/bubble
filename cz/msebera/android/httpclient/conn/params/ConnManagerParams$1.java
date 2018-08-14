package cz.msebera.android.httpclient.conn.params;

import cz.msebera.android.httpclient.conn.routing.HttpRoute;

class ConnManagerParams$1 implements ConnPerRoute {
    ConnManagerParams$1() {
    }

    public int getMaxForRoute(HttpRoute route) {
        return 2;
    }
}
