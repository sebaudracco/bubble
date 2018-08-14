package com.cuebiq.cuebiqsdk.model.helper;

import com.cuebiq.cuebiqsdk.model.CoverageManager.CoverageStatus;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.cuebiq.cuebiqsdk.model.manager.AlgorithmScheduler.AlgorithmSchedulerStatus;
import com.cuebiq.cuebiqsdk.model.wrapper.TrackRequest;
import com.cuebiq.cuebiqsdk.utils.InformationList;

public interface PreferenceHelper {
    void increaseAppOpenCounter();

    void increaseCoverageCounter();

    boolean isGAIDDisabled();

    boolean isSDKCollectionEnabled();

    boolean isUserCOPAProtected();

    String retrieveAppKey();

    int retrieveAppOpenCounter();

    String retrieveCountry();

    int retrieveCoverageCounter();

    CoverageStatus retrieveCoverageStatus();

    long retrieveCurrentAcquisitionMills();

    String retrieveCustomPublisherID();

    boolean retrieveGDPRComplianceAlreadyRun();

    boolean retrieveGDPRConsentSentSuccessfully();

    String retrieveGoogleAdvID();

    boolean retrieveIsGDPRCountry();

    boolean retrieveLocationON();

    long retrieveNextCoverageCallMills();

    int retrieveNextFlushCounter();

    TrackRequest retrievePayload();

    AlgorithmSchedulerStatus retrieveSchedulerStatus();

    Settings retrieveSettings();

    int retrieveSleepDwellCounter();

    int retrieveSleepHighCounter();

    InformationList retrieveTempInformationList();

    boolean retrieveUseGDPRFlowByCuebiq();

    void saveAppKey(String str);

    void saveAppOpenCounter();

    void saveCountry(String str);

    void saveCoverageCounter();

    void saveCoverageStatus(CoverageStatus coverageStatus);

    void saveCurrentAcquisitionMills(long j);

    void saveCustomPublisherID(String str);

    void saveGDPRComplianceAlreadyRun();

    void saveGDPRConsentSentSuccesfully(boolean z);

    void saveGoogleAdvID(String str);

    void saveIsGAIDDisabled(boolean z);

    void saveIsGDPRCountry(boolean z);

    void saveLocationON(boolean z);

    void saveNextCoverageCallsMills(long j);

    void saveNextFlushingContent(int i);

    void savePayload(TrackRequest trackRequest);

    void saveSDKCollectionEnabled(boolean z);

    void saveSchedulerStatus(AlgorithmSchedulerStatus algorithmSchedulerStatus);

    void saveSettings(Settings settings);

    void saveSleepDwellCounter(int i);

    void saveSleepHighCounter(int i);

    void saveTempInformation(InformationList informationList);

    void saveUseGDPRFlowByCuebiq(boolean z);

    void saveUserCOPAProtected(boolean z);
}
