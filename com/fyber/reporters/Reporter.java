package com.fyber.reporters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import com.fyber.p086a.C2408a;
import com.fyber.p094b.C2513k;
import com.fyber.reporters.p109a.C2582c;
import com.fyber.utils.C2646d;
import com.fyber.utils.C2656g;
import com.fyber.utils.C2664l;
import com.fyber.utils.C2672t;
import com.fyber.utils.FyberLogger;
import com.fyber.utils.StringUtils;
import java.util.HashMap;
import java.util.Map;

public abstract class Reporter {
    protected final String f6462b;
    protected Map<String, String> f6463c;

    protected abstract C2672t mo3980a(C2672t c2672t);

    protected abstract C2408a mo3981b();

    protected abstract String mo3986c();

    protected abstract String mo3987d();

    protected abstract C2582c mo3988e();

    public Reporter addParameters(Map<String, String> map) {
        if (C2664l.m8521b(map)) {
            if (this.f6463c == null) {
                this.f6463c = new HashMap(map);
            } else {
                this.f6463c.putAll(map);
            }
        }
        return this;
    }

    public Reporter addParameter(String str, String str2) {
        if (StringUtils.notNullNorEmpty(str)) {
            if (this.f6463c == null) {
                this.f6463c = new HashMap();
            }
            this.f6463c.put(str, str2);
        }
        return this;
    }

    public boolean report(Context context) {
        if (C2656g.m8490f()) {
            C2656g.m8482a(context);
            new Thread(new C2513k(mo3980a(C2672t.m8534a(C2646d.m8469a(mo3986c()), mo3981b()).m8540a(this.f6463c).m8537a()), mo3988e())).start();
            return true;
        }
        String str = "Only devices running Android API level 14 and above are able to report";
        if (FyberLogger.isLogging()) {
            FyberLogger.m8451i(mo3987d(), str);
        } else {
            Log.i(mo3987d(), str);
        }
        return false;
    }

    protected Reporter(@NonNull String str) {
        if (StringUtils.nullOrEmpty(str)) {
            throw new IllegalArgumentException("App id cannot be null nor empty.");
        }
        this.f6462b = str;
    }
}
