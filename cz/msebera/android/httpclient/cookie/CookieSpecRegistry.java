package cz.msebera.android.httpclient.cookie;

import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.config.Lookup;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

@ThreadSafe
@Deprecated
public final class CookieSpecRegistry implements Lookup<CookieSpecProvider> {
    private final ConcurrentHashMap<String, CookieSpecFactory> registeredSpecs = new ConcurrentHashMap();

    public void register(String name, CookieSpecFactory factory) {
        Args.notNull(name, SchemaSymbols.ATTVAL_NAME);
        Args.notNull(factory, "Cookie spec factory");
        this.registeredSpecs.put(name.toLowerCase(Locale.ENGLISH), factory);
    }

    public void unregister(String id) {
        Args.notNull(id, "Id");
        this.registeredSpecs.remove(id.toLowerCase(Locale.ENGLISH));
    }

    public CookieSpec getCookieSpec(String name, HttpParams params) throws IllegalStateException {
        Args.notNull(name, SchemaSymbols.ATTVAL_NAME);
        CookieSpecFactory factory = (CookieSpecFactory) this.registeredSpecs.get(name.toLowerCase(Locale.ENGLISH));
        if (factory != null) {
            return factory.newInstance(params);
        }
        throw new IllegalStateException("Unsupported cookie spec: " + name);
    }

    public CookieSpec getCookieSpec(String name) throws IllegalStateException {
        return getCookieSpec(name, null);
    }

    public List<String> getSpecNames() {
        return new ArrayList(this.registeredSpecs.keySet());
    }

    public void setItems(Map<String, CookieSpecFactory> map) {
        if (map != null) {
            this.registeredSpecs.clear();
            this.registeredSpecs.putAll(map);
        }
    }

    public CookieSpecProvider lookup(String name) {
        return new 1(this, name);
    }
}
