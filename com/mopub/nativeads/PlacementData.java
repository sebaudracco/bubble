package com.mopub.nativeads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.logging.MoPubLog;
import com.mopub.nativeads.MoPubNativeAdPositioning.MoPubClientPositioning;
import java.util.List;

class PlacementData {
    private static final int MAX_ADS = 200;
    public static final int NOT_FOUND = -1;
    @NonNull
    private final int[] mAdjustedAdPositions = new int[200];
    private int mDesiredCount = 0;
    @NonNull
    private final int[] mDesiredInsertionPositions = new int[200];
    @NonNull
    private final int[] mDesiredOriginalPositions = new int[200];
    @NonNull
    private final NativeAd[] mNativeAds = new NativeAd[200];
    @NonNull
    private final int[] mOriginalAdPositions = new int[200];
    private int mPlacedCount = 0;

    private PlacementData(@NonNull int[] desiredInsertionPositions) {
        this.mDesiredCount = Math.min(desiredInsertionPositions.length, 200);
        System.arraycopy(desiredInsertionPositions, 0, this.mDesiredInsertionPositions, 0, this.mDesiredCount);
        System.arraycopy(desiredInsertionPositions, 0, this.mDesiredOriginalPositions, 0, this.mDesiredCount);
    }

    @NonNull
    static PlacementData fromAdPositioning(@NonNull MoPubClientPositioning adPositioning) {
        int numAds;
        List<Integer> fixed = adPositioning.getFixedPositions();
        int interval = adPositioning.getRepeatingInterval();
        int size = interval == Integer.MAX_VALUE ? fixed.size() : 200;
        int[] desiredInsertionPositions = new int[size];
        int numAds2 = 0;
        int lastPos = 0;
        for (Integer position : fixed) {
            lastPos = position.intValue() - numAds2;
            numAds = numAds2 + 1;
            desiredInsertionPositions[numAds2] = lastPos;
            numAds2 = numAds;
        }
        numAds = numAds2;
        while (numAds < size) {
            lastPos = (lastPos + interval) - 1;
            numAds2 = numAds + 1;
            desiredInsertionPositions[numAds] = lastPos;
            numAds = numAds2;
        }
        return new PlacementData(desiredInsertionPositions);
    }

    @NonNull
    static PlacementData empty() {
        return new PlacementData(new int[0]);
    }

    boolean shouldPlaceAd(int position) {
        if (binarySearch(this.mDesiredInsertionPositions, 0, this.mDesiredCount, position) >= 0) {
            return true;
        }
        return false;
    }

    int nextInsertionPosition(int position) {
        int index = binarySearchGreaterThan(this.mDesiredInsertionPositions, this.mDesiredCount, position);
        if (index == this.mDesiredCount) {
            return -1;
        }
        return this.mDesiredInsertionPositions[index];
    }

    int previousInsertionPosition(int position) {
        int index = binarySearchFirstEquals(this.mDesiredInsertionPositions, this.mDesiredCount, position);
        if (index == 0) {
            return -1;
        }
        return this.mDesiredInsertionPositions[index - 1];
    }

