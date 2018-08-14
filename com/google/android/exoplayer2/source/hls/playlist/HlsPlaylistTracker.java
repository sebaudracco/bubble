package com.google.android.exoplayer2.source.hls.playlist;

import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.source.AdaptiveMediaSourceEventListener.EventDispatcher;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import com.google.android.exoplayer2.source.hls.HlsDataSourceFactory;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist.HlsUrl;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist.Segment;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.Loader.Callback;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.util.UriUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;

public final class HlsPlaylistTracker implements Callback<ParsingLoadable<HlsPlaylist>> {
    private static final double PLAYLIST_STUCK_TARGET_DURATION_COEFFICIENT = 3.5d;
    private static final long PRIMARY_URL_KEEPALIVE_MS = 15000;
    private final HlsDataSourceFactory dataSourceFactory;
    private final EventDispatcher eventDispatcher;
    private final Loader initialPlaylistLoader = new Loader("HlsPlaylistTracker:MasterPlaylist");
    private final Uri initialPlaylistUri;
    private boolean isLive;
    private final List<PlaylistEventListener> listeners = new ArrayList();
    private HlsMasterPlaylist masterPlaylist;
    private final int minRetryCount;
    private final IdentityHashMap<HlsUrl, MediaPlaylistBundle> playlistBundles = new IdentityHashMap();
    private final HlsPlaylistParser playlistParser = new HlsPlaylistParser();
    private final Handler playlistRefreshHandler = new Handler();
    private HlsUrl primaryHlsUrl;
    private final PrimaryPlaylistListener primaryPlaylistListener;
    private HlsMediaPlaylist primaryUrlSnapshot;

    public interface PlaylistEventListener {
        void onPlaylistBlacklisted(HlsUrl hlsUrl, long j);

        void onPlaylistChanged();
    }

    public interface PrimaryPlaylistListener {
        void onPrimaryPlaylistRefreshed(HlsMediaPlaylist hlsMediaPlaylist);
    }

    private final class MediaPlaylistBundle implements Callback<ParsingLoadable<HlsPlaylist>>, Runnable {
        private long blacklistUntilMs;
        private long lastSnapshotAccessTimeMs;
        private long lastSnapshotChangeMs;
        private long lastSnapshotLoadMs;
        private final ParsingLoadable<HlsPlaylist> mediaPlaylistLoadable;
        private final Loader mediaPlaylistLoader = new Loader("HlsPlaylistTracker:MediaPlaylist");
        private boolean pendingRefresh;
        private IOException playlistError;
        private HlsMediaPlaylist playlistSnapshot;
        private final HlsUrl playlistUrl;

        public MediaPlaylistBundle(HlsUrl playlistUrl, long initialLastSnapshotAccessTimeMs) {
            this.playlistUrl = playlistUrl;
            this.lastSnapshotAccessTimeMs = initialLastSnapshotAccessTimeMs;
            this.mediaPlaylistLoadable = new ParsingLoadable(HlsPlaylistTracker.this.dataSourceFactory.createDataSource(4), UriUtil.resolveToUri(HlsPlaylistTracker.this.masterPlaylist.baseUri, playlistUrl.url), 4, HlsPlaylistTracker.this.playlistParser);
        }

        public HlsMediaPlaylist getPlaylistSnapshot() {
            this.lastSnapshotAccessTimeMs = SystemClock.elapsedRealtime();
            return this.playlistSnapshot;
        }

        public boolean isSnapshotValid() {
            if (this.playlistSnapshot == null) {
                return false;
            }
            long currentTimeMs = SystemClock.elapsedRealtime();
            long snapshotValidityDurationMs = Math.max(30000, C.usToMs(this.playlistSnapshot.durationUs));
            if (this.playlistSnapshot.hasEndTag || this.playlistSnapshot.playlistType == 2 || this.playlistSnapshot.playlistType == 1 || this.lastSnapshotLoadMs + snapshotValidityDurationMs > currentTimeMs) {
                return true;
            }
            return false;
        }

