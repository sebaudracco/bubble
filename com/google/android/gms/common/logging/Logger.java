package com.google.android.gms.common.logging;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.internal.GmsLogger;
import java.util.Locale;

public class Logger {
    private final String mTag;
    private final String zzud;
    private final GmsLogger zzvd;
    private final int zzve;

    private Logger(String str, String str2) {
        this.zzud = str2;
        this.mTag = str;
        this.zzvd = new GmsLogger(str);
        int i = 2;
        while (7 >= i && !Log.isLoggable(this.mTag, i)) {
            i++;
        }
        this.zzve = i;
    }

    public Logger(String str, String... strArr) {
        String str2;
        if (strArr.length == 0) {
            str2 = "";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append('[');
            for (String str3 : strArr) {
                if (stringBuilder.length() > 1) {
                    stringBuilder.append(",");
                }
                stringBuilder.append(str3);
            }
            stringBuilder.append(']').append(' ');
            str2 = stringBuilder.toString();
        }
        this(str, str2);
    }

    public void m8597d(String str, Throwable th, @Nullable Object... objArr) {
        if (isLoggable(3)) {
            Log.d(this.mTag, format(str, objArr), th);
        }
    }

    public void m8598d(String str, @Nullable Object... objArr) {
        if (isLoggable(3)) {
            Log.d(this.mTag, format(str, objArr));
        }
    }

    public void m8599e(String str, Throwable th, @Nullable Object... objArr) {
        Log.e(this.mTag, format(str, objArr), th);
    }

    public void m8600e(String str, @Nullable Object... objArr) {
        Log.e(this.mTag, format(str, objArr));
    }

    public String elidePii(Object obj) {
        boolean canLogPii = this.zzvd.canLogPii();
        if (obj == null) {
            return "<NULL>";
        }
        String trim = obj.toString().trim();
        if (trim.isEmpty()) {
            return "<EMPTY>";
        }
        if (canLogPii) {
            return trim;
        }
        return String.format("<ELLIDED:%s>", new Object[]{Integer.valueOf(trim.hashCode())});
    }

    protected String format(String str, @Nullable Object... objArr) {
        if (objArr != null && objArr.length > 0) {
            str = String.format(Locale.US, str, objArr);
        }
        return this.zzud.concat(str);
    }

    public String getTag() {
        return this.mTag;
    }

    public void m8601i(String str, Throwable th, @Nullable Object... objArr) {
        Log.i(this.mTag, format(str, objArr), th);
    }

    public void m8602i(String str, @Nullable Object... objArr) {
        Log.i(this.mTag, format(str, objArr));
    }

    public boolean isLoggable(int i) {
        return this.zzve <= i;
    }

    public boolean isPiiLoggable() {
        return this.zzvd.canLogPii();
    }

    public void m8603v(String str, Throwable th, @Nullable Object... objArr) {
        if (isLoggable(2)) {
            Log.v(this.mTag, format(str, objArr), th);
        }
    }

    public void m8604v(String str, @Nullable Object... objArr) {
        if (isLoggable(2)) {
            Log.v(this.mTag, format(str, objArr));
        }
    }

    public void m8605w(String str, Throwable th, @Nullable Object... objArr) {
        Log.w(this.mTag, format(str, objArr), th);
    }

    public void m8606w(String str, @Nullable Object... objArr) {
        Log.w(this.mTag, format(str, objArr));
    }

    public void m8607w(Throwable th) {
        Log.w(this.mTag, th);
    }

    public void wtf(String str, Throwable th, @Nullable Object... objArr) {
        Log.wtf(this.mTag, format(str, objArr), th);
    }

    @SuppressLint({"WtfWithoutException"})
    public void wtf(String str, @Nullable Object... objArr) {
        Log.wtf(this.mTag, format(str, objArr));
    }

    public void wtf(Throwable th) {
        Log.wtf(this.mTag, th);
    }
}
