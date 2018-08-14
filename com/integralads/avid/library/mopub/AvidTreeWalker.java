package com.integralads.avid.library.mopub;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import com.integralads.avid.library.mopub.processing.AvidProcessorFactory;
import com.integralads.avid.library.mopub.processing.IAvidNodeProcessor;
import com.integralads.avid.library.mopub.processing.IAvidNodeProcessor.IAvidViewWalker;
import com.integralads.avid.library.mopub.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.integralads.avid.library.mopub.utils.AvidTimestamp;
import com.integralads.avid.library.mopub.utils.AvidViewUtil;
import com.integralads.avid.library.mopub.walking.AvidAdViewCache;
import com.integralads.avid.library.mopub.walking.AvidStatePublisher;
import com.integralads.avid.library.mopub.walking.ViewType;
import com.integralads.avid.library.mopub.walking.async.AvidAsyncTaskQueue;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class AvidTreeWalker implements IAvidViewWalker {
    private static final int UPDATE_TREE_BY_SCHEDULE_PERIOD = 200;
    private static AvidTreeWalker avidTreeWalker = new AvidTreeWalker();
    private static TreeWalkerHandler handler;
    private static final Runnable viewTreeUpdaterRunnable = new C33431();
    private AvidAdViewCache adViewCache = new AvidAdViewCache(AvidAdSessionRegistry.getInstance());
    private double endTime;
    private int objectsCount;
    private AvidProcessorFactory processorFactory = new AvidProcessorFactory();
    private double startTime;
    private AvidStatePublisher statePublisher = new AvidStatePublisher(AvidAdSessionRegistry.getInstance(), new AvidAsyncTaskQueue());
    private List<AvidTreeWalkerTimeLogger> timeLoggers = new ArrayList();

    static class C33431 implements Runnable {
        C33431() {
        }

        public void run() {
            if (AvidTreeWalker.handler != null) {
                AvidTreeWalker.handler.sendEmptyMessage(0);
                AvidTreeWalker.handler.postDelayed(AvidTreeWalker.viewTreeUpdaterRunnable, 200);
            }
        }
    }

    public interface AvidTreeWalkerTimeLogger {
        void onTreeProcessed(int i, long j);
    }

    private static class TreeWalkerHandler extends Handler {
        private TreeWalkerHandler() {
        }

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            AvidTreeWalker.getInstance().updateTreeState();
        }
    }

    public static AvidTreeWalker getInstance() {
        return avidTreeWalker;
    }

    public void addTimeLogger(AvidTreeWalkerTimeLogger timeLogger) {
        if (!this.timeLoggers.contains(timeLogger)) {
            this.timeLoggers.add(timeLogger);
        }
    }

    public void removeTimeLogger(AvidTreeWalkerTimeLogger timeLogger) {
        if (this.timeLoggers.contains(timeLogger)) {
            this.timeLoggers.remove(timeLogger);
        }
    }

    public void start() {
        startTreeWalkerUpdater();
        updateTreeState();
    }

    public void stop() {
        pause();
        this.timeLoggers.clear();
        this.statePublisher.cleanupCache();
    }

    public void pause() {
        stopTreeWalkerUpdater();
    }

    private void updateTreeState() {
        beforeWalk();
        doWalk();
        afterWalk();
    }

    private void beforeWalk() {
        this.objectsCount = 0;
        this.startTime = AvidTimestamp.getCurrentTime();
    }

    private void afterWalk() {
        this.endTime = AvidTimestamp.getCurrentTime();
        notifyTimeLogger((long) (this.endTime - this.startTime));
    }

    @VisibleForTesting
    void doWalk() {
        this.adViewCache.prepare();
        double timestamp = AvidTimestamp.getCurrentTime();
        IAvidNodeProcessor processor = this.processorFactory.getRootProcessor();
        if (this.adViewCache.getHiddenSessionIds().size() > 0) {
            this.statePublisher.publishEmptyState(processor.getState(null), this.adViewCache.getHiddenSessionIds(), timestamp);
        }
        if (this.adViewCache.getVisibleSessionIds().size() > 0) {
            JSONObject state = processor.getState(null);
            walkViewChildren(null, processor, state, ViewType.ROOT_VIEW);
            AvidJSONUtil.fixStateFrame(state);
            this.statePublisher.publishState(state, this.adViewCache.getVisibleSessionIds(), timestamp);
        } else {
            this.statePublisher.cleanupCache();
        }
        this.adViewCache.cleanup();
    }

    public void walkView(View view, IAvidNodeProcessor processor, JSONObject parentState) {
        if (AvidViewUtil.isViewVisible(view)) {
            ViewType viewType = this.adViewCache.getViewType(view);
            if (viewType != ViewType.UNDERLYING_VIEW) {
                JSONObject state = processor.getState(view);
                AvidJSONUtil.addChildState(parentState, state);
                if (!handleAdView(view, state)) {
                    checkFriendlyObstruction(view, state);
                    walkViewChildren(view, processor, state, viewType);
                }
                this.objectsCount++;
            }
        }
    }

    private void walkViewChildren(View view, IAvidNodeProcessor processor, JSONObject state, ViewType viewType) {
        processor.iterateChildren(view, state, this, viewType == ViewType.ROOT_VIEW);
    }

    private boolean handleAdView(View view, JSONObject viewStateObject) {
        String sessionId = this.adViewCache.getSessionId(view);
        if (sessionId == null) {
            return false;
        }
        AvidJSONUtil.addAvidId(viewStateObject, sessionId);
        this.adViewCache.onAdViewProcessed();
        return true;
    }

    private void checkFriendlyObstruction(View view, JSONObject viewStateObject) {
        ArrayList<String> friendlySessionIds = this.adViewCache.getFriendlySessionIds(view);
        if (friendlySessionIds != null) {
            AvidJSONUtil.addFriendlyObstruction(viewStateObject, friendlySessionIds);
        }
    }

    private void notifyTimeLogger(long calculationTime) {
        if (this.timeLoggers.size() > 0) {
            for (AvidTreeWalkerTimeLogger timeLogger : this.timeLoggers) {
                timeLogger.onTreeProcessed(this.objectsCount, calculationTime);
            }
        }
    }

    private void startTreeWalkerUpdater() {
        if (handler == null) {
            handler = new TreeWalkerHandler();
            handler.postDelayed(viewTreeUpdaterRunnable, 200);
        }
    }

    private void stopTreeWalkerUpdater() {
        if (handler != null) {
            handler.removeCallbacks(viewTreeUpdaterRunnable);
            handler = null;
        }
    }

    @VisibleForTesting
    void setAdViewCache(AvidAdViewCache adViewCache) {
        this.adViewCache = adViewCache;
    }

    @VisibleForTesting
    void setStatePublisher(AvidStatePublisher statePublisher) {
        this.statePublisher = statePublisher;
    }

    @VisibleForTesting
    void setProcessorFactory(AvidProcessorFactory processorFactory) {
        this.processorFactory = processorFactory;
    }
}
