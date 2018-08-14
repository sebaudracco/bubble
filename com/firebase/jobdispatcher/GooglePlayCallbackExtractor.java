package com.firebase.jobdispatcher;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.Pair;
import com.appnext.base.p019a.p022c.C1028c;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import java.util.ArrayList;

final class GooglePlayCallbackExtractor {
    private static final String BUNDLE_KEY_CALLBACK = "callback";
    private static final int BUNDLE_MAGIC = 1279544898;
    private static final String ERROR_INVALID_CALLBACK = "Bad callback received, terminating";
    private static final String ERROR_NULL_CALLBACK = "No callback received, terminating";
    private static final String PENDING_CALLBACK_CLASS = "com.google.android.gms.gcm.PendingCallback";
    private static final String TAG = "FJD.GooglePlayReceiver";
    private static final int VAL_PARCELABLE = 4;
    private static Boolean shouldReadKeysAsStringsCached = null;

    GooglePlayCallbackExtractor() {
    }

    public Pair<JobCallback, Bundle> extractCallback(@Nullable Bundle data) {
        if (data != null) {
            return extractWrappedBinderFromParcel(data);
        }
        Log.e(TAG, ERROR_NULL_CALLBACK);
        return null;
    }

    @Nullable
    @SuppressLint({"ParcelClassLoader"})
    private Pair<JobCallback, Bundle> extractWrappedBinderFromParcel(Bundle data) {
        Throwable th;
        Bundle cleanBundle = new Bundle();
        Parcel serialized = toParcel(data);
        try {
            if (serialized.readInt() <= 0) {
                Log.w(TAG, ERROR_NULL_CALLBACK);
                serialized.recycle();
                return null;
            } else if (serialized.readInt() != BUNDLE_MAGIC) {
                Log.w(TAG, ERROR_NULL_CALLBACK);
                serialized.recycle();
                return null;
            } else {
                JobCallback callback;
                int numEntries = serialized.readInt();
                int i = 0;
                JobCallback callback2 = null;
                while (i < numEntries) {
                    String entryKey = readKey(serialized);
                    if (entryKey == null) {
                        callback = callback2;
                    } else if (callback2 != null || !BUNDLE_KEY_CALLBACK.equals(entryKey)) {
                        Object value = serialized.readValue(null);
                        if (value instanceof String) {
                            cleanBundle.putString(entryKey, (String) value);
                            callback = callback2;
                        } else if (value instanceof Boolean) {
                            cleanBundle.putBoolean(entryKey, ((Boolean) value).booleanValue());
                            callback = callback2;
                        } else if (value instanceof Integer) {
                            cleanBundle.putInt(entryKey, ((Integer) value).intValue());
                            callback = callback2;
                        } else if (value instanceof ArrayList) {
                            cleanBundle.putParcelableArrayList(entryKey, (ArrayList) value);
                            callback = callback2;
                        } else if (value instanceof Bundle) {
                            cleanBundle.putBundle(entryKey, (Bundle) value);
                            callback = callback2;
                        } else if (value instanceof Parcelable) {
                            cleanBundle.putParcelable(entryKey, (Parcelable) value);
                            callback = callback2;
                        } else {
                            callback = callback2;
                        }
                    } else if (serialized.readInt() != 4) {
                        Log.w(TAG, ERROR_INVALID_CALLBACK);
                        serialized.recycle();
                        callback = callback2;
                        return null;
                    } else {
                        if (PENDING_CALLBACK_CLASS.equals(serialized.readString())) {
                            try {
                                callback = new GooglePlayJobCallback(serialized.readStrongBinder());
                            } catch (Throwable th2) {
                                th = th2;
                                callback = callback2;
                            }
                        } else {
                            Log.w(TAG, ERROR_INVALID_CALLBACK);
                            serialized.recycle();
                            callback = callback2;
                            return null;
                        }
                    }
                    i++;
                    callback2 = callback;
                }
                if (callback2 == null) {
                    Log.w(TAG, ERROR_NULL_CALLBACK);
                    serialized.recycle();
                    callback = callback2;
                    return null;
                }
                Pair<JobCallback, Bundle> create = Pair.create(callback2, cleanBundle);
                serialized.recycle();
                callback = callback2;
                return create;
            }
        } catch (Throwable th3) {
            th = th3;
            serialized.recycle();
            throw th;
        }
    }

    private static Parcel toParcel(Bundle data) {
        Parcel serialized = Parcel.obtain();
        data.writeToParcel(serialized, 0);
        serialized.setDataPosition(0);
        return serialized;
    }

    private String readKey(Parcel serialized) {
        if (shouldReadKeysAsStrings()) {
            return serialized.readString();
        }
        Object entryKeyObj = serialized.readValue(null);
        if (entryKeyObj instanceof String) {
            return (String) entryKeyObj;
        }
        Log.w(TAG, ERROR_INVALID_CALLBACK);
        return null;
    }

    private static synchronized boolean shouldReadKeysAsStrings() {
        boolean z = true;
        synchronized (GooglePlayCallbackExtractor.class) {
            if (shouldReadKeysAsStringsCached == null) {
                String expectedKey = C1028c.gv;
                Bundle testBundle = new Bundle();
                testBundle.putString(C1028c.gv, FirebaseAnalytics$Param.VALUE);
                Parcel testParcel = toParcel(testBundle);
                try {
                    boolean z2;
                    checkCondition(testParcel.readInt() > 0);
                    if (testParcel.readInt() == BUNDLE_MAGIC) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    checkCondition(z2);
                    if (testParcel.readInt() != 1) {
                        z = false;
                    }
                    checkCondition(z);
                    shouldReadKeysAsStringsCached = Boolean.valueOf(C1028c.gv.equals(testParcel.readString()));
                    testParcel.recycle();
                } catch (RuntimeException e) {
                    shouldReadKeysAsStringsCached = Boolean.FALSE;
                    testParcel.recycle();
                } catch (Throwable th) {
                    testParcel.recycle();
                }
            }
            z = shouldReadKeysAsStringsCached.booleanValue();
        }
        return z;
    }

    private static void checkCondition(boolean condition) {
        if (!condition) {
            throw new IllegalStateException();
        }
    }
}
