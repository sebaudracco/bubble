package com.inmobi.ads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bgjd.ici.p025b.C1408j.C1404b;
import java.util.Map;

public class InMobiAdRequest {
    private final Map<String, String> mExtras;
    private final int mHeightInDp;
    private final String mKeywords;
    private final MonetizationContext mMonetizationContext;
    private final long mPlacementId;
    private final int mWidthInDp;

    public static class Builder {
        Map<String, String> mExtras;
        int mHeightInDp;
        String mKeywords;
        private MonetizationContext mMonetizationContext;
        private long mPlacementId;
        int mWidthInDp;

        public Builder(long j) {
            this.mPlacementId = j;
        }

        public Builder setMonetizationContext(MonetizationContext monetizationContext) {
            this.mMonetizationContext = monetizationContext;
            return this;
        }

        public Builder setSlotSize(int i, int i2) {
            this.mWidthInDp = i;
            this.mHeightInDp = i2;
            return this;
        }

        public Builder setKeywords(String str) {
            this.mKeywords = str;
            return this;
        }

        public Builder setExtras(Map<String, String> map) {
            this.mExtras = map;
            return this;
        }

        public InMobiAdRequest build() {
            return new InMobiAdRequest(this.mPlacementId, this.mMonetizationContext, this.mWidthInDp, this.mHeightInDp, this.mKeywords, this.mExtras);
        }
    }

    public enum MonetizationContext {
        MONETIZATION_CONTEXT_ACTIVITY(C1404b.aw),
        MONETIZATION_CONTEXT_OTHER("others");
        
        private final String f6741a;

        private MonetizationContext(String str) {
            this.f6741a = str;
        }

        String m8787a() {
            return this.f6741a;
        }

        static MonetizationContext m8786a(String str) {
            for (MonetizationContext monetizationContext : values()) {
                if (monetizationContext.m8787a().equalsIgnoreCase(str)) {
                    return monetizationContext;
                }
            }
            return null;
        }

        public String toString() {
            return this.f6741a;
        }
    }

    private InMobiAdRequest(long j, MonetizationContext monetizationContext, int i, int i2, String str, Map<String, String> map) {
        this.mPlacementId = j;
        this.mMonetizationContext = monetizationContext;
        this.mWidthInDp = i;
        this.mHeightInDp = i2;
        this.mKeywords = str;
        this.mExtras = map;
    }

    long getPlacementId() {
        return this.mPlacementId;
    }

    @NonNull
    MonetizationContext getMonetizationContext() {
        return this.mMonetizationContext;
    }

    int getWidth() {
        return this.mWidthInDp;
    }

    int getHeight() {
        return this.mHeightInDp;
    }

    @Nullable
    String getKeywords() {
        return this.mKeywords;
    }

    @Nullable
    Map<String, String> getExtras() {
        return this.mExtras;
    }
}
