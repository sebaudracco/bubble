package com.adcolony.sdk;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import com.adcolony.sdk.aa.C0595a;
import com.google.android.gms.plus.PlusShare;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import com.google.firebase.analytics.FirebaseAnalytics.Event;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.TimeZone;
import org.json.JSONObject;

public class AdColonyEventTracker {
    public static final String CUSTOM_EVENT_1 = "ADCT_CUSTOM_EVENT_1";
    public static final String CUSTOM_EVENT_2 = "ADCT_CUSTOM_EVENT_2";
    public static final String CUSTOM_EVENT_3 = "ADCT_CUSTOM_EVENT_3";
    public static final String CUSTOM_EVENT_4 = "ADCT_CUSTOM_EVENT_4";
    public static final String CUSTOM_EVENT_5 = "ADCT_CUSTOM_EVENT_5";
    public static final String LOGIN_DEFAULT = "ADCT_DEFAULT_LOGIN";
    public static final String LOGIN_FACEBOOK = "ADCT_FACEBOOK_LOGIN";
    public static final String LOGIN_GOOGLE = "ADCT_GOOGLE_LOGIN";
    public static final String LOGIN_LINKEDIN = "ADCT_LINKEDIN_LOGIN";
    public static final String LOGIN_OPENID = "ADCT_OPENID_LOGIN";
    public static final String LOGIN_TWITTER = "ADCT_TWITTER_LOGIN";
    public static final String REGISTRATION_CUSTOM = "ADCT_CUSTOM_REGISTRATION";
    public static final String REGISTRATION_DEFAULT = "ADCT_DEFAULT_REGISTRATION";
    public static final String REGISTRATION_FACEBOOK = "ADCT_FACEBOOK_REGISTRATION";
    public static final String REGISTRATION_GOOGLE = "ADCT_GOOGLE_REGISTRATION";
    public static final String REGISTRATION_LINKEDIN = "ADCT_LINKEDIN_REGISTRATION";
    public static final String REGISTRATION_OPENID = "ADCT_OPENID_REGISTRATION";
    public static final String REGISTRATION_TWITTER = "ADCT_TWITTER_REGISTRATION";
    public static final String SOCIAL_SHARING_CUSTOM = "ADCT_CUSTOM_SHARING";
    public static final String SOCIAL_SHARING_FACEBOOK = "ADCT_FACEBOOK_SHARING";
    public static final String SOCIAL_SHARING_FLICKR = "ADCT_FLICKR_SHARING";
    public static final String SOCIAL_SHARING_FOURSQUARE = "ADCT_FOURSQUARE_SHARING";
    public static final String SOCIAL_SHARING_GOOGLE = "ADCT_GOOGLE_SHARING";
    public static final String SOCIAL_SHARING_INSTAGRAM = "ADCT_INSTAGRAM_SHARING";
    public static final String SOCIAL_SHARING_LINKEDIN = "ADCT_LINKEDIN_SHARING";
    public static final String SOCIAL_SHARING_PINTEREST = "ADCT_PINTEREST_SHARING";
    public static final String SOCIAL_SHARING_SNAPCHAT = "ADCT_SNAPCHAT_SHARING";
    public static final String SOCIAL_SHARING_TUMBLR = "ADCT_TUMBLR_SHARING";
    public static final String SOCIAL_SHARING_TWITTER = "ADCT_TWITTER_SHARING";
    public static final String SOCIAL_SHARING_VIMEO = "ADCT_VIMEO_SHARING";
    public static final String SOCIAL_SHARING_VINE = "ADCT_VINE_SHARING";
    public static final String SOCIAL_SHARING_YOUTUBE = "ADCT_YOUTUBE_SHARING";
    private static final List<JSONObject> f399a = Collections.synchronizedList(new ArrayList());
    private static final int f400b = 200;

    static void m557a(JSONObject jSONObject) {
        synchronized (f399a) {
            if (200 > f399a.size()) {
                f399a.add(jSONObject);
            }
        }
    }

    static void m556a() {
        if (!C0740l.m1211C().equals("")) {
            synchronized (f399a) {
                for (JSONObject EventTracker__logEvent : f399a) {
                    ADCNative.EventTracker__logEvent(EventTracker__logEvent);
                }
                f399a.clear();
            }
        }
    }

    static boolean m560b() {
        boolean z;
        synchronized (f399a) {
            z = f399a.size() != 0;
        }
        return z;
    }

