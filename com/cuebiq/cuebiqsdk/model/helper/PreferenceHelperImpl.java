package com.cuebiq.cuebiqsdk.model.helper;

import android.content.Context;
import android.content.SharedPreferences;
import com.cuebiq.cuebiqsdk.model.CoverageManager.CoverageStatus;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.cuebiq.cuebiqsdk.model.manager.AlgorithmScheduler.AlgorithmSchedulerStatus;
import com.cuebiq.cuebiqsdk.model.wrapper.TrackRequest;
import com.cuebiq.cuebiqsdk.utils.InformationList;
import com.cuebiq.cuebiqsdk.utils.ObscuredSharedPreferences;

public class PreferenceHelperImpl implements PreferenceHelper {
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
    private final ObscuredSharedPreferences obscuredSharedPreferences;
    private final SharedPreferences sharedPreferences;

    public PreferenceHelperImpl(Context context) {
        this.sharedPreferences = context.getSharedPreferences(CUEBIQ_PREFERENCE, 0);
        this.obscuredSharedPreferences = new ObscuredSharedPreferences(context.getSharedPreferences(REQUEST_CACHED, 0));
    }

    public void increaseAppOpenCounter() {
    }

    public void increaseCoverageCounter() {
    }

    public boolean isGAIDDisabled() {
        return false;
    }

    public boolean isSDKCollectionEnabled() {
        return this.sharedPreferences.getBoolean(Q_SDK_COLLECTION, true);
    }

    public boolean isUserCOPAProtected() {
        return false;
    }

    public String retrieveAppKey() {
        return this.sharedPreferences.getString(BEA_APP_KEY, "");
    }

    public int retrieveAppOpenCounter() {
        return 0;
    }

    public String retrieveCountry() {
        return null;
    }

    public int retrieveCoverageCounter() {
        return 0;
    }

    public CoverageStatus retrieveCoverageStatus() {
        return null;
    }

    public long retrieveCurrentAcquisitionMills() {
        return 0;
    }

    public String retrieveCustomPublisherID() {
        return null;
    }

    public boolean retrieveGDPRComplianceAlreadyRun() {
        return false;
    }

    public boolean retrieveGDPRConsentSentSuccessfully() {
        return false;
    }

    public String retrieveGoogleAdvID() {
        return null;
    }

    public boolean retrieveIsGDPRCountry() {
        return false;
    }

    public boolean retrieveLocationON() {
        return false;
    }

    public long retrieveNextCoverageCallMills() {
        return 0;
    }

    public int retrieveNextFlushCounter() {
        return 0;
    }

    public TrackRequest retrievePayload() {
        return null;
    }

    public AlgorithmSchedulerStatus retrieveSchedulerStatus() {
        return null;
    }

    public Settings retrieveSettings() {
        return Settings.fromJSON(this.sharedPreferences.getString(BEA_CONFIGURATION, ""));
    }

    public int retrieveSleepDwellCounter() {
        return 0;
    }

    public int retrieveSleepHighCounter() {
        return 0;
    }

    public InformationList retrieveTempInformationList() {
        return null;
    }

    public boolean retrieveUseGDPRFlowByCuebiq() {
        return false;
    }

    public void saveAppKey(String str) {
        this.sharedPreferences.edit().putString(BEA_APP_KEY, str).apply();
    }

    public void saveAppOpenCounter() {
    }

    public void saveCountry(String str) {
    }

    public void saveCoverageCounter() {
    }

    public void saveCoverageStatus(CoverageStatus coverageStatus) {
    }

    public void saveCurrentAcquisitionMills(long j) {
    }

    public void saveCustomPublisherID(String str) {
    }

    public void saveGDPRComplianceAlreadyRun() {
    }

    public void saveGDPRConsentSentSuccesfully(boolean z) {
    }

    public void saveGoogleAdvID(String str) {
    }

    public void saveIsGAIDDisabled(boolean z) {
    }

    public void saveIsGDPRCountry(boolean z) {
    }

    public void saveLocationON(boolean z) {
    }

    public void saveNextCoverageCallsMills(long j) {
    }

    public void saveNextFlushingContent(int i) {
    }

    public void savePayload(TrackRequest trackRequest) {
    }

    public void saveSDKCollectionEnabled(boolean z) {
    }

    public void saveSchedulerStatus(AlgorithmSchedulerStatus algorithmSchedulerStatus) {
    }

    public void saveSettings(Settings settings) {
    }

    public void saveSleepDwellCounter(int i) {
    }

    public void saveSleepHighCounter(int i) {
    }

    public void saveTempInformation(InformationList informationList) {
    }

    public void saveUseGDPRFlowByCuebiq(boolean z) {
    }

    public void saveUserCOPAProtected(boolean z) {
    }
}
