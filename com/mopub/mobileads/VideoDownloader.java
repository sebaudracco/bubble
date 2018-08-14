package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.AsyncTasks;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.Deque;

public class VideoDownloader {
    private static final int MAX_VIDEO_SIZE = 26214400;
    private static final Deque<WeakReference<VideoDownloaderTask>> sDownloaderTasks = new ArrayDeque();

    private VideoDownloader() {
    }

    public static void cache(@Nullable String url, @NonNull VideoDownloaderListener listener) {
        Preconditions.checkNotNull(listener);
        if (url == null) {
            MoPubLog.d("VideoDownloader attempted to cache video with null url.");
            listener.onComplete(false);
            return;
        }
        try {
            AsyncTasks.safeExecuteOnExecutor(new VideoDownloaderTask(listener), new String[]{url});
        } catch (Exception e) {
            listener.onComplete(false);
        }
    }

    public static void cancelAllDownloaderTasks() {
        for (WeakReference<VideoDownloaderTask> weakDownloaderTask : sDownloaderTasks) {
            cancelOneTask(weakDownloaderTask);
        }
        sDownloaderTasks.clear();
    }

    public static void cancelLastDownloadTask() {
        if (!sDownloaderTasks.isEmpty()) {
            cancelOneTask((WeakReference) sDownloaderTasks.peekLast());
            sDownloaderTasks.removeLast();
        }
    }

    private static boolean cancelOneTask(@Nullable WeakReference<VideoDownloaderTask> weakDownloaderTask) {
        if (weakDownloaderTask == null) {
            return false;
        }
        VideoDownloaderTask downloaderTask = (VideoDownloaderTask) weakDownloaderTask.get();
        if (downloaderTask != null) {
            return downloaderTask.cancel(true);
        }
        return false;
    }

    @Deprecated
    @VisibleForTesting
    public static Deque<WeakReference<VideoDownloaderTask>> getDownloaderTasks() {
        return sDownloaderTasks;
    }

    @Deprecated
    @VisibleForTesting
    public static void clearDownloaderTasks() {
        sDownloaderTasks.clear();
    }
}
