package com.cuebiq.cuebiqsdk.model;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;
import com.cuebiq.cuebiqsdk.CuebiqSDK;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.cuebiq.cuebiqsdk.model.persistence.PersistenceManagerFactory;
import com.cuebiq.cuebiqsdk.task.GAIDRunnable;
import java.util.concurrent.Executors;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class CuebiqSDKProvider extends ContentProvider {
    public static final String CUEBIQ_APPKEY = "com.cuebiq.sdk.AppKey";

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public boolean onCreate() {
        boolean z = false;
        Log.i("CuebiqSDKProvider", "Starting CuebiqSDK...");
        Executors.newSingleThreadExecutor().submit(new GAIDRunnable(getContext(), null));
        boolean isSDKCollectionEnabled = PersistenceManagerFactory.get().isSDKCollectionEnabled(getContext());
        try {
            Boolean.parseBoolean(getContext().getString(getContext().getResources().getIdentifier("cuebiq_sdk_collection", SchemaSymbols.ATTVAL_STRING, getContext().getPackageName())));
            z = PersistenceManagerFactory.get().isUserCOPAProtected(getContext());
        } catch (Exception e) {
        }
        if (!isSDKCollectionEnabled || r0) {
            Log.i("CuebiqSDKProvider", "SDK has explicit opt-out, stop tracking.");
        } else {
            try {
                String string = getContext().getPackageManager().getApplicationInfo(getContext().getPackageName(), 128).metaData.getString(CUEBIQ_APPKEY);
                if (string == null || "".equals(string)) {
                    Log.i("CuebiqSDKProvider", "CuebiqSDK AppKey must not be null. Did you forget to add it in your AndroidManifest as metadata?");
                    string = "aWildcard";
                }
                if (PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean("q_temp_fist_launch", true)) {
                    PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putBoolean("q_temp_fist_launch", false).apply();
                    PersistenceManagerFactory.get().saveBeAudienceConfiguration(getContext(), new Settings());
                }
                int identifier = getContext().getResources().getIdentifier("cuebiq_use_gdpr_flow_by_cuebiq", "bool", getContext().getPackageName());
                if (identifier != 0) {
                    PersistenceManagerFactory.get().saveUseGDPRFlowByCuebiq(getContext(), getContext().getResources().getBoolean(identifier));
                }
                CuebiqSDK.initialize(getContext(), string);
            } catch (NameNotFoundException e2) {
                Log.i("CuebiqSDKProvider", "...Failed to initialize CuebiqSDK: " + e2.getMessage());
            } catch (NullPointerException e3) {
                Log.i("CuebiqSDKProvider", "...Failed to initialize CuebiqSDK: " + e3.getMessage());
            }
        }
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
