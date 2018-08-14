package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import org.json.JSONArray;
import org.json.JSONException;

@zzadh
@Class(creator = "RewardItemParcelCreator")
@Reserved({1})
public final class zzaig extends AbstractSafeParcelable {
    public static final Creator<zzaig> CREATOR = new zzaih();
    @Field(id = 2)
    public final String type;
    @Field(id = 3)
    public final int zzcmk;

    public zzaig(RewardItem rewardItem) {
        this(rewardItem.getType(), rewardItem.getAmount());
    }

    @Constructor
    public zzaig(@Param(id = 2) String str, @Param(id = 3) int i) {
        this.type = str;
        this.zzcmk = i;
    }

    @Nullable
    public static zzaig zza(JSONArray jSONArray) throws JSONException {
        return (jSONArray == null || jSONArray.length() == 0) ? null : new zzaig(jSONArray.getJSONObject(0).optString("rb_type"), jSONArray.getJSONObject(0).optInt("rb_amount"));
    }

    @Nullable
    public static zzaig zzce(@Nullable String str) {
        zzaig com_google_android_gms_internal_ads_zzaig = null;
        if (!TextUtils.isEmpty(str)) {
            try {
                com_google_android_gms_internal_ads_zzaig = zza(new JSONArray(str));
            } catch (JSONException e) {
            }
        }
        return com_google_android_gms_internal_ads_zzaig;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof zzaig)) {
            return false;
        }
        zzaig com_google_android_gms_internal_ads_zzaig = (zzaig) obj;
        return Objects.equal(this.type, com_google_android_gms_internal_ads_zzaig.type) && Objects.equal(Integer.valueOf(this.zzcmk), Integer.valueOf(com_google_android_gms_internal_ads_zzaig.zzcmk));
    }

    public final int hashCode() {
        return Objects.hashCode(this.type, Integer.valueOf(this.zzcmk));
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.type, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzcmk);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
