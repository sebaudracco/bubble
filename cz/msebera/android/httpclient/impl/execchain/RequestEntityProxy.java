package cz.msebera.android.httpclient.impl.execchain;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@NotThreadSafe
class RequestEntityProxy implements HttpEntity {
    private boolean consumed = false;
    private final HttpEntity original;

    static void enhance(HttpEntityEnclosingRequest request) {
        HttpEntity entity = request.getEntity();
        if (entity != null && !entity.isRepeatable() && !isEnhanced(entity)) {
            request.setEntity(new RequestEntityProxy(entity));
        }
    }

    static boolean isEnhanced(HttpEntity entity) {
        return entity instanceof RequestEntityProxy;
    }

    static boolean isRepeatable(HttpRequest request) {
        if (!(request instanceof HttpEntityEnclosingRequest)) {
            return true;
        }
        HttpEntity entity = ((HttpEntityEnclosingRequest) request).getEntity();
        if (entity == null) {
            return true;
        }
        if (!isEnhanced(entity) || ((RequestEntityProxy) entity).isConsumed()) {
            return entity.isRepeatable();
        }
        return true;
    }

    RequestEntityProxy(HttpEntity original) {
        this.original = original;
    }

    public HttpEntity getOriginal() {
        return this.original;
    }

    public boolean isConsumed() {
        return this.consumed;
    }

    public boolean isRepeatable() {
        return this.original.isRepeatable();
    }

    public boolean isChunked() {
        return this.original.isChunked();
    }

    public long getContentLength() {
        return this.original.getContentLength();
    }

    public Header getContentType() {
        return this.original.getContentType();
    }

    public Header getContentEncoding() {
        return this.original.getContentEncoding();
    }

    public InputStream getContent() throws IOException, IllegalStateException {
        return this.original.getContent();
    }

    public void writeTo(OutputStream outstream) throws IOException {
        this.consumed = true;
        this.original.writeTo(outstream);
    }

    public boolean isStreaming() {
        return this.original.isStreaming();
    }

    @Deprecated
    public void consumeContent() throws IOException {
        this.consumed = true;
        this.original.consumeContent();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("RequestEntityProxy{");
        sb.append(this.original);
        sb.append('}');
        return sb.toString();
    }
}