        public void release() {
            this.mediaPlaylistLoader.release();
        }

        public void loadPlaylist() {
            this.blacklistUntilMs = 0;
            if (!this.pendingRefresh && !this.mediaPlaylistLoader.isLoading()) {
                this.mediaPlaylistLoader.startLoading(this.mediaPlaylistLoadable, this, HlsPlaylistTracker.this.minRetryCount);
            }
        }

        public void maybeThrowPlaylistRefreshError() throws IOException {
            this.mediaPlaylistLoader.maybeThrowError();
            if (this.playlistError != null) {
                throw this.playlistError;
            }
        }

        public void onLoadCompleted(ParsingLoadable<HlsPlaylist> loadable, long elapsedRealtimeMs, long loadDurationMs) {
            HlsPlaylist result = (HlsPlaylist) loadable.getResult();
            if (result instanceof HlsMediaPlaylist) {
                processLoadedPlaylist((HlsMediaPlaylist) result);
                HlsPlaylistTracker.this.eventDispatcher.loadCompleted(loadable.dataSpec, 4, elapsedRealtimeMs, loadDurationMs, loadable.bytesLoaded());
                return;
            }
            this.playlistError = new ParserException("Loaded playlist has unexpected type.");
        }

        public void onLoadCanceled(ParsingLoadable<HlsPlaylist> loadable, long elapsedRealtimeMs, long loadDurationMs, boolean released) {
            HlsPlaylistTracker.this.eventDispatcher.loadCanceled(loadable.dataSpec, 4, elapsedRealtimeMs, loadDurationMs, loadable.bytesLoaded());
        }

        public int onLoadError(ParsingLoadable<HlsPlaylist> loadable, long elapsedRealtimeMs, long loadDurationMs, IOException error) {
            boolean isFatal = error instanceof ParserException;
            HlsPlaylistTracker.this.eventDispatcher.loadError(loadable.dataSpec, 4, elapsedRealtimeMs, loadDurationMs, loadable.bytesLoaded(), error, isFatal);
            if (isFatal) {
                return 3;
            }
            boolean shouldRetry = true;
            if (ChunkedTrackBlacklistUtil.shouldBlacklist(error)) {
                blacklistPlaylist();
                shouldRetry = HlsPlaylistTracker.this.primaryHlsUrl == this.playlistUrl && !HlsPlaylistTracker.this.maybeSelectNewPrimaryUrl();
            }
            return shouldRetry ? 0 : 2;
        }

        public void run() {
            this.pendingRefresh = false;
            loadPlaylist();
        }

        private void processLoadedPlaylist(HlsMediaPlaylist loadedPlaylist) {
            HlsMediaPlaylist oldPlaylist = this.playlistSnapshot;
            long currentTimeMs = SystemClock.elapsedRealtime();
            this.lastSnapshotLoadMs = currentTimeMs;
            this.playlistSnapshot = HlsPlaylistTracker.this.getLatestPlaylistSnapshot(oldPlaylist, loadedPlaylist);
            long refreshDelayUs = -9223372036854775807L;
            if (this.playlistSnapshot != oldPlaylist) {
                this.playlistError = null;
                this.lastSnapshotChangeMs = currentTimeMs;
                if (HlsPlaylistTracker.this.onPlaylistUpdated(this.playlistUrl, this.playlistSnapshot)) {
                    refreshDelayUs = this.playlistSnapshot.targetDurationUs;
                }
            } else if (!this.playlistSnapshot.hasEndTag) {
                if (((double) (currentTimeMs - this.lastSnapshotChangeMs)) > ((double) C.usToMs(this.playlistSnapshot.targetDurationUs)) * HlsPlaylistTracker.PLAYLIST_STUCK_TARGET_DURATION_COEFFICIENT) {
                    this.playlistError = new PlaylistStuckException(this.playlistUrl.url);
                    blacklistPlaylist();
                } else if (loadedPlaylist.mediaSequence + loadedPlaylist.segments.size() < this.playlistSnapshot.mediaSequence) {
                    this.playlistError = new PlaylistResetException(this.playlistUrl.url);
                }
                refreshDelayUs = this.playlistSnapshot.targetDurationUs / 2;
            }
            if (refreshDelayUs != -9223372036854775807L) {
                this.pendingRefresh = HlsPlaylistTracker.this.playlistRefreshHandler.postDelayed(this, C.usToMs(refreshDelayUs));
            }
        }

