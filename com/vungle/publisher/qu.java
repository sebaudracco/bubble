package com.vungle.publisher;

import com.vungle.publisher.log.Logger;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* compiled from: vungle */
public class qu {

    /* compiled from: vungle */
    public interface C4245a {
        void m13851a(File file, String str, long j);
    }

    public static void m13852a(File file, File file2, C4245a... c4245aArr) throws IOException {
        OutputStream fileOutputStream;
        Logger.m13635d(Logger.FILE_TAG, "extracting " + file + " to " + file2);
        if (file2.isDirectory() || file2.mkdirs()) {
            ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(file)));
            byte[] bArr = new byte[8192];
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry == null) {
                    try {
                        zipInputStream.close();
                        return;
                    } catch (IOException e) {
                        Logger.m13647w(Logger.FILE_TAG, "error closing zip input stream " + file);
                        return;
                    }
                } else if (!nextEntry.isDirectory()) {
                    String name = nextEntry.getName();
                    if (qr.m13847b(name)) {
                        File canonicalFile = new File(file2, name).getCanonicalFile();
                        if (qr.m13844a(file2, canonicalFile)) {
                            Logger.m13644v(Logger.FILE_TAG, "verified " + canonicalFile + " is nested within " + file2);
                            if (qr.m13849c(canonicalFile.getParentFile())) {
                                try {
                                    int read;
                                    Logger.m13644v(Logger.FILE_TAG, "extracting " + canonicalFile);
                                    fileOutputStream = new FileOutputStream(canonicalFile);
                                    long j = 0;
                                    while (true) {
                                        read = zipInputStream.read(bArr);
                                        if (read <= 0) {
                                            break;
                                        }
                                        j += (long) read;
                                        fileOutputStream.write(bArr, 0, read);
                                    }
                                    if (c4245aArr != null) {
                                        for (C4245a a : c4245aArr) {
                                            a.m13851a(canonicalFile, name, j);
                                        }
                                    }
                                    try {
                                        fileOutputStream.close();
                                    } catch (IOException e2) {
                                        Logger.m13647w(Logger.FILE_TAG, "error closing file output stream " + file2);
                                    }
                                } catch (Throwable th) {
                                    try {
                                        zipInputStream.close();
                                    } catch (IOException e3) {
                                        Logger.m13647w(Logger.FILE_TAG, "error closing zip input stream " + file);
                                    }
                                }
                            } else {
                                Logger.m13647w(Logger.FILE_TAG, "could not ensure directory");
                            }
                        } else {
                            throw new qt("aborting zip extraction - child " + name + " escapes destination directory " + file2);
                        }
                    }
                    throw new qs("Unsafe path " + name);
                }
            }
        } else {
            throw new IOException("failed to create directories " + file2);
        }
    }
}
