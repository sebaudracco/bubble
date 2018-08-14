package com.mopub.nativeads;

import android.support.annotation.NonNull;
import com.mopub.common.Preconditions;
import com.mopub.common.Preconditions.NoThrow;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MoPubNativeAdPositioning {

    public static class MoPubClientPositioning {
        public static final int NO_REPEAT = Integer.MAX_VALUE;
        @NonNull
        private final ArrayList<Integer> mFixedPositions = new ArrayList();
        private int mRepeatInterval = Integer.MAX_VALUE;

        @NonNull
        public MoPubClientPositioning addFixedPosition(int position) {
            if (NoThrow.checkArgument(position >= 0)) {
                int index = Collections.binarySearch(this.mFixedPositions, Integer.valueOf(position));
                if (index < 0) {
                    this.mFixedPositions.add(index ^ -1, Integer.valueOf(position));
                }
            }
            return this;
        }

        @NonNull
        List<Integer> getFixedPositions() {
            return this.mFixedPositions;
        }

        @NonNull
        public MoPubClientPositioning enableRepeatingPositions(int interval) {
            boolean z = true;
            if (interval <= 1) {
                z = false;
            }
            if (NoThrow.checkArgument(z, "Repeating interval must be greater than 1")) {
                this.mRepeatInterval = interval;
            } else {
                this.mRepeatInterval = Integer.MAX_VALUE;
            }
            return this;
        }

        int getRepeatingInterval() {
            return this.mRepeatInterval;
        }
    }

    public static class MoPubServerPositioning {
    }

    @NonNull
    static MoPubClientPositioning clone(@NonNull MoPubClientPositioning positioning) {
        Preconditions.checkNotNull(positioning);
        MoPubClientPositioning clone = new MoPubClientPositioning();
        clone.mFixedPositions.addAll(positioning.mFixedPositions);
        clone.mRepeatInterval = positioning.mRepeatInterval;
        return clone;
    }

    @NonNull
    public static MoPubClientPositioning clientPositioning() {
        return new MoPubClientPositioning();
    }

    @NonNull
    public static MoPubServerPositioning serverPositioning() {
        return new MoPubServerPositioning();
    }
}
