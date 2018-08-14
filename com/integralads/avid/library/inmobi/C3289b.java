package com.integralads.avid.library.inmobi;

import android.content.Context;
import com.stripe.android.net.StripeApiHandler;

/* compiled from: AvidContext */
public class C3289b {
    private static final C3289b f8426a = new C3289b();
    private String f8427b;

    public static C3289b m11196a() {
        return f8426a;
    }

    public void m11197a(Context context) {
        if (this.f8427b == null) {
            this.f8427b = context.getApplicationContext().getPackageName();
        }
    }

    public String m11198b() {
        return this.f8427b;
    }

    public String m11199c() {
        return StripeApiHandler.VERSION;
    }

    public String m11200d() {
        return "inmobi";
    }
}
