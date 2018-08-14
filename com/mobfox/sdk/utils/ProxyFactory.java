package com.mobfox.sdk.utils;

import android.content.Context;
import com.danikula.videocache.HttpProxyCacheServer;
import com.danikula.videocache.HttpProxyCacheServer.Builder;

public class ProxyFactory {
    private static HttpProxyCacheServer sharedProxy;

    private ProxyFactory() {
    }

    public static HttpProxyCacheServer getProxy(Context context) {
        if (sharedProxy != null) {
            return sharedProxy;
        }
        HttpProxyCacheServer newProxy = newProxy(context);
        sharedProxy = newProxy;
        return newProxy;
    }

    private static HttpProxyCacheServer newProxy(Context context) {
        return new Builder(context).maxCacheSize(20971520).cacheDirectory(context.getCacheDir()).build();
    }
}
