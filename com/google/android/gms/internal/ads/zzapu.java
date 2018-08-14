package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.concurrent.CountDownLatch;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

@zzadh
@TargetApi(14)
public final class zzapu extends Thread implements OnFrameAvailableListener, zzapt {
    private static final float[] zzcyv = new float[]{-1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f};
    private final float[] zzcys;
    private final zzapr zzcyw;
    private final float[] zzcyx;
    private final float[] zzcyy;
    private final float[] zzcyz;
    private final float[] zzcza;
    private final float[] zzczb;
    private final float[] zzczc;
    private float zzczd;
    private float zzcze;
    private float zzczf;
    private SurfaceTexture zzczg;
    private SurfaceTexture zzczh;
    private int zzczi;
    private int zzczj;
    private int zzczk;
    private FloatBuffer zzczl = ByteBuffer.allocateDirect(zzcyv.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
    private final CountDownLatch zzczm;
    private final Object zzczn;
    private EGL10 zzczo;
    private EGLDisplay zzczp;
    private EGLContext zzczq;
    private EGLSurface zzczr;
    private volatile boolean zzczs;
    private volatile boolean zzczt;
    private int zzuq;
    private int zzur;

    public zzapu(Context context) {
        super("SphericalVideoProcessor");
        this.zzczl.put(zzcyv).position(0);
        this.zzcys = new float[9];
        this.zzcyx = new float[9];
        this.zzcyy = new float[9];
        this.zzcyz = new float[9];
        this.zzcza = new float[9];
        this.zzczb = new float[9];
        this.zzczc = new float[9];
        this.zzczd = Float.NaN;
        this.zzcyw = new zzapr(context);
        this.zzcyw.zza((zzapt) this);
        this.zzczm = new CountDownLatch(1);
        this.zzczn = new Object();
    }

    private static void zza(float[] fArr, float f) {
        fArr[0] = 1.0f;
        fArr[1] = 0.0f;
        fArr[2] = 0.0f;
        fArr[3] = 0.0f;
        fArr[4] = (float) Math.cos((double) f);
        fArr[5] = (float) (-Math.sin((double) f));
        fArr[6] = 0.0f;
        fArr[7] = (float) Math.sin((double) f);
        fArr[8] = (float) Math.cos((double) f);
    }

    private static void zza(float[] fArr, float[] fArr2, float[] fArr3) {
        fArr[0] = ((fArr2[0] * fArr3[0]) + (fArr2[1] * fArr3[3])) + (fArr2[2] * fArr3[6]);
        fArr[1] = ((fArr2[0] * fArr3[1]) + (fArr2[1] * fArr3[4])) + (fArr2[2] * fArr3[7]);
        fArr[2] = ((fArr2[0] * fArr3[2]) + (fArr2[1] * fArr3[5])) + (fArr2[2] * fArr3[8]);
        fArr[3] = ((fArr2[3] * fArr3[0]) + (fArr2[4] * fArr3[3])) + (fArr2[5] * fArr3[6]);
        fArr[4] = ((fArr2[3] * fArr3[1]) + (fArr2[4] * fArr3[4])) + (fArr2[5] * fArr3[7]);
        fArr[5] = ((fArr2[3] * fArr3[2]) + (fArr2[4] * fArr3[5])) + (fArr2[5] * fArr3[8]);
        fArr[6] = ((fArr2[6] * fArr3[0]) + (fArr2[7] * fArr3[3])) + (fArr2[8] * fArr3[6]);
        fArr[7] = ((fArr2[6] * fArr3[1]) + (fArr2[7] * fArr3[4])) + (fArr2[8] * fArr3[7]);
        fArr[8] = ((fArr2[6] * fArr3[2]) + (fArr2[7] * fArr3[5])) + (fArr2[8] * fArr3[8]);
    }

    private static void zzb(float[] fArr, float f) {
        fArr[0] = (float) Math.cos((double) f);
        fArr[1] = (float) (-Math.sin((double) f));
        fArr[2] = 0.0f;
        fArr[3] = (float) Math.sin((double) f);
        fArr[4] = (float) Math.cos((double) f);
        fArr[5] = 0.0f;
        fArr[6] = 0.0f;
        fArr[7] = 0.0f;
        fArr[8] = 1.0f;
    }

    private static int zzd(int i, String str) {
        int glCreateShader = GLES20.glCreateShader(i);
        zzdo("createShader");
        if (glCreateShader != 0) {
            GLES20.glShaderSource(glCreateShader, str);
            zzdo("shaderSource");
            GLES20.glCompileShader(glCreateShader);
            zzdo("compileShader");
            int[] iArr = new int[1];
            GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
            zzdo("getShaderiv");
            if (iArr[0] == 0) {
                Log.e("SphericalVideoRenderer", "Could not compile shader " + i + ":");
                Log.e("SphericalVideoRenderer", GLES20.glGetShaderInfoLog(glCreateShader));
                GLES20.glDeleteShader(glCreateShader);
                zzdo("deleteShader");
                return 0;
            }
        }
        return glCreateShader;
    }

    private static void zzdo(String str) {
        int glGetError = GLES20.glGetError();
        if (glGetError != 0) {
            Log.e("SphericalVideoRenderer", new StringBuilder(String.valueOf(str).length() + 21).append(str).append(": glError ").append(glGetError).toString());
        }
    }

    @VisibleForTesting
    private final boolean zztk() {
        boolean z = false;
        if (!(this.zzczr == null || this.zzczr == EGL10.EGL_NO_SURFACE)) {
            z = (this.zzczo.eglMakeCurrent(this.zzczp, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT) | 0) | this.zzczo.eglDestroySurface(this.zzczp, this.zzczr);
            this.zzczr = null;
        }
        if (this.zzczq != null) {
            z |= this.zzczo.eglDestroyContext(this.zzczp, this.zzczq);
            this.zzczq = null;
        }
        if (this.zzczp == null) {
            return z;
        }
        z |= this.zzczo.eglTerminate(this.zzczp);
        this.zzczp = null;
        return z;
    }

    public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.zzczk++;
        synchronized (this.zzczn) {
            this.zzczn.notifyAll();
        }
    }

