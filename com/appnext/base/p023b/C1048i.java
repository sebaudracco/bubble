package com.appnext.base.p023b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.Set;

@SuppressLint({"CommitPrefEdits"})
public class C1048i {
    private static final String TAG = "LibrarySettings";
    public static final String ke = "_lastupdate";
    public static final String kf = "_lastcollectedtime";
    public static final String kg = "_cycles";
    public static final String kh = "_hash";
    public static final String ki = "google_ads_id";
    public static final String kj = "_sent_once";
    public static final String kk = "generated";
    public static final String kl = "db_init";
    public static final String km = "apps_category_saved";
    public static final String kn = "moments_sdk_version";
    public static final String ko = "lat";
    public static final String kp = "usloc_status";
    public static final String kq = "lib_shared_preferences";
    @SuppressLint({"StaticFieldLeak"})
    private static final C1048i kr = new C1048i();
    private SharedPreferences kd;
    private Context mContext = C1043d.getContext();

    private C1048i() {
        if (this.mContext != null) {
            this.kd = this.mContext.getSharedPreferences(kq, 0);
        }
    }

    public static C1048i cy() {
        return kr;
    }

    public void clear() {
        this.kd.edit().clear().apply();
    }

    public void init(Context context) {
        if (context != null) {
            this.mContext = context;
            this.kd = this.mContext.getSharedPreferences(kq, 0);
        }
    }

    public String getString(String str, String str2) {
        if (this.kd != null) {
            return this.kd.getString(str, str2);
        }
        return str2;
    }

    public long getLong(String str, long j) {
        if (this.kd != null) {
            return this.kd.getLong(str, j);
        }
        return j;
    }

    public int getInt(String str, int i) {
        if (this.kd != null) {
            return this.kd.getInt(str, i);
        }
        return i;
    }

    public boolean getBoolean(String str, boolean z) {
        if (this.kd != null) {
            return this.kd.getBoolean(str, z);
        }
        return z;
    }

    public void putLong(String str, long j) {
        if (this.kd != null) {
            this.kd.edit().putLong(str, j).apply();
        }
    }

    public void putInt(String str, int i) {
        if (this.kd != null) {
            this.kd.edit().putInt(str, i).apply();
        }
    }

    public void putBoolean(String str, boolean z) {
        if (this.kd != null) {
            this.kd.edit().putBoolean(str, z).apply();
        }
    }

    public void putString(String str, String str2) {
        if (this.kd != null) {
            this.kd.edit().putString(str, str2).apply();
        }
    }

    public Set<String> getStringSet(String str, Set<String> set) {
        if (this.kd != null) {
            return this.kd.getStringSet(str, set);
        }
        return set;
    }

    public void putStringSet(String str, Set<String> set) {
        if (this.kd != null) {
            this.kd.edit().remove(str);
            this.kd.edit().putStringSet(str, set).apply();
        }
    }
}
