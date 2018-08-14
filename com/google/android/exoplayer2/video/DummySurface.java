package com.google.android.exoplayer2.video;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.Surface;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

@TargetApi(17)
public final class DummySurface extends Surface {
    private static final int EGL_PROTECTED_CONTENT_EXT = 12992;
    public static final boolean SECURE_SUPPORTED;
    private static final String TAG = "DummySurface";
    public final boolean secure;
    private final DummySurfaceThread thread;
    private boolean threadReleased;

    private static class DummySurfaceThread extends HandlerThread implements OnFrameAvailableListener, Callback {
        private static final int MSG_INIT = 1;
        private static final int MSG_RELEASE = 3;
        private static final int MSG_UPDATE_TEXTURE = 2;
        private Handler handler;
        private Error initError;
        private RuntimeException initException;
        private DummySurface surface;
        private SurfaceTexture surfaceTexture;
        private final int[] textureIdHolder = new int[1];

        public DummySurfaceThread() {
            super("dummySurface");
        }

        public DummySurface init(boolean secure) {
            int i = 1;
            start();
            this.handler = new Handler(getLooper(), this);
            boolean wasInterrupted = false;
            synchronized (this) {
                Handler handler = this.handler;
                if (!secure) {
                    i = 0;
                }
                handler.obtainMessage(1, i, 0).sendToTarget();
                while (this.surface == null && this.initException == null && this.initError == null) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        wasInterrupted = true;
                    }
                }
            }
            if (wasInterrupted) {
                Thread.currentThread().interrupt();
            }
            if (this.initException != null) {
                throw this.initException;
            } else if (this.initError == null) {
                return this.surface;
            } else {
                throw this.initError;
            }
        }

        public void release() {
            this.handler.sendEmptyMessage(3);
        }

        public void onFrameAvailable(SurfaceTexture surfaceTexture) {
            this.handler.sendEmptyMessage(2);
        }

        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    try {
                        initInternal(msg.arg1 != 0);
                        synchronized (this) {
                            notify();
                        }
                        break;
                    } catch (RuntimeException e) {
                        Log.e(DummySurface.TAG, "Failed to initialize dummy surface", e);
                        this.initException = e;
                        synchronized (this) {
                            notify();
                            break;
                        }
                    } catch (Error e2) {
                        Log.e(DummySurface.TAG, "Failed to initialize dummy surface", e2);
                        this.initError = e2;
                        synchronized (this) {
                            notify();
                            break;
                        }
                    } catch (Throwable th) {
                        synchronized (this) {
                            notify();
                        }
                    }
                case 2:
                    this.surfaceTexture.updateTexImage();
                    break;
                case 3:
                    try {
                        releaseInternal();
                        break;
                    } catch (Throwable e3) {
                        Log.e(DummySurface.TAG, "Failed to release dummy surface", e3);
                        break;
                    } finally {
                        quit();
                    }
            }
            return true;
        }

        private void initInternal(boolean secure) {
            int[] glAttributes;
            int[] iArr;
            EGLDisplay display = EGL14.eglGetDisplay(0);
            Assertions.checkState(display != null, "eglGetDisplay failed");
            int[] version = new int[2];
            Assertions.checkState(EGL14.eglInitialize(display, version, 0, version, 1), "eglInitialize failed");
            EGLConfig[] configs = new EGLConfig[1];
            int[] numConfigs = new int[1];
            boolean z = EGL14.eglChooseConfig(display, new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12327, 12344, 12339, 4, 12344}, 0, configs, 0, 1, numConfigs, 0) && numConfigs[0] > 0 && configs[0] != null;
            Assertions.checkState(z, "eglChooseConfig failed");
            EGLConfig config = configs[0];
            if (secure) {
                glAttributes = new int[]{12440, 2, DummySurface.EGL_PROTECTED_CONTENT_EXT, 1, 12344};
            } else {
                glAttributes = new int[]{12440, 2, 12344};
            }
            EGLContext context = EGL14.eglCreateContext(display, config, EGL14.EGL_NO_CONTEXT, glAttributes, 0);
            Assertions.checkState(context != null, "eglCreateContext failed");
            if (secure) {
                iArr = new int[7];
                iArr = new int[]{12375, 1, 12374, 1, DummySurface.EGL_PROTECTED_CONTENT_EXT, 1, 12344};
            } else {
                iArr = new int[5];
                iArr = new int[]{12375, 1, 12374, 1, 12344};
            }
            EGLSurface pbuffer = EGL14.eglCreatePbufferSurface(display, config, iArr, 0);
            Assertions.checkState(pbuffer != null, "eglCreatePbufferSurface failed");
            Assertions.checkState(EGL14.eglMakeCurrent(display, pbuffer, pbuffer, context), "eglMakeCurrent failed");
            GLES20.glGenTextures(1, this.textureIdHolder, 0);
            this.surfaceTexture = new SurfaceTexture(this.textureIdHolder[0]);
            this.surfaceTexture.setOnFrameAvailableListener(this);
            this.surface = new DummySurface(this, this.surfaceTexture, secure);
        }

        private void releaseInternal() {
            try {
                this.surfaceTexture.release();
            } finally {
                this.surface = null;
                this.surfaceTexture = null;
                GLES20.glDeleteTextures(1, this.textureIdHolder, 0);
            }
        }
    }

    static {
        boolean z = false;
        if (Util.SDK_INT >= 17) {
            String extensions = EGL14.eglQueryString(EGL14.eglGetDisplay(0), 12373);
            if (extensions != null && extensions.contains("EGL_EXT_protected_content")) {
                z = true;
            }
            SECURE_SUPPORTED = z;
            return;
        }
        SECURE_SUPPORTED = false;
    }

    public static DummySurface newInstanceV17(boolean secure) {
        assertApiLevel17OrHigher();
        boolean z = !secure || SECURE_SUPPORTED;
        Assertions.checkState(z);
        return new DummySurfaceThread().init(secure);
    }

    private DummySurface(DummySurfaceThread thread, SurfaceTexture surfaceTexture, boolean secure) {
        super(surfaceTexture);
        this.thread = thread;
        this.secure = secure;
    }

    public void release() {
        super.release();
        synchronized (this.thread) {
            if (!this.threadReleased) {
                this.thread.release();
                this.threadReleased = true;
            }
        }
    }

    private static void assertApiLevel17OrHigher() {
        if (Util.SDK_INT < 17) {
            throw new UnsupportedOperationException("Unsupported prior to API level 17");
        }
    }
}
