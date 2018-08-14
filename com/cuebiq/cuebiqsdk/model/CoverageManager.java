package com.cuebiq.cuebiqsdk.model;

import android.content.Context;
import com.cuebiq.cuebiqsdk.CuebiqSDKImpl;
import com.cuebiq.cuebiqsdk.api.CoverageCallback;
import com.cuebiq.cuebiqsdk.api.CoverageRequest;
import com.cuebiq.cuebiqsdk.injection.Injection;
import com.cuebiq.cuebiqsdk.model.persistence.PersistenceManagerFactory;
import com.cuebiq.cuebiqsdk.model.wrapper.CoverageSettings;
import com.cuebiq.cuebiqsdk.model.wrapper.GeoLocationStats;
import com.cuebiq.cuebiqsdk.task.GAIDRunnable;
import com.cuebiq.cuebiqsdk.task.GAIDRunnable.OnGAIDListener;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

public class CoverageManager {
    private static CoverageManager instance;

    public interface CoverageListener {
        void onCountryCovered(CoverageSettings coverageSettings);

        void onCountryNotCovered(int i);

        void onError(String str);
    }

    public enum CoverageStatus {
        UNCHECKED,
        PENDING,
        CHECKED
    }

    private void coverage(Context context, GeoLocationStats geoLocationStats, CoverageListener coverageListener) {
        Injection.provideNetworkLayer().callAsync(new CoverageRequest(PersistenceManagerFactory.get().retrieveAppKey(context), context.getPackageName(), extractQueryParams(geoLocationStats)), new CoverageCallback(coverageListener));
    }

    public static CoverageManager get() {
        if (instance == null) {
            instance = new CoverageManager();
        }
        return instance;
    }

    private void getGAIDAndCallCoverage(final Context context, final CoverageListener coverageListener) {
        Executors.newSingleThreadExecutor().submit(new GAIDRunnable(context.getApplicationContext(), new OnGAIDListener() {
            public void onGoogleAdvID(String str, boolean z) {
                GeoLocationStats build = GeoLocationStats.build(context, Boolean.valueOf(true));
                build.setGoogleAid(str);
                build.setIsGAIDOptout(Boolean.valueOf(z));
                CoverageManager.this.coverage(context, build, coverageListener);
            }
        }));
    }

    public void checkCoverage(Context context, boolean z, CoverageListener coverageListener) {
        CoverageStatus coverageStatus = getCoverageStatus(context);
        CuebiqSDKImpl.log("CuebiqSDK -> Coverage Status: " + coverageStatus);
        if (z) {
            getGAIDAndCallCoverage(context, coverageListener);
            return;
        }
        switch (coverageStatus) {
            case UNCHECKED:
                getGAIDAndCallCoverage(context, coverageListener);
                return;
            case PENDING:
                if (System.currentTimeMillis() > PersistenceManagerFactory.get().retrieveNextCoverageCallMills(context)) {
                    setCoverageStatus(context, CoverageStatus.UNCHECKED);
                    getGAIDAndCallCoverage(context, coverageListener);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public Map<String, String> extractQueryParams(GeoLocationStats geoLocationStats) {
        int i = 1;
        if (geoLocationStats == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        StringBuilder append = new StringBuilder().append(String.valueOf(geoLocationStats.isAfterCoverage().booleanValue() ? 1 : 0)).append(geoLocationStats.isGAIDOptout().booleanValue() ? 1 : 0).append(geoLocationStats.isLocationON().booleanValue() ? 1 : 0).append(geoLocationStats.isWifiEnabled().booleanValue() ? 1 : 0);
        if (!geoLocationStats.isWifiAlwaysScanning().booleanValue()) {
            i = 0;
        }
        hashMap.put("f", append.append(i).toString());
        hashMap.put("id", geoLocationStats.getGoogleAid());
        hashMap.put("os", "a");
        hashMap.put("mcc", geoLocationStats.getMcc());
        hashMap.put("mnc", geoLocationStats.getMnc());
        return hashMap;
    }

    public CoverageStatus getCoverageStatus(Context context) {
        return PersistenceManagerFactory.get().getCoverageStatus(context);
    }

    public boolean isCoverageOpened(Context context) {
        return getCoverageStatus(context) == CoverageStatus.CHECKED;
    }

    public void scheduleCheckCoverage(Context context, long j) {
        get().setCoverageStatus(context, CoverageStatus.PENDING);
        CuebiqSDKImpl.disableTracking(context);
        PersistenceManagerFactory.get().saveNextCoverageCallsMills(context, System.currentTimeMillis() + j);
    }

    public void setCoverageStatus(Context context, CoverageStatus coverageStatus) {
        PersistenceManagerFactory.get().setCoverageStatus(context, coverageStatus);
    }

    public void updateCoverageSettings(Context context, CoverageSettings coverageSettings) {
        PersistenceManagerFactory.get().saveCountry(context, coverageSettings.getCountry());
        PersistenceManagerFactory.get().saveIsGDPRCountry(context, coverageSettings.isGDPRCountry());
    }
}
