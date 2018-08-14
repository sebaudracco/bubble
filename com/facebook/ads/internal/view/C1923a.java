package com.facebook.ads.internal.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.p052j.C2001d;

public interface C1923a {

    public interface C1832a {
        void mo3560a(View view);

        void mo3561a(String str);

        void mo3562a(String str, C2001d c2001d);
    }

    void mo3683a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity);

    void mo3684a(Bundle bundle);

    void mo3686i();

    void mo3687j();

    void onDestroy();

    void setListener(C1832a c1832a);
}
