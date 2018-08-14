package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import cz.msebera.android.httpclient.config.Lookup;
import cz.msebera.android.httpclient.conn.DnsResolver;
import cz.msebera.android.httpclient.conn.ManagedHttpClientConnection;
import cz.msebera.android.httpclient.conn.SchemePortResolver;
import cz.msebera.android.httpclient.conn.UnsupportedSchemeException;
import cz.msebera.android.httpclient.conn.socket.ConnectionSocketFactory;
import cz.msebera.android.httpclient.conn.socket.LayeredConnectionSocketFactory;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;

@Immutable
class HttpClientConnectionOperator {
    static final String SOCKET_FACTORY_REGISTRY = "http.socket-factory-registry";
    private final DnsResolver dnsResolver;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());
    private final SchemePortResolver schemePortResolver;
    private final Lookup<ConnectionSocketFactory> socketFactoryRegistry;

    HttpClientConnectionOperator(Lookup<ConnectionSocketFactory> socketFactoryRegistry, SchemePortResolver schemePortResolver, DnsResolver dnsResolver) {
        Args.notNull(socketFactoryRegistry, "Socket factory registry");
        this.socketFactoryRegistry = socketFactoryRegistry;
        if (schemePortResolver == null) {
            schemePortResolver = DefaultSchemePortResolver.INSTANCE;
        }
        this.schemePortResolver = schemePortResolver;
        if (dnsResolver == null) {
            dnsResolver = SystemDefaultDnsResolver.INSTANCE;
        }
        this.dnsResolver = dnsResolver;
    }

    private Lookup<ConnectionSocketFactory> getSocketFactoryRegistry(HttpContext context) {
        Lookup<ConnectionSocketFactory> reg = (Lookup) context.getAttribute("http.socket-factory-registry");
        if (reg == null) {
            return this.socketFactoryRegistry;
        }
        return reg;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void connect(cz.msebera.android.httpclient.conn.ManagedHttpClientConnection r19, cz.msebera.android.httpclient.HttpHost r20, java.net.InetSocketAddress r21, int r22, cz.msebera.android.httpclient.config.SocketConfig r23, cz.msebera.android.httpclient.protocol.HttpContext r24) throws java.io.IOException {
        /*
        r18 = this;
        r0 = r18;
        r1 = r24;
        r17 = r0.getSocketFactoryRegistry(r1);
        r3 = r20.getSchemeName();
        r0 = r17;
        r2 = r0.lookup(r3);
        r2 = (cz.msebera.android.httpclient.conn.socket.ConnectionSocketFactory) r2;
        if (r2 != 0) goto L_0x0034;
    L_0x0016:
        r3 = new cz.msebera.android.httpclient.conn.UnsupportedSchemeException;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r7 = r20.getSchemeName();
        r5 = r5.append(r7);
        r7 = " protocol is not supported";
        r5 = r5.append(r7);
        r5 = r5.toString();
        r3.<init>(r5);
        throw r3;
    L_0x0034:
        r3 = r20.getAddress();
        if (r3 == 0) goto L_0x00f2;
    L_0x003a:
        r3 = 1;
        r10 = new java.net.InetAddress[r3];
        r3 = 0;
        r5 = r20.getAddress();
        r10[r3] = r5;
    L_0x0044:
        r0 = r18;
        r3 = r0.schemePortResolver;
        r0 = r20;
        r16 = r3.resolve(r0);
        r12 = 0;
    L_0x004f:
        r3 = r10.length;
        if (r12 >= r3) goto L_0x00f1;
    L_0x0052:
        r9 = r10[r12];
        r3 = r10.length;
        r3 = r3 + -1;
        if (r12 != r3) goto L_0x0100;
    L_0x0059:
        r13 = 1;
    L_0x005a:
        r0 = r24;
        r4 = r2.createSocket(r0);
        r3 = r23.getSoTimeout();
        r4.setSoTimeout(r3);
        r3 = r23.isSoReuseAddress();
        r4.setReuseAddress(r3);
        r3 = r23.isTcpNoDelay();
        r4.setTcpNoDelay(r3);
        r3 = r23.isSoKeepAlive();
        r4.setKeepAlive(r3);
        r14 = r23.getSoLinger();
        if (r14 < 0) goto L_0x0088;
    L_0x0082:
        if (r14 <= 0) goto L_0x0103;
    L_0x0084:
        r3 = 1;
    L_0x0085:
        r4.setSoLinger(r3, r14);
    L_0x0088:
        r0 = r19;
        r0.bind(r4);
        r6 = new java.net.InetSocketAddress;
        r0 = r16;
        r6.<init>(r9, r0);
        r0 = r18;
        r3 = r0.log;
        r3 = r3.isDebugEnabled();
        if (r3 == 0) goto L_0x00b9;
    L_0x009e:
        r0 = r18;
        r3 = r0.log;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r7 = "Connecting to ";
        r5 = r5.append(r7);
        r5 = r5.append(r6);
        r5 = r5.toString();
        r3.debug(r5);
    L_0x00b9:
        r3 = r22;
        r5 = r20;
        r7 = r21;
        r8 = r24;
        r4 = r2.connectSocket(r3, r4, r5, r6, r7, r8);	 Catch:{ SocketTimeoutException -> 0x0105, ConnectException -> 0x0110, NoRouteToHostException -> 0x0130 }
        r0 = r19;
        r0.bind(r4);	 Catch:{ SocketTimeoutException -> 0x0105, ConnectException -> 0x0110, NoRouteToHostException -> 0x0130 }
        r0 = r18;
        r3 = r0.log;	 Catch:{ SocketTimeoutException -> 0x0105, ConnectException -> 0x0110, NoRouteToHostException -> 0x0130 }
        r3 = r3.isDebugEnabled();	 Catch:{ SocketTimeoutException -> 0x0105, ConnectException -> 0x0110, NoRouteToHostException -> 0x0130 }
        if (r3 == 0) goto L_0x00f1;
    L_0x00d4:
        r0 = r18;
        r3 = r0.log;	 Catch:{ SocketTimeoutException -> 0x0105, ConnectException -> 0x0110, NoRouteToHostException -> 0x0130 }
        r5 = new java.lang.StringBuilder;	 Catch:{ SocketTimeoutException -> 0x0105, ConnectException -> 0x0110, NoRouteToHostException -> 0x0130 }
        r5.<init>();	 Catch:{ SocketTimeoutException -> 0x0105, ConnectException -> 0x0110, NoRouteToHostException -> 0x0130 }
        r7 = "Connection established ";
        r5 = r5.append(r7);	 Catch:{ SocketTimeoutException -> 0x0105, ConnectException -> 0x0110, NoRouteToHostException -> 0x0130 }
        r0 = r19;
        r5 = r5.append(r0);	 Catch:{ SocketTimeoutException -> 0x0105, ConnectException -> 0x0110, NoRouteToHostException -> 0x0130 }
        r5 = r5.toString();	 Catch:{ SocketTimeoutException -> 0x0105, ConnectException -> 0x0110, NoRouteToHostException -> 0x0130 }
        r3.debug(r5);	 Catch:{ SocketTimeoutException -> 0x0105, ConnectException -> 0x0110, NoRouteToHostException -> 0x0130 }
    L_0x00f1:
        return;
    L_0x00f2:
        r0 = r18;
        r3 = r0.dnsResolver;
        r5 = r20.getHostName();
        r10 = r3.resolve(r5);
        goto L_0x0044;
    L_0x0100:
        r13 = 0;
        goto L_0x005a;
    L_0x0103:
        r3 = 0;
        goto L_0x0085;
    L_0x0105:
        r11 = move-exception;
        if (r13 == 0) goto L_0x0134;
    L_0x0108:
        r3 = new cz.msebera.android.httpclient.conn.ConnectTimeoutException;
        r0 = r20;
        r3.<init>(r11, r0, r10);
        throw r3;
    L_0x0110:
        r11 = move-exception;
        if (r13 == 0) goto L_0x0134;
    L_0x0113:
        r15 = r11.getMessage();
        r3 = "Connection timed out";
        r3 = r3.equals(r15);
        if (r3 == 0) goto L_0x0128;
    L_0x0120:
        r3 = new cz.msebera.android.httpclient.conn.ConnectTimeoutException;
        r0 = r20;
        r3.<init>(r11, r0, r10);
        throw r3;
    L_0x0128:
        r3 = new cz.msebera.android.httpclient.conn.HttpHostConnectException;
        r0 = r20;
        r3.<init>(r11, r0, r10);
        throw r3;
    L_0x0130:
        r11 = move-exception;
        if (r13 == 0) goto L_0x0134;
    L_0x0133:
        throw r11;
    L_0x0134:
        r0 = r18;
        r3 = r0.log;
        r3 = r3.isDebugEnabled();
        if (r3 == 0) goto L_0x0167;
    L_0x013e:
        r0 = r18;
        r3 = r0.log;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r7 = "Connect to ";
        r5 = r5.append(r7);
        r5 = r5.append(r6);
        r7 = " timed out. ";
        r5 = r5.append(r7);
        r7 = "Connection will be retried using another IP address";
        r5 = r5.append(r7);
        r5 = r5.toString();
        r3.debug(r5);
    L_0x0167:
        r12 = r12 + 1;
        goto L_0x004f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.httpclient.impl.conn.HttpClientConnectionOperator.connect(cz.msebera.android.httpclient.conn.ManagedHttpClientConnection, cz.msebera.android.httpclient.HttpHost, java.net.InetSocketAddress, int, cz.msebera.android.httpclient.config.SocketConfig, cz.msebera.android.httpclient.protocol.HttpContext):void");
    }

    public void upgrade(ManagedHttpClientConnection conn, HttpHost host, HttpContext context) throws IOException {
        ConnectionSocketFactory sf = (ConnectionSocketFactory) getSocketFactoryRegistry(HttpClientContext.adapt(context)).lookup(host.getSchemeName());
        if (sf == null) {
            throw new UnsupportedSchemeException(host.getSchemeName() + " protocol is not supported");
        } else if (sf instanceof LayeredConnectionSocketFactory) {
            conn.bind(((LayeredConnectionSocketFactory) sf).createLayeredSocket(conn.getSocket(), host.getHostName(), this.schemePortResolver.resolve(host), context));
        } else {
            throw new UnsupportedSchemeException(host.getSchemeName() + " protocol does not support connection upgrade");
        }
    }
}
