package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.client.protocol.ClientContext;
import cz.msebera.android.httpclient.conn.ClientConnectionOperator;
import cz.msebera.android.httpclient.conn.DnsResolver;
import cz.msebera.android.httpclient.conn.OperatedClientConnection;
import cz.msebera.android.httpclient.conn.scheme.Scheme;
import cz.msebera.android.httpclient.conn.scheme.SchemeLayeredSocketFactory;
import cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.params.HttpConnectionParams;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

@ThreadSafe
@Deprecated
public class DefaultClientConnectionOperator implements ClientConnectionOperator {
    protected final DnsResolver dnsResolver;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());
    protected final SchemeRegistry schemeRegistry;

    public DefaultClientConnectionOperator(SchemeRegistry schemes) {
        Args.notNull(schemes, "Scheme registry");
        this.schemeRegistry = schemes;
        this.dnsResolver = new SystemDefaultDnsResolver();
    }

    public DefaultClientConnectionOperator(SchemeRegistry schemes, DnsResolver dnsResolver) {
        Args.notNull(schemes, "Scheme registry");
        Args.notNull(dnsResolver, "DNS resolver");
        this.schemeRegistry = schemes;
        this.dnsResolver = dnsResolver;
    }

    public OperatedClientConnection createConnection() {
        return new DefaultClientConnection();
    }

    private SchemeRegistry getSchemeRegistry(HttpContext context) {
        SchemeRegistry reg = (SchemeRegistry) context.getAttribute(ClientContext.SCHEME_REGISTRY);
        if (reg == null) {
            return this.schemeRegistry;
        }
        return reg;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void openConnection(cz.msebera.android.httpclient.conn.OperatedClientConnection r20, cz.msebera.android.httpclient.HttpHost r21, java.net.InetAddress r22, cz.msebera.android.httpclient.protocol.HttpContext r23, cz.msebera.android.httpclient.params.HttpParams r24) throws java.io.IOException {
        /*
        r19 = this;
        r16 = "Connection";
        r0 = r20;
        r1 = r16;
        cz.msebera.android.httpclient.util.Args.notNull(r0, r1);
        r16 = "Target host";
        r0 = r21;
        r1 = r16;
        cz.msebera.android.httpclient.util.Args.notNull(r0, r1);
        r16 = "HTTP parameters";
        r0 = r24;
        r1 = r16;
        cz.msebera.android.httpclient.util.Args.notNull(r0, r1);
        r16 = r20.isOpen();
        if (r16 != 0) goto L_0x00e0;
    L_0x0024:
        r16 = 1;
    L_0x0026:
        r17 = "Connection must not be open";
        cz.msebera.android.httpclient.util.Asserts.check(r16, r17);
        r0 = r19;
        r1 = r23;
        r11 = r0.getSchemeRegistry(r1);
        r16 = r21.getSchemeName();
        r0 = r16;
        r13 = r11.getScheme(r0);
        r14 = r13.getSchemeSocketFactory();
        r16 = r21.getHostName();
        r0 = r19;
        r1 = r16;
        r4 = r0.resolveHostname(r1);
        r16 = r21.getPort();
        r0 = r16;
        r10 = r13.resolvePort(r0);
        r7 = 0;
    L_0x0059:
        r0 = r4.length;
        r16 = r0;
        r0 = r16;
        if (r7 >= r0) goto L_0x00df;
    L_0x0060:
        r3 = r4[r7];
        r0 = r4.length;
        r16 = r0;
        r16 = r16 + -1;
        r0 = r16;
        if (r7 != r0) goto L_0x00e4;
    L_0x006b:
        r8 = 1;
    L_0x006c:
        r0 = r24;
        r15 = r14.createSocket(r0);
        r0 = r20;
        r1 = r21;
        r0.opening(r15, r1);
        r12 = new cz.msebera.android.httpclient.conn.HttpInetSocketAddress;
        r0 = r21;
        r12.<init>(r0, r3, r10);
        r9 = 0;
        if (r22 == 0) goto L_0x008e;
    L_0x0083:
        r9 = new java.net.InetSocketAddress;
        r16 = 0;
        r0 = r22;
        r1 = r16;
        r9.<init>(r0, r1);
    L_0x008e:
        r0 = r19;
        r0 = r0.log;
        r16 = r0;
        r16 = r16.isDebugEnabled();
        if (r16 == 0) goto L_0x00b9;
    L_0x009a:
        r0 = r19;
        r0 = r0.log;
        r16 = r0;
        r17 = new java.lang.StringBuilder;
        r17.<init>();
        r18 = "Connecting to ";
        r17 = r17.append(r18);
        r0 = r17;
        r17 = r0.append(r12);
        r17 = r17.toString();
        r16.debug(r17);
    L_0x00b9:
        r0 = r24;
        r5 = r14.connectSocket(r15, r12, r9, r0);	 Catch:{ ConnectException -> 0x00e6, ConnectTimeoutException -> 0x00ea }
        if (r15 == r5) goto L_0x00c9;
    L_0x00c1:
        r15 = r5;
        r0 = r20;
        r1 = r21;
        r0.opening(r15, r1);	 Catch:{ ConnectException -> 0x00e6, ConnectTimeoutException -> 0x00ea }
    L_0x00c9:
        r0 = r19;
        r1 = r23;
        r2 = r24;
        r0.prepareSocket(r15, r1, r2);	 Catch:{ ConnectException -> 0x00e6, ConnectTimeoutException -> 0x00ea }
        r16 = r14.isSecure(r15);	 Catch:{ ConnectException -> 0x00e6, ConnectTimeoutException -> 0x00ea }
        r0 = r20;
        r1 = r16;
        r2 = r24;
        r0.openCompleted(r1, r2);	 Catch:{ ConnectException -> 0x00e6, ConnectTimeoutException -> 0x00ea }
    L_0x00df:
        return;
    L_0x00e0:
        r16 = 0;
        goto L_0x0026;
    L_0x00e4:
        r8 = 0;
        goto L_0x006c;
    L_0x00e6:
        r6 = move-exception;
        if (r8 == 0) goto L_0x00ee;
    L_0x00e9:
        throw r6;
    L_0x00ea:
        r6 = move-exception;
        if (r8 == 0) goto L_0x00ee;
    L_0x00ed:
        throw r6;
    L_0x00ee:
        r0 = r19;
        r0 = r0.log;
        r16 = r0;
        r16 = r16.isDebugEnabled();
        if (r16 == 0) goto L_0x0127;
    L_0x00fa:
        r0 = r19;
        r0 = r0.log;
        r16 = r0;
        r17 = new java.lang.StringBuilder;
        r17.<init>();
        r18 = "Connect to ";
        r17 = r17.append(r18);
        r0 = r17;
        r17 = r0.append(r12);
        r18 = " timed out. ";
        r17 = r17.append(r18);
        r18 = "Connection will be retried using another IP address";
        r17 = r17.append(r18);
        r17 = r17.toString();
        r16.debug(r17);
    L_0x0127:
        r7 = r7 + 1;
        goto L_0x0059;
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.httpclient.impl.conn.DefaultClientConnectionOperator.openConnection(cz.msebera.android.httpclient.conn.OperatedClientConnection, cz.msebera.android.httpclient.HttpHost, java.net.InetAddress, cz.msebera.android.httpclient.protocol.HttpContext, cz.msebera.android.httpclient.params.HttpParams):void");
    }

    public void updateSecureConnection(OperatedClientConnection conn, HttpHost target, HttpContext context, HttpParams params) throws IOException {
        Args.notNull(conn, "Connection");
        Args.notNull(target, "Target host");
        Args.notNull(params, "Parameters");
        Asserts.check(conn.isOpen(), "Connection must be open");
        Scheme schm = getSchemeRegistry(context).getScheme(target.getSchemeName());
        Asserts.check(schm.getSchemeSocketFactory() instanceof SchemeLayeredSocketFactory, "Socket factory must implement SchemeLayeredSocketFactory");
        SchemeLayeredSocketFactory lsf = (SchemeLayeredSocketFactory) schm.getSchemeSocketFactory();
        Socket sock = lsf.createLayeredSocket(conn.getSocket(), target.getHostName(), schm.resolvePort(target.getPort()), params);
        prepareSocket(sock, context, params);
        conn.update(sock, target, lsf.isSecure(sock), params);
    }

    protected void prepareSocket(Socket sock, HttpContext context, HttpParams params) throws IOException {
        sock.setTcpNoDelay(HttpConnectionParams.getTcpNoDelay(params));
        sock.setSoTimeout(HttpConnectionParams.getSoTimeout(params));
        int linger = HttpConnectionParams.getLinger(params);
        if (linger >= 0) {
            sock.setSoLinger(linger > 0, linger);
        }
    }

    protected InetAddress[] resolveHostname(String host) throws UnknownHostException {
        return this.dnsResolver.resolve(host);
    }
}
