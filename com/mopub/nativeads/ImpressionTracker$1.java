package com.mopub.nativeads;

import android.support.annotation.NonNull;
import android.view.View;
import java.util.List;

class ImpressionTracker$1 implements VisibilityTrackerListener {
    final /* synthetic */ ImpressionTracker this$0;

    ImpressionTracker$1(ImpressionTracker this$0) {
        this.this$0 = this$0;
    }

    public void onVisibilityChanged(@NonNull List<View> visibleViews, @NonNull List<View> invisibleViews) {
        for (View view : visibleViews) {
            ImpressionInterface impressionInterface = (ImpressionInterface) ImpressionTracker.access$000(this.this$0).get(view);
            if (impressionInterface == null) {
                this.this$0.removeView(view);
            } else {
                TimestampWrapper<ImpressionInterface> polling = (TimestampWrapper) ImpressionTracker.access$100(this.this$0).get(view);
                if (polling == null || !impressionInterface.equals(polling.mInstance)) {
                    ImpressionTracker.access$100(this.this$0).put(view, new TimestampWrapper(impressionInterface));
                }
            }
        }
        for (View view2 : invisibleViews) {
            ImpressionTracker.access$100(this.this$0).remove(view2);
        }
        this.this$0.scheduleNextPoll();
    }
}
