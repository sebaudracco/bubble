package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.HttpClientConnection;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.annotation.GuardedBy;
import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.config.ConnectionConfig;
import cz.msebera.android.httpclient.config.Lookup;
import cz.msebera.android.httpclient.config.Registry;
import cz.msebera.android.httpclient.config.RegistryBuilder;
import cz.msebera.android.httpclient.config.SocketConfig;
import cz.msebera.android.httpclient.conn.ConnectionRequest;
import cz.msebera.android.httpclient.conn.DnsResolver;
import cz.msebera.android.httpclient.conn.HttpClientConnectionManager;
import cz.msebera.android.httpclient.conn.HttpConnectionFactory;
import cz.msebera.android.httpclient.conn.ManagedHttpClientConnection;
import cz.msebera.android.httpclient.conn.SchemePortResolver;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.conn.socket.ConnectionSocketFactory;
import cz.msebera.android.httpclient.conn.socket.PlainConnectionSocketFactory;
import cz.msebera.android.httpclient.conn.ssl.SSLConnectionSocketFactory;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import cz.msebera.android.httpclient.util.LangUtils;
import java.io.Closeable;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@ThreadSafe
public class BasicHttpClientConnectionManager implements HttpClientConnectionManager, Closeable {
    @GuardedBy("this")
    private ManagedHttpClientConnection conn;
    @GuardedBy("this")
    private ConnectionConfig connConfig;
    private final HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory;
    private final HttpClientConnectionOperator connectionOperator;
    @GuardedBy("this")
    private long expiry;
    private final AtomicBoolean isShutdown;
    @GuardedBy("this")
    private boolean leased;
    public HttpClientAndroidLog log;
    @GuardedBy("this")
    private HttpRoute route;
    @GuardedBy("this")
    private SocketConfig socketConfig;
    @GuardedBy("this")
    private Object state;
    @GuardedBy("this")
    private long updated;

    private static Registry<ConnectionSocketFactory> getDefaultRegistry() {
        return RegistryBuilder.create().register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", SSLConnectionSocketFactory.getSocketFactory()).build();
    }

    public BasicHttpClientConnectionManager(Lookup<ConnectionSocketFactory> socketFactoryRegistry, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory, SchemePortResolver schemePortResolver, DnsResolver dnsResolver) {
        this.log = new HttpClientAndroidLog(getClass());
        this.connectionOperator = new HttpClientConnectionOperator(socketFactoryRegistry, schemePortResolver, dnsResolver);
        if (connFactory == null) {
            connFactory = ManagedHttpClientConnectionFactory.INSTANCE;
        }
        this.connFactory = connFactory;
        this.expiry = Long.MAX_VALUE;
        this.socketConfig = SocketConfig.DEFAULT;
        this.connConfig = ConnectionConfig.DEFAULT;
        this.isShutdown = new AtomicBoolean(false);
    }

    public BasicHttpClientConnectionManager(Lookup<ConnectionSocketFactory> socketFactoryRegistry, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory) {
        this(socketFactoryRegistry, connFactory, null, null);
    }

    public BasicHttpClientConnectionManager(Lookup<ConnectionSocketFactory> socketFactoryRegistry) {
        this(socketFactoryRegistry, null, null, null);
    }

    public BasicHttpClientConnectionManager() {
        this(getDefaultRegistry(), null, null, null);
    }

    protected void finalize() throws Throwable {
        try {
            shutdown();
        } finally {
            super.finalize();
        }
    }

    public void close() {
        shutdown();
    }

    HttpRoute getRoute() {
        return this.route;
    }

    Object getState() {
        return this.state;
    }

    public synchronized SocketConfig getSocketConfig() {
        return this.socketConfig;
    }

    public synchronized void setSocketConfig(SocketConfig socketConfig) {
        if (socketConfig == null) {
            socketConfig = SocketConfig.DEFAULT;
        }
        this.socketConfig = socketConfig;
    }

    public synchronized ConnectionConfig getConnectionConfig() {
        return this.connConfig;
    }

    public synchronized void setConnectionConfig(ConnectionConfig connConfig) {
        if (connConfig == null) {
            connConfig = ConnectionConfig.DEFAULT;
        }
        this.connConfig = connConfig;
    }

    public final ConnectionRequest requestConnection(final HttpRoute route, final Object state) {
        Args.notNull(route, "Route");
        return new ConnectionRequest() {
            public boolean cancel() {
                return false;
            }

            public HttpClientConnection get(long timeout, TimeUnit tunit) {
                return BasicHttpClientConnectionManager.this.getConnection(route, state);
            }
        };
    }

    private void closeConnection() {
        if (this.conn != null) {
            this.log.debug("Closing connection");
            try {
                this.conn.close();
            } catch (IOException iox) {
                if (this.log.isDebugEnabled()) {
                    this.log.debug("I/O exception closing connection", iox);
                }
            }
            this.conn = null;
        }
    }

