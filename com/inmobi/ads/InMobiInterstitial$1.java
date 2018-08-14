package com.inmobi.ads;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.inmobi.ads.AdUnit.C2908e;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

class InMobiInterstitial$1 implements C2908e {
    InMobiInterstitial$1() {
    }

    public void mo6116a(@NonNull AdUnit adUnit) {
        if (adUnit != null) {
            try {
                ArrayList arrayList = (ArrayList) InMobiInterstitial.access$000().get(adUnit);
                if (arrayList != null) {
                    InMobiInterstitial.access$000().remove(adUnit);
                    Handler handler = new Handler(Looper.getMainLooper());
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        WeakReference weakReference = (WeakReference) it.next();
                        if (weakReference != null) {
                            final InMobiInterstitial$InterstitialAdRequestListener inMobiInterstitial$InterstitialAdRequestListener = (InMobiInterstitial$InterstitialAdRequestListener) weakReference.get();
                            if (inMobiInterstitial$InterstitialAdRequestListener != null) {
                                final InMobiInterstitial inMobiInterstitial = new InMobiInterstitial(adUnit.m8711a(), adUnit.m8736b(), null);
                                inMobiInterstitial.setKeywords(adUnit.m8784y());
                                inMobiInterstitial.setExtras(adUnit.m8785z());
                                handler.post(new Runnable(this) {
                                    final /* synthetic */ InMobiInterstitial$1 f6752c;

                                    public void run() {
                                        try {
                                            inMobiInterstitial$InterstitialAdRequestListener.onAdRequestCompleted(new InMobiAdRequestStatus(StatusCode.NO_ERROR), inMobiInterstitial);
                                        } catch (Exception e) {
                                            Logger.m10359a(InternalLogLevel.ERROR, InMobiInterstitial.access$200(), "Publisher handler caused unexpected error");
                                        }
                                    }
                                });
                            }
                        }
                    }
                }
            } catch (Exception e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, InMobiInterstitial.access$200(), "SDK encountered unexpected error in onAdPrefetchSucceeded " + e.getMessage());
            }
        }
    }

    public void mo6117a(@NonNull AdUnit adUnit, @NonNull final InMobiAdRequestStatus inMobiAdRequestStatus) {
        if (adUnit != null) {
            try {
                ArrayList arrayList = (ArrayList) InMobiInterstitial.access$000().get(adUnit);
                if (arrayList != null && arrayList.size() > 0) {
                    WeakReference weakReference = (WeakReference) arrayList.get(arrayList.size() - 1);
                    if (weakReference != null) {
                        arrayList.remove(weakReference);
                        if (arrayList.size() == 0) {
                            InMobiInterstitial.access$000().remove(adUnit);
                        }
                        final InMobiInterstitial$InterstitialAdRequestListener inMobiInterstitial$InterstitialAdRequestListener = (InMobiInterstitial$InterstitialAdRequestListener) weakReference.get();
                        if (inMobiInterstitial$InterstitialAdRequestListener != null) {
                            new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                                final /* synthetic */ InMobiInterstitial$1 f6755c;

                                public void run() {
                                    try {
                                        inMobiInterstitial$InterstitialAdRequestListener.onAdRequestCompleted(inMobiAdRequestStatus, null);
                                    } catch (Exception e) {
                                        Logger.m10359a(InternalLogLevel.ERROR, InMobiInterstitial.access$200(), "Publisher handler caused unexpected error");
                                    }
                                }
                            });
                        }
                    }
                }
            } catch (Exception e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, InMobiInterstitial.access$200(), "SDK encountered unexpected error in onAdPrefetchFailed " + e.getMessage());
            }
        }
    }
}
