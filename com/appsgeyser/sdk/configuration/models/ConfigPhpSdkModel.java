package com.appsgeyser.sdk.configuration.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;

public class ConfigPhpSdkModel implements Parcelable {
    public static final Creator<ConfigPhpSdkModel> CREATOR = new C12931();
    private boolean active;
    private boolean activeByDefault;
    private String id;
    private String tag;
    private String textOfPolicy;

    static class C12931 implements Creator<ConfigPhpSdkModel> {
        C12931() {
        }

        public ConfigPhpSdkModel createFromParcel(Parcel in) {
            return new ConfigPhpSdkModel(in);
        }

        public ConfigPhpSdkModel[] newArray(int size) {
            return new ConfigPhpSdkModel[size];
        }
    }

    public ConfigPhpSdkModel(boolean active, String id, boolean activeByDefault, String textOfPolicy, String tag) {
        this.active = active;
        this.id = id;
        this.activeByDefault = activeByDefault;
        this.textOfPolicy = textOfPolicy;
        this.tag = tag;
    }

    private ConfigPhpSdkModel(Parcel in) {
        boolean z = true;
        this.active = in.readByte() != (byte) 0;
        this.id = in.readString();
        if (in.readByte() == (byte) 0) {
            z = false;
        }
        this.activeByDefault = z;
        this.textOfPolicy = in.readString();
        this.tag = in.readString();
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Nullable
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isActiveByDefault() {
        return this.activeByDefault;
    }

    public void setActiveByDefault(boolean activeByDefault) {
        this.activeByDefault = activeByDefault;
    }

    @Nullable
    public String getTextOfPolicy() {
        return this.textOfPolicy;
    }

    public void setTextOfPolicy(String textOfPolicy) {
        this.textOfPolicy = textOfPolicy;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = 1;
        parcel.writeByte((byte) (this.active ? 1 : 0));
        parcel.writeString(this.id);
        if (!this.activeByDefault) {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        parcel.writeString(this.textOfPolicy);
        parcel.writeString(this.tag);
    }
}
