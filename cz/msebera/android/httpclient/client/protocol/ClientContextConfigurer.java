package cz.msebera.android.httpclient.client.protocol;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.auth.AuthSchemeRegistry;
import cz.msebera.android.httpclient.client.CookieStore;
import cz.msebera.android.httpclient.client.CredentialsProvider;
import cz.msebera.android.httpclient.cookie.CookieSpecRegistry;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;

@NotThreadSafe
@Deprecated
public class ClientContextConfigurer implements ClientContext {
    private final HttpContext context;

    public ClientContextConfigurer(HttpContext context) {
        Args.notNull(context, "HTTP context");
        this.context = context;
    }

    public void setCookieSpecRegistry(CookieSpecRegistry registry) {
        this.context.setAttribute("http.cookiespec-registry", registry);
    }

    public void setAuthSchemeRegistry(AuthSchemeRegistry registry) {
        this.context.setAttribute("http.authscheme-registry", registry);
    }

    public void setCookieStore(CookieStore store) {
        this.context.setAttribute("http.cookie-store", store);
    }

    public void setCredentialsProvider(CredentialsProvider provider) {
        this.context.setAttribute("http.auth.credentials-provider", provider);
    }
}
