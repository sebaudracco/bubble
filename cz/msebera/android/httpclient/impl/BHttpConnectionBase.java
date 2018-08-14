package cz.msebera.android.httpclient.impl;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpConnection;
import cz.msebera.android.httpclient.HttpConnectionMetrics;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpInetConnection;
import cz.msebera.android.httpclient.HttpMessage;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.config.MessageConstraints;
import cz.msebera.android.httpclient.entity.BasicHttpEntity;
import cz.msebera.android.httpclient.entity.ContentLengthStrategy;
import cz.msebera.android.httpclient.impl.entity.LaxContentLengthStrategy;
import cz.msebera.android.httpclient.impl.entity.StrictContentLengthStrategy;
import cz.msebera.android.httpclient.impl.io.ChunkedInputStream;
import cz.msebera.android.httpclient.impl.io.ChunkedOutputStream;
import cz.msebera.android.httpclient.impl.io.ContentLengthInputStream;
import cz.msebera.android.httpclient.impl.io.ContentLengthOutputStream;
import cz.msebera.android.httpclient.impl.io.HttpTransportMetricsImpl;
import cz.msebera.android.httpclient.impl.io.IdentityInputStream;
import cz.msebera.android.httpclient.impl.io.IdentityOutputStream;
import cz.msebera.android.httpclient.impl.io.SessionInputBufferImpl;
import cz.msebera.android.httpclient.impl.io.SessionOutputBufferImpl;
import cz.msebera.android.httpclient.io.SessionInputBuffer;
import cz.msebera.android.httpclient.io.SessionOutputBuffer;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import cz.msebera.android.httpclient.util.NetUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.concurrent.atomic.AtomicReference;

@NotThreadSafe
public class BHttpConnectionBase implements HttpConnection, HttpInetConnection {
    private final HttpConnectionMetricsImpl connMetrics;
    private final SessionInputBufferImpl inbuffer;
    private final ContentLengthStrategy incomingContentStrategy;
    private final SessionOutputBufferImpl outbuffer;
    private final ContentLengthStrategy outgoingContentStrategy;
    private final AtomicReference<Socket> socketHolder;

    protected BHttpConnectionBase(int buffersize, int fragmentSizeHint, CharsetDecoder chardecoder, CharsetEncoder charencoder, MessageConstraints constraints, ContentLengthStrategy incomingContentStrategy, ContentLengthStrategy outgoingContentStrategy) {
        Args.positive(buffersize, "Buffer size");
        HttpTransportMetricsImpl inTransportMetrics = new HttpTransportMetricsImpl();
        HttpTransportMetricsImpl outTransportMetrics = new HttpTransportMetricsImpl();
        this.inbuffer = new SessionInputBufferImpl(inTransportMetrics, buffersize, -1, constraints != null ? constraints : MessageConstraints.DEFAULT, chardecoder);
        this.outbuffer = new SessionOutputBufferImpl(outTransportMetrics, buffersize, fragmentSizeHint, charencoder);
        this.connMetrics = new HttpConnectionMetricsImpl(inTransportMetrics, outTransportMetrics);
        if (incomingContentStrategy == null) {
            incomingContentStrategy = LaxContentLengthStrategy.INSTANCE;
        }
        this.incomingContentStrategy = incomingContentStrategy;
        if (outgoingContentStrategy == null) {
            outgoingContentStrategy = StrictContentLengthStrategy.INSTANCE;
        }
        this.outgoingContentStrategy = outgoingContentStrategy;
        this.socketHolder = new AtomicReference();
    }

    protected void ensureOpen() throws IOException {
        Socket socket = (Socket) this.socketHolder.get();
        Asserts.check(socket != null, "Connection is not open");
        if (!this.inbuffer.isBound()) {
            this.inbuffer.bind(getSocketInputStream(socket));
        }
        if (!this.outbuffer.isBound()) {
            this.outbuffer.bind(getSocketOutputStream(socket));
        }
    }

    protected InputStream getSocketInputStream(Socket socket) throws IOException {
        return socket.getInputStream();
    }

    protected OutputStream getSocketOutputStream(Socket socket) throws IOException {
        return socket.getOutputStream();
    }

    protected void bind(Socket socket) throws IOException {
        Args.notNull(socket, "Socket");
        this.socketHolder.set(socket);
        this.inbuffer.bind(null);
        this.outbuffer.bind(null);
    }

    protected SessionInputBuffer getSessionInputBuffer() {
        return this.inbuffer;
    }

    protected SessionOutputBuffer getSessionOutputBuffer() {
        return this.outbuffer;
    }

    protected void doFlush() throws IOException {
        this.outbuffer.flush();
    }

    public boolean isOpen() {
        return this.socketHolder.get() != null;
    }

    protected Socket getSocket() {
        return (Socket) this.socketHolder.get();
    }

    protected OutputStream createOutputStream(long len, SessionOutputBuffer outbuffer) {
        if (len == -2) {
            return new ChunkedOutputStream(2048, outbuffer);
        }
        if (len == -1) {
            return new IdentityOutputStream(outbuffer);
        }
        return new ContentLengthOutputStream(outbuffer, len);
    }

    protected OutputStream prepareOutput(HttpMessage message) throws HttpException {
        return createOutputStream(this.outgoingContentStrategy.determineLength(message), this.outbuffer);
    }

    protected InputStream createInputStream(long len, SessionInputBuffer inbuffer) {
        if (len == -2) {
            return new ChunkedInputStream(inbuffer);
        }
        if (len == -1) {
            return new IdentityInputStream(inbuffer);
        }
        return new ContentLengthInputStream(inbuffer, len);
    }

