package com.appnext.ads.fullscreen;

import com.appnext.core.C0921q;
import com.appnext.core.Configuration;
import java.io.Serializable;

public class VideoConfig extends Configuration implements Serializable {
    private static final long serialVersionUID = 1;
    public int rollCaptionTime = -2;
    public Boolean showCta;
    public String videoLength = "15";

    public String getVideoLength() {
        return this.videoLength;
    }

    public void setVideoLength(String str) {
        if (str.equals("15") || str.equals("30")) {
            this.videoLength = str;
            return;
        }
        throw new IllegalArgumentException("Wrong video length");
    }

    protected C0921q ai() {
        return C0922c.aj();
    }

    protected boolean ao() {
        return this.mute != null;
    }

    public int getRollCaptionTime() {
        return this.rollCaptionTime;
    }

    public void setRollCaptionTime(int i) {
        this.rollCaptionTime = i;
    }

    protected boolean ap() {
        return this.showCta != null;
    }

    public boolean isShowCta() {
        return this.showCta == null ? true : this.showCta.booleanValue();
    }

    public void setShowCta(boolean z) {
        this.showCta = Boolean.valueOf(z);
    }
}
