package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@NotThreadSafe
class ResponseProxyHandler implements InvocationHandler {
    private static final Method CLOSE_METHOD;
    private final HttpResponse original;

    static {
        try {
            CLOSE_METHOD = Closeable.class.getMethod("close", new Class[0]);
        } catch (NoSuchMethodException ex) {
            throw new Error(ex);
        }
    }

    ResponseProxyHandler(HttpResponse original) {
        this.original = original;
    }

    public void close() throws IOException {
        IOUtils.consume(this.original.getEntity());
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.equals(CLOSE_METHOD)) {
            close();
            return null;
        }
        try {
            return method.invoke(this.original, args);
        } catch (InvocationTargetException ex) {
            Throwable cause = ex.getCause();
            if (cause != null) {
                throw cause;
            }
            throw ex;
        }
    }
}
