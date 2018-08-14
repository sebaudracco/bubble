package com.adcolony.sdk;

import android.content.Intent;
import android.view.View;
import com.adcolony.sdk.aa.C0595a;
import com.adcolony.sdk.d.AnonymousClass17;
import com.adcolony.sdk.d.AnonymousClass18;
import com.adcolony.sdk.d.AnonymousClass19;
import com.adcolony.sdk.d.AnonymousClass20;
import com.adcolony.sdk.d.AnonymousClass21;
import com.google.android.gms.plus.PlusShare;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

class C0690d {
    private HashMap<String, C0673c> f959a;
    private ConcurrentHashMap<String, AdColonyInterstitial> f960b;
    private HashMap<String, bd> f961c;
    private HashMap<String, AdColonyNativeAdViewListener> f962d;
    private HashMap<String, bc> f963e;
    private HashMap<String, C0691f> f964f;

    class C06802 implements ah {
        final /* synthetic */ C0690d f943a;

        C06802(C0690d c0690d) {
            this.f943a = c0690d;
        }

        public void mo1863a(af afVar) {
            this.f943a.m1121e(afVar);
        }
    }

    class C06813 implements ah {
        final /* synthetic */ C0690d f948a;

        C06813(C0690d c0690d) {
            this.f948a = c0690d;
        }

        public void mo1863a(af afVar) {
            this.f948a.m1152a(afVar);
        }
    }

    class C06834 implements ah {
        final /* synthetic */ C0690d f951a;

        C06834(C0690d c0690d) {
            this.f951a = c0690d;
        }

        public void mo1863a(final af afVar) {
            az.m880a(new Runnable(this) {
                final /* synthetic */ C06834 f950b;

                public void run() {
                    AdColonyInterstitial adColonyInterstitial = (AdColonyInterstitial) this.f950b.f951a.f960b.get(C0802y.m1468b(afVar.m698c(), "id"));
                    if (adColonyInterstitial != null && adColonyInterstitial.getListener() != null) {
                        adColonyInterstitial.getListener().onAudioStopped(adColonyInterstitial);
                    }
                }
            });
        }
    }

    class C06855 implements ah {
        final /* synthetic */ C0690d f954a;

        C06855(C0690d c0690d) {
            this.f954a = c0690d;
        }

        public void mo1863a(final af afVar) {
            az.m880a(new Runnable(this) {
                final /* synthetic */ C06855 f953b;

                public void run() {
                    AdColonyInterstitial adColonyInterstitial = (AdColonyInterstitial) this.f953b.f954a.f960b.get(C0802y.m1468b(afVar.m698c(), "id"));
                    if (adColonyInterstitial != null && adColonyInterstitial.getListener() != null) {
                        adColonyInterstitial.getListener().onAudioStarted(adColonyInterstitial);
                    }
                }
            });
        }
    }

    class C06866 implements ah {
        final /* synthetic */ C0690d f955a;

        C06866(C0690d c0690d) {
            this.f955a = c0690d;
        }

        public void mo1863a(af afVar) {
            this.f955a.m1139n(afVar);
        }
    }

    class C06877 implements ah {
        final /* synthetic */ C0690d f956a;

        C06877(C0690d c0690d) {
            this.f956a = c0690d;
        }

        public void mo1863a(af afVar) {
            if (this.f956a.m1156c(afVar)) {
                this.f956a.m1141o(afVar);
            }
        }
    }

    class C06888 implements ah {
        final /* synthetic */ C0690d f957a;

        C06888(C0690d c0690d) {
            this.f957a = c0690d;
        }

        public void mo1863a(af afVar) {
            if (this.f957a.m1156c(afVar)) {
                this.f957a.m1143p(afVar);
            }
        }
    }

    class C06899 implements ah {
        final /* synthetic */ C0690d f958a;

        C06899(C0690d c0690d) {
            this.f958a = c0690d;
        }

        public void mo1863a(af afVar) {
            if (this.f958a.m1156c(afVar)) {
                this.f958a.m1144q(afVar);
            }
        }
    }

    C0690d() {
    }

