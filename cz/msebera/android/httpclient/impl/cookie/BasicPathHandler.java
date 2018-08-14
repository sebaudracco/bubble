package cz.msebera.android.httpclient.impl.cookie;

import com.github.lzyzsd.jsbridge.BridgeUtil;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.cookie.CookieAttributeHandler;
import cz.msebera.android.httpclient.cookie.CookieOrigin;
import cz.msebera.android.httpclient.cookie.CookieRestrictionViolationException;
import cz.msebera.android.httpclient.cookie.MalformedCookieException;
import cz.msebera.android.httpclient.cookie.SM;
import cz.msebera.android.httpclient.cookie.SetCookie;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.TextUtils;

@Immutable
public class BasicPathHandler implements CookieAttributeHandler {
    public void parse(SetCookie cookie, String value) throws MalformedCookieException {
        Args.notNull(cookie, SM.COOKIE);
        if (TextUtils.isBlank(value)) {
            value = BridgeUtil.SPLIT_MARK;
        }
        cookie.setPath(value);
    }

    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
        if (!match(cookie, origin)) {
            throw new CookieRestrictionViolationException("Illegal path attribute \"" + cookie.getPath() + "\". Path of origin: \"" + origin.getPath() + "\"");
        }
    }

    public boolean match(Cookie cookie, CookieOrigin origin) {
        Args.notNull(cookie, SM.COOKIE);
        Args.notNull(origin, "Cookie origin");
        String targetpath = origin.getPath();
        String topmostPath = cookie.getPath();
        if (topmostPath == null) {
            topmostPath = BridgeUtil.SPLIT_MARK;
        }
        if (topmostPath.length() > 1 && topmostPath.endsWith(BridgeUtil.SPLIT_MARK)) {
            topmostPath = topmostPath.substring(0, topmostPath.length() - 1);
        }
        boolean match = targetpath.startsWith(topmostPath);
        if (!match || targetpath.length() == topmostPath.length() || topmostPath.endsWith(BridgeUtil.SPLIT_MARK)) {
            return match;
        }
        if (targetpath.charAt(topmostPath.length()) == '/') {
            return true;
        }
        return false;
    }
}
