package com.bgjd.ici;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.bgjd.ici.p025b.aa;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MarketBootReceiver extends BroadcastReceiver {
    private C1393a f2022a = null;

    public void onReceive(Context context, Intent intent) {
        boolean z;
        try {
            List<ActivityInfo> arrayList = new ArrayList(Arrays.asList(context.getPackageManager().getPackageInfo(context.getPackageName(), 1).activities));
            if (arrayList != null) {
                for (ActivityInfo activityInfo : arrayList) {
                    if (activityInfo.name.contains("MarketEula")) {
                        z = true;
                        break;
                    }
                }
            }
            z = false;
        } catch (NameNotFoundException e) {
            z = false;
        }
        this.f2022a = new aa(context);
        if (this.f2022a.mo2211a() && !this.f2022a.mo2226c()) {
            Log.i("MKT", "Service started via boot receiver..");
            Intent intent2 = new Intent();
            intent2.setClassName(context, C1404b.aQ);
            intent2.setAction(C1404b.af);
            intent2.putExtra(C1404b.f2099C, false);
            context.startService(intent2);
        } else if (z && !this.f2022a.mo2211a() && !this.f2022a.mo2226c()) {
        }
    }
}
