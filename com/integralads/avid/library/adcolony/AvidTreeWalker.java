package com.integralads.avid.library.adcolony;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import com.integralads.avid.library.adcolony.processing.AvidProcessorFactory;
import com.integralads.avid.library.adcolony.processing.IAvidNodeProcessor;
import com.integralads.avid.library.adcolony.processing.IAvidNodeProcessor.IAvidViewWalker;
import com.integralads.avid.library.adcolony.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import com.integralads.avid.library.adcolony.utils.AvidTimestamp;
import com.integralads.avid.library.adcolony.utils.AvidViewUtil;
import com.integralads.avid.library.adcolony.walking.AvidAdViewCache;
import com.integralads.avid.library.adcolony.walking.AvidStatePublisher;
import com.integralads.avid.library.adcolony.walking.ViewType;
import com.integralads.avid.library.adcolony.walking.async.AvidAsyncTaskQueue;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class AvidTreeWalker implements IAvidViewWalker {
    private static final int f8315a = 200;
    private static AvidTreeWalker f8316b = new AvidTreeWalker();
    private static C3284a f8317c;
    private static final Runnable f8318k = new C32831();
    private List<AvidTreeWalkerTimeLogger> f8319d = new ArrayList();
    private int f8320e;
    private AvidProcessorFactory f8321f = new AvidProcessorFactory();
    private AvidAdViewCache f8322g = new AvidAdViewCache(AvidAdSessionRegistry.getInstance());
    private AvidStatePublisher f8323h = new AvidStatePublisher(AvidAdSessionRegistry.getInstance(), new AvidAsyncTaskQueue());
    private double f8324i;
    private double f8325j;

    static class C32831 implements Runnable {
        C32831() {
        }

        public void run() {
            if (AvidTreeWalker.f8317c != null) {
                AvidTreeWalker.f8317c.sendEmptyMessage(0);
                AvidTreeWalker.f8317c.postDelayed(AvidTreeWalker.f8318k, 200);
            }
        }
    }

    public interface AvidTreeWalkerTimeLogger {
        void onTreeProcessed(int i, long j);
    }

    private static class C3284a extends Handler {
        private C3284a() {
        }

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            AvidTreeWalker.getInstance().m11120d();
        }
    }

    public static AvidTreeWalker getInstance() {
        return f8316b;
    }

    public void addTimeLogger(AvidTreeWalkerTimeLogger timeLogger) {
        if (!this.f8319d.contains(timeLogger)) {
            this.f8319d.add(timeLogger);
        }
    }

    public void removeTimeLogger(AvidTreeWalkerTimeLogger timeLogger) {
        if (this.f8319d.contains(timeLogger)) {
            this.f8319d.remove(timeLogger);
        }
    }

    public void start() {
        m11123g();
        m11120d();
    }

    public void stop() {
        pause();
        this.f8319d.clear();
        this.f8323h.cleanupCache();
    }

    public void pause() {
        m11124h();
    }

    private void m11120d() {
        m11121e();
        m11125a();
        m11122f();
    }

    private void m11121e() {
        this.f8320e = 0;
        this.f8324i = AvidTimestamp.getCurrentTime();
    }

    private void m11122f() {
        this.f8325j = AvidTimestamp.getCurrentTime();
        m11113a((long) (this.f8325j - this.f8324i));
    }

    @VisibleForTesting
    void m11125a() {
        this.f8322g.prepare();
        double currentTime = AvidTimestamp.getCurrentTime();
        IAvidNodeProcessor rootProcessor = this.f8321f.getRootProcessor();
        if (this.f8322g.getHiddenSessionIds().size() > 0) {
            this.f8323h.publishEmptyState(rootProcessor.getState(null), this.f8322g.getHiddenSessionIds(), currentTime);
        }
        if (this.f8322g.getVisibleSessionIds().size() > 0) {
            JSONObject state = rootProcessor.getState(null);
            m11114a(null, rootProcessor, state, ViewType.ROOT_VIEW);
            AvidJSONUtil.fixStateFrame(state);
            this.f8323h.publishState(state, this.f8322g.getVisibleSessionIds(), currentTime);
        } else {
            this.f8323h.cleanupCache();
        }
        this.f8322g.cleanup();
    }

    public void walkView(View view, IAvidNodeProcessor processor, JSONObject parentState) {
        if (AvidViewUtil.isViewVisible(view)) {
            ViewType viewType = this.f8322g.getViewType(view);
            if (viewType != ViewType.UNDERLYING_VIEW) {
                JSONObject state = processor.getState(view);
                AvidJSONUtil.addChildState(parentState, state);
                if (!m11116a(view, state)) {
                    m11118b(view, state);
                    m11114a(view, processor, state, viewType);
                }
                this.f8320e++;
            }
        }
    }

    private void m11114a(View view, IAvidNodeProcessor iAvidNodeProcessor, JSONObject jSONObject, ViewType viewType) {
        iAvidNodeProcessor.iterateChildren(view, jSONObject, this, viewType == ViewType.ROOT_VIEW);
    }

    private boolean m11116a(View view, JSONObject jSONObject) {
        String sessionId = this.f8322g.getSessionId(view);
        if (sessionId == null) {
            return false;
        }
        AvidJSONUtil.addAvidId(jSONObject, sessionId);
        this.f8322g.onAdViewProcessed();
        return true;
    }

    private void m11118b(View view, JSONObject jSONObject) {
        List friendlySessionIds = this.f8322g.getFriendlySessionIds(view);
        if (friendlySessionIds != null) {
            AvidJSONUtil.addFriendlyObstruction(jSONObject, friendlySessionIds);
        }
    }

    private void m11113a(long j) {
        if (this.f8319d.size() > 0) {
            for (AvidTreeWalkerTimeLogger onTreeProcessed : this.f8319d) {
                onTreeProcessed.onTreeProcessed(this.f8320e, j);
            }
        }
    }

    private void m11123g() {
        if (f8317c == null) {
            f8317c = new C3284a();
            f8317c.postDelayed(f8318k, 200);
        }
    }

    private void m11124h() {
        if (f8317c != null) {
            f8317c.removeCallbacks(f8318k);
            f8317c = null;
        }
    }

    @VisibleForTesting
    void m11127a(AvidAdViewCache avidAdViewCache) {
        this.f8322g = avidAdViewCache;
    }

    @VisibleForTesting
    void m11128a(AvidStatePublisher avidStatePublisher) {
        this.f8323h = avidStatePublisher;
    }

    @VisibleForTesting
    void m11126a(AvidProcessorFactory avidProcessorFactory) {
        this.f8321f = avidProcessorFactory;
    }
}
