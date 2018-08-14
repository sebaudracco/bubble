package com.vungle.publisher;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

/* compiled from: vungle */
public class og extends LinearLayout implements OnClickListener {
    private qg f10853a;
    private boolean f10854b = false;
    private TextView f10855c;

    public og(Context context) {
        super(context);
        setOnClickListener(this);
    }

    public void onClick(View arg0) {
        if (this.f10854b) {
            this.f10853a.a(new aq());
            return;
        }
        this.f10854b = true;
        m13781a();
    }

    private void m13781a() {
        setBackgroundColor(Color.parseColor("#000000"));
        this.f10855c.setVisibility(0);
    }
}
