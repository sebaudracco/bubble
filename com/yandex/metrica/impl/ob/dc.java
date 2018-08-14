package com.yandex.metrica.impl.ob;

import android.content.Context;
import java.util.Map;

public class dc extends dd {
    private final dk f12138c = new dk("init_event_pref_key", m15645j());
    private final dk f12139d = new dk("init_event_pref_key");
    private final dk f12140e = new dk("first_event_pref_key", m15645j());
    private final dk f12141f = new dk("fitst_event_description_key", m15645j());

    public dc(Context context, String str) {
        super(context, str);
    }

    public void m15651a() {
        m15640a(this.f12138c.m15730b(), "DONE").m15646k();
    }

    public String m15650a(String str) {
        return this.b.getString(this.f12139d.m15730b(), str);
    }

    public String m15652b(String str) {
        return this.b.getString(this.f12138c.m15730b(), str);
    }

    public String m15654c(String str) {
        return this.b.getString(this.f12140e.m15730b(), str);
    }

    public void m15653b() {
        m15647a(this.f12139d);
    }

    public void m15657d(String str) {
        m15647a(new dk("init_event_pref_key", str));
    }

    public void m15655c() {
        m15647a(this.f12138c);
    }

    public void m15656d() {
        m15647a(this.f12140e);
    }

    public String m15658e(String str) {
        return this.b.getString(this.f12141f.m15730b(), str);
    }

    public void m15659e() {
        m15647a(this.f12141f);
    }

    private void m15647a(dk dkVar) {
        this.b.edit().remove(dkVar.m15730b()).apply();
    }

    protected String mo7072f() {
        return "_initpreferences";
    }

    Map<String, ?> m15661g() {
        return this.b.getAll();
    }

    static String m15648f(String str) {
        return new dk("init_event_pref_key", str).m15730b();
    }

    static String m15649g(String str) {
        return str.replace("init_event_pref_key", "");
    }
}
