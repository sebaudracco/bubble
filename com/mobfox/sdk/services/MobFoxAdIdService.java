package com.mobfox.sdk.services;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.mobfox.sdk.constants.Constants;

public class MobFoxAdIdService extends MobFoxBaseService {
    String ad_id = "";
    Context context;
    Listener listener;
    AsyncTask<Void, Void, String> task;

    public interface Listener {
        void onFinish(String str);
    }

    public MobFoxAdIdService(Listener listener, final Context context) {
        super(context);
        this.context = context;
        this.listener = listener;
        this.task = new AsyncTask<Void, Void, String>() {
            protected String doInBackground(Void... params) {
                try {
                    String advertId = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getDeclaredMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{context}).toString();
                    advertId = advertId.substring(advertId.indexOf("{") + 1);
                    return advertId.substring(0, advertId.indexOf("}"));
                } catch (Exception e) {
                    Log.d(Constants.MOBFOX_BANNER, "google play error");
                    return "";
                } catch (Throwable e2) {
                    if (e2.getMessage() != null) {
                        Log.d(Constants.MOBFOX_BANNER, "google play throwable " + e2.getMessage());
                        return "";
                    }
                    Log.d(Constants.MOBFOX_BANNER, "google play throwable");
                    return "";
                }
            }

            protected void onPostExecute(String advertId) {
                MobFoxAdIdService.this.setAd_id(advertId);
                MobFoxAdIdService.this.run();
            }
        };
    }

    public void execute() {
        this.task.execute(new Void[0]);
    }

    public void callback() {
        this.listener.onFinish(getAd_id());
    }

    public void setAd_id(String ad_id) {
        this.ad_id = ad_id;
    }

    public String getAd_id() {
        return this.ad_id;
    }
}
