package com.appsgeyser.sdk.configuration;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.appsgeyser.sdk.GuidGenerator;
import org.json.JSONException;
import org.json.JSONObject;

public final class Configuration {
    private static final String DEFAULT_APPGUID = "";
    private static final String DEFAULT_APPID = "";
    private static final String DEFAULT_TEMPLATE_VERSION = "";
    private static final String KEY_APPLICATION_ID = "ApplicationId";
    private static final String KEY_APP_GUID = "AppGuid";
    private static final String KEY_APP_NAME = "AppName";
    private static final String KEY_REGISTERED = "Registered";
    private static final String KEY_TEMPLATE_VERSION = "TemplateVersion";
    private static final String METRICA_APPSGEYSER_SDK_VERSION_NAME = "appsgeyserSdkVersion";
    private static final String METRICA_JSON_LOAD = "metricaJsonLoad";
    private static final String METRICA_TEMPLATE_VERSION_NAME = "templateVersion";
    private static Configuration instance;
    private String appGuid = "";
    private String appName = "";
    private String applicationId = "";
    private String publisherName = "";
    private boolean registered = false;
    private PreferencesCoder settingsCoder;

    private Configuration() {
    }

    public static Configuration getInstance(@NonNull Context context) {
        if (instance == null) {
            instance = new Configuration();
        }
        instance.setSettingsCoder(new PreferencesCoder(context));
        return instance;
    }

    @NonNull
    public PreferencesCoder getSettingsCoder() {
        return this.settingsCoder;
    }

    private void setSettingsCoder(PreferencesCoder coder) {
        this.settingsCoder = coder;
    }

    public void loadConfiguration() {
        this.publisherName = "";
        this.applicationId = this.settingsCoder.getPrefString(KEY_APPLICATION_ID, "");
        this.appGuid = this.settingsCoder.getPrefString(KEY_APP_GUID, "");
        this.registered = this.settingsCoder.getPrefBoolean(KEY_REGISTERED, false);
    }

    public boolean isRegistered() {
        return this.registered;
    }

    public String getAppGuid() {
        if (TextUtils.isEmpty(this.appGuid)) {
            this.appGuid = GuidGenerator.generateNewGuid();
            if (this.settingsCoder != null) {
                this.settingsCoder.savePrefString(KEY_APP_GUID, this.appGuid);
            }
        }
        return this.appGuid;
    }

    public String getPublisherName() {
        return this.publisherName;
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public void setApplicationId(@NonNull String applicationId) {
        this.applicationId = applicationId;
        if (this.settingsCoder != null) {
            this.settingsCoder.savePrefString(KEY_APPLICATION_ID, this.applicationId);
        }
    }

    public void setTemplateVersion(@NonNull String templateVersion) {
        this.settingsCoder.savePrefString(KEY_TEMPLATE_VERSION, templateVersion);
    }

    public String getTemplateVersion() {
        return this.settingsCoder.getPrefString(KEY_TEMPLATE_VERSION, "");
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(@NonNull String appName) {
        this.appName = appName;
        if (this.settingsCoder != null) {
            this.settingsCoder.savePrefString(KEY_APP_NAME, this.appName);
        }
    }

    public void clearApplicationSettings() {
        this.applicationId = "";
        this.appGuid = "";
        this.settingsCoder.savePrefString(KEY_APPLICATION_ID, "");
        this.settingsCoder.savePrefString(KEY_APP_GUID, "");
    }

    public void registerNew() {
        this.registered = true;
        this.settingsCoder.savePrefBoolean(KEY_REGISTERED, true);
    }

    public void setMetricaOnStartEvent(@NonNull String metricaOnStartEvent, @NonNull String templateVersion) {
        try {
            JSONObject jsonObject = new JSONObject(metricaOnStartEvent);
            jsonObject.put(METRICA_TEMPLATE_VERSION_NAME, templateVersion);
            jsonObject.put(METRICA_APPSGEYSER_SDK_VERSION_NAME, Constants.PLATFORM_VERSION);
            this.settingsCoder.savePrefString(METRICA_JSON_LOAD, jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    public String getMetricaOnStartEvent() {
        return this.settingsCoder.getPrefString(METRICA_JSON_LOAD, null);
    }
}
