package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.PriorityTaskManager;
import java.io.IOException;

public final class PriorityDataSource implements DataSource {
    private final int priority;
    private final PriorityTaskManager priorityTaskManager;
    private final DataSource upstream;

    public PriorityDataSource(DataSource upstream, PriorityTaskManager priorityTaskManager, int priority) {
        this.upstream = (DataSource) Assertions.checkNotNull(upstream);
        this.priorityTaskManager = (PriorityTaskManager) Assertions.checkNotNull(priorityTaskManager);
        this.priority = priority;
    }

    public long open(DataSpec dataSpec) throws IOException {
        this.priorityTaskManager.proceedOrThrow(this.priority);
        return this.upstream.open(dataSpec);
    }

    public int read(byte[] buffer, int offset, int max) throws IOException {
        this.priorityTaskManager.proceedOrThrow(this.priority);
        return this.upstream.read(buffer, offset, max);
    }

    public Uri getUri() {
        return this.upstream.getUri();
    }

    public void close() throws IOException {
        this.upstream.close();
    }
}
