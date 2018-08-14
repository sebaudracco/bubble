package com.mopub.common.event;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Constants;
import com.mopub.common.ClientMetadata$MoPubNetworkType;
import com.mopub.common.Preconditions;
import com.mopub.common.event.BaseEvent.AppPlatform;
import com.mopub.common.event.BaseEvent.SdkProduct;
import com.mopub.common.logging.MoPubLog;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EventSerializer {
    @NonNull
    public JSONArray serializeAsJson(@NonNull List<BaseEvent> events) {
        Preconditions.checkNotNull(events);
        JSONArray jsonArray = new JSONArray();
        for (BaseEvent event : events) {
            try {
                jsonArray.put(serializeAsJson(event));
            } catch (JSONException e) {
                MoPubLog.m12062d("Failed to serialize event \"" + event.getName() + "\" to JSON: ", e);
            }
        }
        return jsonArray;
    }

    @NonNull
    public JSONObject serializeAsJson(@NonNull BaseEvent event) throws JSONException {
        Object obj;
        Object obj2 = null;
        Preconditions.checkNotNull(event);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("_category_", event.getScribeCategory().getCategory());
        jsonObject.put("ts", event.getTimestampUtcMs());
        jsonObject.put("name", event.getName().getName());
        jsonObject.put("name_category", event.getCategory().getCategory());
        SdkProduct sdkProduct = event.getSdkProduct();
        String str = "sdk_product";
        if (sdkProduct == null) {
            obj = null;
        } else {
            obj = Integer.valueOf(sdkProduct.getType());
        }
        jsonObject.put(str, obj);
        jsonObject.put("sdk_version", event.getSdkVersion());
        jsonObject.put("ad_unit_id", event.getAdUnitId());
        jsonObject.put("ad_creative_id", event.getAdCreativeId());
        jsonObject.put("ad_type", event.getAdType());
        jsonObject.put("ad_network_type", event.getAdNetworkType());
        jsonObject.put("ad_width_px", event.getAdWidthPx());
        jsonObject.put("ad_height_px", event.getAdHeightPx());
        jsonObject.put("dsp_creative_id", event.getDspCreativeId());
        AppPlatform appPlatform = event.getAppPlatform();
        str = "app_platform";
        if (appPlatform == null) {
            obj = null;
        } else {
            obj = Integer.valueOf(appPlatform.getType());
        }
        jsonObject.put(str, obj);
        jsonObject.put("app_name", event.getAppName());
        jsonObject.put(Constants.KEY_APP_PACKAGE_NAME, event.getAppPackageName());
        jsonObject.put("app_version", event.getAppVersion());
        jsonObject.put("client_advertising_id", event.getObfuscatedClientAdvertisingId());
        jsonObject.put("client_do_not_track", event.getClientDoNotTrack());
        jsonObject.put("device_manufacturer", event.getDeviceManufacturer());
        jsonObject.put("device_model", event.getDeviceModel());
        jsonObject.put("device_product", event.getDeviceProduct());
        jsonObject.put("device_os_version", event.getDeviceOsVersion());
        jsonObject.put("device_screen_width_px", event.getDeviceScreenWidthDip());
        jsonObject.put("device_screen_height_px", event.getDeviceScreenHeightDip());
        jsonObject.put("geo_lat", event.getGeoLat());
        jsonObject.put("geo_lon", event.getGeoLon());
        jsonObject.put("geo_accuracy_radius_meters", event.getGeoAccuracy());
        jsonObject.put("perf_duration_ms", event.getPerformanceDurationMs());
        ClientMetadata$MoPubNetworkType moPubNetworkType = event.getNetworkType();
        String str2 = "network_type";
        if (moPubNetworkType != null) {
            obj2 = Integer.valueOf(moPubNetworkType.getId());
        }
        jsonObject.put(str2, obj2);
        jsonObject.put("network_operator_code", event.getNetworkOperatorCode());
        jsonObject.put("network_operator_name", event.getNetworkOperatorName());
        jsonObject.put("network_iso_country_code", event.getNetworkIsoCountryCode());
        jsonObject.put("network_sim_code", event.getNetworkSimCode());
        jsonObject.put("network_sim_operator_name", event.getNetworkSimOperatorName());
        jsonObject.put("network_sim_iso_country_code", event.getNetworkSimIsoCountryCode());
        jsonObject.put("req_id", event.getRequestId());
        jsonObject.put("req_status_code", event.getRequestStatusCode());
        jsonObject.put("req_uri", event.getRequestUri());
        jsonObject.put("req_retries", event.getRequestRetries());
        jsonObject.put("timestamp_client", event.getTimestampUtcMs());
        if (event instanceof ErrorEvent) {
            ErrorEvent errorEvent = (ErrorEvent) event;
            jsonObject.put("error_exception_class_name", errorEvent.getErrorExceptionClassName());
            jsonObject.put("error_message", errorEvent.getErrorMessage());
            jsonObject.put("error_stack_trace", errorEvent.getErrorStackTrace());
            jsonObject.put("error_file_name", errorEvent.getErrorFileName());
            jsonObject.put("error_class_name", errorEvent.getErrorClassName());
            jsonObject.put("error_method_name", errorEvent.getErrorMethodName());
            jsonObject.put("error_line_number", errorEvent.getErrorLineNumber());
        }
        return jsonObject;
    }
}