    void placeAd(int adjustedPosition, NativeAd nativeAd) {
        int desiredIndex = binarySearchFirstEquals(this.mDesiredInsertionPositions, this.mDesiredCount, adjustedPosition);
        if (desiredIndex == this.mDesiredCount || this.mDesiredInsertionPositions[desiredIndex] != adjustedPosition) {
            MoPubLog.m12069w("Attempted to insert an ad at an invalid position");
            return;
        }
        int num;
        int i;
        int originalPosition = this.mDesiredOriginalPositions[desiredIndex];
        int placeIndex = binarySearchGreaterThan(this.mOriginalAdPositions, this.mPlacedCount, originalPosition);
        if (placeIndex < this.mPlacedCount) {
            num = this.mPlacedCount - placeIndex;
            System.arraycopy(this.mOriginalAdPositions, placeIndex, this.mOriginalAdPositions, placeIndex + 1, num);
            System.arraycopy(this.mAdjustedAdPositions, placeIndex, this.mAdjustedAdPositions, placeIndex + 1, num);
            System.arraycopy(this.mNativeAds, placeIndex, this.mNativeAds, placeIndex + 1, num);
        }
        this.mOriginalAdPositions[placeIndex] = originalPosition;
        this.mAdjustedAdPositions[placeIndex] = adjustedPosition;
        this.mNativeAds[placeIndex] = nativeAd;
        this.mPlacedCount++;
        num = (this.mDesiredCount - desiredIndex) - 1;
        System.arraycopy(this.mDesiredInsertionPositions, desiredIndex + 1, this.mDesiredInsertionPositions, desiredIndex, num);
        System.arraycopy(this.mDesiredOriginalPositions, desiredIndex + 1, this.mDesiredOriginalPositions, desiredIndex, num);
        this.mDesiredCount--;
        for (i = desiredIndex; i < this.mDesiredCount; i++) {
            int[] iArr = this.mDesiredInsertionPositions;
            iArr[i] = iArr[i] + 1;
        }
        for (i = placeIndex + 1; i < this.mPlacedCount; i++) {
            iArr = this.mAdjustedAdPositions;
            iArr[i] = iArr[i] + 1;
        }
    }

    boolean isPlacedAd(int position) {
        if (binarySearch(this.mAdjustedAdPositions, 0, this.mPlacedCount, position) >= 0) {
            return true;
        }
        return false;
    }

    @Nullable
    NativeAd getPlacedAd(int position) {
        int index = binarySearch(this.mAdjustedAdPositions, 0, this.mPlacedCount, position);
        if (index < 0) {
            return null;
        }
        return this.mNativeAds[index];
    }

    @NonNull
    int[] getPlacedAdPositions() {
        int[] positions = new int[this.mPlacedCount];
        System.arraycopy(this.mAdjustedAdPositions, 0, positions, 0, this.mPlacedCount);
        return positions;
    }

    int getOriginalPosition(int position) {
        int index = binarySearch(this.mAdjustedAdPositions, 0, this.mPlacedCount, position);
        if (index < 0) {
            return position - (index ^ -1);
        }
        return -1;
    }

    int getAdjustedPosition(int originalPosition) {
        return originalPosition + binarySearchGreaterThan(this.mOriginalAdPositions, this.mPlacedCount, originalPosition);
    }

    int getOriginalCount(int count) {
        if (count == 0) {
            return 0;
        }
        int originalPos = getOriginalPosition(count - 1);
        if (originalPos != -1) {
            return originalPos + 1;
        }
        return -1;
    }

    int getAdjustedCount(int originalCount) {
        if (originalCount == 0) {
            return 0;
        }
        return getAdjustedPosition(originalCount - 1) + 1;
    }

    int clearAdsInRange(int adjustedStartRange, int adjustedEndRange) {
        int i;
        int[] clearOriginalPositions = new int[this.mPlacedCount];
        int[] clearAdjustedPositions = new int[this.mPlacedCount];
        int clearCount = 0;
        for (i = 0; i < this.mPlacedCount; i++) {
            int originalPosition = this.mOriginalAdPositions[i];
            int adjustedPosition = this.mAdjustedAdPositions[i];
            if (adjustedStartRange <= adjustedPosition && adjustedPosition < adjustedEndRange) {
                clearOriginalPositions[clearCount] = originalPosition;
                clearAdjustedPositions[clearCount] = adjustedPosition - clearCount;
                this.mNativeAds[i].destroy();
                this.mNativeAds[i] = null;
                clearCount++;
            } else if (clearCount > 0) {
                int newIndex = i - clearCount;
                this.mOriginalAdPositions[newIndex] = originalPosition;
                this.mAdjustedAdPositions[newIndex] = adjustedPosition - clearCount;
                this.mNativeAds[newIndex] = this.mNativeAds[i];
            }
        }
        if (clearCount == 0) {
            return 0;
        }
        int desiredIndex = binarySearchFirstEquals(this.mDesiredInsertionPositions, this.mDesiredCount, clearAdjustedPositions[0]);
        for (i = this.mDesiredCount - 1; i >= desiredIndex; i--) {
            this.mDesiredOriginalPositions[i + clearCount] = this.mDesiredOriginalPositions[i];
            this.mDesiredInsertionPositions[i + clearCount] = this.mDesiredInsertionPositions[i] - clearCount;
        }
        for (i = 0; i < clearCount; i++) {
            this.mDesiredOriginalPositions[desiredIndex + i] = clearOriginalPositions[i];
            this.mDesiredInsertionPositions[desiredIndex + i] = clearAdjustedPositions[i];
        }
        this.mDesiredCount += clearCount;
        this.mPlacedCount -= clearCount;
        return clearCount;
    }

