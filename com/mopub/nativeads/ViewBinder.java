package com.mopub.nativeads;

import android.support.annotation.NonNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ViewBinder {
    final int callToActionId;
    @NonNull
    final Map<String, Integer> extras;
    final int iconImageId;
    final int layoutId;
    final int mainImageId;
    final int privacyInformationIconImageId;
    final int textId;
    final int titleId;

    public static final class Builder {
        private int callToActionId;
        @NonNull
        private Map<String, Integer> extras = Collections.emptyMap();
        private int iconImageId;
        private final int layoutId;
        private int mainImageId;
        private int privacyInformationIconImageId;
        private int textId;
        private int titleId;

        public Builder(int layoutId) {
            this.layoutId = layoutId;
            this.extras = new HashMap();
        }

        @NonNull
        public final Builder titleId(int titleId) {
            this.titleId = titleId;
            return this;
        }

        @NonNull
        public final Builder textId(int textId) {
            this.textId = textId;
            return this;
        }

        @NonNull
        public final Builder callToActionId(int callToActionId) {
            this.callToActionId = callToActionId;
            return this;
        }

        @NonNull
        public final Builder mainImageId(int mediaLayoutId) {
            this.mainImageId = mediaLayoutId;
            return this;
        }

        @NonNull
        public final Builder iconImageId(int iconImageId) {
            this.iconImageId = iconImageId;
            return this;
        }

        @NonNull
        public final Builder privacyInformationIconImageId(int privacyInformationIconImageId) {
            this.privacyInformationIconImageId = privacyInformationIconImageId;
            return this;
        }

        @NonNull
        public final Builder addExtras(Map<String, Integer> resourceIds) {
            this.extras = new HashMap(resourceIds);
            return this;
        }

        @NonNull
        public final Builder addExtra(String key, int resourceId) {
            this.extras.put(key, Integer.valueOf(resourceId));
            return this;
        }

        @NonNull
        public final ViewBinder build() {
            return new ViewBinder();
        }
    }

    private ViewBinder(@NonNull Builder builder) {
        this.layoutId = builder.layoutId;
        this.titleId = builder.titleId;
        this.textId = builder.textId;
        this.callToActionId = builder.callToActionId;
        this.mainImageId = builder.mainImageId;
        this.iconImageId = builder.iconImageId;
        this.privacyInformationIconImageId = builder.privacyInformationIconImageId;
        this.extras = builder.extras;
    }
}
