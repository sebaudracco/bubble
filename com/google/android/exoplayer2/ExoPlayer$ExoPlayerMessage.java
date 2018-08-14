package com.google.android.exoplayer2;

import com.google.android.exoplayer2.ExoPlayer.ExoPlayerComponent;

public final class ExoPlayer$ExoPlayerMessage {
    public final Object message;
    public final int messageType;
    public final ExoPlayerComponent target;

    public ExoPlayer$ExoPlayerMessage(ExoPlayerComponent target, int messageType, Object message) {
        this.target = target;
        this.messageType = messageType;
        this.message = message;
    }
}
