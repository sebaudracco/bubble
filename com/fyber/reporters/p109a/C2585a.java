package com.fyber.reporters.p109a;

import android.content.Context;
import android.content.SharedPreferences;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

/* compiled from: AdvertiserState */
public final class C2585a {
    private SharedPreferences f6468a;

    public C2585a(Context context) {
        this.f6468a = context.getSharedPreferences("FyberPreferences", 0);
    }

    public final void m8264a(String str) {
        this.f6468a.edit().putString("AdvertiserAnswerReceived" + str, SchemaSymbols.ATTVAL_TRUE_1).apply();
    }

    public final String m8266b(String str) {
        return this.f6468a.getString("AdvertiserAnswerReceived" + str, SchemaSymbols.ATTVAL_FALSE_0);
    }

    public final void m8267c(String str) {
        this.f6468a.edit().putString("InstallSubId", str).apply();
    }

    public final String m8263a() {
        return this.f6468a.getString("InstallSubId", "");
    }

    public final void m8268d(String str) {
        this.f6468a.edit().putString("InstallReferrer", str).apply();
    }

    public final String m8265b() {
        return this.f6468a.getString("InstallReferrer", "");
    }
}
