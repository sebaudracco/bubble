package com.mopub.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.LruCache;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.mopub.common.ClientMetadata;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.util.DeviceUtils;
import com.mopub.volley.Network;
import com.mopub.volley.RequestQueue;
import com.mopub.volley.toolbox.BasicNetwork;
import com.mopub.volley.toolbox.DiskBasedCache;
import com.mopub.volley.toolbox.ImageLoader;
import com.mopub.volley.toolbox.ImageLoader.ImageCache;
import java.io.File;

public class Networking {
    @VisibleForTesting
    static final String CACHE_DIRECTORY_NAME = "mopub-volley-cache";
    private static final String DEFAULT_USER_AGENT = System.getProperty("http.agent");
    private static volatile MaxWidthImageLoader sMaxWidthImageLoader;
    private static volatile MoPubRequestQueue sRequestQueue;
    private static boolean sUseHttps = false;
    private static volatile String sUserAgent;

    @Nullable
    public static MoPubRequestQueue getRequestQueue() {
        return sRequestQueue;
    }

    @NonNull
    public static MoPubRequestQueue getRequestQueue(@NonNull Context context) {
        MoPubRequestQueue requestQueue = sRequestQueue;
        if (requestQueue == null) {
            synchronized (Networking.class) {
                try {
                    requestQueue = sRequestQueue;
                    if (requestQueue == null) {
                        Network network = new BasicNetwork(new RequestQueueHttpStack(getUserAgent(context.getApplicationContext()), new PlayServicesUrlRewriter(ClientMetadata.getInstance(context).getDeviceId(), context), CustomSSLSocketFactory.getDefault(10000)));
                        File volleyCacheDir = new File(context.getCacheDir().getPath() + File.separator + CACHE_DIRECTORY_NAME);
                        MoPubRequestQueue requestQueue2 = new MoPubRequestQueue(new DiskBasedCache(volleyCacheDir, (int) DeviceUtils.diskCacheSizeBytes(volleyCacheDir, 10485760)), network);
                        try {
                            sRequestQueue = requestQueue2;
                            requestQueue2.start();
                            requestQueue = requestQueue2;
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            requestQueue = requestQueue2;
                            throw th2;
                        }
                    }
                } catch (Throwable th3) {
                    th2 = th3;
                    throw th2;
                }
            }
        }
        return requestQueue;
    }

    @NonNull
    public static ImageLoader getImageLoader(@NonNull Context context) {
        MaxWidthImageLoader imageLoader = sMaxWidthImageLoader;
        if (imageLoader == null) {
            synchronized (Networking.class) {
                try {
                    imageLoader = sMaxWidthImageLoader;
                    if (imageLoader == null) {
                        RequestQueue queue = getRequestQueue(context);
                        final LruCache<String, Bitmap> imageCache = new LruCache<String, Bitmap>(DeviceUtils.memoryCacheSizeBytes(context)) {
                            protected int sizeOf(String key, Bitmap value) {
                                if (value != null) {
                                    return value.getRowBytes() * value.getHeight();
                                }
                                return super.sizeOf(key, value);
                            }
                        };
                        MaxWidthImageLoader imageLoader2 = new MaxWidthImageLoader(queue, context, new ImageCache() {
                            public Bitmap getBitmap(String key) {
                                return (Bitmap) imageCache.get(key);
                            }

                            public void putBitmap(String key, Bitmap bitmap) {
                                imageCache.put(key, bitmap);
                            }
                        });
                        try {
                            sMaxWidthImageLoader = imageLoader2;
                            imageLoader = imageLoader2;
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            imageLoader = imageLoader2;
                            throw th2;
                        }
                    }
                } catch (Throwable th3) {
                    th2 = th3;
                    throw th2;
                }
            }
        }
        return imageLoader;
    }

    @NonNull
    public static String getUserAgent(@NonNull Context context) {
        Preconditions.checkNotNull(context);
        String userAgent = sUserAgent;
        if (userAgent == null) {
            synchronized (Networking.class) {
                userAgent = sUserAgent;
                if (userAgent == null) {
                    try {
                        if (VERSION.SDK_INT >= 17) {
                            userAgent = WebSettings.getDefaultUserAgent(context);
                        } else if (Looper.myLooper() == Looper.getMainLooper()) {
                            userAgent = new WebView(context).getSettings().getUserAgentString();
                        } else {
                            userAgent = DEFAULT_USER_AGENT;
                        }
                    } catch (Exception e) {
                        userAgent = DEFAULT_USER_AGENT;
                    }
                    sUserAgent = userAgent;
                }
            }
        }
        return userAgent;
    }

    @NonNull
    public static String getCachedUserAgent() {
        String userAgent = sUserAgent;
        if (userAgent == null) {
            return DEFAULT_USER_AGENT;
        }
        return userAgent;
    }

    @VisibleForTesting
    public static synchronized void clearForTesting() {
        synchronized (Networking.class) {
            sRequestQueue = null;
            sMaxWidthImageLoader = null;
            sUserAgent = null;
        }
    }

    @VisibleForTesting
    public static synchronized void setRequestQueueForTesting(MoPubRequestQueue queue) {
        synchronized (Networking.class) {
            sRequestQueue = queue;
        }
    }

    @VisibleForTesting
    public static synchronized void setImageLoaderForTesting(MaxWidthImageLoader imageLoader) {
        synchronized (Networking.class) {
            sMaxWidthImageLoader = imageLoader;
        }
    }

    @VisibleForTesting
    public static synchronized void setUserAgentForTesting(String userAgent) {
        synchronized (Networking.class) {
            sUserAgent = userAgent;
        }
    }

    public static void useHttps(boolean useHttps) {
        sUseHttps = useHttps;
    }

    public static boolean useHttps() {
        return sUseHttps;
    }

    public static String getScheme() {
        return useHttps() ? "https" : "http";
    }

    public static String getBaseUrlScheme() {
        return "http";
    }
}
