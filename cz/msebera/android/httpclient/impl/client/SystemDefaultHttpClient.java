package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.ConnectionReuseStrategy;
import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.conn.ClientConnectionManager;
import cz.msebera.android.httpclient.conn.routing.HttpRoutePlanner;
import cz.msebera.android.httpclient.impl.DefaultConnectionReuseStrategy;
import cz.msebera.android.httpclient.impl.NoConnectionReuseStrategy;
import cz.msebera.android.httpclient.impl.conn.PoolingClientConnectionManager;
import cz.msebera.android.httpclient.impl.conn.ProxySelectorRoutePlanner;
import cz.msebera.android.httpclient.impl.conn.SchemeRegistryFactory;
import cz.msebera.android.httpclient.params.HttpParams;
import java.net.ProxySelector;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

@ThreadSafe
@Deprecated
public class SystemDefaultHttpClient extends DefaultHttpClient {
    public SystemDefaultHttpClient(HttpParams params) {
        super(null, params);
    }

    public SystemDefaultHttpClient() {
        super(null, null);
    }

    protected ClientConnectionManager createClientConnectionManager() {
        PoolingClientConnectionManager connmgr = new PoolingClientConnectionManager(SchemeRegistryFactory.createSystemDefault());
        if (SchemaSymbols.ATTVAL_TRUE.equalsIgnoreCase(System.getProperty("http.keepAlive", SchemaSymbols.ATTVAL_TRUE))) {
            int max = Integer.parseInt(System.getProperty("http.maxConnections", "5"));
            connmgr.setDefaultMaxPerRoute(max);
            connmgr.setMaxTotal(max * 2);
        }
        return connmgr;
    }

    protected HttpRoutePlanner createHttpRoutePlanner() {
        return new ProxySelectorRoutePlanner(getConnectionManager().getSchemeRegistry(), ProxySelector.getDefault());
    }

    protected ConnectionReuseStrategy createConnectionReuseStrategy() {
        if (SchemaSymbols.ATTVAL_TRUE.equalsIgnoreCase(System.getProperty("http.keepAlive", SchemaSymbols.ATTVAL_TRUE))) {
            return new DefaultConnectionReuseStrategy();
        }
        return new NoConnectionReuseStrategy();
    }
}
