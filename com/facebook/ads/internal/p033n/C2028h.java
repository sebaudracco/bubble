package com.facebook.ads.internal.p033n;

import android.graphics.Color;
import android.graphics.Typeface;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.google.ads.mediation.facebook.FacebookAdapter;
import org.json.JSONObject;

public class C2028h {
    private Typeface f4807a = Typeface.DEFAULT;
    private int f4808b = -1;
    private int f4809c = -16777216;
    private int f4810d = -11643291;
    private int f4811e = 0;
    private int f4812f = -12420889;
    private int f4813g = -12420889;
    private boolean f4814h = AdInternalSettings.isVideoAutoplay();
    private boolean f4815i = AdInternalSettings.isVideoAutoplayOnMobile();

    public C2028h(JSONObject jSONObject) {
        int parseColor = jSONObject.getBoolean("background_transparent") ? 0 : Color.parseColor(jSONObject.getString(FacebookAdapter.KEY_BACKGROUND_COLOR));
        int parseColor2 = Color.parseColor(jSONObject.getString(FacebookAdapter.KEY_TITLE_TEXT_COLOR));
        int parseColor3 = Color.parseColor(jSONObject.getString(FacebookAdapter.KEY_DESCRIPTION_TEXT_COLOR));
        int parseColor4 = jSONObject.getBoolean("button_transparent") ? 0 : Color.parseColor(jSONObject.getString(FacebookAdapter.KEY_BUTTON_COLOR));
        int parseColor5 = jSONObject.getBoolean("button_border_transparent") ? 0 : Color.parseColor(jSONObject.getString(FacebookAdapter.KEY_BUTTON_BORDER_COLOR));
        int parseColor6 = Color.parseColor(jSONObject.getString(FacebookAdapter.KEY_BUTTON_TEXT_COLOR));
        Typeface create = Typeface.create(jSONObject.getString("android_typeface"), 0);
        this.f4808b = parseColor;
        this.f4809c = parseColor2;
        this.f4810d = parseColor3;
        this.f4811e = parseColor4;
        this.f4813g = parseColor5;
        this.f4812f = parseColor6;
        this.f4807a = create;
    }

    public Typeface m6479a() {
        return this.f4807a;
    }

    public void m6480a(int i) {
        this.f4808b = i;
    }

    public void m6481a(Typeface typeface) {
        this.f4807a = typeface;
    }

    public void m6482a(boolean z) {
        this.f4815i = z;
    }

    public int m6483b() {
        return this.f4808b;
    }

    public void m6484b(int i) {
        this.f4809c = i;
    }

    public void m6485b(boolean z) {
        this.f4814h = z;
    }

    public int m6486c() {
        return this.f4809c;
    }

    public void m6487c(int i) {
        this.f4810d = i;
    }

    public int m6488d() {
        return this.f4810d;
    }

    public void m6489d(int i) {
        this.f4811e = i;
    }

    public int m6490e() {
        return this.f4811e;
    }

    public void m6491e(int i) {
        this.f4812f = i;
    }

    public int m6492f() {
        return this.f4812f;
    }

    public void m6493f(int i) {
        this.f4813g = i;
    }

    public int m6494g() {
        return this.f4813g;
    }

    public int m6495h() {
        return 16;
    }

    public int m6496i() {
        return 10;
    }

    public boolean m6497j() {
        return this.f4814h;
    }

    public boolean m6498k() {
        return this.f4815i;
    }
}
