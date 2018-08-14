package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import cz.msebera.android.httpclient.util.EntityUtils;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@NotThreadSafe
class CloseableHttpResponseProxy implements InvocationHandler {
    private static final Constructor<?> CONSTRUCTOR;
    private final HttpResponse original;

    static {
        try {
            CONSTRUCTOR = Proxy.getProxyClass(CloseableHttpResponseProxy.class.getClassLoader(), new Class[]{CloseableHttpResponse.class}).getConstructor(new Class[]{InvocationHandler.class});
        } catch (NoSuchMethodException ex) {
            throw new IllegalStateException(ex);
        }
    }

    CloseableHttpResponseProxy(HttpResponse original) {
        this.original = original;
    }

    public void close() throws IOException {
        EntityUtils.consume(this.original.getEntity());
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("close")) {
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

    public static CloseableHttpResponse newProxy(HttpResponse original) {
        try {
            return (CloseableHttpResponse) CONSTRUCTOR.newInstance(new Object[]{new CloseableHttpResponseProxy(original)});
        } catch (InstantiationException ex) {
            throw new IllegalStateException(ex);
        } catch (InvocationTargetException ex2) {
            throw new IllegalStateException(ex2);
        } catch (IllegalAccessException ex3) {
            throw new IllegalStateException(ex3);
        }
    }
}
