package com.appnext.ads.fullscreen;

import com.appnext.core.AppnextAd;
import com.appnext.core.C0921q;
import java.util.ArrayList;

public interface C0906i {
    void closeClicked();

    C0921q getConfigManager();

    ArrayList<AppnextAd> getPreRollAds();

    int getTemplate(String str);

    void privacyClicked();

    void report(String str, String str2);

    void videoSelected(AppnextAd appnextAd);
}
