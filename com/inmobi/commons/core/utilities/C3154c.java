package com.inmobi.commons.core.utilities;

import android.net.Uri;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.io.File;

/* compiled from: FileUtils */
public class C3154c {
    private static final String f7780a = C3154c.class.getSimpleName();

    public static void m10400a(File file) {
        try {
            if (file.exists()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    for (File file2 : listFiles) {
                        if (file2.isDirectory()) {
                            C3154c.m10400a(file2);
                        } else {
                            file2.delete();
                        }
                    }
                }
                file.delete();
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7780a, "SDK encountered unexpected error in deleting directory; " + e.getMessage());
        }
    }

    public static long m10399a(String str) {
        long j = 0;
        try {
            File file = new File(Uri.parse(str).getPath());
            if (file != null && file.exists()) {
                j = file.length();
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, "FileUtils", "Error in finding Asset size");
        }
        return j;
    }
}
