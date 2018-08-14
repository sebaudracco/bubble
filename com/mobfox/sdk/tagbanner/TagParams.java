package com.mobfox.sdk.tagbanner;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TagParams extends HashMap<String, String> {
    public static final String ADSPACE_STRICT = "adspace_strict";
    public static final String C_MRID = "c_mraid";
    public static final String DEMO_AGE = "demo_age";
    public static final String DEMO_GENDER = "demo_gender";
    public static final String DEMO_KEYWORDS = "demo_keywords";
    public static final String DEV_DNT = "dev_dnt";
    public static final String DEV_JS = "dev_js";
    public static final String DEV_LMT = "dev_lmt";
    public static final String HEIGHT = "adspace_height";
    public static final String IMP_SECURE = "imp_secure";
    public static final String INVENTORY_HASH = "s";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String O_ANDADVID = "o_andadvid";
    public static final String RT = "rt";
    public static final String R_FLOOR = "r_floor";
    public static final String SUB_BUNDLE_ID = "sub_bundle_id";
    public static final String SUB_DOMAIN = "sub_domain";
    public static final String SUB_NAME = "sub_name";
    public static final String SUB_STOREURL = "sub_storeurl";
    public static final String S_SUBID = "s_subid";
    public static final String TAG_SECURE_URL = "https://sdk.starbolt.io/dist/tagBanner.html";
    public static final String TAG_URL = "http://sdk.starbolt.io/dist/tagBanner.html";
    public static final String TAG_URL_PATH = "dist/tagBanner.html";
    public static final String VERSION = "v";
    public static final String WIDTH = "adspace_width";

    public String getTagUrl() {
        String url = TAG_URL;
        if (containsKey(IMP_SECURE) && Integer.parseInt((String) get(IMP_SECURE)) == 1) {
            return TAG_SECURE_URL;
        }
        return url;
    }

    public String toQuery() {
        List<String> kvs = new ArrayList();
        for (String key : keySet()) {
            try {
                kvs.add(String.format("%s=%s", new Object[]{key, URLEncoder.encode((String) get(key), "UTF-8")}));
            } catch (UnsupportedEncodingException e) {
            }
        }
        return TextUtils.join("&", kvs);
    }

    public void setTagParam(String key, String value) {
        if (key != null && !containsKey(key) && value != null && !value.isEmpty()) {
            put(key, value);
        }
    }

    public void setTagParam(String key, int value) {
        if (key != null && !containsKey(key)) {
            put(key, String.valueOf(value));
        }
    }

    public void setTagParam(String key, float value) {
        if (key != null && !containsKey(key)) {
            put(key, String.valueOf(value));
        }
    }

    public void setTagParam(String key, double value) {
        if (key != null && !containsKey(key)) {
            put(key, String.valueOf(value));
        }
    }

    protected void mergeParams(TagParams params) {
        for (String key : params.keySet()) {
            if (!containsKey(key)) {
                put(key, params.get(key));
            }
        }
    }

    public String getTagUrlQuery() {
        String url = "";
        try {
            url = String.format(getTagUrl() + "?%s", new Object[]{toQuery()});
        } catch (Exception e) {
        }
        return url;
    }
}
