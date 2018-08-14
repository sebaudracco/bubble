package com.mopub.common.event;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;

public class EventDispatcher {
    private final Iterable<EventRecorder> mEventRecorders;
    private final Callback mHandlerCallback = new C36371();
    private final Looper mLooper;
    private final Handler mMessageHandler = new Handler(this.mLooper, this.mHandlerCallback);

    class C36371 implements Callback {
        C36371() {
        }

        public boolean handleMessage(Message msg) {
            if (msg.obj instanceof BaseEvent) {
                for (EventRecorder recorder : EventDispatcher.this.mEventRecorders) {
                    recorder.record((BaseEvent) msg.obj);
                }
            } else {
                MoPubLog.m12061d("EventDispatcher received non-BaseEvent message type.");
            }
            return true;
        }
    }

    @VisibleForTesting
    EventDispatcher(Iterable<EventRecorder> recorders, Looper looper) {
        this.mEventRecorders = recorders;
        this.mLooper = looper;
    }

    public void dispatch(BaseEvent event) {
        Message.obtain(this.mMessageHandler, 0, event).sendToTarget();
    }

    @VisibleForTesting
    Iterable<EventRecorder> getEventRecorders() {
        return this.mEventRecorders;
    }

    @VisibleForTesting
    Callback getHandlerCallback() {
        return this.mHandlerCallback;
    }
}
