package com.fyber.mediation;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.adcolony.sdk.AdColonyAppOptions;
import com.fyber.Fyber;
import com.fyber.ads.AdFormat;
import com.fyber.ads.banners.mediation.BannerWrapper;
import com.fyber.ads.internal.Offer;
import com.fyber.ads.interstitials.p091b.C2440a;
import com.fyber.ads.videos.mediation.C2465a;
import com.fyber.ads.videos.mediation.TPNVideoEvent;
import com.fyber.mediation.p107a.C2572a;
import com.fyber.p086a.C2408a;
import com.fyber.p094b.C2507f;
import com.fyber.requesters.p097a.p098a.C2610j;
import com.fyber.requesters.p097a.p098a.C2610j.C2490b;
import com.fyber.utils.FyberLogger;
import com.fyber.utils.StringUtils;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/* compiled from: MediationCoordinator */
public final class C2573a {
    public static final C2573a f6454a = new C2573a();
    private boolean f6455b = false;
    private Future<Boolean> f6456c;
    private Map<String, MediationAdapter> f6457d = new HashMap();

    /* compiled from: MediationCoordinator */
    class C25702 implements Runnable {
        final /* synthetic */ C2573a f6451a;

        C25702(C2573a c2573a) {
            this.f6451a = c2573a;
        }

        public final void run() {
            for (MediationAdapter a : this.f6451a.f6457d.values()) {
                a.m8204a();
            }
        }
    }

    /* compiled from: MediationCoordinator */
    class C25713 implements Callable<Boolean> {
        final /* synthetic */ C2573a f6452a;

        C25713(C2573a c2573a) {
            this.f6452a = c2573a;
        }

        public final /* synthetic */ Object call() throws Exception {
            return Boolean.valueOf(false);
        }
    }

    private C2573a() {
    }

    public final void m8217a(final Activity activity) {
        if (!this.f6455b) {
            this.f6455b = true;
            this.f6457d.put(AdColonyAppOptions.FYBER.toLowerCase(Locale.ENGLISH), new C2572a());
            this.f6456c = Fyber.getConfigs().m7599a(new Callable<Boolean>(this) {
                final /* synthetic */ C2573a f6450b;

                public final /* synthetic */ Object call() throws Exception {
                    return m8210a();
                }

                private Boolean m8210a() {
                    try {
                        Class cls = Class.forName("com.fyber.mediation.MediationAdapterStarter");
                        if (((Integer) cls.getMethod("getAdaptersCount", new Class[0]).invoke(null, new Object[0])).intValue() > 0) {
                            Future a;
                            C2408a i = Fyber.getConfigs().m7608i();
                            if (StringUtils.notNullNorEmpty(i.m7594c())) {
                                a = C2507f.m7978a(i, activity);
                            } else {
                                a = null;
                            }
                            Map map = (Map) cls.getMethod("startAdapters", new Class[]{Activity.class, Future.class}).invoke(null, new Object[]{activity, a});
                            if (!map.isEmpty()) {
                                for (Entry entry : map.entrySet()) {
                                    this.f6450b.f6457d.put(((String) entry.getKey()).toLowerCase(Locale.ENGLISH), entry.getValue());
                                }
                            }
                        } else {
                            FyberLogger.m8448d("MediationCoordinator", "No mediation adapters to start");
                        }
                        FyberLogger.m8448d("MediationCoordinator", "Finished sending \"start\" to all the adapters");
                        return Boolean.valueOf(true);
                    } catch (ClassNotFoundException e) {
                        FyberLogger.m8448d("MediationCoordinator", "There was an issue starting mediation for this session");
                        FyberLogger.m8448d("MediationCoordinator", "MediationAdapterStarter class was not found. (if you're currently not using mediation, disregard this message)\nIt could mean that there's a proguard entry missing - \"-keep class com.fyber.mediation.MediationAdapterStarter { *;}\"\nOr the annotation processor did not run at compile time.");
                        return Boolean.valueOf(false);
                    } catch (Exception e2) {
                        FyberLogger.m8448d("MediationCoordinator", "There was an issue starting mediation for this session - " + e2.getMessage());
                        FyberLogger.m8448d("MediationCoordinator", "Make sure you're currently using 'fyber-annotations-compiler' 1.5.+ \nand that you have following proguard entry - \"-keep class com.fyber.mediation.MediationAdapterStarter { *;}\"");
                        return Boolean.valueOf(false);
                    }
                }
            });
        }
    }

