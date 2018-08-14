package cz.msebera.android.httpclient.cookie;

import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.protocol.HttpContext;

class CookieSpecRegistry$1 implements CookieSpecProvider {
    final /* synthetic */ CookieSpecRegistry this$0;
    final /* synthetic */ String val$name;

    CookieSpecRegistry$1(CookieSpecRegistry this$0, String str) {
        this.this$0 = this$0;
        this.val$name = str;
    }

    public CookieSpec create(HttpContext context) {
        return this.this$0.getCookieSpec(this.val$name, ((HttpRequest) context.getAttribute("http.request")).getParams());
    }
}
