package cz.msebera.android.httpclient.impl.conn.tsccm;

import cz.msebera.android.httpclient.conn.ClientConnectionRequest;
import cz.msebera.android.httpclient.conn.ConnectionPoolTimeoutException;
import cz.msebera.android.httpclient.conn.ManagedClientConnection;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.util.Args;
import java.util.concurrent.TimeUnit;

class ThreadSafeClientConnManager$1 implements ClientConnectionRequest {
    final /* synthetic */ ThreadSafeClientConnManager this$0;
    final /* synthetic */ PoolEntryRequest val$poolRequest;
    final /* synthetic */ HttpRoute val$route;

    ThreadSafeClientConnManager$1(ThreadSafeClientConnManager this$0, PoolEntryRequest poolEntryRequest, HttpRoute httpRoute) {
        this.this$0 = this$0;
        this.val$poolRequest = poolEntryRequest;
        this.val$route = httpRoute;
    }

    public void abortRequest() {
        this.val$poolRequest.abortRequest();
    }

    public ManagedClientConnection getConnection(long timeout, TimeUnit tunit) throws InterruptedException, ConnectionPoolTimeoutException {
        Args.notNull(this.val$route, "Route");
        if (this.this$0.log.isDebugEnabled()) {
            this.this$0.log.debug("Get connection: " + this.val$route + ", timeout = " + timeout);
        }
        return new BasicPooledConnAdapter(this.this$0, this.val$poolRequest.getPoolEntry(timeout, tunit));
    }
}
