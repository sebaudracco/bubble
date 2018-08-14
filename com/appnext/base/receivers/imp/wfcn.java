package com.appnext.base.receivers.imp;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.NetworkInfo.DetailedState;
import android.os.Handler;
import android.text.TextUtils;
import com.appnext.base.C1061b;
import com.appnext.base.p019a.p021b.C1021c;
import com.appnext.base.p023b.C1042c;
import com.appnext.base.p023b.C1042c.C1041a;
import com.appnext.base.p023b.C1043d;
import com.appnext.base.p023b.C1044e;
import com.appnext.base.p023b.C1045f;
import com.appnext.base.p023b.C1048i;
import com.appnext.base.p023b.C1058l;
import com.appnext.base.receivers.C1080b;

public class wfcn extends C1080b {
    public static final String hl = wfcn.class.getSimpleName();
    public static final String iT = "LAST_WIFI_CONNECTION";
    public static final String iU = (hl + "IS_CONNENTED");
    private static final String iV = "<unknown ssid>";

    public IntentFilter getBRFilter() {
        try {
            if (hasPermission()) {
                return new IntentFilter("android.net.wifi.STATE_CHANGE");
            }
            return null;
        } catch (Throwable th) {
            C1061b.m2191a(th);
            return null;
        }
    }

    public boolean hasPermission() {
        return C1045f.m2133g(C1043d.getContext().getApplicationContext(), "android.permission.ACCESS_NETWORK_STATE");
    }

    public void onReceive(Context context, Intent intent) {
        try {
            super.onReceive(context, intent);
            C1058l.m2184k("Receiver", hl);
            if (intent.getAction().equals("android.net.wifi.STATE_CHANGE")) {
                C1021c av = C1044e.cs().av(hl);
                if (av != null && !C1042c.jG.equalsIgnoreCase(av.ba())) {
                    if (hasPermission()) {
                        Boolean valueOf;
                        String str = "";
                        NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
                        if (networkInfo != null && networkInfo.isConnected() && networkInfo.getDetailedState() == DetailedState.CONNECTED) {
                            valueOf = Boolean.valueOf(true);
                            str = networkInfo.getExtraInfo();
                            if (!TextUtils.isEmpty(str) && !str.equals(iV)) {
                                str = str.replace("\"", "");
                                C1048i.cy().putString(iT, str);
                            } else {
                                return;
                            }
                        } else if (networkInfo.getDetailedState() == DetailedState.DISCONNECTED) {
                            valueOf = Boolean.valueOf(false);
                            str = C1048i.cy().getString(iT, "");
                        } else {
                            return;
                        }
                        final boolean booleanValue = valueOf.booleanValue();
                        C1048i.cy().putBoolean(iU, valueOf.booleanValue());
                        new Handler().postDelayed(new Runnable(this) {
                            final /* synthetic */ wfcn iY;

                            public void run() {
                                this.iY.m2250b(wfcn.hl, this.iY.m2251a(Boolean.valueOf(booleanValue), str), C1041a.JSONArray);
                            }
                        }, 15000);
                        return;
                    }
                    C1058l.m2186m(hl, "No permission granted android.permission.ACCESS_NETWORK_STATE");
                }
            }
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
    }
}
