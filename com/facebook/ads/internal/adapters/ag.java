package com.facebook.ads.internal.adapters;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.facebook.ads.AdError;
import com.facebook.ads.internal.view.p053e.p054b.C2246z;

public class ag extends BroadcastReceiver {
    private String f4198a;
    private af f4199b;
    private ae f4200c;

    public ag(String str, ae aeVar, af afVar) {
        this.f4200c = aeVar;
        this.f4199b = afVar;
        this.f4198a = str;
    }

    public IntentFilter m5748a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(C2246z.REWARDED_VIDEO_COMPLETE.m7092a(this.f4198a));
        intentFilter.addAction(C2246z.REWARDED_VIDEO_ERROR.m7092a(this.f4198a));
        intentFilter.addAction(C2246z.REWARDED_VIDEO_AD_CLICK.m7092a(this.f4198a));
        intentFilter.addAction(C2246z.REWARDED_VIDEO_IMPRESSION.m7092a(this.f4198a));
        intentFilter.addAction(C2246z.REWARDED_VIDEO_CLOSED.m7092a(this.f4198a));
        intentFilter.addAction(C2246z.REWARD_SERVER_SUCCESS.m7092a(this.f4198a));
        intentFilter.addAction(C2246z.REWARD_SERVER_FAILED.m7092a(this.f4198a));
        return intentFilter;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (C2246z.REWARDED_VIDEO_COMPLETE.m7092a(this.f4198a).equals(action)) {
            this.f4199b.mo3610d(this.f4200c);
        } else if (C2246z.REWARDED_VIDEO_ERROR.m7092a(this.f4198a).equals(action)) {
            this.f4199b.mo3607a(this.f4200c, AdError.INTERNAL_ERROR);
        } else if (C2246z.REWARDED_VIDEO_AD_CLICK.m7092a(this.f4198a).equals(action)) {
            this.f4199b.mo3608b(this.f4200c);
        } else if (C2246z.REWARDED_VIDEO_IMPRESSION.m7092a(this.f4198a).equals(action)) {
            this.f4199b.mo3609c(this.f4200c);
        } else if (C2246z.REWARDED_VIDEO_CLOSED.m7092a(this.f4198a).equals(action)) {
            this.f4199b.mo3605a();
        } else if (C2246z.REWARD_SERVER_FAILED.m7092a(this.f4198a).equals(action)) {
            this.f4199b.mo3611e(this.f4200c);
        } else if (C2246z.REWARD_SERVER_SUCCESS.m7092a(this.f4198a).equals(action)) {
            this.f4199b.mo3612f(this.f4200c);
        }
    }
}
