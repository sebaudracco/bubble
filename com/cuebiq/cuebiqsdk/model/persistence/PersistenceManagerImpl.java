package com.cuebiq.cuebiqsdk.model.persistence;

import android.content.Context;
import android.content.SharedPreferences;
import com.cuebiq.cuebiqsdk.CuebiqSDKImpl;
import com.cuebiq.cuebiqsdk.model.CoverageManager.CoverageStatus;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.cuebiq.cuebiqsdk.model.manager.AlgorithmScheduler.AlgorithmSchedulerStatus;
import com.cuebiq.cuebiqsdk.model.wrapper.TrackRequest;
import com.cuebiq.cuebiqsdk.utils.InformationList;
import com.cuebiq.cuebiqsdk.utils.ObscuredSharedPreferences;

final class PersistenceManagerImpl implements PersistenceManager {
    private static final String BEA_ANALYTICS_APP_OPEN_COUNTER = "bea_analytics_app_open_counter";
    private static final String BEA_ANALYTICS_COVERAGE_CHECKER_COUNTER = "bea_analytics_coverage_checker_counter";
    private static final String BEA_APP_KEY = "beaudience_appkey";
    private static final String BEA_CONFIGURATION = "beaudience_configuration";
    private static final String BEA_COVERAGE_STATUS = "bea_coverage_status";
    private static final String BEA_CURRENT_ACQUISITION_RATE = "bea_current_acquisition_rate";
    private static final String BEA_CUSTOM_PUBLISHER_ID = "bea_custom_publisher_id";
    private static final String BEA_IS_GAID_DISABLED = "beaudience_is_gai_disabled";
    private static final String BEA_LOCATION_ON = "bea_location_on";
    private static final String CUEBIQ_PREFERENCE = "cuebiq_preference";
    private static final String Q_COPA_PROTECTED = "q_copa_protected";
    private static final String Q_COUNTRY = "q_country";
    private static final String Q_GDPR_COMPLIANCE_FLOW_ALREADY_RUN = "q_gdpr_compliance_flow_already_run";
    private static final String Q_GDPR_CONSENT_SENT_SUCCESFULLY = "q_gdpr_consent_sent_successfully";
    private static final String Q_GOOGLE_ADV_ID = "q_google_adv_id";
    private static final String Q_IS_GDPR_COUNTRY = "q_is_gdpr_country";
    private static final String Q_NEXT_COVERAGE_CALL_MILLS = "q_next_coverage_call_mills";
    private static final String Q_NEXT_FLUSH_COUNTER = "q_next_flush_counter";
    private static final String Q_SCHEDULER_INDEX = "q_scheduler_index";
    private static final String Q_SCHEDULER_SLEEP_DWELL_COUNTER = "q_scheduler_sleep_dwell_counter";
    private static final String Q_SCHEDULER_SLEEP_HIGH_COUNTER = "q_scheduler_sleep_high_counter";
    private static final String Q_SCHEDULER_STATUS = "q_scheduler_status";
    private static final String Q_SDK_COLLECTION = "q_sdk_collection";
    private static final String Q_TEMP_INFORMATION = "q_temp_information";
    private static final String Q_USE_GDPR_FLOW_BY_CUEBIQ = "q_use_gdpr_flow_by_cuebiq";
    private static final String REQUESTS_KEY = "beaudience_requests";
    private static final String REQUEST_CACHED = "beaudience_cache";

    PersistenceManagerImpl() {
    }

    private SharedPreferences getCuebiqPreferences(Context context) {
        return context.getSharedPreferences(CUEBIQ_PREFERENCE, 0);
    }

    public final int getAppOpenCounter(Context context) {
        return getCuebiqPreferences(context).getInt(BEA_ANALYTICS_APP_OPEN_COUNTER, 0);
    }

    public final int getCoverageCounter(Context context) {
        return getCuebiqPreferences(context).getInt(BEA_ANALYTICS_COVERAGE_CHECKER_COUNTER, 0);
    }

    public final CoverageStatus getCoverageStatus(Context context) {
        return CoverageStatus.valueOf(getCuebiqPreferences(context).getString(BEA_COVERAGE_STATUS, CoverageStatus.UNCHECKED.name()));
    }

    public final long getCurrentAcquisitionMills(Context context) {
        return getCuebiqPreferences(context).getLong(BEA_CURRENT_ACQUISITION_RATE, (CuebiqSDKImpl.getBeAudienceConfiguration(context).getAminar() * 60) * 1000);
    }

    public final String getCustomPublisherID(Context context) {
        String str = null;
        try {
            str = getCuebiqPreferences(context).getString(BEA_CUSTOM_PUBLISHER_ID, null);
        } catch (Throwable th) {
        }
        return str;
    }

    public final boolean getLocationON(Context context) {
        return getCuebiqPreferences(context).getBoolean(BEA_LOCATION_ON, true);
    }

    public final int getNextFlushCounter(Context context) {
        return getCuebiqPreferences(context).getInt(Q_NEXT_FLUSH_COUNTER, 0);
    }

