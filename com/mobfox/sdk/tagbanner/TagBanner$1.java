package com.mobfox.sdk.tagbanner;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.mobfox.sdk.constants.Constants;

class TagBanner$1 extends WebViewClient {
    final /* synthetic */ TagBanner this$0;
    final /* synthetic */ TagBanner val$self;

    TagBanner$1(TagBanner this$0, TagBanner tagBanner) {
        this.this$0 = this$0;
        this.val$self = tagBanner;
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (this.val$self.userInteraction) {
            try {
                Intent launchBrowser = new Intent("android.intent.action.VIEW");
                launchBrowser.setData(Uri.parse(url));
                launchBrowser.setFlags(ErrorDialogData.BINDER_CRASH);
                this.val$self.context.startActivity(launchBrowser);
            } catch (Throwable th) {
                Log.d(Constants.MOBFOX_BANNER, "launch browser exception");
            }
            this.val$self.listener.onBannerClicked(this.val$self);
        }
        return true;
    }
}
