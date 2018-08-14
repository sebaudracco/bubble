package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_langPackDifference;

class LocaleController$5 implements RequestDelegate {
    final /* synthetic */ LocaleController this$0;

    LocaleController$5(LocaleController this$0) {
        this.this$0 = this$0;
    }

    public void run(final TLObject response, TLRPC$TL_error error) {
        if (response != null) {
            AndroidUtilities.runOnUIThread(new Runnable() {
                public void run() {
                    LocaleController$5.this.this$0.saveRemoteLocaleStrings((TLRPC$TL_langPackDifference) response);
                }
            });
        }
    }
}
