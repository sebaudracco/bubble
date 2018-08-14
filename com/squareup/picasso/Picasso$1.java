package com.squareup.picasso;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.List;

class Picasso$1 extends Handler {
    Picasso$1(Looper x0) {
        super(x0);
    }

    public void handleMessage(Message msg) {
        Action action;
        int n;
        int i;
        switch (msg.what) {
            case 3:
                action = msg.obj;
                if (action.getPicasso().loggingEnabled) {
                    Utils.log("Main", "canceled", action.request.logId(), "target got garbage collected");
                }
                Picasso.access$000(action.picasso, action.getTarget());
                return;
            case 8:
                List<BitmapHunter> batch = msg.obj;
                n = batch.size();
                for (i = 0; i < n; i++) {
                    BitmapHunter hunter = (BitmapHunter) batch.get(i);
                    hunter.picasso.complete(hunter);
                }
                return;
            case 13:
                List<Action> batch2 = msg.obj;
                n = batch2.size();
                for (i = 0; i < n; i++) {
                    action = (Action) batch2.get(i);
                    action.picasso.resumeAction(action);
                }
                return;
            default:
                throw new AssertionError("Unknown handler message received: " + msg.what);
        }
    }
}
