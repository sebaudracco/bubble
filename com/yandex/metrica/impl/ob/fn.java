package com.yandex.metrica.impl.ob;

import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

public class fn implements fm {
    private final SSLSocketFactory f12409a;

    public fn() {
        this(null);
    }

    public fn(SSLSocketFactory sSLSocketFactory) {
        this.f12409a = sSLSocketFactory;
    }

    public HttpResponse mo7107a(fu<?> fuVar) throws IOException, fr {
        String a = fuVar.mo7105a();
        HashMap hashMap = new HashMap();
        hashMap.putAll(fuVar.mo7106b());
        URL url = new URL(a);
        HttpURLConnection a2 = m16063a(url);
        int n = fuVar.m16049n();
        a2.setConnectTimeout(n);
        a2.setReadTimeout(n);
        a2.setUseCaches(false);
        a2.setDoInput(true);
        if ("https".equals(url.getProtocol()) && this.f12409a != null) {
            ((HttpsURLConnection) a2).setSSLSocketFactory(this.f12409a);
        }
        for (String a3 : hashMap.keySet()) {
            a2.addRequestProperty(a3, (String) hashMap.get(a3));
        }
        switch (fuVar.m16039d()) {
            case -1:
                byte[] j = fuVar.m16045j();
                if (j != null) {
                    a2.setDoOutput(true);
                    a2.setRequestMethod(HttpPost.METHOD_NAME);
                    a2.addRequestProperty("Content-Type", fuVar.m16044i());
                    DataOutputStream dataOutputStream = new DataOutputStream(a2.getOutputStream());
                    dataOutputStream.write(j);
                    dataOutputStream.close();
                    break;
                }
                break;
            case 0:
                a2.setRequestMethod("GET");
                break;
            case 1:
                a2.setRequestMethod(HttpPost.METHOD_NAME);
                m16062a(a2, fuVar);
                break;
            case 2:
                a2.setRequestMethod("PUT");
                m16062a(a2, fuVar);
                break;
            case 3:
                a2.setRequestMethod("DELETE");
                break;
            case 4:
                a2.setRequestMethod("HEAD");
                break;
            case 5:
                a2.setRequestMethod("OPTIONS");
                break;
            case 6:
                a2.setRequestMethod("TRACE");
                break;
            case 7:
                a2.setRequestMethod("PATCH");
                m16062a(a2, fuVar);
                break;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
        ProtocolVersion protocolVersion = new ProtocolVersion(HttpVersion.HTTP, 1, 1);
        if (a2.getResponseCode() == -1) {
            throw new IOException("Could not retrieve response code from HttpUrlConnection.");
        }
        HttpResponse basicHttpResponse = new BasicHttpResponse(new BasicStatusLine(protocolVersion, a2.getResponseCode(), a2.getResponseMessage()));
        basicHttpResponse.setEntity(m16061a(a2));
        for (Entry entry : a2.getHeaderFields().entrySet()) {
            if (entry.getKey() != null) {
                basicHttpResponse.addHeader(new BasicHeader((String) entry.getKey(), (String) ((List) entry.getValue()).get(0)));
            }
        }
        return basicHttpResponse;
    }

    private static HttpEntity m16061a(HttpURLConnection httpURLConnection) {
        InputStream inputStream;
        HttpEntity basicHttpEntity = new BasicHttpEntity();
        try {
            inputStream = httpURLConnection.getInputStream();
        } catch (IOException e) {
            inputStream = httpURLConnection.getErrorStream();
        }
        basicHttpEntity.setContent(inputStream);
        basicHttpEntity.setContentLength((long) httpURLConnection.getContentLength());
        basicHttpEntity.setContentEncoding(httpURLConnection.getContentEncoding());
        basicHttpEntity.setContentType(httpURLConnection.getContentType());
        return basicHttpEntity;
    }

    protected HttpURLConnection m16063a(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }

    private static void m16062a(HttpURLConnection httpURLConnection, fu<?> fuVar) throws IOException, fr {
        byte[] c = fuVar.mo7104c();
        if (c != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty("Content-Type", fuVar.m16048m());
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(c);
            dataOutputStream.close();
        }
    }
}
