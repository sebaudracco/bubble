package com.appnext.base.p023b;

import com.appnext.base.C1061b;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class C1060n {
    private String kH;
    private String kI;

    public C1060n(String str, String str2) {
        this.kH = str;
        this.kI = str2;
        if (this.kI.charAt(this.kI.length() - 1) != '/') {
            this.kI += BridgeUtil.SPLIT_MARK;
        }
        aH("");
    }

    private void aH(String str) {
        File file = new File(this.kI + str);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
    }

    public void cJ() {
        Throwable th;
        FileInputStream fileInputStream;
        ZipInputStream zipInputStream = null;
        C1058l.m2184k("sals", "Unzipped started");
        if (this.kH == null || this.kH.length() == 0 || this.kI == null || this.kI.length() == 0) {
            C1058l.m2184k("sals", "Unzipped abort");
            return;
        }
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream2;
        ZipInputStream zipInputStream2;
        try {
            byte[] bArr = new byte[2048];
            fileInputStream2 = new FileInputStream(this.kH);
            try {
                zipInputStream2 = new ZipInputStream(fileInputStream2);
                fileOutputStream = null;
                while (true) {
                    try {
                        ZipEntry nextEntry = zipInputStream2.getNextEntry();
                        if (nextEntry == null) {
                            break;
                        }
                        try {
                            if (nextEntry.isDirectory()) {
                                aH(nextEntry.getName());
                            } else {
                                String str = this.kI + BridgeUtil.SPLIT_MARK + nextEntry.getName();
                                C1058l.m2188o("slas", "Unzipping ");
                                if (nextEntry.isDirectory()) {
                                    aH(nextEntry.getName());
                                } else {
                                    FileOutputStream fileOutputStream2 = new FileOutputStream(str);
                                    while (true) {
                                        try {
                                            int read = zipInputStream2.read(bArr);
                                            if (read <= 0) {
                                                break;
                                            }
                                            fileOutputStream2.write(bArr, 0, read);
                                        } catch (Throwable th2) {
                                            th = th2;
                                            fileOutputStream = fileOutputStream2;
                                        }
                                    }
                                    fileOutputStream = fileOutputStream2;
                                }
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                                fileOutputStream = null;
                            }
                            C1058l.m2184k("sals", "Unzipped finished");
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                    }
                }
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (Throwable th5) {
                        C1058l.m2184k("sals", "Unzipped failed 2");
                        C1061b.m2191a(th5);
                        return;
                    }
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (zipInputStream2 != null) {
                    zipInputStream2.close();
                    return;
                }
                return;
            } catch (Throwable th6) {
                th5 = th6;
                fileOutputStream = null;
                zipInputStream2 = null;
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (zipInputStream2 != null) {
                    zipInputStream2.close();
                }
                throw th5;
            }
        } catch (Throwable th7) {
            th5 = th7;
            fileOutputStream = null;
            zipInputStream2 = null;
            fileInputStream2 = null;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (zipInputStream2 != null) {
                zipInputStream2.close();
            }
            throw th5;
        }
        if (fileOutputStream != null) {
            fileOutputStream.close();
            ZipInputStream zipInputStream3 = null;
        }
        C1058l.m2184k("sals", "Unzipped finished");
        throw th5;
    }
}
