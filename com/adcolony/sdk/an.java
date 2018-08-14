package com.adcolony.sdk;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.opengl.GLES20;
import android.util.Log;
import com.adcolony.sdk.ap.C0613a;
import com.adcolony.sdk.ap.C0614b;
import com.adcolony.sdk.ap.C0615c;
import com.adcolony.sdk.ap.C0616d;
import com.adcolony.sdk.ap.C0617e;
import com.adcolony.sdk.ap.C0618f;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

class an {
    static final int f587a = 1;
    static final int f588b = 2;
    static final int f589c = 4;
    static final int f590d = 1;
    static final int f591e = 2;
    static final int f592f = 512;
    static int f593g = 1;
    static ByteBuffer f594h;
    static IntBuffer f595i;
    static Options f596j = new Options();
    static int[] f597k = new int[1];
    ap f598A;
    ap f599B;
    ap f600C;
    ap f601D;
    ap f602E;
    ap f603F;
    int f604l;
    int f605m;
    int f606n;
    int f607o;
    ArrayList<C0611a> f608p = new ArrayList();
    int f609q;
    int f610r;
    boolean f611s = true;
    boolean f612t = true;
    C0611a f613u;
    int f614v;
    FloatBuffer f615w;
    FloatBuffer f616x;
    IntBuffer f617y;
    ay f618z = new ay(this);

    class C0611a {
        int f578a = an.f597k[0];
        int f579b;
        int f580c;
        int f581d;
        int f582e;
        int f583f;
        double f584g;
        double f585h;
        final /* synthetic */ an f586i;

        C0611a(an anVar) {
            this.f586i = anVar;
            int i = an.f593g;
            an.f593g = i + 1;
            this.f579b = i;
            GLES20.glGenTextures(1, an.f597k, 0);
        }

        C0611a m733a(IntBuffer intBuffer, int i, int i2) {
            int i3;
            int i4 = 1;
            int i5 = 1;
            while (i5 < i) {
                i5 <<= 1;
            }
            while (i4 < i2) {
                i4 <<= 1;
            }
            this.f580c = i;
            this.f581d = i2;
            this.f582e = i5;
            this.f583f = i4;
            this.f584g = ((double) this.f580c) / ((double) this.f582e);
            this.f585h = ((double) this.f581d) / ((double) this.f583f);
            intBuffer.rewind();
            i4 = i * i2;
            while (true) {
                i4--;
                if (i4 < 0) {
                    break;
                }
                i5 = intBuffer.get(i4);
                intBuffer.put(i4, ((i5 << 16) & 16711680) | ((-16711936 & i5) | ((i5 >> 16) & 255)));
            }
            intBuffer.rewind();
            if (this.f580c < this.f582e) {
                i3 = ((this.f581d - 1) * this.f582e) + this.f580c;
                i5 = this.f581d * this.f580c;
                i4 = this.f581d;
                int i6 = this.f582e - this.f580c;
                while (true) {
                    int i7 = i4 - 1;
                    if (i7 < 0) {
                        break;
                    }
                    intBuffer.put(i3, intBuffer.get(i5 - 1));
                    i4 = this.f580c;
                    while (true) {
                        i4--;
                        if (i4 < 0) {
                            break;
                        }
                        i3--;
                        i5--;
                        intBuffer.put(i3, intBuffer.get(i5));
                    }
                    i3 -= i6;
                    i4 = i7;
                }
            }
            intBuffer.rewind();
            if (this.f581d < this.f583f) {
                i3 = this.f581d * this.f582e;
                i5 = i3 + this.f582e;
                i4 = this.f582e;
                while (true) {
                    i4--;
                    if (i4 < 0) {
                        break;
                    }
                    i5--;
                    i3--;
                    intBuffer.put(i5, intBuffer.get(i3));
                }
            }
            GLES20.glBindTexture(3553, this.f578a);
            intBuffer.rewind();
            GLES20.glTexImage2D(3553, 0, 6408, this.f582e, this.f583f, 0, 6408, 5121, intBuffer);
            System.out.println("ADC3 Texture::set gl_texture_id:" + this.f578a + " texture_id:" + this.f579b + " w:" + i + " h:" + i2);
            return this;
        }
    }

    an() {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(4096);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.f615w = allocateDirect.asFloatBuffer();
        this.f615w.rewind();
        allocateDirect = ByteBuffer.allocateDirect(4096);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.f616x = allocateDirect.asFloatBuffer();
        this.f616x.rewind();
        allocateDirect = ByteBuffer.allocateDirect(Math.max(2048, 4194304));
        allocateDirect.order(ByteOrder.nativeOrder());
        this.f617y = allocateDirect.asIntBuffer();
        this.f617y.rewind();
    }

