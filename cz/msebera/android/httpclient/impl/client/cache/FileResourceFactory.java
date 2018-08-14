package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.cache.InputLimit;
import cz.msebera.android.httpclient.client.cache.Resource;
import cz.msebera.android.httpclient.client.cache.ResourceFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Immutable
public class FileResourceFactory implements ResourceFactory {
    private final File cacheDir;
    private final BasicIdGenerator idgen = new BasicIdGenerator();

    public FileResourceFactory(File cacheDir) {
        this.cacheDir = cacheDir;
    }

    private File generateUniqueCacheFile(String requestId) {
        StringBuilder buffer = new StringBuilder();
        this.idgen.generate(buffer);
        buffer.append('.');
        int len = Math.min(requestId.length(), 100);
        for (int i = 0; i < len; i++) {
            char ch = requestId.charAt(i);
            if (Character.isLetterOrDigit(ch) || ch == '.') {
                buffer.append(ch);
            } else {
                buffer.append('-');
            }
        }
        return new File(this.cacheDir, buffer.toString());
    }

    public Resource generate(String requestId, InputStream instream, InputLimit limit) throws IOException {
        File file = generateUniqueCacheFile(requestId);
        FileOutputStream outstream = new FileOutputStream(file);
        try {
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
            outstream.close();
            return new FileResource(file);
        } catch (Throwable th) {
            outstream.close();
        }
    }

    public Resource copy(String requestId, Resource resource) throws IOException {
        File file = generateUniqueCacheFile(requestId);
        if (resource instanceof FileResource) {
            IOUtils.copyFile(((FileResource) resource).getFile(), file);
        } else {
            IOUtils.copyAndClose(resource.getInputStream(), new FileOutputStream(file));
        }
        return new FileResource(file);
    }
}
