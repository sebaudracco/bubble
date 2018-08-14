package com.google.android.exoplayer2.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction;
import com.google.android.exoplayer2.ui.TimeBar.OnScrubListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.Formatter;
import java.util.Locale;

public class DefaultTimeBar extends View implements TimeBar {
    private static final int DEFAULT_AD_MARKER_COLOR = -1291845888;
    private static final int DEFAULT_AD_MARKER_WIDTH = 4;
    private static final int DEFAULT_BAR_HEIGHT = 4;
    private static final int DEFAULT_INCREMENT_COUNT = 20;
    private static final int DEFAULT_PLAYED_COLOR = -1;
    private static final int DEFAULT_SCRUBBER_DISABLED_SIZE = 0;
    private static final int DEFAULT_SCRUBBER_DRAGGED_SIZE = 16;
    private static final int DEFAULT_SCRUBBER_ENABLED_SIZE = 12;
    private static final int DEFAULT_TOUCH_TARGET_HEIGHT = 26;
    private static final int FINE_SCRUB_RATIO = 3;
    private static final int FINE_SCRUB_Y_THRESHOLD = -50;
    private static final long STOP_SCRUBBING_TIMEOUT_MS = 1000;
    private int adBreakCount;
    private long[] adBreakTimesMs;
    private final Paint adMarkerPaint = new Paint();
    private final int adMarkerWidth;
    private final int barHeight;
    private final Rect bufferedBar = new Rect();
    private final Paint bufferedPaint = new Paint();
    private long bufferedPosition;
    private long duration;
    private final int fineScrubYThreshold;
    private final StringBuilder formatBuilder;
    private final Formatter formatter;
    private int keyCountIncrement;
    private long keyTimeIncrement;
    private int lastCoarseScrubXPosition;
    private OnScrubListener listener;
    private int[] locationOnScreen;
    private final Paint playedPaint = new Paint();
    private long position;
    private final Rect progressBar = new Rect();
    private long scrubPosition;
    private final Rect scrubberBar = new Rect();
    private final int scrubberDisabledSize;
    private final int scrubberDraggedSize;
    private final int scrubberEnabledSize;
    private final int scrubberPadding;
    private final Paint scrubberPaint = new Paint();
    private boolean scrubbing;
    private final Rect seekBounds = new Rect();
    private final Runnable stopScrubbingRunnable;
    private Point touchPosition;
    private final int touchTargetHeight;
    private final Paint unplayedPaint = new Paint();

    class C27651 implements Runnable {
        C27651() {
        }

        public void run() {
            DefaultTimeBar.this.stopScrubbing(false);
        }
    }

