package com.appnext.core;

import android.app.IntentService;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.ResultReceiver;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class AdsService extends IntentService {
    public static final int ADD_PACK = 8348;
    public static final int START_APP = 8346;
    private static HashMap<String, C1137o> kX = new HashMap();
    private Runnable kY = new C10941(this);
    private Handler mHandler;
    Messenger mMessenger = new Messenger(new C1095a(this));

    class C10941 implements Runnable {
        final /* synthetic */ AdsService kZ;

        C10941(AdsService adsService) {
            this.kZ = adsService;
        }

        public void run() {
            try {
                for (Entry entry : AdsService.kX.entrySet()) {
                    C1137o c1137o = (C1137o) entry.getValue();
                    if (this.kZ.aJ(c1137o.bj)) {
                        new Bundle().putAll(c1137o.mr);
                        this.kZ.m2292a(c1137o);
                        AdsService.kX.remove(entry.getKey());
                        this.kZ.startActivity(this.kZ.getPackageManager().getLaunchIntentForPackage(c1137o.bj));
                    }
                }
                if (this.kZ.mHandler == null) {
                    this.kZ.stopSelf();
                } else if (AdsService.kX.entrySet().size() > 0) {
                    this.kZ.mHandler.postDelayed(this.kZ.kY, 10000);
                } else {
                    this.kZ.mHandler.removeCallbacksAndMessages(null);
                    this.kZ.mHandler = null;
                    this.kZ.stopSelf();
                }
            } catch (Throwable th) {
                this.kZ.stopSelf();
            }
        }
    }

    class C1095a extends Handler {
        final /* synthetic */ AdsService kZ;

        C1095a(AdsService adsService) {
            this.kZ = adsService;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case AdsService.ADD_PACK /*8348*/:
                    Bundle data = message.getData();
                    this.kZ.addPack(data.getString("package"), data, (ResultReceiver) data.getParcelable("receiver"));
                    C1128g.m2333W("Package added: " + data.getString("package"));
                    return;
                default:
                    super.handleMessage(message);
                    return;
            }
        }
    }

    public AdsService() {
        super("AdsService");
    }

    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
    }

    public void onDestroy() {
        super.onDestroy();
        kX.clear();
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages(null);
        }
        this.mHandler = null;
    }

    protected void onHandleIntent(Intent intent) {
        if (intent.getIntExtra("added_info", 0) == ADD_PACK) {
            addPack(intent.getStringExtra("package"), intent.getExtras(), (ResultReceiver) intent.getParcelableExtra("receiver"));
            C1128g.m2333W("Package added: " + intent.getStringExtra("package"));
        }
    }

    public void addPack(String str, Bundle bundle, ResultReceiver resultReceiver) {
        if (kX == null) {
            kX = new HashMap();
        }
        if (kX.containsKey(str)) {
            m2289a(str, bundle, resultReceiver);
            return;
        }
        C1137o c1137o = new C1137o();
        c1137o.bj = str;
        c1137o.mr = bundle;
        kX.put(str, c1137o);
        if (this.mHandler == null) {
            this.mHandler = new Handler();
            this.mHandler.postDelayed(this.kY, 10000);
        }
    }

    private void m2289a(String str, Bundle bundle, ResultReceiver resultReceiver) {
        C1137o c1137o = (C1137o) kX.get(str);
        if (c1137o == null) {
            addPack(str, bundle, resultReceiver);
            return;
        }
        c1137o.mr = bundle;
        kX.put(str, c1137o);
    }

    private boolean aJ(String str) {
        try {
            getPackageManager().getPackageInfo(str, 128);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    protected synchronized void m2292a(C1137o c1137o) {
        HashMap hashMap = new HashMap();
        hashMap.put("guid", c1137o.mr.getString("guid"));
        hashMap.put("zone", c1137o.mr.getString("zone") == null ? "" : c1137o.mr.getString("zone"));
        hashMap.put("adsID", C1128g.m2358u(this));
        hashMap.put("isApk", SchemaSymbols.ATTVAL_FALSE_0);
        hashMap.put("bannerid", c1137o.mr.getString("bannerid"));
        hashMap.put("placementid", c1137o.mr.getString("placementid"));
        hashMap.put("vid", c1137o.mr.getString("vid"));
        hashMap.put("tid", c1137o.mr.getString("tid", ""));
        hashMap.put("osid", "100");
        hashMap.put("auid", c1137o.mr.getString("auid", ""));
        Object installerPackageName = getPackageManager().getInstallerPackageName(c1137o.bj);
        String str = "installer";
        if (installerPackageName == null) {
            installerPackageName = "";
        }
        hashMap.put(str, installerPackageName);
        try {
            C1128g.m2336a("https://admin.appnext.com/AdminService.asmx/SetOpenV1", hashMap);
        } catch (IOException e) {
        }
    }

    public IBinder onBind(Intent intent) {
        return this.mMessenger.getBinder();
    }

    public boolean onUnbind(Intent intent) {
        return false;
    }
}
