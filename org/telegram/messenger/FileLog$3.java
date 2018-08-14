package org.telegram.messenger;

class FileLog$3 implements Runnable {
    final /* synthetic */ Throwable val$e;

    FileLog$3(Throwable th) {
        this.val$e = th;
    }

    public void run() {
        try {
            FileLog.access$100(FileLog.getInstance()).write(FileLog.access$000(FileLog.getInstance()).format(System.currentTimeMillis()) + " E/tmessages: " + this.val$e + "\n");
            StackTraceElement[] stack = this.val$e.getStackTrace();
            for (Object obj : stack) {
                FileLog.access$100(FileLog.getInstance()).write(FileLog.access$000(FileLog.getInstance()).format(System.currentTimeMillis()) + " E/tmessages: " + obj + "\n");
            }
            FileLog.access$100(FileLog.getInstance()).flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
