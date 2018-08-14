package com.appnext.ads.fullscreen;

import com.appnext.core.AppnextAd;
import com.appnext.core.C0921q;
import java.util.ArrayList;

public interface C0905h {
    void closeClicked();

    C0921q getConfigManager();

    String getCtaText();

    ArrayList<AppnextAd> getPostRollAds();

    int getTemplate(String str);

    void installClicked();

    void installClicked(AppnextAd appnextAd);

    void privacyClicked();

    void report(String str, String str2);
}
