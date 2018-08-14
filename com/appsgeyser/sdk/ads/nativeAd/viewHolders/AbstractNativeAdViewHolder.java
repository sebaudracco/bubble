package com.appsgeyser.sdk.ads.nativeAd.viewHolders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.appsgeyser.sdk.C1195R;
import com.appsgeyser.sdk.ads.nativeAd.nativeFacades.NativeAdFacade;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.squareup.picasso.Picasso;

public abstract class AbstractNativeAdViewHolder extends ViewHolder {
    private Button adsActionButton;
    private ImageView adsIcon;
    private TextView adsTitle;
    protected NativeAdFacade nativeAd;
    private ProgressBar progressBar;

    protected abstract void bindSpecificFields();

    protected abstract void findSpecificViews();

    public AbstractNativeAdViewHolder(View itemView) {
        super(itemView);
        findAllViews(itemView);
    }

    private void findAllViews(View itemView) {
        this.adsIcon = (ImageView) itemView.findViewById(C1195R.id.appsgeysersdk_adsIcon);
        this.adsTitle = (TextView) itemView.findViewById(C1195R.id.appsgeysersdk_adsTitle);
        this.adsActionButton = (Button) itemView.findViewById(C1195R.id.appsgeysersdk_adsActionButton);
        this.progressBar = (ProgressBar) itemView.findViewById(C1195R.id.appsgeysersdk_itemProgressBar);
        findSpecificViews();
    }

    public void bindAd(final NativeAdFacade ad, final ConfigPhp configPhp, @NonNull final Context context) {
        this.nativeAd = ad;
        this.adsActionButton.setText(ad.getButtonText());
        this.adsActionButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                AbstractNativeAdViewHolder.this.progressBar.setVisibility(0);
                ad.adClicked(configPhp, context);
                AbstractNativeAdViewHolder.this.adsActionButton.setVisibility(4);
            }
        });
        this.adsTitle.setText(ad.getAdTitle());
        Picasso.with(this.itemView.getContext()).load(ad.getImageUrl()).into(this.adsIcon);
        bindSpecificFields();
        this.progressBar.setVisibility(8);
        this.adsActionButton.setVisibility(0);
        ad.registerViewForAd(this.itemView);
    }

    public void sendImpression(ConfigPhp configPhp, boolean shouldSendImpressions, @NonNull Context context) {
        if (this.nativeAd != null && shouldSendImpressions) {
            this.nativeAd.adImpression(configPhp, context);
        }
        this.progressBar.setVisibility(8);
        this.adsActionButton.setVisibility(0);
    }

    public void recycleView() {
        this.nativeAd.viewRecycled(this.itemView);
    }
}
