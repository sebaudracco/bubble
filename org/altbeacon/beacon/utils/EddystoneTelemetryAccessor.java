package org.altbeacon.beacon.utils;

import android.annotation.TargetApi;
import android.util.Base64;
import android.util.Log;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.Beacon$Builder;
import org.altbeacon.beacon.BeaconParser;

public class EddystoneTelemetryAccessor {
    private static final String TAG = "EddystoneTLMAccessor";

    public byte[] getTelemetryBytes(Beacon beacon) {
        if (beacon.getExtraDataFields().size() < 5) {
            return null;
        }
        byte[] telemetryBytes = new BeaconParser().setBeaconLayout(BeaconParser.EDDYSTONE_TLM_LAYOUT).getBeaconAdvertisementData(new Beacon$Builder().setDataFields(beacon.getExtraDataFields()).build());
        Log.d(TAG, "Rehydrated telemetry bytes are :" + byteArrayToString(telemetryBytes));
        return telemetryBytes;
    }

    @TargetApi(8)
    public String getBase64EncodedTelemetry(Beacon beacon) {
        byte[] bytes = getTelemetryBytes(beacon);
        if (bytes == null) {
            return null;
        }
        String base64EncodedTelemetry = Base64.encodeToString(bytes, 0);
        Log.d(TAG, "Base64 telemetry bytes are :" + base64EncodedTelemetry);
        return base64EncodedTelemetry;
    }

    private String byteArrayToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(String.format("%02x", new Object[]{Byte.valueOf(bytes[i])}));
            sb.append(" ");
        }
        return sb.toString().trim();
    }
}
