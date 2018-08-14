package com.appsgeyser.sdk.ads.nativeAd.nativeHelpers;

import com.appsgeyser.sdk.ads.nativeAd.nativeFacades.NativeAdFacade;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class AbstractSDKHelper implements NativeSdkHelper {
    protected ArrayList<NativeAdFacade> ads = new ArrayList();
    boolean isMoreAdsAvailable = true;

    private boolean isInExistingArray(NativeAdFacade receivedAd) {
        boolean isInArray = false;
        Iterator it = this.ads.iterator();
        while (it.hasNext()) {
            if (receivedAd.getUniqueAdString().equals(((NativeAdFacade) it.next()).getUniqueAdString())) {
                isInArray = true;
            }
        }
        return isInArray;
    }

    ArrayList<NativeAdFacade> createListOfUniqueAdsAndAddToExisting(ArrayList<NativeAdFacade> arrayList) {
        ArrayList<NativeAdFacade> uniqueAds = new ArrayList(arrayList.size());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            NativeAdFacade receivedAd = (NativeAdFacade) it.next();
            if (!isInExistingArray(receivedAd)) {
                uniqueAds.add(receivedAd);
            }
        }
        if (uniqueAds.size() == 0) {
            this.isMoreAdsAvailable = false;
        } else {
            this.ads.addAll(uniqueAds);
        }
        return uniqueAds;
    }

    public boolean isMoreAdsAvailable() {
        return this.isMoreAdsAvailable;
    }
}
