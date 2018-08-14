package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.os.Build.VERSION;
import com.yandex.metrica.impl.utils.C4520d;
import java.util.List;

public class cx {
    public List<cw> m15622a(Context context, List<cw> list) {
        List<cw> a = m15621a(context).mo7071a();
        if (C4520d.m16253a(a, list)) {
            return null;
        }
        return a;
    }

    cv m15621a(Context context) {
        if (VERSION.SDK_INT >= 16) {
            return new cy(context);
        }
        return new cz(context);
    }
}
