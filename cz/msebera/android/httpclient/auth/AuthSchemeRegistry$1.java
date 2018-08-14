package cz.msebera.android.httpclient.auth;

import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.protocol.HttpContext;

class AuthSchemeRegistry$1 implements AuthSchemeProvider {
    final /* synthetic */ AuthSchemeRegistry this$0;
    final /* synthetic */ String val$name;

    AuthSchemeRegistry$1(AuthSchemeRegistry this$0, String str) {
        this.this$0 = this$0;
        this.val$name = str;
    }

    public AuthScheme create(HttpContext context) {
        return this.this$0.getAuthScheme(this.val$name, ((HttpRequest) context.getAttribute("http.request")).getParams());
    }
}
