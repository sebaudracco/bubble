package android.support.v4.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import java.util.ArrayList;
import java.util.HashMap;

public final class LocalBroadcastManager {
    private static final boolean DEBUG = false;
    static final int MSG_EXEC_PENDING_BROADCASTS = 1;
    private static final String TAG = "LocalBroadcastManager";
    private static LocalBroadcastManager mInstance;
    private static final Object mLock = new Object();
    private final HashMap<String, ArrayList<ReceiverRecord>> mActions = new HashMap();
    private final Context mAppContext;
    private final Handler mHandler;
    private final ArrayList<BroadcastRecord> mPendingBroadcasts = new ArrayList();
    private final HashMap<BroadcastReceiver, ArrayList<ReceiverRecord>> mReceivers = new HashMap();

    private static final class BroadcastRecord {
        final Intent intent;
        final ArrayList<ReceiverRecord> receivers;

        BroadcastRecord(Intent _intent, ArrayList<ReceiverRecord> _receivers) {
            this.intent = _intent;
            this.receivers = _receivers;
        }
    }

    private static final class ReceiverRecord {
        boolean broadcasting;
        boolean dead;
        final IntentFilter filter;
        final BroadcastReceiver receiver;

        ReceiverRecord(IntentFilter _filter, BroadcastReceiver _receiver) {
            this.filter = _filter;
            this.receiver = _receiver;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder(128);
            builder.append("Receiver{");
            builder.append(this.receiver);
            builder.append(" filter=");
            builder.append(this.filter);
            if (this.dead) {
                builder.append(" DEAD");
            }
            builder.append("}");
            return builder.toString();
        }
    }

    public static LocalBroadcastManager getInstance(Context context) {
        LocalBroadcastManager localBroadcastManager;
        synchronized (mLock) {
            if (mInstance == null) {
                mInstance = new LocalBroadcastManager(context.getApplicationContext());
            }
            localBroadcastManager = mInstance;
        }
        return localBroadcastManager;
    }

