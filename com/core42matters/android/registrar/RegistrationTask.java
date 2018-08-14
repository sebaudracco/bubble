package com.core42matters.android.registrar;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.mopub.common.GpsHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class RegistrationTask implements Runnable {
    private static long elapsedRealtime = 0;
    private final AppId appId;
    final Context context;
    private final Store store;

    RegistrationTask(Context context, Store store, AppId appId) {
        if (context == null || store == null) {
            throw new NullPointerException();
        }
        this.context = context;
        this.store = store;
        this.appId = appId;
    }

    public void run() {
        String udid = IdUtils.getUDID(this.context);
        if (this.store.getSettings(udid) == null) {
            Logger.m3318d("No settings fetched yet");
            return;
        }
        try {
            ensureAdvertisingIdPrivacy();
            if (Registrar.getConsentStatus(this.context) == ConsentStatus.GIVEN && this.store.getSettingsBoolean(udid, "device_registration_enabled", true).booleanValue()) {
                try {
                    PackageUtils.validateManifest(this.context);
                } catch (SecurityException e) {
                    e.printStackTrace();
                    Logger.showToast(this.context, e.getMessage());
                }
                if (SystemClock.elapsedRealtime() < elapsedRealtime + 300000) {
                    Logger.m3321v("skipped, too soon");
                    return;
                } else if (udid != null) {
                    Logger.m3321v("registration started for " + udid);
                    makeAndSendSnapshot(this.context, this.store, udid);
                    elapsedRealtime = SystemClock.elapsedRealtime();
                    return;
                } else {
                    return;
                }
            }
            this.store.delete("udid");
        } catch (LimitAdTrackingException e2) {
            Logger.m3321v(e2.getMessage());
        }
    }

    private void ensureAdvertisingIdPrivacy() throws LimitAdTrackingException {
        AdvertisingId advertisingId = AdvertisingId.get(this.context);
        if (advertisingId != null && !TextUtils.isEmpty(advertisingId.id)) {
            if (advertisingId.isLimitAdTrackingEnabled) {
                throw new LimitAdTrackingException();
            }
            SharedPreferences deleted_advertising_ids = this.context.getSharedPreferences("deleted_advertising_ids", 0);
            if (Registrar.getConsentStatus(this.context) == ConsentStatus.GIVEN) {
                if (deleted_advertising_ids.contains(advertisingId.id)) {
                    deleted_advertising_ids.edit().remove(advertisingId.id).apply();
                }
            } else if (!deleted_advertising_ids.getBoolean(advertisingId.id, false)) {
                if (!deleted_advertising_ids.contains(advertisingId.id)) {
                    deleted_advertising_ids.edit().putBoolean(advertisingId.id, false).apply();
                }
                StringBuilder sb = new StringBuilder();
                for (String id : deleted_advertising_ids.getAll().keySet()) {
                    if (!deleted_advertising_ids.getBoolean(id, false)) {
                        sb.append(',').append(id);
                    }
                }
                if (sb.length() != 0) {
                    List<String> deletedIdList = deleteAdvertisingIdsOnServer(sb.toString().substring(1));
                    if (deletedIdList.size() > 0) {
                        Editor editor = deleted_advertising_ids.edit();
                        for (String deletedId : deletedIdList) {
                            if (deleted_advertising_ids.contains(deletedId)) {
                                editor.putBoolean(deletedId, true);
                            }
                        }
                        editor.apply();
                    }
                }
            }
        }
    }

    private List<String> deleteAdvertisingIdsOnServer(String ids) {
        Bundle params = new Bundle();
        params.putString("ids", ids);
        try {
            JSONArray deleted = new JSONObject(HttpUtils.requestString(this.context, this.appId, "https://profiler.42matters.com/1/advertising_id", "DELETE", params)).optJSONArray("deleted");
            if (deleted == null || deleted.length() == 0) {
                return Collections.EMPTY_LIST;
            }
            List<String> deletedIdList = new ArrayList();
            for (int i = 0; i < deleted.length(); i++) {
                deletedIdList.add(deleted.getString(i));
            }
            return deletedIdList;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }

    private boolean makeAndSendSnapshot(Context context, Store store, String udid) {
        if (context == null || store == null || udid == null) {
            throw new IllegalArgumentException();
        }
        try {
            if (!Connectivity.isConnected(context)) {
                return false;
            }
        } catch (Exception e) {
        }
        Set packagesForPermission = new HashSet(store.getSettingsStringList(udid, "packages_for_permission", Collections.EMPTY_LIST));
        Set<String> packagesForClasspath = new HashSet(store.getSettingsStringList(udid, "packages_for_classpath", Collections.EMPTY_LIST));
        if (packagesForPermission.isEmpty()) {
            packagesForPermission = null;
        }
        if (packagesForClasspath.isEmpty()) {
            packagesForClasspath = null;
        }
        JSONObject snapshot = PackageUtils.makeSnapshot(context, packagesForPermission, packagesForClasspath);
        if (snapshot == null) {
            return false;
        }
        Logger.m3321v("snapshot: " + snapshot);
        JSONObject payload = makePayload(context, udid, store, snapshot);
        if (payload == null) {
            return false;
        }
        Logger.m3321v("payload: " + payload);
        JSONArray payloads = new JSONArray();
        payloads.put(payload);
        try {
            String resp = sendPayload(context, payloads);
            if (resp != null) {
                try {
                    if (new JSONObject(resp).getInt("s") < payloads.length()) {
                        store.putHash("", udid);
                        Logger.m3321v("removed snapshot hash");
                    } else if (!payload.has("snapshotHash")) {
                        store.putHash(HashUtils.md5(snapshot.toString()), udid);
                    }
                    return true;
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        return false;
    }

    private JSONObject makePayload(Context context, String udid, Store store, JSONObject snapshot) {
        JSONObject payload = new JSONObject();
        try {
            payload.put(C1404b.f2147y, this.appId.getId());
            NetworkInfo info = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                payload.put("networkType", info.getType());
                payload.put("networkSubtype", info.getSubtype());
            }
            TelephonyManager tm = (TelephonyManager) context.getSystemService("phone");
            if (tm != null) {
                payload.put("phoneType", tm.getPhoneType());
                payload.put("networkCountryIso", tm.getNetworkCountryIso());
                payload.put("networkOperator", tm.getNetworkOperator());
                payload.put("networkOperatorName", tm.getNetworkOperatorName());
                payload.put("simCountryIso", tm.getSimCountryIso());
                payload.put("simOperator", tm.getSimOperator());
                payload.put("simOperatorName", tm.getSimOperatorName());
            }
            payload.put("timestamp", System.currentTimeMillis());
            payload.put("from", context.getPackageName());
            AdvertisingId advertisingId = AdvertisingId.get(context);
            if (!(advertisingId == null || advertisingId.isLimitAdTrackingEnabled)) {
                payload.put(GpsHelper.ADVERTISING_ID_KEY, advertisingId.id);
            }
            payload.put("udid", udid);
            String lastHash = store.getHash(udid);
            if (lastHash == null || !TextUtils.equals(lastHash, HashUtils.md5(snapshot.toString()))) {
                return payload.put("snapshot", snapshot);
            }
            return payload.put("snapshotHash", lastHash);
        } catch (Exception e) {
            return null;
        }
    }

    private String sendPayload(Context context, JSONArray payloads) throws IOException {
        return HttpUtils.sendPayload(context, this.appId, "https://profiler.42matters.com/1/device", payloads.toString());
    }
}
