package org.telegram.messenger;

import android.os.AsyncTask;
import android.util.Log;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import org.telegram.messenger.config.ProxyManager;

public class ProxyService extends JobService {

    class C51311 extends AsyncTask {
        C51311() {
        }

        protected Object doInBackground(Object[] params) {
            ProxyManager.getInstance().checkProxy();
            return null;
        }
    }

    public boolean onStartJob(JobParameters job) {
        new C51311().execute(new Object[0]);
        Log.d("ProxyService", "onStart");
        return false;
    }

    public boolean onStopJob(JobParameters job) {
        Log.d("ProxyService", "onStop");
        return true;
    }
}
