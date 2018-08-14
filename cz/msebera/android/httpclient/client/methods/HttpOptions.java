package cz.msebera.android.httpclient.client.methods;

import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.HeaderIterator;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.util.Args;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

@NotThreadSafe
public class HttpOptions extends HttpRequestBase {
    public static final String METHOD_NAME = "OPTIONS";

    public HttpOptions(URI uri) {
        setURI(uri);
    }

    public HttpOptions(String uri) {
        setURI(URI.create(uri));
    }

    public String getMethod() {
        return "OPTIONS";
    }

    public Set<String> getAllowedMethods(HttpResponse response) {
        Args.notNull(response, "HTTP response");
        HeaderIterator it = response.headerIterator("Allow");
        Set<String> methods = new HashSet();
        while (it.hasNext()) {
            for (HeaderElement element : it.nextHeader().getElements()) {
                methods.add(element.getName());
            }
        }
        return methods;
    }
}
