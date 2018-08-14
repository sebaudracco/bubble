package com.facebook.ads;

import android.view.View;
import android.view.View.OnLongClickListener;
import com.facebook.ads.internal.view.p034b.C2185c;

class AudienceNetworkActivity$a implements OnLongClickListener {
    final /* synthetic */ AudienceNetworkActivity f3981a;

    private AudienceNetworkActivity$a(AudienceNetworkActivity audienceNetworkActivity) {
        this.f3981a = audienceNetworkActivity;
    }

    public boolean onLongClick(View view) {
        boolean z = false;
        if (!(AudienceNetworkActivity.a(this.f3981a) == null || AudienceNetworkActivity.b(this.f3981a) == null)) {
            AudienceNetworkActivity.a(this.f3981a).setBounds(0, 0, AudienceNetworkActivity.b(this.f3981a).getWidth(), AudienceNetworkActivity.b(this.f3981a).getHeight());
            C2185c a = AudienceNetworkActivity.a(this.f3981a);
            if (!AudienceNetworkActivity.a(this.f3981a).m6997a()) {
                z = true;
            }
            a.m6996a(z);
        }
        return true;
    }
}
