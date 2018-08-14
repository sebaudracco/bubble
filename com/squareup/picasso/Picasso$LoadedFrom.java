package com.squareup.picasso;

import android.support.v4.internal.view.SupportMenu;

public enum Picasso$LoadedFrom {
    MEMORY(-16711936),
    DISK(-16776961),
    NETWORK(SupportMenu.CATEGORY_MASK);
    
    final int debugColor;

    private Picasso$LoadedFrom(int debugColor) {
        this.debugColor = debugColor;
    }
}
