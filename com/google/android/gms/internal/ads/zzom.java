package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzom extends RelativeLayout {
    private static final float[] zzbhs = new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f};
    @Nullable
    private AnimationDrawable zzbht;

    public zzom(Context context, zzoj com_google_android_gms_internal_ads_zzoj, LayoutParams layoutParams) {
        super(context);
        Preconditions.checkNotNull(com_google_android_gms_internal_ads_zzoj);
        Drawable shapeDrawable = new ShapeDrawable(new RoundRectShape(zzbhs, null, null));
        shapeDrawable.getPaint().setColor(com_google_android_gms_internal_ads_zzoj.getBackgroundColor());
        setLayoutParams(layoutParams);
        zzbv.zzem().setBackground(this, shapeDrawable);
        ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-2, -2);
        if (!TextUtils.isEmpty(com_google_android_gms_internal_ads_zzoj.getText())) {
            ViewGroup.LayoutParams layoutParams3 = new LayoutParams(-2, -2);
            View textView = new TextView(context);
            textView.setLayoutParams(layoutParams3);
            textView.setId(1195835393);
            textView.setTypeface(Typeface.DEFAULT);
            textView.setText(com_google_android_gms_internal_ads_zzoj.getText());
            textView.setTextColor(com_google_android_gms_internal_ads_zzoj.getTextColor());
            textView.setTextSize((float) com_google_android_gms_internal_ads_zzoj.getTextSize());
            zzkb.zzif();
            int zza = zzamu.zza(context, 4);
            zzkb.zzif();
            textView.setPadding(zza, 0, zzamu.zza(context, 4), 0);
            addView(textView);
            layoutParams2.addRule(1, textView.getId());
        }
        View imageView = new ImageView(context);
        imageView.setLayoutParams(layoutParams2);
        imageView.setId(1195835394);
        List<zzon> zzjs = com_google_android_gms_internal_ads_zzoj.zzjs();
        if (zzjs != null && zzjs.size() > 1) {
            this.zzbht = new AnimationDrawable();
            for (zzon zzjy : zzjs) {
                try {
                    this.zzbht.addFrame((Drawable) ObjectWrapper.unwrap(zzjy.zzjy()), com_google_android_gms_internal_ads_zzoj.zzjt());
                } catch (Throwable e) {
                    zzane.zzb("Error while getting drawable.", e);
                }
            }
            zzbv.zzem().setBackground(imageView, this.zzbht);
        } else if (zzjs.size() == 1) {
            try {
                imageView.setImageDrawable((Drawable) ObjectWrapper.unwrap(((zzon) zzjs.get(0)).zzjy()));
            } catch (Throwable e2) {
                zzane.zzb("Error while getting drawable.", e2);
            }
        }
        addView(imageView);
    }

    public final void onAttachedToWindow() {
        if (this.zzbht != null) {
            this.zzbht.start();
        }
        super.onAttachedToWindow();
    }
}
