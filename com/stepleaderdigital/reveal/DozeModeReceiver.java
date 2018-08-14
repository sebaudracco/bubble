package com.stepleaderdigital.reveal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PowerManager;
import com.stepleaderdigital.reveal.Reveal.LocationService;
import java.util.Arrays;

public class DozeModeReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Reveal.getInstance().recordEvent("Idle state changes");
        if (VERSION.SDK_INT >= 23 && intent.getAction() != null) {
            String state;
            PowerManager pm = (PowerManager) context.getSystemService("power");
            if (pm.isDeviceIdleMode()) {
                state = "Idle";
            } else {
                Reveal.getInstance().getDwellManager().processPendingEvents();
                LocationService locationService = Reveal.getInstance().getLocationService();
                if (locationService != null) {
                    locationService.startLocationMonitoring(context);
                }
                state = "Not idle";
            }
            if (pm.isPowerSaveMode()) {
                state = state + " (Power Save)";
            }
            Reveal.log("Idle state change: " + state + " intent=" + intentToString(intent), "STATE");
        }
    }

    public static String intentToString(Intent intent) {
        if (intent == null) {
            return null;
        }
        String out = intent.toString();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            extras.size();
            out = out + "\n" + bundleToString(extras);
        }
        if (intent.getAction() != null) {
            out = out + "\nAction = " + intent.getAction();
        }
        if (intent.getType() != null) {
            out = out + "\nType = " + intent.getType();
        }
        if (intent.getData() != null) {
            out = out + "\nData = " + intent.getData();
        }
        if (intent.getPackage() != null) {
            out = out + "\nPackage = " + intent.getPackage();
        }
        if (intent.getDataString() != null) {
            return out + "\nDataString = " + intent.getDataString();
        }
        return out;
    }

    public static String bundleToString(Bundle bundle) {
        StringBuilder out = new StringBuilder("Bundle[");
        if (bundle == null) {
            out.append("null");
        } else {
            boolean first = true;
            for (String key : bundle.keySet()) {
                if (!first) {
                    out.append(", ");
                }
                out.append(key).append('=');
                Object value = bundle.get(key);
                if (value instanceof int[]) {
                    out.append(Arrays.toString((int[]) value));
                } else if (value instanceof byte[]) {
                    out.append(Arrays.toString((byte[]) value));
                } else if (value instanceof boolean[]) {
                    out.append(Arrays.toString((boolean[]) value));
                } else if (value instanceof short[]) {
                    out.append(Arrays.toString((short[]) value));
                } else if (value instanceof long[]) {
                    out.append(Arrays.toString((long[]) value));
                } else if (value instanceof float[]) {
                    out.append(Arrays.toString((float[]) value));
                } else if (value instanceof double[]) {
                    out.append(Arrays.toString((double[]) value));
                } else if (value instanceof String[]) {
                    out.append(Arrays.toString((String[]) value));
                } else if (value instanceof CharSequence[]) {
                    out.append(Arrays.toString((CharSequence[]) value));
                } else if (value instanceof Parcelable[]) {
                    out.append(Arrays.toString((Parcelable[]) value));
                } else if (value instanceof Bundle) {
                    out.append(bundleToString((Bundle) value));
                } else {
                    out.append(value);
                }
                first = false;
            }
        }
        out.append("]");
        return out.toString();
    }
}
