package com.squareup.picasso;

import android.os.Handler;
import android.os.Message;
import android.os.Process;
import java.lang.ref.ReferenceQueue;

class Picasso$CleanupThread extends Thread {
    private final Handler handler;
    private final ReferenceQueue<Object> referenceQueue;

    Picasso$CleanupThread(ReferenceQueue<Object> referenceQueue, Handler handler) {
        this.referenceQueue = referenceQueue;
        this.handler = handler;
        setDaemon(true);
        setName("Picasso-refQueue");
    }

    public void run() {
        Process.setThreadPriority(10);
        while (true) {
            try {
                RequestWeakReference<?> remove = (RequestWeakReference) this.referenceQueue.remove(1000);
                Message message = this.handler.obtainMessage();
                if (remove != null) {
                    message.what = 3;
                    message.obj = remove.action;
                    this.handler.sendMessage(message);
                } else {
                    message.recycle();
                }
            } catch (InterruptedException e) {
                return;
            } catch (final Exception e2) {
                this.handler.post(new Runnable() {
                    public void run() {
                        throw new RuntimeException(e2);
                    }
                });
                return;
            }
        }
    }

    void shutdown() {
        interrupt();
    }
}
