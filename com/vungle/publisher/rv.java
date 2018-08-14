package com.vungle.publisher;

import com.appnext.base.p023b.C1042c;

/* compiled from: vungle */
public enum rv {
    CLOSE("close"),
    EXPAND("expand") {
    },
    USE_CUSTOM_CLOSE("useCustomClose"),
    OPEN("open") {
    },
    RESIZE("resize") {
    },
    SET_ORIENTATION_PROPERTIES("setOrientationProperties"),
    PLAY_VIDEO("playVideo") {
    },
    STORE_PICTURE("storePicture") {
    },
    CREATE_CALENDAR_EVENT("createCalendarEvent") {
    },
    PROPERTIES_SET("propertiesChangeCompleted"),
    SUCCESSFUL_VIEW_EVENT("successfulView"),
    TPAT_EVENT("tpat"),
    USER_ACTION_EVENT(C1042c.jL),
    USER_VALUE_ACTION_EVENT("actionWithValue"),
    ERROR_EVENT("error"),
    PRIVACY_PAGE_EVENT("openPrivacy"),
    PLAY_HTML_VIDEO_EVENT("playHTML5Video"),
    USE_CUSTOM_PRIVACY("useCustomPrivacy"),
    THROW_INCENTIVIZED_DIALOG("throwIncentivizedDialog"),
    UNSPECIFIED("");
    
    private final String f11027u;

    private rv(String str) {
        this.f11027u = str;
    }
}