        private void blacklistPlaylist() {
            this.blacklistUntilMs = SystemClock.elapsedRealtime() + 60000;
            HlsPlaylistTracker.this.notifyPlaylistBlacklisting(this.playlistUrl, 60000);
        }
    }

    public static final class PlaylistResetException extends IOException {
        public final String url;

        private PlaylistResetException(String url) {
            this.url = url;
        }
    }

    public static final class PlaylistStuckException extends IOException {
        public final String url;

        private PlaylistStuckException(String url) {
            this.url = url;
        }
    }

    public HlsPlaylistTracker(Uri initialPlaylistUri, HlsDataSourceFactory dataSourceFactory, EventDispatcher eventDispatcher, int minRetryCount, PrimaryPlaylistListener primaryPlaylistListener) {
        this.initialPlaylistUri = initialPlaylistUri;
        this.dataSourceFactory = dataSourceFactory;
        this.eventDispatcher = eventDispatcher;
        this.minRetryCount = minRetryCount;
        this.primaryPlaylistListener = primaryPlaylistListener;
    }

    public void addListener(PlaylistEventListener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(PlaylistEventListener listener) {
        this.listeners.remove(listener);
    }

    public void start() {
        this.initialPlaylistLoader.startLoading(new ParsingLoadable(this.dataSourceFactory.createDataSource(4), this.initialPlaylistUri, 4, this.playlistParser), this, this.minRetryCount);
    }

    public HlsMasterPlaylist getMasterPlaylist() {
        return this.masterPlaylist;
    }

    public HlsMediaPlaylist getPlaylistSnapshot(HlsUrl url) {
        HlsMediaPlaylist snapshot = ((MediaPlaylistBundle) this.playlistBundles.get(url)).getPlaylistSnapshot();
        if (snapshot != null) {
            maybeSetPrimaryUrl(url);
        }
        return snapshot;
    }

    public boolean isSnapshotValid(HlsUrl url) {
        return ((MediaPlaylistBundle) this.playlistBundles.get(url)).isSnapshotValid();
    }

    public void release() {
        this.initialPlaylistLoader.release();
        for (MediaPlaylistBundle bundle : this.playlistBundles.values()) {
            bundle.release();
        }
        this.playlistRefreshHandler.removeCallbacksAndMessages(null);
        this.playlistBundles.clear();
    }

    public void maybeThrowPrimaryPlaylistRefreshError() throws IOException {
        this.initialPlaylistLoader.maybeThrowError();
        if (this.primaryHlsUrl != null) {
            maybeThrowPlaylistRefreshError(this.primaryHlsUrl);
        }
    }

    public void maybeThrowPlaylistRefreshError(HlsUrl url) throws IOException {
        ((MediaPlaylistBundle) this.playlistBundles.get(url)).maybeThrowPlaylistRefreshError();
    }

    public void refreshPlaylist(HlsUrl url) {
        ((MediaPlaylistBundle) this.playlistBundles.get(url)).loadPlaylist();
    }

    public boolean isLive() {
        return this.isLive;
    }

    public void onLoadCompleted(ParsingLoadable<HlsPlaylist> loadable, long elapsedRealtimeMs, long loadDurationMs) {
        HlsMasterPlaylist masterPlaylist;
        HlsPlaylist result = (HlsPlaylist) loadable.getResult();
        boolean isMediaPlaylist = result instanceof HlsMediaPlaylist;
        if (isMediaPlaylist) {
            masterPlaylist = HlsMasterPlaylist.createSingleVariantMasterPlaylist(result.baseUri);
        } else {
            masterPlaylist = (HlsMasterPlaylist) result;
        }
        this.masterPlaylist = masterPlaylist;
        this.primaryHlsUrl = (HlsUrl) masterPlaylist.variants.get(0);
        ArrayList<HlsUrl> urls = new ArrayList();
        urls.addAll(masterPlaylist.variants);
        urls.addAll(masterPlaylist.audios);
        urls.addAll(masterPlaylist.subtitles);
        createBundles(urls);
        MediaPlaylistBundle primaryBundle = (MediaPlaylistBundle) this.playlistBundles.get(this.primaryHlsUrl);
        if (isMediaPlaylist) {
            primaryBundle.processLoadedPlaylist((HlsMediaPlaylist) result);
        } else {
            primaryBundle.loadPlaylist();
        }
        this.eventDispatcher.loadCompleted(loadable.dataSpec, 4, elapsedRealtimeMs, loadDurationMs, loadable.bytesLoaded());
    }

    public void onLoadCanceled(ParsingLoadable<HlsPlaylist> loadable, long elapsedRealtimeMs, long loadDurationMs, boolean released) {
        this.eventDispatcher.loadCanceled(loadable.dataSpec, 4, elapsedRealtimeMs, loadDurationMs, loadable.bytesLoaded());
    }

    public int onLoadError(ParsingLoadable<HlsPlaylist> loadable, long elapsedRealtimeMs, long loadDurationMs, IOException error) {
        boolean isFatal = error instanceof ParserException;
        this.eventDispatcher.loadError(loadable.dataSpec, 4, elapsedRealtimeMs, loadDurationMs, loadable.bytesLoaded(), error, isFatal);
        return isFatal ? 3 : 0;
    }

    private boolean maybeSelectNewPrimaryUrl() {
        List<HlsUrl> variants = this.masterPlaylist.variants;
        int variantsSize = variants.size();
        long currentTimeMs = SystemClock.elapsedRealtime();
        for (int i = 0; i < variantsSize; i++) {
            MediaPlaylistBundle bundle = (MediaPlaylistBundle) this.playlistBundles.get(variants.get(i));
            if (currentTimeMs > bundle.blacklistUntilMs) {
                this.primaryHlsUrl = bundle.playlistUrl;
                bundle.loadPlaylist();
                return true;
            }
        }
        return false;
    }

    private void maybeSetPrimaryUrl(HlsUrl url) {
        if (!this.masterPlaylist.variants.contains(url)) {
            return;
        }
        if ((this.primaryUrlSnapshot == null || !this.primaryUrlSnapshot.hasEndTag) && ((MediaPlaylistBundle) this.playlistBundles.get(this.primaryHlsUrl)).lastSnapshotAccessTimeMs - SystemClock.elapsedRealtime() > PRIMARY_URL_KEEPALIVE_MS) {
            this.primaryHlsUrl = url;
            ((MediaPlaylistBundle) this.playlistBundles.get(this.primaryHlsUrl)).loadPlaylist();
        }
    }

    private void createBundles(List<HlsUrl> urls) {
        int listSize = urls.size();
        long currentTimeMs = SystemClock.elapsedRealtime();
        for (int i = 0; i < listSize; i++) {
            HlsUrl url = (HlsUrl) urls.get(i);
            this.playlistBundles.put(url, new MediaPlaylistBundle(url, currentTimeMs));
        }
    }

    private boolean onPlaylistUpdated(HlsUrl url, HlsMediaPlaylist newSnapshot) {
        if (url == this.primaryHlsUrl) {
            if (this.primaryUrlSnapshot == null) {
                boolean z;
                if (newSnapshot.hasEndTag) {
                    z = false;
                } else {
                    z = true;
                }
                this.isLive = z;
            }
            this.primaryUrlSnapshot = newSnapshot;
            this.primaryPlaylistListener.onPrimaryPlaylistRefreshed(newSnapshot);
        }
        int listenersSize = this.listeners.size();
        for (int i = 0; i < listenersSize; i++) {
            ((PlaylistEventListener) this.listeners.get(i)).onPlaylistChanged();
        }
        if (url != this.primaryHlsUrl || newSnapshot.hasEndTag) {
            return false;
        }
        return true;
    }

    private void notifyPlaylistBlacklisting(HlsUrl url, long blacklistMs) {
        int listenersSize = this.listeners.size();
        for (int i = 0; i < listenersSize; i++) {
            ((PlaylistEventListener) this.listeners.get(i)).onPlaylistBlacklisted(url, blacklistMs);
        }
    }

    private HlsMediaPlaylist getLatestPlaylistSnapshot(HlsMediaPlaylist oldPlaylist, HlsMediaPlaylist loadedPlaylist) {
        if (loadedPlaylist.isNewerThan(oldPlaylist)) {
            return loadedPlaylist.copyWith(getLoadedPlaylistStartTimeUs(oldPlaylist, loadedPlaylist), getLoadedPlaylistDiscontinuitySequence(oldPlaylist, loadedPlaylist));
        }
        if (loadedPlaylist.hasEndTag) {
            return oldPlaylist.copyWithEndTag();
        }
        return oldPlaylist;
    }

    private long getLoadedPlaylistStartTimeUs(HlsMediaPlaylist oldPlaylist, HlsMediaPlaylist loadedPlaylist) {
        if (loadedPlaylist.hasProgramDateTime) {
            return loadedPlaylist.startTimeUs;
        }
        long j = this.primaryUrlSnapshot != null ? this.primaryUrlSnapshot.startTimeUs : 0;
        if (oldPlaylist == null) {
            return j;
        }
        int oldPlaylistSize = oldPlaylist.segments.size();
        Segment firstOldOverlappingSegment = getFirstOldOverlappingSegment(oldPlaylist, loadedPlaylist);
        if (firstOldOverlappingSegment != null) {
            return oldPlaylist.startTimeUs + firstOldOverlappingSegment.relativeStartTimeUs;
        }
        if (oldPlaylistSize == loadedPlaylist.mediaSequence - oldPlaylist.mediaSequence) {
            return oldPlaylist.getEndTimeUs();
        }
        return j;
    }

    private int getLoadedPlaylistDiscontinuitySequence(HlsMediaPlaylist oldPlaylist, HlsMediaPlaylist loadedPlaylist) {
        if (loadedPlaylist.hasDiscontinuitySequence) {
            return loadedPlaylist.discontinuitySequence;
        }
        int primaryUrlDiscontinuitySequence;
        if (this.primaryUrlSnapshot != null) {
            primaryUrlDiscontinuitySequence = this.primaryUrlSnapshot.discontinuitySequence;
        } else {
            primaryUrlDiscontinuitySequence = 0;
        }
        if (oldPlaylist == null) {
            return primaryUrlDiscontinuitySequence;
        }
        Segment firstOldOverlappingSegment = getFirstOldOverlappingSegment(oldPlaylist, loadedPlaylist);
        if (firstOldOverlappingSegment != null) {
            return (oldPlaylist.discontinuitySequence + firstOldOverlappingSegment.relativeDiscontinuitySequence) - ((Segment) loadedPlaylist.segments.get(0)).relativeDiscontinuitySequence;
        }
        return primaryUrlDiscontinuitySequence;
    }

    private static Segment getFirstOldOverlappingSegment(HlsMediaPlaylist oldPlaylist, HlsMediaPlaylist loadedPlaylist) {
        int mediaSequenceOffset = loadedPlaylist.mediaSequence - oldPlaylist.mediaSequence;
        List<Segment> oldSegments = oldPlaylist.segments;
        return mediaSequenceOffset < oldSegments.size() ? (Segment) oldSegments.get(mediaSequenceOffset) : null;
    }
}
