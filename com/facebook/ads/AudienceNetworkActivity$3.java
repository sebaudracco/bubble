package com.facebook.ads;

import android.view.View;
import com.facebook.ads.internal.p052j.C2001d;
import com.facebook.ads.internal.view.C1923a.C1832a;

class AudienceNetworkActivity$3 implements C1832a {
    final /* synthetic */ AudienceNetworkActivity f3976a;

    AudienceNetworkActivity$3(AudienceNetworkActivity audienceNetworkActivity) {
        this.f3976a = audienceNetworkActivity;
    }

    public void mo3560a(View view) {
        AudienceNetworkActivity.b(this.f3976a).addView(view);
    }

    public void mo3561a(String str) {
        AudienceNetworkActivity.a(this.f3976a, str);
    }

    public void mo3562a(String str, C2001d c2001d) {
        AudienceNetworkActivity.a(this.f3976a, str, c2001d);
    }
}
