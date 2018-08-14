package com.facebook.ads;

import android.view.View;
import com.facebook.ads.internal.p052j.C2001d;
import com.facebook.ads.internal.view.C1923a.C1832a;

class AudienceNetworkActivity$5 implements C1832a {
    final /* synthetic */ AudienceNetworkActivity f3978a;

    AudienceNetworkActivity$5(AudienceNetworkActivity audienceNetworkActivity) {
        this.f3978a = audienceNetworkActivity;
    }

    public void mo3560a(View view) {
        AudienceNetworkActivity.b(this.f3978a).addView(view);
    }

    public void mo3561a(String str) {
        AudienceNetworkActivity.a(this.f3978a, str);
    }

    public void mo3562a(String str, C2001d c2001d) {
        AudienceNetworkActivity.a(this.f3978a, str, c2001d);
    }
}
