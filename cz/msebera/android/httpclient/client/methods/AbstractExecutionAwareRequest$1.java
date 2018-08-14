package cz.msebera.android.httpclient.client.methods;

import cz.msebera.android.httpclient.concurrent.Cancellable;
import cz.msebera.android.httpclient.conn.ClientConnectionRequest;

class AbstractExecutionAwareRequest$1 implements Cancellable {
    final /* synthetic */ AbstractExecutionAwareRequest this$0;
    final /* synthetic */ ClientConnectionRequest val$connRequest;

    AbstractExecutionAwareRequest$1(AbstractExecutionAwareRequest this$0, ClientConnectionRequest clientConnectionRequest) {
        this.this$0 = this$0;
        this.val$connRequest = clientConnectionRequest;
    }

    public boolean cancel() {
        this.val$connRequest.abortRequest();
        return true;
    }
}
