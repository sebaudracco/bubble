package cz.msebera.android.httpclient.protocol;

import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpRequestInterceptor;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpResponseInterceptor;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@NotThreadSafe
@Deprecated
public final class BasicHttpProcessor implements HttpProcessor, HttpRequestInterceptorList, HttpResponseInterceptorList, Cloneable {
    protected final List<HttpRequestInterceptor> requestInterceptors = new ArrayList();
    protected final List<HttpResponseInterceptor> responseInterceptors = new ArrayList();

    public void addRequestInterceptor(HttpRequestInterceptor itcp) {
        if (itcp != null) {
            this.requestInterceptors.add(itcp);
        }
    }

    public void addRequestInterceptor(HttpRequestInterceptor itcp, int index) {
        if (itcp != null) {
            this.requestInterceptors.add(index, itcp);
        }
    }

    public void addResponseInterceptor(HttpResponseInterceptor itcp, int index) {
        if (itcp != null) {
            this.responseInterceptors.add(index, itcp);
        }
    }

    public void removeRequestInterceptorByClass(Class<? extends HttpRequestInterceptor> clazz) {
        Iterator<HttpRequestInterceptor> it = this.requestInterceptors.iterator();
        while (it.hasNext()) {
            if (it.next().getClass().equals(clazz)) {
                it.remove();
            }
        }
    }

    public void removeResponseInterceptorByClass(Class<? extends HttpResponseInterceptor> clazz) {
        Iterator<HttpResponseInterceptor> it = this.responseInterceptors.iterator();
        while (it.hasNext()) {
            if (it.next().getClass().equals(clazz)) {
                it.remove();
            }
        }
    }

    public final void addInterceptor(HttpRequestInterceptor interceptor) {
        addRequestInterceptor(interceptor);
    }

    public final void addInterceptor(HttpRequestInterceptor interceptor, int index) {
        addRequestInterceptor(interceptor, index);
    }

    public int getRequestInterceptorCount() {
        return this.requestInterceptors.size();
    }

    public HttpRequestInterceptor getRequestInterceptor(int index) {
        if (index < 0 || index >= this.requestInterceptors.size()) {
            return null;
        }
        return (HttpRequestInterceptor) this.requestInterceptors.get(index);
    }

    public void clearRequestInterceptors() {
        this.requestInterceptors.clear();
    }

    public void addResponseInterceptor(HttpResponseInterceptor itcp) {
        if (itcp != null) {
            this.responseInterceptors.add(itcp);
        }
    }

    public final void addInterceptor(HttpResponseInterceptor interceptor) {
        addResponseInterceptor(interceptor);
    }

    public final void addInterceptor(HttpResponseInterceptor interceptor, int index) {
        addResponseInterceptor(interceptor, index);
    }

    public int getResponseInterceptorCount() {
        return this.responseInterceptors.size();
    }

    public HttpResponseInterceptor getResponseInterceptor(int index) {
        if (index < 0 || index >= this.responseInterceptors.size()) {
            return null;
        }
        return (HttpResponseInterceptor) this.responseInterceptors.get(index);
    }

    public void clearResponseInterceptors() {
        this.responseInterceptors.clear();
    }

    public void setInterceptors(List<?> list) {
        Args.notNull(list, "Inteceptor list");
        this.requestInterceptors.clear();
        this.responseInterceptors.clear();
        for (Object obj : list) {
            if (obj instanceof HttpRequestInterceptor) {
                addInterceptor((HttpRequestInterceptor) obj);
            }
            if (obj instanceof HttpResponseInterceptor) {
                addInterceptor((HttpResponseInterceptor) obj);
            }
        }
    }

    public void clearInterceptors() {
        clearRequestInterceptors();
        clearResponseInterceptors();
    }

    public void process(HttpRequest request, HttpContext context) throws IOException, HttpException {
        for (HttpRequestInterceptor interceptor : this.requestInterceptors) {
            interceptor.process(request, context);
        }
    }

    public void process(HttpResponse response, HttpContext context) throws IOException, HttpException {
        for (HttpResponseInterceptor interceptor : this.responseInterceptors) {
            interceptor.process(response, context);
        }
    }

    protected void copyInterceptors(BasicHttpProcessor target) {
        target.requestInterceptors.clear();
        target.requestInterceptors.addAll(this.requestInterceptors);
        target.responseInterceptors.clear();
        target.responseInterceptors.addAll(this.responseInterceptors);
    }

    public BasicHttpProcessor copy() {
        BasicHttpProcessor clone = new BasicHttpProcessor();
        copyInterceptors(clone);
        return clone;
    }

    public Object clone() throws CloneNotSupportedException {
        BasicHttpProcessor clone = (BasicHttpProcessor) super.clone();
        copyInterceptors(clone);
        return clone;
    }
}
