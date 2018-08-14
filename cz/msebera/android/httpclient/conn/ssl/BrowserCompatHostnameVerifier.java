package cz.msebera.android.httpclient.conn.ssl;

import cz.msebera.android.httpclient.annotation.Immutable;
import javax.net.ssl.SSLException;

@Immutable
public class BrowserCompatHostnameVerifier extends AbstractVerifier {
    public final void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
        verify(host, cns, subjectAlts, false);
    }

    boolean validCountryWildcard(String cn) {
        return true;
    }

    public final String toString() {
        return "BROWSER_COMPATIBLE";
    }
}
