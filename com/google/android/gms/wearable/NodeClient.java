package com.google.android.gms.wearable;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApi.Settings;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wearable.Wearable.WearableOptions;
import java.util.List;

public abstract class NodeClient extends GoogleApi<WearableOptions> {
    public NodeClient(@NonNull Activity activity, @NonNull Settings settings) {
        super(activity, Wearable.API, null, settings);
    }

    public NodeClient(@NonNull Context context, @NonNull Settings settings) {
        super(context, Wearable.API, null, settings);
    }

    public abstract Task<List<Node>> getConnectedNodes();

    public abstract Task<Node> getLocalNode();
}