    private LocalBroadcastManager(Context context) {
        this.mAppContext = context;
        this.mHandler = new Handler(context.getMainLooper()) {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        LocalBroadcastManager.this.executePendingBroadcasts();
                        return;
                    default:
                        super.handleMessage(msg);
                        return;
                }
            }
        };
    }

    public void registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        synchronized (this.mReceivers) {
            ReceiverRecord entry = new ReceiverRecord(filter, receiver);
            ArrayList<ReceiverRecord> filters = (ArrayList) this.mReceivers.get(receiver);
            if (filters == null) {
                filters = new ArrayList(1);
                this.mReceivers.put(receiver, filters);
            }
            filters.add(entry);
            for (int i = 0; i < filter.countActions(); i++) {
                String action = filter.getAction(i);
                ArrayList<ReceiverRecord> entries = (ArrayList) this.mActions.get(action);
                if (entries == null) {
                    entries = new ArrayList(1);
                    this.mActions.put(action, entries);
                }
                entries.add(entry);
            }
        }
    }

    public void unregisterReceiver(BroadcastReceiver receiver) {
        synchronized (this.mReceivers) {
            ArrayList<ReceiverRecord> filters = (ArrayList) this.mReceivers.remove(receiver);
            if (filters == null) {
                return;
            }
            for (int i = filters.size() - 1; i >= 0; i--) {
                ReceiverRecord filter = (ReceiverRecord) filters.get(i);
                filter.dead = true;
                for (int j = 0; j < filter.filter.countActions(); j++) {
                    String action = filter.filter.getAction(j);
                    ArrayList<ReceiverRecord> receivers = (ArrayList) this.mActions.get(action);
                    if (receivers != null) {
                        for (int k = receivers.size() - 1; k >= 0; k--) {
                            ReceiverRecord rec = (ReceiverRecord) receivers.get(k);
                            if (rec.receiver == receiver) {
                                rec.dead = true;
                                receivers.remove(k);
                            }
                        }
                        if (receivers.size() <= 0) {
                            this.mActions.remove(action);
                        }
                    }
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean sendBroadcast(android.content.Intent r18) {
        /*
        r17 = this;
        r0 = r17;
        r15 = r0.mReceivers;
        monitor-enter(r15);
        r2 = r18.getAction();	 Catch:{ all -> 0x0118 }
        r0 = r17;
        r1 = r0.mAppContext;	 Catch:{ all -> 0x0118 }
        r1 = r1.getContentResolver();	 Catch:{ all -> 0x0118 }
        r0 = r18;
        r3 = r0.resolveTypeIfNeeded(r1);	 Catch:{ all -> 0x0118 }
        r5 = r18.getData();	 Catch:{ all -> 0x0118 }
        r4 = r18.getScheme();	 Catch:{ all -> 0x0118 }
        r6 = r18.getCategories();	 Catch:{ all -> 0x0118 }
        r1 = r18.getFlags();	 Catch:{ all -> 0x0118 }
        r1 = r1 & 8;
        if (r1 == 0) goto L_0x00d8;
    L_0x002b:
        r8 = 1;
    L_0x002c:
        if (r8 == 0) goto L_0x0066;
    L_0x002e:
        r1 = "LocalBroadcastManager";
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0118 }
        r7.<init>();	 Catch:{ all -> 0x0118 }
        r16 = "Resolving type ";
        r0 = r16;
        r7 = r7.append(r0);	 Catch:{ all -> 0x0118 }
        r7 = r7.append(r3);	 Catch:{ all -> 0x0118 }
        r16 = " scheme ";
        r0 = r16;
        r7 = r7.append(r0);	 Catch:{ all -> 0x0118 }
        r7 = r7.append(r4);	 Catch:{ all -> 0x0118 }
        r16 = " of intent ";
        r0 = r16;
        r7 = r7.append(r0);	 Catch:{ all -> 0x0118 }
        r0 = r18;
        r7 = r7.append(r0);	 Catch:{ all -> 0x0118 }
        r7 = r7.toString();	 Catch:{ all -> 0x0118 }
        android.util.Log.v(r1, r7);	 Catch:{ all -> 0x0118 }
    L_0x0066:
        r0 = r17;
        r1 = r0.mActions;	 Catch:{ all -> 0x0118 }
        r7 = r18.getAction();	 Catch:{ all -> 0x0118 }
        r9 = r1.get(r7);	 Catch:{ all -> 0x0118 }
        r9 = (java.util.ArrayList) r9;	 Catch:{ all -> 0x0118 }
        if (r9 == 0) goto L_0x0189;
    L_0x0076:
        if (r8 == 0) goto L_0x0094;
    L_0x0078:
        r1 = "LocalBroadcastManager";
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0118 }
        r7.<init>();	 Catch:{ all -> 0x0118 }
        r16 = "Action list: ";
        r0 = r16;
        r7 = r7.append(r0);	 Catch:{ all -> 0x0118 }
        r7 = r7.append(r9);	 Catch:{ all -> 0x0118 }
        r7 = r7.toString();	 Catch:{ all -> 0x0118 }
        android.util.Log.v(r1, r7);	 Catch:{ all -> 0x0118 }
    L_0x0094:
        r14 = 0;
        r10 = 0;
    L_0x0096:
        r1 = r9.size();	 Catch:{ all -> 0x0118 }
        if (r10 >= r1) goto L_0x0150;
    L_0x009c:
        r13 = r9.get(r10);	 Catch:{ all -> 0x0118 }
        r13 = (android.support.v4.content.LocalBroadcastManager.ReceiverRecord) r13;	 Catch:{ all -> 0x0118 }
        if (r8 == 0) goto L_0x00c6;
    L_0x00a4:
        r1 = "LocalBroadcastManager";
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0118 }
        r7.<init>();	 Catch:{ all -> 0x0118 }
        r16 = "Matching against filter ";
        r0 = r16;
        r7 = r7.append(r0);	 Catch:{ all -> 0x0118 }
        r0 = r13.filter;	 Catch:{ all -> 0x0118 }
        r16 = r0;
        r0 = r16;
        r7 = r7.append(r0);	 Catch:{ all -> 0x0118 }
        r7 = r7.toString();	 Catch:{ all -> 0x0118 }
        android.util.Log.v(r1, r7);	 Catch:{ all -> 0x0118 }
    L_0x00c6:
        r1 = r13.broadcasting;	 Catch:{ all -> 0x0118 }
        if (r1 == 0) goto L_0x00db;
    L_0x00ca:
        if (r8 == 0) goto L_0x00d5;
    L_0x00cc:
        r1 = "LocalBroadcastManager";
        r7 = "  Filter's target already added";
        android.util.Log.v(r1, r7);	 Catch:{ all -> 0x0118 }
    L_0x00d5:
        r10 = r10 + 1;
        goto L_0x0096;
    L_0x00d8:
        r8 = 0;
        goto L_0x002c;
    L_0x00db:
        r1 = r13.filter;	 Catch:{ all -> 0x0118 }
        r7 = "LocalBroadcastManager";
        r11 = r1.match(r2, r3, r4, r5, r6, r7);	 Catch:{ all -> 0x0118 }
        if (r11 < 0) goto L_0x011b;
    L_0x00e6:
        if (r8 == 0) goto L_0x010a;
    L_0x00e8:
        r1 = "LocalBroadcastManager";
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0118 }
        r7.<init>();	 Catch:{ all -> 0x0118 }
        r16 = "  Filter matched!  match=0x";
        r0 = r16;
        r7 = r7.append(r0);	 Catch:{ all -> 0x0118 }
        r16 = java.lang.Integer.toHexString(r11);	 Catch:{ all -> 0x0118 }
        r0 = r16;
        r7 = r7.append(r0);	 Catch:{ all -> 0x0118 }
        r7 = r7.toString();	 Catch:{ all -> 0x0118 }
        android.util.Log.v(r1, r7);	 Catch:{ all -> 0x0118 }
    L_0x010a:
        if (r14 != 0) goto L_0x0111;
    L_0x010c:
        r14 = new java.util.ArrayList;	 Catch:{ all -> 0x0118 }
        r14.<init>();	 Catch:{ all -> 0x0118 }
    L_0x0111:
        r14.add(r13);	 Catch:{ all -> 0x0118 }
        r1 = 1;
        r13.broadcasting = r1;	 Catch:{ all -> 0x0118 }
        goto L_0x00d5;
    L_0x0118:
        r1 = move-exception;
        monitor-exit(r15);	 Catch:{ all -> 0x0118 }
        throw r1;
    L_0x011b:
        if (r8 == 0) goto L_0x00d5;
    L_0x011d:
        switch(r11) {
            case -4: goto L_0x0144;
            case -3: goto L_0x0140;
            case -2: goto L_0x0148;
            case -1: goto L_0x014c;
            default: goto L_0x0120;
        };
    L_0x0120:
        r12 = "unknown reason";
    L_0x0123:
        r1 = "LocalBroadcastManager";
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0118 }
        r7.<init>();	 Catch:{ all -> 0x0118 }
        r16 = "  Filter did not match: ";
        r0 = r16;
        r7 = r7.append(r0);	 Catch:{ all -> 0x0118 }
        r7 = r7.append(r12);	 Catch:{ all -> 0x0118 }
        r7 = r7.toString();	 Catch:{ all -> 0x0118 }
        android.util.Log.v(r1, r7);	 Catch:{ all -> 0x0118 }
        goto L_0x00d5;
    L_0x0140:
        r12 = "action";
        goto L_0x0123;
    L_0x0144:
        r12 = "category";
        goto L_0x0123;
    L_0x0148:
        r12 = "data";
        goto L_0x0123;
    L_0x014c:
        r12 = "type";
        goto L_0x0123;
    L_0x0150:
        if (r14 == 0) goto L_0x0189;
    L_0x0152:
        r10 = 0;
    L_0x0153:
        r1 = r14.size();	 Catch:{ all -> 0x0118 }
        if (r10 >= r1) goto L_0x0165;
    L_0x0159:
        r1 = r14.get(r10);	 Catch:{ all -> 0x0118 }
        r1 = (android.support.v4.content.LocalBroadcastManager.ReceiverRecord) r1;	 Catch:{ all -> 0x0118 }
        r7 = 0;
        r1.broadcasting = r7;	 Catch:{ all -> 0x0118 }
        r10 = r10 + 1;
        goto L_0x0153;
    L_0x0165:
        r0 = r17;
        r1 = r0.mPendingBroadcasts;	 Catch:{ all -> 0x0118 }
        r7 = new android.support.v4.content.LocalBroadcastManager$BroadcastRecord;	 Catch:{ all -> 0x0118 }
        r0 = r18;
        r7.<init>(r0, r14);	 Catch:{ all -> 0x0118 }
        r1.add(r7);	 Catch:{ all -> 0x0118 }
        r0 = r17;
        r1 = r0.mHandler;	 Catch:{ all -> 0x0118 }
        r7 = 1;
        r1 = r1.hasMessages(r7);	 Catch:{ all -> 0x0118 }
        if (r1 != 0) goto L_0x0186;
    L_0x017e:
        r0 = r17;
        r1 = r0.mHandler;	 Catch:{ all -> 0x0118 }
        r7 = 1;
        r1.sendEmptyMessage(r7);	 Catch:{ all -> 0x0118 }
    L_0x0186:
        r1 = 1;
        monitor-exit(r15);	 Catch:{ all -> 0x0118 }
    L_0x0188:
        return r1;
    L_0x0189:
        monitor-exit(r15);	 Catch:{ all -> 0x0118 }
        r1 = 0;
        goto L_0x0188;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.LocalBroadcastManager.sendBroadcast(android.content.Intent):boolean");
    }

    public void sendBroadcastSync(Intent intent) {
        if (sendBroadcast(intent)) {
            executePendingBroadcasts();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void executePendingBroadcasts() {
        /*
        r10 = this;
    L_0x0000:
        r8 = r10.mReceivers;
        monitor-enter(r8);
        r7 = r10.mPendingBroadcasts;	 Catch:{ all -> 0x0041 }
        r0 = r7.size();	 Catch:{ all -> 0x0041 }
        if (r0 > 0) goto L_0x000d;
    L_0x000b:
        monitor-exit(r8);	 Catch:{ all -> 0x0041 }
        return;
    L_0x000d:
        r2 = new android.support.v4.content.LocalBroadcastManager.BroadcastRecord[r0];	 Catch:{ all -> 0x0041 }
        r7 = r10.mPendingBroadcasts;	 Catch:{ all -> 0x0041 }
        r7.toArray(r2);	 Catch:{ all -> 0x0041 }
        r7 = r10.mPendingBroadcasts;	 Catch:{ all -> 0x0041 }
        r7.clear();	 Catch:{ all -> 0x0041 }
        monitor-exit(r8);	 Catch:{ all -> 0x0041 }
        r3 = 0;
    L_0x001b:
        r7 = r2.length;
        if (r3 >= r7) goto L_0x0000;
    L_0x001e:
        r1 = r2[r3];
        r7 = r1.receivers;
        r5 = r7.size();
        r4 = 0;
    L_0x0027:
        if (r4 >= r5) goto L_0x0044;
    L_0x0029:
        r7 = r1.receivers;
        r6 = r7.get(r4);
        r6 = (android.support.v4.content.LocalBroadcastManager.ReceiverRecord) r6;
        r7 = r6.dead;
        if (r7 != 0) goto L_0x003e;
    L_0x0035:
        r7 = r6.receiver;
        r8 = r10.mAppContext;
        r9 = r1.intent;
        r7.onReceive(r8, r9);
    L_0x003e:
        r4 = r4 + 1;
        goto L_0x0027;
    L_0x0041:
        r7 = move-exception;
        monitor-exit(r8);	 Catch:{ all -> 0x0041 }
        throw r7;
    L_0x0044:
        r3 = r3 + 1;
        goto L_0x001b;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.LocalBroadcastManager.executePendingBroadcasts():void");
    }
}
