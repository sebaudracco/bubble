package com.stepleaderdigital.reveal;

import android.content.Context;
import android.os.AsyncTask;
import com.stepleaderdigital.reveal.Reveal.Utils;

public class ModelSpecificDistanceUpdater extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "ModelSpecificDistanceUpdater";
    private CompletionHandler mCompletionHandler;
    private Context mContext;
    private DistanceConfigFetcher mDistanceConfigFetcher;

    interface CompletionHandler {
        void onComplete(String str, Exception exception, int i);
    }

    protected Void doInBackground(Void... params) {
        this.mDistanceConfigFetcher.request();
        if (this.mCompletionHandler != null) {
            this.mCompletionHandler.onComplete(this.mDistanceConfigFetcher.getResponseString(), this.mDistanceConfigFetcher.getException(), this.mDistanceConfigFetcher.getResponseCode());
        }
        return null;
    }

    public ModelSpecificDistanceUpdater(Context context, String urlString, CompletionHandler completionHandler) {
        this.mContext = context;
        this.mDistanceConfigFetcher = new DistanceConfigFetcher(urlString, getUserAgentString());
        this.mCompletionHandler = completionHandler;
    }

    private String getUserAgentString() {
        return "Android Beacon Library;" + getVersion() + ";" + getPackage() + ";" + getInstallId() + ";" + getModel();
    }

    private String getPackage() {
        return this.mContext.getPackageName();
    }

    private String getModel() {
        return AndroidModel.forThisDevice().toString();
    }

    private String getInstallId() {
        return Utils.getDeviceId(this.mContext);
    }

    private String getVersion() {
        return BuildConfig.VERSION_NAME;
    }
}