    protected HttpEntity prepareInput(HttpMessage message) throws HttpException {
        BasicHttpEntity entity = new BasicHttpEntity();
        long len = this.incomingContentStrategy.determineLength(message);
        InputStream instream = createInputStream(len, this.inbuffer);
        if (len == -2) {
            entity.setChunked(true);
            entity.setContentLength(-1);
            entity.setContent(instream);
        } else if (len == -1) {
            entity.setChunked(false);
            entity.setContentLength(-1);
            entity.setContent(instream);
        } else {
            entity.setChunked(false);
            entity.setContentLength(len);
            entity.setContent(instream);
        }
        Header contentTypeHeader = message.getFirstHeader("Content-Type");
        if (contentTypeHeader != null) {
            entity.setContentType(contentTypeHeader);
        }
        Header contentEncodingHeader = message.getFirstHeader("Content-Encoding");
        if (contentEncodingHeader != null) {
            entity.setContentEncoding(contentEncodingHeader);
        }
        return entity;
    }

    public InetAddress getLocalAddress() {
        Socket socket = (Socket) this.socketHolder.get();
        return socket != null ? socket.getLocalAddress() : null;
    }

    public int getLocalPort() {
        Socket socket = (Socket) this.socketHolder.get();
        return socket != null ? socket.getLocalPort() : -1;
    }

    public InetAddress getRemoteAddress() {
        Socket socket = (Socket) this.socketHolder.get();
        return socket != null ? socket.getInetAddress() : null;
    }

    public int getRemotePort() {
        Socket socket = (Socket) this.socketHolder.get();
        return socket != null ? socket.getPort() : -1;
    }

    public void setSocketTimeout(int timeout) {
        Socket socket = (Socket) this.socketHolder.get();
        if (socket != null) {
            try {
                socket.setSoTimeout(timeout);
            } catch (SocketException e) {
            }
        }
    }

    public int getSocketTimeout() {
        int i = -1;
        Socket socket = (Socket) this.socketHolder.get();
        if (socket != null) {
            try {
                i = socket.getSoTimeout();
            } catch (SocketException e) {
            }
        }
        return i;
    }

    public void shutdown() throws IOException {
        Socket socket = (Socket) this.socketHolder.getAndSet(null);
        if (socket != null) {
            socket.close();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void close() throws java.io.IOException {
        /*
        r3 = this;
        r1 = r3.socketHolder;
        r2 = 0;
        r0 = r1.getAndSet(r2);
        r0 = (java.net.Socket) r0;
        if (r0 == 0) goto L_0x001e;
    L_0x000b:
        r1 = r3.inbuffer;	 Catch:{ all -> 0x001f }
        r1.clear();	 Catch:{ all -> 0x001f }
        r1 = r3.outbuffer;	 Catch:{ all -> 0x001f }
        r1.flush();	 Catch:{ all -> 0x001f }
        r0.shutdownOutput();	 Catch:{ IOException -> 0x0024, UnsupportedOperationException -> 0x0028 }
    L_0x0018:
        r0.shutdownInput();	 Catch:{ IOException -> 0x0026, UnsupportedOperationException -> 0x0028 }
    L_0x001b:
        r0.close();
    L_0x001e:
        return;
    L_0x001f:
        r1 = move-exception;
        r0.close();
        throw r1;
    L_0x0024:
        r1 = move-exception;
        goto L_0x0018;
    L_0x0026:
        r1 = move-exception;
        goto L_0x001b;
    L_0x0028:
        r1 = move-exception;
        goto L_0x001b;
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.httpclient.impl.BHttpConnectionBase.close():void");
    }

    private int fillInputBuffer(int timeout) throws IOException {
        Socket socket = (Socket) this.socketHolder.get();
        int oldtimeout = socket.getSoTimeout();
        try {
            socket.setSoTimeout(timeout);
            int fillBuffer = this.inbuffer.fillBuffer();
            return fillBuffer;
        } finally {
            socket.setSoTimeout(oldtimeout);
        }
    }

    protected boolean awaitInput(int timeout) throws IOException {
        if (this.inbuffer.hasBufferedData()) {
            return true;
        }
        fillInputBuffer(timeout);
        return this.inbuffer.hasBufferedData();
    }

    public boolean isStale() {
        if (!isOpen()) {
            return true;
        }
        try {
            if (fillInputBuffer(1) >= 0) {
                return false;
            }
            return true;
        } catch (SocketTimeoutException e) {
            return false;
        } catch (IOException e2) {
            return true;
        }
    }

    protected void incrementRequestCount() {
        this.connMetrics.incrementRequestCount();
    }

    protected void incrementResponseCount() {
        this.connMetrics.incrementResponseCount();
    }

    public HttpConnectionMetrics getMetrics() {
        return this.connMetrics;
    }

    public String toString() {
        Socket socket = (Socket) this.socketHolder.get();
        if (socket == null) {
            return "[Not bound]";
        }
        StringBuilder buffer = new StringBuilder();
        SocketAddress remoteAddress = socket.getRemoteSocketAddress();
        SocketAddress localAddress = socket.getLocalSocketAddress();
        if (!(remoteAddress == null || localAddress == null)) {
            NetUtils.formatAddress(buffer, localAddress);
            buffer.append("<->");
            NetUtils.formatAddress(buffer, remoteAddress);
        }
        return buffer.toString();
    }
}
