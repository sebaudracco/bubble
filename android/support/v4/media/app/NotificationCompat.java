package android.support.v4.media.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.media.session.MediaSession;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.mediacompat.C0087R;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v4.app.NotificationCompat.Action;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationCompat.Style;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.widget.RemoteViews;

public class NotificationCompat {

    public static class MediaStyle extends Style {
        private static final int MAX_MEDIA_BUTTONS = 5;
        private static final int MAX_MEDIA_BUTTONS_IN_COMPACT = 3;
        int[] mActionsToShowInCompact = null;
        PendingIntent mCancelButtonIntent;
        boolean mShowCancelButton;
        Token mToken;

        public static Token getMediaSession(Notification notification) {
            Bundle extras = android.support.v4.app.NotificationCompat.getExtras(notification);
            if (extras != null) {
                if (VERSION.SDK_INT >= 21) {
                    Parcelable tokenInner = extras.getParcelable(android.support.v4.app.NotificationCompat.EXTRA_MEDIA_SESSION);
                    if (tokenInner != null) {
                        return Token.fromToken(tokenInner);
                    }
                }
                IBinder tokenInner2 = BundleCompat.getBinder(extras, android.support.v4.app.NotificationCompat.EXTRA_MEDIA_SESSION);
                if (tokenInner2 != null) {
                    Parcel p = Parcel.obtain();
                    p.writeStrongBinder(tokenInner2);
                    p.setDataPosition(0);
                    Token token = (Token) Token.CREATOR.createFromParcel(p);
                    p.recycle();
                    return token;
                }
            }
            return null;
        }

        public MediaStyle(Builder builder) {
            setBuilder(builder);
        }

        public MediaStyle setShowActionsInCompactView(int... actions) {
            this.mActionsToShowInCompact = actions;
            return this;
        }

        public MediaStyle setMediaSession(Token token) {
            this.mToken = token;
            return this;
        }

        public MediaStyle setShowCancelButton(boolean show) {
            if (VERSION.SDK_INT < 21) {
                this.mShowCancelButton = show;
            }
            return this;
        }

        public MediaStyle setCancelButtonIntent(PendingIntent pendingIntent) {
            this.mCancelButtonIntent = pendingIntent;
            return this;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor builder) {
            if (VERSION.SDK_INT >= 21) {
                builder.getBuilder().setStyle(fillInMediaStyle(new android.app.Notification.MediaStyle()));
            } else if (this.mShowCancelButton) {
                builder.getBuilder().setOngoing(true);
            }
        }

        @RequiresApi(21)
        android.app.Notification.MediaStyle fillInMediaStyle(android.app.Notification.MediaStyle style) {
            if (this.mActionsToShowInCompact != null) {
                style.setShowActionsInCompactView(this.mActionsToShowInCompact);
            }
            if (this.mToken != null) {
                style.setMediaSession((MediaSession.Token) this.mToken.getToken());
            }
            return style;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor builder) {
            if (VERSION.SDK_INT >= 21) {
                return null;
            }
            return generateContentView();
        }

        RemoteViews generateContentView() {
            int numActionsInCompact;
            RemoteViews view = applyStandardTemplate(false, getContentViewLayoutResource(), true);
            int numActions = this.mBuilder.mActions.size();
            if (this.mActionsToShowInCompact == null) {
                numActionsInCompact = 0;
            } else {
                numActionsInCompact = Math.min(this.mActionsToShowInCompact.length, 3);
            }
            view.removeAllViews(C0087R.id.media_actions);
            if (numActionsInCompact > 0) {
                for (int i = 0; i < numActionsInCompact; i++) {
                    if (i >= numActions) {
                        throw new IllegalArgumentException(String.format("setShowActionsInCompactView: action %d out of bounds (max %d)", new Object[]{Integer.valueOf(i), Integer.valueOf(numActions - 1)}));
                    }
                    view.addView(C0087R.id.media_actions, generateMediaActionButton((Action) this.mBuilder.mActions.get(this.mActionsToShowInCompact[i])));
                }
            }
            if (this.mShowCancelButton) {
                view.setViewVisibility(C0087R.id.end_padder, 8);
                view.setViewVisibility(C0087R.id.cancel_action, 0);
                view.setOnClickPendingIntent(C0087R.id.cancel_action, this.mCancelButtonIntent);
                view.setInt(C0087R.id.cancel_action, "setAlpha", this.mBuilder.mContext.getResources().getInteger(C0087R.integer.cancel_button_image_alpha));
            } else {
                view.setViewVisibility(C0087R.id.end_padder, 0);
                view.setViewVisibility(C0087R.id.cancel_action, 8);
            }
            return view;
        }

        private RemoteViews generateMediaActionButton(Action action) {
            boolean tombstone = action.getActionIntent() == null;
            RemoteViews button = new RemoteViews(this.mBuilder.mContext.getPackageName(), C0087R.layout.notification_media_action);
            button.setImageViewResource(C0087R.id.action0, action.getIcon());
            if (!tombstone) {
                button.setOnClickPendingIntent(C0087R.id.action0, action.getActionIntent());
            }
            if (VERSION.SDK_INT >= 15) {
                button.setContentDescription(C0087R.id.action0, action.getTitle());
            }
            return button;
        }

