package com.facebook.ads.internal.view.p053e.p085c;

import android.content.Context;
import android.widget.TextView;
import com.facebook.ads.internal.p052j.C1839f;
import com.facebook.ads.internal.view.p053e.p054b.C2236n;
import com.facebook.ads.internal.view.p053e.p083a.C2224c;
import java.util.concurrent.TimeUnit;

public class C2272c extends C2224c {
    private final TextView f5497a;
    private final String f5498b;
    private final C1839f<C2236n> f5499c = new C22711(this);

    class C22711 extends C1839f<C2236n> {
        final /* synthetic */ C2272c f5496a;

        C22711(C2272c c2272c) {
            this.f5496a = c2272c;
        }

        public Class<C2236n> mo3580a() {
            return C2236n.class;
        }

        public void m7172a(C2236n c2236n) {
            if (this.f5496a.getVideoView() != null) {
                this.f5496a.f5497a.setText(this.f5496a.m7174a((long) (this.f5496a.getVideoView().getDuration() - this.f5496a.getVideoView().getCurrentPosition())));
            }
        }
    }

    public C2272c(Context context, String str) {
        super(context);
        this.f5497a = new TextView(context);
        this.f5498b = str;
        addView(this.f5497a);
    }

    private String m7174a(long j) {
        if (j <= 0) {
            return "00:00";
        }
        long toMinutes = TimeUnit.MILLISECONDS.toMinutes(j);
        long toSeconds = TimeUnit.MILLISECONDS.toSeconds(j % 60000);
        if (this.f5498b.isEmpty()) {
            return String.format("%02d:%02d", new Object[]{Long.valueOf(toMinutes), Long.valueOf(toSeconds)});
        }
        return this.f5498b.replace("{{REMAINING_TIME}}", String.format("%02d:%02d", new Object[]{Long.valueOf(toMinutes), Long.valueOf(toSeconds)}));
    }

    protected void mo3787a() {
        super.mo3787a();
        if (getVideoView() != null) {
            getVideoView().getEventBus().m6329a(this.f5499c);
        }
    }

    protected void mo3788b() {
        if (getVideoView() != null) {
            getVideoView().getEventBus().m6331b(this.f5499c);
        }
        super.mo3788b();
    }

    public void setCountdownTextColor(int i) {
        this.f5497a.setTextColor(i);
    }
}
