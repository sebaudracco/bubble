package com.mopub.common.event;

import android.os.HandlerThread;
import com.mopub.common.VisibleForTesting;
import java.util.ArrayList;

public class MoPubEvents {
    private static volatile EventDispatcher sEventDispatcher;

    public static void log(BaseEvent baseEvent) {
        getDispatcher().dispatch(baseEvent);
    }

    @VisibleForTesting
    public static void setEventDispatcher(EventDispatcher dispatcher) {
        sEventDispatcher = dispatcher;
    }

    @VisibleForTesting
    static EventDispatcher getDispatcher() {
        EventDispatcher result = sEventDispatcher;
        if (result == null) {
            synchronized (MoPubEvents.class) {
                result = sEventDispatcher;
                if (result == null) {
                    ArrayList<EventRecorder> recorders = new ArrayList();
                    HandlerThread handlerThread = new HandlerThread("mopub_event_logging");
                    handlerThread.start();
                    recorders.add(new ScribeEventRecorder(handlerThread.getLooper()));
                    EventDispatcher result2 = new EventDispatcher(recorders, handlerThread.getLooper());
                    sEventDispatcher = result2;
                    result = result2;
                }
            }
        }
        return result;
    }
}
