package com.fyber.ads.ofw.p092a;

import android.app.Activity;
import android.net.Uri;
import android.webkit.WebView;
import com.fyber.Fyber$Settings.UIStringIdentifier;
import com.fyber.utils.C2429y;
import com.fyber.utils.C2671s;
import com.fyber.utils.FyberLogger;
import java.util.Locale;

/* compiled from: ActivityOfferWebClient */
public final class C2443a extends C2429y {
    private boolean f6119a;

    public C2443a(Activity activity, boolean z) {
        super(activity);
        this.f6119a = z;
    }

    protected final void mo3866a(int i, String str) {
        Activity a = mo3865a();
        if (a != null) {
            boolean z;
            a.setResult(i);
            if (str == null) {
                z = true;
            } else {
                z = this.f6119a;
                if (!m7681a(str)) {
                    return;
                }
            }
            FyberLogger.m8451i("ActivityOfferWebClient", "Should close: " + this.f6119a + ", will close activity: " + z);
            if (z) {
                a.finish();
            }
        }
    }

    protected final void mo3867a(String str, Uri uri) {
    }

    protected final void mo3868b() {
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        UIStringIdentifier uIStringIdentifier;
        FyberLogger.m8449e("ActivityOfferWebClient", String.format(Locale.ENGLISH, "OfferWall WebView triggered an error. Error code: %d, error description: %s. Failing URL: %s", new Object[]{Integer.valueOf(i), str, str2}));
        switch (i) {
            case -7:
            case -2:
                uIStringIdentifier = UIStringIdentifier.ERROR_LOADING_OFFERWALL_NO_INTERNET_CONNECTION;
                break;
            default:
                uIStringIdentifier = UIStringIdentifier.ERROR_LOADING_OFFERWALL;
                break;
        }
        m7683b(C2671s.m8532a(uIStringIdentifier));
    }
}
