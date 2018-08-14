package com.core42matters.android.registrar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import java.io.IOException;

public final class Registrar {
    private Registrar() {
        throw new AssertionError();
    }

    public static void init(Activity activity) throws AppIdMissingException {
        init(activity, new AppId(activity));
    }

    public static void init(Activity activity, String appId) {
        init(activity, new AppId((Context) activity, appId));
    }

    public static void setConsent(Activity activity, boolean given) {
        PreferenceManager.getDefaultSharedPreferences(activity).edit().putBoolean("com.core42matters.android.registrar.user_consent", given).commit();
    }

    private static void init(Activity activity, AppId appId) {
        if (activity == null) {
            throw new NullPointerException("No activity passed");
        }
        TaskQueue.scheduleSettingsUpdate(activity, appId);
        switch (getConsentStatus(activity)) {
            case GIVEN:
                Logger.m3321v("consent is given");
                TaskQueue.scheduleRegistration(activity, appId);
                return;
            case DENIED:
                Logger.m3321v("consent is denied");
                return;
            case NOT_SET:
                Logger.m3321v("consent is not set");
                ConsentClickListener listener = new ConsentClickListener(activity, appId);
                Builder builder = new Builder(activity);
                builder.setCancelable(false).setTitle("Personalize Your Experience").setMessage(Html.fromHtml("This app helps personalize your future advertising experience through 42matters AG, a Swiss company. By consenting to this enhanced ad experience, you will agree to share some of your personal data. Depending on your privacy settings, 42matters and its partners may collect and process personal data such as your device identifier and your list of installed apps (no in-app content is collected). <a href=\"https://42matters.com/privacy\">Learn more</a>.\n\n<br/><br/>By agreeing, you are confirming that you are over the age of 18 and would like a personalized ad experience and contribute to market statistics."));
                builder.setPositiveButton("Agree", listener).setNegativeButton("No", listener);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                ((TextView) alertDialog.findViewById(16908299)).setMovementMethod(LinkMovementMethod.getInstance());
                return;
            default:
                return;
        }
    }

    public static boolean putCustomVariablesSync(Context context, Bundle variables) throws AppIdMissingException, IOException {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            throw new IllegalStateException("Should not be called on UI thread");
        } else if (context == null || variables == null) {
            throw new NullPointerException("No context or variables");
        } else {
            variables.putString("udid__", IdUtils.getUDID(context));
            variables.putLong("timestamp_mili__", System.currentTimeMillis());
            try {
                if (HttpUtils.requestString(context, new AppId(context), "https://profiler.42matters.com/1/custom_variable", "PUT", variables) != null) {
                    return true;
                }
                return false;
            } catch (InvalidApiCallException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    static ConsentStatus getConsentStatus(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            if (!sharedPreferences.contains("com.core42matters.android.registrar.user_consent")) {
                return ConsentStatus.NOT_SET;
            }
            if (sharedPreferences.getBoolean("com.core42matters.android.registrar.user_consent", false)) {
                return ConsentStatus.GIVEN;
            }
            return ConsentStatus.DENIED;
        } catch (Exception e) {
            return ConsentStatus.DENIED;
        }
    }
}
