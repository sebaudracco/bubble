package com.squareup.picasso;

import android.graphics.Bitmap;
import java.io.InputStream;

public class Downloader$Response {
    final Bitmap bitmap;
    final boolean cached;
    final long contentLength;
    final InputStream stream;

    @Deprecated
    public Downloader$Response(Bitmap bitmap, boolean loadedFromCache) {
        if (bitmap == null) {
            throw new IllegalArgumentException("Bitmap may not be null.");
        }
        this.stream = null;
        this.bitmap = bitmap;
        this.cached = loadedFromCache;
        this.contentLength = -1;
    }

    @Deprecated
    public Downloader$Response(InputStream stream, boolean loadedFromCache) {
        this(stream, loadedFromCache, -1);
    }

    @Deprecated
    public Downloader$Response(Bitmap bitmap, boolean loadedFromCache, long contentLength) {
        this(bitmap, loadedFromCache);
    }

    public Downloader$Response(InputStream stream, boolean loadedFromCache, long contentLength) {
        if (stream == null) {
            throw new IllegalArgumentException("Stream may not be null.");
        }
        this.stream = stream;
        this.bitmap = null;
        this.cached = loadedFromCache;
        this.contentLength = contentLength;
    }

    public InputStream getInputStream() {
        return this.stream;
    }

    @Deprecated
    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public long getContentLength() {
        return this.contentLength;
    }
}
