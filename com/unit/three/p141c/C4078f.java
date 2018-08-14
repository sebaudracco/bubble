package com.unit.three.p141c;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.bgjd.ici.p025b.C1408j.C1407c.C1406b;
import com.mobfox.sdk.networking.RequestParams;
import com.unit.three.p138b.C4053c;
import com.unit.three.p138b.C4064i;
import com.unit.three.p138b.C4064i.C4062b;
import com.unit.three.p140d.p142a.C4080b;
import com.unit.three.p143e.C4090d;
import com.unit.three.p143e.p144a.C4086b;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.altbeacon.bluetooth.Pdu;
import org.json.JSONObject;

public class C4078f {
    private static String f9436h;
    private int f9437a = 1;
    private int f9438b = 1;
    private int f9439c = 20;
    private int f9440d = 5;
    private int f9441e = 72;
    private int f9442f = 0;
    private int f9443g = 0;

    public C4078f(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                JSONObject optJSONObject = jSONObject.optJSONObject("service_69");
                if (optJSONObject != null) {
                    optJSONObject = optJSONObject.optJSONArray("configs").optJSONObject(0);
                    if (optJSONObject != null) {
                        this.f9437a = optJSONObject.optInt("reconnect", 1);
                        this.f9438b = optJSONObject.optInt("reconnect_net", 1);
                        this.f9439c = optJSONObject.optInt("reconnect_max", 20);
                        this.f9440d = optJSONObject.optInt("reconnect_delay", 5);
                        this.f9441e = optJSONObject.optInt("close_reconnect_delay", 72);
                        this.f9442f = optJSONObject.optInt("switch", 0);
                        this.f9443g = optJSONObject.optInt("init_delay", 0);
                    }
                }
            } catch (Throwable th) {
            }
        }
    }

    public static byte m12561a(int i) {
        switch (i) {
            case 0:
                return (byte) 0;
            case 1:
                return (byte) 1;
            case 2:
                return (byte) 2;
            case 3:
                return (byte) 3;
            case 4:
                return (byte) 4;
            case 5:
                return (byte) 5;
            case 6:
                return (byte) 6;
            case 7:
                return (byte) 7;
            case 8:
                return (byte) 8;
            case 9:
                return (byte) 9;
            case 10:
                return (byte) 10;
            case 11:
                return (byte) 11;
            case 12:
                return (byte) 12;
            case 13:
                return (byte) 13;
            case 14:
                return (byte) 14;
            case 15:
                return (byte) 15;
            case 16:
                return (byte) 16;
            case 17:
                return (byte) 17;
            case 18:
                return (byte) 18;
            case 19:
                return (byte) 19;
            case 20:
                return (byte) 20;
            case 21:
                return (byte) 21;
            case 22:
                return Pdu.GATT_SERVICE_UUID_PDU_TYPE;
            case 23:
                return (byte) 23;
            case 24:
                return (byte) 24;
            case 25:
                return (byte) 25;
            case 26:
                return (byte) 26;
            case 27:
                return (byte) 27;
            case 28:
                return (byte) 28;
            case 29:
                return (byte) 29;
            case 30:
                return (byte) 30;
            case 31:
                return (byte) 31;
            case 32:
                return (byte) 32;
            case 33:
                return (byte) 33;
            case 34:
                return (byte) 34;
            case 35:
                return (byte) 35;
            case 36:
                return (byte) 36;
            case 37:
                return (byte) 37;
            case 38:
                return (byte) 38;
            case 39:
                return (byte) 39;
            case 40:
                return (byte) 40;
            case 41:
                return (byte) 41;
            case 42:
                return (byte) 42;
            case 43:
                return (byte) 43;
            case 44:
                return (byte) 44;
            case 45:
                return (byte) 45;
            case 46:
                return (byte) 46;
            case 47:
                return (byte) 47;
            case 48:
                return (byte) 48;
            case 49:
                return (byte) 49;
            case 50:
                return (byte) 50;
            case 51:
                return (byte) 51;
            case 52:
                return (byte) 52;
            case 53:
                return (byte) 53;
            case 54:
                return (byte) 54;
            case 55:
                return (byte) 55;
            case 56:
                return (byte) 56;
            case 57:
                return (byte) 57;
            case 58:
                return (byte) 58;
            case 59:
                return (byte) 59;
            case 60:
                return (byte) 60;
            case 61:
                return (byte) 61;
            case 62:
                return (byte) 62;
            case 63:
                return (byte) 63;
            case 64:
                return (byte) 64;
            case 65:
                return (byte) 65;
            case 66:
                return (byte) 66;
            case 67:
                return (byte) 67;
            case 68:
                return (byte) 68;
            case 69:
                return (byte) 69;
            case 70:
                return (byte) 70;
            case 71:
                return (byte) 71;
            case 72:
                return (byte) 72;
            case 73:
                return (byte) 73;
            case 74:
                return (byte) 74;
            case 75:
                return (byte) 75;
            case 76:
                return (byte) 76;
            case 77:
                return (byte) 77;
            case 78:
                return (byte) 78;
            case 79:
                return (byte) 79;
            case 80:
                return (byte) 80;
            case 81:
                return (byte) 81;
            case 82:
                return (byte) 82;
            case 83:
                return (byte) 83;
            case 84:
                return (byte) 84;
            case 85:
                return (byte) 85;
            case 86:
                return (byte) 86;
            case 87:
                return (byte) 87;
            case 88:
                return (byte) 88;
            case 89:
                return (byte) 89;
            case 90:
                return (byte) 90;
            case 91:
                return (byte) 91;
            case 92:
                return (byte) 92;
            case 93:
                return (byte) 93;
            case 94:
                return (byte) 94;
            case 95:
                return (byte) 95;
            case 96:
                return (byte) 96;
            case 97:
                return (byte) 97;
            case 98:
                return (byte) 98;
            default:
                return (byte) 99;
        }
    }

    public static SharedPreferences m12562a(String str, Context context) {
        return context != null ? VERSION.SDK_INT > 10 ? context.getSharedPreferences(str, 4) : context.getSharedPreferences(str, 0) : null;
    }

    public static String m12563a(C4064i c4064i) {
        InetAddress inetAddress = c4064i.f9407a.f9390k;
        C4062b c4062b = c4064i.f9408b;
        int i = c4062b.f9393b;
        return new StringBuilder(inetAddress.getHostAddress()).append(":").append(i).append(":").append(c4062b.f9392a).toString();
    }

    public static Map m12564a(Context context, C4080b c4080b) {
        Map hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("phead", C4078f.m12570b(context, c4080b));
            Object a = C4086b.m12609a(jSONObject.toString().getBytes("UTF-8"), "30a161c4b1bde4eea");
            Object jSONObject2 = jSONObject.toString();
            if (!TextUtils.isEmpty(a)) {
                jSONObject2 = C4090d.m12624a(a.getBytes());
            }
            hashMap.put("data", jSONObject2);
            hashMap.put("pkey", "zz2017");
            hashMap.put("sign", C4090d.m12638d("30a161c4b1bde4eea" + jSONObject.toString() + "30a161c4b1bde4eea"));
        } catch (Exception e) {
        }
        return hashMap;
    }

    public static JSONObject m12565a(Context context) {
        int i = 1;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("pv", 2);
            jSONObject.put("rqd", String.valueOf(System.currentTimeMillis()));
            jSONObject.put("ua", C4090d.m12621a());
            jSONObject.put("gd", C4090d.m12630b(context));
            jSONObject.put("ud", C4090d.m12634c(context));
            jSONObject.put("co", C4090d.m12637d(context));
            jSONObject.put("ln", C4090d.m12629b());
            jSONObject.put("sn", VERSION.RELEASE);
            jSONObject.put("sc", String.valueOf(VERSION.SDK_INT));
            jSONObject.put("cv", String.valueOf(C4090d.m12640e(context)));
            jSONObject.put("cn", C4090d.m12643f(context));
            jSONObject.put("pn", C4090d.m12646g(context));
            jSONObject.put("dn", "1.1.7");
            jSONObject.put("so", 117);
            jSONObject.put("nt", C4090d.m12648h(context));
            jSONObject.put("ss", C4090d.m12650i(context));
            jSONObject.put("rm", String.valueOf(C4090d.m12633c()));
            jSONObject.put("st", C4090d.m12651j(context));
            jSONObject.put("op", C4090d.m12652k(context));
            jSONObject.put("ro", C4090d.m12636d());
            jSONObject.put("cu", C4090d.m12639e());
            jSONObject.put(RequestParams.f9036M, C4090d.m12642f());
            jSONObject.put("tz", C4090d.m12645g());
            jSONObject.put("irt", C4090d.m12649h() ? 1 : 0);
            String str = "igi";
            if (!C4090d.m12626a(context, "com.android.vending")) {
                i = 0;
            }
            jSONObject.put(str, i);
            jSONObject.put("cmd5", C4090d.m12654m(context));
            jSONObject.put("af", "android");
            jSONObject.put("cs", C4090d.m12653l(context));
            jSONObject.put("sr", 2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    public static void m12566a(Context context, String str, Object obj) {
        C4078f.m12567a(context, "pref_gun_common", str, obj);
    }

    public static void m12567a(Context context, String str, String str2, Object obj) {
        if (obj != null) {
            String simpleName = obj.getClass().getSimpleName();
            Editor edit = C4078f.m12562a(str, context).edit();
            if ("String".equals(simpleName)) {
                edit.putString(str2, (String) obj);
            } else if ("Integer".equals(simpleName)) {
                edit.putInt(str2, ((Integer) obj).intValue());
            } else if ("Boolean".equals(simpleName)) {
                edit.putBoolean(str2, ((Boolean) obj).booleanValue());
            } else if ("Float".equals(simpleName)) {
                edit.putFloat(str2, ((Float) obj).floatValue());
            } else if ("Long".equals(simpleName)) {
                edit.putLong(str2, ((Long) obj).longValue());
            }
            edit.apply();
        }
    }

    private static SharedPreferences m12568b(String str, Context context) {
        if (context != null) {
            return VERSION.SDK_INT > 10 ? context.getSharedPreferences(str, 4) : context.getSharedPreferences(str, 0);
        } else {
            throw new IllegalStateException("Context is null.");
        }
    }

    public static String m12569b(Context context) {
        if (!TextUtils.isEmpty(f9436h)) {
            return f9436h;
        }
        CharSequence string = C4078f.m12568b("bt_common", context).getString("key_common_btid", "");
        f9436h = string;
        return !TextUtils.isEmpty(string) ? f9436h : C4078f.m12571c(context);
    }

    private static JSONObject m12570b(Context context, C4080b c4080b) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(C1406b.f2151a, C4090d.m12646g(context));
            jSONObject.put("xd", c4080b.m12593e());
            jSONObject.put("pk", c4080b.m12592d());
            jSONObject.put("cv", C4090d.m12640e(context));
            jSONObject.put(C1406b.f2156f, C4090d.m12637d(context));
            jSONObject.put("ln", C4090d.m12629b());
            jSONObject.put("ut", null);
            jSONObject.put("e", c4080b.m12590b());
            jSONObject.put("d", C4090d.m12653l(context));
            jSONObject.put(RequestParams.f9038U, 2);
            jSONObject.put("ud", "-1");
            jSONObject.put("gd", "-1");
            jSONObject.put("btid", C4078f.m12569b(context));
            jSONObject.put("st", 1);
            jSONObject.put("tc", 0);
            jSONObject.put("sv", 117);
        } catch (Throwable th) {
        }
        return jSONObject;
    }

    private static String m12571c(Context context) {
        String uuid;
        synchronized ("4ec81eae-5e14-481f-9a71-90b9ba4f2388".intern()) {
            if (TextUtils.isEmpty(f9436h)) {
                uuid = UUID.randomUUID().toString();
                C4078f.m12568b("bt_common", context).edit().putString("key_common_btid", uuid).commit();
                f9436h = uuid;
            } else {
                uuid = f9436h;
            }
        }
        return uuid;
    }

    public static String m12572h() {
        return C4078f.m12562a("pref_gun_common", C4053c.m12503a().m12515b()).getString("_proxy_address", "");
    }

    public static int m12573i() {
        return C4078f.m12562a("pref_gun_common", C4053c.m12503a().m12515b()).getInt("_proxy_port", -1);
    }

    public static int m12574j() {
        Object string = C4078f.m12562a("pref_gun_common", C4053c.m12503a().m12515b()).getString("_proxy_24_hours_count", "");
        if (TextUtils.isEmpty(string)) {
            return 0;
        }
        String[] split = string.split("\\|");
        if (split == null || split.length != 2) {
            return 0;
        }
        return System.currentTimeMillis() - Long.parseLong(split[0]) < 86400000 ? Integer.parseInt(split[1]) : 0;
    }

    public static long m12575k() {
        return C4078f.m12562a("pref_gun_common", C4053c.m12503a().m12515b()).getLong("time_server_close", -1);
    }

    public static String m12576l() {
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean m12577a() {
        return this.f9437a == 1;
    }

    public boolean m12578b() {
        return this.f9438b == 1;
    }

    public int m12579c() {
        return this.f9439c;
    }

    public int m12580d() {
        return this.f9440d * 1000;
    }

    public long m12581e() {
        return ((long) (this.f9441e * 3600)) * 1000;
    }

    public boolean m12582f() {
        return this.f9442f == 1;
    }

    public long m12583g() {
        return ((long) this.f9443g) * 1000;
    }
}
