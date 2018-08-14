package cz.msebera.android.httpclient.impl.conn.tsccm;

import cz.msebera.android.httpclient.conn.ConnectionPoolTimeoutException;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import java.util.concurrent.TimeUnit;

class ConnPoolByRoute$1 implements PoolEntryRequest {
    final /* synthetic */ ConnPoolByRoute this$0;
    final /* synthetic */ WaitingThreadAborter val$aborter;
    final /* synthetic */ HttpRoute val$route;
    final /* synthetic */ Object val$state;

    ConnPoolByRoute$1(ConnPoolByRoute this$0, WaitingThreadAborter waitingThreadAborter, HttpRoute httpRoute, Object obj) {
        this.this$0 = this$0;
        this.val$aborter = waitingThreadAborter;
        this.val$route = httpRoute;
        this.val$state = obj;
    }

    public void abortRequest() {
        ConnPoolByRoute.access$000(this.this$0).lock();
        try {
            this.val$aborter.abort();
        } finally {
            ConnPoolByRoute.access$000(this.this$0).unlock();
        }
    }

    public BasicPoolEntry getPoolEntry(long timeout, TimeUnit tunit) throws InterruptedException, ConnectionPoolTimeoutException {
        return this.this$0.getEntryBlocking(this.val$route, this.val$state, timeout, tunit, this.val$aborter);
    }
}
