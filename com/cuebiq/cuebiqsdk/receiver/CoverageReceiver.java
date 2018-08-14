package com.cuebiq.cuebiqsdk.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import com.cuebiq.cuebiqsdk.CuebiqSDKImpl;
import com.cuebiq.cuebiqsdk.model.CollectorRequest;
import com.cuebiq.cuebiqsdk.model.CollectorRequest.CollectorCallback;
import com.cuebiq.cuebiqsdk.model.CoverageManager;
import com.cuebiq.cuebiqsdk.model.CoverageManager.CoverageStatus;
import com.cuebiq.cuebiqsdk.model.manager.AlgorithmScheduler.AlgorithmSchedulerStatus;
import com.cuebiq.cuebiqsdk.model.persistence.PersistenceManagerFactory;
import com.cuebiq.cuebiqsdk.model.wrapper.Auth;
import com.cuebiq.cuebiqsdk.model.wrapper.Device;
import com.cuebiq.cuebiqsdk.utils.InformationList;
import com.cuebiq.cuebiqsdk.utils.Utils;
import com.google.android.gms.location.LocationResult;

public class CoverageReceiver extends BroadcastReceiver {
    public static final int ALARM_TRACK_REQUEST_CODE = 111;
    public static final int NEW_REQUEST = 0;
    public static final String REQUEST_CODE_KEY = "requestCode";
    private Handler mWorkerHandler;

    public enum TRIGGER_TYPE {
        ALARM,
        GEOFENCES,
        APP_OPENED
    }

    private boolean collect(Context context, Location location, boolean z, boolean z2, TRIGGER_TYPE trigger_type) {
        final Context context2 = context;
        final Location location2 = location;
        final boolean z3 = z;
        final boolean z4 = z2;
        final TRIGGER_TYPE trigger_type2 = trigger_type;
        Callback c17041 = new Callback() {

            class C17031 implements CollectorCallback {
                C17031() {
                }

                public void onCollectorFinished() {
                    CuebiqSDKImpl.log("CoverageReceiver -> ...acquisition completed.");
                }
            }

            public boolean handleMessage(Message message) {
                if (message.what == 0) {
                    new CollectorRequest().collect(context2.getApplicationContext(), Auth.build(context2.getApplicationContext()), Device.build(context2.getApplicationContext()), location2, z3, z4, new C17031(), trigger_type2);
                }
                return false;
            }
        };
        if (CuebiqSDKImpl.getWorkerThread() == null) {
            Log.i("CuebiqSDK", "CuebiqSDK is not working fine, please contact Cuebiq.");
            return true;
        }
        this.mWorkerHandler = new Handler(CuebiqSDKImpl.getWorkerThread().getLooper(), c17041);
        this.mWorkerHandler.sendEmptyMessage(0);
        return false;
    }

    public void onReceive(Context context, Intent intent) {
        if (!Utils.isAndroidVersionNotSupported(CuebiqSDKImpl.getBeAudienceConfiguration(context).getAmvs())) {
            if (!PersistenceManagerFactory.get().isSDKCollectionEnabled(context)) {
                CuebiqSDKImpl.log("CuebiqSDK -> SDK has explicit opt-out, stop tracking.");
            } else if (!PersistenceManagerFactory.get().retrieveIsGDPRCountry(context) || PersistenceManagerFactory.get().retrieveGDPRComplianceAlreadyRun(context)) {
                try {
                    if (LocationResult.hasResult(intent)) {
                        CuebiqSDKImpl.log("CoverageReceiver -> New location received...");
                        for (Location location : LocationResult.extractResult(intent).getLocations()) {
                            CuebiqSDKImpl.log("CoverageReceiver -> Location: " + location.toString());
                            collect(context, location, false, true, TRIGGER_TYPE.APP_OPENED);
                        }
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String action = intent.getAction();
                if ("android.location.PROVIDERS_CHANGED".equals(action)) {
                    if (CoverageManager.get().getCoverageStatus(context) != CoverageStatus.CHECKED) {
                        return;
                    }
                    if (!Utils.isOptedOut(context) || !PersistenceManagerFactory.get().isSDKCollectionEnabled(context)) {
                        CuebiqSDKImpl.log("QUICK SETTINGS LOCATION CHANGED!");
                        CuebiqSDKImpl.activateImmediately(context.getApplicationContext());
                    }
                } else if ("android.intent.action.BOOT_COMPLETED".equals(action)) {
                    CuebiqSDKImpl.log("Coverage -> BOOT_COMPLETED event received!");
                    PersistenceManagerFactory.get().saveTempInformation(context, new InformationList());
                    PersistenceManagerFactory.get().saveSleepDwellCounter(context, 0);
                    PersistenceManagerFactory.get().saveSleepHighCounter(context, 0);
                    PersistenceManagerFactory.get().saveSchedulerStatus(context, AlgorithmSchedulerStatus.NONE);
                    if (!PersistenceManagerFactory.get().isSDKCollectionEnabled(context) || PersistenceManagerFactory.get().isUserCOPAProtected(context)) {
                        CuebiqSDKImpl.log("CoverageReceiver -> SDK Collection disabled");
                    }
                }
            } else {
                CuebiqSDKImpl.log("CuebiqSDK -> GDPR not compliant, stop tracking.");
            }
        }
    }
}
