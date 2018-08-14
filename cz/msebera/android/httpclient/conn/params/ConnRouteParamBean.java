package cz.msebera.android.httpclient.conn.params;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.params.HttpAbstractParamBean;
import cz.msebera.android.httpclient.params.HttpParams;
import java.net.InetAddress;

@NotThreadSafe
@Deprecated
public class ConnRouteParamBean extends HttpAbstractParamBean {
    public ConnRouteParamBean(HttpParams params) {
        super(params);
    }

    public void setDefaultProxy(HttpHost defaultProxy) {
        this.params.setParameter(ConnRoutePNames.DEFAULT_PROXY, defaultProxy);
    }

    public void setLocalAddress(InetAddress address) {
        this.params.setParameter(ConnRoutePNames.LOCAL_ADDRESS, address);
    }

    public void setForcedRoute(HttpRoute route) {
        this.params.setParameter(ConnRoutePNames.FORCED_ROUTE, route);
    }
}
