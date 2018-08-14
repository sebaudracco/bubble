package com.oneaudience.sdk;

import android.app.Activity;
import android.content.Context;
import com.oneaudience.sdk.p135c.C3833d;

public class OneAudience {
    public static final int GENDER_FEMALE = 1;
    public static final int GENDER_MALE = 0;
    public static final int GENDER_UNKNOWN = 2;
    public static final int VERSION_CODE = 6000;
    public static final String VERSION_NAME = "6.0.0.0";
    private static String f9051a = "";
    private static int f9052b = -1;
    private static int f9053c = -1;
    private static final String[] f9054d = new String[]{"android.permission.INTERNET"};

    private OneAudience() {
    }

    private static void m12075a(Context context) {
        for (String enforceCallingOrSelfPermission : f9054d) {
            context.enforceCallingOrSelfPermission(enforceCallingOrSelfPermission, String.format("OneAudience SDK requires permission %s, please add it in the Manifest.", new Object[]{r2[r0]}));
        }
    }

    private static void m12076a(Context context, String str, boolean z) {
        C3833d.m12246a("OneAudience", "SDK Started");
        m12075a(context);
        if (C3861k.m12321a(context).m12335a(context, str, z)) {
            C3861k.m12321a(context).m12333a(str, context, z);
            if (!f9051a.isEmpty()) {
                setEmailAddress(f9051a);
            }
            if (f9052b != -1) {
                setAge(f9052b);
            }
            if (f9053c != -1) {
                setGender(f9053c);
            }
        }
    }

    public static final void init(Activity activity, String str, boolean z) {
        try {
            m12076a(activity, str, z);
        } catch (Throwable th) {
            C3833d.m12250b("OneAudience", "SDK initialize failed: ", th);
        }
    }

    public static final void init(Context context, String str) {
        try {
            m12076a(context, str, false);
        } catch (Throwable th) {
            C3833d.m12250b("OneAudience", "SDK initialize failed: ", th);
        }
    }

    public static final void optOut() {
        try {
            if (C3861k.m12324a()) {
                C3861k.m12321a(null).m12336b();
            }
        } catch (Throwable th) {
            C3833d.m12250b("OneAudience", "Opt-Out failed: ", th);
        }
    }

    public static final void setAge(int i) {
        try {
            if (C3861k.m12324a()) {
                C3861k.m12321a(null).m12331a(i);
            } else {
                f9052b = i;
            }
        } catch (Throwable th) {
            C3833d.m12248a("OneAudience", "Could not set age: ", th);
        }
    }

    public static final void setEmailAddress(String str) {
        try {
            if (C3861k.m12324a()) {
                C3861k.m12321a(null).m12332a(str);
            } else {
                f9051a = str;
            }
        } catch (Throwable th) {
            C3833d.m12248a("OneAudience", "Could not set email: ", th);
        }
    }

    public static final void setGender(int i) {
        try {
            if (C3861k.m12324a()) {
                C3861k.m12321a(null).m12337b(i);
            } else {
                f9053c = i;
            }
        } catch (Throwable th) {
            C3833d.m12248a("OneAudience", "Could not set gender: ", th);
        }
    }
}