    public DefaultTimeBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.fineScrubYThreshold = dpToPx(displayMetrics, FINE_SCRUB_Y_THRESHOLD);
        int defaultBarHeight = dpToPx(displayMetrics, 4);
        int defaultTouchTargetHeight = dpToPx(displayMetrics, 26);
        int defaultAdMarkerWidth = dpToPx(displayMetrics, 4);
        int defaultScrubberEnabledSize = dpToPx(displayMetrics, 12);
        int defaultScrubberDisabledSize = dpToPx(displayMetrics, 0);
        int defaultScrubberDraggedSize = dpToPx(displayMetrics, 16);
        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DefaultTimeBar, 0, 0);
            try {
                this.barHeight = a.getDimensionPixelSize(R.styleable.DefaultTimeBar_bar_height, defaultBarHeight);
                this.touchTargetHeight = a.getDimensionPixelSize(R.styleable.DefaultTimeBar_touch_target_height, defaultTouchTargetHeight);
                this.adMarkerWidth = a.getDimensionPixelSize(R.styleable.DefaultTimeBar_ad_marker_width, defaultAdMarkerWidth);
                this.scrubberEnabledSize = a.getDimensionPixelSize(R.styleable.DefaultTimeBar_scrubber_enabled_size, defaultScrubberEnabledSize);
                this.scrubberDisabledSize = a.getDimensionPixelSize(R.styleable.DefaultTimeBar_scrubber_disabled_size, defaultScrubberDisabledSize);
                this.scrubberDraggedSize = a.getDimensionPixelSize(R.styleable.DefaultTimeBar_scrubber_dragged_size, defaultScrubberDraggedSize);
                int playedColor = a.getInt(R.styleable.DefaultTimeBar_played_color, -1);
                int scrubberColor = a.getInt(R.styleable.DefaultTimeBar_scrubber_color, getDefaultScrubberColor(playedColor));
                int bufferedColor = a.getInt(R.styleable.DefaultTimeBar_buffered_color, getDefaultBufferedColor(playedColor));
                int unplayedColor = a.getInt(R.styleable.DefaultTimeBar_unplayed_color, getDefaultUnplayedColor(playedColor));
                int adMarkerColor = a.getInt(R.styleable.DefaultTimeBar_ad_marker_color, DEFAULT_AD_MARKER_COLOR);
                this.playedPaint.setColor(playedColor);
                this.scrubberPaint.setColor(scrubberColor);
                this.bufferedPaint.setColor(bufferedColor);
                this.unplayedPaint.setColor(unplayedColor);
                this.adMarkerPaint.setColor(adMarkerColor);
            } finally {
                a.recycle();
            }
        } else {
            this.barHeight = defaultBarHeight;
            this.touchTargetHeight = defaultTouchTargetHeight;
            this.adMarkerWidth = defaultAdMarkerWidth;
            this.scrubberEnabledSize = defaultScrubberEnabledSize;
            this.scrubberDisabledSize = defaultScrubberDisabledSize;
            this.scrubberDraggedSize = defaultScrubberDraggedSize;
            this.playedPaint.setColor(-1);
            this.scrubberPaint.setColor(getDefaultScrubberColor(-1));
            this.bufferedPaint.setColor(getDefaultBufferedColor(-1));
            this.unplayedPaint.setColor(getDefaultUnplayedColor(-1));
            this.adMarkerPaint.setColor(DEFAULT_AD_MARKER_COLOR);
        }
        this.formatBuilder = new StringBuilder();
        this.formatter = new Formatter(this.formatBuilder, Locale.getDefault());
        this.stopScrubbingRunnable = new C27651();
        this.scrubberPadding = (Math.max(this.scrubberDisabledSize, Math.max(this.scrubberEnabledSize, this.scrubberDraggedSize)) + 1) / 2;
        this.duration = -9223372036854775807L;
        this.keyTimeIncrement = -9223372036854775807L;
        this.keyCountIncrement = 20;
        setFocusable(true);
        if (Util.SDK_INT >= 16) {
            maybeSetImportantForAccessibilityV16();
        }
    }

    public void setListener(OnScrubListener listener) {
        this.listener = listener;
    }

    public void setKeyTimeIncrement(long time) {
        Assertions.checkArgument(time > 0);
        this.keyCountIncrement = -1;
        this.keyTimeIncrement = time;
    }

    public void setKeyCountIncrement(int count) {
        Assertions.checkArgument(count > 0);
        this.keyCountIncrement = count;
        this.keyTimeIncrement = -9223372036854775807L;
    }

    public void setPosition(long position) {
        this.position = position;
        setContentDescription(getProgressText());
        update();
    }

    public void setBufferedPosition(long bufferedPosition) {
        this.bufferedPosition = bufferedPosition;
        update();
    }

    public void setDuration(long duration) {
        this.duration = duration;
        if (this.scrubbing && duration == -9223372036854775807L) {
            stopScrubbing(true);
        }
        update();
    }

    public void setAdBreakTimesMs(@Nullable long[] adBreakTimesMs, int adBreakCount) {
        boolean z = adBreakCount == 0 || adBreakTimesMs != null;
        Assertions.checkArgument(z);
        this.adBreakCount = adBreakCount;
        this.adBreakTimesMs = adBreakTimesMs;
        update();
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (this.scrubbing && !enabled) {
            stopScrubbing(true);
        }
    }

    public void onDraw(Canvas canvas) {
        canvas.save();
        drawTimeBar(canvas);
        drawPlayhead(canvas);
        canvas.restore();
    }

    public boolean onTouchEvent(MotionEvent event) {
        boolean z = false;
        if (!isEnabled() || this.duration <= 0) {
            return false;
        }
        Point touchPosition = resolveRelativeTouchPosition(event);
        int x = touchPosition.x;
        int y = touchPosition.y;
        switch (event.getAction()) {
            case 0:
                if (isInSeekBar((float) x, (float) y)) {
                    startScrubbing();
                    positionScrubber((float) x);
                    this.scrubPosition = getScrubberPosition();
                    update();
                    invalidate();
                    return true;
                }
                break;
            case 1:
            case 3:
                if (this.scrubbing) {
                    if (event.getAction() == 3) {
                        z = true;
                    }
                    stopScrubbing(z);
                    return true;
                }
                break;
            case 2:
                if (this.scrubbing) {
                    if (y < this.fineScrubYThreshold) {
                        positionScrubber((float) (this.lastCoarseScrubXPosition + ((x - this.lastCoarseScrubXPosition) / 3)));
                    } else {
                        this.lastCoarseScrubXPosition = x;
                        positionScrubber((float) x);
                    }
                    this.scrubPosition = getScrubberPosition();
                    if (this.listener != null) {
                        this.listener.onScrubMove(this, this.scrubPosition);
                    }
                    update();
                    invalidate();
                    return true;
                }
                break;
        }
        return false;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (isEnabled()) {
            long positionIncrement = getPositionIncrement();
            switch (keyCode) {
                case 21:
                    positionIncrement = -positionIncrement;
                    break;
                case 22:
                    break;
                case 23:
                case 66:
                    if (this.scrubbing) {
                        removeCallbacks(this.stopScrubbingRunnable);
                        this.stopScrubbingRunnable.run();
                        return true;
                    }
                    break;
            }
            if (scrubIncrementally(positionIncrement)) {
                removeCallbacks(this.stopScrubbingRunnable);
                postDelayed(this.stopScrubbingRunnable, STOP_SCRUBBING_TIMEOUT_MS);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int height = heightMode == 0 ? this.touchTargetHeight : heightMode == 1073741824 ? heightSize : Math.min(this.touchTargetHeight, heightSize);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), height);
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int width = right - left;
        int barY = ((bottom - top) - this.touchTargetHeight) / 2;
        int progressY = barY + ((this.touchTargetHeight - this.barHeight) / 2);
        this.seekBounds.set(getPaddingLeft(), barY, width - getPaddingRight(), this.touchTargetHeight + barY);
        this.progressBar.set(this.seekBounds.left + this.scrubberPadding, progressY, this.seekBounds.right - this.scrubberPadding, this.barHeight + progressY);
        update();
    }

    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
    }

    @TargetApi(14)
    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        super.onInitializeAccessibilityEvent(event);
        if (event.getEventType() == 4) {
            event.getText().add(getProgressText());
        }
        event.setClassName(DefaultTimeBar.class.getName());
    }

    @TargetApi(21)
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        info.setClassName(DefaultTimeBar.class.getCanonicalName());
        info.setContentDescription(getProgressText());
        if (this.duration > 0) {
            if (Util.SDK_INT >= 21) {
                info.addAction(AccessibilityAction.ACTION_SCROLL_FORWARD);
                info.addAction(AccessibilityAction.ACTION_SCROLL_BACKWARD);
            } else if (Util.SDK_INT >= 16) {
                info.addAction(4096);
                info.addAction(8192);
            }
        }
    }

    @TargetApi(16)
    public boolean performAccessibilityAction(int action, Bundle args) {
        if (super.performAccessibilityAction(action, args)) {
            return true;
        }
        if (this.duration <= 0) {
            return false;
        }
        if (action == 8192) {
            if (scrubIncrementally(-getPositionIncrement())) {
                stopScrubbing(false);
            }
        } else if (action != 4096) {
            return false;
        } else {
            if (scrubIncrementally(getPositionIncrement())) {
                stopScrubbing(false);
            }
        }
        sendAccessibilityEvent(4);
        return true;
    }

    @TargetApi(16)
    private void maybeSetImportantForAccessibilityV16() {
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
    }

    private void startScrubbing() {
        this.scrubbing = true;
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
        if (this.listener != null) {
            this.listener.onScrubStart(this, getScrubberPosition());
        }
    }

    private void stopScrubbing(boolean canceled) {
        this.scrubbing = false;
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
        invalidate();
        if (this.listener != null) {
            this.listener.onScrubStop(this, getScrubberPosition(), canceled);
        }
    }

    private void update() {
        this.bufferedBar.set(this.progressBar);
        this.scrubberBar.set(this.progressBar);
        long newScrubberTime = this.scrubbing ? this.scrubPosition : this.position;
        if (this.duration > 0) {
            this.bufferedBar.right = Math.min(this.progressBar.left + ((int) ((((long) this.progressBar.width()) * this.bufferedPosition) / this.duration)), this.progressBar.right);
            this.scrubberBar.right = Math.min(this.progressBar.left + ((int) ((((long) this.progressBar.width()) * newScrubberTime) / this.duration)), this.progressBar.right);
        } else {
            this.bufferedBar.right = this.progressBar.left;
            this.scrubberBar.right = this.progressBar.left;
        }
        invalidate(this.seekBounds);
    }

    private void positionScrubber(float xPosition) {
        this.scrubberBar.right = Util.constrainValue((int) xPosition, this.progressBar.left, this.progressBar.right);
    }

    private Point resolveRelativeTouchPosition(MotionEvent motionEvent) {
        if (this.locationOnScreen == null) {
            this.locationOnScreen = new int[2];
            this.touchPosition = new Point();
        }
        getLocationOnScreen(this.locationOnScreen);
        this.touchPosition.set(((int) motionEvent.getRawX()) - this.locationOnScreen[0], ((int) motionEvent.getRawY()) - this.locationOnScreen[1]);
        return this.touchPosition;
    }

    private long getScrubberPosition() {
        if (this.progressBar.width() <= 0 || this.duration == -9223372036854775807L) {
            return 0;
        }
        return (((long) this.scrubberBar.width()) * this.duration) / ((long) this.progressBar.width());
    }

    private boolean isInSeekBar(float x, float y) {
        return this.seekBounds.contains((int) x, (int) y);
    }

    private void drawTimeBar(Canvas canvas) {
        int progressBarHeight = this.progressBar.height();
        int barTop = this.progressBar.centerY() - (progressBarHeight / 2);
        int barBottom = barTop + progressBarHeight;
        if (this.duration <= 0) {
            canvas.drawRect((float) this.progressBar.left, (float) barTop, (float) this.progressBar.right, (float) barBottom, this.unplayedPaint);
            return;
        }
        int bufferedLeft = this.bufferedBar.left;
        int bufferedRight = this.bufferedBar.right;
        int progressLeft = Math.max(Math.max(this.progressBar.left, bufferedRight), this.scrubberBar.right);
        if (progressLeft < this.progressBar.right) {
            canvas.drawRect((float) progressLeft, (float) barTop, (float) this.progressBar.right, (float) barBottom, this.unplayedPaint);
        }
        bufferedLeft = Math.max(bufferedLeft, this.scrubberBar.right);
        if (bufferedRight > bufferedLeft) {
            canvas.drawRect((float) bufferedLeft, (float) barTop, (float) bufferedRight, (float) barBottom, this.bufferedPaint);
        }
        if (this.scrubberBar.width() > 0) {
            canvas.drawRect((float) this.scrubberBar.left, (float) barTop, (float) this.scrubberBar.right, (float) barBottom, this.playedPaint);
        }
        int adMarkerOffset = this.adMarkerWidth / 2;
        for (int i = 0; i < this.adBreakCount; i++) {
            int markerLeft = this.progressBar.left + Math.min(this.progressBar.width() - this.adMarkerWidth, Math.max(0, ((int) ((((long) this.progressBar.width()) * Util.constrainValue(this.adBreakTimesMs[i], 0, this.duration)) / this.duration)) - adMarkerOffset));
            canvas.drawRect((float) markerLeft, (float) barTop, (float) (this.adMarkerWidth + markerLeft), (float) barBottom, this.adMarkerPaint);
        }
    }

    private void drawPlayhead(Canvas canvas) {
        if (this.duration > 0) {
            int scrubberSize = (this.scrubbing || isFocused()) ? this.scrubberDraggedSize : isEnabled() ? this.scrubberEnabledSize : this.scrubberDisabledSize;
            canvas.drawCircle((float) Util.constrainValue(this.scrubberBar.right, this.scrubberBar.left, this.progressBar.right), (float) this.scrubberBar.centerY(), (float) (scrubberSize / 2), this.scrubberPaint);
        }
    }

    private String getProgressText() {
        return Util.getStringForTime(this.formatBuilder, this.formatter, this.position);
    }

    private long getPositionIncrement() {
        if (this.keyTimeIncrement == -9223372036854775807L) {
            return this.duration == -9223372036854775807L ? 0 : this.duration / ((long) this.keyCountIncrement);
        } else {
            return this.keyTimeIncrement;
        }
    }

    private boolean scrubIncrementally(long positionChange) {
        if (this.duration <= 0) {
            return false;
        }
        long scrubberPosition = getScrubberPosition();
        this.scrubPosition = Util.constrainValue(scrubberPosition + positionChange, 0, this.duration);
        if (this.scrubPosition == scrubberPosition) {
            return false;
        }
        if (!this.scrubbing) {
            startScrubbing();
        }
        if (this.listener != null) {
            this.listener.onScrubMove(this, this.scrubPosition);
        }
        update();
        return true;
    }

    private static int dpToPx(DisplayMetrics displayMetrics, int dps) {
        return (int) ((((float) dps) * displayMetrics.density) + 0.5f);
    }

    private static int getDefaultScrubberColor(int playedColor) {
        return -16777216 | playedColor;
    }

    private static int getDefaultUnplayedColor(int playedColor) {
        return 855638016 | (ViewCompat.MEASURED_SIZE_MASK & playedColor);
    }

    private static int getDefaultBufferedColor(int playedColor) {
        return -872415232 | (ViewCompat.MEASURED_SIZE_MASK & playedColor);
    }
}
