package com.mopub.mraid;

import android.support.annotation.NonNull;

public enum MraidJavascriptCommand {
    CLOSE("close"),
    EXPAND("expand") {
        boolean requiresClick(@NonNull PlacementType placementType) {
            return placementType == PlacementType.INLINE;
        }
    },
    USE_CUSTOM_CLOSE("usecustomclose"),
    OPEN("open") {
        boolean requiresClick(@NonNull PlacementType placementType) {
            return true;
        }
    },
    RESIZE("resize") {
        boolean requiresClick(@NonNull PlacementType placementType) {
            return true;
        }
    },
    SET_ORIENTATION_PROPERTIES("setOrientationProperties"),
    PLAY_VIDEO("playVideo") {
        boolean requiresClick(@NonNull PlacementType placementType) {
            return placementType == PlacementType.INLINE;
        }
    },
    STORE_PICTURE("storePicture") {
        boolean requiresClick(@NonNull PlacementType placementType) {
            return true;
        }
    },
    CREATE_CALENDAR_EVENT("createCalendarEvent") {
        boolean requiresClick(@NonNull PlacementType placementType) {
            return true;
        }
    },
    UNSPECIFIED("");
    
    @NonNull
    private final String mJavascriptString;

    private MraidJavascriptCommand(@NonNull String javascriptString) {
        this.mJavascriptString = javascriptString;
    }

    static MraidJavascriptCommand fromJavascriptString(@NonNull String string) {
        for (MraidJavascriptCommand command : values()) {
            if (command.mJavascriptString.equals(string)) {
                return command;
            }
        }
        return UNSPECIFIED;
    }

    String toJavascriptString() {
        return this.mJavascriptString;
    }

    boolean requiresClick(@NonNull PlacementType placementType) {
        return false;
    }
}
