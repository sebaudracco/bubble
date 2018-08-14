package com.adcolony.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.widget.Toast;
import com.adcolony.sdk.aa.C0595a;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.google.android.gms.common.util.AndroidUtilsLight;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.CRC32;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class az {
    static final int f754a = 128;
    static ExecutorService f755b = Executors.newSingleThreadExecutor();

    static class C0639a {
        double f752a;
        double f753b = ((double) System.currentTimeMillis());

        C0639a(double d) {
            m865a(d);
        }

        void m864a() {
            m865a(this.f752a);
        }

        void m865a(double d) {
            this.f752a = d;
            this.f753b = (((double) System.currentTimeMillis()) / 1000.0d) + this.f752a;
        }

        boolean m866b() {
            return m867c() == 0.0d;
        }

        double m867c() {
            double currentTimeMillis = this.f753b - (((double) System.currentTimeMillis()) / 1000.0d);
            if (currentTimeMillis <= 0.0d) {
                return 0.0d;
            }
            return currentTimeMillis;
        }

        public String toString() {
            return az.m870a(m867c(), 2);
        }
    }

    az() {
    }

    static boolean m881a(String str) {
        if (!C0594a.m614d()) {
            return false;
        }
        try {
            C0594a.m613c().getApplication().getPackageManager().getApplicationInfo(str, 0);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    static boolean m876a() {
        try {
            C0740l a = C0594a.m605a();
            File file = new File(a.m1285o().m787g() + "026ae9c9824b3e483fa6c71fa88f57ae27816141");
            File file2 = new File(a.m1285o().m787g() + "7bf3a1e7bbd31e612eda3310c2cdb8075c43c6b5");
            boolean a2 = a.m1280j().m1407a(file);
            boolean a3 = a.m1280j().m1407a(file2);
            if (a2 && a3) {
                return true;
            }
            return false;
        } catch (Exception e) {
            new C0595a().m622a("Unable to delete controller or launch response.").m624a(aa.f484h);
            return false;
        }
    }

    static String m888b() {
        if (!C0594a.m614d()) {
            return "1.0";
        }
        try {
            return C0594a.m613c().getPackageManager().getPackageInfo(C0594a.m613c().getPackageName(), 0).versionName;
        } catch (Exception e) {
            new C0595a().m622a("Failed to retrieve package info.").m624a(aa.f484h);
            return "1.0";
        }
    }

    static int m891c() {
        int i = 0;
        if (C0594a.m614d()) {
            try {
                i = C0594a.m613c().getPackageManager().getPackageInfo(C0594a.m613c().getPackageName(), 0).versionCode;
            } catch (Exception e) {
                new C0595a().m622a("Failed to retrieve package info.").m624a(aa.f484h);
            }
        }
        return i;
    }

    static String m893d() {
        if (!C0594a.m614d()) {
            return "";
        }
        Activity c = C0594a.m613c();
        PackageManager packageManager = c.getApplication().getPackageManager();
        try {
            CharSequence applicationLabel = packageManager.getApplicationLabel(packageManager.getApplicationInfo(c.getPackageName(), 0));
            if (applicationLabel == null) {
                return "";
            }
            return applicationLabel.toString();
        } catch (Exception e) {
            new C0595a().m622a("Failed to retrieve application label.").m624a(aa.f484h);
            return "";
        }
    }

    static int m887b(String str) {
        CRC32 crc32 = new CRC32();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            crc32.update(str.charAt(i));
        }
        return (int) crc32.getValue();
    }

    static String m892c(String str) {
        try {
            return bf.m1009a(str);
        } catch (Exception e) {
            return null;
        }
    }

    static String m895e() {
        return UUID.randomUUID().toString();
    }

    static JSONArray m874a(int i) {
        JSONArray b = C0802y.m1469b();
        for (int i2 = 0; i2 < i; i2++) {
            C0802y.m1458a(b, m895e());
        }
        return b;
    }

    static boolean m884a(String[] strArr, String[] strArr2) {
        if (strArr == null || strArr2 == null || strArr.length != strArr2.length) {
            return false;
        }
        Arrays.sort(strArr);
        Arrays.sort(strArr2);
        return Arrays.equals(strArr, strArr2);
    }

    static boolean m880a(Runnable runnable) {
        if (!C0594a.m614d()) {
            return false;
        }
        C0594a.m613c().runOnUiThread(runnable);
        return true;
    }

    static double m897f() {
        return ((double) System.currentTimeMillis()) / 1000.0d;
    }

    static boolean m894d(String str) {
        if (str != null && str.length() <= 128) {
            return true;
        }
        new C0595a().m622a("String must be non-null and the max length is 128 characters.").m624a(aa.f481e);
        return false;
    }

    static boolean m879a(AudioManager audioManager) {
        if (audioManager == null) {
            new C0595a().m622a("isAudioEnabled() called with a null AudioManager").m624a(aa.f484h);
            return false;
        }
        try {
            if (audioManager.getStreamVolume(3) > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            new C0595a().m622a("Exception occurred when accessing AudioManager.getStreamVolume: ").m622a(e.toString()).m624a(aa.f484h);
            return false;
        }
    }

    static double m885b(AudioManager audioManager) {
        if (audioManager == null) {
            new C0595a().m622a("getAudioVolume() called with a null AudioManager").m624a(aa.f484h);
            return 0.0d;
        }
        try {
            double streamVolume = (double) audioManager.getStreamVolume(3);
            double streamMaxVolume = (double) audioManager.getStreamMaxVolume(3);
            if (streamMaxVolume != 0.0d) {
                return streamVolume / streamMaxVolume;
            }
            return 0.0d;
        } catch (Exception e) {
            new C0595a().m622a("Exception occurred when accessing AudioManager: ").m622a(e.toString()).m624a(aa.f484h);
            return 0.0d;
        }
    }

    static AudioManager m869a(Context context) {
        if (context != null) {
            return (AudioManager) context.getSystemService("audio");
        }
        new C0595a().m622a("getAudioManager called with a null Context").m624a(aa.f484h);
        return null;
    }

    static void m896e(String str) {
        File[] listFiles = new File(str).listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isDirectory()) {
                    new C0595a().m622a(">").m622a(file.getAbsolutePath()).m624a(aa.f478b);
                    m896e(file.getAbsolutePath());
                } else {
                    new C0595a().m622a(file.getAbsolutePath()).m624a(aa.f478b);
                }
            }
        }
    }

    static String m870a(double d, int i) {
        StringBuilder stringBuilder = new StringBuilder();
        m875a(d, i, stringBuilder);
        return stringBuilder.toString();
    }

    static void m875a(double d, int i, StringBuilder stringBuilder) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            stringBuilder.append(d);
            return;
        }
        if (d < 0.0d) {
            d = -d;
            stringBuilder.append('-');
        }
        if (i == 0) {
            stringBuilder.append(Math.round(d));
            return;
        }
        long pow = (long) Math.pow(10.0d, (double) i);
        long round = Math.round(((double) pow) * d);
        stringBuilder.append(round / pow);
        stringBuilder.append('.');
        long j = round % pow;
        if (j == 0) {
            for (int i2 = 0; i2 < i; i2++) {
                stringBuilder.append('0');
            }
            return;
        }
        for (round = j * 10; round < pow; round *= 10) {
            stringBuilder.append('0');
        }
        stringBuilder.append(j);
    }

    static String m898f(String str) {
        if (str == null) {
            return "";
        }
        return URLDecoder.decode(str);
    }

    static String m871a(@NonNull Activity activity) {
        try {
            return activity.getPackageName();
        } catch (Exception e) {
            return "unknown";
        }
    }

    static String m872a(Exception exception) {
        Writer stringWriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    static int m899g(String str) {
        try {
            return (int) Long.parseLong(str, 16);
        } catch (NumberFormatException e) {
            new C0595a().m622a("Unable to parse '").m622a(str).m622a("' as a color.").m624a(aa.f482f);
            return -16777216;
        }
    }

    static int m886b(Activity activity) {
        if (activity == null) {
            return 0;
        }
        int identifier = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return activity.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    static boolean m900g() {
        return C0594a.m614d() && VERSION.SDK_INT >= 24 && C0594a.m613c().isInMultiWindowMode();
    }

    static boolean m883a(String str, File file) {
        boolean z = false;
        try {
            MessageDigest instance = MessageDigest.getInstance(AndroidUtilsLight.DIGEST_ALGORITHM_SHA1);
            try {
                InputStream fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[8192];
                while (true) {
                    try {
                        int read = fileInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        instance.update(bArr, 0, read);
                    } catch (Throwable e) {
                        throw new RuntimeException("Unable to process file for MD5", e);
                    } catch (Throwable th) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e2) {
                            new C0595a().m622a("Exception on closing MD5 input stream").m624a(aa.f484h);
                        }
                    }
                }
                String bigInteger = new BigInteger(1, instance.digest()).toString(16);
                z = str.equals(String.format("%40s", new Object[]{bigInteger}).replace(' ', '0'));
                try {
                    fileInputStream.close();
                } catch (IOException e3) {
                    new C0595a().m622a("Exception on closing MD5 input stream").m624a(aa.f484h);
                }
            } catch (FileNotFoundException e4) {
                new C0595a().m622a("Exception while getting FileInputStream").m624a(aa.f484h);
            }
        } catch (NoSuchAlgorithmException e5) {
            new C0595a().m622a("Exception while getting Digest").m624a(aa.f484h);
        }
        return z;
    }

    static Date m901h(String str) {
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");
        DateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        try {
            return simpleDateFormat.parse(str);
        } catch (Exception e) {
            try {
                return simpleDateFormat2.parse(str);
            } catch (Exception e2) {
                try {
                    return new SimpleDateFormat("yyyy-MM-dd").parse(str);
                } catch (Exception e3) {
                    return null;
                }
            }
        }
    }

    static String m873a(JSONArray jSONArray) throws JSONException {
        String str = "";
        for (int i = 0; i < jSONArray.length(); i++) {
            if (i > 0) {
                str = str + ",";
            }
            switch (jSONArray.getInt(i)) {
                case 1:
                    str = str + "MO";
                    break;
                case 2:
                    str = str + "TU";
                    break;
                case 3:
                    str = str + "WE";
                    break;
                case 4:
                    str = str + "TH";
                    break;
                case 5:
                    str = str + "FR";
                    break;
                case 6:
                    str = str + "SA";
                    break;
                case 7:
                    str = str + "SU";
                    break;
                default:
                    break;
            }
        }
        return str;
    }

    static String m889b(JSONArray jSONArray) throws JSONException {
        String str = "";
        int i = 0;
        while (i < jSONArray.length()) {
            if (i > 0) {
                str = str + ",";
            }
            String str2 = str + jSONArray.getInt(i);
            i++;
            str = str2;
        }
        return str;
    }

    static boolean m878a(Intent intent, boolean z) {
        try {
            AdColonyInterstitial u = C0594a.m605a().m1291u();
            if (u != null && u.m577g()) {
                u.m578h().m1188f();
            }
            if (z) {
                C0594a.m613c().startActivity(Intent.createChooser(intent, "Handle this via..."));
            } else {
                C0594a.m613c().startActivity(intent);
            }
            return true;
        } catch (Exception e) {
            new C0595a().m622a(e.toString()).m624a(aa.f482f);
            return false;
        }
    }

    static boolean m877a(Intent intent) {
        return m878a(intent, false);
    }

    static boolean m882a(final String str, final int i) {
        if (!C0594a.m614d()) {
            return false;
        }
        m880a(new Runnable() {
            public void run() {
                Toast.makeText(C0594a.m613c(), str, i).show();
            }
        });
        return true;
    }

    private static void m903j(String str) {
        try {
            InputStream open = C0594a.m613c().getAssets().open(str);
            OutputStream fileOutputStream = new FileOutputStream(C0594a.m605a().m1285o().m784d() + str);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = open.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    open.close();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return;
                }
            }
        } catch (Exception e) {
            new C0595a().m622a("Failed copy hardcoded ad unit file named: ").m622a(str).m622a(" with error: ").m622a(e.getMessage()).m624a(aa.f484h);
        }
    }

    public static void m902i(String str) {
        if (C0594a.m614d()) {
            try {
                String[] list = C0594a.m613c().getAssets().list(str);
                if (list.length == 0) {
                    m903j(str);
                    return;
                }
                File file = new File(C0594a.m605a().m1285o().m784d() + str);
                if (!file.exists()) {
                    file.mkdir();
                }
                for (String str2 : list) {
                    m902i(str + BridgeUtil.SPLIT_MARK + str2);
                }
            } catch (IOException e) {
                new C0595a().m622a("Failed copy hardcoded ad unit with error: ").m622a(e.getMessage()).m624a(aa.f484h);
            }
        }
    }

    static int m868a(ar arVar) {
        int i = 1;
        int i2 = 0;
        try {
            if (C0594a.m614d()) {
                int i3 = (int) (C0594a.m613c().getPackageManager().getPackageInfo(C0594a.m613c().getPackageName(), 0).lastUpdateTime / 1000);
                if (!new File(arVar.m787g() + "AppVersion").exists()) {
                    i2 = 2;
                } else if (C0802y.m1473c(C0802y.m1475c(arVar.m787g() + "AppVersion"), "last_update") != i3) {
                    i2 = 1;
                } else {
                    i = 0;
                }
                if (i != 0) {
                    JSONObject a = C0802y.m1453a();
                    C0802y.m1472b(a, "last_update", i3);
                    C0802y.m1482h(a, arVar.m787g() + "AppVersion");
                }
            }
        } catch (Exception e) {
        }
        return i2;
    }

    static JSONArray m890b(Context context) {
        JSONArray b = C0802y.m1469b();
        if (context != null) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096);
                if (packageInfo.requestedPermissions != null) {
                    b = C0802y.m1469b();
                    for (Object put : packageInfo.requestedPermissions) {
                        b.put(put);
                    }
                }
            } catch (Exception e) {
            }
        }
        return b;
    }
}
