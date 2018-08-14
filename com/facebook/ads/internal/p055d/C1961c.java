package com.facebook.ads.internal.p055d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.ads.internal.p056q.p075b.C2137c;
import com.facebook.ads.internal.p056q.p076c.C2145d;
import com.facebook.ads.internal.p068l.C2005a;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class C1961c {
    private static final String f4550a = C1961c.class.getSimpleName();
    private static C1961c f4551b;
    private final Context f4552c;

    private C1961c(Context context) {
        this.f4552c = context;
    }

    private Bitmap m6173a(String str) {
        byte[] d = C2145d.m6860a(this.f4552c).m6577a(str, null).m6619d();
        return BitmapFactory.decodeByteArray(d, 0, d.length);
    }

    public static C1961c m6174a(Context context) {
        if (f4551b == null) {
            Context applicationContext = context.getApplicationContext();
            synchronized (C1961c.class) {
                if (f4551b == null) {
                    f4551b = new C1961c(applicationContext);
                }
            }
        }
        return f4551b;
    }

    private static void m6175a(@Nullable Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    private void m6176a(String str, Bitmap bitmap) {
        Closeable fileOutputStream;
        Throwable e;
        Closeable closeable = null;
        File file = new File(this.f4552c.getCacheDir(), str.hashCode() + ".png");
        Closeable byteArrayOutputStream;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
                if (byteArrayOutputStream.size() >= 3145728) {
                    Log.d(f4550a, "Bitmap size exceeds max size for storage");
                    C1961c.m6175a(byteArrayOutputStream);
                    C1961c.m6175a(null);
                    return;
                }
                fileOutputStream = new FileOutputStream(file);
                try {
                    byteArrayOutputStream.writeTo(fileOutputStream);
                    fileOutputStream.flush();
                    C1961c.m6175a(byteArrayOutputStream);
                    C1961c.m6175a(fileOutputStream);
                } catch (FileNotFoundException e2) {
                    e = e2;
                    closeable = fileOutputStream;
                    fileOutputStream = byteArrayOutputStream;
                    try {
                        Log.e(f4550a, "Bad output destination (file=" + file.getAbsolutePath() + ").", e);
                        C1961c.m6175a(fileOutputStream);
                        C1961c.m6175a(closeable);
                    } catch (Throwable th) {
                        e = th;
                        byteArrayOutputStream = fileOutputStream;
                        C1961c.m6175a(byteArrayOutputStream);
                        C1961c.m6175a(closeable);
                        throw e;
                    }
                } catch (IOException e3) {
                    e = e3;
                    closeable = fileOutputStream;
                    try {
                        Log.e(f4550a, "Unable to write bitmap to file (url=" + str + ").", e);
                        C1961c.m6175a(byteArrayOutputStream);
                        C1961c.m6175a(closeable);
                    } catch (Throwable th2) {
                        e = th2;
                        C1961c.m6175a(byteArrayOutputStream);
                        C1961c.m6175a(closeable);
                        throw e;
                    }
                } catch (OutOfMemoryError e4) {
                    e = e4;
                    closeable = fileOutputStream;
                    Log.e(f4550a, "Unable to write bitmap to output stream", e);
                    C1961c.m6175a(byteArrayOutputStream);
                    C1961c.m6175a(closeable);
                } catch (Throwable th3) {
                    e = th3;
                    closeable = fileOutputStream;
                    C1961c.m6175a(byteArrayOutputStream);
                    C1961c.m6175a(closeable);
                    throw e;
                }
            } catch (FileNotFoundException e5) {
                e = e5;
                fileOutputStream = byteArrayOutputStream;
                Log.e(f4550a, "Bad output destination (file=" + file.getAbsolutePath() + ").", e);
                C1961c.m6175a(fileOutputStream);
                C1961c.m6175a(closeable);
            } catch (IOException e6) {
                e = e6;
                Log.e(f4550a, "Unable to write bitmap to file (url=" + str + ").", e);
                C1961c.m6175a(byteArrayOutputStream);
                C1961c.m6175a(closeable);
            } catch (OutOfMemoryError e7) {
                e = e7;
                Log.e(f4550a, "Unable to write bitmap to output stream", e);
                C1961c.m6175a(byteArrayOutputStream);
                C1961c.m6175a(closeable);
            }
        } catch (FileNotFoundException e8) {
            e = e8;
            fileOutputStream = null;
            Log.e(f4550a, "Bad output destination (file=" + file.getAbsolutePath() + ").", e);
            C1961c.m6175a(fileOutputStream);
            C1961c.m6175a(closeable);
        } catch (IOException e9) {
            e = e9;
            byteArrayOutputStream = null;
            Log.e(f4550a, "Unable to write bitmap to file (url=" + str + ").", e);
            C1961c.m6175a(byteArrayOutputStream);
            C1961c.m6175a(closeable);
        } catch (OutOfMemoryError e10) {
            e = e10;
            byteArrayOutputStream = null;
            Log.e(f4550a, "Unable to write bitmap to output stream", e);
            C1961c.m6175a(byteArrayOutputStream);
            C1961c.m6175a(closeable);
        } catch (Throwable th4) {
            e = th4;
            byteArrayOutputStream = null;
            C1961c.m6175a(byteArrayOutputStream);
            C1961c.m6175a(closeable);
            throw e;
        }
    }

    private boolean m6177a(int i, int i2) {
        return i > 0 && i2 > 0 && C2005a.m6343d(this.f4552c);
    }

    @Nullable
    private Bitmap m6178b(String str, int i, int i2) {
        try {
            Bitmap a = m6177a(i, i2) ? C2137c.m6845a(str.substring("file://".length()), i, i2) : BitmapFactory.decodeStream(new FileInputStream(str.substring("file://".length())), null, null);
            m6176a(str, a);
            return a;
        } catch (Throwable e) {
            Log.e(f4550a, "Failed to copy local image into cache (url=" + str + ").", e);
            return null;
        }
    }

    @Nullable
    private Bitmap m6179c(String str, int i, int i2) {
        Closeable open;
        Throwable th;
        Bitmap bitmap = null;
        if (str.startsWith("asset:///")) {
            try {
                open = this.f4552c.getAssets().open(str.substring(9, str.length()));
                try {
                    bitmap = m6177a(i, i2) ? C2137c.m6844a((InputStream) open, i, i2) : BitmapFactory.decodeStream(open);
                    if (open != null) {
                        C1961c.m6175a(open);
                    }
                } catch (IOException e) {
                    if (open != null) {
                        C1961c.m6175a(open);
                    }
                    return bitmap;
                } catch (Throwable th2) {
                    th = th2;
                    if (open != null) {
                        C1961c.m6175a(open);
                    }
                    throw th;
                }
            } catch (IOException e2) {
                open = bitmap;
                if (open != null) {
                    C1961c.m6175a(open);
                }
                return bitmap;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                open = bitmap;
                th = th4;
                if (open != null) {
                    C1961c.m6175a(open);
                }
                throw th;
            }
        } else if (m6177a(i, i2)) {
            try {
                bitmap = m6180d(str, i, i2);
            } catch (IOException e3) {
                bitmap = m6173a(str);
            }
        } else {
            bitmap = m6173a(str);
        }
        m6176a(str, bitmap);
        return bitmap;
    }

    private Bitmap m6180d(String str, int i, int i2) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setDoInput(true);
        httpURLConnection.connect();
        Closeable inputStream = httpURLConnection.getInputStream();
        Bitmap a = C2137c.m6844a((InputStream) inputStream, i, i2);
        C1961c.m6175a(inputStream);
        return a;
    }

    @Nullable
    public Bitmap m6181a(String str, int i, int i2) {
        File file = new File(this.f4552c.getCacheDir(), str.hashCode() + ".png");
        return !file.exists() ? str.startsWith("file://") ? m6178b(str, i, i2) : m6179c(str, i, i2) : m6177a(i, i2) ? C2137c.m6845a(file.getAbsolutePath(), i, i2) : BitmapFactory.decodeFile(file.getAbsolutePath());
    }
}
