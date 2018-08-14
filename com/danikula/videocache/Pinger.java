package com.danikula.videocache;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Pinger {
    private static final Logger LOG = LoggerFactory.getLogger("Pinger");
    private static final String PING_REQUEST = "ping";
    private static final String PING_RESPONSE = "ping ok";
    private final String host;
    private final ExecutorService pingExecutor = Executors.newSingleThreadExecutor();
    private final int port;

    private class PingCallable implements Callable<Boolean> {
        private PingCallable() {
        }

        public Boolean call() throws Exception {
            return Boolean.valueOf(Pinger.this.pingServer());
        }
    }

    Pinger(String host, int port) {
        this.host = (String) Preconditions.checkNotNull(host);
        this.port = port;
    }

    boolean ping(int maxAttempts, int startTimeout) {
        boolean z;
        Exception e;
        if (maxAttempts >= 1) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z);
        if (startTimeout > 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z);
        int timeout = startTimeout;
        int attempts = 0;
        while (attempts < maxAttempts) {
            try {
                if (((Boolean) this.pingExecutor.submit(new PingCallable()).get((long) timeout, TimeUnit.MILLISECONDS)).booleanValue()) {
                    return true;
                }
                attempts++;
                timeout *= 2;
            } catch (TimeoutException e2) {
                LOG.warn("Error pinging server (attempt: " + attempts + ", timeout: " + timeout + "). ");
            } catch (InterruptedException e3) {
                e = e3;
                LOG.error("Error pinging server due to unexpected error", e);
            } catch (ExecutionException e4) {
                e = e4;
                LOG.error("Error pinging server due to unexpected error", e);
            }
        }
        String error = String.format(Locale.US, "Error pinging server (attempts: %d, max timeout: %d). If you see this message, please, report at https://github.com/danikula/AndroidVideoCache/issues/134. Default proxies are: %s", new Object[]{Integer.valueOf(attempts), Integer.valueOf(timeout / 2), getDefaultProxies()});
        LOG.error(error, new ProxyCacheException(error));
        return false;
    }

    private List<Proxy> getDefaultProxies() {
        try {
            return ProxySelector.getDefault().select(new URI(getPingUrl()));
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }

    boolean isPingRequest(String request) {
        return PING_REQUEST.equals(request);
    }

    void responseToPing(Socket socket) throws IOException {
        OutputStream out = socket.getOutputStream();
        out.write("HTTP/1.1 200 OK\n\n".getBytes());
        out.write(PING_RESPONSE.getBytes());
    }

    private boolean pingServer() throws ProxyCacheException {
        boolean equals;
        HttpUrlSource source = new HttpUrlSource(getPingUrl());
        try {
            byte[] expectedResponse = PING_RESPONSE.getBytes();
            source.open(0);
            byte[] response = new byte[expectedResponse.length];
            source.read(response);
            equals = Arrays.equals(expectedResponse, response);
            LOG.info("Ping response: `" + new String(response) + "`, pinged? " + equals);
        } catch (ProxyCacheException e) {
            LOG.error("Error reading ping response", e);
            equals = false;
        } finally {
            source.close();
        }
        return equals;
    }

    private String getPingUrl() {
        return String.format(Locale.US, "http://%s:%d/%s", new Object[]{this.host, Integer.valueOf(this.port), PING_REQUEST});
    }
}
