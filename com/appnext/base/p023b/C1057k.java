package com.appnext.base.p023b;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlarmManager;
import android.app.AppOpsManager;
import android.app.PendingIntent;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import com.appnext.base.C1033a;
import com.appnext.base.C1061b;
import com.appnext.base.operations.C1063a;
import com.appnext.base.operations.C1066d;
import com.appnext.base.operations.imp.cdm;
import com.appnext.base.operations.imp.rcd;
import com.appnext.base.p019a.C1017a;
import com.appnext.base.p019a.p021b.C1020b;
import com.appnext.base.p019a.p021b.C1021c;
import com.appnext.base.p023b.C1042c.C1041a;
import com.appnext.base.receivers.C1078c;
import com.appnext.base.services.OperationService;
import com.appnext.base.services.ReceiverService;
import com.appnext.core.C1128g;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpRetryException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.SortedMap;
import java.util.TimeZone;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class C1057k {
    private static final String TAG = "SdkHelper";
    public static final int kA = 120000;
    private static final long kB = 1000;
    private static final long kC = 60000;
    private static final long kD = 3600000;
    private static final long kE = 86400000;
    private static Random kF = new Random();

    public static List<ApplicationInfo> m2165a(PackageManager packageManager, int i) {
        List<ApplicationInfo> arrayList = new ArrayList();
        for (ApplicationInfo applicationInfo : packageManager.getInstalledApplications(128)) {
            if (applicationInfo != null && (applicationInfo.flags & 1) == i) {
                arrayList.add(applicationInfo);
            }
        }
        return arrayList;
    }

    public static boolean m2170a(String str, String str2, C1021c c1021c) {
        Object obj = -1;
        try {
            switch (str2.hashCode()) {
                case 570418373:
                    if (str2.equals(C1042c.jE)) {
                        obj = null;
                        break;
                    }
                    break;
                case 1852089416:
                    if (str2.equals(C1042c.jB)) {
                        int i = 1;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    return ((C1063a) Class.forName(C1066d.getOperationClassName(str)).getConstructor(new Class[]{C1021c.class, Bundle.class}).newInstance(new Object[]{c1021c, null})).hasPermission();
                case 1:
                    return ((C1078c) Class.forName(ReceiverService.getOperationClassName(str)).getConstructor(new Class[0]).newInstance(new Object[0])).hasPermission();
                default:
                    return false;
            }
        } catch (InvocationTargetException e) {
            e.getCause().printStackTrace();
            C1061b.m2191a(e.getCause());
            return false;
        } catch (ClassNotFoundException e2) {
            return false;
        } catch (Throwable th) {
            C1061b.m2191a(th);
            return false;
        }
    }

    public static boolean m2169a(Class cls) {
        try {
            if (C1043d.getContext().getPackageManager().queryIntentServices(new Intent(C1043d.getContext(), cls), 65536).size() > 0) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            C1128g.m2351c(th);
            return false;
        }
    }

    public static List<String> m2179m(Context context) {
        List<String> arrayList = new ArrayList();
        if (context == null) {
            return arrayList;
        }
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(C1404b.aw);
            if (VERSION.SDK_INT < 21) {
                if (C1045f.m2133g(C1043d.getContext(), "android.permission.GET_TASKS")) {
                    List runningTasks = activityManager.getRunningTasks(3);
                    if (!(runningTasks == null || runningTasks.isEmpty())) {
                        arrayList.add(((RunningTaskInfo) runningTasks.get(0)).baseActivity.getPackageName());
                    }
                }
                return arrayList;
            }
            if (VERSION.SDK_INT >= 21 && C1057k.m2180n(context.getApplicationContext())) {
                UsageStatsManager usageStatsManager = (UsageStatsManager) context.getSystemService("usagestats");
                long currentTimeMillis = System.currentTimeMillis();
                List<UsageStats> queryUsageStats = usageStatsManager.queryUsageStats(4, currentTimeMillis - 300000, currentTimeMillis);
                if (queryUsageStats == null) {
                    return arrayList;
                }
                ListIterator listIterator = queryUsageStats.listIterator();
                while (listIterator.hasNext()) {
                    UsageStats usageStats = (UsageStats) listIterator.next();
                    if (VERSION.SDK_INT >= 23 && usageStatsManager.isAppInactive(usageStats.getPackageName())) {
                        listIterator.remove();
                    }
                }
                if (queryUsageStats.size() > 0) {
                    SortedMap treeMap = new TreeMap();
                    for (UsageStats usageStats2 : queryUsageStats) {
                        treeMap.put(Long.valueOf(usageStats2.getLastTimeUsed()), usageStats2);
                    }
                    if (!treeMap.isEmpty()) {
                        arrayList.add(((UsageStats) treeMap.get(treeMap.lastKey())).getPackageName());
                    }
                }
            }
            return arrayList;
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
    }

    public static void cH() {
        ActivityManager activityManager = (ActivityManager) C1043d.getContext().getSystemService(C1404b.aw);
        C1043d.getContext().getPackageManager();
        TreeMap treeMap = new TreeMap(Collections.reverseOrder());
        List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                Log.e("Running app", "Name : - " + runningAppProcessInfo.processName + " Importance - " + runningAppProcessInfo.importance + " Lru - " + runningAppProcessInfo.lru + " LastTrimLevel - " + runningAppProcessInfo.lastTrimLevel + " ImportanceReasonCode - " + runningAppProcessInfo.importanceReasonCode + " importanceReasonComponent - " + runningAppProcessInfo.importanceReasonComponent);
            }
        }
    }

    @TargetApi(21)
    public static boolean m2180n(Context context) {
        return ((AppOpsManager) context.getSystemService("appops")).checkOpNoThrow("android:get_usage_stats", Process.myUid(), context.getPackageName()) == 0;
    }

    public static long aF(String str) {
        return C1048i.cy().getLong(str + C1048i.ke, 0);
    }

    public static void m2175d(String str, String str2, C1041a c1041a) {
        List arrayList = new ArrayList();
        arrayList.add(new C1020b(str, str2, c1041a.getType()));
        C1057k.m2172b(str, arrayList);
    }

    public static void m2172b(String str, List<C1020b> list) {
        Intent intent = new Intent(C1043d.getContext(), OperationService.class);
        intent.putExtra(C1042c.jv, str);
        JSONArray a = C1040b.m2125a((List) list, false);
        if (a != null && a.length() != 0) {
            intent.putExtra("data", a.toString());
            intent.putExtra(C1042c.jL, rcd.class.getSimpleName());
            C1057k.m2166a(C1043d.getContext(), intent);
        }
    }

    public static long m2177i(String str, String str2) {
        try {
            if (!TextUtils.isDigitsOnly(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
                return 0;
            }
            long parseLong = Long.parseLong(str);
            if ("time".equalsIgnoreCase(str2)) {
                if (str.length() == 4) {
                    return C1057k.m2161a(Integer.parseInt(str.substring(0, 2)), Integer.parseInt(str.substring(2, 4)), 60, 60);
                }
                return -1;
            } else if (C1042c.jw.equalsIgnoreCase(str2)) {
                return kB * parseLong;
            } else {
                if (C1042c.jx.equalsIgnoreCase(str2)) {
                    return 60000 * parseLong;
                }
                if (C1042c.jy.equalsIgnoreCase(str2)) {
                    return kD * parseLong;
                }
                if (C1042c.jz.equalsIgnoreCase(str2)) {
                    return kE * parseLong;
                }
                return -1;
            }
        } catch (Throwable th) {
            C1061b.m2191a(th);
            return -1;
        }
    }

    public static long m2174c(int i, int i2) {
        Calendar instance = Calendar.getInstance();
        instance.set(11, i);
        instance.set(12, i2);
        instance.set(13, 0);
        if (new Date().after(instance.getTime())) {
            instance.add(5, 1);
        }
        return instance.getTimeInMillis();
    }

    public static long m2161a(int i, int i2, int i3, int i4) {
        return C1057k.m2174c(i, i2) + (((long) (kF.nextInt(i3 + i4) - i3)) * kB);
    }

    public static void m2181o(Context context) {
        try {
            List bm = C1017a.aM().aR().bm();
            if (bm == null || bm.size() != 0) {
                C1033a.aI().m2113a(C1017a.aM().aR().bm());
                C1033a.aI().aJ();
                return;
            }
            C1017a.aM().aR().m2100a(new JSONObject("{ \"status\": \"on\", \"sample\": \"1\", \"sample_type\": \"hour\", \"cycle\": \"1\", \"cycle_type\": \"interval\", \"exact\": \"false\", \"key\": \"cdm\" }"));
            Intent intent = new Intent(context, OperationService.class);
            intent.putExtra(C1042c.jv, cdm.class.getSimpleName());
            C1057k.m2166a(context, intent);
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
    }

    public static void m2167a(Context context, Class cls, long j, C1021c c1021c) {
        if (context != null && c1021c != null && cls != null) {
            long j2;
            if ("time".equalsIgnoreCase(c1021c.bc())) {
                j2 = kE;
            } else {
                j2 = C1057k.m2177i(c1021c.bb(), c1021c.bc());
            }
            if (j2 != -1) {
                AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
                Intent intent = new Intent(context, cls);
                intent.putExtra(C1042c.jv, c1021c.getKey());
                int hashCode = c1021c.bf().hashCode();
                C1058l.m2184k(" *** alarm *** ", String.valueOf(c1021c.bf()));
                PendingIntent service = PendingIntent.getService(context, hashCode, intent, 134217728);
                if (!c1021c.bg()) {
                    alarmManager.setInexactRepeating(1, j, j2, service);
                } else if (VERSION.SDK_INT >= 23) {
                    alarmManager.setExactAndAllowWhileIdle(0, j, service);
                } else if (VERSION.SDK_INT >= 19) {
                    alarmManager.setExact(0, j, service);
                } else {
                    alarmManager.set(0, j, service);
                }
                alarmManager.setInexactRepeating(1, j, j2, service);
            }
        }
    }

    public static boolean m2182p(Context context) throws Exception {
        Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
        return advertisingIdInfo != null && advertisingIdInfo.isLimitAdTrackingEnabled();
    }

    public static boolean m2183q(Context context) {
        try {
            Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
            if (advertisingIdInfo == null || advertisingIdInfo.isLimitAdTrackingEnabled()) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            C1061b.m2191a(th);
            C1058l.m2187n(TAG, th.toString());
            return true;
        }
    }

    public static boolean m2173b(String str, Map<String, String> map) {
        C1021c av = C1044e.cs().av(str);
        if (av == null || C1042c.jG.equalsIgnoreCase(av.ba())) {
            return true;
        }
        if (map.isEmpty()) {
            return true;
        }
        String str2 = C1042c.cp() + "/data";
        HashMap hashMap = new HashMap();
        Object u = C1128g.m2358u(C1043d.getContext());
        if (TextUtils.isEmpty(u)) {
            u = C1048i.cy().getString(C1048i.ki, "");
        }
        if (TextUtils.isEmpty(u)) {
            return false;
        }
        hashMap.put("aid", u);
        hashMap.put("cuid", u + BridgeUtil.UNDERLINE_STR + System.currentTimeMillis());
        hashMap.put("lvid", "4.6.8");
        try {
            hashMap.put("localdate", C1057k.m2164a(new Date()));
            hashMap.put("timezone", C1057k.cI());
            hashMap.put("app_package", C1043d.getPackageName());
        } catch (Throwable th) {
            C1061b.m2191a(th);
            hashMap.put("app_package", "");
        }
        String str3 = "";
        for (Entry entry : map.entrySet()) {
            hashMap.put((String) entry.getKey(), (String) entry.getValue());
        }
        C1058l.m2184k(str, "-------Sending to server data for key = " + str + " ----------");
        for (Entry entry2 : hashMap.entrySet()) {
            C1058l.m2184k(str, "---- " + ((String) entry2.getKey()) + " : " + ((String) entry2.getValue()) + " ----");
        }
        try {
            byte[] a = C1128g.m2343a(str2, hashMap, false, 15000, C1041a.HashMap);
            if (a != null) {
                C1058l.m2184k(str, "result send data: " + new String(a, "UTF-8"));
            }
            return true;
        } catch (HttpRetryException e) {
            C1058l.m2187n(str, "(Type:HttpRetryException)" + e.getMessage() + "  " + e.responseCode());
            return false;
        } catch (Throwable th2) {
            C1058l.m2187n(str, "(Type:Throwable) " + th2.getMessage());
            return false;
        }
    }

    public static String cI() {
        Calendar instance = Calendar.getInstance(TimeZone.getDefault(), Locale.US);
        int i = (instance.get(16) + instance.get(15)) / 60000;
        char c = '+';
        if (i < 0) {
            c = '-';
            i = -i;
        }
        StringBuilder stringBuilder = new StringBuilder(9);
        stringBuilder.append("GMT");
        stringBuilder.append(c);
        C1057k.m2168a(stringBuilder, 2, i / 60);
        stringBuilder.append(':');
        C1057k.m2168a(stringBuilder, 2, i % 60);
        return stringBuilder.toString();
    }

    private static void m2168a(StringBuilder stringBuilder, int i, int i2) {
        String num = Integer.toString(i2);
        for (int i3 = 0; i3 < i - num.length(); i3++) {
            stringBuilder.append('0');
        }
        stringBuilder.append(num);
    }

    public static String m2164a(Date date) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(new SimpleDateFormat("EEE MMM dd HH:mm:ss", Locale.US).format(date));
        stringBuilder.append(" ");
        stringBuilder.append(C1057k.cI());
        stringBuilder.append(" ");
        stringBuilder.append(new SimpleDateFormat("yyyy", Locale.US).format(date));
        return stringBuilder.toString();
    }

    public static void m2178j(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            C1048i.cy().putString(str + C1048i.kh, str2);
        }
    }

    public static String aG(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return C1048i.cy().getString(str + C1048i.kh, null);
    }

    public static Location m2162a(double d, double d2, float f) {
        Location location = new Location("");
        location.setLatitude(d);
        location.setLongitude(d2);
        location.setAccuracy(f);
        return location;
    }

    public static void m2166a(Context context, Intent intent) {
        try {
            context.startService(intent);
        } catch (Throwable th) {
        }
    }

    public static void m2176e(String str, String str2, C1041a c1041a) {
        C1017a.aM().aP().m2096b(new C1020b(str, str2, c1041a.getType()));
    }

    public static Object m2163a(String str, C1041a c1041a) {
        try {
            List ad = C1017a.aM().aP().ad(str);
            if (!(ad == null || ad.isEmpty())) {
                return C1057k.m2171b(((C1020b) ad.get(0)).aY(), c1041a);
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public static Object m2171b(String str, C1041a c1041a) {
        try {
            switch (c1041a) {
                case Integer:
                    return Integer.valueOf(str);
                case Double:
                    return Double.valueOf(str);
                case Long:
                    return Long.valueOf(str);
                case Boolean:
                    return Boolean.valueOf(str);
                case Set:
                    return new HashSet(Arrays.asList(str.split(",")));
                case JSONArray:
                    return new JSONArray(str);
                case JSONObject:
                    return new JSONObject(str);
                default:
                    return str;
            }
        } catch (Throwable th) {
            return null;
        }
    }
}
