package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.client.utils.Punycode;
import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.cookie.CookieAttributeHandler;
import cz.msebera.android.httpclient.cookie.CookieOrigin;
import cz.msebera.android.httpclient.cookie.MalformedCookieException;
import cz.msebera.android.httpclient.cookie.SetCookie;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Marker;

public class PublicSuffixFilter implements CookieAttributeHandler {
    private Set<String> exceptions;
    private Set<String> suffixes;
    private final CookieAttributeHandler wrapped;

    public PublicSuffixFilter(CookieAttributeHandler wrapped) {
        this.wrapped = wrapped;
    }

    public void setPublicSuffixes(Collection<String> suffixes) {
        this.suffixes = new HashSet(suffixes);
    }

    public void setExceptions(Collection<String> exceptions) {
        this.exceptions = new HashSet(exceptions);
    }

    public boolean match(Cookie cookie, CookieOrigin origin) {
        if (isForPublicSuffix(cookie)) {
            return false;
        }
        return this.wrapped.match(cookie, origin);
    }

    public void parse(SetCookie cookie, String value) throws MalformedCookieException {
        this.wrapped.parse(cookie, value);
    }

    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
        this.wrapped.validate(cookie, origin);
    }

    private boolean isForPublicSuffix(Cookie cookie) {
        String domain = cookie.getDomain();
        if (domain.startsWith(".")) {
            domain = domain.substring(1);
        }
        domain = Punycode.toUnicode(domain);
        if ((this.exceptions != null && this.exceptions.contains(domain)) || this.suffixes == null) {
            return false;
        }
        while (!this.suffixes.contains(domain)) {
            if (domain.startsWith("*.")) {
                domain = domain.substring(2);
            }
            int nextdot = domain.indexOf(46);
            if (nextdot == -1) {
                return false;
            }
            domain = Marker.ANY_MARKER + domain.substring(nextdot);
            if (domain.length() <= 0) {
                return false;
            }
        }
        return true;
    }
}
