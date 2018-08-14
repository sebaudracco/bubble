package com.mopub.common;

import com.mopub.common.util.ResponseHeader;
import com.mopub.common.util.Streams;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

public class DownloadResponse {
    private byte[] mBytes = new byte[0];
    private final long mContentLength;
    private final Header[] mHeaders;
    private final int mStatusCode;

    public DownloadResponse(HttpResponse httpResponse) throws Exception {
        Throwable th;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BufferedInputStream inputStream = null;
        try {
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                BufferedInputStream inputStream2 = new BufferedInputStream(httpEntity.getContent());
                try {
                    Streams.copyContent(inputStream2, outputStream);
                    this.mBytes = outputStream.toByteArray();
                    inputStream = inputStream2;
                } catch (Throwable th2) {
                    th = th2;
                    inputStream = inputStream2;
                    Streams.closeStream(inputStream);
                    Streams.closeStream(outputStream);
                    throw th;
                }
            }
            Streams.closeStream(inputStream);
            Streams.closeStream(outputStream);
            this.mStatusCode = httpResponse.getStatusLine().getStatusCode();
            this.mContentLength = (long) this.mBytes.length;
            this.mHeaders = httpResponse.getAllHeaders();
        } catch (Throwable th3) {
            th = th3;
            Streams.closeStream(inputStream);
            Streams.closeStream(outputStream);
            throw th;
        }
    }

    public byte[] getByteArray() {
        return this.mBytes;
    }

    public int getStatusCode() {
        return this.mStatusCode;
    }

    public long getContentLength() {
        return this.mContentLength;
    }

    public String getFirstHeader(ResponseHeader responseHeader) {
        for (Header header : this.mHeaders) {
            if (header.getName().equalsIgnoreCase(responseHeader.getKey())) {
                return header.getValue();
            }
        }
        return null;
    }
}
