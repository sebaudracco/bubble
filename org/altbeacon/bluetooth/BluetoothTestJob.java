package org.altbeacon.bluetooth;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import org.altbeacon.beacon.logging.LogManager;

@RequiresApi(21)
public class BluetoothTestJob extends JobService {
    private static final String TAG = BluetoothTestJob.class.getSimpleName();
    private static int sOverrideJobId = -1;
    @Nullable
    private Handler mHandler = null;
    @Nullable
    private HandlerThread mHandlerThread = null;

    public static void setOverrideJobId(int id) {
        sOverrideJobId = id;
    }

    public static int getJobId(Context context) {
        if (sOverrideJobId >= 0) {
            LogManager.m16375i(TAG, "Using BluetoothTestJob JobId from static override: " + sOverrideJobId, new Object[0]);
            return sOverrideJobId;
        }
        PackageItemInfo info = null;
        try {
            info = context.getPackageManager().getServiceInfo(new ComponentName(context, BluetoothTestJob.class), 128);
        } catch (NameNotFoundException e) {
        }
        if (info == null || info.metaData == null || info.metaData.get("jobId") == null) {
            throw new RuntimeException("Cannot get job id from manifest.  Make sure that the BluetoothTestJob is configured in the manifest.");
        }
        int jobId = info.metaData.getInt("jobId");
        LogManager.m16375i(TAG, "Using BluetoothTestJob JobId from manifest: " + jobId, new Object[0]);
        return jobId;
    }

    public boolean onStartJob(final JobParameters params) {
        if (this.mHandlerThread == null) {
            this.mHandlerThread = new HandlerThread("BluetoothTestThread");
            this.mHandlerThread.start();
        }
        if (this.mHandler == null) {
            this.mHandler = new Handler(this.mHandlerThread.getLooper());
        }
        this.mHandler.post(new Runnable() {
            public void run() {
                boolean found = false;
                LogManager.m16375i(BluetoothTestJob.TAG, "Bluetooth Test Job running", new Object[0]);
                int testType = params.getExtras().getInt("test_type");
                if (testType == 0) {
                    found = true;
                    LogManager.m16371d(BluetoothTestJob.TAG, "No test specified.  Done with job.", new Object[0]);
                }
                if ((testType & 1) == 1) {
                    LogManager.m16371d(BluetoothTestJob.TAG, "Scan test specified.", new Object[0]);
                    found = true;
                    if (!BluetoothMedic.getInstance().runScanTest(BluetoothTestJob.this)) {
                        LogManager.m16371d(BluetoothTestJob.TAG, "scan test failed", new Object[0]);
                    }
                }
                if ((testType & 2) == 2) {
                    if (found) {
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                        }
                    }
                    LogManager.m16371d(BluetoothTestJob.TAG, "Transmit test specified.", new Object[0]);
                    found = true;
                    if (!BluetoothMedic.getInstance().runTransmitterTest(BluetoothTestJob.this)) {
                        LogManager.m16371d(BluetoothTestJob.TAG, "transmit test failed", new Object[0]);
                    }
                }
                if (!found) {
                    LogManager.m16379w(BluetoothTestJob.TAG, "Unknown test type:" + testType + "  Exiting.", new Object[0]);
                }
                BluetoothTestJob.this.jobFinished(params, false);
            }
        });
        return true;
    }

    public boolean onStopJob(JobParameters params) {
        return true;
    }
}
