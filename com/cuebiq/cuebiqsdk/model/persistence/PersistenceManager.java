package com.cuebiq.cuebiqsdk.model.persistence;

import android.content.Context;
import com.cuebiq.cuebiqsdk.model.CoverageManager.CoverageStatus;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.cuebiq.cuebiqsdk.model.manager.AlgorithmScheduler.AlgorithmSchedulerStatus;
import com.cuebiq.cuebiqsdk.model.wrapper.TrackRequest;
import com.cuebiq.cuebiqsdk.utils.InformationList;

public interface PersistenceManager {
    int getAppOpenCounter(Context context);

    int getCoverageCounter(Context context);

    CoverageStatus getCoverageStatus(Context context);

    long getCurrentAcquisitionMills(Context context);

    String getCustomPublisherID(Context context);

    boolean getLocationON(Context context);

    int getNextFlushCounter(Context context);

    void increaseAppOpenCounter(Context context);

    void increaseCoverageCounter(Context context);

    boolean isGAIDDisabled(Context context);

    boolean isSDKCollectionEnabled(Context context);

    boolean isUserCOPAProtected(Context context);

    void persistRequest(Context context, TrackRequest trackRequest);

    void resetAppOpenCounter(Context context);

    void resetCoverageCounter(Context context);

    String retrieveAppKey(Context context);

    Settings retrieveBeAudienceConfiguration(Context context);

    String retrieveCountry(Context context);

    boolean retrieveGDPRComplianceAlreadyRun(Context context);

    boolean retrieveGDPRConsentSentSuccessfully(Context context);

    String retrieveGoogleAdvID(Context context);

    boolean retrieveIsGDPRCountry(Context context);

    long retrieveNextCoverageCallMills(Context context);

    TrackRequest retrieveRequest(Context context);

    AlgorithmSchedulerStatus retrieveSchedulerStatus(Context context);

    int retrieveSleepDwellCounter(Context context);

    int retrieveSleepHighCounter(Context context);

    InformationList retrieveTempInformationList(Context context);

    boolean retrieveUseGDPRFlowByCuebiq(Context context);

    void saveAppKey(Context context, String str);

    void saveBeAudienceConfiguration(Context context, Settings settings);

    void saveCountry(Context context, String str);

    void saveCustomPublisherID(Context context, String str);

    void saveGDPRComplianceAlreadyRun(Context context);

    void saveGDPRConsentSentSuccesfully(Context context, boolean z);

    void saveGoogleAdvID(Context context, String str);

    void saveIsGDPRCountry(Context context, boolean z);

    void saveNextCoverageCallsMills(Context context, long j);

    void saveSchedulerStatus(Context context, AlgorithmSchedulerStatus algorithmSchedulerStatus);

    void saveSleepDwellCounter(Context context, int i);

    void saveSleepHighCounter(Context context, int i);

    void saveTempInformation(Context context, InformationList informationList);

    void saveUseGDPRFlowByCuebiq(Context context, boolean z);

    void setCoverageStatus(Context context, CoverageStatus coverageStatus);

    void setCurrentAcquisitionMills(Context context, long j);

    void setIsGAIDDisabled(Context context, boolean z);

    void setLocationON(Context context, boolean z);

    void setNextFlushingContent(Context context, int i);

    void setSDKCollectionEnabled(Context context, boolean z);

    void setUserCOPAProtected(Context context, boolean z);
}
