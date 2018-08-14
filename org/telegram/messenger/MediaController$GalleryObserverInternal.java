package org.telegram.messenger;

import android.database.ContentObserver;
import com.stepleaderdigital.reveal.Reveal;
import org.telegram.ui.PhotoViewer;

class MediaController$GalleryObserverInternal extends ContentObserver {
    final /* synthetic */ MediaController this$0;

    class C49671 implements Runnable {
        C49671() {
        }

        public void run() {
            if (PhotoViewer.getInstance().isVisible()) {
                MediaController$GalleryObserverInternal.this.scheduleReloadRunnable();
                return;
            }
            MediaController.access$1602(null);
            MediaController.loadGalleryPhotosAlbums(0);
        }
    }

    public MediaController$GalleryObserverInternal(MediaController mediaController) {
        this.this$0 = mediaController;
        super(null);
    }

    private void scheduleReloadRunnable() {
        AndroidUtilities.runOnUIThread(MediaController.access$1602(new C49671()), Reveal.CHECK_DELAY);
    }

    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        if (MediaController.access$1600() != null) {
            AndroidUtilities.cancelRunOnUIThread(MediaController.access$1600());
        }
        scheduleReloadRunnable();
    }
}
