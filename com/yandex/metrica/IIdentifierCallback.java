package com.yandex.metrica;

import java.util.Map;

public interface IIdentifierCallback {
    public static final String YANDEX_MOBILE_METRICA_DEVICE_ID = "yandex_mobile_metrica_device_id";
    public static final String YANDEX_MOBILE_METRICA_GET_AD_URL = "yandex_mobile_metrica_get_ad_url";
    public static final String YANDEX_MOBILE_METRICA_REPORT_AD_URL = "yandex_mobile_metrica_report_ad_url";
    public static final String YANDEX_MOBILE_METRICA_UUID = "yandex_mobile_metrica_uuid";

    public enum Reason {
        UNKNOWN,
        NETWORK,
        INVALID_RESPONSE
    }

    void onReceive(Map<String, String> map);

    void onRequestError(Reason reason);
}
