package com.jirbo.adcolony;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyAppOptions;
import com.adcolony.sdk.AdColonyUserMetadata;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

class AdColonyManager {
    private static AdColonyManager _instance = null;
    private ArrayList<String> _configuredListOfZones = new ArrayList();
    private Context _context = null;
    private boolean _isConfigured = false;
    boolean rewardedAdsConfigured = false;

    private AdColonyManager() {
    }

    static AdColonyManager getInstance() {
        if (_instance == null) {
            _instance = new AdColonyManager();
        }
        return _instance;
    }

    void onDestroy() {
        this._context = null;
    }

    boolean configureAdColony(Context context, Bundle serverParams, MediationAdRequest adRequest, Bundle networkExtras) {
        String appId = serverParams.getString("app_id");
        ArrayList<String> newZoneList = parseZoneList(serverParams);
        boolean needToConfigure = false;
        if (context != null) {
            this._context = context;
        }
        if (this._context != null && !(this._context instanceof Activity)) {
            Log.w("AdColonyAdapter", "Context must be of type Activity.");
            return false;
        } else if (appId == null) {
            Log.w("AdColonyAdapter", "A valid appId wasn't provided.");
            return false;
        } else if (newZoneList == null || newZoneList.isEmpty()) {
            Log.w("AdColonyAdapter", "No zones provided to request ad.");
            return false;
        } else {
            Iterator it = newZoneList.iterator();
            while (it.hasNext()) {
                String zone = (String) it.next();
                if (!this._configuredListOfZones.contains(zone)) {
                    this._configuredListOfZones.add(zone);
                    needToConfigure = true;
                }
            }
            AdColonyAppOptions appOptions = buildAppOptions(adRequest, networkExtras);
            if (!this._isConfigured || needToConfigure) {
                String[] zones = (String[]) this._configuredListOfZones.toArray(new String[this._configuredListOfZones.size()]);
                if (appOptions == null) {
                    appOptions = new AdColonyAppOptions();
                }
                appOptions.setMediationNetwork("AdMob", BuildConfig.VERSION_NAME);
                this._isConfigured = AdColony.configure((Activity) this._context, appOptions, appId, zones);
            } else if (appOptions != null) {
                AdColony.setAppOptions(appOptions);
            }
            return this._isConfigured;
        }
    }

    private AdColonyAppOptions buildAppOptions(MediationAdRequest adRequest, Bundle networkExtras) {
        AdColonyAppOptions options = new AdColonyAppOptions();
        boolean updatedOptions = false;
        if (networkExtras != null) {
            String userId = networkExtras.getString("user_id");
            if (userId != null) {
                options.setUserID(userId);
                updatedOptions = true;
            }
            if (networkExtras.containsKey("test_mode")) {
                options.setTestModeEnabled(networkExtras.getBoolean("test_mode"));
                updatedOptions = true;
            }
        }
        if (adRequest != null) {
            AdColonyUserMetadata userMetadata = new AdColonyUserMetadata();
            int genderVal = adRequest.getGender();
            if (genderVal == 2) {
                updatedOptions = true;
                userMetadata.setUserGender(AdColonyUserMetadata.USER_FEMALE);
            } else if (genderVal == 1) {
                updatedOptions = true;
                userMetadata.setUserGender(AdColonyUserMetadata.USER_MALE);
            }
            Location location = adRequest.getLocation();
            if (location != null) {
                updatedOptions = true;
                userMetadata.setUserLocation(location);
            }
            Date birthday = adRequest.getBirthday();
            if (birthday != null) {
                long diff = System.currentTimeMillis() - birthday.getTime();
                if (diff > 0) {
                    updatedOptions = true;
                    userMetadata.setUserAge((int) ((diff / 86400000) / 365));
                }
            }
            options.setUserMetadata(userMetadata);
        }
        return updatedOptions ? options : null;
    }

    ArrayList<String> parseZoneList(Bundle serverParams) {
        if (serverParams == null) {
            return null;
        }
        String requestedZones = serverParams.getString("zone_ids");
        if (requestedZones != null) {
            return new ArrayList(Arrays.asList(requestedZones.split(";")));
        }
        return null;
    }

    String getZoneFromRequest(ArrayList<String> serverListOfZones, Bundle adRequestParams) {
        String requestedZone = null;
        if (!(serverListOfZones == null || serverListOfZones.isEmpty())) {
            requestedZone = (String) serverListOfZones.get(0);
        }
        if (adRequestParams == null || adRequestParams.getString("zone_id") == null) {
            return requestedZone;
        }
        return adRequestParams.getString("zone_id");
    }
}
