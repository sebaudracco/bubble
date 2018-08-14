package com.adcolony.sdk;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.opengl.GLSurfaceView.Renderer;
import com.adcolony.sdk.aa.C0595a;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONObject;

class C0796v implements Renderer {
    static Options f1477a = new Options();
    static ByteBuffer f1478b;
    C0792u f1479c;
    String f1480d;
    int f1481e;
    int f1482f;
    boolean f1483g;
    boolean f1484h;
    ArrayList<af> f1485i = new ArrayList();
    C0673c f1486j;
    int f1487k;
    int f1488l;
    ArrayList<C0795a> f1489m = new ArrayList();
    HashMap<Integer, C0795a> f1490n = new HashMap();

    class C07931 implements ah {
        final /* synthetic */ C0796v f1466a;

        C07931(C0796v c0796v) {
            this.f1466a = c0796v;
        }

        public void mo1863a(af afVar) {
            this.f1466a.m1429a(afVar);
        }
    }

    class C07942 implements ah {
        final /* synthetic */ C0796v f1467a;

        C07942(C0796v c0796v) {
            this.f1467a = c0796v;
        }

        public void mo1863a(af afVar) {
            this.f1467a.m1431b(afVar);
        }
    }

    static class C0795a {
        static int f1468a = 1;
        int f1469b;
        String f1470c;
        boolean f1471d = true;
        int f1472e;
        int f1473f;
        int f1474g;
        int f1475h;
        int f1476i;

        C0795a(int i, String str, int i2, int i3) {
            this.f1469b = i;
            this.f1472e = this.f1472e;
            this.f1470c = str;
            this.f1473f = i2;
            this.f1474g = i3;
            this.f1475h = 1;
            while (this.f1475h < i2) {
                this.f1475h <<= 1;
            }
            this.f1476i = 1;
            while (this.f1476i < i3) {
                this.f1476i <<= 1;
            }
        }
    }

    C0796v(C0792u c0792u, boolean z, String str) {
        System.out.println("AdColony new ADCGLViewRenderer");
        this.f1479c = c0792u;
        this.f1483g = z;
        this.f1480d = str;
        this.f1486j = (C0673c) C0594a.m605a().m1283m().m1153b().get(str);
        this.f1481e = c0792u.f1456b;
        this.f1482f = this.f1486j.m1057c();
        this.f1486j.m1080n().add(C0594a.m604a("RenderView.create_image", new C07931(this), true));
        this.f1486j.m1080n().add(C0594a.m604a("RenderView.load_texture", new C07942(this), true));
        this.f1486j.m1082o().add("RenderView.create_image");
        this.f1486j.m1082o().add("RenderView.load_texture");
    }

    synchronized void m1428a() {
        if (!this.f1484h) {
            this.f1484h = true;
            synchronized (ADCNative.lock) {
                ADCNative.nativeDeleteSceneController(this.f1482f, this.f1481e);
            }
        }
    }

    protected void finalize() throws Throwable {
        m1428a();
    }

    public synchronized void onDrawFrame(GL10 unused) {
        C0594a.m616f();
        synchronized (ADCNative.lock) {
            if (this.f1484h) {
            } else {
                m1430b();
                ADCNative.nativeRender(this.f1482f, this.f1481e, this.f1487k, this.f1488l);
            }
        }
    }

    public synchronized void onSurfaceCreated(GL10 unused, EGLConfig config) {
        synchronized (ADCNative.lock) {
            if (this.f1484h) {
            } else {
                ADCNative.nativeCreateSceneController(this.f1482f, this.f1481e);
            }
        }
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        this.f1487k = width;
        this.f1488l = height;
    }

    void m1429a(af afVar) {
    }

    synchronized void m1431b(af afVar) {
        this.f1485i.add(afVar);
    }

