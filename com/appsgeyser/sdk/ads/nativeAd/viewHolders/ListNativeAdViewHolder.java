package com.appsgeyser.sdk.ads.nativeAd.viewHolders;

import android.view.View;
import android.widget.TextView;
import com.appsgeyser.sdk.C1195R;

public class ListNativeAdViewHolder extends AbstractNativeAdViewHolder {
    private TextView adsDescription;

    public ListNativeAdViewHolder(View itemView) {
        super(itemView);
    }

    protected void findSpecificViews() {
        this.adsDescription = (TextView) this.itemView.findViewById(C1195R.id.appsgeysersdk_adsDescription);
    }

    protected void bindSpecificFields() {
        this.adsDescription.setText(this.nativeAd.getAdDescription());
    }
}
