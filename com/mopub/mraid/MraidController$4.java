package com.mopub.mraid;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import com.mopub.common.CloseableLayout.ClosePosition;
import com.mopub.mraid.MraidBridge.MraidBridgeListener;
import java.net.URI;

class MraidController$4 implements MraidBridgeListener {
    final /* synthetic */ MraidController this$0;

    MraidController$4(MraidController this$0) {
        this.this$0 = this$0;
    }

    public void onPageLoaded() {
        this.this$0.handleTwoPartPageLoad();
    }

    public void onPageFailedToLoad() {
    }

    public void onVisibilityChanged(boolean isVisible) {
        MraidController.access$200(this.this$0).notifyViewability(isVisible);
        MraidController.access$100(this.this$0).notifyViewability(isVisible);
    }

    public boolean onJsAlert(@NonNull String message, @NonNull JsResult result) {
        return this.this$0.handleJsAlert(message, result);
    }

    public boolean onConsoleMessage(@NonNull ConsoleMessage consoleMessage) {
        return this.this$0.handleConsoleMessage(consoleMessage);
    }

    public void onResize(int width, int height, int offsetX, int offsetY, @NonNull ClosePosition closePosition, boolean allowOffscreen) throws MraidCommandException {
        throw new MraidCommandException("Not allowed to resize from an expanded state");
    }

    public void onExpand(@Nullable URI uri, boolean shouldUseCustomClose) {
    }

    public void onClose() {
        this.this$0.handleClose();
    }

    public void onUseCustomClose(boolean shouldUseCustomClose) {
        this.this$0.handleCustomClose(shouldUseCustomClose);
    }

    public void onSetOrientationProperties(boolean allowOrientationChange, MraidOrientation forceOrientation) throws MraidCommandException {
        this.this$0.handleSetOrientationProperties(allowOrientationChange, forceOrientation);
    }

    public void onOpen(URI uri) {
        this.this$0.handleOpen(uri.toString());
    }

    public void onPlayVideo(@NonNull URI uri) {
        this.this$0.handleShowVideo(uri.toString());
    }
}
