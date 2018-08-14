package com.fyber.mediation.admob;

import android.support.annotation.NonNull;
import com.fyber.mediation.MediationAdapter;
import com.fyber.utils.FyberLogger;
import com.fyber.utils.StringUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;

public class AdMobTestDevicesHelper {
    private static final String TAG = "AdMobTestDevicesHelper";

    @NonNull
    public static List<String> getTestDevices(Map<String, Object> configs) {
        String[] inputStrings = (String[]) getConfig(configs, String[].class);
        if (inputStrings != null) {
            return getTestDevicesFromStringArray(inputStrings);
        }
        JSONArray jsonArray = (JSONArray) getConfig(configs, JSONArray.class);
        if (jsonArray != null) {
            return parseJsonArray(jsonArray);
        }
        String inputString = (String) getConfig(configs, String.class);
        if (inputString != null) {
            return parseAsCommaSeparatedList(inputString);
        }
        return Collections.emptyList();
    }

    @NonNull
    private static List<String> getTestDevicesFromStringArray(@NonNull String[] inputStrings) {
        switch (inputStrings.length) {
            case 0:
                return Collections.emptyList();
            case 1:
                try {
                    String jsonArray = inputStrings[0];
                    if (StringUtils.nullOrEmpty(jsonArray)) {
                        return Collections.emptyList();
                    }
                    return parseJsonArray(new JSONArray(jsonArray));
                } catch (JSONException e) {
                    return parseAsCommaSeparatedList(inputStrings[0]);
                }
            default:
                return filterValidIdsAsList(inputStrings);
        }
    }

    @NonNull
    private static List<String> filterValidIdsAsList(@NonNull String[] asArray) {
        List<String> testDevices = new ArrayList(asArray.length);
        for (String entry : asArray) {
            addIfValid(testDevices, entry);
        }
        return testDevices;
    }

    @NonNull
    private static List<String> parseAsCommaSeparatedList(@NonNull String s) {
        String[] entries = s.split(",");
        List<String> testDevices = new ArrayList(entries.length);
        for (String entry : entries) {
            addIfValid(testDevices, entry);
        }
        return testDevices;
    }

    @NonNull
    private static List<String> parseJsonArray(@NonNull JSONArray jsonArray) {
        List<String> testDevices = new ArrayList(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                addIfValid(testDevices, jsonArray.getString(i));
            } catch (JSONException e) {
                FyberLogger.m8453w(TAG, "Test device id is not a string (in JSONArray, index " + i + ")");
            }
        }
        return testDevices;
    }

    private static void addIfValid(List<String> testDevices, String entry) {
        entry = entry.trim();
        if (isValidDeviceId(entry)) {
            testDevices.add(entry);
        } else {
            FyberLogger.m8453w(TAG, "Test device id is unrecognized. Got: " + entry);
        }
    }

    private static boolean isValidDeviceId(String s) {
        return s.matches("\\w+");
    }

    private static <T> T getConfig(Map<String, Object> config, Class<T> clasz) {
        return MediationAdapter.getConfiguration(config, AdMobMediationAdapter.BUILDER_CONFIG_ADD_TEST_DEVICE, clasz);
    }
}
