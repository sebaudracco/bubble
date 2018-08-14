package org.telegram.messenger;

class SendMessagesHelper$18 implements Runnable {
    final /* synthetic */ long val$dialog_id;
    final /* synthetic */ String val$text;

    class C51761 implements Runnable {

        class C51751 implements Runnable {
            C51751() {
            }

            public void run() {
                String textFinal = SendMessagesHelper.access$1600(SendMessagesHelper$18.this.val$text);
                if (textFinal.length() != 0) {
                    int count = (int) Math.ceil((double) (((float) textFinal.length()) / 4096.0f));
                    for (int a = 0; a < count; a++) {
                        SendMessagesHelper.getInstance().sendMessage(textFinal.substring(a * 4096, Math.min((a + 1) * 4096, textFinal.length())), SendMessagesHelper$18.this.val$dialog_id, null, null, true, null, null, null);
                    }
                }
            }
        }

        C51761() {
        }

        public void run() {
            AndroidUtilities.runOnUIThread(new C51751());
        }
    }

    SendMessagesHelper$18(String str, long j) {
        this.val$text = str;
        this.val$dialog_id = j;
    }

    public void run() {
        Utilities.stageQueue.postRunnable(new C51761());
    }
}
