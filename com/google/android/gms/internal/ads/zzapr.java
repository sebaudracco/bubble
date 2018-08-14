package com.google.android.gms.internal.ads;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Display;
import android.view.WindowManager;
import javax.annotation.concurrent.GuardedBy;

@zzadh
final class zzapr implements SensorEventListener {
    private final SensorManager zzcyn;
    private final Object zzcyo = new Object();
    private final Display zzcyp;
    private final float[] zzcyq = new float[9];
    private final float[] zzcyr = new float[9];
    @GuardedBy("mSensorThreadLock")
    private float[] zzcys;
    private Handler zzcyt;
    private zzapt zzcyu;

    zzapr(Context context) {
        this.zzcyn = (SensorManager) context.getSystemService("sensor");
        this.zzcyp = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
    }

    private final void zzg(int i, int i2) {
        float f = this.zzcyr[i];
        this.zzcyr[i] = this.zzcyr[i2];
        this.zzcyr[i2] = f;
    }

    public final void onAccuracyChanged(Sensor sensor, int i) {
    }

    public final void onSensorChanged(SensorEvent sensorEvent) {
        float[] fArr = sensorEvent.values;
        if (fArr[0] != 0.0f || fArr[1] != 0.0f || fArr[2] != 0.0f) {
            synchronized (this.zzcyo) {
                if (this.zzcys == null) {
                    this.zzcys = new float[9];
                }
            }
            SensorManager.getRotationMatrixFromVector(this.zzcyq, fArr);
            switch (this.zzcyp.getRotation()) {
                case 1:
                    SensorManager.remapCoordinateSystem(this.zzcyq, 2, 129, this.zzcyr);
                    break;
                case 2:
                    SensorManager.remapCoordinateSystem(this.zzcyq, 129, 130, this.zzcyr);
                    break;
                case 3:
                    SensorManager.remapCoordinateSystem(this.zzcyq, 130, 1, this.zzcyr);
                    break;
                default:
                    System.arraycopy(this.zzcyq, 0, this.zzcyr, 0, 9);
                    break;
            }
            zzg(1, 3);
            zzg(2, 6);
            zzg(5, 7);
            synchronized (this.zzcyo) {
                System.arraycopy(this.zzcyr, 0, this.zzcys, 0, 9);
            }
            if (this.zzcyu != null) {
                this.zzcyu.zznn();
            }
        }
    }

    final void start() {
        if (this.zzcyt == null) {
            Sensor defaultSensor = this.zzcyn.getDefaultSensor(11);
            if (defaultSensor == null) {
                zzane.m3427e("No Sensor of TYPE_ROTATION_VECTOR");
                return;
            }
            HandlerThread handlerThread = new HandlerThread("OrientationMonitor");
            handlerThread.start();
            this.zzcyt = new Handler(handlerThread.getLooper());
            if (!this.zzcyn.registerListener(this, defaultSensor, 0, this.zzcyt)) {
                zzane.m3427e("SensorManager.registerListener failed.");
                stop();
            }
        }
    }

    final void stop() {
        if (this.zzcyt != null) {
            this.zzcyn.unregisterListener(this);
            this.zzcyt.post(new zzaps(this));
            this.zzcyt = null;
        }
    }

    final void zza(zzapt com_google_android_gms_internal_ads_zzapt) {
        this.zzcyu = com_google_android_gms_internal_ads_zzapt;
    }

    final boolean zza(float[] fArr) {
        boolean z = false;
        synchronized (this.zzcyo) {
            if (this.zzcys == null) {
            } else {
                System.arraycopy(this.zzcys, 0, fArr, 0, this.zzcys.length);
                z = true;
            }
        }
        return z;
    }
}
