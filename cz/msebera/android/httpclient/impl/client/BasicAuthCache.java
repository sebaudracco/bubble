package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.auth.AuthScheme;
import cz.msebera.android.httpclient.client.AuthCache;
import cz.msebera.android.httpclient.conn.SchemePortResolver;
import cz.msebera.android.httpclient.conn.UnsupportedSchemeException;
import cz.msebera.android.httpclient.impl.conn.DefaultSchemePortResolver;
import cz.msebera.android.httpclient.util.Args;
import java.util.HashMap;

@NotThreadSafe
public class BasicAuthCache implements AuthCache {
    private final HashMap<HttpHost, AuthScheme> map;
    private final SchemePortResolver schemePortResolver;

    public BasicAuthCache(SchemePortResolver schemePortResolver) {
        this.map = new HashMap();
        if (schemePortResolver == null) {
            schemePortResolver = DefaultSchemePortResolver.INSTANCE;
        }
        this.schemePortResolver = schemePortResolver;
    }

    public BasicAuthCache() {
        this(null);
    }

    protected HttpHost getKey(HttpHost host) {
        if (host.getPort() > 0) {
            return host;
        }
        try {
            return new HttpHost(host.getHostName(), this.schemePortResolver.resolve(host), host.getSchemeName());
        } catch (UnsupportedSchemeException e) {
            return host;
        }
    }

    public void put(HttpHost host, AuthScheme authScheme) {
        Args.notNull(host, "HTTP host");
        this.map.put(getKey(host), authScheme);
    }

    public AuthScheme get(HttpHost host) {
        Args.notNull(host, "HTTP host");
        return (AuthScheme) this.map.get(getKey(host));
    }

    public void remove(HttpHost host) {
        Args.notNull(host, "HTTP host");
        this.map.remove(getKey(host));
    }

    public void clear() {
        this.map.clear();
    }

    public String toString() {
        return this.map.toString();
    }
}
