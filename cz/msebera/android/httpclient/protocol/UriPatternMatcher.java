package cz.msebera.android.httpclient.protocol;

import cz.msebera.android.httpclient.annotation.GuardedBy;
import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.util.Args;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Marker;

@ThreadSafe
public class UriPatternMatcher<T> {
    @GuardedBy("this")
    private final Map<String, T> map = new HashMap();

    public synchronized void register(String pattern, T obj) {
        Args.notNull(pattern, "URI request pattern");
        this.map.put(pattern, obj);
    }

    public synchronized void unregister(String pattern) {
        if (pattern != null) {
            this.map.remove(pattern);
        }
    }

    @Deprecated
    public synchronized void setHandlers(Map<String, T> map) {
        Args.notNull(map, "Map of handlers");
        this.map.clear();
        this.map.putAll(map);
    }

    @Deprecated
    public synchronized void setObjects(Map<String, T> map) {
        Args.notNull(map, "Map of handlers");
        this.map.clear();
        this.map.putAll(map);
    }

    @Deprecated
    public synchronized Map<String, T> getObjects() {
        return this.map;
    }

    public synchronized T lookup(String path) {
        T obj;
        Args.notNull(path, "Request path");
        obj = this.map.get(path);
        if (obj == null) {
            String bestMatch = null;
            for (String pattern : this.map.keySet()) {
                if (matchUriRequestPattern(pattern, path) && (bestMatch == null || bestMatch.length() < pattern.length() || (bestMatch.length() == pattern.length() && pattern.endsWith(Marker.ANY_MARKER)))) {
                    obj = this.map.get(pattern);
                    bestMatch = pattern;
                }
            }
        }
        return obj;
    }

    protected boolean matchUriRequestPattern(String pattern, String path) {
        boolean z = false;
        if (pattern.equals(Marker.ANY_MARKER)) {
            return true;
        }
        if ((pattern.endsWith(Marker.ANY_MARKER) && path.startsWith(pattern.substring(0, pattern.length() - 1))) || (pattern.startsWith(Marker.ANY_MARKER) && path.endsWith(pattern.substring(1, pattern.length())))) {
            z = true;
        }
        return z;
    }

    public String toString() {
        return this.map.toString();
    }
}
