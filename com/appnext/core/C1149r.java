package com.appnext.core;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import com.appnext.core.C1123e.C0899a;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.io.IOException;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class C1149r {
    private static final String cD = "error_no_market";
    private String banner = "";
    private C1123e click;
    private Context context;
    private String guid = "";
    private String la = "";
    private C0899a mA = new C11483(this);
    private C1131j my;
    private C0897a mz;

    public interface C0897a {
        Ad ac();

        AppnextAd ad();

        C0921q ae();

        void report(String str);
    }

    class C11483 implements C0899a {
        final /* synthetic */ C1149r mD;

        C11483(C1149r c1149r) {
            this.mD = c1149r;
        }

        public void onMarket(String str) {
            AppnextAd ad = this.mD.mz.ad();
            Ad ac = this.mD.mz.ac();
            Context context = ac.getContext();
            if (ad != null && context != null) {
                if (!C1128g.m2356h(context, ad.getAdPackage())) {
                    try {
                        if (!(str.startsWith("market://details?id=" + ad.getAdPackage()) || str.startsWith("http"))) {
                            this.mD.m2397b(C1128g.m2357p("admin.appnext.com", "applink"), ad.getBannerID(), ac.getPlacementID(), ac.getTID(), str, "SetROpenV1");
                        }
                    } catch (Throwable th) {
                    }
                    if (this.mD.my == null) {
                        this.mD.my = new C1131j();
                    }
                    this.mD.my.m2368a(ad.getAdPackage(), str, C1128g.m2357p("admin.appnext.com", "applink"), ad.getBannerID(), ac.getPlacementID(), ac.getTID(), ac.getVID(), ac.getAUID(), null);
                    this.mD.my.m1822C(context);
                    try {
                        this.mD.openLink(str);
                    } catch (Throwable th2) {
                        this.mD.mz.report("error_no_market");
                    }
                } else if (str.startsWith("market://")) {
                    try {
                        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(ad.getAdPackage());
                        launchIntentForPackage.addFlags(ErrorDialogData.BINDER_CRASH);
                        context.startActivity(launchIntentForPackage);
                    } catch (Throwable th3) {
                        this.mD.mz.report("error_no_market");
                    }
                } else {
                    try {
                        this.mD.openLink(str);
                    } catch (Throwable th4) {
                        this.mD.mz.report("error_no_market");
                    }
                }
            }
        }

        public void error(String str) {
            try {
                this.mD.m2397b(C1128g.m2357p("admin.appnext.com", "applink"), this.mD.mz.ad().getBannerID(), this.mD.mz.ac().getPlacementID(), this.mD.mz.ac().getTID(), str, "SetDOpenV1");
            } catch (Throwable th) {
            }
            try {
                if (Boolean.parseBoolean(this.mD.mz.ae().get("urlApp_protection"))) {
                    this.mD.openLink("market://details?id=" + this.mD.mz.ad().getAdPackage());
                } else if (str != null) {
                    try {
                        this.mD.openLink(str);
                    } catch (Throwable th2) {
                        this.mD.mz.report("error_no_market");
                    }
                }
            } catch (Throwable th3) {
            }
        }
    }

    public C1149r(Context context, C0897a c0897a) {
        this.context = context;
        this.click = C1123e.m2320s(context);
        this.mz = c0897a;
    }

    public void m2397b(String str, String str2, String str3, String str4, String str5, String str6) {
        this.click.m2323a(str, str2, str3, str4, str5, str6);
    }

    public void m2395a(final AppnextAd appnextAd, final String str, final C0899a c0899a) {
        if (this.click != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                final /* synthetic */ C1149r mD;

                class C11411 implements C0899a {
                    final /* synthetic */ C11421 mE;

                    C11411(C11421 c11421) {
                        this.mE = c11421;
                    }

                    public void onMarket(String str) {
                        this.mE.mD.la = str;
                        this.mE.mD.guid = C1128g.m2357p("admin.appnext.com", "applink");
                        this.mE.mD.banner = appnextAd.getBannerID();
                        if (c0899a != null) {
                            c0899a.onMarket(str);
                        }
                    }

                    public void error(String str) {
                        this.mE.mD.la = "";
                        this.mE.mD.guid = "";
                        this.mE.mD.banner = "";
                        if (c0899a != null) {
                            c0899a.error(str);
                        }
                    }
                }

                public void run() {
                    try {
                        this.mD.click.m2321a(str + "&device=" + C1128g.cV() + "&ox=0", appnextAd.getBannerID(), new C11411(this));
                    } catch (Throwable th) {
                    }
                }
            });
        }
    }

    public void m2396a(AppnextAd appnextAd, String str, String str2, C0899a c0899a) {
        final String str3 = str;
        final C0899a c0899a2 = c0899a;
        final AppnextAd appnextAd2 = appnextAd;
        final String str4 = str2;
        new Thread(new Runnable(this) {
            final /* synthetic */ C1149r mD;

            class C11431 implements Runnable {
                final /* synthetic */ C11472 mH;

                C11431(C11472 c11472) {
                    this.mH = c11472;
                }

                public void run() {
                    this.mH.mD.mA.error(str3 + "&device=" + C1128g.cV());
                    if (c0899a2 != null) {
                        c0899a2.error(str3 + "&device=" + C1128g.cV());
                    }
                }
            }

            class C11462 implements Runnable {
                final /* synthetic */ C11472 mH;

                class C11441 implements Runnable {
                    final /* synthetic */ C11462 mI;

                    C11441(C11462 c11462) {
                        this.mI = c11462;
                    }

                    public void run() {
                        try {
                            C1128g.m2336a("https://admin.appnext.com/AdminService.asmx/SetRL?guid=" + this.mI.mH.mD.guid + "&bid=" + this.mI.mH.mD.banner + "&pid=" + str4, null);
                        } catch (Throwable th) {
                            C1128g.m2351c(th);
                        }
                    }
                }

                class C11452 implements C0899a {
                    final /* synthetic */ C11462 mI;

                    C11452(C11462 c11462) {
                        this.mI = c11462;
                    }

                    public void onMarket(String str) {
                        this.mI.mH.mD.mA.onMarket(str);
                        if (c0899a2 != null) {
                            c0899a2.onMarket(str);
                        }
                    }

                    public void error(String str) {
                        this.mI.mH.mD.mA.error(str);
                        if (c0899a2 != null) {
                            c0899a2.error(str);
                        }
                    }
                }

                C11462(C11472 c11472) {
                    this.mH = c11472;
                }

                public void run() {
                    if (this.mH.mD.la.equals("") || !this.mH.mD.la.contains(appnextAd2.getAdPackage())) {
                        C1128g.m2333W("click url " + str3);
                        String str = str3 + "&device=" + C1128g.cV();
                        String webview = appnextAd2.getWebview();
                        Object obj = -1;
                        switch (webview.hashCode()) {
                            case 48:
                                if (webview.equals(SchemaSymbols.ATTVAL_FALSE_0)) {
                                    obj = 2;
                                    break;
                                }
                                break;
                            case 49:
                                if (webview.equals(SchemaSymbols.ATTVAL_TRUE_1)) {
                                    obj = 1;
                                    break;
                                }
                                break;
                            case 50:
                                if (webview.equals("2")) {
                                    obj = null;
                                    break;
                                }
                                break;
                        }
                        switch (obj) {
                            case null:
                                try {
                                    this.mH.mD.mA.onMarket(str);
                                    if (c0899a2 != null) {
                                        c0899a2.onMarket(str);
                                        return;
                                    }
                                    return;
                                } catch (Throwable th) {
                                    return;
                                }
                            case 1:
                                Intent intent = new Intent(this.mH.mD.context, ResultActivity.class);
                                intent.putExtra("url", str);
                                intent.putExtra("title", appnextAd2.getAdTitle());
                                intent.addFlags(ErrorDialogData.BINDER_CRASH);
                                this.mH.mD.mz.ac().getContext().startActivity(intent);
                                if (c0899a2 != null) {
                                    c0899a2.onMarket(str);
                                    return;
                                }
                                return;
                            default:
                                this.mH.mD.click.m2322a(str, appnextAd2.getBannerID(), new C11452(this), Long.parseLong(this.mH.mD.mz.ae().get("resolve_timeout")) * 1000);
                                return;
                        }
                    }
                    new Thread(new C11441(this)).start();
                    this.mH.mD.mA.onMarket(this.mH.mD.la);
                    if (c0899a2 != null) {
                        c0899a2.onMarket(this.mH.mD.la);
                    }
                }
            }

            public void run() {
                if (!this.mD.df()) {
                    new Handler(Looper.getMainLooper()).post(new C11431(this));
                } else if (appnextAd2 != null) {
                    new Handler(Looper.getMainLooper()).post(new C11462(this));
                }
            }
        }).start();
    }

    private void openLink(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        intent.addFlags(ErrorDialogData.BINDER_CRASH);
        this.mz.ac().getContext().startActivity(intent);
    }

    public void m2398e(AppnextAd appnextAd) {
        this.click.m2324e(appnextAd);
    }

    protected boolean df() {
        try {
            if (this.context.checkPermission("android.permission.ACCESS_NETWORK_STATE", Process.myPid(), Process.myUid()) != 0) {
                C1128g.m2336a("http://www.appnext.com/myid.html", null);
            } else {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                    throw new IOException();
                }
            }
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    public void destroy() {
        try {
            if (this.my != null) {
                this.my.mo1917d(this.context);
            }
            this.my = null;
        } catch (Throwable th) {
        }
        this.context = null;
        this.click.destroy();
    }
}
