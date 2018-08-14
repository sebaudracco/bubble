package com.mopub.mobileads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils.TruncateAt;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Utils;
import com.mopub.mobileads.resource.CloseButtonDrawable;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;
import com.mopub.network.Networking;
import com.mopub.volley.toolbox.ImageLoader;

public class VastVideoCloseButtonWidget extends RelativeLayout {
    @NonNull
    private CloseButtonDrawable mCloseButtonDrawable = new CloseButtonDrawable();
    private final int mEdgePadding;
    @NonNull
    private final ImageLoader mImageLoader;
    private final int mImagePadding;
    @NonNull
    private ImageView mImageView;
    private final int mTextRightMargin;
    @NonNull
    private TextView mTextView;
    private final int mWidgetHeight;

    public VastVideoCloseButtonWidget(@NonNull Context context) {
        super(context);
        setId((int) Utils.generateUniqueId());
        this.mEdgePadding = Dips.dipsToIntPixels(6.0f, context);
        this.mImagePadding = Dips.dipsToIntPixels(CtaButton.TEXT_SIZE_SP, context);
        this.mWidgetHeight = Dips.dipsToIntPixels(56.0f, context);
        this.mTextRightMargin = Dips.dipsToIntPixels(0.0f, context);
        this.mImageLoader = Networking.getImageLoader(context);
        createImageView();
        createTextView();
        LayoutParams layoutParams = new LayoutParams(-2, this.mWidgetHeight);
        layoutParams.addRule(11);
        setLayoutParams(layoutParams);
    }

    private void createImageView() {
        this.mImageView = new ImageView(getContext());
        this.mImageView.setId((int) Utils.generateUniqueId());
        LayoutParams iconLayoutParams = new LayoutParams(this.mWidgetHeight, this.mWidgetHeight);
        iconLayoutParams.addRule(11);
        this.mImageView.setImageDrawable(this.mCloseButtonDrawable);
        this.mImageView.setPadding(this.mImagePadding, this.mImagePadding + this.mEdgePadding, this.mImagePadding + this.mEdgePadding, this.mImagePadding);
        addView(this.mImageView, iconLayoutParams);
    }

    private void createTextView() {
        this.mTextView = new TextView(getContext());
        this.mTextView.setSingleLine();
        this.mTextView.setEllipsize(TruncateAt.END);
        this.mTextView.setTextColor(-1);
        this.mTextView.setTextSize(CloseButton.TEXT_SIZE_SP);
        this.mTextView.setTypeface(CloseButton.TEXT_TYPEFACE);
        this.mTextView.setText("");
        LayoutParams textLayoutParams = new LayoutParams(-2, -2);
        textLayoutParams.addRule(15);
        textLayoutParams.addRule(0, this.mImageView.getId());
        this.mTextView.setPadding(0, this.mEdgePadding, 0, 0);
        textLayoutParams.setMargins(0, 0, this.mTextRightMargin, 0);
        addView(this.mTextView, textLayoutParams);
    }

    void updateCloseButtonText(@Nullable String text) {
        if (this.mTextView != null) {
            this.mTextView.setText(text);
        }
    }

    void updateCloseButtonIcon(@NonNull String imageUrl) {
        this.mImageLoader.get(imageUrl, new 1(this, imageUrl));
    }

    void setOnTouchListenerToContent(@Nullable OnTouchListener onTouchListener) {
        this.mImageView.setOnTouchListener(onTouchListener);
        this.mTextView.setOnTouchListener(onTouchListener);
    }

    @Deprecated
    @VisibleForTesting
    ImageView getImageView() {
        return this.mImageView;
    }

    @Deprecated
    @VisibleForTesting
    void setImageView(ImageView imageView) {
        this.mImageView = imageView;
    }

    @Deprecated
    @VisibleForTesting
    TextView getTextView() {
        return this.mTextView;
    }
}
