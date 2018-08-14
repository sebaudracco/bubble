package com.mopub.network;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import com.mopub.volley.RequestQueue;
import com.mopub.volley.toolbox.ImageLoader;
import com.mopub.volley.toolbox.ImageLoader.ImageCache;
import com.mopub.volley.toolbox.ImageLoader.ImageContainer;
import com.mopub.volley.toolbox.ImageLoader.ImageListener;

public class MaxWidthImageLoader extends ImageLoader {
    private final int mMaxImageWidth;

    public MaxWidthImageLoader(RequestQueue queue, Context context, ImageCache imageCache) {
        super(queue, imageCache);
        Display display = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        this.mMaxImageWidth = Math.min(size.x, size.y);
    }

    public ImageContainer get(String requestUrl, ImageListener listener) {
        return super.get(requestUrl, listener, this.mMaxImageWidth, 0);
    }
}
