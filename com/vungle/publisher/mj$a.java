package com.vungle.publisher;

import android.content.Context;
import android.content.SharedPreferences;
import android.webkit.WebView;
import com.vungle.publisher.env.i;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class mj$a {
    @Inject
    qg f3116a;
    @Inject
    i f3117b;
    @Inject
    SharedPreferences f3118c;
    @Inject
    bz f3119d;

    @Inject
    mj$a() {
    }

    public void m4379a(Context context) {
        if (this.f3118c.getString("defaultUserAgent", null) == null) {
            this.f3119d.m3482a(mk.m4386a(this, context));
        } else {
            this.f3116a.m4568a(new pg());
        }
    }

    /* synthetic */ void m4380b(Context context) {
        this.f3117b.a(new WebView(context));
        this.f3116a.m4568a(new pg());
    }
}
