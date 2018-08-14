package com.cuebiq.cuebiqsdk.model.wrapper;

import android.content.Context;
import com.cuebiq.cuebiqsdk.CuebiqSDKImpl;
import com.cuebiq.cuebiqsdk.model.persistence.PersistenceManagerFactory;

public class Auth {
    private String anonymousID;
    private String appKey;
    private String appPackageName;
    private String appVersion;
    private String customPublisherID;
    private String googleAdvertiserID;

    public static Auth build(Context context) {
        Auth auth = new Auth();
        try {
            auth.setAppPackageName(context.getPackageName());
            auth.setAppVersion(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName);
            auth.setAppKey(PersistenceManagerFactory.get().retrieveAppKey(context));
            auth.setCustomPublisherID(PersistenceManagerFactory.get().getCustomPublisherID(context));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return auth;
    }

    public String getAnonymousID() {
        return this.anonymousID;
    }

    public String getAppKey() {
        return this.appKey;
    }

    public String getAppPackageName() {
        return this.appPackageName;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public String getCustomPublisherID() {
        return this.customPublisherID;
    }

    public String getGoogleAdvertiserID() {
        return this.googleAdvertiserID;
    }

    public void setAnonymousID(String str) {
        this.anonymousID = str;
    }

    public void setAppKey(String str) {
        this.appKey = str;
    }

    public void setAppPackageName(String str) {
        this.appPackageName = str;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public void setCustomPublisherID(String str) {
        this.customPublisherID = str;
    }

    public void setGoogleAdvertiserID(String str) {
        this.googleAdvertiserID = str;
    }

    public String toString() {
        return CuebiqSDKImpl.GSON.toJson((Object) this);
    }
}
