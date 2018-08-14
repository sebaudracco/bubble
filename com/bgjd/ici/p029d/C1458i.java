package com.bgjd.ici.p029d;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import com.bgjd.ici.json.JSONArray;
import com.bgjd.ici.json.JSONException;
import com.bgjd.ici.json.JSONObject;
import com.bgjd.ici.json.JSONProperty;
import com.bgjd.ici.p025b.C1395h;
import com.bgjd.ici.p025b.C1408j.C1403a;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.bgjd.ici.p025b.C1409k;
import com.bgjd.ici.p025b.ac;
import com.mobfox.sdk.networking.RequestParams;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.TimeZone;
import java.util.UUID;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class C1458i extends C1409k<JSONObject> {
    @JSONProperty(name = "v")
    private boolean f2252A;
    @JSONProperty(name = "w")
    private boolean f2253B;
    @JSONProperty(name = "x")
    private int f2254C;
    @JSONProperty(name = "y")
    private JSONObject f2255D;
    @JSONProperty(name = "z")
    private String f2256E;
    @JSONProperty(name = "1")
    private String f2257F;
    @JSONProperty(name = "2")
    private boolean f2258G;
    @JSONProperty(name = "3")
    private int f2259H;
    @JSONProperty(name = "4")
    private int f2260I;
    @JSONProperty(name = "5")
    private int f2261J;
    @JSONProperty(name = "6")
    private String f2262K;
    @JSONProperty(name = "7")
    private String f2263L;
    @JSONProperty(name = "8")
    private String f2264M;
    @JSONProperty(name = "9")
    private String f2265N;
    @JSONProperty(name = "10")
    private String f2266O;
    @JSONProperty(name = "11")
    private String f2267P;
    @JSONProperty(name = "12")
    private String f2268Q;
    @JSONProperty(name = "13")
    private String f2269R;
    @JSONProperty(name = "14")
    private String f2270S;
    @JSONProperty(name = "15")
    private String f2271T;
    @JSONProperty(name = "16")
    private String f2272U;
    @JSONProperty(name = "17")
    private JSONArray f2273V;
    @JSONProperty(name = "18")
    private JSONObject f2274W;
    @JSONProperty(name = "19")
    private boolean f2275X;
    @JSONProperty(name = "20")
    private int f2276Y;
    @JSONProperty(name = "21")
    private String f2277Z;
    @JSONProperty(name = "22")
    private JSONArray aa;
    @JSONProperty(name = "23")
    private boolean ab;
    @JSONProperty(name = "24")
    private JSONObject ac;
    @JSONProperty(name = "25")
    private boolean ad;
    @JSONProperty(name = "a")
    private String f2278f;
    @JSONProperty(name = "b")
    private String f2279g;
    @JSONProperty(name = "c")
    private boolean f2280h;
    @JSONProperty(name = "d")
    private String f2281i;
    @JSONProperty(name = "e")
    private String f2282j;
    @JSONProperty(name = "f")
    private String f2283k;
    @JSONProperty(name = "g")
    private boolean f2284l;
    @JSONProperty(name = "h")
    private String f2285m;
    @JSONProperty(name = "i")
    private String f2286n;
    @JSONProperty(name = "j")
    private int f2287o;
    @JSONProperty(name = "k")
    private String f2288p;
    @JSONProperty(name = "l")
    private JSONObject f2289q;
    @JSONProperty(name = "m")
    private byte f2290r;
    @JSONProperty(name = "n")
    private String f2291s;
    @JSONProperty(name = "o")
    private String f2292t;
    @JSONProperty(name = "p")
    private byte f2293u;
    @JSONProperty(name = "q")
    private byte f2294v;
    @JSONProperty(name = "r")
    private int f2295w;
    @JSONProperty(name = "s")
    private String f2296x;
    @JSONProperty(name = "t")
    private String f2297y;
    @JSONProperty(name = "u")
    private boolean f2298z;

    public /* synthetic */ Object mo2325d() {
        return m3018f();
    }

    public C1458i(C1395h c1395h) {
        boolean z;
        super(c1395h);
        this.f2278f = "";
        this.f2279g = "";
        this.f2280h = false;
        this.f2281i = C1403a.f2077g;
        this.f2282j = "";
        this.f2283k = "";
        this.f2284l = false;
        this.f2285m = "";
        this.f2286n = "";
        this.f2287o = 0;
        this.f2288p = "";
        this.f2289q = null;
        this.f2290r = (byte) 1;
        this.f2291s = "";
        this.f2292t = SchemaSymbols.ATTVAL_FALSE_0;
        this.f2293u = (byte) 0;
        this.f2294v = (byte) 1;
        this.f2295w = 0;
        this.f2296x = "";
        this.f2297y = "";
        this.f2298z = false;
        this.f2252A = false;
        this.f2253B = false;
        this.f2254C = 0;
        this.f2255D = null;
        this.f2256E = "";
        this.f2257F = "";
        this.f2258G = false;
        this.f2259H = 3;
        this.f2260I = 1;
        this.f2261J = 1;
        this.f2262K = "";
        this.f2263L = "";
        this.f2264M = "";
        this.f2265N = "";
        this.f2266O = "";
        this.f2267P = "";
        this.f2268Q = "";
        this.f2269R = "";
        this.f2270S = "";
        this.f2271T = "";
        this.f2272U = "";
        this.f2273V = new JSONArray();
        this.f2274W = new JSONObject();
        this.f2275X = false;
        this.f2276Y = 0;
        this.f2277Z = "";
        this.aa = new JSONArray();
        this.ab = false;
        this.ac = new JSONObject();
        this.ad = false;
        this.b = "info";
        this.f2279g = c1395h.mo2283r();
        this.f2280h = c1395h.mo2284s();
        this.f2281i = c1395h.mo2271l();
        this.f2282j = c1395h.mo2276n();
        this.f2283k = c1395h.mo2277o();
        this.f2295w = c1395h.mo2279p();
        this.f2284l = c1395h.IsSandbox();
        this.f2285m = Build.MODEL;
        this.f2286n = Build.MANUFACTURER;
        this.f2287o = VERSION.SDK_INT;
        this.f2291s = Build.DEVICE;
        this.f2288p = m3011n();
        this.f2289q = m3006i();
        m3004g();
        m3005h();
        this.f2296x = c1395h.getContext().getPackageName();
        this.f2297y = VERSION.RELEASE;
        this.f2298z = ac.m2861a();
        this.f2252A = ac.m2867b();
        this.f2253B = ac.m2868b(c1395h.getContext());
        this.f2254C = m3012o();
        this.f2255D = m3008k();
        this.f2256E = ac.m2866b(m3009l());
        this.f2257F = m3013p();
        this.f2258G = c1395h.isAccepted();
        String j = m3007j();
        this.f2278f = ac.m2866b(j);
        this.f2263L = ac.m2866b(j.toUpperCase());
        this.f2262K = ac.m2870c(j);
        this.f2264M = ac.m2870c(j.toUpperCase());
        this.f2265N = ac.m2859a(j);
        this.f2266O = ac.m2859a(j.toUpperCase());
        try {
            if (this.f2279g.length() == 0) {
                this.f2279g = UUID.nameUUIDFromBytes(j.getBytes("utf8")).toString();
                this.f2260I = 2;
            }
        } catch (UnsupportedEncodingException e) {
        }
        try {
            PackageManager packageManager = c1395h.getContext().getPackageManager();
            String[] strArr = packageManager.getPackageInfo(c1395h.getContext().getPackageName(), 4096).requestedPermissions;
            if (strArr != null && strArr.length > 0) {
                for (String fileExtensionFromUrl : strArr) {
                    this.f2273V.put(MimeTypeMap.getFileExtensionFromUrl(fileExtensionFromUrl));
                }
            }
            try {
                this.f2275X = ((Boolean) packageManager.getClass().getMethod("hasSystemFeature", new Class[]{String.class}).invoke(packageManager, new Object[]{"android.hardware.bluetooth_le"})).booleanValue();
            } catch (NoSuchMethodException e2) {
                Log.i("MKT", e2.getMessage());
            } catch (IllegalAccessException e3) {
                Log.i("MKT", e3.getMessage());
            } catch (IllegalArgumentException e4) {
                Log.i("MKT", e4.getMessage());
            } catch (InvocationTargetException e5) {
                Log.i("MKT", e5.getMessage());
            }
        } catch (NameNotFoundException e6) {
            Log.i("MKT", e6.getMessage());
        }
        this.ac = c1395h.mo2191R();
        this.ab = c1395h.mo2192S();
        if (c1395h.mo2192S() && c1395h.mo2190Q()) {
            z = true;
        } else {
            z = false;
        }
        this.ad = z;
    }

    public JSONObject m3018f() {
        JSONObject jSONObject = new JSONObject();
        for (Field field : getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(JSONProperty.class)) {
                String name = ((JSONProperty) field.getAnnotation(JSONProperty.class)).name();
                Object obj = null;
                try {
                    obj = field.get(this);
                } catch (IllegalAccessException e) {
                } catch (IllegalArgumentException e2) {
                }
                try {
                    jSONObject.put(name, obj);
                } catch (JSONException e3) {
                }
            }
        }
        return jSONObject;
    }

    private void m3004g() {
        String str;
        CharSequence charSequence;
        TelephonyManager telephonyManager = (TelephonyManager) this.a.getContext().getSystemService("phone");
        CharSequence networkOperator = telephonyManager.getNetworkOperator();
        int phoneType = telephonyManager.getPhoneType();
        if (phoneType == 2) {
            if (TextUtils.isEmpty(networkOperator)) {
                try {
                    Class cls = Class.forName(C1404b.f2144v);
                    str = (String) cls.getMethod("get", new Class[]{String.class}).invoke(cls, new Object[]{C1404b.f2145w});
                    try {
                        if (str.equalsIgnoreCase("123456") || telephonyManager.getSimState() == 1) {
                            str = m3003b(C1404b.f2145w);
                        }
                    } catch (ClassNotFoundException e) {
                    } catch (NoSuchMethodException e2) {
                    } catch (IllegalAccessException e3) {
                    } catch (IllegalArgumentException e4) {
                    } catch (InvocationTargetException e5) {
                    } catch (Exception e6) {
                    }
                } catch (ClassNotFoundException e7) {
                    charSequence = networkOperator;
                } catch (NoSuchMethodException e8) {
                    charSequence = networkOperator;
                } catch (IllegalAccessException e9) {
                    charSequence = networkOperator;
                } catch (IllegalArgumentException e10) {
                    charSequence = networkOperator;
                } catch (InvocationTargetException e11) {
                    charSequence = networkOperator;
                } catch (Exception e12) {
                    charSequence = networkOperator;
                }
            }
            charSequence = networkOperator;
        } else {
            if (TextUtils.isEmpty(networkOperator)) {
                str = m3003b(C1404b.f2146x);
            }
            charSequence = networkOperator;
        }
        int i;
        if (TextUtils.isEmpty(str)) {
            try {
                String str2;
                i = this.a.getContext().getResources().getConfiguration().mcc;
                int i2 = this.a.getContext().getResources().getConfiguration().mnc;
                if (i <= 0 || i2 <= 0) {
                    str2 = SchemaSymbols.ATTVAL_FALSE_0;
                } else {
                    str2 = String.format("%s%s", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)});
                }
                str = str2;
            } catch (Exception e13) {
                str = SchemaSymbols.ATTVAL_FALSE_0;
            }
        } else {
            try {
                if (!TextUtils.isEmpty(str) && str.matches("^\\d+$")) {
                    i = Integer.parseInt(str.substring(0, 3));
                    int parseInt = Integer.parseInt(str.substring(3));
                    if (i > 0 && parseInt == 0) {
                        i = this.a.getContext().getResources().getConfiguration().mcc;
                        parseInt = this.a.getContext().getResources().getConfiguration().mnc;
                        if (i > 0 && parseInt > 0) {
                            str = String.format("%s%s", new Object[]{Integer.valueOf(i), Integer.valueOf(parseInt)});
                        }
                    }
                }
            } catch (Exception e14) {
            }
        }
        this.f2292t = str;
        this.f2293u = (byte) phoneType;
    }

    private void m3005h() {
        if (ac.m2862a(this.a.getContext(), "android.permission.ACCESS_NETWORK_STATE")) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.a.getContext().getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                this.f2294v = (byte) activeNetworkInfo.getType();
            }
        }
    }

    private String m3003b(String str) {
        BufferedReader bufferedReader;
        Throwable th;
        String str2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + str).getInputStream()));
            try {
                str2 = bufferedReader.readLine();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                    }
                }
            } catch (IOException e2) {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e3) {
                    }
                }
                return str2;
            } catch (Throwable th2) {
                th = th2;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e4) {
                    }
                }
                throw th;
            }
        } catch (IOException e5) {
            bufferedReader = str2;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return str2;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            bufferedReader = str2;
            th = th4;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
        return str2;
    }

    private JSONObject m3006i() {
        Calendar instance = Calendar.getInstance();
        Object format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(instance.getTime());
        TimeZone timeZone = TimeZone.getDefault();
        Calendar instance2 = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault());
        Object format2 = new SimpleDateFormat("Z", Locale.US).format(instance2.getTime());
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("a", format);
            jSONObject.put("b", instance.getTimeInMillis());
            jSONObject.put("c", timeZone.getID());
            jSONObject.put("d", format2);
            jSONObject.put("e", instance2.getTimeInMillis());
            Locale locale = Locale.getDefault();
            jSONObject.put("f", locale.toString());
            String language = locale.getLanguage();
            Object obj = "en";
            if (language != null) {
                obj = language.toLowerCase();
            }
            jSONObject.put("g", obj);
            jSONObject.put(RequestParams.f9035H, locale.getISO3Language());
        } catch (JSONException e) {
        } catch (MissingResourceException e2) {
        }
        return jSONObject;
    }

    private String m3007j() {
        String str = "";
        if (this.a.mo2174B()) {
            str = this.a.mo2175C();
            this.f2261J = this.a.mo2173A();
            return str;
        }
        str = Secure.getString(this.a.getContext().getContentResolver(), "android_id");
        if (!TextUtils.isEmpty(str) && "9774d56d682e549c".equals(str)) {
            str = "";
        }
        if (TextUtils.isEmpty(str)) {
            str = m3003b("ro.serialno");
            this.f2261J = 2;
            if (TextUtils.isEmpty(str)) {
                str = m3003b("ro.boot.serialno");
                this.f2261J = 3;
                if (TextUtils.isEmpty(str)) {
                    str = m3003b("net.hostname");
                    this.f2261J = 4;
                } else {
                    this.f2261J = 0;
                }
            }
        }
        if (TextUtils.isEmpty(str)) {
            if (this.f2279g.length() > 0) {
                str = this.f2279g;
                this.f2261J = 6;
            } else {
                this.f2261J = 0;
            }
        }
        if (this.f2261J <= 1 || TextUtils.isEmpty(str)) {
            return str;
        }
        this.a.mo2231e(str);
        this.a.mo2200a(this.f2261J);
        return str;
    }

    private JSONObject m3008k() {
        int i;
        Display defaultDisplay = ((WindowManager) this.a.getContext().getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        int i2 = displayMetrics.widthPixels;
        int i3 = displayMetrics.heightPixels;
        if (VERSION.SDK_INT < 14 || VERSION.SDK_INT >= 17) {
            i = i3;
            i3 = i2;
        } else {
            try {
                i2 = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
                i = ((Integer) Display.class.getMethod("getRawHeight", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
                i3 = i2;
            } catch (Exception e) {
                int i4 = i3;
                i3 = i2;
                i = i4;
            }
        }
        if (VERSION.SDK_INT >= 17) {
            try {
                Point point = new Point();
                Display.class.getMethod("getRealSize", new Class[]{Point.class}).invoke(defaultDisplay, new Object[]{point});
                i3 = point.x;
                i = point.y;
            } catch (Exception e2) {
            }
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("w", i3);
            jSONObject.put(RequestParams.f9035H, i);
        } catch (JSONException e3) {
        }
        return jSONObject;
    }

    private String m3009l() {
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException e) {
        }
        return null;
    }

    @SuppressLint({"DefaultLocale"})
    private String m3010m() {
        String str = VERSION.RELEASE;
        String str2 = Build.MODEL;
        String str3 = Build.ID;
        Locale locale = Locale.getDefault();
        String language = locale.getLanguage();
        String str4 = "en";
        if (language != null) {
            str4 = language.toLowerCase();
            String country = locale.getCountry();
            if (country != null) {
                str4 = str4 + "-" + country.toLowerCase();
            }
        }
        return String.format(C1404b.f2142t, new Object[]{str, str4, str2, str3});
    }

    private String m3011n() {
        String property = System.getProperty("http.agent");
        if (property.length() == 0) {
            return m3010m();
        }
        return property;
    }

    private int m3012o() {
        Display defaultDisplay = ((WindowManager) this.a.getContext().getSystemService("window")).getDefaultDisplay();
        Object invoke;
        if (VERSION.SDK_INT <= 4) {
            try {
                int parseInt;
                invoke = Display.class.getMethod("getOrientation", new Class[0]).invoke(defaultDisplay, new Object[0]);
                if (invoke != null) {
                    parseInt = Integer.parseInt(invoke.toString());
                } else {
                    parseInt = 0;
                }
                return parseInt;
            } catch (IllegalAccessException e) {
                return 0;
            } catch (IllegalArgumentException e2) {
                return 0;
            } catch (InvocationTargetException e3) {
                return 0;
            } catch (NoSuchMethodException e4) {
                return 0;
            }
        }
        try {
            invoke = Display.class.getMethod("getRotation", new Class[0]).invoke(defaultDisplay, new Object[0]);
            if (invoke != null) {
                return Integer.parseInt(invoke.toString());
            }
            return 0;
        } catch (IllegalAccessException e5) {
            return 0;
        } catch (IllegalArgumentException e6) {
            return 0;
        } catch (InvocationTargetException e7) {
            return 0;
        } catch (NoSuchMethodException e8) {
            return 0;
        }
    }

    private String m3013p() {
        if (this.a.getContext().checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            return C1404b.f2123a;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.a.getContext().getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return C1404b.f2123a;
        }
        int type = activeNetworkInfo.getType();
        int subtype = activeNetworkInfo.getSubtype();
        if (type == 1) {
            return C1404b.f2124b;
        }
        if (type == 6) {
            return C1404b.f2125c;
        }
        if (type != 0) {
            return C1404b.f2123a;
        }
        switch (subtype) {
            case 1:
                return C1404b.f2134l;
            case 2:
                return C1404b.f2129g;
            case 3:
                return C1404b.f2141s;
            case 4:
                return C1404b.f2128f;
            case 5:
                return C1404b.f2131i;
            case 6:
                return C1404b.f2132j;
            case 7:
                return C1404b.f2127e;
            case 8:
                return C1404b.f2135m;
            case 9:
                return C1404b.f2138p;
            case 10:
                return C1404b.f2136n;
            case 11:
                return C1404b.f2139q;
            case 12:
                return C1404b.f2133k;
            case 13:
                return C1404b.f2140r;
            case 14:
                return C1404b.f2130h;
            case 15:
                return C1404b.f2137o;
            default:
                return C1404b.f2126d;
        }
    }

    public void m3014a(int i) {
        this.f2276Y = i;
    }

    public void m3016a(String str) {
        this.f2277Z = str;
    }

    public void m3015a(JSONArray jSONArray) {
        this.aa = jSONArray;
    }
}
