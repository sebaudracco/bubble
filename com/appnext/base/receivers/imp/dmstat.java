package com.appnext.base.receivers.imp;

import android.text.TextUtils;
import com.appnext.base.C1061b;
import com.appnext.base.p019a.C1017a;
import com.appnext.base.p019a.p021b.C1021c;
import com.appnext.base.p023b.C1039a;
import com.appnext.base.p023b.C1039a.C1038c;
import com.appnext.base.p023b.C1042c;
import com.appnext.base.p023b.C1042c.C1041a;
import com.appnext.base.p023b.C1043d;
import com.appnext.base.p023b.C1045f;
import com.appnext.base.p023b.C1048i;
import com.appnext.base.p023b.C1057k;
import com.appnext.base.p023b.C1058l;
import com.appnext.base.receivers.C1078c;
import com.google.android.gms.location.DetectedActivity;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class dmstat implements C1038c, C1078c {
    private static final int MINUTE = 60000;
    protected static final String TAG = dmstat.class.getSimpleName();
    private static final String ic = "threshold";
    private static final String id = "wpuld";
    private static final String ie = "wpulw";
    private static final String f1804if = "last_dmstat";
    private static final String ig = "value";
    private static final String ih = "accuracy";
    private static final int ii = 75;
    private static final int ij = 15;
    private static final int ik = 35;
    private static final int il = 2;
    private C1021c gP = C1017a.aM().aR().ab(TAG);
    private int im = 75;
    private int in = 15;
    private int io = 35;
    private C1039a ip = null;

    private class DetectedActivityComparator implements Comparator<DetectedActivity> {
        final /* synthetic */ dmstat ir;

        private DetectedActivityComparator(dmstat com_appnext_base_receivers_imp_dmstat) {
            this.ir = com_appnext_base_receivers_imp_dmstat;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m2257a((DetectedActivity) obj, (DetectedActivity) obj2);
        }

        public int m2257a(DetectedActivity detectedActivity, DetectedActivity detectedActivity2) {
            return detectedActivity.getConfidence() > detectedActivity2.getConfidence() ? 1 : 0;
        }
    }

    public boolean hasPermission() {
        return C1045f.m2133g(C1043d.getContext(), "com.google.android.gms.permission.ACTIVITY_RECOGNITION");
    }

    public void bB() {
        try {
            if (hasPermission()) {
                synchronized (this) {
                    long longValue;
                    try {
                        longValue = Long.valueOf(this.gP.bb()).longValue() * 60000;
                    } catch (Throwable th) {
                        longValue = 600000;
                    }
                    this.ip = new C1039a();
                    this.ip.m2122a((C1038c) this);
                    this.ip.m2123b(longValue);
                }
            }
        } catch (Throwable th2) {
            C1061b.m2191a(th2);
        }
    }

    public void bC() {
        synchronized (this) {
            if (this.ip != null) {
                this.ip.m2122a(null);
                this.ip.stop();
                this.ip = null;
            }
        }
    }

    private void m2261g(List<DetectedActivity> list) {
        Iterator listIterator = list.listIterator();
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        while (listIterator.hasNext()) {
            boolean z4;
            DetectedActivity detectedActivity = (DetectedActivity) listIterator.next();
            int type = detectedActivity.getType();
            if (detectedActivity.getConfidence() > this.in && (type == 0 || type == 1)) {
                z3 = true;
            }
            if (detectedActivity.getConfidence() > this.io && (type == 7 || type == 8)) {
                z2 = true;
            }
            if (detectedActivity.getConfidence() == 100 && type == 3) {
                z4 = true;
            } else {
                z4 = z;
            }
            if (z2 || (z4 && !z3)) {
                C1057k.m2176e(C1042c.jU, String.valueOf(false), C1041a.Boolean);
                return;
            }
            if (z3) {
                C1057k.m2176e(C1042c.jU, String.valueOf(true), C1041a.Boolean);
            }
            z = z4;
        }
    }

    public void mo2051h(List<DetectedActivity> list) {
        if (list != null) {
            try {
                if (list.size() != 0) {
                    DetectedActivity detectedActivity;
                    if (this.gP == null) {
                        this.im = 75;
                        this.in = 15;
                        this.io = 35;
                    } else {
                        this.im = this.gP.m2065b(ic, 75);
                        this.in = this.gP.m2065b(id, 15);
                        this.io = this.gP.m2065b(ie, 35);
                    }
                    Collections.sort(list, new DetectedActivityComparator());
                    Iterator listIterator = list.listIterator();
                    while (listIterator.hasNext()) {
                        detectedActivity = (DetectedActivity) listIterator.next();
                        int type = detectedActivity.getType();
                        if (detectedActivity.getConfidence() < this.im || !(type == 0 || type == 3 || type == 1 || type == 7 || type == 8)) {
                            listIterator.remove();
                        }
                    }
                    m2261g(list);
                    if (list.size() != 0) {
                        detectedActivity = (DetectedActivity) list.get(0);
                        list.remove(0);
                        final String a = m2258a(detectedActivity, (List) list);
                        new Thread(new Runnable(this) {
                            final /* synthetic */ dmstat ir;

                            public void run() {
                                this.ir.ao(a);
                            }
                        }).start();
                        return;
                    }
                    return;
                }
            } catch (Throwable th) {
                C1058l.m2187n(TAG, th.toString());
                return;
            } finally {
                new Thread(/* anonymous class already generated */).start();
            }
        }
        new Thread(/* anonymous class already generated */).start();
    }

    private void ao(String str) {
        if (!TextUtils.isEmpty(str)) {
            C1057k.m2175d(TAG, str, C1041a.JSONArray);
        }
    }

    public void onError(String str) {
    }

    private String m2258a(DetectedActivity detectedActivity, List<DetectedActivity> list) {
        if (detectedActivity == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(m2259a(detectedActivity.getType(), detectedActivity.getConfidence()));
        if (list != null && list.size() > 0) {
            for (int i = 0; i < 1; i++) {
                DetectedActivity detectedActivity2 = (DetectedActivity) list.get(i);
                if (detectedActivity2 != null && detectedActivity.getConfidence() - detectedActivity2.getConfidence() <= 10) {
                    jSONArray.put(m2259a(detectedActivity2.getType(), detectedActivity2.getConfidence()));
                }
            }
        }
        String string = C1048i.cy().getString(f1804if, null);
        C1048i.cy().putString(f1804if, jSONArray.toString());
        if (string == null) {
            return jSONArray.toString();
        }
        try {
            JSONArray jSONArray2 = new JSONArray(string);
            if (jSONArray2.length() != jSONArray.length()) {
                return jSONArray.toString();
            }
            int length = jSONArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                if (jSONArray2.getJSONObject(i2).getInt("value") != jSONArray.getJSONObject(i2).getInt("value")) {
                    return jSONArray.toString();
                }
            }
            return null;
        } catch (Throwable th) {
            return jSONArray.toString();
        }
    }

    private JSONObject m2259a(int i, int i2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("value", i);
            jSONObject.put(ih, i2);
            return jSONObject;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public boolean register() {
        bB();
        return true;
    }

    public void unregister() {
        bC();
    }
}
