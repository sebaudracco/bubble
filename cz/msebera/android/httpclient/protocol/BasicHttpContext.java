package cz.msebera.android.httpclient.protocol;

import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.util.Args;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public class BasicHttpContext implements HttpContext {
    private final Map<String, Object> map;
    private final HttpContext parentContext;

    public BasicHttpContext() {
        this(null);
    }

    public BasicHttpContext(HttpContext parentContext) {
        this.map = new ConcurrentHashMap();
        this.parentContext = parentContext;
    }

    public Object getAttribute(String id) {
        Args.notNull(id, "Id");
        Object obj = this.map.get(id);
        if (obj != null || this.parentContext == null) {
            return obj;
        }
        return this.parentContext.getAttribute(id);
    }

    public void setAttribute(String id, Object obj) {
        Args.notNull(id, "Id");
        if (obj != null) {
            this.map.put(id, obj);
        } else {
            this.map.remove(id);
        }
    }

    public Object removeAttribute(String id) {
        Args.notNull(id, "Id");
        return this.map.remove(id);
    }

    public void clear() {
        this.map.clear();
    }

    public String toString() {
        return this.map.toString();
    }
}
