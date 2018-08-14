package com.core42matters.android.registrar;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.google.android.gms.common.internal.Constants;
import dalvik.system.DexFile;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class PackageUtils {
    static final Comparator<PackageInfo> PACKAGE_COMPARATOR = new C15351();
    static final Set<String> SHORTHAND_LIBS = new HashSet(Arrays.asList(new String[]{"android.support.design.", "android.support.v4.", "android.support.v7.", "android.support.v8.", "android.support.v13.", "android.support.v14.", "android.support.v17.", "com.fasterxml.jackson.", "com.google.gson.", "com.google.zxing."}));

    static class C15351 implements Comparator<PackageInfo> {
        C15351() {
        }

        public int compare(PackageInfo lhs, PackageInfo rhs) {
            return lhs.packageName.compareTo(rhs.packageName);
        }
    }

    private PackageUtils() {
        throw new AssertionError();
    }

    static PackageInfo packageInfo(Context context) {
        return packageInfo(context.getPackageManager(), context.getPackageName());
    }

    static PackageInfo packageInfo(PackageManager pm, String pname) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = pm.getPackageInfo(pname, 4294);
        } catch (NameNotFoundException e) {
        }
        if (packageInfo != null) {
            return packageInfo;
        }
        throw new NullPointerException("Cannot get the package info of " + pname);
    }

    static List<PackageInfo> getInstalledApps(Context context, boolean considerUpdatedSystemApp, boolean getPermissions) {
        int i;
        List<PackageInfo> nonSystemApps = new ArrayList();
        PackageManager pm = context.getPackageManager();
        if (getPermissions) {
            i = 4096;
        } else {
            i = 0;
        }
        try {
            for (PackageInfo info : pm.getInstalledPackages(r6)) {
                try {
                    boolean isdownloaded;
                    ApplicationInfo appInfo = info.applicationInfo;
                    if ((appInfo.flags & 1) == 0) {
                        isdownloaded = true;
                    } else {
                        isdownloaded = false;
                    }
                    if (considerUpdatedSystemApp) {
                        if ((appInfo.flags & 128) != 0) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        isdownloaded |= i;
                    }
                    if (isdownloaded) {
                        nonSystemApps.add(info);
                    }
                } catch (Exception e) {
                }
            }
        } catch (Exception e2) {
        }
        Collections.sort(nonSystemApps, PACKAGE_COMPARATOR);
        return nonSystemApps;
    }

    static boolean isDownloaded(ApplicationInfo info) {
        return (info.flags & 129) == 0;
    }

    static String shorthand(String raw) {
        for (String s : SHORTHAND_LIBS) {
            if (raw.startsWith(s)) {
                return s.substring(0, s.length() - 1);
            }
        }
        return raw;
    }

    static List<String> digIntoApk(Context context, ApplicationInfo applicationInfo) {
        List<String> classList = new ArrayList();
        String filename;
        try {
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(applicationInfo.sourceDir)));
            byte[] buffer = new byte[2048];
            String sessionID = UUID.randomUUID().toString();
            String ownPrefix = applicationInfo.packageName + ".";
            while (true) {
                ZipEntry ze = zis.getNextEntry();
                if (ze == null) {
                    break;
                }
                filename = ze.getName();
                if (!ze.isDirectory() && filename.startsWith("classes") && filename.endsWith(".dex")) {
                    filename = sessionID + "-" + filename;
                    FileOutputStream fileOutputStream = context.openFileOutput(filename, 0);
                    while (true) {
                        int count = zis.read(buffer);
                        if (count == -1) {
                            break;
                        }
                        fileOutputStream.write(buffer, 0, count);
                    }
                    fileOutputStream.close();
                    DexFile dexFile = new DexFile(context.getFileStreamPath(filename));
                    Enumeration<String> entries = dexFile.entries();
                    while (entries.hasMoreElements()) {
                        String entry = (String) entries.nextElement();
                        if (!(entry.contains("$") || entry.startsWith(ownPrefix))) {
                            classList.add(entry);
                        }
                    }
                    dexFile.close();
                    context.deleteFile(filename);
                }
                zis.closeEntry();
            }
            zis.close();
        } catch (IOException e) {
        } catch (Throwable th) {
            context.deleteFile(filename);
        }
        return classList;
    }

    static Set<String> findPackages(List<String> classList) {
        String p;
        Collections.sort(classList);
        Set<String> packageNames = new HashSet();
        String lastPackageName = null;
        String skip = null;
        int numClasses = 0;
        for (String entry : classList) {
            String currentPackageName = entry.substring(0, entry.lastIndexOf(46));
            if (!(currentPackageName.equals("android") || TextUtils.equals(skip, currentPackageName))) {
                if (currentPackageName.equals(lastPackageName)) {
                    numClasses++;
                } else {
                    if (lastPackageName != null) {
                        if (!currentPackageName.startsWith(lastPackageName) || numClasses <= 2) {
                            p = lastPackageName.substring(lastPackageName.lastIndexOf(46) + 1);
                            if (p.length() != 1 || p.charAt(0) < 'a' || p.charAt(0) > 'z') {
                                packageNames.add(shorthand(lastPackageName));
                            }
                        } else {
                            skip = currentPackageName;
                        }
                    }
                    lastPackageName = currentPackageName;
                    numClasses = 1;
                }
            }
        }
        if (lastPackageName != null) {
            p = lastPackageName.substring(lastPackageName.lastIndexOf(46) + 1);
            if (p.length() != 1 || p.charAt(0) < 'a' || p.charAt(0) > 'z') {
                packageNames.add(shorthand(lastPackageName));
            }
        }
        return packageNames;
    }

    static JSONObject makeSnapshot(Context context, Set<String> packagesForPermission, Set<String> packagesForClasspath) {
        long freeBytesAppInternal = new File(context.getFilesDir().getAbsoluteFile().toString()).getFreeSpace();
        PackageManager pm = context.getPackageManager();
        boolean z = packagesForPermission != null && packagesForPermission.size() > 0;
        List<PackageInfo> apps = getInstalledApps(context, true, z);
        JSONObject snapshot = new JSONObject();
        try {
            Locale l = Locale.getDefault();
            snapshot.put(SchemaSymbols.ATTVAL_LANGUAGE, l.getLanguage());
            snapshot.put("country", l.getCountry());
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            snapshot.put(Constants.PARAM_DENSITY, (double) metrics.density);
            snapshot.put("widthPixels", metrics.widthPixels);
            snapshot.put("heightPixels", metrics.heightPixels);
            long size = fsSize(Environment.getRootDirectory().getAbsolutePath());
            if (size >= 0) {
                snapshot.put("internalAvailableKB", size);
            }
            size = fsSize(Environment.getExternalStorageDirectory().getAbsolutePath());
            if (size >= 0) {
                snapshot.put("externalAvailableKB", size);
            }
            snapshot.put("device", Build.DEVICE);
            snapshot.put("model", Build.MODEL);
            snapshot.put("manufacturer", Build.MANUFACTURER);
            snapshot.put("product", Build.PRODUCT);
            snapshot.put("brand", Build.BRAND);
            snapshot.put("sdk", VERSION.SDK_INT);
            JSONArray appsJson = new JSONArray();
            for (PackageInfo info : apps) {
                JSONObject app = new JSONObject();
                boolean updatedSystemApp = (info.applicationInfo.flags & 128) != 0;
                app.put("pn", info.packageName);
                app.put("t", info.applicationInfo.loadLabel(pm));
                app.put("usa", updatedSystemApp);
                app.put("vc", info.versionCode);
                app.put("vn", info.versionName);
                app.put("fit", info.firstInstallTime);
                app.put("lut", info.lastUpdateTime);
                String installer = pm.getInstallerPackageName(info.packageName);
                if (!TextUtils.isEmpty(installer)) {
                    app.put("inst", installer);
                    if (freeBytesAppInternal > 50331648 && packagesForClasspath != null) {
                        if (packagesForClasspath.contains(info.packageName)) {
                            try {
                                app.put("classpath", new JSONArray(findPackages(digIntoApk(context, info.applicationInfo))));
                            } catch (Exception e) {
                                Logger.m3322w(e.getMessage());
                            } catch (JSONException e2) {
                                return null;
                            }
                        }
                    }
                    if (packagesForPermission != null) {
                        if (packagesForPermission.contains(info.packageName) && info.requestedPermissions != null && info.requestedPermissions.length > 0) {
                            app.put("permissions", new JSONArray(Arrays.asList(info.requestedPermissions)));
                        }
                    } else {
                        continue;
                    }
                }
                appsJson.put(app);
            }
            snapshot.put("apps", appsJson);
            return snapshot;
        } catch (JSONException e22) {
            return null;
        } catch (Exception e3) {
            e3.printStackTrace();
            return snapshot;
        }
    }

    static long fsSize(String path) {
        try {
            StatFs statFs = new StatFs(path);
            return (((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    static void validateManifest(Context context) throws SecurityException {
        if (packageInfo(context) == null) {
            throw new NullPointerException("Package info missing for " + context.getPackageName());
        }
        PackageManager pm = context.getPackageManager();
        if (pm.checkPermission("android.permission.INTERNET", context.getPackageName()) != 0) {
            throw new SecurityException("No android.permission.INTERNET granted");
        } else if (pm.checkPermission("android.permission.ACCESS_NETWORK_STATE", context.getPackageName()) != 0) {
            throw new SecurityException("No android.permission.ACCESS_NETWORK_STATE granted");
        }
    }
}
