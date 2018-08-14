package com.cuebiq.cuebiqsdk.model.processor;

import android.content.Context;
import android.location.Location;
import com.cuebiq.cuebiqsdk.CuebiqSDKImpl;
import com.cuebiq.cuebiqsdk.model.listener.ProcessorCompletedListener;
import com.cuebiq.cuebiqsdk.model.manager.LocationManagerHelper;
import com.cuebiq.cuebiqsdk.model.manager.LocationManagerHelper.OnLocationListener;
import com.cuebiq.cuebiqsdk.model.wrapper.Geo;
import com.cuebiq.cuebiqsdk.model.wrapper.Information;

public class LocationProcessor extends AbstractProcessor {
    public LocationProcessor() {
        super(ProcessorType.LOCATION_PROCESSOR);
    }

    public void gather(final Context context, final Information information, final ProcessorCompletedListener processorCompletedListener) {
        new LocationManagerHelper().getLocation(context, new OnLocationListener() {
            public void onLocation(Location location) {
                CuebiqSDKImpl.log("Location Processor -> Location acquired: " + (location != null));
                if (CuebiqSDKImpl.getBeAudienceConfiguration(context).getTlowo() == 0 && location == null) {
                    CuebiqSDKImpl.log("Location Processor -> Location not available. Skip tracking.");
                    processorCompletedListener.onProcessorCompleted(LocationProcessor.this.getType());
                    return;
                }
                if (location != null) {
                    try {
                        information.setGeo(Geo.build(location));
                    } catch (Throwable th) {
                        CuebiqSDKImpl.log(th.getMessage());
                    }
                }
                if (processorCompletedListener != null) {
                    processorCompletedListener.onProcessorCompleted(LocationProcessor.this.getType());
                }
            }
        });
    }
}
