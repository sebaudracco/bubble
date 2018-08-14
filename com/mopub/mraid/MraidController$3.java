package com.mopub.mraid;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import com.mopub.common.CloseableLayout.ClosePosition;
import com.mopub.mraid.MraidBridge.MraidBridgeListener;
import java.net.URI;

class MraidController$3 implements MraidBridgeListener {
    final /* synthetic */ MraidController this$0;

    MraidController$3(MraidController this$0) {
        this.this$0 = this$0;
    }

    public void onPageLoaded() {
        this.this$0.handlePageLoad();
    }

    public void onPageFailedToLoad() {
        if (MraidController.access$000(this.this$0) != null) {
            MraidController.access$000(this.this$0).onFailedToLoad();
        }
    }

    public void onVisibilityChanged(boolean isVisible) {
        if (!MraidController.access$100(this.this$0).isAttached()) {
            MraidController.access$200(this.this$0).notifyViewability(isVisible);
        }
    }

    public boolean onJsAlert(@NonNull String message, @NonNull JsResult result) {
        return this.this$0.handleJsAlert(message, result);
    }

    public boolean onConsoleMessage(@NonNull ConsoleMessage consoleMessage) {
        return this.this$0.handleConsoleMessage(consoleMessage);
    }

    public void onClose() {
        this.this$0.handleClose();
    }

    public void onResize(int width, int height, int offsetX, int offsetY, @NonNull ClosePosition closePosition, boolean allowOffscreen) throws MraidCommandException {
        this.this$0.handleResize(width, height, offsetX, offsetY, closePosition, allowOffscreen);
    }

    public void onExpand(@Nullable URI uri, boolean shouldUseCustomClose) throws MraidCommandException {
        this.this$0.handleExpand(uri, shouldUseCustomClose);
    }

    public void onUseCustomClose(boolean shouldUseCustomClose) {
        this.this$0.handleCustomClose(shouldUseCustomClose);
    }

    public void onSetOrientationProperties(boolean allowOrientationChange, MraidOrientation forceOrientation) throws MraidCommandException {
        this.this$0.handleSetOrientationProperties(allowOrientationChange, forceOrientation);
    }

    public void onOpen(@NonNull URI uri) {
        this.this$0.handleOpen(uri.toString());
    }

    public void onPlayVideo(@NonNull URI uri) {
        this.this$0.handleShowVideo(uri.toString());
    }
}
