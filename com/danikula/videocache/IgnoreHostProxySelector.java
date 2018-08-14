package com.danikula.videocache;

import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

class IgnoreHostProxySelector extends ProxySelector {
    private static final List<Proxy> NO_PROXY_LIST = Arrays.asList(new Proxy[]{Proxy.NO_PROXY});
    private final ProxySelector defaultProxySelector;
    private final String hostToIgnore;
    private final int portToIgnore;

    IgnoreHostProxySelector(ProxySelector defaultProxySelector, String hostToIgnore, int portToIgnore) {
        this.defaultProxySelector = (ProxySelector) Preconditions.checkNotNull(defaultProxySelector);
        this.hostToIgnore = (String) Preconditions.checkNotNull(hostToIgnore);
        this.portToIgnore = portToIgnore;
    }

    static void install(String hostToIgnore, int portToIgnore) {
        ProxySelector.setDefault(new IgnoreHostProxySelector(ProxySelector.getDefault(), hostToIgnore, portToIgnore));
    }

    public List<Proxy> select(URI uri) {
        boolean ignored = this.hostToIgnore.equals(uri.getHost()) && this.portToIgnore == uri.getPort();
        return ignored ? NO_PROXY_LIST : this.defaultProxySelector.select(uri);
    }

    public void connectFailed(URI uri, SocketAddress address, IOException failure) {
        this.defaultProxySelector.connectFailed(uri, address, failure);
    }
}
