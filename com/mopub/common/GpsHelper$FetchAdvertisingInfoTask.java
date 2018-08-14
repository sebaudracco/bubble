package com.mopub.common;

import android.content.Context;
import android.os.AsyncTask;
import com.mopub.common.factories.MethodBuilderFactory;
import com.mopub.common.logging.MoPubLog;
import java.lang.ref.WeakReference;

class GpsHelper$FetchAdvertisingInfoTask extends AsyncTask<Void, Void, Void> {
    private WeakReference<Context> mContextWeakReference;
    private WeakReference<GpsHelper$GpsHelperListener> mGpsHelperListenerWeakReference;

    public GpsHelper$FetchAdvertisingInfoTask(Context context, GpsHelper$GpsHelperListener gpsHelperListener) {
        this.mContextWeakReference = new WeakReference(context);
        this.mGpsHelperListenerWeakReference = new WeakReference(gpsHelperListener);
    }

    protected Void doInBackground(Void... voids) {
        try {
            Object context = (Context) this.mContextWeakReference.get();
            if (context != null) {
                Object adInfo = MethodBuilderFactory.create(null, "getAdvertisingIdInfo").setStatic(Class.forName(GpsHelper.access$000())).addParam(Context.class, context).execute();
                if (adInfo != null) {
                    GpsHelper.updateClientMetadata(context, adInfo);
                }
            }
        } catch (Exception e) {
            MoPubLog.m12061d("Unable to obtain Google AdvertisingIdClient.Info via reflection.");
        }
        return null;
    }

    protected void onPostExecute(Void aVoid) {
        GpsHelper$GpsHelperListener gpsHelperListener = (GpsHelper$GpsHelperListener) this.mGpsHelperListenerWeakReference.get();
        if (gpsHelperListener != null) {
            gpsHelperListener.onFetchAdInfoCompleted();
        }
    }
}
