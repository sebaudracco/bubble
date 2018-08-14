package com.appnext.base.p023b;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import com.appnext.base.C1061b;
import com.appnext.base.services.OperationService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.DetectedActivity;
import java.util.ArrayList;
import java.util.List;

public class C1039a {
    protected static final String TAG = C1039a.class.getSimpleName();
    private GoogleApiClient jd;
    private C1037b je;
    private C1036a jf = null;
    private long jg;
    private C1038c jh;

    class C10341 implements ResultCallback<Status> {
        final /* synthetic */ C1039a ji;

        C10341(C1039a c1039a) {
            this.ji = c1039a;
        }

        public void onResult(@NonNull Status status) {
            synchronized (this.ji) {
                if (!status.isSuccess()) {
                    this.ji.aq(C1039a.TAG + " " + status.getStatusMessage());
                }
            }
        }
    }

    class C10352 implements ResultCallback<Status> {
        final /* synthetic */ C1039a ji;

        C10352(C1039a c1039a) {
            this.ji = c1039a;
        }

        public void onResult(@NonNull Status status) {
            try {
                if (this.ji.jd != null && this.ji.jd.isConnected()) {
                    this.ji.jd.disconnect();
                }
                this.ji.jd = null;
                this.ji.je = null;
            } catch (Throwable th) {
                C1061b.m2191a(th);
            }
        }
    }

    private class C1036a extends BroadcastReceiver {
        final /* synthetic */ C1039a ji;

        private C1036a(C1039a c1039a) {
            this.ji = c1039a;
        }

        public void onReceive(Context context, Intent intent) {
            if (context != null) {
                C1043d.init(context);
                ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra(C1042c.jJ);
                synchronized (this.ji) {
                    this.ji.m2121g(parcelableArrayListExtra);
                }
            }
        }
    }

    private class C1037b implements ConnectionCallbacks, OnConnectionFailedListener {
        final /* synthetic */ C1039a ji;

        private C1037b(C1039a c1039a) {
            this.ji = c1039a;
        }

        public void onConnected(Bundle bundle) {
            synchronized (this.ji) {
                this.ji.cm();
            }
        }

        public void onConnectionSuspended(int i) {
            synchronized (this.ji) {
                if (this.ji.jd != null) {
                    this.ji.jd.connect();
                }
            }
        }

        public void onConnectionFailed(@Nullable ConnectionResult connectionResult) {
            synchronized (this.ji) {
                if (connectionResult != null) {
                    try {
                        this.ji.aq(connectionResult.getErrorMessage());
                    } catch (Throwable th) {
                        this.ji.aq(C1039a.TAG + " Error connecting GoogleApiClient");
                    }
                } else {
                    this.ji.aq(C1039a.TAG + " Error connecting GoogleApiClient");
                }
            }
        }
    }

    public interface C1038c {
        void mo2051h(List<DetectedActivity> list);

        void onError(String str);
    }

    public void m2122a(C1038c c1038c) {
        this.jh = c1038c;
    }

    public void m2123b(long j) {
        synchronized (this) {
            this.jg = j;
            if (cl()) {
                this.jd.connect();
            } else {
                aq(TAG + " Google Api ActivityRecognition not available.");
            }
        }
    }

    public void stop() {
        synchronized (this) {
            if (this.jf != null) {
                LocalBroadcastManager.getInstance(C1043d.getContext()).unregisterReceiver(this.jf);
                this.jf = null;
            }
            cn();
        }
    }

    private boolean cl() {
        try {
            this.je = new C1037b();
            this.jd = new Builder(C1043d.getContext()).addConnectionCallbacks(this.je).addOnConnectionFailedListener(this.je).addApi(ActivityRecognition.API).build();
            return true;
        } catch (Throwable th) {
            C1061b.m2191a(th);
            return false;
        }
    }

    private static boolean hasPermission() {
        return C1045f.m2133g(C1043d.getContext(), "com.google.android.gms.permission.ACTIVITY_RECOGNITION");
    }

    @SuppressLint({"all"})
    private void cm() {
        try {
            if (!C1039a.hasPermission()) {
                aq(TAG + " No permissions for activity recognition.");
            } else if (this.jd == null || !this.jd.isConnected()) {
                aq(TAG + " Google Api Client not connected.");
            } else {
                this.jf = new C1036a();
                LocalBroadcastManager.getInstance(C1043d.getContext()).registerReceiver(this.jf, new IntentFilter(C1042c.jI));
                ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates(this.jd, this.jg, co()).setResultCallback(new C10341(this));
            }
        } catch (Throwable th) {
            aq(TAG + " Google Api Client not connected.");
        }
    }

    @SuppressLint({"all"})
    private void cn() {
        try {
            if (this.jd == null) {
                return;
            }
            if (!this.jd.isConnected()) {
                this.jd.disconnect();
                this.jd = null;
                this.je = null;
            } else if (C1039a.hasPermission()) {
                ActivityRecognition.ActivityRecognitionApi.removeActivityUpdates(this.jd, co()).setResultCallback(new C10352(this));
            } else {
                this.jd.disconnect();
                this.jd = null;
                this.je = null;
            }
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
    }

    private PendingIntent co() {
        Intent intent = new Intent(C1043d.getContext(), OperationService.class);
        intent.putExtra(C1042c.jH, C1042c.jH);
        return PendingIntent.getService(C1043d.getContext(), 0, intent, 134217728);
    }

    private void m2121g(ArrayList<DetectedActivity> arrayList) {
        if (this.jh != null) {
            this.jh.mo2051h(arrayList);
        }
    }

    private void aq(String str) {
        if (this.jh != null) {
            this.jh.onError(str);
        }
    }
}
