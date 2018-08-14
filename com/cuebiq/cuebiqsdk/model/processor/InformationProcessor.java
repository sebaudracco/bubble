package com.cuebiq.cuebiqsdk.model.processor;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.cuebiq.cuebiqsdk.CuebiqSDKImpl;
import com.cuebiq.cuebiqsdk.model.listener.ProcessorCompletedListener;
import com.cuebiq.cuebiqsdk.model.wrapper.Information;
import com.cuebiq.cuebiqsdk.utils.Utils;

public class InformationProcessor extends AbstractProcessor {
    public InformationProcessor() {
        super(ProcessorType.INFORMATION_PROCESSOR);
    }

    public void gather(Context context, Information information, ProcessorCompletedListener processorCompletedListener) {
        try {
            information.setTimestamp(Long.valueOf(System.currentTimeMillis() / 1000));
            information.setBatteryLevel(Float.valueOf(Utils.getBatteryLevel(context)));
            information.setIpAddressV4(Utils.getIPAddressV4());
            information.setIpAddressV6(Utils.getIPAddressV6());
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                information.setDataConnectionType(activeNetworkInfo.getSubtypeName());
                information.setIsRoaming(Boolean.valueOf(activeNetworkInfo.isRoaming()));
            }
        } catch (Throwable th) {
            CuebiqSDKImpl.log(th.getMessage());
        }
        if (processorCompletedListener != null) {
            processorCompletedListener.onProcessorCompleted(getType());
        }
    }
}
