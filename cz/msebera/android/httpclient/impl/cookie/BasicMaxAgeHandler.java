package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.cookie.MalformedCookieException;
import cz.msebera.android.httpclient.cookie.SM;
import cz.msebera.android.httpclient.cookie.SetCookie;
import cz.msebera.android.httpclient.util.Args;
import java.util.Date;

@Immutable
public class BasicMaxAgeHandler extends AbstractCookieAttributeHandler {
    public void parse(SetCookie cookie, String value) throws MalformedCookieException {
        Args.notNull(cookie, SM.COOKIE);
        if (value == null) {
            throw new MalformedCookieException("Missing value for max-age attribute");
        }
        try {
            int age = Integer.parseInt(value);
            if (age < 0) {
                throw new MalformedCookieException("Negative max-age attribute: " + value);
            }
            cookie.setExpiryDate(new Date(System.currentTimeMillis() + (((long) age) * 1000)));
        } catch (NumberFormatException e) {
            throw new MalformedCookieException("Invalid max-age attribute: " + value);
        }
    }
}
