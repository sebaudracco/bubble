package com.facebook.ads.internal.view.component.p081a;

import android.content.Context;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.adapters.C1900j;
import com.facebook.ads.internal.p056q.p057a.C2133v;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.view.C1923a.C1832a;
import com.facebook.ads.internal.view.component.C2200a;
import com.facebook.ads.internal.view.component.C2208i;
import com.mopub.mobileads.dfp.adapters.MoPubAdapter;
import java.util.HashMap;

public abstract class C2195b extends RelativeLayout {
    static final int f5335a = ((int) (16.0f * C2133v.f5083b));
    static final int f5336b = ((int) (28.0f * C2133v.f5083b));
    private final C2208i f5337c;
    private final C2200a f5338d;

    C2195b(Context context, C2012c c2012c, C1832a c1832a, C1900j c1900j, boolean z) {
        super(context);
        this.f5338d = new C2200a(context, true, mo3773c(), "com.facebook.ads.interstitial.clicked", c1900j, c2012c, c1832a);
        C2133v.m6832a(this.f5338d);
        this.f5337c = new C2208i(getContext(), c1900j, z, true, mo3772b());
        C2133v.m6832a(this.f5337c);
    }

    public void mo3771a(String str, String str2, String str3, String str4, String str5, double d) {
        C2208i c2208i = this.f5337c;
        boolean z = !mo3770a() && d > 0.0d && d < MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE;
        c2208i.m7047a(str, str2, false, z);
        this.f5338d.m7044a(str3, str4, str5, new HashMap());
    }

    public abstract boolean mo3770a();

    protected boolean mo3772b() {
        return true;
    }

    protected boolean mo3773c() {
        return true;
    }

    C2200a getCtaButton() {
        return this.f5338d;
    }

    C2208i getTextContainer() {
        return this.f5337c;
    }
}
