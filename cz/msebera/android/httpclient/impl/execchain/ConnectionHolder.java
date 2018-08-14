package cz.msebera.android.httpclient.impl.execchain;

import cz.msebera.android.httpclient.HttpClientConnection;
import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.concurrent.Cancellable;
import cz.msebera.android.httpclient.conn.ConnectionReleaseTrigger;
import cz.msebera.android.httpclient.conn.HttpClientConnectionManager;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@ThreadSafe
class ConnectionHolder implements ConnectionReleaseTrigger, Cancellable, Closeable {
    public HttpClientAndroidLog log;
    private final HttpClientConnection managedConn;
    private final HttpClientConnectionManager manager;
    private volatile boolean released;
    private volatile boolean reusable;
    private volatile Object state;
    private volatile TimeUnit tunit;
    private volatile long validDuration;

    public ConnectionHolder(HttpClientAndroidLog log, HttpClientConnectionManager manager, HttpClientConnection managedConn) {
        this.log = log;
        this.manager = manager;
        this.managedConn = managedConn;
    }

    public boolean isReusable() {
        return this.reusable;
    }

    public void markReusable() {
        this.reusable = true;
    }

    public void markNonReusable() {
        this.reusable = false;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public void setValidFor(long duration, TimeUnit tunit) {
        synchronized (this.managedConn) {
            this.validDuration = duration;
            this.tunit = tunit;
        }
    }

    public void releaseConnection() {
        synchronized (this.managedConn) {
            if (this.released) {
                return;
            }
            this.released = true;
            if (this.reusable) {
                this.manager.releaseConnection(this.managedConn, this.state, this.validDuration, this.tunit);
            } else {
                try {
                    this.managedConn.close();
                    this.log.debug("Connection discarded");
                    this.manager.releaseConnection(this.managedConn, null, 0, TimeUnit.MILLISECONDS);
                } catch (IOException ex) {
                    if (this.log.isDebugEnabled()) {
                        this.log.debug(ex.getMessage(), ex);
                    }
                    this.manager.releaseConnection(this.managedConn, null, 0, TimeUnit.MILLISECONDS);
                } catch (Throwable th) {
                    Throwable th2 = th;
                    this.manager.releaseConnection(this.managedConn, null, 0, TimeUnit.MILLISECONDS);
                }
            }
        }
    }

    public void abortConnection() {
        synchronized (this.managedConn) {
            if (this.released) {
                return;
            }
            this.released = true;
            try {
                this.managedConn.shutdown();
                this.log.debug("Connection discarded");
                this.manager.releaseConnection(this.managedConn, null, 0, TimeUnit.MILLISECONDS);
            } catch (IOException ex) {
                if (this.log.isDebugEnabled()) {
                    this.log.debug(ex.getMessage(), ex);
                }
                this.manager.releaseConnection(this.managedConn, null, 0, TimeUnit.MILLISECONDS);
            } catch (Throwable th) {
                Throwable th2 = th;
                this.manager.releaseConnection(this.managedConn, null, 0, TimeUnit.MILLISECONDS);
            }
        }
    }

    public boolean cancel() {
        boolean alreadyReleased = this.released;
        this.log.debug("Cancelling request execution");
        abortConnection();
        return !alreadyReleased;
    }

    public boolean isReleased() {
        return this.released;
    }

    public void close() throws IOException {
        abortConnection();
    }
}