    void clearAds() {
        if (this.mPlacedCount != 0) {
            clearAdsInRange(0, this.mAdjustedAdPositions[this.mPlacedCount - 1] + 1);
        }
    }

    void insertItem(int originalPosition) {
        int i;
        for (i = binarySearchFirstEquals(this.mDesiredOriginalPositions, this.mDesiredCount, originalPosition); i < this.mDesiredCount; i++) {
            int[] iArr = this.mDesiredOriginalPositions;
            iArr[i] = iArr[i] + 1;
            iArr = this.mDesiredInsertionPositions;
            iArr[i] = iArr[i] + 1;
        }
        for (i = binarySearchFirstEquals(this.mOriginalAdPositions, this.mPlacedCount, originalPosition); i < this.mPlacedCount; i++) {
            iArr = this.mOriginalAdPositions;
            iArr[i] = iArr[i] + 1;
            iArr = this.mAdjustedAdPositions;
            iArr[i] = iArr[i] + 1;
        }
    }

    void removeItem(int originalPosition) {
        int i;
        for (i = binarySearchGreaterThan(this.mDesiredOriginalPositions, this.mDesiredCount, originalPosition); i < this.mDesiredCount; i++) {
            int[] iArr = this.mDesiredOriginalPositions;
            iArr[i] = iArr[i] - 1;
            iArr = this.mDesiredInsertionPositions;
            iArr[i] = iArr[i] - 1;
        }
        for (i = binarySearchGreaterThan(this.mOriginalAdPositions, this.mPlacedCount, originalPosition); i < this.mPlacedCount; i++) {
            iArr = this.mOriginalAdPositions;
            iArr[i] = iArr[i] - 1;
            iArr = this.mAdjustedAdPositions;
            iArr[i] = iArr[i] - 1;
        }
    }

    void moveItem(int originalPosition, int newPosition) {
        removeItem(originalPosition);
        insertItem(newPosition);
    }

    private static int binarySearchFirstEquals(int[] array, int count, int value) {
        int index = binarySearch(array, 0, count, value);
        if (index < 0) {
            return index ^ -1;
        }
        int duplicateValue = array[index];
        while (index >= 0 && array[index] == duplicateValue) {
            index--;
        }
        return index + 1;
    }

    private static int binarySearchGreaterThan(int[] array, int count, int value) {
        int index = binarySearch(array, 0, count, value);
        if (index < 0) {
            return index ^ -1;
        }
        int duplicateValue = array[index];
        while (index < count && array[index] == duplicateValue) {
            index++;
        }
        return index;
    }

    private static int binarySearch(int[] array, int startIndex, int endIndex, int value) {
        int lo = startIndex;
        int hi = endIndex - 1;
        while (lo <= hi) {
            int i = (lo + hi) >>> 1;
            int midVal = array[i];
            if (midVal < value) {
                lo = i + 1;
            } else if (midVal <= value) {
                return i;
            } else {
                hi = i - 1;
            }
        }
        return lo ^ -1;
    }
}
