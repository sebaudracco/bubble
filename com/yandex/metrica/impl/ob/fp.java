package com.yandex.metrica.impl.ob;

import com.facebook.ads.AudienceNetworkActivity;
import java.io.UnsupportedEncodingException;

public abstract class fp<T> extends fu<T> {
    private final String f12406a;

    protected abstract T mo7103b(ft ftVar) throws fr;

    static {
        String.format("application/json; charset=%s", new Object[]{AudienceNetworkActivity.WEBVIEW_ENCODING});
    }

    public fp(int i, String str, String str2) {
        super(i, str);
        this.f12406a = str2;
    }

    public byte[] mo7104c() {
        byte[] bArr = null;
        try {
            if (this.f12406a != null) {
                bArr = this.f12406a.getBytes(AudienceNetworkActivity.WEBVIEW_ENCODING);
            }
        } catch (UnsupportedEncodingException e) {
        }
        return bArr;
    }
}
