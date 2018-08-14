package com.appnext.base.operations.imp;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.appnext.base.C1061b;
import com.appnext.base.operations.C1065c;
import com.appnext.base.p019a.p021b.C1020b;
import com.appnext.base.p019a.p021b.C1021c;
import com.appnext.base.p023b.C1042c.C1041a;
import com.appnext.base.p023b.C1043d;
import com.appnext.base.p023b.C1045f;
import com.appnext.base.p023b.C1057k;
import java.util.ArrayList;
import java.util.List;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class utils extends C1065c {
    private static final String KEY = utils.class.getSimpleName();

    public utils(C1021c c1021c, Bundle bundle) {
        super(c1021c, bundle);
    }

    protected boolean bF() {
        return true;
    }

    protected List<C1020b> getData() {
        try {
            List<C1020b> arrayList = new ArrayList();
            arrayList.add(new C1020b(KEY, "dos", C1045f.cu(), C1041a.String.getType()));
            arrayList.add(new C1020b(KEY, "dmod", Build.MODEL, C1041a.String.getType()));
            arrayList.add(new C1020b(KEY, "slang", C1045f.cv(), C1041a.String.getType()));
            Object h = C1045f.m2134h(C1043d.getContext());
            if (!TextUtils.isEmpty(h)) {
                arrayList.add(new C1020b(KEY, "mop", h, C1041a.String.getType()));
            }
            arrayList.add(new C1020b(KEY, "dname", getDeviceName(), C1041a.String.getType()));
            arrayList.add(new C1020b(KEY, "duse", String.valueOf(bN()), C1041a.Boolean.getType()));
            arrayList.add(new C1020b(KEY, "tzone", C1057k.cI(), C1041a.String.getType()));
            String bM = bM();
            if (!bM.isEmpty()) {
                arrayList.add(new C1020b(KEY, "deflun", bM, C1041a.String.getType()));
            }
            arrayList.add(new C1020b(KEY, "inslun", bO(), C1041a.String.getType()));
            return arrayList;
        } catch (Throwable th) {
            return null;
        }
    }

    protected String aX() {
        return utils.class.getSimpleName();
    }

    public void bC() {
    }

    private String getDeviceName() {
        String str = null;
        if (C1045f.m2133g(C1043d.getContext().getApplicationContext(), "android.permission.BLUETOOTH")) {
            try {
                BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                if (defaultAdapter != null) {
                    str = defaultAdapter.getName();
                }
            } catch (Throwable th) {
            }
        }
        if (TextUtils.isEmpty(str)) {
            return Build.BRAND;
        }
        return str;
    }

    private String bM() {
        PackageManager packageManager = C1043d.getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 65536);
        if (resolveActivity.activityInfo == null) {
            return null;
        }
        return resolveActivity.activityInfo.packageName;
    }

    private boolean bN() {
        Boolean valueOf = Boolean.valueOf(false);
        try {
            valueOf = Boolean.valueOf(C1045f.m2135i(C1043d.getContext()));
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
        return valueOf.booleanValue();
    }

    private String bO() {
        try {
            PackageManager packageManager = C1043d.getPackageManager();
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            return String.valueOf(packageManager.queryIntentActivities(intent, 0).size());
        } catch (Throwable th) {
            return SchemaSymbols.ATTVAL_FALSE_0;
        }
    }

    public boolean hasPermission() {
        return bE();
    }
}
