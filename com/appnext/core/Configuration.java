package com.appnext.core;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public abstract class Configuration implements Serializable {
    private static final long serialVersionUID = 1;
    public String categories = "";
    public int maxVideoLength = 0;
    public int minVideoLength = 0;
    public Boolean mute;
    public String orientation = Ad.ORIENTATION_DEFAULT;
    public String postback = "";

    protected abstract C0921q ai();

    public Configuration() {
        ai().m1920a(null);
    }

    public void setCategories(String str) {
        String str2;
        if (str == null) {
            str2 = "";
        } else {
            str2 = str;
        }
        try {
            if (str2.equals(URLDecoder.decode(str2, "UTF-8"))) {
                str2 = URLEncoder.encode(str2, "UTF-8");
            }
        } catch (Throwable th) {
            str2 = "";
        }
        this.categories = str2;
    }

    public void setPostback(String str) {
        String str2;
        if (str == null) {
            str2 = "";
        } else {
            str2 = str;
        }
        try {
            if (str2.equals(URLDecoder.decode(str2, "UTF-8"))) {
                str2 = URLEncoder.encode(str2, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            str2 = "";
        }
        this.postback = str2;
    }

    public void setMute(boolean z) {
        this.mute = Boolean.valueOf(z);
    }

    public String getCategories() {
        return this.categories;
    }

    public String getPostback() {
        return this.postback;
    }

    public boolean getMute() {
        return this.mute == null ? Boolean.parseBoolean(ai().get("mute")) : this.mute.booleanValue();
    }

    public String getOrientation() {
        return this.orientation;
    }

    public void setOrientation(String str) {
        if (str == null) {
            throw new IllegalArgumentException("orientation type");
        } else if (str.equals(Ad.ORIENTATION_AUTO) || str.equals(Ad.ORIENTATION_DEFAULT) || str.equals(Ad.ORIENTATION_LANDSCAPE) || str.equals(Ad.ORIENTATION_PORTRAIT)) {
            this.orientation = str;
        } else {
            throw new IllegalArgumentException("Wrong orientation type");
        }
    }

    public int getMaxVideoLength() {
        return this.maxVideoLength;
    }

    public void setMaxVideoLength(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Max Length must be higher than 0");
        } else if (i <= 0 || getMinVideoLength() <= 0 || i >= getMinVideoLength()) {
            this.maxVideoLength = i;
        } else {
            throw new IllegalArgumentException("Max Length cannot be lower than min length");
        }
    }

    public int getMinVideoLength() {
        return this.minVideoLength;
    }

    public void setMinVideoLength(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Min Length must be higher than 0");
        } else if (i <= 0 || getMaxVideoLength() <= 0 || i <= getMaxVideoLength()) {
            this.minVideoLength = i;
        } else {
            throw new IllegalArgumentException("Min Length cannot be higher than max length");
        }
    }
}
