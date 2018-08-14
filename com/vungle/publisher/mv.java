package com.vungle.publisher;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class mv {
    @Inject
    Context f3136a;
    public int f3137b;
    public int f3138c;

    @Inject
    mv() {
    }

    public float m4396a(int i) {
        return TypedValue.applyDimension(1, (float) i, this.f3136a.getResources().getDisplayMetrics());
    }

    public float m4395a() {
        DisplayMetrics displayMetrics = this.f3136a.getResources().getDisplayMetrics();
        return ((float) displayMetrics.widthPixels) / displayMetrics.density;
    }

    public float m4398b() {
        DisplayMetrics displayMetrics = this.f3136a.getResources().getDisplayMetrics();
        return ((float) displayMetrics.heightPixels) / displayMetrics.density;
    }

    public int m4400c() {
        return this.f3137b;
    }

    public void m4399b(int i) {
        this.f3137b = i;
    }

    public int m4402d() {
        return this.f3138c;
    }

    public void m4401c(int i) {
        this.f3138c = i;
    }

    public void m4397a(DisplayMetrics displayMetrics, boolean z) {
        if (z) {
            m4399b((int) (((float) displayMetrics.widthPixels) / displayMetrics.density));
            m4401c((int) (((float) Math.round(((double) displayMetrics.widthPixels) * 0.5625d)) / displayMetrics.density));
            return;
        }
        m4399b((int) (((float) Math.round(((double) displayMetrics.heightPixels) * 0.5625d)) / displayMetrics.density));
        m4401c((int) (((float) displayMetrics.heightPixels) / displayMetrics.density));
    }
}
