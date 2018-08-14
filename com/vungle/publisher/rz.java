package com.vungle.publisher;

import android.os.Build.VERSION;
import android.util.Log;
import com.vungle.publisher.log.Logger;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public class rz extends vs {
    JSONObject f3322a;
    JSONObject f3323b;
    JSONObject f3324c;
    JSONObject f3325d;
    JSONObject f3326e;
    JSONObject f3327f;
    JSONObject f3328g;
    JSONObject f3329h;
    String f3330i;
    se f3331j;
    Boolean f3332k;
    Boolean f3333l;
    Boolean f3334m;
    Boolean f3335n;
    x f3336o;
    @Inject
    mv f3337p;

    @Singleton
    /* compiled from: vungle */
    public static class C1671a {
        @Inject
        Provider<rz> f3321a;

        @Inject
        C1671a() {
        }

        public rz m4632a(x xVar) {
            rz rzVar = (rz) this.f3321a.get();
            rzVar.m4634a(xVar);
            return rzVar;
        }
    }

    public /* synthetic */ Object m4643b() throws JSONException {
        return m4637a();
    }

    @Inject
    rz() {
    }

    public JSONObject m4637a() throws JSONException {
        JSONObject a = super.a();
        a.putOpt("maxSize", this.f3322a);
        a.putOpt("screenSize", this.f3323b);
        a.putOpt("defaultPosition", this.f3324c);
        a.putOpt("currentPosition", this.f3325d);
        a.putOpt("expandProperties", this.f3326e);
        a.putOpt("resizeProperties", this.f3327f);
        a.putOpt("orientationProperties", this.f3328g);
        a.putOpt("supports", this.f3329h);
        a.putOpt("state", this.f3330i);
        a.putOpt("placementType", this.f3331j);
        a.putOpt("isViewable", this.f3332k);
        a.putOpt("os", "android");
        a.putOpt("osVersion", Integer.toString(VERSION.SDK_INT));
        a.putOpt("startMuted", this.f3333l);
        a.putOpt("incentivized", this.f3334m);
        a.putOpt("enableBackImmediately", this.f3335n);
        a.putOpt("version", "1.0");
        return a;
    }

    public void m4638a(int i, int i2) {
        this.f3322a = m4635c(i, i2);
    }

    public void m4640a(p pVar, boolean z) {
        this.f3334m = Boolean.valueOf(z);
        this.f3335n = Boolean.valueOf(pVar.isBackButtonImmediatelyEnabled());
    }

    public void m4644b(int i, int i2) {
        this.f3323b = m4635c(i, i2);
    }

    private JSONObject m4635c(int i, int i2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("width", i);
            jSONObject.put("height", i2);
        } catch (Throwable e) {
            Logger.e(Logger.PROTOCOL_TAG, "exception setting mraid size properties", e);
        }
        return jSONObject;
    }

    public void m4639a(int i, int i2, int i3, int i4) {
        this.f3324c = m4636c(i, i2, i3, i4);
    }

    public void m4645b(int i, int i2, int i3, int i4) {
        this.f3325d = m4636c(i, i2, i3, i4);
    }

    private JSONObject m4636c(int i, int i2, int i3, int i4) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("x", i);
            jSONObject.put("y", i2);
            jSONObject.put("width", i3);
            jSONObject.put("height", i4);
        } catch (Throwable e) {
            Logger.e(Logger.PROTOCOL_TAG, "exception setting mraid position properties", e);
        }
        return jSONObject;
    }

    public void m4646c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sms", false);
            jSONObject.put("tel", false);
            jSONObject.put("calendar", false);
            jSONObject.put("storePicture", false);
            jSONObject.put("inlineVideo", false);
        } catch (Throwable e) {
            Logger.e(Logger.PROTOCOL_TAG, "exception setting mraid supports properties", e);
        }
        this.f3329h = jSONObject;
    }

    public void m4647d() {
        int a;
        int b;
        switch (1.a[this.f3336o.ordinal()]) {
            case 1:
                a = (int) this.f3337p.m4395a();
                b = (int) this.f3337p.m4398b();
                break;
            case 2:
                a = this.f3337p.m4400c();
                b = this.f3337p.m4402d();
                break;
            default:
                Log.e(Logger.PROTOCOL_TAG, "Unsupported TemplateType: " + this.f3336o);
                b = 0;
                a = 0;
                break;
        }
        m4638a(a, b);
        m4644b(a, b);
        m4639a(0, 0, a, b);
        m4645b(0, 0, a, b);
    }

    public void m4641a(se seVar) {
        this.f3331j = seVar;
    }

    public void m4642a(boolean z) {
        this.f3332k = Boolean.valueOf(z);
    }

    private void m4634a(x xVar) {
        this.f3336o = xVar;
    }
}