    public final void run() {
        int i = 1;
        if (this.zzczh == null) {
            zzane.m3427e("SphericalVideoProcessor started with no output texture.");
            this.zzczm.countDown();
            return;
        }
        boolean z;
        int i2;
        this.zzczo = (EGL10) EGLContext.getEGL();
        this.zzczp = this.zzczo.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        if (this.zzczp == EGL10.EGL_NO_DISPLAY) {
            z = false;
        } else {
            if (this.zzczo.eglInitialize(this.zzczp, new int[2])) {
                int[] iArr = new int[1];
                EGLConfig[] eGLConfigArr = new EGLConfig[1];
                EGLConfig eGLConfig = (!this.zzczo.eglChooseConfig(this.zzczp, new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12325, 16, 12344}, eGLConfigArr, 1, iArr) || iArr[0] <= 0) ? null : eGLConfigArr[0];
                if (eGLConfig == null) {
                    z = false;
                } else {
                    this.zzczq = this.zzczo.eglCreateContext(this.zzczp, eGLConfig, EGL10.EGL_NO_CONTEXT, new int[]{12440, 2, 12344});
                    if (this.zzczq == null || this.zzczq == EGL10.EGL_NO_CONTEXT) {
                        z = false;
                    } else {
                        this.zzczr = this.zzczo.eglCreateWindowSurface(this.zzczp, eGLConfig, this.zzczh, null);
                        z = (this.zzczr == null || this.zzczr == EGL10.EGL_NO_SURFACE) ? false : this.zzczo.eglMakeCurrent(this.zzczp, this.zzczr, this.zzczr, this.zzczq);
                    }
                }
            } else {
                z = false;
            }
        }
        zzna com_google_android_gms_internal_ads_zzna = zznk.zzazp;
        int zzd = zzd(35633, !((String) zzkb.zzik().zzd(com_google_android_gms_internal_ads_zzna)).equals(com_google_android_gms_internal_ads_zzna.zzja()) ? (String) zzkb.zzik().zzd(com_google_android_gms_internal_ads_zzna) : "attribute highp vec3 aPosition;varying vec3 pos;void main() {  gl_Position = vec4(aPosition, 1.0);  pos = aPosition;}");
        if (zzd == 0) {
            i2 = 0;
        } else {
            zzna com_google_android_gms_internal_ads_zzna2 = zznk.zzazq;
            int zzd2 = zzd(35632, !((String) zzkb.zzik().zzd(com_google_android_gms_internal_ads_zzna2)).equals(com_google_android_gms_internal_ads_zzna2.zzja()) ? (String) zzkb.zzik().zzd(com_google_android_gms_internal_ads_zzna2) : "#extension GL_OES_EGL_image_external : require\n#define INV_PI 0.3183\nprecision highp float;varying vec3 pos;uniform samplerExternalOES uSplr;uniform mat3 uVMat;uniform float uFOVx;uniform float uFOVy;void main() {  vec3 ray = vec3(pos.x * tan(uFOVx), pos.y * tan(uFOVy), -1);  ray = (uVMat * ray).xyz;  ray = normalize(ray);  vec2 texCrd = vec2(    0.5 + atan(ray.x, - ray.z) * INV_PI * 0.5, acos(ray.y) * INV_PI);  gl_FragColor = vec4(texture2D(uSplr, texCrd).xyz, 1.0);}");
            if (zzd2 == 0) {
                i2 = 0;
            } else {
                i2 = GLES20.glCreateProgram();
                zzdo("createProgram");
                if (i2 != 0) {
                    GLES20.glAttachShader(i2, zzd);
                    zzdo("attachShader");
                    GLES20.glAttachShader(i2, zzd2);
                    zzdo("attachShader");
                    GLES20.glLinkProgram(i2);
                    zzdo("linkProgram");
                    int[] iArr2 = new int[1];
                    GLES20.glGetProgramiv(i2, 35714, iArr2, 0);
                    zzdo("getProgramiv");
                    if (iArr2[0] != 1) {
                        Log.e("SphericalVideoRenderer", "Could not link program: ");
                        Log.e("SphericalVideoRenderer", GLES20.glGetProgramInfoLog(i2));
                        GLES20.glDeleteProgram(i2);
                        zzdo("deleteProgram");
                        i2 = 0;
                    } else {
                        GLES20.glValidateProgram(i2);
                        zzdo("validateProgram");
                    }
                }
            }
        }
        this.zzczi = i2;
        GLES20.glUseProgram(this.zzczi);
        zzdo("useProgram");
        int glGetAttribLocation = GLES20.glGetAttribLocation(this.zzczi, "aPosition");
        GLES20.glVertexAttribPointer(glGetAttribLocation, 3, 5126, false, 12, this.zzczl);
        zzdo("vertexAttribPointer");
        GLES20.glEnableVertexAttribArray(glGetAttribLocation);
        zzdo("enableVertexAttribArray");
        int[] iArr3 = new int[1];
        GLES20.glGenTextures(1, iArr3, 0);
        zzdo("genTextures");
        i2 = iArr3[0];
        GLES20.glBindTexture(36197, i2);
        zzdo("bindTextures");
        GLES20.glTexParameteri(36197, 10240, 9729);
        zzdo("texParameteri");
        GLES20.glTexParameteri(36197, 10241, 9729);
        zzdo("texParameteri");
        GLES20.glTexParameteri(36197, 10242, 33071);
        zzdo("texParameteri");
        GLES20.glTexParameteri(36197, 10243, 33071);
        zzdo("texParameteri");
        this.zzczj = GLES20.glGetUniformLocation(this.zzczi, "uVMat");
        GLES20.glUniformMatrix3fv(this.zzczj, 1, false, new float[]{1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f}, 0);
        if (this.zzczi == 0) {
            i = 0;
        }
        if (!z || r4 == 0) {
            String str = "EGL initialization failed: ";
            String valueOf = String.valueOf(GLUtils.getEGLErrorString(this.zzczo.eglGetError()));
            valueOf = valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
            zzane.m3427e(valueOf);
            zzbv.zzeo().zza(new Throwable(valueOf), "SphericalVideoProcessor.run.1");
            zztk();
            this.zzczm.countDown();
            return;
        }
        this.zzczg = new SurfaceTexture(i2);
        this.zzczg.setOnFrameAvailableListener(this);
        this.zzczm.countDown();
        this.zzcyw.start();
        try {
            this.zzczs = true;
            while (!this.zzczt) {
                while (this.zzczk > 0) {
                    this.zzczg.updateTexImage();
                    this.zzczk--;
                }
                if (this.zzcyw.zza(this.zzcys)) {
                    if (Float.isNaN(this.zzczd)) {
                        float[] fArr = this.zzcys;
                        float[] fArr2 = new float[]{0.0f, 1.0f, 0.0f};
                        float[] fArr3 = new float[3];
                        fArr3[0] = ((fArr[0] * fArr2[0]) + (fArr[1] * fArr2[1])) + (fArr[2] * fArr2[2]);
                        fArr3[1] = ((fArr[3] * fArr2[0]) + (fArr[4] * fArr2[1])) + (fArr[5] * fArr2[2]);
                        fArr3[2] = (fArr[8] * fArr2[2]) + ((fArr[6] * fArr2[0]) + (fArr[7] * fArr2[1]));
                        this.zzczd = -(((float) Math.atan2((double) fArr3[1], (double) fArr3[0])) - 1.5707964f);
                    }
                    zzb(this.zzczb, this.zzczd + this.zzcze);
                } else {
                    zza(this.zzcys, -1.5707964f);
                    zzb(this.zzczb, this.zzcze);
                }
                zza(this.zzcyx, 1.5707964f);
                zza(this.zzcyy, this.zzczb, this.zzcyx);
                zza(this.zzcyz, this.zzcys, this.zzcyy);
                zza(this.zzcza, this.zzczf);
                zza(this.zzczc, this.zzcza, this.zzcyz);
                GLES20.glUniformMatrix3fv(this.zzczj, 1, false, this.zzczc, 0);
                GLES20.glDrawArrays(5, 0, 4);
                zzdo("drawArrays");
                GLES20.glFinish();
                this.zzczo.eglSwapBuffers(this.zzczp, this.zzczr);
                if (this.zzczs) {
                    GLES20.glViewport(0, 0, this.zzuq, this.zzur);
                    zzdo("viewport");
                    i2 = GLES20.glGetUniformLocation(this.zzczi, "uFOVx");
                    int glGetUniformLocation = GLES20.glGetUniformLocation(this.zzczi, "uFOVy");
                    if (this.zzuq > this.zzur) {
                        GLES20.glUniform1f(i2, 0.87266463f);
                        GLES20.glUniform1f(glGetUniformLocation, (0.87266463f * ((float) this.zzur)) / ((float) this.zzuq));
                    } else {
                        GLES20.glUniform1f(i2, (0.87266463f * ((float) this.zzuq)) / ((float) this.zzur));
                        GLES20.glUniform1f(glGetUniformLocation, 0.87266463f);
                    }
                    this.zzczs = false;
                }
                try {
                    synchronized (this.zzczn) {
                        if (!(this.zzczt || this.zzczs || this.zzczk != 0)) {
                            this.zzczn.wait();
                        }
                    }
                } catch (InterruptedException e) {
                }
            }
        } catch (IllegalStateException e2) {
            zzane.zzdk("SphericalVideoProcessor halted unexpectedly.");
        } catch (Throwable th) {
            zzane.zzb("SphericalVideoProcessor died.", th);
            zzbv.zzeo().zza(th, "SphericalVideoProcessor.run.2");
        } finally {
            this.zzcyw.stop();
            this.zzczg.setOnFrameAvailableListener(null);
            this.zzczg = null;
            zztk();
        }
    }

    public final void zza(SurfaceTexture surfaceTexture, int i, int i2) {
        this.zzuq = i;
        this.zzur = i2;
        this.zzczh = surfaceTexture;
    }

    public final void zzb(float f, float f2) {
        float f3;
        float f4;
        if (this.zzuq > this.zzur) {
            f3 = (1.7453293f * f) / ((float) this.zzuq);
            f4 = (1.7453293f * f2) / ((float) this.zzuq);
        } else {
            f3 = (1.7453293f * f) / ((float) this.zzur);
            f4 = (1.7453293f * f2) / ((float) this.zzur);
        }
        this.zzcze -= f3;
        this.zzczf -= f4;
        if (this.zzczf < -1.5707964f) {
            this.zzczf = -1.5707964f;
        }
        if (this.zzczf > 1.5707964f) {
            this.zzczf = 1.5707964f;
        }
    }

    public final void zzh(int i, int i2) {
        synchronized (this.zzczn) {
            this.zzuq = i;
            this.zzur = i2;
            this.zzczs = true;
            this.zzczn.notifyAll();
        }
    }

    public final void zznn() {
        synchronized (this.zzczn) {
            this.zzczn.notifyAll();
        }
    }

    public final void zzti() {
        synchronized (this.zzczn) {
            this.zzczt = true;
            this.zzczh = null;
            this.zzczn.notifyAll();
        }
    }

    public final SurfaceTexture zztj() {
        if (this.zzczh == null) {
            return null;
        }
        try {
            this.zzczm.await();
        } catch (InterruptedException e) {
        }
        return this.zzczg;
    }
}
