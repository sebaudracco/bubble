package com.mopub.nativeads;

import android.view.View;
import android.view.View.OnClickListener;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler.Builder;

class NativeVideoViewController$4 implements OnClickListener {
    final /* synthetic */ NativeVideoViewController this$0;

    NativeVideoViewController$4(NativeVideoViewController this$0) {
        this.this$0 = this$0;
    }

    public void onClick(View v) {
        NativeVideoViewController.access$200(this.this$0).setPlayWhenReady(false);
        NativeVideoViewController.access$402(this.this$0, NativeVideoViewController.access$100(this.this$0).getTextureView().getBitmap());
        new Builder().withSupportedUrlActions(UrlAction.OPEN_IN_APP_BROWSER, new UrlAction[0]).build().handleUrl(NativeVideoViewController.access$600(this.this$0), "https://www.mopub.com/optout/");
    }
}
