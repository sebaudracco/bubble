package com.danikula.videocache;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.danikula.videocache.file.FileCache;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

final class HttpProxyCacheServerClients {
    private final AtomicInteger clientsCount = new AtomicInteger(0);
    private final Config config;
    private final List<CacheListener> listeners = new CopyOnWriteArrayList();
    private volatile HttpProxyCache proxyCache;
    private final CacheListener uiCacheListener;
    private final String url;

    private static final class UiListenerHandler extends Handler implements CacheListener {
        private final List<CacheListener> listeners;
        private final String url;

        public UiListenerHandler(String url, List<CacheListener> listeners) {
            super(Looper.getMainLooper());
            this.url = url;
            this.listeners = listeners;
        }

        public void onCacheAvailable(File file, String url, int percentsAvailable) {
            Message message = obtainMessage();
            message.arg1 = percentsAvailable;
            message.obj = file;
            sendMessage(message);
        }

        public void handleMessage(Message msg) {
            for (CacheListener cacheListener : this.listeners) {
                cacheListener.onCacheAvailable((File) msg.obj, this.url, msg.arg1);
            }
        }
    }

    public HttpProxyCacheServerClients(String url, Config config) {
        this.url = (String) Preconditions.checkNotNull(url);
        this.config = (Config) Preconditions.checkNotNull(config);
        this.uiCacheListener = new UiListenerHandler(url, this.listeners);
    }

    public void processRequest(GetRequest request, Socket socket) throws ProxyCacheException, IOException {
        startProcessRequest();
        try {
            this.clientsCount.incrementAndGet();
            this.proxyCache.processRequest(request, socket);
        } finally {
            finishProcessRequest();
        }
    }

    private synchronized void startProcessRequest() throws ProxyCacheException {
        this.proxyCache = this.proxyCache == null ? newHttpProxyCache() : this.proxyCache;
    }

    private synchronized void finishProcessRequest() {
        if (this.clientsCount.decrementAndGet() <= 0) {
            this.proxyCache.shutdown();
            this.proxyCache = null;
        }
    }

    public void registerCacheListener(CacheListener cacheListener) {
        this.listeners.add(cacheListener);
    }

    public void unregisterCacheListener(CacheListener cacheListener) {
        this.listeners.remove(cacheListener);
    }

    public void shutdown() {
        this.listeners.clear();
        if (this.proxyCache != null) {
            this.proxyCache.registerCacheListener(null);
            this.proxyCache.shutdown();
            this.proxyCache = null;
        }
        this.clientsCount.set(0);
    }

    public int getClientsCount() {
        return this.clientsCount.get();
    }

    private HttpProxyCache newHttpProxyCache() throws ProxyCacheException {
        HttpProxyCache httpProxyCache = new HttpProxyCache(new HttpUrlSource(this.url, this.config.sourceInfoStorage), new FileCache(this.config.generateCacheFile(this.url), this.config.diskUsage));
        httpProxyCache.registerCacheListener(this.uiCacheListener);
        return httpProxyCache;
    }
}