    private void shutdownConnection() {
        if (this.conn != null) {
            this.log.debug("Shutting down connection");
            try {
                this.conn.shutdown();
            } catch (IOException iox) {
                if (this.log.isDebugEnabled()) {
                    this.log.debug("I/O exception shutting down connection", iox);
                }
            }
            this.conn = null;
        }
    }

    private void checkExpiry() {
        if (this.conn != null && System.currentTimeMillis() >= this.expiry) {
            if (this.log.isDebugEnabled()) {
                this.log.debug("Connection expired @ " + new Date(this.expiry));
            }
            closeConnection();
        }
    }

    synchronized HttpClientConnection getConnection(HttpRoute route, Object state) {
        HttpClientConnection httpClientConnection;
        boolean z = true;
        synchronized (this) {
            Asserts.check(!this.isShutdown.get(), "Connection manager has been shut down");
            if (this.log.isDebugEnabled()) {
                this.log.debug("Get connection for route " + route);
            }
            if (this.leased) {
                z = false;
            }
            Asserts.check(z, "Connection is still allocated");
            if (!(LangUtils.equals(this.route, (Object) route) && LangUtils.equals(this.state, state))) {
                closeConnection();
            }
            this.route = route;
            this.state = state;
            checkExpiry();
            if (this.conn == null) {
                this.conn = (ManagedHttpClientConnection) this.connFactory.create(route, this.connConfig);
            }
            this.leased = true;
            httpClientConnection = this.conn;
        }
        return httpClientConnection;
    }

    public synchronized void releaseConnection(HttpClientConnection conn, Object state, long keepalive, TimeUnit tunit) {
        boolean z = false;
        synchronized (this) {
            Args.notNull(conn, "Connection");
            if (conn == this.conn) {
                z = true;
            }
            Asserts.check(z, "Connection not obtained from this manager");
            if (this.log.isDebugEnabled()) {
                this.log.debug("Releasing connection " + conn);
            }
            if (!this.isShutdown.get()) {
                try {
                    this.updated = System.currentTimeMillis();
                    ManagedHttpClientConnection isOpen = this.conn.isOpen();
                    if (isOpen == null) {
                        this.conn = isOpen;
                        this.route = null;
                        this.conn = null;
                        this.expiry = Long.MAX_VALUE;
                        this.leased = false;
                    } else {
                        this.state = state;
                        if (this.log.isDebugEnabled()) {
                            String s;
                            if (keepalive > 0) {
                                s = "for " + keepalive + " " + tunit;
                            } else {
                                s = "indefinitely";
                            }
                            this.log.debug("Connection can be kept alive " + s);
                        }
                        if (keepalive > 0) {
                            this.expiry = this.updated + tunit.toMillis(keepalive);
                        } else {
                            this.expiry = Long.MAX_VALUE;
                        }
                        this.leased = false;
                    }
                } finally {
                    this.leased = false;
                }
            }
        }
    }

    public void connect(HttpClientConnection conn, HttpRoute route, int connectTimeout, HttpContext context) throws IOException {
        HttpHost host;
        Args.notNull(conn, "Connection");
        Args.notNull(route, "HTTP route");
        Asserts.check(conn == this.conn, "Connection not obtained from this manager");
        if (route.getProxyHost() != null) {
            host = route.getProxyHost();
        } else {
            host = route.getTargetHost();
        }
        this.connectionOperator.connect(this.conn, host, route.getLocalSocketAddress(), connectTimeout, this.socketConfig, context);
    }

    public void upgrade(HttpClientConnection conn, HttpRoute route, HttpContext context) throws IOException {
        Args.notNull(conn, "Connection");
        Args.notNull(route, "HTTP route");
        Asserts.check(conn == this.conn, "Connection not obtained from this manager");
        this.connectionOperator.upgrade(this.conn, route.getTargetHost(), context);
    }

    public void routeComplete(HttpClientConnection conn, HttpRoute route, HttpContext context) throws IOException {
    }

    public synchronized void closeExpiredConnections() {
        if (!this.isShutdown.get()) {
            if (!this.leased) {
                checkExpiry();
            }
        }
    }

    public synchronized void closeIdleConnections(long idletime, TimeUnit tunit) {
        Args.notNull(tunit, "Time unit");
        if (!this.isShutdown.get()) {
            if (!this.leased) {
                long time = tunit.toMillis(idletime);
                if (time < 0) {
                    time = 0;
                }
                if (this.updated <= System.currentTimeMillis() - time) {
                    closeConnection();
                }
            }
        }
    }

    public synchronized void shutdown() {
        if (this.isShutdown.compareAndSet(false, true)) {
            shutdownConnection();
        }
    }
}
