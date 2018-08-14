package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.HttpEntityWrapper;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class EntityEnclosingRequestWrapper$EntityWrapper extends HttpEntityWrapper {
    final /* synthetic */ EntityEnclosingRequestWrapper this$0;

    EntityEnclosingRequestWrapper$EntityWrapper(EntityEnclosingRequestWrapper this$0, HttpEntity entity) {
        this.this$0 = this$0;
        super(entity);
    }

    public void consumeContent() throws IOException {
        EntityEnclosingRequestWrapper.access$002(this.this$0, true);
        super.consumeContent();
    }

    public InputStream getContent() throws IOException {
        EntityEnclosingRequestWrapper.access$002(this.this$0, true);
        return super.getContent();
    }

    public void writeTo(OutputStream outstream) throws IOException {
        EntityEnclosingRequestWrapper.access$002(this.this$0, true);
        super.writeTo(outstream);
    }
}
