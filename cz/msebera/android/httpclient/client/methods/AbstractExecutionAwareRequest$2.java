package cz.msebera.android.httpclient.client.methods;

import cz.msebera.android.httpclient.concurrent.Cancellable;
import cz.msebera.android.httpclient.conn.ConnectionReleaseTrigger;
import java.io.IOException;

class AbstractExecutionAwareRequest$2 implements Cancellable {
    final /* synthetic */ AbstractExecutionAwareRequest this$0;
    final /* synthetic */ ConnectionReleaseTrigger val$releaseTrigger;

    AbstractExecutionAwareRequest$2(AbstractExecutionAwareRequest this$0, ConnectionReleaseTrigger connectionReleaseTrigger) {
        this.this$0 = this$0;
        this.val$releaseTrigger = connectionReleaseTrigger;
    }

    public boolean cancel() {
        try {
            this.val$releaseTrigger.abortConnection();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
