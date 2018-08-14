package com.mopub.nativeads;

import android.graphics.SurfaceTexture;
import android.view.TextureView.SurfaceTextureListener;
import com.mopub.nativeads.MoPubCustomEventVideoNative.MoPubVideoNativeAd;

class MoPubCustomEventVideoNative$MoPubVideoNativeAd$3 implements SurfaceTextureListener {
    final /* synthetic */ MoPubVideoNativeAd this$0;

    MoPubCustomEventVideoNative$MoPubVideoNativeAd$3(MoPubVideoNativeAd this$0) {
        this.this$0 = this$0;
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        MoPubVideoNativeAd.access$600(this.this$0).setListener(this.this$0);
        MoPubVideoNativeAd.access$600(this.this$0).setOnAudioFocusChangeListener(this.this$0);
        MoPubVideoNativeAd.access$600(this.this$0).setProgressListener(this.this$0);
        MoPubVideoNativeAd.access$600(this.this$0).setTextureView(MoPubVideoNativeAd.access$700(this.this$0).getTextureView());
        MoPubVideoNativeAd.access$700(this.this$0).resetProgress();
        long duration = MoPubVideoNativeAd.access$600(this.this$0).getDuration();
        long currentPosition = MoPubVideoNativeAd.access$600(this.this$0).getCurrentPosition();
        if (MoPubVideoNativeAd.access$800(this.this$0) == 4 || (duration > 0 && duration - currentPosition < 750)) {
            MoPubVideoNativeAd.access$902(this.this$0, true);
        }
        if (MoPubVideoNativeAd.access$1000(this.this$0)) {
            MoPubVideoNativeAd.access$1002(this.this$0, false);
            MoPubVideoNativeAd.access$600(this.this$0).prepare(this.this$0);
        }
        MoPubVideoNativeAd.access$1102(this.this$0, true);
        MoPubVideoNativeAd.access$100(this.this$0);
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        MoPubVideoNativeAd.access$1002(this.this$0, true);
        MoPubVideoNativeAd.access$600(this.this$0).release(this.this$0);
        this.this$0.applyState(MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState.PAUSED);
        return true;
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
    }
}
