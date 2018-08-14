package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.TextureView;

@zzadh
@TargetApi(14)
public abstract class zzapg extends TextureView implements zzaqa {
    protected final zzapp zzcxk = new zzapp();
    protected final zzapz zzcxl;

    public zzapg(Context context) {
        super(context);
        this.zzcxl = new zzapz(context, this);
    }

    public abstract int getCurrentPosition();

    public abstract int getDuration();

    public abstract int getVideoHeight();

    public abstract int getVideoWidth();

    public abstract void pause();

    public abstract void play();

    public abstract void seekTo(int i);

    public abstract void setVideoPath(String str);

    public abstract void stop();

    public abstract void zza(float f, float f2);

    public abstract void zza(zzapf com_google_android_gms_internal_ads_zzapf);

    public abstract String zzsp();

    public abstract void zzst();
}
