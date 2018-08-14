package com.adcolony.sdk;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

class bg {
    private static final String f847a = "INSTALLATION";
    private static String f848b = null;

    bg() {
    }

    public static synchronized String m1011a(Context context) {
        String str;
        synchronized (bg.class) {
            if (f848b == null) {
                File file = new File(context.getFilesDir(), f847a);
                try {
                    if (!file.exists()) {
                        m1013b(file);
                    }
                    f848b = m1012a(file);
                } catch (Exception e) {
                    throw new be(e.toString());
                }
            }
            str = f848b;
        }
        return str;
    }

    private static String m1012a(File file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        byte[] bArr = new byte[((int) randomAccessFile.length())];
        randomAccessFile.readFully(bArr);
        randomAccessFile.close();
        return new String(bArr);
    }

    private static void m1013b(File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(UUID.randomUUID().toString().getBytes());
        fileOutputStream.close();
    }
}
