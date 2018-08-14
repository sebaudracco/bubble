package com.cuebiq.cuebiqsdk.model.persistence;

public final class PersistenceManagerFactory {
    private static PersistenceManager mInject = null;
    private static PersistenceManager mInstance;

    private PersistenceManagerFactory() {
    }

    public static void clearInject() {
        mInject = null;
    }

    public static PersistenceManager get() {
        if (mInject != null) {
            return mInject;
        }
        if (mInstance == null) {
            mInstance = new PersistenceManagerImpl();
        }
        return mInstance;
    }

    public static void inject(PersistenceManager persistenceManager) {
        mInject = persistenceManager;
    }
}
