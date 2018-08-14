package com.yandex.metrica.impl.ob;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class fl implements fm {
    protected final HttpClient f12408a;

    public static final class C4475a extends HttpEntityEnclosingRequestBase {
        public C4475a(String str) {
            setURI(URI.create(str));
        }

        public String getMethod() {
            return "PATCH";
        }
    }

    public fl(HttpClient httpClient) {
        this.f12408a = httpClient;
    }

    private static void m16059a(HttpUriRequest httpUriRequest, Map<String, String> map) {
        for (String str : map.keySet()) {
            httpUriRequest.setHeader(str, (String) map.get(str));
        }
    }

    private static void m16058a(HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase, fu<?> fuVar) throws fr {
        byte[] c = fuVar.mo7104c();
        if (c != null) {
            httpEntityEnclosingRequestBase.setEntity(new ByteArrayEntity(c));
        }
    }

    public HttpResponse mo7107a(fu<?> fuVar) throws IOException, fr {
        HttpUriRequest httpGet;
        HttpEntityEnclosingRequestBase httpPost;
        switch (fuVar.m16039d()) {
            case -1:
                byte[] j = fuVar.m16045j();
                if (j == null) {
                    httpGet = new HttpGet(fuVar.mo7105a());
                    break;
                }
                httpGet = new HttpPost(fuVar.mo7105a());
                httpGet.addHeader("Content-Type", fuVar.m16044i());
                httpGet.setEntity(new ByteArrayEntity(j));
                break;
            case 0:
                httpGet = new HttpGet(fuVar.mo7105a());
                break;
            case 1:
                httpPost = new HttpPost(fuVar.mo7105a());
                httpPost.addHeader("Content-Type", fuVar.m16048m());
                m16058a(httpPost, (fu) fuVar);
                break;
            case 2:
                httpPost = new HttpPut(fuVar.mo7105a());
                httpPost.addHeader("Content-Type", fuVar.m16048m());
                m16058a(httpPost, (fu) fuVar);
                break;
            case 3:
                httpGet = new HttpDelete(fuVar.mo7105a());
                break;
            case 4:
                httpGet = new HttpHead(fuVar.mo7105a());
                break;
            case 5:
                httpGet = new HttpOptions(fuVar.mo7105a());
                break;
            case 6:
                httpGet = new HttpTrace(fuVar.mo7105a());
                break;
            case 7:
                httpPost = new C4475a(fuVar.mo7105a());
                httpPost.addHeader("Content-Type", fuVar.m16048m());
                m16058a(httpPost, (fu) fuVar);
                break;
            default:
                throw new IllegalStateException("Unknown request method.");
        }
        m16059a(httpGet, fuVar.mo7106b());
        HttpParams params = httpGet.getParams();
        int n = fuVar.m16049n();
        HttpConnectionParams.setConnectionTimeout(params, 5000);
        HttpConnectionParams.setSoTimeout(params, n);
        return this.f12408a.execute(httpGet);
    }
}