    synchronized void m1430b() {
        for (int i = 0; i < this.f1485i.size(); i++) {
            af afVar = (af) this.f1485i.get(i);
            JSONObject c = afVar.m698c();
            C0795a c0795a = null;
            if (c.has("pixels")) {
                String b = C0802y.m1468b(c, "pixels");
                int[] iArr = new int[(b.length() / 4)];
                int length = b.length() - 4;
                int length2 = iArr.length;
                while (length >= 0) {
                    char charAt = b.charAt(length);
                    char charAt2 = b.charAt(length + 1);
                    char charAt3 = b.charAt(length + 2);
                    length += 4;
                    length2--;
                    iArr[length2] = (((charAt << 24) | (charAt2 << 16)) | (charAt3 << 8)) | b.charAt(length + 3);
                }
                int c2 = C0802y.m1473c(c, "width");
                int c3 = C0802y.m1473c(c, "height");
                if (c2 * c3 == iArr.length) {
                    c0795a = m1427a(C0802y.m1473c(c, "texture_id"), C0802y.m1468b(c, "filepath"), iArr, c2, c3);
                }
            } else if (c.has("bytes")) {
                String b2 = C0802y.m1468b(c, "bytes");
                byte[] bArr = new byte[b2.length()];
                int length3 = b2.length();
                while (true) {
                    length3--;
                    if (length3 < 0) {
                        break;
                    }
                    bArr[length3] = (byte) b2.charAt(length3);
                }
                c0795a = m1426a(C0802y.m1473c(c, "texture_id"), C0802y.m1468b(c, "filepath"), bArr);
            } else if (c.has("filepath")) {
                c0795a = m1424a(C0802y.m1473c(c, "texture_id"), C0802y.m1468b(c, "filepath"));
            }
            if (c0795a == null) {
                C0802y.m1465a(c, "success", false);
                break;
            }
            C0802y.m1465a(c, "success", c0795a.f1471d);
            C0802y.m1472b(c, "image_width", c0795a.f1473f);
            C0802y.m1472b(c, "image_height", c0795a.f1474g);
            C0802y.m1472b(c, "texture_width", c0795a.f1475h);
            C0802y.m1472b(c, "texture_height", c0795a.f1476i);
            afVar.m694a(c).m695b();
        }
        this.f1485i.clear();
    }

    C0795a m1424a(int i, String str) {
        Bitmap decodeStream;
        f1477a.inScaled = false;
        String str2 = "file:///android_asset/";
        if (str.startsWith(str2)) {
            try {
                decodeStream = BitmapFactory.decodeStream(C0594a.m613c().getAssets().open(str.substring(str2.length())), null, f1477a);
            } catch (IOException e) {
                new C0595a().m622a(e.toString()).m624a(aa.f482f);
                decodeStream = null;
            }
        } else {
            decodeStream = BitmapFactory.decodeFile(str, f1477a);
        }
        if (decodeStream != null) {
            return m1425a(i, str, decodeStream);
        }
        new C0595a().m622a("Failed to load ").m622a(str).m622a(" - using white 16x16 as placeholder.").m624a(aa.f482f);
        decodeStream = Bitmap.createBitmap(1, 1, Config.ARGB_8888);
        decodeStream.eraseColor(-1);
        C0795a a = m1425a(i, str, decodeStream);
        a.f1471d = false;
        return a;
    }

    C0795a m1426a(int i, String str, byte[] bArr) {
        f1477a.inScaled = false;
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        if (decodeByteArray != null) {
            return m1425a(i, str, decodeByteArray);
        }
        new C0595a().m622a("Failed to load ").m622a(str).m622a(" bytes - using white 16x16 as placeholder.").m624a(aa.f482f);
        decodeByteArray = Bitmap.createBitmap(1, 1, Config.ARGB_8888);
        decodeByteArray.eraseColor(-1);
        C0795a a = m1425a(i, str, decodeByteArray);
        a.f1471d = false;
        return a;
    }

    C0795a m1427a(int i, String str, int[] iArr, int i2, int i3) {
        return m1425a(i, str, Bitmap.createBitmap(iArr, i2, i3, Config.ARGB_8888));
    }

    C0795a m1425a(int i, String str, Bitmap bitmap) {
        int i2 = 4194304;
        C0795a c0795a = new C0795a(i, str, bitmap.getWidth(), bitmap.getHeight());
        int i3 = (c0795a.f1475h * c0795a.f1476i) * 4;
        if (f1478b == null || f1478b.capacity() < i3) {
            if (i3 >= 4194304) {
                i2 = i3;
            }
            f1478b = ByteBuffer.allocateDirect(i2);
            f1478b.order(ByteOrder.nativeOrder());
        }
        f1478b.rewind();
        bitmap.copyPixelsToBuffer(f1478b);
        this.f1489m.add(c0795a);
        this.f1490n.put(Integer.valueOf(i), c0795a);
        synchronized (ADCNative.lock) {
            ADCNative.nativeCreateTexture(this.f1482f, this.f1481e, i, str, f1478b, c0795a.f1473f, c0795a.f1474g, c0795a.f1475h, c0795a.f1476i);
        }
        return c0795a;
    }
}