    private boolean m1119d(af afVar) {
        final JSONObject c = afVar.m698c();
        final String b = C0802y.m1468b(c, "id");
        final bd bdVar = (bd) this.f961c.remove(b);
        final AdColonyNativeAdViewListener adColonyNativeAdViewListener = (AdColonyNativeAdViewListener) this.f962d.remove(b);
        if (bdVar == null && adColonyNativeAdViewListener == null) {
            m1151a(afVar.m699d(), b);
            return false;
        } else if (!C0594a.m614d() || !C0594a.m614d()) {
            return false;
        } else {
            final af afVar2 = afVar;
            az.m880a(new Runnable(this) {
                final /* synthetic */ C0690d f919f;

                public void run() {
                    bc bcVar;
                    if (bdVar != null) {
                        bcVar = new bc(C0594a.m613c(), afVar2, bdVar);
                        this.f919f.f963e.put(b, bcVar);
                    } else {
                        bcVar = new AdColonyNativeAdView(C0594a.m613c(), afVar2, adColonyNativeAdViewListener);
                        this.f919f.f963e.put(b, bcVar);
                    }
                    bcVar.setAdvertiserName(C0802y.m1468b(c, "name"));
                    bcVar.setTitle(C0802y.m1468b(c, "title"));
                    bcVar.setDescription(C0802y.m1468b(c, PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION));
                    bcVar.setImageFilepath(C0802y.m1468b(c, "thumb_filepath"));
                    bcVar.m592b();
                    if (bdVar != null) {
                        bdVar.m1004a(bcVar);
                    } else {
                        adColonyNativeAdViewListener.onRequestFilled((AdColonyNativeAdView) bcVar);
                    }
                }
            });
            return true;
        }
    }

    private boolean m1121e(af afVar) {
        String b = C0802y.m1468b(afVar.m698c(), "id");
        final bd bdVar = (bd) this.f961c.remove(b);
        final AdColonyNativeAdViewListener adColonyNativeAdViewListener = (AdColonyNativeAdViewListener) this.f962d.remove(b);
        if (bdVar == null && adColonyNativeAdViewListener == null) {
            m1151a(afVar.m699d(), b);
            return false;
        } else if (!C0594a.m614d()) {
            return false;
        } else {
            az.m880a(new Runnable(this) {
                final /* synthetic */ C0690d f900c;

                public void run() {
                    Object obj = bdVar == null ? 1 : null;
                    String str = obj != null ? adColonyNativeAdViewListener.a : bdVar.a;
                    AdColonyZone adColonyZone = (AdColonyZone) C0594a.m605a().m1276f().get(str);
                    if (adColonyZone == null) {
                        adColonyZone = new AdColonyZone(str);
                        adColonyZone.m603b(6);
                    }
                    if (obj != null) {
                        adColonyNativeAdViewListener.onRequestNotFilled(adColonyZone);
                    } else {
                        bdVar.m1003a(adColonyZone);
                    }
                }
            });
            return true;
        }
    }

