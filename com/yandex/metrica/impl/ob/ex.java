package com.yandex.metrica.impl.ob;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import org.apache.http.conn.ssl.SSLSocketFactory;

public class ex {

    private static class C4468a extends SSLSocketFactory {
        private final SSLContext f12364a;

        public C4468a(SSLContext sSLContext) throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
            super(null);
            this.f12364a = sSLContext;
            setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
        }

        public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException {
            return this.f12364a.getSocketFactory().createSocket(socket, host, port, autoClose);
        }

        public Socket createSocket() throws IOException {
            return this.f12364a.getSocketFactory().createSocket();
        }
    }

    public static X509Certificate m15974a(InputStream inputStream) {
        InputStream bufferedInputStream;
        try {
            CertificateFactory instance = CertificateFactory.getInstance("X.509");
            bufferedInputStream = new BufferedInputStream(inputStream);
            X509Certificate x509Certificate = (X509Certificate) instance.generateCertificate(bufferedInputStream);
            bufferedInputStream.close();
            try {
                inputStream.close();
            } catch (IOException e) {
            }
            return x509Certificate;
        } catch (Throwable e2) {
            try {
                throw new RuntimeException(e2);
            } catch (Throwable th) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                }
            }
        } catch (Throwable th2) {
            bufferedInputStream.close();
        }
    }

    public static X509Certificate m15975a(String str) {
        try {
            return m15974a(new ByteArrayInputStream(str.getBytes()));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
