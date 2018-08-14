package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.methods.HttpPost;

@Immutable
public class LaxRedirectStrategy extends DefaultRedirectStrategy {
    private static final String[] REDIRECT_METHODS = new String[]{"GET", HttpPost.METHOD_NAME, "HEAD"};

    protected boolean isRedirectable(String method) {
        for (String m : REDIRECT_METHODS) {
            if (m.equalsIgnoreCase(method)) {
                return true;
            }
        }
        return false;
    }
}
