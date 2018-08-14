package cz.msebera.android.httpclient.conn.scheme;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.util.Args;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
@Deprecated
public final class SchemeRegistry {
    private final ConcurrentHashMap<String, Scheme> registeredSchemes = new ConcurrentHashMap();

    public final Scheme getScheme(String name) {
        Scheme found = get(name);
        if (found != null) {
            return found;
        }
        throw new IllegalStateException("Scheme '" + name + "' not registered.");
    }

    public final Scheme getScheme(HttpHost host) {
        Args.notNull(host, "Host");
        return getScheme(host.getSchemeName());
    }

    public final Scheme get(String name) {
        Args.notNull(name, "Scheme name");
        return (Scheme) this.registeredSchemes.get(name);
    }

    public final Scheme register(Scheme sch) {
        Args.notNull(sch, "Scheme");
        return (Scheme) this.registeredSchemes.put(sch.getName(), sch);
    }

    public final Scheme unregister(String name) {
        Args.notNull(name, "Scheme name");
        return (Scheme) this.registeredSchemes.remove(name);
    }

    public final List<String> getSchemeNames() {
        return new ArrayList(this.registeredSchemes.keySet());
    }

    public void setItems(Map<String, Scheme> map) {
        if (map != null) {
            this.registeredSchemes.clear();
            this.registeredSchemes.putAll(map);
        }
    }
}
