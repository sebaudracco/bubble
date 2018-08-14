package cz.msebera.android.httpclient.impl.execchain;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.HttpRequestRetryHandler;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.util.Args;

@Immutable
public class RetryExec implements ClientExecChain {
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());
    private final ClientExecChain requestExecutor;
    private final HttpRequestRetryHandler retryHandler;

    public RetryExec(ClientExecChain requestExecutor, HttpRequestRetryHandler retryHandler) {
        Args.notNull(requestExecutor, "HTTP request executor");
        Args.notNull(retryHandler, "HTTP request retry handler");
        this.requestExecutor = requestExecutor;
        this.retryHandler = retryHandler;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public cz.msebera.android.httpclient.client.methods.CloseableHttpResponse execute(cz.msebera.android.httpclient.conn.routing.HttpRoute r8, cz.msebera.android.httpclient.client.methods.HttpRequestWrapper r9, cz.msebera.android.httpclient.client.protocol.HttpClientContext r10, cz.msebera.android.httpclient.client.methods.HttpExecutionAware r11) throws java.io.IOException, cz.msebera.android.httpclient.HttpException {
        /*
        r7 = this;
        r4 = "HTTP route";
        cz.msebera.android.httpclient.util.Args.notNull(r8, r4);
        r4 = "HTTP request";
        cz.msebera.android.httpclient.util.Args.notNull(r9, r4);
        r4 = "HTTP context";
        cz.msebera.android.httpclient.util.Args.notNull(r10, r4);
        r2 = r9.getAllHeaders();
        r1 = 1;
    L_0x0017:
        r4 = r7.requestExecutor;	 Catch:{ IOException -> 0x001e }
        r4 = r4.execute(r8, r9, r10, r11);	 Catch:{ IOException -> 0x001e }
        return r4;
    L_0x001e:
        r0 = move-exception;
        if (r11 == 0) goto L_0x0030;
    L_0x0021:
        r4 = r11.isAborted();
        if (r4 == 0) goto L_0x0030;
    L_0x0027:
        r4 = r7.log;
        r5 = "Request has been aborted";
        r4.debug(r5);
        throw r0;
    L_0x0030:
        r4 = r7.retryHandler;
        r4 = r4.retryRequest(r0, r1, r10);
        if (r4 == 0) goto L_0x00cb;
    L_0x0038:
        r4 = r7.log;
        r4 = r4.isInfoEnabled();
        if (r4 == 0) goto L_0x007b;
    L_0x0040:
        r4 = r7.log;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "I/O exception (";
        r5 = r5.append(r6);
        r6 = r0.getClass();
        r6 = r6.getName();
        r5 = r5.append(r6);
        r6 = ") caught when processing request to ";
        r5 = r5.append(r6);
        r5 = r5.append(r8);
        r6 = ": ";
        r5 = r5.append(r6);
        r6 = r0.getMessage();
        r5 = r5.append(r6);
        r5 = r5.toString();
        r4.info(r5);
    L_0x007b:
        r4 = r7.log;
        r4 = r4.isDebugEnabled();
        if (r4 == 0) goto L_0x008c;
    L_0x0083:
        r4 = r7.log;
        r5 = r0.getMessage();
        r4.debug(r5, r0);
    L_0x008c:
        r4 = cz.msebera.android.httpclient.impl.execchain.RequestEntityProxy.isRepeatable(r9);
        if (r4 != 0) goto L_0x00a3;
    L_0x0092:
        r4 = r7.log;
        r5 = "Cannot retry non-repeatable request";
        r4.debug(r5);
        r4 = new cz.msebera.android.httpclient.client.NonRepeatableRequestException;
        r5 = "Cannot retry request with a non-repeatable request entity";
        r4.<init>(r5, r0);
        throw r4;
    L_0x00a3:
        r9.setHeaders(r2);
        r4 = r7.log;
        r4 = r4.isInfoEnabled();
        if (r4 == 0) goto L_0x00c7;
    L_0x00ae:
        r4 = r7.log;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "Retrying request to ";
        r5 = r5.append(r6);
        r5 = r5.append(r8);
        r5 = r5.toString();
        r4.info(r5);
    L_0x00c7:
        r1 = r1 + 1;
        goto L_0x0017;
    L_0x00cb:
        r4 = r0 instanceof cz.msebera.android.httpclient.NoHttpResponseException;
        if (r4 == 0) goto L_0x00f8;
    L_0x00cf:
        r3 = new cz.msebera.android.httpclient.NoHttpResponseException;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = r8.getTargetHost();
        r5 = r5.toHostString();
        r4 = r4.append(r5);
        r5 = " failed to respond";
        r4 = r4.append(r5);
        r4 = r4.toString();
        r3.<init>(r4);
        r4 = r0.getStackTrace();
        r3.setStackTrace(r4);
        throw r3;
    L_0x00f8:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.httpclient.impl.execchain.RetryExec.execute(cz.msebera.android.httpclient.conn.routing.HttpRoute, cz.msebera.android.httpclient.client.methods.HttpRequestWrapper, cz.msebera.android.httpclient.client.protocol.HttpClientContext, cz.msebera.android.httpclient.client.methods.HttpExecutionAware):cz.msebera.android.httpclient.client.methods.CloseableHttpResponse");
    }
}
