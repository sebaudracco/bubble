package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.HttpClientConnection;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.config.ConnectionConfig;
import cz.msebera.android.httpclient.config.Lookup;
import cz.msebera.android.httpclient.config.Registry;
import cz.msebera.android.httpclient.config.RegistryBuilder;
import cz.msebera.android.httpclient.config.SocketConfig;
import cz.msebera.android.httpclient.conn.ConnectionPoolTimeoutException;
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
import cz.msebera.android.httpclient.pool.ConnFactory;
import cz.msebera.android.httpclient.pool.ConnPoolControl;
import cz.msebera.android.httpclient.pool.PoolStats;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

@ThreadSafe
public class PoolingHttpClientConnectionManager implements HttpClientConnectionManager, ConnPoolControl<HttpRoute>, Closeable {
    private final ConfigData configData;
    private final HttpClientConnectionOperator connectionOperator;
    private final AtomicBoolean isShutDown;
    public HttpClientAndroidLog log;
    private final CPool pool;

    static class ConfigData {
        private final Map<HttpHost, ConnectionConfig> connectionConfigMap = new ConcurrentHashMap();
        private volatile ConnectionConfig defaultConnectionConfig;
        private volatile SocketConfig defaultSocketConfig;
        private final Map<HttpHost, SocketConfig> socketConfigMap = new ConcurrentHashMap();

        ConfigData() {
        }

        public SocketConfig getDefaultSocketConfig() {
            return this.defaultSocketConfig;
        }

        public void setDefaultSocketConfig(SocketConfig defaultSocketConfig) {
            this.defaultSocketConfig = defaultSocketConfig;
        }

        public ConnectionConfig getDefaultConnectionConfig() {
            return this.defaultConnectionConfig;
        }

        public void setDefaultConnectionConfig(ConnectionConfig defaultConnectionConfig) {
            this.defaultConnectionConfig = defaultConnectionConfig;
        }

        public SocketConfig getSocketConfig(HttpHost host) {
            return (SocketConfig) this.socketConfigMap.get(host);
        }

        public void setSocketConfig(HttpHost host, SocketConfig socketConfig) {
            this.socketConfigMap.put(host, socketConfig);
        }

        public ConnectionConfig getConnectionConfig(HttpHost host) {
            return (ConnectionConfig) this.connectionConfigMap.get(host);
        }

        public void setConnectionConfig(HttpHost host, ConnectionConfig connectionConfig) {
            this.connectionConfigMap.put(host, connectionConfig);
        }
    }

    static class InternalConnectionFactory implements ConnFactory<HttpRoute, ManagedHttpClientConnection> {
        private final ConfigData configData;
        private final HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory;

        InternalConnectionFactory(ConfigData configData, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory) {
            if (configData == null) {
                configData = new ConfigData();
            }
            this.configData = configData;
            if (connFactory == null) {
                connFactory = ManagedHttpClientConnectionFactory.INSTANCE;
            }
            this.connFactory = connFactory;
        }

        public ManagedHttpClientConnection create(HttpRoute route) throws IOException {
            ConnectionConfig config = null;
            if (route.getProxyHost() != null) {
                config = this.configData.getConnectionConfig(route.getProxyHost());
            }
            if (config == null) {
                config = this.configData.getConnectionConfig(route.getTargetHost());
            }
            if (config == null) {
                config = this.configData.getDefaultConnectionConfig();
            }
            if (config == null) {
                config = ConnectionConfig.DEFAULT;
            }
            return (ManagedHttpClientConnection) this.connFactory.create(route, config);
        }
    }

    private static Registry<ConnectionSocketFactory> getDefaultRegistry() {
        return RegistryBuilder.create().register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", SSLConnectionSocketFactory.getSocketFactory()).build();
    }

    public PoolingHttpClientConnectionManager() {
        this(getDefaultRegistry());
    }

