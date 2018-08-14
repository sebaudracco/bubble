package com.inmobi.sdk;

import android.annotation.SuppressLint;
import android.content.Context;
import com.inmobi.commons.core.configs.C3122c;
import com.inmobi.commons.core.p116c.C3114a;
import com.inmobi.commons.core.utilities.info.C3163e;
import com.inmobi.commons.core.utilities.p117a.C3149a;
import com.inmobi.rendering.mraid.C3227f;
import com.inmobi.signals.C3248a;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: FileManager */
final class C3242a {
    @SuppressLint({"SdCardPath"})
    public static boolean m10807a(Context context) {
        int i;
        List asList = Arrays.asList(new String[]{"carbpreference", "IMAdMLtvpRuleCache", "inmobiAppAnalyticsSession", "aeskeygenerate", "impref", "IMAdTrackerStatusUpload", "IMAdMMediationCache", "inmobiAppAnalyticsAppId", "inmobiAppAnalyticsSession", "inmobisdkaid", "IMAdTrackerStatusUpload", "testAppPref"});
        for (i = 0; i < asList.size(); i++) {
            File file = new File("/data/data/" + context.getPackageName() + "/shared_prefs/" + ((String) asList.get(i)) + ".xml");
            if (file.exists()) {
                file.delete();
            }
        }
        asList = Arrays.asList(new String[]{C3248a.m10859a(), C3122c.m10194a(), C3149a.m10364a(), C3227f.m10773a(), C3163e.m10444a()});
        for (i = 0; i < asList.size(); i++) {
            file = new File("/data/data/" + context.getPackageName() + "/shared_prefs/" + ((String) asList.get(i)) + ".xml");
            if (file.exists()) {
                file.delete();
            }
        }
        asList = Arrays.asList(new String[]{"inmobi.cache", "inmobi.cache.data", "inmobi.cache.data.events.number", "inmobi.cache.data.events.timestamp"});
        for (i = 0; i < asList.size(); i++) {
            if (context.getCacheDir() != null) {
                file = new File(context.getCacheDir(), (String) asList.get(i));
                if (file.exists()) {
                    file.delete();
                }
            }
        }
        asList = Arrays.asList(new String[]{"eventlog", "imai_click_events"});
        for (i = 0; i < asList.size(); i++) {
            if (context.getDir("data", 0) != null) {
                file = new File(context.getDir("data", 0), (String) asList.get(i));
                if (file.exists()) {
                    file.delete();
                }
            }
        }
        if (C3242a.m10809b(context).size() != 0) {
            return true;
        }
        return false;
    }

    private static boolean m10808a(Context context, String str) {
        File databasePath = context.getDatabasePath(str);
        return databasePath == null || !databasePath.exists() || context.deleteDatabase(str);
    }

    private static Set<String> m10806a() {
        Set<String> hashSet = new HashSet();
        hashSet.add("adcache.db");
        hashSet.add("appengage.db");
        hashSet.add("im.db");
        hashSet.add("ltvp.db");
        hashSet.add("analytics.db");
        hashSet.add("com.im.db");
        return hashSet;
    }

    public static List<String> m10809b(Context context) {
        List<String> arrayList = new ArrayList();
        Set a = C3242a.m10806a();
        String[] databaseList = context.databaseList();
        if (databaseList != null && databaseList.length > 0) {
            for (String str : databaseList) {
                if (a.contains(str) && !C3242a.m10808a(context, str)) {
                    arrayList.add(str);
                } else if (!(!str.matches("com\\.im_([0-9]+\\.){3}db") || str.equals(C3114a.f7621a) || C3242a.m10808a(context, str))) {
                    arrayList.add(str);
                }
            }
        }
        return arrayList;
    }
}