        int getContentViewLayoutResource() {
            return C0087R.layout.notification_template_media;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor builder) {
            if (VERSION.SDK_INT >= 21) {
                return null;
            }
            return generateBigContentView();
        }

        RemoteViews generateBigContentView() {
            int actionCount = Math.min(this.mBuilder.mActions.size(), 5);
            RemoteViews big = applyStandardTemplate(false, getBigContentViewLayoutResource(actionCount), false);
            big.removeAllViews(C0087R.id.media_actions);
            if (actionCount > 0) {
                for (int i = 0; i < actionCount; i++) {
                    big.addView(C0087R.id.media_actions, generateMediaActionButton((Action) this.mBuilder.mActions.get(i)));
                }
            }
            if (this.mShowCancelButton) {
                big.setViewVisibility(C0087R.id.cancel_action, 0);
                big.setInt(C0087R.id.cancel_action, "setAlpha", this.mBuilder.mContext.getResources().getInteger(C0087R.integer.cancel_button_image_alpha));
                big.setOnClickPendingIntent(C0087R.id.cancel_action, this.mCancelButtonIntent);
            } else {
                big.setViewVisibility(C0087R.id.cancel_action, 8);
            }
            return big;
        }

        int getBigContentViewLayoutResource(int actionCount) {
            return actionCount <= 3 ? C0087R.layout.notification_template_big_media_narrow : C0087R.layout.notification_template_big_media;
        }
    }

    public static class DecoratedMediaCustomViewStyle extends MediaStyle {
        @RestrictTo({Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor builder) {
            if (VERSION.SDK_INT >= 24) {
                builder.getBuilder().setStyle(fillInMediaStyle(new android.app.Notification.DecoratedMediaCustomViewStyle()));
            } else {
                super.apply(builder);
            }
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor builder) {
            if (VERSION.SDK_INT >= 24) {
                return null;
            }
            boolean hasContentView;
            if (this.mBuilder.getContentView() != null) {
                hasContentView = true;
            } else {
                hasContentView = false;
            }
            RemoteViews contentView;
            if (VERSION.SDK_INT >= 21) {
                boolean createCustomContent;
                if (hasContentView || this.mBuilder.getBigContentView() != null) {
                    createCustomContent = true;
                } else {
                    createCustomContent = false;
                }
                if (createCustomContent) {
                    contentView = generateContentView();
                    if (hasContentView) {
                        buildIntoRemoteViews(contentView, this.mBuilder.getContentView());
                    }
                    setBackgroundColor(contentView);
                    return contentView;
                }
            }
            contentView = generateContentView();
            if (hasContentView) {
                buildIntoRemoteViews(contentView, this.mBuilder.getContentView());
                return contentView;
            }
            return null;
        }

        int getContentViewLayoutResource() {
            if (this.mBuilder.getContentView() != null) {
                return C0087R.layout.notification_template_media_custom;
            }
            return super.getContentViewLayoutResource();
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor builder) {
            RemoteViews remoteViews = null;
            if (VERSION.SDK_INT < 24) {
                RemoteViews innerView;
                if (this.mBuilder.getBigContentView() != null) {
                    innerView = this.mBuilder.getBigContentView();
                } else {
                    innerView = this.mBuilder.getContentView();
                }
                if (innerView != null) {
                    remoteViews = generateBigContentView();
                    buildIntoRemoteViews(remoteViews, innerView);
                    if (VERSION.SDK_INT >= 21) {
                        setBackgroundColor(remoteViews);
                    }
                }
            }
            return remoteViews;
        }

        int getBigContentViewLayoutResource(int actionCount) {
            return actionCount <= 3 ? C0087R.layout.notification_template_big_media_narrow_custom : C0087R.layout.notification_template_big_media_custom;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor builder) {
            RemoteViews remoteViews = null;
            if (VERSION.SDK_INT < 24) {
                RemoteViews innerView;
                if (this.mBuilder.getHeadsUpContentView() != null) {
                    innerView = this.mBuilder.getHeadsUpContentView();
                } else {
                    innerView = this.mBuilder.getContentView();
                }
                if (innerView != null) {
                    remoteViews = generateBigContentView();
                    buildIntoRemoteViews(remoteViews, innerView);
                    if (VERSION.SDK_INT >= 21) {
                        setBackgroundColor(remoteViews);
                    }
                }
            }
            return remoteViews;
        }

        private void setBackgroundColor(RemoteViews views) {
            int color;
            if (this.mBuilder.getColor() != 0) {
                color = this.mBuilder.getColor();
            } else {
                color = this.mBuilder.mContext.getResources().getColor(C0087R.color.notification_material_background_media_default_color);
            }
            views.setInt(C0087R.id.status_bar_latest_event_content, "setBackgroundColor", color);
        }
    }

    private NotificationCompat() {
    }
}
