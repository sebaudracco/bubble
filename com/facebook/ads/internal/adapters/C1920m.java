package com.facebook.ads.internal.adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.InterstitialAdActivity;
import com.facebook.ads.internal.p055d.C1850a;
import com.facebook.ads.internal.p055d.C1960b;
import com.facebook.ads.internal.p059a.C1877d;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.settings.C2159a.C2158a;
import com.facebook.ads.internal.view.C1923a;
import com.facebook.ads.internal.view.C2364j;
import com.facebook.ads.internal.view.C2366k;
import com.facebook.ads.internal.view.C2374l;
import com.facebook.ads.p051a.C1835a;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.mopub.mobileads.BaseVideoPlayerActivity;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.json.JSONObject;

public class C1920m extends InterstitialAdapter {
    private static final ConcurrentMap<String, C1923a> f4332a = new ConcurrentHashMap();
    private final String f4333b = UUID.randomUUID().toString();
    private String f4334c;
    private long f4335d;
    private Context f4336e;
    private C1937w f4337f;
    private InterstitialAdapterListener f4338g;
    private boolean f4339h = false;
    private C1931r f4340i;
    private C1919a f4341j = C1919a.UNSPECIFIED;
    private C1936v f4342k;
    private C2158a f4343l;

    class C19162 implements C1850a {
        final /* synthetic */ C1920m f4319a;

        C19162(C1920m c1920m) {
            this.f4319a = c1920m;
        }

        private void m5894c() {
            this.f4319a.f4339h = true;
            this.f4319a.f4338g.onInterstitialAdLoaded(this.f4319a);
        }

        public void mo3587a() {
            m5894c();
        }

        public void mo3588b() {
            m5894c();
        }
    }

    public enum C1919a {
        UNSPECIFIED,
        VERTICAL,
        HORIZONTAL;

        public static C1919a m5903a(int i) {
            return i == 0 ? UNSPECIFIED : i == 2 ? HORIZONTAL : VERTICAL;
        }
    }

    private int m5904a() {
        int rotation = ((WindowManager) this.f4336e.getSystemService("window")).getDefaultDisplay().getRotation();
        if (this.f4341j == C1919a.UNSPECIFIED) {
            return -1;
        }
        if (this.f4341j == C1919a.HORIZONTAL) {
            switch (rotation) {
                case 2:
                case 3:
                    return 8;
                default:
                    return 0;
            }
        }
        switch (rotation) {
            case 2:
                return 9;
            default:
                return 1;
        }
    }

    public static C1923a m5907a(String str) {
        return (C1923a) f4332a.get(str);
    }

    public static void m5908a(C1923a c1923a) {
        for (Entry entry : f4332a.entrySet()) {
            if (entry.getValue() == c1923a) {
                f4332a.remove(entry.getKey());
            }
        }
    }

    private static void m5912b(String str, C1923a c1923a) {
        f4332a.put(str, c1923a);
    }

