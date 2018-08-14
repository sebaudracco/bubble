package com.appnext.base.p023b;

import com.appnext.core.C1132k;

public class C1042c {
    public static final String PACKAGE_NAME = "com.appnext.sdk";
    public static final String jA = "month";
    public static final String jB = "monitoring";
    public static final String jC = "time";
    public static final String jD = "once";
    public static final String jE = "interval";
    public static final String jF = "on";
    public static final String jG = "off";
    public static final String jH = "com.appnext.sdk.DETECTED_ACTIVITIES";
    public static final String jI = "com.appnext.sdk.ACTIVITIES_BROADCAST_ACTION";
    public static final String jJ = "com.appnext.sdk.ACTIVITIES_EXTRA";
    public static final String jK = "data";
    public static final String jL = "action";
    public static final String jM = "before_time_remove_data";
    public static final String jN = "type";
    public static final String jO = "service_key";
    public static final int jP = 6;
    public static final int jQ = 70;
    public static final int jR = 50;
    public static final int jS = 25;
    public static final int jT = 100;
    public static final String jU = "wpul_driving_state_dmstat";
    public static final int jV = 150;
    public static final String jj = "4.6.8";
    public static final String jk = "config.json";
    public static final String jl = "plist.zip";
    public static final String jm = "plist.json";
    public static final String jn = "/data/appnext/";
    public static final String jo = "videos/";
    public static final String jp = ".tmp";
    public static final String jq = "http://cdn.appnext.com/tools/services/4.6.7/config.json";
    public static final String jr = "http://cdn.appnext.com/tools/services/4.6.7/plist.zip";
    public static final int js = 1024;
    public static final long jt = 1048576;
    public static final int ju = 15000;
    public static final String jv = "config_data_obj";
    public static final String jw = "second";
    public static final String jx = "minute";
    public static final String jy = "hour";
    public static final String jz = "day";
    public static final long serialVersionUID = 3596288679259847957L;

    public enum C1041a {
        String("String"),
        Long("Long"),
        Double("Double"),
        Integer("Integer"),
        HashMap("HashMap"),
        ArrayList("ArrayList"),
        Boolean("Boolean"),
        JSONArray("JSONArray"),
        JSONObject("JSONObject"),
        Set("Set");
        
        private String mDataType;

        private C1041a(String str) {
            this.mDataType = str;
        }

        public String getType() {
            return this.mDataType;
        }
    }

    public static final String cp() {
        return C1132k.mh;
    }

    public static final String cq() {
        return C1132k.mi;
    }
}
