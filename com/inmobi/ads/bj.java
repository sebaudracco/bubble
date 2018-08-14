package com.inmobi.ads;

/* compiled from: Utils */
public class bj {
    public static String m9518a(String str) {
        if (str.equalsIgnoreCase("window.mraidview")) {
            return "mraidview";
        }
        if (str.equalsIgnoreCase("window.imraidview")) {
            return "imraidview";
        }
        if (str.equalsIgnoreCase("window.imaiview")) {
            return "imaiview";
        }
        return "NA_ERROR_" + str;
    }
}
