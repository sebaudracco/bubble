package com.mopub.mobileads.dfp.adapters;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class DownloadDrawablesAsync extends AsyncTask<Object, Void, HashMap<String, Drawable>> {
    private static final long DRAWABLE_FUTURE_TIMEOUT_SECONDS = 10;
    public static final String KEY_ICON = "icon_key";
    public static final String KEY_IMAGE = "image_key";
    private DrawableDownloadListener mListener;

    public DownloadDrawablesAsync(DrawableDownloadListener listener) {
        this.mListener = listener;
    }

    protected HashMap<String, Drawable> doInBackground(Object... params) {
        HashMap<String, URL> urlsMap = params[0];
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Drawable> imageDrawableFuture = getDrawableFuture((URL) urlsMap.get(KEY_IMAGE), executorService);
        try {
            Drawable imageDrawable = (Drawable) imageDrawableFuture.get(10, TimeUnit.SECONDS);
            Drawable iconDrawable = (Drawable) getDrawableFuture((URL) urlsMap.get(KEY_ICON), executorService).get(10, TimeUnit.SECONDS);
            HashMap<String, Drawable> drawablesMap = new HashMap();
            drawablesMap.put(KEY_IMAGE, imageDrawable);
            drawablesMap.put(KEY_ICON, iconDrawable);
            return drawablesMap;
        } catch (InterruptedException e) {
        } catch (ExecutionException e2) {
        } catch (TimeoutException e3) {
        }
        Log.d(MoPubAdapter.TAG, "Native ad images failed to download");
        return null;
    }

    private Future<Drawable> getDrawableFuture(final URL url, ExecutorService executorService) {
        return executorService.submit(new Callable<Drawable>() {
            public Drawable call() throws Exception {
                Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
                bitmap.setDensity(160);
                return new BitmapDrawable(Resources.getSystem(), bitmap);
            }
        });
    }

    protected void onPostExecute(HashMap<String, Drawable> drawablesMap) {
        super.onPostExecute(drawablesMap);
        if (drawablesMap != null) {
            this.mListener.onDownloadSuccess(drawablesMap);
        } else {
            this.mListener.onDownloadFailure();
        }
    }
}
