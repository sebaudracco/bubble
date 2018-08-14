package cz.msebera.android.httpclient.config;

import cz.msebera.android.httpclient.annotation.ThreadSafe;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public final class Registry<I> implements Lookup<I> {
    private final Map<String, I> map;

    Registry(Map<String, I> map) {
        this.map = new ConcurrentHashMap(map);
    }

    public I lookup(String key) {
        if (key == null) {
            return null;
        }
        return this.map.get(key.toLowerCase(Locale.ENGLISH));
    }

    public String toString() {
        return this.map.toString();
    }
}
