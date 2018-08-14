package com.fyber.mediation;

import android.app.Activity;
import android.content.Context;
import com.fyber.Fyber;
import com.fyber.ads.AdFormat;
import com.fyber.ads.banners.mediation.BannerMediationAdapter;
import com.fyber.ads.banners.mediation.BannerWrapper;
import com.fyber.ads.banners.mediation.C2422a;
import com.fyber.ads.internal.Offer;
import com.fyber.ads.interstitials.mediation.InterstitialMediationAdapter;
import com.fyber.ads.interstitials.p091b.C2440a;
import com.fyber.ads.videos.mediation.C2465a;
import com.fyber.ads.videos.mediation.RewardedVideoMediationAdapter;
import com.fyber.cache.CacheManager;
import com.fyber.exceptions.C2565a;
import com.fyber.requesters.p097a.p098a.C2610j;
import com.fyber.requesters.p097a.p098a.C2610j.C2490b;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

public abstract class MediationAdapter {
    private C2610j<Boolean, C2565a> f6446a;
    private C2610j<BannerWrapper, C2422a> f6447b;
    private C2610j<Boolean, C2565a> f6448c;

    protected abstract BannerMediationAdapter<? extends MediationAdapter> getBannerMediationAdapter();

    protected abstract InterstitialMediationAdapter<? extends MediationAdapter> getInterstitialMediationAdapter();

    protected abstract Set<?> getListeners();

    public abstract String getName();

    public abstract String getVersion();

    protected abstract RewardedVideoMediationAdapter<? extends MediationAdapter> getVideoMediationAdapter();

    public abstract boolean startAdapter(Activity activity, Map<String, Object> map);

    public final boolean m8207a(AdFormat adFormat) {
        switch (adFormat) {
            case REWARDED_VIDEO:
                if (getVideoMediationAdapter() == null) {
                    return false;
                }
                return true;
            case INTERSTITIAL:
                if (getInterstitialMediationAdapter() == null) {
                    return false;
                }
                return true;
            case BANNER:
                return getBannerMediationAdapter() != null;
            default:
                return false;
        }
    }

    public final Future<Boolean> m8202a(Context context, Offer offer) {
        if (this.f6448c == null) {
            this.f6448c = Fyber.getConfigs().m7596a(getVideoMediationAdapter()).m8370a();
        }
        return this.f6448c.m8375a(context, offer);
    }

    public final void m8206a(Activity activity, C2465a c2465a, Map<String, String> map) {
        if (getVideoMediationAdapter() != null) {
            getVideoMediationAdapter().m7855a(activity, c2465a, map);
        }
    }

    public final Future<Boolean> m8209b(Context context, Offer offer) {
        if (this.f6446a == null) {
            this.f6446a = Fyber.getConfigs().m7596a(getInterstitialMediationAdapter()).m8370a();
        }
        return this.f6446a.m8375a(context, offer);
    }

    public final void m8205a(Activity activity, C2440a c2440a) {
        InterstitialMediationAdapter interstitialMediationAdapter = getInterstitialMediationAdapter();
        if (interstitialMediationAdapter != null) {
            interstitialMediationAdapter.mo3871a(activity, c2440a);
        }
    }

    public final Future<Boolean> m8203a(Context context, Offer offer, C2490b<BannerWrapper> c2490b) {
        if (this.f6447b == null) {
            this.f6447b = Fyber.getConfigs().m7596a(getBannerMediationAdapter()).m8368a((C2490b) c2490b).m8370a();
        }
        return this.f6447b.m8375a(context, offer);
    }

    protected String getUserId() {
        return Fyber.getConfigs().m7608i().m7593b();
    }

    protected boolean isPrecachingDisabled() {
        return CacheManager.m8105a().m8120e();
    }

    public final void m8204a() {
        if (m8207a(AdFormat.REWARDED_VIDEO)) {
            getVideoMediationAdapter().startPrecaching();
        }
    }

    public static <T> T getConfiguration(Map<String, Object> map, String str, Class<T> cls) {
        if (!(map == null || map.isEmpty())) {
            T t = map.get(str);
            if (t != null && t.getClass().isAssignableFrom(cls)) {
                return t;
            }
        }
        return null;
    }

    public static <T> T getConfiguration(Map<String, Object> map, String str, T t, Class<T> cls) {
        T configuration = getConfiguration(map, str, cls);
        return configuration == null ? t : configuration;
    }

    protected void notifyListeners(Object[] objArr, Class[] clsArr) {
        m8201a(objArr, clsArr);
    }

    protected void notifyListeners(Object... objArr) {
        Class[] clsArr;
        int i = 0;
        if (objArr != null) {
            Class[] clsArr2 = new Class[objArr.length];
            while (i < objArr.length) {
                clsArr2[i] = objArr[i].getClass();
                i++;
            }
            clsArr = clsArr2;
        } else {
            clsArr = new Class[0];
        }
        m8201a(objArr, clsArr);
    }

    private void m8201a(final Object[] objArr, final Class[] clsArr) {
        final String methodName = Thread.currentThread().getStackTrace()[4].getMethodName();
        Fyber.getConfigs().m7600a(new Runnable(this) {
            final /* synthetic */ MediationAdapter f6444d;

            public final void run() {
                for (Object next : this.f6444d.getListeners()) {
                    try {
                        next.getClass().getDeclaredMethod(methodName, clsArr).invoke(next, objArr);
                    } catch (SecurityException e) {
                    } catch (NoSuchMethodException e2) {
                    } catch (IllegalArgumentException e3) {
                    } catch (IllegalAccessException e4) {
                    } catch (InvocationTargetException e5) {
                    }
                }
            }
        });
    }

    public final C2610j m8208b(AdFormat adFormat) {
        switch (adFormat) {
            case REWARDED_VIDEO:
                return this.f6448c;
            case INTERSTITIAL:
                return this.f6446a;
            case BANNER:
                return this.f6447b;
            default:
                return null;
        }
    }
}
