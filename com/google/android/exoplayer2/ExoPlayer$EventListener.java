package com.google.android.exoplayer2;

import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;

public interface ExoPlayer$EventListener {
    void onLoadingChanged(boolean z);

    void onPlaybackParametersChanged(PlaybackParameters playbackParameters);

    void onPlayerError(ExoPlaybackException exoPlaybackException);

    void onPlayerStateChanged(boolean z, int i);

    void onPositionDiscontinuity();

    void onTimelineChanged(Timeline timeline, Object obj);

    void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray);
}