    public final void increaseAppOpenCounter(Context context) {
        getCuebiqPreferences(context).edit().putInt(BEA_ANALYTICS_APP_OPEN_COUNTER, getAppOpenCounter(context) + 1).apply();
    }

    public final void increaseCoverageCounter(Context context) {
        getCuebiqPreferences(context).edit().putInt(BEA_ANALYTICS_COVERAGE_CHECKER_COUNTER, getCoverageCounter(context) + 1).apply();
    }

    public final boolean isGAIDDisabled(Context context) {
        return getCuebiqPreferences(context).getBoolean(BEA_IS_GAID_DISABLED, false);
    }

    public final boolean isSDKCollectionEnabled(Context context) {
        return getCuebiqPreferences(context).getBoolean(Q_SDK_COLLECTION, true);
    }

    public final boolean isUserCOPAProtected(Context context) {
        return getCuebiqPreferences(context).getBoolean(Q_COPA_PROTECTED, true);
    }

    public final void persistRequest(Context context, TrackRequest trackRequest) {
        ObscuredSharedPreferences obscuredSharedPreferences = new ObscuredSharedPreferences(context.getSharedPreferences(REQUEST_CACHED, 0));
        if (trackRequest == null) {
            obscuredSharedPreferences.edit().putString(REQUESTS_KEY, "").apply();
            return;
        }
        try {
            obscuredSharedPreferences.edit().putString(REQUESTS_KEY, trackRequest.toString()).apply();
            CuebiqSDKImpl.log("Collector -> Request cached: " + trackRequest.toString());
        } catch (Throwable th) {
            obscuredSharedPreferences.edit().putString(REQUESTS_KEY, "").apply();
            CuebiqSDKImpl.log("Error persistRequest: " + th.getMessage() + " " + trackRequest.toString());
        }
    }

    public final void resetAppOpenCounter(Context context) {
        getCuebiqPreferences(context).edit().putInt(BEA_ANALYTICS_APP_OPEN_COUNTER, 0).apply();
    }

    public final void resetCoverageCounter(Context context) {
        getCuebiqPreferences(context).edit().putInt(BEA_ANALYTICS_COVERAGE_CHECKER_COUNTER, 0).apply();
    }

    public final String retrieveAppKey(Context context) {
        return getCuebiqPreferences(context).getString(BEA_APP_KEY, "aWildcard");
    }

    public final Settings retrieveBeAudienceConfiguration(Context context) {
        SharedPreferences cuebiqPreferences = getCuebiqPreferences(context);
        Settings settings = new Settings();
        String string = cuebiqPreferences.getString(BEA_CONFIGURATION, "");
        if (!"".equals(string)) {
            return (Settings) CuebiqSDKImpl.GSON.fromJson(string, Settings.class);
        }
        cuebiqPreferences.edit().putString(BEA_CONFIGURATION, settings.toString()).apply();
        return settings;
    }

    public final String retrieveCountry(Context context) {
        return getCuebiqPreferences(context).getString(Q_COUNTRY, "");
    }

    public final boolean retrieveGDPRComplianceAlreadyRun(Context context) {
        return getCuebiqPreferences(context).getBoolean(Q_GDPR_COMPLIANCE_FLOW_ALREADY_RUN, false);
    }

    public final boolean retrieveGDPRConsentSentSuccessfully(Context context) {
        return getCuebiqPreferences(context).getBoolean(Q_GDPR_CONSENT_SENT_SUCCESFULLY, false);
    }

    public final String retrieveGoogleAdvID(Context context) {
        return getCuebiqPreferences(context).getString(Q_GOOGLE_ADV_ID, "");
    }

    public final boolean retrieveIsGDPRCountry(Context context) {
        return getCuebiqPreferences(context).getBoolean(Q_IS_GDPR_COUNTRY, false);
    }

    public final long retrieveNextCoverageCallMills(Context context) {
        return getCuebiqPreferences(context).getLong(Q_NEXT_COVERAGE_CALL_MILLS, 0);
    }

    public final TrackRequest retrieveRequest(Context context) {
        ObscuredSharedPreferences obscuredSharedPreferences = new ObscuredSharedPreferences(context.getSharedPreferences(REQUEST_CACHED, 0));
        try {
            return (TrackRequest) CuebiqSDKImpl.GSON.fromJson(obscuredSharedPreferences.getString(REQUESTS_KEY, ""), TrackRequest.class);
        } catch (Throwable th) {
            CuebiqSDKImpl.log("Error retrieveRequest: " + th.getMessage() + " " + obscuredSharedPreferences.getString(REQUESTS_KEY, "N/A"));
            obscuredSharedPreferences.edit().putString(REQUESTS_KEY, "").apply();
            return null;
        }
    }

    public final AlgorithmSchedulerStatus retrieveSchedulerStatus(Context context) {
        return AlgorithmSchedulerStatus.valueOf(getCuebiqPreferences(context).getString(Q_SCHEDULER_STATUS, AlgorithmSchedulerStatus.NONE.name()));
    }

