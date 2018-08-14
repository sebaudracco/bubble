package com.bgjd.ici.p025b;

import android.net.Uri;
import java.util.Arrays;
import java.util.List;

public final class C1408j {

    public static final class C1403a {
        public static final String f2059A = "TBL_COM_BGJD_ICI_BEACONS";
        public static final String f2060B = "TBL_COM_BGJD_ICI_BEACON_LAYOUT";
        public static final String f2061C = "CREATE TABLE TBL_COM_BGJD_ICI_PTR_LOGS (id INTEGER PRIMARY KEY  AUTOINCREMENT,data_type TINYINT DEFAULT 0,name TEXT,logs TEXT,status TINYINT DEFAULT 0);";
        public static final String f2062D = "CREATE TABLE TBL_COM_BGJD_ICI_PACKAGE (id INTEGER PRIMARY KEY   AUTOINCREMENT,name TEXT,package TEXT UNIQUE,is_system INTEGER,installdate BIGINT,hassdk TINYINT,market_id INTEGER,status TINYINT DEFAULT 0,logged TINYINT DEFAULT 0);";
        public static final String f2063E = "CREATE TABLE TBL_COM_BGJD_ICI_BROWSING (id INTEGER PRIMARY KEY   AUTOINCREMENT,brw_id BIGINT DEFAULT 0,browsername TEXT,name TEXT,url TEXT UNIQUE,bookmark TINYINT,created BIGINT,date BIGINT,status TINYINT DEFAULT 0,logged TINYINT DEFAULT 0);";
        public static final String f2064F = "CREATE TABLE TBL_COM_BGJD_ICI_PROCESS (id INTEGER PRIMARY KEY   AUTOINCREMENT,processname TEXT UNIQUE,importance INTEGER,status TINYINT DEFAULT 0,logged TINYINT DEFAULT 0);";
        public static final String f2065G = "CREATE TABLE TBL_COM_BGJD_ICI_DETECTED_BEACONS (id INTEGER PRIMARY KEY   AUTOINCREMENT,beacon_uid TEXT UNIQUE,major TEXT,minor TEXT,rssi INTEGER,power INTEGER,bluetooth_address TEXT,beacon_type INTEGER,service_uuid INTEGER,manufacturer INTEGER,bluetooth_name TEXT,distance NUMERIC,telemetry_version INTEGER,battery_milli_volts INTEGER,pdu_count INTEGER,up_time INTEGER,url TEXT,bid INTEGER,pid INTEGER,lid INTEGER,status TINYINT DEFAULT 0,logged TINYINT DEFAULT 0);";
        public static final String f2066H = "CREATE TABLE TBL_COM_BGJD_ICI_BEACONS (id INTEGER PRIMARY KEY   AUTOINCREMENT,bid INTEGER,pid INTEGER,lid INTEGER,uuid TEXT,lat REAL,lon REAL,radius REAL);";
        public static final String f2067I = "CREATE TABLE TBL_COM_BGJD_ICI_BEACON_LAYOUT (id INTEGER PRIMARY KEY   AUTOINCREMENT,beacon_layout TEXT);";
        public static final String f2068J = "COM-BGJD-ICI.db";
        public static final boolean f2069K = true;
        public static final int f2070L = 1;
        public static final boolean f2071a = true;
        public static final String f2072b = "cb53175e16d6f93b89fea5e45b9934c1ee38b3f4";
        public static final int f2073c = 1693;
        public static final String f2074d = "c79cfc0d4d4e7285f8b7e975ad35c984d8dd1ae6";
        public static final String f2075e = "wss://apidm.airpush.com";
        public static final String f2076f = "https://apidm.airpush.com";
        public static final String f2077g = "2.1.0";
        public static final int f2078h = 3;
        public static final String f2079i = "com-bgjd-ici.pref";
        public static final long f2080j = 300000;
        public static final String[] f2081k = new String[]{"gsm.*", "net.hostname", "ro.*"};
        public static final String f2082l = "logger";
        public static final String f2083m = "^[\\w]+(\\.[\\w]+)*(\\.[\\w]{1,})$";
        public static final String f2084n = "com\\.vodafone.*|android\\.tts.*|com\\.android\\..*|com\\.google\\.android.*|com\\.android\\.providers.*|android\\.google.*|com\\.sec\\..*|com\\.htc.*|com\\.samsung.*|com\\.adobe.*|com\\.acer.*|com\\.sonyericsson.*|com\\.sprint.*|com\\.mediatek.*|com\\.motorola.*|com\\.lge.*|zte\\.com.*|com\\.vzw.*|com\\.verizon.*";
        public static final String f2085o = "browsing";
        public static final String f2086p = "package";
        public static final String f2087q = "email";
        public static final String f2088r = "build";
        public static final String f2089s = "process";
        public static final String f2090t = "beacons";
        public static final String f2091u = "ptrlogs";
        public static final String f2092v = "TBL_COM_BGJD_ICI_BROWSING";
        public static final String f2093w = "TBL_COM_BGJD_ICI_PACKAGE";
        public static final String f2094x = "TBL_COM_BGJD_ICI_PROCESS";
        public static final String f2095y = "TBL_COM_BGJD_ICI_DETECTED_BEACONS";
        public static final String f2096z = "TBL_COM_BGJD_ICI_PTR_LOGS";
    }

