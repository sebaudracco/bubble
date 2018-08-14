package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageButton;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzamu;
import com.google.android.gms.internal.ads.zzkb;
import javax.annotation.Nullable;

@zzadh
public final class zzo extends FrameLayout implements OnClickListener {
    private final ImageButton zzbyy;
    private final zzw zzbyz;

    public zzo(Context context, zzp com_google_android_gms_ads_internal_overlay_zzp, @Nullable zzw com_google_android_gms_ads_internal_overlay_zzw) {
        super(context);
        this.zzbyz = com_google_android_gms_ads_internal_overlay_zzw;
        setOnClickListener(this);
        this.zzbyy = new ImageButton(context);
        this.zzbyy.setImageResource(17301527);
        this.zzbyy.setBackgroundColor(0);
        this.zzbyy.setOnClickListener(this);
        ImageButton imageButton = this.zzbyy;
        zzkb.zzif();
        int zza = zzamu.zza(context, com_google_android_gms_ads_internal_overlay_zzp.paddingLeft);
        zzkb.zzif();
        int zza2 = zzamu.zza(context, 0);
        zzkb.zzif();
        int zza3 = zzamu.zza(context, com_google_android_gms_ads_internal_overlay_zzp.paddingRight);
        zzkb.zzif();
        imageButton.setPadding(zza, zza2, zza3, zzamu.zza(context, com_google_android_gms_ads_internal_overlay_zzp.paddingBottom));
        this.zzbyy.setContentDescription("Interstitial close button");
        zzkb.zzif();
        zzamu.zza(context, com_google_android_gms_ads_internal_overlay_zzp.size);
        View view = this.zzbyy;
        zzkb.zzif();
        zza2 = zzamu.zza(context, (com_google_android_gms_ads_internal_overlay_zzp.size + com_google_android_gms_ads_internal_overlay_zzp.paddingLeft) + com_google_android_gms_ads_internal_overlay_zzp.paddingRight);
        zzkb.zzif();
        addView(view, new LayoutParams(zza2, zzamu.zza(context, com_google_android_gms_ads_internal_overlay_zzp.size + com_google_android_gms_ads_internal_overlay_zzp.paddingBottom), 17));
    }

    public final void onClick(View view) {
        if (this.zzbyz != null) {
            this.zzbyz.zzni();
        }
    }

    public final void zzu(boolean z) {
        if (z) {
            this.zzbyy.setVisibility(8);
        } else {
            this.zzbyy.setVisibility(0);
        }
    }
}
