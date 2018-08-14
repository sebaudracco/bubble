package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class dd {
    private static final dk f12133c = new dk("UNDEFINED_");
    protected final String f12134a;
    protected final SharedPreferences f12135b;
    private final Map<String, Object> f12136d = new HashMap();
    private boolean f12137e = false;

    protected abstract String mo7072f();

    public dd(Context context, String str) {
        this.f12134a = str;
        this.f12135b = m15639a(context);
        mo7073h();
    }

    protected void mo7073h() {
        dk dkVar = new dk(f12133c.m15728a(), this.f12134a);
    }

    protected SharedPreferences m15639a(Context context) {
        return dl.m15731a(context, mo7072f());
    }

    protected <T extends dd> T m15640a(String str, Object obj) {
        synchronized (this) {
            if (obj != null) {
                this.f12136d.put(str, obj);
            }
        }
        return this;
    }

    protected <T extends dd> T m15642h(String str) {
        synchronized (this) {
            this.f12136d.put(str, this);
        }
        return this;
    }

    protected <T extends dd> T m15644i() {
        synchronized (this) {
            this.f12137e = true;
            this.f12136d.clear();
        }
        return this;
    }

    protected String m15645j() {
        return this.f12134a;
    }

    public void m15646k() {
        synchronized (this) {
            Editor edit = this.f12135b.edit();
            if (this.f12137e) {
                edit.clear();
                edit.apply();
            } else {
                for (Entry entry : this.f12136d.entrySet()) {
                    String str = (String) entry.getKey();
                    dd value = entry.getValue();
                    if (value == this) {
                        edit.remove(str);
                    } else if (value instanceof String) {
                        edit.putString(str, (String) value);
                    } else if (value instanceof Long) {
                        edit.putLong(str, ((Long) value).longValue());
                    } else if (value instanceof Integer) {
                        edit.putInt(str, ((Integer) value).intValue());
                    } else if (value instanceof Boolean) {
                        edit.putBoolean(str, ((Boolean) value).booleanValue());
                    } else if (value != null) {
                        throw new UnsupportedOperationException();
                    }
                }
                edit.apply();
            }
            this.f12136d.clear();
            this.f12137e = false;
        }
    }
}
