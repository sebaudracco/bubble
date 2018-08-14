package com.appsgeyser.sdk.ads.nativeAd;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.appsgeyser.sdk.C1195R;
import com.appsgeyser.sdk.ads.nativeAd.nativeFacades.AdmobSDKFacade;
import com.appsgeyser.sdk.ads.nativeAd.nativeFacades.NativeAdFacade;
import com.appsgeyser.sdk.ads.nativeAd.viewHolders.AbstractNativeAdViewHolder;
import com.appsgeyser.sdk.ads.nativeAd.viewHolders.GridNativeAdViewHolder;
import com.appsgeyser.sdk.ads.nativeAd.viewHolders.GridNativeAdmobViewHolder;
import com.appsgeyser.sdk.ads.nativeAd.viewHolders.ListNativeAdViewHolder;
import com.appsgeyser.sdk.ads.nativeAd.viewHolders.ListNativeAdmobViewHolder;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import java.util.ArrayList;
import java.util.Iterator;

public class NativeAdAdapter extends Adapter<ViewHolder> {
    public static final int FOOTER_ITEM = 3;
    public static final int GRID_ITEM = 1;
    public static final int GRID_ITEM_ADMOB = 4;
    public static final int LIST_ITEM = 2;
    public static final int LIST_ITEM_ADMOB = 5;
    private final ArrayList<NativeAdFacade> ads;
    private final ArrayList<AbstractNativeAdViewHolder> adsViewHolders = new ArrayList();
    private final ConfigPhp configPhp;
    private Context context;
    private int itemsCount;
    private int lastShownImpressionPosition = -1;
    private boolean shouldSendImpressions = false;
    private boolean shouldShowFooter = true;

    public NativeAdAdapter(ArrayList<NativeAdFacade> ads, ConfigPhp configPhp, boolean shouldSendImpressions) {
        this.ads = ads;
        this.itemsCount = ads.size() + 1;
        this.configPhp = configPhp;
        this.shouldSendImpressions = shouldSendImpressions;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        if (viewType == 3 && this.shouldShowFooter) {
            view = inflater.inflate(C1195R.layout.appsgeysersdk_footer_for_native_ads_list, parent, false);
            this.context = view.getContext();
            return new FooterViewHolder(view);
        } else if (viewType == 2) {
            view = inflater.inflate(C1195R.layout.appsgeysersdk_native_ad_list_item, parent, false);
            nativeAdViewHolder = new ListNativeAdViewHolder(view);
            this.adsViewHolders.add(nativeAdViewHolder);
            this.context = view.getContext();
            return nativeAdViewHolder;
        } else if (viewType == 1) {
            view = inflater.inflate(C1195R.layout.appsgeysersdk_native_ad_grid_item, parent, false);
            gridNativeAdViewHolder = new GridNativeAdViewHolder(view);
            this.adsViewHolders.add(gridNativeAdViewHolder);
            this.context = view.getContext();
            return gridNativeAdViewHolder;
        } else if (viewType == 5) {
            view = inflater.inflate(C1195R.layout.appsgeysersdk_native_admob_list_item, parent, false);
            nativeAdViewHolder = new ListNativeAdmobViewHolder(view);
            this.adsViewHolders.add(nativeAdViewHolder);
            this.context = view.getContext();
            return nativeAdViewHolder;
        } else {
            view = inflater.inflate(C1195R.layout.appsgeysersdk_native_admob_grid_item, parent, false);
            gridNativeAdViewHolder = new GridNativeAdmobViewHolder(view);
            this.adsViewHolders.add(gridNativeAdViewHolder);
            this.context = view.getContext();
            return gridNativeAdViewHolder;
        }
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder instanceof AbstractNativeAdViewHolder) {
            ((AbstractNativeAdViewHolder) holder).bindAd((NativeAdFacade) this.ads.get(position), this.configPhp, this.context);
        }
    }

    public int getItemViewType(int position) {
        if (this.shouldShowFooter && position == this.ads.size()) {
            return 3;
        }
        if (this.ads.get(position) instanceof AdmobSDKFacade) {
            return getViewTypeForAdmob(position);
        }
        return getViewTypeForOtherSdk(position);
    }

    private int getViewTypeForAdmob(int position) {
        if (position < 6) {
            return 1;
        }
        return 2;
    }

    private int getViewTypeForOtherSdk(int position) {
        if (position < 6) {
            return 1;
        }
        return 2;
    }

    public int getItemCount() {
        return this.itemsCount;
    }

    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
        if (holder instanceof AbstractNativeAdViewHolder) {
            ((AbstractNativeAdViewHolder) holder).recycleView();
        }
    }

    public void setShouldSendImpressions(boolean shouldSendImpressions) {
        this.shouldSendImpressions = shouldSendImpressions;
    }

    public synchronized void addMoreItems(ArrayList<NativeAdFacade> arrayList) {
        ArrayList<NativeAdFacade> uniqueAds = createListOfUniqueAds(arrayList);
        if (uniqueAds.size() > 0) {
            int oldAdsCount = this.ads.size();
            this.ads.addAll(uniqueAds);
            notifyItemRangeInserted(oldAdsCount, arrayList.size());
            this.itemsCount += uniqueAds.size();
        }
    }

    public boolean checkIfAdNotShownYet(ArrayList<NativeAdFacade> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (!isInExistingArray((NativeAdFacade) it.next())) {
                return true;
            }
        }
        removeFooter();
        return false;
    }

    public void removeFooter() {
        this.shouldShowFooter = false;
        this.itemsCount--;
        notifyDataSetChanged();
    }

    private ArrayList<NativeAdFacade> createListOfUniqueAds(ArrayList<NativeAdFacade> arrayList) {
        ArrayList<NativeAdFacade> uniqueAds = new ArrayList(arrayList.size());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            NativeAdFacade receivedAd = (NativeAdFacade) it.next();
            if (!isInExistingArray(receivedAd)) {
                uniqueAds.add(receivedAd);
            }
        }
        return uniqueAds;
    }

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

    public void sendImpressions(int lastPosition, @NonNull Context context) {
        if (this.shouldSendImpressions && this.lastShownImpressionPosition < lastPosition) {
            for (int i = this.lastShownImpressionPosition + 1; i <= lastPosition; i++) {
                if (i < this.adsViewHolders.size()) {
                    ((AbstractNativeAdViewHolder) this.adsViewHolders.get(i)).sendImpression(this.configPhp, this.shouldSendImpressions, context);
                }
            }
            this.lastShownImpressionPosition = lastPosition;
        }
    }
}
