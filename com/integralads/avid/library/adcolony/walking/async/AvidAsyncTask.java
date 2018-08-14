package com.integralads.avid.library.adcolony.walking.async;

import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.support.annotation.VisibleForTesting;
import java.util.concurrent.ThreadPoolExecutor;
import org.json.JSONObject;

public abstract class AvidAsyncTask extends AsyncTask<Object, Void, String> {
    private AvidAsyncTaskListener f8413a;
    protected final StateProvider stateProvider;

    public interface StateProvider {
        JSONObject getPreviousState();

        void setPreviousState(JSONObject jSONObject);
    }

    public interface AvidAsyncTaskListener {
        void onTaskCompleted(AvidAsyncTask avidAsyncTask);
    }

    public AvidAsyncTask(StateProvider stateProvider) {
        this.stateProvider = stateProvider;
    }

    public void setListener(AvidAsyncTaskListener listener) {
        this.f8413a = listener;
    }

    public AvidAsyncTaskListener getListener() {
        return this.f8413a;
    }

    public StateProvider getStateProvider() {
        return this.stateProvider;
    }

    public void start(ThreadPoolExecutor executor) {
        if (VERSION.SDK_INT > 11) {
            executeOnExecutor(executor, new Object[0]);
        } else {
            execute(new Object[0]);
        }
    }

    protected void onPostExecute(String result) {
        if (this.f8413a != null) {
            this.f8413a.onTaskCompleted(this);
        }
    }

    @VisibleForTesting
    String m11173a() {
        return (String) doInBackground(new Object[0]);
    }

    @VisibleForTesting
    void m11174a(String str) {
        onPostExecute(str);
    }
}
