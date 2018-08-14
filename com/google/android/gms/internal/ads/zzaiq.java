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
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
@Class(creator = "SafeBrowsingConfigParcelCreator")
@Reserved({1})
public final class zzaiq extends AbstractSafeParcelable {
    public static final Creator<zzaiq> CREATOR = new zzair();
    @Field(id = 2)
    public final String zzcnd;
    @Field(id = 3)
    public final String zzcne;
    @Field(id = 4)
    public final boolean zzcnf;
    @Field(id = 5)
    public final boolean zzcng;
    @Field(id = 6)
    public final List<String> zzcnh;
    @Field(id = 7)
    public final boolean zzcni;
    @Field(id = 8)
    public final boolean zzcnj;
    @Field(id = 9)
    public final List<String> zzcnk;

    @Constructor
    public zzaiq(@Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) boolean z, @Param(id = 5) boolean z2, @Param(id = 6) List<String> list, @Param(id = 7) boolean z3, @Param(id = 8) boolean z4, @Param(id = 9) List<String> list2) {
        List arrayList;
        this.zzcnd = str;
        this.zzcne = str2;
        this.zzcnf = z;
        this.zzcng = z2;
        this.zzcnh = list;
        this.zzcni = z3;
        this.zzcnj = z4;
        if (list2 == null) {
            arrayList = new ArrayList();
        }
        this.zzcnk = arrayList;
    }

    @Nullable
    public static zzaiq zzo(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        return new zzaiq(jSONObject.optString("click_string", ""), jSONObject.optString("report_url", ""), jSONObject.optBoolean("rendered_ad_enabled", false), jSONObject.optBoolean("non_malicious_reporting_enabled", false), zzamd.zza(jSONObject.optJSONArray("allowed_headers"), null), jSONObject.optBoolean("protection_enabled", false), jSONObject.optBoolean("malicious_reporting_enabled", false), zzamd.zza(jSONObject.optJSONArray("webview_permissions"), null));
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzcnd, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzcne, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzcnf);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzcng);
        SafeParcelWriter.writeStringList(parcel, 6, this.zzcnh, false);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzcni);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzcnj);
        SafeParcelWriter.writeStringList(parcel, 9, this.zzcnk, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
