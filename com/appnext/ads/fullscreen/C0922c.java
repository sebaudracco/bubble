package com.appnext.ads.fullscreen;

import com.appnext.core.C0921q;
import java.util.HashMap;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class C0922c extends C0921q {
    private static C0922c dO;
    private String bW = "https://cdn.appnext.com/tools/sdk/config/2.2.5/fullscreen_config.txt";
    private HashMap<String, String> bq = null;

    public static synchronized C0922c aj() {
        C0922c c0922c;
        synchronized (C0922c.class) {
            if (dO == null) {
                dO = new C0922c();
            }
            c0922c = dO;
        }
        return c0922c;
    }

    private C0922c() {
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

    public void m1925b(HashMap<String, String> hashMap) {
        this.bq = hashMap;
    }

    protected HashMap<String, String> mo1983E() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("can_close", SchemaSymbols.ATTVAL_TRUE);
        hashMap.put("show_close", SchemaSymbols.ATTVAL_TRUE);
        hashMap.put("video_length", "15");
        hashMap.put("mute", SchemaSymbols.ATTVAL_FALSE);
        hashMap.put("urlApp_protection", SchemaSymbols.ATTVAL_FALSE);
        hashMap.put("pview", SchemaSymbols.ATTVAL_TRUE);
        hashMap.put("min_internet_connection_video", "3g");
        hashMap.put("banner_expiration_time", SchemaSymbols.ATTVAL_FALSE_0);
        hashMap.put("postpone_vta_sec", SchemaSymbols.ATTVAL_FALSE_0);
        hashMap.put("postpone_impression_sec", SchemaSymbols.ATTVAL_FALSE_0);
        hashMap.put("resolve_timeout", "8");
        hashMap.put("fq_control", SchemaSymbols.ATTVAL_FALSE);
        hashMap.put("num_saved_videos", "5");
        hashMap.put("caption_text_time", "3");
        hashMap.put("ads_caching_time_minutes", SchemaSymbols.ATTVAL_FALSE_0);
        return hashMap;
    }
}
