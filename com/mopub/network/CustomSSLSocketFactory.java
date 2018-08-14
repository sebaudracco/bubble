package com.mopub.network;

import android.net.SSLCertificateSocketFactory;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Reflection.MethodBuilder;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class CustomSSLSocketFactory extends SSLSocketFactory {
    @Nullable
    private SSLSocketFactory mCertificateSocketFactory;

    private CustomSSLSocketFactory() {
    }

    @NonNull
    public static CustomSSLSocketFactory getDefault(int handshakeTimeoutMillis) {
        CustomSSLSocketFactory factory = new CustomSSLSocketFactory();
        factory.mCertificateSocketFactory = SSLCertificateSocketFactory.getDefault(handshakeTimeoutMillis, null);
        return factory;
    }

    public Socket createSocket() throws IOException {
        if (this.mCertificateSocketFactory == null) {
            throw new SocketException("SSLSocketFactory was null. Unable to create socket.");
        }
        Socket socket = this.mCertificateSocketFactory.createSocket();
        enableTlsIfAvailable(socket);
        return socket;
    }

    public Socket createSocket(String host, int i) throws IOException, UnknownHostException {
        if (this.mCertificateSocketFactory == null) {
            throw new SocketException("SSLSocketFactory was null. Unable to create socket.");
        }
        Socket socket = this.mCertificateSocketFactory.createSocket(host, i);
        enableTlsIfAvailable(socket);
        return socket;
    }

    public Socket createSocket(String host, int port, InetAddress localhost, int localPort) throws IOException, UnknownHostException {
        if (this.mCertificateSocketFactory == null) {
            throw new SocketException("SSLSocketFactory was null. Unable to create socket.");
        }
        Socket socket = this.mCertificateSocketFactory.createSocket(host, port, localhost, localPort);
        enableTlsIfAvailable(socket);
        return socket;
    }

    public Socket createSocket(InetAddress address, int port) throws IOException {
        if (this.mCertificateSocketFactory == null) {
            throw new SocketException("SSLSocketFactory was null. Unable to create socket.");
        }
        Socket socket = this.mCertificateSocketFactory.createSocket(address, port);
        enableTlsIfAvailable(socket);
        return socket;
    }

    public Socket createSocket(InetAddress address, int port, InetAddress localhost, int localPort) throws IOException {
        if (this.mCertificateSocketFactory == null) {
            throw new SocketException("SSLSocketFactory was null. Unable to create socket.");
        }
        Socket socket = this.mCertificateSocketFactory.createSocket(address, port, localhost, localPort);
        enableTlsIfAvailable(socket);
        return socket;
    }

    public String[] getDefaultCipherSuites() {
        if (this.mCertificateSocketFactory == null) {
            return new String[0];
        }
        return this.mCertificateSocketFactory.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites() {
        if (this.mCertificateSocketFactory == null) {
            return new String[0];
        }
        return this.mCertificateSocketFactory.getSupportedCipherSuites();
    }

    public Socket createSocket(Socket socketParam, String host, int port, boolean autoClose) throws IOException {
        if (this.mCertificateSocketFactory == null) {
            throw new SocketException("SSLSocketFactory was null. Unable to create socket.");
        } else if (VERSION.SDK_INT < 23) {
            if (autoClose && socketParam != null) {
                socketParam.close();
            }
            socket = this.mCertificateSocketFactory.createSocket(InetAddressUtils.getInetAddressByName(host), port);
            enableTlsIfAvailable(socket);
            doManualServerNameIdentification(socket, host);
            return socket;
        } else {
            socket = this.mCertificateSocketFactory.createSocket(socketParam, host, port, autoClose);
            enableTlsIfAvailable(socket);
            return socket;
        }
    }

    private void doManualServerNameIdentification(@NonNull Socket socket, @Nullable String host) throws IOException {
        Preconditions.checkNotNull(socket);
        if (this.mCertificateSocketFactory == null) {
            throw new SocketException("SSLSocketFactory was null. Unable to create socket.");
        } else if (socket instanceof SSLSocket) {
            SSLSocket sslSocket = (SSLSocket) socket;
            setHostnameOnSocket((SSLCertificateSocketFactory) this.mCertificateSocketFactory, sslSocket, host);
            verifyServerName(sslSocket, host);
        }
    }

    @VisibleForTesting
    static void setHostnameOnSocket(@NonNull SSLCertificateSocketFactory certificateSocketFactory, @NonNull SSLSocket sslSocket, @Nullable String host) {
        Preconditions.checkNotNull(certificateSocketFactory);
        Preconditions.checkNotNull(sslSocket);
        if (VERSION.SDK_INT >= 17) {
            certificateSocketFactory.setHostname(sslSocket, host);
            return;
        }
        try {
            new MethodBuilder(sslSocket, "setHostname").addParam(String.class, host).execute();
        } catch (Exception e) {
            MoPubLog.d("Unable to call setHostname() on the socket");
        }
    }

    @VisibleForTesting
    static void verifyServerName(@NonNull SSLSocket sslSocket, @Nullable String host) throws IOException {
        Preconditions.checkNotNull(sslSocket);
        sslSocket.startHandshake();
        if (!HttpsURLConnection.getDefaultHostnameVerifier().verify(host, sslSocket.getSession())) {
            throw new SSLHandshakeException("Server Name Identification failed.");
        }
    }

    private void enableTlsIfAvailable(@Nullable Socket socket) {
        if (socket instanceof SSLSocket) {
            SSLSocket sslSocket = (SSLSocket) socket;
            sslSocket.setEnabledProtocols(sslSocket.getSupportedProtocols());
        }
    }

    @Deprecated
    @VisibleForTesting
    void setCertificateSocketFactory(@NonNull SSLSocketFactory sslSocketFactory) {
        this.mCertificateSocketFactory = sslSocketFactory;
    }
}
