package org.telegram.messenger;

class LocaleController$2 implements Runnable {
    final /* synthetic */ LocaleController this$0;

    LocaleController$2(LocaleController this$0) {
        this.this$0 = this$0;
    }

    public void run() {
        this.this$0.reloadCurrentRemoteLocale();
    }
}
