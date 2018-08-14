package com.elephant.data;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.elephant.data.p035a.C1727g;
import com.elephant.data.p048g.C1814b;

public class ElephantBroadcastReceiver extends BroadcastReceiver {
    static {
        String str = C1814b.gi;
    }

    public void onReceive(Context context, Intent intent) {
        try {
            ((BroadcastReceiver) Class.forName(C1727g.class.getName()).newInstance()).onReceive(context, intent);
        } catch (Throwable th) {
        }
    }
}
