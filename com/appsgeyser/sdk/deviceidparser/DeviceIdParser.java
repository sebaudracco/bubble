package com.appsgeyser.sdk.deviceidparser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.Settings.Secure;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

public class DeviceIdParser {
    private static DeviceIdParser instance;
    private final DeviceIdParameters deviceIdParameters = new DeviceIdParameters();

    private class ParserThread extends AsyncTask<Context, Void, DeviceIdParameters> {
        private final Context context;
        private final IDeviceIdParserListener listener;

        ParserThread(Context context, IDeviceIdParserListener listener) {
            this.listener = listener;
            this.context = context;
        }

        protected DeviceIdParameters doInBackground(Context... params) {
            DeviceIdParser.this.deviceIdParameters.clear();
            Info advIdInfo = DeviceIdParser.this.getAdvertisingIdInfo(params[0]);
            if (advIdInfo != null) {
                DeviceIdParser.this.deviceIdParameters.setLimitAdTrackingEnabled(advIdInfo.isLimitAdTrackingEnabled() ? LimitAdTrackingEnabledStates.TRUE : LimitAdTrackingEnabledStates.FALSE);
                DeviceIdParser.this.deviceIdParameters.setAdvId(advIdInfo.getId());
            } else {
                DeviceIdParser.this.deviceIdParameters.setLimitAdTrackingEnabled(LimitAdTrackingEnabledStates.UNKNOWN);
                DeviceIdParser.this.deviceIdParameters.setAdvId(null);
                DeviceIdParser.this.deviceIdParameters.setaId(DeviceIdParser.this.getAndroidId(params[0]));
            }
            return DeviceIdParser.this.generateParametersCopy();
        }

        protected void onPostExecute(DeviceIdParameters result) {
            if (this.listener != null) {
                this.listener.onDeviceIdParametersObtained(this.context, result);
            }
        }
    }

    public DeviceIdParameters getDeviceIdParameters() {
        return this.deviceIdParameters;
    }

    public static DeviceIdParser getInstance() {
        if (instance == null) {
            instance = new DeviceIdParser();
        }
        return instance;
    }

    private DeviceIdParser() {
    }

    public boolean isEmpty() {
        return this.deviceIdParameters.isEmpty();
    }

    public void rescan(Context context, IDeviceIdParserListener listener) {
        new ParserThread(context, listener).execute(new Context[]{context});
    }

    private Info getAdvertisingIdInfo(Context context) {
        Info info = null;
        try {
            info = AdvertisingIdClient.getAdvertisingIdInfo(context);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e2) {
            e2.printStackTrace();
        } catch (GooglePlayServicesRepairableException e3) {
            e3.printStackTrace();
        }
        return info;
    }

    @SuppressLint({"HardwareIds"})
    private String getAndroidId(Context context) {
        try {
            return Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private DeviceIdParameters generateParametersCopy() {
        try {
            return (DeviceIdParameters) this.deviceIdParameters.clone();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
