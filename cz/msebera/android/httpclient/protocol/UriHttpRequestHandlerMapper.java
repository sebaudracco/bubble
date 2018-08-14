package cz.msebera.android.httpclient.protocol;

import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.util.Args;

@ThreadSafe
public class UriHttpRequestHandlerMapper implements HttpRequestHandlerMapper {
    private final UriPatternMatcher<HttpRequestHandler> matcher;

    protected UriHttpRequestHandlerMapper(UriPatternMatcher<HttpRequestHandler> matcher) {
        this.matcher = (UriPatternMatcher) Args.notNull(matcher, "Pattern matcher");
    }

    public UriHttpRequestHandlerMapper() {
        this(new UriPatternMatcher());
    }

    public void register(String pattern, HttpRequestHandler handler) {
        Args.notNull(pattern, "Pattern");
        Args.notNull(handler, "Handler");
        this.matcher.register(pattern, handler);
    }

    public void unregister(String pattern) {
        this.matcher.unregister(pattern);
    }

    protected String getRequestPath(HttpRequest request) {
        String uriPath = request.getRequestLine().getUri();
        int index = uriPath.indexOf("?");
        if (index != -1) {
            return uriPath.substring(0, index);
        }
        index = uriPath.indexOf("#");
        if (index != -1) {
            return uriPath.substring(0, index);
        }
        return uriPath;
    }

    public HttpRequestHandler lookup(HttpRequest request) {
        Args.notNull(request, "HTTP request");
        return (HttpRequestHandler) this.matcher.lookup(getRequestPath(request));
    }
}
