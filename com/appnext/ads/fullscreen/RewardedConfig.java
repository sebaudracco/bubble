package com.appnext.ads.fullscreen;

import com.appnext.core.C0921q;
import java.io.Serializable;

public class RewardedConfig extends VideoConfig implements Serializable {
    private static final long serialVersionUID = 1;
    private String mode = "default";
    private int multiTimerLength = 3;

    public String getMode() {
        return this.mode;
    }

    public void setMode(String str) {
        this.mode = str;
    }

    public int getMultiTimerLength() {
        return this.multiTimerLength;
    }

    public void setMultiTimerLength(int i) {
        this.multiTimerLength = i;
    }

    protected C0921q ai() {
        return C0940f.al();
    }
}
