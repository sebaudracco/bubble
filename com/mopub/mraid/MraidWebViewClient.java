package com.mopub.mraid;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.mopub.mobileads.resource.MraidJavascript;
import java.io.ByteArrayInputStream;
import java.util.Locale;

public class MraidWebViewClient extends WebViewClient {
    private static final String MRAID_INJECTION_JAVASCRIPT = (BridgeUtil.JAVASCRIPT_STR + MraidJavascript.JAVASCRIPT_SOURCE);
    private static final String MRAID_JS = "mraid.js";

    public WebResourceResponse shouldInterceptRequest(@NonNull WebView view, @NonNull String url) {
        if (matchesInjectionUrl(url)) {
            return createMraidInjectionResponse();
        }
        return super.shouldInterceptRequest(view, url);
    }

    @VisibleForTesting
    boolean matchesInjectionUrl(@NonNull String url) {
        return MRAID_JS.equals(Uri.parse(url.toLowerCase(Locale.US)).getLastPathSegment());
    }

    private WebResourceResponse createMraidInjectionResponse() {
        return new WebResourceResponse("text/javascript", "UTF-8", new ByteArrayInputStream(MRAID_INJECTION_JAVASCRIPT.getBytes()));
    }
}
