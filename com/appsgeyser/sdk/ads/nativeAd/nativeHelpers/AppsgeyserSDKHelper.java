package com.appsgeyser.sdk.ads.nativeAd.nativeHelpers;

import android.content.Context;
import com.appsgeyser.sdk.ads.nativeAd.AppsgeyserNativeAdModel;
import com.appsgeyser.sdk.ads.nativeAd.nativeFacades.AppsgeyserSDKFacade;
import com.appsgeyser.sdk.ads.nativeAd.nativeFacades.NativeAdFacade;
import com.appsgeyser.sdk.ads.nativeAd.nativeHelpers.NativeSdkHelper.NativeAdsListener;
import com.appsgeyser.sdk.ads.nativeAd.nativeHelpers.NativeSdkHelper.OnAdOpenedListener;
import com.appsgeyser.sdk.server.implementation.AppsgeyserServerClient;
import com.appsgeyser.sdk.server.implementation.AppsgeyserServerClient.AppsgeyserNativeAdsListener;
import java.util.ArrayList;
import java.util.Iterator;

public class AppsgeyserSDKHelper extends AbstractSDKHelper {
    private Context context;
    private NativeAdsListener nativeAdsListener;
    private OnAdOpenedListener onAdOpenedListener;

    class C12701 implements AppsgeyserNativeAdsListener {
        C12701() {
        }

        public void nativeAdsReceived(ArrayList<AppsgeyserNativeAdModel> nativeAdModels) {
            ArrayList<NativeAdFacade> facades = new ArrayList(nativeAdModels.size());
            Iterator it = nativeAdModels.iterator();
            while (it.hasNext()) {
                facades.add(new AppsgeyserSDKFacade(AppsgeyserSDKHelper.this.context, (AppsgeyserNativeAdModel) it.next(), AppsgeyserSDKHelper.this.onAdOpenedListener));
            }
            AppsgeyserSDKHelper.this.createListOfUniqueAdsAndAddToExisting(facades);
            if (AppsgeyserSDKHelper.this.nativeAdsListener != null) {
                AppsgeyserSDKHelper.this.nativeAdsListener.onAdsLoaded(AppsgeyserSDKHelper.this.ads);
            }
        }

        public void onError(String errorMessage) {
            if (AppsgeyserSDKHelper.this.nativeAdsListener != null) {
                AppsgeyserSDKHelper.this.nativeAdsListener.onError(errorMessage);
            }
        }
    }

    public AppsgeyserSDKHelper(Context context) {
        this.context = context;
    }

    public void onDestroy() {
    }

    public void init() {
    }

    public void loadAds(int adsCount) {
        AppsgeyserServerClient.getInstance().requestNativeAds(this.context, adsCount, new C12701());
    }

    public void setAdsListener(NativeAdsListener listener) {
        this.nativeAdsListener = listener;
    }

    public void setOnAdOpenedListener(OnAdOpenedListener onAdOpenedListener) {
        this.onAdOpenedListener = onAdOpenedListener;
    }
}
