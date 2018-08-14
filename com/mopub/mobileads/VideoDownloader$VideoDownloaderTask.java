package com.mopub.mobileads;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.mopub.common.CacheService;
import com.mopub.common.MoPubHttpUrlConnection;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Streams;
import cz.msebera.android.httpclient.HttpStatus;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;

@VisibleForTesting
class VideoDownloader$VideoDownloaderTask extends AsyncTask<String, Void, Boolean> {
    @NonNull
    private final VideoDownloader$VideoDownloaderListener mListener;
    @NonNull
    private final WeakReference<VideoDownloader$VideoDownloaderTask> mWeakSelf = new WeakReference(this);

    @VisibleForTesting
    VideoDownloader$VideoDownloaderTask(@NonNull VideoDownloader$VideoDownloaderListener listener) {
        this.mListener = listener;
        VideoDownloader.access$000().add(this.mWeakSelf);
    }

    protected Boolean doInBackground(String... params) {
        Exception e;
        Throwable th;
        if (params == null || params.length == 0 || params[0] == null) {
            MoPubLog.m12061d("VideoDownloader task tried to execute null or empty url.");
            return Boolean.valueOf(false);
        }
        String videoUrl = params[0];
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        Boolean valueOf;
        try {
            urlConnection = MoPubHttpUrlConnection.getHttpUrlConnection(videoUrl);
            InputStream inputStream2 = new BufferedInputStream(urlConnection.getInputStream());
            try {
                int statusCode = urlConnection.getResponseCode();
                if (statusCode < 200 || statusCode >= HttpStatus.SC_MULTIPLE_CHOICES) {
                    MoPubLog.m12061d("VideoDownloader encountered unexpected statusCode: " + statusCode);
                    valueOf = Boolean.valueOf(false);
                    Streams.closeStream(inputStream2);
                    if (urlConnection == null) {
                        return valueOf;
                    }
                    urlConnection.disconnect();
                    return valueOf;
                }
                if (urlConnection.getContentLength() > 26214400) {
                    MoPubLog.m12061d(String.format("VideoDownloader encountered video larger than disk cap. (%d bytes / %d maximum).", new Object[]{Integer.valueOf(urlConnection.getContentLength()), Integer.valueOf(26214400)}));
                    valueOf = Boolean.valueOf(false);
                    Streams.closeStream(inputStream2);
                    if (urlConnection == null) {
                        return valueOf;
                    }
                    urlConnection.disconnect();
                    return valueOf;
                }
                valueOf = Boolean.valueOf(CacheService.putToDiskCache(videoUrl, inputStream2));
                Streams.closeStream(inputStream2);
                if (urlConnection == null) {
                    return valueOf;
                }
                urlConnection.disconnect();
                return valueOf;
            } catch (Exception e2) {
                e = e2;
                inputStream = inputStream2;
                try {
                    MoPubLog.m12062d("VideoDownloader task threw an internal exception.", e);
                    valueOf = Boolean.valueOf(false);
                    Streams.closeStream(inputStream);
                    if (urlConnection != null) {
                        return valueOf;
                    }
                    urlConnection.disconnect();
                    return valueOf;
                } catch (Throwable th2) {
                    th = th2;
                    Streams.closeStream(inputStream);
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                inputStream = inputStream2;
                Streams.closeStream(inputStream);
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            MoPubLog.m12062d("VideoDownloader task threw an internal exception.", e);
            valueOf = Boolean.valueOf(false);
            Streams.closeStream(inputStream);
            if (urlConnection != null) {
                return valueOf;
            }
            urlConnection.disconnect();
            return valueOf;
        }
    }

    protected void onPostExecute(Boolean success) {
        if (isCancelled()) {
            onCancelled();
            return;
        }
        VideoDownloader.access$000().remove(this.mWeakSelf);
        if (success == null) {
            this.mListener.onComplete(false);
        } else {
            this.mListener.onComplete(success.booleanValue());
        }
    }

    protected void onCancelled() {
        MoPubLog.m12061d("VideoDownloader task was cancelled.");
        VideoDownloader.access$000().remove(this.mWeakSelf);
        this.mListener.onComplete(false);
    }
}
