package org.telegram.ui.Components;

import org.telegram.ui.Components.EmbedBottomSheet.YoutubeProxy;

class EmbedBottomSheet$YoutubeProxy$1 implements Runnable {
    final /* synthetic */ YoutubeProxy this$1;
    final /* synthetic */ String val$eventName;

    EmbedBottomSheet$YoutubeProxy$1(YoutubeProxy this$1, String str) {
        this.this$1 = this$1;
        this.val$eventName = str;
    }

    public void run() {
        String str = this.val$eventName;
        boolean z = true;
        switch (str.hashCode()) {
            case -1097519099:
                if (str.equals("loaded")) {
                    z = false;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                this.this$1.this$0.progressBar.setVisibility(4);
                this.this$1.this$0.progressBarBlackBackground.setVisibility(4);
                this.this$1.this$0.pipButton.setEnabled(true);
                this.this$1.this$0.pipButton.setAlpha(1.0f);
                this.this$1.this$0.showOrHideYoutubeLogo(false);
                return;
            default:
                return;
        }
    }
}
