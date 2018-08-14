package org.telegram.messenger;

import android.database.ContentObserver;
import com.stepleaderdigital.reveal.Reveal;

class MediaController$GalleryObserverExternal extends ContentObserver {
    final /* synthetic */ MediaController this$0;

    class C49661 implements Runnable {
        C49661() {
        }

        public void run() {
            MediaController.access$1602(null);
            MediaController.loadGalleryPhotosAlbums(0);
        }
    }

    public MediaController$GalleryObserverExternal(MediaController mediaController) {
        this.this$0 = mediaController;
        super(null);
    }

    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        if (MediaController.access$1600() != null) {
            AndroidUtilities.cancelRunOnUIThread(MediaController.access$1600());
        }
        AndroidUtilities.runOnUIThread(MediaController.access$1602(new C49661()), Reveal.CHECK_DELAY);
    }
}
