package com.fyber.p094b;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Pair;
import com.fyber.Fyber;
import com.fyber.ads.Ad;
import com.fyber.ads.AdFormat;
import com.fyber.ads.C2410a;
import com.fyber.ads.internal.C2423a;
import com.fyber.ads.internal.C2424c;
import com.fyber.ads.internal.Offer;
import com.fyber.ads.interstitials.mediation.InterstitialMediationAdapter;
import com.fyber.exceptions.C2565a;
import com.fyber.mediation.C2573a;
import com.fyber.p094b.C2484h.C2482a;
import com.fyber.requesters.p097a.C2623c;
import com.fyber.requesters.p097a.p098a.C2610j;
import com.fyber.utils.FyberLogger;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: Validator */
public abstract class C2492m<A extends Ad<A, ?>, P extends C2410a<A>> implements Callable<P> {
    protected WeakReference<Context> f6223a;
    protected BlockingQueue<P> f6224b = new LinkedBlockingQueue();

    protected abstract int mo3920a();

    protected abstract C2482a<? extends C2479c, ? extends C2482a<?, ?>> mo3921a(@NonNull C2423a c2423a);

    @Nullable
    protected abstract Future<Boolean> mo3922a(C2623c c2623c, Offer offer);

    protected abstract void mo3923a(P p, Offer offer);

    protected abstract String mo3924b();

    @NonNull
    protected abstract AdFormat mo3925c();

    public /* synthetic */ Object call() throws Exception {
        return m7929d();
    }

    protected C2492m(WeakReference<Context> weakReference) {
        this.f6223a = weakReference;
    }

    @NonNull
    public final C2505e<P> m7931a(@NonNull P p) {
        Runnable c2505e = new C2505e(this);
        try {
            this.f6224b.put(p);
            Fyber.getConfigs().m7600a(c2505e);
        } catch (Exception e) {
            FyberLogger.m8450e(mo3924b(), "Error adding the validation thread into the validator queue", e);
            e.printStackTrace();
        }
        return c2505e;
    }

    private P m7929d() throws Exception {
        Offer offer;
        C2423a c2423a;
        String str;
        List list = null;
        C2410a c2410a = (C2410a) this.f6224b.take();
        List b = c2410a.m7619b();
        if (!(b == null || b.isEmpty())) {
            List arrayList = new ArrayList(b.size());
            int size = b.size();
            for (int i = 0; i < size; i++) {
                offer = (Offer) b.get(i);
                String providerType = offer.getProviderType();
                FyberLogger.m8448d(mo3924b(), "Processing ad from " + providerType);
                if (C2573a.f6454a.m8220a(providerType, mo3925c())) {
                    int a;
                    boolean booleanValue;
                    FyberLogger.m8448d(mo3924b(), providerType + " is available, proceeding...");
                    m7927a(offer, C2423a.ValidationRequest, null);
                    Future a2 = mo3922a(c2410a.m7623f(), offer);
                    Integer num = (Integer) offer.getProviderRequest().m8231a("timeout", Integer.class);
                    if (num == null || num.intValue() > 180 || num.intValue() <= 0) {
                        a = mo3920a();
                    } else {
                        try {
                            a = num.intValue();
                        } catch (InterruptedException e) {
                            list = arrayList;
                        }
                    }
                    if (a2 != null) {
                        try {
                            booleanValue = ((Boolean) a2.get((long) a, TimeUnit.SECONDS)).booleanValue();
                        } catch (TimeoutException e2) {
                            arrayList.add(i, offer);
                            m7928a(offer, C2423a.ValidationTimeout, "network", Collections.singletonMap("timeout_value", String.valueOf(a)));
                        } catch (ExecutionException e3) {
                            Throwable cause = e3.getCause();
                            String str2 = "";
                            providerType = "";
                            C2423a c2423a2 = C2423a.ValidationError;
                            if (cause != null) {
                                str2 = cause.getMessage();
                                if (cause instanceof C2565a) {
                                    providerType = ((C2565a) cause).m8200a();
                                    if (InterstitialMediationAdapter.ERROR_NO_PLACEMENT_ID.equals(providerType)) {
                                        c2423a = C2423a.NotIntegrated;
                                        str = providerType;
                                        providerType = str2;
                                        FyberLogger.m8448d(mo3924b(), "Error requesting ads - " + providerType);
                                        m7927a(offer, c2423a, str);
                                    }
                                }
                            }
                            c2423a = c2423a2;
                            str = providerType;
                            providerType = str2;
                            FyberLogger.m8448d(mo3924b(), "Error requesting ads - " + providerType);
                            m7927a(offer, c2423a, str);
                        }
                    } else {
                        booleanValue = false;
                    }
                    if (booleanValue) {
                        FyberLogger.m8448d(mo3924b(), "Ad is available from " + providerType);
                        m7927a(offer, C2423a.ValidationFill, null);
                        m7926a(c2410a, offer, i);
                        break;
                    }
                    FyberLogger.m8448d(mo3924b(), "No ad available from " + providerType);
                    m7927a(offer, C2423a.ValidationNoFill, null);
                } else {
                    FyberLogger.m8448d(mo3924b(), providerType + " is not integrated");
                    m7927a(offer, C2423a.NotIntegrated, null);
                }
            }
            list = arrayList;
        }
        Pair a3 = m7925a(c2410a.m7623f(), list);
        if (a3 != null) {
            offer = (Offer) a3.first;
            FyberLogger.m8448d(mo3924b(), "Ad is available from " + offer.getProviderType());
            m7927a(offer, C2423a.ValidationFill, "retry");
            m7926a(c2410a, offer, ((Integer) a3.second).intValue());
        } else {
            FyberLogger.m8448d(mo3924b(), "There are no ads available currently.");
        }
        return c2410a;
    }

    @Nullable
    private Pair<Offer, Integer> m7925a(C2623c c2623c, @Nullable List<Offer> list) {
        if (!(list == null || list.isEmpty())) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Offer offer = (Offer) list.get(i);
                if (offer != null) {
                    try {
                        boolean booleanValue;
                        Future a = mo3922a(c2623c, offer);
                        if (a != null) {
                            booleanValue = ((Boolean) a.get(10, TimeUnit.MILLISECONDS)).booleanValue();
                        } else {
                            booleanValue = false;
                        }
                        if (booleanValue) {
                            return new Pair(offer, Integer.valueOf(i));
                        }
                        m7927a(offer, C2423a.ValidationNoFill, "retry");
                    } catch (TimeoutException e) {
                    } catch (ExecutionException e2) {
                    } catch (InterruptedException e3) {
                    }
                }
            }
        }
        return null;
    }

    private void m7927a(Offer offer, @NonNull C2423a c2423a, String str) {
        m7928a(offer, c2423a, str, null);
    }

    private void m7928a(Offer offer, @NonNull C2423a c2423a, String str, Map<String, String> map) {
        if (c2423a == C2423a.ValidationFill) {
            C2610j b = C2573a.f6454a.m8221b(offer.getProviderType(), mo3925c());
            if (b != null) {
                if (map == null) {
                    map = new HashMap();
                }
                map.putAll(C2424c.m7673a(0, b.m8376b("")));
            }
        }
        ((C2482a) ((C2482a) mo3921a(c2423a).m7861a((Map) map)).m7860a(str)).m7890a(offer).mo3907b();
    }

    private void m7926a(P p, Offer offer, int i) {
        p.m7617b(i);
        mo3923a((C2410a) p, offer);
    }
}
