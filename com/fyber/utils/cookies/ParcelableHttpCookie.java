package com.fyber.utils.cookies;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fyber.utils.StringUtils;
import java.net.HttpCookie;

public class ParcelableHttpCookie implements Parcelable {
    public static final Creator<ParcelableHttpCookie> CREATOR = new C26431();
    private HttpCookie f6583a;

    static class C26431 implements Creator<ParcelableHttpCookie> {
        C26431() {
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new ParcelableHttpCookie[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new ParcelableHttpCookie(parcel);
        }
    }

    public ParcelableHttpCookie(Parcel parcel) {
        boolean z = true;
        String readString = parcel.readString();
        if (StringUtils.notNullNorEmpty(readString)) {
            this.f6583a = new HttpCookie(readString, parcel.readString());
            this.f6583a.setComment(parcel.readString());
            this.f6583a.setCommentURL(parcel.readString());
            this.f6583a.setDiscard(parcel.readByte() != (byte) 0);
            this.f6583a.setDomain(parcel.readString());
            this.f6583a.setMaxAge(parcel.readLong());
            this.f6583a.setPath(parcel.readString());
            this.f6583a.setPortlist(parcel.readString());
            HttpCookie httpCookie = this.f6583a;
            if (parcel.readByte() == (byte) 0) {
                z = false;
            }
            httpCookie.setSecure(z);
            this.f6583a.setVersion(parcel.readInt());
        }
    }

    public final HttpCookie m8467a() {
        return this.f6583a;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = 1;
        parcel.writeString(this.f6583a.getName());
        parcel.writeString(this.f6583a.getValue());
        parcel.writeString(this.f6583a.getComment());
        parcel.writeString(this.f6583a.getCommentURL());
        parcel.writeByte((byte) (this.f6583a.getDiscard() ? 1 : 0));
        parcel.writeString(this.f6583a.getDomain());
        parcel.writeLong(this.f6583a.getMaxAge());
        parcel.writeString(this.f6583a.getPath());
        parcel.writeString(this.f6583a.getPortlist());
        if (!this.f6583a.getSecure()) {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        parcel.writeInt(this.f6583a.getVersion());
    }
}
