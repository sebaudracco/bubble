package com.appsgeyser.sdk.ads.fullscreenSdk;

import android.content.Context;
import com.appsgeyser.sdk.ads.fullscreenSdk.fullscreenFacades.FullscreenSdkFacade;
import com.appsgeyser.sdk.ads.fullscreenSdk.fullscreenFacades.FullscreenSdkFacade.FullscreenAdListener;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class FullscreenSdkHelper {
    private ConfigPhp configPhp;
    private Context context;
    private ArrayList<FullscreenSdkFacade> fullscreenFacades;
    private HashMap<FullscreenSdkFacade, Boolean> loadingFacades;

    public FullscreenSdkHelper(ConfigPhp configPhp, Context context) {
        this.configPhp = configPhp;
        this.context = context;
        init();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void init() {
        /*
        r6 = this;
        r2 = 5;
        r1 = new java.util.HashMap;
        r1.<init>(r2);
        r6.loadingFacades = r1;
        r1 = new java.util.ArrayList;
        r1.<init>(r2);
        r6.fullscreenFacades = r1;
        r1 = r6.configPhp;
        r1 = r1.getFullscreenSdk();
        r1 = r1.entrySet();
        r3 = r1.iterator();
    L_0x001d:
        r1 = r3.hasNext();
        if (r1 == 0) goto L_0x00cd;
    L_0x0023:
        r0 = r3.next();
        r0 = (java.util.Map.Entry) r0;
        r1 = r0.getValue();
        r1 = (com.appsgeyser.sdk.configuration.models.AdNetworkSdkModel) r1;
        r1 = r1.isActive();
        if (r1 == 0) goto L_0x001d;
    L_0x0035:
        r1 = r0.getKey();
        r1 = (java.lang.String) r1;
        r2 = -1;
        r4 = r1.hashCode();
        switch(r4) {
            case -1143343796: goto L_0x0078;
            case -963943683: goto L_0x0062;
            case 294202302: goto L_0x0083;
            case 541767659: goto L_0x006d;
            case 1314914054: goto L_0x0057;
            default: goto L_0x0043;
        };
    L_0x0043:
        r1 = r2;
    L_0x0044:
        switch(r1) {
            case 0: goto L_0x0048;
            case 1: goto L_0x008e;
            case 2: goto L_0x009d;
            case 3: goto L_0x00ad;
            case 4: goto L_0x00bd;
            default: goto L_0x0047;
        };
    L_0x0047:
        goto L_0x001d;
    L_0x0048:
        r1 = r6.fullscreenFacades;
        r2 = new com.appsgeyser.sdk.ads.fullscreenSdk.fullscreenFacades.AppnextFullscreenFacade;
        r4 = r6.context;
        r5 = r6.configPhp;
        r2.<init>(r4, r5);
        r1.add(r2);
        goto L_0x001d;
    L_0x0057:
        r4 = "appnextSdk";
        r1 = r1.equals(r4);
        if (r1 == 0) goto L_0x0043;
    L_0x0060:
        r1 = 0;
        goto L_0x0044;
    L_0x0062:
        r4 = "admobSdk";
        r1 = r1.equals(r4);
        if (r1 == 0) goto L_0x0043;
    L_0x006b:
        r1 = 1;
        goto L_0x0044;
    L_0x006d:
        r4 = "mobfoxSdk";
        r1 = r1.equals(r4);
        if (r1 == 0) goto L_0x0043;
    L_0x0076:
        r1 = 2;
        goto L_0x0044;
    L_0x0078:
        r4 = "inmobiSdk";
        r1 = r1.equals(r4);
        if (r1 == 0) goto L_0x0043;
    L_0x0081:
        r1 = 3;
        goto L_0x0044;
    L_0x0083:
        r4 = "fyberSdk";
        r1 = r1.equals(r4);
        if (r1 == 0) goto L_0x0043;
    L_0x008c:
        r1 = 4;
        goto L_0x0044;
    L_0x008e:
        r1 = r6.fullscreenFacades;
        r2 = new com.appsgeyser.sdk.ads.fullscreenSdk.fullscreenFacades.AdmobFullscreenFacade;
        r4 = r6.context;
        r5 = r6.configPhp;
        r2.<init>(r4, r5);
        r1.add(r2);
        goto L_0x001d;
    L_0x009d:
        r1 = r6.fullscreenFacades;
        r2 = new com.appsgeyser.sdk.ads.fullscreenSdk.fullscreenFacades.MobfoxFullscreenFacade;
        r4 = r6.context;
        r5 = r6.configPhp;
        r2.<init>(r4, r5);
        r1.add(r2);
        goto L_0x001d;
    L_0x00ad:
        r1 = r6.fullscreenFacades;
        r2 = new com.appsgeyser.sdk.ads.fullscreenSdk.fullscreenFacades.InmobiFullscreenFacade;
        r4 = r6.context;
        r5 = r6.configPhp;
        r2.<init>(r4, r5);
        r1.add(r2);
        goto L_0x001d;
    L_0x00bd:
        r1 = r6.fullscreenFacades;
        r2 = new com.appsgeyser.sdk.ads.fullscreenSdk.fullscreenFacades.FyberFullscreenFacade;
        r4 = r6.context;
        r5 = r6.configPhp;
        r2.<init>(r4, r5);
        r1.add(r2);
        goto L_0x001d;
    L_0x00cd:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsgeyser.sdk.ads.fullscreenSdk.FullscreenSdkHelper.init():void");
    }

    public void loadInterstitial(final FullscreenAdListener listener) {
        Iterator it = this.fullscreenFacades.iterator();
        while (it.hasNext()) {
            final FullscreenSdkFacade facade = (FullscreenSdkFacade) it.next();
            facade.setListener(new FullscreenAdListener() {
                public void onFullscreenReceived() {
                    FullscreenSdkHelper.this.loadingFacades.put(facade, Boolean.valueOf(false));
                    if (FullscreenSdkHelper.this.allFacadesLoadedAd()) {
                        listener.onFullscreenReceived();
                    }
                }

                public void onFullscreenOpened() {
                    listener.onFullscreenOpened();
                }

                public void onFullscreenClosed() {
                    listener.onFullscreenClosed();
                }

                public void onFullscreenError(String message) {
                    FullscreenSdkHelper.this.loadingFacades.put(facade, Boolean.valueOf(false));
                    if (FullscreenSdkHelper.this.allFacadesLoadedAd()) {
                        listener.onFullscreenReceived();
                    }
                }

                public void onFullscreenClicked() {
                    listener.onFullscreenClicked();
                }
            });
            this.loadingFacades.put(facade, Boolean.valueOf(true));
            facade.loadFullscreenAd();
        }
    }

    private boolean allFacadesLoadedAd() {
        for (Entry<FullscreenSdkFacade, Boolean> entry : this.loadingFacades.entrySet()) {
            if (((Boolean) entry.getValue()).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public void showInterstitial() {
        Iterator it = this.fullscreenFacades.iterator();
        while (it.hasNext()) {
            FullscreenSdkFacade facade = (FullscreenSdkFacade) it.next();
            if (facade.isFullscreenLoaded()) {
                facade.showFullscreenAd();
                return;
            }
        }
    }

    public void onPause() {
        Iterator it = this.fullscreenFacades.iterator();
        while (it.hasNext()) {
            ((FullscreenSdkFacade) it.next()).onPause();
        }
    }

    public void onResume() {
        Iterator it = this.fullscreenFacades.iterator();
        while (it.hasNext()) {
            ((FullscreenSdkFacade) it.next()).onResume();
        }
    }
}
