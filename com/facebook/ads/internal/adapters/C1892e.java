package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.facebook.ads.internal.n.e;
import com.facebook.ads.internal.p033n.C2020c;
import com.facebook.ads.internal.p033n.C2026f;
import com.facebook.ads.internal.p033n.C2027g;
import com.facebook.ads.internal.p033n.C2028h;
import com.facebook.ads.internal.p033n.C2030j;
import com.facebook.ads.internal.p033n.e$d;
import com.facebook.ads.internal.p056q.p057a.C2110d;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.C2097a;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader$Builder;
import com.google.android.gms.ads.AdRequest$Builder;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.formats.NativeAdOptions.Builder;
import com.google.android.gms.ads.formats.NativeAdView;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd.OnAppInstallAdLoadedListener;
import com.google.android.gms.ads.formats.NativeAppInstallAdView;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAd.OnContentAdLoadedListener;
import com.google.android.gms.ads.formats.NativeContentAdView;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class C1892e extends ab implements C1891x {
    private static final String f4223a = C1892e.class.getSimpleName();
    private View f4224b;
    private NativeAd f4225c;
    private ac f4226d;
    private NativeAdView f4227e;
    private View f4228f;
    private boolean f4229g;
    private Uri f4230h;
    private Uri f4231i;
    private String f4232j;
    private String f4233k;
    private String f4234l;
    private String f4235m;

    class C18904 implements OnClickListener {
        final /* synthetic */ C1892e f4222a;

        C18904(C1892e c1892e) {
            this.f4222a = c1892e;
        }

        public void onClick(View view) {
            this.f4222a.f4228f.performClick();
        }
    }

    private void m5774a(View view) {
        if (view != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(view);
            }
        }
    }

    public String mo3629A() {
        return null;
    }

    public List<e> mo3630B() {
        return null;
    }

    public int mo3631C() {
        return 0;
    }

    public int mo3632D() {
        return 0;
    }

    public C2020c mo3633E() {
        return C2020c.ADMOB;
    }

    public C1895g mo3634F() {
        return C1895g.ADMOB;
    }

    public void mo3635a(int i) {
    }

    public void mo3636a(final Context context, ac acVar, C2012c c2012c, Map<String, Object> map, e$d com_facebook_ads_internal_n_e_d) {
        C2110d.m6772a(context, C1939y.m6121a(mo3634F()) + " Loading");
        JSONObject jSONObject = (JSONObject) map.get("data");
        String optString = jSONObject.optString("ad_unit_id");
        JSONArray optJSONArray = jSONObject.optJSONArray("creative_types");
        Object obj = null;
        Object obj2 = null;
        if (optJSONArray != null) {
            int length = optJSONArray.length();
            int i = 0;
            while (i < length) {
                try {
                    String string = optJSONArray.getString(i);
                    if (string != null) {
                        Object obj3 = -1;
                        switch (string.hashCode()) {
                            case 704091517:
                                if (string.equals("app_install")) {
                                    obj3 = null;
                                    break;
                                }
                                break;
                            case 883765328:
                                if (string.equals("page_post")) {
                                    obj3 = 1;
                                    break;
                                }
                                break;
                        }
                        switch (obj3) {
                            case null:
                                obj = 1;
                                break;
                            case 1:
                                obj2 = 1;
                                break;
                            default:
                                break;
                        }
                    }
                    i++;
                } catch (JSONException e) {
                    C2110d.m6772a(context, C1939y.m6121a(mo3634F()) + " AN server error");
                    acVar.mo3602a(this, C2097a.m6746a(AdErrorType.SERVER_ERROR, "Server Error"));
                    return;
                }
            }
        }
        if (TextUtils.isEmpty(optString) || (obj == null && obj2 == null)) {
            C2110d.m6772a(context, C1939y.m6121a(mo3634F()) + " AN server error");
            acVar.mo3602a(this, C2097a.m6746a(AdErrorType.SERVER_ERROR, "Server Error"));
            return;
        }
        this.f4226d = acVar;
        AdLoader$Builder adLoader$Builder = new AdLoader$Builder(context, optString);
        if (obj != null) {
            adLoader$Builder.forAppInstallAd(new OnAppInstallAdLoadedListener(this) {
                final /* synthetic */ C1892e f4217b;

                public void onAppInstallAdLoaded(NativeAppInstallAd nativeAppInstallAd) {
                    Uri uri = null;
                    this.f4217b.f4225c = nativeAppInstallAd;
                    this.f4217b.f4229g = true;
                    this.f4217b.f4232j = nativeAppInstallAd.getHeadline() != null ? nativeAppInstallAd.getHeadline().toString() : null;
                    this.f4217b.f4233k = nativeAppInstallAd.getBody() != null ? nativeAppInstallAd.getBody().toString() : null;
                    this.f4217b.f4235m = nativeAppInstallAd.getStore() != null ? nativeAppInstallAd.getStore().toString() : null;
                    this.f4217b.f4234l = nativeAppInstallAd.getCallToAction() != null ? nativeAppInstallAd.getCallToAction().toString() : null;
                    List images = nativeAppInstallAd.getImages();
                    C1892e c1892e = this.f4217b;
                    Uri uri2 = (images == null || images.size() <= 0) ? null : ((Image) images.get(0)).getUri();
                    c1892e.f4230h = uri2;
                    C1892e c1892e2 = this.f4217b;
                    if (nativeAppInstallAd.getIcon() != null) {
                        uri = nativeAppInstallAd.getIcon().getUri();
                    }
                    c1892e2.f4231i = uri;
                    if (this.f4217b.f4226d != null) {
                        C2110d.m6772a(context, C1939y.m6121a(this.f4217b.mo3634F()) + " Loaded");
                        this.f4217b.f4226d.mo3601a(this.f4217b);
                    }
                }
            });
        }
        if (obj2 != null) {
            adLoader$Builder.forContentAd(new OnContentAdLoadedListener(this) {
                final /* synthetic */ C1892e f4219b;

                public void onContentAdLoaded(NativeContentAd nativeContentAd) {
                    Uri uri = null;
                    this.f4219b.f4225c = nativeContentAd;
                    this.f4219b.f4229g = true;
                    this.f4219b.f4232j = nativeContentAd.getHeadline() != null ? nativeContentAd.getHeadline().toString() : null;
                    this.f4219b.f4233k = nativeContentAd.getBody() != null ? nativeContentAd.getBody().toString() : null;
                    this.f4219b.f4235m = nativeContentAd.getAdvertiser() != null ? nativeContentAd.getAdvertiser().toString() : null;
                    this.f4219b.f4234l = nativeContentAd.getCallToAction() != null ? nativeContentAd.getCallToAction().toString() : null;
                    List images = nativeContentAd.getImages();
                    C1892e c1892e = this.f4219b;
                    Uri uri2 = (images == null || images.size() <= 0) ? null : ((Image) images.get(0)).getUri();
                    c1892e.f4230h = uri2;
                    C1892e c1892e2 = this.f4219b;
                    if (nativeContentAd.getLogo() != null) {
                        uri = nativeContentAd.getLogo().getUri();
                    }
                    c1892e2.f4231i = uri;
                    if (this.f4219b.f4226d != null) {
                        C2110d.m6772a(context, C1939y.m6121a(this.f4219b.mo3634F()) + " Loaded");
                        this.f4219b.f4226d.mo3601a(this.f4219b);
                    }
                }
            });
        }
        adLoader$Builder.withAdListener(new AdListener(this) {
            final /* synthetic */ C1892e f4221b;

            public void onAdFailedToLoad(int i) {
                C2110d.m6772a(context, C1939y.m6121a(this.f4221b.mo3634F()) + " Failed with error code: " + i);
                if (this.f4221b.f4226d != null) {
                    this.f4221b.f4226d.mo3602a(this.f4221b, new C2097a(AdErrorType.MEDIATION_ERROR.getErrorCode(), "AdMob error code: " + i));
                }
            }

            public void onAdOpened() {
                if (this.f4221b.f4226d != null) {
                    this.f4221b.f4226d.mo3604c(this.f4221b);
                }
            }
        }).withNativeAdOptions(new Builder().setReturnUrlsForImageAssets(true).build()).build().loadAd(new AdRequest$Builder().build());
    }

    public void mo3637a(View view, List<View> list) {
        this.f4224b = view;
        if (c_() && view != null) {
            ViewGroup viewGroup;
            int i;
            int i2 = -1;
            ViewGroup viewGroup2 = null;
            while (true) {
                ViewGroup viewGroup3 = (ViewGroup) view.getParent();
                if (viewGroup3 == null) {
                    Log.e(f4223a, "View must have valid parent for AdMob registration, skipping registration. Impressions and clicks will not be logged.");
                    return;
                }
                if (viewGroup3 instanceof NativeAdView) {
                    viewGroup = (ViewGroup) viewGroup3.getParent();
                    if (viewGroup == null) {
                        Log.e(f4223a, "View must have valid parent for AdMob registration, skipping registration. Impressions and clicks will not be logged.");
                        return;
                    }
                    int indexOfChild = viewGroup.indexOfChild(viewGroup3);
                    viewGroup3.removeView(view);
                    viewGroup.removeView(viewGroup3);
                    viewGroup.addView(view, indexOfChild);
                    i = i2;
                    viewGroup = viewGroup2;
                } else {
                    viewGroup = viewGroup3;
                    i = viewGroup3.indexOfChild(view);
                }
                if (viewGroup != null) {
                    break;
                }
                i2 = i;
                viewGroup2 = viewGroup;
            }
            View nativeContentAdView = this.f4225c instanceof NativeContentAd ? new NativeContentAdView(view.getContext()) : new NativeAppInstallAdView(view.getContext());
            if (view instanceof ViewGroup) {
                nativeContentAdView.setLayoutParams(view.getLayoutParams());
            }
            m5774a(view);
            nativeContentAdView.addView(view);
            viewGroup.removeView(nativeContentAdView);
            viewGroup.addView(nativeContentAdView, i);
            this.f4227e = nativeContentAdView;
            this.f4227e.setNativeAd(this.f4225c);
            this.f4228f = new View(view.getContext());
            this.f4227e.addView(this.f4228f);
            this.f4228f.setVisibility(8);
            if (this.f4227e instanceof NativeContentAdView) {
                ((NativeContentAdView) this.f4227e).setCallToActionView(this.f4228f);
            } else if (this.f4227e instanceof NativeAppInstallAdView) {
                ((NativeAppInstallAdView) this.f4227e).setCallToActionView(this.f4228f);
            }
            OnClickListener c18904 = new C18904(this);
            for (View onClickListener : list) {
                onClickListener.setOnClickListener(c18904);
            }
        }
    }

    public void mo3638a(ac acVar) {
        this.f4226d = acVar;
    }

    public void mo3639a(Map<String, String> map) {
        if (c_() && this.f4226d != null) {
            this.f4226d.mo3603b(this);
        }
    }

    public void mo3640b(Map<String, String> map) {
    }

    public void b_() {
        m5774a(this.f4228f);
        this.f4228f = null;
        if (this.f4224b != null) {
            View view = (ViewGroup) this.f4224b.getParent();
            if ((view instanceof NativeContentAdView) || (view instanceof NativeAppInstallAdView)) {
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                if (viewGroup != null) {
                    int indexOfChild = viewGroup.indexOfChild(view);
                    m5774a(this.f4224b);
                    m5774a(view);
                    viewGroup.addView(this.f4224b, indexOfChild);
                }
            }
            this.f4224b = null;
        }
        this.f4227e = null;
    }

    public String mo3642c() {
        return null;
    }

    public boolean c_() {
        return this.f4229g && this.f4225c != null;
    }

    public boolean mo3644d() {
        return false;
    }

    public boolean mo3645e() {
        return false;
    }

    public boolean mo3646f() {
        return false;
    }

    public boolean mo3647g() {
        return false;
    }

    public boolean mo3648h() {
        return false;
    }

    public int mo3649i() {
        return 0;
    }

    public int mo3650j() {
        return 0;
    }

    public int mo3651k() {
        return 0;
    }

    public C2026f mo3652l() {
        return (!c_() || this.f4231i == null) ? null : new C2026f(this.f4231i.toString(), 50, 50);
    }

    public C2026f mo3653m() {
        return (!c_() || this.f4230h == null) ? null : new C2026f(this.f4230h.toString(), 1200, Settings.MAX_DYNAMIC_ACQUISITION);
    }

    public C2028h mo3654n() {
        return null;
    }

    public String mo3655o() {
        return this.f4232j;
    }

    public void onDestroy() {
        b_();
        this.f4226d = null;
        this.f4225c = null;
        this.f4229g = false;
        this.f4230h = null;
        this.f4231i = null;
        this.f4232j = null;
        this.f4233k = null;
        this.f4234l = null;
        this.f4235m = null;
    }

    public String mo3657p() {
        return null;
    }

    public String mo3658q() {
        return this.f4233k;
    }

    public String mo3659r() {
        return this.f4234l;
    }

    public String mo3660s() {
        return this.f4235m;
    }

    public C2027g mo3661t() {
        return null;
    }

    public C2026f mo3662u() {
        return null;
    }

    public String mo3663v() {
        return null;
    }

    public String mo3664w() {
        return null;
    }

    public String mo3665x() {
        return null;
    }

    public String mo3666y() {
        return null;
    }

    public C2030j mo3667z() {
        return C2030j.DEFAULT;
    }
}
