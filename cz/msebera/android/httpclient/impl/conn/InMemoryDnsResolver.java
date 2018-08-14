package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.conn.DnsResolver;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.util.Args;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryDnsResolver implements DnsResolver {
    private final Map<String, InetAddress[]> dnsMap = new ConcurrentHashMap();
    public HttpClientAndroidLog log = new HttpClientAndroidLog(InMemoryDnsResolver.class);

    public void add(String host, InetAddress... ips) {
        Args.notNull(host, "Host name");
        Args.notNull(ips, "Array of IP addresses");
        this.dnsMap.put(host, ips);
    }

    public InetAddress[] resolve(String host) throws UnknownHostException {
        InetAddress[] resolvedAddresses = (InetAddress[]) this.dnsMap.get(host);
        if (this.log.isInfoEnabled()) {
            this.log.info("Resolving " + host + " to " + Arrays.deepToString(resolvedAddresses));
        }
        if (resolvedAddresses != null) {
            return resolvedAddresses;
        }
        throw new UnknownHostException(host + " cannot be resolved");
    }
}
