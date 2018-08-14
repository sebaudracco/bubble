package com.mopub.nativeads;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.HttpDataSource.InvalidResponseCodeException;
import com.mopub.common.CacheService;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.event.BaseEvent.Category;
import com.mopub.common.event.BaseEvent.Name;
import com.mopub.common.event.BaseEvent.SamplingRate;
import com.mopub.common.event.Event;
import com.mopub.common.event.EventDetails;
import com.mopub.common.event.MoPubEvents;
import com.mopub.common.logging.MoPubLog;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpDiskCompositeDataSource implements DataSource {
    @VisibleForTesting
    static final int BLOCK_SIZE = 512000;
    @VisibleForTesting
    static final String EXPECTED_FILE_SIZE_KEY_PREFIX = "expectedsize-";
    private static final int HTTP_RESPONSE_REQUESTED_RANGE_NOT_SATISFIABLE = 416;
    @VisibleForTesting
    static final String INTERVALS_KEY_PREFIX = "intervals-sorted-";
    private static final String LENGTH = "length";
    @VisibleForTesting
    static final int LENGTH_UNBOUNDED = -1;
    private static final String START = "start";
    @Nullable
    private byte[] mCachedBytes;
    private int mDataBlockOffset;
    @Nullable
    private DataSpec mDataSpec;
    @Nullable
    private final EventDetails mEventDetails;
    @Nullable
    private Integer mExpectedFileLength;
    private boolean mHasLoggedDownloadStart;
    @NonNull
    private final HttpDataSource mHttpDataSource;
    @NonNull
    private final TreeSet<IntInterval> mIntervals;
    private boolean mIsDirty;
    private boolean mIsHttpSourceOpen;
    @Nullable
    private String mKey;
    private int mSegment;
    private int mStartInDataBlock;
    private int mStartInFile;

    public HttpDiskCompositeDataSource(@NonNull Context context, @NonNull String userAgent, @Nullable EventDetails eventDetails) {
        this(context, userAgent, eventDetails, new DefaultHttpDataSource(userAgent, null));
    }

    @VisibleForTesting
    HttpDiskCompositeDataSource(@NonNull Context context, @NonNull String userAgent, @Nullable EventDetails eventDetails, @NonNull HttpDataSource httpDataSource) {
        this.mExpectedFileLength = null;
        this.mHttpDataSource = httpDataSource;
        CacheService.initialize(context);
        this.mIntervals = new TreeSet();
        this.mEventDetails = eventDetails;
    }

    public long open(@NonNull DataSpec dataSpec) throws IOException {
        Preconditions.checkNotNull(dataSpec);
        if (dataSpec.uri == null) {
            return -1;
        }
        this.mIsDirty = false;
        this.mDataSpec = dataSpec;
        this.mKey = dataSpec.uri.toString();
        if (this.mKey == null) {
            return -1;
        }
        this.mStartInFile = (int) dataSpec.absoluteStreamPosition;
        this.mSegment = this.mStartInFile / BLOCK_SIZE;
        this.mCachedBytes = CacheService.getFromDiskCache(this.mSegment + this.mKey);
        this.mStartInDataBlock = this.mStartInFile % BLOCK_SIZE;
        this.mDataBlockOffset = 0;
        this.mExpectedFileLength = getExpectedFileLengthFromDisk(this.mKey);
        populateIntervalsFromDisk(this.mKey, this.mIntervals);
        int mDataRequestStartPoint = getFirstContiguousPointAfter(this.mStartInFile, this.mIntervals);
        if (this.mCachedBytes == null) {
            this.mCachedBytes = new byte[BLOCK_SIZE];
            if (mDataRequestStartPoint > this.mStartInFile) {
                MoPubLog.m12061d("Cache segment " + this.mSegment + " was evicted. Invalidating cache");
                this.mIntervals.clear();
                mDataRequestStartPoint = (int) dataSpec.absoluteStreamPosition;
            }
        }
        if (this.mExpectedFileLength == null || mDataRequestStartPoint != this.mExpectedFileLength.intValue()) {
            long lengthToUse;
            if (this.mDataSpec.length == -1) {
                lengthToUse = -1;
            } else {
                lengthToUse = this.mDataSpec.length - ((long) (mDataRequestStartPoint - this.mStartInFile));
            }
            long size;
            try {
                size = this.mHttpDataSource.open(new DataSpec(dataSpec.uri, (long) mDataRequestStartPoint, lengthToUse, dataSpec.key, dataSpec.flags));
                if (this.mExpectedFileLength == null && lengthToUse == -1) {
                    this.mExpectedFileLength = Integer.valueOf((int) (((long) this.mStartInFile) + size));
                    CacheService.putToDiskCache(EXPECTED_FILE_SIZE_KEY_PREFIX + this.mKey, String.valueOf(this.mExpectedFileLength).getBytes());
                }
                this.mIsHttpSourceOpen = true;
                if (this.mHasLoggedDownloadStart) {
                    return size;
                }
                MoPubEvents.log(Event.createEventFromDetails(Name.DOWNLOAD_START, Category.NATIVE_VIDEO, SamplingRate.NATIVE_VIDEO, this.mEventDetails));
                this.mHasLoggedDownloadStart = true;
                return size;
            } catch (InvalidResponseCodeException e) {
                if (e.responseCode == 416) {
                    size = this.mExpectedFileLength == null ? (long) (mDataRequestStartPoint - this.mStartInFile) : (long) (this.mExpectedFileLength.intValue() - this.mStartInFile);
                    this.mIsHttpSourceOpen = false;
                    return size;
                }
                throw e;
            }
        }
        return dataSpec.length == -1 ? (long) (this.mExpectedFileLength.intValue() - this.mStartInFile) : dataSpec.length;
    }

    private static void populateIntervalsFromDisk(@NonNull String key, @NonNull TreeSet<IntInterval> intervals) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(intervals);
        intervals.clear();
        byte[] intervalsFromDisk = CacheService.getFromDiskCache(INTERVALS_KEY_PREFIX + key);
        if (intervalsFromDisk != null) {
            try {
                JSONArray jsonIntervalArray = new JSONArray(new String(intervalsFromDisk));
                for (int i = 0; i < jsonIntervalArray.length(); i++) {
                    JSONObject jsonInterval = new JSONObject((String) jsonIntervalArray.get(i));
                    intervals.add(new IntInterval(jsonInterval.getInt("start"), jsonInterval.getInt(LENGTH)));
                }
            } catch (JSONException e) {
                MoPubLog.m12062d("clearing cache since invalid json intervals found", e);
                intervals.clear();
            } catch (ClassCastException e2) {
                MoPubLog.m12061d("clearing cache since unable to read json data");
                intervals.clear();
            }
        }
    }

    private static Integer getExpectedFileLengthFromDisk(@NonNull String key) {
        Integer num = null;
        Preconditions.checkNotNull(key);
        byte[] maxSizeByteArray = CacheService.getFromDiskCache(EXPECTED_FILE_SIZE_KEY_PREFIX + key);
        if (maxSizeByteArray != null) {
            try {
                num = Integer.valueOf(Integer.parseInt(new String(maxSizeByteArray)));
            } catch (NumberFormatException e) {
            }
        }
        return num;
    }

    public Uri getUri() {
        return this.mDataSpec != null ? this.mDataSpec.uri : null;
    }

    public void close() throws IOException {
        if (!(TextUtils.isEmpty(this.mKey) || this.mCachedBytes == null)) {
            CacheService.putToDiskCache(this.mSegment + this.mKey, this.mCachedBytes);
            addNewInterval(this.mIntervals, this.mStartInFile, this.mDataBlockOffset);
            writeIntervalsToDisk(this.mIntervals, this.mKey);
            if (this.mIsDirty && this.mExpectedFileLength != null && getFirstContiguousPointAfter(0, this.mIntervals) == this.mExpectedFileLength.intValue()) {
                MoPubEvents.log(Event.createEventFromDetails(Name.DOWNLOAD_FINISHED, Category.NATIVE_VIDEO, SamplingRate.NATIVE_VIDEO, this.mEventDetails));
            }
        }
        this.mCachedBytes = null;
        this.mHttpDataSource.close();
        this.mIsHttpSourceOpen = false;
        this.mStartInFile = 0;
        this.mDataBlockOffset = 0;
        this.mStartInDataBlock = 0;
        this.mExpectedFileLength = null;
        this.mIsDirty = false;
    }

    private static void writeIntervalsToDisk(@NonNull TreeSet<IntInterval> intervals, @NonNull String key) {
        Preconditions.checkNotNull(intervals);
        Preconditions.checkNotNull(key);
        JSONArray jsonIntervals = new JSONArray();
        Iterator it = intervals.iterator();
        while (it.hasNext()) {
            jsonIntervals.put((IntInterval) it.next());
        }
        CacheService.putToDiskCache(INTERVALS_KEY_PREFIX + key, jsonIntervals.toString().getBytes());
    }

    public int read(byte[] buffer, int offset, int length) throws IOException {
        if (length > BLOCK_SIZE) {
            MoPubLog.m12061d("Reading more than the block size (512000 bytes) at once is not possible. length = " + length);
            return -1;
        } else if (this.mDataSpec == null) {
            MoPubLog.m12061d("Unable to read from data source when no spec provided");
            return -1;
        } else if (this.mCachedBytes == null) {
            MoPubLog.m12061d("No cache set up. Call open before read.");
            return -1;
        } else {
            int bytesAvailableInCurrentBlock = (BLOCK_SIZE - this.mStartInDataBlock) - this.mDataBlockOffset;
            int farthestContiguousPoint = getFirstContiguousPointAfter(this.mStartInFile + this.mDataBlockOffset, this.mIntervals);
            int bytesToRead = Math.min((farthestContiguousPoint - this.mStartInFile) - this.mDataBlockOffset, length);
            int bytesReadFromDisk = 0;
            if (areBytesAvailableInCache(farthestContiguousPoint, this.mStartInFile, this.mDataBlockOffset)) {
                if (bytesToRead <= bytesAvailableInCurrentBlock) {
                    System.arraycopy(this.mCachedBytes, this.mStartInDataBlock + this.mDataBlockOffset, buffer, offset, bytesToRead);
                    this.mDataBlockOffset += bytesToRead;
                    bytesReadFromDisk = 0 + bytesToRead;
                } else {
                    System.arraycopy(this.mCachedBytes, this.mStartInDataBlock + this.mDataBlockOffset, buffer, offset, bytesAvailableInCurrentBlock);
                    this.mDataBlockOffset += bytesAvailableInCurrentBlock;
                    bytesReadFromDisk = 0 + bytesAvailableInCurrentBlock;
                    writeCacheToDiskAndClearVariables();
                    this.mCachedBytes = CacheService.getFromDiskCache(this.mSegment + this.mKey);
                    if (this.mCachedBytes == null) {
                        MoPubLog.m12061d("Unexpected cache miss. Invalidating cache");
                        this.mIntervals.clear();
                        this.mCachedBytes = new byte[BLOCK_SIZE];
                        this.mHttpDataSource.close();
                        this.mHttpDataSource.open(new DataSpec(this.mDataSpec.uri, (long) (this.mStartInFile + this.mDataBlockOffset), -1, this.mDataSpec.key, this.mDataSpec.flags));
                        this.mIsHttpSourceOpen = true;
                    } else {
                        System.arraycopy(this.mCachedBytes, this.mStartInDataBlock + this.mDataBlockOffset, buffer, offset + bytesReadFromDisk, bytesToRead - bytesReadFromDisk);
                        this.mDataBlockOffset += bytesToRead - bytesReadFromDisk;
                        bytesReadFromDisk = bytesToRead;
                    }
                }
            }
            int bytesToReadFromNetwork = length - bytesReadFromDisk;
            if (bytesToReadFromNetwork <= 0) {
                return bytesReadFromDisk;
            }
            this.mIsDirty = true;
            if (this.mIsHttpSourceOpen) {
                int bytesReadFromNetwork = this.mHttpDataSource.read(buffer, offset + bytesReadFromDisk, bytesToReadFromNetwork);
                int bytesAvailableInCurrentBlockForNetwork = (BLOCK_SIZE - this.mStartInDataBlock) - this.mDataBlockOffset;
                if (bytesAvailableInCurrentBlockForNetwork < bytesReadFromNetwork) {
                    System.arraycopy(buffer, offset + bytesReadFromDisk, this.mCachedBytes, this.mStartInDataBlock + this.mDataBlockOffset, bytesAvailableInCurrentBlockForNetwork);
                    this.mDataBlockOffset += bytesAvailableInCurrentBlockForNetwork;
                    writeCacheToDiskAndClearVariables();
                    this.mCachedBytes = CacheService.getFromDiskCache(this.mSegment + this.mKey);
                    if (this.mCachedBytes == null) {
                        this.mCachedBytes = new byte[BLOCK_SIZE];
                    }
                    System.arraycopy(buffer, (offset + bytesAvailableInCurrentBlockForNetwork) + bytesReadFromDisk, this.mCachedBytes, this.mStartInDataBlock + this.mDataBlockOffset, bytesReadFromNetwork - bytesAvailableInCurrentBlockForNetwork);
                    this.mDataBlockOffset += bytesReadFromNetwork - bytesAvailableInCurrentBlockForNetwork;
                } else {
                    System.arraycopy(buffer, offset + bytesReadFromDisk, this.mCachedBytes, this.mStartInDataBlock + this.mDataBlockOffset, bytesReadFromNetwork);
                    this.mDataBlockOffset += bytesReadFromNetwork;
                }
                return bytesReadFromDisk + bytesReadFromNetwork;
            }
            MoPubLog.m12061d("end of cache reached. No http source open");
            return -1;
        }
    }

    private static boolean areBytesAvailableInCache(int farthestContiguousPoint, int startInFile, int dataBlockOffset) {
        return farthestContiguousPoint > startInFile + dataBlockOffset;
    }

    private void writeCacheToDiskAndClearVariables() {
        CacheService.putToDiskCache(this.mSegment + this.mKey, this.mCachedBytes);
        addNewInterval(this.mIntervals, this.mStartInFile, this.mDataBlockOffset);
        this.mStartInDataBlock = 0;
        this.mStartInFile += this.mDataBlockOffset;
        this.mDataBlockOffset = 0;
        this.mSegment = this.mStartInFile / BLOCK_SIZE;
    }

    @VisibleForTesting
    static int getFirstContiguousPointAfter(int point, @NonNull TreeSet<IntInterval> intervals) {
        Preconditions.checkNotNull(intervals);
        int lastContiguousPoint = point;
        Iterator it = intervals.iterator();
        while (it.hasNext()) {
            IntInterval interval = (IntInterval) it.next();
            if (interval.getStart() <= lastContiguousPoint) {
                lastContiguousPoint = Math.max(lastContiguousPoint, interval.getStart() + interval.getLength());
            }
        }
        return lastContiguousPoint;
    }

    @VisibleForTesting
    static void addNewInterval(@NonNull TreeSet<IntInterval> intervals, int start, int length) {
        Preconditions.checkNotNull(intervals);
        if (getFirstContiguousPointAfter(start, intervals) < start + length) {
            intervals.add(new IntInterval(start, length));
        }
    }
}
