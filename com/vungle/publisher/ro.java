package com.vungle.publisher;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/* compiled from: vungle */
public class ro extends RelativeLayout {
    private ImageView f10992a;

    /* compiled from: vungle */
    public enum C4248b {
        visible,
        invisible,
        gone
    }

    private ro(Context context) {
        super(context);
    }

    public void setCloseVisibility(C4248b visibility) {
        switch (visibility) {
            case visible:
                this.f10992a.setVisibility(0);
                setVisibility(0);
                return;
            case invisible:
                this.f10992a.setVisibility(4);
                setVisibility(0);
                return;
            case gone:
                setVisibility(8);
                return;
            default:
                return;
        }
    }
}
