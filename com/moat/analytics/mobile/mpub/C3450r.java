package com.moat.analytics.mobile.mpub;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import android.support.annotation.FloatRange;
import android.telephony.TelephonyManager;
import com.a.e.d;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient$Info;
import java.lang.ref.WeakReference;

final class C3450r {
    private static int f8795 = 1;
    private static C3449e f8796 = null;
    private static C3448d f8797 = null;
    private static int f8798 = 0;
    private static String f8799;
    private static int[] f8800 = new int[]{-39340411, 1646369784, -593413711, -1069164445, -50787683, -1327220997, 423245644, -742130253, 54775946, -495304555, 1880137505, 1742082653, 65717847, 1497802820, 828947133, -614454858, 941569790, -1897799303};

    static class C3448d {
        boolean f8785;
        boolean f8786;
        boolean f8787;
        String f8788;
        String f8789;
        Integer f8790;

        private C3448d() {
            this.f8788 = "_unknown_";
            this.f8789 = "_unknown_";
            this.f8790 = Integer.valueOf(-1);
            this.f8787 = false;
            this.f8786 = false;
            this.f8785 = false;
            try {
                Context ˏ = C3450r.m11782();
                if (ˏ != null) {
                    this.f8785 = true;
                    TelephonyManager telephonyManager = (TelephonyManager) ˏ.getSystemService("phone");
                    this.f8788 = telephonyManager.getSimOperatorName();
                    this.f8789 = telephonyManager.getNetworkOperatorName();
                    this.f8790 = Integer.valueOf(telephonyManager.getPhoneType());
                    this.f8787 = C3450r.m11773();
                    this.f8786 = C3450r.m11778(ˏ);
                }
            } catch (Exception e) {
                C3442o.m11756(e);
            }
        }
    }

    static class C3449e {
        private String f8791;
        private String f8792;
        private boolean f8793;
        private String f8794;

        private C3449e() {
            this.f8793 = false;
            this.f8791 = "_unknown_";
            this.f8792 = "_unknown_";
            this.f8794 = "_unknown_";
            try {
                Context ˏ = C3450r.m11782();
                if (ˏ != null) {
                    this.f8793 = true;
                    PackageManager packageManager = ˏ.getPackageManager();
                    this.f8792 = ˏ.getPackageName();
                    this.f8791 = packageManager.getApplicationLabel(ˏ.getApplicationInfo()).toString();
                    this.f8794 = packageManager.getInstallerPackageName(this.f8792);
                    return;
                }
                C3412a.m11642(3, "Util", this, "Can't get app name, appContext is null.");
            } catch (Exception e) {
                C3442o.m11756(e);
            }
        }

        final String m11771() {
            return this.f8791;
        }

        final String m11770() {
            return this.f8792;
        }

        final String m11772() {
            return this.f8794 != null ? this.f8794 : "_unknown_";
        }
    }

    C3450r() {
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    static double m11783() {
        try {
            AudioManager audioManager = (AudioManager) C3416c.m11664().getSystemService(C3450r.m11780(new int[]{-1741845568, 995393484, -1443163044, -1832527325}, 5).intern());
            return ((double) C3450r.m11774()) / ((double) audioManager.getStreamMaxVolume(3));
        } catch (Exception e) {
            C3442o.m11756(e);
            return 0.0d;
        }
    }

    private static int m11774() {
        try {
            return ((AudioManager) C3416c.m11664().getSystemService(C3450r.m11780(new int[]{-1741845568, 995393484, -1443163044, -1832527325}, 5).intern())).getStreamVolume(3);
        } catch (Exception e) {
            C3442o.m11756(e);
            return 0;
        }
    }

    static void m11781(final Application application) {
        try {
            AsyncTask.execute(new Runnable() {
                public final void run() {
                    try {
                        AdvertisingIdClient$Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(application);
                        if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                            C3412a.m11642(3, "Util", this, "User has limited ad tracking");
                            return;
                        }
                        C3450r.f8799 = advertisingIdInfo.getId();
                        C3412a.m11642(3, "Util", this, "Retrieved Advertising ID = " + C3450r.f8799);
                    } catch (Exception e) {
                        C3442o.m11756(e);
                    }
                }
            });
        } catch (Exception e) {
            C3442o.m11756(e);
        }
    }

    static String m11779() {
        return f8799;
    }

    static Context m11782() {
        WeakReference weakReference = ((C3419f) MoatAnalytics.getInstance()).f8684;
        if (weakReference != null) {
            return (Context) weakReference.get();
        }
        return null;
    }

    static C3449e m11776() {
        if (f8796 == null || !f8796.f8793) {
            f8796 = new C3449e();
        }
        return f8796;
    }

    private static String m11780(int[] iArr, int i) {
        char[] cArr = new char[4];
        char[] cArr2 = new char[(iArr.length << 1)];
        int[] iArr2 = (int[]) f8800.clone();
        int i2 = 0;
        while (true) {
            switch (i2 >= iArr.length) {
                case true:
                    return new String(cArr2, 0, i);
                default:
                    cArr[0] = iArr[i2] >>> 16;
                    cArr[1] = (char) iArr[i2];
                    cArr[2] = iArr[i2 + 1] >>> 16;
                    cArr[3] = (char) iArr[i2 + 1];
                    d.ˎ(cArr, iArr2, false);
                    cArr2[i2 << 1] = cArr[0];
                    cArr2[(i2 << 1) + 1] = cArr[1];
                    cArr2[(i2 << 1) + 2] = cArr[2];
                    cArr2[(i2 << 1) + 3] = cArr[3];
                    i2 += 2;
            }
        }
    }

    static C3448d m11777() {
        if (f8797 == null || !f8797.f8785) {
            f8797 = new C3448d();
        }
        return f8797;
    }

    static boolean m11778(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    static /* synthetic */ boolean m11773() {
        Context context;
        int i;
        WeakReference weakReference = ((C3419f) MoatAnalytics.getInstance()).f8684;
        if (weakReference != null) {
            context = (Context) weakReference.get();
        } else {
            context = null;
        }
        if (context == null) {
            i = 0;
        } else {
            i = 1;
        }
        switch (i) {
            case 0:
                i = 0;
                break;
            default:
                Object obj;
                i = f8795 + 27;
                f8798 = i % 128;
                if (i % 2 != 0) {
                }
                if (VERSION.SDK_INT < 17) {
                    obj = 22;
                } else {
                    obj = 19;
                }
                switch (obj) {
                    case 22:
                        i = Secure.getInt(context.getContentResolver(), C3450r.m11780(new int[]{-474338915, -1244865125, 562481890, 44523707, -1306238932, 74746991}, 11).intern(), 0);
                        break;
                    default:
                        i = f8795 + 87;
                        f8798 = i % 128;
                        if (i % 2 != 0) {
                            i = Global.getInt(context.getContentResolver(), C3450r.m11780(new int[]{-474338915, -1244865125, 562481890, 44523707, -1306238932, 74746991}, 11).intern(), 0);
                            break;
                        }
                        i = Global.getInt(context.getContentResolver(), C3450r.m11780(new int[]{-474338915, -1244865125, 562481890, 44523707, -1306238932, 74746991}, 11).intern(), 0);
                }
        }
        if (i != 1) {
            i = 0;
        } else {
            i = 1;
        }
        switch (i) {
            case 1:
                i = f8798 + 33;
                f8795 = i % 128;
                if (i % 2 == 0) {
                }
                return true;
            default:
                return false;
        }
    }
}
