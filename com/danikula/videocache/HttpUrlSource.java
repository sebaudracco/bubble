package com.danikula.videocache;

import android.text.TextUtils;
import com.danikula.videocache.sourcestorage.SourceInfoStorage;
import com.danikula.videocache.sourcestorage.SourceInfoStorageFactory;
import cz.msebera.android.httpclient.HttpHeaders;
import cz.msebera.android.httpclient.HttpStatus;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUrlSource implements Source {
    private static final Logger LOG = LoggerFactory.getLogger("HttpUrlSource");
    private static final int MAX_REDIRECTS = 5;
    private HttpURLConnection connection;
    private InputStream inputStream;
    private SourceInfo sourceInfo;
    private final SourceInfoStorage sourceInfoStorage;

    private void fetchContentInfo() throws com.danikula.videocache.ProxyCacheException {
        /* JADX: method processing error */
/*
Error: java.util.NoSuchElementException
	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1431)
	at java.util.HashMap$KeyIterator.next(HashMap.java:1453)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.applyRemove(BlockFinallyExtract.java:535)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.extractFinally(BlockFinallyExtract.java:175)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.processExceptionHandler(BlockFinallyExtract.java:79)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:51)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r9 = this;
        r6 = LOG;
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r8 = "Read content info from ";
        r7 = r7.append(r8);
        r8 = r9.sourceInfo;
        r8 = r8.url;
        r7 = r7.append(r8);
        r7 = r7.toString();
        r6.debug(r7);
        r5 = 0;
        r1 = 0;
        r6 = 0;
        r8 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r5 = r9.openConnection(r6, r8);	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        r2 = r9.getContentLength(r5);	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        r4 = r5.getContentType();	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        r1 = r5.getInputStream();	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        r6 = new com.danikula.videocache.SourceInfo;	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        r7 = r9.sourceInfo;	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        r7 = r7.url;	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        r6.<init>(r7, r2, r4);	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        r9.sourceInfo = r6;	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        r6 = r9.sourceInfoStorage;	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        r7 = r9.sourceInfo;	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        r7 = r7.url;	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        r8 = r9.sourceInfo;	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        r6.put(r7, r8);	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        r6 = LOG;	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        r7 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        r7.<init>();	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        r8 = "Source info fetched: ";	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        r7 = r7.append(r8);	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        r8 = r9.sourceInfo;	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        r7 = r7.append(r8);	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        r7 = r7.toString();	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        r6.debug(r7);	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        com.danikula.videocache.ProxyCacheUtils.close(r1);
        if (r5 == 0) goto L_0x006c;
    L_0x0069:
        r5.disconnect();
    L_0x006c:
        return;
    L_0x006d:
        r0 = move-exception;
        r6 = LOG;	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        r7 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        r7.<init>();	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        r8 = "Error fetching info from ";	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        r7 = r7.append(r8);	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        r8 = r9.sourceInfo;	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        r8 = r8.url;	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        r7 = r7.append(r8);	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        r7 = r7.toString();	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        r6.error(r7, r0);	 Catch:{ IOException -> 0x006d, all -> 0x0094 }
        com.danikula.videocache.ProxyCacheUtils.close(r1);
        if (r5 == 0) goto L_0x006c;
    L_0x0090:
        r5.disconnect();
        goto L_0x006c;
    L_0x0094:
        r6 = move-exception;
        com.danikula.videocache.ProxyCacheUtils.close(r1);
        if (r5 == 0) goto L_0x009d;
    L_0x009a:
        r5.disconnect();
    L_0x009d:
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.danikula.videocache.HttpUrlSource.fetchContentInfo():void");
    }

    public HttpUrlSource(String url) {
        this(url, SourceInfoStorageFactory.newEmptySourceInfoStorage());
    }

    public HttpUrlSource(String url, SourceInfoStorage sourceInfoStorage) {
        this.sourceInfoStorage = (SourceInfoStorage) Preconditions.checkNotNull(sourceInfoStorage);
        SourceInfo sourceInfo = sourceInfoStorage.get(url);
        if (sourceInfo == null) {
            sourceInfo = new SourceInfo(url, -2147483648L, ProxyCacheUtils.getSupposablyMime(url));
        }
        this.sourceInfo = sourceInfo;
    }

    public HttpUrlSource(HttpUrlSource source) {
        this.sourceInfo = source.sourceInfo;
        this.sourceInfoStorage = source.sourceInfoStorage;
    }

    public synchronized long length() throws ProxyCacheException {
        if (this.sourceInfo.length == -2147483648L) {
            fetchContentInfo();
        }
        return this.sourceInfo.length;
    }

    public void open(long offset) throws ProxyCacheException {
        try {
            this.connection = openConnection(offset, -1);
            String mime = this.connection.getContentType();
            this.inputStream = new BufferedInputStream(this.connection.getInputStream(), 8192);
            this.sourceInfo = new SourceInfo(this.sourceInfo.url, readSourceAvailableBytes(this.connection, offset, this.connection.getResponseCode()), mime);
            this.sourceInfoStorage.put(this.sourceInfo.url, this.sourceInfo);
        } catch (IOException e) {
            throw new ProxyCacheException("Error opening connection for " + this.sourceInfo.url + " with offset " + offset, e);
        }
    }

    private long readSourceAvailableBytes(HttpURLConnection connection, long offset, int responseCode) throws IOException {
        long contentLength = getContentLength(connection);
        if (responseCode == 200) {
            return contentLength;
        }
        return responseCode == HttpStatus.SC_PARTIAL_CONTENT ? contentLength + offset : this.sourceInfo.length;
    }

    private long getContentLength(HttpURLConnection connection) {
        String contentLengthValue = connection.getHeaderField("Content-Length");
        return contentLengthValue == null ? -1 : Long.parseLong(contentLengthValue);
    }

    public void close() throws ProxyCacheException {
        RuntimeException e;
        if (this.connection != null) {
            try {
                this.connection.disconnect();
                return;
            } catch (NullPointerException e2) {
                e = e2;
            } catch (IllegalArgumentException e3) {
                e = e3;
            } catch (ArrayIndexOutOfBoundsException e4) {
                LOG.error("Error closing connection correctly. Should happen only on Android L. If anybody know how to fix it, please visit https://github.com/danikula/AndroidVideoCache/issues/88. Until good solution is not know, just ignore this issue :(", e4);
                return;
            }
        }
        return;
        throw new RuntimeException("Wait... but why? WTF!? Really shouldn't happen any more after fixing https://github.com/danikula/AndroidVideoCache/issues/43. If you read it on your device log, please, notify me danikula@gmail.com or create issue here https://github.com/danikula/AndroidVideoCache/issues.", e);
    }

    public int read(byte[] buffer) throws ProxyCacheException {
        if (this.inputStream == null) {
            throw new ProxyCacheException("Error reading data from " + this.sourceInfo.url + ": connection is absent!");
        }
        try {
            return this.inputStream.read(buffer, 0, buffer.length);
        } catch (InterruptedIOException e) {
            throw new InterruptedProxyCacheException("Reading source " + this.sourceInfo.url + " is interrupted", e);
        } catch (IOException e2) {
            throw new ProxyCacheException("Error reading data from " + this.sourceInfo.url, e2);
        }
    }

    private HttpURLConnection openConnection(long offset, int timeout) throws IOException, ProxyCacheException {
        HttpURLConnection connection;
        int redirectCount = 0;
        String url = this.sourceInfo.url;
        boolean redirected;
        do {
            LOG.debug("Open connection " + (offset > 0 ? " with offset " + offset : "") + " to " + url);
            connection = (HttpURLConnection) new URL(url).openConnection();
            if (offset > 0) {
                connection.setRequestProperty("Range", "bytes=" + offset + "-");
            }
            if (timeout > 0) {
                connection.setConnectTimeout(timeout);
                connection.setReadTimeout(timeout);
            }
            int code = connection.getResponseCode();
            redirected = code == HttpStatus.SC_MOVED_PERMANENTLY || code == HttpStatus.SC_MOVED_TEMPORARILY || code == HttpStatus.SC_SEE_OTHER;
            if (redirected) {
                url = connection.getHeaderField(HttpHeaders.LOCATION);
                redirectCount++;
                connection.disconnect();
            }
            if (redirectCount > 5) {
                throw new ProxyCacheException("Too many redirects: " + redirectCount);
            }
        } while (redirected);
        return connection;
    }

    public synchronized String getMime() throws ProxyCacheException {
        if (TextUtils.isEmpty(this.sourceInfo.mime)) {
            fetchContentInfo();
        }
        return this.sourceInfo.mime;
    }

    public String getUrl() {
        return this.sourceInfo.url;
    }

    public String toString() {
        return "HttpUrlSource{sourceInfo='" + this.sourceInfo + "}";
    }
}
