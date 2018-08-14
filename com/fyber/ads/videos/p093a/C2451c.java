package com.fyber.ads.videos.p093a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import com.fyber.Fyber$Settings.UIStringIdentifier;
import com.fyber.p089c.p090d.C2543d;
import com.fyber.p089c.p101a.C2523a;
import com.fyber.utils.C2435a;
import com.fyber.utils.StringUtils;

/* compiled from: RewardedVideoClickthroughBrowserListener */
public final class C2451c extends C2435a {
    protected final void mo3880a(final C2543d c2543d, final C2523a c2523a, final Uri uri) {
        Object obj;
        OnClickListener c24491 = new OnClickListener(this) {
            final /* synthetic */ C2451c f6132c;

            public final void onClick(DialogInterface dialogInterface, int i) {
                c2543d.getContext().startActivity(new Intent("android.intent.action.VIEW", uri));
            }
        };
        OnClickListener c24502 = new OnClickListener(this) {
            final /* synthetic */ C2451c f6135c;

            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                if (c2523a != null) {
                    c2523a.invalidate();
                } else {
                    c2543d.m8100c();
                }
            }
        };
        String uri2 = uri.toString();
        if (StringUtils.notNullNorEmpty(uri2) && uri2.startsWith("market://")) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            m7718a(c2543d.getContext(), UIStringIdentifier.RV_REDIRECT_DIALOG_MESSAGE_MARKET, "Yes", c24491, "No", c24502);
        } else {
            m7718a(c2543d.getContext(), UIStringIdentifier.RV_REDIRECT_DIALOG_MESSAGE_DEFAULT, "Yes", c24491, "No", c24502);
        }
    }

    protected final String mo3879a() {
        return "RewardedVideoClickthroughBrowserListener";
    }
}
