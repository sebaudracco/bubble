package com.areametrics.areametricssdk;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.Log;
import com.appnext.base.p023b.C1042c;
import com.appnext.base.p023b.C1057k;
import com.areametrics.areametricssdk.C1343b.C1342a;
import com.areametrics.areametricssdk.C1381h.C1380a;
import com.coremedia.iso.boxes.UserBox;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import com.stepleaderdigital.reveal.Reveal;
import cz.msebera.android.httpclient.HttpStatus;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class C1378g {
    private static final String f1988d = ("AMS-" + C1378g.class.getSimpleName());
    int f1989a;
    int f1990b;
    boolean f1991c = false;
    private Context f1992e;
    private C1381h f1993f;
    private AreaMetricsSDK f1994g;

    interface C1352c {
        void mo2159a(Map<String, String> map);
    }

    enum C1376a {
        ;
        
        public static final int f1979a = 0;
        public static final int f1980b = 0;
        public static final int f1981c = 0;

        static {
            f1979a = 1;
            f1980b = 2;
            f1981c = 3;
            f1982d = new int[]{f1979a, f1980b, f1981c};
        }
    }

    private class C1377b extends AsyncTask<Void, Void, Address> {
        Location f1983a;
        String f1984b;
        C1352c f1985c;
        Geocoder f1986d;
        final /* synthetic */ C1378g f1987e;

        C1377b(C1378g c1378g, Location location, String str, C1352c c1352c) {
            this.f1987e = c1378g;
            this.f1983a = location;
            this.f1984b = str;
            this.f1985c = c1352c;
        }

        private Address m2570a() {
            try {
                List fromLocation = this.f1986d.getFromLocation(this.f1983a.getLatitude(), this.f1983a.getLongitude(), 1);
                return fromLocation.size() > 0 ? (Address) fromLocation.get(0) : null;
            } catch (Exception e) {
                return null;
            }
        }

        protected final /* synthetic */ Object doInBackground(Object[] objArr) {
            return m2570a();
        }

        protected final /* synthetic */ void onPostExecute(Object obj) {
            Address address = (Address) obj;
            super.onPostExecute(address);
            Map hashMap = new HashMap();
            if (address == null) {
                String country = this.f1987e.m2629n().getResources().getConfiguration().locale.getCountry();
                if (country != null && country.length() > 0) {
                    hashMap.put("country", country);
                }
                this.f1985c.mo2159a(hashMap);
                return;
            }
            if (address.getPostalCode() != null) {
                hashMap.put("zip", address.getPostalCode());
            }
            if (address.getLocality() != null) {
                hashMap.put("city", address.getLocality());
            }
            if (address.getAdminArea() != null) {
                hashMap.put("state", C1378g.m2581i(address.getAdminArea()));
            }
            if (address.getCountryCode() != null) {
                hashMap.put("country", address.getCountryCode());
            }
            Editor edit = this.f1987e.m2629n().getSharedPreferences("AMS_SDK_LOCAL", 0).edit();
            if (hashMap.containsKey("zip")) {
                edit.putString("AMS_ZIP", (String) hashMap.get("zip"));
            } else {
                edit.remove("AMS_ZIP");
            }
            if (hashMap.containsKey("city")) {
                edit.putString("AMS_CITY", (String) hashMap.get("city"));
            } else {
                edit.remove("AMS_CITY");
            }
            if (hashMap.containsKey("state")) {
                edit.putString("AMS_STATE", (String) hashMap.get("state"));
            } else {
                edit.remove("AMS_STATE");
            }
            if (hashMap.containsKey("country")) {
                edit.putString("AMS_COUNTRY", (String) hashMap.get("country"));
            } else {
                edit.remove("AMS_COUNTRY");
            }
            edit.putString("AMS_INPUT_COORD", String.valueOf(this.f1983a.getLatitude()) + "," + String.valueOf(this.f1983a.getLongitude()));
            edit.apply();
            this.f1985c.mo2159a(hashMap);
            if (this.f1984b != null && hashMap.containsKey("country") && !this.f1984b.equals(hashMap.get("country"))) {
                C1381h b = this.f1987e.m2630o();
                if (b.m2641c() != null) {
                    b.m2641c().m2534h();
                }
            }
        }

        protected final void onPreExecute() {
            super.onPreExecute();
            this.f1986d = new Geocoder(this.f1987e.m2629n(), Locale.US);
        }
    }

    C1378g() {
    }

    private void m2573a(String str, String str2, String str3) {
        int i = 1;
        SharedPreferences sharedPreferences = m2629n().getSharedPreferences("AMS_User_Data", 0);
        Editor edit = sharedPreferences.edit();
        String string = sharedPreferences.getString(C1378g.m2578e(str, str2), null);
        String f = C1378g.m2579f(str, str2);
        String string2 = sharedPreferences.getString(f, null);
        if (string2 == null) {
            if (!C1378g.m2580g(str3, string).booleanValue()) {
                if (str3 != null || string == null) {
                    edit.putString(f, str3);
                } else {
                    edit.putString(f, "AMS_VOID_VALUE");
                }
            }
            i = 0;
        } else if (C1378g.m2580g(str3, string).booleanValue()) {
            edit.remove(f);
        } else {
            if (!C1378g.m2580g(str3, string2).booleanValue()) {
                if (str3 == null) {
                    edit.putString(f, "AMS_VOID_VALUE");
                } else {
                    edit.putString(f, str3);
                }
            }
            i = 0;
        }
        edit.apply();
        if (i != 0) {
            m2630o().m2640b(C1380a.f1999d);
        }
    }

    private static long m2576c(long j) {
        if (j < 10) {
            return 10;
        }
        double pow = Math.pow(10.0d, Math.floor(Math.log10((double) j)));
        return (long) (((double) j) % pow > pow / 2.0d ? (((double) j) + pow) - (((double) j) % pow) : ((double) j) - (((double) j) % pow));
    }

    private static long m2577d(long j) {
        long j2 = j % 5 > 2 ? (j + 5) - (j % 5) : j - (j % 5);
        return j2 > 0 ? j2 : 5;
    }

    static String m2578e(String str, String str2) {
        return str + "~~~AMS~~~" + str2;
    }

    static String m2579f(String str, String str2) {
        return "AMS_DIRTY_SHARED_PREFS_PREFIX:" + str + "~~~AMS~~~" + str2;
    }

    private static Boolean m2580g(String str, String str2) {
        boolean z = !(str == null || str2 == null || !str.equals(str2)) || (str == null && str2 == null);
        return Boolean.valueOf(z);
    }

    static /* synthetic */ String m2581i(String str) {
        Map hashMap = new HashMap();
        hashMap.put("Alabama", "AL");
        hashMap.put("Alaska", "AK");
        hashMap.put("Alberta", "AB");
        hashMap.put("American Samoa", "AS");
        hashMap.put("Arizona", "AZ");
        hashMap.put("Arkansas", "AR");
        hashMap.put("Armed Forces (AE)", "AE");
        hashMap.put("Armed Forces Americas", "AA");
        hashMap.put("Armed Forces Pacific", "AP");
        hashMap.put("British Columbia", "BC");
        hashMap.put("California", "CA");
        hashMap.put("Colorado", "CO");
        hashMap.put("Connecticut", "CT");
        hashMap.put("Delaware", "DE");
        hashMap.put("District Of Columbia", "DC");
        hashMap.put("Florida", "FL");
        hashMap.put("Georgia", "GA");
        hashMap.put("Guam", "GU");
        hashMap.put("Hawaii", "HI");
        hashMap.put("Idaho", SchemaSymbols.ATTVAL_ID);
        hashMap.put("Illinois", "IL");
        hashMap.put("Indiana", "IN");
        hashMap.put("Iowa", "IA");
        hashMap.put("Kansas", "KS");
        hashMap.put("Kentucky", "KY");
        hashMap.put("Louisiana", "LA");
        hashMap.put("Maine", "ME");
        hashMap.put("Manitoba", "MB");
        hashMap.put("Maryland", "MD");
        hashMap.put("Massachusetts", "MA");
        hashMap.put("Michigan", "MI");
        hashMap.put("Minnesota", "MN");
        hashMap.put("Mississippi", "MS");
        hashMap.put("Missouri", "MO");
        hashMap.put("Montana", "MT");
        hashMap.put("Nebraska", "NE");
        hashMap.put("Nevada", "NV");
        hashMap.put("New Brunswick", "NB");
        hashMap.put("New Hampshire", "NH");
        hashMap.put("New Jersey", "NJ");
        hashMap.put("New Mexico", "NM");
        hashMap.put("New York", "NY");
        hashMap.put("Newfoundland", "NF");
        hashMap.put("North Carolina", "NC");
        hashMap.put("North Dakota", "ND");
        hashMap.put("Northwest Territories", "NT");
        hashMap.put("Nova Scotia", "NS");
        hashMap.put("Nunavut", "NU");
        hashMap.put("Ohio", "OH");
        hashMap.put("Oklahoma", "OK");
        hashMap.put("Ontario", "ON");
        hashMap.put("Oregon", "OR");
        hashMap.put("Pennsylvania", "PA");
        hashMap.put("Prince Edward Island", "PE");
        hashMap.put("Puerto Rico", "PR");
        hashMap.put("Quebec", "PQ");
        hashMap.put("Rhode Island", "RI");
        hashMap.put("Saskatchewan", "SK");
        hashMap.put("South Carolina", "SC");
        hashMap.put("South Dakota", "SD");
        hashMap.put("Tennessee", "TN");
        hashMap.put("Texas", "TX");
        hashMap.put("Utah", "UT");
        hashMap.put("Vermont", "VT");
        hashMap.put("Virgin Islands", "VI");
        hashMap.put("Virginia", "VA");
        hashMap.put("Washington", "WA");
        hashMap.put("West Virginia", "WV");
        hashMap.put("Wisconsin", "WI");
        hashMap.put("Wyoming", "WY");
        hashMap.put("Yukon Territory", "YT");
        return hashMap.get(str) != null ? (String) hashMap.get(str) : str;
    }

    static String m2582j() {
        return Build.MANUFACTURER + " " + Build.MODEL;
    }

    private void m2583j(String str) {
        String k = C1378g.m2584k(str);
        String l = C1378g.m2585l(str);
        String m = C1378g.m2586m(str);
        m2616d("email_md5", k);
        m2616d("email_sha1", l);
        m2616d("email_sha256", m);
    }

    private static String m2584k(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes("UTF-8"));
            byte[] digest = instance.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : digest) {
                stringBuilder.append(Integer.toString((b & 255) + 256, 16).substring(1));
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            return null;
        }
    }

    private static String m2585l(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(str.getBytes("UTF-8"));
            byte[] digest = instance.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : digest) {
                stringBuilder.append(Integer.toString((b & 255) + 256, 16).substring(1));
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            return null;
        }
    }

    private static String m2586m(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.update(str.getBytes("UTF-8"));
            byte[] digest = instance.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : digest) {
                stringBuilder.append(Integer.toString((b & 255) + 256, 16).substring(1));
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            return null;
        }
    }

    private static boolean m2587n(String str) {
        String toLowerCase = str.toLowerCase(Locale.US);
        return toLowerCase.equals("organizations") || toLowerCase.equals("socialprofiles") || toLowerCase.equals("scores");
    }

    private String m2588p() {
        try {
            PackageInfo packageInfo = m2629n().getPackageManager().getPackageInfo(m2629n().getPackageName(), 0);
            return (packageInfo.versionName != null ? packageInfo.versionName : "") + "_(" + String.valueOf(packageInfo.versionCode) + ")";
        } catch (Exception e) {
            Log.e(f1988d, "Getting package info for app version check failed: " + e.getMessage());
            return null;
        }
    }

    private Boolean m2589q() {
        if (m2629n().getSharedPreferences("AMS_User_Data", 0).getString(C1378g.m2578e("env_settings", "new_user"), null) != null) {
            return Boolean.valueOf(false);
        }
        try {
            PackageInfo packageInfo = m2629n().getPackageManager().getPackageInfo(m2629n().getPackageName(), 0);
            return Boolean.valueOf(packageInfo.lastUpdateTime < packageInfo.firstInstallTime + 120000);
        } catch (Exception e) {
            Log.e(f1988d, "Getting package info for new user status check failed");
            return Boolean.valueOf(false);
        }
    }

    private AreaMetricsSDK m2590r() {
        return this.f1994g != null ? this.f1994g : AreaMetricsSDK.INSTANCE;
    }

    private void m2591s() {
        if (m2590r().delegate != null) {
            m2606b("Sharing mock persona data with delegate!");
            m2590r().delegate.didGeneratePersona(C1378g.m2592t());
            return;
        }
        m2606b("Unable to share mock persona, delegate property unassigned!");
    }

    private static JSONObject m2592t() {
        try {
            return new JSONObject("{'likelihood':0.87,'demographics':{'gender':'Male','age':'47','ageRange':'45-54','locationGeneral':'Honolulu','locationDeduced':{'normalizedLocation':'Honolulu','deducedLocation':'Honolulu, Hawaii, United States','city':{'name':'Honolulu'},'state':{'name':'Hawaii','code':'HI'},'country':{'name':'United States','code':'US'},'continent':{'name':'North America'},'county':{'name':'Honolulu','code':'Honolulu'}}},'socialProfiles':[{'followers':5000,'type':'angellist'},{'type':'twitter'},{'type':'facebook'},{'type':'foursquare'},{'followers':150,'following':140,'type':'pinterest'},{'type':'google'},{'type':'klout'},{'followers':500,'following':500,'type':'linkedin'},{'type':'aboutme'}],'digitalFootprint':{'scores':[{'provider':'klout','value':50}],'topics':[{'value':'Business'},{'value':'Startups'},{'value':'Investor'}]},'areametrics':{'interests':[{'name':'Cars','id':'5457c811d4ac147c2bf5df55','score':0.2,'parents':['55afc38892cffb786d83f985'],'category':'default'},{'name':'Luxury vehicles','id':'5457c811d4ac147c2bf5df59','score':0.2,'parents':['5457c811d4ac147c2bf5df55'],'category':'default'},{'name':'Business news','id':'5457c85ad4ac147c59c0ca4f','score':1,'parents':['55afc38492cffb786d83f7d0','55afc38392cffb786d83f797'],'category':'default'},{'name':'Entrepreneurship','id':'5457c85ad4ac147c59c0ca52','score':1,'parents':['55afc38492cffb786d83f7d0'],'category':'default'},{'name':'Marketing','id':'5457c85ad4ac147c59c0ca58','score':0.2,'parents':['55afc38492cffb786d83f7d0'],'category':'default'},{'name':'Small and medium business','id':'5457c85ad4ac147c59c0ca5b','score':0.4,'parents':['5457c85ad4ac147c59c0ca52'],'category':'default'},{'name':'Gaming','id':'5457c85ad4ac147c716f5d67','score':0.2,'category':'default'},{'name':'Reality shows','id':'5457c85bd4ac147c89d8be03','score':0.4,'parents':['54613f71d4ac14770d4e9f8c'],'category':'default'},{'name':'Talk radio','id':'5457c85bd4ac147c8dd12715','score':0.4,'parents':['55afc38792cffb786d83f900'],'category':'default'},{'name':'Financial markets and news','id':'5457c85bd4ac147c91c96556','score':0.4,'parents':['55afc38892cffb786d83f94c'],'category':'default'},{'name':'Wealth and asset management','id':'5457c85bd4ac147c91c9655a','score':0.2,'parents':['55afc38492cffb786d83f7d0','55afc38892cffb786d83f94c'],'category':'default'},{'name':'Dogs','id':'5457c85bd4ac147c950ef95a','score':0.2,'parents':['54f65944d4ac14708e560729'],'category':'default'},{'name':'Science','id':'5457c85bd4ac147c99d3f490','score':1,'category':'default'},{'name':'Space and astronomy','id':'5457c85bd4ac147c99d3f491','score':0.8,'parents':['5457c85bd4ac147c99d3f490'],'category':'default'},{'name':'Mobile devices','id':'5457c85bd4ac147cabc75c73','score':0.2,'parents':['55afc38592cffb786d83f856'],'category':'default'},{'name':'Startups','id':'5457c85bd4ac147cabc75c78','score':1,'parents':['5457c85ad4ac147c59c0ca52'],'category':'default'},{'name':'Technology','id':'5457c85bd4ac147cabc75c7a','score':1,'category':'default'},{'name':'Hawaii','id':'5457c85cd4ac147caf35b7b2','score':1,'parents':['55afc38592cffb786d83f83e'],'category':'place'},{'name':'Cryptocurrency','id':'54601629d4ac1482f7849f4c','score':0.2,'parents':['5457c85bd4ac147cabc75c7a'],'category':'default'},{'name':'Apple','id':'546129ded4ac14770d4e9f1c','score':0.2,'parents':['5457c85bd4ac147cabc75c7a'],'category':'brand'},{'name':'Television','id':'54613f71d4ac14770d4e9f8c','score':0.4,'parents':['55afc38692cffb786d83f8a9'],'category':'default'},{'name':'Pets','id':'54f65944d4ac14708e560729','score':0.2,'category':'default'},{'name':'The Profit','id':'55525e84d2729a57db8ec373','score':0.4,'parents':['5457c85bd4ac147c89d8be03','5457c85ad4ac147c59c0ca5b','55afc38592cffb786d83f872'],'category':'show'},{'name':'News','id':'55afc38392cffb786d83f797','score':1,'category':'default'},{'name':'Technology expos','id':'55afc38392cffb786d83f7a2','score':0.2,'parents':['55afc38592cffb786d83f878'],'category':'default'},{'name':'Publications','id':'55afc38492cffb786d83f7c2','score':0.8,'parents':['55afc38492cffb786d83f7e8'],'category':'default'},{'name':'Google','id':'55afc38492cffb786d83f7c9','score':0.6,'parents':['5457c85bd4ac147cabc75c7a'],'category':'brand'},{'name':'Business and careers','id':'55afc38492cffb786d83f7d0','score':1,'category':'default'},{'name':'Books and publications','id':'55afc38492cffb786d83f7e8','score':0.8,'category':'default'},{'name':'Banks','id':'55afc38492cffb786d83f7f2','score':0.2,'parents':['55afc38892cffb786d83f94c'],'category':'default'},{'name':'Startup accelerators','id':'55afc38492cffb786d83f7fe','score':0.8,'parents':['5457c85bd4ac147cabc75c78'],'category':'default'},{'name':'United States','id':'55afc38592cffb786d83f83e','score':1,'parents':['55afc38892cffb786d83f94e'],'category':'place'},{'name':'Venture capital','id':'55afc38592cffb786d83f852','score':1,'parents':['5457c85ad4ac147c59c0ca52'],'category':'default'},{'name':'Hardware','id':'55afc38592cffb786d83f856','score':0.2,'parents':['5457c85bd4ac147cabc75c7a'],'category':'default'},{'name':'CNBC','id':'55afc38592cffb786d83f872','score':0.4,'parents':['5457c85ad4ac147c59c0ca4f','54613f71d4ac14770d4e9f8c'],'category':'publisher'},{'name':'Technology events','id':'55afc38592cffb786d83f878','score':0.2,'parents':['5457c85bd4ac147cabc75c7a'],'category':'default'},{'name':'Society','id':'55afc38692cffb786d83f891','score':0.2,'category':'default'},{'name':'Television and film','id':'55afc38692cffb786d83f8a9','score':0.4,'category':'default'},{'name':'Supermarkets','id':'55afc38692cffb786d83f8b6','score':0.2,'parents':['55afc38792cffb786d83f91a'],'category':'default'},{'name':'Civil rights','id':'55afc38692cffb786d83f8da','score':0.2,'parents':['55afc38692cffb786d83f891','55afc38792cffb786d83f924'],'category':'default'},{'name':'Hobbies and interests','id':'55afc38792cffb786d83f900','score':0.6,'category':'default'},{'name':'Food and drink','id':'55afc38792cffb786d83f91a','score':0.2,'category':'default'},{'name':'Whole Foods Market','id':'55afc38792cffb786d83f91d','score':0.2,'parents':['55afc38692cffb786d83f8b6'],'category':'brand'},{'name':'Causes and activism','id':'55afc38792cffb786d83f924','score':0.2,'category':'default'},{'name':'National Public Radio','id':'55afc38892cffb786d83f93c','score':0.2,'parents':['5457c85bd4ac147c8dd12715'],'category':'publisher'},{'name':'Mercedes-Benz','id':'55afc38892cffb786d83f944','score':0.2,'parents':['5457c811d4ac147c2bf5df59'],'category':'brand'},{'name':'Personal finance','id':'55afc38892cffb786d83f94c','score':0.6,'category':'default'},{'name':'Travel destinations','id':'55afc38892cffb786d83f94e','score':1,'category':'default'},{'name':'Automotive','id':'55afc38892cffb786d83f985','score':0.2,'category':'default'},{'name':'Social media','id':'55b3c98492cffb11604e584a','score':0.4,'category':'default'},{'name':'Business Insider','id':'55b3fa6292cffb17bfdc17cb','score':0.2,'parents':['5457c85ad4ac147c59c0ca4f'],'category':'publisher'},{'name':'Huffington Post','id':'55b51f7f92cffb34f9a24cef','score':0.2,'parents':['55afc38392cffb786d83f797'],'category':'publisher'},{'name':'Podcasts','id':'55b550af92cffb3a021df422','score':0.2,'parents':['5457c85bd4ac147c8dd12715'],'category':'default'},{'name':'The Wall Street Journal','id':'55b5593492cffb3b194d1379','score':0.2,'parents':['5457c85ad4ac147c59c0ca4f','55afc38492cffb786d83f7c2'],'category':'publisher'},{'name':'Social media analytics','id':'55be4e3992cffb7c68a4b857','score':0.4,'parents':['55b3c98492cffb11604e584a'],'category':'default'},{'name':'AngelList','id':'55be4e9692cffb7c68a4b85a','score':0.2,'parents':['5457c85bd4ac147cabc75c78'],'category':'community'},{'name':'SocialBro','id':'55be51ec92cffb7c68a4b85e','score':0.2,'parents':['55be4e3992cffb7c68a4b857'],'category':'brand'},{'name':'The Verge','id':'55bfa87e92cffb600f1b5709','score':0.2,'parents':['5457c85bd4ac147cabc75c7a'],'category':'publisher'},{'name':'Inc.','id':'55d4bc3e92cffb43fe4a73cf','score':0.2,'parents':['5457c85ad4ac147c59c0ca4f','5457c85ad4ac147c59c0ca52','5457c85bd4ac147cabc75c78'],'category':'publisher'},{'name':'Forbes','id':'55d4c29f92cffb4f7de7cc0f','score':0.2,'parents':['5457c85ad4ac147c59c0ca4f'],'category':'publisher'},{'name':'WIRED Magazine','id':'55d4c94e92cffb634a6f54c2','score':0.2,'parents':['5457c85bd4ac147cabc75c7a','55afc38492cffb786d83f7c2'],'category':'publisher'},{'name':'VentureBeat','id':'55d6077b92cffb42e913053b','score':0.2,'parents':['5457c85bd4ac147cabc75c7a','5457c85ad4ac147c59c0ca4f'],'category':'publisher'},{'name':'Mashable','id':'55da524992cffb3a95d49c6a','score':0.2,'parents':['55afc38392cffb786d83f797'],'category':'publisher'},{'name':'The New York Times','id':'55da538092cffb3a95d49c84','score':0.2,'parents':['55afc38492cffb786d83f7c2','55afc38392cffb786d83f797'],'category':'publisher'},{'name':'Gizmodo','id':'55da54b392cffb3a95d49ca7','score':0.2,'parents':['5457c85bd4ac147cabc75c7a'],'category':'publisher'},{'name':'ad:tech','id':'55db379c92cffb6958898835','score':0.2,'parents':['5457c85ad4ac147c59c0ca58','55afc38392cffb786d83f7a2'],'category':'event'},{'name':'The Guardian','id':'55db390992cffb6958898847','score':0.2,'parents':['55afc38392cffb786d83f797'],'category':'publisher'},{'name':'NASA','id':'55db691f92cffb21fa9d5b37','score':0.6,'parents':['5457c85bd4ac147c99d3f491'],'category':'nonprofit'},{'name':'SpaceX','id':'55db6abe92cffb22e186777e','score':0.2,'parents':['5457c85bd4ac147c99d3f491'],'category':'brand'},{'name':'Entrepreneur Magazine','id':'55de0d5392cffb246db212a3','score':0.2,'parents':['55afc38492cffb786d83f7c2','5457c85ad4ac147c59c0ca52'],'category':'publisher'},{'name':'Google Ventures','id':'55e2048192cffb4aba02df67','score':0.2,'parents':['55afc38592cffb786d83f852','55afc38492cffb786d83f7c9'],'category':'brand'},{'name':'Sequoia Capital','id':'55e2049c92cffb4aba02df6c','score':0.2,'parents':['55afc38592cffb786d83f852'],'category':'brand'},{'name':'Andreessen Horowitz','id':'55e204b392cffb4aba02df70','score':0.2,'parents':['55afc38592cffb786d83f852'],'category':'brand'},{'name':'Greylock Partners','id':'55e2055292cffb4aba02df82','score':0.2,'parents':['55afc38592cffb786d83f852'],'category':'brand'},{'name':'Union Square Ventures','id':'55e205d192cffb4aba02df92','score':0.2,'parents':['55afc38592cffb786d83f852'],'category':'brand'},{'name':'500 Startups','id':'55e2066992cffb4aba02dfa1','score':0.2,'parents':['55afc38492cffb786d83f7fe'],'category':'brand'},{'name':'Y Combinator','id':'55e206f392cffb7789ee2957','score':0.6,'parents':['55afc38492cffb786d83f7fe'],'category':'brand'},{'name':'Elon Musk','id':'55f3493b92cffb4557864641','score':0.4,'parents':['5457c85bd4ac147cabc75c7a'],'category':'celebrity'},{'name':'PandoDaily','id':'55f46ba192cffb26ca036526','score':0.2,'parents':['5457c85bd4ac147cabc75c7a'],'category':'publisher'},{'name':'The Intercept','id':'55fafd5992cffb6a1ab8b082','score':0.2,'parents':['55afc38392cffb786d83f797'],'category':'publisher'},{'name':'Fred Wilson','id':'55ff119092cffb402246af83','score':0.2,'parents':['55e205d192cffb4aba02df92'],'category':'celebrity'},{'name':'Bill Gurley','id':'55ff126c92cffb402246af8f','score':0.2,'parents':['55afc38592cffb786d83f852'],'category':'celebrity'},{'name':'Brad Feld','id':'55ff16a392cffb402246afa0','score':0.4,'parents':['55afc38592cffb786d83f852'],'category':'celebrity'},{'name':'Marc Andreessen','id':'55ff178592cffb402246afa9','score':0.4,'parents':['55afc38592cffb786d83f852'],'category':'celebrity'},{'name':'Mark Cuban','id':'5601a91192cffb50776593a5','score':0.2,'parents':['5457c85ad4ac147c59c0ca52'],'category':'celebrity'},{'name':'Marissa Mayer','id':'5602cd3392cffb5b7ea6c74d','score':0.2,'parents':['5457c85bd4ac147cabc75c7a'],'category':'celebrity'},{'name':'Chris Dixon','id':'56044f4f92cffb7b7462ef74','score':0.2,'parents':['55afc38592cffb786d83f852'],'category':'celebrity'},{'name':'Paul Graham','id':'560591f492cffb6b4bf16d68','score':0.2,'parents':['55e206f392cffb7789ee2957'],'category':'celebrity'},{'name':'Eric Schmidt','id':'56070fdc92cffb4837048c49','score':0.2,'parents':['55afc38492cffb786d83f7c9'],'category':'celebrity'},{'name':'Tim Cook','id':'5617ecca92cffb7570539152','score':0.2,'parents':['546129ded4ac14770d4e9f1c'],'category':'celebrity'},{'name':'Edward Snowden','id':'561e78ef92cffb21cdbbbb78','score':0.2,'parents':['55afc38692cffb786d83f8da'],'category':'celebrity'},{'name':'Neil deGrasse Tyson','id':'5635519e92cffb62175d3fc1','score':0.2,'parents':['5457c85bd4ac147c99d3f490'],'category':'celebrity'},{'name':'Mark Suster','id':'5636903792cffb62175d45f3','score':0.2,'parents':['55afc38592cffb786d83f852'],'category':'celebrity'},{'name':'Bill Nye','id':'563789f192cffb62175d489f','score':0.2,'parents':['5457c85bd4ac147c99d3f490'],'category':'celebrity'},{'name':'Investment banking','id':'564b666b92cffb6bcb0a7560','score':0.2,'parents':['5457c85bd4ac147c91c9655a','55afc38492cffb786d83f7f2'],'category':'default'},{'name':'Goldman Sachs','id':'564b668792cffb6bcb0a7563','score':0.2,'parents':['564b666b92cffb6bcb0a7560'],'category':'brand'},{'name':'Serial','id':'5660b03a92cffb68f5324c59','score':0.2,'parents':['55b550af92cffb3a021df422'],'category':'publisher'},{'name':'T-Mobile US','id':'56a6c02392cffb338344099f','score':0.2,'parents':['5457c85bd4ac147cabc75c73'],'category':'brand'},{'name':'Markus Persson','id':'56fdab0692cffb31f66f9798','score':0.2,'parents':['5457c85ad4ac147c716f5d67'],'category':'celebrity'}]}}".replace("'", "\""));
        } catch (JSONException e) {
            return null;
        }
    }

    final JSONObject m2593a() {
        String string = m2629n().getSharedPreferences("AMS_FC_SHARED_PERSONA", 0).getString("shared_persona", null);
        if (string != null) {
            try {
                return new JSONObject(string);
            } catch (JSONException e) {
                Log.e(f1988d, "getSharedPersonaException: " + e.getMessage());
            }
        }
        return null;
    }

    final JSONObject m2594a(JSONObject jSONObject, boolean z) {
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            Object opt = jSONObject.opt(str);
            if (opt instanceof JSONObject) {
                try {
                    jSONObject.putOpt(str, m2594a((JSONObject) opt, C1378g.m2587n(str)));
                } catch (JSONException e) {
                    jSONObject.remove(str);
                }
            } else if (opt instanceof JSONArray) {
                boolean n = C1378g.m2587n(str);
                JSONArray jSONArray = (JSONArray) opt;
                for (int i = 0; i < jSONArray.length(); i++) {
                    Object opt2 = jSONArray.opt(i);
                    if (opt2 instanceof JSONObject) {
                        try {
                            jSONArray.put(i, m2594a((JSONObject) opt2, n));
                        } catch (JSONException e2) {
                            jSONObject.remove(str);
                        }
                    } else if (n) {
                        try {
                            jSONArray.put(i, "AMS_REDACTED");
                            this.f1989a++;
                        } catch (JSONException e3) {
                            jSONObject.remove(str);
                        }
                    } else {
                        continue;
                    }
                }
                try {
                    jSONObject.put(str, jSONArray);
                } catch (JSONException e4) {
                    jSONObject.remove(str);
                }
            } else {
                String toLowerCase = str.toLowerCase(Locale.US);
                if (toLowerCase.equals("url") || toLowerCase.equals("bio") || toLowerCase.equals("rss") || toLowerCase.equals("username") || toLowerCase.equals("userid") || toLowerCase.equals("fullname") || toLowerCase.equals("familyname") || toLowerCase.equals("givenname") || toLowerCase.equals("title") || toLowerCase.equals("handle") || toLowerCase.equals("requestid")) {
                    opt = 1;
                } else if (z && (toLowerCase.equals("name") || toLowerCase.equals("id"))) {
                    int i2 = 1;
                } else {
                    opt = null;
                }
                if (opt != null) {
                    try {
                        jSONObject.put(str, "AMS_REDACTED");
                        this.f1989a++;
                    } catch (JSONException e5) {
                        jSONObject.remove(str);
                    }
                } else if (str.equals("followers") || str.equals("following")) {
                    try {
                        jSONObject.put(str, C1378g.m2576c(jSONObject.optLong(str)));
                        this.f1990b++;
                    } catch (JSONException e6) {
                        jSONObject.remove(str);
                    }
                } else if (z && str.equals(FirebaseAnalytics$Param.VALUE)) {
                    try {
                        jSONObject.put(str, C1378g.m2577d(jSONObject.optLong(str)));
                        this.f1990b++;
                    } catch (JSONException e7) {
                        jSONObject.remove(str);
                    }
                }
            }
        }
        return jSONObject;
    }

    final void m2595a(int i) {
        if (i >= 0 && i <= C1057k.kA) {
            Editor edit = m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
            edit.putInt("fg_bscan_period", i);
            edit.apply();
            m2612c("fg_bscan_period", Integer.toString(i));
            if (m2590r().getBeaconTracker() != null) {
                m2590r().getBeaconTracker().m2559a((long) i);
            }
        }
    }

    final void m2596a(long j) {
        Editor edit = m2629n().getSharedPreferences("AMS_FC_PERSONA_WAIT", 0).edit();
        edit.putLong("wait", j);
        edit.apply();
    }

    final void m2597a(Location location, C1352c c1352c) {
        SharedPreferences sharedPreferences = m2629n().getSharedPreferences("AMS_SDK_LOCAL", 0);
        final String string = sharedPreferences.getString("AMS_ZIP", null);
        final String string2 = sharedPreferences.getString("AMS_CITY", null);
        final String string3 = sharedPreferences.getString("AMS_STATE", null);
        final String string4 = sharedPreferences.getString("AMS_COUNTRY", null);
        final String string5 = sharedPreferences.getString("AMS_INPUT_COORD", null);
        final Location location2 = location;
        final C1352c c1352c2 = c1352c;
        m2590r().getLocationChangeListener().m2444a(new C1342a(this) {
            final /* synthetic */ C1378g f1978h;

            public final void mo2164a(Location location) {
                if (location2 != null) {
                    location = location2;
                }
                Map hashMap = new HashMap();
                if (string5 != null && string5.length() > 0) {
                    String[] split = string5.split(",");
                    Location location2 = new Location("prevInputLoc");
                    location2.setLatitude(Double.valueOf(split[0]).doubleValue());
                    location2.setLongitude(Double.valueOf(split[1]).doubleValue());
                    if (location == null || location.distanceTo(location2) < this.f1978h.m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getFloat("geo_cache_dist", 20000.0f)) {
                        if (string != null && string.length() > 0) {
                            hashMap.put("zip", string);
                        }
                        if (string2 != null && string2.length() > 0) {
                            hashMap.put("city", string2);
                        }
                        if (string3 != null && string3.length() > 0) {
                            hashMap.put("state", string3);
                        }
                        if (string4 != null && string4.length() > 0) {
                            hashMap.put("country", string4);
                            if (this.f1978h.m2624h(string4)) {
                                hashMap.put("blankData", SchemaSymbols.ATTVAL_TRUE_1);
                            }
                        }
                        c1352c2.mo2159a(hashMap);
                        return;
                    }
                    new C1377b(this.f1978h, location, string4, c1352c2).execute(new Void[0]);
                } else if (location != null) {
                    new C1377b(this.f1978h, location, string4, c1352c2).execute(new Void[0]);
                } else {
                    String country = this.f1978h.m2629n().getResources().getConfiguration().locale.getCountry();
                    if (country != null && country.length() > 0) {
                        hashMap.put("country", country);
                    }
                    c1352c2.mo2159a(hashMap);
                }
            }
        });
    }

    final void m2598a(String str) {
        if (!this.f1991c) {
            Log.i("AMSDK", str);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final void m2599a(java.lang.String r6, java.lang.String r7) {
        /*
        r5 = this;
        r0 = 0;
        r4 = 0;
        r1 = r5.m2629n();
        r2 = "AMS_FC_PARAM_STORE";
        r2 = r1.getSharedPreferences(r2, r0);
        r1 = -1;
        r3 = r6.hashCode();
        switch(r3) {
            case -916346253: goto L_0x004b;
            case 96619420: goto L_0x0020;
            case 106642798: goto L_0x0056;
            case 216948778: goto L_0x0040;
            case 1318660040: goto L_0x0035;
            case 2120741467: goto L_0x002a;
            default: goto L_0x0015;
        };
    L_0x0015:
        r0 = r1;
    L_0x0016:
        switch(r0) {
            case 0: goto L_0x0061;
            case 1: goto L_0x00d2;
            case 2: goto L_0x0133;
            case 3: goto L_0x0157;
            case 4: goto L_0x01b8;
            case 5: goto L_0x0228;
            default: goto L_0x0019;
        };
    L_0x0019:
        r0 = "etc_settings";
        r5.m2573a(r0, r6, r7);
    L_0x001f:
        return;
    L_0x0020:
        r3 = "email";
        r3 = r6.equals(r3);
        if (r3 == 0) goto L_0x0015;
    L_0x0029:
        goto L_0x0016;
    L_0x002a:
        r0 = "email_md5";
        r0 = r6.equals(r0);
        if (r0 == 0) goto L_0x0015;
    L_0x0033:
        r0 = 1;
        goto L_0x0016;
    L_0x0035:
        r0 = "email_sha1";
        r0 = r6.equals(r0);
        if (r0 == 0) goto L_0x0015;
    L_0x003e:
        r0 = 2;
        goto L_0x0016;
    L_0x0040:
        r0 = "email_sha256";
        r0 = r6.equals(r0);
        if (r0 == 0) goto L_0x0015;
    L_0x0049:
        r0 = 3;
        goto L_0x0016;
    L_0x004b:
        r0 = "twitter";
        r0 = r6.equals(r0);
        if (r0 == 0) goto L_0x0015;
    L_0x0054:
        r0 = 4;
        goto L_0x0016;
    L_0x0056:
        r0 = "phone";
        r0 = r6.equals(r0);
        if (r0 == 0) goto L_0x0015;
    L_0x005f:
        r0 = 5;
        goto L_0x0016;
    L_0x0061:
        if (r7 == 0) goto L_0x0069;
    L_0x0063:
        r0 = r7.length();
        if (r0 != 0) goto L_0x0070;
    L_0x0069:
        r0 = "Email value must not be null or empty";
        r5.m2606b(r0);
        goto L_0x001f;
    L_0x0070:
        r0 = "persona@areametrics.com";
        r0 = r7.equals(r0);
        if (r0 == 0) goto L_0x007d;
    L_0x0079:
        r5.m2591s();
        goto L_0x001f;
    L_0x007d:
        r0 = r7.trim();
        r1 = java.util.Locale.US;
        r0 = r0.toLowerCase(r1);
        r1 = "email";
        r1 = r2.getString(r1, r4);
        if (r1 == 0) goto L_0x0096;
    L_0x0090:
        r1 = r0.equals(r1);
        if (r1 != 0) goto L_0x00ca;
    L_0x0096:
        r1 = "Storing email value locally";
        r5.m2606b(r1);
        r1 = "email";
        r5.m2618e(r1);
        r1 = r2.edit();
        r2 = "email";
        r1.putString(r2, r0);
        r1.apply();
        r1 = "email_set";
        r2 = "true";
        r5.m2616d(r1, r2);
        r5.m2583j(r0);
        r0 = r5.m2630o();
        if (r0 == 0) goto L_0x001f;
    L_0x00c1:
        r0 = r5.m2630o();
        r0.m2639b();
        goto L_0x001f;
    L_0x00ca:
        r0 = "Email input same as previous value, not fetching";
        r5.m2606b(r0);
        goto L_0x001f;
    L_0x00d2:
        if (r7 == 0) goto L_0x00dc;
    L_0x00d4:
        r0 = r7.length();
        r1 = 32;
        if (r0 == r1) goto L_0x00e4;
    L_0x00dc:
        r0 = "email_md5 value must be a 32 character string";
        r5.m2606b(r0);
        goto L_0x001f;
    L_0x00e4:
        r0 = r7.trim();
        r1 = java.util.Locale.US;
        r0 = r0.toLowerCase(r1);
        r1 = "email_md5";
        r5.m2616d(r1, r0);
        r1 = "emailMD5";
        r1 = r2.getString(r1, r4);
        if (r1 == 0) goto L_0x0103;
    L_0x00fd:
        r1 = r0.equals(r1);
        if (r1 != 0) goto L_0x012b;
    L_0x0103:
        r1 = "Storing email_md5 value locally";
        r5.m2606b(r1);
        r1 = "emailMD5";
        r5.m2618e(r1);
        r1 = r2.edit();
        r2 = "emailMD5";
        r1.putString(r2, r0);
        r1.apply();
        r0 = r5.m2630o();
        if (r0 == 0) goto L_0x001f;
    L_0x0122:
        r0 = r5.m2630o();
        r0.m2639b();
        goto L_0x001f;
    L_0x012b:
        r0 = "email_md5 input same as previous value, not fetching";
        r5.m2606b(r0);
        goto L_0x001f;
    L_0x0133:
        if (r7 == 0) goto L_0x013d;
    L_0x0135:
        r0 = r7.length();
        r1 = 40;
        if (r0 == r1) goto L_0x0145;
    L_0x013d:
        r0 = "email_sha1 value must be a 40 character string";
        r5.m2606b(r0);
        goto L_0x001f;
    L_0x0145:
        r0 = r7.trim();
        r1 = java.util.Locale.US;
        r0 = r0.toLowerCase(r1);
        r1 = "email_sha1";
        r5.m2616d(r1, r0);
        goto L_0x001f;
    L_0x0157:
        if (r7 == 0) goto L_0x0161;
    L_0x0159:
        r0 = r7.length();
        r1 = 64;
        if (r0 == r1) goto L_0x0169;
    L_0x0161:
        r0 = "email_sha256 value must be a 64 character string";
        r5.m2606b(r0);
        goto L_0x001f;
    L_0x0169:
        r0 = r7.trim();
        r1 = java.util.Locale.US;
        r0 = r0.toLowerCase(r1);
        r1 = "email_sha256";
        r5.m2616d(r1, r0);
        r1 = "emailSHA256";
        r1 = r2.getString(r1, r4);
        if (r1 == 0) goto L_0x0188;
    L_0x0182:
        r1 = r0.equals(r1);
        if (r1 != 0) goto L_0x01b0;
    L_0x0188:
        r1 = "Storing email_md5 value locally";
        r5.m2606b(r1);
        r1 = "emailSHA256";
        r5.m2618e(r1);
        r1 = r2.edit();
        r2 = "emailSHA256";
        r1.putString(r2, r0);
        r1.apply();
        r0 = r5.m2630o();
        if (r0 == 0) goto L_0x001f;
    L_0x01a7:
        r0 = r5.m2630o();
        r0.m2639b();
        goto L_0x001f;
    L_0x01b0:
        r0 = "email_sha256 input same as previous value, not fetching";
        r5.m2606b(r0);
        goto L_0x001f;
    L_0x01b8:
        if (r7 == 0) goto L_0x01c0;
    L_0x01ba:
        r0 = r7.length();
        if (r0 != 0) goto L_0x01c8;
    L_0x01c0:
        r0 = "Twitter value must not be null or empty";
        r5.m2606b(r0);
        goto L_0x001f;
    L_0x01c8:
        r0 = "ams_persona";
        r0 = r7.equals(r0);
        if (r0 == 0) goto L_0x01d6;
    L_0x01d1:
        r5.m2591s();
        goto L_0x001f;
    L_0x01d6:
        r0 = r7.trim();
        r1 = java.util.Locale.US;
        r0 = r0.toLowerCase(r1);
        r1 = "twitter";
        r1 = r2.getString(r1, r4);
        if (r1 == 0) goto L_0x01ef;
    L_0x01e9:
        r1 = r0.equals(r1);
        if (r1 != 0) goto L_0x0220;
    L_0x01ef:
        r1 = "Storing twitter value locally";
        r5.m2606b(r1);
        r1 = "twitter";
        r5.m2618e(r1);
        r1 = r2.edit();
        r2 = "twitter";
        r1.putString(r2, r0);
        r1.apply();
        r0 = "twitter_set";
        r1 = "true";
        r5.m2616d(r0, r1);
        r0 = r5.m2630o();
        if (r0 == 0) goto L_0x001f;
    L_0x0217:
        r0 = r5.m2630o();
        r0.m2639b();
        goto L_0x001f;
    L_0x0220:
        r0 = "Twitter input same as previous value, not fetching";
        r5.m2606b(r0);
        goto L_0x001f;
    L_0x0228:
        if (r7 == 0) goto L_0x0230;
    L_0x022a:
        r0 = r7.length();
        if (r0 != 0) goto L_0x0238;
    L_0x0230:
        r0 = "Phone value must not be null or empty";
        r5.m2606b(r0);
        goto L_0x001f;
    L_0x0238:
        r0 = "0001234567";
        r0 = r7.equals(r0);
        if (r0 == 0) goto L_0x0246;
    L_0x0241:
        r5.m2591s();
        goto L_0x001f;
    L_0x0246:
        r0 = r7.trim();
        r1 = "phone";
        r1 = r2.getString(r1, r4);
        if (r1 == 0) goto L_0x0259;
    L_0x0253:
        r1 = r0.equals(r1);
        if (r1 != 0) goto L_0x028a;
    L_0x0259:
        r1 = "Storing phone value locally";
        r5.m2606b(r1);
        r1 = "phone";
        r5.m2618e(r1);
        r1 = r2.edit();
        r2 = "phone";
        r1.putString(r2, r0);
        r1.apply();
        r0 = "phone_set";
        r1 = "true";
        r5.m2616d(r0, r1);
        r0 = r5.m2630o();
        if (r0 == 0) goto L_0x001f;
    L_0x0281:
        r0 = r5.m2630o();
        r0.m2639b();
        goto L_0x001f;
    L_0x028a:
        r0 = "Phone input same as previous value, not fetching";
        r5.m2606b(r0);
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.areametrics.areametricssdk.g.a(java.lang.String, java.lang.String):void");
    }

    final void m2600a(Set<String> set) {
        SharedPreferences sharedPreferences = m2629n().getSharedPreferences("AMS_User_Data", 0);
        Editor edit = sharedPreferences.edit();
        for (String split : set) {
            String split2;
            String[] split3 = split2.split("~~~AMS~~~");
            if (split3.length == 3) {
                String str = split3[0];
                String str2 = split3[1];
                split2 = split3[2];
                String e = C1378g.m2578e(str, str2);
                str = C1378g.m2579f(str, str2);
                str2 = sharedPreferences.getString(str, null);
                if (str2 != null) {
                    if (split2.equals("AMS_VOID_VALUE") && str2.equals("AMS_VOID_VALUE")) {
                        edit.remove(e);
                        edit.remove(str);
                    } else if (str2.equals(split2)) {
                        edit.putString(e, split2);
                        edit.remove(str);
                    }
                }
            }
        }
        edit.apply();
    }

    final void m2601a(JSONArray jSONArray) {
        int i = 0;
        if (jSONArray != null) {
            Editor edit = m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
            Set hashSet = new HashSet();
            Iterable arrayList = new ArrayList();
            while (i < jSONArray.length()) {
                String optString = jSONArray.optString(i);
                if (optString.length() > 0) {
                    hashSet.add(optString);
                    arrayList.add(optString);
                }
                i++;
            }
            edit.putStringSet("loc_batch_priority_windows", hashSet);
            edit.apply();
            m2612c("loc_batch_priority_windows", arrayList.size() > 0 ? TextUtils.join("~", arrayList) : null);
        }
    }

    final void m2602a(JSONObject jSONObject) {
        String g = m2621g();
        if (g != null) {
            try {
                JSONArray jSONArray;
                JSONObject jSONObject2;
                String str;
                JSONObject jSONObject3;
                JSONArray jSONArray2;
                int i;
                jSONObject.remove("status");
                jSONObject.remove("requestId");
                jSONObject.remove("contactInfo");
                jSONObject.remove("photos");
                jSONObject.remove("organizations");
                if (jSONObject.optJSONArray("socialProfiles") != null) {
                    jSONArray = jSONObject.getJSONArray("socialProfiles");
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        jSONObject2 = jSONArray.getJSONObject(i2);
                        Iterator keys = jSONObject2.keys();
                        Set<String> hashSet = new HashSet();
                        while (keys.hasNext()) {
                            str = (String) keys.next();
                            Object obj = -1;
                            switch (str.hashCode()) {
                                case 3575610:
                                    if (str.equals("type")) {
                                        obj = null;
                                        break;
                                    }
                                    break;
                                case 765912085:
                                    if (str.equals("followers")) {
                                        obj = 1;
                                        break;
                                    }
                                    break;
                                case 765915793:
                                    if (str.equals("following")) {
                                        obj = 2;
                                        break;
                                    }
                                    break;
                            }
                            switch (obj) {
                                case null:
                                    break;
                                case 1:
                                case 2:
                                    jSONObject2.put(str, C1378g.m2576c(jSONObject2.optLong(str)));
                                    break;
                                default:
                                    hashSet.add(str);
                                    break;
                            }
                        }
                        for (String str2 : hashSet) {
                            jSONObject2.remove(str2);
                        }
                    }
                }
                if (jSONObject.optJSONObject("digitalFootprint") != null) {
                    jSONObject3 = jSONObject.getJSONObject("digitalFootprint");
                    if (jSONObject3.optJSONArray("topics") != null) {
                        jSONArray2 = jSONObject3.getJSONArray("topics");
                        for (i = 0; i < jSONArray2.length(); i++) {
                            jSONArray2.getJSONObject(i).remove("provider");
                        }
                    }
                    if (jSONObject3.optJSONArray("scores") != null) {
                        JSONArray jSONArray3 = jSONObject3.getJSONArray("scores");
                        for (i = 0; i < jSONArray3.length(); i++) {
                            JSONObject jSONObject4 = jSONArray3.getJSONObject(i);
                            jSONObject4.remove("type");
                            if (jSONObject4.has(FirebaseAnalytics$Param.VALUE)) {
                                jSONObject4.put(FirebaseAnalytics$Param.VALUE, C1378g.m2577d(jSONObject4.optLong(FirebaseAnalytics$Param.VALUE)));
                            }
                        }
                    }
                }
                if (jSONObject.optJSONObject("demographics") != null) {
                    JSONObject jSONObject5 = jSONObject.getJSONObject("demographics");
                    if (jSONObject5.optJSONObject("locationDeduced") != null) {
                        jSONObject3 = jSONObject5.getJSONObject("locationDeduced");
                        jSONObject3.remove("likelihood");
                        Iterator keys2 = jSONObject3.keys();
                        while (keys2.hasNext()) {
                            str2 = (String) keys2.next();
                            if (jSONObject3.optJSONObject(str2) != null) {
                                jSONObject3.getJSONObject(str2).remove("deduced");
                            }
                        }
                    }
                }
                if (jSONObject.optJSONObject(g) != null) {
                    jSONObject3 = jSONObject.getJSONObject(g);
                    if (jSONObject3.optJSONArray("interests") != null) {
                        jSONArray2 = jSONObject3.optJSONArray("interests");
                        jSONArray = new JSONArray();
                        for (i = 0; i < jSONArray2.length(); i++) {
                            JSONObject jSONObject6 = jSONArray2.getJSONObject(i);
                            jSONObject2 = new JSONObject();
                            if (m2617e() != C1376a.f1980b) {
                                if (jSONObject6.has("name")) {
                                    jSONObject2.put("name", jSONObject6.get("name"));
                                }
                                if (jSONObject6.has("id")) {
                                    jSONObject2.put("id", jSONObject6.get("id"));
                                }
                                if (jSONObject6.has(FirebaseAnalytics$Param.SCORE)) {
                                    jSONObject2.put(FirebaseAnalytics$Param.SCORE, jSONObject6.get(FirebaseAnalytics$Param.SCORE));
                                }
                                if (jSONObject6.has("category")) {
                                    jSONObject2.put("category", jSONObject6.get("category"));
                                }
                                if (jSONObject6.has("parents")) {
                                    jSONObject2.put("parents", jSONObject6.get("parents"));
                                }
                                jSONArray.put(jSONObject2);
                            } else if (!jSONObject6.has("parents")) {
                                if (jSONObject6.has("name")) {
                                    jSONObject2.put("name", jSONObject6.get("name"));
                                }
                                if (jSONObject6.has(FirebaseAnalytics$Param.SCORE)) {
                                    jSONObject2.put(FirebaseAnalytics$Param.SCORE, jSONObject6.get(FirebaseAnalytics$Param.SCORE));
                                }
                                jSONArray.put(jSONObject2);
                            }
                        }
                        jSONObject3.put("interests", jSONArray);
                    }
                    jSONObject.put("areametrics", jSONObject3);
                    jSONObject.remove(g);
                }
                Editor edit = m2629n().getSharedPreferences("AMS_FC_SHARED_PERSONA", 0).edit();
                edit.putString("shared_persona", jSONObject.toString());
                edit.apply();
                m2622g(null);
                if (m2617e() == C1376a.f1979a) {
                    return;
                }
                if (m2590r().delegate != null) {
                    m2606b("Sharing persona data with delegate!");
                    m2590r().delegate.didGeneratePersona(jSONObject);
                    return;
                }
                m2606b("Unable to share persona, delegate property unassigned!");
            } catch (JSONException e) {
            }
        }
    }

    final JSONObject m2603b() {
        String string = m2629n().getSharedPreferences("AMS_FC_PARSED_PERSONA", 0).getString("parsed_persona", null);
        if (string != null) {
            try {
                return new JSONObject(string);
            } catch (JSONException e) {
                Log.e(f1988d, "getParsedPersonaException: " + e.getMessage());
            }
        }
        return null;
    }

    final void m2604b(int i) {
        if (i >= 0 && i <= 14400) {
            Editor edit = m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
            edit.putInt("fg_bscan_wait", i);
            edit.apply();
            m2612c("fg_bscan_wait", Integer.toString(i));
            if (m2590r().getBeaconTracker() != null) {
                m2590r().getBeaconTracker().m2563b((long) i);
            }
        }
    }

    final void m2605b(long j) {
        if (j - ((long) (((double) System.currentTimeMillis()) / 1000.0d)) < 2592000) {
            Editor edit = m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
            edit.putLong("refresh_date", j);
            edit.apply();
            m2612c("refresh_date", Long.toString(j));
        }
    }

    final void m2606b(String str) {
        if (!this.f1991c && m2617e() != C1376a.f1979a) {
            Log.i("AMSDK-Persona", str);
        }
    }

    final void m2607b(String str, String str2) {
        m2573a("env_settings", str, str2);
    }

    final void m2608b(JSONArray jSONArray) {
        int i = 0;
        if (jSONArray != null) {
            Editor edit = m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
            Set hashSet = new HashSet();
            Iterable arrayList = new ArrayList();
            while (i < jSONArray.length()) {
                String toUpperCase = jSONArray.optString(i).toUpperCase(Locale.US);
                if (toUpperCase.length() > 0) {
                    hashSet.add(toUpperCase);
                    arrayList.add(toUpperCase);
                }
                i++;
            }
            edit.putStringSet("gdpr_countries", hashSet);
            edit.apply();
            m2612c("gdpr_countries", arrayList.size() > 0 ? TextUtils.join("~", arrayList) : null);
        }
    }

    final JSONObject m2609c() {
        Map all = m2629n().getSharedPreferences("AMS_FC_PARSED_PERSONA_METADATA", 0).getAll();
        if (all.size() > 0) {
            try {
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObject2 = new JSONObject();
                JSONObject jSONObject3 = new JSONObject();
                for (Entry entry : all.entrySet()) {
                    String str = (String) entry.getKey();
                    if (str.length() == 3) {
                        jSONObject2.put(str, ((Integer) entry.getValue()).intValue());
                    } else if (str.equals("date")) {
                        jSONObject.put("date", ((Long) entry.getValue()).longValue());
                    } else if (str.equals("redactions")) {
                        jSONObject3.put("redactions", ((Integer) entry.getValue()).intValue());
                    } else if (str.equals("obfuscations")) {
                        jSONObject3.put("obfuscations", ((Integer) entry.getValue()).intValue());
                    } else if (str.equals("remaining")) {
                        jSONObject.put("rate_limit_remaining", ((Integer) entry.getValue()).intValue());
                    } else if (str.equals("reset")) {
                        jSONObject.put("rate_limit_reset", ((Integer) entry.getValue()).intValue());
                    }
                }
                jSONObject.put("status_codes", jSONObject2);
                jSONObject.put("anonymizations", jSONObject3);
                return jSONObject;
            } catch (JSONException e) {
                Log.e(f1988d, "getParsedPersonaMetadataException: " + e.getMessage());
            }
        }
        return null;
    }

    final void m2610c(int i) {
        if (i >= 0 && i <= C1057k.kA) {
            Editor edit = m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
            edit.putInt("bg_bscan_period", i);
            edit.apply();
            m2612c("bg_bscan_period", Integer.toString(i));
            if (m2590r().getBeaconTracker() != null) {
                m2590r().getBeaconTracker().m2565c((long) i);
            }
        }
    }

    final void m2611c(final String str) {
        m2597a(null, new C1352c(this) {
            final /* synthetic */ C1378g f1970b;

            public final void mo2159a(Map<String, String> map) {
                boolean h = this.f1970b.m2624h(null);
                if (!h && map.containsKey("country")) {
                    h = this.f1970b.m2624h((String) map.get("country"));
                }
                if (!h) {
                    this.f1970b.m2616d("ad_id", str);
                }
            }
        });
    }

    final void m2612c(String str, String str2) {
        m2573a("sdk_settings", str, str2);
    }

    final int m2613d() {
        return m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getInt("join_duration", "release".equals("debug") ? 15 : HttpStatus.SC_MULTIPLE_CHOICES);
    }

    final void m2614d(int i) {
        if (i >= 0 && i <= 14400) {
            Editor edit = m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
            edit.putInt("bg_bscan_wait", i);
            edit.apply();
            m2612c("bg_bscan_wait", Integer.toString(i));
            if (m2590r().getBeaconTracker() != null) {
                m2590r().getBeaconTracker().m2566d((long) i);
            }
        }
    }

    final void m2615d(String str) {
        if (System.currentTimeMillis() - m2629n().getSharedPreferences("AMS_FC_CONSECUTIVE_404", 0).getLong(str + "Last404Time", 0) > 7776000000L) {
            m2618e(str);
        }
    }

    final void m2616d(String str, String str2) {
        m2573a("usr_settings", str, str2);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final int m2617e() {
        /*
        r4 = this;
        r0 = 0;
        r1 = r4.m2629n();
        r2 = "AMS_SDK_CONFIG";
        r1 = r1.getSharedPreferences(r2, r0);
        r2 = "persona_sharing";
        r3 = "none";
        r2 = r1.getString(r2, r3);
        r1 = -1;
        r3 = r2.hashCode();
        switch(r3) {
            case 3154575: goto L_0x0025;
            case 3387192: goto L_0x003a;
            case 176117146: goto L_0x002f;
            default: goto L_0x001e;
        };
    L_0x001e:
        r0 = r1;
    L_0x001f:
        switch(r0) {
            case 0: goto L_0x0045;
            case 1: goto L_0x0048;
            default: goto L_0x0022;
        };
    L_0x0022:
        r0 = com.areametrics.areametricssdk.C1378g.C1376a.f1979a;
    L_0x0024:
        return r0;
    L_0x0025:
        r3 = "full";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x001e;
    L_0x002e:
        goto L_0x001f;
    L_0x002f:
        r0 = "limited";
        r0 = r2.equals(r0);
        if (r0 == 0) goto L_0x001e;
    L_0x0038:
        r0 = 1;
        goto L_0x001f;
    L_0x003a:
        r0 = "none";
        r0 = r2.equals(r0);
        if (r0 == 0) goto L_0x001e;
    L_0x0043:
        r0 = 2;
        goto L_0x001f;
    L_0x0045:
        r0 = com.areametrics.areametricssdk.C1378g.C1376a.f1981c;
        goto L_0x0024;
    L_0x0048:
        r0 = com.areametrics.areametricssdk.C1378g.C1376a.f1980b;
        goto L_0x0024;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.areametrics.areametricssdk.g.e():int");
    }

    final void m2618e(String str) {
        Editor edit = m2629n().getSharedPreferences("AMS_FC_CONSECUTIVE_404", 0).edit();
        edit.remove(str);
        edit.remove(str + "Last404Time");
        edit.apply();
    }

    final int m2619f() {
        return m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getInt("loc_batch_days_expiry", 7);
    }

    final void m2620f(String str) {
        Editor edit = m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
        if (str == null || str.length() == 0) {
            edit.remove("papi_url");
        } else {
            edit.putString("papi_url", str);
        }
        edit.apply();
    }

    final String m2621g() {
        return m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getString("mm_word", null);
    }

    final void m2622g(String str) {
        Editor edit = m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
        if (str == null || str.length() == 0) {
            edit.remove("mm_word");
        } else {
            edit.putString("mm_word", str);
        }
        edit.apply();
    }

    final Set<String> m2623h() {
        Set<String> hashSet = new HashSet();
        for (Entry entry : m2629n().getSharedPreferences("AMS_User_Data", 0).getAll().entrySet()) {
            String str = (String) entry.getKey();
            if (str.startsWith("AMS_DIRTY_SHARED_PREFS_PREFIX:")) {
                str = str.replaceFirst("AMS_DIRTY_SHARED_PREFS_PREFIX:", "");
                if (!str.equals("usr_settings~~~AMS~~~applist")) {
                    hashSet.add(str + "~~~AMS~~~" + ((String) entry.getValue()));
                }
            }
        }
        return hashSet;
    }

    final boolean m2624h(String str) {
        long j = m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getLong("gdpr_date", 0);
        if (j == 0 || System.currentTimeMillis() < j * 1000) {
            return false;
        }
        Set stringSet = m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getStringSet("gdpr_countries", new HashSet(Arrays.asList(new String[]{"AT", "BE", "BG", "HR", "CY", "CZ", "DK", "EE", "FI", "FR", "DE", "GR", "HU", "IE", "IT", "LV", "LT", "LU", "MT", "NL", "PL", "PT", "RO", "SK", "SI", "ES", "SE", "GB"})));
        String country = Locale.getDefault().getCountry();
        return (str == null || !stringSet.contains(str)) ? country != null && stringSet.contains(country) : true;
    }

    final String m2625i() {
        String str = null;
        try {
            if (!(m2629n() == null || m2629n().getContentResolver() == null)) {
                String string = Secure.getString(m2629n().getContentResolver(), "android_id");
                if (string != null) {
                    str = UUID.nameUUIDFromBytes(string.getBytes("utf8")).toString();
                }
            }
        } catch (UnsupportedEncodingException e) {
        }
        return str;
    }

    final void m2626k() {
        m2607b("new_user", m2589q().booleanValue() ? SchemaSymbols.ATTVAL_TRUE : SchemaSymbols.ATTVAL_FALSE);
        m2607b("os", "android");
        m2607b("model", C1378g.m2582j());
        m2607b(UserBox.TYPE, m2625i());
        m2607b("version", VERSION.RELEASE);
        String str = Reveal.STATUS_BLUETOOTH;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        boolean z = defaultAdapter != null && defaultAdapter.isEnabled();
        m2607b(str, Boolean.valueOf(z).booleanValue() ? C1042c.jF : C1042c.jG);
        m2573a("app_settings", "version", m2588p());
        m2612c("version", C1373f.m2567a());
        m2616d("country", Locale.getDefault().getCountry());
        m2616d(SchemaSymbols.ATTVAL_LANGUAGE, Locale.getDefault().getLanguage());
        String string = m2629n().getSharedPreferences("AMS_FC_PARAM_STORE", 0).getString("email", null);
        if (string != null) {
            m2583j(string);
        }
    }

    final void m2627l() {
        SharedPreferences sharedPreferences = m2629n().getSharedPreferences("AMS_User_Data", 0);
        Editor edit = sharedPreferences.edit();
        int i = 0;
        for (Entry entry : sharedPreferences.getAll().entrySet()) {
            int i2;
            String str = (String) entry.getKey();
            if (!str.startsWith("AMS_DIRTY_SHARED_PREFS_PREFIX:")) {
                String[] split = str.split("~~~AMS~~~");
                str = C1378g.m2579f(split[0], split[1]);
                if (sharedPreferences.getString(str, null) == null) {
                    edit.putString(str, (String) entry.getValue());
                    i2 = 1;
                    i = i2;
                }
            }
            i2 = i;
            i = i2;
        }
        edit.apply();
        if (i != 0) {
            m2630o().m2640b(C1380a.f1999d);
        }
    }

    final Boolean m2628m() {
        boolean z = false;
        SharedPreferences sharedPreferences = m2629n().getSharedPreferences("AMS_User_Data", 0);
        String string = sharedPreferences.getString(C1378g.m2578e("env_settings", "new_user"), null);
        String string2 = sharedPreferences.getString(C1378g.m2579f("env_settings", "new_user"), null);
        if (string == null && string2 == null) {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    final Context m2629n() {
        return this.f1992e != null ? this.f1992e : AreaMetricsSDK.INSTANCE.getContext();
    }

    final C1381h m2630o() {
        return this.f1993f != null ? this.f1993f : AreaMetricsSDK.INSTANCE.getUserDataController();
    }
}
