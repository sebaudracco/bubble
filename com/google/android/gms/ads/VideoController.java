package com.google.android.gms.ads;

import android.support.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzlo;
import com.google.android.gms.internal.ads.zzmt;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public final class VideoController {
    @KeepForSdk
    public static final int PLAYBACK_STATE_ENDED = 3;
    @KeepForSdk
    public static final int PLAYBACK_STATE_PAUSED = 2;
    @KeepForSdk
    public static final int PLAYBACK_STATE_PLAYING = 1;
    @KeepForSdk
    public static final int PLAYBACK_STATE_READY = 5;
    @KeepForSdk
    public static final int PLAYBACK_STATE_UNKNOWN = 0;
    private final Object mLock = new Object();
    @Nullable
    @GuardedBy("mLock")
    private zzlo zzux;
    @Nullable
    @GuardedBy("mLock")
    private VideoLifecycleCallbacks zzuy;

    public final float getAspectRatio() {
        float f = 0.0f;
        synchronized (this.mLock) {
            if (this.zzux == null) {
            } else {
                try {
                    f = this.zzux.getAspectRatio();
                } catch (Throwable e) {
                    zzane.zzb("Unable to call getAspectRatio on video controller.", e);
                }
            }
        }
        return f;
    }

    @KeepForSdk
    public final int getPlaybackState() {
        int i = 0;
        synchronized (this.mLock) {
            if (this.zzux == null) {
            } else {
                try {
                    i = this.zzux.getPlaybackState();
                } catch (Throwable e) {
                    zzane.zzb("Unable to call getPlaybackState on video controller.", e);
                }
            }
        }
        return i;
    }

    @Nullable
    public final VideoLifecycleCallbacks getVideoLifecycleCallbacks() {
        VideoLifecycleCallbacks videoLifecycleCallbacks;
        synchronized (this.mLock) {
            videoLifecycleCallbacks = this.zzuy;
        }
        return videoLifecycleCallbacks;
    }

    public final boolean hasVideoContent() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzux != null;
        }
        return z;
    }

    public final boolean isClickToExpandEnabled() {
        boolean z = false;
        synchronized (this.mLock) {
            if (this.zzux == null) {
            } else {
                try {
                    z = this.zzux.isClickToExpandEnabled();
                } catch (Throwable e) {
                    zzane.zzb("Unable to call isClickToExpandEnabled.", e);
                }
            }
        }
        return z;
    }

    public final boolean isCustomControlsEnabled() {
        boolean z = false;
        synchronized (this.mLock) {
            if (this.zzux == null) {
            } else {
                try {
                    z = this.zzux.isCustomControlsEnabled();
                } catch (Throwable e) {
                    zzane.zzb("Unable to call isUsingCustomPlayerControls.", e);
                }
            }
        }
        return z;
    }

    public final boolean isMuted() {
        boolean z = true;
        synchronized (this.mLock) {
            if (this.zzux == null) {
            } else {
                try {
                    z = this.zzux.isMuted();
                } catch (Throwable e) {
                    zzane.zzb("Unable to call isMuted on video controller.", e);
                }
            }
        }
        return z;
    }

    public final void mute(boolean z) {
        synchronized (this.mLock) {
            if (this.zzux == null) {
                return;
            }
            try {
                this.zzux.mute(z);
            } catch (Throwable e) {
                zzane.zzb("Unable to call mute on video controller.", e);
            }
        }
    }

    public final void pause() {
        synchronized (this.mLock) {
            if (this.zzux == null) {
                return;
            }
            try {
                this.zzux.pause();
            } catch (Throwable e) {
                zzane.zzb("Unable to call pause on video controller.", e);
            }
        }
    }

    public final void play() {
        synchronized (this.mLock) {
            if (this.zzux == null) {
                return;
            }
            try {
                this.zzux.play();
            } catch (Throwable e) {
                zzane.zzb("Unable to call play on video controller.", e);
            }
        }
    }

    public final void setVideoLifecycleCallbacks(VideoLifecycleCallbacks videoLifecycleCallbacks) {
        Preconditions.checkNotNull(videoLifecycleCallbacks, "VideoLifecycleCallbacks may not be null.");
        synchronized (this.mLock) {
            this.zzuy = videoLifecycleCallbacks;
            if (this.zzux == null) {
                return;
            }
            try {
                this.zzux.zza(new zzmt(videoLifecycleCallbacks));
            } catch (Throwable e) {
                zzane.zzb("Unable to call setVideoLifecycleCallbacks on video controller.", e);
            }
        }
    }

    public final void zza(zzlo com_google_android_gms_internal_ads_zzlo) {
        synchronized (this.mLock) {
            this.zzux = com_google_android_gms_internal_ads_zzlo;
            if (this.zzuy != null) {
                setVideoLifecycleCallbacks(this.zzuy);
            }
        }
    }

    public final zzlo zzbc() {
        zzlo com_google_android_gms_internal_ads_zzlo;
        synchronized (this.mLock) {
            com_google_android_gms_internal_ads_zzlo = this.zzux;
        }
        return com_google_android_gms_internal_ads_zzlo;
    }
}
