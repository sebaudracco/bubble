package com.inmobi.ads;

import com.cuebiq.cuebiqsdk.model.config.Settings;
import cz.msebera.android.httpclient.HttpStatus;

public enum VastErrorCode {
    NO_ERROR(Integer.valueOf(0)),
    XML_PARSING_ERROR(Integer.valueOf(100)),
    SCHEMA_VALIDATION_ERROR(Integer.valueOf(101)),
    UNSUPPORTED_AD_TYPE(Integer.valueOf(201)),
    GENERAL_WRAPPER_ERROR(Integer.valueOf(HttpStatus.SC_MULTIPLE_CHOICES)),
    VAST_URI_NETWORK_ERROR(Integer.valueOf(HttpStatus.SC_MOVED_PERMANENTLY)),
    WRAPPER_MAX_LIMIT_EXCEEDED(Integer.valueOf(HttpStatus.SC_MOVED_TEMPORARILY)),
    WRAPPER_NO_AD(Integer.valueOf(HttpStatus.SC_SEE_OTHER)),
    GENERAL_LINEAR_ERROR(Integer.valueOf(HttpStatus.SC_BAD_REQUEST)),
    LINEAR_MEDIA_FILE_NOT_FOUND(Integer.valueOf(HttpStatus.SC_UNAUTHORIZED)),
    MEDIA_FILE_TIMEOUT(Integer.valueOf(402)),
    NO_SUPPORTED_MEDIA(Integer.valueOf(HttpStatus.SC_FORBIDDEN)),
    MEDIA_PLAY_ERROR(Integer.valueOf(405)),
    GENERAL_COMPANION_ERROR(Integer.valueOf(Settings.MAX_DYNAMIC_ACQUISITION)),
    NO_BEST_FIT_COMPANION(Integer.valueOf(601)),
    COMPANION_RESOURCE_NOT_ACCESSIBLE(Integer.valueOf(603)),
    MISSING_SUPPORTED_TYPE_COMPANION(Integer.valueOf(604)),
    UNDEFINED_ERROR(Integer.valueOf(900));
    
    private Integer f6947a;

    private VastErrorCode(Integer num) {
        this.f6947a = num;
    }

    public Integer getId() {
        return this.f6947a;
    }
}
