package cz.msebera.android.httpclient.client.utils;

import cz.msebera.android.httpclient.annotation.Immutable;

@Immutable
public class Punycode {
    private static final Idn impl;

    static {
        Idn _impl;
        try {
            _impl = new JdkIdn();
        } catch (Exception e) {
            _impl = new Rfc3492Idn();
        }
        impl = _impl;
    }

    public static String toUnicode(String punycode) {
        return impl.toUnicode(punycode);
    }
}
