package org.telegram.messenger;

class FileLog$2 implements Runnable {
    final /* synthetic */ String val$message;

    FileLog$2(String str) {
        this.val$message = str;
    }

    public void run() {
        try {
            FileLog.access$100(FileLog.getInstance()).write(FileLog.access$000(FileLog.getInstance()).format(System.currentTimeMillis()) + " E/tmessages: " + this.val$message + "\n");
            FileLog.access$100(FileLog.getInstance()).flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
