package com.vungle.publisher;

import android.database.Cursor;
import android.support.v4.app.NotificationCompat;
import com.vungle.publisher.eh.a;
import com.vungle.publisher.log.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: vungle */
public abstract class ji extends eh {

    /* compiled from: vungle */
    public enum C4223a implements jf {
        error,
        mute,
        moat,
        play_percentage_0((String) 0.0f),
        play_percentage_25((String) 0.25f),
        play_percentage_50((String) 0.5f),
        play_percentage_75((String) 0.75f),
        play_percentage_80((String) 0.8f),
        play_percentage_100((String) 0.99f),
        postroll_click((String) true),
        postroll_view,
        unmute,
        video_click((String) true),
        video_close,
        video_pause,
        video_resume;
        
        private final float f10557q;
        private final boolean f10558r;

        private C4223a(float f) {
            this(r2, r3, f, false);
        }

        private C4223a(boolean z) {
            this(r2, r3, -1.0f, z);
        }

        private C4223a(float f, boolean z) {
            this.f10557q = f;
            this.f10558r = z;
        }

        public boolean mo6944a() {
            return this.f10558r;
        }

        public float m13528b() {
            return this.f10557q;
        }
    }

    /* compiled from: vungle */
    static abstract class C4224b<R extends wp> extends a<ji, wv, R> {
        C4224b() {
        }

        protected /* synthetic */ dp[] m13535d(int i) {
            return m13534c(i);
        }

        Map<jf, List<ji>> m13533a(String str, wv wvVar) {
            Map<jf, List<ji>> map = null;
            if (wvVar != null) {
                map = new HashMap();
                a(str, map, C4223a.error, wvVar.m14104g());
                a(str, map, C4223a.mute, wvVar.m14105h());
                wa[] j = wvVar.m14107j();
                if (j != null && j.length > 0) {
                    for (wa waVar : j) {
                        Float c = waVar.m14026c();
                        if (c != null) {
                            switch ((int) (((double) c.floatValue()) * 100.0d)) {
                                case 0:
                                    a(str, map, C4223a.play_percentage_0, waVar.m14027d());
                                    break;
                                case 25:
                                    a(str, map, C4223a.play_percentage_25, waVar.m14027d());
                                    break;
                                case 50:
                                    a(str, map, C4223a.play_percentage_50, waVar.m14027d());
                                    break;
                                case 75:
                                    a(str, map, C4223a.play_percentage_75, waVar.m14027d());
                                    break;
                                case 100:
                                    a(str, map, C4223a.play_percentage_100, waVar.m14027d());
                                    break;
                                default:
                                    Logger.m13647w(Logger.DATABASE_TAG, "Invalid/unsupported play percentage checkpoint: " + c);
                                    break;
                            }
                        }
                        Logger.m13647w(Logger.DATABASE_TAG, "Invalid/null play percentage checkpoint received");
                    }
                }
                a(str, map, C4223a.postroll_click, wvVar.m14101c());
                a(str, map, C4223a.postroll_view, wvVar.m14108k());
                a(str, map, C4223a.video_click, wvVar.m14102d());
                a(str, map, C4223a.video_close, wvVar.m14103f());
                a(str, map, C4223a.video_pause, wvVar.m14106i());
                a(str, map, C4223a.video_resume, wvVar.m14109l());
                a(str, map, C4223a.unmute, wvVar.m14110m());
                if (wvVar.m14111n().booleanValue()) {
                    List arrayList = new ArrayList();
                    arrayList.add(wvVar.m14112o());
                    a(str, map, C4223a.moat, arrayList);
                }
            }
            return map;
        }

        protected ji m13531a(ji jiVar, Cursor cursor, boolean z) {
            super.a(jiVar, cursor, z);
            jiVar.c = (jf) ce.a(cursor, NotificationCompat.CATEGORY_EVENT, C4223a.class);
            return jiVar;
        }

        protected ji[] m13534c(int i) {
            return new ji[i];
        }
    }

    protected ji() {
    }
}
