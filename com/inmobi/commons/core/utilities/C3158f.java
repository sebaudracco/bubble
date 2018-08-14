package com.inmobi.commons.core.utilities;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.PowerManager;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p112a.C3105a;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: SystemBroadcastObserver */
public class C3158f {
    private static final String f7783a = C3158f.class.getSimpleName();
    private static HashMap<String, CopyOnWriteArrayList<C2983b>> f7784b = new HashMap();
    private static HashMap<String, C3157a> f7785c = new HashMap();
    private static final Object f7786d = new Object();
    private static volatile C3158f f7787e;

    /* compiled from: SystemBroadcastObserver */
    public interface C2983b {
        void mo6163b(boolean z);
    }

    /* compiled from: SystemBroadcastObserver */
    static final class C3157a extends BroadcastReceiver {
        private static final String f7782a = C3157a.class.getSimpleName();

        C3157a() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                try {
                    if (intent.getAction() != null) {
                        boolean z;
                        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                            if (connectivityManager != null) {
                                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                                if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                                    z = true;
                                }
                            }
                            z = false;
                        } else if ("android.os.action.DEVICE_IDLE_MODE_CHANGED".equalsIgnoreCase(intent.getAction())) {
                            z = m10411a(context);
                        } else {
                            if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
                                z = true;
                            }
                            z = false;
                        }
                        C3158f.m10416b(z, intent.getAction());
                        Logger.m10359a(InternalLogLevel.INTERNAL, f7782a, intent.getAction() + " Availability:" + z);
                    }
                } catch (Exception e) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7782a, "SDK encountered unexpected error in SystemBroadReceiver.onReceive handler; " + e.getMessage());
                }
            }
        }

        @TargetApi(23)
        private boolean m10411a(Context context) {
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            if (powerManager == null || VERSION.SDK_INT < 23 || !powerManager.isDeviceIdleMode()) {
                return false;
            }
            return true;
        }
    }

    public static C3158f m10412a() {
        C3158f c3158f = f7787e;
        if (c3158f == null) {
            synchronized (f7786d) {
                c3158f = f7787e;
                if (c3158f == null) {
                    c3158f = new C3158f();
                    f7787e = c3158f;
                }
            }
        }
        return c3158f;
    }

    public void m10418a(String str, C2983b c2983b) {
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) f7784b.get(str);
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.add(c2983b);
        } else {
            copyOnWriteArrayList = new CopyOnWriteArrayList();
            copyOnWriteArrayList.add(c2983b);
        }
        f7784b.put(str, copyOnWriteArrayList);
        if (copyOnWriteArrayList.size() == 1) {
            m10413a(str);
        }
    }

    private void m10413a(String str) {
        Context b = C3105a.m10078b();
        if (b != null) {
            BroadcastReceiver c3157a = new C3157a();
            f7785c.put(str, c3157a);
            b.registerReceiver(c3157a, new IntentFilter(str));
        }
    }

    private static void m10416b(boolean z, String str) {
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) f7784b.get(str);
        if (copyOnWriteArrayList != null) {
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                try {
                    ((C2983b) it.next()).mo6163b(z);
                } catch (Exception e) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7783a, "SDK encountered unexpected error in SystemBroadcastObserver.onServiceChanged event handler; " + e.getMessage());
                }
            }
        }
    }

    public void m10417a(C2983b c2983b, String str) {
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) f7784b.get(str);
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.remove(c2983b);
            if (copyOnWriteArrayList.size() == 0) {
                m10415b(str);
            }
        }
    }

    private void m10415b(String str) {
        Context b = C3105a.m10078b();
        if (b != null && f7785c.get(str) != null) {
            b.unregisterReceiver((BroadcastReceiver) f7785c.get(str));
            f7785c.remove(str);
        }
    }
}
