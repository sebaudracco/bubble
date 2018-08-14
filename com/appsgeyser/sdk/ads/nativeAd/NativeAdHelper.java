package com.appsgeyser.sdk.ads.nativeAd;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import com.appsgeyser.sdk.ads.nativeAd.EndlessRecyclerViewScrollListener.OnScrollListener;
import com.appsgeyser.sdk.ads.nativeAd.nativeFacades.NativeAdFacade;
import com.appsgeyser.sdk.ads.nativeAd.nativeHelpers.NativeSdkHelper;
import com.appsgeyser.sdk.ads.nativeAd.nativeHelpers.NativeSdkHelper.NativeAdsListener;
import com.appsgeyser.sdk.ads.nativeAd.nativeHelpers.NativeSdkHelper.OnAdOpenedListener;
import com.appsgeyser.sdk.configuration.Configuration;
import com.appsgeyser.sdk.configuration.Constants;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.server.StatController;
import com.appsgeyser.sdk.server.implementation.AppsgeyserServerClient;
import com.appsgeyser.sdk.server.implementation.AppsgeyserServerClient.ConfigPhpRequestListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class NativeAdHelper implements OnScrollListener {
    private Context context;
    private HashMap<NativeSdkHelper, Boolean> currentlyLoadingSdks;
    private ArrayList<NativeAdFacade> facadeArrayList;
    private FlexibleLayoutManager flexibleLayoutManager;
    private NativeAdAdapter nativeAdAdapter;
    private ProgressBar nativeAdProgressBar;
    private RecyclerView nativeAdRecyclerView;
    private HashSet<NativeSdkHelper> sdkHelpers;
    private boolean sendImpressionsOnStart;

    class C12591 extends SpanSizeLookup {
        C12591() {
        }

        public int getSpanSize(int position) {
            if (position < 6) {
                return 1;
            }
            return 3;
        }
    }

    class C12655 implements OnAdOpenedListener {
        C12655() {
        }

        public void onAdOpened() {
            if (NativeAdHelper.this.nativeAdAdapter != null) {
                NativeAdHelper.this.nativeAdAdapter.notifyDataSetChanged();
            }
        }

        public void onError(String errorMessage) {
            if (NativeAdHelper.this.nativeAdAdapter != null) {
                NativeAdHelper.this.nativeAdAdapter.notifyDataSetChanged();
            }
        }
    }

    public NativeAdHelper(Context context, RecyclerView nativeAdRecyclerView, ProgressBar nativeAdProgressBar, boolean sendImpressionsOnStart) {
        this.context = context;
        this.nativeAdRecyclerView = nativeAdRecyclerView;
        this.nativeAdProgressBar = nativeAdProgressBar;
        this.sendImpressionsOnStart = sendImpressionsOnStart;
        init();
    }

    private void init() {
        this.facadeArrayList = new ArrayList();
        this.flexibleLayoutManager = new FlexibleLayoutManager(this.context, new C12591());
        this.nativeAdRecyclerView.setLayoutManager(this.flexibleLayoutManager);
        prepareScrollViewToLoadMoreAdsOnDemand();
        final Handler mainLooperHandler = new Handler(this.context.getMainLooper());
        AppsgeyserServerClient.getInstance().getConfigPhp(this.context, null, new ConfigPhpRequestListener() {
            public void receivedConfigPhp(final ConfigPhp configPhp) {
                mainLooperHandler.post(new Runnable() {
                    public void run() {
                        NativeAdHelper.this.initAppropriateSDKHelpers(configPhp);
                        NativeAdHelper.this.requestAds(configPhp);
                    }
                });
            }
        });
    }

    private void prepareScrollViewToLoadMoreAdsOnDemand() {
        this.nativeAdRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(this.flexibleLayoutManager, this) {
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (!NativeAdHelper.this.noMoreAdAvailable() && !NativeAdHelper.this.isAdsLoading()) {
                    NativeAdHelper.this.requestMoreAdForRecyclerList();
                }
            }
        });
    }

    private boolean noMoreAdAvailable() {
        Iterator it = this.sdkHelpers.iterator();
        while (it.hasNext()) {
            if (((NativeSdkHelper) it.next()).isMoreAdsAvailable()) {
                return false;
            }
        }
        return true;
    }

    private boolean isAdsLoading() {
        boolean isLoading = false;
        for (Boolean isSdkLoading : this.currentlyLoadingSdks.values()) {
            if (isSdkLoading.booleanValue()) {
                isLoading = true;
            }
        }
        return isLoading;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initAppropriateSDKHelpers(com.appsgeyser.sdk.configuration.models.ConfigPhp r8) {
        /*
        r7 = this;
        r1 = r8.getAdsNetworkSdk();
        r2 = new java.util.HashSet;
        r3 = r1.size();
        r2.<init>(r3);
        r7.sdkHelpers = r2;
        r2 = new java.util.HashMap;
        r3 = r1.size();
        r2.<init>(r3);
        r7.currentlyLoadingSdks = r2;
        r2 = r1.entrySet();
        r4 = r2.iterator();
    L_0x0022:
        r2 = r4.hasNext();
        if (r2 == 0) goto L_0x00b2;
    L_0x0028:
        r0 = r4.next();
        r0 = (java.util.Map.Entry) r0;
        r2 = r0.getValue();
        r2 = (com.appsgeyser.sdk.configuration.models.AdNetworkSdkModel) r2;
        r2 = r2.isActive();
        if (r2 == 0) goto L_0x0022;
    L_0x003a:
        r2 = r0.getKey();
        r2 = (java.lang.String) r2;
        r3 = -1;
        r5 = r2.hashCode();
        switch(r5) {
            case -1143343796: goto L_0x0081;
            case -963943683: goto L_0x0076;
            case 211607715: goto L_0x006b;
            case 541767659: goto L_0x0060;
            default: goto L_0x0048;
        };
    L_0x0048:
        r2 = r3;
    L_0x0049:
        switch(r2) {
            case 0: goto L_0x004d;
            case 1: goto L_0x008c;
            case 2: goto L_0x0098;
            case 3: goto L_0x00a4;
            default: goto L_0x004c;
        };
    L_0x004c:
        goto L_0x0022;
    L_0x004d:
        r3 = r7.sdkHelpers;
        r5 = new com.appsgeyser.sdk.ads.nativeAd.nativeHelpers.MobfoxSDKHelper;
        r6 = r7.context;
        r2 = r0.getValue();
        r2 = (com.appsgeyser.sdk.configuration.models.AdNetworkSdkModel) r2;
        r5.<init>(r6, r2, r8);
        r3.add(r5);
        goto L_0x0022;
    L_0x0060:
        r5 = "mobfoxSdk";
        r2 = r2.equals(r5);
        if (r2 == 0) goto L_0x0048;
    L_0x0069:
        r2 = 0;
        goto L_0x0049;
    L_0x006b:
        r5 = "appsgeyserSdk";
        r2 = r2.equals(r5);
        if (r2 == 0) goto L_0x0048;
    L_0x0074:
        r2 = 1;
        goto L_0x0049;
    L_0x0076:
        r5 = "admobSdk";
        r2 = r2.equals(r5);
        if (r2 == 0) goto L_0x0048;
    L_0x007f:
        r2 = 2;
        goto L_0x0049;
    L_0x0081:
        r5 = "inmobiSdk";
        r2 = r2.equals(r5);
        if (r2 == 0) goto L_0x0048;
    L_0x008a:
        r2 = 3;
        goto L_0x0049;
    L_0x008c:
        r2 = r7.sdkHelpers;
        r3 = new com.appsgeyser.sdk.ads.nativeAd.nativeHelpers.AppsgeyserSDKHelper;
        r5 = r7.context;
        r3.<init>(r5);
        r2.add(r3);
    L_0x0098:
        r2 = r7.sdkHelpers;
        r3 = new com.appsgeyser.sdk.ads.nativeAd.nativeHelpers.AdmobSDKHelper;
        r5 = r7.context;
        r3.<init>(r5, r8);
        r2.add(r3);
    L_0x00a4:
        r2 = r7.sdkHelpers;
        r3 = new com.appsgeyser.sdk.ads.nativeAd.nativeHelpers.InmobiSDKHelper;
        r5 = r7.context;
        r3.<init>(r5, r8);
        r2.add(r3);
        goto L_0x0022;
    L_0x00b2:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsgeyser.sdk.ads.nativeAd.NativeAdHelper.initAppropriateSDKHelpers(com.appsgeyser.sdk.configuration.models.ConfigPhp):void");
    }

    private void requestMoreAdForRecyclerList() {
        Iterator it = this.sdkHelpers.iterator();
        while (it.hasNext()) {
            final NativeSdkHelper sdkHelper = (NativeSdkHelper) it.next();
            if (sdkHelper.isMoreAdsAvailable()) {
                sdkHelper.setAdsListener(new NativeAdsListener() {
                    public void onAdsLoaded(final ArrayList<NativeAdFacade> ads) {
                        NativeAdHelper.this.currentlyLoadingSdks.put(sdkHelper, Boolean.valueOf(false));
                        if (!NativeAdHelper.this.isAdsLoading()) {
                            new Handler(NativeAdHelper.this.context.getMainLooper()).post(new Runnable() {
                                public void run() {
                                    if (NativeAdHelper.this.nativeAdAdapter.checkIfAdNotShownYet(ads)) {
                                        NativeAdHelper.this.nativeAdAdapter.addMoreItems(ads);
                                    }
                                    if (NativeAdHelper.this.noMoreAdAvailable()) {
                                        NativeAdHelper.this.nativeAdAdapter.removeFooter();
                                        NativeAdHelper.this.nativeAdAdapter.notifyDataSetChanged();
                                    }
                                }
                            });
                        }
                    }

                    public void onError(String errorMessage) {
                        NativeAdHelper.this.currentlyLoadingSdks.put(sdkHelper, Boolean.valueOf(false));
                        if (NativeAdHelper.this.noMoreAdAvailable()) {
                            NativeAdHelper.this.nativeAdAdapter.removeFooter();
                            NativeAdHelper.this.nativeAdAdapter.notifyDataSetChanged();
                        }
                    }
                });
                this.currentlyLoadingSdks.put(sdkHelper, Boolean.valueOf(true));
                sdkHelper.loadAds(16);
            }
        }
    }

    private void requestAds(final ConfigPhp configPhp) {
        if (this.sdkHelpers.size() > 0) {
            Iterator it = this.sdkHelpers.iterator();
            while (it.hasNext()) {
                final NativeSdkHelper sdk = (NativeSdkHelper) it.next();
                sdk.setOnAdOpenedListener(new C12655());
                sdk.setAdsListener(new NativeAdsListener() {
                    public void onAdsLoaded(ArrayList<NativeAdFacade> ads) {
                        NativeAdHelper.this.currentlyLoadingSdks.put(sdk, Boolean.valueOf(false));
                        NativeAdHelper.this.facadeArrayList.addAll(ads);
                        if (!NativeAdHelper.this.isAdsLoading()) {
                            NativeAdHelper.this.prepareRecycler(configPhp);
                            NativeAdHelper.this.currentlyLoadingSdks.put(sdk, Boolean.valueOf(false));
                        }
                    }

                    public void onError(String errorMessage) {
                        NativeAdHelper.this.currentlyLoadingSdks.put(sdk, Boolean.valueOf(false));
                        if (NativeAdHelper.this.noMoreAdAvailable()) {
                            if (NativeAdHelper.this.nativeAdAdapter != null) {
                                NativeAdHelper.this.nativeAdAdapter.removeFooter();
                                NativeAdHelper.this.nativeAdAdapter.notifyDataSetChanged();
                            }
                            NativeAdHelper.this.currentlyLoadingSdks.put(sdk, Boolean.valueOf(false));
                        }
                    }
                });
                sdk.loadAds(16);
                this.currentlyLoadingSdks.put(sdk, Boolean.valueOf(true));
            }
        }
    }

    private void prepareRecycler(ConfigPhp configPhp) {
        this.nativeAdAdapter = new NativeAdAdapter(this.facadeArrayList, configPhp, this.sendImpressionsOnStart);
        this.nativeAdRecyclerView.setAdapter(this.nativeAdAdapter);
        this.nativeAdProgressBar.setVisibility(8);
    }

    public void sendOfferWallImpression() {
        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_OFFERWALL_OPENED, generateQueryParameters(), this.context, false);
    }

    private HashMap<String, String> generateQueryParameters() {
        HashMap<String, String> queryParameters = new HashMap();
        Configuration configuration = Configuration.getInstance(this.context);
        queryParameters.put("wdid", configuration.getApplicationId());
        queryParameters.put("guid", configuration.getAppGuid());
        queryParameters.put("v", Constants.PLATFORM_VERSION);
        return queryParameters;
    }

    public void sendItemsImpressions() {
        this.nativeAdAdapter.sendImpressions(this.flexibleLayoutManager.findLastCompletelyVisibleItemPosition(), this.context);
    }

    public void onResume() {
        if (this.nativeAdAdapter != null) {
            this.nativeAdAdapter.setShouldSendImpressions(true);
            sendItemsImpressions();
        }
    }

    public void onPause() {
        if (this.nativeAdAdapter != null) {
            this.nativeAdAdapter.setShouldSendImpressions(false);
            sendItemsImpressions();
        }
    }

    public void onDestroy() {
        Iterator it = this.sdkHelpers.iterator();
        while (it.hasNext()) {
            ((NativeSdkHelper) it.next()).onDestroy();
        }
    }
}