    public void loadInterstitialAd(final Context context, InterstitialAdapterListener interstitialAdapterListener, Map<String, Object> map, final C2012c c2012c, EnumSet<CacheFlag> enumSet) {
        int i = 0;
        this.f4336e = context;
        this.f4338g = interstitialAdapterListener;
        this.f4334c = (String) map.get("placementId");
        this.f4335d = ((Long) map.get(AudienceNetworkActivity.REQUEST_TIME)).longValue();
        JSONObject jSONObject = (JSONObject) map.get("data");
        if (jSONObject.has("markup")) {
            this.f4343l = C2158a.INTERSTITIAL_WEB_VIEW;
            this.f4340i = C1931r.m6042a(jSONObject);
            if (C1877d.m5639a(context, this.f4340i, c2012c)) {
                interstitialAdapterListener.onInterstitialError(this, AdError.NO_FILL);
                return;
            }
            this.f4337f = new C1937w(context, this.f4333b, this, this.f4338g);
            this.f4337f.m6119a();
            Map f = this.f4340i.m6050f();
            if (f.containsKey("orientation")) {
                this.f4341j = C1919a.m5903a(Integer.parseInt((String) f.get("orientation")));
            }
            this.f4339h = true;
            if (this.f4338g != null) {
                this.f4338g.onInterstitialAdLoaded(this);
            }
        } else if (jSONObject.has("video")) {
            this.f4343l = C2158a.INTERSTITIAL_NATIVE_VIDEO;
            this.f4337f = new C1937w(context, this.f4333b, this, this.f4338g);
            this.f4337f.m6119a();
            final C1924n c1924n = new C1924n();
            c1924n.mo3676a(context, new C1835a(this) {
                final /* synthetic */ C1920m f4318b;

                public void mo3564a(C1913u c1913u) {
                    this.f4318b.f4339h = true;
                    if (this.f4318b.f4338g != null) {
                        this.f4318b.f4338g.onInterstitialAdLoaded(this.f4318b);
                    }
                }

                public void mo3565a(C1913u c1913u, View view) {
                    this.f4318b.f4341j = c1924n.m5935k();
                    C1920m.m5912b(this.f4318b.f4333b, c1924n);
                }

                public void mo3566a(C1913u c1913u, AdError adError) {
                    c1924n.m5936l();
                    this.f4318b.f4338g.onInterstitialError(this.f4318b, adError);
                }

                public void mo3567b(C1913u c1913u) {
                    this.f4318b.f4338g.onInterstitialAdClicked(this.f4318b, "", true);
                }

                public void mo3568c(C1913u c1913u) {
                    this.f4318b.f4338g.onInterstitialLoggingImpression(this.f4318b);
                }

                public void mo3569d(C1913u c1913u) {
                }
            }, (Map) map, c2012c, (EnumSet) enumSet);
        } else {
            this.f4342k = C1936v.m6107a(jSONObject, context);
            if (this.f4342k.m6111d().size() == 0) {
                this.f4338g.onInterstitialError(this, AdError.NO_FILL);
            }
            this.f4337f = new C1937w(context, this.f4333b, this, this.f4338g);
            this.f4337f.m6119a();
            C1960b c1960b;
            if (jSONObject.has("carousel")) {
                this.f4343l = C2158a.INTERSTITIAL_NATIVE_CAROUSEL;
                C1920m.m5912b(this.f4333b, new C2364j(context, c2012c));
                c1960b = new C1960b(context);
                c1960b.m6171a(this.f4342k.m6110c(), -1, -1);
                List d = this.f4342k.m6111d();
                while (i < d.size()) {
                    c1960b.m6171a(((C1886d) d.get(i)).m5762f(), ((C1886d) d.get(i)).m5764h(), ((C1886d) d.get(i)).m5763g());
                    i++;
                }
                c1960b.m6169a(new C19162(this));
                this.f4339h = true;
                this.f4338g.onInterstitialAdLoaded(this);
            } else if (jSONObject.has(BaseVideoPlayerActivity.VIDEO_URL)) {
                this.f4343l = C2158a.INTERSTITIAL_NATIVE_VIDEO;
                final C1960b c1960b2 = new C1960b(context);
                c1960b2.m6171a(((C1886d) this.f4342k.m6111d().get(0)).m5762f(), ((C1886d) this.f4342k.m6111d().get(0)).m5764h(), ((C1886d) this.f4342k.m6111d().get(0)).m5763g());
                c1960b2.m6171a(this.f4342k.m6110c(), -1, -1);
                if (enumSet.contains(CacheFlag.VIDEO)) {
                    c1960b2.m6170a(((C1886d) this.f4342k.m6111d().get(0)).m5765i());
                }
                final EnumSet<CacheFlag> enumSet2 = enumSet;
                final Context context2 = context;
                final C2012c c2012c2 = c2012c;
                c1960b2.m6169a(new C1850a(this) {
                    final /* synthetic */ C1920m f4324e;

                    private void m5897a(boolean z) {
                        C1920m.m5912b(this.f4324e.f4333b, new C2374l(context2, c2012c2, this.f4324e.f4342k, z ? c1960b2 : null));
                        this.f4324e.f4339h = true;
                        this.f4324e.f4338g.onInterstitialAdLoaded(this.f4324e);
                    }

                    public void mo3587a() {
                        m5897a(enumSet2.contains(CacheFlag.VIDEO));
                    }

                    public void mo3588b() {
                        m5897a(false);
                    }
                });
            } else {
                this.f4343l = C2158a.INTERSTITIAL_NATIVE_IMAGE;
                c1960b = new C1960b(context);
                c1960b.m6171a(((C1886d) this.f4342k.m6111d().get(0)).m5762f(), ((C1886d) this.f4342k.m6111d().get(0)).m5764h(), ((C1886d) this.f4342k.m6111d().get(0)).m5763g());
                c1960b.m6171a(this.f4342k.m6110c(), -1, -1);
                c1960b.m6169a(new C1850a(this) {
                    final /* synthetic */ C1920m f4327c;

                    private void m5900c() {
                        C1920m.m5912b(this.f4327c.f4333b, new C2366k(context, this.f4327c.f4342k, c2012c));
                        this.f4327c.f4339h = true;
                        this.f4327c.f4338g.onInterstitialAdLoaded(this.f4327c);
                    }

                    public void mo3587a() {
                        m5900c();
                    }

                    public void mo3588b() {
                        m5900c();
                    }
                });
            }
        }
    }

    public void onDestroy() {
        if (this.f4337f != null) {
            this.f4337f.m6120b();
        }
    }

    public boolean show() {
        if (this.f4339h) {
            Intent intent = new Intent(this.f4336e, AudienceNetworkActivity.class);
            intent.putExtra(AudienceNetworkActivity.PREDEFINED_ORIENTATION_KEY, m5904a());
            intent.putExtra(AudienceNetworkActivity.AUDIENCE_NETWORK_UNIQUE_ID_EXTRA, this.f4333b);
            intent.putExtra("placementId", this.f4334c);
            intent.putExtra(AudienceNetworkActivity.REQUEST_TIME, this.f4335d);
            intent.putExtra(AudienceNetworkActivity.VIEW_TYPE, this.f4343l);
            if (this.f4342k != null) {
                intent.putExtra("ad_data_bundle", this.f4342k);
            } else if (this.f4340i != null) {
                this.f4340i.m6045a(intent);
            }
            intent.addFlags(ErrorDialogData.BINDER_CRASH);
            try {
                this.f4336e.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                intent.setClass(this.f4336e, InterstitialAdActivity.class);
                this.f4336e.startActivity(intent);
            }
            return true;
        }
        if (this.f4338g != null) {
            this.f4338g.onInterstitialError(this, AdError.INTERNAL_ERROR);
        }
        return false;
    }
}
