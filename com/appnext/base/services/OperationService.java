package com.appnext.base.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import com.appnext.base.C1061b;
import com.appnext.base.operations.C1066d;
import com.appnext.base.p019a.p020a.C1016a;
import com.appnext.base.p019a.p021b.C1021c;
import com.appnext.base.p023b.C1042c;
import com.appnext.base.p023b.C1043d;
import com.appnext.base.p023b.C1044e;
import com.appnext.base.p023b.C1048i;
import com.appnext.base.p023b.C1057k;
import com.google.android.gms.location.ActivityRecognitionResult;
import java.util.ArrayList;

public class OperationService extends IntentService {
    private static final String TAG = OperationService.class.getSimpleName();

    public OperationService() {
        super(OperationService.class.getName());
    }

    protected void onHandleIntent(@Nullable Intent intent) {
        if (m2280b(intent)) {
            m2282d(intent);
        } else {
            m2281c(intent);
        }
    }

    private boolean m2280b(@Nullable Intent intent) {
        if (intent == null) {
            return false;
        }
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return false;
        }
        String string = extras.getString(C1042c.jH);
        if (string == null || !string.equals(C1042c.jH)) {
            return false;
        }
        return true;
    }

    private void m2281c(@Nullable Intent intent) {
        String str = null;
        try {
            C1048i.cy().init(getApplicationContext());
            C1043d.init(getApplicationContext());
            C1016a.m2060g(getApplicationContext());
            if (C1057k.m2182p(getApplicationContext())) {
                C1048i.cy().putBoolean(C1048i.ko, true);
                return;
            }
            Bundle extras;
            C1021c av;
            if (intent != null) {
                String stringExtra = intent.getStringExtra(C1042c.jv);
                str = intent.getStringExtra(C1042c.jL);
                extras = intent.getExtras();
                av = C1044e.cs().av(stringExtra);
            } else {
                extras = null;
                av = null;
            }
            if (av != null) {
                String bc = av.bc();
                if (bc == null || bc.equalsIgnoreCase(C1042c.jB) || !C1042c.jE.equalsIgnoreCase(av.be()) || System.currentTimeMillis() - C1048i.cy().getLong(av.getKey() + C1048i.ke, 0) >= 4000) {
                    C1066d bG = C1066d.bG();
                    if (str == null) {
                        str = av.getKey();
                    }
                    bG.m2201a(str, av, extras);
                }
            }
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
    }

    private void m2282d(@Nullable Intent intent) {
        ActivityRecognitionResult extractResult = ActivityRecognitionResult.extractResult(intent);
        if (extractResult != null) {
            ArrayList arrayList = (ArrayList) extractResult.getProbableActivities();
            if (arrayList != null) {
                Intent intent2 = new Intent(C1042c.jI);
                intent2.putExtra(C1042c.jJ, arrayList);
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent2);
            }
        }
    }
}
