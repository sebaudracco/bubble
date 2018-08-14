package com.jaredrummler.android.processes.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;

public final class Statm extends ProcFile {
    public static final Creator<Statm> CREATOR = new C33501();
    public final String[] fields;

    static class C33501 implements Creator<Statm> {
        C33501() {
        }

        public Statm createFromParcel(Parcel source) {
            return new Statm(source);
        }

        public Statm[] newArray(int size) {
            return new Statm[size];
        }
    }

    public static Statm get(int pid) throws IOException {
        return new Statm(String.format("/proc/%d/statm", new Object[]{Integer.valueOf(pid)}));
    }

    private Statm(String path) throws IOException {
        super(path);
        this.fields = this.content.split("\\s+");
    }

    private Statm(Parcel in) {
        super(in);
        this.fields = in.createStringArray();
    }

    public long getSize() {
        return Long.parseLong(this.fields[0]) * PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
    }

    public long getResidentSetSize() {
        return Long.parseLong(this.fields[1]) * PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeStringArray(this.fields);
    }
}
