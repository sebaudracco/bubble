package com.appnext.ads.interstitial;

import android.graphics.Color;
import com.appnext.core.C0921q;
import com.appnext.core.Configuration;
import java.io.Serializable;

public class InterstitialConfig extends Configuration implements Serializable {
    private static final long serialVersionUID = 1;
    public Boolean autoPlay;
    public Boolean backButtonCanClose;
    public String buttonColor = "";
    public String creativeType = Interstitial.TYPE_MANAGED;
    protected boolean fd = false;
    public String skipText = "";

    public void setCreativeType(String str) {
        if (str.equals(Interstitial.TYPE_MANAGED) || str.equals("static") || str.equals("video")) {
            this.creativeType = str;
            return;
        }
        throw new IllegalArgumentException("Wrong creative type");
    }

    public String getCreativeType() {
        return this.creativeType;
    }

    protected C0921q ai() {
        return C0980c.ax();
    }

    public boolean isAutoPlay() {
        return this.autoPlay == null ? Boolean.parseBoolean(ai().get("auto_play")) : this.autoPlay.booleanValue();
    }

    public void setAutoPlay(boolean z) {
        this.autoPlay = Boolean.valueOf(z);
    }

    public void setSkipText(String str) {
        if (str == null) {
            str = "";
        }
        this.skipText = str;
    }

    public String getSkipText() {
        return this.skipText;
    }

    protected boolean ao() {
        return this.mute != null;
    }

    protected boolean av() {
        return this.autoPlay != null;
    }

    protected boolean aw() {
        return this.backButtonCanClose != null;
    }

    public void setButtonColor(String str) {
        if (str == null) {
            this.buttonColor = "";
            return;
        }
        if (!str.startsWith("#")) {
            str = "#" + str;
        }
        try {
            Color.parseColor(str);
            this.buttonColor = str;
            this.fd = true;
        } catch (Throwable th) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Unknown color");
        }
    }

    public String getButtonColor() {
        return this.buttonColor;
    }

    public boolean isBackButtonCanClose() {
        return this.backButtonCanClose == null ? Boolean.parseBoolean(ai().get("can_close")) : this.backButtonCanClose.booleanValue();
    }

    public void setBackButtonCanClose(boolean z) {
        this.backButtonCanClose = Boolean.valueOf(z);
    }
}