    public final int retrieveSleepDwellCounter(Context context) {
        return getCuebiqPreferences(context).getInt(Q_SCHEDULER_SLEEP_DWELL_COUNTER, 0);
    }

    public final int retrieveSleepHighCounter(Context context) {
        return getCuebiqPreferences(context).getInt(Q_SCHEDULER_SLEEP_HIGH_COUNTER, 0);
    }

    public final InformationList retrieveTempInformationList(Context context) {
        String string = getCuebiqPreferences(context).getString(Q_TEMP_INFORMATION, "");
        return "".equals(string) ? new InformationList() : (InformationList) CuebiqSDKImpl.GSON.fromJson(string, InformationList.class);
    }

    public final boolean retrieveUseGDPRFlowByCuebiq(Context context) {
        return getCuebiqPreferences(context).getBoolean(Q_USE_GDPR_FLOW_BY_CUEBIQ, false);
    }

    public final void saveAppKey(Context context, String str) {
        getCuebiqPreferences(context).edit().putString(BEA_APP_KEY, str).apply();
    }

    public final void saveBeAudienceConfiguration(Context context, Settings settings) {
        getCuebiqPreferences(context).edit().putString(BEA_CONFIGURATION, settings.toString()).apply();
    }

    public final void saveCountry(Context context, String str) {
        getCuebiqPreferences(context).edit().putString(Q_COUNTRY, str).apply();
    }

    public final void saveCustomPublisherID(Context context, String str) {
        getCuebiqPreferences(context).edit().putString(BEA_CUSTOM_PUBLISHER_ID, str).apply();
    }

    public final void saveGDPRComplianceAlreadyRun(Context context) {
        getCuebiqPreferences(context).edit().putBoolean(Q_GDPR_COMPLIANCE_FLOW_ALREADY_RUN, true).apply();
    }

    public final void saveGDPRConsentSentSuccesfully(Context context, boolean z) {
        getCuebiqPreferences(context).edit().putBoolean(Q_GDPR_CONSENT_SENT_SUCCESFULLY, z).apply();
    }

    public final void saveGoogleAdvID(Context context, String str) {
        getCuebiqPreferences(context).edit().putString(Q_GOOGLE_ADV_ID, str).apply();
    }

    public final void saveIsGDPRCountry(Context context, boolean z) {
        getCuebiqPreferences(context).edit().putBoolean(Q_IS_GDPR_COUNTRY, z).apply();
    }

    public final void saveNextCoverageCallsMills(Context context, long j) {
        getCuebiqPreferences(context).edit().putLong(Q_NEXT_COVERAGE_CALL_MILLS, j).apply();
    }

    public final void saveSchedulerStatus(Context context, AlgorithmSchedulerStatus algorithmSchedulerStatus) {
        getCuebiqPreferences(context).edit().putString(Q_SCHEDULER_STATUS, algorithmSchedulerStatus.name()).apply();
    }

    public final void saveSleepDwellCounter(Context context, int i) {
        getCuebiqPreferences(context).edit().putInt(Q_SCHEDULER_SLEEP_DWELL_COUNTER, i).apply();
    }

    public final void saveSleepHighCounter(Context context, int i) {
        getCuebiqPreferences(context).edit().putInt(Q_SCHEDULER_SLEEP_HIGH_COUNTER, i).apply();
    }

    public final void saveTempInformation(Context context, InformationList informationList) {
        getCuebiqPreferences(context).edit().putString(Q_TEMP_INFORMATION, CuebiqSDKImpl.GSON.toJson((Object) informationList)).apply();
    }

    public final void saveUseGDPRFlowByCuebiq(Context context, boolean z) {
        getCuebiqPreferences(context).edit().putBoolean(Q_USE_GDPR_FLOW_BY_CUEBIQ, z).apply();
    }

    public final void setCoverageStatus(Context context, CoverageStatus coverageStatus) {
        getCuebiqPreferences(context).edit().putString(BEA_COVERAGE_STATUS, coverageStatus.name()).apply();
    }

    public final void setCurrentAcquisitionMills(Context context, long j) {
        getCuebiqPreferences(context).edit().putLong(BEA_CURRENT_ACQUISITION_RATE, j).apply();
    }

    public final void setIsGAIDDisabled(Context context, boolean z) {
        getCuebiqPreferences(context).edit().putBoolean(BEA_IS_GAID_DISABLED, z).apply();
    }

    public final void setLocationON(Context context, boolean z) {
        getCuebiqPreferences(context).edit().putBoolean(BEA_LOCATION_ON, z).apply();
    }

    public final void setNextFlushingContent(Context context, int i) {
        getCuebiqPreferences(context).edit().putInt(Q_NEXT_FLUSH_COUNTER, i).apply();
    }

    public final void setSDKCollectionEnabled(Context context, boolean z) {
        getCuebiqPreferences(context).edit().putBoolean(Q_SDK_COLLECTION, z).apply();
    }

    public final void setUserCOPAProtected(Context context, boolean z) {
        getCuebiqPreferences(context).edit().putBoolean(Q_COPA_PROTECTED, z).apply();
    }
}