    public PoolingHttpClientConnectionManager(long timeToLive, TimeUnit tunit) {
        this(getDefaultRegistry(), null, null, null, timeToLive, tunit);
    }

    public PoolingHttpClientConnectionManager(Registry<ConnectionSocketFactory> socketFactoryRegistry) {
        this(socketFactoryRegistry, null, null);
    }

    public PoolingHttpClientConnectionManager(Registry<ConnectionSocketFactory> socketFactoryRegistry, DnsResolver dnsResolver) {
        this(socketFactoryRegistry, null, dnsResolver);
    }

    public PoolingHttpClientConnectionManager(Registry<ConnectionSocketFactory> socketFactoryRegistry, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory) {
        this(socketFactoryRegistry, connFactory, null);
    }

    public PoolingHttpClientConnectionManager(HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory) {
        this(getDefaultRegistry(), connFactory, null);
    }

    public PoolingHttpClientConnectionManager(Registry<ConnectionSocketFactory> socketFactoryRegistry, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory, DnsResolver dnsResolver) {
        this(socketFactoryRegistry, connFactory, null, dnsResolver, -1, TimeUnit.MILLISECONDS);
    }

    public PoolingHttpClientConnectionManager(Registry<ConnectionSocketFactory> socketFactoryRegistry, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory, SchemePortResolver schemePortResolver, DnsResolver dnsResolver, long timeToLive, TimeUnit tunit) {
        this.log = new HttpClientAndroidLog(getClass());
        this.configData = new ConfigData();
        this.pool = new CPool(new InternalConnectionFactory(this.configData, connFactory), 2, 20, timeToLive, tunit);
        this.connectionOperator = new HttpClientConnectionOperator(socketFactoryRegistry, schemePortResolver, dnsResolver);
        this.isShutDown = new AtomicBoolean(false);
    }

