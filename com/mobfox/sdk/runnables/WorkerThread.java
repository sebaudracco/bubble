package com.mobfox.sdk.runnables;

import android.content.Context;
import android.util.Log;
import com.loopj.android.http.RequestParams;
import com.mobfox.sdk.networking.AsyncCallback;
import com.mobfox.sdk.networking.MobFoxRequest;
import com.mobfox.sdk.services.MobFoxAdIdService;
import com.mobfox.sdk.services.MobFoxAdIdService.Listener;
import java.util.List;
import java.util.Map;

public class WorkerThread extends Thread {
    private static final int INTERVAL_TIME = 20000;
    private static final String NAME = "UAM";
    private static long mLastSentTime = 0;
    private static long mTimeInterval = 0;
    private Context mContext;
    private MobFoxUASRunnable updateTask;

    class C35941 implements Listener {
        C35941() {
        }

        public void onFinish(String o_andadvid) {
            if (o_andadvid == null) {
                Log.d("", "getAdvIdTask onPostExecute error");
            } else if (WorkerThread.this.updateTask != null) {
                WorkerThread.this.updateTask.setAndAdvId(o_andadvid);
            }
        }
    }

    class C35952 implements AsyncCallback {
        C35952() {
        }

        public void onComplete(int code, Object response, Map<String, List<String>> map) {
            if (WorkerThread.this.updateTask != null) {
                WorkerThread.this.updateTask.bundleSentComplete();
            }
            WorkerThread.this.incTime();
        }

        public void onError(Exception e) {
            Log.e("Error", "In sending update to server");
        }
    }

    public WorkerThread(Context c, long timeInterval) {
        this.mContext = c;
        getAndAdvId();
        this.updateTask = new MobFoxUASRunnable(c);
        mTimeInterval = timeInterval;
        mLastSentTime = System.currentTimeMillis();
    }

    public void run() {
        setName(NAME);
        while (!isInterrupted()) {
            if (isTimeToSend()) {
                sendToServer();
            } else {
                this.updateTask.run();
            }
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                Log.e("Error", e.getLocalizedMessage());
            }
        }
    }

    public void startThread() {
        start();
    }

    public void interruptThread() {
        interrupt();
    }

    private void getAndAdvId() {
        new MobFoxAdIdService(new C35941(), this.mContext).execute();
    }

    private void sendToServer() {
        Object u = this.updateTask.getBundle();
        if (u == null || (u.toString() != null && u.toString().equals(""))) {
            incTime();
            return;
        }
        MobFoxRequest req = new MobFoxRequest(this.mContext, "https://devices.starbolt.io/");
        req.setTestMode(true);
        req.setData(u);
        req.setHeader("Content-type", RequestParams.APPLICATION_JSON);
        req.post(new C35952());
        incTime();
    }

    private boolean isTimeToSend() {
        return mLastSentTime + mTimeInterval < System.currentTimeMillis();
    }

    private void incTime() {
        mLastSentTime = System.currentTimeMillis();
    }
}
