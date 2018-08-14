package com.appnext.ads.fullscreen;

import com.appnext.core.C0921q;
import java.util.HashMap;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class C0940f extends C0921q {
    private static C0940f er;
    private String bW = "https://cdn.appnext.com/tools/sdk/config/2.2.5/rewarded_config.txt";
    private HashMap<String, String> bq = null;

    public static synchronized C0940f al() {
        C0940f c0940f;
        synchronized (C0940f.class) {
            if (er == null) {
                er = new C0940f();
            }
            c0940f = er;
        }
        return c0940f;
    }

    private C0940f() {
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

    public void m1942b(HashMap<String, String> hashMap) {
        this.bq = hashMap;
    }

    protected HashMap<String, String> mo1983E() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("can_close", SchemaSymbols.ATTVAL_FALSE);
        hashMap.put("show_close", SchemaSymbols.ATTVAL_FALSE);
        hashMap.put("video_length", "15");
        hashMap.put("mute", SchemaSymbols.ATTVAL_FALSE);
        hashMap.put("urlApp_protection", SchemaSymbols.ATTVAL_FALSE);
        hashMap.put("pview", SchemaSymbols.ATTVAL_TRUE);
        hashMap.put("min_internet_connection_video", "3g");
        hashMap.put("banner_expiration_time", SchemaSymbols.ATTVAL_FALSE_0);
        hashMap.put("default_mode", "normal");
        hashMap.put("postpone_vta_sec", SchemaSymbols.ATTVAL_FALSE_0);
        hashMap.put("postpone_impression_sec", SchemaSymbols.ATTVAL_FALSE_0);
        hashMap.put("resolve_timeout", "8");
        hashMap.put("fq_control", SchemaSymbols.ATTVAL_FALSE);
        hashMap.put("num_saved_videos", "5");
        hashMap.put("caption_text_time", "3");
        hashMap.put("pre_title_string1", "Which Ad");
        hashMap.put("pre_title_string2", "Would you like to watch?");
        hashMap.put("pre_cta_string", "Play this ad");
        hashMap.put("ads_caching_time_minutes", SchemaSymbols.ATTVAL_FALSE_0);
        return hashMap;
    }
}