    public static void logTransaction(@Nullable String itemId, @Nullable Integer quantity, @Nullable Double price, @Nullable String currencyCode, @Nullable String receipt, @Nullable String store, @Nullable String description) {
        if (!m558a(description, "logTransaction")) {
            if (currencyCode == null || currencyCode.length() == 3) {
                HashMap hashMap = new HashMap();
                hashMap.put(FirebaseAnalytics$Param.ITEM_ID, itemId);
                hashMap.put(FirebaseAnalytics$Param.QUANTITY, String.valueOf(quantity));
                hashMap.put(FirebaseAnalytics$Param.PRICE, String.valueOf(price));
                hashMap.put("currency_code", currencyCode);
                hashMap.put("receipt", receipt);
                hashMap.put("store", store);
                hashMap.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, description);
                logEvent("transaction", hashMap);
                return;
            }
            new C0595a().m622a("Event logCreditsSpentWithName currency code is specified, but a three-letter ISO 4217 code, (e.g.: 'USD'). Event will not be sent.").m624a(aa.f483g);
        }
    }

    public static void logCreditsSpent(@Nullable String name, @Nullable Integer quantity, @Nullable Double value, @Nullable String currencyCode) {
        if (currencyCode == null || currencyCode.length() == 3) {
            HashMap hashMap = new HashMap();
            hashMap.put("name", name);
            hashMap.put(FirebaseAnalytics$Param.QUANTITY, String.valueOf(quantity));
            hashMap.put(FirebaseAnalytics$Param.VALUE, String.valueOf(value));
            hashMap.put("currency_code", currencyCode);
            logEvent("credits_spent", hashMap);
            return;
        }
        new C0595a().m622a("Event logCreditsSpentWithName currency code is specified, but a three-letter ISO 4217 code, (e.g.: 'USD'). Event will not be sent.").m624a(aa.f483g);
    }

    public static void logPaymentInfoAdded() {
        logEvent("payment_info_added");
    }

    public static void logAchievementUnlocked(@Nullable String description) {
        if (!m558a(description, "logAchievementUnlocked")) {
            HashMap hashMap = new HashMap();
            hashMap.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, description);
            logEvent("achievement_unlocked", hashMap);
        }
    }

    public static void logLevelAchieved(@Nullable Integer levelAchieved) {
        HashMap hashMap = new HashMap();
        hashMap.put("level_achieved", String.valueOf(levelAchieved));
        logEvent("level_achieved", hashMap);
    }

    public static void logAppRated() {
        logEvent("app_rated");
    }

    public static void logActivated() {
        logEvent("activated");
    }

    public static void logTutorialCompleted() {
        logEvent("tutorial_completed");
    }

    public static void logSocialSharingEvent(@Nullable String network, @Nullable String description) {
        if (!m558a(description, "logSocialSharingEvent")) {
            HashMap hashMap = new HashMap();
            hashMap.put("network", network);
            hashMap.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, description);
            logEvent("social_sharing_event", hashMap);
        }
    }

    public static void logRegistrationCompleted(@Nullable String method, @Nullable String description) {
        if (!m558a(description, "logRegistrationCompleted")) {
            HashMap hashMap = new HashMap();
            hashMap.put("method", method);
            hashMap.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, description);
            logEvent("registration_completed", hashMap);
        }
    }

    public static void logCustomEvent(@Nullable String event, @Nullable String description) {
        if (!m558a(description, "logCustomEvent")) {
            HashMap hashMap = new HashMap();
            hashMap.put(NotificationCompat.CATEGORY_EVENT, event);
            hashMap.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, description);
            logEvent("custom_event", hashMap);
        }
    }

    public static void logAddToCart(@Nullable String itemId) {
        HashMap hashMap = new HashMap();
        hashMap.put(FirebaseAnalytics$Param.ITEM_ID, itemId);
        logEvent(Event.ADD_TO_CART, hashMap);
    }

    public static void logAddToWishlist(@Nullable String itemId) {
        HashMap hashMap = new HashMap();
        hashMap.put(FirebaseAnalytics$Param.ITEM_ID, itemId);
        logEvent(Event.ADD_TO_WISHLIST, hashMap);
    }

    public static void logCheckoutInitiated() {
        logEvent("checkout_initiated");
    }

    public static void logContentView(@Nullable String contentId, @Nullable String contentType) {
        HashMap hashMap = new HashMap();
        hashMap.put("content_id", contentId);
        hashMap.put(FirebaseAnalytics$Param.CONTENT_TYPE, contentType);
        logEvent("content_view", hashMap);
    }

    public static void logInvite() {
        logEvent("invite");
    }

    public static void logLogin(@Nullable String method) {
        HashMap hashMap = new HashMap();
        hashMap.put("method", method);
        logEvent(Event.LOGIN, hashMap);
    }

    public static void logReservation() {
        logEvent("reservation");
    }

    public static void logSearch(@Nullable String searchString) {
        if (searchString == null || searchString.length() <= 512) {
            HashMap hashMap = new HashMap();
            hashMap.put("search_string", searchString);
            logEvent(Event.SEARCH, hashMap);
            return;
        }
        new C0595a().m622a("logSearch searchString cannot exceed 512 characters. Event will not be sent.").m624a(aa.f483g);
    }

    public static void logEvent(@Nullable String eventName) {
        logEvent(eventName, null);
    }

    public static void logEvent(@NonNull String eventName, @Nullable HashMap<String, String> payload) {
        JSONObject a = C0802y.m1453a();
        C0802y.m1462a(a, "event_name", eventName);
        JSONObject a2 = C0802y.m1453a();
        if (payload != null) {
            for (Entry entry : payload.entrySet()) {
                if (!(entry.getValue() == null || ((String) entry.getValue()).equals("null"))) {
                    C0802y.m1462a(a2, (String) entry.getKey(), (String) entry.getValue());
                }
            }
        }
        m559b(a2);
        C0802y.m1464a(a, "payload", a2);
        ADCNative.EventTracker__logEvent(a);
    }

    private static void m559b(JSONObject jSONObject) {
        C0802y.m1462a(jSONObject, "timezone", TimeZone.getDefault().getID());
        C0802y.m1462a(jSONObject, "action_time", String.valueOf(Math.round((float) (System.currentTimeMillis() / 1000))));
    }

    private static boolean m558a(String str, String str2) {
        if (str == null || str.length() <= 512) {
            return false;
        }
        new C0595a().m622a("Description of event ").m622a(str2).m622a(" must be less than 512 characters").m624a(aa.f483g);
        return true;
    }
}
