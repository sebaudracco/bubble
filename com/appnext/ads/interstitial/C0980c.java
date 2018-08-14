package com.appnext.ads.interstitial;

import com.appnext.core.C0921q;
import com.google.ads.mediation.facebook.FacebookAdapter;
import java.util.HashMap;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class C0980c extends C0921q {
    private static C0980c fe;
    private String bW = "https://cdn.appnext.com/tools/sdk/config/2.2.5/interstitial_config.txt";
    private HashMap<String, String> bq = null;

    public static synchronized C0980c ax() {
        C0980c c0980c;
        synchronized (C0980c.class) {
            if (fe == null) {
                fe = new C0980c();
            }
            c0980c = fe;
        }
        return c0980c;
    }

    private C0980c() {
    }

    protected String getUrl() {
        return this.bW;
    }

    protected HashMap<String, String> mo1982D() {
        return this.bq;
    }

    public void setUrl(String str) {
        this.bW = str;
    }

    public void m2034b(HashMap<String, String> hashMap) {
        this.bq = hashMap;
    }

    protected HashMap<String, String> mo1983E() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("creative", Interstitial.TYPE_MANAGED);
        hashMap.put("auto_play", SchemaSymbols.ATTVAL_TRUE);
        hashMap.put("mute", SchemaSymbols.ATTVAL_FALSE);
        hashMap.put("pview", SchemaSymbols.ATTVAL_TRUE);
        hashMap.put("min_internet_connection", "2g");
        hashMap.put("min_internet_connection_video", "3g");
        hashMap.put("urlApp_protection", SchemaSymbols.ATTVAL_FALSE);
        hashMap.put("can_close", SchemaSymbols.ATTVAL_FALSE);
        hashMap.put("video_length", "15");
        hashMap.put("button_text", "");
        hashMap.put(FacebookAdapter.KEY_BUTTON_COLOR, "");
        hashMap.put("skip_title", "");
        hashMap.put("remove_poster_on_auto_play", SchemaSymbols.ATTVAL_TRUE);
        hashMap.put("banner_expiration_time", SchemaSymbols.ATTVAL_FALSE_0);
        hashMap.put("show_rating", SchemaSymbols.ATTVAL_TRUE);
        hashMap.put("show_desc", SchemaSymbols.ATTVAL_TRUE);
        hashMap.put("new_button_text", "Install");
        hashMap.put("existing_button_text", "Open");
        hashMap.put("postpone_vta_sec", SchemaSymbols.ATTVAL_FALSE_0);
        hashMap.put("postpone_impression_sec", SchemaSymbols.ATTVAL_FALSE_0);
        hashMap.put("fq_control", SchemaSymbols.ATTVAL_FALSE);
        hashMap.put("resolve_timeout", "8");
        hashMap.put("ads_caching_time_minutes", SchemaSymbols.ATTVAL_FALSE_0);
        return hashMap;
    }
}
