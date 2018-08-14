package com.yandex.metrica.impl.ob;

import com.loopj.android.http.RequestParams;
import com.yandex.metrica.impl.bc;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public abstract class cq {
    private final String f12124a;

    public static final class C4436a {
        public static final int f12123a = ((int) TimeUnit.SECONDS.toMillis(30));
    }

    public abstract boolean mo7069b();

    public cq(String str) {
        this.f12124a = str;
    }

    public HttpURLConnection mo7070a() throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.f12124a).openConnection();
        httpURLConnection.setConnectTimeout(C4436a.f12123a);
        httpURLConnection.setReadTimeout(C4436a.f12123a);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setRequestProperty("Accept", RequestParams.APPLICATION_JSON);
        httpURLConnection.setRequestProperty("User-Agent", bc.m14874a("com.yandex.mobile.metrica.sdk"));
        return httpURLConnection;
    }
}
