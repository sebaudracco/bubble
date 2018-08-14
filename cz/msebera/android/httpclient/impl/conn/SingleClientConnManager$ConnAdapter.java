package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.conn.routing.HttpRoute;

protected class SingleClientConnManager$ConnAdapter extends AbstractPooledConnAdapter {
    final /* synthetic */ SingleClientConnManager this$0;

    protected SingleClientConnManager$ConnAdapter(SingleClientConnManager this$0, SingleClientConnManager$PoolEntry entry, HttpRoute route) {
        this.this$0 = this$0;
        super(this$0, entry);
        markReusable();
        entry.route = route;
    }
}
