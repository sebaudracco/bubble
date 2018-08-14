package org.altbeacon.beacon.distance;

import android.content.Context;
import android.os.AsyncTask;
import android.provider.Settings.Secure;
import org.altbeacon.beacon.BuildConfig;

public class ModelSpecificDistanceUpdater extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "ModelSpecificDistanceUpdater";
    private Exception exception = null;
    private CompletionHandler mCompletionHandler;
    private Context mContext;
    private DistanceConfigFetcher mDistanceConfigFetcher;
    private String response = null;
    private String urlString = null;

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

    protected void onPostExecute() {
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
        return Secure.getString(this.mContext.getContentResolver(), "android_id");
    }

    private String getVersion() {
        return BuildConfig.VERSION_NAME;
    }
}
