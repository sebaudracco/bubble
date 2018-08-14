package com.appsgeyser.sdk.admobutils;

import android.util.Log;
import com.adcolony.sdk.AdColonyUserMetadata;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class AdMobParameters {
    private static final String TAG = AdMobParameters.class.getSimpleName();
    private Date birthday;
    private int gender;
    private Set<String> keywords;
    private Double latitude;
    private Double longitude;
    private String publisherId;

    AdMobParameters() {
    }

    public AdMobParameters(String publisherId, String keywords, String gender, String birthday, String latitude, String longtitude) {
        this.publisherId = "";
        if (publisherId != null) {
            this.publisherId = publisherId;
        }
        this.keywords = null;
        if (keywords != null) {
            String[] keywordsParts = keywords.split(",");
            this.keywords = new HashSet(keywordsParts.length);
            for (String word : keywordsParts) {
                this.keywords.add(word.trim());
            }
        }
        this.gender = 0;
        if (gender != null) {
            if (gender.compareToIgnoreCase(AdColonyUserMetadata.USER_MALE) == 0) {
                this.gender = 1;
            } else if (gender.compareToIgnoreCase(AdColonyUserMetadata.USER_FEMALE) == 0) {
                this.gender = 2;
            }
        }
        if (birthday != null) {
            try {
                this.birthday = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(birthday);
            } catch (Exception e) {
                this.birthday = null;
                if (e.getMessage() != null) {
                    Log.e(TAG, e.getMessage());
                }
            }
        }
        this.latitude = null;
        this.longitude = null;
        if (latitude != null && longtitude != null) {
            try {
                this.latitude = Double.valueOf(Double.parseDouble(latitude));
                this.longitude = Double.valueOf(Double.parseDouble(longtitude));
            } catch (NumberFormatException e2) {
                this.latitude = null;
                this.longitude = null;
                if (e2.getMessage() != null) {
                    Log.e(TAG, e2.getMessage());
                } else {
                    e2.printStackTrace();
                }
            }
        }
    }

    public String getPublisherId() {
        return this.publisherId;
    }

    void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    Set<String> getKeywords() {
        return this.keywords;
    }

    void setKeywords(Set<String> keywords) {
        this.keywords = keywords;
    }

    int getGender() {
        return this.gender;
    }

    void setGender(int gender) {
        this.gender = gender;
    }

    Date getBirthday() {
        return this.birthday;
    }

    void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    Double getLatitude() {
        return this.latitude;
    }

    Double getLongitude() {
        return this.longitude;
    }

    void setCoordinates(Double latitude, Double longtitude) {
        this.latitude = latitude;
        this.longitude = longtitude;
    }
}
