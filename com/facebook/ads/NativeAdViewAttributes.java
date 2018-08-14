package com.facebook.ads;

import android.graphics.Typeface;
import com.facebook.ads.internal.p033n.C2028h;
import com.facebook.ads.internal.p052j.C1998a;
import com.facebook.ads.internal.p052j.C1999b;
import org.json.JSONObject;

public class NativeAdViewAttributes {
    private C2028h f4033a;

    public NativeAdViewAttributes() {
        this.f4033a = new C2028h();
    }

    NativeAdViewAttributes(C2028h c2028h) {
        this.f4033a = c2028h;
    }

    public NativeAdViewAttributes(JSONObject jSONObject) {
        try {
            this.f4033a = new C2028h(jSONObject);
        } catch (Throwable e) {
            this.f4033a = new C2028h();
            C1999b.m6321a(C1998a.m6318a(e, "Error retrieving native ui configuration data"));
        }
    }

    C2028h m5503a() {
        return this.f4033a;
    }

    public boolean getAutoplay() {
        return this.f4033a.m6497j();
    }

    public boolean getAutoplayOnMobile() {
        return this.f4033a.m6498k();
    }

    public int getBackgroundColor() {
        return this.f4033a.m6483b();
    }

    public int getButtonBorderColor() {
        return this.f4033a.m6494g();
    }

    public int getButtonColor() {
        return this.f4033a.m6490e();
    }

    public int getButtonTextColor() {
        return this.f4033a.m6492f();
    }

    public int getDescriptionTextColor() {
        return this.f4033a.m6488d();
    }

    public int getDescriptionTextSize() {
        return this.f4033a.m6496i();
    }

    public int getTitleTextColor() {
        return this.f4033a.m6486c();
    }

    public int getTitleTextSize() {
        return this.f4033a.m6495h();
    }

    public Typeface getTypeface() {
        return this.f4033a.m6479a();
    }

    public NativeAdViewAttributes setAutoplay(boolean z) {
        this.f4033a.m6485b(z);
        return this;
    }

    public NativeAdViewAttributes setAutoplayOnMobile(boolean z) {
        this.f4033a.m6482a(z);
        return this;
    }

    public NativeAdViewAttributes setBackgroundColor(int i) {
        this.f4033a.m6480a(i);
        return this;
    }

    public NativeAdViewAttributes setButtonBorderColor(int i) {
        this.f4033a.m6493f(i);
        return this;
    }

    public NativeAdViewAttributes setButtonColor(int i) {
        this.f4033a.m6489d(i);
        return this;
    }

    public NativeAdViewAttributes setButtonTextColor(int i) {
        this.f4033a.m6491e(i);
        return this;
    }

    public NativeAdViewAttributes setDescriptionTextColor(int i) {
        this.f4033a.m6487c(i);
        return this;
    }

    public NativeAdViewAttributes setTitleTextColor(int i) {
        this.f4033a.m6484b(i);
        return this;
    }

    public NativeAdViewAttributes setTypeface(Typeface typeface) {
        this.f4033a.m6481a(typeface);
        return this;
    }
}
