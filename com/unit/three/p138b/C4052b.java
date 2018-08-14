package com.unit.three.p138b;

import com.unit.three.p138b.C4064i.C4062b;
import com.unit.three.p138b.C4067j.C4066b;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

public final class C4052b implements Runnable {
    private LinkedBlockingQueue f9361a;
    private C4066b f9362b;
    private HashMap f9363c;

    public C4052b(HashMap hashMap, LinkedBlockingQueue linkedBlockingQueue, C4066b c4066b) {
        this.f9363c = hashMap;
        this.f9361a = linkedBlockingQueue;
        this.f9362b = c4066b;
    }

    public final void run() {
        while (true) {
            C4064i c4064i = null;
            do {
                try {
                    c4064i = (C4064i) this.f9361a.take();
                    continue;
                } catch (Exception e) {
                    continue;
                }
            } while (c4064i == null);
            InetAddress inetAddress = c4064i.f9407a.f9390k;
            C4062b c4062b = c4064i.f9408b;
            if (c4062b != null) {
                int i = c4062b.f9393b;
                String str = inetAddress.getHostAddress() + ":" + i + ":" + c4062b.f9392a;
                LinkedBlockingQueue linkedBlockingQueue = (LinkedBlockingQueue) this.f9363c.get(str);
                if (linkedBlockingQueue == null) {
                    linkedBlockingQueue = new LinkedBlockingQueue();
                    this.f9363c.put(str, linkedBlockingQueue);
                    linkedBlockingQueue.offer(c4064i);
                    this.f9362b.mo6917a(c4064i);
                } else {
                    linkedBlockingQueue.offer(c4064i);
                }
            }
        }
    }
}