    PoolingHttpClientConnectionManager(CPool pool, Lookup<ConnectionSocketFactory> socketFactoryRegistry, SchemePortResolver schemePortResolver, DnsResolver dnsResolver) {
        this.log = new HttpClientAndroidLog(getClass());
        this.configData = new ConfigData();
        this.pool = pool;
        this.connectionOperator = new HttpClientConnectionOperator(socketFactoryRegistry, schemePortResolver, dnsResolver);
        this.isShutDown = new AtomicBoolean(false);
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

    private String format(HttpRoute route, Object state) {
        StringBuilder buf = new StringBuilder();
        buf.append("[route: ").append(route).append("]");
        if (state != null) {
            buf.append("[state: ").append(state).append("]");
        }
        return buf.toString();
    }

    private String formatStats(HttpRoute route) {
        StringBuilder buf = new StringBuilder();
        PoolStats totals = this.pool.getTotalStats();
        PoolStats stats = this.pool.getStats(route);
        buf.append("[total kept alive: ").append(totals.getAvailable()).append("; ");
        buf.append("route allocated: ").append(stats.getLeased() + stats.getAvailable());
        buf.append(" of ").append(stats.getMax()).append("; ");
        buf.append("total allocated: ").append(totals.getLeased() + totals.getAvailable());
        buf.append(" of ").append(totals.getMax()).append("]");
        return buf.toString();
    }

    private String format(CPoolEntry entry) {
        StringBuilder buf = new StringBuilder();
        buf.append("[id: ").append(entry.getId()).append("]");
        buf.append("[route: ").append(entry.getRoute()).append("]");
        Object state = entry.getState();
        if (state != null) {
            buf.append("[state: ").append(state).append("]");
        }
        return buf.toString();
    }

    public ConnectionRequest requestConnection(HttpRoute route, Object state) {
        Args.notNull(route, "HTTP route");
        if (this.log.isDebugEnabled()) {
            this.log.debug("Connection request: " + format(route, state) + formatStats(route));
        }
        final Future<CPoolEntry> future = this.pool.lease(route, state, null);
        return new ConnectionRequest() {
            public boolean cancel() {
                return future.cancel(true);
            }

            public HttpClientConnection get(long timeout, TimeUnit tunit) throws InterruptedException, ExecutionException, ConnectionPoolTimeoutException {
                return PoolingHttpClientConnectionManager.this.leaseConnection(future, timeout, tunit);
            }
        };
    }

    protected HttpClientConnection leaseConnection(Future<CPoolEntry> future, long timeout, TimeUnit tunit) throws InterruptedException, ExecutionException, ConnectionPoolTimeoutException {
        try {
            CPoolEntry entry = (CPoolEntry) future.get(timeout, tunit);
            if (entry == null || future.isCancelled()) {
                throw new InterruptedException();
            }
            Asserts.check(entry.getConnection() != null, "Pool entry with no connection");
            if (this.log.isDebugEnabled()) {
                this.log.debug("Connection leased: " + format(entry) + formatStats((HttpRoute) entry.getRoute()));
            }
            return CPoolProxy.newProxy(entry);
        } catch (TimeoutException e) {
            throw new ConnectionPoolTimeoutException("Timeout waiting for connection from pool");
        }
    }

    public void releaseConnection(cz.msebera.android.httpclient.HttpClientConnection r12, java.lang.Object r13, long r14, java.util.concurrent.TimeUnit r16) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.utils.BlockUtils.getBlockByInsn(BlockUtils.java:172)
	at jadx.core.dex.visitors.ssa.EliminatePhiNodes.replaceMerge(EliminatePhiNodes.java:90)
	at jadx.core.dex.visitors.ssa.EliminatePhiNodes.replaceMergeInstructions(EliminatePhiNodes.java:68)
	at jadx.core.dex.visitors.ssa.EliminatePhiNodes.visit(EliminatePhiNodes.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r11 = this;
        r4 = "Managed connection";
        cz.msebera.android.httpclient.util.Args.notNull(r12, r4);
        monitor-enter(r12);
        r2 = cz.msebera.android.httpclient.impl.conn.CPoolProxy.detach(r12);	 Catch:{ all -> 0x00c9 }
        if (r2 != 0) goto L_0x000f;	 Catch:{ all -> 0x00c9 }
    L_0x000d:
        monitor-exit(r12);	 Catch:{ all -> 0x00c9 }
    L_0x000e:
        return;	 Catch:{ all -> 0x00c9 }
    L_0x000f:
        r0 = r2.getConnection();	 Catch:{ all -> 0x00c9 }
        r0 = (cz.msebera.android.httpclient.conn.ManagedHttpClientConnection) r0;	 Catch:{ all -> 0x00c9 }
        r4 = r0.isOpen();	 Catch:{ all -> 0x00d6 }
        if (r4 == 0) goto L_0x0081;	 Catch:{ all -> 0x00d6 }
    L_0x001b:
        if (r16 == 0) goto L_0x00cc;	 Catch:{ all -> 0x00d6 }
    L_0x001d:
        r1 = r16;	 Catch:{ all -> 0x00d6 }
    L_0x001f:
        r2.setState(r13);	 Catch:{ all -> 0x00d6 }
        r2.updateExpiry(r14, r1);	 Catch:{ all -> 0x00d6 }
        r4 = r11.log;	 Catch:{ all -> 0x00d6 }
        r4 = r4.isDebugEnabled();	 Catch:{ all -> 0x00d6 }
        if (r4 == 0) goto L_0x0081;	 Catch:{ all -> 0x00d6 }
    L_0x002d:
        r4 = 0;	 Catch:{ all -> 0x00d6 }
        r4 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1));	 Catch:{ all -> 0x00d6 }
        if (r4 <= 0) goto L_0x00d0;	 Catch:{ all -> 0x00d6 }
    L_0x0033:
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00d6 }
        r4.<init>();	 Catch:{ all -> 0x00d6 }
        r5 = "for ";	 Catch:{ all -> 0x00d6 }
        r4 = r4.append(r5);	 Catch:{ all -> 0x00d6 }
        r6 = r1.toMillis(r14);	 Catch:{ all -> 0x00d6 }
        r6 = (double) r6;	 Catch:{ all -> 0x00d6 }
        r8 = 4652007308841189376; // 0x408f400000000000 float:0.0 double:1000.0;	 Catch:{ all -> 0x00d6 }
        r6 = r6 / r8;	 Catch:{ all -> 0x00d6 }
        r4 = r4.append(r6);	 Catch:{ all -> 0x00d6 }
        r5 = " seconds";	 Catch:{ all -> 0x00d6 }
        r4 = r4.append(r5);	 Catch:{ all -> 0x00d6 }
        r3 = r4.toString();	 Catch:{ all -> 0x00d6 }
    L_0x0059:
        r4 = r11.log;	 Catch:{ all -> 0x00d6 }
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00d6 }
        r5.<init>();	 Catch:{ all -> 0x00d6 }
        r6 = "Connection ";	 Catch:{ all -> 0x00d6 }
        r5 = r5.append(r6);	 Catch:{ all -> 0x00d6 }
        r6 = r11.format(r2);	 Catch:{ all -> 0x00d6 }
        r5 = r5.append(r6);	 Catch:{ all -> 0x00d6 }
        r6 = " can be kept alive ";	 Catch:{ all -> 0x00d6 }
        r5 = r5.append(r6);	 Catch:{ all -> 0x00d6 }
        r5 = r5.append(r3);	 Catch:{ all -> 0x00d6 }
        r5 = r5.toString();	 Catch:{ all -> 0x00d6 }
        r4.debug(r5);	 Catch:{ all -> 0x00d6 }
    L_0x0081:
        r5 = r11.pool;	 Catch:{ all -> 0x00c9 }
        r4 = r0.isOpen();	 Catch:{ all -> 0x00c9 }
        if (r4 == 0) goto L_0x00d4;	 Catch:{ all -> 0x00c9 }
    L_0x0089:
        r4 = r2.isRouteComplete();	 Catch:{ all -> 0x00c9 }
        if (r4 == 0) goto L_0x00d4;	 Catch:{ all -> 0x00c9 }
    L_0x008f:
        r4 = 1;	 Catch:{ all -> 0x00c9 }
    L_0x0090:
        r5.release(r2, r4);	 Catch:{ all -> 0x00c9 }
        r4 = r11.log;	 Catch:{ all -> 0x00c9 }
        r4 = r4.isDebugEnabled();	 Catch:{ all -> 0x00c9 }
        if (r4 == 0) goto L_0x00c6;	 Catch:{ all -> 0x00c9 }
    L_0x009b:
        r5 = r11.log;	 Catch:{ all -> 0x00c9 }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00c9 }
        r4.<init>();	 Catch:{ all -> 0x00c9 }
        r6 = "Connection released: ";	 Catch:{ all -> 0x00c9 }
        r4 = r4.append(r6);	 Catch:{ all -> 0x00c9 }
        r6 = r11.format(r2);	 Catch:{ all -> 0x00c9 }
        r6 = r4.append(r6);	 Catch:{ all -> 0x00c9 }
        r4 = r2.getRoute();	 Catch:{ all -> 0x00c9 }
        r4 = (cz.msebera.android.httpclient.conn.routing.HttpRoute) r4;	 Catch:{ all -> 0x00c9 }
        r4 = r11.formatStats(r4);	 Catch:{ all -> 0x00c9 }
        r4 = r6.append(r4);	 Catch:{ all -> 0x00c9 }
        r4 = r4.toString();	 Catch:{ all -> 0x00c9 }
        r5.debug(r4);	 Catch:{ all -> 0x00c9 }
    L_0x00c6:
        monitor-exit(r12);	 Catch:{ all -> 0x00c9 }
        goto L_0x000e;	 Catch:{ all -> 0x00c9 }
    L_0x00c9:
        r4 = move-exception;	 Catch:{ all -> 0x00c9 }
        monitor-exit(r12);	 Catch:{ all -> 0x00c9 }
        throw r4;
    L_0x00cc:
        r1 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ all -> 0x00d6 }
        goto L_0x001f;	 Catch:{ all -> 0x00d6 }
    L_0x00d0:
        r3 = "indefinitely";	 Catch:{ all -> 0x00d6 }
        goto L_0x0059;
    L_0x00d4:
        r4 = 0;
        goto L_0x0090;
    L_0x00d6:
        r4 = move-exception;
        r5 = r4;
        r6 = r11.pool;	 Catch:{ all -> 0x00c9 }
        r4 = r0.isOpen();	 Catch:{ all -> 0x00c9 }
        if (r4 == 0) goto L_0x011e;	 Catch:{ all -> 0x00c9 }
    L_0x00e0:
        r4 = r2.isRouteComplete();	 Catch:{ all -> 0x00c9 }
        if (r4 == 0) goto L_0x011e;	 Catch:{ all -> 0x00c9 }
    L_0x00e6:
        r4 = 1;	 Catch:{ all -> 0x00c9 }
    L_0x00e7:
        r6.release(r2, r4);	 Catch:{ all -> 0x00c9 }
        r4 = r11.log;	 Catch:{ all -> 0x00c9 }
        r4 = r4.isDebugEnabled();	 Catch:{ all -> 0x00c9 }
        if (r4 == 0) goto L_0x011d;	 Catch:{ all -> 0x00c9 }
    L_0x00f2:
        r6 = r11.log;	 Catch:{ all -> 0x00c9 }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00c9 }
        r4.<init>();	 Catch:{ all -> 0x00c9 }
        r7 = "Connection released: ";	 Catch:{ all -> 0x00c9 }
        r4 = r4.append(r7);	 Catch:{ all -> 0x00c9 }
        r7 = r11.format(r2);	 Catch:{ all -> 0x00c9 }
        r7 = r4.append(r7);	 Catch:{ all -> 0x00c9 }
        r4 = r2.getRoute();	 Catch:{ all -> 0x00c9 }
        r4 = (cz.msebera.android.httpclient.conn.routing.HttpRoute) r4;	 Catch:{ all -> 0x00c9 }
        r4 = r11.formatStats(r4);	 Catch:{ all -> 0x00c9 }
        r4 = r7.append(r4);	 Catch:{ all -> 0x00c9 }
        r4 = r4.toString();	 Catch:{ all -> 0x00c9 }
        r6.debug(r4);	 Catch:{ all -> 0x00c9 }
    L_0x011d:
        throw r5;	 Catch:{ all -> 0x00c9 }
    L_0x011e:
        r4 = 0;
        goto L_0x00e7;
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.httpclient.impl.conn.PoolingHttpClientConnectionManager.releaseConnection(cz.msebera.android.httpclient.HttpClientConnection, java.lang.Object, long, java.util.concurrent.TimeUnit):void");
    }

    public void connect(HttpClientConnection managedConn, HttpRoute route, int connectTimeout, HttpContext context) throws IOException {
        ManagedHttpClientConnection conn;
        HttpHost host;
        Args.notNull(managedConn, "Managed Connection");
        Args.notNull(route, "HTTP route");
        synchronized (managedConn) {
            conn = (ManagedHttpClientConnection) CPoolProxy.getPoolEntry(managedConn).getConnection();
        }
        if (route.getProxyHost() != null) {
            host = route.getProxyHost();
        } else {
            host = route.getTargetHost();
        }
        InetSocketAddress localAddress = route.getLocalSocketAddress();
        SocketConfig socketConfig = this.configData.getSocketConfig(host);
        if (socketConfig == null) {
            socketConfig = this.configData.getDefaultSocketConfig();
        }
        if (socketConfig == null) {
            socketConfig = SocketConfig.DEFAULT;
        }
        this.connectionOperator.connect(conn, host, localAddress, connectTimeout, socketConfig, context);
    }

    public void upgrade(HttpClientConnection managedConn, HttpRoute route, HttpContext context) throws IOException {
        ManagedHttpClientConnection conn;
        Args.notNull(managedConn, "Managed Connection");
        Args.notNull(route, "HTTP route");
        synchronized (managedConn) {
            conn = (ManagedHttpClientConnection) CPoolProxy.getPoolEntry(managedConn).getConnection();
        }
        this.connectionOperator.upgrade(conn, route.getTargetHost(), context);
    }

    public void routeComplete(HttpClientConnection managedConn, HttpRoute route, HttpContext context) throws IOException {
        Args.notNull(managedConn, "Managed Connection");
        Args.notNull(route, "HTTP route");
        synchronized (managedConn) {
            CPoolProxy.getPoolEntry(managedConn).markRouteComplete();
        }
    }

    public void shutdown() {
        if (this.isShutDown.compareAndSet(false, true)) {
            this.log.debug("Connection manager is shutting down");
            try {
                this.pool.shutdown();
            } catch (IOException ex) {
                this.log.debug("I/O exception shutting down connection manager", ex);
            }
            this.log.debug("Connection manager shut down");
        }
    }

    public void closeIdleConnections(long idleTimeout, TimeUnit tunit) {
        if (this.log.isDebugEnabled()) {
            this.log.debug("Closing connections idle longer than " + idleTimeout + " " + tunit);
        }
        this.pool.closeIdle(idleTimeout, tunit);
    }

    public void closeExpiredConnections() {
        this.log.debug("Closing expired connections");
        this.pool.closeExpired();
    }

    public int getMaxTotal() {
        return this.pool.getMaxTotal();
    }

    public void setMaxTotal(int max) {
        this.pool.setMaxTotal(max);
    }

    public int getDefaultMaxPerRoute() {
        return this.pool.getDefaultMaxPerRoute();
    }

    public void setDefaultMaxPerRoute(int max) {
        this.pool.setDefaultMaxPerRoute(max);
    }

    public int getMaxPerRoute(HttpRoute route) {
        return this.pool.getMaxPerRoute(route);
    }

    public void setMaxPerRoute(HttpRoute route, int max) {
        this.pool.setMaxPerRoute(route, max);
    }

    public PoolStats getTotalStats() {
        return this.pool.getTotalStats();
    }

    public PoolStats getStats(HttpRoute route) {
        return this.pool.getStats(route);
    }

    public SocketConfig getDefaultSocketConfig() {
        return this.configData.getDefaultSocketConfig();
    }

    public void setDefaultSocketConfig(SocketConfig defaultSocketConfig) {
        this.configData.setDefaultSocketConfig(defaultSocketConfig);
    }

    public ConnectionConfig getDefaultConnectionConfig() {
        return this.configData.getDefaultConnectionConfig();
    }

    public void setDefaultConnectionConfig(ConnectionConfig defaultConnectionConfig) {
        this.configData.setDefaultConnectionConfig(defaultConnectionConfig);
    }

    public SocketConfig getSocketConfig(HttpHost host) {
        return this.configData.getSocketConfig(host);
    }

    public void setSocketConfig(HttpHost host, SocketConfig socketConfig) {
        this.configData.setSocketConfig(host, socketConfig);
    }

    public ConnectionConfig getConnectionConfig(HttpHost host) {
        return this.configData.getConnectionConfig(host);
    }

    public void setConnectionConfig(HttpHost host, ConnectionConfig connectionConfig) {
        this.configData.setConnectionConfig(host, connectionConfig);
    }
}
