package com.mopub.mraid;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.mopub.common.MoPubHttpUrlConnection;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.util.ResponseHeader;
import com.mopub.common.util.Streams;
import com.mopub.mraid.MraidNativeCommandHandler.MoPubMediaScannerConnectionClient;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.List;
import java.util.Map;

@VisibleForTesting
class MraidNativeCommandHandler$DownloadImageAsyncTask extends AsyncTask<String, Void, Boolean> {
    private final Context mContext;
    private final DownloadImageAsyncTaskListener mListener;

    public MraidNativeCommandHandler$DownloadImageAsyncTask(@NonNull Context context, @NonNull DownloadImageAsyncTaskListener listener) {
        this.mContext = context.getApplicationContext();
        this.mListener = listener;
    }

    protected Boolean doInBackground(@NonNull String[] params) {
        OutputStream pictureOutputStream;
        Throwable th;
        if (params == null || params.length == 0 || params[0] == null) {
            return Boolean.valueOf(false);
        }
        File pictureStoragePath = getPictureStoragePath();
        pictureStoragePath.mkdirs();
        String uriString = params[0];
        URI uri = URI.create(uriString);
        InputStream pictureInputStream = null;
        OutputStream pictureOutputStream2 = null;
        Boolean valueOf;
        try {
            File pictureFile;
            HttpURLConnection urlConnection = MoPubHttpUrlConnection.getHttpUrlConnection(uriString);
            InputStream pictureInputStream2 = new BufferedInputStream(urlConnection.getInputStream());
            try {
                String redirectLocation = urlConnection.getHeaderField(ResponseHeader.LOCATION.getKey());
                if (!TextUtils.isEmpty(redirectLocation)) {
                    uri = URI.create(redirectLocation);
                }
                pictureFile = new File(pictureStoragePath, getFileNameForUriAndHeaders(uri, urlConnection.getHeaderFields()));
                pictureOutputStream = new FileOutputStream(pictureFile);
            } catch (Exception e) {
                pictureInputStream = pictureInputStream2;
                try {
                    valueOf = Boolean.valueOf(false);
                    Streams.closeStream(pictureInputStream);
                    Streams.closeStream(pictureOutputStream2);
                    return valueOf;
                } catch (Throwable th2) {
                    th = th2;
                    Streams.closeStream(pictureInputStream);
                    Streams.closeStream(pictureOutputStream2);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                pictureInputStream = pictureInputStream2;
                Streams.closeStream(pictureInputStream);
                Streams.closeStream(pictureOutputStream2);
                throw th;
            }
            try {
                Streams.copyContent(pictureInputStream2, pictureOutputStream);
                loadPictureIntoGalleryApp(pictureFile.toString());
                valueOf = Boolean.valueOf(true);
                Streams.closeStream(pictureInputStream2);
                Streams.closeStream(pictureOutputStream);
                return valueOf;
            } catch (Exception e2) {
                pictureOutputStream2 = pictureOutputStream;
                pictureInputStream = pictureInputStream2;
                valueOf = Boolean.valueOf(false);
                Streams.closeStream(pictureInputStream);
                Streams.closeStream(pictureOutputStream2);
                return valueOf;
            } catch (Throwable th4) {
                th = th4;
                pictureOutputStream2 = pictureOutputStream;
                pictureInputStream = pictureInputStream2;
                Streams.closeStream(pictureInputStream);
                Streams.closeStream(pictureOutputStream2);
                throw th;
            }
        } catch (Exception e3) {
            valueOf = Boolean.valueOf(false);
            Streams.closeStream(pictureInputStream);
            Streams.closeStream(pictureOutputStream2);
            return valueOf;
        }
    }

    protected void onPostExecute(Boolean success) {
        if (success == null || !success.booleanValue()) {
            this.mListener.onFailure();
        } else {
            this.mListener.onSuccess();
        }
    }

    @Nullable
    private String getFileNameForUriAndHeaders(@NonNull URI uri, @Nullable Map<String, List<String>> headers) {
        Preconditions.checkNotNull(uri);
        String path = uri.getPath();
        if (path == null || headers == null) {
            return null;
        }
        String filename = new File(path).getName();
        List<String> mimeTypeHeaders = (List) headers.get("Content-Type");
        if (mimeTypeHeaders == null || mimeTypeHeaders.isEmpty() || mimeTypeHeaders.get(0) == null) {
            return filename;
        }
        for (String field : ((String) mimeTypeHeaders.get(0)).split(";")) {
            if (field.contains("image/")) {
                String extension = "." + field.split(BridgeUtil.SPLIT_MARK)[1];
                if (filename.endsWith(extension)) {
                    return filename;
                }
                return filename + extension;
            }
        }
        return filename;
    }

    private File getPictureStoragePath() {
        return new File(Environment.getExternalStorageDirectory(), "Pictures");
    }

    private void loadPictureIntoGalleryApp(String filename) {
        MoPubMediaScannerConnectionClient mediaScannerConnectionClient = new MoPubMediaScannerConnectionClient(filename, null, null);
        MediaScannerConnection mediaScannerConnection = new MediaScannerConnection(this.mContext, mediaScannerConnectionClient);
        MoPubMediaScannerConnectionClient.access$100(mediaScannerConnectionClient, mediaScannerConnection);
        mediaScannerConnection.connect();
    }

    @Deprecated
    @VisibleForTesting
    DownloadImageAsyncTaskListener getListener() {
        return this.mListener;
    }
}
