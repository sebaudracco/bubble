package org.telegram.messenger;

import java.io.File;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLRPC$TL_document;
import org.telegram.tgnet.TLRPC$TL_documentAttributeAudio;

class MediaController$24 implements Runnable {
    final /* synthetic */ MediaController this$0;
    final /* synthetic */ TLRPC$TL_document val$audioToSend;
    final /* synthetic */ File val$recordingAudioFileToSend;
    final /* synthetic */ int val$send;

    class C49591 implements Runnable {
        C49591() {
        }

        public void run() {
            VideoEditedInfo videoEditedInfo = null;
            MediaController$24.this.val$audioToSend.date = ConnectionsManager.getInstance().getCurrentTime();
            MediaController$24.this.val$audioToSend.size = (int) MediaController$24.this.val$recordingAudioFileToSend.length();
            TLRPC$TL_documentAttributeAudio attributeAudio = new TLRPC$TL_documentAttributeAudio();
            attributeAudio.voice = true;
            attributeAudio.waveform = MediaController$24.this.this$0.getWaveform2(MediaController.access$400(MediaController$24.this.this$0), MediaController.access$400(MediaController$24.this.this$0).length);
            if (attributeAudio.waveform != null) {
                attributeAudio.flags |= 4;
            }
            long duration = MediaController.access$700(MediaController$24.this.this$0);
            attributeAudio.duration = (int) (MediaController.access$700(MediaController$24.this.this$0) / 1000);
            MediaController$24.this.val$audioToSend.attributes.add(attributeAudio);
            if (duration > 700) {
                TLRPC$TL_document tLRPC$TL_document;
                if (MediaController$24.this.val$send == 1) {
                    SendMessagesHelper.getInstance().sendMessage(MediaController$24.this.val$audioToSend, null, MediaController$24.this.val$recordingAudioFileToSend.getAbsolutePath(), MediaController.access$6800(MediaController$24.this.this$0), MediaController.access$6900(MediaController$24.this.this$0), null, null, 0);
                }
                NotificationCenter instance = NotificationCenter.getInstance();
                int i = NotificationCenter.audioDidSent;
                Object[] objArr = new Object[2];
                if (MediaController$24.this.val$send == 2) {
                    tLRPC$TL_document = MediaController$24.this.val$audioToSend;
                } else {
                    tLRPC$TL_document = null;
                }
                objArr[0] = tLRPC$TL_document;
                if (MediaController$24.this.val$send == 2) {
                    videoEditedInfo = MediaController$24.this.val$recordingAudioFileToSend.getAbsolutePath();
                }
                objArr[1] = videoEditedInfo;
                instance.postNotificationName(i, objArr);
                return;
            }
            MediaController$24.this.val$recordingAudioFileToSend.delete();
        }
    }

    MediaController$24(MediaController this$0, TLRPC$TL_document tLRPC$TL_document, File file, int i) {
        this.this$0 = this$0;
        this.val$audioToSend = tLRPC$TL_document;
        this.val$recordingAudioFileToSend = file;
        this.val$send = i;
    }

    public void run() {
        MediaController.access$7000(this.this$0);
        AndroidUtilities.runOnUIThread(new C49591());
    }
}
