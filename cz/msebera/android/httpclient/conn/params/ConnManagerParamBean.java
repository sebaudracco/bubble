package cz.msebera.android.httpclient.conn.params;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.params.HttpAbstractParamBean;
import cz.msebera.android.httpclient.params.HttpParams;

@NotThreadSafe
@Deprecated
public class ConnManagerParamBean extends HttpAbstractParamBean {
    public ConnManagerParamBean(HttpParams params) {
        super(params);
    }

    public void setTimeout(long timeout) {
        this.params.setLongParameter("http.conn-manager.timeout", timeout);
    }

    public void setMaxTotalConnections(int maxConnections) {
        this.params.setIntParameter(ConnManagerPNames.MAX_TOTAL_CONNECTIONS, maxConnections);
    }

    public void setConnectionsPerRoute(ConnPerRouteBean connPerRoute) {
        this.params.setParameter(ConnManagerPNames.MAX_CONNECTIONS_PER_ROUTE, connPerRoute);
    }
}
