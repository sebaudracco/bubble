package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.net.Uri;
import android.webkit.WebResourceRequest;
import java.util.Collections;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzasu {
    private final String method;
    public final Uri uri;
    public final String url;
    public final Map<String, String> zzab;

    @TargetApi(21)
    public zzasu(WebResourceRequest webResourceRequest) {
        this(webResourceRequest.getUrl().toString(), webResourceRequest.getUrl(), webResourceRequest.getMethod(), webResourceRequest.getRequestHeaders());
    }

    public zzasu(String str) {
        this(str, Uri.parse(str), null, null);
    }

    private zzasu(String str, Uri uri, @Nullable String str2, @Nullable Map<String, String> map) {
        Map emptyMap;
        this.url = str;
        this.uri = uri;
        if (str2 == null) {
            str2 = "GET";
        }
        this.method = str2;
        if (map == null) {
            emptyMap = Collections.emptyMap();
        }
        this.zzab = emptyMap;
    }
}
