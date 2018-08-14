package android.support.v4.content;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import java.util.ArrayList;
import org.slf4j.Marker;

public final class MimeTypeFilter {
    private MimeTypeFilter() {
    }

    private static boolean mimeTypeAgainstFilter(@NonNull String[] mimeTypeParts, @NonNull String[] filterParts) {
        if (filterParts.length != 2) {
            throw new IllegalArgumentException("Ill-formatted MIME type filter. Must be type/subtype.");
        } else if (filterParts[0].isEmpty() || filterParts[1].isEmpty()) {
            throw new IllegalArgumentException("Ill-formatted MIME type filter. Type or subtype empty.");
        } else if (mimeTypeParts.length != 2) {
            return false;
        } else {
            if (!Marker.ANY_MARKER.equals(filterParts[0]) && !filterParts[0].equals(mimeTypeParts[0])) {
                return false;
            }
            if (Marker.ANY_MARKER.equals(filterParts[1]) || filterParts[1].equals(mimeTypeParts[1])) {
                return true;
            }
            return false;
        }
    }

    public static boolean matches(@Nullable String mimeType, @NonNull String filter) {
        if (mimeType == null) {
            return false;
        }
        return mimeTypeAgainstFilter(mimeType.split(BridgeUtil.SPLIT_MARK), filter.split(BridgeUtil.SPLIT_MARK));
    }

    public static String matches(@Nullable String mimeType, @NonNull String[] filters) {
        if (mimeType == null) {
            return null;
        }
        String[] mimeTypeParts = mimeType.split(BridgeUtil.SPLIT_MARK);
        for (String filter : filters) {
            if (mimeTypeAgainstFilter(mimeTypeParts, filter.split(BridgeUtil.SPLIT_MARK))) {
                return filter;
            }
        }
        return null;
    }

    public static String matches(@Nullable String[] mimeTypes, @NonNull String filter) {
        if (mimeTypes == null) {
            return null;
        }
        String[] filterParts = filter.split(BridgeUtil.SPLIT_MARK);
        for (String mimeType : mimeTypes) {
            if (mimeTypeAgainstFilter(mimeType.split(BridgeUtil.SPLIT_MARK), filterParts)) {
                return mimeType;
            }
        }
        return null;
    }

    public static String[] matchesMany(@Nullable String[] mimeTypes, @NonNull String filter) {
        int i = 0;
        if (mimeTypes == null) {
            return new String[0];
        }
        ArrayList<String> list = new ArrayList();
        String[] filterParts = filter.split(BridgeUtil.SPLIT_MARK);
        int length = mimeTypes.length;
        while (i < length) {
            String mimeType = mimeTypes[i];
            if (mimeTypeAgainstFilter(mimeType.split(BridgeUtil.SPLIT_MARK), filterParts)) {
                list.add(mimeType);
            }
            i++;
        }
        return (String[]) list.toArray(new String[list.size()]);
    }
}
