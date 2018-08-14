package org.telegram.messenger;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import cz.msebera.android.httpclient.HttpStatus;
import org.telegram.ui.ActionBar.BaseFragment;

class AndroidUtilities$1 implements OnClickListener {
    final /* synthetic */ BaseFragment val$fragment;

    AndroidUtilities$1(BaseFragment baseFragment) {
        this.val$fragment = baseFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            this.val$fragment.getParentActivity().startActivityForResult(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.google.android.apps.maps")), HttpStatus.SC_INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            FileLog.e(e);
        }
    }
}