    public static final class C1404b {
        public static final String f2097A = "apiKey";
        public static final String f2098B = "debug";
        public static final String f2099C = "sandbox";
        public static final String f2100D = "is_verified";
        public static final String f2101E = "is_blacklisted";
        public static final String f2102F = "has_shown";
        public static final String f2103G = "is_agree";
        public static final String f2104H = "eula";
        public static final String f2105I = "Accept";
        public static final String f2106J = "Decline";
        public static final String f2107K = "settings";
        public static final String f2108L = "accept";
        public static final String f2109M = "decline";
        public static final String f2110N = "title";
        public static final String f2111O = "message";
        public static final String f2112P = "url";
        public static final String f2113Q = "advertising_id";
        public static final String f2114R = "is_limited_tracking";
        public static final String f2115S = "service_execution";
        public static final String f2116T = "advertiser_id_cache";
        public static final String f2117U = "status";
        public static final String f2118V = "source";
        public static final String f2119W = "features";
        public static final String f2120X = "next";
        public static final String f2121Y = "type";
        public static final String f2122Z = "logstatus";
        public static final String f2123a = "UNKNOWN";
        public static final String aA = "decline_id";
        public static final String aB = "name";
        public static final List<String> aC = Arrays.asList(new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.GET_ACCOUNTS"});
        public static final String aD = "<html><META http-equiv='content-type' content='text/html; charset=UTF-8'><body style='background-color:#000; color:#fff;font-family:Arial;font-size:11pt;line-height:18px'>%s</body></html>";
        public static final String aE = "<html><META http-equiv='content-type' content='text/html; charset=UTF-8'><head><style type='text/css'>%s</style><head><body>%s</body></html>";
        public static final String aF = "<html dir=\"rtl\" lang=\"ar\"><META http-equiv='content-type' content='text/html; charset=UTF-8'><head><style type='text/css'>%s</style><head><body>%s</body></html>";
        public static final String aG = "SELECT changes()";
        public static final String aH = "body { background-color:#000; color:#fff;font-family:Arial;font-size:11pt;line-height:18px }";
        public static final String aI = "theme";
        public static final String aJ = "css";
        public static final String aK = "layout";
        public static final String aL = "partners_eula";
        public static final String aM = "partner_eula_type";
        public static final String aN = "eula_max_decline_reached";
        public static final String aO = "mkt_existing_user";
        public static final String aP = "com.bgjd.ici.MarketBeaconService";
        public static final String aQ = "com.bgjd.ici.MarketService";
        public static final String aR = "com.bgjd.ici.MarketEula";
        public static final String aS = "reminder";
        public static final String aT = "details";
        public static final String aU = "notify";
        public static final String aV = "logged";
        public static final String aW = "notification";
        public static final String aX = "latitude";
        public static final String aY = "longitude";
        public static final String aa = "additonal_version";
        public static final String ab = "com.bgjd.ici.action.START_EULA";
        public static final String ac = "com.bgjd.ici.action.VERIFY";
        public static final String ad = "com.bgjd.ici.action.FAILED";
        public static final String ae = "com.bgjd.ici.action.VERIFIED";
        public static final String af = "com.bgjd.ici.action.START";
        public static final String ag = "com.bgjd.ici.action.NOTIFICATION";
        public static final String ah = "com.bgjd.ici.action.PARTNER_REGISTRATION_START";
        public static final String ai = "com.android.browser.permission.READ_HISTORY_BOOKMARKS";
        public static final String aj = "packages";
        public static final String ak = "packagefilter";
        public static final String al = "systemfilter";
        public static final String am = "browsing";
        public static final Uri an = Uri.parse("content://browser/bookmarks");
        public static final Uri ao = Uri.parse("content://com.android.chrome.browser/bookmarks");
        public static final String ap = "id";
        public static final String aq = "name";
        public static final String ar = "url";
        public static final String as = "bookmark";
        public static final String at = "created";
        public static final String au = "date";
        public static final String av = "eula_type";
        public static final String aw = "activity";
        public static final String ax = "dialog";
        public static final String ay = "default";
        public static final String az = "accept_id";
        public static final String f2124b = "WIFI";
        public static final String f2125c = "WIMAX";
        public static final String f2126d = "MOBILE";
        public static final String f2127e = "1xRTT";
        public static final String f2128f = "CDMA";
        public static final String f2129g = "EDGE";
        public static final String f2130h = "EHRPD";
        public static final String f2131i = "EVDO_0";
        public static final String f2132j = "EVDO_A";
        public static final String f2133k = "EVDO_B";
        public static final String f2134l = "GPRS";
        public static final String f2135m = "HSDPA";
        public static final String f2136n = "HSPA";
        public static final String f2137o = "HSPAP";
        public static final String f2138p = "HSUPA";
        public static final String f2139q = "IDEN";
        public static final String f2140r = "LTE";
        public static final String f2141s = "UMTS";
        public static final String f2142t = "Mozilla/5.0 (Linux; U; Android %1$s; %2$s; %3$s Build/%4$s) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";
        public static final String f2143u = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        public static final String f2144v = "android.os.SystemProperties";
        public static final String f2145w = "ro.cdma.home.operator.numeric";
        public static final String f2146x = "gsm.sim.operator.numeric";
        public static final String f2147y = "appid";
        public static final String f2148z = "uid";
    }

    public static final class C1407c {
        public static final String f2161a = "package";
        public static final String f2162b = "browsing";
        public static final String f2163c = "email";
        public static final String f2164d = "beacon";
        public static final String f2165e = "ptrlog";
        public static final String f2166f = "location";
        public static final String f2167g = "plugins";

        public static final class C1405a {
            public static final String f2149a = "uuids";
            public static final String f2150b = "layout";
        }

        public static final class C1406b {
            public static final String f2151a = "pkg";
            public static final String f2152b = "brw";
            public static final String f2153c = "act";
            public static final String f2154d = "beac";
            public static final String f2155e = "ptrlog";
            public static final String f2156f = "loc";
            public static final String f2157g = "plg";
            public static final String f2158h = "bat";
            public static final String f2159i = "procs";
            public static final String f2160j = "prop";
        }
    }
}
