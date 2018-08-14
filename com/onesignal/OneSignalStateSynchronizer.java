package com.onesignal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.HandlerThread;
import com.appnext.base.p023b.C1048i;
import com.onesignal.OneSignal.LOG_LEVEL;
import cz.msebera.android.httpclient.HttpStatus;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class OneSignalStateSynchronizer {
    private static final String[] LOCATION_FIELDS = new String[]{C1048i.ko, SchemaSymbols.ATTVAL_LONG, "loc_acc", "loc_type", "loc_bg", "ad_id"};
    private static final Set<String> LOCATION_FIELDS_SET = new HashSet(Arrays.asList(LOCATION_FIELDS));
    private static Context appContext;
    private static UserState currentUserState;
    private static final Object networkHandlerSyncLock = new C39101();
    static HashMap<Integer, NetworkHandlerThread> networkHandlerThreads = new HashMap();
    private static boolean nextSyncIsSession = false;
    private static boolean serverSuccess;
    private static final Object syncLock = new C39112();
    private static UserState toSyncUserState;
    private static boolean waitingForSessionResponse = false;

    static class C39101 {
        C39101() {
        }
    }

    static class C39112 {
        C39112() {
        }
    }

    static class C39145 extends ResponseHandler {
        C39145() {
        }

        void onSuccess(String responseStr) {
            OneSignalStateSynchronizer.serverSuccess = true;
            try {
                JSONObject lastGetTagsResponse = new JSONObject(responseStr);
                if (lastGetTagsResponse.has("tags")) {
                    synchronized (OneSignalStateSynchronizer.syncLock) {
                        JSONObject dependDiff = OneSignalStateSynchronizer.generateJsonDiff(OneSignalStateSynchronizer.currentUserState.syncValues.optJSONObject("tags"), OneSignalStateSynchronizer.toSyncUserState.syncValues.optJSONObject("tags"), null, null);
                        OneSignalStateSynchronizer.currentUserState.syncValues.put("tags", lastGetTagsResponse.optJSONObject("tags"));
                        OneSignalStateSynchronizer.currentUserState.persistState();
                        OneSignalStateSynchronizer.toSyncUserState.mergeTags(lastGetTagsResponse, dependDiff);
                        OneSignalStateSynchronizer.toSyncUserState.persistState();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    static class GetTagsResult {
        public JSONObject result;
        public boolean serverSuccess;

        GetTagsResult(boolean serverSuccess, JSONObject result) {
            this.serverSuccess = serverSuccess;
            this.result = result;
        }
    }

    static class NetworkHandlerThread extends HandlerThread {
        static final int MAX_RETRIES = 3;
        private static final int NETWORK_HANDLER_USERSTATE = 0;
        int currentRetry;
        Handler mHandler = null;
        int mType;

        class C39151 implements Runnable {
            C39151() {
            }

            public void run() {
                OneSignalStateSynchronizer.syncUserState(false);
            }
        }

        NetworkHandlerThread(int type) {
            super("OSH_NetworkHandlerThread");
            this.mType = type;
            start();
            this.mHandler = new Handler(getLooper());
        }

        void runNewJob() {
            this.currentRetry = 0;
            this.mHandler.removeCallbacksAndMessages(null);
            this.mHandler.postDelayed(getNewRunnable(), 5000);
        }

        private Runnable getNewRunnable() {
            switch (this.mType) {
                case 0:
                    return new C39151();
                default:
                    return null;
            }
        }

        void stopScheduledRunnable() {
            this.mHandler.removeCallbacksAndMessages(null);
        }

        void doRetry() {
            if (this.currentRetry < 3 && !this.mHandler.hasMessages(0)) {
                this.currentRetry++;
                this.mHandler.postDelayed(getNewRunnable(), (long) (this.currentRetry * 15000));
            }
        }
    }

    class UserState {
        private final int NOTIFICATION_TYPES_NO_PERMISSION;
        private final int NOTIFICATION_TYPES_SUBSCRIBED;
        private final int NOTIFICATION_TYPES_UNSUBSCRIBE;
        JSONObject dependValues;
        private String persistKey;
        JSONObject syncValues;

        private UserState(String inPersistKey, boolean load) {
            this.NOTIFICATION_TYPES_SUBSCRIBED = 1;
            this.NOTIFICATION_TYPES_NO_PERMISSION = 0;
            this.NOTIFICATION_TYPES_UNSUBSCRIBE = -2;
            this.persistKey = inPersistKey;
            if (load) {
                loadState();
                return;
            }
            this.dependValues = new JSONObject();
            this.syncValues = new JSONObject();
        }

        private UserState deepClone(String persistKey) {
            UserState clonedUserState = new UserState(persistKey, false);
            try {
                clonedUserState.dependValues = new JSONObject(this.dependValues.toString());
                clonedUserState.syncValues = new JSONObject(this.syncValues.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return clonedUserState;
        }

        private void addDependFields() {
            try {
                this.syncValues.put("notification_types", getNotificationTypes());
            } catch (JSONException e) {
            }
        }

        private int getNotificationTypes() {
            int subscribableStatus = this.dependValues.optInt("subscribableStatus", 1);
            if (subscribableStatus < -2) {
                return subscribableStatus;
            }
            if (!this.dependValues.optBoolean("androidPermission", true)) {
                return 0;
            }
            if (this.dependValues.optBoolean("userSubscribePref", true)) {
                return 1;
            }
            return -2;
        }

        private Set<String> getGroupChangeFields(UserState changedTo) {
            try {
                if (!(this.dependValues.optLong("loc_time_stamp") == changedTo.dependValues.getLong("loc_time_stamp") && this.syncValues.optDouble(C1048i.ko) == changedTo.syncValues.getDouble(C1048i.ko) && this.syncValues.optDouble(SchemaSymbols.ATTVAL_LONG) == changedTo.syncValues.getDouble(SchemaSymbols.ATTVAL_LONG) && this.syncValues.optDouble("loc_acc") == changedTo.syncValues.getDouble("loc_acc") && this.syncValues.optInt("loc_type") == changedTo.syncValues.optInt("loc_type"))) {
                    if (changedTo.dependValues.optBoolean("loc_bg")) {
                        changedTo.syncValues.put("loc_bg", changedTo.dependValues.optBoolean("loc_bg"));
                    }
                    return OneSignalStateSynchronizer.LOCATION_FIELDS_SET;
                }
            } catch (Throwable th) {
            }
            return null;
        }

        void setLocation(LocationPoint point) {
            try {
                this.syncValues.put(C1048i.ko, point.lat);
                this.syncValues.put(SchemaSymbols.ATTVAL_LONG, point.log);
                this.syncValues.put("loc_acc", point.accuracy);
                this.syncValues.put("loc_type", point.type);
                this.dependValues.put("loc_bg", point.bg);
                this.dependValues.put("loc_time_stamp", point.timeStamp);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        void clearLocation() {
            try {
                this.syncValues.put(C1048i.ko, null);
                this.syncValues.put(SchemaSymbols.ATTVAL_LONG, null);
                this.syncValues.put("loc_acc", null);
                this.syncValues.put("loc_type", null);
                this.syncValues.put("loc_bg", null);
                this.dependValues.put("loc_bg", null);
                this.dependValues.put("loc_time_stamp", null);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        private JSONObject generateJsonDiff(UserState newState, boolean isSessionCall) {
            addDependFields();
            newState.addDependFields();
            JSONObject sendJson = OneSignalStateSynchronizer.generateJsonDiff(this.syncValues, newState.syncValues, null, getGroupChangeFields(newState));
            if (!isSessionCall && sendJson.toString().equals("{}")) {
                return null;
            }
            try {
                if (sendJson.has("app_id")) {
                    return sendJson;
                }
                sendJson.put("app_id", this.syncValues.optString("app_id"));
                return sendJson;
            } catch (JSONException e) {
                e.printStackTrace();
                return sendJson;
            }
        }

        void set(String key, Object value) {
            try {
                this.syncValues.put(key, value);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        void setState(String key, Object value) {
            try {
                this.dependValues.put(key, value);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        private void loadState() {
            SharedPreferences prefs = OneSignal.getGcmPreferences(OneSignalStateSynchronizer.appContext);
            String dependValuesStr = prefs.getString("ONESIGNAL_USERSTATE_DEPENDVALYES_" + this.persistKey, null);
            if (dependValuesStr == null) {
                this.dependValues = new JSONObject();
                boolean userSubscribePref = true;
                try {
                    int subscribableStatus;
                    if (this.persistKey.equals("CURRENT_STATE")) {
                        subscribableStatus = prefs.getInt("ONESIGNAL_SUBSCRIPTION", 1);
                    } else {
                        subscribableStatus = prefs.getInt("ONESIGNAL_SYNCED_SUBSCRIPTION", 1);
                    }
                    if (subscribableStatus == -2) {
                        subscribableStatus = 1;
                        userSubscribePref = false;
                    }
                    this.dependValues.put("subscribableStatus", subscribableStatus);
                    this.dependValues.put("userSubscribePref", userSubscribePref);
                } catch (JSONException e) {
                }
            } else {
                try {
                    this.dependValues = new JSONObject(dependValuesStr);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
            String syncValuesStr = prefs.getString("ONESIGNAL_USERSTATE_SYNCVALYES_" + this.persistKey, null);
            if (syncValuesStr == null) {
                try {
                    this.syncValues = new JSONObject();
                    this.syncValues.put("identifier", prefs.getString("GT_REGISTRATION_ID", null));
                    return;
                } catch (JSONException e22) {
                    e22.printStackTrace();
                    return;
                }
            }
            this.syncValues = new JSONObject(syncValuesStr);
        }

        private void persistState() {
            synchronized (OneSignalStateSynchronizer.syncLock) {
                modifySyncValuesJsonArray("pkgs");
                Editor editor = OneSignal.getGcmPreferences(OneSignalStateSynchronizer.appContext).edit();
                editor.putString("ONESIGNAL_USERSTATE_SYNCVALYES_" + this.persistKey, this.syncValues.toString());
                editor.putString("ONESIGNAL_USERSTATE_DEPENDVALYES_" + this.persistKey, this.dependValues.toString());
                editor.commit();
            }
        }

        private void modifySyncValuesJsonArray(String baseKey) {
            if (this.syncValues.has(baseKey + "_d") || !this.syncValues.has(baseKey + "_d")) {
                try {
                    JSONArray orgArray;
                    int i;
                    if (this.syncValues.has(baseKey)) {
                        orgArray = this.syncValues.getJSONArray(baseKey);
                    } else {
                        orgArray = new JSONArray();
                    }
                    JSONArray tempArray = new JSONArray();
                    if (this.syncValues.has(baseKey + "_d")) {
                        String remArrayStr = OneSignalStateSynchronizer.toStringNE(this.syncValues.getJSONArray(baseKey + "_d"));
                        for (i = 0; i < orgArray.length(); i++) {
                            if (!remArrayStr.contains(orgArray.getString(i))) {
                                tempArray.put(orgArray.get(i));
                            }
                        }
                    } else {
                        tempArray = orgArray;
                    }
                    if (this.syncValues.has(baseKey + "_a")) {
                        JSONArray newArray = this.syncValues.getJSONArray(baseKey + "_a");
                        for (i = 0; i < newArray.length(); i++) {
                            tempArray.put(newArray.get(i));
                        }
                    }
                    this.syncValues.put(baseKey, tempArray);
                    this.syncValues.remove(baseKey + "_a");
                    this.syncValues.remove(baseKey + "_d");
                } catch (Throwable th) {
                }
            }
        }

        private void persistStateAfterSync(JSONObject inDependValues, JSONObject inSyncValues) {
            if (inDependValues != null) {
                OneSignalStateSynchronizer.generateJsonDiff(this.dependValues, inDependValues, this.dependValues, null);
            }
            if (inSyncValues != null) {
                OneSignalStateSynchronizer.generateJsonDiff(this.syncValues, inSyncValues, this.syncValues, null);
                mergeTags(inSyncValues, null);
            }
            if (inDependValues != null || inSyncValues != null) {
                persistState();
            }
        }

        void mergeTags(JSONObject inSyncValues, JSONObject omitKeys) {
            synchronized (OneSignalStateSynchronizer.syncLock) {
                if (inSyncValues.has("tags")) {
                    JSONObject newTags;
                    if (this.syncValues.has("tags")) {
                        try {
                            newTags = new JSONObject(this.syncValues.optString("tags"));
                        } catch (JSONException e) {
                            newTags = new JSONObject();
                        }
                    } else {
                        newTags = new JSONObject();
                    }
                    JSONObject curTags = inSyncValues.optJSONObject("tags");
                    Iterator<String> keys = curTags.keys();
                    while (keys.hasNext()) {
                        String key = (String) keys.next();
                        if ("".equals(curTags.optString(key))) {
                            newTags.remove(key);
                        } else {
                            if (omitKeys != null) {
                                try {
                                    if (omitKeys.has(key)) {
                                    }
                                } catch (Throwable th) {
                                }
                            }
                            newTags.put(key, curTags.optString(key));
                        }
                    }
                    if (newTags.toString().equals("{}")) {
                        this.syncValues.remove("tags");
                    } else {
                        this.syncValues.put("tags", newTags);
                    }
                }
            }
        }
    }

    OneSignalStateSynchronizer() {
    }

    private static JSONObject generateJsonDiff(JSONObject cur, JSONObject changedTo, JSONObject baseOutput, Set<String> includeFields) {
        JSONObject synchronizedGenerateJsonDiff;
        synchronized (syncLock) {
            synchronizedGenerateJsonDiff = synchronizedGenerateJsonDiff(cur, changedTo, baseOutput, includeFields);
        }
        return synchronizedGenerateJsonDiff;
    }

    private static JSONObject synchronizedGenerateJsonDiff(JSONObject cur, JSONObject changedTo, JSONObject baseOutput, Set<String> includeFields) {
        if (cur == null) {
            return null;
        }
        if (changedTo == null) {
            return baseOutput;
        }
        JSONObject output;
        Iterator<String> keys = changedTo.keys();
        if (baseOutput != null) {
            output = baseOutput;
        } else {
            output = new JSONObject();
        }
        while (keys.hasNext()) {
            try {
                String key = (String) keys.next();
                Object value = changedTo.get(key);
                if (cur.has(key)) {
                    if (value instanceof JSONObject) {
                        JSONObject curValue = cur.getJSONObject(key);
                        JSONObject outValue = null;
                        if (baseOutput != null && baseOutput.has(key)) {
                            outValue = baseOutput.getJSONObject(key);
                        }
                        String returnedJsonStr = synchronizedGenerateJsonDiff(curValue, (JSONObject) value, outValue, includeFields).toString();
                        if (!returnedJsonStr.equals("{}")) {
                            output.put(key, new JSONObject(returnedJsonStr));
                        }
                    } else if (value instanceof JSONArray) {
                        handleJsonArray(key, (JSONArray) value, cur.getJSONArray(key), output);
                    } else if (includeFields == null || !includeFields.contains(key)) {
                        Object curValue2 = cur.get(key);
                        if (!value.equals(curValue2)) {
                            if (!(curValue2 instanceof Integer) || "".equals(value)) {
                                output.put(key, value);
                            } else if (((Number) curValue2).doubleValue() != ((Number) value).doubleValue()) {
                                output.put(key, value);
                            }
                        }
                    } else {
                        output.put(key, value);
                    }
                } else if (value instanceof JSONObject) {
                    output.put(key, new JSONObject(value.toString()));
                } else if (value instanceof JSONArray) {
                    handleJsonArray(key, (JSONArray) value, null, output);
                } else {
                    output.put(key, value);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return output;
    }

    private static void handleJsonArray(String key, JSONArray newArray, JSONArray curArray, JSONObject output) throws JSONException {
        if (key.endsWith("_a") || key.endsWith("_d")) {
            output.put(key, newArray);
            return;
        }
        int i;
        String arrayStr = toStringNE(newArray);
        JSONArray newOutArray = new JSONArray();
        JSONArray remOutArray = new JSONArray();
        String curArrayStr = curArray == null ? null : toStringNE(curArray);
        for (i = 0; i < newArray.length(); i++) {
            String arrayValue = (String) newArray.get(i);
            if (curArray == null || !curArrayStr.contains(arrayValue)) {
                newOutArray.put(arrayValue);
            }
        }
        if (curArray != null) {
            for (i = 0; i < curArray.length(); i++) {
                arrayValue = curArray.getString(i);
                if (!arrayStr.contains(arrayValue)) {
                    remOutArray.put(arrayValue);
                }
            }
        }
        if (!newOutArray.toString().equals("[]")) {
            output.put(key + "_a", newOutArray);
        }
        if (!remOutArray.toString().equals("[]")) {
            output.put(key + "_d", remOutArray);
        }
    }

    private static String toStringNE(JSONArray jsonArray) {
        String strArray = "[";
        int i = 0;
        while (i < jsonArray.length()) {
            try {
                strArray = strArray + "\"" + jsonArray.getString(i) + "\"";
                i++;
            } catch (Throwable th) {
            }
        }
        return strArray + "]";
    }

    private static JSONObject getTagsWithoutDeletedKeys(JSONObject jsonObject) {
        if (!jsonObject.has("tags")) {
            return null;
        }
        JSONObject toReturn = new JSONObject();
        synchronized (syncLock) {
            JSONObject keyValues = jsonObject.optJSONObject("tags");
            Iterator<String> keys = keyValues.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                try {
                    Object value = keyValues.get(key);
                    if (!"".equals(value)) {
                        toReturn.put(key, value);
                    }
                } catch (Throwable th) {
                }
            }
        }
        return toReturn;
    }

    static boolean stopAndPersist() {
        boolean unSynced = false;
        for (Entry<Integer, NetworkHandlerThread> handlerThread : networkHandlerThreads.entrySet()) {
            ((NetworkHandlerThread) handlerThread.getValue()).stopScheduledRunnable();
        }
        if (toSyncUserState != null) {
            if (currentUserState.generateJsonDiff(toSyncUserState, isSessionCall()) != null) {
                unSynced = true;
            }
            toSyncUserState.persistState();
        }
        return unSynced;
    }

    static void clearLocation() {
        if (toSyncUserState != null) {
            toSyncUserState.clearLocation();
            toSyncUserState.persistState();
        }
    }

    static void initUserState(Context context) {
        appContext = context;
        synchronized (syncLock) {
            if (currentUserState == null) {
                OneSignalStateSynchronizer oneSignalStateSynchronizer = new OneSignalStateSynchronizer();
                oneSignalStateSynchronizer.getClass();
                currentUserState = new UserState("CURRENT_STATE", true);
            }
            if (toSyncUserState == null) {
                oneSignalStateSynchronizer = new OneSignalStateSynchronizer();
                oneSignalStateSynchronizer.getClass();
                toSyncUserState = new UserState("TOSYNC_STATE", true);
            }
        }
    }

    static UserState getNewUserState() {
        OneSignalStateSynchronizer oneSignalStateSynchronizer = new OneSignalStateSynchronizer();
        oneSignalStateSynchronizer.getClass();
        return new UserState("nonPersist", false);
    }

    private static boolean isSessionCall() {
        return OneSignal.getUserId() == null || (nextSyncIsSession && !waitingForSessionResponse);
    }

    static void syncUserState(boolean fromSyncService) {
        String userId = OneSignal.getUserId();
        boolean isSessionCall = isSessionCall();
        final JSONObject jsonBody = currentUserState.generateJsonDiff(toSyncUserState, isSessionCall);
        final JSONObject dependDiff = generateJsonDiff(currentUserState.dependValues, toSyncUserState.dependValues, null, null);
        if (jsonBody == null) {
            currentUserState.persistStateAfterSync(dependDiff, null);
            return;
        }
        toSyncUserState.persistState();
        if (userId == null && !nextSyncIsSession) {
            return;
        }
        if (!isSessionCall || fromSyncService) {
            OneSignalRestClient.putSync("players/" + userId, jsonBody, new ResponseHandler() {
                void onFailure(int statusCode, String response, Throwable throwable) {
                    OneSignal.Log(LOG_LEVEL.WARN, "Failed last request. statusCode: " + statusCode + "\nresponse: " + response);
                    if (OneSignalStateSynchronizer.response400WithErrorsContaining(statusCode, response, "No user with this id found")) {
                        OneSignalStateSynchronizer.handlePlayerDeletedFromServer();
                    } else {
                        OneSignalStateSynchronizer.getNetworkHandlerThread(Integer.valueOf(0)).doRetry();
                    }
                }

                void onSuccess(String response) {
                    OneSignalStateSynchronizer.currentUserState.persistStateAfterSync(dependDiff, jsonBody);
                }
            });
            return;
        }
        String urlStr;
        if (userId == null) {
            urlStr = "players";
        } else {
            urlStr = "players/" + userId + "/on_session";
        }
        waitingForSessionResponse = true;
        OneSignalRestClient.postSync(urlStr, jsonBody, new ResponseHandler() {
            void onFailure(int statusCode, String response, Throwable throwable) {
                OneSignalStateSynchronizer.waitingForSessionResponse = false;
                OneSignal.Log(LOG_LEVEL.WARN, "Failed last request. statusCode: " + statusCode + "\nresponse: " + response);
                if (OneSignalStateSynchronizer.response400WithErrorsContaining(statusCode, response, "not a valid device_type")) {
                    OneSignalStateSynchronizer.handlePlayerDeletedFromServer();
                } else {
                    OneSignalStateSynchronizer.getNetworkHandlerThread(Integer.valueOf(0)).doRetry();
                }
            }

            void onSuccess(String response) {
                OneSignalStateSynchronizer.nextSyncIsSession = OneSignalStateSynchronizer.waitingForSessionResponse = false;
                OneSignalStateSynchronizer.currentUserState.persistStateAfterSync(dependDiff, jsonBody);
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    if (jsonResponse.has("id")) {
                        String userId = jsonResponse.optString("id");
                        OneSignal.updateUserIdDependents(userId);
                        OneSignal.Log(LOG_LEVEL.INFO, "Device registered, UserId = " + userId);
                    } else {
                        OneSignal.Log(LOG_LEVEL.INFO, "session sent, UserId = " + OneSignal.getUserId());
                    }
                    OneSignal.updateOnSessionDependents();
                } catch (Throwable t) {
                    OneSignal.Log(LOG_LEVEL.ERROR, "ERROR parsing on_session or create JSON Response.", t);
                }
            }
        });
    }

    private static boolean response400WithErrorsContaining(int statusCode, String response, String contains) {
        if (statusCode != HttpStatus.SC_BAD_REQUEST || response == null) {
            return false;
        }
        try {
            JSONObject responseJson = new JSONObject(response);
            if (responseJson.has("errors") && responseJson.optString("errors").contains(contains)) {
                return true;
            }
            return false;
        } catch (Throwable t) {
            t.printStackTrace();
            return false;
        }
    }

    private static NetworkHandlerThread getNetworkHandlerThread(Integer type) {
        NetworkHandlerThread networkHandlerThread;
        synchronized (networkHandlerSyncLock) {
            if (!networkHandlerThreads.containsKey(type)) {
                networkHandlerThreads.put(type, new NetworkHandlerThread(type.intValue()));
            }
            networkHandlerThread = (NetworkHandlerThread) networkHandlerThreads.get(type);
        }
        return networkHandlerThread;
    }

    private static UserState getUserStateForModification() {
        if (toSyncUserState == null) {
            toSyncUserState = currentUserState.deepClone("TOSYNC_STATE");
        }
        postNewSyncUserState();
        return toSyncUserState;
    }

    private static void postNewSyncUserState() {
        getNetworkHandlerThread(Integer.valueOf(0)).runNewJob();
    }

    static void postUpdate(UserState postSession, boolean isSession) {
        JSONObject toSync = getUserStateForModification().syncValues;
        generateJsonDiff(toSync, postSession.syncValues, toSync, null);
        JSONObject dependValues = getUserStateForModification().dependValues;
        generateJsonDiff(dependValues, postSession.dependValues, dependValues, null);
        boolean z = nextSyncIsSession || isSession || OneSignal.getUserId() == null;
        nextSyncIsSession = z;
    }

    static void sendTags(JSONObject newTags) {
        JSONObject userStateTags = getUserStateForModification().syncValues;
        try {
            generateJsonDiff(userStateTags, new JSONObject().put("tags", newTags), userStateTags, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    static void syncHashedEmail(String email) {
        JSONObject syncValues = getUserStateForModification().syncValues;
        try {
            JSONObject emailFields = new JSONObject();
            emailFields.put("em_m", hexDigest(email, "MD5"));
            emailFields.put("em_s", hexDigest(email, "SHA-1"));
            generateJsonDiff(syncValues, emailFields, syncValues, null);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private static String hexDigest(String str, String digestInstance) throws Throwable {
        MessageDigest digest = MessageDigest.getInstance(digestInstance);
        digest.update(str.getBytes("UTF-8"));
        byte[] messageDigest = digest.digest();
        StringBuilder hexString = new StringBuilder();
        for (byte aMessageDigest : messageDigest) {
            String h = Integer.toHexString(aMessageDigest & 255);
            while (h.length() < 2) {
                h = SchemaSymbols.ATTVAL_FALSE_0 + h;
            }
            hexString.append(h);
        }
        return hexString.toString();
    }

    static void setSubscription(boolean enable) {
        try {
            getUserStateForModification().dependValues.put("userSubscribePref", enable);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    static boolean getUserSubscribePreference() {
        return toSyncUserState.dependValues.optBoolean("userSubscribePref", true);
    }

    static void setPermission(boolean enable) {
        try {
            getUserStateForModification().dependValues.put("androidPermission", enable);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    static void updateLocation(LocationPoint point) {
        getUserStateForModification().setLocation(point);
    }

    static boolean getSubscribed() {
        return toSyncUserState.getNotificationTypes() > 0;
    }

    static String getRegistrationId() {
        return toSyncUserState.syncValues.optString("identifier", null);
    }

    static GetTagsResult getTags(boolean fromServer) {
        GetTagsResult getTagsResult;
        if (fromServer) {
            String userId = OneSignal.getUserId();
            OneSignalRestClient.getSync("players/" + userId + "?app_id=" + OneSignal.getSavedAppId(), new C39145());
        }
        synchronized (syncLock) {
            getTagsResult = new GetTagsResult(serverSuccess, getTagsWithoutDeletedKeys(toSyncUserState.syncValues));
        }
        return getTagsResult;
    }

    static void resetCurrentState() {
        OneSignal.saveUserId(null);
        currentUserState.syncValues = new JSONObject();
        currentUserState.persistState();
        OneSignal.setLastSessionTime(-3660);
    }

    private static void handlePlayerDeletedFromServer() {
        resetCurrentState();
        nextSyncIsSession = true;
        postNewSyncUserState();
    }
}
