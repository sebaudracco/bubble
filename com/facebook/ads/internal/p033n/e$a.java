package com.facebook.ads.internal.p033n;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import com.facebook.ads.internal.n.e;
import com.facebook.ads.internal.p056q.p057a.C2119j;
import com.facebook.ads.internal.p068l.C2005a;
import com.facebook.ads.internal.view.p034b.C2185c;
import java.util.HashMap;
import java.util.Map;

class e$a implements OnClickListener, OnLongClickListener, OnTouchListener {
    final /* synthetic */ e f4799a;

    private e$a(e eVar) {
        this.f4799a = eVar;
    }

    public void onClick(View view) {
        if (!e.f(this.f4799a).m6825d()) {
            Log.e("FBAudienceNetworkLog", "No touch data recorded, please ensure touch events reach the ad View by returning false if you intercept the event.");
        }
        int l = C2005a.m6351l(e.o(this.f4799a));
        if (l < 0 || e.f(this.f4799a).m6824c() >= ((long) l)) {
            Map hashMap = new HashMap();
            hashMap.put("touch", C2119j.m6798a(e.f(this.f4799a).m6826e()));
            if (e.j(this.f4799a) != null) {
                hashMap.put("nti", String.valueOf(e.j(this.f4799a).m6501c()));
            }
            if (e.k(this.f4799a)) {
                hashMap.put("nhs", String.valueOf(e.k(this.f4799a)));
            }
            e.g(this.f4799a).m6919a(hashMap);
            if (this.f4799a.a != null) {
                this.f4799a.a.mo3640b(hashMap);
            }
        } else if (e.f(this.f4799a).m6823b()) {
            Log.e("FBAudienceNetworkLog", "Clicks happened too fast.");
        } else {
            Log.e("FBAudienceNetworkLog", "Ad cannot be clicked before it is viewed.");
        }
    }

    public boolean onLongClick(View view) {
        boolean z = false;
        if (e.i(this.f4799a) == null || e.p(this.f4799a) == null) {
            return false;
        }
        e.p(this.f4799a).setBounds(0, 0, e.i(this.f4799a).getWidth(), e.i(this.f4799a).getHeight());
        C2185c p = e.p(this.f4799a);
        if (!e.p(this.f4799a).m6997a()) {
            z = true;
        }
        p.m6996a(z);
        return true;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        e.f(this.f4799a).m6822a(motionEvent, e.i(this.f4799a), view);
        return e.q(this.f4799a) != null && e.q(this.f4799a).onTouch(view, motionEvent);
    }
}
