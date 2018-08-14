package com.vungle.publisher;

import android.content.Intent;
import android.os.Bundle;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class C1674u {
    @Inject
    AdConfig f3372a;

    public t m4690a(AdConfig... adConfigArr) {
        int i = 0;
        C1663o[] c1663oArr = null;
        if (adConfigArr != null) {
            C1663o[] c1663oArr2 = new C1663o[adConfigArr.length];
            int length = adConfigArr.length;
            int i2 = 0;
            while (i2 < length) {
                int i3;
                AdConfig adConfig = adConfigArr[i2];
                if (adConfig != null) {
                    i3 = i + 1;
                    c1663oArr2[i] = adConfig.getDelegateAdConfig();
                } else {
                    i3 = i;
                }
                i2++;
                i = i3;
            }
            c1663oArr = c1663oArr2;
        }
        return new t(c1663oArr);
    }

    public p m4689a(Intent intent) {
        return (p) intent.getBundleExtra("adConfig").getParcelable("adConfig");
    }

    public void m4691a(Intent intent, t tVar) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("adConfig", tVar);
        intent.putExtra("adConfig", bundle);
    }
}