    void m739a(double d, double d2, double d3, double d4, int i) {
        int i2 = (i >> 24) & 255;
        int i3 = (i >> 16) & 255;
        int i4 = (i >> 8) & 255;
        int i5 = i & 255;
        if (i2 > 0) {
            this.f612t = false;
        }
        if (i2 < 255) {
            this.f611s = false;
        }
        i2 = (((i2 << 24) | (((i5 * i2) / 255) << 16)) | (((i4 * i2) / 255) << 8)) | ((i3 * i2) / 255);
        this.f615w.put((float) d);
        this.f615w.put((float) d2);
        this.f616x.put((float) d3);
        this.f616x.put((float) d4);
        this.f617y.put(i2);
        this.f614v++;
    }

    void m738a() {
        m748b(null);
        this.f618z.m856b();
        m745b();
    }

    void m742a(int i, int i2, double d, int i3) {
        if (i != 0) {
            int i4 = 0;
            m750d();
            if ((i & 1) != 0) {
                i4 = 16384;
                GLES20.glClearColor(((float) ((i2 >> 16) & 255)) / 255.0f, ((float) ((i2 >> 8) & 255)) / 255.0f, ((float) (i2 & 255)) / 255.0f, ((float) ((i2 >> 24) & 255)) / 255.0f);
            }
            if ((i & 2) != 0) {
                i4 |= 256;
                GLES20.glClearDepthf((float) d);
            }
            if ((i & 4) != 0) {
                i4 |= 1024;
                GLES20.glClearStencil(i3);
            }
            GLES20.glClear(i4);
        }
    }

    void m745b() {
        m750d();
        GLES20.glDisable(3089);
    }

    C0611a m735a(Bitmap bitmap) {
        int i = 4194304;
        int i2 = 1;
        m750d();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i3 = 1;
        while (i3 < width) {
            i3 <<= 1;
        }
        while (i2 < height) {
            i2 <<= 1;
        }
        i2 = (i2 * i3) * 4;
        if (f594h == null || f594h.capacity() < i2) {
            if (i2 >= 4194304) {
                i = i2;
            }
            f594h = ByteBuffer.allocateDirect(i);
            f594h.order(ByteOrder.nativeOrder());
            f595i = f594h.asIntBuffer();
        }
        f594h.rewind();
        bitmap.copyPixelsToBuffer(f594h);
        bitmap.recycle();
        C0611a c0611a = new C0611a(this);
        this.f608p.add(c0611a);
        return c0611a.m733a(f595i, width, height);
    }

    void m744a(C0611a c0611a) {
        this.f608p.remove(c0611a);
        f597k[0] = c0611a.f579b;
        GLES20.glDeleteTextures(1, f597k, 0);
    }

    void m749c() {
        m750d();
        m745b();
    }

    C0611a m734a(int i) {
        for (int i2 = 0; i2 < this.f608p.size(); i2++) {
            C0611a c0611a = (C0611a) this.f608p.get(i2);
            if (c0611a.f579b == i) {
                return c0611a;
            }
        }
        return null;
    }

