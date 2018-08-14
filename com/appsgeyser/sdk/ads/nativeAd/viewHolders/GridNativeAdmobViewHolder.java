package com.appsgeyser.sdk.ads.nativeAd.viewHolders;

import android.util.Log;
import android.view.View;
import com.appsgeyser.sdk.C1195R;
import com.google.android.gms.ads.formats.NativeAppInstallAdView;

public class GridNativeAdmobViewHolder extends GridNativeAdViewHolder {
    public GridNativeAdmobViewHolder(View itemView) {
        super(itemView);
    }

    protected void bindSpecificFields() {
        super.bindSpecificFields();
        try {
            ((NativeAppInstallAdView) this.itemView.findViewById(C1195R.id.appsgeysersdk_admobRootView)).setNativeAd(this.nativeAd.getNativeAd());
        } catch (ClassCastException e) {
            Log.e("GridNativeAdmobViewHold", "Can not cast nativeAdFacade to AdmobSDKFacade");
        }
    }
}
