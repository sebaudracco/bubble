package com.inmobi.ads;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.media.MediaMetadataRetriever;
import android.support.annotation.NonNull;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.inmobi.commons.core.p115d.C3132b;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.mopub.mobileads.dfp.adapters.MoPubAdapter;

public class NativeStrandVideoWrapper extends RelativeLayout {
    private static final String f6849a = NativeStrandVideoWrapper.class.getSimpleName();
    private NativeStrandVideoView f6850b;
    private ImageView f6851c;
    private ProgressBar f6852d;
    private NativeStrandVideoController f6853e;
    private ba f6854f;

    public NativeStrandVideoWrapper(Context context) {
        super(context);
        m8917b();
    }

    private void m8917b() {
        this.f6850b = new NativeStrandVideoView(getContext());
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        addView(this.f6850b, layoutParams);
        this.f6851c = new ImageView(getContext());
        this.f6851c.setScaleType(ScaleType.FIT_XY);
        this.f6851c.setVisibility(8);
        addView(this.f6851c, layoutParams);
        this.f6852d = new ProgressBar(getContext());
        this.f6852d.setVisibility(8);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        addView(this.f6852d, layoutParams);
        this.f6853e = new NativeStrandVideoController(getContext());
        layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        this.f6850b.setMediaController(this.f6853e);
        addView(this.f6853e, layoutParams);
    }

    public void m8918a() {
        aw awVar = (aw) this.f6850b.getTag();
        if (awVar != null) {
            LayoutParams layoutParams;
            try {
                double d;
                double d2;
                String b = awVar.m9441D().mo6219b();
                MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                mediaMetadataRetriever.setDataSource(b);
                int intValue = Integer.valueOf(mediaMetadataRetriever.extractMetadata(18)).intValue();
                int intValue2 = Integer.valueOf(mediaMetadataRetriever.extractMetadata(19)).intValue();
                mediaMetadataRetriever.release();
                Point a = awVar.m9001b().m8849a();
                if (((double) a.x) / ((double) a.y) > ((double) intValue) / ((double) intValue2)) {
                    d = ((double) intValue) * ((((double) a.y) * MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE) / ((double) intValue2));
                    d2 = (double) a.y;
                } else {
                    d2 = (double) a.x;
                    double d3 = ((((double) a.x) * MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE) / ((double) intValue)) * ((double) intValue2);
                    d = d2;
                    d2 = d3;
                }
                layoutParams = new RelativeLayout.LayoutParams((int) d, (int) d2);
            } catch (Throwable e) {
                Throwable th = e;
                Logger.m10359a(InternalLogLevel.INTERNAL, f6849a, "SDK encountered unexpected error in computing aspect ratio for video");
                layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                C3135c.m10255a().m10279a(new C3132b(th));
            }
            layoutParams.addRule(13);
            this.f6850b.setLayoutParams(layoutParams);
        }
    }

    public void setPosterImage(@NonNull Bitmap bitmap) {
        this.f6851c.setImageBitmap(bitmap);
    }

    @NonNull
    public NativeStrandVideoView getVideoView() {
        return this.f6850b;
    }

    @NonNull
    public ImageView getPoster() {
        return this.f6851c;
    }

    @NonNull
    public ProgressBar getProgressBar() {
        return this.f6852d;
    }

    public NativeStrandVideoController getVideoController() {
        return this.f6853e;
    }

    public void setVideoEventListener(ba baVar) {
        this.f6854f = baVar;
    }
}
