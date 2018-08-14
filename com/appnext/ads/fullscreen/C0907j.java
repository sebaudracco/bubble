package com.appnext.ads.fullscreen;

import android.net.Uri;
import com.appnext.core.C0921q;

public interface C0907j {
    void closeClicked();

    long closeDelay();

    int getCaptionTextTime();

    C0921q getConfigManager();

    String getCtaText();

    boolean getMute();

    Uri getSelectedVideoUri();

    int getTemplate(String str);

    void installClicked();

    boolean isInstalled();

    void privacyClicked();

    void report(String str, String str2);

    boolean showClose();

    void videoEnded();

    void videoStarted();
}
