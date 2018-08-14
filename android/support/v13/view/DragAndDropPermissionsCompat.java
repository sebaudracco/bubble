package android.support.v13.view;

import android.app.Activity;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.view.DragAndDropPermissions;
import android.view.DragEvent;

public final class DragAndDropPermissionsCompat {
    private static DragAndDropPermissionsCompatImpl IMPL;
    private Object mDragAndDropPermissions;

    interface DragAndDropPermissionsCompatImpl {
        void release(Object obj);

        Object request(Activity activity, DragEvent dragEvent);
    }

    static class BaseDragAndDropPermissionsCompatImpl implements DragAndDropPermissionsCompatImpl {
        BaseDragAndDropPermissionsCompatImpl() {
        }

        public Object request(Activity activity, DragEvent dragEvent) {
            return null;
        }

        public void release(Object dragAndDropPermissions) {
        }
    }

    @RequiresApi(24)
    static class Api24DragAndDropPermissionsCompatImpl extends BaseDragAndDropPermissionsCompatImpl {
        Api24DragAndDropPermissionsCompatImpl() {
        }

        public Object request(Activity activity, DragEvent dragEvent) {
            return activity.requestDragAndDropPermissions(dragEvent);
        }

        public void release(Object dragAndDropPermissions) {
            ((DragAndDropPermissions) dragAndDropPermissions).release();
        }
    }

    static {
        if (VERSION.SDK_INT >= 24) {
            IMPL = new Api24DragAndDropPermissionsCompatImpl();
        } else {
            IMPL = new BaseDragAndDropPermissionsCompatImpl();
        }
    }

    private DragAndDropPermissionsCompat(Object dragAndDropPermissions) {
        this.mDragAndDropPermissions = dragAndDropPermissions;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static DragAndDropPermissionsCompat request(Activity activity, DragEvent dragEvent) {
        Object dragAndDropPermissions = IMPL.request(activity, dragEvent);
        if (dragAndDropPermissions != null) {
            return new DragAndDropPermissionsCompat(dragAndDropPermissions);
        }
        return null;
    }

    public void release() {
        IMPL.release(this.mDragAndDropPermissions);
    }
}
