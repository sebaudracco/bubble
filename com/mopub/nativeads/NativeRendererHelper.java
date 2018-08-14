package com.mopub.nativeads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler.Builder;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Drawables;
import java.util.Map;

public class NativeRendererHelper {
    public static void addTextView(@Nullable TextView textView, @Nullable String contents) {
        if (textView == null) {
            MoPubLog.m12061d("Attempted to add text (" + contents + ") to null TextView.");
            return;
        }
        textView.setText(null);
        if (contents == null) {
            MoPubLog.m12061d("Attempted to set TextView contents to null.");
        } else {
            textView.setText(contents);
        }
    }

    public static void addPrivacyInformationIcon(ImageView privacyInformationIconImageView, String privacyInformationImageUrl, final String privacyInformationClickthroughUrl) {
        if (privacyInformationIconImageView != null) {
            if (privacyInformationClickthroughUrl == null) {
                privacyInformationIconImageView.setImageDrawable(null);
                privacyInformationIconImageView.setOnClickListener(null);
                privacyInformationIconImageView.setVisibility(4);
                return;
            }
            final Context context = privacyInformationIconImageView.getContext();
            if (context != null) {
                if (privacyInformationImageUrl == null) {
                    privacyInformationIconImageView.setImageDrawable(Drawables.NATIVE_PRIVACY_INFORMATION_ICON.createDrawable(context));
                } else {
                    NativeImageHelper.loadImageView(privacyInformationImageUrl, privacyInformationIconImageView);
                }
                privacyInformationIconImageView.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        new Builder().withSupportedUrlActions(UrlAction.IGNORE_ABOUT_SCHEME, UrlAction.OPEN_NATIVE_BROWSER, UrlAction.OPEN_IN_APP_BROWSER, UrlAction.HANDLE_SHARE_TWEET, UrlAction.FOLLOW_DEEP_LINK_WITH_FALLBACK, UrlAction.FOLLOW_DEEP_LINK).build().handleUrl(context, privacyInformationClickthroughUrl);
                    }
                });
                privacyInformationIconImageView.setVisibility(0);
            }
        }
    }

    public static void addCtaButton(@Nullable TextView ctaTextView, @Nullable final View rootView, @Nullable String contents) {
        addTextView(ctaTextView, contents);
        if (ctaTextView != null && rootView != null) {
            ctaTextView.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    rootView.performClick();
                }
            });
        }
    }

    public static void updateExtras(@Nullable View mainView, @NonNull Map<String, Integer> extrasIds, @NonNull Map<String, Object> extras) {
        if (mainView == null) {
            MoPubLog.m12069w("Attempted to bind extras on a null main view.");
            return;
        }
        for (String key : extrasIds.keySet()) {
            View view = mainView.findViewById(((Integer) extrasIds.get(key)).intValue());
            Object content = extras.get(key);
            if (view instanceof ImageView) {
                ((ImageView) view).setImageDrawable(null);
                Object object = extras.get(key);
                if (object != null && (object instanceof String)) {
                    NativeImageHelper.loadImageView((String) object, (ImageView) view);
                }
            } else if (view instanceof TextView) {
                ((TextView) view).setText(null);
                if (content instanceof String) {
                    addTextView((TextView) view, (String) content);
                }
            } else {
                MoPubLog.m12061d("View bound to " + key + " should be an instance of TextView or ImageView.");
            }
        }
    }
}
