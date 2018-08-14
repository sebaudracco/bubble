package com.google.ads.mediation;

import android.location.Location;
import com.google.ads.AdRequest.Gender;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@Deprecated
public class MediationAdRequest {
    private final Date zzhl;
    private final Gender zzhm;
    private final Set<String> zzhn;
    private final boolean zzho;
    private final Location zzhp;

    public MediationAdRequest(Date date, Gender gender, Set<String> set, boolean z, Location location) {
        this.zzhl = date;
        this.zzhm = gender;
        this.zzhn = set;
        this.zzho = z;
        this.zzhp = location;
    }

    public Integer getAgeInYears() {
        if (this.zzhl == null) {
            return null;
        }
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance.setTime(this.zzhl);
        Integer valueOf = Integer.valueOf(instance2.get(1) - instance.get(1));
        return (instance2.get(2) < instance.get(2) || (instance2.get(2) == instance.get(2) && instance2.get(5) < instance.get(5))) ? Integer.valueOf(valueOf.intValue() - 1) : valueOf;
    }

    public Date getBirthday() {
        return this.zzhl;
    }

    public Gender getGender() {
        return this.zzhm;
    }

    public Set<String> getKeywords() {
        return this.zzhn;
    }

    public Location getLocation() {
        return this.zzhp;
    }

    public boolean isTesting() {
        return this.zzho;
    }
}
