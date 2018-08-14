package com.cuebiq.cuebiqsdk.model.manager;

import android.content.Context;
import android.location.Location;
import com.cuebiq.cuebiqsdk.CuebiqSDKImpl;
import com.cuebiq.cuebiqsdk.model.BatchManager;
import com.cuebiq.cuebiqsdk.model.InfoAnalysisResult;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.cuebiq.cuebiqsdk.model.helper.ResourcesHelper;
import com.cuebiq.cuebiqsdk.model.persistence.PersistenceManagerFactory;
import com.cuebiq.cuebiqsdk.model.processor.InformationProcessor;
import com.cuebiq.cuebiqsdk.model.wrapper.Auth;
import com.cuebiq.cuebiqsdk.model.wrapper.Device;
import com.cuebiq.cuebiqsdk.model.wrapper.Event;
import com.cuebiq.cuebiqsdk.model.wrapper.Geo;
import com.cuebiq.cuebiqsdk.model.wrapper.Information;
import com.cuebiq.cuebiqsdk.model.wrapper.TrackRequest;
import com.cuebiq.cuebiqsdk.utils.InformationList;
import com.cuebiq.cuebiqsdk.utils.Utils;

public class CustomEventManager {
    public static void gatherCustomEvent(Context context, String str, String str2, String str3, String str4, String str5, Location location) {
        try {
            Event event = new Event();
            event.setName(str);
            event.seteInfo1(str2);
            event.seteInfo2(str3);
            event.seteInfo3(str4);
            event.seteInfo4(str5);
            TrackRequest trackRequest = new TrackRequest();
            trackRequest.setDevice(Device.build(context));
            trackRequest.setAuth(Auth.build(context));
            Information information = new Information();
            if (location != null) {
                information.setGeo(Geo.build(location));
            }
            information.setEvent(event);
            new InformationProcessor().gather(context, information, null);
            InformationList informationList = new InformationList();
            informationList.add(information);
            trackRequest.setInformation(informationList);
            Settings beAudienceConfiguration = CuebiqSDKImpl.getBeAudienceConfiguration(context);
            InfoAnalysisResult analyzeRequest = BatchManager.analyzeRequest(PersistenceManagerFactory.get().retrieveRequest(context), trackRequest, (int) Utils.getBatteryLevel(context), beAudienceConfiguration);
            if (analyzeRequest.getTrackRequest() != null) {
                PersistenceManagerFactory.get().persistRequest(context, analyzeRequest.getTrackRequest());
                if (!Utils.isFlushCounterActive(context) && BatchManager.flush(analyzeRequest.getTrackRequest(), beAudienceConfiguration)) {
                    PersistenceManagerFactory.get().persistRequest(context, null);
                    ResourcesHelper.get().bea(context.getApplicationContext(), analyzeRequest.getTrackRequest());
                    return;
                }
                return;
            }
            PersistenceManagerFactory.get().persistRequest(context, null);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
