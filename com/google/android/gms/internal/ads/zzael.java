package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONArray;
import org.json.JSONObject;

@zzadh
@Class(creator = "AutoClickProtectionConfigurationParcelCreator")
@Reserved({1})
@ParametersAreNonnullByDefault
public final class zzael extends AbstractSafeParcelable {
    public static final Creator<zzael> CREATOR = new zzaem();
    @Field(id = 2)
    public final boolean zzcfr;
    @Nullable
    @Field(id = 3)
    public final List<String> zzcfs;

    public zzael() {
        this(false, Collections.emptyList());
    }

    @Constructor
    public zzael(@Param(id = 2) boolean z, @Param(id = 3) List<String> list) {
        this.zzcfr = z;
        this.zzcfs = list;
    }

    @Nullable
    public static zzael zzl(JSONObject jSONObject) {
        if (jSONObject == null) {
            return new zzael();
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("reporting_urls");
        List arrayList = new ArrayList();
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                try {
                    arrayList.add(optJSONArray.getString(i));
                } catch (Throwable e) {
                    zzane.zzc("Error grabbing url from json.", e);
                }
            }
        }
        return new zzael(jSONObject.optBoolean("enable_protection"), arrayList);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzcfr);
        SafeParcelWriter.writeStringList(parcel, 3, this.zzcfs, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
