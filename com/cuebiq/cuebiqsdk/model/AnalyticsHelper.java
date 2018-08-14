package com.cuebiq.cuebiqsdk.model;

import android.content.Context;
import com.cuebiq.cuebiqsdk.CuebiqSDKImpl;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.cuebiq.cuebiqsdk.model.persistence.PersistenceManagerFactory;

public class AnalyticsHelper {
    public boolean pingCoverageAfterXAcquisition(Context context, Settings settings) {
        if (PersistenceManagerFactory.get().getCoverageCounter(context) >= settings.getAncc()) {
            CoverageManager.get().checkCoverage(context, true, null);
            PersistenceManagerFactory.get().resetCoverageCounter(context);
            return true;
        }
        PersistenceManagerFactory.get().increaseCoverageCounter(context);
        return false;
    }

    public boolean pingCoverageAfterXAppOpenFromOptout(Context context) {
        if (PersistenceManagerFactory.get().getAppOpenCounter(context) >= CuebiqSDKImpl.getBeAudienceConfiguration(context).getAnao()) {
            CoverageManager.get().checkCoverage(context, true, null);
            PersistenceManagerFactory.get().resetAppOpenCounter(context);
            return true;
        }
        PersistenceManagerFactory.get().increaseAppOpenCounter(context);
        return false;
    }
}
