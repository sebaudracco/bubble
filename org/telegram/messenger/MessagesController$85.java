package org.telegram.messenger;

import android.app.Activity;
import android.content.Context;
import com.wBubble_7453037.R;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$Updates;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.AlertDialog.Builder;

class MessagesController$85 implements RequestDelegate {
    final /* synthetic */ MessagesController this$0;
    final /* synthetic */ Context val$context;
    final /* synthetic */ AlertDialog val$progressDialog;

    class C50451 implements Runnable {
        C50451() {
        }

        public void run() {
            if (!((Activity) MessagesController$85.this.val$context).isFinishing()) {
                try {
                    MessagesController$85.this.val$progressDialog.dismiss();
                } catch (Exception e) {
                    FileLog.e(e);
                }
            }
        }
    }

    class C50462 implements Runnable {
        C50462() {
        }

        public void run() {
            if (!((Activity) MessagesController$85.this.val$context).isFinishing()) {
                try {
                    MessagesController$85.this.val$progressDialog.dismiss();
                } catch (Exception e) {
                    FileLog.e(e);
                }
                Builder builder = new Builder(MessagesController$85.this.val$context);
                builder.setTitle(ApplicationLoader.getConfig().getAppName());
                builder.setMessage(LocaleController.getString("ErrorOccurred", R.string.ErrorOccurred));
                builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), null);
                builder.show().setCanceledOnTouchOutside(true);
            }
        }
    }

    MessagesController$85(MessagesController this$0, Context context, AlertDialog alertDialog) {
        this.this$0 = this$0;
        this.val$context = context;
        this.val$progressDialog = alertDialog;
    }

    public void run(TLObject response, TLRPC$TL_error error) {
        if (error == null) {
            AndroidUtilities.runOnUIThread(new C50451());
            TLRPC$Updates updates = (TLRPC$Updates) response;
            this.this$0.processUpdates((TLRPC$Updates) response, false);
            return;
        }
        AndroidUtilities.runOnUIThread(new C50462());
    }
}
