package com.appnext.base.operations.imp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.appnext.base.operations.C1063a;
import com.appnext.base.operations.C1066d;
import com.appnext.base.p019a.p021b.C1020b;
import com.appnext.base.p019a.p021b.C1021c;
import com.appnext.base.p023b.C1042c.C1041a;
import com.appnext.base.p023b.C1043d;
import com.appnext.base.p023b.C1045f;
import com.appnext.base.p023b.C1048i;
import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.awareness.snapshot.WeatherResult;
import com.google.android.gms.awareness.state.Weather;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import java.util.ArrayList;
import java.util.List;

public class ulwc extends C1063a {
    private static final int hn = 1;
    private static final int ho = 2;
    private GoogleApiClient hp;
    private int hq = 0;
    private List<Integer> hr;

    class C10701 implements OnConnectionFailedListener {
        final /* synthetic */ ulwc hs;

        C10701(ulwc com_appnext_base_operations_imp_ulwc) {
            this.hs = com_appnext_base_operations_imp_ulwc;
        }

        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            this.hs.bC();
        }
    }

    class C10712 implements ConnectionCallbacks {
        final /* synthetic */ ulwc hs;

        C10712(ulwc com_appnext_base_operations_imp_ulwc) {
            this.hs = com_appnext_base_operations_imp_ulwc;
        }

        public void onConnected(@Nullable Bundle bundle) {
            this.hs.bL();
        }

        public void onConnectionSuspended(int i) {
            this.hs.bC();
        }
    }

    class C10723 implements ResultCallback<WeatherResult> {
        final /* synthetic */ ulwc hs;

        C10723(ulwc com_appnext_base_operations_imp_ulwc) {
            this.hs = com_appnext_base_operations_imp_ulwc;
        }

        public void onResult(@NonNull WeatherResult weatherResult) {
            if (weatherResult.getStatus().isSuccess()) {
                Weather weather = weatherResult.getWeather();
                if (weather != null) {
                    Log.e("Weather", weather.toString());
                }
                int[] conditions = weather.getConditions();
                if (conditions != null) {
                    for (int valueOf : conditions) {
                        if (this.hs.hr.contains(Integer.valueOf(valueOf))) {
                            this.hs.m2217e(1);
                            return;
                        }
                    }
                    this.hs.m2217e(2);
                    return;
                }
                return;
            }
            this.hs.bC();
        }
    }

    public ulwc(C1021c c1021c, Bundle bundle) {
        super(c1021c, bundle);
        bK();
    }

    private void bK() {
        this.hr = new ArrayList();
        this.hr.add(Integer.valueOf(5));
        this.hr.add(Integer.valueOf(8));
        this.hr.add(Integer.valueOf(6));
        this.hr.add(Integer.valueOf(7));
        this.hr.add(Integer.valueOf(2));
    }

    public void bB() {
        if (hasPermission()) {
            this.hp = new Builder(C1043d.getContext()).addApi(Awareness.API).addConnectionCallbacks(new C10712(this)).addOnConnectionFailedListener(new C10701(this)).build();
            this.hp.connect();
            return;
        }
        C1066d.bG().m2200a(this);
    }

    private void bL() {
        Awareness.SnapshotApi.getWeather(this.hp).setResultCallback(new C10723(this));
    }

    public void bC() {
        if (this.hp != null && this.hp.isConnected()) {
            this.hp.disconnect();
        }
    }

    protected List<C1020b> getData() {
        if (this.hq != 1 && this.hq != 2) {
            return null;
        }
        List<C1020b> arrayList = new ArrayList();
        arrayList.add(new C1020b(ulwc.class.getSimpleName(), String.valueOf(this.hq), C1041a.Integer.getType()));
        return arrayList;
    }

    public boolean hasPermission() {
        return C1045f.m2133g(C1043d.getContext().getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION");
    }

    private void m2217e(final int i) {
        new Thread(new Runnable(this) {
            final /* synthetic */ ulwc hs;

            public void run() {
                if (C1048i.cy().getInt(ulwc.class.getSimpleName(), 0) != i) {
                    C1048i.cy().putInt(ulwc.class.getSimpleName(), i);
                    this.hs.hq = i;
                    this.hs.bs();
                    return;
                }
                this.hs.bC();
            }
        }).start();
    }
}
