package com.fyber;

import android.content.Context;
import com.fyber.utils.C2664l;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Fyber$Settings {
    public static Fyber$Settings f5981a = new Fyber$Settings();
    Map<String, String> f5982b;
    boolean f5983c = true;
    boolean f5984d = true;
    boolean f5985e = false;
    private EnumMap<UIStringIdentifier, String> f5986f = new EnumMap(UIStringIdentifier.class);

    public enum UIStringIdentifier {
        ERROR_DIALOG_TITLE,
        DISMISS_ERROR_DIALOG,
        GENERIC_ERROR,
        ERROR_LOADING_OFFERWALL,
        ERROR_LOADING_OFFERWALL_NO_INTERNET_CONNECTION,
        LOADING_INTERSTITIAL,
        LOADING_OFFERWALL,
        ERROR_PLAY_STORE_UNAVAILABLE,
        RV_REWARD_NOTIFICATION,
        VCS_COINS_NOTIFICATION,
        VCS_DEFAULT_CURRENCY,
        RV_ERROR_DIALOG_TITLE,
        RV_ERROR_DIALOG_MESSAGE_DEFAULT,
        RV_ERROR_DIALOG_MESSAGE_OFFLINE,
        RV_ERROR_DIALOG_BUTTON_TITLE_DISMISS,
        RV_FORFEIT_DIALOG_TITLE,
        RV_CLICKTHROUGH_HINT,
        RV_ALERT_DIALOG_EXIT_VIDEO_TEXT,
        RV_ALERT_DIALOG_CLOSE_VIDEO_TEXT,
        RV_ALERT_DIALOG_RESUME_VIDEO_TEXT,
        RV_ALERT_DIALOG_TITLE,
        RV_ALERT_DIALOG_MESSAGE,
        RV_LOADING_MESSAGE,
        RV_REDIRECT_DIALOG_TITLE,
        RV_REDIRECT_DIALOG_MESSAGE_MARKET,
        RV_REDIRECT_DIALOG_MESSAGE_DEFAULT,
        RV_REDIRECT_ERROR,
        INT_VIDEO_DIALOG_CLOSE,
        SDK_NOT_STARTED,
        ANNOTATIONS_PROBLEM,
        ANNOTATIONS_PROBLEM_DESCRIPTION,
        TOKEN_MISSING,
        NO_BUNDLES,
        TEST_SUITE_VERSION,
        SDK_VERSION,
        STARTED_BUNDLES_TITLE,
        STARTED_BUNDLES_MESSAGE,
        BUNDLES_NOT_STARTED_TITLE,
        BUNDLES_NOT_STARTED_MESSAGE,
        MISSING_BUNDLES_TITLE,
        MISSING_BUNDLES_MESSAGE
    }

    Fyber$Settings() {
        this.f5986f.put(UIStringIdentifier.ERROR_DIALOG_TITLE, "Error");
        this.f5986f.put(UIStringIdentifier.DISMISS_ERROR_DIALOG, "Dismiss");
        this.f5986f.put(UIStringIdentifier.GENERIC_ERROR, "An error happened when performing this operation");
        this.f5986f.put(UIStringIdentifier.ERROR_LOADING_OFFERWALL, "An error happened when loading the offer wall");
        this.f5986f.put(UIStringIdentifier.ERROR_LOADING_OFFERWALL_NO_INTERNET_CONNECTION, "An error happened when loading the offer wall (no internet connection)");
        this.f5986f.put(UIStringIdentifier.LOADING_INTERSTITIAL, "Loading...");
        this.f5986f.put(UIStringIdentifier.LOADING_OFFERWALL, "Loading...");
        this.f5986f.put(UIStringIdentifier.ERROR_PLAY_STORE_UNAVAILABLE, "You don't have the Google Play Store application on your device to complete App Install offers.");
        this.f5986f.put(UIStringIdentifier.RV_REWARD_NOTIFICATION, "Thanks! Your reward will be paid out shortly");
        this.f5986f.put(UIStringIdentifier.VCS_COINS_NOTIFICATION, "Congratulations! You've earned %.0f %s!");
        this.f5986f.put(UIStringIdentifier.VCS_DEFAULT_CURRENCY, "coins");
        this.f5986f.put(UIStringIdentifier.RV_ERROR_DIALOG_TITLE, "Error");
        this.f5986f.put(UIStringIdentifier.RV_ERROR_DIALOG_MESSAGE_DEFAULT, "We're sorry, something went wrong. Please try again.");
        this.f5986f.put(UIStringIdentifier.RV_ERROR_DIALOG_MESSAGE_OFFLINE, "Your Internet connection has been lost. Please try again later.");
        this.f5986f.put(UIStringIdentifier.RV_ERROR_DIALOG_BUTTON_TITLE_DISMISS, "Dismiss");
        this.f5986f.put(UIStringIdentifier.RV_FORFEIT_DIALOG_TITLE, "");
        this.f5986f.put(UIStringIdentifier.RV_CLICKTHROUGH_HINT, "Tap anywhere to discover more about this ad");
        this.f5986f.put(UIStringIdentifier.RV_ALERT_DIALOG_EXIT_VIDEO_TEXT, "Exit Video");
        this.f5986f.put(UIStringIdentifier.RV_ALERT_DIALOG_CLOSE_VIDEO_TEXT, "Close Video");
        this.f5986f.put(UIStringIdentifier.RV_ALERT_DIALOG_RESUME_VIDEO_TEXT, "Resume Video");
        this.f5986f.put(UIStringIdentifier.RV_ALERT_DIALOG_TITLE, "Error");
        this.f5986f.put(UIStringIdentifier.RV_ALERT_DIALOG_MESSAGE, "An error has occurred while trying to load the video");
        this.f5986f.put(UIStringIdentifier.RV_LOADING_MESSAGE, "Loading...");
        this.f5986f.put(UIStringIdentifier.RV_REDIRECT_DIALOG_TITLE, "Warning");
        this.f5986f.put(UIStringIdentifier.RV_REDIRECT_DIALOG_MESSAGE_MARKET, "You will now be redirected to the play store, do you wish to forfeit your reward?");
        this.f5986f.put(UIStringIdentifier.RV_REDIRECT_DIALOG_MESSAGE_DEFAULT, "Do you wish to forfeit your reward?");
        this.f5986f.put(UIStringIdentifier.RV_REDIRECT_ERROR, "Sorry, we cannot redirect you to the desired application");
        this.f5986f.put(UIStringIdentifier.INT_VIDEO_DIALOG_CLOSE, "Do you really want to skip the video?");
        this.f5986f.put(UIStringIdentifier.SDK_NOT_STARTED, "The SDK was not started");
        this.f5986f.put(UIStringIdentifier.ANNOTATIONS_PROBLEM, "Annotations not correctly integrated");
        this.f5986f.put(UIStringIdentifier.ANNOTATIONS_PROBLEM_DESCRIPTION, "You might be missing a dependency to the annotations and/or annotations-compiler libs. Make sure you also add @FyberSDK to one of your classes.\nYou need compiler version 1.5.0 or higher and annotations version 1.3.0 or higher.");
        this.f5986f.put(UIStringIdentifier.TOKEN_MISSING, "The SDK was started without a security token\nThe token is required to fetch bundles' credentials from the dashboard");
        this.f5986f.put(UIStringIdentifier.NO_BUNDLES, "No bundles integrated\nYou need at least one bundle integrated to have a complete analysis");
        this.f5986f.put(UIStringIdentifier.TEST_SUITE_VERSION, "Integration Test Suite - v%s");
        this.f5986f.put(UIStringIdentifier.SDK_VERSION, "Fyber SDK - v%s");
        this.f5986f.put(UIStringIdentifier.STARTED_BUNDLES_TITLE, "STARTED BUNDLES");
        this.f5986f.put(UIStringIdentifier.STARTED_BUNDLES_MESSAGE, "The SDK successfully started the bundles above.");
        this.f5986f.put(UIStringIdentifier.BUNDLES_NOT_STARTED_TITLE, "BUNDLES NOT STARTED");
        this.f5986f.put(UIStringIdentifier.BUNDLES_NOT_STARTED_MESSAGE, "The SDK could not start the bundles above.\nPlease make sure the corresponding networks are enabled on the dashboard and the necessary credentials are present.");
        this.f5986f.put(UIStringIdentifier.MISSING_BUNDLES_TITLE, "MISSING BUNDLES");
        this.f5986f.put(UIStringIdentifier.MISSING_BUNDLES_MESSAGE, "The SDK could not find the bundles above.\nPlease follow the Integration Guides in the Developer Portal to add them to your project.");
    }

    public String getUserId() {
        return Fyber.getConfigs().f5997d.m7593b();
    }

    public void updateUserId(String str) throws IllegalArgumentException, IllegalStateException {
        Fyber.getConfigs().f5997d.m7592a(str);
    }

    public Fyber$Settings notifyUserOnCompletion(boolean z) {
        this.f5983c = z;
        return this;
    }

    public Fyber$Settings notifyUserOnReward(boolean z) {
        this.f5984d = z;
        return this;
    }

    public Fyber$Settings closeOfferWallOnRedirect(boolean z) {
        this.f5985e = z;
        return this;
    }

    public Fyber$Settings addParameters(Map<String, String> map) {
        if (C2664l.m8521b(map)) {
            if (this.f5982b == null) {
                this.f5982b = new HashMap();
            }
            this.f5982b.putAll(map);
        }
        return this;
    }

    public Fyber$Settings addParameter(String str, String str2) {
        if (this.f5982b == null) {
            this.f5982b = new HashMap();
        }
        this.f5982b.put(str, str2);
        return this;
    }

    public Fyber$Settings clearParameters() {
        if (this.f5982b != null) {
            this.f5982b.clear();
        }
        return this;
    }

    public Fyber$Settings removeParameter(String str) {
        if (this.f5982b != null) {
            this.f5982b.remove(str);
        }
        return this;
    }

    public String getUIString(UIStringIdentifier uIStringIdentifier) {
        return (String) this.f5986f.get(uIStringIdentifier);
    }

    public Fyber$Settings setCustomUIString(UIStringIdentifier uIStringIdentifier, String str) {
        this.f5986f.put(uIStringIdentifier, str);
        return this;
    }

    public Fyber$Settings setCustomUIStrings(EnumMap<UIStringIdentifier, String> enumMap) {
        for (Entry entry : enumMap.entrySet()) {
            setCustomUIString((UIStringIdentifier) entry.getKey(), (String) entry.getValue());
        }
        return this;
    }

    public Fyber$Settings setCustomUIStrings(EnumMap<UIStringIdentifier, Integer> enumMap, Context context) {
        for (Entry entry : enumMap.entrySet()) {
            setCustomUIString((UIStringIdentifier) entry.getKey(), ((Integer) entry.getValue()).intValue(), context);
        }
        return this;
    }

    public Fyber$Settings setCustomUIString(UIStringIdentifier uIStringIdentifier, int i, Context context) {
        setCustomUIString(uIStringIdentifier, context.getString(i));
        return this;
    }
}
