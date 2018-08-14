package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.cache.InputLimit;
import cz.msebera.android.httpclient.client.cache.Resource;
import cz.msebera.android.httpclient.client.cache.ResourceFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Immutable
public class HeapResourceFactory implements ResourceFactory {
    public Resource generate(String requestId, InputStream instream, InputLimit limit) throws IOException {
        ByteArrayOutputStream outstream = new ByteArrayOutputStream();
        byte[] buf = new byte[2048];
        long total = 0;
        while (true) {
            int l = instream.read(buf);
            if (l == -1) {
                break;
            }
            outstream.write(buf, 0, l);
            total += (long) l;
            if (limit != null && total > limit.getValue()) {
                break;
            }
        }
        limit.reached();
        return createResource(outstream.toByteArray());
    }

    public Resource copy(String requestId, Resource resource) throws IOException {
        byte[] body;
        if (resource instanceof HeapResource) {
            body = ((HeapResource) resource).getByteArray();
        } else {
            ByteArrayOutputStream outstream = new ByteArrayOutputStream();
            IOUtils.copyAndClose(resource.getInputStream(), outstream);
            body = outstream.toByteArray();
        }
        return createResource(body);
    }

    Resource createResource(byte[] buf) {
        return new HeapResource(buf);
    }
}
