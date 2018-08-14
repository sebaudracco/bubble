package com.adcolony.sdk;

import android.location.Location;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import org.json.JSONArray;
import org.json.JSONObject;

public class AdColonyUserMetadata {
    public static final String USER_EDUCATION_ASSOCIATES_DEGREE = "associates_degree";
    public static final String USER_EDUCATION_BACHELORS_DEGREE = "bachelors_degree";
    public static final String USER_EDUCATION_GRADE_SCHOOL = "grade_school";
    public static final String USER_EDUCATION_GRADUATE_DEGREE = "graduate_degree";
    public static final String USER_EDUCATION_HIGH_SCHOOL_DIPLOMA = "high_school_diploma";
    public static final String USER_EDUCATION_SOME_COLLEGE = "some_college";
    public static final String USER_EDUCATION_SOME_HIGH_SCHOOL = "some_high_school";
    public static final String USER_FEMALE = "female";
    public static final String USER_MALE = "male";
    public static final String USER_MARRIED = "married";
    public static final String USER_SINGLE = "single";
    static final int f447a = 128;
    JSONArray f448b = C0802y.m1469b();
    JSONObject f449c = C0802y.m1453a();
    Location f450d;

    public AdColonyUserMetadata setUserGender(@NonNull String gender) {
        if (az.m894d(gender)) {
            setMetadata("adc_gender", gender);
        }
        return this;
    }

    public String getUserGender() {
        return C0802y.m1468b(this.f449c, "adc_gender");
    }

    public AdColonyUserMetadata setUserAge(@IntRange(from = 0, to = 130) int age) {
        setMetadata("adc_age", (double) age);
        return this;
    }

    public int getUserAge() {
        return C0802y.m1473c(this.f449c, "adc_age");
    }

    public AdColonyUserMetadata setUserMaritalStatus(@NonNull String status) {
        if (az.m894d(status)) {
            setMetadata("adc_marital_status", status);
        }
        return this;
    }

    public String getUserMaritalStatus() {
        return C0802y.m1468b(this.f449c, "adc_marital_status");
    }

    public AdColonyUserMetadata setUserAnnualHouseholdIncome(@IntRange(from = 0) int income) {
        setMetadata("adc_household_income", (double) income);
        return this;
    }

    public int getUserAnnualHouseholdIncome() {
        return C0802y.m1473c(this.f449c, "adc_household_income");
    }

    public AdColonyUserMetadata setUserEducation(@NonNull String education) {
        if (az.m894d(education)) {
            setMetadata("adc_education", education);
        }
        return this;
    }

    public String getUserEducation() {
        return C0802y.m1468b(this.f449c, "adc_education");
    }

    public AdColonyUserMetadata setUserZipCode(@NonNull String zip) {
        if (az.m894d(zip)) {
            setMetadata("adc_zip", zip);
        }
        return this;
    }

    public String getUserZipCode() {
        return C0802y.m1468b(this.f449c, "adc_zip");
    }

    public AdColonyUserMetadata setUserLocation(@NonNull Location location) {
        this.f450d = location;
        setMetadata("adc_longitude", location.getLongitude());
        setMetadata("adc_latitude", location.getLatitude());
        setMetadata("adc_speed", (double) location.getSpeed());
        setMetadata("adc_altitude", location.getAltitude());
        setMetadata("adc_time", (double) location.getTime());
        setMetadata("adc_accuracy", (double) location.getAccuracy());
        return this;
    }

    public Location getUserLocation() {
        return this.f450d;
    }

    public AdColonyUserMetadata addUserInterest(@NonNull String interest) {
        if (az.m894d(interest)) {
            C0802y.m1458a(this.f448b, interest);
            C0802y.m1463a(this.f449c, "adc_interests", this.f448b);
        }
        return this;
    }

    public AdColonyUserMetadata clearUserInterests() {
        this.f448b = C0802y.m1469b();
        C0802y.m1463a(this.f449c, "adc_interests", this.f448b);
        return this;
    }

    public String[] getUserInterests() {
        String[] strArr = new String[this.f448b.length()];
        for (int i = 0; i < this.f448b.length(); i++) {
            strArr[i] = C0802y.m1474c(this.f448b, i);
        }
        return strArr;
    }

    public AdColonyUserMetadata setMetadata(@NonNull String key, boolean value) {
        if (az.m894d(key)) {
            C0802y.m1465a(this.f449c, key, value);
        }
        return this;
    }

    public Object getMetadata(@NonNull String key) {
        return C0802y.m1450a(this.f449c, key);
    }

    public AdColonyUserMetadata setMetadata(@NonNull String key, double value) {
        if (az.m894d(key)) {
            C0802y.m1460a(this.f449c, key, value);
        }
        return this;
    }

    public AdColonyUserMetadata setMetadata(@NonNull String key, @NonNull String value) {
        if (az.m894d(value) && az.m894d(key)) {
            C0802y.m1462a(this.f449c, key, value);
        }
        return this;
    }
}
