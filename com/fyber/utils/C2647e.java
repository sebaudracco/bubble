package com.fyber.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import android.webkit.CookieManager;
import com.fyber.cache.internal.C2559f;
import com.fyber.utils.cookies.a;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.HttpCookie;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: FyberPersistence */
public final class C2647e {
    public static void m8471a(Context context) {
        Exception e;
        String str;
        StringBuilder stringBuilder;
        String str2;
        SharedPreferences sharedPreferences = context.getSharedPreferences("migration", 0);
        if (!sharedPreferences.getBoolean("successfulMigration", false)) {
            C2647e.m8470a(context, "ConfigurationRequester", "MediationConfigurationNetworkOperation");
            C2647e.m8470a(context, "SPStatistics", "FyberStatistics");
            C2647e.m8470a(context, "SponsorPayPublisherState", "FyberPreferences");
            C2647e.m8470a(context, "SponsorPayCookiePrefsFile", "FyberCookiePrefsFile");
            C2647e.m8470a(context, "SponsorPayAdvertiserState", "FyberPreferences");
            File file = new File(context.getFilesDir(), "FyberCacheStorage");
            File file2 = new File(context.getFilesDir(), "SPCacheStorage");
            if (file2.exists() && file2.renameTo(file)) {
                FyberLogger.m8451i("FyberPersistence", "File:" + file2.getName() + " has been successfully renamed.");
            }
            FyberLogger.m8448d("FyberPersistence", "Checking previous cache directory");
            if ("mounted".equals(Environment.getExternalStorageState())) {
                boolean z;
                if (context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    FyberLogger.m8448d("FyberPersistence", "External write storage permission granted");
                    File file3 = new File(Environment.getExternalStorageDirectory(), "VideoCache" + File.separator + context.getPackageName().hashCode());
                    if (file3.exists()) {
                        FyberLogger.m8448d("FyberPersistence", "Application cache directory exists, deleting...");
                        C2647e.m8472a(file3);
                        file3.delete();
                    }
                    File[] listFiles = file3.getParentFile().listFiles();
                    if (listFiles != null && listFiles.length == 0) {
                        FyberLogger.m8448d("FyberPersistence", "Cache directory empty, deleting...");
                        file3.getParentFile().delete();
                    }
                    sharedPreferences.edit().putBoolean("successfulMigration", true).apply();
                }
            }
            file = new File(context.getFilesDir(), "VideoCache");
            if (file.exists()) {
                FyberLogger.m8448d("FyberPersistence", "Internal cache directory exists, deleting...");
                C2647e.m8472a(file);
                file.delete();
            }
            sharedPreferences.edit().putBoolean("successfulMigration", true).apply();
        }
        if (!sharedPreferences.getBoolean("cookiesMigrated", false)) {
            C2647e.m8473b(context);
            sharedPreferences.edit().putBoolean("cookiesMigrated", true).apply();
        }
        if (!sharedPreferences.getBoolean("protocolMigration", false)) {
            File file4 = new File(context.getFilesDir().getAbsolutePath(), "FyberCacheStorage");
            if (file4.exists()) {
                InputStream fileInputStream = new FileInputStream(file4);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                try {
                    HashMap hashMap = (HashMap) objectInputStream.readObject();
                    SharedPreferences sharedPreferences2 = context.getSharedPreferences("FyberCacheStorage", 0);
                    sharedPreferences2.edit().putString("FyberCacheStorage", C2559f.m8167a(hashMap.values())).apply();
                } catch (IOException e2) {
                    e = e2;
                    try {
                        FyberLogger.m8448d("FyberPersistence", "There was an exception migrating the old cache information - " + e.getMessage());
                        objectInputStream.close();
                        fileInputStream.close();
                        str = "FyberPersistence";
                        stringBuilder = new StringBuilder("Removing old cache file info - ");
                        if (file4.delete()) {
                            str2 = "unsuccessful";
                        } else {
                            str2 = "successful";
                        }
                        FyberLogger.m8448d(str, stringBuilder.append(str2).toString());
                    } catch (IOException e3) {
                        FyberLogger.m8448d("FyberPersistence", "There was an exception migrating the old cache information - " + e3.getMessage());
                    }
                    sharedPreferences.edit().putBoolean("protocolMigration", true).apply();
                } catch (ClassNotFoundException e4) {
                    e = e4;
                    FyberLogger.m8448d("FyberPersistence", "There was an exception migrating the old cache information - " + e.getMessage());
                    objectInputStream.close();
                    fileInputStream.close();
                    str = "FyberPersistence";
                    stringBuilder = new StringBuilder("Removing old cache file info - ");
                    if (file4.delete()) {
                        str2 = "successful";
                    } else {
                        str2 = "unsuccessful";
                    }
                    FyberLogger.m8448d(str, stringBuilder.append(str2).toString());
                    sharedPreferences.edit().putBoolean("protocolMigration", true).apply();
                }
                objectInputStream.close();
                fileInputStream.close();
                str = "FyberPersistence";
                stringBuilder = new StringBuilder("Removing old cache file info - ");
                if (file4.delete()) {
                    str2 = "successful";
                } else {
                    str2 = "unsuccessful";
                }
                FyberLogger.m8448d(str, stringBuilder.append(str2).toString());
            }
            sharedPreferences.edit().putBoolean("protocolMigration", true).apply();
        }
    }

