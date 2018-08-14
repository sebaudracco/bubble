package com.mobfox.sdk.nativeads;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import com.mobfox.sdk.constants.Constants;
import com.mobfox.sdk.networking.AsyncCallback;
import com.mobfox.sdk.networking.AsyncCallbackBitmap;
import com.mobfox.sdk.networking.MobFoxRequest;
import com.mobfox.sdk.networking.RequestParams;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NativeAd {
    int imagedFailed = 0;
    List<ImageItem> images;
    int imagesLoaded = 0;
    String link;
    ImagesLoadedListener listener;
    NativeAd self = this;
    List<TextItem> texts;
    List<Tracker> trackerList;

    public interface FireTrackersCallback {
        void onComplete();
    }

    public interface ImagesLoadedListener {
        void onImagesLoaded(NativeAd nativeAd);
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setTexts(List<TextItem> texts) {
        this.texts = texts;
    }

    public void setImages(List<ImageItem> images) {
        this.images = images;
    }

    private void checkImagesDone() {
        if (this.imagesLoaded + this.imagedFailed == this.images.size()) {
            this.listener.onImagesLoaded(this);
        }
    }

    public void loadImages(Context c, ImagesLoadedListener listener) {
        this.listener = listener;
        if (this.images.size() != 0) {
            for (int i = 0; i < this.images.size(); i++) {
                final ImageItem imageItem = (ImageItem) this.images.get(i);
                new MobFoxRequest(c, imageItem.getUrl()).getBitmap(new AsyncCallbackBitmap() {
                    public void onComplete(int code, Bitmap bitmap, Map<String, List<String>> map) {
                        imageItem.setImg(bitmap);
                        NativeAd nativeAd = NativeAd.this;
                        nativeAd.imagesLoaded++;
                        NativeAd.this.checkImagesDone();
                    }

                    public void onError(Exception e) {
                        Log.d(Constants.MOBFOX_NATIVE, "load bitmap failed");
                        NativeAd nativeAd = NativeAd.this;
                        nativeAd.imagedFailed++;
                        NativeAd.this.checkImagesDone();
                    }
                });
            }
        }
    }

    public void fireTrackers(Context c, final FireTrackersCallback cb) {
        final CountDownLatch trackersLeft = new CountDownLatch(this.trackerList.size());
        for (final Tracker t : this.trackerList) {
            new MobFoxRequest(c, t.getUrl()).get(new AsyncCallback() {
                public void onComplete(int code, Object response, Map<String, List<String>> map) {
                    Log.d(Constants.MOBFOX_NATIVE, "fired tracker: " + t.getUrl());
                    trackersLeft.countDown();
                    if (cb != null && trackersLeft.getCount() == 0) {
                        cb.onComplete();
                    }
                }

                public void onError(Exception e) {
                    trackersLeft.countDown();
                    if (cb != null && trackersLeft.getCount() == 0) {
                        cb.onComplete();
                    }
                }
            });
        }
    }

    public void fireTrackers(Context c) {
        fireTrackers(c, null);
    }

    public static NativeAd parse(JSONObject obj) {
        if (obj == null) {
            return null;
        }
        NativeAd ad = new NativeAd();
        JSONObject aNative = obj.getJSONObject("native");
        try {
            ad.setLink(aNative.getJSONObject("link").getString("url"));
        } catch (JSONException e) {
            Log.d(Constants.MOBFOX_NATIVE, "no link");
        }
        try {
            int i;
            JSONArray assets = aNative.getJSONArray("assets");
            List<Tracker> trackerList = new ArrayList();
            List<ImageItem> images = new ArrayList();
            List<TextItem> texts = new ArrayList();
            for (i = 0; i < assets.length(); i++) {
                JSONObject asset = assets.getJSONObject(i);
                if (asset.has("type")) {
                    if (asset.getString("type").equals("icon") || asset.getString("type").equals("main")) {
                        try {
                            images.add(new ImageItem(asset.getString("type"), asset.getJSONObject("img").getString("url"), asset.getJSONObject("img").getInt(RequestParams.f9035H), asset.getJSONObject("img").getInt("w")));
                        } catch (JSONException e2) {
                        }
                    }
                    if (asset.getString("type").equals("title") || asset.getString("type").equals("desc") || asset.getString("type").equals("ctatext") || asset.getString("type").equals("sponsored")) {
                        try {
                            if (asset.getString("type").equals("title")) {
                                texts.add(new TextItem(asset.getString("type"), asset.getJSONObject("title").getString("text")));
                            } else {
                                texts.add(new TextItem(asset.getString("type"), asset.getJSONObject("data").getString(FirebaseAnalytics$Param.VALUE)));
                            }
                        } catch (JSONException e3) {
                        }
                    }
                }
            }
            ad.setImages(images);
            ad.setTexts(texts);
            JSONArray imptrackers = aNative.getJSONArray("imptrackers");
            for (i = 0; i < imptrackers.length(); i++) {
                try {
                    trackerList.add(new Tracker("type", imptrackers.getString(i)));
                } catch (JSONException e4) {
                }
            }
            ad.setTrackerList(trackerList);
            return ad;
        } catch (JSONException e5) {
            if (e5.getMessage() == null) {
                Log.d(Constants.MOBFOX_NATIVE, "native ad parse err " + e5.getMessage());
            } else {
                Log.d(Constants.MOBFOX_NATIVE, "native ad parse err");
            }
            return null;
        }
    }

    public List<Tracker> getTrackerList() {
        return this.trackerList;
    }

    public void setTrackerList(List<Tracker> trackerList) {
        this.trackerList = trackerList;
    }

    public List<ImageItem> getImages() {
        return this.images;
    }

    public List<TextItem> getTexts() {
        return this.texts;
    }

    public String getLink() {
        return this.link;
    }
}
