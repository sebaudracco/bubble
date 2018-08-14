package com.danikula.videocache;

import android.content.Context;
import android.net.Uri;
import com.danikula.videocache.file.DiskUsage;
import com.danikula.videocache.file.FileNameGenerator;
import com.danikula.videocache.file.Md5FileNameGenerator;
import com.danikula.videocache.file.TotalCountLruDiskUsage;
import com.danikula.videocache.file.TotalSizeLruDiskUsage;
import com.danikula.videocache.sourcestorage.SourceInfoStorage;
import com.danikula.videocache.sourcestorage.SourceInfoStorageFactory;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpProxyCacheServer {
    private static final Logger LOG = LoggerFactory.getLogger("HttpProxyCacheServer");
    private static final String PROXY_HOST = "127.0.0.1";
    private final Object clientsLock;
    private final Map<String, HttpProxyCacheServerClients> clientsMap;
    private final Config config;
    private final Pinger pinger;
    private final int port;
    private final ServerSocket serverSocket;
    private final ExecutorService socketProcessor;
    private final Thread waitConnectionThread;

    public static final class Builder {
        private static final long DEFAULT_MAX_SIZE = 536870912;
        private File cacheRoot;
        private DiskUsage diskUsage = new TotalSizeLruDiskUsage(DEFAULT_MAX_SIZE);
        private FileNameGenerator fileNameGenerator = new Md5FileNameGenerator();
        private SourceInfoStorage sourceInfoStorage;

        public Builder(Context context) {
            this.sourceInfoStorage = SourceInfoStorageFactory.newSourceInfoStorage(context);
            this.cacheRoot = StorageUtils.getIndividualCacheDirectory(context);
        }

        public Builder cacheDirectory(File file) {
            this.cacheRoot = (File) Preconditions.checkNotNull(file);
            return this;
        }

        public Builder fileNameGenerator(FileNameGenerator fileNameGenerator) {
            this.fileNameGenerator = (FileNameGenerator) Preconditions.checkNotNull(fileNameGenerator);
            return this;
        }

        public Builder maxCacheSize(long maxSize) {
            this.diskUsage = new TotalSizeLruDiskUsage(maxSize);
            return this;
        }

        public Builder maxCacheFilesCount(int count) {
            this.diskUsage = new TotalCountLruDiskUsage(count);
            return this;
        }

        public Builder diskUsage(DiskUsage diskUsage) {
            this.diskUsage = (DiskUsage) Preconditions.checkNotNull(diskUsage);
            return this;
        }

        public HttpProxyCacheServer build() {
            return new HttpProxyCacheServer(buildConfig());
        }

        private Config buildConfig() {
            return new Config(this.cacheRoot, this.fileNameGenerator, this.diskUsage, this.sourceInfoStorage);
        }
    }

    private final class SocketProcessorRunnable implements Runnable {
        private final Socket socket;

        public SocketProcessorRunnable(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            HttpProxyCacheServer.this.processSocket(this.socket);
        }
    }

    private final class WaitRequestsRunnable implements Runnable {
        private final CountDownLatch startSignal;

        public WaitRequestsRunnable(CountDownLatch startSignal) {
            this.startSignal = startSignal;
        }

        public void run() {
            this.startSignal.countDown();
            HttpProxyCacheServer.this.waitForRequest();
        }
    }

    public HttpProxyCacheServer(Context context) {
        this(new Builder(context).buildConfig());
    }

    private HttpProxyCacheServer(Config config) {
        Exception e;
        this.clientsLock = new Object();
        this.socketProcessor = Executors.newFixedThreadPool(8);
        this.clientsMap = new ConcurrentHashMap();
        this.config = (Config) Preconditions.checkNotNull(config);
        try {
            this.serverSocket = new ServerSocket(0, 8, InetAddress.getByName(PROXY_HOST));
            this.port = this.serverSocket.getLocalPort();
            IgnoreHostProxySelector.install(PROXY_HOST, this.port);
            CountDownLatch startSignal = new CountDownLatch(1);
            this.waitConnectionThread = new Thread(new WaitRequestsRunnable(startSignal));
            this.waitConnectionThread.start();
            startSignal.await();
            this.pinger = new Pinger(PROXY_HOST, this.port);
            LOG.info("Proxy cache server started. Is it alive? " + isAlive());
        } catch (IOException e2) {
            e = e2;
            this.socketProcessor.shutdown();
            throw new IllegalStateException("Error starting local proxy server", e);
        } catch (InterruptedException e3) {
            e = e3;
            this.socketProcessor.shutdown();
            throw new IllegalStateException("Error starting local proxy server", e);
        }
    }

    public String getProxyUrl(String url) {
        return getProxyUrl(url, true);
    }

    public String getProxyUrl(String url, boolean allowCachedFileUri) {
        if (!allowCachedFileUri || !isCached(url)) {
            return isAlive() ? appendToProxyUrl(url) : url;
        } else {
            File cacheFile = getCacheFile(url);
            touchFileSafely(cacheFile);
            return Uri.fromFile(cacheFile).toString();
        }
    }

    public void registerCacheListener(CacheListener cacheListener, String url) {
        Preconditions.checkAllNotNull(cacheListener, url);
        synchronized (this.clientsLock) {
            try {
                getClients(url).registerCacheListener(cacheListener);
            } catch (ProxyCacheException e) {
                LOG.warn("Error registering cache listener", e);
            }
        }
    }

    public void unregisterCacheListener(CacheListener cacheListener, String url) {
        Preconditions.checkAllNotNull(cacheListener, url);
        synchronized (this.clientsLock) {
            try {
                getClients(url).unregisterCacheListener(cacheListener);
            } catch (ProxyCacheException e) {
                LOG.warn("Error registering cache listener", e);
            }
        }
    }

    public void unregisterCacheListener(CacheListener cacheListener) {
        Preconditions.checkNotNull(cacheListener);
        synchronized (this.clientsLock) {
            for (HttpProxyCacheServerClients clients : this.clientsMap.values()) {
                clients.unregisterCacheListener(cacheListener);
            }
        }
    }

    public boolean isCached(String url) {
        Preconditions.checkNotNull(url, "Url can't be null!");
        return getCacheFile(url).exists();
    }

    public void shutdown() {
        LOG.info("Shutdown proxy server");
        shutdownClients();
        this.config.sourceInfoStorage.release();
        this.waitConnectionThread.interrupt();
        try {
            if (!this.serverSocket.isClosed()) {
                this.serverSocket.close();
            }
        } catch (IOException e) {
            onError(new ProxyCacheException("Error shutting down proxy server", e));
        }
    }

    private boolean isAlive() {
        return this.pinger.ping(3, 70);
    }

    private String appendToProxyUrl(String url) {
        return String.format(Locale.US, "http://%s:%d/%s", new Object[]{PROXY_HOST, Integer.valueOf(this.port), ProxyCacheUtils.encode(url)});
    }

    private File getCacheFile(String url) {
        return new File(this.config.cacheRoot, this.config.fileNameGenerator.generate(url));
    }

    private void touchFileSafely(File cacheFile) {
        try {
            this.config.diskUsage.touch(cacheFile);
        } catch (IOException e) {
            LOG.error("Error touching file " + cacheFile, e);
        }
    }

    private void shutdownClients() {
        synchronized (this.clientsLock) {
            for (HttpProxyCacheServerClients clients : this.clientsMap.values()) {
                clients.shutdown();
            }
            this.clientsMap.clear();
        }
    }

    private void waitForRequest() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Socket socket = this.serverSocket.accept();
                LOG.debug("Accept new socket " + socket);
                this.socketProcessor.submit(new SocketProcessorRunnable(socket));
            } catch (IOException e) {
                onError(new ProxyCacheException("Error during waiting connection", e));
                return;
            }
        }
    }

    private void processSocket(Socket socket) {
        Exception e;
        try {
            GetRequest request = GetRequest.read(socket.getInputStream());
            LOG.debug("Request to cache proxy:" + request);
            String url = ProxyCacheUtils.decode(request.uri);
            if (this.pinger.isPingRequest(url)) {
                this.pinger.responseToPing(socket);
            } else {
                getClients(url).processRequest(request, socket);
            }
            releaseSocket(socket);
            LOG.debug("Opened connections: " + getClientsCount());
        } catch (SocketException e2) {
            LOG.debug("Closing socket… Socket is closed by client.");
            releaseSocket(socket);
            LOG.debug("Opened connections: " + getClientsCount());
        } catch (Exception e3) {
            e = e3;
            onError(new ProxyCacheException("Error processing request", e));
            releaseSocket(socket);
            LOG.debug("Opened connections: " + getClientsCount());
        } catch (Exception e32) {
            e = e32;
            onError(new ProxyCacheException("Error processing request", e));
            releaseSocket(socket);
            LOG.debug("Opened connections: " + getClientsCount());
        } catch (Throwable th) {
            releaseSocket(socket);
            LOG.debug("Opened connections: " + getClientsCount());
        }
    }

    private HttpProxyCacheServerClients getClients(String url) throws ProxyCacheException {
        HttpProxyCacheServerClients clients;
        synchronized (this.clientsLock) {
            clients = (HttpProxyCacheServerClients) this.clientsMap.get(url);
            if (clients == null) {
                clients = new HttpProxyCacheServerClients(url, this.config);
                this.clientsMap.put(url, clients);
            }
        }
        return clients;
    }

    private int getClientsCount() {
        int count;
        synchronized (this.clientsLock) {
            count = 0;
            for (HttpProxyCacheServerClients clients : this.clientsMap.values()) {
                count += clients.getClientsCount();
            }
        }
        return count;
    }

    private void releaseSocket(Socket socket) {
        closeSocketInput(socket);
        closeSocketOutput(socket);
        closeSocket(socket);
    }

    private void closeSocketInput(Socket socket) {
        try {
            if (!socket.isInputShutdown()) {
                socket.shutdownInput();
            }
        } catch (SocketException e) {
            LOG.debug("Releasing input stream… Socket is closed by client.");
        } catch (IOException e2) {
            onError(new ProxyCacheException("Error closing socket input stream", e2));
        }
    }

    private void closeSocketOutput(Socket socket) {
        try {
            if (!socket.isOutputShutdown()) {
                socket.shutdownOutput();
            }
        } catch (IOException e) {
            LOG.warn("Failed to close socket on proxy side: {}. It seems client have already closed connection.", e.getMessage());
        }
    }

    private void closeSocket(Socket socket) {
        try {
            if (!socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            onError(new ProxyCacheException("Error closing socket", e));
        }
    }

    private void onError(Throwable e) {
        LOG.error("HttpProxyCacheServer error", e);
    }
}
