package com.jirbo.adcolony;

import android.os.Bundle;

public class AdColonyBundleBuilder {
    private static boolean _showPostAdPopup;
    private static boolean _showPreAdPopup;
    private static boolean _testMode;
    private static String _userId;
    private static String _zoneId;

    public static void setZoneId(String requestedZone) {
        _zoneId = requestedZone;
    }

    public static void setUserId(String userIdValue) {
        _userId = userIdValue;
    }

    public static void setShowPrePopup(boolean showPrePopupValue) {
        _showPreAdPopup = showPrePopupValue;
    }

    public static void setShowPostPopup(boolean showPostPopupValue) {
        _showPostAdPopup = showPostPopupValue;
    }

    public static void setTestModeEnabled(boolean enabled) {
        _testMode = enabled;
    }

    public static Bundle build() {
        Bundle bundle = new Bundle();
        bundle.putString("zone_id", _zoneId);
        bundle.putString("user_id", _userId);
        bundle.putBoolean("show_pre_popup", _showPreAdPopup);
        bundle.putBoolean("show_post_popup", _showPostAdPopup);
        bundle.putBoolean("test_mode", _testMode);
        return bundle;
    }
}