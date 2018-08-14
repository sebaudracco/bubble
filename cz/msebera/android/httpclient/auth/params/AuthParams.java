package cz.msebera.android.httpclient.auth.params;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.HTTP;
import cz.msebera.android.httpclient.util.Args;

@Immutable
@Deprecated
public final class AuthParams {
    private AuthParams() {
    }

    public static String getCredentialCharset(HttpParams params) {
        Args.notNull(params, "HTTP parameters");
        String charset = (String) params.getParameter(AuthPNames.CREDENTIAL_CHARSET);
        if (charset == null) {
            return HTTP.DEF_PROTOCOL_CHARSET.name();
        }
        return charset;
    }

    public static void setCredentialCharset(HttpParams params, String charset) {
        Args.notNull(params, "HTTP parameters");
        params.setParameter(AuthPNames.CREDENTIAL_CHARSET, charset);
    }
}