    void m750d() {
        if (this.f614v != 0) {
            this.f618z.m863g();
            if ((this.f609q & 1) != 0) {
                int i;
                int i2;
                switch ((this.f609q >> 8) & 15) {
                    case 0:
                        i = 0;
                        break;
                    case 1:
                        i = 1;
                        break;
                    case 2:
                        i = 770;
                        break;
                    case 3:
                        i = 771;
                        break;
                    default:
                        i = 1;
                        break;
                }
                switch ((this.f609q >> 12) & 15) {
                    case 0:
                        i2 = 0;
                        break;
                    case 1:
                        i2 = 1;
                        break;
                    case 2:
                        i2 = 770;
                        break;
                    case 3:
                        i2 = 771;
                        break;
                    default:
                        i2 = 0;
                        break;
                }
                GLES20.glBlendFunc(i, i2);
                GLES20.glEnable(3042);
            } else {
                GLES20.glDisable(3042);
            }
            if (this.f613u != null) {
                GLES20.glEnable(3553);
                GLES20.glActiveTexture(33984);
                GLES20.glBindTexture(3553, this.f613u.f578a);
                if ((this.f609q & 12) != 0) {
                    GLES20.glTexParameterf(3553, 10242, 10497.0f);
                    GLES20.glTexParameterf(3553, 10243, 10497.0f);
                } else {
                    GLES20.glTexParameterf(3553, 10242, 33071.0f);
                    GLES20.glTexParameterf(3553, 10243, 33071.0f);
                }
                if ((this.f609q & 16) != 0) {
                    GLES20.glTexParameterf(3553, 10241, 9728.0f);
                    GLES20.glTexParameterf(3553, 10240, 9728.0f);
                } else {
                    GLES20.glTexParameterf(3553, 10241, 9729.0f);
                    GLES20.glTexParameterf(3553, 10240, 9729.0f);
                }
                switch (this.f609q & 16711680) {
                    case 65536:
                        this.f600C.m771a();
                        break;
                    case 131072:
                        this.f601D.m771a();
                        break;
                    case 196608:
                        if (!this.f611s) {
                            if (!this.f612t) {
                                this.f603F.m771a();
                                break;
                            } else {
                                this.f599B.m771a();
                                break;
                            }
                        }
                        this.f602E.m771a();
                        break;
                    default:
                        this.f599B.m771a();
                        break;
                }
            }
            GLES20.glDisable(3553);
            this.f598A.m771a();
            switch (this.f610r) {
                case 1:
                    this.f598A.m771a();
                    GLES20.glDrawArrays(1, 0, this.f614v);
                    break;
                case 2:
                    GLES20.glDrawArrays(4, 0, this.f614v);
                    break;
            }
            this.f614v = 0;
            this.f615w.rewind();
            this.f616x.rewind();
            this.f617y.rewind();
            this.f611s = true;
            this.f612t = true;
        }
    }

    C0611a m737a(String str) {
        m750d();
        f596j.inScaled = false;
        Bitmap decodeFile = BitmapFactory.decodeFile(str, f596j);
        if (decodeFile == null) {
            System.out.println("Failed to load " + str);
            decodeFile = Bitmap.createBitmap(16, 16, Config.ARGB_8888);
        }
        return m735a(decodeFile);
    }

    C0611a m736a(InputStream inputStream) {
        m750d();
        f596j.inScaled = false;
        Bitmap decodeStream = BitmapFactory.decodeStream(inputStream, null, f596j);
        if (decodeStream == null) {
            Log.w("ADC3", "Failed to decode input stream.");
            decodeStream = Bitmap.createBitmap(16, 16, Config.ARGB_8888);
        }
        return m735a(decodeStream);
    }

    void m751e() {
        System.out.println("ADCRenderer on_surface_created()");
        this.f598A = new C0613a(this);
        this.f599B = new C0614b(this);
        this.f600C = new C0616d(this);
        this.f601D = new C0615c(this);
        this.f602E = new C0617e(this);
        this.f603F = new C0618f(this);
    }

    void m740a(int i, int i2) {
        System.out.println("ADCRenderer on_surface_changed " + i + "x" + i2);
        this.f604l = i;
        this.f605m = i2;
        this.f606n = i;
        this.f607o = i2;
        GLES20.glViewport(0, 0, i, i2);
    }

    void m743a(int i, int i2, int i3, int i4) {
        m750d();
        double d = ((double) this.f604l) / ((double) this.f606n);
        double d2 = ((double) this.f605m) / ((double) this.f607o);
        int i5 = (int) (((double) i4) * d2);
        GLES20.glScissor((int) (d * ((double) i)), this.f605m - (((int) (d2 * ((double) i2))) + i5), (int) (((double) i3) * d), i5);
        GLES20.glEnable(3089);
    }

    void m752f() {
        if (this.f610r != 1) {
            m750d();
            this.f610r = 1;
        }
    }

    void m741a(int i, int i2, double d) {
        this.f606n = i;
        this.f607o = i2;
        this.f618z.m855a(this.f618z.f742d.m671a(i, i2, d));
    }

    void m746b(int i) {
        if (i != this.f609q) {
            m750d();
            this.f609q = i;
        }
    }

    void m747b(int i, int i2, int i3, int i4) {
        m746b((((i | 2) | (i2 << 8)) | (i3 << 12)) | (i4 << 16));
    }

    void m748b(C0611a c0611a) {
        if (c0611a != this.f613u) {
            m750d();
            this.f613u = c0611a;
        }
    }

    void m753g() {
        if (this.f610r != 2) {
            m750d();
            this.f610r = 2;
        }
    }
}
