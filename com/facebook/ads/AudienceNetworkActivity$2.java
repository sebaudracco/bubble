package com.facebook.ads;

import android.view.View;
import com.facebook.ads.internal.p052j.C2001d;
import com.facebook.ads.internal.view.C1923a.C1832a;
import com.facebook.ads.internal.view.p053e.p054b.C2246z;

class AudienceNetworkActivity$2 implements C1832a {
    final /* synthetic */ AudienceNetworkActivity f3975a;

    AudienceNetworkActivity$2(AudienceNetworkActivity audienceNetworkActivity) {
        this.f3975a = audienceNetworkActivity;
    }

    public void mo3560a(View view) {
        AudienceNetworkActivity.b(this.f3975a).addView(view);
    }

    public void mo3561a(String str) {
        AudienceNetworkActivity.a(this.f3975a, str);
        String a = C2246z.REWARDED_VIDEO_END_ACTIVITY.m7091a();
        String a2 = C2246z.REWARDED_VIDEO_ERROR.m7091a();
        if (str.equals(a) || str.equals(a2)) {
            this.f3975a.finish();
        }
    }

    public void mo3562a(String str, C2001d c2001d) {
        AudienceNetworkActivity.a(this.f3975a, str);
    }
}
