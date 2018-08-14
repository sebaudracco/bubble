package com.mobfox.sdk.networking;

import com.google.firebase.analytics.FirebaseAnalytics$Param;
import com.mobfox.sdk.constants.Constants;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestParams {
    public static final String ADSPACE_HEIGHT = "adspace_height";
    public static final String ADSPACE_STRICT = "adspace_strict";
    public static final String ADSPACE_WIDTH = "adspace_width";
    public static final String DEMO_AGE = "demo_age";
    public static final String DEMO_GENDER = "demo_gender";
    public static final String DEMO_KEYWORDS = "demo_keywords";
    public static final String DEV_DNT = "dev_dnt";
    public static final String f9035H = "h";
    public static final String INVH = "s";
    public static final String IP = "i";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String f9036M = "m";
    public static final String N_ADUNIT = "n_adunit";
    public static final String N_CONTEXT = "n_context";
    public static final String N_DESC_LEN = "n_desc_len";
    public static final String N_DESC_REQ = "n_desc_req";
    public static final String N_IMG = "n_img";
    public static final String N_IMG_ICON_REQ = "n_img_icon_req";
    public static final String N_IMG_ICON_SIZE = "n_img_icon_size";
    public static final String N_IMG_LARGE_H = "n_img_large_h";
    public static final String N_IMG_LARGE_REQ = "n_img_large_req";
    public static final String N_IMG_LARGE_W = "n_img_large_w";
    public static final String N_LAYOUT = "n_layout";
    public static final String N_PLCMTTYPE = "n_plcmttype";
    public static final String N_RATING_REQ = "n_rating_req";
    public static final String N_TITLE_LEN = "n_title_len";
    public static final String N_TITLE_REQ = "n_title_req";
    public static final String N_TXT = "n_txt";
    public static final String N_TYPE = "n_type";
    public static final String N_VER = "n_ver";
    public static final String O_ANDADVID = "o_andadvid";
    public static final String O_ANDROIDID = "o_andadvid";
    public static final String O_ANDROIDIMEI = "o_androidimei";
    public static final String f9037P = "p";
    public static final String RT = "rt";
    public static final String R_FLOOR = "r_floor";
    public static final String R_RESP = "r_resp";
    public static final String R_TYPE = "r_type";
    public static final String SUB_BUNDLE_ID = "sub_bundle_id";
    public static final String SUB_DOMAIN = "sub_domain";
    public static final String SUB_NAME = "sub_name";
    public static final String SUB_STOREURL = "sub_storeurl";
    public static final String S_SUBID = "s_subid";
    public static final String f9038U = "u";
    public static final String U_BR = "u_br";
    public static final String U_WV = "u_vw";
    public static final String f9039V = "v";
    public static final String domain = "my.mobfox.com";
    static String userAgent = System.getProperty("http.agent");
    JSONObject params = new JSONObject();

    public RequestParams() {
        setParam(f9038U, userAgent);
        setParam("rt", "android_app");
        setParam(R_TYPE, "native");
        setParam("adspace_strict", 0);
        setParam(N_VER, 1.1d);
        setParam(N_CONTEXT, FirebaseAnalytics$Param.CONTENT);
        setParam(N_PLCMTTYPE, "atomic");
        setParam("v", Constants.MOBFOX_SDK_VERSION);
        setParam(N_IMG_ICON_REQ, 1);
        setParam(N_IMG_ICON_SIZE, 80);
        setParam(N_TITLE_REQ, 1);
        setParam(N_TITLE_LEN, 100);
        setParam(N_DESC_REQ, 1);
        setParam(N_DESC_LEN, 200);
        setParam(N_IMG_LARGE_REQ, 1);
        setParam(N_IMG_LARGE_W, 1200);
        setParam(N_IMG_LARGE_H, 627);
    }

    public RequestParams(RequestParams rp) {
        try {
            this.params = new JSONObject(rp.params.toString());
        } catch (JSONException e) {
            this.params = new JSONObject();
        }
    }

    public Object getParam(String name) {
        Object obj = null;
        if (!(name == null || name.length() == 0)) {
            try {
                obj = this.params.get(name);
            } catch (JSONException e) {
            }
        }
        return obj;
    }

    public Iterator<String> getNames() {
        return this.params.keys();
    }

    public void setParam(String name, String value) {
        if (name != null && value != null && name.length() != 0) {
            try {
                this.params.put(name, value);
            } catch (JSONException e) {
            }
        }
    }

    public void setParam(String name, int value) {
        if (name != null && name.length() != 0) {
            try {
                this.params.put(name, value);
            } catch (JSONException e) {
            }
        }
    }

    public void setParam(String name, double value) {
        if (name != null && name.length() != 0) {
            try {
                this.params.put(name, value);
            } catch (JSONException e) {
            }
        }
    }

    public void setParam(String name, boolean value) {
        if (name != null && name.length() != 0) {
            try {
                this.params.put(name, value);
            } catch (JSONException e) {
            }
        }
    }

    public JSONObject getJSON() {
        return this.params;
    }

    public void setParams(JSONObject params) {
        this.params = params;
    }
}
