package com.mopub.mobileads;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.os.AsyncTask.Status;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.VideoView;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.AsyncTasks;

public class VastVideoView extends VideoView {
    private static final int MAX_VIDEO_RETRIES = 1;
    private static final int VIDEO_VIEW_FILE_PERMISSION_ERROR = Integer.MIN_VALUE;
    @Nullable
    private VastVideoBlurLastVideoFrameTask mBlurLastVideoFrameTask;
    @Nullable
    private MediaMetadataRetriever mMediaMetadataRetriever = new MediaMetadataRetriever();

    public VastVideoView(@NonNull Context context) {
        super(context);
        Preconditions.checkNotNull(context, "context cannot be null");
    }

    public void prepareBlurredLastVideoFrame(@NonNull ImageView blurredLastVideoFrameImageView, @NonNull String diskMediaFileUrl) {
        if (this.mMediaMetadataRetriever != null) {
            this.mBlurLastVideoFrameTask = new VastVideoBlurLastVideoFrameTask(this.mMediaMetadataRetriever, blurredLastVideoFrameImageView, getDuration());
            try {
                AsyncTasks.safeExecuteOnExecutor(this.mBlurLastVideoFrameTask, new String[]{diskMediaFileUrl});
            } catch (Exception e) {
                MoPubLog.d("Failed to blur last video frame", e);
            }
        }
    }

    public void onDestroy() {
        if (this.mBlurLastVideoFrameTask != null && this.mBlurLastVideoFrameTask.getStatus() != Status.FINISHED) {
            this.mBlurLastVideoFrameTask.cancel(true);
        }
    }

    @Deprecated
    @VisibleForTesting
    void setMediaMetadataRetriever(@NonNull MediaMetadataRetriever mediaMetadataRetriever) {
        this.mMediaMetadataRetriever = mediaMetadataRetriever;
    }

    @Nullable
    @Deprecated
    @VisibleForTesting
    VastVideoBlurLastVideoFrameTask getBlurLastVideoFrameTask() {
        return this.mBlurLastVideoFrameTask;
    }

    @Deprecated
    @VisibleForTesting
    void setBlurLastVideoFrameTask(@NonNull VastVideoBlurLastVideoFrameTask blurLastVideoFrameTask) {
        this.mBlurLastVideoFrameTask = blurLastVideoFrameTask;
    }
}
