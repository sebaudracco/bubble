package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.conn.ClientConnectionRequest;
import cz.msebera.android.httpclient.conn.ManagedClientConnection;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import java.util.concurrent.TimeUnit;

class SingleClientConnManager$1 implements ClientConnectionRequest {
    final /* synthetic */ SingleClientConnManager this$0;
    final /* synthetic */ HttpRoute val$route;
    final /* synthetic */ Object val$state;

    SingleClientConnManager$1(SingleClientConnManager this$0, HttpRoute httpRoute, Object obj) {
        this.this$0 = this$0;
        this.val$route = httpRoute;
        this.val$state = obj;
    }

    public void abortRequest() {
    }

    public ManagedClientConnection getConnection(long timeout, TimeUnit tunit) {
        return this.this$0.getConnection(this.val$route, this.val$state);
    }
}
