package cz.msebera.android.httpclient.client.utils;

import com.github.lzyzsd.jsbridge.BridgeUtil;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.TextUtils;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Stack;

@Immutable
public class URIUtils {
    @Deprecated
    public static URI createURI(String scheme, String host, int port, String path, String query, String fragment) throws URISyntaxException {
        StringBuilder buffer = new StringBuilder();
        if (host != null) {
            if (scheme != null) {
                buffer.append(scheme);
                buffer.append("://");
            }
            buffer.append(host);
            if (port > 0) {
                buffer.append(':');
                buffer.append(port);
            }
        }
        if (path == null || !path.startsWith(BridgeUtil.SPLIT_MARK)) {
            buffer.append('/');
        }
        if (path != null) {
            buffer.append(path);
        }
        if (query != null) {
            buffer.append('?');
            buffer.append(query);
        }
        if (fragment != null) {
            buffer.append('#');
            buffer.append(fragment);
        }
        return new URI(buffer.toString());
    }

    public static URI rewriteURI(URI uri, HttpHost target, boolean dropFragment) throws URISyntaxException {
        Args.notNull(uri, "URI");
        if (uri.isOpaque()) {
            return uri;
        }
        URIBuilder uribuilder = new URIBuilder(uri);
        if (target != null) {
            uribuilder.setScheme(target.getSchemeName());
            uribuilder.setHost(target.getHostName());
            uribuilder.setPort(target.getPort());
        } else {
            uribuilder.setScheme(null);
            uribuilder.setHost(null);
            uribuilder.setPort(-1);
        }
        if (dropFragment) {
            uribuilder.setFragment(null);
        }
        if (TextUtils.isEmpty(uribuilder.getPath())) {
            uribuilder.setPath(BridgeUtil.SPLIT_MARK);
        }
        return uribuilder.build();
    }

    public static URI rewriteURI(URI uri, HttpHost target) throws URISyntaxException {
        return rewriteURI(uri, target, false);
    }

    public static URI rewriteURI(URI uri) throws URISyntaxException {
        Args.notNull(uri, "URI");
        if (uri.isOpaque()) {
            return uri;
        }
        URIBuilder uribuilder = new URIBuilder(uri);
        if (uribuilder.getUserInfo() != null) {
            uribuilder.setUserInfo(null);
        }
        if (TextUtils.isEmpty(uribuilder.getPath())) {
            uribuilder.setPath(BridgeUtil.SPLIT_MARK);
        }
        if (uribuilder.getHost() != null) {
            uribuilder.setHost(uribuilder.getHost().toLowerCase(Locale.ENGLISH));
        }
        uribuilder.setFragment(null);
        return uribuilder.build();
    }

    public static URI resolve(URI baseURI, String reference) {
        return resolve(baseURI, URI.create(reference));
    }

    public static URI resolve(URI baseURI, URI reference) {
        Args.notNull(baseURI, "Base URI");
        Args.notNull(reference, "Reference URI");
        URI ref = reference;
        String s = ref.toString();
        if (s.startsWith("?")) {
            return resolveReferenceStartingWithQueryString(baseURI, ref);
        }
        boolean emptyReference;
        if (s.length() == 0) {
            emptyReference = true;
        } else {
            emptyReference = false;
        }
        if (emptyReference) {
            ref = URI.create("#");
        }
        URI resolved = baseURI.resolve(ref);
        if (emptyReference) {
            String resolvedString = resolved.toString();
            resolved = URI.create(resolvedString.substring(0, resolvedString.indexOf(35)));
        }
        return normalizeSyntax(resolved);
    }

    private static URI resolveReferenceStartingWithQueryString(URI baseURI, URI reference) {
        String baseUri = baseURI.toString();
        if (baseUri.indexOf(63) > -1) {
            baseUri = baseUri.substring(0, baseUri.indexOf(63));
        }
        return URI.create(baseUri + reference.toString());
    }

