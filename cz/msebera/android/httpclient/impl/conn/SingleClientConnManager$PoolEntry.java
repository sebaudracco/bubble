package cz.msebera.android.httpclient.impl.conn;

import java.io.IOException;

protected class SingleClientConnManager$PoolEntry extends AbstractPoolEntry {
    final /* synthetic */ SingleClientConnManager this$0;

    protected SingleClientConnManager$PoolEntry(SingleClientConnManager this$0) {
        this.this$0 = this$0;
        super(this$0.connOperator, null);
    }

    protected void close() throws IOException {
        shutdownEntry();
        if (this.connection.isOpen()) {
            this.connection.close();
        }
    }

    protected void shutdown() throws IOException {
        shutdownEntry();
        if (this.connection.isOpen()) {
            this.connection.shutdown();
        }
    }
}
