package com.yandex.metrica.impl.ob;

import android.os.Build.VERSION;
import android.util.Base64;
import com.yandex.metrica.impl.ob.ex.C4468a;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

class fg {
    static String m16007a(X509Certificate x509Certificate) {
        try {
            return Base64.encodeToString(MessageDigest.getInstance("SHA-256").digest(x509Certificate.getPublicKey().getEncoded()), 2);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    static fs m16006a(List<X509Certificate> list) throws GeneralSecurityException, IOException {
        TrustManager[] trustManagerArr;
        fm fnVar;
        if (list == null || list.isEmpty()) {
            trustManagerArr = null;
        } else {
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            instance.load(null, null);
            for (int i = 0; i < list.size(); i++) {
                instance.setCertificateEntry("ca" + i, (Certificate) list.get(i));
            }
            TrustManagerFactory instance2 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance2.init(instance);
            trustManagerArr = instance2.getTrustManagers();
        }
        SSLContext instance3 = SSLContext.getInstance("TLS");
        instance3.init(null, trustManagerArr, null);
        if (VERSION.SDK_INT >= 9) {
            fnVar = new fn(instance3.getSocketFactory());
        } else {
            SocketFactory c4468a = new C4468a(instance3);
            SocketFactory socketFactory = PlainSocketFactory.getSocketFactory();
            HttpParams basicHttpParams = new BasicHttpParams();
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("http", socketFactory, 80));
            schemeRegistry.register(new Scheme("https", c4468a, 443));
            fnVar = new fl(new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams));
        }
        return new fs(new fq(fnVar));
    }

    static boolean m16008a(fd fdVar) {
        return !"https://certificate.mobile.yandex.net/api/v1/pins".equals(fdVar.m15993b());
    }
}
