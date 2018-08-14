package com.inmobi.ads;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;
import com.inmobi.ads.AdUnit.AdMarkupType;
import java.util.Map;

public interface AdContainer {

    public enum EventType {
        EVENT_TYPE_UNKNOWN,
        EVENT_TYPE_AD_LOADED,
        EVENT_TYPE_AD_SERVED,
        EVENT_TYPE_FILL_REQUEST
    }

    public static class RenderingProperties {
        private PlacementType f6690a;

        public enum PlacementType {
            PLACEMENT_TYPE_INLINE,
            PLACEMENT_TYPE_FULLSCREEN
        }

        public RenderingProperties(PlacementType placementType) {
            this.f6690a = placementType;
        }

        public PlacementType m8613a() {
            return this.f6690a;
        }
    }

    public interface C2892a {
        void mo6201a();

        void mo6202a(Object obj);

        void mo6203b(Object obj);
    }

    void mo6173a();

    void mo6174a(EventType eventType, Map<String, String> map);

    void mo6175b();

    boolean mo6176c();

    void destroy();

    Object getDataModel();

    C2892a getFullScreenEventsListener();

    AdMarkupType getMarkupType();

    RenderingProperties getRenderingProperties();

    @Nullable
    View getVideoContainerView();

    ViewableAd getViewableAd();

    void setExitAnimation(int i);

    void setFullScreenActivityContext(Activity activity);

    void setRequestedScreenOrientation();
}
