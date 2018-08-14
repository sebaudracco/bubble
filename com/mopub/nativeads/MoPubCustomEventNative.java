package com.mopub.nativeads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.mopub.common.DataKeys;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Numbers;
import com.mopub.nativeads.CustomEventNative.CustomEventNativeListener;
import com.mopub.nativeads.NativeImageHelper.ImageListener;
import com.silvermob.sdk.Const.BannerType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class MoPubCustomEventNative extends CustomEventNative {

    static class MoPubStaticNativeAd extends StaticNativeAd {
        @VisibleForTesting
        static final String PRIVACY_INFORMATION_CLICKTHROUGH_URL = "https://www.mopub.com/optout";
        @NonNull
        private final Context mContext;
        @NonNull
        private final CustomEventNativeListener mCustomEventNativeListener;
        @NonNull
        private final ImpressionTracker mImpressionTracker;
        @NonNull
        private final JSONObject mJsonObject;
        @NonNull
        private final NativeClickHandler mNativeClickHandler;

        class C37321 implements ImageListener {
            C37321() {
            }

            public void onImagesCached() {
                MoPubStaticNativeAd.this.mCustomEventNativeListener.onNativeAdLoaded(MoPubStaticNativeAd.this);
            }

            public void onImagesFailedToCache(NativeErrorCode errorCode) {
                MoPubStaticNativeAd.this.mCustomEventNativeListener.onNativeAdFailed(errorCode);
            }
        }

        enum Parameter {
            IMPRESSION_TRACKER("imptracker", true),
            CLICK_TRACKER("clktracker", true),
            TITLE("title", false),
            TEXT("text", false),
            MAIN_IMAGE("mainimage", false),
            ICON_IMAGE("iconimage", false),
            CLICK_DESTINATION("clk", false),
            FALLBACK("fallback", false),
            CALL_TO_ACTION("ctatext", false),
            STAR_RATING("starrating", false);
            
            @NonNull
            @VisibleForTesting
            static final Set<String> requiredKeys = null;
            @NonNull
            final String name;
            final boolean required;

            static {
                requiredKeys = new HashSet();
                Parameter[] values = values();
                int length = values.length;
                int i;
                while (i < length) {
                    Parameter parameter = values[i];
                    if (parameter.required) {
                        requiredKeys.add(parameter.name);
                    }
                    i++;
                }
            }

            private Parameter(@NonNull String name, boolean required) {
                this.name = name;
                this.required = required;
            }

            @Nullable
            static Parameter from(@NonNull String name) {
                for (Parameter parameter : values()) {
                    if (parameter.name.equals(name)) {
                        return parameter;
                    }
                }
                return null;
            }
        }

        MoPubStaticNativeAd(@NonNull Context context, @NonNull JSONObject jsonBody, @NonNull ImpressionTracker impressionTracker, @NonNull NativeClickHandler nativeClickHandler, @NonNull CustomEventNativeListener customEventNativeListener) {
            this.mJsonObject = jsonBody;
            this.mContext = context.getApplicationContext();
            this.mImpressionTracker = impressionTracker;
            this.mNativeClickHandler = nativeClickHandler;
            this.mCustomEventNativeListener = customEventNativeListener;
        }

        void loadAd() throws IllegalArgumentException {
            if (containsRequiredKeys(this.mJsonObject)) {
                Iterator<String> keys = this.mJsonObject.keys();
                while (keys.hasNext()) {
                    String key = (String) keys.next();
                    Parameter parameter = Parameter.from(key);
                    if (parameter != null) {
                        try {
                            addInstanceVariable(parameter, this.mJsonObject.opt(key));
                        } catch (ClassCastException e) {
                            throw new IllegalArgumentException("JSONObject key (" + key + ") contained unexpected value.");
                        }
                    }
                    addExtra(key, this.mJsonObject.opt(key));
                }
                setPrivacyInformationIconClickThroughUrl(PRIVACY_INFORMATION_CLICKTHROUGH_URL);
                NativeImageHelper.preCacheImages(this.mContext, getAllImageUrls(), new C37321());
                return;
            }
            throw new IllegalArgumentException("JSONObject did not contain required keys.");
        }

        private boolean containsRequiredKeys(@NonNull JSONObject jsonObject) {
            Set<String> keys = new HashSet();
            Iterator<String> jsonKeys = jsonObject.keys();
            while (jsonKeys.hasNext()) {
                keys.add(jsonKeys.next());
            }
            return keys.containsAll(Parameter.requiredKeys);
        }

        private void addInstanceVariable(@NonNull Parameter key, @Nullable Object value) throws ClassCastException {
            try {
                switch (key) {
                    case MAIN_IMAGE:
                        setMainImageUrl((String) value);
                        return;
                    case ICON_IMAGE:
                        setIconImageUrl((String) value);
                        return;
                    case IMPRESSION_TRACKER:
                        addImpressionTrackers(value);
                        return;
                    case CLICK_DESTINATION:
                        setClickDestinationUrl((String) value);
                        return;
                    case CLICK_TRACKER:
                        parseClickTrackers(value);
                        return;
                    case CALL_TO_ACTION:
                        setCallToAction((String) value);
                        return;
                    case TITLE:
                        setTitle((String) value);
                        return;
                    case TEXT:
                        setText((String) value);
                        return;
                    case STAR_RATING:
                        setStarRating(Numbers.parseDouble(value));
                        return;
                    default:
                        MoPubLog.m12061d("Unable to add JSON key to internal mapping: " + key.name);
                        return;
                }
            } catch (ClassCastException e) {
                if (key.required) {
                    throw e;
                }
                MoPubLog.m12061d("Ignoring class cast exception for optional key: " + key.name);
                return;
            }
            if (key.required) {
                MoPubLog.m12061d("Ignoring class cast exception for optional key: " + key.name);
                return;
            }
            throw e;
        }

        private void parseClickTrackers(@NonNull Object clickTrackers) {
            if (clickTrackers instanceof JSONArray) {
                addClickTrackers(clickTrackers);
            } else {
                addClickTracker((String) clickTrackers);
            }
        }

        private boolean isImageKey(@Nullable String name) {
            return name != null && name.toLowerCase(Locale.US).endsWith(BannerType.IMAGE);
        }

        @NonNull
        List<String> getExtrasImageUrls() {
            List<String> extrasBitmapUrls = new ArrayList(getExtras().size());
            for (Entry<String, Object> entry : getExtras().entrySet()) {
                if (isImageKey((String) entry.getKey()) && (entry.getValue() instanceof String)) {
                    extrasBitmapUrls.add((String) entry.getValue());
                }
            }
            return extrasBitmapUrls;
        }

        @NonNull
        List<String> getAllImageUrls() {
            List<String> imageUrls = new ArrayList();
            if (getMainImageUrl() != null) {
                imageUrls.add(getMainImageUrl());
            }
            if (getIconImageUrl() != null) {
                imageUrls.add(getIconImageUrl());
            }
            imageUrls.addAll(getExtrasImageUrls());
            return imageUrls;
        }

        public void prepare(@NonNull View view) {
            this.mImpressionTracker.addView(view, this);
            this.mNativeClickHandler.setOnClickListener(view, (ClickInterface) this);
        }

        public void clear(@NonNull View view) {
            this.mImpressionTracker.removeView(view);
            this.mNativeClickHandler.clearOnClickListener(view);
        }

        public void destroy() {
            this.mImpressionTracker.destroy();
        }

        public void recordImpression(@NonNull View view) {
            notifyAdImpressed();
        }

        public void handleClick(@Nullable View view) {
            notifyAdClicked();
            this.mNativeClickHandler.openClickDestinationUrl(getClickDestinationUrl(), view);
        }
    }

    protected void loadNativeAd(@NonNull Context context, @NonNull CustomEventNativeListener customEventNativeListener, @NonNull Map<String, Object> localExtras, @NonNull Map<String, String> serverExtras) {
        Object json = localExtras.get(DataKeys.JSON_BODY_KEY);
        if (json instanceof JSONObject) {
            MoPubStaticNativeAd moPubStaticNativeAd = new MoPubStaticNativeAd(context, (JSONObject) json, new ImpressionTracker(context), new NativeClickHandler(context), customEventNativeListener);
            if (serverExtras.containsKey(DataKeys.IMPRESSION_MIN_VISIBLE_PERCENT)) {
                try {
                    moPubStaticNativeAd.setImpressionMinPercentageViewed(Integer.parseInt((String) serverExtras.get(DataKeys.IMPRESSION_MIN_VISIBLE_PERCENT)));
                } catch (NumberFormatException e) {
                    MoPubLog.m12061d("Unable to format min visible percent: " + ((String) serverExtras.get(DataKeys.IMPRESSION_MIN_VISIBLE_PERCENT)));
                }
            }
            if (serverExtras.containsKey(DataKeys.IMPRESSION_VISIBLE_MS)) {
                try {
                    moPubStaticNativeAd.setImpressionMinTimeViewed(Integer.parseInt((String) serverExtras.get(DataKeys.IMPRESSION_VISIBLE_MS)));
                } catch (NumberFormatException e2) {
                    MoPubLog.m12061d("Unable to format min time: " + ((String) serverExtras.get(DataKeys.IMPRESSION_VISIBLE_MS)));
                }
            }
            if (serverExtras.containsKey(DataKeys.IMPRESSION_MIN_VISIBLE_PX)) {
                try {
                    moPubStaticNativeAd.setImpressionMinVisiblePx(Integer.valueOf(Integer.parseInt((String) serverExtras.get(DataKeys.IMPRESSION_MIN_VISIBLE_PX))));
                } catch (NumberFormatException e3) {
                    MoPubLog.m12061d("Unable to format min visible px: " + ((String) serverExtras.get(DataKeys.IMPRESSION_MIN_VISIBLE_PX)));
                }
            }
            try {
                moPubStaticNativeAd.loadAd();
                return;
            } catch (IllegalArgumentException e4) {
                customEventNativeListener.onNativeAdFailed(NativeErrorCode.UNSPECIFIED);
                return;
            }
        }
        customEventNativeListener.onNativeAdFailed(NativeErrorCode.INVALID_RESPONSE);
    }
}
