package com.core42matters.android.registrar;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class Store {
    Context context;
    SharedPreferences preferences;

    protected Store(Context context) {
        this(context, null);
    }

    protected Store(Context context, String name) {
        this.preferences = context.getApplicationContext().getSharedPreferences(prefName(context, name), 4);
        this.context = context;
    }

    private static String prefName(Context context, String name) {
        if (TextUtils.isEmpty(name)) {
            name = "prefs";
        }
        return HashUtils.md5(context.getPackageName() + BridgeUtil.SPLIT_MARK + name);
    }

    protected void put(String key, String value) {
        this.preferences.edit().putString(key, new Obfuscator("42isthemeaningoflifeperiod".getBytes(), this.context.getPackageName() + IdUtils.id(this.context), true, false).obfuscate(value, "key:" + key)).commit();
    }

    protected String get(String key) throws ValidationException {
        Obfuscator privateObfuscator = new Obfuscator("42isthemeaningoflifeperiod".getBytes(), this.context.getPackageName() + IdUtils.id(this.context), false, true);
        String value = this.preferences.getString(key, null);
        if (value == null) {
            return null;
        }
        return privateObfuscator.unobfuscate(value, "key:" + key);
    }

    protected void delete(String key) {
        if (this.preferences.contains(key)) {
            this.preferences.edit().remove(key).commit();
        }
    }

    protected void clear() {
        this.preferences.edit().clear().commit();
    }

    protected boolean putHash(String hash, String udid) {
        JSONObject h = new JSONObject();
        try {
            h.put("hash", hash);
            h.put("udid", udid);
            h.put("timestamp", System.currentTimeMillis());
            put("hash", h.toString());
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    protected String getHash(String udid) {
        try {
            JSONObject hash = new JSONObject(get("hash"));
            if (TextUtils.equals(udid, hash.getString("udid"))) {
                return hash.optString("hash", null);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    protected JSONObject getProfile(String udid) {
        try {
            JSONObject jSONObject;
            JSONObject jsonProfile = new JSONObject(get("json_profile"));
            if (udid != null) {
                try {
                    if (!TextUtils.equals(udid, jsonProfile.getString("udid"))) {
                        jSONObject = jsonProfile;
                        return null;
                    }
                } catch (Exception e) {
                    jSONObject = jsonProfile;
                    return null;
                }
            }
            Logger.m3318d("cached profile id: " + jsonProfile.getString("udid"));
            jSONObject = jsonProfile;
            return jsonProfile;
        } catch (Exception e2) {
            return null;
        }
    }

    protected boolean putProfile(String udid, JSONObject jsonProfile) {
        if (TextUtils.isEmpty(udid) || jsonProfile == null) {
            return false;
        }
        try {
            jsonProfile.put("udid", udid);
            put("json_profile", jsonProfile.toString());
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    protected Boolean getSettingsBoolean(String udid, String key, boolean fallback) {
        JSONObject jsonSettings = getSettings(udid);
        if (jsonSettings != null && jsonSettings.has(key)) {
            try {
                return Boolean.valueOf(jsonSettings.getBoolean(key));
            } catch (Exception e) {
                Logger.m3319e(e.getMessage());
            }
        }
        return Boolean.valueOf(fallback);
    }

    protected List<String> getSettingsStringList(String udid, String key, List<String> fallback) {
        JSONObject jsonSettings = getSettings(udid);
        if (jsonSettings != null && jsonSettings.has(key)) {
            try {
                JSONArray jsonArray = jsonSettings.getJSONArray(key);
                List<String> arrayList = new ArrayList();
                for (int i = 0; i < jsonArray.length(); i++) {
                    arrayList.add(jsonArray.getString(i));
                }
                return arrayList;
            } catch (Exception e) {
                Logger.m3319e(e.getMessage());
            }
        }
        return fallback;
    }

    protected JSONObject getSettings(String udid) {
        try {
            JSONObject jsonSettings = new JSONObject(get("json_settings"));
            String saved_udid = jsonSettings.getString("udid");
            if (udid != null && !TextUtils.equals(udid, saved_udid)) {
                return null;
            }
            Logger.m3318d("cached settings id: " + saved_udid);
            return jsonSettings;
        } catch (Exception e) {
            return null;
        }
    }

    protected boolean putSettings(String udid, JSONObject jsonSettings) {
        if (TextUtils.isEmpty(udid) || jsonSettings == null) {
            return false;
        }
        try {
            jsonSettings.put("udid", udid);
            put("json_settings", jsonSettings.toString());
            return true;
        } catch (JSONException e) {
            return false;
        }
    }
}
