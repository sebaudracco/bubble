package com.inmobi.ads;

import android.content.ContentValues;
import android.support.annotation.NonNull;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.commons.core.p115d.C3132b;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.mopub.mobileads.BaseVideoPlayerActivity;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: VideoV2Ad */
class bq extends C2968a {
    private static final String f7306a = bq.class.getSimpleName();
    private final String f7307b;
    private final String f7308c;
    private final String f7309d;
    private final String f7310e;
    private final String f7311f;

    public bq(JSONObject jSONObject, String str, long j, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, MonetizationContext monetizationContext) {
        super(jSONObject, str, j, str2, str3, str4, str5, monetizationContext);
        this.f7307b = str6;
        this.f7308c = str7;
        this.f7309d = str8;
        this.f7310e = str9;
        this.f7311f = str10;
    }

    public bq(ContentValues contentValues) {
        super(contentValues);
        this.f7307b = contentValues.getAsString(BaseVideoPlayerActivity.VIDEO_URL);
        this.f7308c = contentValues.getAsString("video_track_duration");
        this.f7309d = contentValues.getAsString("click_url");
        this.f7310e = contentValues.getAsString("video_trackers");
        this.f7311f = contentValues.getAsString("companion_ads");
    }

    public ContentValues mo6224a() {
        ContentValues a = super.mo6224a();
        a.put(BaseVideoPlayerActivity.VIDEO_URL, this.f7307b);
        a.put("video_track_duration", this.f7308c);
        a.put("click_url", this.f7309d);
        a.put("video_trackers", this.f7310e);
        a.put("companion_ads", this.f7311f);
        return a;
    }

    public String m9583j() {
        return this.f7307b;
    }

    public String m9584k() {
        return this.f7308c;
    }

    public String m9585l() {
        return this.f7309d;
    }

    @NonNull
    public List<ah> m9586m() {
        List<ah> arrayList = new ArrayList();
        if (!(this.f7310e == null || this.f7310e.length() == 0)) {
            try {
                JSONArray jSONArray = new JSONArray(this.f7310e);
                if (jSONArray.length() != 0) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        ah a = ah.m9289a(new JSONObject(jSONArray.getString(i)));
                        if (a != null) {
                            arrayList.add(a);
                        }
                    }
                }
            } catch (Throwable e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7306a, "Error getting trackers");
                C3135c.m10255a().m10279a(new C3132b(e));
            }
        }
        return arrayList;
    }

    @NonNull
    public List<bl> m9587n() {
        List<bl> arrayList = new ArrayList();
        if (!(this.f7311f == null || this.f7311f.length() == 0)) {
            try {
                JSONArray jSONArray = new JSONArray(this.f7311f);
                if (jSONArray.length() != 0) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        bl a = bl.m9524a(new JSONObject(jSONArray.getString(i)));
                        if (a != null) {
                            arrayList.add(a);
                        }
                    }
                }
            } catch (Throwable e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7306a, "Error getting companion ads");
                C3135c.m10255a().m10279a(new C3132b(e));
            }
        }
        return arrayList;
    }
}