    private static SharedPreferences m8470a(Context context, String str, String str2) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
        Map all = sharedPreferences.getAll();
        SharedPreferences sharedPreferences2 = context.getSharedPreferences(str2, 0);
        if (!all.isEmpty()) {
            Editor edit = sharedPreferences2.edit();
            for (Entry entry : sharedPreferences.getAll().entrySet()) {
                String replace;
                Object value = entry.getValue();
                String str3 = (String) entry.getKey();
                if (str3.contains("SponsorPayAdvertiserState")) {
                    replace = str3.replace("SponsorPayAdvertiserState", "AdvertiserAnswerReceived");
                } else {
                    replace = str3;
                }
                if (value instanceof String) {
                    edit.putString(replace, (String) value);
                } else if (value instanceof Integer) {
                    edit.putInt(replace, ((Integer) value).intValue());
                } else if (value instanceof Long) {
                    edit.putLong(replace, ((Long) value).longValue());
                } else if (value instanceof Float) {
                    edit.putFloat(replace, ((Float) value).floatValue());
                } else if (value instanceof Boolean) {
                    edit.putBoolean(replace, ((Boolean) value).booleanValue());
                }
            }
            edit.commit();
            sharedPreferences.edit().clear().apply();
        }
        File file = new File(context.getApplicationInfo().dataDir + File.separator + "shared_prefs" + File.separator + str + ".xml");
        if (file.exists() && file.delete()) {
            FyberLogger.m8451i("FyberPersistence", "SharedPreferences " + str + ", have been deleted.");
        }
        return sharedPreferences2;
    }

    private static void m8472a(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File delete : listFiles) {
                delete.delete();
            }
        }
    }

    private static void m8473b(Context context) {
        ConcurrentHashMap a = new a(context).a();
        String cookie = CookieManager.getInstance().getCookie("fyber.com");
        for (Entry entry : a.entrySet()) {
            URI uri = (URI) entry.getKey();
            for (HttpCookie httpCookie : (List) entry.getValue()) {
                int i;
                CharSequence charSequence = httpCookie.getName() + "=";
                if (cookie == null || !cookie.contains(charSequence)) {
                    i = 0;
                } else {
                    i = 1;
                }
                if (i == 0) {
                    CookieManager.getInstance().setCookie(uri.toString(), httpCookie.toString());
                }
            }
        }
        context.getSharedPreferences("FyberCookiePrefsFile", 0).edit().clear().apply();
    }
}
