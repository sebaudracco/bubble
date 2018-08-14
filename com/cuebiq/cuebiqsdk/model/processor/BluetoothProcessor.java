package com.cuebiq.cuebiqsdk.model.processor;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.Build.VERSION;
import com.cuebiq.cuebiqsdk.CuebiqSDKImpl;
import com.cuebiq.cuebiqsdk.model.listener.ProcessorCompletedListener;
import com.cuebiq.cuebiqsdk.model.wrapper.Information;

public class BluetoothProcessor extends AbstractProcessor {
    BluetoothProcessor() {
        super(ProcessorType.BLUETOOTH_PROCESSOR);
    }

    public void gather(Context context, Information information, ProcessorCompletedListener processorCompletedListener) {
        if (VERSION.SDK_INT < 23) {
            return;
        }
        if (context.checkSelfPermission("android.permission.BLUETOOTH") != 0) {
            CuebiqSDKImpl.log("LocationManager -> Permission BLUETOOTH is not granted. Unable to acquire bluetooth signals.");
            processorCompletedListener.onProcessorCompleted(ProcessorType.BLUETOOTH_PROCESSOR);
            return;
        }
        BluetoothAdapter.getDefaultAdapter().getBondedDevices();
    }
}
