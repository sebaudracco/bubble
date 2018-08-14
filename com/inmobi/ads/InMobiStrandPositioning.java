package com.inmobi.ads;

import android.support.annotation.NonNull;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class InMobiStrandPositioning {
    private static final String TAG = InMobiStrandPositioning.class.getSimpleName();

    public static class InMobiClientPositioning {
        public static final int NO_REPEAT = Integer.MAX_VALUE;
        private static final String TAG = InMobiClientPositioning.class.getSimpleName();
        private List<Integer> mAdPositions = new ArrayList();
        private int mRepeatInterval = Integer.MAX_VALUE;

        @NonNull
        public InMobiClientPositioning addFixedPosition(int i) {
            if (i < 0) {
                Logger.m10359a(InternalLogLevel.ERROR, TAG, "Ad positions must be non-negative");
            } else {
                int binarySearch = Collections.binarySearch(this.mAdPositions, Integer.valueOf(i));
                if (binarySearch < 0) {
                    this.mAdPositions.add(binarySearch ^ -1, Integer.valueOf(i));
                }
            }
            return this;
        }

        @NonNull
        List<Integer> getFixedPositions() {
            return this.mAdPositions;
        }

        @NonNull
        public InMobiClientPositioning enableRepeatingPositions(int i) {
            if (i <= 1) {
                Logger.m10359a(InternalLogLevel.ERROR, TAG, "Repeating interval must be greater than 1");
                this.mRepeatInterval = Integer.MAX_VALUE;
            } else {
                this.mRepeatInterval = i;
            }
            return this;
        }

        int getRepeatingInterval() {
            return this.mRepeatInterval;
        }
    }
}