    void m1146a() {
        this.f959a = new HashMap();
        this.f960b = new ConcurrentHashMap();
        this.f961c = new HashMap();
        this.f962d = new HashMap();
        this.f963e = new HashMap();
        this.f964f = new HashMap();
        C0594a.m609a("AdContainer.create", new ah(this) {
            final /* synthetic */ C0690d f929a;

            {
                this.f929a = r1;
            }

            public void mo1863a(af afVar) {
                this.f929a.m1131j(afVar);
            }
        });
        C0594a.m609a("AdContainer.destroy", new ah(this) {
            final /* synthetic */ C0690d f941a;

            {
                this.f941a = r1;
            }

            public void mo1863a(af afVar) {
                this.f941a.m1133k(afVar);
            }
        });
        C0594a.m609a("AdContainer.move_view_to_index", new ah(this) {
            final /* synthetic */ C0690d f942a;

            {
                this.f942a = r1;
            }

            public void mo1863a(af afVar) {
                this.f942a.m1135l(afVar);
            }
        });
        C0594a.m609a("AdContainer.move_view_to_front", new ah(this) {
            final /* synthetic */ C0690d f944a;

            {
                this.f944a = r1;
            }

            public void mo1863a(af afVar) {
                this.f944a.m1137m(afVar);
            }
        });
        C0594a.m609a("AdSession.finish_fullscreen_ad", new ah(this) {
            final /* synthetic */ C0690d f945a;

            {
                this.f945a = r1;
            }

            public void mo1863a(af afVar) {
                this.f945a.m1129i(afVar);
            }
        });
        C0594a.m609a("AdSession.start_fullscreen_ad", new ah(this) {
            final /* synthetic */ C0690d f946a;

            {
                this.f946a = r1;
            }

            public void mo1863a(af afVar) {
                this.f946a.m1127h(afVar);
            }
        });
        C0594a.m609a("AdSession.native_ad_view_available", new ah(this) {
            final /* synthetic */ C0690d f947a;

            {
                this.f947a = r1;
            }

            public void mo1863a(af afVar) {
                this.f947a.m1119d(afVar);
            }
        });
        C0594a.m609a("AdSession.native_ad_view_unavailable", new C06802(this));
        C0594a.m609a("AdSession.expiring", new C06813(this));
        C0594a.m609a("AdSession.audio_stopped", new C06834(this));
        C0594a.m609a("AdSession.audio_started", new C06855(this));
        C0594a.m609a("AudioPlayer.create", new C06866(this));
        C0594a.m609a("AudioPlayer.destroy", new C06877(this));
        C0594a.m609a("AudioPlayer.play", new C06888(this));
        C0594a.m609a("AudioPlayer.pause", new C06899(this));
        C0594a.m609a("AudioPlayer.stop", new ah(this) {
            final /* synthetic */ C0690d f896a;

            {
                this.f896a = r1;
            }

            public void mo1863a(af afVar) {
                if (this.f896a.m1156c(afVar)) {
                    this.f896a.m1145r(afVar);
                }
            }
        });
        C0594a.m609a("AdSession.interstitial_available", new ah(this) {
            final /* synthetic */ C0690d f897a;

            {
                this.f897a = r1;
            }

            public void mo1863a(af afVar) {
                this.f897a.m1125g(afVar);
            }
        });
        C0594a.m609a("AdSession.interstitial_unavailable", new ah(this) {
            final /* synthetic */ C0690d f901a;

            {
                this.f901a = r1;
            }

            public void mo1863a(af afVar) {
                this.f901a.m1154b(afVar);
            }
        });
        C0594a.m609a("AdSession.has_audio", new ah(this) {
            final /* synthetic */ C0690d f902a;

            {
                this.f902a = r1;
            }

            public void mo1863a(af afVar) {
                this.f902a.m1123f(afVar);
            }
        });
        C0594a.m609a("WebView.prepare", new ah(this) {
            final /* synthetic */ C0690d f903a;

            {
                this.f903a = r1;
            }

            public void mo1863a(af afVar) {
                JSONObject a = C0802y.m1453a();
                C0802y.m1465a(a, "success", true);
                afVar.m694a(a).m695b();
            }
        });
        C0594a.m609a("AdSession.iap_event", new ah(this) {
            final /* synthetic */ C0690d f904a;

            {
                this.f904a = r1;
            }

            public void mo1863a(af afVar) {
                JSONObject c = afVar.m698c();
                switch (C0802y.m1473c(c, "type")) {
                    case 2:
                        bc bcVar = (bc) this.f904a.f963e.get(C0802y.m1468b(c, "id"));
                        JSONObject f = C0802y.m1480f(c, "v4iap");
                        JSONArray g = C0802y.m1481g(f, "product_ids");
                        if (bcVar != null && f != null && g.length() > 0) {
                            ((AdColonyNativeAdViewListener) bcVar.getListener()).onIAPEvent((AdColonyNativeAdView) bcVar, C0802y.m1474c(g, 0), C0802y.m1473c(f, "engagement_type"));
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        });
        C0594a.m609a("AdSession.native_ad_view_finished", new ah(this) {
            final /* synthetic */ C0690d f907a;

            {
                this.f907a = r1;
            }

            public void mo1863a(final af afVar) {
                az.m880a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass17 f906b;

                    public void run() {
                        bc bcVar = (bc) this.f906b.f907a.f963e.get(C0802y.m1468b(afVar.m698c(), "id"));
                        if (bcVar != null && bcVar.getListener() != null && (bcVar instanceof AdColonyNativeAdView)) {
                            ((AdColonyNativeAdViewListener) bcVar.getListener()).onNativeVideoFinished((AdColonyNativeAdView) bcVar);
                        }
                    }
                });
            }
        });
        C0594a.m609a("AdSession.native_ad_view_started", new ah(this) {
            final /* synthetic */ C0690d f910a;

            {
                this.f910a = r1;
            }

            public void mo1863a(final af afVar) {
                az.m880a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass18 f909b;

                    public void run() {
                        bc bcVar = (bc) this.f909b.f910a.f963e.get(C0802y.m1468b(afVar.m698c(), "id"));
                        if (bcVar != null && bcVar.getListener() != null && (bcVar instanceof AdColonyNativeAdView)) {
                            ((AdColonyNativeAdViewListener) bcVar.getListener()).onNativeVideoStarted((AdColonyNativeAdView) bcVar);
                        }
                    }
                });
            }
        });
        C0594a.m609a("AdSession.destroy_native_ad_view", new ah(this) {
            final /* synthetic */ C0690d f913a;

            {
                this.f913a = r1;
            }

            public void mo1863a(final af afVar) {
                az.m880a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass19 f912b;

                    public void run() {
                        JSONObject c = afVar.m698c();
                        bc bcVar = (bc) this.f912b.f913a.f963e.get(C0802y.m1468b(c, "id"));
                        if (bcVar != null) {
                            bcVar.m591a();
                            afVar.m694a(c).m695b();
                        }
                    }
                });
            }
        });
        C0594a.m609a("AdSession.expanded", new ah(this) {
            final /* synthetic */ C0690d f922a;

            {
                this.f922a = r1;
            }

            public void mo1863a(final af afVar) {
                az.m880a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass20 f921b;

                    public void run() {
                        afVar.m694a(afVar.m698c()).m695b();
                    }
                });
            }
        });
        C0594a.m609a("AdSession.native_ad_muted", new ah(this) {
            final /* synthetic */ C0690d f925a;

            {
                this.f925a = r1;
            }

            public void mo1863a(final af afVar) {
                az.m880a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass21 f924b;

                    public void run() {
                        JSONObject c = afVar.m698c();
                        bc bcVar = (bc) this.f924b.f925a.f963e.get(C0802y.m1468b(c, "id"));
                        boolean d = C0802y.m1477d(c, "muted");
                        C0592e listener = bcVar != null ? bcVar.getListener() : null;
                        if (!(bcVar instanceof AdColonyNativeAdView) || listener == null) {
                            if (bcVar != null && listener != null) {
                            }
                        } else if (d) {
                            ((AdColonyNativeAdViewListener) listener).onAudioStopped((AdColonyNativeAdView) bcVar);
                        } else {
                            ((AdColonyNativeAdViewListener) listener).onAudioStarted((AdColonyNativeAdView) bcVar);
                        }
                    }
                });
            }
        });
    }

    boolean m1152a(af afVar) {
        JSONObject c = afVar.m698c();
        String b = C0802y.m1468b(c, "id");
        switch (C0802y.m1473c(c, "type")) {
            case 0:
                final AdColonyInterstitial adColonyInterstitial = (AdColonyInterstitial) this.f960b.remove(b);
                final AdColonyInterstitialListener listener = adColonyInterstitial == null ? null : adColonyInterstitial.getListener();
                if (listener != null) {
                    if (C0594a.m614d()) {
                        az.m880a(new Runnable(this) {
                            final /* synthetic */ C0690d f928c;

                            public void run() {
                                adColonyInterstitial.m566a(true);
                                listener.onExpiring(adColonyInterstitial);
                                C0753o r = C0594a.m605a().m1288r();
                                if (r.m1343b() != null) {
                                    r.m1343b().dismiss();
                                    r.m1341a(null);
                                }
                            }
                        });
                        break;
                    }
                    return false;
                }
                m1151a(afVar.m699d(), b);
                return false;
        }
        return true;
    }

    private boolean m1123f(af afVar) {
        String b = C0802y.m1468b(afVar.m698c(), "id");
        JSONObject a = C0802y.m1453a();
        C0802y.m1462a(a, "id", b);
        if (C0594a.m614d()) {
            boolean a2 = az.m879a(az.m869a(C0594a.m613c()));
            double b2 = az.m885b(az.m869a(C0594a.m613c()));
            C0802y.m1465a(a, "has_audio", a2);
            C0802y.m1460a(a, "volume", b2);
            afVar.m694a(a).m695b();
            return a2;
        }
        C0802y.m1465a(a, "has_audio", false);
        afVar.m694a(a).m695b();
        return false;
    }

    private boolean m1125g(af afVar) {
        String b = C0802y.m1468b(afVar.m698c(), "id");
        final AdColonyInterstitial adColonyInterstitial = (AdColonyInterstitial) this.f960b.get(b);
        final AdColonyInterstitialListener listener = adColonyInterstitial == null ? null : adColonyInterstitial.getListener();
        if (listener == null) {
            m1151a(afVar.m699d(), b);
            return false;
        } else if (!C0594a.m614d()) {
            return false;
        } else {
            adColonyInterstitial.m565a(C0802y.m1480f(afVar.m698c(), "ias"));
            adColonyInterstitial.m564a(C0802y.m1468b(afVar.m698c(), "ad_id"));
            adColonyInterstitial.m571b(C0802y.m1468b(afVar.m698c(), "creative_id"));
            if (adColonyInterstitial.m577g()) {
                adColonyInterstitial.m578h().m1182b();
            }
            az.m880a(new Runnable(this) {
                final /* synthetic */ C0690d f932c;

                public void run() {
                    listener.onRequestFilled(adColonyInterstitial);
                }
            });
            return true;
        }
    }

    boolean m1154b(af afVar) {
        String b = C0802y.m1468b(afVar.m698c(), "id");
        final AdColonyInterstitial adColonyInterstitial = (AdColonyInterstitial) this.f960b.remove(b);
        final AdColonyInterstitialListener listener = adColonyInterstitial == null ? null : adColonyInterstitial.getListener();
        if (listener == null) {
            m1151a(afVar.m699d(), b);
            return false;
        } else if (!C0594a.m614d()) {
            return false;
        } else {
            az.m880a(new Runnable(this) {
                final /* synthetic */ C0690d f935c;

                public void run() {
                    AdColonyZone adColonyZone = (AdColonyZone) C0594a.m605a().m1276f().get(adColonyInterstitial.getZoneID());
                    if (adColonyZone == null) {
                        adColonyZone = new AdColonyZone(adColonyInterstitial.getZoneID());
                        adColonyZone.m603b(6);
                    }
                    listener.onRequestNotFilled(adColonyZone);
                }
            });
            return true;
        }
    }

    boolean m1156c(af afVar) {
        String b = C0802y.m1468b(afVar.m698c(), "ad_session_id");
        C0673c c0673c = (C0673c) this.f959a.get(b);
        C0691f c0691f = (C0691f) this.f964f.get(b);
        if (c0673c != null && c0691f != null) {
            return true;
        }
        new C0595a().m622a("Invalid AudioPlayer message!").m624a(aa.f483g);
        return false;
    }

    void m1149a(String str, AdColonyNativeAdViewListener adColonyNativeAdViewListener, AdColonyAdSize adColonyAdSize, AdColonyAdOptions adColonyAdOptions) {
        float o = C0594a.m605a().m1284n().m1324o();
        String e = az.m895e();
        JSONObject a = C0802y.m1453a();
        C0802y.m1462a(a, "zone_id", str);
        C0802y.m1472b(a, "type", 2);
        C0802y.m1472b(a, "width", (int) (((float) adColonyAdSize.f374a) * o));
        C0802y.m1472b(a, "height", (int) (o * ((float) adColonyAdSize.f375b)));
        C0802y.m1462a(a, "id", e);
        adColonyNativeAdViewListener.a = str;
        if (!(adColonyAdOptions == null || adColonyAdOptions.f372d == null)) {
            C0802y.m1464a(a, "options", adColonyAdOptions.f372d);
        }
        this.f962d.put(e, adColonyNativeAdViewListener);
        new af("AdSession.on_request", 1, a).m695b();
    }

    void m1150a(String str, bd bdVar, AdColonyAdSize adColonyAdSize) {
    }

    void m1148a(String str, AdColonyInterstitialListener adColonyInterstitialListener, AdColonyAdOptions adColonyAdOptions) {
        String e = az.m895e();
        C0740l a = C0594a.m605a();
        JSONObject a2 = C0802y.m1453a();
        C0802y.m1462a(a2, "zone_id", str);
        C0802y.m1465a(a2, "fullscreen", true);
        C0802y.m1472b(a2, "width", a.f1295c.m1326q());
        C0802y.m1472b(a2, "height", a.f1295c.m1327r());
        C0802y.m1472b(a2, "type", 0);
        C0802y.m1462a(a2, "id", e);
        new C0595a().m622a("AdSession request with id = ").m622a(e).m624a(aa.f478b);
        AdColonyInterstitial adColonyInterstitial = new AdColonyInterstitial(e, adColonyInterstitialListener, str);
        this.f960b.put(e, adColonyInterstitial);
        if (!(adColonyAdOptions == null || adColonyAdOptions.f372d == null)) {
            adColonyInterstitial.m562a(adColonyAdOptions);
            C0802y.m1464a(a2, "options", adColonyAdOptions.f372d);
        }
        new C0595a().m622a("Requesting AdColony interstitial advertisement.").m624a(aa.f477a);
        new af("AdSession.on_request", 1, a2).m695b();
    }

    private boolean m1127h(af afVar) {
        if (!C0594a.m614d()) {
            return false;
        }
        JSONObject c = afVar.m698c();
        C0740l a = C0594a.m605a();
        String b = C0802y.m1468b(c, "id");
        AdColonyInterstitial adColonyInterstitial = (AdColonyInterstitial) this.f960b.get(b);
        bc bcVar = (bc) this.f963e.get(b);
        int a2 = C0802y.m1449a(c, "orientation", -1);
        Object obj = bcVar != null ? 1 : null;
        if (adColonyInterstitial == null && obj == null) {
            m1151a(afVar.m699d(), b);
            return false;
        }
        JSONObject a3 = C0802y.m1453a();
        C0802y.m1462a(a3, "id", b);
        if (adColonyInterstitial != null) {
            adColonyInterstitial.m561a(C0802y.m1473c(a3, "module_id"));
            adColonyInterstitial.m570b(a2);
            adColonyInterstitial.m567a();
        } else if (obj != null) {
            bcVar.f421b = a2;
            a.m1256a(bcVar.getExpandedContainer());
            a.m1255a(bcVar);
            C0594a.m613c().startActivity(new Intent(C0594a.m613c(), AdColonyAdViewActivity.class));
        }
        return true;
    }

    private boolean m1129i(af afVar) {
        JSONObject c = afVar.m698c();
        int c2 = C0802y.m1473c(c, "status");
        if (c2 == 5 || c2 == 1 || c2 == 0 || c2 == 6) {
            return false;
        }
        String b = C0802y.m1468b(c, "id");
        final AdColonyInterstitial adColonyInterstitial = (AdColonyInterstitial) this.f960b.remove(b);
        final AdColonyInterstitialListener listener = adColonyInterstitial == null ? null : adColonyInterstitial.getListener();
        if (listener == null) {
            m1151a(afVar.m699d(), b);
            return false;
        }
        az.m880a(new Runnable(this) {
            final /* synthetic */ C0690d f938c;

            public void run() {
                C0594a.m605a().m1270c(false);
                listener.onClosed(adColonyInterstitial);
            }
        });
        adColonyInterstitial.m563a(null);
        return true;
    }

    private boolean m1131j(af afVar) {
        if (!C0594a.m614d()) {
            return false;
        }
        JSONObject c = afVar.m698c();
        String b = C0802y.m1468b(c, "ad_session_id");
        C0673c c0673c = new C0673c(C0594a.m613c(), b);
        c0673c.m1055b(afVar);
        if (this.f959a.containsKey(b)) {
            bc bcVar = (bc) this.f963e.get(b);
            if (bcVar == null) {
                return false;
            }
            bcVar.setExpandedContainer(c0673c);
            return true;
        }
        new C0595a().m622a("Inserting container into hash map tied to ad session id: ").m622a(b).m624a(aa.f478b);
        this.f959a.put(b, c0673c);
        if (C0802y.m1473c(c, "width") != 0) {
            c0673c.m1051a(false);
        } else if (((AdColonyInterstitial) this.f960b.get(b)) == null) {
            m1151a(afVar.m699d(), b);
            return false;
        } else {
            ((AdColonyInterstitial) this.f960b.get(b)).m563a(c0673c);
        }
        c = C0802y.m1453a();
        C0802y.m1465a(c, "success", true);
        afVar.m694a(c).m695b();
        return true;
    }

    private boolean m1133k(af afVar) {
        String b = C0802y.m1468b(afVar.m698c(), "ad_session_id");
        C0673c c0673c = (C0673c) this.f959a.get(b);
        if (c0673c == null) {
            m1151a(afVar.m699d(), b);
            return false;
        }
        m1147a(c0673c);
        return true;
    }

    void m1147a(final C0673c c0673c) {
        if (C0594a.m614d()) {
            az.m880a(new Runnable(this) {
                final /* synthetic */ C0690d f940b;

                public void run() {
                    for (int i = 0; i < c0673c.m1080n().size(); i++) {
                        C0594a.m611b((String) c0673c.m1082o().get(i), (ah) c0673c.m1080n().get(i));
                    }
                    c0673c.m1082o().clear();
                    c0673c.m1080n().clear();
                    c0673c.removeAllViews();
                    c0673c.f873d = null;
                    c0673c.f872c = null;
                    new C0595a().m622a("Destroying container tied to ad_session_id = ").m622a(c0673c.m1053b()).m624a(aa.f480d);
                    for (C0792u b : c0673c.m1067g().values()) {
                        b.m1420b();
                    }
                    for (bb bbVar : c0673c.m1068h().values()) {
                        if (!bbVar.m1001g()) {
                            C0594a.m605a().m1260a(bbVar.mo1841a());
                            bbVar.loadUrl("about:blank");
                            bbVar.clearCache(true);
                            bbVar.removeAllViews();
                            bbVar.m991a(true);
                        }
                    }
                    new C0595a().m622a("Stopping and releasing all media players associated with VideoViews tied to ad_session_id = ").m622a(c0673c.m1053b()).m624a(aa.f480d);
                    for (ba baVar : c0673c.m1063e().values()) {
                        baVar.m959d();
                        baVar.m962g();
                    }
                    c0673c.m1063e().clear();
                    c0673c.m1064f().clear();
                    c0673c.m1068h().clear();
                    c0673c.m1067g().clear();
                    c0673c.m1075k().clear();
                    c0673c.m1079m().clear();
                    c0673c.m1072j().clear();
                    c0673c.m1076l().clear();
                    c0673c.f870a = true;
                }
            });
            bc bcVar = (bc) this.f963e.get(c0673c.m1053b());
            if (bcVar == null || bcVar.m593c()) {
                new C0595a().m622a("Removing ad 4").m624a(aa.f478b);
                this.f959a.remove(c0673c.m1053b());
                c0673c.f872c = null;
            }
        }
    }

    void m1151a(String str, String str2) {
        new C0595a().m622a("Message '").m622a(str).m622a("' sent with invalid id: ").m622a(str2).m624a(aa.f483g);
    }

    private boolean m1135l(af afVar) {
        JSONObject c = afVar.m698c();
        String d = afVar.m699d();
        String b = C0802y.m1468b(c, "ad_session_id");
        int c2 = C0802y.m1473c(c, "view_id");
        C0673c c0673c = (C0673c) this.f959a.get(b);
        View view = (View) c0673c.m1079m().get(Integer.valueOf(c2));
        if (c0673c == null) {
            m1151a(d, b);
            return false;
        } else if (view == null) {
            m1151a(d, "" + c2);
            return false;
        } else {
            view.bringToFront();
            return true;
        }
    }

    private boolean m1137m(af afVar) {
        JSONObject c = afVar.m698c();
        String d = afVar.m699d();
        String b = C0802y.m1468b(c, "ad_session_id");
        int c2 = C0802y.m1473c(c, "view_id");
        C0673c c0673c = (C0673c) this.f959a.get(b);
        if (c0673c == null) {
            m1151a(d, b);
            return false;
        }
        C0673c expandedContainer;
        View view;
        if (c0673c.m1060d() == 0 && C0802y.m1473c(c, "id") == 1) {
            bc bcVar = (bc) this.f963e.get(b);
            if (!(bcVar == null || bcVar.getExpandedContainer() == null)) {
                expandedContainer = bcVar.getExpandedContainer();
                view = (View) expandedContainer.m1079m().get(Integer.valueOf(c2));
                if (view != null) {
                    m1151a(d, "" + c2);
                    return false;
                }
                expandedContainer.removeView(view);
                expandedContainer.addView(view, view.getLayoutParams());
                return true;
            }
        }
        expandedContainer = c0673c;
        view = (View) expandedContainer.m1079m().get(Integer.valueOf(c2));
        if (view != null) {
            expandedContainer.removeView(view);
            expandedContainer.addView(view, view.getLayoutParams());
            return true;
        }
        m1151a(d, "" + c2);
        return false;
    }

    private boolean m1139n(af afVar) {
        String b = C0802y.m1468b(afVar.m698c(), "ad_session_id");
        C0673c c0673c = (C0673c) this.f959a.get(b);
        if (c0673c == null) {
            m1151a(afVar.m699d(), b);
            return false;
        }
        C0691f c0691f = (C0691f) this.f964f.get(b);
        if (c0691f == null) {
            c0691f = new C0691f(b, c0673c.m1057c());
            this.f964f.put(b, c0691f);
        }
        c0691f.m1162a(afVar);
        return true;
    }

    private boolean m1141o(af afVar) {
        String b = C0802y.m1468b(afVar.m698c(), "ad_session_id");
        C0691f c0691f = (C0691f) this.f964f.get(b);
        if (c0691f == null) {
            m1151a(afVar.m699d(), b);
            return false;
        }
        c0691f.m1168d(afVar);
        return true;
    }

    private boolean m1143p(af afVar) {
        String b = C0802y.m1468b(afVar.m698c(), "ad_session_id");
        C0691f c0691f = (C0691f) this.f964f.get(b);
        if (c0691f == null) {
            m1151a(afVar.m699d(), b);
            return false;
        }
        c0691f.m1166c(afVar);
        return true;
    }

    private boolean m1144q(af afVar) {
        String b = C0802y.m1468b(afVar.m698c(), "ad_session_id");
        C0691f c0691f = (C0691f) this.f964f.get(b);
        if (c0691f == null) {
            m1151a(afVar.m699d(), b);
            return false;
        }
        c0691f.m1164b(afVar);
        return true;
    }

    private boolean m1145r(af afVar) {
        String b = C0802y.m1468b(afVar.m698c(), "ad_session_id");
        C0691f c0691f = (C0691f) this.f964f.get(b);
        if (c0691f == null) {
            m1151a(afVar.m699d(), b);
            return false;
        }
        c0691f.m1169e(afVar);
        return true;
    }

    HashMap<String, C0673c> m1153b() {
        return this.f959a;
    }

    ConcurrentHashMap<String, AdColonyInterstitial> m1155c() {
        return this.f960b;
    }

    HashMap<String, bd> m1157d() {
        return this.f961c;
    }

    HashMap<String, AdColonyNativeAdViewListener> m1158e() {
        return this.f962d;
    }

    HashMap<String, bc> m1159f() {
        return this.f963e;
    }

    HashMap<String, C0691f> m1160g() {
        return this.f964f;
    }
}
