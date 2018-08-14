package com.inmobi.ads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.inmobi.ads.NativeV2ScrollableContainer.TYPE;
import com.inmobi.commons.core.p115d.C3132b;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;

/* compiled from: NativeV2ScrollableDataSourceFactory */
final class ar {
    private static final String f7158a = ar.class.getSimpleName();

    ar() {
    }

    @Nullable
    public static aq m9398a(TYPE type, @NonNull NativeV2DataModel nativeV2DataModel, @NonNull ae aeVar) {
        switch (type) {
            case TYPE_PAGED:
                return new ad(nativeV2DataModel, aeVar);
            case TYPE_FREE:
                try {
                    return new NativeV2RecyclerViewAdapter(nativeV2DataModel, aeVar);
                } catch (Throwable e) {
                    Logger.m10359a(InternalLogLevel.ERROR, "InMobi", "Error rendering ad! RecyclerView not found. Please check if the recyclerview support library was included");
                    C3135c.m10255a().m10279a(new C3132b(e));
                    return null;
                }
            default:
                return null;
        }
    }
}
