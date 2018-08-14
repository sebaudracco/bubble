package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.conn.ClientConnectionRequest;
import cz.msebera.android.httpclient.conn.ConnectionPoolTimeoutException;
import cz.msebera.android.httpclient.conn.ManagedClientConnection;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class PoolingClientConnectionManager$1 implements ClientConnectionRequest {
    final /* synthetic */ PoolingClientConnectionManager this$0;
    final /* synthetic */ Future val$future;

    PoolingClientConnectionManager$1(PoolingClientConnectionManager this$0, Future future) {
        this.this$0 = this$0;
        this.val$future = future;
    }

    public void abortRequest() {
        this.val$future.cancel(true);
    }

    public ManagedClientConnection getConnection(long timeout, TimeUnit tunit) throws InterruptedException, ConnectionPoolTimeoutException {
        return this.this$0.leaseConnection(this.val$future, timeout, tunit);
    }
}
