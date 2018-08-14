package com.mopub.common;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.integralads.avid.library.mopub.BuildConfig;
import com.mopub.common.UrlHandler.MoPubSchemeListener;
import com.mopub.common.event.BaseEvent.Name;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Intents;
import com.mopub.exceptions.IntentNotResolvableException;
import com.mopub.exceptions.UrlParseException;
import com.mopub.network.TrackingRequest;
import java.net.URISyntaxException;
import java.util.List;

public enum UrlAction {
    HANDLE_MOPUB_SCHEME(false) {
        public boolean shouldTryHandlingUrl(@NonNull Uri uri) {
            return BuildConfig.SDK_NAME.equalsIgnoreCase(uri.getScheme());
        }

        protected void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String creativeId) throws IntentNotResolvableException {
            String host = uri.getHost();
            MoPubSchemeListener moPubSchemeListener = urlHandler.getMoPubSchemeListener();
            if ("finishLoad".equalsIgnoreCase(host)) {
                moPubSchemeListener.onFinishLoad();
            } else if ("close".equalsIgnoreCase(host)) {
                moPubSchemeListener.onClose();
            } else if ("failLoad".equalsIgnoreCase(host)) {
                moPubSchemeListener.onFailLoad();
            } else {
                throw new IntentNotResolvableException("Could not handle MoPub Scheme url: " + uri);
            }
        }
    },
    IGNORE_ABOUT_SCHEME(false) {
        public boolean shouldTryHandlingUrl(@NonNull Uri uri) {
            return "about".equalsIgnoreCase(uri.getScheme());
        }

        protected void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String creativeId) throws IntentNotResolvableException {
            MoPubLog.m12061d("Link to about page ignored.");
        }
    },
    HANDLE_PHONE_SCHEME(true) {
        public boolean shouldTryHandlingUrl(@NonNull Uri uri) {
            String scheme = uri.getScheme();
            return "tel".equalsIgnoreCase(scheme) || "voicemail".equalsIgnoreCase(scheme) || "sms".equalsIgnoreCase(scheme) || "mailto".equalsIgnoreCase(scheme) || "geo".equalsIgnoreCase(scheme) || "google.streetview".equalsIgnoreCase(scheme);
        }

        protected void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String creativeId) throws IntentNotResolvableException {
            Intents.launchActionViewIntent(context, uri, "Could not handle intent with URI: " + uri + "\n\tIs this intent supported on your phone?");
        }
    },
    OPEN_NATIVE_BROWSER(true) {
        public boolean shouldTryHandlingUrl(@NonNull Uri uri) {
            String scheme = uri.getScheme();
            if ("http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme)) {
                return MoPub.getBrowserAgent() == MoPub$BrowserAgent.NATIVE;
            } else {
                return "mopubnativebrowser".equalsIgnoreCase(scheme);
            }
        }

        protected void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String creativeId) throws IntentNotResolvableException {
            String errorMessage = "Unable to load mopub native browser url: " + uri;
            try {
                Intents.launchIntentForUserClick(context, Intents.intentForNativeBrowserScheme(uri), errorMessage);
            } catch (UrlParseException e) {
                throw new IntentNotResolvableException(errorMessage + "\n\t" + e.getMessage());
            }
        }
    },
    OPEN_APP_MARKET(true) {
        public boolean shouldTryHandlingUrl(@NonNull Uri uri) {
            String scheme = uri.getScheme();
            String host = uri.getHost();
            return "play.google.com".equalsIgnoreCase(host) || "market.android.com".equalsIgnoreCase(host) || "market".equalsIgnoreCase(scheme) || uri.toString().toLowerCase().startsWith("play.google.com/") || uri.toString().toLowerCase().startsWith("market.android.com/");
        }

        protected void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String creativeId) throws IntentNotResolvableException {
            Intents.launchApplicationUrl(context, uri);
        }
    },
    OPEN_IN_APP_BROWSER(true) {
        public boolean shouldTryHandlingUrl(@NonNull Uri uri) {
            String scheme = uri.getScheme();
            return "http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme);
        }

        protected void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String creativeId) throws IntentNotResolvableException {
            if (!urlHandler.shouldSkipShowMoPubBrowser()) {
                Intents.showMoPubBrowserForUrl(context, uri, creativeId);
            }
        }
    },
    HANDLE_SHARE_TWEET(true) {
        public boolean shouldTryHandlingUrl(@NonNull Uri uri) {
            Preconditions.checkNotNull(uri);
            return "mopubshare".equalsIgnoreCase(uri.getScheme()) && "tweet".equalsIgnoreCase(uri.getHost());
        }

        protected void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String creativeId) throws IntentNotResolvableException {
            Preconditions.checkNotNull(context);
            Preconditions.checkNotNull(uri);
            String chooserText = "Share via";
            String errorMessage = "Could not handle share tweet intent with URI " + uri;
            try {
                Intents.launchIntentForUserClick(context, Intent.createChooser(Intents.intentForShareTweet(uri), "Share via"), errorMessage);
            } catch (UrlParseException e) {
                throw new IntentNotResolvableException(errorMessage + "\n\t" + e.getMessage());
            }
        }
    },
    FOLLOW_DEEP_LINK_WITH_FALLBACK(true) {
        public boolean shouldTryHandlingUrl(@NonNull Uri uri) {
            return "deeplink+".equalsIgnoreCase(uri.getScheme());
        }

        protected void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String creativeId) throws IntentNotResolvableException {
            if ("navigate".equalsIgnoreCase(uri.getHost())) {
                try {
                    String primaryUrl = uri.getQueryParameter("primaryUrl");
                    Iterable primaryTrackingUrls = uri.getQueryParameters("primaryTrackingUrl");
                    String fallbackUrl = uri.getQueryParameter("fallbackUrl");
                    List<String> fallbackTrackingUrls = uri.getQueryParameters("fallbackTrackingUrl");
                    if (primaryUrl == null) {
                        throw new IntentNotResolvableException("Deeplink+ did not have 'primaryUrl' query param.");
                    }
                    Uri primaryUri = Uri.parse(primaryUrl);
                    if (shouldTryHandlingUrl(primaryUri)) {
                        throw new IntentNotResolvableException("Deeplink+ had another Deeplink+ as the 'primaryUrl'.");
                    }
                    try {
                        Intents.launchApplicationUrl(context, primaryUri);
                        TrackingRequest.makeTrackingHttpRequest(primaryTrackingUrls, context, Name.CLICK_REQUEST);
                        return;
                    } catch (IntentNotResolvableException e) {
                        if (fallbackUrl == null) {
                            throw new IntentNotResolvableException("Unable to handle 'primaryUrl' for Deeplink+ and 'fallbackUrl' was missing.");
                        } else if (shouldTryHandlingUrl(Uri.parse(fallbackUrl))) {
                            throw new IntentNotResolvableException("Deeplink+ URL had another Deeplink+ URL as the 'fallbackUrl'.");
                        } else {
                            urlHandler.handleUrl(context, fallbackUrl, true, fallbackTrackingUrls);
                            return;
                        }
                    }
                } catch (UnsupportedOperationException e2) {
                    throw new IntentNotResolvableException("Deeplink+ URL was not a hierarchical URI.");
                }
            }
            throw new IntentNotResolvableException("Deeplink+ URL did not have 'navigate' as the host.");
        }
    },
    FOLLOW_DEEP_LINK(true) {
        public boolean shouldTryHandlingUrl(@NonNull Uri uri) {
            return !TextUtils.isEmpty(uri.getScheme());
        }

        protected void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String creativeId) throws IntentNotResolvableException {
            if (Constants.INTENT_SCHEME.equalsIgnoreCase(uri.getScheme())) {
                try {
                    Intents.launchApplicationIntent(context, Intent.parseUri(uri.toString(), 1));
                    return;
                } catch (URISyntaxException e) {
                    throw new IntentNotResolvableException("Intent uri had invalid syntax: " + uri.toString());
                }
            }
            Intents.launchApplicationUrl(context, uri);
        }
    },
    NOOP(false) {
        public boolean shouldTryHandlingUrl(@NonNull Uri uri) {
            return false;
        }

        protected void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String creativeId) throws IntentNotResolvableException {
        }
    };
    
    private final boolean mRequiresUserInteraction;

    protected abstract void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String str) throws IntentNotResolvableException;

    public abstract boolean shouldTryHandlingUrl(@NonNull Uri uri);

    public void handleUrl(UrlHandler urlHandler, @NonNull Context context, @NonNull Uri destinationUri, boolean fromUserInteraction, @Nullable String creativeId) throws IntentNotResolvableException {
        MoPubLog.m12061d("Ad event URL: " + destinationUri);
        if (!this.mRequiresUserInteraction || fromUserInteraction) {
            performAction(context, destinationUri, urlHandler, creativeId);
            return;
        }
        throw new IntentNotResolvableException("Attempted to handle action without user interaction.");
    }

    private UrlAction(boolean requiresUserInteraction) {
        this.mRequiresUserInteraction = requiresUserInteraction;
    }
}
