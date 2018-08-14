package android.support.v13.view.inputmethod;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.inputmethod.EditorInfo;

public final class EditorInfoCompat {
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final int IME_FLAG_FORCE_ASCII = Integer.MIN_VALUE;
    public static final int IME_FLAG_NO_PERSONALIZED_LEARNING = 16777216;
    private static final EditorInfoCompatImpl IMPL;

    private interface EditorInfoCompatImpl {
        @NonNull
        String[] getContentMimeTypes(@NonNull EditorInfo editorInfo);

        void setContentMimeTypes(@NonNull EditorInfo editorInfo, @Nullable String[] strArr);
    }

    @RequiresApi(25)
    private static final class EditorInfoCompatApi25Impl implements EditorInfoCompatImpl {
        private EditorInfoCompatApi25Impl() {
        }

        public void setContentMimeTypes(@NonNull EditorInfo editorInfo, @Nullable String[] contentMimeTypes) {
            editorInfo.contentMimeTypes = contentMimeTypes;
        }

        @NonNull
        public String[] getContentMimeTypes(@NonNull EditorInfo editorInfo) {
            String[] result = editorInfo.contentMimeTypes;
            return result != null ? result : EditorInfoCompat.EMPTY_STRING_ARRAY;
        }
    }

    private static final class EditorInfoCompatBaseImpl implements EditorInfoCompatImpl {
        private static String CONTENT_MIME_TYPES_KEY = "android.support.v13.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES";

        private EditorInfoCompatBaseImpl() {
        }

        public void setContentMimeTypes(@NonNull EditorInfo editorInfo, @Nullable String[] contentMimeTypes) {
            if (editorInfo.extras == null) {
                editorInfo.extras = new Bundle();
            }
            editorInfo.extras.putStringArray(CONTENT_MIME_TYPES_KEY, contentMimeTypes);
        }

        @NonNull
        public String[] getContentMimeTypes(@NonNull EditorInfo editorInfo) {
            if (editorInfo.extras == null) {
                return EditorInfoCompat.EMPTY_STRING_ARRAY;
            }
            String[] result = editorInfo.extras.getStringArray(CONTENT_MIME_TYPES_KEY);
            return result == null ? EditorInfoCompat.EMPTY_STRING_ARRAY : result;
        }
    }

    static {
        if (VERSION.SDK_INT >= 25) {
            IMPL = new EditorInfoCompatApi25Impl();
        } else {
            IMPL = new EditorInfoCompatBaseImpl();
        }
    }

    public static void setContentMimeTypes(@NonNull EditorInfo editorInfo, @Nullable String[] contentMimeTypes) {
        IMPL.setContentMimeTypes(editorInfo, contentMimeTypes);
    }

    @NonNull
    public static String[] getContentMimeTypes(EditorInfo editorInfo) {
        return IMPL.getContentMimeTypes(editorInfo);
    }
}
