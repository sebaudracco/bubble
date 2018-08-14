package com.mopub.mobileads;

import android.graphics.Bitmap;
import com.mopub.common.logging.MoPubLog;
import com.mopub.volley.VolleyError;
import com.mopub.volley.toolbox.ImageLoader.ImageContainer;
import com.mopub.volley.toolbox.ImageLoader.ImageListener;

class VastVideoCloseButtonWidget$1 implements ImageListener {
    final /* synthetic */ VastVideoCloseButtonWidget this$0;
    final /* synthetic */ String val$imageUrl;

    VastVideoCloseButtonWidget$1(VastVideoCloseButtonWidget this$0, String str) {
        this.this$0 = this$0;
        this.val$imageUrl = str;
    }

    public void onResponse(ImageContainer imageContainer, boolean isImmediate) {
        Bitmap bitmap = imageContainer.getBitmap();
        if (bitmap != null) {
            VastVideoCloseButtonWidget.access$000(this.this$0).setImageBitmap(bitmap);
            return;
        }
        MoPubLog.m12061d(String.format("%s returned null bitmap", new Object[]{this.val$imageUrl}));
    }

    public void onErrorResponse(VolleyError volleyError) {
        MoPubLog.m12062d("Failed to load image.", volleyError);
    }
}