    public final Future<Boolean> m8214a() {
        return this.f6456c;
    }

    public final void m8223b() {
        if (this.f6455b) {
            Fyber.getConfigs().m7600a(new C25702(this));
        }
    }

    public final boolean m8220a(@NonNull String str, @NonNull AdFormat adFormat) {
        MediationAdapter mediationAdapter = (MediationAdapter) this.f6457d.get(str.toLowerCase(Locale.ENGLISH));
        if (mediationAdapter != null) {
            return mediationAdapter.m8207a(adFormat);
        }
        return false;
    }

    public final Future<Boolean> m8215a(@NonNull Context context, @NonNull Offer offer) {
        MediationAdapter mediationAdapter = (MediationAdapter) this.f6457d.get(offer.getProviderType().toLowerCase(Locale.ENGLISH));
        if (mediationAdapter != null) {
            return mediationAdapter.m8202a(context, offer);
        }
        return m8212c();
    }

    public final void m8218a(Activity activity, String str, Map<String, String> map, C2465a c2465a) {
        String toLowerCase = str.toLowerCase(Locale.ENGLISH);
        if (m8220a(toLowerCase, AdFormat.REWARDED_VIDEO)) {
            ((MediationAdapter) this.f6457d.get(toLowerCase)).m8206a(activity, c2465a, (Map) map);
        } else {
            c2465a.mo3894a(str, m8213a(str), TPNVideoEvent.AdapterNotIntegrated, map);
        }
    }

    public final Future<Boolean> m8222b(@NonNull Context context, @NonNull Offer offer) {
        MediationAdapter mediationAdapter = (MediationAdapter) this.f6457d.get(offer.getProviderType().toLowerCase(Locale.ENGLISH));
        if (mediationAdapter != null) {
            return mediationAdapter.m8209b(context, offer);
        }
        return m8212c();
    }

    public final boolean m8219a(Activity activity, C2440a c2440a) {
        Offer j = c2440a.m7627j();
        if (j != null) {
            String toLowerCase = j.getProviderType().toLowerCase(Locale.ENGLISH);
            if (m8220a(toLowerCase, AdFormat.INTERSTITIAL)) {
                ((MediationAdapter) this.f6457d.get(toLowerCase)).m8205a(activity, c2440a);
                return true;
            }
        }
        return false;
    }

    public final Future<Boolean> m8216a(@NonNull Context context, @NonNull Offer offer, @Nullable C2490b<BannerWrapper> c2490b) {
        MediationAdapter mediationAdapter = (MediationAdapter) this.f6457d.get(offer.getProviderType().toLowerCase(Locale.ENGLISH));
        if (mediationAdapter != null) {
            return mediationAdapter.m8203a(context, offer, (C2490b) c2490b);
        }
        return m8212c();
    }

    public final String m8213a(@NonNull String str) {
        MediationAdapter mediationAdapter = (MediationAdapter) this.f6457d.get(str.toLowerCase(Locale.ENGLISH));
        if (mediationAdapter != null) {
            return mediationAdapter.getVersion();
        }
        return "";
    }

    public final C2610j m8221b(@NonNull String str, @NonNull AdFormat adFormat) {
        if (m8220a(str, adFormat)) {
            return ((MediationAdapter) this.f6457d.get(str.toLowerCase(Locale.ENGLISH))).m8208b(adFormat);
        }
        return null;
    }

    private Future<Boolean> m8212c() {
        return Fyber.getConfigs().m7599a(new C25713(this));
    }
}
