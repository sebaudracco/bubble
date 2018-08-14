package com.cuebiq.cuebiqsdk.model;

import com.cuebiq.cuebiqsdk.model.CoverageManager.CoverageStatus;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.cuebiq.cuebiqsdk.model.helper.PreferenceHelper;
import com.cuebiq.cuebiqsdk.model.manager.AlgorithmScheduler.AlgorithmSchedulerStatus;
import com.cuebiq.cuebiqsdk.model.wrapper.TrackRequest;
import com.cuebiq.cuebiqsdk.utils.InformationList;

public class DataRepository implements PreferenceHelper {
    private static DataRepository mInstance;
    private static final Object mLock = new Object();
    private PreferenceHelper mPreferenceHelper;

    public static DataRepository get() {
        DataRepository dataRepository;
        synchronized (mLock) {
            if (mInstance == null) {
                mInstance = new DataRepository();
            }
            dataRepository = mInstance;
        }
        return dataRepository;
    }

    public void increaseAppOpenCounter() {
        this.mPreferenceHelper.increaseAppOpenCounter();
    }

    public void increaseCoverageCounter() {
        this.mPreferenceHelper.increaseCoverageCounter();
    }

    public void inject(PreferenceHelper preferenceHelper) {
        this.mPreferenceHelper = preferenceHelper;
    }

    public boolean isGAIDDisabled() {
        return this.mPreferenceHelper.isGAIDDisabled();
    }

    public boolean isSDKCollectionEnabled() {
        return this.mPreferenceHelper.isSDKCollectionEnabled();
    }

    public boolean isUserCOPAProtected() {
        return this.mPreferenceHelper.isUserCOPAProtected();
    }

    public String retrieveAppKey() {
        return this.mPreferenceHelper.retrieveAppKey();
    }

    public int retrieveAppOpenCounter() {
        return this.mPreferenceHelper.retrieveAppOpenCounter();
    }

    public String retrieveCountry() {
        return this.mPreferenceHelper.retrieveCountry();
    }

    public int retrieveCoverageCounter() {
        return this.mPreferenceHelper.retrieveCoverageCounter();
    }

    public CoverageStatus retrieveCoverageStatus() {
        return this.mPreferenceHelper.retrieveCoverageStatus();
    }

    public long retrieveCurrentAcquisitionMills() {
        return this.mPreferenceHelper.retrieveCurrentAcquisitionMills();
    }

    public String retrieveCustomPublisherID() {
        return this.mPreferenceHelper.retrieveCustomPublisherID();
    }

    public boolean retrieveGDPRComplianceAlreadyRun() {
        return this.mPreferenceHelper.retrieveGDPRComplianceAlreadyRun();
    }

    public boolean retrieveGDPRConsentSentSuccessfully() {
        return this.mPreferenceHelper.retrieveGDPRConsentSentSuccessfully();
    }

    public String retrieveGoogleAdvID() {
        return this.mPreferenceHelper.retrieveGoogleAdvID();
    }

    public boolean retrieveIsGDPRCountry() {
        return this.mPreferenceHelper.retrieveIsGDPRCountry();
    }

    public boolean retrieveLocationON() {
        return this.mPreferenceHelper.retrieveLocationON();
    }

    public long retrieveNextCoverageCallMills() {
        return this.mPreferenceHelper.retrieveNextCoverageCallMills();
    }

    public int retrieveNextFlushCounter() {
        return this.mPreferenceHelper.retrieveNextFlushCounter();
    }

    public TrackRequest retrievePayload() {
        return this.mPreferenceHelper.retrievePayload();
    }

    public AlgorithmSchedulerStatus retrieveSchedulerStatus() {
        return this.mPreferenceHelper.retrieveSchedulerStatus();
    }

    public Settings retrieveSettings() {
        return this.mPreferenceHelper.retrieveSettings();
    }

    public int retrieveSleepDwellCounter() {
        return this.mPreferenceHelper.retrieveSleepDwellCounter();
    }

    public int retrieveSleepHighCounter() {
        return this.mPreferenceHelper.retrieveSleepHighCounter();
    }

    public InformationList retrieveTempInformationList() {
        return this.mPreferenceHelper.retrieveTempInformationList();
    }

    public boolean retrieveUseGDPRFlowByCuebiq() {
        return this.mPreferenceHelper.retrieveUseGDPRFlowByCuebiq();
    }

    public void saveAppKey(String str) {
        this.mPreferenceHelper.saveAppKey(str);
    }

    public void saveAppOpenCounter() {
        this.mPreferenceHelper.saveAppOpenCounter();
    }

    public void saveCountry(String str) {
        this.mPreferenceHelper.saveCountry(str);
    }

    public void saveCoverageCounter() {
        this.mPreferenceHelper.saveCoverageCounter();
    }

    public void saveCoverageStatus(CoverageStatus coverageStatus) {
        this.mPreferenceHelper.saveCoverageStatus(coverageStatus);
    }

    public void saveCurrentAcquisitionMills(long j) {
        this.mPreferenceHelper.saveCurrentAcquisitionMills(j);
    }

    public void saveCustomPublisherID(String str) {
        this.mPreferenceHelper.saveCustomPublisherID(str);
    }

    public void saveGDPRComplianceAlreadyRun() {
        this.mPreferenceHelper.saveGDPRComplianceAlreadyRun();
    }

    public void saveGDPRConsentSentSuccesfully(boolean z) {
        this.mPreferenceHelper.saveGDPRConsentSentSuccesfully(z);
    }

    public void saveGoogleAdvID(String str) {
        this.mPreferenceHelper.saveGoogleAdvID(str);
    }

    public void saveIsGAIDDisabled(boolean z) {
        this.mPreferenceHelper.saveIsGAIDDisabled(z);
    }

    public void saveIsGDPRCountry(boolean z) {
        this.mPreferenceHelper.saveIsGDPRCountry(z);
    }

    public void saveLocationON(boolean z) {
        this.mPreferenceHelper.saveLocationON(z);
    }

    public void saveNextCoverageCallsMills(long j) {
        this.mPreferenceHelper.saveNextCoverageCallsMills(j);
    }

    public void saveNextFlushingContent(int i) {
        this.mPreferenceHelper.saveNextFlushingContent(i);
    }

    public void savePayload(TrackRequest trackRequest) {
        this.mPreferenceHelper.savePayload(trackRequest);
    }

    public void saveSDKCollectionEnabled(boolean z) {
        this.mPreferenceHelper.saveSDKCollectionEnabled(z);
    }

    public void saveSchedulerStatus(AlgorithmSchedulerStatus algorithmSchedulerStatus) {
        this.mPreferenceHelper.saveSchedulerStatus(algorithmSchedulerStatus);
    }

    public void saveSettings(Settings settings) {
        this.mPreferenceHelper.saveSettings(settings);
    }

    public void saveSleepDwellCounter(int i) {
        this.mPreferenceHelper.saveSleepDwellCounter(i);
    }

    public void saveSleepHighCounter(int i) {
        this.mPreferenceHelper.saveSleepHighCounter(i);
    }

    public void saveTempInformation(InformationList informationList) {
        this.mPreferenceHelper.saveTempInformation(informationList);
    }

    public void saveUseGDPRFlowByCuebiq(boolean z) {
        this.mPreferenceHelper.saveUseGDPRFlowByCuebiq(z);
    }

    public void saveUserCOPAProtected(boolean z) {
        this.mPreferenceHelper.saveUserCOPAProtected(z);
    }
}