    private static URI normalizeSyntax(URI uri) {
        if (uri.isOpaque() || uri.getAuthority() == null) {
            return uri;
        }
        Args.check(uri.isAbsolute(), "Base URI must be absolute");
        String path = uri.getPath() == null ? "" : uri.getPath();
        String[] inputSegments = path.split(BridgeUtil.SPLIT_MARK);
        Stack<String> outputSegments = new Stack();
        for (String inputSegment : inputSegments) {
            if (!(inputSegment.length() == 0 || ".".equals(inputSegment))) {
                if (!"..".equals(inputSegment)) {
                    outputSegments.push(inputSegment);
                } else if (!outputSegments.isEmpty()) {
                    outputSegments.pop();
                }
            }
        }
        StringBuilder outputBuffer = new StringBuilder();
        Iterator it = outputSegments.iterator();
        while (it.hasNext()) {
            outputBuffer.append('/').append((String) it.next());
        }
        if (path.lastIndexOf(47) == path.length() - 1) {
            outputBuffer.append('/');
        }
        try {
            URI ref = new URI(uri.getScheme().toLowerCase(Locale.ENGLISH), uri.getAuthority().toLowerCase(Locale.ENGLISH), outputBuffer.toString(), null, null);
            if (uri.getQuery() == null && uri.getFragment() == null) {
                return ref;
            }
            StringBuilder normalized = new StringBuilder(ref.toASCIIString());
            if (uri.getQuery() != null) {
                normalized.append('?').append(uri.getRawQuery());
            }
            if (uri.getFragment() != null) {
                normalized.append('#').append(uri.getRawFragment());
            }
            return URI.create(normalized.toString());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static HttpHost extractHost(URI uri) {
        if (uri == null) {
            return null;
        }
        if (!uri.isAbsolute()) {
            return null;
        }
        int port = uri.getPort();
        String host = uri.getHost();
        if (host == null) {
            host = uri.getAuthority();
            if (host != null) {
                int at = host.indexOf(64);
                if (at >= 0) {
                    if (host.length() > at + 1) {
                        host = host.substring(at + 1);
                    } else {
                        host = null;
                    }
                }
                if (host != null) {
                    int colon = host.indexOf(58);
                    if (colon >= 0) {
                        int pos = colon + 1;
                        int len = 0;
                        int i = pos;
                        while (i < host.length() && Character.isDigit(host.charAt(i))) {
                            len++;
                            i++;
                        }
                        if (len > 0) {
                            try {
                                port = Integer.parseInt(host.substring(pos, pos + len));
                            } catch (NumberFormatException e) {
                            }
                        }
                        host = host.substring(0, colon);
                    }
                }
            }
        }
        String scheme = uri.getScheme();
        if (TextUtils.isBlank(host)) {
            return null;
        }
        return new HttpHost(host, port, scheme);
    }

    public static URI resolve(URI originalURI, HttpHost target, List<URI> redirects) throws URISyntaxException {
        URIBuilder uribuilder;
        Args.notNull(originalURI, "Request URI");
        if (redirects == null || redirects.isEmpty()) {
            uribuilder = new URIBuilder(originalURI);
        } else {
            uribuilder = new URIBuilder((URI) redirects.get(redirects.size() - 1));
            String frag = uribuilder.getFragment();
            int i = redirects.size() - 1;
            while (frag == null && i >= 0) {
                frag = ((URI) redirects.get(i)).getFragment();
                i--;
            }
            uribuilder.setFragment(frag);
        }
        if (uribuilder.getFragment() == null) {
            uribuilder.setFragment(originalURI.getFragment());
        }
        if (!(target == null || uribuilder.isAbsolute())) {
            uribuilder.setScheme(target.getSchemeName());
            uribuilder.setHost(target.getHostName());
            uribuilder.setPort(target.getPort());
        }
        return uribuilder.build();
    }

    private URIUtils() {
    }
}
