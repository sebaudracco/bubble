package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnShowListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.support.v4.content.FileProvider;
import android.support.v4.view.InputDeviceCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.appsgeyser.sdk.AppsgeyserSDK;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackBaseAdapter.RewardedVideoListener;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.elephant.data.ElephantLib;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.plus.PlusShare;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;
import com.stepleaderdigital.reveal.Reveal;
import com.stepleaderdigital.reveal.Reveal.PDUiBeacon;
import com.wBubble_7453037.R;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.cookie.ClientCookie;
import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.Semaphore;
import java.util.regex.Matcher;
import okhttp3.internal.http.StatusLine;
import org.slf4j.Marker;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.EmojiSuggestion;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MediaController$PhotoEntry;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessageObject.GroupedMessagePosition;
import org.telegram.messenger.MessageObject.GroupedMessages;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.MessagesStorage.IntCallback;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.NotificationCenter.NotificationCenterDelegate;
import org.telegram.messenger.NotificationsController;
import org.telegram.messenger.SecretChatHelper;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.SendMessagesHelper$SendingMediaInfo;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.messenger.browser.Browser;
import org.telegram.messenger.exoplayer2.ui.AspectRatioFrameLayout;
import org.telegram.messenger.query.BotQuery;
import org.telegram.messenger.query.DraftQuery;
import org.telegram.messenger.query.MessagesQuery;
import org.telegram.messenger.query.MessagesSearchQuery;
import org.telegram.messenger.query.SearchQuery;
import org.telegram.messenger.query.StickersQuery;
import org.telegram.messenger.support.widget.GridLayoutManager.SpanSizeLookup;
import org.telegram.messenger.support.widget.GridLayoutManagerFixed;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.support.widget.LinearSmoothScrollerMiddle;
import org.telegram.messenger.support.widget.RecyclerView;
import org.telegram.messenger.support.widget.RecyclerView.Adapter;
import org.telegram.messenger.support.widget.RecyclerView.ItemDecoration;
import org.telegram.messenger.support.widget.RecyclerView.LayoutManager;
import org.telegram.messenger.support.widget.RecyclerView.LayoutParams;
import org.telegram.messenger.support.widget.RecyclerView.OnScrollListener;
import org.telegram.messenger.support.widget.RecyclerView.State;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$BotInfo;
import org.telegram.tgnet.TLRPC$BotInlineResult;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$ChatFull;
import org.telegram.tgnet.TLRPC$ChatParticipant;
import org.telegram.tgnet.TLRPC$Document;
import org.telegram.tgnet.TLRPC$DocumentAttribute;
import org.telegram.tgnet.TLRPC$DraftMessage;
import org.telegram.tgnet.TLRPC$EncryptedChat;
import org.telegram.tgnet.TLRPC$FileLocation;
import org.telegram.tgnet.TLRPC$InputStickerSet;
import org.telegram.tgnet.TLRPC$KeyboardButton;
import org.telegram.tgnet.TLRPC$Message;
import org.telegram.tgnet.TLRPC$MessageEntity;
import org.telegram.tgnet.TLRPC$MessageMedia;
import org.telegram.tgnet.TLRPC$PhotoSize;
import org.telegram.tgnet.TLRPC$TL_botCommand;
import org.telegram.tgnet.TLRPC$TL_channelForbidden;
import org.telegram.tgnet.TLRPC$TL_channelFull;
import org.telegram.tgnet.TLRPC$TL_channelParticipantAdmin;
import org.telegram.tgnet.TLRPC$TL_channelParticipantCreator;
import org.telegram.tgnet.TLRPC$TL_channels_channelParticipant;
import org.telegram.tgnet.TLRPC$TL_channels_exportMessageLink;
import org.telegram.tgnet.TLRPC$TL_channels_getParticipant;
import org.telegram.tgnet.TLRPC$TL_channels_reportSpam;
import org.telegram.tgnet.TLRPC$TL_chatForbidden;
import org.telegram.tgnet.TLRPC$TL_chatFull;
import org.telegram.tgnet.TLRPC$TL_chatParticipantsForbidden;
import org.telegram.tgnet.TLRPC$TL_dialog;
import org.telegram.tgnet.TLRPC$TL_document;
import org.telegram.tgnet.TLRPC$TL_documentAttributeImageSize;
import org.telegram.tgnet.TLRPC$TL_documentAttributeVideo;
import org.telegram.tgnet.TLRPC$TL_encryptedChat;
import org.telegram.tgnet.TLRPC$TL_encryptedChatDiscarded;
import org.telegram.tgnet.TLRPC$TL_encryptedChatRequested;
import org.telegram.tgnet.TLRPC$TL_encryptedChatWaiting;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_fileLocationUnavailable;
import org.telegram.tgnet.TLRPC$TL_game;
import org.telegram.tgnet.TLRPC$TL_inlineBotSwitchPM;
import org.telegram.tgnet.TLRPC$TL_inputMessageEntityMentionName;
import org.telegram.tgnet.TLRPC$TL_inputStickerSetID;
import org.telegram.tgnet.TLRPC$TL_inputStickerSetShortName;
import org.telegram.tgnet.TLRPC$TL_keyboardButtonBuy;
import org.telegram.tgnet.TLRPC$TL_keyboardButtonCallback;
import org.telegram.tgnet.TLRPC$TL_keyboardButtonGame;
import org.telegram.tgnet.TLRPC$TL_keyboardButtonSwitchInline;
import org.telegram.tgnet.TLRPC$TL_keyboardButtonUrl;
import org.telegram.tgnet.TLRPC$TL_messageActionChatDeleteUser;
import org.telegram.tgnet.TLRPC$TL_messageActionEmpty;
import org.telegram.tgnet.TLRPC$TL_messageActionPhoneCall;
import org.telegram.tgnet.TLRPC$TL_messageActionPinMessage;
import org.telegram.tgnet.TLRPC$TL_messageEntityBold;
import org.telegram.tgnet.TLRPC$TL_messageEntityCode;
import org.telegram.tgnet.TLRPC$TL_messageEntityItalic;
import org.telegram.tgnet.TLRPC$TL_messageEntityMentionName;
import org.telegram.tgnet.TLRPC$TL_messageEntityPre;
import org.telegram.tgnet.TLRPC$TL_messageMediaGame;
import org.telegram.tgnet.TLRPC$TL_messageMediaPhoto;
import org.telegram.tgnet.TLRPC$TL_messageMediaWebPage;
import org.telegram.tgnet.TLRPC$TL_messages_getMessageEditData;
import org.telegram.tgnet.TLRPC$TL_messages_getUnreadMentions;
import org.telegram.tgnet.TLRPC$TL_messages_getWebPagePreview;
import org.telegram.tgnet.TLRPC$TL_messages_readMentions;
import org.telegram.tgnet.TLRPC$TL_peerNotifySettings;
import org.telegram.tgnet.TLRPC$TL_phoneCallDiscardReasonBusy;
import org.telegram.tgnet.TLRPC$TL_phoneCallDiscardReasonMissed;
import org.telegram.tgnet.TLRPC$TL_photoSizeEmpty;
import org.telegram.tgnet.TLRPC$TL_replyInlineMarkup;
import org.telegram.tgnet.TLRPC$TL_replyKeyboardForceReply;
import org.telegram.tgnet.TLRPC$TL_user;
import org.telegram.tgnet.TLRPC$TL_userFull;
import org.telegram.tgnet.TLRPC$TL_webPage;
import org.telegram.tgnet.TLRPC$TL_webPagePending;
import org.telegram.tgnet.TLRPC$WebPage;
import org.telegram.tgnet.TLRPC$messages_Messages;
import org.telegram.tgnet.TLRPC.User;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet.Builder;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.Theme.ThemeInfo;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.ActionBar.ThemeDescription.ThemeDescriptionDelegate;
import org.telegram.ui.Adapters.MentionsAdapter;
import org.telegram.ui.Adapters.MentionsAdapter.MentionsAdapterDelegate;
import org.telegram.ui.Adapters.StickersAdapter;
import org.telegram.ui.Adapters.StickersAdapter.StickersAdapterDelegate;
import org.telegram.ui.AudioSelectActivity.AudioSelectActivityDelegate;
import org.telegram.ui.Cells.BotHelpCell;
import org.telegram.ui.Cells.BotHelpCell.BotHelpCellDelegate;
import org.telegram.ui.Cells.BotSwitchCell;
import org.telegram.ui.Cells.ChatActionCell;
import org.telegram.ui.Cells.ChatActionCell.ChatActionCellDelegate;
import org.telegram.ui.Cells.ChatLoadingCell;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate;
import org.telegram.ui.Cells.ChatUnreadCell;
import org.telegram.ui.Cells.CheckBoxCell;
import org.telegram.ui.Cells.ContextLinkCell;
import org.telegram.ui.Cells.MentionCell;
import org.telegram.ui.Cells.StickerCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.ChatActivityEnterView;
import org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate;
import org.telegram.ui.Components.ChatAttachAlert;
import org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate;
import org.telegram.ui.Components.ChatAvatarContainer;
import org.telegram.ui.Components.ChatBigEmptyView;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.CorrectlyMeasuringTextView;
import org.telegram.ui.Components.EmbedBottomSheet;
import org.telegram.ui.Components.EmojiView;
import org.telegram.ui.Components.ExtendedGridLayoutManager;
import org.telegram.ui.Components.FragmentContextView;
import org.telegram.ui.Components.InstantCameraView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.NumberTextView;
import org.telegram.ui.Components.PipRoundVideoView;
import org.telegram.ui.Components.RadialProgressView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.Holder;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListener;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListenerExtended;
import org.telegram.ui.Components.RecyclerListView.OnItemLongClickListener;
import org.telegram.ui.Components.ShareAlert;
import org.telegram.ui.Components.Size;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.StickersAlert;
import org.telegram.ui.Components.StickersAlert.StickersAlertDelegate;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.Components.URLSpanBotCommand;
import org.telegram.ui.Components.URLSpanMono;
import org.telegram.ui.Components.URLSpanNoUnderline;
import org.telegram.ui.Components.URLSpanReplacement;
import org.telegram.ui.Components.URLSpanUserMention;
import org.telegram.ui.Components.voip.VoIPHelper;
import org.telegram.ui.DialogsActivity.DialogsActivityDelegate;
import org.telegram.ui.DocumentSelectActivity.DocumentSelectActivityDelegate;
import org.telegram.ui.LocationActivity.LocationActivityDelegate;
import org.telegram.ui.PhotoAlbumPickerActivity.PhotoAlbumPickerActivityDelegate;
import org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider;
import org.telegram.ui.PhotoViewer.PhotoViewerProvider;
import org.telegram.ui.PhotoViewer.PlaceProviderObject;

public class ChatActivity extends BaseFragment implements NotificationCenterDelegate, DialogsActivityDelegate, LocationActivityDelegate {
    private static final int add_shortcut = 24;
    private static final int attach_audio = 3;
    private static final int attach_contact = 5;
    private static final int attach_document = 4;
    private static final int attach_gallery = 1;
    private static final int attach_location = 6;
    private static final int attach_photo = 0;
    private static final int attach_video = 2;
    private static final int bot_help = 30;
    private static final int bot_settings = 31;
    private static final int call = 32;
    private static final int chat_enc_timer = 13;
    private static final int chat_menu_attach = 14;
    private static final int clear_history = 15;
    private static final int copy = 10;
    private static final int delete = 12;
    private static final int delete_chat = 16;
    private static final int edit = 23;
    private static final int forward = 11;
    private static final int id_chat_compose_panel = 1000;
    private static final int mute = 18;
    private static final int reply = 19;
    private static final int report = 21;
    private static final int search = 40;
    private static final int share_contact = 17;
    private static final int star = 22;
    private SimpleTextView actionModeSubTextView;
    private SimpleTextView actionModeTextView;
    private FrameLayout actionModeTitleContainer;
    private ArrayList<View> actionModeViews = new ArrayList();
    private TextView addContactItem;
    private TextView addToContactsButton;
    private TextView alertNameTextView;
    private TextView alertTextView;
    private FrameLayout alertView;
    private AnimatorSet alertViewAnimator;
    private boolean allowContextBotPanel;
    private boolean allowContextBotPanelSecond = true;
    private boolean allowStickersPanel;
    private ArrayList<MessageObject> animatingMessageObjects = new ArrayList();
    private Paint aspectPaint;
    private Path aspectPath;
    private AspectRatioFrameLayout aspectRatioFrameLayout;
    private ActionBarMenuItem attachItem;
    private ChatAvatarContainer avatarContainer;
    private ChatBigEmptyView bigEmptyView;
    private MessageObject botButtons;
    private PhotoViewerProvider botContextProvider = new C59183();
    private ArrayList<Object> botContextResults;
    private HashMap<Integer, TLRPC$BotInfo> botInfo = new HashMap();
    private MessageObject botReplyButtons;
    private String botUser;
    private int botsCount;
    private FrameLayout bottomOverlay;
    private FrameLayout bottomOverlayChat;
    private TextView bottomOverlayChatText;
    private TextView bottomOverlayText;
    private boolean[] cacheEndReached = new boolean[2];
    private ActionBarMenuItem callItem;
    private int canEditMessagesCount;
    private int cantDeleteMessagesCount;
    protected ChatActivityEnterView chatActivityEnterView;
    private ChatActivityAdapter chatAdapter;
    private ChatAttachAlert chatAttachAlert;
    private long chatEnterTime;
    private GridLayoutManagerFixed chatLayoutManager;
    private long chatLeaveTime;
    private RecyclerListView chatListView;
    private boolean chatListViewIgnoreLayout;
    private ArrayList<ChatMessageCell> chatMessageCellsCache = new ArrayList();
    private boolean checkTextureViewPosition;
    private Dialog closeChatDialog;
    private ImageView closePinned;
    private ImageView closeReportSpam;
    private SizeNotifierFrameLayout contentView;
    private int createUnreadMessageAfterId;
    private boolean createUnreadMessageAfterIdLoading;
    protected TLRPC$Chat currentChat;
    protected TLRPC$EncryptedChat currentEncryptedChat;
    private boolean currentFloatingDateOnScreen;
    private boolean currentFloatingTopIsNotMessage;
    private String currentPicturePath;
    protected User currentUser;
    private long dialog_id;
    private int editingMessageObjectReqId;
    private View emojiButtonRed;
    private TextView emptyView;
    private FrameLayout emptyViewContainer;
    private boolean[] endReached = new boolean[2];
    private boolean first = true;
    private boolean firstLoading = true;
    private int first_unread_id;
    private AnimatorSet floatingDateAnimation;
    private ChatActionCell floatingDateView;
    private boolean forceScrollToTop;
    private boolean[] forwardEndReached = new boolean[]{true, true};
    private MessageObject forwardingMessage;
    private GroupedMessages forwardingMessageGroup;
    private ArrayList<MessageObject> forwardingMessages;
    private ArrayList<CharSequence> foundUrls;
    private TLRPC$WebPage foundWebPage;
    private FragmentContextView fragmentContextView;
    private FragmentContextView fragmentLocationContextView;
    private TextView gifHintTextView;
    private HashMap<Long, GroupedMessages> groupedMessagesMap = new HashMap();
    private boolean hasAllMentionsLocal;
    private boolean hasBotsCommands;
    private boolean hasUnfavedSelected;
    private ActionBarMenuItem headerItem;
    private Runnable hideAlertViewRunnable;
    private int highlightMessageId = Integer.MAX_VALUE;
    private boolean ignoreAttachOnPause;
    protected TLRPC$ChatFull info;
    private long inlineReturn;
    private InstantCameraView instantCameraView;
    private boolean isBroadcast;
    private int lastLoadIndex;
    private int last_message_id = 0;
    private int linkSearchRequestId;
    private boolean loading;
    private boolean loadingForward;
    private boolean loadingFromOldPosition;
    private int loadingPinnedMessage;
    private int loadsCount;
    private int[] maxDate = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
    private int[] maxMessageId = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
    private TextView mediaBanTooltip;
    private FrameLayout mentionContainer;
    private ExtendedGridLayoutManager mentionGridLayoutManager;
    private LinearLayoutManager mentionLayoutManager;
    private AnimatorSet mentionListAnimation;
    private RecyclerListView mentionListView;
    private boolean mentionListViewIgnoreLayout;
    private boolean mentionListViewIsScrolling;
    private int mentionListViewLastViewPosition;
    private int mentionListViewLastViewTop;
    private int mentionListViewScrollOffsetY;
    private FrameLayout mentiondownButton;
    private ObjectAnimator mentiondownButtonAnimation;
    private TextView mentiondownButtonCounter;
    private ImageView mentiondownButtonImage;
    private MentionsAdapter mentionsAdapter;
    private OnItemClickListener mentionsOnItemClickListener;
    private long mergeDialogId;
    protected ArrayList<MessageObject> messages = new ArrayList();
    private HashMap<String, ArrayList<MessageObject>> messagesByDays = new HashMap();
    private HashMap<Integer, MessageObject>[] messagesDict = new HashMap[]{new HashMap(), new HashMap()};
    private int[] minDate = new int[2];
    private int[] minMessageId = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
    private TextView muteItem;
    private boolean needSelectFromMessageId;
    private int newMentionsCount;
    private int newUnreadMessageCount;
    OnItemClickListenerExtended onItemClickListener = new C59305();
    OnItemLongClickListener onItemLongClickListener = new C59214();
    private boolean openAnimationEnded;
    private boolean openSearchKeyboard;
    private View overlayView;
    private FrameLayout pagedownButton;
    private AnimatorSet pagedownButtonAnimation;
    private TextView pagedownButtonCounter;
    private ImageView pagedownButtonImage;
    private boolean pagedownButtonShowedByScroll;
    private boolean paused = true;
    private boolean pausedOnLastMessage;
    private String pendingLinkSearchString;
    private Runnable pendingWebPageTimeoutRunnable;
    private PhotoViewerProvider photoViewerProvider = new C59071();
    private TLRPC$FileLocation pinnedImageLocation;
    private View pinnedLineView;
    private BackupImageView pinnedMessageImageView;
    private SimpleTextView pinnedMessageNameTextView;
    private MessageObject pinnedMessageObject;
    private SimpleTextView pinnedMessageTextView;
    private FrameLayout pinnedMessageView;
    private AnimatorSet pinnedMessageViewAnimator;
    private RadialProgressView progressBar;
    private FrameLayout progressView;
    private View progressView2;
    private Runnable readRunnable = new C59142();
    private boolean readWhenResume;
    private int readWithDate;
    private int readWithMid;
    private AnimatorSet replyButtonAnimation;
    private ImageView replyCloseImageView;
    private ImageView replyIconImageView;
    private TLRPC$FileLocation replyImageLocation;
    private BackupImageView replyImageView;
    private View replyLineView;
    private SimpleTextView replyNameTextView;
    private SimpleTextView replyObjectTextView;
    private MessageObject replyingMessageObject;
    private TextView reportSpamButton;
    private FrameLayout reportSpamContainer;
    private LinearLayout reportSpamView;
    private AnimatorSet reportSpamViewAnimator;
    private int returnToLoadIndex;
    private int returnToMessageId;
    private FrameLayout roundVideoContainer;
    private AnimatorSet runningAnimation;
    private MessageObject scrollToMessage;
    private int scrollToMessagePosition = -10000;
    private int scrollToOffsetOnRecreate = 0;
    private int scrollToPositionOnRecreate = -1;
    private boolean scrollToTopOnResume;
    private boolean scrollToTopUnReadOnResume;
    private boolean scrollingFloatingDate;
    private ImageView searchCalendarButton;
    private FrameLayout searchContainer;
    private SimpleTextView searchCountText;
    private ImageView searchDownButton;
    private ActionBarMenuItem searchItem;
    private ImageView searchUpButton;
    private ImageView searchUserButton;
    private boolean searchingForUser;
    private User searchingUserMessages;
    private HashMap<Integer, MessageObject>[] selectedMessagesCanCopyIds = new HashMap[]{new HashMap(), new HashMap()};
    private HashMap<Integer, MessageObject>[] selectedMessagesCanStarIds = new HashMap[]{new HashMap(), new HashMap()};
    private NumberTextView selectedMessagesCountTextView;
    private HashMap<Integer, MessageObject>[] selectedMessagesIds = new HashMap[]{new HashMap(), new HashMap()};
    private MessageObject selectedObject;
    private GroupedMessages selectedObjectGroup;
    private int startLoadFromMessageId;
    private int startLoadFromMessageOffset = Integer.MAX_VALUE;
    private boolean startReplyOnTextChange;
    private String startVideoEdit;
    private StickersAdapter stickersAdapter;
    private RecyclerListView stickersListView;
    private OnItemClickListener stickersOnItemClickListener;
    private FrameLayout stickersPanel;
    private ImageView stickersPanelArrow;
    private View timeItem2;
    private int topViewWasVisible;
    private MessageObject unreadMessageObject;
    private int unread_to_load;
    private boolean userBlocked = false;
    private TextureView videoTextureView;
    private AnimatorSet voiceHintAnimation;
    private Runnable voiceHintHideRunnable;
    private TextView voiceHintTextView;
    private Runnable waitingForCharaterEnterRunnable;
    private ArrayList<Integer> waitingForLoad = new ArrayList();
    private boolean waitingForReplyMessageLoad;
    private boolean wasPaused;

    class C59071 extends EmptyPhotoViewerProvider {
        C59071() {
        }

        public PlaceProviderObject getPlaceForPhoto(MessageObject messageObject, TLRPC$FileLocation fileLocation, int index) {
            int count = ChatActivity.this.chatListView.getChildCount();
            for (int a = 0; a < count; a++) {
                ImageReceiver imageReceiver = null;
                View view = ChatActivity.this.chatListView.getChildAt(a);
                MessageObject message;
                if (view instanceof ChatMessageCell) {
                    if (messageObject != null) {
                        ChatMessageCell cell = (ChatMessageCell) view;
                        message = cell.getMessageObject();
                        if (message != null && message.getId() == messageObject.getId()) {
                            imageReceiver = cell.getPhotoImage();
                        }
                    }
                } else if (view instanceof ChatActionCell) {
                    ChatActionCell cell2 = (ChatActionCell) view;
                    message = cell2.getMessageObject();
                    if (message != null) {
                        if (messageObject != null) {
                            if (message.getId() == messageObject.getId()) {
                                imageReceiver = cell2.getPhotoImage();
                            }
                        } else if (fileLocation != null && message.photoThumbs != null) {
                            for (int b = 0; b < message.photoThumbs.size(); b++) {
                                TLRPC$PhotoSize photoSize = (TLRPC$PhotoSize) message.photoThumbs.get(b);
                                if (photoSize.location.volume_id == fileLocation.volume_id && photoSize.location.local_id == fileLocation.local_id) {
                                    imageReceiver = cell2.getPhotoImage();
                                    break;
                                }
                            }
                        }
                    }
                }
                if (imageReceiver != null) {
                    int[] coords = new int[2];
                    view.getLocationInWindow(coords);
                    PlaceProviderObject object = new PlaceProviderObject();
                    object.viewX = coords[0];
                    object.viewY = coords[1] - (VERSION.SDK_INT >= 21 ? 0 : AndroidUtilities.statusBarHeight);
                    object.parentView = ChatActivity.this.chatListView;
                    object.imageReceiver = imageReceiver;
                    object.thumb = imageReceiver.getBitmap();
                    object.radius = imageReceiver.getRoundRadius();
                    if ((view instanceof ChatActionCell) && ChatActivity.this.currentChat != null) {
                        object.dialogId = -ChatActivity.this.currentChat.id;
                    }
                    if ((ChatActivity.this.pinnedMessageView == null || ChatActivity.this.pinnedMessageView.getTag() != null) && (ChatActivity.this.reportSpamView == null || ChatActivity.this.reportSpamView.getTag() != null)) {
                        return object;
                    }
                    object.clipTopAddition = AndroidUtilities.dp(48.0f);
                    return object;
                }
            }
            return null;
        }
    }

    class C59142 implements Runnable {
        C59142() {
        }

        public void run() {
            if (ChatActivity.this.readWhenResume && !ChatActivity.this.messages.isEmpty()) {
                for (int a = 0; a < ChatActivity.this.messages.size(); a++) {
                    MessageObject messageObject = (MessageObject) ChatActivity.this.messages.get(a);
                    if (!messageObject.isUnread() && !messageObject.isOut()) {
                        break;
                    }
                    if (!messageObject.isOut()) {
                        messageObject.setIsRead();
                    }
                }
                ChatActivity.this.readWhenResume = false;
                MessagesController.getInstance().markDialogAsRead(ChatActivity.this.dialog_id, ((MessageObject) ChatActivity.this.messages.get(0)).getId(), ChatActivity.this.readWithMid, ChatActivity.this.readWithDate, true, false);
            }
        }
    }

    class C59183 extends EmptyPhotoViewerProvider {
        C59183() {
        }

        public PlaceProviderObject getPlaceForPhoto(MessageObject messageObject, TLRPC$FileLocation fileLocation, int index) {
            PlaceProviderObject placeProviderObject = null;
            int i = 0;
            if (index >= 0 && index < ChatActivity.this.botContextResults.size()) {
                int count = ChatActivity.this.mentionListView.getChildCount();
                TLRPC$BotInlineResult result = ChatActivity.this.botContextResults.get(index);
                int a = 0;
                while (a < count) {
                    ImageReceiver imageReceiver = null;
                    View view = ChatActivity.this.mentionListView.getChildAt(a);
                    if (view instanceof ContextLinkCell) {
                        ContextLinkCell cell = (ContextLinkCell) view;
                        if (cell.getResult() == result) {
                            imageReceiver = cell.getPhotoImage();
                        }
                    }
                    if (imageReceiver != null) {
                        int[] coords = new int[2];
                        view.getLocationInWindow(coords);
                        placeProviderObject = new PlaceProviderObject();
                        placeProviderObject.viewX = coords[0];
                        int i2 = coords[1];
                        if (VERSION.SDK_INT < 21) {
                            i = AndroidUtilities.statusBarHeight;
                        }
                        placeProviderObject.viewY = i2 - i;
                        placeProviderObject.parentView = ChatActivity.this.mentionListView;
                        placeProviderObject.imageReceiver = imageReceiver;
                        placeProviderObject.thumb = imageReceiver.getBitmap();
                        placeProviderObject.radius = imageReceiver.getRoundRadius();
                    } else {
                        a++;
                    }
                }
            }
            return placeProviderObject;
        }

        public void sendButtonPressed(int index, VideoEditedInfo videoEditedInfo) {
            if (index >= 0 && index < ChatActivity.this.botContextResults.size()) {
                ChatActivity.this.sendBotInlineResult((TLRPC$BotInlineResult) ChatActivity.this.botContextResults.get(index));
            }
        }
    }

    class C59214 implements OnItemLongClickListener {
        C59214() {
        }

        public boolean onItemClick(View view, int position) {
            if (ChatActivity.this.actionBar.isActionModeShowed()) {
                return false;
            }
            ChatActivity.this.createMenu(view, false, true);
            return true;
        }
    }

    class C59305 implements OnItemClickListenerExtended {
        C59305() {
        }

        public void onItemClick(View view, int position, float x, float y) {
            if (ChatActivity.this.actionBar.isActionModeShowed()) {
                boolean outside = false;
                if (view instanceof ChatMessageCell) {
                    if (((ChatMessageCell) view).isInsideBackground(x, y)) {
                        outside = false;
                    } else {
                        outside = true;
                    }
                }
                ChatActivity.this.processRowSelect(view, outside);
                return;
            }
            ChatActivity.this.createMenu(view, true, false);
        }
    }

    public class ChatActivityAdapter extends Adapter {
        private int botInfoRow = -1;
        private boolean isBot;
        private int loadingDownRow;
        private int loadingUpRow;
        private Context mContext;
        private int messagesEndRow;
        private int messagesStartRow;
        private int rowCount;

        class C59521 implements ChatMessageCellDelegate {
            C59521() {
            }

            public void didPressedShare(ChatMessageCell cell) {
                if (ChatActivity.this.getParentActivity() != null) {
                    if (ChatActivity.this.chatActivityEnterView != null) {
                        ChatActivity.this.chatActivityEnterView.closeKeyboard();
                    }
                    MessageObject messageObject = cell.getMessageObject();
                    if (!UserObject.isUserSelf(ChatActivity.this.currentUser) || messageObject.messageOwner.fwd_from.saved_from_peer == null) {
                        ArrayList<MessageObject> arrayList = null;
                        if (messageObject.getGroupId() != 0) {
                            GroupedMessages groupedMessages = (GroupedMessages) ChatActivity.this.groupedMessagesMap.get(Long.valueOf(messageObject.getGroupId()));
                            if (groupedMessages != null) {
                                arrayList = groupedMessages.messages;
                            }
                        }
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                            arrayList.add(messageObject);
                        }
                        ChatActivity chatActivity = ChatActivity.this;
                        Context access$20000 = ChatActivityAdapter.this.mContext;
                        boolean z = ChatObject.isChannel(ChatActivity.this.currentChat) && !ChatActivity.this.currentChat.megagroup && ChatActivity.this.currentChat.username != null && ChatActivity.this.currentChat.username.length() > 0;
                        chatActivity.showDialog(new ShareAlert(access$20000, arrayList, null, z, null, false));
                        return;
                    }
                    Bundle args = new Bundle();
                    if (messageObject.messageOwner.fwd_from.saved_from_peer.channel_id != 0) {
                        args.putInt("chat_id", messageObject.messageOwner.fwd_from.saved_from_peer.channel_id);
                    } else if (messageObject.messageOwner.fwd_from.saved_from_peer.chat_id != 0) {
                        args.putInt("chat_id", messageObject.messageOwner.fwd_from.saved_from_peer.chat_id);
                    } else if (messageObject.messageOwner.fwd_from.saved_from_peer.user_id != 0) {
                        args.putInt("user_id", messageObject.messageOwner.fwd_from.saved_from_peer.user_id);
                    }
                    args.putInt("message_id", messageObject.messageOwner.fwd_from.saved_from_msg_id);
                    if (MessagesController.checkCanOpenChat(args, ChatActivity.this)) {
                        ChatActivity.this.presentFragment(new ChatActivity(args));
                    }
                }
            }

            public boolean needPlayMessage(MessageObject messageObject) {
                if (!messageObject.isVoice() && !messageObject.isRoundVideo()) {
                    return messageObject.isMusic() ? MediaController.getInstance().setPlaylist(ChatActivity.this.messages, messageObject) : false;
                } else {
                    boolean result = MediaController.getInstance().playMessage(messageObject);
                    MediaController.getInstance().setVoiceMessagesPlaylist(result ? ChatActivity.this.createVoiceMessagesPlaylist(messageObject, false) : null, false);
                    return result;
                }
            }

            public void didPressedChannelAvatar(ChatMessageCell cell, TLRPC$Chat chat, int postId) {
                if (ChatActivity.this.actionBar.isActionModeShowed()) {
                    ChatActivity.this.processRowSelect(cell, true);
                } else if (chat != null && chat != ChatActivity.this.currentChat) {
                    Bundle args = new Bundle();
                    args.putInt("chat_id", chat.id);
                    if (postId != 0) {
                        args.putInt("message_id", postId);
                    }
                    if (MessagesController.checkCanOpenChat(args, ChatActivity.this, cell.getMessageObject())) {
                        ChatActivity.this.presentFragment(new ChatActivity(args), true);
                    }
                }
            }

            public void didPressedOther(ChatMessageCell cell) {
                if (cell.getMessageObject().type != 16) {
                    ChatActivity.this.createMenu(cell, true, false, false);
                } else if (ChatActivity.this.currentUser != null) {
                    VoIPHelper.startCall(ChatActivity.this.currentUser, ChatActivity.this.getParentActivity(), MessagesController.getInstance().getUserFull(ChatActivity.this.currentUser.id));
                }
            }

            public void didPressedUserAvatar(ChatMessageCell cell, User user) {
                boolean z = true;
                if (ChatActivity.this.actionBar.isActionModeShowed()) {
                    ChatActivity.this.processRowSelect(cell, true);
                } else if (user != null && user.id != UserConfig.getClientUserId()) {
                    Bundle args = new Bundle();
                    args.putInt("user_id", user.id);
                    ProfileActivity fragment = new ProfileActivity(args);
                    if (ChatActivity.this.currentUser == null || ChatActivity.this.currentUser.id != user.id) {
                        z = false;
                    }
                    fragment.setPlayProfileAnimation(z);
                    ChatActivity.this.presentFragment(fragment);
                }
            }

            public void didPressedBotButton(ChatMessageCell cell, TLRPC$KeyboardButton button) {
                if (ChatActivity.this.getParentActivity() == null) {
                    return;
                }
                if (ChatActivity.this.bottomOverlayChat.getVisibility() != 0 || (button instanceof TLRPC$TL_keyboardButtonSwitchInline) || (button instanceof TLRPC$TL_keyboardButtonCallback) || (button instanceof TLRPC$TL_keyboardButtonGame) || (button instanceof TLRPC$TL_keyboardButtonUrl) || (button instanceof TLRPC$TL_keyboardButtonBuy)) {
                    ChatActivity.this.chatActivityEnterView.didPressedBotButton(button, cell.getMessageObject(), cell.getMessageObject());
                }
            }

            public void didPressedCancelSendButton(ChatMessageCell cell) {
                MessageObject message = cell.getMessageObject();
                if (message.messageOwner.send_state != 0) {
                    SendMessagesHelper.getInstance().cancelSendingMessage(message);
                }
            }

            public void didLongPressed(ChatMessageCell cell) {
                ChatActivity.this.createMenu(cell, false, false);
            }

            public boolean canPerformActions() {
                return (ChatActivity.this.actionBar == null || ChatActivity.this.actionBar.isActionModeShowed()) ? false : true;
            }

            public void didPressedUrl(MessageObject messageObject, CharacterStyle url, boolean longPress) {
                if (url != null) {
                    if (url instanceof URLSpanMono) {
                        ((URLSpanMono) url).copyToClipboard();
                        Toast.makeText(ChatActivity.this.getParentActivity(), LocaleController.getString("TextCopied", R.string.TextCopied), 0).show();
                    } else if (url instanceof URLSpanUserMention) {
                        User user = MessagesController.getInstance().getUser(Utilities.parseInt(((URLSpanUserMention) url).getURL()));
                        if (user != null) {
                            MessagesController.openChatOrProfileWith(user, null, ChatActivity.this, 0, false);
                        }
                    } else if (url instanceof URLSpanNoUnderline) {
                        String str = ((URLSpanNoUnderline) url).getURL();
                        if (str.startsWith("@")) {
                            MessagesController.openByUserName(str.substring(1), ChatActivity.this, 0);
                        } else if (str.startsWith("#")) {
                            if (ChatObject.isChannel(ChatActivity.this.currentChat)) {
                                ChatActivity.this.openSearchWithText(str);
                                return;
                            }
                            DialogsActivity fragment = new DialogsActivity(null);
                            fragment.setSearchString(str);
                            ChatActivity.this.presentFragment(fragment);
                        } else if (str.startsWith(BridgeUtil.SPLIT_MARK) && URLSpanBotCommand.enabled) {
                            ChatActivityEnterView chatActivityEnterView = ChatActivity.this.chatActivityEnterView;
                            boolean z = ChatActivity.this.currentChat != null && ChatActivity.this.currentChat.megagroup;
                            chatActivityEnterView.setCommand(messageObject, str, longPress, z);
                            if (!longPress && ChatActivity.this.chatActivityEnterView.getFieldText() == null) {
                                ChatActivity.this.showReplyPanel(false, null, null, null, false);
                            }
                        }
                    } else {
                        final String urlFinal = ((URLSpan) url).getURL();
                        if (longPress) {
                            Builder builder = new Builder(ChatActivity.this.getParentActivity());
                            builder.setTitle(urlFinal);
                            builder.setItems(new CharSequence[]{LocaleController.getString("Open", R.string.Open), LocaleController.getString("Copy", R.string.Copy)}, new OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    boolean z = true;
                                    if (which == 0) {
                                        Context parentActivity = ChatActivity.this.getParentActivity();
                                        String str = urlFinal;
                                        if (ChatActivity.this.inlineReturn != 0) {
                                            z = false;
                                        }
                                        Browser.openUrl(parentActivity, str, z);
                                    } else if (which == 1) {
                                        String url = urlFinal;
                                        if (url.startsWith("mailto:")) {
                                            url = url.substring(7);
                                        } else if (url.startsWith("tel:")) {
                                            url = url.substring(4);
                                        }
                                        AndroidUtilities.addToClipboard(url);
                                    }
                                }
                            });
                            ChatActivity.this.showDialog(builder.create());
                        } else if (url instanceof URLSpanReplacement) {
                            ChatActivity.this.showOpenUrlAlert(((URLSpanReplacement) url).getURL(), true);
                        } else if (url instanceof URLSpan) {
                            if (!(!(messageObject.messageOwner.media instanceof TLRPC$TL_messageMediaWebPage) || messageObject.messageOwner.media.webpage == null || messageObject.messageOwner.media.webpage.cached_page == null)) {
                                String lowerUrl = urlFinal.toLowerCase();
                                String lowerUrl2 = messageObject.messageOwner.media.webpage.url.toLowerCase();
                                if ((lowerUrl.contains("telegra.ph") || lowerUrl.contains("t.me/iv")) && (lowerUrl.contains(lowerUrl2) || lowerUrl2.contains(lowerUrl))) {
                                    ArticleViewer.getInstance().setParentActivity(ChatActivity.this.getParentActivity(), ChatActivity.this);
                                    ArticleViewer.getInstance().open(messageObject);
                                    return;
                                }
                            }
                            Browser.openUrl(ChatActivity.this.getParentActivity(), urlFinal, ChatActivity.this.inlineReturn == 0);
                        } else if (url instanceof ClickableSpan) {
                            ((ClickableSpan) url).onClick(ChatActivity.this.fragmentView);
                        }
                    }
                }
            }

            public void needOpenWebView(String url, String title, String description, String originalUrl, int w, int h) {
                EmbedBottomSheet.show(ChatActivityAdapter.this.mContext, title, description, originalUrl, url, w, h);
            }

            public void didPressedReplyMessage(ChatMessageCell cell, int id) {
                int i;
                MessageObject messageObject = cell.getMessageObject();
                ChatActivity chatActivity = ChatActivity.this;
                int id2 = messageObject.getId();
                if (messageObject.getDialogId() == ChatActivity.this.mergeDialogId) {
                    i = 1;
                } else {
                    i = 0;
                }
                chatActivity.scrollToMessageId(id, id2, true, i, false);
            }

            public void didPressedViaBot(ChatMessageCell cell, String username) {
                if (ChatActivity.this.bottomOverlayChat != null && ChatActivity.this.bottomOverlayChat.getVisibility() == 0) {
                    return;
                }
                if ((ChatActivity.this.bottomOverlay == null || ChatActivity.this.bottomOverlay.getVisibility() != 0) && ChatActivity.this.chatActivityEnterView != null && username != null && username.length() > 0) {
                    ChatActivity.this.chatActivityEnterView.setFieldText("@" + username + " ");
                    ChatActivity.this.chatActivityEnterView.openKeyboard();
                }
            }

            public void didPressedImage(ChatMessageCell cell) {
                MessageObject message = cell.getMessageObject();
                if (message.isSendError()) {
                    ChatActivity.this.createMenu(cell, false, false);
                } else if (!message.isSending()) {
                    if (message.isSecretPhoto()) {
                        if (ChatActivity.this.sendSecretMessageRead(message)) {
                            cell.invalidate();
                        }
                        SecretMediaViewer.getInstance().setParentActivity(ChatActivity.this.getParentActivity());
                        SecretMediaViewer.getInstance().openMedia(message, ChatActivity.this.photoViewerProvider);
                    } else if (message.type == 13) {
                        ChatActivity chatActivity = ChatActivity.this;
                        Context parentActivity = ChatActivity.this.getParentActivity();
                        BaseFragment baseFragment = ChatActivity.this;
                        TLRPC$InputStickerSet inputStickerSet = message.getInputStickerSet();
                        StickersAlertDelegate stickersAlertDelegate = (ChatActivity.this.bottomOverlayChat.getVisibility() == 0 || !ChatObject.canSendStickers(ChatActivity.this.currentChat)) ? null : ChatActivity.this.chatActivityEnterView;
                        chatActivity.showDialog(new StickersAlert(parentActivity, baseFragment, inputStickerSet, null, stickersAlertDelegate));
                    } else if (message.isVideo() || message.type == 1 || ((message.type == 0 && !message.isWebpageDocument()) || message.isGif())) {
                        if (message.isVideo()) {
                            ChatActivity.this.sendSecretMessageRead(message);
                        }
                        PhotoViewer.getInstance().setParentActivity(ChatActivity.this.getParentActivity());
                        if (PhotoViewer.getInstance().openPhoto(message, message.type != 0 ? ChatActivity.this.dialog_id : 0, message.type != 0 ? ChatActivity.this.mergeDialogId : 0, ChatActivity.this.photoViewerProvider)) {
                            PhotoViewer.getInstance().setParentChatActivity(ChatActivity.this);
                        }
                    } else if (message.type == 3) {
                        ChatActivity.this.sendSecretMessageRead(message);
                        f = null;
                        try {
                            if (!(message.messageOwner.attachPath == null || message.messageOwner.attachPath.length() == 0)) {
                                f = new File(message.messageOwner.attachPath);
                            }
                            if (f == null || !f.exists()) {
                                f = FileLoader.getPathToMessage(message.messageOwner);
                            }
                            Intent intent = new Intent("android.intent.action.VIEW");
                            if (VERSION.SDK_INT >= 24) {
                                intent.setFlags(1);
                                intent.setDataAndType(FileProvider.getUriForFile(ChatActivity.this.getParentActivity(), "org.telegram.messenger.provider", f), "video/mp4");
                            } else {
                                intent.setDataAndType(Uri.fromFile(f), "video/mp4");
                            }
                            ChatActivity.this.getParentActivity().startActivityForResult(intent, HttpStatus.SC_INTERNAL_SERVER_ERROR);
                        } catch (Exception e) {
                            FileLog.e(e);
                            ChatActivity.this.alertUserOpenError(message);
                        }
                    } else if (message.type == 4) {
                        if (!AndroidUtilities.isGoogleMapsInstalled(ChatActivity.this)) {
                            return;
                        }
                        LocationActivity fragment;
                        if (message.isLiveLocation()) {
                            fragment = new LocationActivity(2);
                            fragment.setMessageObject(message);
                            fragment.setDelegate(ChatActivity.this);
                            ChatActivity.this.presentFragment(fragment);
                            return;
                        }
                        fragment = new LocationActivity(ChatActivity.this.currentEncryptedChat == null ? 3 : 0);
                        fragment.setMessageObject(message);
                        fragment.setDelegate(ChatActivity.this);
                        ChatActivity.this.presentFragment(fragment);
                    } else if (message.type == 9 || message.type == 0) {
                        if (message.getDocumentName().endsWith("attheme")) {
                            File locFile = null;
                            if (!(message.messageOwner.attachPath == null || message.messageOwner.attachPath.length() == 0)) {
                                f = new File(message.messageOwner.attachPath);
                                if (f.exists()) {
                                    locFile = f;
                                }
                            }
                            if (locFile == null) {
                                f = FileLoader.getPathToMessage(message.messageOwner);
                                if (f.exists()) {
                                    locFile = f;
                                }
                            }
                            if (ChatActivity.this.chatLayoutManager != null) {
                                int lastPosition = ChatActivity.this.chatLayoutManager.findFirstVisibleItemPosition();
                                if (lastPosition != 0) {
                                    ChatActivity.this.scrollToPositionOnRecreate = lastPosition;
                                    Holder holder = (Holder) ChatActivity.this.chatListView.findViewHolderForAdapterPosition(ChatActivity.this.scrollToPositionOnRecreate);
                                    if (holder != null) {
                                        ChatActivity.this.scrollToOffsetOnRecreate = (ChatActivity.this.chatListView.getMeasuredHeight() - holder.itemView.getBottom()) - ChatActivity.this.chatListView.getPaddingBottom();
                                    } else {
                                        ChatActivity.this.scrollToPositionOnRecreate = -1;
                                    }
                                } else {
                                    ChatActivity.this.scrollToPositionOnRecreate = -1;
                                }
                            }
                            ThemeInfo themeInfo = Theme.applyThemeFile(locFile, message.getDocumentName(), true);
                            if (themeInfo != null) {
                                ChatActivity.this.presentFragment(new ThemePreviewActivity(locFile, themeInfo));
                                return;
                            }
                            ChatActivity.this.scrollToPositionOnRecreate = -1;
                        }
                        try {
                            AndroidUtilities.openForView(message, ChatActivity.this.getParentActivity());
                        } catch (Exception e2) {
                            FileLog.e(e2);
                            ChatActivity.this.alertUserOpenError(message);
                        }
                    }
                }
            }

            public void didPressedInstantButton(ChatMessageCell cell, int type) {
                MessageObject messageObject = cell.getMessageObject();
                if (type == 0) {
                    if (messageObject.messageOwner.media != null && messageObject.messageOwner.media.webpage != null && messageObject.messageOwner.media.webpage.cached_page != null) {
                        ArticleViewer.getInstance().setParentActivity(ChatActivity.this.getParentActivity(), ChatActivity.this);
                        ArticleViewer.getInstance().open(messageObject);
                    }
                } else if (messageObject.messageOwner.media != null && messageObject.messageOwner.media.webpage != null) {
                    Browser.openUrl(ChatActivity.this.getParentActivity(), messageObject.messageOwner.media.webpage.url);
                }
            }

            public boolean isChatAdminCell(int uid) {
                if (ChatObject.isChannel(ChatActivity.this.currentChat) && ChatActivity.this.currentChat.megagroup) {
                    return MessagesController.getInstance().isChannelAdmin(ChatActivity.this.currentChat.id, uid);
                }
                return false;
            }
        }

        class C59532 implements ChatActionCellDelegate {
            C59532() {
            }

            public void didClickedImage(ChatActionCell cell) {
                MessageObject message = cell.getMessageObject();
                PhotoViewer.getInstance().setParentActivity(ChatActivity.this.getParentActivity());
                TLRPC$PhotoSize photoSize = FileLoader.getClosestPhotoSizeWithSize(message.photoThumbs, 640);
                if (photoSize != null) {
                    PhotoViewer.getInstance().openPhoto(photoSize.location, ChatActivity.this.photoViewerProvider);
                    return;
                }
                PhotoViewer.getInstance().openPhoto(message, 0, 0, ChatActivity.this.photoViewerProvider);
            }

            public void didLongPressed(ChatActionCell cell) {
                ChatActivity.this.createMenu(cell, false, false);
            }

            public void needOpenUserProfile(int uid) {
                boolean z = true;
                Bundle args;
                if (uid < 0) {
                    args = new Bundle();
                    args.putInt("chat_id", -uid);
                    if (MessagesController.checkCanOpenChat(args, ChatActivity.this)) {
                        ChatActivity.this.presentFragment(new ChatActivity(args), true);
                    }
                } else if (uid != UserConfig.getClientUserId()) {
                    args = new Bundle();
                    args.putInt("user_id", uid);
                    if (ChatActivity.this.currentEncryptedChat != null && uid == ChatActivity.this.currentUser.id) {
                        args.putLong("dialog_id", ChatActivity.this.dialog_id);
                    }
                    ProfileActivity fragment = new ProfileActivity(args);
                    if (ChatActivity.this.currentUser == null || ChatActivity.this.currentUser.id != uid) {
                        z = false;
                    }
                    fragment.setPlayProfileAnimation(z);
                    ChatActivity.this.presentFragment(fragment);
                }
            }

            public void didPressedReplyMessage(ChatActionCell cell, int id) {
                int i;
                MessageObject messageObject = cell.getMessageObject();
                ChatActivity chatActivity = ChatActivity.this;
                int id2 = messageObject.getId();
                if (messageObject.getDialogId() == ChatActivity.this.mergeDialogId) {
                    i = 1;
                } else {
                    i = 0;
                }
                chatActivity.scrollToMessageId(id, id2, true, i, false);
            }

            public void didPressedBotButton(MessageObject messageObject, TLRPC$KeyboardButton button) {
                if (ChatActivity.this.getParentActivity() == null) {
                    return;
                }
                if (ChatActivity.this.bottomOverlayChat.getVisibility() != 0 || (button instanceof TLRPC$TL_keyboardButtonSwitchInline) || (button instanceof TLRPC$TL_keyboardButtonCallback) || (button instanceof TLRPC$TL_keyboardButtonGame) || (button instanceof TLRPC$TL_keyboardButtonUrl) || (button instanceof TLRPC$TL_keyboardButtonBuy)) {
                    ChatActivity.this.chatActivityEnterView.didPressedBotButton(button, messageObject, messageObject);
                }
            }
        }

        class C59543 implements BotHelpCellDelegate {
            C59543() {
            }

            public void didPressUrl(String url) {
                if (url.startsWith("@")) {
                    MessagesController.openByUserName(url.substring(1), ChatActivity.this, 0);
                } else if (url.startsWith("#")) {
                    DialogsActivity fragment = new DialogsActivity(null);
                    fragment.setSearchString(url);
                    ChatActivity.this.presentFragment(fragment);
                } else if (url.startsWith(BridgeUtil.SPLIT_MARK)) {
                    ChatActivity.this.chatActivityEnterView.setCommand(null, url, false, false);
                    if (ChatActivity.this.chatActivityEnterView.getFieldText() == null) {
                        ChatActivity.this.showReplyPanel(false, null, null, null, false);
                    }
                }
            }
        }

        public ChatActivityAdapter(Context context) {
            this.mContext = context;
            boolean z = ChatActivity.this.currentUser != null && ChatActivity.this.currentUser.bot;
            this.isBot = z;
        }

        public void updateRows() {
            int i;
            this.rowCount = 0;
            if (ChatActivity.this.messages.isEmpty()) {
                this.loadingUpRow = -1;
                this.loadingDownRow = -1;
                this.messagesStartRow = -1;
                this.messagesEndRow = -1;
            } else {
                if (ChatActivity.this.forwardEndReached[0] && (ChatActivity.this.mergeDialogId == 0 || ChatActivity.this.forwardEndReached[1])) {
                    this.loadingDownRow = -1;
                } else {
                    i = this.rowCount;
                    this.rowCount = i + 1;
                    this.loadingDownRow = i;
                }
                this.messagesStartRow = this.rowCount;
                this.rowCount += ChatActivity.this.messages.size();
                this.messagesEndRow = this.rowCount;
                if (ChatActivity.this.endReached[0] && (ChatActivity.this.mergeDialogId == 0 || ChatActivity.this.endReached[1])) {
                    this.loadingUpRow = -1;
                } else {
                    i = this.rowCount;
                    this.rowCount = i + 1;
                    this.loadingUpRow = i;
                }
            }
            if (ChatActivity.this.currentUser == null || !ChatActivity.this.currentUser.bot) {
                this.botInfoRow = -1;
                return;
            }
            i = this.rowCount;
            this.rowCount = i + 1;
            this.botInfoRow = i;
        }

        public int getItemCount() {
            return this.rowCount;
        }

        public long getItemId(int i) {
            return -1;
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = null;
            if (viewType == 0) {
                if (ChatActivity.this.chatMessageCellsCache.isEmpty()) {
                    view = new ChatMessageCell(this.mContext);
                } else {
                    view = (View) ChatActivity.this.chatMessageCellsCache.get(0);
                    ChatActivity.this.chatMessageCellsCache.remove(0);
                }
                ChatMessageCell chatMessageCell = (ChatMessageCell) view;
                chatMessageCell.setDelegate(new C59521());
                if (ChatActivity.this.currentEncryptedChat == null) {
                    chatMessageCell.setAllowAssistant(true);
                }
            } else if (viewType == 1) {
                view = new ChatActionCell(this.mContext);
                ((ChatActionCell) view).setDelegate(new C59532());
            } else if (viewType == 2) {
                view = new ChatUnreadCell(this.mContext);
            } else if (viewType == 3) {
                view = new BotHelpCell(this.mContext);
                ((BotHelpCell) view).setDelegate(new C59543());
            } else if (viewType == 4) {
                view = new ChatLoadingCell(this.mContext);
            }
            view.setLayoutParams(new LayoutParams(-1, -2));
            return new Holder(view);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            if (position == this.botInfoRow) {
                String str;
                BotHelpCell helpView = holder.itemView;
                if (ChatActivity.this.botInfo.isEmpty()) {
                    str = null;
                } else {
                    str = ((TLRPC$BotInfo) ChatActivity.this.botInfo.get(Integer.valueOf(ChatActivity.this.currentUser.id))).description;
                }
                helpView.setText(str);
                return;
            }
            if (position == this.loadingDownRow || position == this.loadingUpRow) {
                holder.itemView.setProgressVisible(ChatActivity.this.loadsCount > 1);
                return;
            }
            if (position >= this.messagesStartRow && position < this.messagesEndRow) {
                MessageObject message = (MessageObject) ChatActivity.this.messages.get(position - this.messagesStartRow);
                View view = holder.itemView;
                if (view instanceof ChatMessageCell) {
                    int prevPosition;
                    int nextPosition;
                    int index;
                    final ChatMessageCell messageCell = (ChatMessageCell) view;
                    boolean z = ChatActivity.this.currentChat != null || UserObject.isUserSelf(ChatActivity.this.currentUser);
                    messageCell.isChat = z;
                    boolean pinnedBottom = false;
                    boolean pinnedTop = false;
                    GroupedMessages groupedMessages = ChatActivity.this.getValidGroupedMessage(message);
                    if (groupedMessages != null) {
                        GroupedMessagePosition pos = (GroupedMessagePosition) groupedMessages.positions.get(message);
                        if (pos != null) {
                            if ((pos.flags & 4) != 0) {
                                prevPosition = (groupedMessages.posArray.indexOf(pos) + position) + 1;
                            } else {
                                pinnedTop = true;
                                prevPosition = -100;
                            }
                            if ((pos.flags & 8) != 0) {
                                nextPosition = (position - groupedMessages.posArray.size()) + groupedMessages.posArray.indexOf(pos);
                            } else {
                                pinnedBottom = true;
                                nextPosition = -100;
                            }
                        } else {
                            prevPosition = -100;
                            nextPosition = -100;
                        }
                    } else {
                        nextPosition = position - 1;
                        prevPosition = position + 1;
                    }
                    int nextType = getItemViewType(nextPosition);
                    int prevType = getItemViewType(prevPosition);
                    if (!(message.messageOwner.reply_markup instanceof TLRPC$TL_replyInlineMarkup) && nextType == holder.getItemViewType()) {
                        MessageObject nextMessage = (MessageObject) ChatActivity.this.messages.get(nextPosition - this.messagesStartRow);
                        pinnedBottom = nextMessage.isOutOwner() == message.isOutOwner() && Math.abs(nextMessage.messageOwner.date - message.messageOwner.date) <= 300;
                        if (pinnedBottom) {
                            if (ChatActivity.this.currentChat != null) {
                                pinnedBottom = nextMessage.messageOwner.from_id == message.messageOwner.from_id;
                            } else if (UserObject.isUserSelf(ChatActivity.this.currentUser)) {
                                pinnedBottom = nextMessage.getFromId() == message.getFromId();
                            }
                        }
                    }
                    if (prevType == holder.getItemViewType()) {
                        MessageObject prevMessage = (MessageObject) ChatActivity.this.messages.get(prevPosition - this.messagesStartRow);
                        pinnedTop = !(prevMessage.messageOwner.reply_markup instanceof TLRPC$TL_replyInlineMarkup) && prevMessage.isOutOwner() == message.isOutOwner() && Math.abs(prevMessage.messageOwner.date - message.messageOwner.date) <= 300;
                        if (pinnedTop) {
                            if (ChatActivity.this.currentChat != null) {
                                pinnedTop = prevMessage.messageOwner.from_id == message.messageOwner.from_id;
                            } else if (UserObject.isUserSelf(ChatActivity.this.currentUser)) {
                                pinnedTop = prevMessage.getFromId() == message.getFromId();
                            }
                        }
                    }
                    messageCell.setMessageObject(message, groupedMessages, pinnedBottom, pinnedTop);
                    if ((view instanceof ChatMessageCell) && MediaController.getInstance().canDownloadMedia(message)) {
                        ((ChatMessageCell) view).downloadAudioIfNeed();
                    }
                    z = ChatActivity.this.highlightMessageId != Integer.MAX_VALUE && message.getId() == ChatActivity.this.highlightMessageId;
                    messageCell.setHighlighted(z);
                    if (ChatActivity.this.searchContainer != null && ChatActivity.this.searchContainer.getVisibility() == 0) {
                        if (MessagesSearchQuery.isMessageFound(message.getId(), message.getDialogId() == ChatActivity.this.mergeDialogId) && MessagesSearchQuery.getLastSearchQuery() != null) {
                            messageCell.setHighlightedText(MessagesSearchQuery.getLastSearchQuery());
                            index = ChatActivity.this.animatingMessageObjects.indexOf(message);
                            if (index != -1) {
                                ChatActivity.this.animatingMessageObjects.remove(index);
                                messageCell.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
                                    public boolean onPreDraw() {
                                        PipRoundVideoView pipRoundVideoView = PipRoundVideoView.getInstance();
                                        if (pipRoundVideoView != null) {
                                            pipRoundVideoView.showTemporary(true);
                                        }
                                        messageCell.getViewTreeObserver().removeOnPreDrawListener(this);
                                        ImageReceiver imageReceiver = messageCell.getPhotoImage();
                                        float scale = ((float) imageReceiver.getImageWidth()) / ChatActivity.this.instantCameraView.getCameraRect().width;
                                        int[] position = new int[2];
                                        messageCell.setAlpha(0.0f);
                                        messageCell.getLocationOnScreen(position);
                                        position[0] = position[0] + imageReceiver.getImageX();
                                        position[1] = position[1] + imageReceiver.getImageY();
                                        final View cameraContainer = ChatActivity.this.instantCameraView.getCameraContainer();
                                        cameraContainer.setPivotX(0.0f);
                                        cameraContainer.setPivotY(0.0f);
                                        AnimatorSet animatorSet = new AnimatorSet();
                                        r8 = new Animator[8];
                                        r8[0] = ObjectAnimator.ofFloat(ChatActivity.this.instantCameraView, "alpha", new float[]{0.0f});
                                        r8[1] = ObjectAnimator.ofFloat(cameraContainer, "scaleX", new float[]{scale});
                                        r8[2] = ObjectAnimator.ofFloat(cameraContainer, "scaleY", new float[]{scale});
                                        r8[3] = ObjectAnimator.ofFloat(cameraContainer, "translationX", new float[]{((float) position[0]) - rect.f12666x});
                                        r8[4] = ObjectAnimator.ofFloat(cameraContainer, "translationY", new float[]{((float) position[1]) - rect.f12667y});
                                        r8[5] = ObjectAnimator.ofFloat(ChatActivity.this.instantCameraView.getSwitchButtonView(), "alpha", new float[]{0.0f});
                                        r8[6] = ObjectAnimator.ofInt(ChatActivity.this.instantCameraView.getPaint(), "alpha", new int[]{0});
                                        r8[7] = ObjectAnimator.ofFloat(ChatActivity.this.instantCameraView.getMuteImageView(), "alpha", new float[]{0.0f});
                                        animatorSet.playTogether(r8);
                                        animatorSet.setDuration(180);
                                        animatorSet.setInterpolator(new DecelerateInterpolator());
                                        animatorSet.addListener(new AnimatorListenerAdapter() {

                                            class C59551 extends AnimatorListenerAdapter {
                                                C59551() {
                                                }

                                                public void onAnimationEnd(Animator animation) {
                                                    ChatActivity.this.instantCameraView.hideCamera(true);
                                                    ChatActivity.this.instantCameraView.setVisibility(4);
                                                }
                                            }

                                            public void onAnimationEnd(Animator animation) {
                                                AnimatorSet animatorSet = new AnimatorSet();
                                                r1 = new Animator[2];
                                                r1[0] = ObjectAnimator.ofFloat(cameraContainer, "alpha", new float[]{0.0f});
                                                r1[1] = ObjectAnimator.ofFloat(messageCell, "alpha", new float[]{1.0f});
                                                animatorSet.playTogether(r1);
                                                animatorSet.setDuration(100);
                                                animatorSet.setInterpolator(new DecelerateInterpolator());
                                                animatorSet.addListener(new C59551());
                                                animatorSet.start();
                                            }
                                        });
                                        animatorSet.start();
                                        return true;
                                    }
                                });
                            }
                        }
                    }
                    messageCell.setHighlightedText(null);
                    index = ChatActivity.this.animatingMessageObjects.indexOf(message);
                    if (index != -1) {
                        ChatActivity.this.animatingMessageObjects.remove(index);
                        messageCell.getViewTreeObserver().addOnPreDrawListener(/* anonymous class already generated */);
                    }
                } else if (view instanceof ChatActionCell) {
                    ChatActionCell actionCell = (ChatActionCell) view;
                    actionCell.setMessageObject(message);
                    actionCell.setAlpha(1.0f);
                } else if (view instanceof ChatUnreadCell) {
                    ((ChatUnreadCell) view).setText(LocaleController.formatPluralString("NewMessages", ChatActivity.this.unread_to_load));
                    if (ChatActivity.this.createUnreadMessageAfterId != 0) {
                        ChatActivity.this.createUnreadMessageAfterId = 0;
                    }
                }
                if (message != null && message.messageOwner != null && message.messageOwner.media_unread && message.messageOwner.mentioned) {
                    if (!(message.isVoice() || message.isRoundVideo())) {
                        ChatActivity.this.newMentionsCount = ChatActivity.this.newMentionsCount - 1;
                        if (ChatActivity.this.newMentionsCount <= 0) {
                            ChatActivity.this.newMentionsCount = 0;
                            ChatActivity.this.hasAllMentionsLocal = true;
                            ChatActivity.this.showMentiondownButton(false, true);
                        } else {
                            ChatActivity.this.mentiondownButtonCounter.setText(String.format("%d", new Object[]{Integer.valueOf(ChatActivity.this.newMentionsCount)}));
                        }
                        MessagesController.getInstance().markMentionMessageAsRead(message.getId(), ChatObject.isChannel(ChatActivity.this.currentChat) ? ChatActivity.this.currentChat.id : 0, ChatActivity.this.dialog_id);
                        message.setContentIsRead();
                    }
                    if (view instanceof ChatMessageCell) {
                        ((ChatMessageCell) view).setHighlightedAnimated();
                    }
                }
            }
        }

        public int getItemViewType(int position) {
            if (position >= this.messagesStartRow && position < this.messagesEndRow) {
                return ((MessageObject) ChatActivity.this.messages.get(position - this.messagesStartRow)).contentType;
            }
            if (position == this.botInfoRow) {
                return 3;
            }
            return 4;
        }

        public void onViewAttachedToWindow(ViewHolder holder) {
            boolean z = true;
            if (holder.itemView instanceof ChatMessageCell) {
                boolean z2;
                boolean z3;
                final ChatMessageCell messageCell = holder.itemView;
                MessageObject message = messageCell.getMessageObject();
                boolean selected = false;
                boolean disableSelection = false;
                if (ChatActivity.this.actionBar.isActionModeShowed()) {
                    MessageObject messageObject;
                    int idx;
                    if (ChatActivity.this.chatActivityEnterView != null) {
                        messageObject = ChatActivity.this.chatActivityEnterView.getEditingMessageObject();
                    } else {
                        messageObject = null;
                    }
                    if (message.getDialogId() == ChatActivity.this.dialog_id) {
                        idx = 0;
                    } else {
                        idx = 1;
                    }
                    if (messageObject == message || ChatActivity.this.selectedMessagesIds[idx].containsKey(Integer.valueOf(message.getId()))) {
                        ChatActivity.this.setCellSelectionBackground(message, messageCell, idx);
                        selected = true;
                    } else {
                        messageCell.setBackgroundDrawable(null);
                    }
                    disableSelection = true;
                } else {
                    messageCell.setBackgroundDrawable(null);
                }
                if (disableSelection) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                if (disableSelection && selected) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                messageCell.setCheckPressed(z2, z3);
                messageCell.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
                    public boolean onPreDraw() {
                        messageCell.getViewTreeObserver().removeOnPreDrawListener(this);
                        int height = ChatActivity.this.chatListView.getMeasuredHeight();
                        int top = messageCell.getTop();
                        int bottom = messageCell.getBottom();
                        int viewTop = top >= 0 ? 0 : -top;
                        int viewBottom = messageCell.getMeasuredHeight();
                        if (viewBottom > height) {
                            viewBottom = viewTop + height;
                        }
                        messageCell.setVisiblePart(viewTop, viewBottom - viewTop);
                        return true;
                    }
                });
                if (ChatActivity.this.highlightMessageId == Integer.MAX_VALUE || messageCell.getMessageObject().getId() != ChatActivity.this.highlightMessageId) {
                    z = false;
                }
                messageCell.setHighlighted(z);
            }
        }

        public void updateRowWithMessageObject(MessageObject messageObject) {
            int index = ChatActivity.this.messages.indexOf(messageObject);
            if (index != -1) {
                notifyItemChanged(this.messagesStartRow + index);
            }
        }

        public void notifyDataSetChanged() {
            updateRows();
            try {
                super.notifyDataSetChanged();
            } catch (Exception e) {
                FileLog.e(e);
            }
        }

        public void notifyItemChanged(int position) {
            try {
                super.notifyItemChanged(position);
            } catch (Exception e) {
                FileLog.e(e);
            }
        }

        public void notifyItemRangeChanged(int positionStart, int itemCount) {
            try {
                super.notifyItemRangeChanged(positionStart, itemCount);
            } catch (Exception e) {
                FileLog.e(e);
            }
        }

        public void notifyItemInserted(int position) {
            updateRows();
            try {
                super.notifyItemInserted(position);
            } catch (Exception e) {
                FileLog.e(e);
            }
        }

        public void notifyItemMoved(int fromPosition, int toPosition) {
            updateRows();
            try {
                super.notifyItemMoved(fromPosition, toPosition);
            } catch (Exception e) {
                FileLog.e(e);
            }
        }

        public void notifyItemRangeInserted(int positionStart, int itemCount) {
            updateRows();
            try {
                super.notifyItemRangeInserted(positionStart, itemCount);
            } catch (Exception e) {
                FileLog.e(e);
            }
        }

        public void notifyItemRemoved(int position) {
            updateRows();
            try {
                super.notifyItemRemoved(position);
            } catch (Exception e) {
                FileLog.e(e);
            }
        }

        public void notifyItemRangeRemoved(int positionStart, int itemCount) {
            updateRows();
            try {
                super.notifyItemRangeRemoved(positionStart, itemCount);
            } catch (Exception e) {
                FileLog.e(e);
            }
        }
    }

    public ChatActivity(Bundle args) {
        super(args);
    }

    public boolean onFragmentCreate() {
        Semaphore semaphore;
        int chatId = this.arguments.getInt("chat_id", 0);
        int userId = this.arguments.getInt("user_id", 0);
        int encId = this.arguments.getInt("enc_id", 0);
        this.inlineReturn = this.arguments.getLong("inline_return", 0);
        String inlineQuery = this.arguments.getString("inline_query");
        this.startLoadFromMessageId = this.arguments.getInt("message_id", 0);
        int migrated_to = this.arguments.getInt("migrated_to", 0);
        this.scrollToTopOnResume = this.arguments.getBoolean("scrollToTopOnResume", false);
        final int i;
        final Semaphore semaphore2;
        if (chatId != 0) {
            this.currentChat = MessagesController.getInstance().getChat(Integer.valueOf(chatId));
            if (this.currentChat == null) {
                semaphore = new Semaphore(0);
                i = chatId;
                semaphore2 = semaphore;
                MessagesStorage.getInstance().getStorageQueue().postRunnable(new Runnable() {
                    public void run() {
                        ChatActivity.this.currentChat = MessagesStorage.getInstance().getChat(i);
                        semaphore2.release();
                    }
                });
                try {
                    semaphore.acquire();
                } catch (Exception e) {
                    FileLog.e(e);
                }
                if (this.currentChat == null) {
                    return false;
                }
                MessagesController.getInstance().putChat(this.currentChat, true);
            }
            if (chatId > 0) {
                this.dialog_id = (long) (-chatId);
            } else {
                this.isBroadcast = true;
                this.dialog_id = AndroidUtilities.makeBroadcastId(chatId);
            }
            if (ChatObject.isChannel(this.currentChat)) {
                MessagesController.getInstance().startShortPoll(chatId, false);
            }
        } else if (userId != 0) {
            this.currentUser = MessagesController.getInstance().getUser(Integer.valueOf(userId));
            if (this.currentUser == null) {
                semaphore = new Semaphore(0);
                i = userId;
                semaphore2 = semaphore;
                MessagesStorage.getInstance().getStorageQueue().postRunnable(new Runnable() {
                    public void run() {
                        ChatActivity.this.currentUser = MessagesStorage.getInstance().getUser(i);
                        semaphore2.release();
                    }
                });
                try {
                    semaphore.acquire();
                } catch (Exception e2) {
                    FileLog.e(e2);
                }
                if (this.currentUser == null) {
                    return false;
                }
                MessagesController.getInstance().putUser(this.currentUser, true);
            }
            this.dialog_id = (long) userId;
            this.botUser = this.arguments.getString("botUser");
            if (inlineQuery != null) {
                MessagesController.getInstance().sendBotStart(this.currentUser, inlineQuery);
            }
        } else if (encId == 0) {
            return false;
        } else {
            this.currentEncryptedChat = MessagesController.getInstance().getEncryptedChat(Integer.valueOf(encId));
            if (this.currentEncryptedChat == null) {
                semaphore = new Semaphore(0);
                i = encId;
                semaphore2 = semaphore;
                MessagesStorage.getInstance().getStorageQueue().postRunnable(new Runnable() {
                    public void run() {
                        ChatActivity.this.currentEncryptedChat = MessagesStorage.getInstance().getEncryptedChat(i);
                        semaphore2.release();
                    }
                });
                try {
                    semaphore.acquire();
                } catch (Exception e22) {
                    FileLog.e(e22);
                }
                if (this.currentEncryptedChat == null) {
                    return false;
                }
                MessagesController.getInstance().putEncryptedChat(this.currentEncryptedChat, true);
            }
            this.currentUser = MessagesController.getInstance().getUser(Integer.valueOf(this.currentEncryptedChat.user_id));
            if (this.currentUser == null) {
                semaphore = new Semaphore(0);
                final Semaphore semaphore3 = semaphore;
                MessagesStorage.getInstance().getStorageQueue().postRunnable(new Runnable() {
                    public void run() {
                        ChatActivity.this.currentUser = MessagesStorage.getInstance().getUser(ChatActivity.this.currentEncryptedChat.user_id);
                        semaphore3.release();
                    }
                });
                try {
                    semaphore.acquire();
                } catch (Exception e222) {
                    FileLog.e(e222);
                }
                if (this.currentUser == null) {
                    return false;
                }
                MessagesController.getInstance().putUser(this.currentUser, true);
            }
            this.dialog_id = ((long) encId) << 32;
            int[] iArr = this.maxMessageId;
            this.maxMessageId[1] = Integer.MIN_VALUE;
            iArr[0] = Integer.MIN_VALUE;
            iArr = this.minMessageId;
            this.minMessageId[1] = Integer.MAX_VALUE;
            iArr[0] = Integer.MAX_VALUE;
        }
        if (this.currentUser != null) {
            MediaController.getInstance().startMediaObserver();
        }
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.messagesDidLoaded);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.emojiDidLoaded);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.updateInterfaces);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.didReceivedNewMessages);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.closeChats);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.messagesRead);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.messagesDeleted);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.historyCleared);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.messageReceivedByServer);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.messageReceivedByAck);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.messageSendError);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.chatInfoDidLoaded);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.contactsDidLoaded);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.encryptedChatUpdated);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.messagesReadEncrypted);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.removeAllMessagesFromDialog);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.messagePlayingProgressDidChanged);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.messagePlayingDidReset);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.messagePlayingPlayStateChanged);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.screenshotTook);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.blockedUsersDidLoaded);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.FileNewChunkAvailable);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.didCreatedNewDeleteTask);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.messagePlayingDidStarted);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.updateMessageMedia);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.replaceMessagesObjects);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.notificationsSettingsUpdated);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.didLoadedReplyMessages);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.didReceivedWebpages);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.didReceivedWebpagesInUpdates);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.messagesReadContent);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.botInfoDidLoaded);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.botKeyboardDidLoaded);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.chatSearchResultsAvailable);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.chatSearchResultsLoading);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.didUpdatedMessagesViews);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.chatInfoCantLoad);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.didLoadedPinnedMessage);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.peerSettingsDidLoaded);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.newDraftReceived);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.userInfoDidLoaded);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.didSetNewWallpapper);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.channelRightsUpdated);
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.updateMentionsCount);
        super.onFragmentCreate();
        if (this.currentEncryptedChat == null && !this.isBroadcast) {
            BotQuery.loadBotKeyboard(this.dialog_id);
        }
        this.loading = true;
        MessagesController.getInstance().loadPeerSettings(this.currentUser, this.currentChat);
        MessagesController.getInstance().setLastCreatedDialogId(this.dialog_id, true);
        if (this.startLoadFromMessageId == 0) {
            SharedPreferences sharedPreferences = ApplicationLoader.applicationContext.getSharedPreferences("Notifications", 0);
            int messageId = sharedPreferences.getInt("diditem" + this.dialog_id, 0);
            if (messageId != 0) {
                this.loadingFromOldPosition = true;
                this.startLoadFromMessageOffset = sharedPreferences.getInt("diditemo" + this.dialog_id, 0);
                this.startLoadFromMessageId = messageId;
            }
        } else {
            this.needSelectFromMessageId = true;
        }
        MessagesController instance;
        long j;
        int i2;
        int i3;
        boolean isChannel;
        int i4;
        if (this.startLoadFromMessageId != 0) {
            this.waitingForLoad.add(Integer.valueOf(this.lastLoadIndex));
            int i5;
            if (migrated_to != 0) {
                this.mergeDialogId = (long) migrated_to;
                instance = MessagesController.getInstance();
                j = this.mergeDialogId;
                i2 = this.loadingFromOldPosition ? 50 : AndroidUtilities.isTablet() ? 30 : 20;
                i5 = this.startLoadFromMessageId;
                i3 = this.classGuid;
                isChannel = ChatObject.isChannel(this.currentChat);
                i4 = this.lastLoadIndex;
                this.lastLoadIndex = i4 + 1;
                instance.loadMessages(j, i2, i5, 0, true, 0, i3, 3, 0, isChannel, i4);
            } else {
                instance = MessagesController.getInstance();
                j = this.dialog_id;
                i2 = this.loadingFromOldPosition ? 50 : AndroidUtilities.isTablet() ? 30 : 20;
                i5 = this.startLoadFromMessageId;
                i3 = this.classGuid;
                isChannel = ChatObject.isChannel(this.currentChat);
                i4 = this.lastLoadIndex;
                this.lastLoadIndex = i4 + 1;
                instance.loadMessages(j, i2, i5, 0, true, 0, i3, 3, 0, isChannel, i4);
            }
        } else {
            this.waitingForLoad.add(Integer.valueOf(this.lastLoadIndex));
            instance = MessagesController.getInstance();
            j = this.dialog_id;
            i2 = AndroidUtilities.isTablet() ? 30 : 20;
            i3 = this.classGuid;
            isChannel = ChatObject.isChannel(this.currentChat);
            i4 = this.lastLoadIndex;
            this.lastLoadIndex = i4 + 1;
            instance.loadMessages(j, i2, 0, 0, true, 0, i3, 2, 0, isChannel, i4);
        }
        if (this.currentChat != null) {
            Semaphore semaphore4 = null;
            if (this.isBroadcast) {
                semaphore = new Semaphore(0);
            }
            MessagesController.getInstance().loadChatInfo(this.currentChat.id, semaphore4, ChatObject.isChannel(this.currentChat));
            if (this.isBroadcast && semaphore4 != null) {
                try {
                    semaphore4.acquire();
                } catch (Exception e2222) {
                    FileLog.e(e2222);
                }
            }
        }
        if (userId != 0 && this.currentUser.bot) {
            BotQuery.loadBotInfo(userId, true, this.classGuid);
        } else if (this.info instanceof TLRPC$TL_chatFull) {
            for (int a = 0; a < this.info.participants.participants.size(); a++) {
                User user = MessagesController.getInstance().getUser(Integer.valueOf(((TLRPC$ChatParticipant) this.info.participants.participants.get(a)).user_id));
                if (user != null && user.bot) {
                    BotQuery.loadBotInfo(user.id, true, this.classGuid);
                }
            }
        }
        if (this.currentUser != null) {
            this.userBlocked = MessagesController.getInstance().blockedUsers.contains(Integer.valueOf(this.currentUser.id));
        }
        if (AndroidUtilities.isTablet()) {
            NotificationCenter.getInstance().postNotificationName(NotificationCenter.openedChatChanged, Long.valueOf(this.dialog_id), Boolean.valueOf(false));
        }
        if (!(this.currentEncryptedChat == null || AndroidUtilities.getMyLayerVersion(this.currentEncryptedChat.layer) == 73)) {
            SecretChatHelper.getInstance().sendNotifyLayerMessage(this.currentEncryptedChat, null);
        }
        return true;
    }

    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        if (this.chatActivityEnterView != null) {
            this.chatActivityEnterView.onDestroy();
        }
        if (this.mentionsAdapter != null) {
            this.mentionsAdapter.onDestroy();
        }
        if (this.chatAttachAlert != null) {
            this.chatAttachAlert.dismissInternal();
        }
        MessagesController.getInstance().setLastCreatedDialogId(this.dialog_id, false);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.messagesDidLoaded);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.emojiDidLoaded);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.updateInterfaces);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.didReceivedNewMessages);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.closeChats);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.messagesRead);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.messagesDeleted);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.historyCleared);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.messageReceivedByServer);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.messageReceivedByAck);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.messageSendError);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.chatInfoDidLoaded);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.encryptedChatUpdated);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.messagesReadEncrypted);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.removeAllMessagesFromDialog);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.contactsDidLoaded);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.messagePlayingProgressDidChanged);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.messagePlayingDidReset);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.screenshotTook);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.blockedUsersDidLoaded);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.FileNewChunkAvailable);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.didCreatedNewDeleteTask);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.messagePlayingDidStarted);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.updateMessageMedia);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.replaceMessagesObjects);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.notificationsSettingsUpdated);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.didLoadedReplyMessages);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.didReceivedWebpages);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.didReceivedWebpagesInUpdates);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.messagesReadContent);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.botInfoDidLoaded);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.botKeyboardDidLoaded);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.chatSearchResultsAvailable);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.chatSearchResultsLoading);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.messagePlayingPlayStateChanged);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.didUpdatedMessagesViews);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.chatInfoCantLoad);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.didLoadedPinnedMessage);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.peerSettingsDidLoaded);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.newDraftReceived);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.userInfoDidLoaded);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.didSetNewWallpapper);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.channelRightsUpdated);
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.updateMentionsCount);
        if (AndroidUtilities.isTablet()) {
            NotificationCenter.getInstance().postNotificationName(NotificationCenter.openedChatChanged, Long.valueOf(this.dialog_id), Boolean.valueOf(true));
        }
        if (this.currentUser != null) {
            MediaController.getInstance().stopMediaObserver();
        }
        if (this.currentEncryptedChat != null) {
            try {
                if (VERSION.SDK_INT >= 23 && (UserConfig.passcodeHash.length() == 0 || UserConfig.allowScreenCapture)) {
                    getParentActivity().getWindow().clearFlags(8192);
                }
            } catch (Throwable e) {
                FileLog.e(e);
            }
        }
        if (this.currentUser != null) {
            MessagesController.getInstance().cancelLoadFullUser(this.currentUser.id);
        }
        AndroidUtilities.removeAdjustResize(getParentActivity(), this.classGuid);
        if (this.stickersAdapter != null) {
            this.stickersAdapter.onDestroy();
        }
        if (this.chatAttachAlert != null) {
            this.chatAttachAlert.onDestroy();
        }
        AndroidUtilities.unlockOrientation(getParentActivity());
        if (ChatObject.isChannel(this.currentChat)) {
            MessagesController.getInstance().startShortPoll(this.currentChat.id, true);
        }
    }

    public View createView(Context context) {
        int a;
        CharSequence oldMessage;
        boolean z;
        View fragmentContextView;
        boolean z2;
        MessageObject messageObject;
        if (this.chatMessageCellsCache.isEmpty()) {
            for (a = 0; a < 8; a++) {
                this.chatMessageCellsCache.add(new ChatMessageCell(context));
            }
        }
        for (a = 1; a >= 0; a--) {
            this.selectedMessagesIds[a].clear();
            this.selectedMessagesCanCopyIds[a].clear();
            this.selectedMessagesCanStarIds[a].clear();
        }
        this.cantDeleteMessagesCount = 0;
        this.canEditMessagesCount = 0;
        this.hasOwnBackground = true;
        if (this.chatAttachAlert != null) {
            try {
                if (this.chatAttachAlert.isShowing()) {
                    this.chatAttachAlert.dismiss();
                }
            } catch (Exception e) {
            }
            this.chatAttachAlert.onDestroy();
            this.chatAttachAlert = null;
        }
        Theme.createChatResources(context, false);
        this.actionBar.setAddToContainer(false);
        this.actionBar.setBackButtonDrawable(new BackDrawable(false));
        this.actionBar.setActionBarMenuOnItemClick(new ActionBarMenuOnItemClick() {
            public void onItemClick(int id) {
                int a;
                if (id == -1) {
                    if (ChatActivity.this.actionBar.isActionModeShowed()) {
                        for (a = 1; a >= 0; a--) {
                            ChatActivity.this.selectedMessagesIds[a].clear();
                            ChatActivity.this.selectedMessagesCanCopyIds[a].clear();
                            ChatActivity.this.selectedMessagesCanStarIds[a].clear();
                        }
                        ChatActivity.this.cantDeleteMessagesCount = 0;
                        ChatActivity.this.canEditMessagesCount = 0;
                        if (ChatActivity.this.chatActivityEnterView.isEditingMessage()) {
                            ChatActivity.this.chatActivityEnterView.setEditingMessageObject(null, false);
                        } else {
                            ChatActivity.this.actionBar.hideActionMode();
                            ChatActivity.this.updatePinnedMessageView(true);
                        }
                        ChatActivity.this.updateVisibleRows();
                        return;
                    }
                    ChatActivity.this.finishFragment();
                } else if (id == 10) {
                    String str = "";
                    int previousUid = 0;
                    for (a = 1; a >= 0; a--) {
                        ArrayList<Integer> arrayList = new ArrayList(ChatActivity.this.selectedMessagesCanCopyIds[a].keySet());
                        if (ChatActivity.this.currentEncryptedChat == null) {
                            Collections.sort(arrayList);
                        } else {
                            Collections.sort(arrayList, Collections.reverseOrder());
                        }
                        for (int b = 0; b < arrayList.size(); b++) {
                            messageObject = (MessageObject) ChatActivity.this.selectedMessagesCanCopyIds[a].get((Integer) arrayList.get(b));
                            if (str.length() != 0) {
                                str = str + "\n\n";
                            }
                            str = str + ChatActivity.this.getMessageContent(messageObject, previousUid, true);
                            previousUid = messageObject.messageOwner.from_id;
                        }
                    }
                    if (str.length() != 0) {
                        AndroidUtilities.addToClipboard(str);
                    }
                    for (a = 1; a >= 0; a--) {
                        ChatActivity.this.selectedMessagesIds[a].clear();
                        ChatActivity.this.selectedMessagesCanCopyIds[a].clear();
                        ChatActivity.this.selectedMessagesCanStarIds[a].clear();
                    }
                    ChatActivity.this.cantDeleteMessagesCount = 0;
                    ChatActivity.this.canEditMessagesCount = 0;
                    ChatActivity.this.actionBar.hideActionMode();
                    ChatActivity.this.updatePinnedMessageView(true);
                    ChatActivity.this.updateVisibleRows();
                } else if (id == 12) {
                    if (ChatActivity.this.getParentActivity() != null) {
                        ChatActivity.this.createDeleteMessagesAlert(null, null);
                    }
                } else if (id == 11) {
                    args = new Bundle();
                    args.putBoolean("onlySelect", true);
                    args.putInt("dialogsType", 3);
                    BaseFragment dialogsActivity = new DialogsActivity(args);
                    dialogsActivity.setDelegate(ChatActivity.this);
                    ChatActivity.this.presentFragment(dialogsActivity);
                } else if (id == 13) {
                    if (ChatActivity.this.getParentActivity() != null) {
                        ChatActivity.this.showDialog(AlertsCreator.createTTLAlert(ChatActivity.this.getParentActivity(), ChatActivity.this.currentEncryptedChat).create());
                    }
                } else if (id == 15 || id == 16) {
                    if (ChatActivity.this.getParentActivity() != null) {
                        boolean isChat = ((int) ChatActivity.this.dialog_id) < 0 && ((int) (ChatActivity.this.dialog_id >> 32)) != 1;
                        AlertDialog.Builder builder = new AlertDialog.Builder(ChatActivity.this.getParentActivity());
                        builder.setTitle(ApplicationLoader.getConfig().getAppName());
                        if (id == 15) {
                            builder.setMessage(LocaleController.getString("AreYouSureClearHistory", R.string.AreYouSureClearHistory));
                        } else if (isChat) {
                            builder.setMessage(LocaleController.getString("AreYouSureDeleteAndExit", R.string.AreYouSureDeleteAndExit));
                        } else {
                            builder.setMessage(LocaleController.getString("AreYouSureDeleteThisChat", R.string.AreYouSureDeleteThisChat));
                        }
                        final int i = id;
                        final boolean z = isChat;
                        builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (i != 15) {
                                    if (!z) {
                                        MessagesController.getInstance().deleteDialog(ChatActivity.this.dialog_id, 0);
                                    } else if (ChatObject.isNotInChat(ChatActivity.this.currentChat)) {
                                        MessagesController.getInstance().deleteDialog(ChatActivity.this.dialog_id, 0);
                                    } else {
                                        MessagesController.getInstance().deleteUserFromChat((int) (-ChatActivity.this.dialog_id), MessagesController.getInstance().getUser(Integer.valueOf(UserConfig.getClientUserId())), null);
                                    }
                                    ChatActivity.this.finishFragment();
                                    return;
                                }
                                if (!(!ChatObject.isChannel(ChatActivity.this.currentChat) || ChatActivity.this.info == null || ChatActivity.this.info.pinned_msg_id == 0)) {
                                    ApplicationLoader.applicationContext.getSharedPreferences("Notifications", 0).edit().putInt("pin_" + ChatActivity.this.dialog_id, ChatActivity.this.info.pinned_msg_id).commit();
                                    ChatActivity.this.updatePinnedMessageView(true);
                                }
                                MessagesController.getInstance().deleteDialog(ChatActivity.this.dialog_id, 1);
                            }
                        });
                        builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
                        ChatActivity.this.showDialog(builder.create());
                    }
                } else if (id == 17) {
                    if (ChatActivity.this.currentUser != null && ChatActivity.this.getParentActivity() != null) {
                        if (ChatActivity.this.currentUser.phone == null || ChatActivity.this.currentUser.phone.length() == 0) {
                            ChatActivity.this.shareMyContact(ChatActivity.this.replyingMessageObject);
                            return;
                        }
                        args = new Bundle();
                        args.putInt("user_id", ChatActivity.this.currentUser.id);
                        args.putBoolean("addContact", true);
                        ChatActivity.this.presentFragment(new ContactAddActivity(args));
                    }
                } else if (id == 18) {
                    ChatActivity.this.toggleMute(false);
                } else if (id == 24) {
                    try {
                        AndroidUtilities.installShortcut((long) ChatActivity.this.currentUser.id);
                    } catch (Exception e) {
                        FileLog.e(e);
                    }
                } else if (id == 21) {
                    ChatActivity.this.showDialog(AlertsCreator.createReportAlert(ChatActivity.this.getParentActivity(), ChatActivity.this.dialog_id, ChatActivity.this));
                } else if (id == 19) {
                    messageObject = null;
                    a = 1;
                    while (a >= 0) {
                        if (messageObject == null && ChatActivity.this.selectedMessagesIds[a].size() == 1) {
                            messageObject = (MessageObject) ChatActivity.this.messagesDict[a].get(new ArrayList(ChatActivity.this.selectedMessagesIds[a].keySet()).get(0));
                        }
                        ChatActivity.this.selectedMessagesIds[a].clear();
                        ChatActivity.this.selectedMessagesCanCopyIds[a].clear();
                        ChatActivity.this.selectedMessagesCanStarIds[a].clear();
                        a--;
                    }
                    if (messageObject != null && (messageObject.messageOwner.id > 0 || (messageObject.messageOwner.id < 0 && ChatActivity.this.currentEncryptedChat != null))) {
                        ChatActivity.this.showReplyPanel(true, messageObject, null, null, false);
                    }
                    ChatActivity.this.cantDeleteMessagesCount = 0;
                    ChatActivity.this.canEditMessagesCount = 0;
                    ChatActivity.this.actionBar.hideActionMode();
                    ChatActivity.this.updatePinnedMessageView(true);
                    ChatActivity.this.updateVisibleRows();
                } else if (id == 22) {
                    for (a = 0; a < 2; a++) {
                        for (Entry<Integer, MessageObject> entry : ChatActivity.this.selectedMessagesCanStarIds[a].entrySet()) {
                            StickersQuery.addRecentSticker(2, ((MessageObject) entry.getValue()).getDocument(), (int) (System.currentTimeMillis() / 1000), !ChatActivity.this.hasUnfavedSelected);
                        }
                    }
                    for (a = 1; a >= 0; a--) {
                        ChatActivity.this.selectedMessagesIds[a].clear();
                        ChatActivity.this.selectedMessagesCanCopyIds[a].clear();
                        ChatActivity.this.selectedMessagesCanStarIds[a].clear();
                    }
                    ChatActivity.this.cantDeleteMessagesCount = 0;
                    ChatActivity.this.canEditMessagesCount = 0;
                    ChatActivity.this.actionBar.hideActionMode();
                    ChatActivity.this.updatePinnedMessageView(true);
                    ChatActivity.this.updateVisibleRows();
                } else if (id == 23) {
                    messageObject = null;
                    a = 1;
                    while (a >= 0) {
                        if (messageObject == null && ChatActivity.this.selectedMessagesIds[a].size() == 1) {
                            messageObject = (MessageObject) ChatActivity.this.messagesDict[a].get(new ArrayList(ChatActivity.this.selectedMessagesIds[a].keySet()).get(0));
                        }
                        ChatActivity.this.selectedMessagesIds[a].clear();
                        ChatActivity.this.selectedMessagesCanCopyIds[a].clear();
                        ChatActivity.this.selectedMessagesCanStarIds[a].clear();
                        a--;
                    }
                    ChatActivity.this.startReplyOnTextChange = false;
                    ChatActivity.this.startEditingMessageObject(messageObject);
                    ChatActivity.this.cantDeleteMessagesCount = 0;
                    ChatActivity.this.canEditMessagesCount = 0;
                    ChatActivity.this.updatePinnedMessageView(true);
                    ChatActivity.this.updateVisibleRows();
                } else if (id == 14) {
                    ChatActivity.this.openAttachMenu();
                } else if (id == 30) {
                    SendMessagesHelper.getInstance().sendMessage("/help", ChatActivity.this.dialog_id, null, null, false, null, null, null);
                } else if (id == 31) {
                    SendMessagesHelper.getInstance().sendMessage("/settings", ChatActivity.this.dialog_id, null, null, false, null, null, null);
                } else if (id == 40) {
                    ChatActivity.this.openSearchWithText(null);
                } else if (id == 32 && ChatActivity.this.currentUser != null && ChatActivity.this.getParentActivity() != null) {
                    VoIPHelper.startCall(ChatActivity.this.currentUser, ChatActivity.this.getParentActivity(), MessagesController.getInstance().getUserFull(ChatActivity.this.currentUser.id));
                }
            }
        });
        this.avatarContainer = new ChatAvatarContainer(context, this, this.currentEncryptedChat != null);
        this.actionBar.addView(this.avatarContainer, 0, LayoutHelper.createFrame(-2, -1.0f, 51, 56.0f, 0.0f, 100.0f, 0.0f));
        if (!(this.currentChat == null || ChatObject.isChannel(this.currentChat))) {
            int count = this.currentChat.participants_count;
            if (this.info != null) {
                count = this.info.participants.participants.size();
            }
            if (count == 0 || this.currentChat.deactivated || this.currentChat.left || (this.currentChat instanceof TLRPC$TL_chatForbidden) || (this.info != null && (this.info.participants instanceof TLRPC$TL_chatParticipantsForbidden))) {
                this.avatarContainer.setEnabled(false);
            }
        }
        ActionBarMenu menu = this.actionBar.createMenu();
        if (this.currentEncryptedChat == null && !this.isBroadcast) {
            this.searchItem = menu.addItem(0, (int) R.drawable.ic_ab_search).setIsSearchField(true).setActionBarMenuItemSearchListener(new ActionBarMenuItemSearchListener() {
                boolean searchWas;

                class C59051 implements Runnable {
                    C59051() {
                    }

                    public void run() {
                        AnonymousClass11.this.searchWas = false;
                        ChatActivity.this.searchItem.getSearchField().requestFocus();
                        AndroidUtilities.showKeyboard(ChatActivity.this.searchItem.getSearchField());
                    }
                }

                public void onSearchCollapse() {
                    ChatActivity.this.searchCalendarButton.setVisibility(0);
                    if (ChatActivity.this.searchUserButton != null) {
                        ChatActivity.this.searchUserButton.setVisibility(0);
                    }
                    if (ChatActivity.this.searchingForUser) {
                        ChatActivity.this.mentionsAdapter.searchUsernameOrHashtag(null, 0, null, false);
                        ChatActivity.this.searchingForUser = false;
                    }
                    ChatActivity.this.mentionLayoutManager.setReverseLayout(false);
                    ChatActivity.this.mentionsAdapter.setSearchingMentions(false);
                    ChatActivity.this.searchingUserMessages = null;
                    ChatActivity.this.searchItem.getSearchField().setHint(LocaleController.getString("Search", R.string.Search));
                    ChatActivity.this.searchItem.setSearchFieldCaption(null);
                    ChatActivity.this.avatarContainer.setVisibility(0);
                    if (ChatActivity.this.chatActivityEnterView.hasText()) {
                        if (ChatActivity.this.headerItem != null) {
                            ChatActivity.this.headerItem.setVisibility(8);
                        }
                        if (ChatActivity.this.attachItem != null) {
                            ChatActivity.this.attachItem.setVisibility(0);
                        }
                    } else {
                        if (ChatActivity.this.headerItem != null) {
                            ChatActivity.this.headerItem.setVisibility(0);
                        }
                        if (ChatActivity.this.attachItem != null) {
                            ChatActivity.this.attachItem.setVisibility(8);
                        }
                    }
                    ChatActivity.this.searchItem.setVisibility(8);
                    ChatActivity.this.highlightMessageId = Integer.MAX_VALUE;
                    ChatActivity.this.updateVisibleRows();
                    if (this.searchWas) {
                        ChatActivity.this.scrollToLastMessage(false);
                    }
                    ChatActivity.this.updateBottomOverlay();
                    ChatActivity.this.updatePinnedMessageView(true);
                }

                public void onSearchExpand() {
                    if (ChatActivity.this.openSearchKeyboard) {
                        AndroidUtilities.runOnUIThread(new C59051(), 300);
                    }
                }

                public void onSearchPressed(EditText editText) {
                    this.searchWas = true;
                    ChatActivity.this.updateSearchButtons(0, 0, -1);
                    MessagesSearchQuery.searchMessagesInChat(editText.getText().toString(), ChatActivity.this.dialog_id, ChatActivity.this.mergeDialogId, ChatActivity.this.classGuid, 0, ChatActivity.this.searchingUserMessages);
                }

                public void onTextChanged(EditText editText) {
                    if (ChatActivity.this.searchingForUser) {
                        ChatActivity.this.mentionsAdapter.searchUsernameOrHashtag("@" + editText.getText().toString(), 0, ChatActivity.this.messages, true);
                    } else if (!ChatActivity.this.searchingForUser && ChatActivity.this.searchingUserMessages == null && ChatActivity.this.searchUserButton != null && TextUtils.equals(editText.getText(), LocaleController.getString("SearchFrom", R.string.SearchFrom))) {
                        ChatActivity.this.searchUserButton.callOnClick();
                    }
                }

                public void onCaptionCleared() {
                    if (ChatActivity.this.searchingUserMessages != null) {
                        ChatActivity.this.searchUserButton.callOnClick();
                        return;
                    }
                    if (ChatActivity.this.searchingForUser) {
                        ChatActivity.this.mentionsAdapter.searchUsernameOrHashtag(null, 0, null, false);
                        ChatActivity.this.searchingForUser = false;
                    }
                    ChatActivity.this.searchItem.getSearchField().setHint(LocaleController.getString("Search", R.string.Search));
                    ChatActivity.this.searchCalendarButton.setVisibility(0);
                    ChatActivity.this.searchUserButton.setVisibility(0);
                    ChatActivity.this.searchingUserMessages = null;
                }
            });
            this.searchItem.getSearchField().setHint(LocaleController.getString("Search", R.string.Search));
            this.searchItem.setVisibility(8);
        }
        if (this.currentUser != null) {
            TLRPC$TL_userFull userFull = MessagesController.getInstance().getUserFull(this.currentUser.id);
            this.callItem = menu.addItem(32, (int) R.drawable.ic_call_white_24dp);
            if (userFull == null || !userFull.phone_calls_available) {
                this.callItem.setVisibility(4);
            } else {
                this.callItem.setVisibility(0);
            }
        }
        this.headerItem = menu.addItem(0, (int) R.drawable.ic_ab_other);
        if (this.searchItem != null) {
            this.headerItem.addSubItem(40, LocaleController.getString("Search", R.string.Search));
        }
        if (ChatObject.isChannel(this.currentChat) && !this.currentChat.creator && (!this.currentChat.megagroup || (this.currentChat.username != null && this.currentChat.username.length() > 0))) {
            this.headerItem.addSubItem(21, LocaleController.getString("ReportChat", R.string.ReportChat));
        }
        if (this.currentUser != null) {
            this.addContactItem = this.headerItem.addSubItem(17, "");
        }
        if (this.currentEncryptedChat != null) {
            this.timeItem2 = this.headerItem.addSubItem(13, LocaleController.getString("SetTimer", R.string.SetTimer));
        }
        if (!ChatObject.isChannel(this.currentChat) || (this.currentChat != null && this.currentChat.megagroup && TextUtils.isEmpty(this.currentChat.username))) {
            this.headerItem.addSubItem(15, LocaleController.getString("ClearHistory", R.string.ClearHistory));
        }
        if (!ChatObject.isChannel(this.currentChat)) {
            if (this.currentChat == null || this.isBroadcast) {
                this.headerItem.addSubItem(16, LocaleController.getString("DeleteChatUser", R.string.DeleteChatUser));
            } else {
                this.headerItem.addSubItem(16, LocaleController.getString("DeleteAndExit", R.string.DeleteAndExit));
            }
        }
        if (this.currentUser == null || !this.currentUser.self) {
            this.muteItem = this.headerItem.addSubItem(18, null);
        } else if (this.currentUser.self) {
            this.headerItem.addSubItem(24, LocaleController.getString("AddShortcut", R.string.AddShortcut));
        }
        if (this.currentUser != null && this.currentEncryptedChat == null && this.currentUser.bot) {
            this.headerItem.addSubItem(31, LocaleController.getString("BotSettings", R.string.BotSettings));
            this.headerItem.addSubItem(30, LocaleController.getString("BotHelp", R.string.BotHelp));
            updateBotButtons();
        }
        updateTitle();
        this.avatarContainer.updateOnlineCount();
        this.avatarContainer.updateSubtitle();
        updateTitleIcons();
        this.attachItem = menu.addItem(14, (int) R.drawable.ic_ab_other).setOverrideMenuClick(true).setAllowCloseAnimation(false);
        this.attachItem.setVisibility(8);
        this.actionModeViews.clear();
        ActionBarMenu actionMode = this.actionBar.createActionMode();
        this.selectedMessagesCountTextView = new NumberTextView(actionMode.getContext());
        this.selectedMessagesCountTextView.setTextSize(18);
        this.selectedMessagesCountTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.selectedMessagesCountTextView.setTextColor(Theme.getColor(Theme.key_actionBarActionModeDefaultIcon));
        actionMode.addView(this.selectedMessagesCountTextView, LayoutHelper.createLinear(0, -1, 1.0f, 65, 0, 0, 0));
        this.selectedMessagesCountTextView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        this.actionModeTitleContainer = new FrameLayout(context) {
            protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                int width = MeasureSpec.getSize(widthMeasureSpec);
                setMeasuredDimension(width, MeasureSpec.getSize(heightMeasureSpec));
                SimpleTextView access$5300 = ChatActivity.this.actionModeTextView;
                int i = (AndroidUtilities.isTablet() || getResources().getConfiguration().orientation != 2) ? 20 : 18;
                access$5300.setTextSize(i);
                ChatActivity.this.actionModeTextView.measure(MeasureSpec.makeMeasureSpec(width, Integer.MIN_VALUE), MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(24.0f), Integer.MIN_VALUE));
                if (ChatActivity.this.actionModeSubTextView.getVisibility() != 8) {
                    access$5300 = ChatActivity.this.actionModeSubTextView;
                    i = (AndroidUtilities.isTablet() || getResources().getConfiguration().orientation != 2) ? 16 : 14;
                    access$5300.setTextSize(i);
                    ChatActivity.this.actionModeSubTextView.measure(MeasureSpec.makeMeasureSpec(width, Integer.MIN_VALUE), MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(CloseButton.TEXT_SIZE_SP), Integer.MIN_VALUE));
                }
            }

            protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
                int textTop;
                int height = bottom - top;
                if (ChatActivity.this.actionModeSubTextView.getVisibility() != 8) {
                    int textHeight = ((height / 2) - ChatActivity.this.actionModeTextView.getTextHeight()) / 2;
                    float f = (AndroidUtilities.isTablet() || getResources().getConfiguration().orientation != 2) ? 3.0f : 2.0f;
                    textTop = textHeight + AndroidUtilities.dp(f);
                } else {
                    textTop = (height - ChatActivity.this.actionModeTextView.getTextHeight()) / 2;
                }
                ChatActivity.this.actionModeTextView.layout(0, textTop, ChatActivity.this.actionModeTextView.getMeasuredWidth(), ChatActivity.this.actionModeTextView.getTextHeight() + textTop);
                if (ChatActivity.this.actionModeSubTextView.getVisibility() != 8) {
                    int textHeight2 = (height / 2) + (((height / 2) - ChatActivity.this.actionModeSubTextView.getTextHeight()) / 2);
                    if (AndroidUtilities.isTablet() || getResources().getConfiguration().orientation == 2) {
                        textTop = textHeight2 - AndroidUtilities.dp(1.0f);
                        ChatActivity.this.actionModeSubTextView.layout(0, textTop, ChatActivity.this.actionModeSubTextView.getMeasuredWidth(), ChatActivity.this.actionModeSubTextView.getTextHeight() + textTop);
                    } else {
                        textTop = textHeight2 - AndroidUtilities.dp(1.0f);
                        ChatActivity.this.actionModeSubTextView.layout(0, textTop, ChatActivity.this.actionModeSubTextView.getMeasuredWidth(), ChatActivity.this.actionModeSubTextView.getTextHeight() + textTop);
                    }
                }
            }
        };
        actionMode.addView(this.actionModeTitleContainer, LayoutHelper.createLinear(0, -1, 1.0f, 65, 0, 0, 0));
        this.actionModeTitleContainer.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        this.actionModeTitleContainer.setVisibility(8);
        this.actionModeTextView = new SimpleTextView(context);
        this.actionModeTextView.setTextSize(18);
        this.actionModeTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.actionModeTextView.setTextColor(Theme.getColor(Theme.key_actionBarActionModeDefaultIcon));
        this.actionModeTextView.setText(LocaleController.getString("Edit", R.string.Edit));
        this.actionModeTitleContainer.addView(this.actionModeTextView, LayoutHelper.createFrame(-1, -1.0f));
        this.actionModeSubTextView = new SimpleTextView(context);
        this.actionModeSubTextView.setGravity(3);
        this.actionModeSubTextView.setTextColor(Theme.getColor(Theme.key_actionBarActionModeDefaultIcon));
        this.actionModeTitleContainer.addView(this.actionModeSubTextView, LayoutHelper.createFrame(-1, -1.0f));
        if (this.currentEncryptedChat == null) {
            this.actionModeViews.add(actionMode.addItemWithWidth(23, R.drawable.group_edit, AndroidUtilities.dp(54.0f)));
            if (!this.isBroadcast) {
                this.actionModeViews.add(actionMode.addItemWithWidth(19, R.drawable.ic_ab_reply, AndroidUtilities.dp(54.0f)));
            }
            this.actionModeViews.add(actionMode.addItemWithWidth(22, R.drawable.ic_ab_fave, AndroidUtilities.dp(54.0f)));
            this.actionModeViews.add(actionMode.addItemWithWidth(10, R.drawable.ic_ab_copy, AndroidUtilities.dp(54.0f)));
            this.actionModeViews.add(actionMode.addItemWithWidth(11, R.drawable.ic_ab_forward, AndroidUtilities.dp(54.0f)));
            this.actionModeViews.add(actionMode.addItemWithWidth(12, R.drawable.ic_ab_delete, AndroidUtilities.dp(54.0f)));
        } else {
            this.actionModeViews.add(actionMode.addItemWithWidth(23, R.drawable.group_edit, AndroidUtilities.dp(54.0f)));
            this.actionModeViews.add(actionMode.addItemWithWidth(19, R.drawable.ic_ab_reply, AndroidUtilities.dp(54.0f)));
            this.actionModeViews.add(actionMode.addItemWithWidth(22, R.drawable.ic_ab_fave, AndroidUtilities.dp(54.0f)));
            this.actionModeViews.add(actionMode.addItemWithWidth(10, R.drawable.ic_ab_copy, AndroidUtilities.dp(54.0f)));
            this.actionModeViews.add(actionMode.addItemWithWidth(12, R.drawable.ic_ab_delete, AndroidUtilities.dp(54.0f)));
        }
        ActionBarMenuItem item = actionMode.getItem(23);
        int i = (this.canEditMessagesCount == 1 && this.selectedMessagesIds[0].size() + this.selectedMessagesIds[1].size() == 1) ? 0 : 8;
        item.setVisibility(i);
        actionMode.getItem(10).setVisibility(this.selectedMessagesCanCopyIds[0].size() + this.selectedMessagesCanCopyIds[1].size() != 0 ? 0 : 8);
        actionMode.getItem(22).setVisibility(this.selectedMessagesCanStarIds[0].size() + this.selectedMessagesCanStarIds[1].size() != 0 ? 0 : 8);
        actionMode.getItem(12).setVisibility(this.cantDeleteMessagesCount == 0 ? 0 : 8);
        checkActionBarMenu();
        this.fragmentView = new SizeNotifierFrameLayout(context) {
            int inputFieldHeight = 0;

            protected void onAttachedToWindow() {
                super.onAttachedToWindow();
                MessageObject messageObject = MediaController.getInstance().getPlayingMessageObject();
                if (messageObject != null && messageObject.isRoundVideo() && messageObject.eventId == 0 && messageObject.getDialogId() == ChatActivity.this.dialog_id) {
                    MediaController.getInstance().setTextureView(ChatActivity.this.createTextureView(false), ChatActivity.this.aspectRatioFrameLayout, ChatActivity.this.roundVideoContainer, true);
                }
            }

            protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
                boolean result;
                MessageObject messageObject = MediaController.getInstance().getPlayingMessageObject();
                boolean isRoundVideo = messageObject != null && messageObject.eventId == 0 && messageObject.isRoundVideo();
                if (!isRoundVideo || child != ChatActivity.this.roundVideoContainer) {
                    result = super.drawChild(canvas, child, drawingTime);
                    if (isRoundVideo && child == ChatActivity.this.chatListView && messageObject.type != 5 && ChatActivity.this.roundVideoContainer != null) {
                        super.drawChild(canvas, ChatActivity.this.roundVideoContainer, drawingTime);
                    }
                } else if (messageObject.type == 5) {
                    if (Theme.chat_roundVideoShadow != null && ChatActivity.this.aspectRatioFrameLayout.isDrawingReady()) {
                        int x = ((int) child.getX()) - AndroidUtilities.dp(3.0f);
                        int y = ((int) child.getY()) - AndroidUtilities.dp(2.0f);
                        Theme.chat_roundVideoShadow.setAlpha(255);
                        Theme.chat_roundVideoShadow.setBounds(x, y, (AndroidUtilities.roundMessageSize + x) + AndroidUtilities.dp(6.0f), (AndroidUtilities.roundMessageSize + y) + AndroidUtilities.dp(6.0f));
                        Theme.chat_roundVideoShadow.draw(canvas);
                    }
                    result = super.drawChild(canvas, child, drawingTime);
                } else {
                    result = false;
                }
                if (child == ChatActivity.this.actionBar && ChatActivity.this.parentLayout != null) {
                    ChatActivity.this.parentLayout.drawHeaderShadow(canvas, ChatActivity.this.actionBar.getVisibility() == 0 ? ChatActivity.this.actionBar.getMeasuredHeight() : 0);
                }
                return result;
            }

            protected boolean isActionBarVisible() {
                return ChatActivity.this.actionBar.getVisibility() == 0;
            }

            protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                int widthSize = MeasureSpec.getSize(widthMeasureSpec);
                int allHeight = MeasureSpec.getSize(heightMeasureSpec);
                int heightSize = allHeight;
                setMeasuredDimension(widthSize, heightSize);
                heightSize -= getPaddingTop();
                measureChildWithMargins(ChatActivity.this.actionBar, widthMeasureSpec, 0, heightMeasureSpec, 0);
                int actionBarHeight = ChatActivity.this.actionBar.getMeasuredHeight();
                if (ChatActivity.this.actionBar.getVisibility() == 0) {
                    heightSize -= actionBarHeight;
                }
                if (getKeyboardHeight() <= AndroidUtilities.dp(CloseButton.TEXT_SIZE_SP) && !AndroidUtilities.isInMultiwindow) {
                    heightSize -= ChatActivity.this.chatActivityEnterView.getEmojiPadding();
                    allHeight -= ChatActivity.this.chatActivityEnterView.getEmojiPadding();
                }
                int childCount = getChildCount();
                measureChildWithMargins(ChatActivity.this.chatActivityEnterView, widthMeasureSpec, 0, heightMeasureSpec, 0);
                this.inputFieldHeight = ChatActivity.this.chatActivityEnterView.getMeasuredHeight();
                for (int i = 0; i < childCount; i++) {
                    View child = getChildAt(i);
                    if (!(child == null || child.getVisibility() == 8 || child == ChatActivity.this.chatActivityEnterView || child == ChatActivity.this.actionBar)) {
                        if (child == ChatActivity.this.chatListView || child == ChatActivity.this.progressView) {
                            child.measure(MeasureSpec.makeMeasureSpec(widthSize, 1073741824), MeasureSpec.makeMeasureSpec(Math.max(AndroidUtilities.dp(Settings.LOCATION_REQUEST_SMALLEST_DISPLACEMENT), AndroidUtilities.dp((float) ((ChatActivity.this.chatActivityEnterView.isTopViewVisible() ? 48 : 0) + 2)) + (heightSize - this.inputFieldHeight)), 1073741824));
                        } else if (child == ChatActivity.this.instantCameraView || child == ChatActivity.this.overlayView) {
                            child.measure(MeasureSpec.makeMeasureSpec(widthSize, 1073741824), MeasureSpec.makeMeasureSpec((allHeight - this.inputFieldHeight) + AndroidUtilities.dp(3.0f), 1073741824));
                        } else if (child == ChatActivity.this.emptyViewContainer) {
                            child.measure(MeasureSpec.makeMeasureSpec(widthSize, 1073741824), MeasureSpec.makeMeasureSpec(heightSize, 1073741824));
                        } else if (ChatActivity.this.chatActivityEnterView.isPopupView(child)) {
                            if (!AndroidUtilities.isInMultiwindow) {
                                child.measure(MeasureSpec.makeMeasureSpec(widthSize, 1073741824), MeasureSpec.makeMeasureSpec(child.getLayoutParams().height, 1073741824));
                            } else if (AndroidUtilities.isTablet()) {
                                child.measure(MeasureSpec.makeMeasureSpec(widthSize, 1073741824), MeasureSpec.makeMeasureSpec(Math.min(AndroidUtilities.dp(320.0f), (((heightSize - this.inputFieldHeight) + actionBarHeight) - AndroidUtilities.statusBarHeight) + getPaddingTop()), 1073741824));
                            } else {
                                child.measure(MeasureSpec.makeMeasureSpec(widthSize, 1073741824), MeasureSpec.makeMeasureSpec((((heightSize - this.inputFieldHeight) + actionBarHeight) - AndroidUtilities.statusBarHeight) + getPaddingTop(), 1073741824));
                            }
                        } else if (child == ChatActivity.this.mentionContainer) {
                            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) ChatActivity.this.mentionContainer.getLayoutParams();
                            if (ChatActivity.this.mentionsAdapter.isBannedInline()) {
                                child.measure(MeasureSpec.makeMeasureSpec(widthSize, 1073741824), MeasureSpec.makeMeasureSpec(heightSize, Integer.MIN_VALUE));
                            } else {
                                int height;
                                ChatActivity.this.mentionListViewIgnoreLayout = true;
                                int maxHeight;
                                int padding;
                                if (ChatActivity.this.mentionsAdapter.isBotContext() && ChatActivity.this.mentionsAdapter.isMediaLayout()) {
                                    maxHeight = ChatActivity.this.mentionGridLayoutManager.getRowsCount(widthSize) * 102;
                                    if (ChatActivity.this.mentionsAdapter.isBotContext() && ChatActivity.this.mentionsAdapter.getBotContextSwitch() != null) {
                                        maxHeight += 34;
                                    }
                                    height = (heightSize - ChatActivity.this.chatActivityEnterView.getMeasuredHeight()) + (maxHeight != 0 ? AndroidUtilities.dp(2.0f) : 0);
                                    padding = Math.max(0, height - AndroidUtilities.dp(Math.min((float) maxHeight, 122.399994f)));
                                    if (ChatActivity.this.mentionLayoutManager.getReverseLayout()) {
                                        ChatActivity.this.mentionListView.setPadding(0, 0, 0, padding);
                                    } else {
                                        ChatActivity.this.mentionListView.setPadding(0, padding, 0, 0);
                                    }
                                } else {
                                    int size = ChatActivity.this.mentionsAdapter.getItemCount();
                                    maxHeight = 0;
                                    if (ChatActivity.this.mentionsAdapter.isBotContext()) {
                                        if (ChatActivity.this.mentionsAdapter.getBotContextSwitch() != null) {
                                            maxHeight = 0 + 36;
                                            size--;
                                        }
                                        maxHeight += size * 68;
                                    } else {
                                        maxHeight = 0 + (size * 36);
                                    }
                                    height = (heightSize - ChatActivity.this.chatActivityEnterView.getMeasuredHeight()) + (maxHeight != 0 ? AndroidUtilities.dp(2.0f) : 0);
                                    padding = Math.max(0, height - AndroidUtilities.dp(Math.min((float) maxHeight, 122.399994f)));
                                    if (ChatActivity.this.mentionLayoutManager.getReverseLayout()) {
                                        ChatActivity.this.mentionListView.setPadding(0, 0, 0, padding);
                                    } else {
                                        ChatActivity.this.mentionListView.setPadding(0, padding, 0, 0);
                                    }
                                }
                                layoutParams.height = height;
                                layoutParams.topMargin = 0;
                                ChatActivity.this.mentionListViewIgnoreLayout = false;
                                child.measure(MeasureSpec.makeMeasureSpec(widthSize, 1073741824), MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824));
                            }
                        } else {
                            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
                        }
                    }
                }
            }

            protected void onLayout(boolean changed, int l, int t, int r, int b) {
                int count = getChildCount();
                int paddingBottom = (getKeyboardHeight() > AndroidUtilities.dp(CloseButton.TEXT_SIZE_SP) || AndroidUtilities.isInMultiwindow) ? 0 : ChatActivity.this.chatActivityEnterView.getEmojiPadding();
                setBottomClip(paddingBottom);
                for (int i = 0; i < count; i++) {
                    View child = getChildAt(i);
                    if (child.getVisibility() != 8) {
                        int childLeft;
                        int childTop;
                        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) child.getLayoutParams();
                        int width = child.getMeasuredWidth();
                        int height = child.getMeasuredHeight();
                        int gravity = lp.gravity;
                        if (gravity == -1) {
                            gravity = 51;
                        }
                        int verticalGravity = gravity & 112;
                        switch ((gravity & 7) & 7) {
                            case 1:
                                childLeft = ((((r - l) - width) / 2) + lp.leftMargin) - lp.rightMargin;
                                break;
                            case 5:
                                childLeft = (r - width) - lp.rightMargin;
                                break;
                            default:
                                childLeft = lp.leftMargin;
                                break;
                        }
                        switch (verticalGravity) {
                            case 16:
                                childTop = (((((b - paddingBottom) - t) - height) / 2) + lp.topMargin) - lp.bottomMargin;
                                break;
                            case 48:
                                childTop = lp.topMargin + getPaddingTop();
                                if (child != ChatActivity.this.actionBar && ChatActivity.this.actionBar.getVisibility() == 0) {
                                    childTop += ChatActivity.this.actionBar.getMeasuredHeight();
                                    break;
                                }
                            case 80:
                                childTop = (((b - paddingBottom) - t) - height) - lp.bottomMargin;
                                break;
                            default:
                                childTop = lp.topMargin;
                                break;
                        }
                        if (child == ChatActivity.this.mentionContainer) {
                            childTop -= ChatActivity.this.chatActivityEnterView.getMeasuredHeight() - AndroidUtilities.dp(2.0f);
                        } else if (child == ChatActivity.this.pagedownButton) {
                            childTop -= ChatActivity.this.chatActivityEnterView.getMeasuredHeight();
                        } else if (child == ChatActivity.this.mentiondownButton) {
                            childTop -= ChatActivity.this.chatActivityEnterView.getMeasuredHeight();
                        } else if (child == ChatActivity.this.emptyViewContainer) {
                            childTop -= (this.inputFieldHeight / 2) - (ChatActivity.this.actionBar.getVisibility() == 0 ? ChatActivity.this.actionBar.getMeasuredHeight() / 2 : 0);
                        } else if (ChatActivity.this.chatActivityEnterView.isPopupView(child)) {
                            if (AndroidUtilities.isInMultiwindow) {
                                childTop = (ChatActivity.this.chatActivityEnterView.getTop() - child.getMeasuredHeight()) + AndroidUtilities.dp(1.0f);
                            } else {
                                childTop = ChatActivity.this.chatActivityEnterView.getBottom();
                            }
                        } else if (child == ChatActivity.this.gifHintTextView || child == ChatActivity.this.voiceHintTextView || child == ChatActivity.this.mediaBanTooltip) {
                            childTop -= this.inputFieldHeight;
                        } else if (child == ChatActivity.this.chatListView || child == ChatActivity.this.progressView) {
                            if (ChatActivity.this.chatActivityEnterView.isTopViewVisible()) {
                                childTop -= AndroidUtilities.dp(48.0f);
                            }
                        } else if (child == ChatActivity.this.actionBar) {
                            childTop -= getPaddingTop();
                        } else if (child == ChatActivity.this.roundVideoContainer) {
                            childTop = ChatActivity.this.actionBar.getMeasuredHeight();
                        } else if (child == ChatActivity.this.instantCameraView || child == ChatActivity.this.overlayView) {
                            childTop = 0;
                        }
                        child.layout(childLeft, childTop, childLeft + width, childTop + height);
                    }
                }
                ChatActivity.this.updateMessagesVisisblePart();
                notifyHeightChanged();
            }
        };
        this.contentView = (SizeNotifierFrameLayout) this.fragmentView;
        this.contentView.setBackgroundImage(Theme.getCachedWallpaper());
        this.emptyViewContainer = new FrameLayout(context);
        this.emptyViewContainer.setVisibility(4);
        this.contentView.addView(this.emptyViewContainer, LayoutHelper.createFrame(-1, -2, 17));
        this.emptyViewContainer.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        if (this.currentEncryptedChat != null) {
            this.bigEmptyView = new ChatBigEmptyView(context, true);
            if (this.currentEncryptedChat.admin_id == UserConfig.getClientUserId()) {
                this.bigEmptyView.setSecretText(LocaleController.formatString("EncryptedPlaceholderTitleOutgoing", R.string.EncryptedPlaceholderTitleOutgoing, new Object[]{UserObject.getFirstName(this.currentUser)}));
            } else {
                this.bigEmptyView.setSecretText(LocaleController.formatString("EncryptedPlaceholderTitleIncoming", R.string.EncryptedPlaceholderTitleIncoming, new Object[]{UserObject.getFirstName(this.currentUser)}));
            }
            this.emptyViewContainer.addView(this.bigEmptyView, new FrameLayout.LayoutParams(-2, -2, 17));
        } else if (this.currentUser == null || !this.currentUser.self) {
            this.emptyView = new TextView(context);
            if (this.currentUser == null || this.currentUser.id == 777000 || this.currentUser.id == 429000 || this.currentUser.id == 4244000 || !MessagesController.isSupportId(this.currentUser.id)) {
                this.emptyView.setText(LocaleController.getString("NoMessages", R.string.NoMessages));
            } else {
                this.emptyView.setText(LocaleController.getAppNameString("GotAQuestion", R.string.GotAQuestion));
            }
            this.emptyView.setTextSize(1, 14.0f);
            this.emptyView.setGravity(17);
            this.emptyView.setTextColor(Theme.getColor(Theme.key_chat_serviceText));
            this.emptyView.setBackgroundResource(R.drawable.system);
            this.emptyView.getBackground().setColorFilter(Theme.colorFilter);
            this.emptyView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            this.emptyView.setPadding(AndroidUtilities.dp(Settings.LOCATION_REQUEST_SMALLEST_DISPLACEMENT), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(Settings.LOCATION_REQUEST_SMALLEST_DISPLACEMENT), AndroidUtilities.dp(3.0f));
            this.emptyViewContainer.addView(this.emptyView, new FrameLayout.LayoutParams(-2, -2, 17));
        } else {
            this.bigEmptyView = new ChatBigEmptyView(context, false);
            this.emptyViewContainer.addView(this.bigEmptyView, new FrameLayout.LayoutParams(-2, -2, 17));
        }
        if (this.chatActivityEnterView != null) {
            this.chatActivityEnterView.onDestroy();
            if (this.chatActivityEnterView.isEditingMessage()) {
                oldMessage = null;
            } else {
                oldMessage = this.chatActivityEnterView.getFieldText();
            }
        } else {
            oldMessage = null;
        }
        if (this.mentionsAdapter != null) {
            this.mentionsAdapter.onDestroy();
        }
        this.chatListView = new RecyclerListView(context) {
            ArrayList<ChatMessageCell> drawCaptionAfter = new ArrayList();
            ArrayList<ChatMessageCell> drawNamesAfter = new ArrayList();
            ArrayList<ChatMessageCell> drawTimeAfter = new ArrayList();

            protected void onLayout(boolean changed, int l, int t, int r, int b) {
                super.onLayout(changed, l, t, r, b);
                ChatActivity.this.forceScrollToTop = false;
                if (ChatActivity.this.chatAdapter.isBot) {
                    int childCount = getChildCount();
                    for (int a = 0; a < childCount; a++) {
                        View child = getChildAt(a);
                        if (child instanceof BotHelpCell) {
                            int top = ((b - t) / 2) - (child.getMeasuredHeight() / 2);
                            if (child.getTop() > top) {
                                child.layout(0, top, r - l, child.getMeasuredHeight() + top);
                                return;
                            }
                            return;
                        }
                    }
                }
            }

            protected void onChildPressed(View child, boolean pressed) {
                super.onChildPressed(child, pressed);
                if (child instanceof ChatMessageCell) {
                    GroupedMessages groupedMessages = ((ChatMessageCell) child).getCurrentMessagesGroup();
                    if (groupedMessages != null) {
                        int count = getChildCount();
                        for (int a = 0; a < count; a++) {
                            View item = getChildAt(a);
                            if (item != child && (item instanceof ChatMessageCell)) {
                                ChatMessageCell cell = (ChatMessageCell) item;
                                if (((ChatMessageCell) item).getCurrentMessagesGroup() == groupedMessages) {
                                    cell.setPressed(pressed);
                                }
                            }
                        }
                    }
                }
            }

            public void requestLayout() {
                if (!ChatActivity.this.chatListViewIgnoreLayout) {
                    super.requestLayout();
                }
            }

            public boolean drawChild(Canvas canvas, View child, long drawingTime) {
                ChatMessageCell cell;
                GroupedMessagePosition position;
                int a;
                int size;
                int clipLeft = 0;
                int clipBottom = 0;
                if (child instanceof ChatMessageCell) {
                    cell = (ChatMessageCell) child;
                    position = cell.getCurrentPosition();
                    GroupedMessages group = cell.getCurrentMessagesGroup();
                    if (position != null) {
                        if (position.pw != position.spanSize && position.spanSize == 1000 && position.siblingHeights == null && group.hasSibling) {
                            clipLeft = ((ChatMessageCell) child).getBackgroundDrawableLeft();
                        } else if (position.siblingHeights != null) {
                            clipBottom = child.getBottom() - AndroidUtilities.dp((float) ((cell.isPinnedBottom() ? 1 : 0) + 1));
                        }
                    }
                }
                if (clipLeft != 0) {
                    canvas.save();
                    canvas.clipRect(clipLeft, child.getTop(), child.getRight(), child.getBottom());
                } else if (clipBottom != 0) {
                    canvas.save();
                    canvas.clipRect(child.getLeft(), child.getTop(), child.getRight(), clipBottom);
                }
                boolean result = super.drawChild(canvas, child, drawingTime);
                if (!(clipLeft == 0 && clipBottom == 0)) {
                    canvas.restore();
                }
                int num = 0;
                int count = getChildCount();
                for (a = 0; a < count; a++) {
                    if (getChildAt(a) == child) {
                        num = a;
                        break;
                    }
                }
                if (num == count - 1) {
                    size = this.drawTimeAfter.size();
                    if (size > 0) {
                        for (a = 0; a < size; a++) {
                            cell = (ChatMessageCell) this.drawTimeAfter.get(a);
                            canvas.save();
                            canvas.translate((float) cell.getLeft(), (float) cell.getTop());
                            cell.drawTimeLayout(canvas);
                            canvas.restore();
                        }
                        this.drawTimeAfter.clear();
                    }
                    size = this.drawNamesAfter.size();
                    if (size > 0) {
                        for (a = 0; a < size; a++) {
                            cell = (ChatMessageCell) this.drawNamesAfter.get(a);
                            canvas.save();
                            canvas.translate((float) cell.getLeft(), (float) cell.getTop());
                            cell.drawNamesLayout(canvas);
                            canvas.restore();
                        }
                        this.drawNamesAfter.clear();
                    }
                    size = this.drawCaptionAfter.size();
                    if (size > 0) {
                        for (a = 0; a < size; a++) {
                            cell = (ChatMessageCell) this.drawCaptionAfter.get(a);
                            canvas.save();
                            canvas.translate((float) cell.getLeft(), (float) cell.getTop());
                            cell.drawCaptionLayout(canvas);
                            canvas.restore();
                        }
                        this.drawCaptionAfter.clear();
                    }
                }
                if (child instanceof ChatMessageCell) {
                    ChatMessageCell chatMessageCell = (ChatMessageCell) child;
                    position = chatMessageCell.getCurrentPosition();
                    if (position != null) {
                        if (position.last || (position.minX == (byte) 0 && position.minY == (byte) 0)) {
                            if (num == count - 1) {
                                canvas.save();
                                canvas.translate((float) chatMessageCell.getLeft(), (float) chatMessageCell.getTop());
                                if (position.last) {
                                    chatMessageCell.drawTimeLayout(canvas);
                                }
                                if (position.minX == (byte) 0 && position.minY == (byte) 0) {
                                    chatMessageCell.drawNamesLayout(canvas);
                                }
                                canvas.restore();
                            } else {
                                if (position.last) {
                                    this.drawTimeAfter.add(chatMessageCell);
                                }
                                if (position.minX == (byte) 0 && position.minY == (byte) 0 && chatMessageCell.hasNameLayout()) {
                                    this.drawNamesAfter.add(chatMessageCell);
                                }
                            }
                        }
                        if (num == count - 1) {
                            canvas.save();
                            canvas.translate((float) chatMessageCell.getLeft(), (float) chatMessageCell.getTop());
                            if (!(!chatMessageCell.hasCaptionLayout() || (position.flags & 8) == 0 || (position.flags & 1) == 0)) {
                                chatMessageCell.drawCaptionLayout(canvas);
                            }
                            canvas.restore();
                        } else if (!(!chatMessageCell.hasCaptionLayout() || (position.flags & 8) == 0 || (position.flags & 1) == 0)) {
                            this.drawCaptionAfter.add(chatMessageCell);
                        }
                    }
                    ImageReceiver imageReceiver = chatMessageCell.getAvatarImage();
                    if (imageReceiver != null) {
                        ViewHolder holder;
                        int p;
                        int idx;
                        GroupedMessages groupedMessages = ChatActivity.this.getValidGroupedMessage(chatMessageCell.getMessageObject());
                        int top = child.getTop();
                        if (chatMessageCell.isPinnedBottom()) {
                            holder = ChatActivity.this.chatListView.getChildViewHolder(child);
                            if (holder != null) {
                                int nextPosition;
                                p = holder.getAdapterPosition();
                                if (groupedMessages == null || position == null) {
                                    nextPosition = p - 1;
                                } else {
                                    idx = groupedMessages.posArray.indexOf(position);
                                    size = groupedMessages.posArray.size();
                                    if ((position.flags & 8) != 0) {
                                        nextPosition = (p - size) + idx;
                                    } else {
                                        nextPosition = p - 1;
                                        a = idx + 1;
                                        while (idx < size && ((GroupedMessagePosition) groupedMessages.posArray.get(a)).minY <= position.maxY) {
                                            nextPosition--;
                                            a++;
                                        }
                                    }
                                }
                                if (ChatActivity.this.chatListView.findViewHolderForAdapterPosition(nextPosition) != null) {
                                    imageReceiver.setImageY(-AndroidUtilities.dp(1000.0f));
                                    imageReceiver.draw(canvas);
                                }
                            }
                        }
                        if (chatMessageCell.isPinnedTop()) {
                            holder = ChatActivity.this.chatListView.getChildViewHolder(child);
                            if (holder != null) {
                                do {
                                    int prevPosition;
                                    p = holder.getAdapterPosition();
                                    if (groupedMessages == null || position == null) {
                                        prevPosition = p + 1;
                                    } else {
                                        idx = groupedMessages.posArray.indexOf(position);
                                        size = groupedMessages.posArray.size();
                                        if ((position.flags & 4) != 0) {
                                            prevPosition = (p + idx) + 1;
                                        } else {
                                            prevPosition = p + 1;
                                            a = idx - 1;
                                            while (idx >= 0 && ((GroupedMessagePosition) groupedMessages.posArray.get(a)).maxY >= position.minY) {
                                                prevPosition++;
                                                a--;
                                            }
                                        }
                                    }
                                    holder = ChatActivity.this.chatListView.findViewHolderForAdapterPosition(prevPosition);
                                    if (holder == null) {
                                        break;
                                    }
                                    top = holder.itemView.getTop();
                                    if (!(holder.itemView instanceof ChatMessageCell)) {
                                        break;
                                    }
                                } while (((ChatMessageCell) holder.itemView).isPinnedTop());
                            }
                        }
                        int y = child.getTop() + chatMessageCell.getLayoutHeight();
                        int maxY = ChatActivity.this.chatListView.getMeasuredHeight() - ChatActivity.this.chatListView.getPaddingBottom();
                        if (y > maxY) {
                            y = maxY;
                        }
                        if (y - AndroidUtilities.dp(48.0f) < top) {
                            y = top + AndroidUtilities.dp(48.0f);
                        }
                        imageReceiver.setImageY(y - AndroidUtilities.dp(44.0f));
                        imageReceiver.draw(canvas);
                    }
                }
                return result;
            }
        };
        this.chatListView.setTag(Integer.valueOf(1));
        this.chatListView.setVerticalScrollBarEnabled(true);
        RecyclerListView recyclerListView = this.chatListView;
        Adapter chatActivityAdapter = new ChatActivityAdapter(context);
        this.chatAdapter = chatActivityAdapter;
        recyclerListView.setAdapter(chatActivityAdapter);
        this.chatListView.setClipToPadding(false);
        this.chatListView.setPadding(0, AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(3.0f));
        this.chatListView.setItemAnimator(null);
        this.chatListView.setLayoutAnimation(null);
        this.chatLayoutManager = new GridLayoutManagerFixed(context, 1000, 1, true) {
            public boolean supportsPredictiveItemAnimations() {
                return false;
            }

            public void smoothScrollToPosition(RecyclerView recyclerView, State state, int position) {
                LinearSmoothScrollerMiddle linearSmoothScroller = new LinearSmoothScrollerMiddle(recyclerView.getContext());
                linearSmoothScroller.setTargetPosition(position);
                startSmoothScroll(linearSmoothScroller);
            }

            public boolean shouldLayoutChildFromOpositeSide(View child) {
                if (!(child instanceof ChatMessageCell) || ((ChatMessageCell) child).getMessageObject().isOutOwner()) {
                    return false;
                }
                return true;
            }

            protected boolean hasSiblingChild(int position) {
                if (position < ChatActivity.this.chatAdapter.messagesStartRow || position >= ChatActivity.this.chatAdapter.messagesEndRow) {
                    return false;
                }
                int index = position - ChatActivity.this.chatAdapter.messagesStartRow;
                if (index < 0 || index >= ChatActivity.this.messages.size()) {
                    return false;
                }
                MessageObject message = (MessageObject) ChatActivity.this.messages.get(index);
                GroupedMessages group = ChatActivity.this.getValidGroupedMessage(message);
                if (group == null) {
                    return false;
                }
                GroupedMessagePosition pos = (GroupedMessagePosition) group.positions.get(message);
                if (pos.minX == pos.maxX || pos.minY != pos.maxY || pos.minY == (byte) 0) {
                    return false;
                }
                int count = group.posArray.size();
                for (int a = 0; a < count; a++) {
                    GroupedMessagePosition p = (GroupedMessagePosition) group.posArray.get(a);
                    if (p != pos && p.minY <= pos.minY && p.maxY >= pos.minY) {
                        return true;
                    }
                }
                return false;
            }
        };
        this.chatLayoutManager.setSpanSizeLookup(new SpanSizeLookup() {
            public int getSpanSize(int position) {
                if (position >= ChatActivity.this.chatAdapter.messagesStartRow && position < ChatActivity.this.chatAdapter.messagesEndRow) {
                    int idx = position - ChatActivity.this.chatAdapter.messagesStartRow;
                    if (idx >= 0 && idx < ChatActivity.this.messages.size()) {
                        MessageObject message = (MessageObject) ChatActivity.this.messages.get(idx);
                        GroupedMessages groupedMessages = ChatActivity.this.getValidGroupedMessage(message);
                        if (groupedMessages != null) {
                            return ((GroupedMessagePosition) groupedMessages.positions.get(message)).spanSize;
                        }
                    }
                }
                return 1000;
            }
        });
        this.chatListView.setLayoutManager(this.chatLayoutManager);
        this.chatListView.addItemDecoration(new ItemDecoration() {
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
                outRect.bottom = 0;
                if (view instanceof ChatMessageCell) {
                    ChatMessageCell cell = (ChatMessageCell) view;
                    GroupedMessages group = cell.getCurrentMessagesGroup();
                    if (group != null) {
                        GroupedMessagePosition position = cell.getCurrentPosition();
                        if (position != null && position.siblingHeights != null) {
                            int a;
                            float maxHeight = ((float) Math.max(AndroidUtilities.displaySize.x, AndroidUtilities.displaySize.y)) * 0.5f;
                            int h = 0;
                            for (float f : position.siblingHeights) {
                                h += (int) Math.ceil((double) (f * maxHeight));
                            }
                            h += (position.maxY - position.minY) * AndroidUtilities.dp(11.0f);
                            int count = group.posArray.size();
                            for (a = 0; a < count; a++) {
                                GroupedMessagePosition pos = (GroupedMessagePosition) group.posArray.get(a);
                                if (pos.minY == position.minY && ((pos.minX != position.minX || pos.maxX != position.maxX || pos.minY != position.minY || pos.maxY != position.maxY) && pos.minY == position.minY)) {
                                    h -= ((int) Math.ceil((double) (pos.ph * maxHeight))) - AndroidUtilities.dp(4.0f);
                                    break;
                                }
                            }
                            outRect.bottom = -h;
                        }
                    }
                }
            }
        });
        this.contentView.addView(this.chatListView, LayoutHelper.createFrame(-1, -1.0f));
        this.chatListView.setOnItemLongClickListener(this.onItemLongClickListener);
        this.chatListView.setOnItemClickListener(this.onItemClickListener);
        this.chatListView.setOnScrollListener(new OnScrollListener() {
            private final int scrollValue = AndroidUtilities.dp(100.0f);
            private float totalDy = 0.0f;

            class C59081 extends AnimatorListenerAdapter {
                C59081() {
                }

                public void onAnimationEnd(Animator animation) {
                    if (animation.equals(ChatActivity.this.floatingDateAnimation)) {
                        ChatActivity.this.floatingDateAnimation = null;
                    }
                }
            }

            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == 1) {
                    ChatActivity.this.scrollingFloatingDate = true;
                    ChatActivity.this.checkTextureViewPosition = true;
                } else if (newState == 0) {
                    ChatActivity.this.scrollingFloatingDate = false;
                    ChatActivity.this.checkTextureViewPosition = false;
                    ChatActivity.this.hideFloatingDateView(true);
                }
            }

            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                ChatActivity.this.chatListView.invalidate();
                if (!(dy == 0 || !ChatActivity.this.scrollingFloatingDate || ChatActivity.this.currentFloatingTopIsNotMessage)) {
                    if (ChatActivity.this.highlightMessageId != Integer.MAX_VALUE) {
                        ChatActivity.this.highlightMessageId = Integer.MAX_VALUE;
                        ChatActivity.this.updateVisibleRows();
                    }
                    if (ChatActivity.this.floatingDateView.getTag() == null) {
                        if (ChatActivity.this.floatingDateAnimation != null) {
                            ChatActivity.this.floatingDateAnimation.cancel();
                        }
                        ChatActivity.this.floatingDateView.setTag(Integer.valueOf(1));
                        ChatActivity.this.floatingDateAnimation = new AnimatorSet();
                        ChatActivity.this.floatingDateAnimation.setDuration(150);
                        AnimatorSet access$10000 = ChatActivity.this.floatingDateAnimation;
                        Animator[] animatorArr = new Animator[1];
                        animatorArr[0] = ObjectAnimator.ofFloat(ChatActivity.this.floatingDateView, "alpha", new float[]{1.0f});
                        access$10000.playTogether(animatorArr);
                        ChatActivity.this.floatingDateAnimation.addListener(new C59081());
                        ChatActivity.this.floatingDateAnimation.start();
                    }
                }
                ChatActivity.this.checkScrollForLoad(true);
                int firstVisibleItem = ChatActivity.this.chatLayoutManager.findFirstVisibleItemPosition();
                if (firstVisibleItem != -1) {
                    int totalItemCount = ChatActivity.this.chatAdapter.getItemCount();
                    if (firstVisibleItem == 0 && ChatActivity.this.forwardEndReached[0]) {
                        ChatActivity.this.showPagedownButton(false, true);
                    } else if (dy > 0) {
                        if (ChatActivity.this.pagedownButton.getTag() == null) {
                            this.totalDy += (float) dy;
                            if (this.totalDy > ((float) this.scrollValue)) {
                                this.totalDy = 0.0f;
                                ChatActivity.this.showPagedownButton(true, true);
                                ChatActivity.this.pagedownButtonShowedByScroll = true;
                            }
                        }
                    } else if (ChatActivity.this.pagedownButtonShowedByScroll && ChatActivity.this.pagedownButton.getTag() != null) {
                        this.totalDy += (float) dy;
                        if (this.totalDy < ((float) (-this.scrollValue))) {
                            ChatActivity.this.showPagedownButton(false, true);
                            this.totalDy = 0.0f;
                        }
                    }
                }
                ChatActivity.this.updateMessagesVisisblePart();
            }
        });
        if (this.scrollToPositionOnRecreate != -1) {
            this.chatLayoutManager.scrollToPositionWithOffset(this.scrollToPositionOnRecreate, this.scrollToOffsetOnRecreate);
            this.scrollToPositionOnRecreate = -1;
        }
        this.progressView = new FrameLayout(context);
        this.progressView.setVisibility(4);
        this.contentView.addView(this.progressView, LayoutHelper.createFrame(-1, -1, 51));
        this.progressView2 = new View(context);
        this.progressView2.setBackgroundResource(R.drawable.system_loader);
        this.progressView2.getBackground().setColorFilter(Theme.colorFilter);
        this.progressView.addView(this.progressView2, LayoutHelper.createFrame(36, 36, 17));
        this.progressBar = new RadialProgressView(context);
        this.progressBar.setSize(AndroidUtilities.dp(28.0f));
        this.progressBar.setProgressColor(Theme.getColor(Theme.key_chat_serviceText));
        this.progressView.addView(this.progressBar, LayoutHelper.createFrame(32, 32, 17));
        this.floatingDateView = new ChatActionCell(context);
        this.floatingDateView.setAlpha(0.0f);
        this.contentView.addView(this.floatingDateView, LayoutHelper.createFrame(-2, -2.0f, 49, 0.0f, 4.0f, 0.0f, 0.0f));
        this.floatingDateView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ChatActivity.this.floatingDateView.getAlpha() != 0.0f) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(((long) ChatActivity.this.floatingDateView.getCustomDate()) * 1000);
                    int year = calendar.get(1);
                    int monthOfYear = calendar.get(2);
                    int dayOfMonth = calendar.get(5);
                    calendar.clear();
                    calendar.set(year, monthOfYear, dayOfMonth);
                    ChatActivity.this.jumpToDate((int) (calendar.getTime().getTime() / 1000));
                }
            }
        });
        if (ChatObject.isChannel(this.currentChat)) {
            this.pinnedMessageView = new FrameLayout(context);
            this.pinnedMessageView.setTag(Integer.valueOf(1));
            this.pinnedMessageView.setTranslationY((float) (-AndroidUtilities.dp(50.0f)));
            this.pinnedMessageView.setVisibility(8);
            this.pinnedMessageView.setBackgroundResource(R.drawable.blockpanel);
            this.pinnedMessageView.getBackground().setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_topPanelBackground), Mode.MULTIPLY));
            this.contentView.addView(this.pinnedMessageView, LayoutHelper.createFrame(-1, 50, 51));
            this.pinnedMessageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    ChatActivity.this.scrollToMessageId(ChatActivity.this.info.pinned_msg_id, 0, true, 0, false);
                }
            });
            this.pinnedLineView = new View(context);
            this.pinnedLineView.setBackgroundColor(Theme.getColor(Theme.key_chat_topPanelLine));
            this.pinnedMessageView.addView(this.pinnedLineView, LayoutHelper.createFrame(2, 32.0f, 51, 8.0f, 8.0f, 0.0f, 0.0f));
            this.pinnedMessageImageView = new BackupImageView(context);
            this.pinnedMessageView.addView(this.pinnedMessageImageView, LayoutHelper.createFrame(32, 32.0f, 51, 17.0f, 8.0f, 0.0f, 0.0f));
            this.pinnedMessageNameTextView = new SimpleTextView(context);
            this.pinnedMessageNameTextView.setTextSize(14);
            this.pinnedMessageNameTextView.setTextColor(Theme.getColor(Theme.key_chat_topPanelTitle));
            this.pinnedMessageNameTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            this.pinnedMessageView.addView(this.pinnedMessageNameTextView, LayoutHelper.createFrame(-1, (float) AndroidUtilities.dp(RadialCountdown.TEXT_SIZE_SP), 51, RadialCountdown.TEXT_SIZE_SP, 7.3f, 52.0f, 0.0f));
            this.pinnedMessageTextView = new SimpleTextView(context);
            this.pinnedMessageTextView.setTextSize(14);
            this.pinnedMessageTextView.setTextColor(Theme.getColor(Theme.key_chat_topPanelMessage));
            this.pinnedMessageView.addView(this.pinnedMessageTextView, LayoutHelper.createFrame(-1, (float) AndroidUtilities.dp(RadialCountdown.TEXT_SIZE_SP), 51, RadialCountdown.TEXT_SIZE_SP, 25.3f, 52.0f, 0.0f));
            this.closePinned = new ImageView(context);
            this.closePinned.setImageResource(R.drawable.miniplayer_close);
            this.closePinned.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_topPanelClose), Mode.MULTIPLY));
            this.closePinned.setScaleType(ScaleType.CENTER);
            this.pinnedMessageView.addView(this.closePinned, LayoutHelper.createFrame(48, 48, 53));
            this.closePinned.setOnClickListener(new View.OnClickListener() {

                class C59091 implements OnClickListener {
                    C59091() {
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        MessagesController.getInstance().pinChannelMessage(ChatActivity.this.currentChat, 0, false);
                    }
                }

                public void onClick(View v) {
                    if (ChatActivity.this.getParentActivity() != null) {
                        if (ChatActivity.this.currentChat.creator || (ChatActivity.this.currentChat.admin_rights != null && ((ChatActivity.this.currentChat.megagroup && ChatActivity.this.currentChat.admin_rights.pin_messages) || (!ChatActivity.this.currentChat.megagroup && ChatActivity.this.currentChat.admin_rights.edit_messages)))) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ChatActivity.this.getParentActivity());
                            builder.setMessage(LocaleController.getString("UnpinMessageAlert", R.string.UnpinMessageAlert));
                            builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new C59091());
                            builder.setTitle(ApplicationLoader.getConfig().getAppName());
                            builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
                            ChatActivity.this.showDialog(builder.create());
                            return;
                        }
                        ApplicationLoader.applicationContext.getSharedPreferences("Notifications", 0).edit().putInt("pin_" + ChatActivity.this.dialog_id, ChatActivity.this.info.pinned_msg_id).commit();
                        ChatActivity.this.updatePinnedMessageView(true);
                    }
                }
            });
        }
        this.reportSpamView = new LinearLayout(context);
        this.reportSpamView.setTag(Integer.valueOf(1));
        this.reportSpamView.setTranslationY((float) (-AndroidUtilities.dp(50.0f)));
        this.reportSpamView.setVisibility(8);
        this.reportSpamView.setBackgroundResource(R.drawable.blockpanel);
        this.reportSpamView.getBackground().setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_topPanelBackground), Mode.MULTIPLY));
        this.contentView.addView(this.reportSpamView, LayoutHelper.createFrame(-1, 50, 51));
        this.addToContactsButton = new TextView(context);
        this.addToContactsButton.setTextColor(Theme.getColor(Theme.key_chat_addContact));
        this.addToContactsButton.setVisibility(8);
        this.addToContactsButton.setTextSize(1, 14.0f);
        this.addToContactsButton.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.addToContactsButton.setSingleLine(true);
        this.addToContactsButton.setMaxLines(1);
        this.addToContactsButton.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
        this.addToContactsButton.setGravity(17);
        this.addToContactsButton.setText(LocaleController.getString("AddContactChat", R.string.AddContactChat));
        this.reportSpamView.addView(this.addToContactsButton, LayoutHelper.createLinear(-1, -1, 0.5f, 51, 0, 0, 0, AndroidUtilities.dp(1.0f)));
        this.addToContactsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putInt("user_id", ChatActivity.this.currentUser.id);
                args.putBoolean("addContact", true);
                ChatActivity.this.presentFragment(new ContactAddActivity(args));
            }
        });
        this.reportSpamContainer = new FrameLayout(context);
        this.reportSpamView.addView(this.reportSpamContainer, LayoutHelper.createLinear(-1, -1, 1.0f, 51, 0, 0, 0, AndroidUtilities.dp(1.0f)));
        this.reportSpamButton = new TextView(context);
        this.reportSpamButton.setTextColor(Theme.getColor(Theme.key_chat_reportSpam));
        this.reportSpamButton.setTextSize(1, 14.0f);
        this.reportSpamButton.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.reportSpamButton.setSingleLine(true);
        this.reportSpamButton.setMaxLines(1);
        if (this.currentChat != null) {
            this.reportSpamButton.setText(LocaleController.getString("ReportSpamAndLeave", R.string.ReportSpamAndLeave));
        } else {
            this.reportSpamButton.setText(LocaleController.getString("ReportSpam", R.string.ReportSpam));
        }
        this.reportSpamButton.setGravity(17);
        this.reportSpamButton.setPadding(AndroidUtilities.dp(50.0f), 0, AndroidUtilities.dp(50.0f), 0);
        this.reportSpamContainer.addView(this.reportSpamButton, LayoutHelper.createFrame(-1, -1, 51));
        this.reportSpamButton.setOnClickListener(new View.OnClickListener() {

            class C59101 implements OnClickListener {
                C59101() {
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    if (ChatActivity.this.currentUser != null) {
                        MessagesController.getInstance().blockUser(ChatActivity.this.currentUser.id);
                    }
                    MessagesController.getInstance().reportSpam(ChatActivity.this.dialog_id, ChatActivity.this.currentUser, ChatActivity.this.currentChat, ChatActivity.this.currentEncryptedChat);
                    ChatActivity.this.updateSpamView();
                    if (ChatActivity.this.currentChat == null) {
                        MessagesController.getInstance().deleteDialog(ChatActivity.this.dialog_id, 0);
                    } else if (ChatObject.isNotInChat(ChatActivity.this.currentChat)) {
                        MessagesController.getInstance().deleteDialog(ChatActivity.this.dialog_id, 0);
                    } else {
                        MessagesController.getInstance().deleteUserFromChat((int) (-ChatActivity.this.dialog_id), MessagesController.getInstance().getUser(Integer.valueOf(UserConfig.getClientUserId())), null);
                    }
                    ChatActivity.this.finishFragment();
                }
            }

            public void onClick(View v) {
                if (ChatActivity.this.getParentActivity() != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ChatActivity.this.getParentActivity());
                    if (ChatObject.isChannel(ChatActivity.this.currentChat) && !ChatActivity.this.currentChat.megagroup) {
                        builder.setMessage(LocaleController.getString("ReportSpamAlertChannel", R.string.ReportSpamAlertChannel));
                    } else if (ChatActivity.this.currentChat != null) {
                        builder.setMessage(LocaleController.getString("ReportSpamAlertGroup", R.string.ReportSpamAlertGroup));
                    } else {
                        builder.setMessage(LocaleController.getString("ReportSpamAlert", R.string.ReportSpamAlert));
                    }
                    builder.setTitle(ApplicationLoader.getConfig().getAppName());
                    builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new C59101());
                    builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
                    ChatActivity.this.showDialog(builder.create());
                }
            }
        });
        this.closeReportSpam = new ImageView(context);
        this.closeReportSpam.setImageResource(R.drawable.miniplayer_close);
        this.closeReportSpam.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_topPanelClose), Mode.MULTIPLY));
        this.closeReportSpam.setScaleType(ScaleType.CENTER);
        this.reportSpamContainer.addView(this.closeReportSpam, LayoutHelper.createFrame(48, 48, 53));
        this.closeReportSpam.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MessagesController.getInstance().hideReportSpam(ChatActivity.this.dialog_id, ChatActivity.this.currentUser, ChatActivity.this.currentChat);
                ChatActivity.this.updateSpamView();
            }
        });
        this.alertView = new FrameLayout(context);
        this.alertView.setTag(Integer.valueOf(1));
        this.alertView.setTranslationY((float) (-AndroidUtilities.dp(50.0f)));
        this.alertView.setVisibility(8);
        this.alertView.setBackgroundResource(R.drawable.blockpanel);
        this.alertView.getBackground().setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_topPanelBackground), Mode.MULTIPLY));
        this.contentView.addView(this.alertView, LayoutHelper.createFrame(-1, 50, 51));
        this.alertNameTextView = new TextView(context);
        this.alertNameTextView.setTextSize(1, 14.0f);
        this.alertNameTextView.setTextColor(Theme.getColor(Theme.key_chat_topPanelTitle));
        this.alertNameTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.alertNameTextView.setSingleLine(true);
        this.alertNameTextView.setEllipsize(TruncateAt.END);
        this.alertNameTextView.setMaxLines(1);
        this.alertView.addView(this.alertNameTextView, LayoutHelper.createFrame(-2, -2.0f, 51, 8.0f, 5.0f, 8.0f, 0.0f));
        this.alertTextView = new TextView(context);
        this.alertTextView.setTextSize(1, 14.0f);
        this.alertTextView.setTextColor(Theme.getColor(Theme.key_chat_topPanelMessage));
        this.alertTextView.setSingleLine(true);
        this.alertTextView.setEllipsize(TruncateAt.END);
        this.alertTextView.setMaxLines(1);
        this.alertView.addView(this.alertTextView, LayoutHelper.createFrame(-2, -2.0f, 51, 8.0f, 23.0f, 8.0f, 0.0f));
        this.pagedownButton = new FrameLayout(context);
        this.pagedownButton.setVisibility(4);
        this.contentView.addView(this.pagedownButton, LayoutHelper.createFrame(46, 59.0f, 85, 0.0f, 0.0f, 7.0f, 5.0f));
        this.pagedownButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ChatActivity.this.checkTextureViewPosition = true;
                if (ChatActivity.this.createUnreadMessageAfterId != 0) {
                    ChatActivity.this.scrollToMessageId(ChatActivity.this.createUnreadMessageAfterId, 0, false, ChatActivity.this.returnToLoadIndex, false);
                } else if (ChatActivity.this.returnToMessageId > 0) {
                    ChatActivity.this.scrollToMessageId(ChatActivity.this.returnToMessageId, 0, true, ChatActivity.this.returnToLoadIndex, false);
                } else {
                    ChatActivity.this.scrollToLastMessage(true);
                }
            }
        });
        this.mentiondownButton = new FrameLayout(context);
        this.mentiondownButton.setVisibility(4);
        this.contentView.addView(this.mentiondownButton, LayoutHelper.createFrame(46, 59.0f, 85, 0.0f, 0.0f, 7.0f, 5.0f));
        this.mentiondownButton.setOnClickListener(new View.OnClickListener() {

            class C59111 implements IntCallback {
                C59111() {
                }

                public void run(int param) {
                    if (param == 0) {
                        ChatActivity.this.hasAllMentionsLocal = false;
                        AnonymousClass29.this.loadLastUnreadMention();
                        return;
                    }
                    ChatActivity.this.scrollToMessageId(param, 0, false, 0, false);
                }
            }

            class C59132 implements RequestDelegate {
                C59132() {
                }

                public void run(final TLObject response, final TLRPC$TL_error error) {
                    AndroidUtilities.runOnUIThread(new Runnable() {
                        public void run() {
                            TLRPC$messages_Messages res = response;
                            if (error != null || res.messages.isEmpty()) {
                                if (res != null) {
                                    ChatActivity.this.newMentionsCount = res.count;
                                } else {
                                    ChatActivity.this.newMentionsCount = 0;
                                }
                                MessagesStorage.getInstance().resetMentionsCount(ChatActivity.this.dialog_id, ChatActivity.this.newMentionsCount);
                                if (ChatActivity.this.newMentionsCount == 0) {
                                    ChatActivity.this.hasAllMentionsLocal = true;
                                    ChatActivity.this.showMentiondownButton(false, true);
                                    return;
                                }
                                ChatActivity.this.mentiondownButtonCounter.setText(String.format("%d", new Object[]{Integer.valueOf(ChatActivity.this.newMentionsCount)}));
                                AnonymousClass29.this.loadLastUnreadMention();
                                return;
                            }
                            int id = ((TLRPC$Message) res.messages.get(0)).id;
                            long mid = (long) id;
                            if (ChatObject.isChannel(ChatActivity.this.currentChat)) {
                                mid |= ((long) ChatActivity.this.currentChat.id) << 32;
                            }
                            MessageObject object = (MessageObject) ChatActivity.this.messagesDict[0].get(Integer.valueOf(id));
                            MessagesStorage.getInstance().markMessageAsMention(mid);
                            if (object != null) {
                                object.messageOwner.media_unread = true;
                                object.messageOwner.mentioned = true;
                            }
                            ChatActivity.this.scrollToMessageId(id, 0, false, 0, false);
                        }
                    });
                }
            }

            private void loadLastUnreadMention() {
                if (ChatActivity.this.hasAllMentionsLocal) {
                    MessagesStorage.getInstance().getUnreadMention(ChatActivity.this.dialog_id, new C59111());
                    return;
                }
                TLRPC$TL_messages_getUnreadMentions req = new TLRPC$TL_messages_getUnreadMentions();
                req.peer = MessagesController.getInputPeer((int) ChatActivity.this.dialog_id);
                req.limit = 1;
                req.add_offset = ChatActivity.this.newMentionsCount - 1;
                ConnectionsManager.getInstance().sendRequest(req, new C59132());
            }

            public void onClick(View view) {
                loadLastUnreadMention();
            }
        });
        this.mentiondownButton.setOnLongClickListener(new OnLongClickListener() {

            class C59151 implements RequestDelegate {
                C59151() {
                }

                public void run(TLObject response, TLRPC$TL_error error) {
                }
            }

            public boolean onLongClick(View view) {
                for (int a = 0; a < ChatActivity.this.messages.size(); a++) {
                    MessageObject messageObject = (MessageObject) ChatActivity.this.messages.get(a);
                    if (messageObject.messageOwner.mentioned && !messageObject.isContentUnread()) {
                        messageObject.setContentIsRead();
                    }
                }
                ChatActivity.this.newMentionsCount = 0;
                MessagesStorage.getInstance().resetMentionsCount(ChatActivity.this.dialog_id, ChatActivity.this.newMentionsCount);
                ChatActivity.this.hasAllMentionsLocal = true;
                ChatActivity.this.showMentiondownButton(false, true);
                TLRPC$TL_messages_readMentions req = new TLRPC$TL_messages_readMentions();
                req.peer = MessagesController.getInputPeer((int) ChatActivity.this.dialog_id);
                ConnectionsManager.getInstance().sendRequest(req, new C59151());
                return true;
            }
        });
        if (!this.isBroadcast) {
            this.mentionContainer = new FrameLayout(context) {
                public void onDraw(Canvas canvas) {
                    if (ChatActivity.this.mentionListView.getChildCount() > 0) {
                        int top;
                        if (ChatActivity.this.mentionLayoutManager.getReverseLayout()) {
                            top = ChatActivity.this.mentionListViewScrollOffsetY + AndroidUtilities.dp(2.0f);
                            Theme.chat_composeShadowDrawable.setBounds(0, top + Theme.chat_composeShadowDrawable.getIntrinsicHeight(), getMeasuredWidth(), top);
                            Theme.chat_composeShadowDrawable.draw(canvas);
                            canvas.drawRect(0.0f, 0.0f, (float) getMeasuredWidth(), (float) top, Theme.chat_composeBackgroundPaint);
                            return;
                        }
                        if (ChatActivity.this.mentionsAdapter.isBotContext() && ChatActivity.this.mentionsAdapter.isMediaLayout() && ChatActivity.this.mentionsAdapter.getBotContextSwitch() == null) {
                            top = ChatActivity.this.mentionListViewScrollOffsetY - AndroidUtilities.dp(4.0f);
                        } else {
                            top = ChatActivity.this.mentionListViewScrollOffsetY - AndroidUtilities.dp(2.0f);
                        }
                        int bottom = top + Theme.chat_composeShadowDrawable.getIntrinsicHeight();
                        Theme.chat_composeShadowDrawable.setBounds(0, top, getMeasuredWidth(), bottom);
                        Theme.chat_composeShadowDrawable.draw(canvas);
                        canvas.drawRect(0.0f, (float) bottom, (float) getMeasuredWidth(), (float) getMeasuredHeight(), Theme.chat_composeBackgroundPaint);
                    }
                }

                public void requestLayout() {
                    if (!ChatActivity.this.mentionListViewIgnoreLayout) {
                        super.requestLayout();
                    }
                }
            };
            this.mentionContainer.setVisibility(8);
            this.mentionContainer.setWillNotDraw(false);
            this.contentView.addView(this.mentionContainer, LayoutHelper.createFrame(-1, 110, 83));
            this.mentionListView = new RecyclerListView(context) {
                private int lastHeight;
                private int lastWidth;

                public boolean onInterceptTouchEvent(MotionEvent event) {
                    if (ChatActivity.this.mentionLayoutManager.getReverseLayout()) {
                        if (!(ChatActivity.this.mentionListViewIsScrolling || ChatActivity.this.mentionListViewScrollOffsetY == 0 || event.getY() <= ((float) ChatActivity.this.mentionListViewScrollOffsetY))) {
                            return false;
                        }
                    } else if (!(ChatActivity.this.mentionListViewIsScrolling || ChatActivity.this.mentionListViewScrollOffsetY == 0 || event.getY() >= ((float) ChatActivity.this.mentionListViewScrollOffsetY))) {
                        return false;
                    }
                    boolean result = StickerPreviewViewer.getInstance().onInterceptTouchEvent(event, ChatActivity.this.mentionListView, 0, null);
                    if (super.onInterceptTouchEvent(event) || result) {
                        return true;
                    }
                    return false;
                }

                public boolean onTouchEvent(MotionEvent event) {
                    if (ChatActivity.this.mentionLayoutManager.getReverseLayout()) {
                        if (!(ChatActivity.this.mentionListViewIsScrolling || ChatActivity.this.mentionListViewScrollOffsetY == 0 || event.getY() <= ((float) ChatActivity.this.mentionListViewScrollOffsetY))) {
                            return false;
                        }
                    } else if (!(ChatActivity.this.mentionListViewIsScrolling || ChatActivity.this.mentionListViewScrollOffsetY == 0 || event.getY() >= ((float) ChatActivity.this.mentionListViewScrollOffsetY))) {
                        return false;
                    }
                    return super.onTouchEvent(event);
                }

                public void requestLayout() {
                    if (!ChatActivity.this.mentionListViewIgnoreLayout) {
                        super.requestLayout();
                    }
                }

                protected void onLayout(boolean changed, int l, int t, int r, int b) {
                    int width = r - l;
                    int height = b - t;
                    int newPosition = -1;
                    int newTop = 0;
                    if (!(ChatActivity.this.mentionLayoutManager.getReverseLayout() || ChatActivity.this.mentionListView == null || ChatActivity.this.mentionListViewLastViewPosition < 0 || width != this.lastWidth || height - this.lastHeight == 0)) {
                        newPosition = ChatActivity.this.mentionListViewLastViewPosition;
                        newTop = ((ChatActivity.this.mentionListViewLastViewTop + height) - this.lastHeight) - getPaddingTop();
                    }
                    super.onLayout(changed, l, t, r, b);
                    if (newPosition != -1) {
                        ChatActivity.this.mentionListViewIgnoreLayout = true;
                        if (ChatActivity.this.mentionsAdapter.isBotContext() && ChatActivity.this.mentionsAdapter.isMediaLayout()) {
                            ChatActivity.this.mentionGridLayoutManager.scrollToPositionWithOffset(newPosition, newTop);
                        } else {
                            ChatActivity.this.mentionLayoutManager.scrollToPositionWithOffset(newPosition, newTop);
                        }
                        super.onLayout(false, l, t, r, b);
                        ChatActivity.this.mentionListViewIgnoreLayout = false;
                    }
                    this.lastHeight = height;
                    this.lastWidth = width;
                    ChatActivity.this.mentionListViewUpdateLayout();
                }
            };
            this.mentionListView.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    return StickerPreviewViewer.getInstance().onTouch(event, ChatActivity.this.mentionListView, 0, ChatActivity.this.mentionsOnItemClickListener, null);
                }
            });
            this.mentionListView.setTag(Integer.valueOf(2));
            this.mentionLayoutManager = new LinearLayoutManager(context) {
                public boolean supportsPredictiveItemAnimations() {
                    return false;
                }
            };
            this.mentionLayoutManager.setOrientation(1);
            this.mentionGridLayoutManager = new ExtendedGridLayoutManager(context, 100) {
                private Size size = new Size();

                protected Size getSizeForItem(int i) {
                    float f = 100.0f;
                    if (ChatActivity.this.mentionsAdapter.getBotContextSwitch() != null) {
                        i++;
                    }
                    TLRPC$BotInlineResult object = ChatActivity.this.mentionsAdapter.getItem(i);
                    if (object instanceof TLRPC$BotInlineResult) {
                        TLRPC$BotInlineResult inlineResult = object;
                        if (inlineResult.document != null) {
                            float f2;
                            Size size = this.size;
                            if (inlineResult.document.thumb != null) {
                                f2 = (float) inlineResult.document.thumb.f12637w;
                            } else {
                                f2 = 100.0f;
                            }
                            size.width = f2;
                            Size size2 = this.size;
                            if (inlineResult.document.thumb != null) {
                                f = (float) inlineResult.document.thumb.f12636h;
                            }
                            size2.height = f;
                            for (int b = 0; b < inlineResult.document.attributes.size(); b++) {
                                TLRPC$DocumentAttribute attribute = (TLRPC$DocumentAttribute) inlineResult.document.attributes.get(b);
                                if ((attribute instanceof TLRPC$TL_documentAttributeImageSize) || (attribute instanceof TLRPC$TL_documentAttributeVideo)) {
                                    this.size.width = (float) attribute.f12630w;
                                    this.size.height = (float) attribute.f12629h;
                                    break;
                                }
                            }
                        } else {
                            this.size.width = (float) inlineResult.f12625w;
                            this.size.height = (float) inlineResult.f12624h;
                        }
                    }
                    return this.size;
                }

                protected int getFlowItemCount() {
                    if (ChatActivity.this.mentionsAdapter.getBotContextSwitch() != null) {
                        return getItemCount() - 1;
                    }
                    return super.getFlowItemCount();
                }
            };
            this.mentionGridLayoutManager.setSpanSizeLookup(new SpanSizeLookup() {
                public int getSpanSize(int position) {
                    if (ChatActivity.this.mentionsAdapter.getItem(position) instanceof TLRPC$TL_inlineBotSwitchPM) {
                        return 100;
                    }
                    if (ChatActivity.this.mentionsAdapter.getBotContextSwitch() != null) {
                        position--;
                    }
                    return ChatActivity.this.mentionGridLayoutManager.getSpanSizeForItem(position);
                }
            });
            this.mentionListView.addItemDecoration(new ItemDecoration() {
                public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
                    int i = 0;
                    outRect.left = 0;
                    outRect.right = 0;
                    outRect.top = 0;
                    outRect.bottom = 0;
                    if (parent.getLayoutManager() == ChatActivity.this.mentionGridLayoutManager) {
                        int position = parent.getChildAdapterPosition(view);
                        if (ChatActivity.this.mentionsAdapter.getBotContextSwitch() == null) {
                            outRect.top = AndroidUtilities.dp(2.0f);
                        } else if (position != 0) {
                            position--;
                            if (!ChatActivity.this.mentionGridLayoutManager.isFirstRow(position)) {
                                outRect.top = AndroidUtilities.dp(2.0f);
                            }
                        } else {
                            return;
                        }
                        if (!ChatActivity.this.mentionGridLayoutManager.isLastInRow(position)) {
                            i = AndroidUtilities.dp(2.0f);
                        }
                        outRect.right = i;
                    }
                }
            });
            this.mentionListView.setItemAnimator(null);
            this.mentionListView.setLayoutAnimation(null);
            this.mentionListView.setClipToPadding(false);
            this.mentionListView.setLayoutManager(this.mentionLayoutManager);
            this.mentionListView.setOverScrollMode(2);
            this.mentionContainer.addView(this.mentionListView, LayoutHelper.createFrame(-1, -1.0f));
            recyclerListView = this.mentionListView;
            chatActivityAdapter = new MentionsAdapter(context, false, this.dialog_id, new MentionsAdapterDelegate() {

                class C59161 extends AnimatorListenerAdapter {
                    C59161() {
                    }

                    public void onAnimationEnd(Animator animation) {
                        if (ChatActivity.this.mentionListAnimation != null && ChatActivity.this.mentionListAnimation.equals(animation)) {
                            ChatActivity.this.mentionListAnimation = null;
                        }
                    }

                    public void onAnimationCancel(Animator animation) {
                        if (ChatActivity.this.mentionListAnimation != null && ChatActivity.this.mentionListAnimation.equals(animation)) {
                            ChatActivity.this.mentionListAnimation = null;
                        }
                    }
                }

                class C59172 extends AnimatorListenerAdapter {
                    C59172() {
                    }

                    public void onAnimationEnd(Animator animation) {
                        if (ChatActivity.this.mentionListAnimation != null && ChatActivity.this.mentionListAnimation.equals(animation)) {
                            ChatActivity.this.mentionContainer.setVisibility(8);
                            ChatActivity.this.mentionContainer.setTag(null);
                            ChatActivity.this.mentionListAnimation = null;
                        }
                    }

                    public void onAnimationCancel(Animator animation) {
                        if (ChatActivity.this.mentionListAnimation != null && ChatActivity.this.mentionListAnimation.equals(animation)) {
                            ChatActivity.this.mentionListAnimation = null;
                        }
                    }
                }

                public void needChangePanelVisibility(boolean show) {
                    if (ChatActivity.this.mentionsAdapter.isBotContext() && ChatActivity.this.mentionsAdapter.isMediaLayout()) {
                        ChatActivity.this.mentionListView.setLayoutManager(ChatActivity.this.mentionGridLayoutManager);
                    } else {
                        ChatActivity.this.mentionListView.setLayoutManager(ChatActivity.this.mentionLayoutManager);
                    }
                    if (show && ChatActivity.this.bottomOverlay.getVisibility() == 0) {
                        show = false;
                    }
                    if (show) {
                        if (ChatActivity.this.mentionListAnimation != null) {
                            ChatActivity.this.mentionListAnimation.cancel();
                            ChatActivity.this.mentionListAnimation = null;
                        }
                        if (ChatActivity.this.mentionContainer.getVisibility() == 0) {
                            ChatActivity.this.mentionContainer.setAlpha(1.0f);
                            return;
                        }
                        if (ChatActivity.this.mentionsAdapter.isBotContext() && ChatActivity.this.mentionsAdapter.isMediaLayout()) {
                            ChatActivity.this.mentionGridLayoutManager.scrollToPositionWithOffset(0, 10000);
                        } else {
                            ChatActivity.this.mentionLayoutManager.scrollToPositionWithOffset(0, 10000);
                        }
                        if (ChatActivity.this.allowStickersPanel && (!ChatActivity.this.mentionsAdapter.isBotContext() || ChatActivity.this.allowContextBotPanel || ChatActivity.this.allowContextBotPanelSecond)) {
                            if (ChatActivity.this.currentEncryptedChat != null && ChatActivity.this.mentionsAdapter.isBotContext()) {
                                SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
                                if (!preferences.getBoolean("secretbot", false)) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ChatActivity.this.getParentActivity());
                                    builder.setTitle(ApplicationLoader.getConfig().getAppName());
                                    builder.setMessage(LocaleController.getString("SecretChatContextBotAlert", R.string.SecretChatContextBotAlert));
                                    builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), null);
                                    ChatActivity.this.showDialog(builder.create());
                                    preferences.edit().putBoolean("secretbot", true).commit();
                                }
                            }
                            ChatActivity.this.mentionContainer.setVisibility(0);
                            ChatActivity.this.mentionContainer.setTag(null);
                            ChatActivity.this.mentionListAnimation = new AnimatorSet();
                            ChatActivity.this.mentionListAnimation.playTogether(new Animator[]{ObjectAnimator.ofFloat(ChatActivity.this.mentionContainer, "alpha", new float[]{0.0f, 1.0f})});
                            ChatActivity.this.mentionListAnimation.addListener(new C59161());
                            ChatActivity.this.mentionListAnimation.setDuration(200);
                            ChatActivity.this.mentionListAnimation.start();
                            return;
                        }
                        ChatActivity.this.mentionContainer.setAlpha(1.0f);
                        ChatActivity.this.mentionContainer.setVisibility(4);
                        return;
                    }
                    if (ChatActivity.this.mentionListAnimation != null) {
                        ChatActivity.this.mentionListAnimation.cancel();
                        ChatActivity.this.mentionListAnimation = null;
                    }
                    if (ChatActivity.this.mentionContainer.getVisibility() == 8) {
                        return;
                    }
                    if (ChatActivity.this.allowStickersPanel) {
                        ChatActivity.this.mentionListAnimation = new AnimatorSet();
                        AnimatorSet access$12300 = ChatActivity.this.mentionListAnimation;
                        Animator[] animatorArr = new Animator[1];
                        animatorArr[0] = ObjectAnimator.ofFloat(ChatActivity.this.mentionContainer, "alpha", new float[]{0.0f});
                        access$12300.playTogether(animatorArr);
                        ChatActivity.this.mentionListAnimation.addListener(new C59172());
                        ChatActivity.this.mentionListAnimation.setDuration(200);
                        ChatActivity.this.mentionListAnimation.start();
                        return;
                    }
                    ChatActivity.this.mentionContainer.setTag(null);
                    ChatActivity.this.mentionContainer.setVisibility(8);
                }

                public void onContextSearch(boolean searching) {
                    if (ChatActivity.this.chatActivityEnterView != null) {
                        ChatActivity.this.chatActivityEnterView.setCaption(ChatActivity.this.mentionsAdapter.getBotCaption());
                        ChatActivity.this.chatActivityEnterView.showContextProgress(searching);
                    }
                }

                public void onContextClick(TLRPC$BotInlineResult result) {
                    if (ChatActivity.this.getParentActivity() != null && result.content_url != null) {
                        if (result.type.equals("video") || result.type.equals("web_player_video")) {
                            EmbedBottomSheet.show(ChatActivity.this.getParentActivity(), result.title != null ? result.title : "", result.description, result.content_url, result.content_url, result.f12625w, result.f12624h);
                        } else {
                            Browser.openUrl(ChatActivity.this.getParentActivity(), result.content_url);
                        }
                    }
                }
            });
            this.mentionsAdapter = chatActivityAdapter;
            recyclerListView.setAdapter(chatActivityAdapter);
            if (!ChatObject.isChannel(this.currentChat) || (this.currentChat != null && this.currentChat.megagroup)) {
                this.mentionsAdapter.setBotInfo(this.botInfo);
            }
            this.mentionsAdapter.setParentFragment(this);
            this.mentionsAdapter.setChatInfo(this.info);
            this.mentionsAdapter.setNeedUsernames(this.currentChat != null);
            MentionsAdapter mentionsAdapter = this.mentionsAdapter;
            z = this.currentEncryptedChat == null || AndroidUtilities.getPeerLayerVersion(this.currentEncryptedChat.layer) >= 46;
            mentionsAdapter.setNeedBotContext(z);
            this.mentionsAdapter.setBotsCount(this.currentChat != null ? this.botsCount : 1);
            recyclerListView = this.mentionListView;
            OnItemClickListener anonymousClass39 = new OnItemClickListener() {
                public void onItemClick(View view, int position) {
                    if (!ChatActivity.this.mentionsAdapter.isBannedInline()) {
                        TLObject object = ChatActivity.this.mentionsAdapter.getItem(position);
                        int start = ChatActivity.this.mentionsAdapter.getResultStartPosition();
                        int len = ChatActivity.this.mentionsAdapter.getResultLength();
                        if (object instanceof User) {
                            Spannable spannableString;
                            if (ChatActivity.this.searchingForUser && ChatActivity.this.searchContainer.getVisibility() == 0) {
                                ChatActivity.this.searchingUserMessages = (User) object;
                                if (ChatActivity.this.searchingUserMessages != null) {
                                    String name = ChatActivity.this.searchingUserMessages.first_name;
                                    if (TextUtils.isEmpty(name)) {
                                        name = ChatActivity.this.searchingUserMessages.last_name;
                                    }
                                    ChatActivity.this.searchingForUser = false;
                                    String from = LocaleController.getString("SearchFrom", R.string.SearchFrom);
                                    spannableString = new SpannableString(from + " " + name);
                                    spannableString.setSpan(new ForegroundColorSpan(Theme.getColor(Theme.key_actionBarDefaultSubtitle)), from.length() + 1, spannableString.length(), 33);
                                    ChatActivity.this.searchItem.setSearchFieldCaption(spannableString);
                                    ChatActivity.this.mentionsAdapter.searchUsernameOrHashtag(null, 0, null, false);
                                    ChatActivity.this.searchItem.getSearchField().setHint(null);
                                    ChatActivity.this.searchItem.clearSearchText();
                                    MessagesSearchQuery.searchMessagesInChat("", ChatActivity.this.dialog_id, ChatActivity.this.mergeDialogId, ChatActivity.this.classGuid, 0, ChatActivity.this.searchingUserMessages);
                                    return;
                                }
                                return;
                            }
                            User user = (User) object;
                            if (user == null) {
                                return;
                            }
                            if (user.username != null) {
                                ChatActivity.this.chatActivityEnterView.replaceWithText(start, len, "@" + user.username + " ", false);
                                return;
                            }
                            spannableString = new SpannableString(UserObject.getFirstName(user) + " ");
                            spannableString.setSpan(new URLSpanUserMention("" + user.id, true), 0, spannableString.length(), 33);
                            ChatActivity.this.chatActivityEnterView.replaceWithText(start, len, spannableString, false);
                        } else if (object instanceof String) {
                            if (ChatActivity.this.mentionsAdapter.isBotCommands()) {
                                SendMessagesHelper.getInstance().sendMessage((String) object, ChatActivity.this.dialog_id, ChatActivity.this.replyingMessageObject, null, false, null, null, null);
                                ChatActivity.this.chatActivityEnterView.setFieldText("");
                                ChatActivity.this.showReplyPanel(false, null, null, null, false);
                                return;
                            }
                            ChatActivity.this.chatActivityEnterView.replaceWithText(start, len, object + " ", false);
                        } else if (object instanceof TLRPC$BotInlineResult) {
                            if (ChatActivity.this.chatActivityEnterView.getFieldText() != null) {
                                TLRPC$BotInlineResult result = (TLRPC$BotInlineResult) object;
                                if ((!result.type.equals("photo") || (result.photo == null && result.content_url == null)) && ((!result.type.equals("gif") || (result.document == null && result.content_url == null)) && (!result.type.equals("video") || result.document == null))) {
                                    ChatActivity.this.sendBotInlineResult(result);
                                    return;
                                }
                                ArrayList<Object> arrayList = ChatActivity.this.botContextResults = new ArrayList(ChatActivity.this.mentionsAdapter.getSearchResultBotContext());
                                PhotoViewer.getInstance().setParentActivity(ChatActivity.this.getParentActivity());
                                PhotoViewer.getInstance().openPhotoForSelect(arrayList, ChatActivity.this.mentionsAdapter.getItemPosition(position), 3, ChatActivity.this.botContextProvider, null);
                            }
                        } else if (object instanceof TLRPC$TL_inlineBotSwitchPM) {
                            ChatActivity.this.processInlineBotContextPM((TLRPC$TL_inlineBotSwitchPM) object);
                        } else if (object instanceof EmojiSuggestion) {
                            String code = ((EmojiSuggestion) object).emoji;
                            ChatActivity.this.chatActivityEnterView.addEmojiToRecent(code);
                            ChatActivity.this.chatActivityEnterView.replaceWithText(start, len, code, true);
                        }
                    }
                }
            };
            this.mentionsOnItemClickListener = anonymousClass39;
            recyclerListView.setOnItemClickListener(anonymousClass39);
            this.mentionListView.setOnItemLongClickListener(new OnItemLongClickListener() {

                class C59191 implements OnClickListener {
                    C59191() {
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        ChatActivity.this.mentionsAdapter.clearRecentHashtags();
                    }
                }

                public boolean onItemClick(View view, int position) {
                    boolean z = false;
                    if (ChatActivity.this.getParentActivity() == null || !ChatActivity.this.mentionsAdapter.isLongClickEnabled()) {
                        return false;
                    }
                    Object object = ChatActivity.this.mentionsAdapter.getItem(position);
                    if (!(object instanceof String)) {
                        return false;
                    }
                    if (!ChatActivity.this.mentionsAdapter.isBotCommands()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ChatActivity.this.getParentActivity());
                        builder.setTitle(ApplicationLoader.getConfig().getAppName());
                        builder.setMessage(LocaleController.getString("ClearSearch", R.string.ClearSearch));
                        builder.setPositiveButton(LocaleController.getString("ClearButton", R.string.ClearButton).toUpperCase(), new C59191());
                        builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
                        ChatActivity.this.showDialog(builder.create());
                        return true;
                    } else if (!URLSpanBotCommand.enabled) {
                        return false;
                    } else {
                        ChatActivity.this.chatActivityEnterView.setFieldText("");
                        ChatActivityEnterView chatActivityEnterView = ChatActivity.this.chatActivityEnterView;
                        String str = (String) object;
                        if (ChatActivity.this.currentChat != null && ChatActivity.this.currentChat.megagroup) {
                            z = true;
                        }
                        chatActivityEnterView.setCommand(null, str, true, z);
                        return true;
                    }
                }
            });
            this.mentionListView.setOnScrollListener(new OnScrollListener() {
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    boolean z = true;
                    ChatActivity chatActivity = ChatActivity.this;
                    if (newState != 1) {
                        z = false;
                    }
                    chatActivity.mentionListViewIsScrolling = z;
                }

                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    int lastVisibleItem;
                    int visibleItemCount;
                    if (ChatActivity.this.mentionsAdapter.isBotContext() && ChatActivity.this.mentionsAdapter.isMediaLayout()) {
                        lastVisibleItem = ChatActivity.this.mentionGridLayoutManager.findLastVisibleItemPosition();
                    } else {
                        lastVisibleItem = ChatActivity.this.mentionLayoutManager.findLastVisibleItemPosition();
                    }
                    if (lastVisibleItem == -1) {
                        visibleItemCount = 0;
                    } else {
                        visibleItemCount = lastVisibleItem;
                    }
                    if (visibleItemCount > 0 && lastVisibleItem > ChatActivity.this.mentionsAdapter.getItemCount() - 5) {
                        ChatActivity.this.mentionsAdapter.searchForContextBotForNextOffset();
                    }
                    ChatActivity.this.mentionListViewUpdateLayout();
                }
            });
        }
        this.pagedownButtonImage = new ImageView(context);
        this.pagedownButtonImage.setImageResource(R.drawable.pagedown);
        this.pagedownButtonImage.setScaleType(ScaleType.CENTER);
        this.pagedownButtonImage.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_goDownButtonIcon), Mode.MULTIPLY));
        this.pagedownButtonImage.setPadding(0, AndroidUtilities.dp(2.0f), 0, 0);
        Drawable drawable = Theme.createCircleDrawable(AndroidUtilities.dp(42.0f), Theme.getColor(Theme.key_chat_goDownButton));
        Drawable shadowDrawable = context.getResources().getDrawable(R.drawable.pagedown_shadow).mutate();
        shadowDrawable.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_goDownButtonShadow), Mode.MULTIPLY));
        Drawable combinedDrawable = new CombinedDrawable(shadowDrawable, drawable, 0, 0);
        combinedDrawable.setIconSize(AndroidUtilities.dp(42.0f), AndroidUtilities.dp(42.0f));
        this.pagedownButtonImage.setBackgroundDrawable(combinedDrawable);
        this.pagedownButton.addView(this.pagedownButtonImage, LayoutHelper.createFrame(46, 46, 83));
        this.pagedownButtonCounter = new TextView(context);
        this.pagedownButtonCounter.setVisibility(4);
        this.pagedownButtonCounter.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.pagedownButtonCounter.setTextSize(1, 13.0f);
        this.pagedownButtonCounter.setTextColor(Theme.getColor(Theme.key_chat_goDownButtonCounter));
        this.pagedownButtonCounter.setGravity(17);
        this.pagedownButtonCounter.setBackgroundDrawable(Theme.createRoundRectDrawable(AndroidUtilities.dp(11.5f), Theme.getColor(Theme.key_chat_goDownButtonCounterBackground)));
        this.pagedownButtonCounter.setMinWidth(AndroidUtilities.dp(23.0f));
        this.pagedownButtonCounter.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(1.0f));
        this.pagedownButton.addView(this.pagedownButtonCounter, LayoutHelper.createFrame(-2, 23, 49));
        this.mentiondownButtonImage = new ImageView(context);
        this.mentiondownButtonImage.setImageResource(R.drawable.mentionbutton);
        this.mentiondownButtonImage.setScaleType(ScaleType.CENTER);
        this.mentiondownButtonImage.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_goDownButtonIcon), Mode.MULTIPLY));
        this.mentiondownButtonImage.setPadding(0, AndroidUtilities.dp(2.0f), 0, 0);
        drawable = Theme.createCircleDrawable(AndroidUtilities.dp(42.0f), Theme.getColor(Theme.key_chat_goDownButton));
        shadowDrawable = context.getResources().getDrawable(R.drawable.pagedown_shadow).mutate();
        shadowDrawable.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_goDownButtonShadow), Mode.MULTIPLY));
        combinedDrawable = new CombinedDrawable(shadowDrawable, drawable, 0, 0);
        combinedDrawable.setIconSize(AndroidUtilities.dp(42.0f), AndroidUtilities.dp(42.0f));
        this.mentiondownButtonImage.setBackgroundDrawable(combinedDrawable);
        this.mentiondownButton.addView(this.mentiondownButtonImage, LayoutHelper.createFrame(46, 46, 83));
        this.mentiondownButtonCounter = new TextView(context);
        this.mentiondownButtonCounter.setVisibility(4);
        this.mentiondownButtonCounter.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.mentiondownButtonCounter.setTextSize(1, 13.0f);
        this.mentiondownButtonCounter.setTextColor(Theme.getColor(Theme.key_chat_goDownButtonCounter));
        this.mentiondownButtonCounter.setGravity(17);
        this.mentiondownButtonCounter.setBackgroundDrawable(Theme.createRoundRectDrawable(AndroidUtilities.dp(11.5f), Theme.getColor(Theme.key_chat_goDownButtonCounterBackground)));
        this.mentiondownButtonCounter.setMinWidth(AndroidUtilities.dp(23.0f));
        this.mentiondownButtonCounter.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(1.0f));
        this.mentiondownButton.addView(this.mentiondownButtonCounter, LayoutHelper.createFrame(-2, 23, 49));
        if (!AndroidUtilities.isTablet() || AndroidUtilities.isSmallTablet()) {
            SizeNotifierFrameLayout sizeNotifierFrameLayout = this.contentView;
            fragmentContextView = new FragmentContextView(context, this, true);
            this.fragmentLocationContextView = fragmentContextView;
            sizeNotifierFrameLayout.addView(fragmentContextView, LayoutHelper.createFrame(-1, 39.0f, 51, 0.0f, -36.0f, 0.0f, 0.0f));
            sizeNotifierFrameLayout = this.contentView;
            fragmentContextView = new FragmentContextView(context, this, false);
            this.fragmentContextView = fragmentContextView;
            sizeNotifierFrameLayout.addView(fragmentContextView, LayoutHelper.createFrame(-1, 39.0f, 51, 0.0f, -36.0f, 0.0f, 0.0f));
            this.fragmentContextView.setAdditionalContextView(this.fragmentLocationContextView);
            this.fragmentLocationContextView.setAdditionalContextView(this.fragmentContextView);
        }
        this.contentView.addView(this.actionBar);
        this.overlayView = new View(context);
        this.overlayView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 0) {
                    ChatActivity.this.checkRecordLocked();
                }
                ChatActivity.this.overlayView.getParent().requestDisallowInterceptTouchEvent(true);
                return true;
            }
        });
        this.contentView.addView(this.overlayView, LayoutHelper.createFrame(-1, -1, 51));
        this.overlayView.setVisibility(8);
        this.instantCameraView = new InstantCameraView(context, this);
        this.contentView.addView(this.instantCameraView, LayoutHelper.createFrame(-1, -1, 51));
        this.chatActivityEnterView = new ChatActivityEnterView(getParentActivity(), this.contentView, this, true);
        this.chatActivityEnterView.setDialogId(this.dialog_id);
        this.chatActivityEnterView.setId(1000);
        this.chatActivityEnterView.setBotsCount(this.botsCount, this.hasBotsCommands);
        ChatActivityEnterView chatActivityEnterView = this.chatActivityEnterView;
        z = this.currentEncryptedChat == null || AndroidUtilities.getPeerLayerVersion(this.currentEncryptedChat.layer) >= 23;
        if (this.currentEncryptedChat == null || AndroidUtilities.getPeerLayerVersion(this.currentEncryptedChat.layer) >= 46) {
            z2 = true;
        } else {
            z2 = false;
        }
        chatActivityEnterView.setAllowStickersAndGifs(z, z2);
        this.contentView.addView(this.chatActivityEnterView, this.contentView.getChildCount() - 1, LayoutHelper.createFrame(-1, -2, 83));
        this.chatActivityEnterView.setDelegate(new ChatActivityEnterViewDelegate() {
            public void onMessageSend(CharSequence message) {
                ChatActivity.this.moveScrollToLastMessage();
                ChatActivity.this.showReplyPanel(false, null, null, null, false);
                if (ChatActivity.this.mentionsAdapter != null) {
                    ChatActivity.this.mentionsAdapter.addHashtagsFromMessage(message);
                }
            }

            public void onSwitchRecordMode(boolean video) {
                ChatActivity.this.showVoiceHint(false, video);
            }

            public void onPreAudioVideoRecord() {
                ChatActivity.this.showVoiceHint(true, false);
            }

            public void onTextChanged(final CharSequence text, boolean bigChange) {
                if (ChatActivity.this.startReplyOnTextChange && text.length() > 0) {
                    ChatActivity.this.actionBar.getActionBarMenuOnItemClick().onItemClick(19);
                    ChatActivity.this.startReplyOnTextChange = false;
                }
                MediaController instance = MediaController.getInstance();
                boolean z = !TextUtils.isEmpty(text) || ChatActivity.this.chatActivityEnterView.isEditingMessage();
                instance.setInputFieldHasText(z);
                if (!(ChatActivity.this.stickersAdapter == null || ChatActivity.this.chatActivityEnterView.isEditingMessage() || !ChatObject.canSendStickers(ChatActivity.this.currentChat))) {
                    ChatActivity.this.stickersAdapter.loadStikersForEmoji(text);
                }
                if (ChatActivity.this.mentionsAdapter != null) {
                    ChatActivity.this.mentionsAdapter.searchUsernameOrHashtag(text.toString(), ChatActivity.this.chatActivityEnterView.getCursorPosition(), ChatActivity.this.messages, false);
                }
                if (ChatActivity.this.waitingForCharaterEnterRunnable != null) {
                    AndroidUtilities.cancelRunOnUIThread(ChatActivity.this.waitingForCharaterEnterRunnable);
                    ChatActivity.this.waitingForCharaterEnterRunnable = null;
                }
                if (!ChatObject.canSendEmbed(ChatActivity.this.currentChat) || !ChatActivity.this.chatActivityEnterView.isMessageWebPageSearchEnabled()) {
                    return;
                }
                if (!ChatActivity.this.chatActivityEnterView.isEditingMessage() || !ChatActivity.this.chatActivityEnterView.isEditingCaption()) {
                    if (bigChange) {
                        ChatActivity.this.searchLinks(text, true);
                        return;
                    }
                    ChatActivity.this.waitingForCharaterEnterRunnable = new Runnable() {
                        public void run() {
                            if (this == ChatActivity.this.waitingForCharaterEnterRunnable) {
                                ChatActivity.this.searchLinks(text, false);
                                ChatActivity.this.waitingForCharaterEnterRunnable = null;
                            }
                        }
                    };
                    AndroidUtilities.runOnUIThread(ChatActivity.this.waitingForCharaterEnterRunnable, AndroidUtilities.WEB_URL == null ? 3000 : 1000);
                }
            }

            public void needSendTyping() {
                MessagesController.getInstance().sendTyping(ChatActivity.this.dialog_id, 0, ChatActivity.this.classGuid);
            }

            public void onAttachButtonHidden() {
                if (!ChatActivity.this.actionBar.isSearchFieldVisible()) {
                    if (ChatActivity.this.attachItem != null) {
                        ChatActivity.this.attachItem.setVisibility(0);
                    }
                    if (ChatActivity.this.headerItem != null) {
                        ChatActivity.this.headerItem.setVisibility(8);
                    }
                }
            }

            public void onAttachButtonShow() {
                if (!ChatActivity.this.actionBar.isSearchFieldVisible()) {
                    if (ChatActivity.this.attachItem != null) {
                        ChatActivity.this.attachItem.setVisibility(8);
                    }
                    if (ChatActivity.this.headerItem != null) {
                        ChatActivity.this.headerItem.setVisibility(0);
                    }
                }
            }

            public void onMessageEditEnd(boolean loading) {
                if (!loading) {
                    MentionsAdapter access$3900 = ChatActivity.this.mentionsAdapter;
                    boolean z = ChatActivity.this.currentEncryptedChat == null || AndroidUtilities.getPeerLayerVersion(ChatActivity.this.currentEncryptedChat.layer) >= 46;
                    access$3900.setNeedBotContext(z);
                    ChatActivity.this.chatListView.setOnItemLongClickListener(ChatActivity.this.onItemLongClickListener);
                    ChatActivity.this.chatListView.setOnItemClickListener(ChatActivity.this.onItemClickListener);
                    ChatActivity.this.chatListView.setClickable(true);
                    ChatActivity.this.chatListView.setLongClickable(true);
                    ChatActivity.this.mentionsAdapter.setAllowNewMentions(true);
                    ChatActivity.this.actionModeTitleContainer.setVisibility(8);
                    ChatActivity.this.selectedMessagesCountTextView.setVisibility(0);
                    ChatActivityEnterView chatActivityEnterView = ChatActivity.this.chatActivityEnterView;
                    if (ChatActivity.this.currentEncryptedChat == null || AndroidUtilities.getPeerLayerVersion(ChatActivity.this.currentEncryptedChat.layer) >= 23) {
                        z = true;
                    } else {
                        z = false;
                    }
                    boolean z2 = ChatActivity.this.currentEncryptedChat == null || AndroidUtilities.getPeerLayerVersion(ChatActivity.this.currentEncryptedChat.layer) >= 46;
                    chatActivityEnterView.setAllowStickersAndGifs(z, z2);
                    if (ChatActivity.this.editingMessageObjectReqId != 0) {
                        ConnectionsManager.getInstance().cancelRequest(ChatActivity.this.editingMessageObjectReqId, true);
                        ChatActivity.this.editingMessageObjectReqId = 0;
                    }
                    ChatActivity.this.actionBar.hideActionMode();
                    ChatActivity.this.updatePinnedMessageView(true);
                    ChatActivity.this.updateBottomOverlay();
                    ChatActivity.this.updateVisibleRows();
                }
            }

            public void onWindowSizeChanged(int size) {
                boolean z = true;
                if (size < AndroidUtilities.dp(72.0f) + ActionBar.getCurrentActionBarHeight()) {
                    ChatActivity.this.allowStickersPanel = false;
                    if (ChatActivity.this.stickersPanel.getVisibility() == 0) {
                        ChatActivity.this.stickersPanel.setVisibility(4);
                    }
                    if (ChatActivity.this.mentionContainer != null && ChatActivity.this.mentionContainer.getVisibility() == 0) {
                        ChatActivity.this.mentionContainer.setVisibility(4);
                    }
                } else {
                    ChatActivity.this.allowStickersPanel = true;
                    if (ChatActivity.this.stickersPanel.getVisibility() == 4) {
                        ChatActivity.this.stickersPanel.setVisibility(0);
                    }
                    if (ChatActivity.this.mentionContainer != null && ChatActivity.this.mentionContainer.getVisibility() == 4 && (!ChatActivity.this.mentionsAdapter.isBotContext() || ChatActivity.this.allowContextBotPanel || ChatActivity.this.allowContextBotPanelSecond)) {
                        ChatActivity.this.mentionContainer.setVisibility(0);
                        ChatActivity.this.mentionContainer.setTag(null);
                    }
                }
                ChatActivity chatActivity = ChatActivity.this;
                if (ChatActivity.this.chatActivityEnterView.isPopupShowing()) {
                    z = false;
                }
                chatActivity.allowContextBotPanel = z;
                ChatActivity.this.checkContextBotPanel();
            }

            public void onStickersTab(boolean opened) {
                if (ChatActivity.this.emojiButtonRed != null) {
                    ChatActivity.this.emojiButtonRed.setVisibility(8);
                }
                ChatActivity.this.allowContextBotPanelSecond = !opened;
                ChatActivity.this.checkContextBotPanel();
            }

            public void didPressedAttachButton() {
                ChatActivity.this.openAttachMenu();
            }

            public void needStartRecordVideo(int state) {
                if (ChatActivity.this.instantCameraView == null) {
                    return;
                }
                if (state == 0) {
                    ChatActivity.this.instantCameraView.showCamera();
                } else if (state == 1 || state == 3 || state == 4) {
                    ChatActivity.this.instantCameraView.send(state);
                } else if (state == 2) {
                    ChatActivity.this.instantCameraView.cancel();
                }
            }

            public void needChangeVideoPreviewState(int state, float seekProgress) {
                if (ChatActivity.this.instantCameraView != null) {
                    ChatActivity.this.instantCameraView.changeVideoPreviewState(state, seekProgress);
                }
            }

            public void needStartRecordAudio(int state) {
                ChatActivity.this.overlayView.setVisibility(state == 0 ? 8 : 0);
            }

            public void needShowMediaBanHint() {
                ChatActivity.this.showMediaBannedHint();
            }
        });
        fragmentContextView = new FrameLayout(context) {
            public void setTranslationY(float translationY) {
                super.setTranslationY(translationY);
                if (ChatActivity.this.chatActivityEnterView != null) {
                    ChatActivity.this.chatActivityEnterView.invalidate();
                }
                if (getVisibility() != 8) {
                    int height = getLayoutParams().height;
                    if (ChatActivity.this.chatListView != null) {
                        ChatActivity.this.chatListView.setTranslationY(translationY);
                    }
                    if (ChatActivity.this.progressView != null) {
                        ChatActivity.this.progressView.setTranslationY(translationY);
                    }
                    if (ChatActivity.this.mentionContainer != null) {
                        ChatActivity.this.mentionContainer.setTranslationY(translationY);
                    }
                    if (ChatActivity.this.pagedownButton != null) {
                        ChatActivity.this.pagedownButton.setTranslationY(translationY);
                    }
                    if (ChatActivity.this.mentiondownButton != null) {
                        FrameLayout access$7900 = ChatActivity.this.mentiondownButton;
                        if (ChatActivity.this.pagedownButton.getVisibility() == 0) {
                            translationY -= (float) AndroidUtilities.dp(72.0f);
                        }
                        access$7900.setTranslationY(translationY);
                    }
                }
            }

            public boolean hasOverlappingRendering() {
                return false;
            }

            public void setVisibility(int visibility) {
                float f = 0.0f;
                super.setVisibility(visibility);
                if (visibility == 8) {
                    FrameLayout access$7800;
                    if (ChatActivity.this.chatListView != null) {
                        ChatActivity.this.chatListView.setTranslationY(0.0f);
                    }
                    if (ChatActivity.this.progressView != null) {
                        ChatActivity.this.progressView.setTranslationY(0.0f);
                    }
                    if (ChatActivity.this.mentionContainer != null) {
                        ChatActivity.this.mentionContainer.setTranslationY(0.0f);
                    }
                    if (ChatActivity.this.pagedownButton != null) {
                        access$7800 = ChatActivity.this.pagedownButton;
                        if (ChatActivity.this.pagedownButton.getTag() == null) {
                            f = (float) AndroidUtilities.dp(100.0f);
                        }
                        access$7800.setTranslationY(f);
                    }
                    if (ChatActivity.this.mentiondownButton != null) {
                        access$7800 = ChatActivity.this.mentiondownButton;
                        if (ChatActivity.this.mentiondownButton.getTag() == null) {
                            f = (float) AndroidUtilities.dp(100.0f);
                        } else {
                            f = (float) (ChatActivity.this.pagedownButton.getVisibility() == 0 ? -AndroidUtilities.dp(72.0f) : 0);
                        }
                        access$7800.setTranslationY(f);
                    }
                }
            }
        };
        this.chatActivityEnterView.addTopView(fragmentContextView, 48);
        fragmentContextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (ChatActivity.this.replyingMessageObject != null) {
                    ChatActivity.this.scrollToMessageId(ChatActivity.this.replyingMessageObject.getId(), 0, true, 0, false);
                }
            }
        });
        this.replyLineView = new View(context);
        this.replyLineView.setBackgroundColor(Theme.getColor(Theme.key_chat_replyPanelLine));
        fragmentContextView.addView(this.replyLineView, LayoutHelper.createFrame(-1, 1, 83));
        this.replyIconImageView = new ImageView(context);
        this.replyIconImageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_replyPanelIcons), Mode.MULTIPLY));
        this.replyIconImageView.setScaleType(ScaleType.CENTER);
        fragmentContextView.addView(this.replyIconImageView, LayoutHelper.createFrame(52, 46, 51));
        this.replyCloseImageView = new ImageView(context);
        this.replyCloseImageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_replyPanelClose), Mode.MULTIPLY));
        this.replyCloseImageView.setImageResource(R.drawable.msg_panel_clear);
        this.replyCloseImageView.setScaleType(ScaleType.CENTER);
        fragmentContextView.addView(this.replyCloseImageView, LayoutHelper.createFrame(52, 46.0f, 53, 0.0f, 0.5f, 0.0f, 0.0f));
        this.replyCloseImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (ChatActivity.this.forwardingMessages != null) {
                    ChatActivity.this.forwardingMessages.clear();
                }
                ChatActivity.this.showReplyPanel(false, null, null, ChatActivity.this.foundWebPage, true);
            }
        });
        this.replyNameTextView = new SimpleTextView(context);
        this.replyNameTextView.setTextSize(14);
        this.replyNameTextView.setTextColor(Theme.getColor(Theme.key_chat_replyPanelName));
        this.replyNameTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        fragmentContextView.addView(this.replyNameTextView, LayoutHelper.createFrame(-1, RadialCountdown.TEXT_SIZE_SP, 51, 52.0f, 6.0f, 52.0f, 0.0f));
        this.replyObjectTextView = new SimpleTextView(context);
        this.replyObjectTextView.setTextSize(14);
        this.replyObjectTextView.setTextColor(Theme.getColor(Theme.key_chat_replyPanelMessage));
        fragmentContextView.addView(this.replyObjectTextView, LayoutHelper.createFrame(-1, RadialCountdown.TEXT_SIZE_SP, 51, 52.0f, 24.0f, 52.0f, 0.0f));
        this.replyImageView = new BackupImageView(context);
        fragmentContextView.addView(this.replyImageView, LayoutHelper.createFrame(34, 34.0f, 51, 52.0f, 6.0f, 0.0f, 0.0f));
        this.stickersPanel = new FrameLayout(context);
        this.stickersPanel.setVisibility(8);
        this.contentView.addView(this.stickersPanel, LayoutHelper.createFrame(-2, 81.5f, 83, 0.0f, 0.0f, 0.0f, 38.0f));
        this.stickersListView = new RecyclerListView(context) {
            public boolean onInterceptTouchEvent(MotionEvent event) {
                boolean result = StickerPreviewViewer.getInstance().onInterceptTouchEvent(event, ChatActivity.this.stickersListView, 0, null);
                if (super.onInterceptTouchEvent(event) || result) {
                    return true;
                }
                return false;
            }
        };
        this.stickersListView.setTag(Integer.valueOf(3));
        this.stickersListView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return StickerPreviewViewer.getInstance().onTouch(event, ChatActivity.this.stickersListView, 0, ChatActivity.this.stickersOnItemClickListener, null);
            }
        });
        this.stickersListView.setDisallowInterceptTouchEvents(true);
        LayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(0);
        this.stickersListView.setLayoutManager(linearLayoutManager);
        this.stickersListView.setClipToPadding(false);
        this.stickersListView.setOverScrollMode(2);
        this.stickersPanel.addView(this.stickersListView, LayoutHelper.createFrame(-1, 78.0f));
        initStickers();
        this.stickersPanelArrow = new ImageView(context);
        this.stickersPanelArrow.setImageResource(R.drawable.stickers_back_arrow);
        this.stickersPanelArrow.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_stickersHintPanel), Mode.MULTIPLY));
        this.stickersPanel.addView(this.stickersPanelArrow, LayoutHelper.createFrame(-2, -2.0f, 83, 53.0f, 0.0f, 0.0f, 0.0f));
        this.searchContainer = new FrameLayout(context) {
            public void onDraw(Canvas canvas) {
                int bottom = Theme.chat_composeShadowDrawable.getIntrinsicHeight();
                Theme.chat_composeShadowDrawable.setBounds(0, 0, getMeasuredWidth(), bottom);
                Theme.chat_composeShadowDrawable.draw(canvas);
                canvas.drawRect(0.0f, (float) bottom, (float) getMeasuredWidth(), (float) getMeasuredHeight(), Theme.chat_composeBackgroundPaint);
            }
        };
        this.searchContainer.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        this.searchContainer.setWillNotDraw(false);
        this.searchContainer.setVisibility(4);
        this.searchContainer.setFocusable(true);
        this.searchContainer.setFocusableInTouchMode(true);
        this.searchContainer.setClickable(true);
        this.searchContainer.setPadding(0, AndroidUtilities.dp(3.0f), 0, 0);
        this.searchUpButton = new ImageView(context);
        this.searchUpButton.setScaleType(ScaleType.CENTER);
        this.searchUpButton.setImageResource(R.drawable.search_up);
        this.searchUpButton.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_searchPanelIcons), Mode.MULTIPLY));
        this.searchContainer.addView(this.searchUpButton, LayoutHelper.createFrame(48, 48.0f, 53, 0.0f, 0.0f, 48.0f, 0.0f));
        this.searchUpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MessagesSearchQuery.searchMessagesInChat(null, ChatActivity.this.dialog_id, ChatActivity.this.mergeDialogId, ChatActivity.this.classGuid, 1, ChatActivity.this.searchingUserMessages);
            }
        });
        this.searchDownButton = new ImageView(context);
        this.searchDownButton.setScaleType(ScaleType.CENTER);
        this.searchDownButton.setImageResource(R.drawable.search_down);
        this.searchDownButton.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_searchPanelIcons), Mode.MULTIPLY));
        this.searchContainer.addView(this.searchDownButton, LayoutHelper.createFrame(48, 48.0f, 53, 0.0f, 0.0f, 0.0f, 0.0f));
        this.searchDownButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MessagesSearchQuery.searchMessagesInChat(null, ChatActivity.this.dialog_id, ChatActivity.this.mergeDialogId, ChatActivity.this.classGuid, 2, ChatActivity.this.searchingUserMessages);
            }
        });
        if (this.currentChat != null && (!ChatObject.isChannel(this.currentChat) || this.currentChat.megagroup)) {
            this.searchUserButton = new ImageView(context);
            this.searchUserButton.setScaleType(ScaleType.CENTER);
            this.searchUserButton.setImageResource(R.drawable.usersearch);
            this.searchUserButton.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_searchPanelIcons), Mode.MULTIPLY));
            this.searchContainer.addView(this.searchUserButton, LayoutHelper.createFrame(48, 48.0f, 51, 48.0f, 0.0f, 0.0f, 0.0f));
            this.searchUserButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ChatActivity.this.mentionLayoutManager.setReverseLayout(true);
                    ChatActivity.this.mentionsAdapter.setSearchingMentions(true);
                    ChatActivity.this.searchCalendarButton.setVisibility(8);
                    ChatActivity.this.searchUserButton.setVisibility(8);
                    ChatActivity.this.searchingForUser = true;
                    ChatActivity.this.searchingUserMessages = null;
                    ChatActivity.this.searchItem.getSearchField().setHint(LocaleController.getString("SearchMembers", R.string.SearchMembers));
                    ChatActivity.this.searchItem.setSearchFieldCaption(LocaleController.getString("SearchFrom", R.string.SearchFrom));
                    AndroidUtilities.showKeyboard(ChatActivity.this.searchItem.getSearchField());
                    ChatActivity.this.searchItem.clearSearchText();
                }
            });
        }
        this.searchCalendarButton = new ImageView(context);
        this.searchCalendarButton.setScaleType(ScaleType.CENTER);
        this.searchCalendarButton.setImageResource(R.drawable.search_calendar);
        this.searchCalendarButton.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_searchPanelIcons), Mode.MULTIPLY));
        this.searchContainer.addView(this.searchCalendarButton, LayoutHelper.createFrame(48, 48, 51));
        this.searchCalendarButton.setOnClickListener(new View.OnClickListener() {

            class C59221 implements OnDateSetListener {
                C59221() {
                }

                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.clear();
                    calendar.set(year, month, dayOfMonth);
                    int date = (int) (calendar.getTime().getTime() / 1000);
                    ChatActivity.this.clearChatData();
                    ChatActivity.this.waitingForLoad.add(Integer.valueOf(ChatActivity.this.lastLoadIndex));
                    MessagesController.getInstance().loadMessages(ChatActivity.this.dialog_id, 30, 0, date, true, 0, ChatActivity.this.classGuid, 4, 0, ChatObject.isChannel(ChatActivity.this.currentChat), ChatActivity.this.lastLoadIndex = ChatActivity.this.lastLoadIndex + 1);
                }
            }

            class C59232 implements OnClickListener {
                C59232() {
                }

                public void onClick(DialogInterface dialog, int which) {
                }
            }

            public void onClick(View view) {
                if (ChatActivity.this.getParentActivity() != null) {
                    AndroidUtilities.hideKeyboard(ChatActivity.this.searchItem.getSearchField());
                    Calendar calendar = Calendar.getInstance();
                    try {
                        DatePickerDialog dialog = new DatePickerDialog(ChatActivity.this.getParentActivity(), new C59221(), calendar.get(1), calendar.get(2), calendar.get(5));
                        final DatePicker datePicker = dialog.getDatePicker();
                        datePicker.setMinDate(1375315200000L);
                        datePicker.setMaxDate(System.currentTimeMillis());
                        dialog.setButton(-1, LocaleController.getString("JumpToDate", R.string.JumpToDate), dialog);
                        dialog.setButton(-2, LocaleController.getString("Cancel", R.string.Cancel), new C59232());
                        if (VERSION.SDK_INT >= 21) {
                            dialog.setOnShowListener(new OnShowListener() {
                                public void onShow(DialogInterface dialog) {
                                    int count = datePicker.getChildCount();
                                    for (int a = 0; a < count; a++) {
                                        View child = datePicker.getChildAt(a);
                                        ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
                                        layoutParams.width = -1;
                                        child.setLayoutParams(layoutParams);
                                    }
                                }
                            });
                        }
                        ChatActivity.this.showDialog(dialog);
                    } catch (Exception e) {
                        FileLog.e(e);
                    }
                }
            }
        });
        this.searchCountText = new SimpleTextView(context);
        this.searchCountText.setTextColor(Theme.getColor(Theme.key_chat_searchPanelText));
        this.searchCountText.setTextSize(15);
        this.searchCountText.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.searchCountText.setGravity(5);
        this.searchContainer.addView(this.searchCountText, LayoutHelper.createFrame(-2, -2.0f, 21, 0.0f, 0.0f, 108.0f, 0.0f));
        this.bottomOverlay = new FrameLayout(context) {
            public void onDraw(Canvas canvas) {
                int bottom = Theme.chat_composeShadowDrawable.getIntrinsicHeight();
                Theme.chat_composeShadowDrawable.setBounds(0, 0, getMeasuredWidth(), bottom);
                Theme.chat_composeShadowDrawable.draw(canvas);
                canvas.drawRect(0.0f, (float) bottom, (float) getMeasuredWidth(), (float) getMeasuredHeight(), Theme.chat_composeBackgroundPaint);
            }
        };
        this.bottomOverlay.setWillNotDraw(false);
        this.bottomOverlay.setVisibility(4);
        this.bottomOverlay.setFocusable(true);
        this.bottomOverlay.setFocusableInTouchMode(true);
        this.bottomOverlay.setClickable(true);
        this.bottomOverlay.setPadding(0, AndroidUtilities.dp(2.0f), 0, 0);
        this.contentView.addView(this.bottomOverlay, LayoutHelper.createFrame(-1, 51, 80));
        this.bottomOverlayText = new TextView(context);
        this.bottomOverlayText.setTextSize(1, 14.0f);
        this.bottomOverlayText.setGravity(17);
        this.bottomOverlayText.setMaxLines(2);
        this.bottomOverlayText.setEllipsize(TruncateAt.END);
        this.bottomOverlayText.setLineSpacing((float) AndroidUtilities.dp(2.0f), 1.0f);
        this.bottomOverlayText.setTextColor(Theme.getColor(Theme.key_chat_secretChatStatusText));
        this.bottomOverlay.addView(this.bottomOverlayText, LayoutHelper.createFrame(-2, -2.0f, 17, 14.0f, 0.0f, 14.0f, 0.0f));
        this.bottomOverlayChat = new FrameLayout(context) {
            public void onDraw(Canvas canvas) {
                int bottom = Theme.chat_composeShadowDrawable.getIntrinsicHeight();
                Theme.chat_composeShadowDrawable.setBounds(0, 0, getMeasuredWidth(), bottom);
                Theme.chat_composeShadowDrawable.draw(canvas);
                canvas.drawRect(0.0f, (float) bottom, (float) getMeasuredWidth(), (float) getMeasuredHeight(), Theme.chat_composeBackgroundPaint);
            }
        };
        this.bottomOverlayChat.setWillNotDraw(false);
        this.bottomOverlayChat.setPadding(0, AndroidUtilities.dp(3.0f), 0, 0);
        this.bottomOverlayChat.setVisibility(4);
        this.contentView.addView(this.bottomOverlayChat, LayoutHelper.createFrame(-1, 51, 80));
        final Context context2 = context;
        this.bottomOverlayChat.setOnClickListener(new View.OnClickListener() {

            class C59251 implements OnClickListener {
                C59251() {
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    MessagesController.getInstance().unblockUser(ChatActivity.this.currentUser.id);
                }
            }

            class C59272 implements OnClickListener {

                class C59261 implements RewardedVideoListener {
                    boolean isVideoFinished;

                    C59261() {
                    }

                    public void onVideoOpened() {
                    }

                    public void onVideoClicked() {
                    }

                    public void onVideoClosed() {
                        if (this.isVideoFinished) {
                            MessagesController.getInstance().addUserToChat(ChatActivity.this.currentChat.id, UserConfig.getCurrentUser(), null, 0, null, ChatActivity.this);
                        }
                    }

                    public void onVideoError(String s) {
                        Log.w("rewarded video error", s);
                        MessagesController.getInstance().addUserToChat(ChatActivity.this.currentChat.id, UserConfig.getCurrentUser(), null, 0, null, ChatActivity.this);
                    }

                    public void onVideoFinished() {
                        this.isVideoFinished = true;
                    }
                }

                C59272() {
                }

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    AppsgeyserSDK.getFastTrackAdsController().showRewardedVideo(new C59261());
                }
            }

            class C59283 implements OnClickListener {
                C59283() {
                }

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }

            class C59294 implements OnClickListener {
                C59294() {
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    MessagesController.getInstance().deleteDialog(ChatActivity.this.dialog_id, 0);
                    ChatActivity.this.finishFragment();
                }
            }

            public void onClick(View view) {
                if (ChatActivity.this.getParentActivity() != null) {
                    AlertDialog.Builder builder = null;
                    if (ChatActivity.this.currentUser == null || !ChatActivity.this.userBlocked) {
                        if (ChatActivity.this.currentUser != null && ChatActivity.this.currentUser.bot && ChatActivity.this.botUser != null) {
                            if (ChatActivity.this.botUser.length() != 0) {
                                MessagesController.getInstance().sendBotStart(ChatActivity.this.currentUser, ChatActivity.this.botUser);
                            } else {
                                SendMessagesHelper.getInstance().sendMessage("/start", ChatActivity.this.dialog_id, null, null, false, null, null, null);
                            }
                            ChatActivity.this.botUser = null;
                            ChatActivity.this.updateBottomOverlay();
                        } else if (!ChatObject.isChannel(ChatActivity.this.currentChat) || (ChatActivity.this.currentChat instanceof TLRPC$TL_channelForbidden)) {
                            builder = new AlertDialog.Builder(ChatActivity.this.getParentActivity());
                            builder.setMessage(LocaleController.getString("AreYouSureDeleteThisChat", R.string.AreYouSureDeleteThisChat));
                            builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new C59294());
                        } else if (ChatObject.isNotInChat(ChatActivity.this.currentChat)) {
                            AlertDialog.Builder joinAlertBuilder = new AlertDialog.Builder(context2);
                            joinAlertBuilder.setMessage(context2.getString(R.string.RewardedVideoChannels));
                            joinAlertBuilder.setPositiveButton(context2.getString(17039370), new C59272());
                            joinAlertBuilder.setNegativeButton(context2.getString(17039360), new C59283());
                            joinAlertBuilder.show();
                        } else {
                            ChatActivity.this.toggleMute(true);
                        }
                    } else if (ChatActivity.this.currentUser.bot) {
                        String botUserLast = ChatActivity.this.botUser;
                        ChatActivity.this.botUser = null;
                        MessagesController.getInstance().unblockUser(ChatActivity.this.currentUser.id);
                        if (botUserLast == null || botUserLast.length() == 0) {
                            SendMessagesHelper.getInstance().sendMessage("/start", ChatActivity.this.dialog_id, null, null, false, null, null, null);
                        } else {
                            MessagesController.getInstance().sendBotStart(ChatActivity.this.currentUser, botUserLast);
                        }
                    } else {
                        builder = new AlertDialog.Builder(ChatActivity.this.getParentActivity());
                        builder.setMessage(LocaleController.getString("AreYouSureUnblockContact", R.string.AreYouSureUnblockContact));
                        builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new C59251());
                    }
                    if (builder != null) {
                        builder.setTitle(ApplicationLoader.getConfig().getAppName());
                        builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
                        ChatActivity.this.showDialog(builder.create());
                    }
                }
            }
        });
        this.bottomOverlayChatText = new TextView(context);
        this.bottomOverlayChatText.setTextSize(1, CtaButton.TEXT_SIZE_SP);
        this.bottomOverlayChatText.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.bottomOverlayChatText.setTextColor(Theme.getColor(Theme.key_chat_fieldOverlayText));
        this.bottomOverlayChat.addView(this.bottomOverlayChatText, LayoutHelper.createFrame(-2, -2, 17));
        this.contentView.addView(this.searchContainer, LayoutHelper.createFrame(-1, 51, 80));
        this.chatAdapter.updateRows();
        if (this.loading && this.messages.isEmpty()) {
            this.progressView.setVisibility(this.chatAdapter.botInfoRow == -1 ? 0 : 4);
            this.chatListView.setEmptyView(null);
        } else {
            this.progressView.setVisibility(4);
            this.chatListView.setEmptyView(this.emptyViewContainer);
        }
        ChatActivityEnterView chatActivityEnterView2 = this.chatActivityEnterView;
        if (this.userBlocked) {
            messageObject = null;
        } else {
            messageObject = this.botButtons;
        }
        chatActivityEnterView2.setButtons(messageObject);
        updateContactStatus();
        updateBottomOverlay();
        updateSecretStatus();
        updateSpamView();
        updatePinnedMessageView(true);
        try {
            if (this.currentEncryptedChat != null && VERSION.SDK_INT >= 23 && (UserConfig.passcodeHash.length() == 0 || UserConfig.allowScreenCapture)) {
                getParentActivity().getWindow().setFlags(8192, 8192);
            }
        } catch (Throwable e2) {
            FileLog.e(e2);
        }
        if (oldMessage != null) {
            this.chatActivityEnterView.setFieldText(oldMessage);
        }
        fixLayoutInternal();
        return this.fragmentView;
    }

    private TextureView createTextureView(boolean add) {
        if (this.parentLayout == null) {
            return null;
        }
        if (this.roundVideoContainer == null) {
            if (VERSION.SDK_INT >= 21) {
                this.roundVideoContainer = new FrameLayout(getParentActivity()) {
                    public void setTranslationY(float translationY) {
                        super.setTranslationY(translationY);
                        ChatActivity.this.contentView.invalidate();
                    }
                };
                this.roundVideoContainer.setOutlineProvider(new ViewOutlineProvider() {
                    @TargetApi(21)
                    public void getOutline(View view, Outline outline) {
                        outline.setOval(0, 0, AndroidUtilities.roundMessageSize, AndroidUtilities.roundMessageSize);
                    }
                });
                this.roundVideoContainer.setClipToOutline(true);
            } else {
                this.roundVideoContainer = new FrameLayout(getParentActivity()) {
                    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
                        super.onSizeChanged(w, h, oldw, oldh);
                        ChatActivity.this.aspectPath.reset();
                        ChatActivity.this.aspectPath.addCircle((float) (w / 2), (float) (h / 2), (float) (w / 2), Direction.CW);
                        ChatActivity.this.aspectPath.toggleInverseFillType();
                    }

                    public void setTranslationY(float translationY) {
                        super.setTranslationY(translationY);
                        ChatActivity.this.contentView.invalidate();
                    }

                    public void setVisibility(int visibility) {
                        super.setVisibility(visibility);
                        if (visibility == 0) {
                            setLayerType(2, null);
                        }
                    }

                    protected void dispatchDraw(Canvas canvas) {
                        super.dispatchDraw(canvas);
                        canvas.drawPath(ChatActivity.this.aspectPath, ChatActivity.this.aspectPaint);
                    }
                };
                this.aspectPath = new Path();
                this.aspectPaint = new Paint(1);
                this.aspectPaint.setColor(-16777216);
                this.aspectPaint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
            }
            this.roundVideoContainer.setWillNotDraw(false);
            this.roundVideoContainer.setVisibility(4);
            this.aspectRatioFrameLayout = new AspectRatioFrameLayout(getParentActivity());
            this.aspectRatioFrameLayout.setBackgroundColor(0);
            if (add) {
                this.roundVideoContainer.addView(this.aspectRatioFrameLayout, LayoutHelper.createFrame(-1, -1.0f));
            }
            this.videoTextureView = new TextureView(getParentActivity());
            this.videoTextureView.setOpaque(false);
            this.aspectRatioFrameLayout.addView(this.videoTextureView, LayoutHelper.createFrame(-1, -1.0f));
        }
        if (this.roundVideoContainer.getParent() == null) {
            this.contentView.addView(this.roundVideoContainer, 1, new FrameLayout.LayoutParams(AndroidUtilities.roundMessageSize, AndroidUtilities.roundMessageSize));
        }
        this.roundVideoContainer.setVisibility(4);
        this.aspectRatioFrameLayout.setDrawingReady(false);
        return this.videoTextureView;
    }

    private void destroyTextureView() {
        if (this.roundVideoContainer != null && this.roundVideoContainer.getParent() != null) {
            this.contentView.removeView(this.roundVideoContainer);
            this.aspectRatioFrameLayout.setDrawingReady(false);
            this.roundVideoContainer.setVisibility(4);
            if (VERSION.SDK_INT < 21) {
                this.roundVideoContainer.setLayerType(0, null);
            }
        }
    }

    private void sendBotInlineResult(TLRPC$BotInlineResult result) {
        int uid = this.mentionsAdapter.getContextBotId();
        HashMap<String, String> params = new HashMap();
        params.put("id", result.id);
        params.put("query_id", "" + result.query_id);
        params.put("bot", "" + uid);
        params.put("bot_name", this.mentionsAdapter.getContextBotName());
        SendMessagesHelper.prepareSendingBotContextResult(result, params, this.dialog_id, this.replyingMessageObject);
        this.chatActivityEnterView.setFieldText("");
        showReplyPanel(false, null, null, null, false);
        SearchQuery.increaseInlineRaiting(uid);
    }

    private void mentionListViewUpdateLayout() {
        if (this.mentionListView.getChildCount() <= 0) {
            this.mentionListViewScrollOffsetY = 0;
            this.mentionListViewLastViewPosition = -1;
            return;
        }
        View child = this.mentionListView.getChildAt(this.mentionListView.getChildCount() - 1);
        Holder holder = (Holder) this.mentionListView.findContainingViewHolder(child);
        int newOffset;
        if (this.mentionLayoutManager.getReverseLayout()) {
            if (holder != null) {
                this.mentionListViewLastViewPosition = holder.getAdapterPosition();
                this.mentionListViewLastViewTop = child.getBottom();
            } else {
                this.mentionListViewLastViewPosition = -1;
            }
            child = this.mentionListView.getChildAt(0);
            holder = (Holder) this.mentionListView.findContainingViewHolder(child);
            newOffset = (child.getBottom() >= this.mentionListView.getMeasuredHeight() || holder == null || holder.getAdapterPosition() != 0) ? this.mentionListView.getMeasuredHeight() : child.getBottom();
            if (this.mentionListViewScrollOffsetY != newOffset) {
                RecyclerListView recyclerListView = this.mentionListView;
                this.mentionListViewScrollOffsetY = newOffset;
                recyclerListView.setBottomGlowOffset(newOffset);
                this.mentionListView.setTopGlowOffset(0);
                this.mentionListView.invalidate();
                this.mentionContainer.invalidate();
                return;
            }
            return;
        }
        if (holder != null) {
            this.mentionListViewLastViewPosition = holder.getAdapterPosition();
            this.mentionListViewLastViewTop = child.getTop();
        } else {
            this.mentionListViewLastViewPosition = -1;
        }
        child = this.mentionListView.getChildAt(0);
        holder = (Holder) this.mentionListView.findContainingViewHolder(child);
        if (child.getTop() <= 0 || holder == null || holder.getAdapterPosition() != 0) {
            newOffset = 0;
        } else {
            newOffset = child.getTop();
        }
        if (this.mentionListViewScrollOffsetY != newOffset) {
            recyclerListView = this.mentionListView;
            this.mentionListViewScrollOffsetY = newOffset;
            recyclerListView.setTopGlowOffset(newOffset);
            this.mentionListView.setBottomGlowOffset(0);
            this.mentionListView.invalidate();
            this.mentionContainer.invalidate();
        }
    }

    private void checkBotCommands() {
        boolean z = true;
        URLSpanBotCommand.enabled = false;
        if (this.currentUser != null && this.currentUser.bot) {
            URLSpanBotCommand.enabled = true;
        } else if (this.info instanceof TLRPC$TL_chatFull) {
            int a = 0;
            while (a < this.info.participants.participants.size()) {
                User user = MessagesController.getInstance().getUser(Integer.valueOf(((TLRPC$ChatParticipant) this.info.participants.participants.get(a)).user_id));
                if (user == null || !user.bot) {
                    a++;
                } else {
                    URLSpanBotCommand.enabled = true;
                    return;
                }
            }
        } else if (this.info instanceof TLRPC$TL_channelFull) {
            if (this.info.bot_info.isEmpty() || this.currentChat == null || !this.currentChat.megagroup) {
                z = false;
            }
            URLSpanBotCommand.enabled = z;
        }
    }

    private GroupedMessages getValidGroupedMessage(MessageObject message) {
        if (message.getGroupId() == 0) {
            return null;
        }
        GroupedMessages groupedMessages = (GroupedMessages) this.groupedMessagesMap.get(Long.valueOf(message.getGroupId()));
        if (groupedMessages == null) {
            return groupedMessages;
        }
        if (groupedMessages.messages.size() <= 1 || groupedMessages.positions.get(message) == null) {
            return null;
        }
        return groupedMessages;
    }

    private void jumpToDate(int date) {
        if (!this.messages.isEmpty()) {
            MessageObject lastMessage = (MessageObject) this.messages.get(this.messages.size() - 1);
            if (((MessageObject) this.messages.get(0)).messageOwner.date >= date && lastMessage.messageOwner.date <= date) {
                int a = this.messages.size() - 1;
                while (a >= 0) {
                    MessageObject message = (MessageObject) this.messages.get(a);
                    if (message.messageOwner.date < date || message.getId() == 0) {
                        a--;
                    } else {
                        scrollToMessageId(message.getId(), 0, false, message.getDialogId() == this.mergeDialogId ? 1 : 0, false);
                        return;
                    }
                }
            } else if (((int) this.dialog_id) != 0) {
                clearChatData();
                this.waitingForLoad.add(Integer.valueOf(this.lastLoadIndex));
                MessagesController instance = MessagesController.getInstance();
                long j = this.dialog_id;
                int i = this.classGuid;
                boolean isChannel = ChatObject.isChannel(this.currentChat);
                int i2 = this.lastLoadIndex;
                this.lastLoadIndex = i2 + 1;
                instance.loadMessages(j, 30, 0, date, true, 0, i, 4, 0, isChannel, i2);
                this.floatingDateView.setAlpha(0.0f);
                this.floatingDateView.setTag(null);
            }
        }
    }

    public void processInlineBotContextPM(TLRPC$TL_inlineBotSwitchPM object) {
        if (object != null) {
            User user = this.mentionsAdapter.getContextBotUser();
            if (user != null) {
                this.chatActivityEnterView.setFieldText("");
                if (this.dialog_id == ((long) user.id)) {
                    this.inlineReturn = this.dialog_id;
                    MessagesController.getInstance().sendBotStart(this.currentUser, object.start_param);
                    return;
                }
                Bundle args = new Bundle();
                args.putInt("user_id", user.id);
                args.putString("inline_query", object.start_param);
                args.putLong("inline_return", this.dialog_id);
                if (MessagesController.checkCanOpenChat(args, this)) {
                    presentFragment(new ChatActivity(args));
                }
            }
        }
    }

    private void createChatAttachView() {
        if (getParentActivity() != null && this.chatAttachAlert == null) {
            this.chatAttachAlert = new ChatAttachAlert(getParentActivity(), this);
            this.chatAttachAlert.setDelegate(new ChatAttachViewDelegate() {
                public void didPressedButton(int button) {
                    if (ChatActivity.this.getParentActivity() != null && ChatActivity.this.chatAttachAlert != null) {
                        if (button == 7 || (button == 4 && !ChatActivity.this.chatAttachAlert.getSelectedPhotos().isEmpty())) {
                            ChatActivity.this.chatAttachAlert.dismiss();
                            HashMap<Object, Object> selectedPhotos = ChatActivity.this.chatAttachAlert.getSelectedPhotos();
                            ArrayList<Object> selectedPhotosOrder = ChatActivity.this.chatAttachAlert.getSelectedPhotosOrder();
                            if (!selectedPhotos.isEmpty()) {
                                ArrayList<SendMessagesHelper$SendingMediaInfo> photos = new ArrayList();
                                for (int a = 0; a < selectedPhotosOrder.size(); a++) {
                                    MediaController$PhotoEntry photoEntry = (MediaController$PhotoEntry) selectedPhotos.get(selectedPhotosOrder.get(a));
                                    SendMessagesHelper$SendingMediaInfo info = new SendMessagesHelper$SendingMediaInfo();
                                    if (photoEntry.imagePath != null) {
                                        info.path = photoEntry.imagePath;
                                    } else if (photoEntry.path != null) {
                                        info.path = photoEntry.path;
                                    }
                                    info.isVideo = photoEntry.isVideo;
                                    info.caption = photoEntry.caption != null ? photoEntry.caption.toString() : null;
                                    info.masks = !photoEntry.stickers.isEmpty() ? new ArrayList(photoEntry.stickers) : null;
                                    info.ttl = photoEntry.ttl;
                                    info.videoEditedInfo = photoEntry.editedInfo;
                                    photos.add(info);
                                    photoEntry.reset();
                                }
                                SendMessagesHelper.prepareSendingMedia(photos, ChatActivity.this.dialog_id, ChatActivity.this.replyingMessageObject, null, button == 4, MediaController.getInstance().isGroupPhotosEnabled());
                                ChatActivity.this.showReplyPanel(false, null, null, null, false);
                                DraftQuery.cleanDraft(ChatActivity.this.dialog_id, true);
                                return;
                            }
                            return;
                        }
                        if (ChatActivity.this.chatAttachAlert != null) {
                            ChatActivity.this.chatAttachAlert.dismissWithButtonClick(button);
                        }
                        ChatActivity.this.processSelectedAttach(button);
                    }
                }

                public View getRevealView() {
                    return ChatActivity.this.chatActivityEnterView.getAttachButton();
                }

                public void didSelectBot(User user) {
                    if (ChatActivity.this.chatActivityEnterView != null && !TextUtils.isEmpty(user.username)) {
                        ChatActivity.this.chatActivityEnterView.setFieldText("@" + user.username + " ");
                        ChatActivity.this.chatActivityEnterView.openKeyboard();
                    }
                }

                public void onCameraOpened() {
                    ChatActivity.this.chatActivityEnterView.closeKeyboard();
                }
            });
        }
    }

    public long getDialogId() {
        return this.dialog_id;
    }

    public void setBotUser(String value) {
        if (this.inlineReturn != 0) {
            MessagesController.getInstance().sendBotStart(this.currentUser, value);
            return;
        }
        this.botUser = value;
        updateBottomOverlay();
    }

    public boolean playFirstUnreadVoiceMessage() {
        if (this.chatActivityEnterView != null && this.chatActivityEnterView.isRecordingAudioVideo()) {
            return true;
        }
        for (int a = this.messages.size() - 1; a >= 0; a--) {
            MessageObject messageObject = (MessageObject) this.messages.get(a);
            if ((messageObject.isVoice() || messageObject.isRoundVideo()) && messageObject.isContentUnread() && !messageObject.isOut()) {
                MediaController.getInstance().setVoiceMessagesPlaylist(MediaController.getInstance().playMessage(messageObject) ? createVoiceMessagesPlaylist(messageObject, true) : null, true);
                return true;
            }
        }
        if (VERSION.SDK_INT < 23 || getParentActivity() == null || getParentActivity().checkSelfPermission("android.permission.RECORD_AUDIO") == 0) {
            return false;
        }
        getParentActivity().requestPermissions(new String[]{"android.permission.RECORD_AUDIO"}, 3);
        return true;
    }

    private void initStickers() {
        if (this.chatActivityEnterView != null && getParentActivity() != null && this.stickersAdapter == null) {
            if (this.currentEncryptedChat == null || AndroidUtilities.getPeerLayerVersion(this.currentEncryptedChat.layer) >= 23) {
                if (this.stickersAdapter != null) {
                    this.stickersAdapter.onDestroy();
                }
                this.stickersListView.setPadding(AndroidUtilities.dp(RadialCountdown.TEXT_SIZE_SP), 0, AndroidUtilities.dp(RadialCountdown.TEXT_SIZE_SP), 0);
                RecyclerListView recyclerListView = this.stickersListView;
                Adapter stickersAdapter = new StickersAdapter(getParentActivity(), new StickersAdapterDelegate() {
                    public void needChangePanelVisibility(final boolean show) {
                        float f = 1.0f;
                        if (!show || ChatActivity.this.stickersPanel.getVisibility() != 0) {
                            if (show || ChatActivity.this.stickersPanel.getVisibility() != 8) {
                                if (show) {
                                    ChatActivity.this.stickersListView.scrollToPosition(0);
                                    ChatActivity.this.stickersPanel.setVisibility(ChatActivity.this.allowStickersPanel ? 0 : 4);
                                }
                                if (ChatActivity.this.runningAnimation != null) {
                                    ChatActivity.this.runningAnimation.cancel();
                                    ChatActivity.this.runningAnimation = null;
                                }
                                if (ChatActivity.this.stickersPanel.getVisibility() != 4) {
                                    float f2;
                                    ChatActivity.this.runningAnimation = new AnimatorSet();
                                    AnimatorSet access$16500 = ChatActivity.this.runningAnimation;
                                    Animator[] animatorArr = new Animator[1];
                                    FrameLayout access$14300 = ChatActivity.this.stickersPanel;
                                    String str = "alpha";
                                    float[] fArr = new float[2];
                                    if (show) {
                                        f2 = 0.0f;
                                    } else {
                                        f2 = 1.0f;
                                    }
                                    fArr[0] = f2;
                                    if (!show) {
                                        f = 0.0f;
                                    }
                                    fArr[1] = f;
                                    animatorArr[0] = ObjectAnimator.ofFloat(access$14300, str, fArr);
                                    access$16500.playTogether(animatorArr);
                                    ChatActivity.this.runningAnimation.setDuration(150);
                                    ChatActivity.this.runningAnimation.addListener(new AnimatorListenerAdapter() {
                                        public void onAnimationEnd(Animator animation) {
                                            if (ChatActivity.this.runningAnimation != null && ChatActivity.this.runningAnimation.equals(animation)) {
                                                if (!show) {
                                                    ChatActivity.this.stickersAdapter.clearStickers();
                                                    ChatActivity.this.stickersPanel.setVisibility(8);
                                                    if (StickerPreviewViewer.getInstance().isVisible()) {
                                                        StickerPreviewViewer.getInstance().close();
                                                    }
                                                    StickerPreviewViewer.getInstance().reset();
                                                }
                                                ChatActivity.this.runningAnimation = null;
                                            }
                                        }

                                        public void onAnimationCancel(Animator animation) {
                                            if (ChatActivity.this.runningAnimation != null && ChatActivity.this.runningAnimation.equals(animation)) {
                                                ChatActivity.this.runningAnimation = null;
                                            }
                                        }
                                    });
                                    ChatActivity.this.runningAnimation.start();
                                } else if (!show) {
                                    ChatActivity.this.stickersPanel.setVisibility(8);
                                }
                            }
                        }
                    }
                });
                this.stickersAdapter = stickersAdapter;
                recyclerListView.setAdapter(stickersAdapter);
                recyclerListView = this.stickersListView;
                OnItemClickListener anonymousClass63 = new OnItemClickListener() {
                    public void onItemClick(View view, int position) {
                        TLRPC$Document document = ChatActivity.this.stickersAdapter.getItem(position);
                        if (document instanceof TLRPC$TL_document) {
                            SendMessagesHelper.getInstance().sendSticker(document, ChatActivity.this.dialog_id, ChatActivity.this.replyingMessageObject);
                            ChatActivity.this.showReplyPanel(false, null, null, null, false);
                            ChatActivity.this.chatActivityEnterView.addStickerToRecent(document);
                        }
                        ChatActivity.this.chatActivityEnterView.setFieldText("");
                    }
                };
                this.stickersOnItemClickListener = anonymousClass63;
                recyclerListView.setOnItemClickListener(anonymousClass63);
            }
        }
    }

    public void shareMyContact(final MessageObject messageObject) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getParentActivity());
        builder.setTitle(LocaleController.getString("ShareYouPhoneNumberTitle", R.string.ShareYouPhoneNumberTitle));
        if (this.currentUser == null) {
            builder.setMessage(LocaleController.getString("AreYouSureShareMyContactInfo", R.string.AreYouSureShareMyContactInfo));
        } else if (this.currentUser.bot) {
            builder.setMessage(LocaleController.getString("AreYouSureShareMyContactInfoBot", R.string.AreYouSureShareMyContactInfoBot));
        } else {
            builder.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("AreYouSureShareMyContactInfoUser", R.string.AreYouSureShareMyContactInfoUser, new Object[]{PhoneFormat.getInstance().format(Marker.ANY_NON_NULL_MARKER + UserConfig.getCurrentUser().phone), ContactsController.formatName(this.currentUser.first_name, this.currentUser.last_name)})));
        }
        builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                SendMessagesHelper.getInstance().sendMessage(UserConfig.getCurrentUser(), ChatActivity.this.dialog_id, messageObject, null, null);
                ChatActivity.this.moveScrollToLastMessage();
                ChatActivity.this.showReplyPanel(false, null, null, null, false);
            }
        });
        builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
        showDialog(builder.create());
    }

    private void hideVoiceHint() {
        this.voiceHintAnimation = new AnimatorSet();
        AnimatorSet animatorSet = this.voiceHintAnimation;
        Animator[] animatorArr = new Animator[1];
        animatorArr[0] = ObjectAnimator.ofFloat(this.voiceHintTextView, "alpha", new float[]{0.0f});
        animatorSet.playTogether(animatorArr);
        this.voiceHintAnimation.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                if (animation.equals(ChatActivity.this.voiceHintAnimation)) {
                    ChatActivity.this.voiceHintAnimation = null;
                    ChatActivity.this.voiceHintHideRunnable = null;
                    if (ChatActivity.this.voiceHintTextView != null) {
                        ChatActivity.this.voiceHintTextView.setVisibility(8);
                    }
                }
            }

            public void onAnimationCancel(Animator animation) {
                if (animation.equals(ChatActivity.this.voiceHintAnimation)) {
                    ChatActivity.this.voiceHintHideRunnable = null;
                    ChatActivity.this.voiceHintHideRunnable = null;
                }
            }
        });
        this.voiceHintAnimation.setDuration(300);
        this.voiceHintAnimation.start();
    }

    private void showVoiceHint(boolean hide, boolean video) {
        if (getParentActivity() != null && this.fragmentView != null) {
            if (!hide || this.voiceHintTextView != null) {
                if (this.voiceHintTextView == null) {
                    SizeNotifierFrameLayout frameLayout = this.fragmentView;
                    int index = frameLayout.indexOfChild(this.chatActivityEnterView);
                    if (index != -1) {
                        this.voiceHintTextView = new TextView(getParentActivity());
                        this.voiceHintTextView.setBackgroundDrawable(Theme.createRoundRectDrawable(AndroidUtilities.dp(3.0f), Theme.getColor(Theme.key_chat_gifSaveHintBackground)));
                        this.voiceHintTextView.setTextColor(Theme.getColor(Theme.key_chat_gifSaveHintText));
                        this.voiceHintTextView.setTextSize(1, 14.0f);
                        this.voiceHintTextView.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(7.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(7.0f));
                        this.voiceHintTextView.setGravity(16);
                        this.voiceHintTextView.setAlpha(0.0f);
                        frameLayout.addView(this.voiceHintTextView, index + 1, LayoutHelper.createFrame(-2, -2.0f, 85, 5.0f, 0.0f, 5.0f, 3.0f));
                    } else {
                        return;
                    }
                }
                if (hide) {
                    if (this.voiceHintAnimation != null) {
                        this.voiceHintAnimation.cancel();
                        this.voiceHintAnimation = null;
                    }
                    AndroidUtilities.cancelRunOnUIThread(this.voiceHintHideRunnable);
                    this.voiceHintHideRunnable = null;
                    hideVoiceHint();
                    return;
                }
                this.voiceHintTextView.setText(video ? LocaleController.getString("HoldToVideo", R.string.HoldToVideo) : LocaleController.getString("HoldToAudio", R.string.HoldToAudio));
                if (this.voiceHintHideRunnable != null) {
                    if (this.voiceHintAnimation != null) {
                        this.voiceHintAnimation.cancel();
                        this.voiceHintAnimation = null;
                    } else {
                        AndroidUtilities.cancelRunOnUIThread(this.voiceHintHideRunnable);
                        Runnable anonymousClass66 = new Runnable() {
                            public void run() {
                                ChatActivity.this.hideVoiceHint();
                            }
                        };
                        this.voiceHintHideRunnable = anonymousClass66;
                        AndroidUtilities.runOnUIThread(anonymousClass66, Reveal.CHECK_DELAY);
                        return;
                    }
                } else if (this.voiceHintAnimation != null) {
                    return;
                }
                this.voiceHintTextView.setVisibility(0);
                this.voiceHintAnimation = new AnimatorSet();
                AnimatorSet animatorSet = this.voiceHintAnimation;
                Animator[] animatorArr = new Animator[1];
                animatorArr[0] = ObjectAnimator.ofFloat(this.voiceHintTextView, "alpha", new float[]{1.0f});
                animatorSet.playTogether(animatorArr);
                this.voiceHintAnimation.addListener(new AnimatorListenerAdapter() {

                    class C59321 implements Runnable {
                        C59321() {
                        }

                        public void run() {
                            ChatActivity.this.hideVoiceHint();
                        }
                    }

                    public void onAnimationEnd(Animator animation) {
                        if (animation.equals(ChatActivity.this.voiceHintAnimation)) {
                            ChatActivity.this.voiceHintAnimation = null;
                            AndroidUtilities.runOnUIThread(ChatActivity.this.voiceHintHideRunnable = new C59321(), Reveal.CHECK_DELAY);
                        }
                    }

                    public void onAnimationCancel(Animator animation) {
                        if (animation.equals(ChatActivity.this.voiceHintAnimation)) {
                            ChatActivity.this.voiceHintAnimation = null;
                        }
                    }
                });
                this.voiceHintAnimation.setDuration(300);
                this.voiceHintAnimation.start();
            }
        }
    }

    private void showMediaBannedHint() {
        if (getParentActivity() != null && this.currentChat != null && this.currentChat.banned_rights != null && this.fragmentView != null) {
            if (this.mediaBanTooltip == null || this.mediaBanTooltip.getVisibility() != 0) {
                SizeNotifierFrameLayout frameLayout = this.fragmentView;
                int index = frameLayout.indexOfChild(this.chatActivityEnterView);
                if (index != -1) {
                    if (this.mediaBanTooltip == null) {
                        this.mediaBanTooltip = new CorrectlyMeasuringTextView(getParentActivity());
                        this.mediaBanTooltip.setBackgroundDrawable(Theme.createRoundRectDrawable(AndroidUtilities.dp(3.0f), Theme.getColor(Theme.key_chat_gifSaveHintBackground)));
                        this.mediaBanTooltip.setTextColor(Theme.getColor(Theme.key_chat_gifSaveHintText));
                        this.mediaBanTooltip.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(7.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(7.0f));
                        this.mediaBanTooltip.setGravity(16);
                        this.mediaBanTooltip.setTextSize(1, 14.0f);
                        frameLayout.addView(this.mediaBanTooltip, index + 1, LayoutHelper.createFrame(-2, -2.0f, 85, BitmapDescriptorFactory.HUE_ORANGE, 0.0f, 5.0f, 3.0f));
                    }
                    if (AndroidUtilities.isBannedForever(this.currentChat.banned_rights.until_date)) {
                        this.mediaBanTooltip.setText(LocaleController.getString("AttachMediaRestrictedForever", R.string.AttachMediaRestrictedForever));
                    } else {
                        this.mediaBanTooltip.setText(LocaleController.formatString("AttachMediaRestricted", R.string.AttachMediaRestricted, new Object[]{LocaleController.formatDateForBan((long) this.currentChat.banned_rights.until_date)}));
                    }
                    this.mediaBanTooltip.setVisibility(0);
                    AnimatorSet AnimatorSet = new AnimatorSet();
                    AnimatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.mediaBanTooltip, "alpha", new float[]{0.0f, 1.0f})});
                    AnimatorSet.addListener(new AnimatorListenerAdapter() {

                        class C59341 implements Runnable {

                            class C59331 extends AnimatorListenerAdapter {
                                C59331() {
                                }

                                public void onAnimationEnd(Animator animation) {
                                    if (ChatActivity.this.mediaBanTooltip != null) {
                                        ChatActivity.this.mediaBanTooltip.setVisibility(8);
                                    }
                                }
                            }

                            C59341() {
                            }

                            public void run() {
                                if (ChatActivity.this.mediaBanTooltip != null) {
                                    AnimatorSet AnimatorSet = new AnimatorSet();
                                    Animator[] animatorArr = new Animator[1];
                                    animatorArr[0] = ObjectAnimator.ofFloat(ChatActivity.this.mediaBanTooltip, "alpha", new float[]{0.0f});
                                    AnimatorSet.playTogether(animatorArr);
                                    AnimatorSet.addListener(new C59331());
                                    AnimatorSet.setDuration(300);
                                    AnimatorSet.start();
                                }
                            }
                        }

                        public void onAnimationEnd(Animator animation) {
                            AndroidUtilities.runOnUIThread(new C59341(), 5000);
                        }
                    });
                    AnimatorSet.setDuration(300);
                    AnimatorSet.start();
                }
            }
        }
    }

    private void showGifHint() {
        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
        if (!preferences.getBoolean("gifhint", false)) {
            preferences.edit().putBoolean("gifhint", true).commit();
            if (getParentActivity() != null && this.fragmentView != null && this.gifHintTextView == null) {
                if (this.allowContextBotPanelSecond) {
                    SizeNotifierFrameLayout frameLayout = this.fragmentView;
                    int index = frameLayout.indexOfChild(this.chatActivityEnterView);
                    if (index != -1) {
                        this.chatActivityEnterView.setOpenGifsTabFirst();
                        this.emojiButtonRed = new View(getParentActivity());
                        this.emojiButtonRed.setBackgroundResource(R.drawable.redcircle);
                        frameLayout.addView(this.emojiButtonRed, index + 1, LayoutHelper.createFrame(10, Settings.LOCATION_REQUEST_SMALLEST_DISPLACEMENT, 83, BitmapDescriptorFactory.HUE_ORANGE, 0.0f, 0.0f, 27.0f));
                        this.gifHintTextView = new TextView(getParentActivity());
                        this.gifHintTextView.setBackgroundDrawable(Theme.createRoundRectDrawable(AndroidUtilities.dp(3.0f), Theme.getColor(Theme.key_chat_gifSaveHintBackground)));
                        this.gifHintTextView.setTextColor(Theme.getColor(Theme.key_chat_gifSaveHintText));
                        this.gifHintTextView.setTextSize(1, 14.0f);
                        this.gifHintTextView.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(7.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(7.0f));
                        this.gifHintTextView.setText(LocaleController.getString("TapHereGifs", R.string.TapHereGifs));
                        this.gifHintTextView.setGravity(16);
                        frameLayout.addView(this.gifHintTextView, index + 1, LayoutHelper.createFrame(-2, -2.0f, 83, 5.0f, 0.0f, 5.0f, 3.0f));
                        AnimatorSet AnimatorSet = new AnimatorSet();
                        AnimatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.gifHintTextView, "alpha", new float[]{0.0f, 1.0f}), ObjectAnimator.ofFloat(this.emojiButtonRed, "alpha", new float[]{0.0f, 1.0f})});
                        AnimatorSet.addListener(new AnimatorListenerAdapter() {

                            class C59361 implements Runnable {

                                class C59351 extends AnimatorListenerAdapter {
                                    C59351() {
                                    }

                                    public void onAnimationEnd(Animator animation) {
                                        if (ChatActivity.this.gifHintTextView != null) {
                                            ChatActivity.this.gifHintTextView.setVisibility(8);
                                        }
                                    }
                                }

                                C59361() {
                                }

                                public void run() {
                                    if (ChatActivity.this.gifHintTextView != null) {
                                        AnimatorSet AnimatorSet = new AnimatorSet();
                                        Animator[] animatorArr = new Animator[1];
                                        animatorArr[0] = ObjectAnimator.ofFloat(ChatActivity.this.gifHintTextView, "alpha", new float[]{0.0f});
                                        AnimatorSet.playTogether(animatorArr);
                                        AnimatorSet.addListener(new C59351());
                                        AnimatorSet.setDuration(300);
                                        AnimatorSet.start();
                                    }
                                }
                            }

                            public void onAnimationEnd(Animator animation) {
                                AndroidUtilities.runOnUIThread(new C59361(), Reveal.CHECK_DELAY);
                            }
                        });
                        AnimatorSet.setDuration(300);
                        AnimatorSet.start();
                    }
                } else if (this.chatActivityEnterView != null) {
                    this.chatActivityEnterView.setOpenGifsTabFirst();
                }
            }
        }
    }

    private void openAttachMenu() {
        if (getParentActivity() != null) {
            createChatAttachView();
            this.chatAttachAlert.loadGalleryPhotos();
            if (VERSION.SDK_INT == 21 || VERSION.SDK_INT == 22) {
                this.chatActivityEnterView.closeKeyboard();
            }
            this.chatAttachAlert.init();
            showDialog(this.chatAttachAlert);
        }
    }

    private void checkContextBotPanel() {
        if (!this.allowStickersPanel || this.mentionsAdapter == null || !this.mentionsAdapter.isBotContext()) {
            return;
        }
        if (this.allowContextBotPanel || this.allowContextBotPanelSecond) {
            if (this.mentionContainer.getVisibility() == 4 || this.mentionContainer.getTag() != null) {
                if (this.mentionListAnimation != null) {
                    this.mentionListAnimation.cancel();
                }
                this.mentionContainer.setTag(null);
                this.mentionContainer.setVisibility(0);
                this.mentionListAnimation = new AnimatorSet();
                this.mentionListAnimation.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.mentionContainer, "alpha", new float[]{0.0f, 1.0f})});
                this.mentionListAnimation.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animation) {
                        if (ChatActivity.this.mentionListAnimation != null && ChatActivity.this.mentionListAnimation.equals(animation)) {
                            ChatActivity.this.mentionListAnimation = null;
                        }
                    }

                    public void onAnimationCancel(Animator animation) {
                        if (ChatActivity.this.mentionListAnimation != null && ChatActivity.this.mentionListAnimation.equals(animation)) {
                            ChatActivity.this.mentionListAnimation = null;
                        }
                    }
                });
                this.mentionListAnimation.setDuration(200);
                this.mentionListAnimation.start();
            }
        } else if (this.mentionContainer.getVisibility() == 0 && this.mentionContainer.getTag() == null) {
            if (this.mentionListAnimation != null) {
                this.mentionListAnimation.cancel();
            }
            this.mentionContainer.setTag(Integer.valueOf(1));
            this.mentionListAnimation = new AnimatorSet();
            AnimatorSet animatorSet = this.mentionListAnimation;
            Animator[] animatorArr = new Animator[1];
            animatorArr[0] = ObjectAnimator.ofFloat(this.mentionContainer, "alpha", new float[]{0.0f});
            animatorSet.playTogether(animatorArr);
            this.mentionListAnimation.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {
                    if (ChatActivity.this.mentionListAnimation != null && ChatActivity.this.mentionListAnimation.equals(animation)) {
                        ChatActivity.this.mentionContainer.setVisibility(4);
                        ChatActivity.this.mentionListAnimation = null;
                    }
                }

                public void onAnimationCancel(Animator animation) {
                    if (ChatActivity.this.mentionListAnimation != null && ChatActivity.this.mentionListAnimation.equals(animation)) {
                        ChatActivity.this.mentionListAnimation = null;
                    }
                }
            });
            this.mentionListAnimation.setDuration(200);
            this.mentionListAnimation.start();
        }
    }

    private void hideFloatingDateView(boolean animated) {
        if (this.floatingDateView.getTag() != null && !this.currentFloatingDateOnScreen) {
            if (!this.scrollingFloatingDate || this.currentFloatingTopIsNotMessage) {
                this.floatingDateView.setTag(null);
                if (animated) {
                    this.floatingDateAnimation = new AnimatorSet();
                    this.floatingDateAnimation.setDuration(150);
                    AnimatorSet animatorSet = this.floatingDateAnimation;
                    Animator[] animatorArr = new Animator[1];
                    animatorArr[0] = ObjectAnimator.ofFloat(this.floatingDateView, "alpha", new float[]{0.0f});
                    animatorSet.playTogether(animatorArr);
                    this.floatingDateAnimation.addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animation) {
                            if (animation.equals(ChatActivity.this.floatingDateAnimation)) {
                                ChatActivity.this.floatingDateAnimation = null;
                            }
                        }
                    });
                    this.floatingDateAnimation.setStartDelay(500);
                    this.floatingDateAnimation.start();
                    return;
                }
                if (this.floatingDateAnimation != null) {
                    this.floatingDateAnimation.cancel();
                    this.floatingDateAnimation = null;
                }
                this.floatingDateView.setAlpha(0.0f);
            }
        }
    }

    protected void onRemoveFromParent() {
        MediaController.getInstance().setTextureView(this.videoTextureView, null, null, false);
    }

    protected void setIgnoreAttachOnPause(boolean value) {
        this.ignoreAttachOnPause = value;
    }

    private void checkScrollForLoad(boolean scroll) {
        if (this.chatLayoutManager != null && !this.paused) {
            int firstVisibleItem = this.chatLayoutManager.findFirstVisibleItemPosition();
            int visibleItemCount = firstVisibleItem == -1 ? 0 : Math.abs(this.chatLayoutManager.findLastVisibleItemPosition() - firstVisibleItem) + 1;
            if (visibleItemCount > 0 || this.currentEncryptedChat != null) {
                int checkLoadCount;
                MessagesController instance;
                long j;
                int i;
                int i2;
                int i3;
                boolean isChannel;
                int i4;
                int totalItemCount = this.chatAdapter.getItemCount();
                if (scroll) {
                    checkLoadCount = 25;
                } else {
                    checkLoadCount = 5;
                }
                if ((totalItemCount - firstVisibleItem) - visibleItemCount <= checkLoadCount && !this.loading) {
                    boolean z;
                    if (!this.endReached[0]) {
                        this.loading = true;
                        this.waitingForLoad.add(Integer.valueOf(this.lastLoadIndex));
                        if (this.messagesByDays.size() != 0) {
                            instance = MessagesController.getInstance();
                            j = this.dialog_id;
                            i = this.maxMessageId[0];
                            z = !this.cacheEndReached[0];
                            i2 = this.minDate[0];
                            i3 = this.classGuid;
                            isChannel = ChatObject.isChannel(this.currentChat);
                            i4 = this.lastLoadIndex;
                            this.lastLoadIndex = i4 + 1;
                            instance.loadMessages(j, 50, i, 0, z, i2, i3, 0, 0, isChannel, i4);
                        } else {
                            instance = MessagesController.getInstance();
                            j = this.dialog_id;
                            z = !this.cacheEndReached[0];
                            i2 = this.minDate[0];
                            i3 = this.classGuid;
                            isChannel = ChatObject.isChannel(this.currentChat);
                            i4 = this.lastLoadIndex;
                            this.lastLoadIndex = i4 + 1;
                            instance.loadMessages(j, 50, 0, 0, z, i2, i3, 0, 0, isChannel, i4);
                        }
                    } else if (!(this.mergeDialogId == 0 || this.endReached[1])) {
                        this.loading = true;
                        this.waitingForLoad.add(Integer.valueOf(this.lastLoadIndex));
                        instance = MessagesController.getInstance();
                        j = this.mergeDialogId;
                        i = this.maxMessageId[1];
                        z = !this.cacheEndReached[1];
                        i2 = this.minDate[1];
                        i3 = this.classGuid;
                        isChannel = ChatObject.isChannel(this.currentChat);
                        i4 = this.lastLoadIndex;
                        this.lastLoadIndex = i4 + 1;
                        instance.loadMessages(j, 50, i, 0, z, i2, i3, 0, 0, isChannel, i4);
                    }
                }
                if (visibleItemCount > 0 && !this.loadingForward && firstVisibleItem <= 10) {
                    if (this.mergeDialogId != 0 && !this.forwardEndReached[1]) {
                        this.waitingForLoad.add(Integer.valueOf(this.lastLoadIndex));
                        instance = MessagesController.getInstance();
                        j = this.mergeDialogId;
                        i = this.minMessageId[1];
                        i2 = this.maxDate[1];
                        i3 = this.classGuid;
                        isChannel = ChatObject.isChannel(this.currentChat);
                        i4 = this.lastLoadIndex;
                        this.lastLoadIndex = i4 + 1;
                        instance.loadMessages(j, 50, i, 0, true, i2, i3, 1, 0, isChannel, i4);
                        this.loadingForward = true;
                    } else if (!this.forwardEndReached[0]) {
                        this.waitingForLoad.add(Integer.valueOf(this.lastLoadIndex));
                        instance = MessagesController.getInstance();
                        j = this.dialog_id;
                        i = this.minMessageId[0];
                        i2 = this.maxDate[0];
                        i3 = this.classGuid;
                        isChannel = ChatObject.isChannel(this.currentChat);
                        i4 = this.lastLoadIndex;
                        this.lastLoadIndex = i4 + 1;
                        instance.loadMessages(j, 50, i, 0, true, i2, i3, 1, 0, isChannel, i4);
                        this.loadingForward = true;
                    }
                }
            }
        }
    }

    private void processSelectedAttach(int which) {
        if (which == 0 || which == 1 || which == 4 || which == 2) {
            String action;
            if (this.currentChat != null) {
                if (this.currentChat.participants_count > MessagesController.getInstance().groupBigSize) {
                    if (which == 0 || which == 1) {
                        action = "bigchat_upload_photo";
                    } else {
                        action = "bigchat_upload_document";
                    }
                } else if (which == 0 || which == 1) {
                    action = "chat_upload_photo";
                } else {
                    action = "chat_upload_document";
                }
            } else if (which == 0 || which == 1) {
                action = "pm_upload_photo";
            } else {
                action = "pm_upload_document";
            }
            if (!MessagesController.isFeatureEnabled(action, this)) {
                return;
            }
        }
        if (which == 0) {
            if (VERSION.SDK_INT < 23 || getParentActivity().checkSelfPermission("android.permission.CAMERA") == 0) {
                try {
                    Intent takePictureIntent = new Intent("android.media.action.IMAGE_CAPTURE");
                    File image = AndroidUtilities.generatePicturePath();
                    if (image != null) {
                        if (VERSION.SDK_INT >= 24) {
                            takePictureIntent.putExtra("output", FileProvider.getUriForFile(getParentActivity(), "org.telegram.messenger.provider", image));
                            takePictureIntent.addFlags(2);
                            takePictureIntent.addFlags(1);
                        } else {
                            takePictureIntent.putExtra("output", Uri.fromFile(image));
                        }
                        this.currentPicturePath = image.getAbsolutePath();
                    }
                    startActivityForResult(takePictureIntent, 0);
                    return;
                } catch (Exception e) {
                    FileLog.e(e);
                    return;
                }
            }
            getParentActivity().requestPermissions(new String[]{"android.permission.CAMERA"}, 19);
        } else if (which == 1) {
            if (VERSION.SDK_INT < 23 || getParentActivity().checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == 0) {
                boolean z = this.currentEncryptedChat == null || AndroidUtilities.getPeerLayerVersion(this.currentEncryptedChat.layer) >= 46;
                PhotoAlbumPickerActivity fragment = new PhotoAlbumPickerActivity(false, z, true, this);
                fragment.setDelegate(new PhotoAlbumPickerActivityDelegate() {
                    public void didSelectPhotos(ArrayList<SendMessagesHelper$SendingMediaInfo> photos) {
                        SendMessagesHelper.prepareSendingMedia(photos, ChatActivity.this.dialog_id, ChatActivity.this.replyingMessageObject, null, false, MediaController.getInstance().isGroupPhotosEnabled());
                        ChatActivity.this.showReplyPanel(false, null, null, null, false);
                        DraftQuery.cleanDraft(ChatActivity.this.dialog_id, true);
                    }

                    public void startPhotoSelectActivity() {
                        try {
                            Intent videoPickerIntent = new Intent();
                            videoPickerIntent.setType("video/*");
                            videoPickerIntent.setAction("android.intent.action.GET_CONTENT");
                            videoPickerIntent.putExtra("android.intent.extra.sizeLimit", 1610612736);
                            Intent photoPickerIntent = new Intent("android.intent.action.PICK");
                            photoPickerIntent.setType("image/*");
                            Intent chooserIntent = Intent.createChooser(photoPickerIntent, null);
                            chooserIntent.putExtra("android.intent.extra.INITIAL_INTENTS", new Intent[]{videoPickerIntent});
                            ChatActivity.this.startActivityForResult(chooserIntent, 1);
                        } catch (Exception e) {
                            FileLog.e(e);
                        }
                    }
                });
                presentFragment(fragment);
                return;
            }
            getParentActivity().requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 4);
        } else if (which == 2) {
            if (VERSION.SDK_INT < 23 || getParentActivity().checkSelfPermission("android.permission.CAMERA") == 0) {
                try {
                    Intent takeVideoIntent = new Intent("android.media.action.VIDEO_CAPTURE");
                    File video = AndroidUtilities.generateVideoPath();
                    if (video != null) {
                        if (VERSION.SDK_INT >= 24) {
                            takeVideoIntent.putExtra("output", FileProvider.getUriForFile(getParentActivity(), "org.telegram.messenger.provider", video));
                            takeVideoIntent.addFlags(2);
                            takeVideoIntent.addFlags(1);
                        } else if (VERSION.SDK_INT >= 18) {
                            takeVideoIntent.putExtra("output", Uri.fromFile(video));
                        }
                        takeVideoIntent.putExtra("android.intent.extra.sizeLimit", 1610612736);
                        this.currentPicturePath = video.getAbsolutePath();
                    }
                    startActivityForResult(takeVideoIntent, 2);
                    return;
                } catch (Exception e2) {
                    FileLog.e(e2);
                    return;
                }
            }
            getParentActivity().requestPermissions(new String[]{"android.permission.CAMERA"}, 20);
        } else if (which == 6) {
            if (AndroidUtilities.isGoogleMapsInstalled(this)) {
                LocationActivity fragment2 = new LocationActivity(this.currentEncryptedChat == null ? 1 : 0);
                fragment2.setDialogId(this.dialog_id);
                fragment2.setDelegate(this);
                presentFragment(fragment2);
            }
        } else if (which == 4) {
            if (VERSION.SDK_INT < 23 || getParentActivity().checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == 0) {
                DocumentSelectActivity fragment3 = new DocumentSelectActivity();
                fragment3.setDelegate(new DocumentSelectActivityDelegate() {
                    public void didSelectFiles(DocumentSelectActivity activity, ArrayList<String> files) {
                        activity.finishFragment();
                        SendMessagesHelper.prepareSendingDocuments(files, files, null, null, ChatActivity.this.dialog_id, ChatActivity.this.replyingMessageObject, null);
                        ChatActivity.this.showReplyPanel(false, null, null, null, false);
                        DraftQuery.cleanDraft(ChatActivity.this.dialog_id, true);
                    }

                    public void startDocumentSelectActivity() {
                        try {
                            Intent photoPickerIntent = new Intent("android.intent.action.GET_CONTENT");
                            if (VERSION.SDK_INT >= 18) {
                                photoPickerIntent.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
                            }
                            photoPickerIntent.setType("*/*");
                            ChatActivity.this.startActivityForResult(photoPickerIntent, 21);
                        } catch (Exception e) {
                            FileLog.e(e);
                        }
                    }
                });
                presentFragment(fragment3);
                return;
            }
            getParentActivity().requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 4);
        } else if (which == 3) {
            if (VERSION.SDK_INT < 23 || getParentActivity().checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == 0) {
                AudioSelectActivity fragment4 = new AudioSelectActivity();
                fragment4.setDelegate(new AudioSelectActivityDelegate() {
                    public void didSelectAudio(ArrayList<MessageObject> audios) {
                        SendMessagesHelper.prepareSendingAudioDocuments(audios, ChatActivity.this.dialog_id, ChatActivity.this.replyingMessageObject);
                        ChatActivity.this.showReplyPanel(false, null, null, null, false);
                        DraftQuery.cleanDraft(ChatActivity.this.dialog_id, true);
                    }
                });
                presentFragment(fragment4);
                return;
            }
            getParentActivity().requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 4);
        } else if (which != 5) {
        } else {
            if (VERSION.SDK_INT < 23 || getParentActivity().checkSelfPermission("android.permission.READ_CONTACTS") == 0) {
                try {
                    Intent intent = new Intent("android.intent.action.PICK", Contacts.CONTENT_URI);
                    intent.setType("vnd.android.cursor.dir/phone_v2");
                    startActivityForResult(intent, 31);
                    return;
                } catch (Exception e22) {
                    FileLog.e(e22);
                    return;
                }
            }
            getParentActivity().requestPermissions(new String[]{"android.permission.READ_CONTACTS"}, 5);
        }
    }

    public boolean dismissDialogOnPause(Dialog dialog) {
        return dialog != this.chatAttachAlert && super.dismissDialogOnPause(dialog);
    }

    private void searchLinks(final CharSequence charSequence, final boolean force) {
        if (this.currentEncryptedChat == null || (MessagesController.getInstance().secretWebpagePreview != 0 && AndroidUtilities.getPeerLayerVersion(this.currentEncryptedChat.layer) >= 46)) {
            if (force && this.foundWebPage != null) {
                if (this.foundWebPage.url != null) {
                    int index = TextUtils.indexOf(charSequence, this.foundWebPage.url);
                    char lastChar = '\u0000';
                    boolean lenEqual = false;
                    if (index != -1) {
                        if (this.foundWebPage.url.length() + index == charSequence.length()) {
                            lenEqual = true;
                        } else {
                            lenEqual = false;
                        }
                        if (lenEqual) {
                            lastChar = '\u0000';
                        } else {
                            lastChar = charSequence.charAt(this.foundWebPage.url.length() + index);
                        }
                    } else if (this.foundWebPage.display_url != null) {
                        index = TextUtils.indexOf(charSequence, this.foundWebPage.display_url);
                        if (index == -1 || this.foundWebPage.display_url.length() + index != charSequence.length()) {
                            lenEqual = false;
                        } else {
                            lenEqual = true;
                        }
                        if (index == -1 || lenEqual) {
                            lastChar = '\u0000';
                        } else {
                            lastChar = charSequence.charAt(this.foundWebPage.display_url.length() + index);
                        }
                    }
                    if (index != -1 && (lenEqual || lastChar == ' ' || lastChar == ',' || lastChar == '.' || lastChar == '!' || lastChar == '/')) {
                        return;
                    }
                }
                this.pendingLinkSearchString = null;
                showReplyPanel(false, null, null, this.foundWebPage, false);
            }
            Utilities.searchQueue.postRunnable(new Runnable() {

                class C59381 implements Runnable {
                    C59381() {
                    }

                    public void run() {
                        if (ChatActivity.this.foundWebPage != null) {
                            ChatActivity.this.showReplyPanel(false, null, null, ChatActivity.this.foundWebPage, false);
                            ChatActivity.this.foundWebPage = null;
                        }
                    }
                }

                class C59392 implements Runnable {
                    C59392() {
                    }

                    public void run() {
                        if (ChatActivity.this.foundWebPage != null) {
                            ChatActivity.this.showReplyPanel(false, null, null, ChatActivity.this.foundWebPage, false);
                            ChatActivity.this.foundWebPage = null;
                        }
                    }
                }

                class C59413 implements Runnable {

                    class C59401 implements OnClickListener {
                        C59401() {
                        }

                        public void onClick(DialogInterface dialog, int which) {
                            MessagesController.getInstance().secretWebpagePreview = 1;
                            ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).edit().putInt("secretWebpage2", MessagesController.getInstance().secretWebpagePreview).commit();
                            ChatActivity.this.foundUrls = null;
                            ChatActivity.this.searchLinks(charSequence, force);
                        }
                    }

                    C59413() {
                    }

                    public void run() {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ChatActivity.this.getParentActivity());
                        builder.setTitle(ApplicationLoader.getConfig().getAppName());
                        builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new C59401());
                        builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
                        builder.setMessage(LocaleController.getAppNameString("SecretLinkPreviewAlert", R.string.SecretLinkPreviewAlert));
                        ChatActivity.this.showDialog(builder.create());
                        MessagesController.getInstance().secretWebpagePreview = 0;
                        ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).edit().putInt("secretWebpage2", MessagesController.getInstance().secretWebpagePreview).commit();
                    }
                }

                public void run() {
                    Exception e;
                    CharSequence textToCheck;
                    final TLRPC$TL_messages_getWebPagePreview req;
                    if (ChatActivity.this.linkSearchRequestId != 0) {
                        ConnectionsManager.getInstance().cancelRequest(ChatActivity.this.linkSearchRequestId, true);
                        ChatActivity.this.linkSearchRequestId = 0;
                    }
                    try {
                        ArrayList<CharSequence> urls;
                        Matcher m = AndroidUtilities.WEB_URL.matcher(charSequence);
                        ArrayList<CharSequence> urls2 = null;
                        while (m.find()) {
                            try {
                                if (m.start() <= 0 || charSequence.charAt(m.start() - 1) != '@') {
                                    if (urls2 == null) {
                                        urls = new ArrayList();
                                    } else {
                                        urls = urls2;
                                    }
                                    urls.add(charSequence.subSequence(m.start(), m.end()));
                                    urls2 = urls;
                                }
                            } catch (Exception e2) {
                                e = e2;
                                urls = urls2;
                            }
                        }
                        if (urls2 != null) {
                            if (ChatActivity.this.foundUrls != null && urls2.size() == ChatActivity.this.foundUrls.size()) {
                                boolean clear = true;
                                for (int a = 0; a < urls2.size(); a++) {
                                    if (!TextUtils.equals((CharSequence) urls2.get(a), (CharSequence) ChatActivity.this.foundUrls.get(a))) {
                                        clear = false;
                                    }
                                }
                                if (clear) {
                                    urls = urls2;
                                    return;
                                }
                            }
                        }
                        ChatActivity.this.foundUrls = urls2;
                        if (urls2 == null) {
                            AndroidUtilities.runOnUIThread(new C59381());
                            urls = urls2;
                            return;
                        }
                        textToCheck = TextUtils.join(" ", urls2);
                        urls = urls2;
                        if (ChatActivity.this.currentEncryptedChat == null && MessagesController.getInstance().secretWebpagePreview == 2) {
                            AndroidUtilities.runOnUIThread(new C59413());
                            return;
                        }
                        req = new TLRPC$TL_messages_getWebPagePreview();
                        if (textToCheck instanceof String) {
                            req.message = textToCheck.toString();
                        } else {
                            req.message = (String) textToCheck;
                        }
                        ChatActivity.this.linkSearchRequestId = ConnectionsManager.getInstance().sendRequest(req, new RequestDelegate() {
                            public void run(final TLObject response, final TLRPC$TL_error error) {
                                AndroidUtilities.runOnUIThread(new Runnable() {
                                    public void run() {
                                        ChatActivity.this.linkSearchRequestId = 0;
                                        if (error != null) {
                                            return;
                                        }
                                        if (response instanceof TLRPC$TL_messageMediaWebPage) {
                                            ChatActivity.this.foundWebPage = ((TLRPC$TL_messageMediaWebPage) response).webpage;
                                            if ((ChatActivity.this.foundWebPage instanceof TLRPC$TL_webPage) || (ChatActivity.this.foundWebPage instanceof TLRPC$TL_webPagePending)) {
                                                if (ChatActivity.this.foundWebPage instanceof TLRPC$TL_webPagePending) {
                                                    ChatActivity.this.pendingLinkSearchString = req.message;
                                                }
                                                if (ChatActivity.this.currentEncryptedChat != null && (ChatActivity.this.foundWebPage instanceof TLRPC$TL_webPagePending)) {
                                                    ChatActivity.this.foundWebPage.url = req.message;
                                                }
                                                ChatActivity.this.showReplyPanel(true, null, null, ChatActivity.this.foundWebPage, false);
                                            } else if (ChatActivity.this.foundWebPage != null) {
                                                ChatActivity.this.showReplyPanel(false, null, null, ChatActivity.this.foundWebPage, false);
                                                ChatActivity.this.foundWebPage = null;
                                            }
                                        } else if (ChatActivity.this.foundWebPage != null) {
                                            ChatActivity.this.showReplyPanel(false, null, null, ChatActivity.this.foundWebPage, false);
                                            ChatActivity.this.foundWebPage = null;
                                        }
                                    }
                                });
                            }
                        });
                        ConnectionsManager.getInstance().bindRequestToGuid(ChatActivity.this.linkSearchRequestId, ChatActivity.this.classGuid);
                    } catch (Exception e3) {
                        e = e3;
                    }
                    FileLog.e(e);
                    String text = charSequence.toString().toLowerCase();
                    if (charSequence.length() < 13 || !(text.contains("http://") || text.contains("https://"))) {
                        AndroidUtilities.runOnUIThread(new C59392());
                        return;
                    }
                    textToCheck = charSequence;
                    if (ChatActivity.this.currentEncryptedChat == null) {
                    }
                    req = new TLRPC$TL_messages_getWebPagePreview();
                    if (textToCheck instanceof String) {
                        req.message = textToCheck.toString();
                    } else {
                        req.message = (String) textToCheck;
                    }
                    ChatActivity.this.linkSearchRequestId = ConnectionsManager.getInstance().sendRequest(req, /* anonymous class already generated */);
                    ConnectionsManager.getInstance().bindRequestToGuid(ChatActivity.this.linkSearchRequestId, ChatActivity.this.classGuid);
                }
            });
        }
    }

    private void forwardMessages(ArrayList<MessageObject> arrayList, boolean fromMyName) {
        if (arrayList != null && !arrayList.isEmpty()) {
            if (fromMyName) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    SendMessagesHelper.getInstance().processForwardFromMyName((MessageObject) it.next(), this.dialog_id);
                }
                return;
            }
            AlertsCreator.showSendMediaAlert(SendMessagesHelper.getInstance().sendMessage(arrayList, this.dialog_id), this);
        }
    }

    public void showReplyPanel(boolean show, MessageObject messageObjectToReply, ArrayList<MessageObject> messageObjectsToForward, TLRPC$WebPage webPage, boolean cancel) {
        if (this.chatActivityEnterView != null) {
            if (show) {
                if (messageObjectToReply != null || messageObjectsToForward != null || webPage != null) {
                    if (this.searchItem != null && this.actionBar.isSearchFieldVisible()) {
                        this.actionBar.closeSearchField(false);
                        this.chatActivityEnterView.setFieldFocused();
                        AndroidUtilities.runOnUIThread(new Runnable() {
                            public void run() {
                                if (ChatActivity.this.chatActivityEnterView != null) {
                                    ChatActivity.this.chatActivityEnterView.openKeyboard();
                                }
                            }
                        }, 100);
                    }
                    boolean openKeyboard = false;
                    if (!(messageObjectToReply == null || messageObjectToReply.getDialogId() == this.dialog_id)) {
                        messageObjectsToForward = new ArrayList();
                        messageObjectsToForward.add(messageObjectToReply);
                        messageObjectToReply = null;
                        openKeyboard = true;
                    }
                    User user;
                    String name;
                    TLRPC$Chat chat;
                    String mess;
                    if (messageObjectToReply != null) {
                        this.forwardingMessages = null;
                        this.replyingMessageObject = messageObjectToReply;
                        this.chatActivityEnterView.setReplyingMessageObject(messageObjectToReply);
                        if (this.foundWebPage == null) {
                            if (messageObjectToReply.isFromUser()) {
                                user = MessagesController.getInstance().getUser(Integer.valueOf(messageObjectToReply.messageOwner.from_id));
                                if (user != null) {
                                    name = UserObject.getUserName(user);
                                } else {
                                    return;
                                }
                            }
                            chat = MessagesController.getInstance().getChat(Integer.valueOf(messageObjectToReply.messageOwner.to_id.channel_id));
                            if (chat != null) {
                                name = chat.title;
                            } else {
                                return;
                            }
                            this.replyIconImageView.setImageResource(R.drawable.msg_panel_reply);
                            this.replyNameTextView.setText(name);
                            if (messageObjectToReply.messageOwner.media instanceof TLRPC$TL_messageMediaGame) {
                                this.replyObjectTextView.setText(Emoji.replaceEmoji(messageObjectToReply.messageOwner.media.game.title, this.replyObjectTextView.getPaint().getFontMetricsInt(), AndroidUtilities.dp(14.0f), false));
                            } else if (messageObjectToReply.messageText != null) {
                                mess = messageObjectToReply.messageText.toString();
                                if (mess.length() > 150) {
                                    mess = mess.substring(0, 150);
                                }
                                this.replyObjectTextView.setText(Emoji.replaceEmoji(mess.replace('\n', ' '), this.replyObjectTextView.getPaint().getFontMetricsInt(), AndroidUtilities.dp(14.0f), false));
                            }
                        } else {
                            return;
                        }
                    } else if (messageObjectsToForward == null) {
                        this.replyIconImageView.setImageResource(R.drawable.msg_panel_link);
                        if (webPage instanceof TLRPC$TL_webPagePending) {
                            this.replyNameTextView.setText(LocaleController.getString("GettingLinkInfo", R.string.GettingLinkInfo));
                            this.replyObjectTextView.setText(this.pendingLinkSearchString);
                        } else {
                            if (webPage.site_name != null) {
                                this.replyNameTextView.setText(webPage.site_name);
                            } else if (webPage.title != null) {
                                this.replyNameTextView.setText(webPage.title);
                            } else {
                                this.replyNameTextView.setText(LocaleController.getString("LinkPreview", R.string.LinkPreview));
                            }
                            if (webPage.title != null) {
                                this.replyObjectTextView.setText(webPage.title);
                            } else if (webPage.description != null) {
                                this.replyObjectTextView.setText(webPage.description);
                            } else if (webPage.author != null) {
                                this.replyObjectTextView.setText(webPage.author);
                            } else {
                                this.replyObjectTextView.setText(webPage.display_url);
                            }
                            this.chatActivityEnterView.setWebPage(webPage, true);
                        }
                    } else if (!messageObjectsToForward.isEmpty()) {
                        this.replyingMessageObject = null;
                        this.chatActivityEnterView.setReplyingMessageObject(null);
                        this.forwardingMessages = messageObjectsToForward;
                        if (this.foundWebPage == null) {
                            int a;
                            Integer uid;
                            this.chatActivityEnterView.setForceShowSendButton(true, false);
                            ArrayList<Integer> uids = new ArrayList();
                            this.replyIconImageView.setImageResource(R.drawable.msg_panel_forward);
                            MessageObject object = (MessageObject) messageObjectsToForward.get(0);
                            if (object.isFromUser()) {
                                uids.add(Integer.valueOf(object.messageOwner.from_id));
                            } else {
                                uids.add(Integer.valueOf(-object.messageOwner.to_id.channel_id));
                            }
                            int type = ((MessageObject) messageObjectsToForward.get(0)).type;
                            for (a = 1; a < messageObjectsToForward.size(); a++) {
                                object = (MessageObject) messageObjectsToForward.get(a);
                                if (object.isFromUser()) {
                                    uid = Integer.valueOf(object.messageOwner.from_id);
                                } else {
                                    uid = Integer.valueOf(-object.messageOwner.to_id.channel_id);
                                }
                                if (!uids.contains(uid)) {
                                    uids.add(uid);
                                }
                                if (((MessageObject) messageObjectsToForward.get(a)).type != type) {
                                    type = -1;
                                }
                            }
                            StringBuilder userNames = new StringBuilder();
                            for (a = 0; a < uids.size(); a++) {
                                uid = (Integer) uids.get(a);
                                chat = null;
                                user = null;
                                if (uid.intValue() > 0) {
                                    user = MessagesController.getInstance().getUser(uid);
                                } else {
                                    chat = MessagesController.getInstance().getChat(Integer.valueOf(-uid.intValue()));
                                }
                                if (user != null || chat != null) {
                                    if (uids.size() != 1) {
                                        if (uids.size() != 2 && userNames.length() != 0) {
                                            userNames.append(" ");
                                            userNames.append(LocaleController.formatPluralString("AndOther", uids.size() - 1));
                                            break;
                                        }
                                        if (userNames.length() > 0) {
                                            userNames.append(", ");
                                        }
                                        if (user == null) {
                                            userNames.append(chat.title);
                                        } else if (!TextUtils.isEmpty(user.first_name)) {
                                            userNames.append(user.first_name);
                                        } else if (TextUtils.isEmpty(user.last_name)) {
                                            userNames.append(" ");
                                        } else {
                                            userNames.append(user.last_name);
                                        }
                                    } else if (user != null) {
                                        userNames.append(UserObject.getUserName(user));
                                    } else {
                                        userNames.append(chat.title);
                                    }
                                }
                            }
                            this.replyNameTextView.setText(userNames);
                            if (type == -1 || type == 0 || type == 10 || type == 11) {
                                if (messageObjectsToForward.size() != 1 || ((MessageObject) messageObjectsToForward.get(0)).messageText == null) {
                                    this.replyObjectTextView.setText(LocaleController.formatPluralString("ForwardedMessageCount", messageObjectsToForward.size()));
                                } else {
                                    MessageObject messageObject = (MessageObject) messageObjectsToForward.get(0);
                                    if (messageObject.messageOwner.media instanceof TLRPC$TL_messageMediaGame) {
                                        this.replyObjectTextView.setText(Emoji.replaceEmoji(messageObject.messageOwner.media.game.title, this.replyObjectTextView.getPaint().getFontMetricsInt(), AndroidUtilities.dp(14.0f), false));
                                    } else {
                                        mess = messageObject.messageText.toString();
                                        if (mess.length() > 150) {
                                            mess = mess.substring(0, 150);
                                        }
                                        this.replyObjectTextView.setText(Emoji.replaceEmoji(mess.replace('\n', ' '), this.replyObjectTextView.getPaint().getFontMetricsInt(), AndroidUtilities.dp(14.0f), false));
                                    }
                                }
                            } else if (type == 1) {
                                this.replyObjectTextView.setText(LocaleController.formatPluralString("ForwardedPhoto", messageObjectsToForward.size()));
                                if (messageObjectsToForward.size() == 1) {
                                    messageObjectToReply = (MessageObject) messageObjectsToForward.get(0);
                                }
                            } else if (type == 4) {
                                this.replyObjectTextView.setText(LocaleController.formatPluralString("ForwardedLocation", messageObjectsToForward.size()));
                            } else if (type == 3) {
                                this.replyObjectTextView.setText(LocaleController.formatPluralString("ForwardedVideo", messageObjectsToForward.size()));
                                if (messageObjectsToForward.size() == 1) {
                                    messageObjectToReply = (MessageObject) messageObjectsToForward.get(0);
                                }
                            } else if (type == 12) {
                                this.replyObjectTextView.setText(LocaleController.formatPluralString("ForwardedContact", messageObjectsToForward.size()));
                            } else if (type == 2) {
                                this.replyObjectTextView.setText(LocaleController.formatPluralString("ForwardedAudio", messageObjectsToForward.size()));
                            } else if (type == 5) {
                                this.replyObjectTextView.setText(LocaleController.formatPluralString("ForwardedRound", messageObjectsToForward.size()));
                            } else if (type == 14) {
                                this.replyObjectTextView.setText(LocaleController.formatPluralString("ForwardedMusic", messageObjectsToForward.size()));
                            } else if (type == 13) {
                                this.replyObjectTextView.setText(LocaleController.formatPluralString("ForwardedSticker", messageObjectsToForward.size()));
                            } else if (type == 8 || type == 9) {
                                if (messageObjectsToForward.size() != 1) {
                                    this.replyObjectTextView.setText(LocaleController.formatPluralString("ForwardedFile", messageObjectsToForward.size()));
                                } else if (type == 8) {
                                    this.replyObjectTextView.setText(LocaleController.getString("AttachGif", R.string.AttachGif));
                                } else {
                                    name = FileLoader.getDocumentFileName(((MessageObject) messageObjectsToForward.get(0)).getDocument());
                                    if (name.length() != 0) {
                                        this.replyObjectTextView.setText(name);
                                    }
                                    messageObjectToReply = (MessageObject) messageObjectsToForward.get(0);
                                }
                            }
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                    FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) this.replyNameTextView.getLayoutParams();
                    FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.replyObjectTextView.getLayoutParams();
                    TLRPC$PhotoSize photoSize = null;
                    if (messageObjectToReply != null) {
                        photoSize = FileLoader.getClosestPhotoSizeWithSize(messageObjectToReply.photoThumbs2, 80);
                        if (photoSize == null) {
                            photoSize = FileLoader.getClosestPhotoSizeWithSize(messageObjectToReply.photoThumbs, 80);
                        }
                    }
                    int dp;
                    if (photoSize == null || (photoSize instanceof TLRPC$TL_photoSizeEmpty) || (photoSize.location instanceof TLRPC$TL_fileLocationUnavailable) || messageObjectToReply.type == 13 || (messageObjectToReply != null && messageObjectToReply.isSecretMedia())) {
                        this.replyImageView.setImageBitmap(null);
                        this.replyImageLocation = null;
                        this.replyImageView.setVisibility(4);
                        dp = AndroidUtilities.dp(52.0f);
                        layoutParams2.leftMargin = dp;
                        layoutParams1.leftMargin = dp;
                    } else {
                        if (messageObjectToReply.isRoundVideo()) {
                            this.replyImageView.setRoundRadius(AndroidUtilities.dp(17.0f));
                        } else {
                            this.replyImageView.setRoundRadius(0);
                        }
                        this.replyImageLocation = photoSize.location;
                        this.replyImageView.setImage(this.replyImageLocation, "50_50", (Drawable) null);
                        this.replyImageView.setVisibility(0);
                        dp = AndroidUtilities.dp(96.0f);
                        layoutParams2.leftMargin = dp;
                        layoutParams1.leftMargin = dp;
                    }
                    this.replyNameTextView.setLayoutParams(layoutParams1);
                    this.replyObjectTextView.setLayoutParams(layoutParams2);
                    this.chatActivityEnterView.showTopView(false, openKeyboard);
                }
            } else if (this.replyingMessageObject != null || this.forwardingMessages != null || this.foundWebPage != null) {
                if (this.replyingMessageObject != null && (this.replyingMessageObject.messageOwner.reply_markup instanceof TLRPC$TL_replyKeyboardForceReply)) {
                    ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).edit().putInt("answered_" + this.dialog_id, this.replyingMessageObject.getId()).commit();
                }
                if (this.foundWebPage != null) {
                    this.foundWebPage = null;
                    this.chatActivityEnterView.setWebPage(null, !cancel);
                    if (!(webPage == null || (this.replyingMessageObject == null && this.forwardingMessages == null))) {
                        showReplyPanel(true, this.replyingMessageObject, this.forwardingMessages, null, false);
                        return;
                    }
                }
                if (this.forwardingMessages != null) {
                    forwardMessages(this.forwardingMessages, false);
                }
                this.chatActivityEnterView.setForceShowSendButton(false, false);
                this.chatActivityEnterView.hideTopView(false);
                this.chatActivityEnterView.setReplyingMessageObject(null);
                this.replyingMessageObject = null;
                this.forwardingMessages = null;
                this.replyImageLocation = null;
            }
        }
    }

    private void moveScrollToLastMessage() {
        if (this.chatListView != null && !this.messages.isEmpty()) {
            this.chatLayoutManager.scrollToPositionWithOffset(0, 0);
        }
    }

    private boolean sendSecretMessageRead(MessageObject messageObject) {
        int i = 0;
        if (messageObject == null || messageObject.isOut() || !messageObject.isSecretMedia() || messageObject.messageOwner.destroyTime != 0 || messageObject.messageOwner.ttl <= 0) {
            return false;
        }
        if (this.currentEncryptedChat != null) {
            MessagesController.getInstance().markMessageAsRead(this.dialog_id, messageObject.messageOwner.random_id, messageObject.messageOwner.ttl);
        } else {
            MessagesController instance = MessagesController.getInstance();
            int id = messageObject.getId();
            if (ChatObject.isChannel(this.currentChat)) {
                i = this.currentChat.id;
            }
            instance.markMessageAsRead(id, i, messageObject.messageOwner.ttl);
        }
        messageObject.messageOwner.destroyTime = messageObject.messageOwner.ttl + ConnectionsManager.getInstance().getCurrentTime();
        return true;
    }

    private void clearChatData() {
        this.messages.clear();
        this.messagesByDays.clear();
        this.waitingForLoad.clear();
        this.groupedMessagesMap.clear();
        this.progressView.setVisibility(this.chatAdapter.botInfoRow == -1 ? 0 : 4);
        this.chatListView.setEmptyView(null);
        for (int a = 0; a < 2; a++) {
            this.messagesDict[a].clear();
            if (this.currentEncryptedChat == null) {
                this.maxMessageId[a] = Integer.MAX_VALUE;
                this.minMessageId[a] = Integer.MIN_VALUE;
            } else {
                this.maxMessageId[a] = Integer.MIN_VALUE;
                this.minMessageId[a] = Integer.MAX_VALUE;
            }
            this.maxDate[a] = Integer.MIN_VALUE;
            this.minDate[a] = 0;
            this.endReached[a] = false;
            this.cacheEndReached[a] = false;
            this.forwardEndReached[a] = true;
        }
        this.first = true;
        this.firstLoading = true;
        this.loading = true;
        this.loadingForward = false;
        this.waitingForReplyMessageLoad = false;
        this.startLoadFromMessageId = 0;
        this.last_message_id = 0;
        this.unreadMessageObject = null;
        this.createUnreadMessageAfterId = 0;
        this.createUnreadMessageAfterIdLoading = false;
        this.needSelectFromMessageId = false;
        this.chatAdapter.notifyDataSetChanged();
    }

    private void scrollToLastMessage(boolean pagedown) {
        if (!this.forwardEndReached[0] || this.first_unread_id != 0 || this.startLoadFromMessageId != 0) {
            clearChatData();
            this.waitingForLoad.add(Integer.valueOf(this.lastLoadIndex));
            MessagesController instance = MessagesController.getInstance();
            long j = this.dialog_id;
            int i = this.classGuid;
            boolean isChannel = ChatObject.isChannel(this.currentChat);
            int i2 = this.lastLoadIndex;
            this.lastLoadIndex = i2 + 1;
            instance.loadMessages(j, 30, 0, 0, true, 0, i, 0, 0, isChannel, i2);
        } else if (pagedown && this.chatLayoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
            showPagedownButton(false, true);
            this.highlightMessageId = Integer.MAX_VALUE;
            updateVisibleRows();
        } else {
            this.chatLayoutManager.scrollToPositionWithOffset(0, 0);
        }
    }

    private void updateTextureViewPosition() {
        if (this.fragmentView != null) {
            MessageObject messageObject;
            boolean foundTextureViewMessage = false;
            int count = this.chatListView.getChildCount();
            int additionalTop = this.chatActivityEnterView.isTopViewVisible() ? AndroidUtilities.dp(48.0f) : 0;
            for (int a = 0; a < count; a++) {
                View view = this.chatListView.getChildAt(a);
                if (view instanceof ChatMessageCell) {
                    ChatMessageCell messageCell = (ChatMessageCell) view;
                    messageObject = messageCell.getMessageObject();
                    if (this.roundVideoContainer != null && messageObject.isRoundVideo() && MediaController.getInstance().isPlayingMessage(messageObject)) {
                        ImageReceiver imageReceiver = messageCell.getPhotoImage();
                        this.roundVideoContainer.setTranslationX((float) imageReceiver.getImageX());
                        this.roundVideoContainer.setTranslationY((float) (((this.fragmentView.getPaddingTop() + messageCell.getTop()) + imageReceiver.getImageY()) - additionalTop));
                        this.fragmentView.invalidate();
                        this.roundVideoContainer.invalidate();
                        foundTextureViewMessage = true;
                        break;
                    }
                }
            }
            if (this.roundVideoContainer != null) {
                messageObject = MediaController.getInstance().getPlayingMessageObject();
                if (messageObject.eventId != 0) {
                    return;
                }
                if (foundTextureViewMessage) {
                    MediaController.getInstance().setCurrentRoundVisible(true);
                    scrollToMessageId(messageObject.getId(), 0, false, 0, true);
                    return;
                }
                this.roundVideoContainer.setTranslationY((float) ((-AndroidUtilities.roundMessageSize) - 100));
                this.fragmentView.invalidate();
                if (messageObject != null && messageObject.isRoundVideo()) {
                    if (this.checkTextureViewPosition || PipRoundVideoView.getInstance() != null) {
                        MediaController.getInstance().setCurrentRoundVisible(false);
                    } else {
                        scrollToMessageId(messageObject.getId(), 0, false, 0, true);
                    }
                }
            }
        }
    }

    private void updateMessagesVisisblePart() {
        if (this.chatListView != null) {
            MessageObject messageObject;
            int count = this.chatListView.getChildCount();
            int additionalTop = this.chatActivityEnterView.isTopViewVisible() ? AndroidUtilities.dp(48.0f) : 0;
            int height = this.chatListView.getMeasuredHeight();
            int minPositionHolder = Integer.MAX_VALUE;
            int minPositionDateHolder = Integer.MAX_VALUE;
            View minDateChild = null;
            View minChild = null;
            View minMessageChild = null;
            boolean foundTextureViewMessage = false;
            for (int a = 0; a < count; a++) {
                View view = this.chatListView.getChildAt(a);
                if (view instanceof ChatMessageCell) {
                    int viewTop;
                    ChatMessageCell messageCell = (ChatMessageCell) view;
                    int top = messageCell.getTop();
                    int bottom = messageCell.getBottom();
                    if (top >= 0) {
                        viewTop = 0;
                    } else {
                        viewTop = -top;
                    }
                    int viewBottom = messageCell.getMeasuredHeight();
                    if (viewBottom > height) {
                        viewBottom = viewTop + height;
                    }
                    messageCell.setVisiblePart(viewTop, viewBottom - viewTop);
                    messageObject = messageCell.getMessageObject();
                    if (this.roundVideoContainer != null && messageObject.isRoundVideo() && MediaController.getInstance().isPlayingMessage(messageObject)) {
                        ImageReceiver imageReceiver = messageCell.getPhotoImage();
                        this.roundVideoContainer.setTranslationX((float) imageReceiver.getImageX());
                        this.roundVideoContainer.setTranslationY((float) (((this.fragmentView.getPaddingTop() + top) + imageReceiver.getImageY()) - additionalTop));
                        this.fragmentView.invalidate();
                        this.roundVideoContainer.invalidate();
                        foundTextureViewMessage = true;
                    }
                }
                if (view.getBottom() > this.chatListView.getPaddingTop()) {
                    int position = view.getBottom();
                    if (position < minPositionHolder) {
                        minPositionHolder = position;
                        if ((view instanceof ChatMessageCell) || (view instanceof ChatActionCell)) {
                            minMessageChild = view;
                        }
                        minChild = view;
                    }
                    if ((view instanceof ChatActionCell) && ((ChatActionCell) view).getMessageObject().isDateObject) {
                        if (view.getAlpha() != 1.0f) {
                            view.setAlpha(1.0f);
                        }
                        if (position < minPositionDateHolder) {
                            minPositionDateHolder = position;
                            minDateChild = view;
                        }
                    }
                }
            }
            if (this.roundVideoContainer != null) {
                if (foundTextureViewMessage) {
                    MediaController.getInstance().setCurrentRoundVisible(true);
                } else {
                    this.roundVideoContainer.setTranslationY((float) ((-AndroidUtilities.roundMessageSize) - 100));
                    this.fragmentView.invalidate();
                    messageObject = MediaController.getInstance().getPlayingMessageObject();
                    if (messageObject != null && messageObject.isRoundVideo() && messageObject.eventId == 0 && this.checkTextureViewPosition) {
                        MediaController.getInstance().setCurrentRoundVisible(false);
                    }
                }
            }
            if (minMessageChild != null) {
                if (minMessageChild instanceof ChatMessageCell) {
                    messageObject = ((ChatMessageCell) minMessageChild).getMessageObject();
                } else {
                    messageObject = ((ChatActionCell) minMessageChild).getMessageObject();
                }
                this.floatingDateView.setCustomDate(messageObject.messageOwner.date);
            }
            this.currentFloatingDateOnScreen = false;
            boolean z = ((minChild instanceof ChatMessageCell) || (minChild instanceof ChatActionCell)) ? false : true;
            this.currentFloatingTopIsNotMessage = z;
            if (minDateChild != null) {
                if (minDateChild.getTop() > this.chatListView.getPaddingTop() || this.currentFloatingTopIsNotMessage) {
                    if (minDateChild.getAlpha() != 1.0f) {
                        minDateChild.setAlpha(1.0f);
                    }
                    if (this.currentFloatingTopIsNotMessage) {
                        z = false;
                    } else {
                        z = true;
                    }
                    hideFloatingDateView(z);
                } else {
                    if (minDateChild.getAlpha() != 0.0f) {
                        minDateChild.setAlpha(0.0f);
                    }
                    if (this.floatingDateAnimation != null) {
                        this.floatingDateAnimation.cancel();
                        this.floatingDateAnimation = null;
                    }
                    if (this.floatingDateView.getTag() == null) {
                        this.floatingDateView.setTag(Integer.valueOf(1));
                    }
                    if (this.floatingDateView.getAlpha() != 1.0f) {
                        this.floatingDateView.setAlpha(1.0f);
                    }
                    this.currentFloatingDateOnScreen = true;
                }
                int offset = minDateChild.getBottom() - this.chatListView.getPaddingTop();
                if (offset <= this.floatingDateView.getMeasuredHeight() || offset >= this.floatingDateView.getMeasuredHeight() * 2) {
                    this.floatingDateView.setTranslationY(0.0f);
                    return;
                } else {
                    this.floatingDateView.setTranslationY((float) (((-this.floatingDateView.getMeasuredHeight()) * 2) + offset));
                    return;
                }
            }
            hideFloatingDateView(true);
            this.floatingDateView.setTranslationY(0.0f);
        }
    }

    private void toggleMute(boolean instant) {
        Editor editor;
        TLRPC$TL_dialog dialog;
        if (MessagesController.getInstance().isDialogMuted(this.dialog_id)) {
            editor = ApplicationLoader.applicationContext.getSharedPreferences("Notifications", 0).edit();
            editor.putInt("notify2_" + this.dialog_id, 0);
            MessagesStorage.getInstance().setDialogFlags(this.dialog_id, 0);
            editor.commit();
            dialog = (TLRPC$TL_dialog) MessagesController.getInstance().dialogs_dict.get(Long.valueOf(this.dialog_id));
            if (dialog != null) {
                dialog.notify_settings = new TLRPC$TL_peerNotifySettings();
            }
            NotificationsController.updateServerNotificationsSettings(this.dialog_id);
        } else if (instant) {
            editor = ApplicationLoader.applicationContext.getSharedPreferences("Notifications", 0).edit();
            editor.putInt("notify2_" + this.dialog_id, 2);
            MessagesStorage.getInstance().setDialogFlags(this.dialog_id, 1);
            editor.commit();
            dialog = (TLRPC$TL_dialog) MessagesController.getInstance().dialogs_dict.get(Long.valueOf(this.dialog_id));
            if (dialog != null) {
                dialog.notify_settings = new TLRPC$TL_peerNotifySettings();
                dialog.notify_settings.mute_until = Integer.MAX_VALUE;
            }
            NotificationsController.updateServerNotificationsSettings(this.dialog_id);
            NotificationsController.getInstance().removeNotificationsForDialog(this.dialog_id);
        } else {
            showDialog(AlertsCreator.createMuteAlert(getParentActivity(), this.dialog_id));
        }
    }

    private int getScrollOffsetForMessage(MessageObject object) {
        int offset = Integer.MAX_VALUE;
        GroupedMessages groupedMessages = getValidGroupedMessage(object);
        if (groupedMessages != null) {
            float itemHeight;
            GroupedMessagePosition currentPosition = (GroupedMessagePosition) groupedMessages.positions.get(object);
            float maxH = ((float) Math.max(AndroidUtilities.displaySize.x, AndroidUtilities.displaySize.y)) * 0.5f;
            if (currentPosition.siblingHeights != null) {
                itemHeight = currentPosition.siblingHeights[0];
            } else {
                itemHeight = currentPosition.ph;
            }
            float totalHeight = 0.0f;
            float moveDiff = 0.0f;
            SparseBooleanArray array = new SparseBooleanArray();
            for (int a = 0; a < groupedMessages.posArray.size(); a++) {
                GroupedMessagePosition pos = (GroupedMessagePosition) groupedMessages.posArray.get(a);
                if (array.indexOfKey(pos.minY) < 0 && pos.siblingHeights == null) {
                    array.put(pos.minY, true);
                    if (pos.minY < currentPosition.minY) {
                        moveDiff -= pos.ph;
                    } else if (pos.minY > currentPosition.minY) {
                        moveDiff += pos.ph;
                    }
                    totalHeight += pos.ph;
                }
            }
            if (Math.abs(totalHeight - itemHeight) < 0.02f) {
                offset = ((((int) (((float) this.chatListView.getMeasuredHeight()) - (totalHeight * maxH))) / 2) - this.chatListView.getPaddingTop()) - AndroidUtilities.dp(7.0f);
            } else {
                offset = ((((int) (((float) this.chatListView.getMeasuredHeight()) - ((itemHeight + moveDiff) * maxH))) / 2) - this.chatListView.getPaddingTop()) - AndroidUtilities.dp(7.0f);
            }
        }
        if (offset == Integer.MAX_VALUE) {
            offset = (this.chatListView.getMeasuredHeight() - object.getApproximateHeight()) / 2;
        }
        return Math.max(0, offset);
    }

    public void scrollToMessageId(int id, int fromMessageId, boolean select, int loadIndex, boolean smooth) {
        MessageObject object = (MessageObject) this.messagesDict[loadIndex].get(Integer.valueOf(id));
        boolean query = false;
        if (object == null) {
            query = true;
        } else if (this.messages.indexOf(object) != -1) {
            if (select) {
                this.highlightMessageId = id;
            } else {
                this.highlightMessageId = Integer.MAX_VALUE;
            }
            int yOffset = getScrollOffsetForMessage(object);
            if (smooth) {
                if (this.messages.get(this.messages.size() - 1) == object) {
                    this.chatListView.smoothScrollToPosition(this.chatAdapter.getItemCount() - 1);
                } else {
                    this.chatListView.smoothScrollToPosition(this.chatAdapter.messagesStartRow + this.messages.indexOf(object));
                }
            } else if (this.messages.get(this.messages.size() - 1) == object) {
                this.chatLayoutManager.scrollToPositionWithOffset(this.chatAdapter.getItemCount() - 1, yOffset, false);
            } else {
                this.chatLayoutManager.scrollToPositionWithOffset(this.chatAdapter.messagesStartRow + this.messages.indexOf(object), yOffset, false);
            }
            updateVisibleRows();
            boolean found = false;
            int count = this.chatListView.getChildCount();
            for (int a = 0; a < count; a++) {
                View view = this.chatListView.getChildAt(a);
                MessageObject messageObject;
                if (view instanceof ChatMessageCell) {
                    messageObject = ((ChatMessageCell) view).getMessageObject();
                    if (messageObject != null && messageObject.getId() == object.getId()) {
                        found = true;
                        break;
                    }
                } else if (view instanceof ChatActionCell) {
                    messageObject = ((ChatActionCell) view).getMessageObject();
                    if (messageObject != null && messageObject.getId() == object.getId()) {
                        found = true;
                        break;
                    }
                } else {
                    continue;
                }
            }
            if (!found) {
                showPagedownButton(true, true);
            }
        } else {
            query = true;
        }
        if (query) {
            if (this.currentEncryptedChat == null || MessagesStorage.getInstance().checkMessageId(this.dialog_id, this.startLoadFromMessageId)) {
                this.waitingForLoad.clear();
                this.waitingForReplyMessageLoad = true;
                this.highlightMessageId = Integer.MAX_VALUE;
                this.scrollToMessagePosition = -10000;
                this.startLoadFromMessageId = id;
                if (id == this.createUnreadMessageAfterId) {
                    this.createUnreadMessageAfterIdLoading = true;
                }
                this.waitingForLoad.add(Integer.valueOf(this.lastLoadIndex));
                MessagesController instance = MessagesController.getInstance();
                long j = loadIndex == 0 ? this.dialog_id : this.mergeDialogId;
                int i = AndroidUtilities.isTablet() ? 30 : 20;
                int i2 = this.startLoadFromMessageId;
                int i3 = this.classGuid;
                boolean isChannel = ChatObject.isChannel(this.currentChat);
                int i4 = this.lastLoadIndex;
                this.lastLoadIndex = i4 + 1;
                instance.loadMessages(j, i, i2, 0, true, 0, i3, 3, 0, isChannel, i4);
            } else {
                return;
            }
        }
        this.returnToMessageId = fromMessageId;
        this.returnToLoadIndex = loadIndex;
        this.needSelectFromMessageId = select;
    }

    private void showPagedownButton(boolean show, boolean animated) {
        if (this.pagedownButton != null) {
            AnimatorSet animatorSet;
            Animator[] animatorArr;
            if (show) {
                this.pagedownButtonShowedByScroll = false;
                if (this.pagedownButton.getTag() == null) {
                    if (this.pagedownButtonAnimation != null) {
                        this.pagedownButtonAnimation.cancel();
                        this.pagedownButtonAnimation = null;
                    }
                    if (animated) {
                        if (this.pagedownButton.getTranslationY() == 0.0f) {
                            this.pagedownButton.setTranslationY((float) AndroidUtilities.dp(100.0f));
                        }
                        this.pagedownButton.setVisibility(0);
                        this.pagedownButton.setTag(Integer.valueOf(1));
                        this.pagedownButtonAnimation = new AnimatorSet();
                        if (this.mentiondownButton.getVisibility() == 0) {
                            animatorSet = this.pagedownButtonAnimation;
                            animatorArr = new Animator[2];
                            animatorArr[0] = ObjectAnimator.ofFloat(this.pagedownButton, "translationY", new float[]{0.0f});
                            animatorArr[1] = ObjectAnimator.ofFloat(this.mentiondownButton, "translationY", new float[]{(float) (-AndroidUtilities.dp(72.0f))});
                            animatorSet.playTogether(animatorArr);
                        } else {
                            animatorSet = this.pagedownButtonAnimation;
                            animatorArr = new Animator[1];
                            animatorArr[0] = ObjectAnimator.ofFloat(this.pagedownButton, "translationY", new float[]{0.0f});
                            animatorSet.playTogether(animatorArr);
                        }
                        this.pagedownButtonAnimation.setDuration(200);
                        this.pagedownButtonAnimation.start();
                        return;
                    }
                    this.pagedownButton.setVisibility(0);
                    return;
                }
                return;
            }
            this.returnToMessageId = 0;
            this.newUnreadMessageCount = 0;
            if (this.pagedownButton.getTag() != null) {
                this.pagedownButton.setTag(null);
                if (this.pagedownButtonAnimation != null) {
                    this.pagedownButtonAnimation.cancel();
                    this.pagedownButtonAnimation = null;
                }
                if (animated) {
                    this.pagedownButtonAnimation = new AnimatorSet();
                    if (this.mentiondownButton.getVisibility() == 0) {
                        animatorSet = this.pagedownButtonAnimation;
                        animatorArr = new Animator[2];
                        animatorArr[0] = ObjectAnimator.ofFloat(this.pagedownButton, "translationY", new float[]{(float) AndroidUtilities.dp(100.0f)});
                        animatorArr[1] = ObjectAnimator.ofFloat(this.mentiondownButton, "translationY", new float[]{0.0f});
                        animatorSet.playTogether(animatorArr);
                    } else {
                        animatorSet = this.pagedownButtonAnimation;
                        animatorArr = new Animator[1];
                        animatorArr[0] = ObjectAnimator.ofFloat(this.pagedownButton, "translationY", new float[]{(float) AndroidUtilities.dp(100.0f)});
                        animatorSet.playTogether(animatorArr);
                    }
                    this.pagedownButtonAnimation.setDuration(200);
                    this.pagedownButtonAnimation.addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animation) {
                            ChatActivity.this.pagedownButtonCounter.setVisibility(4);
                            ChatActivity.this.pagedownButton.setVisibility(4);
                        }
                    });
                    this.pagedownButtonAnimation.start();
                    return;
                }
                this.pagedownButton.setVisibility(4);
            }
        }
    }

    private void showMentiondownButton(boolean show, boolean animated) {
        if (this.mentiondownButton != null) {
            if (!show) {
                this.returnToMessageId = 0;
                if (this.mentiondownButton.getTag() != null) {
                    this.mentiondownButton.setTag(null);
                    if (this.mentiondownButtonAnimation != null) {
                        this.mentiondownButtonAnimation.cancel();
                        this.mentiondownButtonAnimation = null;
                    }
                    if (animated) {
                        if (this.pagedownButton.getVisibility() == 0) {
                            this.mentiondownButtonAnimation = ObjectAnimator.ofFloat(this.mentiondownButton, "alpha", new float[]{1.0f, 0.0f}).setDuration(200);
                        } else {
                            this.mentiondownButtonAnimation = ObjectAnimator.ofFloat(this.mentiondownButton, "translationY", new float[]{(float) AndroidUtilities.dp(100.0f)}).setDuration(200);
                        }
                        this.mentiondownButtonAnimation.addListener(new AnimatorListenerAdapter() {
                            public void onAnimationEnd(Animator animation) {
                                ChatActivity.this.mentiondownButtonCounter.setVisibility(4);
                                ChatActivity.this.mentiondownButton.setVisibility(4);
                            }
                        });
                        this.mentiondownButtonAnimation.start();
                        return;
                    }
                    this.mentiondownButton.setVisibility(4);
                }
            } else if (this.mentiondownButton.getTag() == null) {
                if (this.mentiondownButtonAnimation != null) {
                    this.mentiondownButtonAnimation.cancel();
                    this.mentiondownButtonAnimation = null;
                }
                if (animated) {
                    this.mentiondownButton.setVisibility(0);
                    this.mentiondownButton.setTag(Integer.valueOf(1));
                    if (this.pagedownButton.getVisibility() == 0) {
                        this.mentiondownButton.setTranslationY((float) (-AndroidUtilities.dp(72.0f)));
                        this.mentiondownButtonAnimation = ObjectAnimator.ofFloat(this.mentiondownButton, "alpha", new float[]{0.0f, 1.0f}).setDuration(200);
                    } else {
                        if (this.mentiondownButton.getTranslationY() == 0.0f) {
                            this.mentiondownButton.setTranslationY((float) AndroidUtilities.dp(100.0f));
                        }
                        this.mentiondownButtonAnimation = ObjectAnimator.ofFloat(this.mentiondownButton, "translationY", new float[]{0.0f}).setDuration(200);
                    }
                    this.mentiondownButtonAnimation.start();
                    return;
                }
                this.mentiondownButton.setVisibility(0);
            }
        }
    }

    private void updateSecretStatus() {
        if (this.bottomOverlay != null) {
            boolean hideKeyboard = false;
            if (ChatObject.isChannel(this.currentChat) && this.currentChat.banned_rights != null && this.currentChat.banned_rights.send_messages) {
                if (AndroidUtilities.isBannedForever(this.currentChat.banned_rights.until_date)) {
                    this.bottomOverlayText.setText(LocaleController.getString("SendMessageRestrictedForever", R.string.SendMessageRestrictedForever));
                } else {
                    this.bottomOverlayText.setText(LocaleController.formatString("SendMessageRestricted", R.string.SendMessageRestricted, new Object[]{LocaleController.formatDateForBan((long) this.currentChat.banned_rights.until_date)}));
                }
                this.bottomOverlay.setVisibility(0);
                if (this.mentionListAnimation != null) {
                    this.mentionListAnimation.cancel();
                    this.mentionListAnimation = null;
                }
                this.mentionContainer.setVisibility(8);
                this.mentionContainer.setTag(null);
                hideKeyboard = true;
            } else if (this.currentEncryptedChat == null || this.bigEmptyView == null) {
                this.bottomOverlay.setVisibility(4);
                return;
            } else {
                if (this.currentEncryptedChat instanceof TLRPC$TL_encryptedChatRequested) {
                    this.bottomOverlayText.setText(LocaleController.getString("EncryptionProcessing", R.string.EncryptionProcessing));
                    this.bottomOverlay.setVisibility(0);
                    hideKeyboard = true;
                } else if (this.currentEncryptedChat instanceof TLRPC$TL_encryptedChatWaiting) {
                    this.bottomOverlayText.setText(AndroidUtilities.replaceTags(LocaleController.formatString("AwaitingEncryption", R.string.AwaitingEncryption, new Object[]{"<b>" + this.currentUser.first_name + "</b>"})));
                    this.bottomOverlay.setVisibility(0);
                    hideKeyboard = true;
                } else if (this.currentEncryptedChat instanceof TLRPC$TL_encryptedChatDiscarded) {
                    this.bottomOverlayText.setText(LocaleController.getString("EncryptionRejected", R.string.EncryptionRejected));
                    this.bottomOverlay.setVisibility(0);
                    this.chatActivityEnterView.setFieldText("");
                    DraftQuery.cleanDraft(this.dialog_id, false);
                    hideKeyboard = true;
                } else if (this.currentEncryptedChat instanceof TLRPC$TL_encryptedChat) {
                    this.bottomOverlay.setVisibility(4);
                }
                checkRaiseSensors();
                checkActionBarMenu();
            }
            if (hideKeyboard) {
                this.chatActivityEnterView.hidePopup(false);
                if (getParentActivity() != null) {
                    AndroidUtilities.hideKeyboard(getParentActivity().getCurrentFocus());
                }
            }
        }
    }

    public void onRequestPermissionsResultFragment(int requestCode, String[] permissions, int[] grantResults) {
        if (this.chatActivityEnterView != null) {
            this.chatActivityEnterView.onRequestPermissionsResultFragment(requestCode, permissions, grantResults);
        }
        if (this.mentionsAdapter != null) {
            this.mentionsAdapter.onRequestPermissionsResultFragment(requestCode, permissions, grantResults);
        }
        if (requestCode == 17 && this.chatAttachAlert != null) {
            this.chatAttachAlert.checkCamera(false);
        } else if (requestCode == 21) {
            if (getParentActivity() != null && grantResults != null && grantResults.length != 0 && grantResults[0] != 0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getParentActivity());
                builder.setTitle(ApplicationLoader.getConfig().getAppName());
                builder.setMessage(LocaleController.getAppNameString("PermissionNoAudioVideo", R.string.PermissionNoAudioVideo));
                builder.setNegativeButton(LocaleController.getAppNameString("PermissionOpenSettings", R.string.PermissionOpenSettings), new OnClickListener() {
                    @TargetApi(9)
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                            intent.setData(Uri.parse("package:" + ApplicationLoader.applicationContext.getPackageName()));
                            ChatActivity.this.getParentActivity().startActivity(intent);
                        } catch (Exception e) {
                            FileLog.e(e);
                        }
                    }
                });
                builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), null);
                builder.show();
            }
        } else if (requestCode == 19 && grantResults != null && grantResults.length > 0 && grantResults[0] == 0) {
            processSelectedAttach(0);
        } else if (requestCode == 20 && grantResults != null && grantResults.length > 0 && grantResults[0] == 0) {
            processSelectedAttach(2);
        } else if (requestCode == 101 && this.currentUser != null) {
            if (grantResults.length <= 0 || grantResults[0] != 0) {
                VoIPHelper.permissionDenied(getParentActivity(), null);
            } else {
                VoIPHelper.startCall(this.currentUser, getParentActivity(), MessagesController.getInstance().getUserFull(this.currentUser.id));
            }
        }
    }

    private void checkActionBarMenu() {
        if ((this.currentEncryptedChat == null || (this.currentEncryptedChat instanceof TLRPC$TL_encryptedChat)) && ((this.currentChat == null || !ChatObject.isNotInChat(this.currentChat)) && (this.currentUser == null || !UserObject.isDeleted(this.currentUser)))) {
            if (this.timeItem2 != null) {
                this.timeItem2.setVisibility(0);
            }
            if (this.avatarContainer != null) {
                this.avatarContainer.showTimeItem();
            }
        } else {
            if (this.timeItem2 != null) {
                this.timeItem2.setVisibility(8);
            }
            if (this.avatarContainer != null) {
                this.avatarContainer.hideTimeItem();
            }
        }
        if (!(this.avatarContainer == null || this.currentEncryptedChat == null)) {
            this.avatarContainer.setTime(this.currentEncryptedChat.ttl);
        }
        checkAndUpdateAvatar();
    }

    private int getMessageType(MessageObject messageObject) {
        if (messageObject == null) {
            return -1;
        }
        TLRPC$InputStickerSet inputStickerSet;
        boolean canSave;
        String mime;
        if (this.currentEncryptedChat == null) {
            boolean isBroadcastError;
            if (this.isBroadcast && messageObject.getId() <= 0 && messageObject.isSendError()) {
                isBroadcastError = true;
            } else {
                isBroadcastError = false;
            }
            if ((this.isBroadcast || messageObject.getId() > 0 || !messageObject.isOut()) && !isBroadcastError) {
                if (messageObject.type == 6) {
                    return -1;
                }
                if (messageObject.type == 10 || messageObject.type == 11 || messageObject.type == 16) {
                    if (messageObject.getId() == 0) {
                        return -1;
                    }
                    return 1;
                } else if (messageObject.isVoice()) {
                    return 2;
                } else {
                    if (messageObject.isSticker()) {
                        inputStickerSet = messageObject.getInputStickerSet();
                        if (inputStickerSet instanceof TLRPC$TL_inputStickerSetID) {
                            if (!StickersQuery.isStickerPackInstalled(inputStickerSet.id)) {
                                return 7;
                            }
                        } else if ((inputStickerSet instanceof TLRPC$TL_inputStickerSetShortName) && !StickersQuery.isStickerPackInstalled(inputStickerSet.short_name)) {
                            return 7;
                        }
                        return 9;
                    }
                    if ((!messageObject.isRoundVideo() || (messageObject.isRoundVideo() && BuildVars.DEBUG_VERSION)) && ((messageObject.messageOwner.media instanceof TLRPC$TL_messageMediaPhoto) || messageObject.getDocument() != null || messageObject.isMusic() || messageObject.isVideo())) {
                        canSave = false;
                        if (!TextUtils.isEmpty(messageObject.messageOwner.attachPath) && new File(messageObject.messageOwner.attachPath).exists()) {
                            canSave = true;
                        }
                        if (!canSave && FileLoader.getPathToMessage(messageObject.messageOwner).exists()) {
                            canSave = true;
                        }
                        if (canSave) {
                            if (messageObject.getDocument() != null) {
                                mime = messageObject.getDocument().mime_type;
                                if (mime != null) {
                                    if (messageObject.getDocumentName().endsWith("attheme")) {
                                        return 10;
                                    }
                                    if (mime.endsWith("/xml")) {
                                        return 5;
                                    }
                                    if (mime.endsWith("/png") || mime.endsWith("/jpg") || mime.endsWith("/jpeg")) {
                                        return 6;
                                    }
                                }
                            }
                            return 4;
                        }
                    } else if (messageObject.type == 12) {
                        return 8;
                    } else {
                        if (messageObject.isMediaEmpty()) {
                            return 3;
                        }
                    }
                    return 2;
                }
            } else if (!messageObject.isSendError()) {
                return -1;
            } else {
                if (messageObject.isMediaEmpty()) {
                    return 20;
                }
                return 0;
            }
        } else if (messageObject.isSending()) {
            return -1;
        } else {
            if (messageObject.type == 6) {
                return -1;
            }
            if (messageObject.isSendError()) {
                if (messageObject.isMediaEmpty()) {
                    return 20;
                }
                return 0;
            } else if (messageObject.type == 10 || messageObject.type == 11) {
                if (messageObject.getId() == 0 || messageObject.isSending()) {
                    return -1;
                }
                return 1;
            } else if (messageObject.isVoice()) {
                return 2;
            } else {
                if (messageObject.isSticker()) {
                    inputStickerSet = messageObject.getInputStickerSet();
                    if ((inputStickerSet instanceof TLRPC$TL_inputStickerSetShortName) && !StickersQuery.isStickerPackInstalled(inputStickerSet.short_name)) {
                        return 7;
                    }
                } else if (!messageObject.isRoundVideo() && ((messageObject.messageOwner.media instanceof TLRPC$TL_messageMediaPhoto) || messageObject.getDocument() != null || messageObject.isMusic() || messageObject.isVideo())) {
                    canSave = false;
                    if (!TextUtils.isEmpty(messageObject.messageOwner.attachPath) && new File(messageObject.messageOwner.attachPath).exists()) {
                        canSave = true;
                    }
                    if (!canSave && FileLoader.getPathToMessage(messageObject.messageOwner).exists()) {
                        canSave = true;
                    }
                    if (canSave) {
                        if (messageObject.getDocument() != null) {
                            mime = messageObject.getDocument().mime_type;
                            if (mime != null && mime.endsWith("text/xml")) {
                                return 5;
                            }
                        }
                        if (messageObject.messageOwner.ttl <= 0) {
                            return 4;
                        }
                    }
                } else if (messageObject.type == 12) {
                    return 8;
                } else {
                    if (messageObject.isMediaEmpty()) {
                        return 3;
                    }
                }
                return 2;
            }
        }
    }

    private void addToSelectedMessages(MessageObject messageObject, boolean outside) {
        addToSelectedMessages(messageObject, outside, true);
    }

    private void addToSelectedMessages(MessageObject messageObject, boolean outside, boolean last) {
        int index = messageObject.getDialogId() == this.dialog_id ? 0 : 1;
        GroupedMessages groupedMessages;
        int a;
        if (!outside || messageObject.getGroupId() == 0) {
            if (this.selectedMessagesIds[index].containsKey(Integer.valueOf(messageObject.getId()))) {
                this.selectedMessagesIds[index].remove(Integer.valueOf(messageObject.getId()));
                if (messageObject.type == 0 || messageObject.caption != null) {
                    this.selectedMessagesCanCopyIds[index].remove(Integer.valueOf(messageObject.getId()));
                }
                if (messageObject.isSticker()) {
                    this.selectedMessagesCanStarIds[index].remove(Integer.valueOf(messageObject.getId()));
                }
                if (messageObject.canEditMessage(this.currentChat) && messageObject.getGroupId() != 0) {
                    groupedMessages = (GroupedMessages) this.groupedMessagesMap.get(Long.valueOf(messageObject.getGroupId()));
                    if (groupedMessages != null && groupedMessages.messages.size() > 1) {
                        this.canEditMessagesCount--;
                    }
                }
                if (!messageObject.canDeleteMessage(this.currentChat)) {
                    this.cantDeleteMessagesCount--;
                }
            } else if (this.selectedMessagesIds[0].size() + this.selectedMessagesIds[1].size() < 100) {
                this.selectedMessagesIds[index].put(Integer.valueOf(messageObject.getId()), messageObject);
                if (messageObject.type == 0 || messageObject.caption != null) {
                    this.selectedMessagesCanCopyIds[index].put(Integer.valueOf(messageObject.getId()), messageObject);
                }
                if (messageObject.isSticker()) {
                    this.selectedMessagesCanStarIds[index].put(Integer.valueOf(messageObject.getId()), messageObject);
                }
                if (messageObject.canEditMessage(this.currentChat) && messageObject.getGroupId() != 0) {
                    groupedMessages = (GroupedMessages) this.groupedMessagesMap.get(Long.valueOf(messageObject.getGroupId()));
                    if (groupedMessages != null && groupedMessages.messages.size() > 1) {
                        this.canEditMessagesCount++;
                    }
                }
                if (!messageObject.canDeleteMessage(this.currentChat)) {
                    this.cantDeleteMessagesCount++;
                }
            } else {
                return;
            }
            if (last && this.actionBar.isActionModeShowed()) {
                int selectedCount = this.selectedMessagesIds[0].size() + this.selectedMessagesIds[1].size();
                if (selectedCount == 0) {
                    this.actionBar.hideActionMode();
                    updatePinnedMessageView(true);
                    this.startReplyOnTextChange = false;
                    return;
                }
                ActionBarMenuItem copyItem = this.actionBar.createActionMode().getItem(10);
                ActionBarMenuItem starItem = this.actionBar.createActionMode().getItem(22);
                ActionBarMenuItem editItem = this.actionBar.createActionMode().getItem(23);
                ActionBarMenuItem replyItem = this.actionBar.createActionMode().getItem(19);
                int copyVisible = copyItem.getVisibility();
                int starVisible = starItem.getVisibility();
                copyItem.setVisibility(this.selectedMessagesCanCopyIds[0].size() + this.selectedMessagesCanCopyIds[1].size() != 0 ? 0 : 8);
                int i = (StickersQuery.canAddStickerToFavorites() && this.selectedMessagesCanStarIds[0].size() + this.selectedMessagesCanStarIds[1].size() == selectedCount) ? 0 : 8;
                starItem.setVisibility(i);
                int newCopyVisible = copyItem.getVisibility();
                int newStarVisible = starItem.getVisibility();
                this.actionBar.createActionMode().getItem(12).setVisibility(this.cantDeleteMessagesCount == 0 ? 0 : 8);
                if (editItem != null) {
                    i = (this.canEditMessagesCount == 1 && this.selectedMessagesIds[0].size() + this.selectedMessagesIds[1].size() == 1) ? 0 : 8;
                    editItem.setVisibility(i);
                }
                this.hasUnfavedSelected = false;
                for (a = 0; a < 2; a++) {
                    for (Entry<Integer, MessageObject> entry : this.selectedMessagesCanStarIds[a].entrySet()) {
                        if (!StickersQuery.isStickerInFavorites(((MessageObject) entry.getValue()).getDocument())) {
                            this.hasUnfavedSelected = true;
                            break;
                        }
                    }
                    if (this.hasUnfavedSelected) {
                        break;
                    }
                }
                starItem.setIcon(this.hasUnfavedSelected ? R.drawable.ic_ab_fave : R.drawable.ic_ab_unfave);
                if (replyItem != null) {
                    boolean allowChatActions = true;
                    if ((this.currentEncryptedChat != null && AndroidUtilities.getPeerLayerVersion(this.currentEncryptedChat.layer) < 46) || this.isBroadcast || ((this.bottomOverlayChat != null && this.bottomOverlayChat.getVisibility() == 0) || (this.currentChat != null && (ChatObject.isNotInChat(this.currentChat) || !((!ChatObject.isChannel(this.currentChat) || ChatObject.canPost(this.currentChat) || this.currentChat.megagroup) && ChatObject.canSendMessages(this.currentChat)))))) {
                        allowChatActions = false;
                    }
                    int newVisibility = (allowChatActions && this.selectedMessagesIds[0].size() + this.selectedMessagesIds[1].size() == 1) ? 0 : 8;
                    boolean z = newVisibility == 0 && !this.chatActivityEnterView.hasText();
                    this.startReplyOnTextChange = z;
                    if (replyItem.getVisibility() != newVisibility) {
                        if (this.replyButtonAnimation != null) {
                            this.replyButtonAnimation.cancel();
                        }
                        if (copyVisible == newCopyVisible && starVisible == newStarVisible) {
                            this.replyButtonAnimation = new AnimatorSet();
                            replyItem.setPivotX((float) AndroidUtilities.dp(54.0f));
                            editItem.setPivotX((float) AndroidUtilities.dp(54.0f));
                            AnimatorSet animatorSet;
                            Animator[] animatorArr;
                            if (newVisibility == 0) {
                                replyItem.setVisibility(newVisibility);
                                animatorSet = this.replyButtonAnimation;
                                animatorArr = new Animator[4];
                                animatorArr[0] = ObjectAnimator.ofFloat(replyItem, "alpha", new float[]{1.0f});
                                animatorArr[1] = ObjectAnimator.ofFloat(replyItem, "scaleX", new float[]{1.0f});
                                animatorArr[2] = ObjectAnimator.ofFloat(editItem, "alpha", new float[]{1.0f});
                                animatorArr[3] = ObjectAnimator.ofFloat(editItem, "scaleX", new float[]{1.0f});
                                animatorSet.playTogether(animatorArr);
                            } else {
                                animatorSet = this.replyButtonAnimation;
                                animatorArr = new Animator[4];
                                animatorArr[0] = ObjectAnimator.ofFloat(replyItem, "alpha", new float[]{0.0f});
                                animatorArr[1] = ObjectAnimator.ofFloat(replyItem, "scaleX", new float[]{0.0f});
                                animatorArr[2] = ObjectAnimator.ofFloat(editItem, "alpha", new float[]{0.0f});
                                animatorArr[3] = ObjectAnimator.ofFloat(editItem, "scaleX", new float[]{0.0f});
                                animatorSet.playTogether(animatorArr);
                            }
                            this.replyButtonAnimation.setDuration(100);
                            final int i2 = newVisibility;
                            final ActionBarMenuItem actionBarMenuItem = replyItem;
                            this.replyButtonAnimation.addListener(new AnimatorListenerAdapter() {
                                public void onAnimationEnd(Animator animation) {
                                    if (ChatActivity.this.replyButtonAnimation != null && ChatActivity.this.replyButtonAnimation.equals(animation) && i2 == 8) {
                                        actionBarMenuItem.setVisibility(8);
                                    }
                                }

                                public void onAnimationCancel(Animator animation) {
                                    if (ChatActivity.this.replyButtonAnimation != null && ChatActivity.this.replyButtonAnimation.equals(animation)) {
                                        ChatActivity.this.replyButtonAnimation = null;
                                    }
                                }
                            });
                            this.replyButtonAnimation.start();
                            return;
                        }
                        if (newVisibility == 0) {
                            replyItem.setAlpha(1.0f);
                            replyItem.setScaleX(1.0f);
                        } else {
                            replyItem.setAlpha(0.0f);
                            replyItem.setScaleX(0.0f);
                        }
                        replyItem.setVisibility(newVisibility);
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        boolean hasUnselected = false;
        groupedMessages = (GroupedMessages) this.groupedMessagesMap.get(Long.valueOf(messageObject.getGroupId()));
        if (groupedMessages != null) {
            int lastNum = 0;
            for (a = 0; a < groupedMessages.messages.size(); a++) {
                if (!this.selectedMessagesIds[index].containsKey(Integer.valueOf(((MessageObject) groupedMessages.messages.get(a)).getId()))) {
                    hasUnselected = true;
                    lastNum = a;
                }
            }
            a = 0;
            while (a < groupedMessages.messages.size()) {
                MessageObject message = (MessageObject) groupedMessages.messages.get(a);
                if (!hasUnselected) {
                    addToSelectedMessages(message, false, a == groupedMessages.messages.size() + -1);
                } else if (!this.selectedMessagesIds[index].containsKey(Integer.valueOf(message.getId()))) {
                    addToSelectedMessages(message, false, a == lastNum);
                }
                a++;
            }
        }
    }

    private void processRowSelect(View view, boolean outside) {
        MessageObject message = null;
        if (view instanceof ChatMessageCell) {
            message = ((ChatMessageCell) view).getMessageObject();
        } else if (view instanceof ChatActionCell) {
            message = ((ChatActionCell) view).getMessageObject();
        }
        int type = getMessageType(message);
        if (type >= 2 && type != 20) {
            addToSelectedMessages(message, outside);
            updateActionModeTitle();
            updateVisibleRows();
        }
    }

    private void updateActionModeTitle() {
        if (!this.actionBar.isActionModeShowed()) {
            return;
        }
        if (!this.selectedMessagesIds[0].isEmpty() || !this.selectedMessagesIds[1].isEmpty()) {
            this.selectedMessagesCountTextView.setNumber(this.selectedMessagesIds[0].size() + this.selectedMessagesIds[1].size(), true);
        }
    }

    private void updateTitle() {
        if (this.avatarContainer != null) {
            if (this.currentChat != null) {
                this.avatarContainer.setTitle(this.currentChat.title);
            } else if (this.currentUser == null) {
            } else {
                if (this.currentUser.self) {
                    this.avatarContainer.setTitle(LocaleController.getString("SavedMessages", R.string.SavedMessages));
                } else if (MessagesController.isSupportId(this.currentUser.id) || ContactsController.getInstance().contactsDict.get(Integer.valueOf(this.currentUser.id)) != null || (ContactsController.getInstance().contactsDict.size() == 0 && ContactsController.getInstance().isLoadingContacts())) {
                    this.avatarContainer.setTitle(UserObject.getUserName(this.currentUser));
                } else if (TextUtils.isEmpty(this.currentUser.phone)) {
                    this.avatarContainer.setTitle(UserObject.getUserName(this.currentUser));
                } else {
                    this.avatarContainer.setTitle(PhoneFormat.getInstance().format(Marker.ANY_NON_NULL_MARKER + this.currentUser.phone));
                }
            }
        }
    }

    private void updateBotButtons() {
        if (this.headerItem != null && this.currentUser != null && this.currentEncryptedChat == null && this.currentUser.bot) {
            boolean hasHelp = false;
            boolean hasSettings = false;
            if (!this.botInfo.isEmpty()) {
                for (Entry<Integer, TLRPC$BotInfo> entry : this.botInfo.entrySet()) {
                    TLRPC$BotInfo info = (TLRPC$BotInfo) entry.getValue();
                    for (int a = 0; a < info.commands.size(); a++) {
                        TLRPC$TL_botCommand command = (TLRPC$TL_botCommand) info.commands.get(a);
                        if (command.command.toLowerCase().equals("help")) {
                            hasHelp = true;
                        } else if (command.command.toLowerCase().equals(C1404b.f2107K)) {
                            hasSettings = true;
                        }
                        if (hasSettings && hasHelp) {
                            break;
                        }
                    }
                }
            }
            if (hasHelp) {
                this.headerItem.showSubItem(30);
            } else {
                this.headerItem.hideSubItem(30);
            }
            if (hasSettings) {
                this.headerItem.showSubItem(31);
            } else {
                this.headerItem.hideSubItem(31);
            }
        }
    }

    private void updateTitleIcons() {
        Drawable drawable = null;
        if (this.avatarContainer != null) {
            Drawable rightIcon;
            if (MessagesController.getInstance().isDialogMuted(this.dialog_id)) {
                rightIcon = Theme.chat_muteIconDrawable;
            } else {
                rightIcon = null;
            }
            ChatAvatarContainer chatAvatarContainer = this.avatarContainer;
            if (this.currentEncryptedChat != null) {
                drawable = Theme.chat_lockIconDrawable;
            }
            chatAvatarContainer.setTitleIcons(drawable, rightIcon);
            if (this.muteItem == null) {
                return;
            }
            if (rightIcon != null) {
                this.muteItem.setText(LocaleController.getString("UnmuteNotifications", R.string.UnmuteNotifications));
            } else {
                this.muteItem.setText(LocaleController.getString("MuteNotifications", R.string.MuteNotifications));
            }
        }
    }

    private void checkAndUpdateAvatar() {
        if (this.currentUser != null) {
            User user = MessagesController.getInstance().getUser(Integer.valueOf(this.currentUser.id));
            if (user != null) {
                this.currentUser = user;
            } else {
                return;
            }
        } else if (this.currentChat != null) {
            TLRPC$Chat chat = MessagesController.getInstance().getChat(Integer.valueOf(this.currentChat.id));
            if (chat != null) {
                this.currentChat = chat;
            } else {
                return;
            }
        }
        if (this.avatarContainer != null) {
            this.avatarContainer.checkAndUpdateAvatar();
        }
    }

    public void openVideoEditor(String videoPath, String caption) {
        if (getParentActivity() != null) {
            Bitmap thumb = ThumbnailUtils.createVideoThumbnail(videoPath, 1);
            PhotoViewer.getInstance().setParentActivity(getParentActivity());
            final ArrayList<Object> cameraPhoto = new ArrayList();
            MediaController$PhotoEntry entry = new MediaController$PhotoEntry(0, 0, 0, videoPath, 0, true);
            entry.caption = caption;
            cameraPhoto.add(entry);
            final Bitmap bitmap = thumb;
            PhotoViewer.getInstance().openPhotoForSelect(cameraPhoto, 0, 2, new EmptyPhotoViewerProvider() {
                public Bitmap getThumbForPhoto(MessageObject messageObject, TLRPC$FileLocation fileLocation, int index) {
                    return bitmap;
                }

                public void sendButtonPressed(int index, VideoEditedInfo videoEditedInfo) {
                    ChatActivity.this.sendMedia((MediaController$PhotoEntry) cameraPhoto.get(0), videoEditedInfo);
                }

                public boolean canScrollAway() {
                    return false;
                }
            }, this);
            return;
        }
        SendMessagesHelper.prepareSendingVideo(videoPath, 0, 0, 0, 0, null, this.dialog_id, this.replyingMessageObject, null, 0);
        showReplyPanel(false, null, null, null, false);
        DraftQuery.cleanDraft(this.dialog_id, true);
    }

    private void showAttachmentError() {
        if (getParentActivity() != null) {
            Toast.makeText(getParentActivity(), LocaleController.getString("UnsupportedAttachment", R.string.UnsupportedAttachment), 0).show();
        }
    }

    private void sendUriAsDocument(Uri uri) {
        if (uri != null) {
            String extractUriFrom = uri.toString();
            if (extractUriFrom.contains("com.google.android.apps.photos.contentprovider")) {
                try {
                    String firstExtraction = extractUriFrom.split("/1/")[1];
                    int index = firstExtraction.indexOf("/ACTUAL");
                    if (index != -1) {
                        uri = Uri.parse(URLDecoder.decode(firstExtraction.substring(0, index), "UTF-8"));
                    }
                } catch (Exception e) {
                    FileLog.e(e);
                }
            }
            String tempPath = AndroidUtilities.getPath(uri);
            String originalPath = tempPath;
            if (tempPath == null) {
                originalPath = uri.toString();
                tempPath = MediaController.copyFileToCache(uri, "file");
            }
            if (tempPath == null) {
                showAttachmentError();
                return;
            }
            SendMessagesHelper.prepareSendingDocument(tempPath, originalPath, null, null, this.dialog_id, this.replyingMessageObject, null);
        }
    }

    public void onActivityResultFragment(int requestCode, int resultCode, Intent data) {
        if (resultCode != -1) {
            return;
        }
        if (requestCode == 0) {
            PhotoViewer.getInstance().setParentActivity(getParentActivity());
            ArrayList<Object> arrayList = new ArrayList();
            int orientation = 0;
            try {
                switch (new ExifInterface(this.currentPicturePath).getAttributeInt("Orientation", 1)) {
                    case 3:
                        orientation = 180;
                        break;
                    case 6:
                        orientation = 90;
                        break;
                    case 8:
                        orientation = 270;
                        break;
                }
            } catch (Exception e) {
                FileLog.e(e);
            }
            arrayList.add(new MediaController$PhotoEntry(0, 0, 0, this.currentPicturePath, orientation, false));
            final ArrayList<Object> arrayList2 = arrayList;
            PhotoViewer.getInstance().openPhotoForSelect(arrayList, 0, 2, new EmptyPhotoViewerProvider() {
                public void sendButtonPressed(int index, VideoEditedInfo videoEditedInfo) {
                    ChatActivity.this.sendMedia((MediaController$PhotoEntry) arrayList2.get(0), null);
                }
            }, this);
            AndroidUtilities.addMediaToGallery(this.currentPicturePath);
            this.currentPicturePath = null;
        } else if (requestCode == 1) {
            if (data == null || data.getData() == null) {
                showAttachmentError();
                return;
            }
            uri = data.getData();
            if (uri.toString().contains("video")) {
                videoPath = null;
                try {
                    videoPath = AndroidUtilities.getPath(uri);
                } catch (Exception e2) {
                    FileLog.e(e2);
                }
                if (videoPath == null) {
                    showAttachmentError();
                }
                if (this.paused) {
                    this.startVideoEdit = videoPath;
                } else {
                    openVideoEditor(videoPath, null);
                }
            } else {
                SendMessagesHelper.prepareSendingPhoto(null, uri, this.dialog_id, this.replyingMessageObject, null, null, null, 0);
            }
            showReplyPanel(false, null, null, null, false);
            DraftQuery.cleanDraft(this.dialog_id, true);
        } else if (requestCode == 2) {
            videoPath = null;
            FileLog.d("pic path " + this.currentPicturePath);
            if (!(data == null || this.currentPicturePath == null || !new File(this.currentPicturePath).exists())) {
                data = null;
            }
            if (data != null) {
                uri = data.getData();
                if (uri != null) {
                    FileLog.d("video record uri " + uri.toString());
                    videoPath = AndroidUtilities.getPath(uri);
                    FileLog.d("resolved path = " + videoPath);
                    if (videoPath == null || !new File(videoPath).exists()) {
                        videoPath = this.currentPicturePath;
                    }
                } else {
                    videoPath = this.currentPicturePath;
                }
                AndroidUtilities.addMediaToGallery(this.currentPicturePath);
                this.currentPicturePath = null;
            }
            if (videoPath == null && this.currentPicturePath != null) {
                if (new File(this.currentPicturePath).exists()) {
                    videoPath = this.currentPicturePath;
                }
                this.currentPicturePath = null;
            }
            if (this.paused) {
                this.startVideoEdit = videoPath;
            } else {
                openVideoEditor(videoPath, null);
            }
        } else if (requestCode == 21) {
            if (data == null) {
                showAttachmentError();
                return;
            }
            if (data.getData() != null) {
                sendUriAsDocument(data.getData());
            } else if (data.getClipData() != null) {
                ClipData clipData = data.getClipData();
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    sendUriAsDocument(clipData.getItemAt(i).getUri());
                }
            } else {
                showAttachmentError();
            }
            showReplyPanel(false, null, null, null, false);
            DraftQuery.cleanDraft(this.dialog_id, true);
        } else if (requestCode != 31) {
        } else {
            if (data == null || data.getData() == null) {
                showAttachmentError();
                return;
            }
            uri = data.getData();
            Cursor c = null;
            try {
                c = getParentActivity().getContentResolver().query(uri, new String[]{"display_name", "data1"}, null, null, null);
                if (c != null) {
                    boolean sent = false;
                    while (c.moveToNext()) {
                        sent = true;
                        String name = c.getString(0);
                        String number = c.getString(1);
                        User user = new TLRPC$TL_user();
                        user.first_name = name;
                        user.last_name = "";
                        user.phone = number;
                        SendMessagesHelper.getInstance().sendMessage(user, this.dialog_id, this.replyingMessageObject, null, null);
                    }
                    if (sent) {
                        showReplyPanel(false, null, null, null, false);
                        DraftQuery.cleanDraft(this.dialog_id, true);
                    }
                }
                if (c != null) {
                    try {
                        if (!c.isClosed()) {
                            c.close();
                        }
                    } catch (Exception e22) {
                        FileLog.e(e22);
                    }
                }
            } catch (Throwable th) {
                if (c != null) {
                    try {
                        if (!c.isClosed()) {
                            c.close();
                        }
                    } catch (Exception e222) {
                        FileLog.e(e222);
                    }
                }
            }
        }
    }

    public void saveSelfArgs(Bundle args) {
        if (this.currentPicturePath != null) {
            args.putString(ClientCookie.PATH_ATTR, this.currentPicturePath);
        }
    }

    public void restoreSelfArgs(Bundle args) {
        this.currentPicturePath = args.getString(ClientCookie.PATH_ATTR);
    }

    private void removeUnreadPlane() {
        if (this.unreadMessageObject != null) {
            boolean[] zArr = this.forwardEndReached;
            this.forwardEndReached[1] = true;
            zArr[0] = true;
            this.first_unread_id = 0;
            this.last_message_id = 0;
            this.createUnreadMessageAfterId = 0;
            this.createUnreadMessageAfterIdLoading = false;
            this.unread_to_load = 0;
            removeMessageObject(this.unreadMessageObject);
            this.unreadMessageObject = null;
        }
    }

    public boolean processSendingText(String text) {
        return this.chatActivityEnterView.processSendingText(text);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void didReceivedNotification(int r164, java.lang.Object... r165) {
        /*
        r163 = this;
        r5 = org.telegram.messenger.NotificationCenter.messagesDidLoaded;
        r0 = r164;
        if (r0 != r5) goto L_0x0d44;
    L_0x0006:
        r5 = 10;
        r5 = r165[r5];
        r5 = (java.lang.Integer) r5;
        r73 = r5.intValue();
        r0 = r163;
        r5 = r0.classGuid;
        r0 = r73;
        if (r0 != r5) goto L_0x005b;
    L_0x0018:
        r0 = r163;
        r5 = r0.openAnimationEnded;
        if (r5 != 0) goto L_0x003c;
    L_0x001e:
        r5 = org.telegram.messenger.NotificationCenter.getInstance();
        r6 = 4;
        r6 = new int[r6];
        r7 = 0;
        r8 = org.telegram.messenger.NotificationCenter.chatInfoDidLoaded;
        r6[r7] = r8;
        r7 = 1;
        r8 = org.telegram.messenger.NotificationCenter.dialogsNeedReload;
        r6[r7] = r8;
        r7 = 2;
        r8 = org.telegram.messenger.NotificationCenter.closeChats;
        r6[r7] = r8;
        r7 = 3;
        r8 = org.telegram.messenger.NotificationCenter.botKeyboardDidLoaded;
        r6[r7] = r8;
        r5.setAllowedNotificationsDutingAnimation(r6);
    L_0x003c:
        r5 = 11;
        r5 = r165[r5];
        r5 = (java.lang.Integer) r5;
        r136 = r5.intValue();
        r0 = r163;
        r5 = r0.waitingForLoad;
        r6 = java.lang.Integer.valueOf(r136);
        r80 = r5.indexOf(r6);
        r53 = org.telegram.messenger.UserConfig.getClientUserId();
        r5 = -1;
        r0 = r80;
        if (r0 != r5) goto L_0x005c;
    L_0x005b:
        return;
    L_0x005c:
        r0 = r163;
        r5 = r0.waitingForLoad;
        r0 = r80;
        r5.remove(r0);
        r5 = 2;
        r102 = r165[r5];
        r102 = (java.util.ArrayList) r102;
        r48 = 0;
        r0 = r163;
        r5 = r0.waitingForReplyMessageLoad;
        if (r5 == 0) goto L_0x00fe;
    L_0x0072:
        r0 = r163;
        r5 = r0.createUnreadMessageAfterIdLoading;
        if (r5 != 0) goto L_0x00d1;
    L_0x0078:
        r71 = 0;
        r4 = 0;
    L_0x007b:
        r5 = r102.size();
        if (r4 >= r5) goto L_0x0095;
    L_0x0081:
        r0 = r102;
        r121 = r0.get(r4);
        r121 = (org.telegram.messenger.MessageObject) r121;
        r5 = r121.getId();
        r0 = r163;
        r6 = r0.startLoadFromMessageId;
        if (r5 != r6) goto L_0x009d;
    L_0x0093:
        r71 = 1;
    L_0x0095:
        if (r71 != 0) goto L_0x00d1;
    L_0x0097:
        r5 = 0;
        r0 = r163;
        r0.startLoadFromMessageId = r5;
        goto L_0x005b;
    L_0x009d:
        r5 = r4 + 1;
        r6 = r102.size();
        if (r5 >= r6) goto L_0x00ce;
    L_0x00a5:
        r5 = r4 + 1;
        r0 = r102;
        r122 = r0.get(r5);
        r122 = (org.telegram.messenger.MessageObject) r122;
        r5 = r121.getId();
        r0 = r163;
        r6 = r0.startLoadFromMessageId;
        if (r5 < r6) goto L_0x00ce;
    L_0x00b9:
        r5 = r122.getId();
        r0 = r163;
        r6 = r0.startLoadFromMessageId;
        if (r5 >= r6) goto L_0x00ce;
    L_0x00c3:
        r5 = r121.getId();
        r0 = r163;
        r0.startLoadFromMessageId = r5;
        r71 = 1;
        goto L_0x0095;
    L_0x00ce:
        r4 = r4 + 1;
        goto L_0x007b;
    L_0x00d1:
        r0 = r163;
        r0 = r0.startLoadFromMessageId;
        r145 = r0;
        r0 = r163;
        r0 = r0.needSelectFromMessageId;
        r113 = r0;
        r0 = r163;
        r0 = r0.createUnreadMessageAfterId;
        r148 = r0;
        r0 = r163;
        r0 = r0.createUnreadMessageAfterIdLoading;
        r48 = r0;
        r163.clearChatData();
        r0 = r148;
        r1 = r163;
        r1.createUnreadMessageAfterId = r0;
        r0 = r145;
        r1 = r163;
        r1.startLoadFromMessageId = r0;
        r0 = r113;
        r1 = r163;
        r1.needSelectFromMessageId = r0;
    L_0x00fe:
        r0 = r163;
        r5 = r0.loadsCount;
        r5 = r5 + 1;
        r0 = r163;
        r0.loadsCount = r5;
        r5 = 0;
        r5 = r165[r5];
        r5 = (java.lang.Long) r5;
        r60 = r5.longValue();
        r0 = r163;
        r6 = r0.dialog_id;
        r5 = (r60 > r6 ? 1 : (r60 == r6 ? 0 : -1));
        if (r5 != 0) goto L_0x029d;
    L_0x0119:
        r94 = 0;
    L_0x011b:
        r5 = 1;
        r5 = r165[r5];
        r5 = (java.lang.Integer) r5;
        r47 = r5.intValue();
        r5 = 3;
        r5 = r165[r5];
        r5 = (java.lang.Boolean) r5;
        r84 = r5.booleanValue();
        r5 = 4;
        r5 = r165[r5];
        r5 = (java.lang.Integer) r5;
        r70 = r5.intValue();
        r5 = 7;
        r5 = r165[r5];
        r5 = (java.lang.Integer) r5;
        r91 = r5.intValue();
        r5 = 8;
        r5 = r165[r5];
        r5 = (java.lang.Integer) r5;
        r95 = r5.intValue();
        r5 = 12;
        r5 = r165[r5];
        r5 = (java.lang.Integer) r5;
        r96 = r5.intValue();
        r5 = 13;
        r5 = r165[r5];
        r5 = (java.lang.Integer) r5;
        r97 = r5.intValue();
        if (r97 >= 0) goto L_0x02a1;
    L_0x015f:
        r97 = r97 * -1;
        r5 = 0;
        r0 = r163;
        r0.hasAllMentionsLocal = r5;
    L_0x0166:
        r5 = 4;
        r0 = r95;
        if (r0 != r5) goto L_0x01a3;
    L_0x016b:
        r0 = r96;
        r1 = r163;
        r1.startLoadFromMessageId = r0;
        r5 = r102.size();
        r4 = r5 + -1;
    L_0x0177:
        if (r4 <= 0) goto L_0x01a3;
    L_0x0179:
        r0 = r102;
        r121 = r0.get(r4);
        r121 = (org.telegram.messenger.MessageObject) r121;
        r0 = r121;
        r5 = r0.type;
        if (r5 >= 0) goto L_0x02ae;
    L_0x0187:
        r5 = r121.getId();
        r0 = r163;
        r6 = r0.startLoadFromMessageId;
        if (r5 != r6) goto L_0x02ae;
    L_0x0191:
        r5 = r4 + -1;
        r0 = r102;
        r5 = r0.get(r5);
        r5 = (org.telegram.messenger.MessageObject) r5;
        r5 = r5.getId();
        r0 = r163;
        r0.startLoadFromMessageId = r5;
    L_0x01a3:
        r159 = 0;
        r142 = 0;
        if (r70 == 0) goto L_0x02c7;
    L_0x01a9:
        r5 = 5;
        r5 = r165[r5];
        r5 = (java.lang.Integer) r5;
        r5 = r5.intValue();
        r0 = r163;
        r0.last_message_id = r5;
        r5 = 3;
        r0 = r95;
        if (r0 != r5) goto L_0x02b2;
    L_0x01bb:
        r0 = r163;
        r5 = r0.loadingFromOldPosition;
        if (r5 == 0) goto L_0x01e1;
    L_0x01c1:
        r5 = 6;
        r5 = r165[r5];
        r5 = (java.lang.Integer) r5;
        r5 = r5.intValue();
        r0 = r163;
        r0.unread_to_load = r5;
        r0 = r163;
        r5 = r0.unread_to_load;
        if (r5 == 0) goto L_0x01da;
    L_0x01d4:
        r0 = r70;
        r1 = r163;
        r1.createUnreadMessageAfterId = r0;
    L_0x01da:
        r142 = 1;
        r5 = 0;
        r0 = r163;
        r0.loadingFromOldPosition = r5;
    L_0x01e1:
        r5 = 0;
        r0 = r163;
        r0.first_unread_id = r5;
    L_0x01e6:
        r119 = 0;
        if (r95 == 0) goto L_0x01fd;
    L_0x01ea:
        r0 = r163;
        r6 = r0.forwardEndReached;
        r0 = r163;
        r5 = r0.startLoadFromMessageId;
        if (r5 != 0) goto L_0x02e6;
    L_0x01f4:
        r0 = r163;
        r5 = r0.last_message_id;
        if (r5 != 0) goto L_0x02e6;
    L_0x01fa:
        r5 = 1;
    L_0x01fb:
        r6[r94] = r5;
    L_0x01fd:
        r5 = 1;
        r0 = r95;
        if (r0 == r5) goto L_0x0207;
    L_0x0202:
        r5 = 3;
        r0 = r95;
        if (r0 != r5) goto L_0x022b;
    L_0x0207:
        r5 = 1;
        r0 = r94;
        if (r0 != r5) goto L_0x022b;
    L_0x020c:
        r0 = r163;
        r5 = r0.endReached;
        r6 = 0;
        r0 = r163;
        r7 = r0.cacheEndReached;
        r8 = 0;
        r11 = 1;
        r7[r8] = r11;
        r5[r6] = r11;
        r0 = r163;
        r5 = r0.forwardEndReached;
        r6 = 0;
        r7 = 0;
        r5[r6] = r7;
        r0 = r163;
        r5 = r0.minMessageId;
        r6 = 0;
        r7 = 0;
        r5[r6] = r7;
    L_0x022b:
        r0 = r163;
        r5 = r0.loadsCount;
        r6 = 1;
        if (r5 != r6) goto L_0x0244;
    L_0x0232:
        r5 = r102.size();
        r6 = 20;
        if (r5 <= r6) goto L_0x0244;
    L_0x023a:
        r0 = r163;
        r5 = r0.loadsCount;
        r5 = r5 + 1;
        r0 = r163;
        r0.loadsCount = r5;
    L_0x0244:
        r0 = r163;
        r5 = r0.firstLoading;
        if (r5 == 0) goto L_0x030a;
    L_0x024a:
        r0 = r163;
        r5 = r0.forwardEndReached;
        r5 = r5[r94];
        if (r5 != 0) goto L_0x02fb;
    L_0x0252:
        r0 = r163;
        r5 = r0.messages;
        r5.clear();
        r0 = r163;
        r5 = r0.messagesByDays;
        r5.clear();
        r0 = r163;
        r5 = r0.groupedMessagesMap;
        r5.clear();
        r4 = 0;
    L_0x0268:
        r5 = 2;
        if (r4 >= r5) goto L_0x02fb;
    L_0x026b:
        r0 = r163;
        r5 = r0.messagesDict;
        r5 = r5[r4];
        r5.clear();
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        if (r5 != 0) goto L_0x02e9;
    L_0x027a:
        r0 = r163;
        r5 = r0.maxMessageId;
        r6 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r5[r4] = r6;
        r0 = r163;
        r5 = r0.minMessageId;
        r6 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r5[r4] = r6;
    L_0x028b:
        r0 = r163;
        r5 = r0.maxDate;
        r6 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r5[r4] = r6;
        r0 = r163;
        r5 = r0.minDate;
        r6 = 0;
        r5[r4] = r6;
        r4 = r4 + 1;
        goto L_0x0268;
    L_0x029d:
        r94 = 1;
        goto L_0x011b;
    L_0x02a1:
        r0 = r163;
        r5 = r0.first;
        if (r5 == 0) goto L_0x0166;
    L_0x02a7:
        r5 = 1;
        r0 = r163;
        r0.hasAllMentionsLocal = r5;
        goto L_0x0166;
    L_0x02ae:
        r4 = r4 + -1;
        goto L_0x0177;
    L_0x02b2:
        r0 = r70;
        r1 = r163;
        r1.first_unread_id = r0;
        r5 = 6;
        r5 = r165[r5];
        r5 = (java.lang.Integer) r5;
        r5 = r5.intValue();
        r0 = r163;
        r0.unread_to_load = r5;
        goto L_0x01e6;
    L_0x02c7:
        r0 = r163;
        r5 = r0.startLoadFromMessageId;
        if (r5 == 0) goto L_0x01e6;
    L_0x02cd:
        r5 = 3;
        r0 = r95;
        if (r0 == r5) goto L_0x02d7;
    L_0x02d2:
        r5 = 4;
        r0 = r95;
        if (r0 != r5) goto L_0x01e6;
    L_0x02d7:
        r5 = 5;
        r5 = r165[r5];
        r5 = (java.lang.Integer) r5;
        r5 = r5.intValue();
        r0 = r163;
        r0.last_message_id = r5;
        goto L_0x01e6;
    L_0x02e6:
        r5 = 0;
        goto L_0x01fb;
    L_0x02e9:
        r0 = r163;
        r5 = r0.maxMessageId;
        r6 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r5[r4] = r6;
        r0 = r163;
        r5 = r0.minMessageId;
        r6 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r5[r4] = r6;
        goto L_0x028b;
    L_0x02fb:
        r5 = 0;
        r0 = r163;
        r0.firstLoading = r5;
        r5 = new org.telegram.ui.ChatActivity$84;
        r0 = r163;
        r5.<init>();
        org.telegram.messenger.AndroidUtilities.runOnUIThread(r5);
    L_0x030a:
        r5 = 1;
        r0 = r95;
        if (r0 != r5) goto L_0x0312;
    L_0x030f:
        java.util.Collections.reverse(r102);
    L_0x0312:
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        if (r5 != 0) goto L_0x0321;
    L_0x0318:
        r0 = r163;
        r6 = r0.dialog_id;
        r0 = r102;
        org.telegram.messenger.query.MessagesQuery.loadReplyMessagesForMessages(r0, r6);
    L_0x0321:
        r24 = 0;
        r5 = 2;
        r0 = r95;
        if (r0 != r5) goto L_0x0338;
    L_0x0328:
        r5 = r102.isEmpty();
        if (r5 == 0) goto L_0x0338;
    L_0x032e:
        if (r84 != 0) goto L_0x0338;
    L_0x0330:
        r0 = r163;
        r5 = r0.forwardEndReached;
        r6 = 0;
        r7 = 1;
        r5[r6] = r7;
    L_0x0338:
        r116 = 0;
        r40 = 0;
        r4 = 0;
    L_0x033d:
        r5 = r102.size();
        if (r4 >= r5) goto L_0x085a;
    L_0x0343:
        r0 = r102;
        r121 = r0.get(r4);
        r121 = (org.telegram.messenger.MessageObject) r121;
        r5 = r121.getApproximateHeight();
        r24 = r24 + r5;
        r0 = r163;
        r5 = r0.currentUser;
        if (r5 == 0) goto L_0x0381;
    L_0x0357:
        r0 = r163;
        r5 = r0.currentUser;
        r5 = r5.self;
        if (r5 == 0) goto L_0x0366;
    L_0x035f:
        r0 = r121;
        r5 = r0.messageOwner;
        r6 = 1;
        r5.out = r6;
    L_0x0366:
        r0 = r163;
        r5 = r0.currentUser;
        r5 = r5.bot;
        if (r5 == 0) goto L_0x0374;
    L_0x036e:
        r5 = r121.isOut();
        if (r5 != 0) goto L_0x037e;
    L_0x0374:
        r0 = r163;
        r5 = r0.currentUser;
        r5 = r5.id;
        r0 = r53;
        if (r5 != r0) goto L_0x0381;
    L_0x037e:
        r121.setIsRead();
    L_0x0381:
        r0 = r163;
        r5 = r0.messagesDict;
        r5 = r5[r94];
        r6 = r121.getId();
        r6 = java.lang.Integer.valueOf(r6);
        r5 = r5.containsKey(r6);
        if (r5 == 0) goto L_0x0398;
    L_0x0395:
        r4 = r4 + 1;
        goto L_0x033d;
    L_0x0398:
        r5 = 1;
        r0 = r94;
        if (r0 != r5) goto L_0x03a0;
    L_0x039d:
        r121.setIsRead();
    L_0x03a0:
        if (r94 != 0) goto L_0x03c1;
    L_0x03a2:
        r0 = r163;
        r5 = r0.currentChat;
        r5 = org.telegram.messenger.ChatObject.isChannel(r5);
        if (r5 == 0) goto L_0x03c1;
    L_0x03ac:
        r5 = r121.getId();
        r6 = 1;
        if (r5 != r6) goto L_0x03c1;
    L_0x03b3:
        r0 = r163;
        r5 = r0.endReached;
        r6 = 1;
        r5[r94] = r6;
        r0 = r163;
        r5 = r0.cacheEndReached;
        r6 = 1;
        r5[r94] = r6;
    L_0x03c1:
        r5 = r121.getId();
        if (r5 <= 0) goto L_0x06fb;
    L_0x03c7:
        r0 = r163;
        r5 = r0.maxMessageId;
        r6 = r121.getId();
        r0 = r163;
        r7 = r0.maxMessageId;
        r7 = r7[r94];
        r6 = java.lang.Math.min(r6, r7);
        r5[r94] = r6;
        r0 = r163;
        r5 = r0.minMessageId;
        r6 = r121.getId();
        r0 = r163;
        r7 = r0.minMessageId;
        r7 = r7[r94];
        r6 = java.lang.Math.max(r6, r7);
        r5[r94] = r6;
    L_0x03ef:
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.date;
        if (r5 == 0) goto L_0x042f;
    L_0x03f7:
        r0 = r163;
        r5 = r0.maxDate;
        r0 = r163;
        r6 = r0.maxDate;
        r6 = r6[r94];
        r0 = r121;
        r7 = r0.messageOwner;
        r7 = r7.date;
        r6 = java.lang.Math.max(r6, r7);
        r5[r94] = r6;
        r0 = r163;
        r5 = r0.minDate;
        r5 = r5[r94];
        if (r5 == 0) goto L_0x0423;
    L_0x0415:
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.date;
        r0 = r163;
        r6 = r0.minDate;
        r6 = r6[r94];
        if (r5 >= r6) goto L_0x042f;
    L_0x0423:
        r0 = r163;
        r5 = r0.minDate;
        r0 = r121;
        r6 = r0.messageOwner;
        r6 = r6.date;
        r5[r94] = r6;
    L_0x042f:
        r5 = r121.getId();
        r0 = r163;
        r6 = r0.last_message_id;
        if (r5 != r6) goto L_0x0440;
    L_0x0439:
        r0 = r163;
        r5 = r0.forwardEndReached;
        r6 = 1;
        r5[r94] = r6;
    L_0x0440:
        r0 = r121;
        r5 = r0.type;
        if (r5 < 0) goto L_0x0395;
    L_0x0446:
        r5 = 1;
        r0 = r94;
        if (r0 != r5) goto L_0x0455;
    L_0x044b:
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.action;
        r5 = r5 instanceof org.telegram.tgnet.TLRPC$TL_messageActionChatMigrateTo;
        if (r5 != 0) goto L_0x0395;
    L_0x0455:
        r5 = r121.isOut();
        if (r5 != 0) goto L_0x0463;
    L_0x045b:
        r5 = r121.isUnread();
        if (r5 == 0) goto L_0x0463;
    L_0x0461:
        r159 = 1;
    L_0x0463:
        r0 = r163;
        r5 = r0.messagesDict;
        r5 = r5[r94];
        r6 = r121.getId();
        r6 = java.lang.Integer.valueOf(r6);
        r0 = r121;
        r5.put(r6, r0);
        r0 = r163;
        r5 = r0.messagesByDays;
        r0 = r121;
        r6 = r0.dateKey;
        r58 = r5.get(r6);
        r58 = (java.util.ArrayList) r58;
        if (r58 != 0) goto L_0x04e7;
    L_0x0486:
        r58 = new java.util.ArrayList;
        r58.<init>();
        r0 = r163;
        r5 = r0.messagesByDays;
        r0 = r121;
        r6 = r0.dateKey;
        r0 = r58;
        r5.put(r6, r0);
        r55 = new org.telegram.tgnet.TLRPC$TL_message;
        r55.<init>();
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.date;
        r6 = (long) r5;
        r5 = org.telegram.messenger.LocaleController.formatDateChat(r6);
        r0 = r55;
        r0.message = r5;
        r5 = 0;
        r0 = r55;
        r0.id = r5;
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.date;
        r0 = r55;
        r0.date = r5;
        r56 = new org.telegram.messenger.MessageObject;
        r5 = 0;
        r6 = 0;
        r0 = r56;
        r1 = r55;
        r0.<init>(r1, r5, r6);
        r5 = 10;
        r0 = r56;
        r0.type = r5;
        r5 = 1;
        r0 = r56;
        r0.contentType = r5;
        r5 = 1;
        r0 = r56;
        r0.isDateObject = r5;
        r5 = 1;
        r0 = r95;
        if (r0 != r5) goto L_0x072b;
    L_0x04db:
        r0 = r163;
        r5 = r0.messages;
        r6 = 0;
        r0 = r56;
        r5.add(r6, r0);
    L_0x04e5:
        r119 = r119 + 1;
    L_0x04e7:
        r5 = r121.hasValidGroupId();
        if (r5 == 0) goto L_0x07a0;
    L_0x04ed:
        r0 = r163;
        r5 = r0.groupedMessagesMap;
        r0 = r121;
        r6 = r0.messageOwner;
        r6 = r6.grouped_id;
        r6 = java.lang.Long.valueOf(r6);
        r72 = r5.get(r6);
        r72 = (org.telegram.messenger.MessageObject.GroupedMessages) r72;
        if (r72 == 0) goto L_0x0552;
    L_0x0503:
        r0 = r163;
        r5 = r0.messages;
        r5 = r5.size();
        r6 = 1;
        if (r5 <= r6) goto L_0x0552;
    L_0x050e:
        r5 = 1;
        r0 = r95;
        if (r0 != r5) goto L_0x0736;
    L_0x0513:
        r0 = r163;
        r5 = r0.messages;
        r6 = 0;
        r135 = r5.get(r6);
        r135 = (org.telegram.messenger.MessageObject) r135;
    L_0x051e:
        r0 = r135;
        r5 = r0.messageOwner;
        r6 = r5.grouped_id;
        r0 = r121;
        r5 = r0.messageOwner;
        r12 = r5.grouped_id;
        r5 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r5 != 0) goto L_0x074c;
    L_0x052e:
        r0 = r135;
        r6 = r0.localGroupId;
        r12 = 0;
        r5 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r5 == 0) goto L_0x0552;
    L_0x0538:
        r0 = r135;
        r6 = r0.localGroupId;
        r0 = r121;
        r0.localGroupId = r6;
        r0 = r163;
        r5 = r0.groupedMessagesMap;
        r0 = r135;
        r6 = r0.localGroupId;
        r6 = java.lang.Long.valueOf(r6);
        r72 = r5.get(r6);
        r72 = (org.telegram.messenger.MessageObject.GroupedMessages) r72;
    L_0x0552:
        if (r72 != 0) goto L_0x076a;
    L_0x0554:
        r72 = new org.telegram.messenger.MessageObject$GroupedMessages;
        r72.<init>();
        r6 = r121.getGroupId();
        r0 = r72;
        r0.groupId = r6;
        r0 = r163;
        r5 = r0.groupedMessagesMap;
        r0 = r72;
        r6 = r0.groupId;
        r6 = java.lang.Long.valueOf(r6);
        r0 = r72;
        r5.put(r6, r0);
    L_0x0572:
        if (r116 != 0) goto L_0x0579;
    L_0x0574:
        r116 = new java.util.HashMap;
        r116.<init>();
    L_0x0579:
        r0 = r72;
        r6 = r0.groupId;
        r5 = java.lang.Long.valueOf(r6);
        r0 = r116;
        r1 = r72;
        r0.put(r5, r1);
        r5 = 1;
        r0 = r95;
        if (r0 != r5) goto L_0x0794;
    L_0x058d:
        r0 = r72;
        r5 = r0.messages;
        r0 = r121;
        r5.add(r0);
    L_0x0596:
        r119 = r119 + 1;
        r0 = r58;
        r1 = r121;
        r0.add(r1);
        r5 = 1;
        r0 = r95;
        if (r0 != r5) goto L_0x07b6;
    L_0x05a4:
        r0 = r163;
        r5 = r0.messages;
        r6 = 0;
        r0 = r121;
        r5.add(r6, r0);
    L_0x05ae:
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        if (r5 != 0) goto L_0x07cf;
    L_0x05b4:
        r0 = r163;
        r5 = r0.createUnreadMessageAfterId;
        if (r5 == 0) goto L_0x07cb;
    L_0x05ba:
        r5 = 1;
        r0 = r95;
        if (r0 == r5) goto L_0x07cb;
    L_0x05bf:
        r5 = r4 + 1;
        r6 = r102.size();
        if (r5 >= r6) goto L_0x07cb;
    L_0x05c7:
        r5 = r4 + 1;
        r0 = r102;
        r134 = r0.get(r5);
        r134 = (org.telegram.messenger.MessageObject) r134;
        r5 = r121.isOut();
        if (r5 != 0) goto L_0x05e1;
    L_0x05d7:
        r5 = r134.getId();
        r0 = r163;
        r6 = r0.createUnreadMessageAfterId;
        if (r5 < r6) goto L_0x05e3;
    L_0x05e1:
        r134 = 0;
    L_0x05e3:
        r5 = 2;
        r0 = r95;
        if (r0 != r5) goto L_0x0800;
    L_0x05e8:
        r5 = r121.getId();
        r0 = r163;
        r6 = r0.first_unread_id;
        if (r5 != r6) goto L_0x0800;
    L_0x05f2:
        r5 = org.telegram.messenger.AndroidUtilities.displaySize;
        r5 = r5.y;
        r5 = r5 / 2;
        r0 = r24;
        if (r0 > r5) goto L_0x0605;
    L_0x05fc:
        r0 = r163;
        r5 = r0.forwardEndReached;
        r6 = 0;
        r5 = r5[r6];
        if (r5 != 0) goto L_0x0654;
    L_0x0605:
        r55 = new org.telegram.tgnet.TLRPC$TL_message;
        r55.<init>();
        r5 = "";
        r0 = r55;
        r0.message = r5;
        r5 = 0;
        r0 = r55;
        r0.id = r5;
        r56 = new org.telegram.messenger.MessageObject;
        r5 = 0;
        r6 = 0;
        r0 = r56;
        r1 = r55;
        r0.<init>(r1, r5, r6);
        r5 = 6;
        r0 = r56;
        r0.type = r5;
        r5 = 2;
        r0 = r56;
        r0.contentType = r5;
        r0 = r163;
        r5 = r0.messages;
        r0 = r163;
        r6 = r0.messages;
        r6 = r6.size();
        r6 = r6 + -1;
        r0 = r56;
        r5.add(r6, r0);
        r0 = r56;
        r1 = r163;
        r1.unreadMessageObject = r0;
        r0 = r163;
        r5 = r0.unreadMessageObject;
        r0 = r163;
        r0.scrollToMessage = r5;
        r5 = -10000; // 0xffffffffffffd8f0 float:NaN double:NaN;
        r0 = r163;
        r0.scrollToMessagePosition = r5;
        r119 = r119 + 1;
    L_0x0654:
        r5 = 2;
        r0 = r95;
        if (r0 == r5) goto L_0x0395;
    L_0x0659:
        r0 = r163;
        r5 = r0.unreadMessageObject;
        if (r5 != 0) goto L_0x0395;
    L_0x065f:
        r0 = r163;
        r5 = r0.createUnreadMessageAfterId;
        if (r5 == 0) goto L_0x0395;
    L_0x0665:
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        if (r5 != 0) goto L_0x067b;
    L_0x066b:
        r5 = r121.isOut();
        if (r5 != 0) goto L_0x067b;
    L_0x0671:
        r5 = r121.getId();
        r0 = r163;
        r6 = r0.createUnreadMessageAfterId;
        if (r5 >= r6) goto L_0x0691;
    L_0x067b:
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        if (r5 == 0) goto L_0x0395;
    L_0x0681:
        r5 = r121.isOut();
        if (r5 != 0) goto L_0x0395;
    L_0x0687:
        r5 = r121.getId();
        r0 = r163;
        r6 = r0.createUnreadMessageAfterId;
        if (r5 > r6) goto L_0x0395;
    L_0x0691:
        r5 = 1;
        r0 = r95;
        if (r0 == r5) goto L_0x06a4;
    L_0x0696:
        if (r134 != 0) goto L_0x06a4;
    L_0x0698:
        if (r134 != 0) goto L_0x0395;
    L_0x069a:
        if (r48 == 0) goto L_0x0395;
    L_0x069c:
        r5 = r102.size();
        r5 = r5 + -1;
        if (r4 != r5) goto L_0x0395;
    L_0x06a4:
        r55 = new org.telegram.tgnet.TLRPC$TL_message;
        r55.<init>();
        r5 = "";
        r0 = r55;
        r0.message = r5;
        r5 = 0;
        r0 = r55;
        r0.id = r5;
        r56 = new org.telegram.messenger.MessageObject;
        r5 = 0;
        r6 = 0;
        r0 = r56;
        r1 = r55;
        r0.<init>(r1, r5, r6);
        r5 = 6;
        r0 = r56;
        r0.type = r5;
        r5 = 2;
        r0 = r56;
        r0.contentType = r5;
        r5 = 1;
        r0 = r95;
        if (r0 != r5) goto L_0x0845;
    L_0x06cf:
        r0 = r163;
        r5 = r0.messages;
        r6 = 1;
        r0 = r56;
        r5.add(r6, r0);
    L_0x06d9:
        r0 = r56;
        r1 = r163;
        r1.unreadMessageObject = r0;
        r5 = 3;
        r0 = r95;
        if (r0 != r5) goto L_0x06f7;
    L_0x06e4:
        r0 = r163;
        r5 = r0.unreadMessageObject;
        r0 = r163;
        r0.scrollToMessage = r5;
        r5 = 0;
        r0 = r163;
        r0.startLoadFromMessageId = r5;
        r5 = -9000; // 0xffffffffffffdcd8 float:NaN double:NaN;
        r0 = r163;
        r0.scrollToMessagePosition = r5;
    L_0x06f7:
        r119 = r119 + 1;
        goto L_0x0395;
    L_0x06fb:
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        if (r5 == 0) goto L_0x03ef;
    L_0x0701:
        r0 = r163;
        r5 = r0.maxMessageId;
        r6 = r121.getId();
        r0 = r163;
        r7 = r0.maxMessageId;
        r7 = r7[r94];
        r6 = java.lang.Math.max(r6, r7);
        r5[r94] = r6;
        r0 = r163;
        r5 = r0.minMessageId;
        r6 = r121.getId();
        r0 = r163;
        r7 = r0.minMessageId;
        r7 = r7[r94];
        r6 = java.lang.Math.min(r6, r7);
        r5[r94] = r6;
        goto L_0x03ef;
    L_0x072b:
        r0 = r163;
        r5 = r0.messages;
        r0 = r56;
        r5.add(r0);
        goto L_0x04e5;
    L_0x0736:
        r0 = r163;
        r5 = r0.messages;
        r0 = r163;
        r6 = r0.messages;
        r6 = r6.size();
        r6 = r6 + -2;
        r135 = r5.get(r6);
        r135 = (org.telegram.messenger.MessageObject) r135;
        goto L_0x051e;
    L_0x074c:
        r0 = r135;
        r5 = r0.messageOwner;
        r6 = r5.grouped_id;
        r0 = r121;
        r5 = r0.messageOwner;
        r12 = r5.grouped_id;
        r5 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r5 == 0) goto L_0x0552;
    L_0x075c:
        r5 = org.telegram.messenger.Utilities.random;
        r6 = r5.nextLong();
        r0 = r121;
        r0.localGroupId = r6;
        r72 = 0;
        goto L_0x0552;
    L_0x076a:
        if (r116 == 0) goto L_0x077c;
    L_0x076c:
        r6 = r121.getGroupId();
        r5 = java.lang.Long.valueOf(r6);
        r0 = r116;
        r5 = r0.containsKey(r5);
        if (r5 != 0) goto L_0x0572;
    L_0x077c:
        if (r40 != 0) goto L_0x0783;
    L_0x077e:
        r40 = new java.util.HashMap;
        r40.<init>();
    L_0x0783:
        r6 = r121.getGroupId();
        r5 = java.lang.Long.valueOf(r6);
        r0 = r40;
        r1 = r72;
        r0.put(r5, r1);
        goto L_0x0572;
    L_0x0794:
        r0 = r72;
        r5 = r0.messages;
        r6 = 0;
        r0 = r121;
        r5.add(r6, r0);
        goto L_0x0596;
    L_0x07a0:
        r0 = r121;
        r5 = r0.messageOwner;
        r6 = r5.grouped_id;
        r12 = 0;
        r5 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r5 == 0) goto L_0x0596;
    L_0x07ac:
        r0 = r121;
        r5 = r0.messageOwner;
        r6 = 0;
        r5.grouped_id = r6;
        goto L_0x0596;
    L_0x07b6:
        r0 = r163;
        r5 = r0.messages;
        r0 = r163;
        r6 = r0.messages;
        r6 = r6.size();
        r6 = r6 + -1;
        r0 = r121;
        r5.add(r6, r0);
        goto L_0x05ae;
    L_0x07cb:
        r134 = 0;
        goto L_0x05e3;
    L_0x07cf:
        r0 = r163;
        r5 = r0.createUnreadMessageAfterId;
        if (r5 == 0) goto L_0x07fc;
    L_0x07d5:
        r5 = 1;
        r0 = r95;
        if (r0 == r5) goto L_0x07fc;
    L_0x07da:
        r5 = r4 + -1;
        if (r5 < 0) goto L_0x07fc;
    L_0x07de:
        r5 = r4 + -1;
        r0 = r102;
        r134 = r0.get(r5);
        r134 = (org.telegram.messenger.MessageObject) r134;
        r5 = r121.isOut();
        if (r5 != 0) goto L_0x07f8;
    L_0x07ee:
        r5 = r134.getId();
        r0 = r163;
        r6 = r0.createUnreadMessageAfterId;
        if (r5 < r6) goto L_0x05e3;
    L_0x07f8:
        r134 = 0;
        goto L_0x05e3;
    L_0x07fc:
        r134 = 0;
        goto L_0x05e3;
    L_0x0800:
        r5 = 3;
        r0 = r95;
        if (r0 == r5) goto L_0x080a;
    L_0x0805:
        r5 = 4;
        r0 = r95;
        if (r0 != r5) goto L_0x0654;
    L_0x080a:
        r5 = r121.getId();
        r0 = r163;
        r6 = r0.startLoadFromMessageId;
        if (r5 != r6) goto L_0x0654;
    L_0x0814:
        r0 = r163;
        r5 = r0.needSelectFromMessageId;
        if (r5 == 0) goto L_0x083d;
    L_0x081a:
        r5 = r121.getId();
        r0 = r163;
        r0.highlightMessageId = r5;
    L_0x0822:
        r0 = r121;
        r1 = r163;
        r1.scrollToMessage = r0;
        r5 = 0;
        r0 = r163;
        r0.startLoadFromMessageId = r5;
        r0 = r163;
        r5 = r0.scrollToMessagePosition;
        r6 = -10000; // 0xffffffffffffd8f0 float:NaN double:NaN;
        if (r5 != r6) goto L_0x0654;
    L_0x0835:
        r5 = -9000; // 0xffffffffffffdcd8 float:NaN double:NaN;
        r0 = r163;
        r0.scrollToMessagePosition = r5;
        goto L_0x0654;
    L_0x083d:
        r5 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r0 = r163;
        r0.highlightMessageId = r5;
        goto L_0x0822;
    L_0x0845:
        r0 = r163;
        r5 = r0.messages;
        r0 = r163;
        r6 = r0.messages;
        r6 = r6.size();
        r6 = r6 + -1;
        r0 = r56;
        r5.add(r6, r0);
        goto L_0x06d9;
    L_0x085a:
        if (r48 == 0) goto L_0x0861;
    L_0x085c:
        r5 = 0;
        r0 = r163;
        r0.createUnreadMessageAfterId = r5;
    L_0x0861:
        if (r95 != 0) goto L_0x086f;
    L_0x0863:
        if (r119 != 0) goto L_0x086f;
    L_0x0865:
        r0 = r163;
        r5 = r0.loadsCount;
        r5 = r5 + -1;
        r0 = r163;
        r0.loadsCount = r5;
    L_0x086f:
        if (r116 == 0) goto L_0x08dc;
    L_0x0871:
        r5 = r116.entrySet();
        r5 = r5.iterator();
    L_0x0879:
        r6 = r5.hasNext();
        if (r6 == 0) goto L_0x08dc;
    L_0x087f:
        r64 = r5.next();
        r64 = (java.util.Map.Entry) r64;
        r72 = r64.getValue();
        r72 = (org.telegram.messenger.MessageObject.GroupedMessages) r72;
        r72.calculate();
        r0 = r163;
        r6 = r0.chatAdapter;
        if (r6 == 0) goto L_0x0879;
    L_0x0894:
        if (r40 == 0) goto L_0x0879;
    L_0x0896:
        r6 = r64.getKey();
        r0 = r40;
        r6 = r0.containsKey(r6);
        if (r6 == 0) goto L_0x0879;
    L_0x08a2:
        r0 = r72;
        r6 = r0.messages;
        r0 = r72;
        r7 = r0.messages;
        r7 = r7.size();
        r7 = r7 + -1;
        r106 = r6.get(r7);
        r106 = (org.telegram.messenger.MessageObject) r106;
        r0 = r163;
        r6 = r0.messages;
        r0 = r106;
        r78 = r6.indexOf(r0);
        if (r78 < 0) goto L_0x0879;
    L_0x08c2:
        r0 = r163;
        r6 = r0.chatAdapter;
        r0 = r163;
        r7 = r0.chatAdapter;
        r7 = r7.messagesStartRow;
        r7 = r7 + r78;
        r0 = r72;
        r8 = r0.messages;
        r8 = r8.size();
        r6.notifyItemRangeChanged(r7, r8);
        goto L_0x0879;
    L_0x08dc:
        r0 = r163;
        r5 = r0.forwardEndReached;
        r5 = r5[r94];
        if (r5 == 0) goto L_0x08f8;
    L_0x08e4:
        r5 = 1;
        r0 = r94;
        if (r0 == r5) goto L_0x08f8;
    L_0x08e9:
        r5 = 0;
        r0 = r163;
        r0.first_unread_id = r5;
        r5 = 0;
        r0 = r163;
        r0.last_message_id = r5;
        r5 = 0;
        r0 = r163;
        r0.createUnreadMessageAfterId = r5;
    L_0x08f8:
        r5 = 1;
        r0 = r95;
        if (r0 != r5) goto L_0x0a44;
    L_0x08fd:
        r141 = 0;
        r5 = r102.size();
        r0 = r47;
        if (r5 == r0) goto L_0x0948;
    L_0x0907:
        if (r84 == 0) goto L_0x0917;
    L_0x0909:
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        if (r5 != 0) goto L_0x0917;
    L_0x090f:
        r0 = r163;
        r5 = r0.forwardEndReached;
        r5 = r5[r94];
        if (r5 == 0) goto L_0x0948;
    L_0x0917:
        r0 = r163;
        r5 = r0.forwardEndReached;
        r6 = 1;
        r5[r94] = r6;
        r5 = 1;
        r0 = r94;
        if (r0 == r5) goto L_0x0943;
    L_0x0923:
        r5 = 0;
        r0 = r163;
        r0.first_unread_id = r5;
        r5 = 0;
        r0 = r163;
        r0.last_message_id = r5;
        r5 = 0;
        r0 = r163;
        r0.createUnreadMessageAfterId = r5;
        r0 = r163;
        r5 = r0.chatAdapter;
        r0 = r163;
        r6 = r0.chatAdapter;
        r6 = r6.loadingDownRow;
        r5.notifyItemRemoved(r6);
        r141 = r141 + 1;
    L_0x0943:
        r5 = 0;
        r0 = r163;
        r0.startLoadFromMessageId = r5;
    L_0x0948:
        if (r119 <= 0) goto L_0x0982;
    L_0x094a:
        r0 = r163;
        r5 = r0.chatLayoutManager;
        r68 = r5.findFirstVisibleItemPosition();
        r146 = 0;
        if (r68 != 0) goto L_0x0958;
    L_0x0956:
        r68 = r68 + 1;
    L_0x0958:
        r0 = r163;
        r5 = r0.chatLayoutManager;
        r0 = r68;
        r69 = r5.findViewByPosition(r0);
        if (r69 != 0) goto L_0x0a2b;
    L_0x0964:
        r146 = 0;
    L_0x0966:
        r0 = r163;
        r5 = r0.chatAdapter;
        r6 = 1;
        r0 = r119;
        r5.notifyItemRangeInserted(r6, r0);
        r5 = -1;
        r0 = r68;
        if (r0 == r5) goto L_0x0982;
    L_0x0975:
        r0 = r163;
        r5 = r0.chatLayoutManager;
        r6 = r68 + r119;
        r6 = r6 - r141;
        r0 = r146;
        r5.scrollToPositionWithOffset(r6, r0);
    L_0x0982:
        r5 = 0;
        r0 = r163;
        r0.loadingForward = r5;
    L_0x0987:
        r0 = r163;
        r5 = r0.first;
        if (r5 == 0) goto L_0x09c3;
    L_0x098d:
        r0 = r163;
        r5 = r0.messages;
        r5 = r5.size();
        if (r5 <= 0) goto L_0x09c3;
    L_0x0997:
        if (r94 != 0) goto L_0x09be;
    L_0x0999:
        r160 = r159;
        r92 = r91;
        r0 = r163;
        r5 = r0.messages;
        r6 = 0;
        r5 = r5.get(r6);
        r5 = (org.telegram.messenger.MessageObject) r5;
        r93 = r5.getId();
        r5 = new org.telegram.ui.ChatActivity$85;
        r0 = r163;
        r1 = r93;
        r2 = r92;
        r3 = r160;
        r5.<init>(r1, r2, r3);
        r6 = 700; // 0x2bc float:9.81E-43 double:3.46E-321;
        org.telegram.messenger.AndroidUtilities.runOnUIThread(r5, r6);
    L_0x09be:
        r5 = 0;
        r0 = r163;
        r0.first = r5;
    L_0x09c3:
        r0 = r163;
        r5 = r0.messages;
        r5 = r5.isEmpty();
        if (r5 == 0) goto L_0x09f1;
    L_0x09cd:
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        if (r5 != 0) goto L_0x09f1;
    L_0x09d3:
        r0 = r163;
        r5 = r0.currentUser;
        if (r5 == 0) goto L_0x09f1;
    L_0x09d9:
        r0 = r163;
        r5 = r0.currentUser;
        r5 = r5.bot;
        if (r5 == 0) goto L_0x09f1;
    L_0x09e1:
        r0 = r163;
        r5 = r0.botUser;
        if (r5 != 0) goto L_0x09f1;
    L_0x09e7:
        r5 = "";
        r0 = r163;
        r0.botUser = r5;
        r163.updateBottomOverlay();
    L_0x09f1:
        if (r119 != 0) goto L_0x0d34;
    L_0x09f3:
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        if (r5 == 0) goto L_0x0d34;
    L_0x09f9:
        r0 = r163;
        r5 = r0.endReached;
        r6 = 0;
        r5 = r5[r6];
        if (r5 != 0) goto L_0x0d34;
    L_0x0a02:
        r5 = 1;
        r0 = r163;
        r0.first = r5;
        r0 = r163;
        r5 = r0.chatListView;
        if (r5 == 0) goto L_0x0a15;
    L_0x0a0d:
        r0 = r163;
        r5 = r0.chatListView;
        r6 = 0;
        r5.setEmptyView(r6);
    L_0x0a15:
        r0 = r163;
        r5 = r0.emptyViewContainer;
        if (r5 == 0) goto L_0x0a23;
    L_0x0a1b:
        r0 = r163;
        r5 = r0.emptyViewContainer;
        r6 = 4;
        r5.setVisibility(r6);
    L_0x0a23:
        r5 = 0;
        r0 = r163;
        r0.checkScrollForLoad(r5);
        goto L_0x005b;
    L_0x0a2b:
        r0 = r163;
        r5 = r0.chatListView;
        r5 = r5.getMeasuredHeight();
        r6 = r69.getBottom();
        r5 = r5 - r6;
        r0 = r163;
        r6 = r0.chatListView;
        r6 = r6.getPaddingBottom();
        r146 = r5 - r6;
        goto L_0x0966;
    L_0x0a44:
        r5 = r102.size();
        r0 = r47;
        if (r5 >= r0) goto L_0x0a77;
    L_0x0a4c:
        r5 = 3;
        r0 = r95;
        if (r0 == r5) goto L_0x0a77;
    L_0x0a51:
        r5 = 4;
        r0 = r95;
        if (r0 == r5) goto L_0x0a77;
    L_0x0a56:
        if (r84 == 0) goto L_0x0bd0;
    L_0x0a58:
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        if (r5 != 0) goto L_0x0a64;
    L_0x0a5e:
        r0 = r163;
        r5 = r0.isBroadcast;
        if (r5 == 0) goto L_0x0a6b;
    L_0x0a64:
        r0 = r163;
        r5 = r0.endReached;
        r6 = 1;
        r5[r94] = r6;
    L_0x0a6b:
        r5 = 2;
        r0 = r95;
        if (r0 == r5) goto L_0x0a77;
    L_0x0a70:
        r0 = r163;
        r5 = r0.cacheEndReached;
        r6 = 1;
        r5[r94] = r6;
    L_0x0a77:
        r5 = 0;
        r0 = r163;
        r0.loading = r5;
        r0 = r163;
        r5 = r0.chatListView;
        if (r5 == 0) goto L_0x0d22;
    L_0x0a82:
        r0 = r163;
        r5 = r0.first;
        if (r5 != 0) goto L_0x0a94;
    L_0x0a88:
        r0 = r163;
        r5 = r0.scrollToTopOnResume;
        if (r5 != 0) goto L_0x0a94;
    L_0x0a8e:
        r0 = r163;
        r5 = r0.forceScrollToTop;
        if (r5 == 0) goto L_0x0c50;
    L_0x0a94:
        r5 = 0;
        r0 = r163;
        r0.forceScrollToTop = r5;
        r0 = r163;
        r5 = r0.chatAdapter;
        r5.notifyDataSetChanged();
        r0 = r163;
        r5 = r0.scrollToMessage;
        if (r5 == 0) goto L_0x0c4b;
    L_0x0aa6:
        r33 = 1;
        r0 = r163;
        r5 = r0.startLoadFromMessageOffset;
        r6 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        if (r5 == r6) goto L_0x0bee;
    L_0x0ab1:
        r0 = r163;
        r5 = r0.startLoadFromMessageOffset;
        r5 = -r5;
        r0 = r163;
        r6 = r0.chatListView;
        r6 = r6.getPaddingBottom();
        r162 = r5 - r6;
        r5 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r0 = r163;
        r0.startLoadFromMessageOffset = r5;
    L_0x0ac7:
        r0 = r163;
        r5 = r0.messages;
        r5 = r5.isEmpty();
        if (r5 != 0) goto L_0x0b14;
    L_0x0ad1:
        r0 = r163;
        r5 = r0.messages;
        r0 = r163;
        r6 = r0.messages;
        r6 = r6.size();
        r6 = r6 + -1;
        r5 = r5.get(r6);
        r0 = r163;
        r6 = r0.scrollToMessage;
        if (r5 == r6) goto L_0x0b01;
    L_0x0ae9:
        r0 = r163;
        r5 = r0.messages;
        r0 = r163;
        r6 = r0.messages;
        r6 = r6.size();
        r6 = r6 + -2;
        r5 = r5.get(r6);
        r0 = r163;
        r6 = r0.scrollToMessage;
        if (r5 != r6) goto L_0x0c29;
    L_0x0b01:
        r0 = r163;
        r5 = r0.chatLayoutManager;
        r0 = r163;
        r6 = r0.chatAdapter;
        r6 = r6.loadingUpRow;
        r0 = r162;
        r1 = r33;
        r5.scrollToPositionWithOffset(r6, r0, r1);
    L_0x0b14:
        r0 = r163;
        r5 = r0.chatListView;
        r5.invalidate();
        r0 = r163;
        r5 = r0.scrollToMessagePosition;
        r6 = -10000; // 0xffffffffffffd8f0 float:NaN double:NaN;
        if (r5 == r6) goto L_0x0b2b;
    L_0x0b23:
        r0 = r163;
        r5 = r0.scrollToMessagePosition;
        r6 = -9000; // 0xffffffffffffdcd8 float:NaN double:NaN;
        if (r5 != r6) goto L_0x0b67;
    L_0x0b2b:
        r5 = 1;
        r6 = 1;
        r0 = r163;
        r0.showPagedownButton(r5, r6);
        r5 = 3;
        r0 = r95;
        if (r0 != r5) goto L_0x0b67;
    L_0x0b37:
        r0 = r163;
        r5 = r0.unread_to_load;
        if (r5 == 0) goto L_0x0b67;
    L_0x0b3d:
        if (r142 == 0) goto L_0x0b67;
    L_0x0b3f:
        r0 = r163;
        r5 = r0.pagedownButtonCounter;
        r6 = 0;
        r5.setVisibility(r6);
        r0 = r163;
        r5 = r0.pagedownButtonCounter;
        r6 = "%d";
        r7 = 1;
        r7 = new java.lang.Object[r7];
        r8 = 0;
        r0 = r163;
        r11 = r0.unread_to_load;
        r0 = r163;
        r0.newUnreadMessageCount = r11;
        r11 = java.lang.Integer.valueOf(r11);
        r7[r8] = r11;
        r6 = java.lang.String.format(r6, r7);
        r5.setText(r6);
    L_0x0b67:
        r5 = -10000; // 0xffffffffffffd8f0 float:NaN double:NaN;
        r0 = r163;
        r0.scrollToMessagePosition = r5;
        r5 = 0;
        r0 = r163;
        r0.scrollToMessage = r5;
    L_0x0b72:
        if (r97 == 0) goto L_0x0ba1;
    L_0x0b74:
        r5 = 1;
        r6 = 1;
        r0 = r163;
        r0.showMentiondownButton(r5, r6);
        r0 = r163;
        r5 = r0.mentiondownButtonCounter;
        r6 = 0;
        r5.setVisibility(r6);
        r0 = r163;
        r5 = r0.mentiondownButtonCounter;
        r6 = "%d";
        r7 = 1;
        r7 = new java.lang.Object[r7];
        r8 = 0;
        r0 = r97;
        r1 = r163;
        r1.newMentionsCount = r0;
        r11 = java.lang.Integer.valueOf(r97);
        r7[r8] = r11;
        r6 = java.lang.String.format(r6, r7);
        r5.setText(r6);
    L_0x0ba1:
        r0 = r163;
        r5 = r0.paused;
        if (r5 == 0) goto L_0x0bb7;
    L_0x0ba7:
        r5 = 1;
        r0 = r163;
        r0.scrollToTopOnResume = r5;
        r0 = r163;
        r5 = r0.scrollToMessage;
        if (r5 == 0) goto L_0x0bb7;
    L_0x0bb2:
        r5 = 1;
        r0 = r163;
        r0.scrollToTopUnReadOnResume = r5;
    L_0x0bb7:
        r0 = r163;
        r5 = r0.first;
        if (r5 == 0) goto L_0x0987;
    L_0x0bbd:
        r0 = r163;
        r5 = r0.chatListView;
        if (r5 == 0) goto L_0x0987;
    L_0x0bc3:
        r0 = r163;
        r5 = r0.chatListView;
        r0 = r163;
        r6 = r0.emptyViewContainer;
        r5.setEmptyView(r6);
        goto L_0x0987;
    L_0x0bd0:
        r5 = 2;
        r0 = r95;
        if (r0 != r5) goto L_0x0be5;
    L_0x0bd5:
        r5 = r102.size();
        if (r5 != 0) goto L_0x0a77;
    L_0x0bdb:
        r0 = r163;
        r5 = r0.messages;
        r5 = r5.isEmpty();
        if (r5 == 0) goto L_0x0a77;
    L_0x0be5:
        r0 = r163;
        r5 = r0.endReached;
        r6 = 1;
        r5[r94] = r6;
        goto L_0x0a77;
    L_0x0bee:
        r0 = r163;
        r5 = r0.scrollToMessagePosition;
        r6 = -9000; // 0xffffffffffffdcd8 float:NaN double:NaN;
        if (r5 != r6) goto L_0x0c04;
    L_0x0bf6:
        r0 = r163;
        r5 = r0.scrollToMessage;
        r0 = r163;
        r162 = r0.getScrollOffsetForMessage(r5);
        r33 = 0;
        goto L_0x0ac7;
    L_0x0c04:
        r0 = r163;
        r5 = r0.scrollToMessagePosition;
        r6 = -10000; // 0xffffffffffffd8f0 float:NaN double:NaN;
        if (r5 != r6) goto L_0x0c21;
    L_0x0c0c:
        r0 = r163;
        r5 = r0.chatListView;
        r5 = r5.getPaddingTop();
        r5 = -r5;
        r6 = 1088421888; // 0x40e00000 float:7.0 double:5.37751863E-315;
        r6 = org.telegram.messenger.AndroidUtilities.dp(r6);
        r162 = r5 - r6;
        r33 = 0;
        goto L_0x0ac7;
    L_0x0c21:
        r0 = r163;
        r0 = r0.scrollToMessagePosition;
        r162 = r0;
        goto L_0x0ac7;
    L_0x0c29:
        r0 = r163;
        r5 = r0.chatLayoutManager;
        r0 = r163;
        r6 = r0.chatAdapter;
        r6 = r6.messagesStartRow;
        r0 = r163;
        r7 = r0.messages;
        r0 = r163;
        r8 = r0.scrollToMessage;
        r7 = r7.indexOf(r8);
        r6 = r6 + r7;
        r0 = r162;
        r1 = r33;
        r5.scrollToPositionWithOffset(r6, r0, r1);
        goto L_0x0b14;
    L_0x0c4b:
        r163.moveScrollToLastMessage();
        goto L_0x0b72;
    L_0x0c50:
        if (r119 == 0) goto L_0x0cf8;
    L_0x0c52:
        r63 = 0;
        r0 = r163;
        r5 = r0.endReached;
        r5 = r5[r94];
        if (r5 == 0) goto L_0x0c88;
    L_0x0c5c:
        if (r94 != 0) goto L_0x0c68;
    L_0x0c5e:
        r0 = r163;
        r6 = r0.mergeDialogId;
        r12 = 0;
        r5 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r5 == 0) goto L_0x0c6d;
    L_0x0c68:
        r5 = 1;
        r0 = r94;
        if (r0 != r5) goto L_0x0c88;
    L_0x0c6d:
        r63 = 1;
        r0 = r163;
        r5 = r0.chatAdapter;
        r0 = r163;
        r6 = r0.chatAdapter;
        r6 = r6.loadingUpRow;
        r6 = r6 + -1;
        r7 = 2;
        r5.notifyItemRangeChanged(r6, r7);
        r0 = r163;
        r5 = r0.chatAdapter;
        r5.updateRows();
    L_0x0c88:
        r0 = r163;
        r5 = r0.chatLayoutManager;
        r68 = r5.findFirstVisibleItemPosition();
        r0 = r163;
        r5 = r0.chatLayoutManager;
        r0 = r68;
        r69 = r5.findViewByPosition(r0);
        if (r69 != 0) goto L_0x0cdc;
    L_0x0c9c:
        r146 = 0;
    L_0x0c9e:
        if (r63 == 0) goto L_0x0cf4;
    L_0x0ca0:
        r5 = 1;
    L_0x0ca1:
        r5 = r119 - r5;
        if (r5 <= 0) goto L_0x0cca;
    L_0x0ca5:
        r0 = r163;
        r5 = r0.chatAdapter;
        r83 = r5.messagesEndRow;
        r0 = r163;
        r5 = r0.chatAdapter;
        r0 = r163;
        r6 = r0.chatAdapter;
        r6 = r6.loadingUpRow;
        r5.notifyItemChanged(r6);
        r0 = r163;
        r6 = r0.chatAdapter;
        if (r63 == 0) goto L_0x0cf6;
    L_0x0cc2:
        r5 = 1;
    L_0x0cc3:
        r5 = r119 - r5;
        r0 = r83;
        r6.notifyItemRangeInserted(r0, r5);
    L_0x0cca:
        r5 = -1;
        r0 = r68;
        if (r0 == r5) goto L_0x0ba1;
    L_0x0ccf:
        r0 = r163;
        r5 = r0.chatLayoutManager;
        r0 = r68;
        r1 = r146;
        r5.scrollToPositionWithOffset(r0, r1);
        goto L_0x0ba1;
    L_0x0cdc:
        r0 = r163;
        r5 = r0.chatListView;
        r5 = r5.getMeasuredHeight();
        r6 = r69.getBottom();
        r5 = r5 - r6;
        r0 = r163;
        r6 = r0.chatListView;
        r6 = r6.getPaddingBottom();
        r146 = r5 - r6;
        goto L_0x0c9e;
    L_0x0cf4:
        r5 = 0;
        goto L_0x0ca1;
    L_0x0cf6:
        r5 = 0;
        goto L_0x0cc3;
    L_0x0cf8:
        r0 = r163;
        r5 = r0.endReached;
        r5 = r5[r94];
        if (r5 == 0) goto L_0x0ba1;
    L_0x0d00:
        if (r94 != 0) goto L_0x0d0c;
    L_0x0d02:
        r0 = r163;
        r6 = r0.mergeDialogId;
        r12 = 0;
        r5 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r5 == 0) goto L_0x0d11;
    L_0x0d0c:
        r5 = 1;
        r0 = r94;
        if (r0 != r5) goto L_0x0ba1;
    L_0x0d11:
        r0 = r163;
        r5 = r0.chatAdapter;
        r0 = r163;
        r6 = r0.chatAdapter;
        r6 = r6.loadingUpRow;
        r5.notifyItemRemoved(r6);
        goto L_0x0ba1;
    L_0x0d22:
        r5 = 1;
        r0 = r163;
        r0.scrollToTopOnResume = r5;
        r0 = r163;
        r5 = r0.scrollToMessage;
        if (r5 == 0) goto L_0x0987;
    L_0x0d2d:
        r5 = 1;
        r0 = r163;
        r0.scrollToTopUnReadOnResume = r5;
        goto L_0x0987;
    L_0x0d34:
        r0 = r163;
        r5 = r0.progressView;
        if (r5 == 0) goto L_0x0a23;
    L_0x0d3a:
        r0 = r163;
        r5 = r0.progressView;
        r6 = 4;
        r5.setVisibility(r6);
        goto L_0x0a23;
    L_0x0d44:
        r5 = org.telegram.messenger.NotificationCenter.emojiDidLoaded;
        r0 = r164;
        if (r0 != r5) goto L_0x0d8d;
    L_0x0d4a:
        r0 = r163;
        r5 = r0.chatListView;
        if (r5 == 0) goto L_0x0d57;
    L_0x0d50:
        r0 = r163;
        r5 = r0.chatListView;
        r5.invalidateViews();
    L_0x0d57:
        r0 = r163;
        r5 = r0.replyObjectTextView;
        if (r5 == 0) goto L_0x0d64;
    L_0x0d5d:
        r0 = r163;
        r5 = r0.replyObjectTextView;
        r5.invalidate();
    L_0x0d64:
        r0 = r163;
        r5 = r0.alertTextView;
        if (r5 == 0) goto L_0x0d71;
    L_0x0d6a:
        r0 = r163;
        r5 = r0.alertTextView;
        r5.invalidate();
    L_0x0d71:
        r0 = r163;
        r5 = r0.pinnedMessageTextView;
        if (r5 == 0) goto L_0x0d7e;
    L_0x0d77:
        r0 = r163;
        r5 = r0.pinnedMessageTextView;
        r5.invalidate();
    L_0x0d7e:
        r0 = r163;
        r5 = r0.mentionListView;
        if (r5 == 0) goto L_0x005b;
    L_0x0d84:
        r0 = r163;
        r5 = r0.mentionListView;
        r5.invalidateViews();
        goto L_0x005b;
    L_0x0d8d:
        r5 = org.telegram.messenger.NotificationCenter.updateInterfaces;
        r0 = r164;
        if (r0 != r5) goto L_0x0e7a;
    L_0x0d93:
        r5 = 0;
        r5 = r165[r5];
        r5 = (java.lang.Integer) r5;
        r152 = r5.intValue();
        r5 = r152 & 1;
        if (r5 != 0) goto L_0x0da4;
    L_0x0da0:
        r5 = r152 & 16;
        if (r5 == 0) goto L_0x0dc7;
    L_0x0da4:
        r0 = r163;
        r5 = r0.currentChat;
        if (r5 == 0) goto L_0x0e58;
    L_0x0daa:
        r5 = org.telegram.messenger.MessagesController.getInstance();
        r0 = r163;
        r6 = r0.currentChat;
        r6 = r6.id;
        r6 = java.lang.Integer.valueOf(r6);
        r44 = r5.getChat(r6);
        if (r44 == 0) goto L_0x0dc4;
    L_0x0dbe:
        r0 = r44;
        r1 = r163;
        r1.currentChat = r0;
    L_0x0dc4:
        r163.updateTitle();
    L_0x0dc7:
        r153 = 0;
        r5 = r152 & 32;
        if (r5 != 0) goto L_0x0dd1;
    L_0x0dcd:
        r5 = r152 & 4;
        if (r5 == 0) goto L_0x0de6;
    L_0x0dd1:
        r0 = r163;
        r5 = r0.currentChat;
        if (r5 == 0) goto L_0x0de4;
    L_0x0dd7:
        r0 = r163;
        r5 = r0.avatarContainer;
        if (r5 == 0) goto L_0x0de4;
    L_0x0ddd:
        r0 = r163;
        r5 = r0.avatarContainer;
        r5.updateOnlineCount();
    L_0x0de4:
        r153 = 1;
    L_0x0de6:
        r5 = r152 & 2;
        if (r5 != 0) goto L_0x0df2;
    L_0x0dea:
        r5 = r152 & 8;
        if (r5 != 0) goto L_0x0df2;
    L_0x0dee:
        r5 = r152 & 1;
        if (r5 == 0) goto L_0x0df8;
    L_0x0df2:
        r163.checkAndUpdateAvatar();
        r163.updateVisibleRows();
    L_0x0df8:
        r5 = r152 & 64;
        if (r5 == 0) goto L_0x0dfe;
    L_0x0dfc:
        r153 = 1;
    L_0x0dfe:
        r0 = r152;
        r5 = r0 & 8192;
        if (r5 == 0) goto L_0x0e3e;
    L_0x0e04:
        r0 = r163;
        r5 = r0.currentChat;
        r5 = org.telegram.messenger.ChatObject.isChannel(r5);
        if (r5 == 0) goto L_0x0e3e;
    L_0x0e0e:
        r5 = org.telegram.messenger.MessagesController.getInstance();
        r0 = r163;
        r6 = r0.currentChat;
        r6 = r6.id;
        r6 = java.lang.Integer.valueOf(r6);
        r44 = r5.getChat(r6);
        if (r44 == 0) goto L_0x005b;
    L_0x0e22:
        r0 = r44;
        r1 = r163;
        r1.currentChat = r0;
        r153 = 1;
        r163.updateBottomOverlay();
        r0 = r163;
        r5 = r0.chatActivityEnterView;
        if (r5 == 0) goto L_0x0e3e;
    L_0x0e33:
        r0 = r163;
        r5 = r0.chatActivityEnterView;
        r0 = r163;
        r6 = r0.dialog_id;
        r5.setDialogId(r6);
    L_0x0e3e:
        r0 = r163;
        r5 = r0.avatarContainer;
        if (r5 == 0) goto L_0x0e4d;
    L_0x0e44:
        if (r153 == 0) goto L_0x0e4d;
    L_0x0e46:
        r0 = r163;
        r5 = r0.avatarContainer;
        r5.updateSubtitle();
    L_0x0e4d:
        r0 = r152;
        r5 = r0 & 128;
        if (r5 == 0) goto L_0x005b;
    L_0x0e53:
        r163.updateContactStatus();
        goto L_0x005b;
    L_0x0e58:
        r0 = r163;
        r5 = r0.currentUser;
        if (r5 == 0) goto L_0x0dc4;
    L_0x0e5e:
        r5 = org.telegram.messenger.MessagesController.getInstance();
        r0 = r163;
        r6 = r0.currentUser;
        r6 = r6.id;
        r6 = java.lang.Integer.valueOf(r6);
        r156 = r5.getUser(r6);
        if (r156 == 0) goto L_0x0dc4;
    L_0x0e72:
        r0 = r156;
        r1 = r163;
        r1.currentUser = r0;
        goto L_0x0dc4;
    L_0x0e7a:
        r5 = org.telegram.messenger.NotificationCenter.didReceivedNewMessages;
        r0 = r164;
        if (r0 != r5) goto L_0x1ad7;
    L_0x0e80:
        r5 = 0;
        r5 = r165[r5];
        r5 = (java.lang.Long) r5;
        r60 = r5.longValue();
        r0 = r163;
        r6 = r0.dialog_id;
        r5 = (r60 > r6 ? 1 : (r60 == r6 ? 0 : -1));
        if (r5 != 0) goto L_0x005b;
    L_0x0e91:
        r53 = org.telegram.messenger.UserConfig.getClientUserId();
        r150 = 0;
        r74 = 0;
        r5 = 1;
        r26 = r165[r5];
        r26 = (java.util.ArrayList) r26;
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        if (r5 == 0) goto L_0x0f64;
    L_0x0ea4:
        r5 = r26.size();
        r6 = 1;
        if (r5 != r6) goto L_0x0f64;
    L_0x0eab:
        r5 = 0;
        r0 = r26;
        r121 = r0.get(r5);
        r121 = (org.telegram.messenger.MessageObject) r121;
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        if (r5 == 0) goto L_0x0f64;
    L_0x0eba:
        r5 = r121.isOut();
        if (r5 == 0) goto L_0x0f64;
    L_0x0ec0:
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.action;
        if (r5 == 0) goto L_0x0f64;
    L_0x0ec8:
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.action;
        r5 = r5 instanceof org.telegram.tgnet.TLRPC$TL_messageEncryptedAction;
        if (r5 == 0) goto L_0x0f64;
    L_0x0ed2:
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.action;
        r5 = r5.encryptedAction;
        r5 = r5 instanceof org.telegram.tgnet.TLRPC$TL_decryptedMessageActionSetMessageTTL;
        if (r5 == 0) goto L_0x0f64;
    L_0x0ede:
        r5 = r163.getParentActivity();
        if (r5 == 0) goto L_0x0f64;
    L_0x0ee4:
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        r5 = r5.layer;
        r5 = org.telegram.messenger.AndroidUtilities.getPeerLayerVersion(r5);
        r6 = 17;
        if (r5 >= r6) goto L_0x0f64;
    L_0x0ef2:
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        r5 = r5.ttl;
        if (r5 <= 0) goto L_0x0f64;
    L_0x0efa:
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        r5 = r5.ttl;
        r6 = 60;
        if (r5 > r6) goto L_0x0f64;
    L_0x0f04:
        r34 = new org.telegram.ui.ActionBar.AlertDialog$Builder;
        r5 = r163.getParentActivity();
        r0 = r34;
        r0.<init>(r5);
        r5 = org.telegram.messenger.ApplicationLoader.getConfig();
        r5 = r5.getAppName();
        r0 = r34;
        r0.setTitle(r5);
        r5 = "OK";
        r6 = 2131559582; // 0x7f0d049e float:1.8744512E38 double:1.0531303615E-314;
        r5 = org.telegram.messenger.LocaleController.getString(r5, r6);
        r6 = 0;
        r0 = r34;
        r0.setPositiveButton(r5, r6);
        r5 = "CompatibilityChat";
        r6 = 2131558818; // 0x7f0d01a2 float:1.8742963E38 double:1.053129984E-314;
        r7 = 3;
        r7 = new java.lang.Object[r7];
        r8 = 0;
        r0 = r163;
        r11 = r0.currentUser;
        r11 = r11.first_name;
        r7[r8] = r11;
        r8 = 1;
        r0 = r163;
        r11 = r0.currentUser;
        r11 = r11.first_name;
        r7[r8] = r11;
        r8 = 2;
        r11 = org.telegram.messenger.ApplicationLoader.getConfig();
        r11 = r11.getAppName();
        r7[r8] = r11;
        r5 = org.telegram.messenger.LocaleController.formatString(r5, r6, r7);
        r0 = r34;
        r0.setMessage(r5);
        r5 = r34.create();
        r0 = r163;
        r0.showDialog(r5);
    L_0x0f64:
        r0 = r163;
        r5 = r0.currentChat;
        if (r5 != 0) goto L_0x0f74;
    L_0x0f6a:
        r0 = r163;
        r6 = r0.inlineReturn;
        r12 = 0;
        r5 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r5 == 0) goto L_0x10cc;
    L_0x0f74:
        r4 = 0;
    L_0x0f75:
        r5 = r26.size();
        if (r4 >= r5) goto L_0x10cc;
    L_0x0f7b:
        r0 = r26;
        r106 = r0.get(r4);
        r106 = (org.telegram.messenger.MessageObject) r106;
        r0 = r163;
        r5 = r0.currentChat;
        if (r5 == 0) goto L_0x1069;
    L_0x0f89:
        r0 = r106;
        r5 = r0.messageOwner;
        r5 = r5.action;
        r5 = r5 instanceof org.telegram.tgnet.TLRPC$TL_messageActionChatDeleteUser;
        if (r5 == 0) goto L_0x0f9f;
    L_0x0f93:
        r0 = r106;
        r5 = r0.messageOwner;
        r5 = r5.action;
        r5 = r5.user_id;
        r0 = r53;
        if (r5 == r0) goto L_0x0fbb;
    L_0x0f9f:
        r0 = r106;
        r5 = r0.messageOwner;
        r5 = r5.action;
        r5 = r5 instanceof org.telegram.tgnet.TLRPC$TL_messageActionChatAddUser;
        if (r5 == 0) goto L_0x0feb;
    L_0x0fa9:
        r0 = r106;
        r5 = r0.messageOwner;
        r5 = r5.action;
        r5 = r5.users;
        r6 = java.lang.Integer.valueOf(r53);
        r5 = r5.contains(r6);
        if (r5 == 0) goto L_0x0feb;
    L_0x0fbb:
        r5 = org.telegram.messenger.MessagesController.getInstance();
        r0 = r163;
        r6 = r0.currentChat;
        r6 = r6.id;
        r6 = java.lang.Integer.valueOf(r6);
        r114 = r5.getChat(r6);
        if (r114 == 0) goto L_0x0fe8;
    L_0x0fcf:
        r0 = r114;
        r1 = r163;
        r1.currentChat = r0;
        r163.checkActionBarMenu();
        r163.updateBottomOverlay();
        r0 = r163;
        r5 = r0.avatarContainer;
        if (r5 == 0) goto L_0x0fe8;
    L_0x0fe1:
        r0 = r163;
        r5 = r0.avatarContainer;
        r5.updateSubtitle();
    L_0x0fe8:
        r4 = r4 + 1;
        goto L_0x0f75;
    L_0x0feb:
        r0 = r106;
        r5 = r0.messageOwner;
        r5 = r5.reply_to_msg_id;
        if (r5 == 0) goto L_0x0fe8;
    L_0x0ff3:
        r0 = r106;
        r5 = r0.replyMessageObject;
        if (r5 != 0) goto L_0x0fe8;
    L_0x0ff9:
        r0 = r163;
        r5 = r0.messagesDict;
        r6 = 0;
        r5 = r5[r6];
        r0 = r106;
        r6 = r0.messageOwner;
        r6 = r6.reply_to_msg_id;
        r6 = java.lang.Integer.valueOf(r6);
        r5 = r5.get(r6);
        r5 = (org.telegram.messenger.MessageObject) r5;
        r0 = r106;
        r0.replyMessageObject = r5;
        r0 = r106;
        r5 = r0.messageOwner;
        r5 = r5.action;
        r5 = r5 instanceof org.telegram.tgnet.TLRPC$TL_messageActionPinMessage;
        if (r5 == 0) goto L_0x1047;
    L_0x101e:
        r5 = 0;
        r6 = 0;
        r0 = r106;
        r0.generatePinMessageText(r5, r6);
    L_0x1025:
        r5 = r106.isMegagroup();
        if (r5 == 0) goto L_0x0fe8;
    L_0x102b:
        r0 = r106;
        r5 = r0.replyMessageObject;
        if (r5 == 0) goto L_0x0fe8;
    L_0x1031:
        r0 = r106;
        r5 = r0.replyMessageObject;
        r5 = r5.messageOwner;
        if (r5 == 0) goto L_0x0fe8;
    L_0x1039:
        r0 = r106;
        r5 = r0.replyMessageObject;
        r5 = r5.messageOwner;
        r6 = r5.flags;
        r7 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r6 = r6 | r7;
        r5.flags = r6;
        goto L_0x0fe8;
    L_0x1047:
        r0 = r106;
        r5 = r0.messageOwner;
        r5 = r5.action;
        r5 = r5 instanceof org.telegram.tgnet.TLRPC$TL_messageActionGameScore;
        if (r5 == 0) goto L_0x1058;
    L_0x1051:
        r5 = 0;
        r0 = r106;
        r0.generateGameMessageText(r5);
        goto L_0x1025;
    L_0x1058:
        r0 = r106;
        r5 = r0.messageOwner;
        r5 = r5.action;
        r5 = r5 instanceof org.telegram.tgnet.TLRPC$TL_messageActionPaymentSent;
        if (r5 == 0) goto L_0x1025;
    L_0x1062:
        r5 = 0;
        r0 = r106;
        r0.generatePaymentSentMessageText(r5);
        goto L_0x1025;
    L_0x1069:
        r0 = r163;
        r6 = r0.inlineReturn;
        r12 = 0;
        r5 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r5 == 0) goto L_0x0fe8;
    L_0x1073:
        r0 = r106;
        r5 = r0.messageOwner;
        r5 = r5.reply_markup;
        if (r5 == 0) goto L_0x0fe8;
    L_0x107b:
        r31 = 0;
    L_0x107d:
        r0 = r106;
        r5 = r0.messageOwner;
        r5 = r5.reply_markup;
        r5 = r5.rows;
        r5 = r5.size();
        r0 = r31;
        if (r0 >= r5) goto L_0x0fe8;
    L_0x108d:
        r0 = r106;
        r5 = r0.messageOwner;
        r5 = r5.reply_markup;
        r5 = r5.rows;
        r0 = r31;
        r140 = r5.get(r0);
        r140 = (org.telegram.tgnet.TLRPC$TL_keyboardButtonRow) r140;
        r37 = 0;
    L_0x109f:
        r0 = r140;
        r5 = r0.buttons;
        r5 = r5.size();
        r0 = r37;
        if (r0 >= r5) goto L_0x10c6;
    L_0x10ab:
        r0 = r140;
        r5 = r0.buttons;
        r0 = r37;
        r36 = r5.get(r0);
        r36 = (org.telegram.tgnet.TLRPC$KeyboardButton) r36;
        r0 = r36;
        r5 = r0 instanceof org.telegram.tgnet.TLRPC$TL_keyboardButtonSwitchInline;
        if (r5 == 0) goto L_0x10c9;
    L_0x10bd:
        r36 = (org.telegram.tgnet.TLRPC$TL_keyboardButtonSwitchInline) r36;
        r0 = r163;
        r1 = r36;
        r0.processSwitchButton(r1);
    L_0x10c6:
        r31 = r31 + 1;
        goto L_0x107d;
    L_0x10c9:
        r37 = r37 + 1;
        goto L_0x109f;
    L_0x10cc:
        r138 = 0;
        r0 = r163;
        r5 = r0.forwardEndReached;
        r6 = 0;
        r5 = r5[r6];
        if (r5 != 0) goto L_0x1388;
    L_0x10d7:
        r10 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r9 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        if (r5 == 0) goto L_0x10e4;
    L_0x10e1:
        r9 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
    L_0x10e4:
        r50 = 0;
        r4 = 0;
    L_0x10e7:
        r5 = r26.size();
        if (r4 >= r5) goto L_0x12a5;
    L_0x10ed:
        r0 = r26;
        r121 = r0.get(r4);
        r121 = (org.telegram.messenger.MessageObject) r121;
        r0 = r163;
        r5 = r0.currentUser;
        if (r5 == 0) goto L_0x1116;
    L_0x10fb:
        r0 = r163;
        r5 = r0.currentUser;
        r5 = r5.bot;
        if (r5 == 0) goto L_0x1109;
    L_0x1103:
        r5 = r121.isOut();
        if (r5 != 0) goto L_0x1113;
    L_0x1109:
        r0 = r163;
        r5 = r0.currentUser;
        r5 = r5.id;
        r0 = r53;
        if (r5 != r0) goto L_0x1116;
    L_0x1113:
        r121.setIsRead();
    L_0x1116:
        r0 = r163;
        r5 = r0.avatarContainer;
        if (r5 == 0) goto L_0x1153;
    L_0x111c:
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        if (r5 == 0) goto L_0x1153;
    L_0x1122:
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.action;
        if (r5 == 0) goto L_0x1153;
    L_0x112a:
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.action;
        r5 = r5 instanceof org.telegram.tgnet.TLRPC$TL_messageEncryptedAction;
        if (r5 == 0) goto L_0x1153;
    L_0x1134:
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.action;
        r5 = r5.encryptedAction;
        r5 = r5 instanceof org.telegram.tgnet.TLRPC$TL_decryptedMessageActionSetMessageTTL;
        if (r5 == 0) goto L_0x1153;
    L_0x1140:
        r0 = r163;
        r6 = r0.avatarContainer;
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.action;
        r5 = r5.encryptedAction;
        r5 = (org.telegram.tgnet.TLRPC$TL_decryptedMessageActionSetMessageTTL) r5;
        r5 = r5.ttl_seconds;
        r6.setTime(r5);
    L_0x1153:
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.action;
        r5 = r5 instanceof org.telegram.tgnet.TLRPC$TL_messageActionChatMigrateTo;
        if (r5 == 0) goto L_0x11b7;
    L_0x115d:
        r35 = new android.os.Bundle;
        r35.<init>();
        r5 = "chat_id";
        r0 = r121;
        r6 = r0.messageOwner;
        r6 = r6.action;
        r6 = r6.channel_id;
        r0 = r35;
        r0.putInt(r5, r6);
        r0 = r163;
        r5 = r0.parentLayout;
        r5 = r5.fragmentsStack;
        r5 = r5.size();
        if (r5 <= 0) goto L_0x11b4;
    L_0x117e:
        r0 = r163;
        r5 = r0.parentLayout;
        r5 = r5.fragmentsStack;
        r0 = r163;
        r6 = r0.parentLayout;
        r6 = r6.fragmentsStack;
        r6 = r6.size();
        r6 = r6 + -1;
        r5 = r5.get(r6);
        r5 = (org.telegram.ui.ActionBar.BaseFragment) r5;
        r87 = r5;
    L_0x1198:
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.action;
        r0 = r5.channel_id;
        r43 = r0;
        r5 = new org.telegram.ui.ChatActivity$86;
        r0 = r163;
        r1 = r87;
        r2 = r35;
        r3 = r43;
        r5.<init>(r1, r2, r3);
        org.telegram.messenger.AndroidUtilities.runOnUIThread(r5);
        goto L_0x005b;
    L_0x11b4:
        r87 = 0;
        goto L_0x1198;
    L_0x11b7:
        r0 = r163;
        r5 = r0.currentChat;
        if (r5 == 0) goto L_0x11db;
    L_0x11bd:
        r0 = r163;
        r5 = r0.currentChat;
        r5 = r5.megagroup;
        if (r5 == 0) goto L_0x11db;
    L_0x11c5:
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.action;
        r5 = r5 instanceof org.telegram.tgnet.TLRPC$TL_messageActionChatAddUser;
        if (r5 != 0) goto L_0x11d9;
    L_0x11cf:
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.action;
        r5 = r5 instanceof org.telegram.tgnet.TLRPC$TL_messageActionChatDeleteUser;
        if (r5 == 0) goto L_0x11db;
    L_0x11d9:
        r138 = 1;
    L_0x11db:
        r5 = r121.isOut();
        if (r5 == 0) goto L_0x11ef;
    L_0x11e1:
        r5 = r121.isSending();
        if (r5 == 0) goto L_0x11ef;
    L_0x11e7:
        r5 = 0;
        r0 = r163;
        r0.scrollToLastMessage(r5);
        goto L_0x005b;
    L_0x11ef:
        r0 = r121;
        r5 = r0.type;
        if (r5 < 0) goto L_0x120a;
    L_0x11f5:
        r0 = r163;
        r5 = r0.messagesDict;
        r6 = 0;
        r5 = r5[r6];
        r6 = r121.getId();
        r6 = java.lang.Integer.valueOf(r6);
        r5 = r5.containsKey(r6);
        if (r5 == 0) goto L_0x120e;
    L_0x120a:
        r4 = r4 + 1;
        goto L_0x10e7;
    L_0x120e:
        r121.checkLayout();
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.date;
        r10 = java.lang.Math.max(r10, r5);
        r5 = r121.getId();
        if (r5 <= 0) goto L_0x1286;
    L_0x1221:
        r5 = r121.getId();
        r9 = java.lang.Math.max(r5, r9);
        r0 = r163;
        r5 = r0.last_message_id;
        r6 = r121.getId();
        r5 = java.lang.Math.max(r5, r6);
        r0 = r163;
        r0.last_message_id = r5;
    L_0x1239:
        r5 = r121.isOut();
        if (r5 != 0) goto L_0x1251;
    L_0x123f:
        r5 = r121.isUnread();
        if (r5 == 0) goto L_0x1251;
    L_0x1245:
        r0 = r163;
        r5 = r0.unread_to_load;
        r5 = r5 + 1;
        r0 = r163;
        r0.unread_to_load = r5;
        r50 = 1;
    L_0x1251:
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.mentioned;
        if (r5 == 0) goto L_0x1269;
    L_0x1259:
        r5 = r121.isContentUnread();
        if (r5 == 0) goto L_0x1269;
    L_0x125f:
        r0 = r163;
        r5 = r0.newMentionsCount;
        r5 = r5 + 1;
        r0 = r163;
        r0.newMentionsCount = r5;
    L_0x1269:
        r0 = r163;
        r5 = r0.newUnreadMessageCount;
        r5 = r5 + 1;
        r0 = r163;
        r0.newUnreadMessageCount = r5;
        r0 = r121;
        r5 = r0.type;
        r6 = 10;
        if (r5 == r6) goto L_0x1283;
    L_0x127b:
        r0 = r121;
        r5 = r0.type;
        r6 = 11;
        if (r5 != r6) goto L_0x120a;
    L_0x1283:
        r150 = 1;
        goto L_0x120a;
    L_0x1286:
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        if (r5 == 0) goto L_0x1239;
    L_0x128c:
        r5 = r121.getId();
        r9 = java.lang.Math.min(r5, r9);
        r0 = r163;
        r5 = r0.last_message_id;
        r6 = r121.getId();
        r5 = java.lang.Math.min(r5, r6);
        r0 = r163;
        r0.last_message_id = r5;
        goto L_0x1239;
    L_0x12a5:
        r0 = r163;
        r5 = r0.newUnreadMessageCount;
        if (r5 == 0) goto L_0x12d5;
    L_0x12ab:
        r0 = r163;
        r5 = r0.pagedownButtonCounter;
        if (r5 == 0) goto L_0x12d5;
    L_0x12b1:
        r0 = r163;
        r5 = r0.pagedownButtonCounter;
        r6 = 0;
        r5.setVisibility(r6);
        r0 = r163;
        r5 = r0.pagedownButtonCounter;
        r6 = "%d";
        r7 = 1;
        r7 = new java.lang.Object[r7];
        r8 = 0;
        r0 = r163;
        r11 = r0.newUnreadMessageCount;
        r11 = java.lang.Integer.valueOf(r11);
        r7[r8] = r11;
        r6 = java.lang.String.format(r6, r7);
        r5.setText(r6);
    L_0x12d5:
        r0 = r163;
        r5 = r0.newMentionsCount;
        if (r5 == 0) goto L_0x130c;
    L_0x12db:
        r0 = r163;
        r5 = r0.mentiondownButtonCounter;
        if (r5 == 0) goto L_0x130c;
    L_0x12e1:
        r0 = r163;
        r5 = r0.mentiondownButtonCounter;
        r6 = 0;
        r5.setVisibility(r6);
        r0 = r163;
        r5 = r0.mentiondownButtonCounter;
        r6 = "%d";
        r7 = 1;
        r7 = new java.lang.Object[r7];
        r8 = 0;
        r0 = r163;
        r11 = r0.newMentionsCount;
        r11 = java.lang.Integer.valueOf(r11);
        r7[r8] = r11;
        r6 = java.lang.String.format(r6, r7);
        r5.setText(r6);
        r5 = 1;
        r6 = 1;
        r0 = r163;
        r0.showMentiondownButton(r5, r6);
    L_0x130c:
        if (r50 == 0) goto L_0x1321;
    L_0x130e:
        r0 = r163;
        r5 = r0.paused;
        if (r5 == 0) goto L_0x1361;
    L_0x1314:
        r5 = 1;
        r0 = r163;
        r0.readWhenResume = r5;
        r0 = r163;
        r0.readWithDate = r10;
        r0 = r163;
        r0.readWithMid = r9;
    L_0x1321:
        r163.updateVisibleRows();
    L_0x1324:
        r0 = r163;
        r5 = r0.messages;
        r5 = r5.isEmpty();
        if (r5 != 0) goto L_0x1346;
    L_0x132e:
        r0 = r163;
        r5 = r0.botUser;
        if (r5 == 0) goto L_0x1346;
    L_0x1334:
        r0 = r163;
        r5 = r0.botUser;
        r5 = r5.length();
        if (r5 != 0) goto L_0x1346;
    L_0x133e:
        r5 = 0;
        r0 = r163;
        r0.botUser = r5;
        r163.updateBottomOverlay();
    L_0x1346:
        if (r150 == 0) goto L_0x134e;
    L_0x1348:
        r163.updateTitle();
        r163.checkAndUpdateAvatar();
    L_0x134e:
        if (r138 == 0) goto L_0x005b;
    L_0x1350:
        r5 = org.telegram.messenger.MessagesController.getInstance();
        r0 = r163;
        r6 = r0.currentChat;
        r6 = r6.id;
        r7 = 0;
        r8 = 1;
        r5.loadFullChat(r6, r7, r8);
        goto L_0x005b;
    L_0x1361:
        r0 = r163;
        r5 = r0.messages;
        r5 = r5.size();
        if (r5 <= 0) goto L_0x1321;
    L_0x136b:
        r5 = org.telegram.messenger.MessagesController.getInstance();
        r0 = r163;
        r6 = r0.dialog_id;
        r0 = r163;
        r8 = r0.messages;
        r11 = 0;
        r8 = r8.get(r11);
        r8 = (org.telegram.messenger.MessageObject) r8;
        r8 = r8.getId();
        r11 = 1;
        r12 = 0;
        r5.markDialogAsRead(r6, r8, r9, r10, r11, r12);
        goto L_0x1321;
    L_0x1388:
        r116 = 0;
        r99 = 0;
        r149 = 1;
        r161 = 0;
        r5 = org.telegram.messenger.BuildVars.DEBUG_VERSION;
        if (r5 == 0) goto L_0x13be;
    L_0x1394:
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "received new messages ";
        r5 = r5.append(r6);
        r6 = r26.size();
        r5 = r5.append(r6);
        r6 = " in dialog ";
        r5 = r5.append(r6);
        r0 = r163;
        r6 = r0.dialog_id;
        r5 = r5.append(r6);
        r5 = r5.toString();
        org.telegram.messenger.FileLog.d(r5);
    L_0x13be:
        r4 = 0;
    L_0x13bf:
        r5 = r26.size();
        if (r4 >= r5) goto L_0x1938;
    L_0x13c5:
        r130 = -1;
        r0 = r26;
        r121 = r0.get(r4);
        r121 = (org.telegram.messenger.MessageObject) r121;
        r0 = r163;
        r5 = r0.currentUser;
        if (r5 == 0) goto L_0x13f0;
    L_0x13d5:
        r0 = r163;
        r5 = r0.currentUser;
        r5 = r5.bot;
        if (r5 == 0) goto L_0x13e3;
    L_0x13dd:
        r5 = r121.isOut();
        if (r5 != 0) goto L_0x13ed;
    L_0x13e3:
        r0 = r163;
        r5 = r0.currentUser;
        r5 = r5.id;
        r0 = r53;
        if (r5 != r0) goto L_0x13f0;
    L_0x13ed:
        r121.setIsRead();
    L_0x13f0:
        r0 = r163;
        r5 = r0.avatarContainer;
        if (r5 == 0) goto L_0x142d;
    L_0x13f6:
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        if (r5 == 0) goto L_0x142d;
    L_0x13fc:
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.action;
        if (r5 == 0) goto L_0x142d;
    L_0x1404:
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.action;
        r5 = r5 instanceof org.telegram.tgnet.TLRPC$TL_messageEncryptedAction;
        if (r5 == 0) goto L_0x142d;
    L_0x140e:
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.action;
        r5 = r5.encryptedAction;
        r5 = r5 instanceof org.telegram.tgnet.TLRPC$TL_decryptedMessageActionSetMessageTTL;
        if (r5 == 0) goto L_0x142d;
    L_0x141a:
        r0 = r163;
        r6 = r0.avatarContainer;
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.action;
        r5 = r5.encryptedAction;
        r5 = (org.telegram.tgnet.TLRPC$TL_decryptedMessageActionSetMessageTTL) r5;
        r5 = r5.ttl_seconds;
        r6.setTime(r5);
    L_0x142d:
        r0 = r121;
        r5 = r0.type;
        if (r5 < 0) goto L_0x1448;
    L_0x1433:
        r0 = r163;
        r5 = r0.messagesDict;
        r6 = 0;
        r5 = r5[r6];
        r6 = r121.getId();
        r6 = java.lang.Integer.valueOf(r6);
        r5 = r5.containsKey(r6);
        if (r5 == 0) goto L_0x144c;
    L_0x1448:
        r4 = r4 + 1;
        goto L_0x13bf;
    L_0x144c:
        if (r4 != 0) goto L_0x1466;
    L_0x144e:
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.id;
        if (r5 >= 0) goto L_0x1466;
    L_0x1456:
        r0 = r121;
        r5 = r0.type;
        r6 = 5;
        if (r5 != r6) goto L_0x1466;
    L_0x145d:
        r0 = r163;
        r5 = r0.animatingMessageObjects;
        r0 = r121;
        r5.add(r0);
    L_0x1466:
        r5 = r121.hasValidGroupId();
        if (r5 == 0) goto L_0x15dd;
    L_0x146c:
        r0 = r163;
        r5 = r0.groupedMessagesMap;
        r6 = r121.getGroupId();
        r6 = java.lang.Long.valueOf(r6);
        r72 = r5.get(r6);
        r72 = (org.telegram.messenger.MessageObject.GroupedMessages) r72;
        if (r72 != 0) goto L_0x149e;
    L_0x1480:
        r72 = new org.telegram.messenger.MessageObject$GroupedMessages;
        r72.<init>();
        r6 = r121.getGroupId();
        r0 = r72;
        r0.groupId = r6;
        r0 = r163;
        r5 = r0.groupedMessagesMap;
        r0 = r72;
        r6 = r0.groupId;
        r6 = java.lang.Long.valueOf(r6);
        r0 = r72;
        r5.put(r6, r0);
    L_0x149e:
        if (r116 != 0) goto L_0x14a5;
    L_0x14a0:
        r116 = new java.util.HashMap;
        r116.<init>();
    L_0x14a5:
        r0 = r72;
        r6 = r0.groupId;
        r5 = java.lang.Long.valueOf(r6);
        r0 = r116;
        r1 = r72;
        r0.put(r5, r1);
        r0 = r72;
        r5 = r0.messages;
        r0 = r121;
        r5.add(r0);
    L_0x14bd:
        if (r72 == 0) goto L_0x14ee;
    L_0x14bf:
        r0 = r72;
        r5 = r0.messages;
        r143 = r5.size();
        r5 = 1;
        r0 = r143;
        if (r0 <= r5) goto L_0x15e1;
    L_0x14cc:
        r0 = r72;
        r5 = r0.messages;
        r0 = r72;
        r6 = r0.messages;
        r6 = r6.size();
        r6 = r6 + -2;
        r5 = r5.get(r6);
        r5 = (org.telegram.messenger.MessageObject) r5;
        r106 = r5;
    L_0x14e2:
        if (r106 == 0) goto L_0x14ee;
    L_0x14e4:
        r0 = r163;
        r5 = r0.messages;
        r0 = r106;
        r130 = r5.indexOf(r0);
    L_0x14ee:
        r5 = -1;
        r0 = r130;
        if (r0 != r5) goto L_0x1507;
    L_0x14f3:
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.id;
        if (r5 < 0) goto L_0x1505;
    L_0x14fb:
        r0 = r163;
        r5 = r0.messages;
        r5 = r5.isEmpty();
        if (r5 == 0) goto L_0x15e5;
    L_0x1505:
        r130 = 0;
    L_0x1507:
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        if (r5 == 0) goto L_0x155b;
    L_0x150d:
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.media;
        r5 = r5 instanceof org.telegram.tgnet.TLRPC$TL_messageMediaWebPage;
        if (r5 == 0) goto L_0x155b;
    L_0x1517:
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.media;
        r5 = r5.webpage;
        r5 = r5 instanceof org.telegram.tgnet.TLRPC$TL_webPageUrlPending;
        if (r5 == 0) goto L_0x155b;
    L_0x1523:
        if (r161 != 0) goto L_0x152a;
    L_0x1525:
        r161 = new java.util.HashMap;
        r161.<init>();
    L_0x152a:
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.media;
        r5 = r5.webpage;
        r5 = r5.url;
        r0 = r161;
        r29 = r0.get(r5);
        r29 = (java.util.ArrayList) r29;
        if (r29 != 0) goto L_0x1554;
    L_0x153e:
        r29 = new java.util.ArrayList;
        r29.<init>();
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.media;
        r5 = r5.webpage;
        r5 = r5.url;
        r0 = r161;
        r1 = r29;
        r0.put(r5, r1);
    L_0x1554:
        r0 = r29;
        r1 = r121;
        r0.add(r1);
    L_0x155b:
        r121.checkLayout();
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.action;
        r5 = r5 instanceof org.telegram.tgnet.TLRPC$TL_messageActionChatMigrateTo;
        if (r5 == 0) goto L_0x16aa;
    L_0x1568:
        r35 = new android.os.Bundle;
        r35.<init>();
        r5 = "chat_id";
        r0 = r121;
        r6 = r0.messageOwner;
        r6 = r6.action;
        r6 = r6.channel_id;
        r0 = r35;
        r0.putInt(r5, r6);
        r0 = r163;
        r5 = r0.parentLayout;
        r5 = r5.fragmentsStack;
        r5 = r5.size();
        if (r5 <= 0) goto L_0x16a6;
    L_0x1589:
        r0 = r163;
        r5 = r0.parentLayout;
        r5 = r5.fragmentsStack;
        r0 = r163;
        r6 = r0.parentLayout;
        r6 = r6.fragmentsStack;
        r6 = r6.size();
        r6 = r6 + -1;
        r5 = r5.get(r6);
        r5 = (org.telegram.ui.ActionBar.BaseFragment) r5;
        r87 = r5;
    L_0x15a3:
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.action;
        r0 = r5.channel_id;
        r43 = r0;
        r5 = new org.telegram.ui.ChatActivity$87;
        r0 = r163;
        r1 = r87;
        r2 = r35;
        r3 = r43;
        r5.<init>(r1, r2, r3);
        org.telegram.messenger.AndroidUtilities.runOnUIThread(r5);
        if (r116 == 0) goto L_0x005b;
    L_0x15bf:
        r5 = r116.entrySet();
        r6 = r5.iterator();
    L_0x15c7:
        r5 = r6.hasNext();
        if (r5 == 0) goto L_0x005b;
    L_0x15cd:
        r64 = r6.next();
        r64 = (java.util.Map.Entry) r64;
        r5 = r64.getValue();
        r5 = (org.telegram.messenger.MessageObject.GroupedMessages) r5;
        r5.calculate();
        goto L_0x15c7;
    L_0x15dd:
        r72 = 0;
        goto L_0x14bd;
    L_0x15e1:
        r106 = 0;
        goto L_0x14e2;
    L_0x15e5:
        r0 = r163;
        r5 = r0.messages;
        r143 = r5.size();
        r31 = 0;
    L_0x15ef:
        r0 = r31;
        r1 = r143;
        if (r0 >= r1) goto L_0x1669;
    L_0x15f5:
        r0 = r163;
        r5 = r0.messages;
        r0 = r31;
        r89 = r5.get(r0);
        r89 = (org.telegram.messenger.MessageObject) r89;
        r0 = r89;
        r5 = r0.type;
        if (r5 < 0) goto L_0x16a2;
    L_0x1607:
        r0 = r89;
        r5 = r0.messageOwner;
        r5 = r5.date;
        if (r5 <= 0) goto L_0x16a2;
    L_0x160f:
        r0 = r89;
        r5 = r0.messageOwner;
        r5 = r5.id;
        if (r5 <= 0) goto L_0x162d;
    L_0x1617:
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.id;
        if (r5 <= 0) goto L_0x162d;
    L_0x161f:
        r0 = r89;
        r5 = r0.messageOwner;
        r5 = r5.id;
        r0 = r121;
        r6 = r0.messageOwner;
        r6 = r6.id;
        if (r5 < r6) goto L_0x163b;
    L_0x162d:
        r0 = r89;
        r5 = r0.messageOwner;
        r5 = r5.date;
        r0 = r121;
        r6 = r0.messageOwner;
        r6 = r6.date;
        if (r5 >= r6) goto L_0x16a2;
    L_0x163b:
        r6 = r89.getGroupId();
        r12 = 0;
        r5 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r5 == 0) goto L_0x1684;
    L_0x1645:
        r0 = r163;
        r5 = r0.groupedMessagesMap;
        r6 = r89.getGroupId();
        r6 = java.lang.Long.valueOf(r6);
        r88 = r5.get(r6);
        r88 = (org.telegram.messenger.MessageObject.GroupedMessages) r88;
        if (r88 == 0) goto L_0x1665;
    L_0x1659:
        r0 = r88;
        r5 = r0.messages;
        r5 = r5.size();
        if (r5 != 0) goto L_0x1665;
    L_0x1663:
        r88 = 0;
    L_0x1665:
        if (r88 != 0) goto L_0x1687;
    L_0x1667:
        r130 = r31;
    L_0x1669:
        r5 = -1;
        r0 = r130;
        if (r0 == r5) goto L_0x167a;
    L_0x166e:
        r0 = r163;
        r5 = r0.messages;
        r5 = r5.size();
        r0 = r130;
        if (r0 <= r5) goto L_0x1507;
    L_0x167a:
        r0 = r163;
        r5 = r0.messages;
        r130 = r5.size();
        goto L_0x1507;
    L_0x1684:
        r88 = 0;
        goto L_0x1665;
    L_0x1687:
        r0 = r163;
        r5 = r0.messages;
        r0 = r88;
        r6 = r0.messages;
        r0 = r88;
        r7 = r0.messages;
        r7 = r7.size();
        r7 = r7 + -1;
        r6 = r6.get(r7);
        r130 = r5.indexOf(r6);
        goto L_0x1669;
    L_0x16a2:
        r31 = r31 + 1;
        goto L_0x15ef;
    L_0x16a6:
        r87 = 0;
        goto L_0x15a3;
    L_0x16aa:
        r0 = r163;
        r5 = r0.currentChat;
        if (r5 == 0) goto L_0x16ce;
    L_0x16b0:
        r0 = r163;
        r5 = r0.currentChat;
        r5 = r5.megagroup;
        if (r5 == 0) goto L_0x16ce;
    L_0x16b8:
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.action;
        r5 = r5 instanceof org.telegram.tgnet.TLRPC$TL_messageActionChatAddUser;
        if (r5 != 0) goto L_0x16cc;
    L_0x16c2:
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.action;
        r5 = r5 instanceof org.telegram.tgnet.TLRPC$TL_messageActionChatDeleteUser;
        if (r5 == 0) goto L_0x16ce;
    L_0x16cc:
        r138 = 1;
    L_0x16ce:
        r0 = r163;
        r5 = r0.minDate;
        r6 = 0;
        r5 = r5[r6];
        if (r5 == 0) goto L_0x16e6;
    L_0x16d7:
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.date;
        r0 = r163;
        r6 = r0.minDate;
        r7 = 0;
        r6 = r6[r7];
        if (r5 >= r6) goto L_0x16f3;
    L_0x16e6:
        r0 = r163;
        r5 = r0.minDate;
        r6 = 0;
        r0 = r121;
        r7 = r0.messageOwner;
        r7 = r7.date;
        r5[r6] = r7;
    L_0x16f3:
        r5 = r121.isOut();
        if (r5 == 0) goto L_0x16fe;
    L_0x16f9:
        r163.removeUnreadPlane();
        r74 = 1;
    L_0x16fe:
        r5 = r121.getId();
        if (r5 <= 0) goto L_0x1904;
    L_0x1704:
        r0 = r163;
        r5 = r0.maxMessageId;
        r6 = 0;
        r7 = r121.getId();
        r0 = r163;
        r8 = r0.maxMessageId;
        r11 = 0;
        r8 = r8[r11];
        r7 = java.lang.Math.min(r7, r8);
        r5[r6] = r7;
        r0 = r163;
        r5 = r0.minMessageId;
        r6 = 0;
        r7 = r121.getId();
        r0 = r163;
        r8 = r0.minMessageId;
        r11 = 0;
        r8 = r8[r11];
        r7 = java.lang.Math.max(r7, r8);
        r5[r6] = r7;
    L_0x1730:
        r0 = r163;
        r5 = r0.maxDate;
        r6 = 0;
        r0 = r163;
        r7 = r0.maxDate;
        r8 = 0;
        r7 = r7[r8];
        r0 = r121;
        r8 = r0.messageOwner;
        r8 = r8.date;
        r7 = java.lang.Math.max(r7, r8);
        r5[r6] = r7;
        r0 = r163;
        r5 = r0.messagesDict;
        r6 = 0;
        r5 = r5[r6];
        r6 = r121.getId();
        r6 = java.lang.Integer.valueOf(r6);
        r0 = r121;
        r5.put(r6, r0);
        r0 = r163;
        r5 = r0.messagesByDays;
        r0 = r121;
        r6 = r0.dateKey;
        r58 = r5.get(r6);
        r58 = (java.util.ArrayList) r58;
        if (r58 != 0) goto L_0x17d6;
    L_0x176c:
        r58 = new java.util.ArrayList;
        r58.<init>();
        r0 = r163;
        r5 = r0.messagesByDays;
        r0 = r121;
        r6 = r0.dateKey;
        r0 = r58;
        r5.put(r6, r0);
        r55 = new org.telegram.tgnet.TLRPC$TL_message;
        r55.<init>();
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.date;
        r6 = (long) r5;
        r5 = org.telegram.messenger.LocaleController.formatDateChat(r6);
        r0 = r55;
        r0.message = r5;
        r5 = 0;
        r0 = r55;
        r0.id = r5;
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.date;
        r0 = r55;
        r0.date = r5;
        r56 = new org.telegram.messenger.MessageObject;
        r5 = 0;
        r6 = 0;
        r0 = r56;
        r1 = r55;
        r0.<init>(r1, r5, r6);
        r5 = 10;
        r0 = r56;
        r0.type = r5;
        r5 = 1;
        r0 = r56;
        r0.contentType = r5;
        r5 = 1;
        r0 = r56;
        r0.isDateObject = r5;
        r0 = r163;
        r5 = r0.messages;
        r0 = r130;
        r1 = r56;
        r5.add(r0, r1);
        r0 = r163;
        r5 = r0.chatAdapter;
        if (r5 == 0) goto L_0x17d6;
    L_0x17cd:
        r0 = r163;
        r5 = r0.chatAdapter;
        r0 = r130;
        r5.notifyItemInserted(r0);
    L_0x17d6:
        r5 = r121.isOut();
        if (r5 != 0) goto L_0x1889;
    L_0x17dc:
        r0 = r163;
        r5 = r0.paused;
        if (r5 == 0) goto L_0x1866;
    L_0x17e2:
        if (r130 != 0) goto L_0x1866;
    L_0x17e4:
        r0 = r163;
        r5 = r0.scrollToTopUnReadOnResume;
        if (r5 != 0) goto L_0x1802;
    L_0x17ea:
        r0 = r163;
        r5 = r0.unreadMessageObject;
        if (r5 == 0) goto L_0x1802;
    L_0x17f0:
        r0 = r163;
        r5 = r0.unreadMessageObject;
        r0 = r163;
        r0.removeMessageObject(r5);
        if (r130 <= 0) goto L_0x17fd;
    L_0x17fb:
        r130 = r130 + -1;
    L_0x17fd:
        r5 = 0;
        r0 = r163;
        r0.unreadMessageObject = r5;
    L_0x1802:
        r0 = r163;
        r5 = r0.unreadMessageObject;
        if (r5 != 0) goto L_0x1866;
    L_0x1808:
        r55 = new org.telegram.tgnet.TLRPC$TL_message;
        r55.<init>();
        r5 = "";
        r0 = r55;
        r0.message = r5;
        r5 = 0;
        r0 = r55;
        r0.id = r5;
        r56 = new org.telegram.messenger.MessageObject;
        r5 = 0;
        r6 = 0;
        r0 = r56;
        r1 = r55;
        r0.<init>(r1, r5, r6);
        r5 = 6;
        r0 = r56;
        r0.type = r5;
        r5 = 2;
        r0 = r56;
        r0.contentType = r5;
        r0 = r163;
        r5 = r0.messages;
        r6 = 0;
        r0 = r56;
        r5.add(r6, r0);
        r0 = r163;
        r5 = r0.chatAdapter;
        if (r5 == 0) goto L_0x1846;
    L_0x183e:
        r0 = r163;
        r5 = r0.chatAdapter;
        r6 = 0;
        r5.notifyItemInserted(r6);
    L_0x1846:
        r0 = r56;
        r1 = r163;
        r1.unreadMessageObject = r0;
        r0 = r163;
        r5 = r0.unreadMessageObject;
        r0 = r163;
        r0.scrollToMessage = r5;
        r5 = -10000; // 0xffffffffffffd8f0 float:NaN double:NaN;
        r0 = r163;
        r0.scrollToMessagePosition = r5;
        r149 = 0;
        r5 = 0;
        r0 = r163;
        r0.unread_to_load = r5;
        r5 = 1;
        r0 = r163;
        r0.scrollToTopUnReadOnResume = r5;
    L_0x1866:
        r0 = r163;
        r5 = r0.unreadMessageObject;
        if (r5 == 0) goto L_0x1878;
    L_0x186c:
        r0 = r163;
        r5 = r0.unread_to_load;
        r5 = r5 + 1;
        r0 = r163;
        r0.unread_to_load = r5;
        r149 = 1;
    L_0x1878:
        r5 = r121.isUnread();
        if (r5 == 0) goto L_0x1889;
    L_0x187e:
        r0 = r163;
        r5 = r0.paused;
        if (r5 != 0) goto L_0x1887;
    L_0x1884:
        r121.setIsRead();
    L_0x1887:
        r99 = 1;
    L_0x1889:
        r5 = 0;
        r0 = r58;
        r1 = r121;
        r0.add(r5, r1);
        r0 = r163;
        r5 = r0.messages;
        r5 = r5.size();
        r0 = r130;
        if (r0 <= r5) goto L_0x18a5;
    L_0x189d:
        r0 = r163;
        r5 = r0.messages;
        r130 = r5.size();
    L_0x18a5:
        r0 = r163;
        r5 = r0.messages;
        r0 = r130;
        r1 = r121;
        r5.add(r0, r1);
        r0 = r163;
        r5 = r0.chatAdapter;
        if (r5 == 0) goto L_0x18c8;
    L_0x18b6:
        r0 = r163;
        r5 = r0.chatAdapter;
        r0 = r130;
        r5.notifyItemChanged(r0);
        r0 = r163;
        r5 = r0.chatAdapter;
        r0 = r130;
        r5.notifyItemInserted(r0);
    L_0x18c8:
        r5 = r121.isOut();
        if (r5 != 0) goto L_0x18e6;
    L_0x18ce:
        r0 = r121;
        r5 = r0.messageOwner;
        r5 = r5.mentioned;
        if (r5 == 0) goto L_0x18e6;
    L_0x18d6:
        r5 = r121.isContentUnread();
        if (r5 == 0) goto L_0x18e6;
    L_0x18dc:
        r0 = r163;
        r5 = r0.newMentionsCount;
        r5 = r5 + 1;
        r0 = r163;
        r0.newMentionsCount = r5;
    L_0x18e6:
        r0 = r163;
        r5 = r0.newUnreadMessageCount;
        r5 = r5 + 1;
        r0 = r163;
        r0.newUnreadMessageCount = r5;
        r0 = r121;
        r5 = r0.type;
        r6 = 10;
        if (r5 == r6) goto L_0x1900;
    L_0x18f8:
        r0 = r121;
        r5 = r0.type;
        r6 = 11;
        if (r5 != r6) goto L_0x1448;
    L_0x1900:
        r150 = 1;
        goto L_0x1448;
    L_0x1904:
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        if (r5 == 0) goto L_0x1730;
    L_0x190a:
        r0 = r163;
        r5 = r0.maxMessageId;
        r6 = 0;
        r7 = r121.getId();
        r0 = r163;
        r8 = r0.maxMessageId;
        r11 = 0;
        r8 = r8[r11];
        r7 = java.lang.Math.max(r7, r8);
        r5[r6] = r7;
        r0 = r163;
        r5 = r0.minMessageId;
        r6 = 0;
        r7 = r121.getId();
        r0 = r163;
        r8 = r0.minMessageId;
        r11 = 0;
        r8 = r8[r11];
        r7 = java.lang.Math.min(r7, r8);
        r5[r6] = r7;
        goto L_0x1730;
    L_0x1938:
        if (r161 == 0) goto L_0x1947;
    L_0x193a:
        r5 = org.telegram.messenger.MessagesController.getInstance();
        r0 = r163;
        r6 = r0.dialog_id;
        r0 = r161;
        r5.reloadWebPages(r6, r0);
    L_0x1947:
        if (r116 == 0) goto L_0x19a8;
    L_0x1949:
        r5 = r116.entrySet();
        r6 = r5.iterator();
    L_0x1951:
        r5 = r6.hasNext();
        if (r5 == 0) goto L_0x19a8;
    L_0x1957:
        r64 = r6.next();
        r64 = (java.util.Map.Entry) r64;
        r72 = r64.getValue();
        r72 = (org.telegram.messenger.MessageObject.GroupedMessages) r72;
        r0 = r72;
        r5 = r0.posArray;
        r124 = r5.size();
        r5 = r64.getValue();
        r5 = (org.telegram.messenger.MessageObject.GroupedMessages) r5;
        r5.calculate();
        r0 = r72;
        r5 = r0.posArray;
        r115 = r5.size();
        r5 = r115 - r124;
        if (r5 <= 0) goto L_0x1951;
    L_0x1980:
        r0 = r163;
        r5 = r0.messages;
        r0 = r72;
        r7 = r0.messages;
        r0 = r72;
        r8 = r0.messages;
        r8 = r8.size();
        r8 = r8 + -1;
        r7 = r7.get(r8);
        r80 = r5.indexOf(r7);
        if (r80 < 0) goto L_0x1951;
    L_0x199c:
        r0 = r163;
        r5 = r0.chatAdapter;
        r0 = r80;
        r1 = r115;
        r5.notifyItemRangeChanged(r0, r1);
        goto L_0x1951;
    L_0x19a8:
        r0 = r163;
        r5 = r0.progressView;
        if (r5 == 0) goto L_0x19b6;
    L_0x19ae:
        r0 = r163;
        r5 = r0.progressView;
        r6 = 4;
        r5.setVisibility(r6);
    L_0x19b6:
        r0 = r163;
        r5 = r0.chatAdapter;
        if (r5 == 0) goto L_0x1a5a;
    L_0x19bc:
        if (r149 == 0) goto L_0x19c9;
    L_0x19be:
        r0 = r163;
        r5 = r0.chatAdapter;
        r0 = r163;
        r6 = r0.unreadMessageObject;
        r5.updateRowWithMessageObject(r6);
    L_0x19c9:
        r0 = r163;
        r5 = r0.chatListView;
        if (r5 == 0) goto L_0x1aa3;
    L_0x19cf:
        r0 = r163;
        r5 = r0.chatAdapter;
        if (r5 == 0) goto L_0x1aa3;
    L_0x19d5:
        r0 = r163;
        r5 = r0.chatLayoutManager;
        r90 = r5.findFirstVisibleItemPosition();
        r5 = -1;
        r0 = r90;
        if (r0 != r5) goto L_0x19e4;
    L_0x19e2:
        r90 = 0;
    L_0x19e4:
        if (r90 == 0) goto L_0x19e8;
    L_0x19e6:
        if (r74 == 0) goto L_0x1a6a;
    L_0x19e8:
        r5 = 0;
        r0 = r163;
        r0.newUnreadMessageCount = r5;
        r0 = r163;
        r5 = r0.firstLoading;
        if (r5 != 0) goto L_0x19fe;
    L_0x19f3:
        r0 = r163;
        r5 = r0.paused;
        if (r5 == 0) goto L_0x1a61;
    L_0x19f9:
        r5 = 1;
        r0 = r163;
        r0.scrollToTopOnResume = r5;
    L_0x19fe:
        r0 = r163;
        r5 = r0.newMentionsCount;
        if (r5 == 0) goto L_0x1a35;
    L_0x1a04:
        r0 = r163;
        r5 = r0.mentiondownButtonCounter;
        if (r5 == 0) goto L_0x1a35;
    L_0x1a0a:
        r0 = r163;
        r5 = r0.mentiondownButtonCounter;
        r6 = 0;
        r5.setVisibility(r6);
        r0 = r163;
        r5 = r0.mentiondownButtonCounter;
        r6 = "%d";
        r7 = 1;
        r7 = new java.lang.Object[r7];
        r8 = 0;
        r0 = r163;
        r11 = r0.newMentionsCount;
        r11 = java.lang.Integer.valueOf(r11);
        r7[r8] = r11;
        r6 = java.lang.String.format(r6, r7);
        r5.setText(r6);
        r5 = 1;
        r6 = 1;
        r0 = r163;
        r0.showMentiondownButton(r5, r6);
    L_0x1a35:
        if (r99 == 0) goto L_0x1324;
    L_0x1a37:
        r0 = r163;
        r5 = r0.paused;
        if (r5 == 0) goto L_0x1aa9;
    L_0x1a3d:
        r5 = 1;
        r0 = r163;
        r0.readWhenResume = r5;
        r0 = r163;
        r5 = r0.maxDate;
        r6 = 0;
        r5 = r5[r6];
        r0 = r163;
        r0.readWithDate = r5;
        r0 = r163;
        r5 = r0.minMessageId;
        r6 = 0;
        r5 = r5[r6];
        r0 = r163;
        r0.readWithMid = r5;
        goto L_0x1324;
    L_0x1a5a:
        r5 = 1;
        r0 = r163;
        r0.scrollToTopOnResume = r5;
        goto L_0x19c9;
    L_0x1a61:
        r5 = 1;
        r0 = r163;
        r0.forceScrollToTop = r5;
        r163.moveScrollToLastMessage();
        goto L_0x19fe;
    L_0x1a6a:
        r0 = r163;
        r5 = r0.newUnreadMessageCount;
        if (r5 == 0) goto L_0x1a9a;
    L_0x1a70:
        r0 = r163;
        r5 = r0.pagedownButtonCounter;
        if (r5 == 0) goto L_0x1a9a;
    L_0x1a76:
        r0 = r163;
        r5 = r0.pagedownButtonCounter;
        r6 = 0;
        r5.setVisibility(r6);
        r0 = r163;
        r5 = r0.pagedownButtonCounter;
        r6 = "%d";
        r7 = 1;
        r7 = new java.lang.Object[r7];
        r8 = 0;
        r0 = r163;
        r11 = r0.newUnreadMessageCount;
        r11 = java.lang.Integer.valueOf(r11);
        r7[r8] = r11;
        r6 = java.lang.String.format(r6, r7);
        r5.setText(r6);
    L_0x1a9a:
        r5 = 1;
        r6 = 1;
        r0 = r163;
        r0.showPagedownButton(r5, r6);
        goto L_0x19fe;
    L_0x1aa3:
        r5 = 1;
        r0 = r163;
        r0.scrollToTopOnResume = r5;
        goto L_0x1a35;
    L_0x1aa9:
        r11 = org.telegram.messenger.MessagesController.getInstance();
        r0 = r163;
        r12 = r0.dialog_id;
        r0 = r163;
        r5 = r0.messages;
        r6 = 0;
        r5 = r5.get(r6);
        r5 = (org.telegram.messenger.MessageObject) r5;
        r14 = r5.getId();
        r0 = r163;
        r5 = r0.minMessageId;
        r6 = 0;
        r15 = r5[r6];
        r0 = r163;
        r5 = r0.maxDate;
        r6 = 0;
        r16 = r5[r6];
        r17 = 1;
        r18 = 0;
        r11.markDialogAsRead(r12, r14, r15, r16, r17, r18);
        goto L_0x1324;
    L_0x1ad7:
        r5 = org.telegram.messenger.NotificationCenter.closeChats;
        r0 = r164;
        if (r0 != r5) goto L_0x1aff;
    L_0x1add:
        if (r165 == 0) goto L_0x1afa;
    L_0x1adf:
        r0 = r165;
        r5 = r0.length;
        if (r5 <= 0) goto L_0x1afa;
    L_0x1ae4:
        r5 = 0;
        r5 = r165[r5];
        r5 = (java.lang.Long) r5;
        r60 = r5.longValue();
        r0 = r163;
        r6 = r0.dialog_id;
        r5 = (r60 > r6 ? 1 : (r60 == r6 ? 0 : -1));
        if (r5 != 0) goto L_0x005b;
    L_0x1af5:
        r163.finishFragment();
        goto L_0x005b;
    L_0x1afa:
        r163.removeSelfFromStack();
        goto L_0x005b;
    L_0x1aff:
        r5 = org.telegram.messenger.NotificationCenter.messagesRead;
        r0 = r164;
        if (r0 != r5) goto L_0x1bef;
    L_0x1b05:
        r5 = 0;
        r79 = r165[r5];
        r79 = (android.util.SparseArray) r79;
        r5 = 1;
        r128 = r165[r5];
        r128 = (android.util.SparseArray) r128;
        r154 = 0;
        r31 = 0;
    L_0x1b13:
        r5 = r79.size();
        r0 = r31;
        if (r0 >= r5) goto L_0x1b6f;
    L_0x1b1b:
        r0 = r79;
        r1 = r31;
        r85 = r0.keyAt(r1);
        r0 = r79;
        r1 = r85;
        r5 = r0.get(r1);
        r5 = (java.lang.Long) r5;
        r104 = r5.longValue();
        r0 = r85;
        r6 = (long) r0;
        r0 = r163;
        r12 = r0.dialog_id;
        r5 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r5 == 0) goto L_0x1b3f;
    L_0x1b3c:
        r31 = r31 + 1;
        goto L_0x1b13;
    L_0x1b3f:
        r4 = 0;
    L_0x1b40:
        r0 = r163;
        r5 = r0.messages;
        r5 = r5.size();
        if (r4 >= r5) goto L_0x1b6f;
    L_0x1b4a:
        r0 = r163;
        r5 = r0.messages;
        r121 = r5.get(r4);
        r121 = (org.telegram.messenger.MessageObject) r121;
        r5 = r121.isOut();
        if (r5 != 0) goto L_0x1ba5;
    L_0x1b5a:
        r5 = r121.getId();
        if (r5 <= 0) goto L_0x1ba5;
    L_0x1b60:
        r5 = r121.getId();
        r0 = r104;
        r6 = (int) r0;
        if (r5 > r6) goto L_0x1ba5;
    L_0x1b69:
        r5 = r121.isUnread();
        if (r5 != 0) goto L_0x1ba0;
    L_0x1b6f:
        r31 = 0;
    L_0x1b71:
        r5 = r128.size();
        r0 = r31;
        if (r0 >= r5) goto L_0x1bd7;
    L_0x1b79:
        r0 = r128;
        r1 = r31;
        r85 = r0.keyAt(r1);
        r0 = r128;
        r1 = r85;
        r5 = r0.get(r1);
        r5 = (java.lang.Long) r5;
        r6 = r5.longValue();
        r0 = (int) r6;
        r17 = r0;
        r0 = r85;
        r6 = (long) r0;
        r0 = r163;
        r12 = r0.dialog_id;
        r5 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r5 == 0) goto L_0x1ba8;
    L_0x1b9d:
        r31 = r31 + 1;
        goto L_0x1b71;
    L_0x1ba0:
        r121.setIsRead();
        r154 = 1;
    L_0x1ba5:
        r4 = r4 + 1;
        goto L_0x1b40;
    L_0x1ba8:
        r4 = 0;
    L_0x1ba9:
        r0 = r163;
        r5 = r0.messages;
        r5 = r5.size();
        if (r4 >= r5) goto L_0x1bd7;
    L_0x1bb3:
        r0 = r163;
        r5 = r0.messages;
        r121 = r5.get(r4);
        r121 = (org.telegram.messenger.MessageObject) r121;
        r5 = r121.isOut();
        if (r5 == 0) goto L_0x1bec;
    L_0x1bc3:
        r5 = r121.getId();
        if (r5 <= 0) goto L_0x1bec;
    L_0x1bc9:
        r5 = r121.getId();
        r0 = r17;
        if (r5 > r0) goto L_0x1bec;
    L_0x1bd1:
        r5 = r121.isUnread();
        if (r5 != 0) goto L_0x1be7;
    L_0x1bd7:
        r5 = r79.size();
        if (r5 == 0) goto L_0x1be0;
    L_0x1bdd:
        r163.removeUnreadPlane();
    L_0x1be0:
        if (r154 == 0) goto L_0x005b;
    L_0x1be2:
        r163.updateVisibleRows();
        goto L_0x005b;
    L_0x1be7:
        r121.setIsRead();
        r154 = 1;
    L_0x1bec:
        r4 = r4 + 1;
        goto L_0x1ba9;
    L_0x1bef:
        r5 = org.telegram.messenger.NotificationCenter.historyCleared;
        r0 = r164;
        if (r0 != r5) goto L_0x1e10;
    L_0x1bf5:
        r5 = 0;
        r5 = r165[r5];
        r5 = (java.lang.Long) r5;
        r60 = r5.longValue();
        r0 = r163;
        r6 = r0.dialog_id;
        r5 = (r60 > r6 ? 1 : (r60 == r6 ? 0 : -1));
        if (r5 != 0) goto L_0x005b;
    L_0x1c06:
        r5 = 1;
        r5 = r165[r5];
        r5 = (java.lang.Integer) r5;
        r100 = r5.intValue();
        r154 = 0;
        r31 = 0;
    L_0x1c13:
        r0 = r163;
        r5 = r0.messages;
        r5 = r5.size();
        r0 = r31;
        if (r0 >= r5) goto L_0x1cc8;
    L_0x1c1f:
        r0 = r163;
        r5 = r0.messages;
        r0 = r31;
        r121 = r5.get(r0);
        r121 = (org.telegram.messenger.MessageObject) r121;
        r110 = r121.getId();
        if (r110 <= 0) goto L_0x1c37;
    L_0x1c31:
        r0 = r110;
        r1 = r100;
        if (r0 <= r1) goto L_0x1c3a;
    L_0x1c37:
        r31 = r31 + 1;
        goto L_0x1c13;
    L_0x1c3a:
        r0 = r163;
        r5 = r0.info;
        if (r5 == 0) goto L_0x1c6a;
    L_0x1c40:
        r0 = r163;
        r5 = r0.info;
        r5 = r5.pinned_msg_id;
        r0 = r110;
        if (r5 != r0) goto L_0x1c6a;
    L_0x1c4a:
        r5 = 0;
        r0 = r163;
        r0.pinnedMessageObject = r5;
        r0 = r163;
        r5 = r0.info;
        r6 = 0;
        r5.pinned_msg_id = r6;
        r5 = org.telegram.messenger.MessagesStorage.getInstance();
        r0 = r163;
        r6 = r0.info;
        r6 = r6.id;
        r7 = 0;
        r5.updateChannelPinnedMessage(r6, r7);
        r5 = 1;
        r0 = r163;
        r0.updatePinnedMessageView(r5);
    L_0x1c6a:
        r0 = r163;
        r5 = r0.messages;
        r0 = r31;
        r5.remove(r0);
        r31 = r31 + -1;
        r0 = r163;
        r5 = r0.messagesDict;
        r6 = 0;
        r5 = r5[r6];
        r6 = java.lang.Integer.valueOf(r110);
        r5.remove(r6);
        r0 = r163;
        r5 = r0.messagesByDays;
        r0 = r121;
        r6 = r0.dateKey;
        r57 = r5.get(r6);
        r57 = (java.util.ArrayList) r57;
        if (r57 == 0) goto L_0x1cc4;
    L_0x1c93:
        r0 = r57;
        r1 = r121;
        r0.remove(r1);
        r5 = r57.isEmpty();
        if (r5 == 0) goto L_0x1cc4;
    L_0x1ca0:
        r0 = r163;
        r5 = r0.messagesByDays;
        r0 = r121;
        r6 = r0.dateKey;
        r5.remove(r6);
        if (r31 < 0) goto L_0x1cc4;
    L_0x1cad:
        r0 = r163;
        r5 = r0.messages;
        r5 = r5.size();
        r0 = r31;
        if (r0 >= r5) goto L_0x1cc4;
    L_0x1cb9:
        r0 = r163;
        r5 = r0.messages;
        r0 = r31;
        r5.remove(r0);
        r31 = r31 + -1;
    L_0x1cc4:
        r154 = 1;
        goto L_0x1c37;
    L_0x1cc8:
        r0 = r163;
        r5 = r0.messages;
        r5 = r5.isEmpty();
        if (r5 == 0) goto L_0x1d97;
    L_0x1cd2:
        r0 = r163;
        r5 = r0.endReached;
        r6 = 0;
        r5 = r5[r6];
        if (r5 != 0) goto L_0x1dd1;
    L_0x1cdb:
        r0 = r163;
        r5 = r0.loading;
        if (r5 != 0) goto L_0x1dd1;
    L_0x1ce1:
        r0 = r163;
        r5 = r0.progressView;
        if (r5 == 0) goto L_0x1cef;
    L_0x1ce7:
        r0 = r163;
        r5 = r0.progressView;
        r6 = 4;
        r5.setVisibility(r6);
    L_0x1cef:
        r0 = r163;
        r5 = r0.chatListView;
        if (r5 == 0) goto L_0x1cfd;
    L_0x1cf5:
        r0 = r163;
        r5 = r0.chatListView;
        r6 = 0;
        r5.setEmptyView(r6);
    L_0x1cfd:
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        if (r5 != 0) goto L_0x1dab;
    L_0x1d03:
        r0 = r163;
        r5 = r0.maxMessageId;
        r6 = 0;
        r0 = r163;
        r7 = r0.maxMessageId;
        r8 = 1;
        r11 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r7[r8] = r11;
        r5[r6] = r11;
        r0 = r163;
        r5 = r0.minMessageId;
        r6 = 0;
        r0 = r163;
        r7 = r0.minMessageId;
        r8 = 1;
        r11 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r7[r8] = r11;
        r5[r6] = r11;
    L_0x1d24:
        r0 = r163;
        r5 = r0.maxDate;
        r6 = 0;
        r0 = r163;
        r7 = r0.maxDate;
        r8 = 1;
        r11 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r7[r8] = r11;
        r5[r6] = r11;
        r0 = r163;
        r5 = r0.minDate;
        r6 = 0;
        r0 = r163;
        r7 = r0.minDate;
        r8 = 1;
        r11 = 0;
        r7[r8] = r11;
        r5[r6] = r11;
        r0 = r163;
        r5 = r0.waitingForLoad;
        r0 = r163;
        r6 = r0.lastLoadIndex;
        r6 = java.lang.Integer.valueOf(r6);
        r5.add(r6);
        r11 = org.telegram.messenger.MessagesController.getInstance();
        r0 = r163;
        r12 = r0.dialog_id;
        r14 = 30;
        r15 = 0;
        r16 = 0;
        r0 = r163;
        r5 = r0.cacheEndReached;
        r6 = 0;
        r5 = r5[r6];
        if (r5 != 0) goto L_0x1dce;
    L_0x1d68:
        r17 = 1;
    L_0x1d6a:
        r0 = r163;
        r5 = r0.minDate;
        r6 = 0;
        r18 = r5[r6];
        r0 = r163;
        r0 = r0.classGuid;
        r19 = r0;
        r20 = 0;
        r21 = 0;
        r0 = r163;
        r5 = r0.currentChat;
        r22 = org.telegram.messenger.ChatObject.isChannel(r5);
        r0 = r163;
        r0 = r0.lastLoadIndex;
        r23 = r0;
        r5 = r23 + 1;
        r0 = r163;
        r0.lastLoadIndex = r5;
        r11.loadMessages(r12, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23);
        r5 = 1;
        r0 = r163;
        r0.loading = r5;
    L_0x1d97:
        if (r154 == 0) goto L_0x005b;
    L_0x1d99:
        r0 = r163;
        r5 = r0.chatAdapter;
        if (r5 == 0) goto L_0x005b;
    L_0x1d9f:
        r163.removeUnreadPlane();
        r0 = r163;
        r5 = r0.chatAdapter;
        r5.notifyDataSetChanged();
        goto L_0x005b;
    L_0x1dab:
        r0 = r163;
        r5 = r0.maxMessageId;
        r6 = 0;
        r0 = r163;
        r7 = r0.maxMessageId;
        r8 = 1;
        r11 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r7[r8] = r11;
        r5[r6] = r11;
        r0 = r163;
        r5 = r0.minMessageId;
        r6 = 0;
        r0 = r163;
        r7 = r0.minMessageId;
        r8 = 1;
        r11 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r7[r8] = r11;
        r5[r6] = r11;
        goto L_0x1d24;
    L_0x1dce:
        r17 = 0;
        goto L_0x1d6a;
    L_0x1dd1:
        r0 = r163;
        r5 = r0.botButtons;
        if (r5 == 0) goto L_0x1deb;
    L_0x1dd7:
        r5 = 0;
        r0 = r163;
        r0.botButtons = r5;
        r0 = r163;
        r5 = r0.chatActivityEnterView;
        if (r5 == 0) goto L_0x1deb;
    L_0x1de2:
        r0 = r163;
        r5 = r0.chatActivityEnterView;
        r6 = 0;
        r7 = 0;
        r5.setButtons(r6, r7);
    L_0x1deb:
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        if (r5 != 0) goto L_0x1d97;
    L_0x1df1:
        r0 = r163;
        r5 = r0.currentUser;
        if (r5 == 0) goto L_0x1d97;
    L_0x1df7:
        r0 = r163;
        r5 = r0.currentUser;
        r5 = r5.bot;
        if (r5 == 0) goto L_0x1d97;
    L_0x1dff:
        r0 = r163;
        r5 = r0.botUser;
        if (r5 != 0) goto L_0x1d97;
    L_0x1e05:
        r5 = "";
        r0 = r163;
        r0.botUser = r5;
        r163.updateBottomOverlay();
        goto L_0x1d97;
    L_0x1e10:
        r5 = org.telegram.messenger.NotificationCenter.messagesDeleted;
        r0 = r164;
        if (r0 != r5) goto L_0x213e;
    L_0x1e16:
        r5 = 0;
        r98 = r165[r5];
        r98 = (java.util.ArrayList) r98;
        r5 = 1;
        r5 = r165[r5];
        r5 = (java.lang.Integer) r5;
        r41 = r5.intValue();
        r94 = 0;
        r0 = r163;
        r5 = r0.currentChat;
        r5 = org.telegram.messenger.ChatObject.isChannel(r5);
        if (r5 == 0) goto L_0x1f47;
    L_0x1e30:
        if (r41 != 0) goto L_0x1f39;
    L_0x1e32:
        r0 = r163;
        r6 = r0.mergeDialogId;
        r12 = 0;
        r5 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r5 == 0) goto L_0x1f39;
    L_0x1e3c:
        r94 = 1;
    L_0x1e3e:
        r154 = 0;
        r116 = 0;
        r4 = 0;
    L_0x1e43:
        r5 = r98.size();
        if (r4 >= r5) goto L_0x1f4b;
    L_0x1e49:
        r0 = r98;
        r77 = r0.get(r4);
        r77 = (java.lang.Integer) r77;
        r0 = r163;
        r5 = r0.messagesDict;
        r5 = r5[r94];
        r0 = r77;
        r121 = r5.get(r0);
        r121 = (org.telegram.messenger.MessageObject) r121;
        if (r94 != 0) goto L_0x1e8f;
    L_0x1e61:
        r0 = r163;
        r5 = r0.info;
        if (r5 == 0) goto L_0x1e8f;
    L_0x1e67:
        r0 = r163;
        r5 = r0.info;
        r5 = r5.pinned_msg_id;
        r6 = r77.intValue();
        if (r5 != r6) goto L_0x1e8f;
    L_0x1e73:
        r5 = 0;
        r0 = r163;
        r0.pinnedMessageObject = r5;
        r0 = r163;
        r5 = r0.info;
        r6 = 0;
        r5.pinned_msg_id = r6;
        r5 = org.telegram.messenger.MessagesStorage.getInstance();
        r6 = 0;
        r0 = r41;
        r5.updateChannelPinnedMessage(r0, r6);
        r5 = 1;
        r0 = r163;
        r0.updatePinnedMessageView(r5);
    L_0x1e8f:
        if (r121 == 0) goto L_0x1f35;
    L_0x1e91:
        r0 = r163;
        r5 = r0.messages;
        r0 = r121;
        r80 = r5.indexOf(r0);
        r5 = -1;
        r0 = r80;
        if (r0 == r5) goto L_0x1f35;
    L_0x1ea0:
        r0 = r163;
        r5 = r0.messages;
        r0 = r80;
        r139 = r5.remove(r0);
        r139 = (org.telegram.messenger.MessageObject) r139;
        r6 = r139.getGroupId();
        r12 = 0;
        r5 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r5 == 0) goto L_0x1ee9;
    L_0x1eb6:
        r0 = r163;
        r5 = r0.groupedMessagesMap;
        r6 = r139.getGroupId();
        r6 = java.lang.Long.valueOf(r6);
        r72 = r5.get(r6);
        r72 = (org.telegram.messenger.MessageObject.GroupedMessages) r72;
        if (r72 == 0) goto L_0x1ee9;
    L_0x1eca:
        if (r116 != 0) goto L_0x1ed1;
    L_0x1ecc:
        r116 = new java.util.HashMap;
        r116.<init>();
    L_0x1ed1:
        r0 = r72;
        r6 = r0.groupId;
        r5 = java.lang.Long.valueOf(r6);
        r0 = r116;
        r1 = r72;
        r0.put(r5, r1);
        r0 = r72;
        r5 = r0.messages;
        r0 = r121;
        r5.remove(r0);
    L_0x1ee9:
        r0 = r163;
        r5 = r0.messagesDict;
        r5 = r5[r94];
        r0 = r77;
        r5.remove(r0);
        r0 = r163;
        r5 = r0.messagesByDays;
        r0 = r121;
        r6 = r0.dateKey;
        r57 = r5.get(r6);
        r57 = (java.util.ArrayList) r57;
        if (r57 == 0) goto L_0x1f33;
    L_0x1f04:
        r0 = r57;
        r1 = r121;
        r0.remove(r1);
        r5 = r57.isEmpty();
        if (r5 == 0) goto L_0x1f33;
    L_0x1f11:
        r0 = r163;
        r5 = r0.messagesByDays;
        r0 = r121;
        r6 = r0.dateKey;
        r5.remove(r6);
        if (r80 < 0) goto L_0x1f33;
    L_0x1f1e:
        r0 = r163;
        r5 = r0.messages;
        r5 = r5.size();
        r0 = r80;
        if (r0 >= r5) goto L_0x1f33;
    L_0x1f2a:
        r0 = r163;
        r5 = r0.messages;
        r0 = r80;
        r5.remove(r0);
    L_0x1f33:
        r154 = 1;
    L_0x1f35:
        r4 = r4 + 1;
        goto L_0x1e43;
    L_0x1f39:
        r0 = r163;
        r5 = r0.currentChat;
        r5 = r5.id;
        r0 = r41;
        if (r0 != r5) goto L_0x005b;
    L_0x1f43:
        r94 = 0;
        goto L_0x1e3e;
    L_0x1f47:
        if (r41 == 0) goto L_0x1e3e;
    L_0x1f49:
        goto L_0x005b;
    L_0x1f4b:
        if (r116 == 0) goto L_0x1fc4;
    L_0x1f4d:
        r5 = r116.entrySet();
        r5 = r5.iterator();
    L_0x1f55:
        r6 = r5.hasNext();
        if (r6 == 0) goto L_0x1fc4;
    L_0x1f5b:
        r64 = r5.next();
        r64 = (java.util.Map.Entry) r64;
        r72 = r64.getValue();
        r72 = (org.telegram.messenger.MessageObject.GroupedMessages) r72;
        r0 = r72;
        r6 = r0.messages;
        r6 = r6.isEmpty();
        if (r6 == 0) goto L_0x1f81;
    L_0x1f71:
        r0 = r163;
        r6 = r0.groupedMessagesMap;
        r0 = r72;
        r12 = r0.groupId;
        r7 = java.lang.Long.valueOf(r12);
        r6.remove(r7);
        goto L_0x1f55;
    L_0x1f81:
        r72.calculate();
        r0 = r72;
        r6 = r0.messages;
        r0 = r72;
        r7 = r0.messages;
        r7 = r7.size();
        r7 = r7 + -1;
        r106 = r6.get(r7);
        r106 = (org.telegram.messenger.MessageObject) r106;
        r0 = r163;
        r6 = r0.messages;
        r0 = r106;
        r80 = r6.indexOf(r0);
        if (r80 < 0) goto L_0x1f55;
    L_0x1fa4:
        r0 = r163;
        r6 = r0.chatAdapter;
        if (r6 == 0) goto L_0x1f55;
    L_0x1faa:
        r0 = r163;
        r6 = r0.chatAdapter;
        r0 = r163;
        r7 = r0.chatAdapter;
        r7 = r7.messagesStartRow;
        r7 = r7 + r80;
        r0 = r72;
        r8 = r0.messages;
        r8 = r8.size();
        r6.notifyItemRangeChanged(r7, r8);
        goto L_0x1f55;
    L_0x1fc4:
        r0 = r163;
        r5 = r0.messages;
        r5 = r5.isEmpty();
        if (r5 == 0) goto L_0x2093;
    L_0x1fce:
        r0 = r163;
        r5 = r0.endReached;
        r6 = 0;
        r5 = r5[r6];
        if (r5 != 0) goto L_0x20cd;
    L_0x1fd7:
        r0 = r163;
        r5 = r0.loading;
        if (r5 != 0) goto L_0x20cd;
    L_0x1fdd:
        r0 = r163;
        r5 = r0.progressView;
        if (r5 == 0) goto L_0x1feb;
    L_0x1fe3:
        r0 = r163;
        r5 = r0.progressView;
        r6 = 4;
        r5.setVisibility(r6);
    L_0x1feb:
        r0 = r163;
        r5 = r0.chatListView;
        if (r5 == 0) goto L_0x1ff9;
    L_0x1ff1:
        r0 = r163;
        r5 = r0.chatListView;
        r6 = 0;
        r5.setEmptyView(r6);
    L_0x1ff9:
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        if (r5 != 0) goto L_0x20a7;
    L_0x1fff:
        r0 = r163;
        r5 = r0.maxMessageId;
        r6 = 0;
        r0 = r163;
        r7 = r0.maxMessageId;
        r8 = 1;
        r11 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r7[r8] = r11;
        r5[r6] = r11;
        r0 = r163;
        r5 = r0.minMessageId;
        r6 = 0;
        r0 = r163;
        r7 = r0.minMessageId;
        r8 = 1;
        r11 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r7[r8] = r11;
        r5[r6] = r11;
    L_0x2020:
        r0 = r163;
        r5 = r0.maxDate;
        r6 = 0;
        r0 = r163;
        r7 = r0.maxDate;
        r8 = 1;
        r11 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r7[r8] = r11;
        r5[r6] = r11;
        r0 = r163;
        r5 = r0.minDate;
        r6 = 0;
        r0 = r163;
        r7 = r0.minDate;
        r8 = 1;
        r11 = 0;
        r7[r8] = r11;
        r5[r6] = r11;
        r0 = r163;
        r5 = r0.waitingForLoad;
        r0 = r163;
        r6 = r0.lastLoadIndex;
        r6 = java.lang.Integer.valueOf(r6);
        r5.add(r6);
        r11 = org.telegram.messenger.MessagesController.getInstance();
        r0 = r163;
        r12 = r0.dialog_id;
        r14 = 30;
        r15 = 0;
        r16 = 0;
        r0 = r163;
        r5 = r0.cacheEndReached;
        r6 = 0;
        r5 = r5[r6];
        if (r5 != 0) goto L_0x20ca;
    L_0x2064:
        r17 = 1;
    L_0x2066:
        r0 = r163;
        r5 = r0.minDate;
        r6 = 0;
        r18 = r5[r6];
        r0 = r163;
        r0 = r0.classGuid;
        r19 = r0;
        r20 = 0;
        r21 = 0;
        r0 = r163;
        r5 = r0.currentChat;
        r22 = org.telegram.messenger.ChatObject.isChannel(r5);
        r0 = r163;
        r0 = r0.lastLoadIndex;
        r23 = r0;
        r5 = r23 + 1;
        r0 = r163;
        r0.lastLoadIndex = r5;
        r11.loadMessages(r12, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23);
        r5 = 1;
        r0 = r163;
        r0.loading = r5;
    L_0x2093:
        r0 = r163;
        r5 = r0.chatAdapter;
        if (r5 == 0) goto L_0x005b;
    L_0x2099:
        if (r154 == 0) goto L_0x210c;
    L_0x209b:
        r163.removeUnreadPlane();
        r0 = r163;
        r5 = r0.chatAdapter;
        r5.notifyDataSetChanged();
        goto L_0x005b;
    L_0x20a7:
        r0 = r163;
        r5 = r0.maxMessageId;
        r6 = 0;
        r0 = r163;
        r7 = r0.maxMessageId;
        r8 = 1;
        r11 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r7[r8] = r11;
        r5[r6] = r11;
        r0 = r163;
        r5 = r0.minMessageId;
        r6 = 0;
        r0 = r163;
        r7 = r0.minMessageId;
        r8 = 1;
        r11 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r7[r8] = r11;
        r5[r6] = r11;
        goto L_0x2020;
    L_0x20ca:
        r17 = 0;
        goto L_0x2066;
    L_0x20cd:
        r0 = r163;
        r5 = r0.botButtons;
        if (r5 == 0) goto L_0x20e7;
    L_0x20d3:
        r5 = 0;
        r0 = r163;
        r0.botButtons = r5;
        r0 = r163;
        r5 = r0.chatActivityEnterView;
        if (r5 == 0) goto L_0x20e7;
    L_0x20de:
        r0 = r163;
        r5 = r0.chatActivityEnterView;
        r6 = 0;
        r7 = 0;
        r5.setButtons(r6, r7);
    L_0x20e7:
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        if (r5 != 0) goto L_0x2093;
    L_0x20ed:
        r0 = r163;
        r5 = r0.currentUser;
        if (r5 == 0) goto L_0x2093;
    L_0x20f3:
        r0 = r163;
        r5 = r0.currentUser;
        r5 = r5.bot;
        if (r5 == 0) goto L_0x2093;
    L_0x20fb:
        r0 = r163;
        r5 = r0.botUser;
        if (r5 != 0) goto L_0x2093;
    L_0x2101:
        r5 = "";
        r0 = r163;
        r0.botUser = r5;
        r163.updateBottomOverlay();
        goto L_0x2093;
    L_0x210c:
        r5 = 0;
        r0 = r163;
        r0.first_unread_id = r5;
        r5 = 0;
        r0 = r163;
        r0.last_message_id = r5;
        r5 = 0;
        r0 = r163;
        r0.createUnreadMessageAfterId = r5;
        r5 = 0;
        r0 = r163;
        r0.unread_to_load = r5;
        r0 = r163;
        r5 = r0.unreadMessageObject;
        r0 = r163;
        r0.removeMessageObject(r5);
        r5 = 0;
        r0 = r163;
        r0.unreadMessageObject = r5;
        r0 = r163;
        r5 = r0.pagedownButtonCounter;
        if (r5 == 0) goto L_0x005b;
    L_0x2134:
        r0 = r163;
        r5 = r0.pagedownButtonCounter;
        r6 = 4;
        r5.setVisibility(r6);
        goto L_0x005b;
    L_0x213e:
        r5 = org.telegram.messenger.NotificationCenter.messageReceivedByServer;
        r0 = r164;
        if (r0 != r5) goto L_0x2339;
    L_0x2144:
        r5 = 0;
        r112 = r165[r5];
        r112 = (java.lang.Integer) r112;
        r0 = r163;
        r5 = r0.messagesDict;
        r6 = 0;
        r5 = r5[r6];
        r0 = r112;
        r121 = r5.get(r0);
        r121 = (org.telegram.messenger.MessageObject) r121;
        if (r121 == 0) goto L_0x005b;
    L_0x215a:
        r5 = 1;
        r117 = r165[r5];
        r117 = (java.lang.Integer) r117;
        r0 = r117;
        r1 = r112;
        r5 = r0.equals(r1);
        if (r5 != 0) goto L_0x21e8;
    L_0x2169:
        r0 = r163;
        r5 = r0.messagesDict;
        r6 = 0;
        r5 = r5[r6];
        r0 = r117;
        r5 = r5.containsKey(r0);
        if (r5 == 0) goto L_0x21e8;
    L_0x2178:
        r0 = r163;
        r5 = r0.messagesDict;
        r6 = 0;
        r5 = r5[r6];
        r0 = r112;
        r139 = r5.remove(r0);
        r139 = (org.telegram.messenger.MessageObject) r139;
        if (r139 == 0) goto L_0x005b;
    L_0x2189:
        r0 = r163;
        r5 = r0.messages;
        r0 = r139;
        r80 = r5.indexOf(r0);
        r0 = r163;
        r5 = r0.messages;
        r0 = r80;
        r5.remove(r0);
        r0 = r163;
        r5 = r0.messagesByDays;
        r0 = r139;
        r6 = r0.dateKey;
        r57 = r5.get(r6);
        r57 = (java.util.ArrayList) r57;
        r0 = r57;
        r1 = r121;
        r0.remove(r1);
        r5 = r57.isEmpty();
        if (r5 == 0) goto L_0x21d9;
    L_0x21b7:
        r0 = r163;
        r5 = r0.messagesByDays;
        r0 = r121;
        r6 = r0.dateKey;
        r5.remove(r6);
        if (r80 < 0) goto L_0x21d9;
    L_0x21c4:
        r0 = r163;
        r5 = r0.messages;
        r5 = r5.size();
        r0 = r80;
        if (r0 >= r5) goto L_0x21d9;
    L_0x21d0:
        r0 = r163;
        r5 = r0.messages;
        r0 = r80;
        r5.remove(r0);
    L_0x21d9:
        r0 = r163;
        r5 = r0.chatAdapter;
        if (r5 == 0) goto L_0x005b;
    L_0x21df:
        r0 = r163;
        r5 = r0.chatAdapter;
        r5.notifyDataSetChanged();
        goto L_0x005b;
    L_0x21e8:
        r5 = 2;
        r118 = r165[r5];
        r118 = (org.telegram.tgnet.TLRPC$Message) r118;
        r101 = 0;
        r155 = 0;
        if (r118 == 0) goto L_0x22ad;
    L_0x21f3:
        r5 = r121.isForwarded();	 Catch:{ Exception -> 0x2333 }
        if (r5 == 0) goto L_0x232b;
    L_0x21f9:
        r0 = r121;
        r5 = r0.messageOwner;	 Catch:{ Exception -> 0x2333 }
        r5 = r5.reply_markup;	 Catch:{ Exception -> 0x2333 }
        if (r5 != 0) goto L_0x2207;
    L_0x2201:
        r0 = r118;
        r5 = r0.reply_markup;	 Catch:{ Exception -> 0x2333 }
        if (r5 != 0) goto L_0x2217;
    L_0x2207:
        r0 = r121;
        r5 = r0.messageOwner;	 Catch:{ Exception -> 0x2333 }
        r5 = r5.message;	 Catch:{ Exception -> 0x2333 }
        r0 = r118;
        r6 = r0.message;	 Catch:{ Exception -> 0x2333 }
        r5 = r5.equals(r6);	 Catch:{ Exception -> 0x2333 }
        if (r5 != 0) goto L_0x232b;
    L_0x2217:
        r155 = 1;
    L_0x2219:
        if (r155 != 0) goto L_0x2258;
    L_0x221b:
        r0 = r121;
        r5 = r0.messageOwner;	 Catch:{ Exception -> 0x2333 }
        r5 = r5.params;	 Catch:{ Exception -> 0x2333 }
        if (r5 == 0) goto L_0x2232;
    L_0x2223:
        r0 = r121;
        r5 = r0.messageOwner;	 Catch:{ Exception -> 0x2333 }
        r5 = r5.params;	 Catch:{ Exception -> 0x2333 }
        r6 = "query_id";
        r5 = r5.containsKey(r6);	 Catch:{ Exception -> 0x2333 }
        if (r5 != 0) goto L_0x2258;
    L_0x2232:
        r0 = r118;
        r5 = r0.media;	 Catch:{ Exception -> 0x2333 }
        if (r5 == 0) goto L_0x232f;
    L_0x2238:
        r0 = r121;
        r5 = r0.messageOwner;	 Catch:{ Exception -> 0x2333 }
        r5 = r5.media;	 Catch:{ Exception -> 0x2333 }
        if (r5 == 0) goto L_0x232f;
    L_0x2240:
        r0 = r118;
        r5 = r0.media;	 Catch:{ Exception -> 0x2333 }
        r5 = r5.getClass();	 Catch:{ Exception -> 0x2333 }
        r0 = r121;
        r6 = r0.messageOwner;	 Catch:{ Exception -> 0x2333 }
        r6 = r6.media;	 Catch:{ Exception -> 0x2333 }
        r6 = r6.getClass();	 Catch:{ Exception -> 0x2333 }
        r5 = r5.equals(r6);	 Catch:{ Exception -> 0x2333 }
        if (r5 != 0) goto L_0x232f;
    L_0x2258:
        r101 = 1;
    L_0x225a:
        r6 = r121.getGroupId();
        r12 = 0;
        r5 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r5 == 0) goto L_0x2293;
    L_0x2264:
        r0 = r118;
        r6 = r0.grouped_id;
        r12 = 0;
        r5 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r5 == 0) goto L_0x2293;
    L_0x226e:
        r0 = r163;
        r5 = r0.groupedMessagesMap;
        r6 = r121.getGroupId();
        r6 = java.lang.Long.valueOf(r6);
        r125 = r5.get(r6);
        r125 = (org.telegram.messenger.MessageObject.GroupedMessages) r125;
        if (r125 == 0) goto L_0x2293;
    L_0x2282:
        r0 = r163;
        r5 = r0.groupedMessagesMap;
        r0 = r118;
        r6 = r0.grouped_id;
        r6 = java.lang.Long.valueOf(r6);
        r0 = r125;
        r5.put(r6, r0);
    L_0x2293:
        r0 = r118;
        r1 = r121;
        r1.messageOwner = r0;
        r5 = 1;
        r0 = r121;
        r0.generateThumbs(r5);
        r121.setType();
        r0 = r118;
        r5 = r0.media;
        r5 = r5 instanceof org.telegram.tgnet.TLRPC$TL_messageMediaGame;
        if (r5 == 0) goto L_0x22ad;
    L_0x22aa:
        r121.applyNewText();
    L_0x22ad:
        if (r155 == 0) goto L_0x22b2;
    L_0x22af:
        r121.measureInlineBotButtons();
    L_0x22b2:
        r0 = r163;
        r5 = r0.messagesDict;
        r6 = 0;
        r5 = r5[r6];
        r0 = r112;
        r5.remove(r0);
        r0 = r163;
        r5 = r0.messagesDict;
        r6 = 0;
        r5 = r5[r6];
        r0 = r117;
        r1 = r121;
        r5.put(r0, r1);
        r0 = r121;
        r5 = r0.messageOwner;
        r6 = r117.intValue();
        r5.id = r6;
        r0 = r121;
        r5 = r0.messageOwner;
        r6 = 0;
        r5.send_state = r6;
        r0 = r101;
        r1 = r121;
        r1.forceUpdate = r0;
        r102 = new java.util.ArrayList;
        r102.<init>();
        r0 = r102;
        r1 = r121;
        r0.add(r1);
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        if (r5 != 0) goto L_0x22fe;
    L_0x22f5:
        r0 = r163;
        r6 = r0.dialog_id;
        r0 = r102;
        org.telegram.messenger.query.MessagesQuery.loadReplyMessagesForMessages(r0, r6);
    L_0x22fe:
        r0 = r163;
        r5 = r0.chatAdapter;
        if (r5 == 0) goto L_0x230d;
    L_0x2304:
        r0 = r163;
        r5 = r0.chatAdapter;
        r0 = r121;
        r5.updateRowWithMessageObject(r0);
    L_0x230d:
        r0 = r163;
        r5 = r0.chatLayoutManager;
        if (r5 == 0) goto L_0x2322;
    L_0x2313:
        if (r101 == 0) goto L_0x2322;
    L_0x2315:
        r0 = r163;
        r5 = r0.chatLayoutManager;
        r5 = r5.findFirstVisibleItemPosition();
        if (r5 != 0) goto L_0x2322;
    L_0x231f:
        r163.moveScrollToLastMessage();
    L_0x2322:
        r5 = org.telegram.messenger.NotificationsController.getInstance();
        r5.playOutChatSound();
        goto L_0x005b;
    L_0x232b:
        r155 = 0;
        goto L_0x2219;
    L_0x232f:
        r101 = 0;
        goto L_0x225a;
    L_0x2333:
        r59 = move-exception;
        org.telegram.messenger.FileLog.e(r59);
        goto L_0x225a;
    L_0x2339:
        r5 = org.telegram.messenger.NotificationCenter.messageReceivedByAck;
        r0 = r164;
        if (r0 != r5) goto L_0x236d;
    L_0x233f:
        r5 = 0;
        r112 = r165[r5];
        r112 = (java.lang.Integer) r112;
        r0 = r163;
        r5 = r0.messagesDict;
        r6 = 0;
        r5 = r5[r6];
        r0 = r112;
        r121 = r5.get(r0);
        r121 = (org.telegram.messenger.MessageObject) r121;
        if (r121 == 0) goto L_0x005b;
    L_0x2355:
        r0 = r121;
        r5 = r0.messageOwner;
        r6 = 0;
        r5.send_state = r6;
        r0 = r163;
        r5 = r0.chatAdapter;
        if (r5 == 0) goto L_0x005b;
    L_0x2362:
        r0 = r163;
        r5 = r0.chatAdapter;
        r0 = r121;
        r5.updateRowWithMessageObject(r0);
        goto L_0x005b;
    L_0x236d:
        r5 = org.telegram.messenger.NotificationCenter.messageSendError;
        r0 = r164;
        if (r0 != r5) goto L_0x2395;
    L_0x2373:
        r5 = 0;
        r112 = r165[r5];
        r112 = (java.lang.Integer) r112;
        r0 = r163;
        r5 = r0.messagesDict;
        r6 = 0;
        r5 = r5[r6];
        r0 = r112;
        r121 = r5.get(r0);
        r121 = (org.telegram.messenger.MessageObject) r121;
        if (r121 == 0) goto L_0x005b;
    L_0x2389:
        r0 = r121;
        r5 = r0.messageOwner;
        r6 = 2;
        r5.send_state = r6;
        r163.updateVisibleRows();
        goto L_0x005b;
    L_0x2395:
        r5 = org.telegram.messenger.NotificationCenter.chatInfoDidLoaded;
        r0 = r164;
        if (r0 != r5) goto L_0x264b;
    L_0x239b:
        r5 = 0;
        r45 = r165[r5];
        r45 = (org.telegram.tgnet.TLRPC$ChatFull) r45;
        r0 = r163;
        r5 = r0.currentChat;
        if (r5 == 0) goto L_0x005b;
    L_0x23a6:
        r0 = r45;
        r5 = r0.id;
        r0 = r163;
        r6 = r0.currentChat;
        r6 = r6.id;
        if (r5 != r6) goto L_0x005b;
    L_0x23b2:
        r0 = r45;
        r5 = r0 instanceof org.telegram.tgnet.TLRPC$TL_channelFull;
        if (r5 == 0) goto L_0x242a;
    L_0x23b8:
        r0 = r163;
        r5 = r0.currentChat;
        r5 = r5.megagroup;
        if (r5 == 0) goto L_0x2414;
    L_0x23c0:
        r86 = 0;
        r0 = r45;
        r5 = r0.participants;
        if (r5 == 0) goto L_0x23ec;
    L_0x23c8:
        r4 = 0;
    L_0x23c9:
        r0 = r45;
        r5 = r0.participants;
        r5 = r5.participants;
        r5 = r5.size();
        if (r4 >= r5) goto L_0x23ec;
    L_0x23d5:
        r0 = r45;
        r5 = r0.participants;
        r5 = r5.participants;
        r5 = r5.get(r4);
        r5 = (org.telegram.tgnet.TLRPC$ChatParticipant) r5;
        r5 = r5.date;
        r0 = r86;
        r86 = java.lang.Math.max(r5, r0);
        r4 = r4 + 1;
        goto L_0x23c9;
    L_0x23ec:
        if (r86 == 0) goto L_0x2403;
    L_0x23ee:
        r6 = java.lang.System.currentTimeMillis();
        r12 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r6 = r6 / r12;
        r0 = r86;
        r12 = (long) r0;
        r6 = r6 - r12;
        r6 = java.lang.Math.abs(r6);
        r12 = 3600; // 0xe10 float:5.045E-42 double:1.7786E-320;
        r5 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r5 <= 0) goto L_0x2414;
    L_0x2403:
        r5 = org.telegram.messenger.MessagesController.getInstance();
        r0 = r163;
        r6 = r0.currentChat;
        r6 = r6.id;
        r6 = java.lang.Integer.valueOf(r6);
        r5.loadChannelParticipants(r6);
    L_0x2414:
        r0 = r45;
        r5 = r0.participants;
        if (r5 != 0) goto L_0x242a;
    L_0x241a:
        r0 = r163;
        r5 = r0.info;
        if (r5 == 0) goto L_0x242a;
    L_0x2420:
        r0 = r163;
        r5 = r0.info;
        r5 = r5.participants;
        r0 = r45;
        r0.participants = r5;
    L_0x242a:
        r0 = r45;
        r1 = r163;
        r1.info = r0;
        r0 = r163;
        r5 = r0.chatActivityEnterView;
        if (r5 == 0) goto L_0x2441;
    L_0x2436:
        r0 = r163;
        r5 = r0.chatActivityEnterView;
        r0 = r163;
        r6 = r0.info;
        r5.setChatInfo(r6);
    L_0x2441:
        r0 = r163;
        r5 = r0.mentionsAdapter;
        if (r5 == 0) goto L_0x2452;
    L_0x2447:
        r0 = r163;
        r5 = r0.mentionsAdapter;
        r0 = r163;
        r6 = r0.info;
        r5.setChatInfo(r6);
    L_0x2452:
        r5 = 3;
        r5 = r165[r5];
        r5 = r5 instanceof org.telegram.messenger.MessageObject;
        if (r5 == 0) goto L_0x24fa;
    L_0x2459:
        r5 = 3;
        r5 = r165[r5];
        r5 = (org.telegram.messenger.MessageObject) r5;
        r0 = r163;
        r0.pinnedMessageObject = r5;
        r5 = 0;
        r0 = r163;
        r0.updatePinnedMessageView(r5);
    L_0x2468:
        r0 = r163;
        r5 = r0.avatarContainer;
        if (r5 == 0) goto L_0x247c;
    L_0x246e:
        r0 = r163;
        r5 = r0.avatarContainer;
        r5.updateOnlineCount();
        r0 = r163;
        r5 = r0.avatarContainer;
        r5.updateSubtitle();
    L_0x247c:
        r0 = r163;
        r5 = r0.isBroadcast;
        if (r5 == 0) goto L_0x248d;
    L_0x2482:
        r5 = org.telegram.messenger.SendMessagesHelper.getInstance();
        r0 = r163;
        r6 = r0.info;
        r5.setCurrentChatInfo(r6);
    L_0x248d:
        r0 = r163;
        r5 = r0.info;
        r5 = r5 instanceof org.telegram.tgnet.TLRPC$TL_chatFull;
        if (r5 == 0) goto L_0x2579;
    L_0x2495:
        r5 = 0;
        r0 = r163;
        r0.hasBotsCommands = r5;
        r0 = r163;
        r5 = r0.botInfo;
        r5.clear();
        r5 = 0;
        r0 = r163;
        r0.botsCount = r5;
        r5 = 0;
        org.telegram.ui.Components.URLSpanBotCommand.enabled = r5;
        r4 = 0;
    L_0x24aa:
        r0 = r163;
        r5 = r0.info;
        r5 = r5.participants;
        r5 = r5.participants;
        r5 = r5.size();
        if (r4 >= r5) goto L_0x2502;
    L_0x24b8:
        r0 = r163;
        r5 = r0.info;
        r5 = r5.participants;
        r5 = r5.participants;
        r129 = r5.get(r4);
        r129 = (org.telegram.tgnet.TLRPC$ChatParticipant) r129;
        r5 = org.telegram.messenger.MessagesController.getInstance();
        r0 = r129;
        r6 = r0.user_id;
        r6 = java.lang.Integer.valueOf(r6);
        r156 = r5.getUser(r6);
        if (r156 == 0) goto L_0x24f7;
    L_0x24d8:
        r0 = r156;
        r5 = r0.bot;
        if (r5 == 0) goto L_0x24f7;
    L_0x24de:
        r5 = 1;
        org.telegram.ui.Components.URLSpanBotCommand.enabled = r5;
        r0 = r163;
        r5 = r0.botsCount;
        r5 = r5 + 1;
        r0 = r163;
        r0.botsCount = r5;
        r0 = r156;
        r5 = r0.id;
        r6 = 1;
        r0 = r163;
        r7 = r0.classGuid;
        org.telegram.messenger.query.BotQuery.loadBotInfo(r5, r6, r7);
    L_0x24f7:
        r4 = r4 + 1;
        goto L_0x24aa;
    L_0x24fa:
        r5 = 1;
        r0 = r163;
        r0.updatePinnedMessageView(r5);
        goto L_0x2468;
    L_0x2502:
        r0 = r163;
        r5 = r0.chatListView;
        if (r5 == 0) goto L_0x250f;
    L_0x2508:
        r0 = r163;
        r5 = r0.chatListView;
        r5.invalidateViews();
    L_0x250f:
        r0 = r163;
        r5 = r0.chatActivityEnterView;
        if (r5 == 0) goto L_0x2524;
    L_0x2515:
        r0 = r163;
        r5 = r0.chatActivityEnterView;
        r0 = r163;
        r6 = r0.botsCount;
        r0 = r163;
        r7 = r0.hasBotsCommands;
        r5.setBotsCount(r6, r7);
    L_0x2524:
        r0 = r163;
        r5 = r0.mentionsAdapter;
        if (r5 == 0) goto L_0x2535;
    L_0x252a:
        r0 = r163;
        r5 = r0.mentionsAdapter;
        r0 = r163;
        r6 = r0.botsCount;
        r5.setBotsCount(r6);
    L_0x2535:
        r0 = r163;
        r5 = r0.currentChat;
        r5 = org.telegram.messenger.ChatObject.isChannel(r5);
        if (r5 == 0) goto L_0x005b;
    L_0x253f:
        r0 = r163;
        r6 = r0.mergeDialogId;
        r12 = 0;
        r5 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r5 != 0) goto L_0x005b;
    L_0x2549:
        r0 = r163;
        r5 = r0.info;
        r5 = r5.migrated_from_chat_id;
        if (r5 == 0) goto L_0x005b;
    L_0x2551:
        r0 = r163;
        r5 = r0.info;
        r5 = r5.migrated_from_chat_id;
        r5 = -r5;
        r6 = (long) r5;
        r0 = r163;
        r0.mergeDialogId = r6;
        r0 = r163;
        r5 = r0.maxMessageId;
        r6 = 1;
        r0 = r163;
        r7 = r0.info;
        r7 = r7.migrated_from_max_id;
        r5[r6] = r7;
        r0 = r163;
        r5 = r0.chatAdapter;
        if (r5 == 0) goto L_0x005b;
    L_0x2570:
        r0 = r163;
        r5 = r0.chatAdapter;
        r5.notifyDataSetChanged();
        goto L_0x005b;
    L_0x2579:
        r0 = r163;
        r5 = r0.info;
        r5 = r5 instanceof org.telegram.tgnet.TLRPC$TL_channelFull;
        if (r5 == 0) goto L_0x250f;
    L_0x2581:
        r5 = 0;
        r0 = r163;
        r0.hasBotsCommands = r5;
        r0 = r163;
        r5 = r0.botInfo;
        r5.clear();
        r5 = 0;
        r0 = r163;
        r0.botsCount = r5;
        r0 = r163;
        r5 = r0.info;
        r5 = r5.bot_info;
        r5 = r5.isEmpty();
        if (r5 != 0) goto L_0x2611;
    L_0x259e:
        r0 = r163;
        r5 = r0.currentChat;
        if (r5 == 0) goto L_0x2611;
    L_0x25a4:
        r0 = r163;
        r5 = r0.currentChat;
        r5 = r5.megagroup;
        if (r5 == 0) goto L_0x2611;
    L_0x25ac:
        r5 = 1;
    L_0x25ad:
        org.telegram.ui.Components.URLSpanBotCommand.enabled = r5;
        r0 = r163;
        r5 = r0.info;
        r5 = r5.bot_info;
        r5 = r5.size();
        r0 = r163;
        r0.botsCount = r5;
        r4 = 0;
    L_0x25be:
        r0 = r163;
        r5 = r0.info;
        r5 = r5.bot_info;
        r5 = r5.size();
        if (r4 >= r5) goto L_0x2613;
    L_0x25ca:
        r0 = r163;
        r5 = r0.info;
        r5 = r5.bot_info;
        r32 = r5.get(r4);
        r32 = (org.telegram.tgnet.TLRPC$BotInfo) r32;
        r0 = r32;
        r5 = r0.commands;
        r5 = r5.isEmpty();
        if (r5 != 0) goto L_0x25fd;
    L_0x25e0:
        r0 = r163;
        r5 = r0.currentChat;
        r5 = org.telegram.messenger.ChatObject.isChannel(r5);
        if (r5 == 0) goto L_0x25f8;
    L_0x25ea:
        r0 = r163;
        r5 = r0.currentChat;
        if (r5 == 0) goto L_0x25fd;
    L_0x25f0:
        r0 = r163;
        r5 = r0.currentChat;
        r5 = r5.megagroup;
        if (r5 == 0) goto L_0x25fd;
    L_0x25f8:
        r5 = 1;
        r0 = r163;
        r0.hasBotsCommands = r5;
    L_0x25fd:
        r0 = r163;
        r5 = r0.botInfo;
        r0 = r32;
        r6 = r0.user_id;
        r6 = java.lang.Integer.valueOf(r6);
        r0 = r32;
        r5.put(r6, r0);
        r4 = r4 + 1;
        goto L_0x25be;
    L_0x2611:
        r5 = 0;
        goto L_0x25ad;
    L_0x2613:
        r0 = r163;
        r5 = r0.chatListView;
        if (r5 == 0) goto L_0x2620;
    L_0x2619:
        r0 = r163;
        r5 = r0.chatListView;
        r5.invalidateViews();
    L_0x2620:
        r0 = r163;
        r5 = r0.mentionsAdapter;
        if (r5 == 0) goto L_0x250f;
    L_0x2626:
        r0 = r163;
        r5 = r0.currentChat;
        r5 = org.telegram.messenger.ChatObject.isChannel(r5);
        if (r5 == 0) goto L_0x263e;
    L_0x2630:
        r0 = r163;
        r5 = r0.currentChat;
        if (r5 == 0) goto L_0x250f;
    L_0x2636:
        r0 = r163;
        r5 = r0.currentChat;
        r5 = r5.megagroup;
        if (r5 == 0) goto L_0x250f;
    L_0x263e:
        r0 = r163;
        r5 = r0.mentionsAdapter;
        r0 = r163;
        r6 = r0.botInfo;
        r5.setBotInfo(r6);
        goto L_0x250f;
    L_0x264b:
        r5 = org.telegram.messenger.NotificationCenter.chatInfoCantLoad;
        r0 = r164;
        if (r0 != r5) goto L_0x2711;
    L_0x2651:
        r5 = 0;
        r5 = r165[r5];
        r5 = (java.lang.Integer) r5;
        r46 = r5.intValue();
        r0 = r163;
        r5 = r0.currentChat;
        if (r5 == 0) goto L_0x005b;
    L_0x2660:
        r0 = r163;
        r5 = r0.currentChat;
        r5 = r5.id;
        r0 = r46;
        if (r5 != r0) goto L_0x005b;
    L_0x266a:
        r5 = 1;
        r5 = r165[r5];
        r5 = (java.lang.Integer) r5;
        r137 = r5.intValue();
        r5 = r163.getParentActivity();
        if (r5 == 0) goto L_0x005b;
    L_0x2679:
        r0 = r163;
        r5 = r0.closeChatDialog;
        if (r5 != 0) goto L_0x005b;
    L_0x267f:
        r34 = new org.telegram.ui.ActionBar.AlertDialog$Builder;
        r5 = r163.getParentActivity();
        r0 = r34;
        r0.<init>(r5);
        r5 = org.telegram.messenger.ApplicationLoader.getConfig();
        r5 = r5.getAppName();
        r0 = r34;
        r0.setTitle(r5);
        if (r137 != 0) goto L_0x26e7;
    L_0x2699:
        r5 = "ChannelCantOpenPrivate";
        r6 = 2131558682; // 0x7f0d011a float:1.8742687E38 double:1.053129917E-314;
        r5 = org.telegram.messenger.LocaleController.getString(r5, r6);
        r0 = r34;
        r0.setMessage(r5);
    L_0x26a8:
        r5 = "OK";
        r6 = 2131559582; // 0x7f0d049e float:1.8744512E38 double:1.0531303615E-314;
        r5 = org.telegram.messenger.LocaleController.getString(r5, r6);
        r6 = 0;
        r0 = r34;
        r0.setPositiveButton(r5, r6);
        r5 = r34.create();
        r0 = r163;
        r0.closeChatDialog = r5;
        r0 = r163;
        r0.showDialog(r5);
        r5 = 0;
        r0 = r163;
        r0.loading = r5;
        r0 = r163;
        r5 = r0.progressView;
        if (r5 == 0) goto L_0x26d8;
    L_0x26d0:
        r0 = r163;
        r5 = r0.progressView;
        r6 = 4;
        r5.setVisibility(r6);
    L_0x26d8:
        r0 = r163;
        r5 = r0.chatAdapter;
        if (r5 == 0) goto L_0x005b;
    L_0x26de:
        r0 = r163;
        r5 = r0.chatAdapter;
        r5.notifyDataSetChanged();
        goto L_0x005b;
    L_0x26e7:
        r5 = 1;
        r0 = r137;
        if (r0 != r5) goto L_0x26fc;
    L_0x26ec:
        r5 = "ChannelCantOpenNa";
        r6 = 2131558681; // 0x7f0d0119 float:1.8742685E38 double:1.0531299164E-314;
        r5 = org.telegram.messenger.LocaleController.getString(r5, r6);
        r0 = r34;
        r0.setMessage(r5);
        goto L_0x26a8;
    L_0x26fc:
        r5 = 2;
        r0 = r137;
        if (r0 != r5) goto L_0x26a8;
    L_0x2701:
        r5 = "ChannelCantOpenBanned";
        r6 = 2131558680; // 0x7f0d0118 float:1.8742683E38 double:1.053129916E-314;
        r5 = org.telegram.messenger.LocaleController.getString(r5, r6);
        r0 = r34;
        r0.setMessage(r5);
        goto L_0x26a8;
    L_0x2711:
        r5 = org.telegram.messenger.NotificationCenter.contactsDidLoaded;
        r0 = r164;
        if (r0 != r5) goto L_0x2732;
    L_0x2717:
        r163.updateContactStatus();
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        if (r5 == 0) goto L_0x2723;
    L_0x2720:
        r163.updateSpamView();
    L_0x2723:
        r0 = r163;
        r5 = r0.avatarContainer;
        if (r5 == 0) goto L_0x005b;
    L_0x2729:
        r0 = r163;
        r5 = r0.avatarContainer;
        r5.updateSubtitle();
        goto L_0x005b;
    L_0x2732:
        r5 = org.telegram.messenger.NotificationCenter.encryptedChatUpdated;
        r0 = r164;
        if (r0 != r5) goto L_0x27d0;
    L_0x2738:
        r5 = 0;
        r44 = r165[r5];
        r44 = (org.telegram.tgnet.TLRPC$EncryptedChat) r44;
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        if (r5 == 0) goto L_0x005b;
    L_0x2743:
        r0 = r44;
        r5 = r0.id;
        r0 = r163;
        r6 = r0.currentEncryptedChat;
        r6 = r6.id;
        if (r5 != r6) goto L_0x005b;
    L_0x274f:
        r0 = r44;
        r1 = r163;
        r1.currentEncryptedChat = r0;
        r163.updateContactStatus();
        r163.updateSecretStatus();
        r163.initStickers();
        r0 = r163;
        r5 = r0.chatActivityEnterView;
        if (r5 == 0) goto L_0x279c;
    L_0x2764:
        r0 = r163;
        r7 = r0.chatActivityEnterView;
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        if (r5 == 0) goto L_0x277c;
    L_0x276e:
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        r5 = r5.layer;
        r5 = org.telegram.messenger.AndroidUtilities.getPeerLayerVersion(r5);
        r6 = 23;
        if (r5 < r6) goto L_0x27ca;
    L_0x277c:
        r5 = 1;
    L_0x277d:
        r0 = r163;
        r6 = r0.currentEncryptedChat;
        if (r6 == 0) goto L_0x2791;
    L_0x2783:
        r0 = r163;
        r6 = r0.currentEncryptedChat;
        r6 = r6.layer;
        r6 = org.telegram.messenger.AndroidUtilities.getPeerLayerVersion(r6);
        r8 = 46;
        if (r6 < r8) goto L_0x27cc;
    L_0x2791:
        r6 = 1;
    L_0x2792:
        r7.setAllowStickersAndGifs(r5, r6);
        r0 = r163;
        r5 = r0.chatActivityEnterView;
        r5.checkRoundVideo();
    L_0x279c:
        r0 = r163;
        r5 = r0.mentionsAdapter;
        if (r5 == 0) goto L_0x005b;
    L_0x27a2:
        r0 = r163;
        r6 = r0.mentionsAdapter;
        r0 = r163;
        r5 = r0.chatActivityEnterView;
        r5 = r5.isEditingMessage();
        if (r5 != 0) goto L_0x27ce;
    L_0x27b0:
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        if (r5 == 0) goto L_0x27c4;
    L_0x27b6:
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        r5 = r5.layer;
        r5 = org.telegram.messenger.AndroidUtilities.getPeerLayerVersion(r5);
        r7 = 46;
        if (r5 < r7) goto L_0x27ce;
    L_0x27c4:
        r5 = 1;
    L_0x27c5:
        r6.setNeedBotContext(r5);
        goto L_0x005b;
    L_0x27ca:
        r5 = 0;
        goto L_0x277d;
    L_0x27cc:
        r6 = 0;
        goto L_0x2792;
    L_0x27ce:
        r5 = 0;
        goto L_0x27c5;
    L_0x27d0:
        r5 = org.telegram.messenger.NotificationCenter.messagesReadEncrypted;
        r0 = r164;
        if (r0 != r5) goto L_0x2833;
    L_0x27d6:
        r5 = 0;
        r5 = r165[r5];
        r5 = (java.lang.Integer) r5;
        r62 = r5.intValue();
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        if (r5 == 0) goto L_0x005b;
    L_0x27e5:
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        r5 = r5.id;
        r0 = r62;
        if (r5 != r0) goto L_0x005b;
    L_0x27ef:
        r5 = 1;
        r5 = r165[r5];
        r5 = (java.lang.Integer) r5;
        r54 = r5.intValue();
        r0 = r163;
        r5 = r0.messages;
        r5 = r5.iterator();
    L_0x2800:
        r6 = r5.hasNext();
        if (r6 == 0) goto L_0x281e;
    L_0x2806:
        r121 = r5.next();
        r121 = (org.telegram.messenger.MessageObject) r121;
        r6 = r121.isOut();
        if (r6 == 0) goto L_0x2800;
    L_0x2812:
        r6 = r121.isOut();
        if (r6 == 0) goto L_0x2823;
    L_0x2818:
        r6 = r121.isUnread();
        if (r6 != 0) goto L_0x2823;
    L_0x281e:
        r163.updateVisibleRows();
        goto L_0x005b;
    L_0x2823:
        r0 = r121;
        r6 = r0.messageOwner;
        r6 = r6.date;
        r6 = r6 + -1;
        r0 = r54;
        if (r6 > r0) goto L_0x2800;
    L_0x282f:
        r121.setIsRead();
        goto L_0x2800;
    L_0x2833:
        r5 = org.telegram.messenger.NotificationCenter.removeAllMessagesFromDialog;
        r0 = r164;
        if (r0 != r5) goto L_0x29f3;
    L_0x2839:
        r5 = 0;
        r5 = r165[r5];
        r5 = (java.lang.Long) r5;
        r60 = r5.longValue();
        r0 = r163;
        r6 = r0.dialog_id;
        r5 = (r6 > r60 ? 1 : (r6 == r60 ? 0 : -1));
        if (r5 != 0) goto L_0x005b;
    L_0x284a:
        r0 = r163;
        r5 = r0.messages;
        r5.clear();
        r0 = r163;
        r5 = r0.waitingForLoad;
        r5.clear();
        r0 = r163;
        r5 = r0.messagesByDays;
        r5.clear();
        r0 = r163;
        r5 = r0.groupedMessagesMap;
        r5.clear();
        r4 = 1;
    L_0x2867:
        if (r4 < 0) goto L_0x28c8;
    L_0x2869:
        r0 = r163;
        r5 = r0.messagesDict;
        r5 = r5[r4];
        r5.clear();
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        if (r5 != 0) goto L_0x28b6;
    L_0x2878:
        r0 = r163;
        r5 = r0.maxMessageId;
        r6 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r5[r4] = r6;
        r0 = r163;
        r5 = r0.minMessageId;
        r6 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r5[r4] = r6;
    L_0x2889:
        r0 = r163;
        r5 = r0.maxDate;
        r6 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r5[r4] = r6;
        r0 = r163;
        r5 = r0.minDate;
        r6 = 0;
        r5[r4] = r6;
        r0 = r163;
        r5 = r0.selectedMessagesIds;
        r5 = r5[r4];
        r5.clear();
        r0 = r163;
        r5 = r0.selectedMessagesCanCopyIds;
        r5 = r5[r4];
        r5.clear();
        r0 = r163;
        r5 = r0.selectedMessagesCanStarIds;
        r5 = r5[r4];
        r5.clear();
        r4 = r4 + -1;
        goto L_0x2867;
    L_0x28b6:
        r0 = r163;
        r5 = r0.maxMessageId;
        r6 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r5[r4] = r6;
        r0 = r163;
        r5 = r0.minMessageId;
        r6 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r5[r4] = r6;
        goto L_0x2889;
    L_0x28c8:
        r5 = 0;
        r0 = r163;
        r0.cantDeleteMessagesCount = r5;
        r5 = 0;
        r0 = r163;
        r0.canEditMessagesCount = r5;
        r0 = r163;
        r5 = r0.actionBar;
        r5.hideActionMode();
        r5 = 1;
        r0 = r163;
        r0.updatePinnedMessageView(r5);
        r0 = r163;
        r5 = r0.botButtons;
        if (r5 == 0) goto L_0x28f9;
    L_0x28e5:
        r5 = 0;
        r0 = r163;
        r0.botButtons = r5;
        r0 = r163;
        r5 = r0.chatActivityEnterView;
        if (r5 == 0) goto L_0x28f9;
    L_0x28f0:
        r0 = r163;
        r5 = r0.chatActivityEnterView;
        r6 = 0;
        r7 = 0;
        r5.setButtons(r6, r7);
    L_0x28f9:
        r5 = 1;
        r5 = r165[r5];
        r5 = (java.lang.Boolean) r5;
        r5 = r5.booleanValue();
        if (r5 == 0) goto L_0x29d9;
    L_0x2904:
        r0 = r163;
        r5 = r0.chatAdapter;
        if (r5 == 0) goto L_0x2925;
    L_0x290a:
        r0 = r163;
        r6 = r0.progressView;
        r0 = r163;
        r5 = r0.chatAdapter;
        r5 = r5.botInfoRow;
        r7 = -1;
        if (r5 != r7) goto L_0x2941;
    L_0x2919:
        r5 = 0;
    L_0x291a:
        r6.setVisibility(r5);
        r0 = r163;
        r5 = r0.chatListView;
        r6 = 0;
        r5.setEmptyView(r6);
    L_0x2925:
        r4 = 0;
    L_0x2926:
        r5 = 2;
        if (r4 >= r5) goto L_0x2943;
    L_0x2929:
        r0 = r163;
        r5 = r0.endReached;
        r6 = 0;
        r5[r4] = r6;
        r0 = r163;
        r5 = r0.cacheEndReached;
        r6 = 0;
        r5[r4] = r6;
        r0 = r163;
        r5 = r0.forwardEndReached;
        r6 = 1;
        r5[r4] = r6;
        r4 = r4 + 1;
        goto L_0x2926;
    L_0x2941:
        r5 = 4;
        goto L_0x291a;
    L_0x2943:
        r5 = 1;
        r0 = r163;
        r0.first = r5;
        r5 = 1;
        r0 = r163;
        r0.firstLoading = r5;
        r5 = 1;
        r0 = r163;
        r0.loading = r5;
        r5 = 0;
        r0 = r163;
        r0.startLoadFromMessageId = r5;
        r5 = 0;
        r0 = r163;
        r0.needSelectFromMessageId = r5;
        r0 = r163;
        r5 = r0.waitingForLoad;
        r0 = r163;
        r6 = r0.lastLoadIndex;
        r6 = java.lang.Integer.valueOf(r6);
        r5.add(r6);
        r11 = org.telegram.messenger.MessagesController.getInstance();
        r0 = r163;
        r12 = r0.dialog_id;
        r5 = org.telegram.messenger.AndroidUtilities.isTablet();
        if (r5 == 0) goto L_0x29d6;
    L_0x2979:
        r14 = 30;
    L_0x297b:
        r15 = 0;
        r16 = 0;
        r17 = 1;
        r18 = 0;
        r0 = r163;
        r0 = r0.classGuid;
        r19 = r0;
        r20 = 2;
        r21 = 0;
        r0 = r163;
        r5 = r0.currentChat;
        r22 = org.telegram.messenger.ChatObject.isChannel(r5);
        r0 = r163;
        r0 = r0.lastLoadIndex;
        r23 = r0;
        r5 = r23 + 1;
        r0 = r163;
        r0.lastLoadIndex = r5;
        r11.loadMessages(r12, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23);
    L_0x29a3:
        r0 = r163;
        r5 = r0.chatAdapter;
        if (r5 == 0) goto L_0x29b0;
    L_0x29a9:
        r0 = r163;
        r5 = r0.chatAdapter;
        r5.notifyDataSetChanged();
    L_0x29b0:
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        if (r5 != 0) goto L_0x005b;
    L_0x29b6:
        r0 = r163;
        r5 = r0.currentUser;
        if (r5 == 0) goto L_0x005b;
    L_0x29bc:
        r0 = r163;
        r5 = r0.currentUser;
        r5 = r5.bot;
        if (r5 == 0) goto L_0x005b;
    L_0x29c4:
        r0 = r163;
        r5 = r0.botUser;
        if (r5 != 0) goto L_0x005b;
    L_0x29ca:
        r5 = "";
        r0 = r163;
        r0.botUser = r5;
        r163.updateBottomOverlay();
        goto L_0x005b;
    L_0x29d6:
        r14 = 20;
        goto L_0x297b;
    L_0x29d9:
        r0 = r163;
        r5 = r0.progressView;
        if (r5 == 0) goto L_0x29a3;
    L_0x29df:
        r0 = r163;
        r5 = r0.progressView;
        r6 = 4;
        r5.setVisibility(r6);
        r0 = r163;
        r5 = r0.chatListView;
        r0 = r163;
        r6 = r0.emptyViewContainer;
        r5.setEmptyView(r6);
        goto L_0x29a3;
    L_0x29f3:
        r5 = org.telegram.messenger.NotificationCenter.screenshotTook;
        r0 = r164;
        if (r0 != r5) goto L_0x29fe;
    L_0x29f9:
        r163.updateInformationForScreenshotDetector();
        goto L_0x005b;
    L_0x29fe:
        r5 = org.telegram.messenger.NotificationCenter.blockedUsersDidLoaded;
        r0 = r164;
        if (r0 != r5) goto L_0x2a35;
    L_0x2a04:
        r0 = r163;
        r5 = r0.currentUser;
        if (r5 == 0) goto L_0x005b;
    L_0x2a0a:
        r0 = r163;
        r0 = r0.userBlocked;
        r127 = r0;
        r5 = org.telegram.messenger.MessagesController.getInstance();
        r5 = r5.blockedUsers;
        r0 = r163;
        r6 = r0.currentUser;
        r6 = r6.id;
        r6 = java.lang.Integer.valueOf(r6);
        r5 = r5.contains(r6);
        r0 = r163;
        r0.userBlocked = r5;
        r0 = r163;
        r5 = r0.userBlocked;
        r0 = r127;
        if (r0 == r5) goto L_0x005b;
    L_0x2a30:
        r163.updateBottomOverlay();
        goto L_0x005b;
    L_0x2a35:
        r5 = org.telegram.messenger.NotificationCenter.FileNewChunkAvailable;
        r0 = r164;
        if (r0 != r5) goto L_0x2a84;
    L_0x2a3b:
        r5 = 0;
        r106 = r165[r5];
        r106 = (org.telegram.messenger.MessageObject) r106;
        r5 = 2;
        r5 = r165[r5];
        r5 = (java.lang.Long) r5;
        r66 = r5.longValue();
        r6 = 0;
        r5 = (r66 > r6 ? 1 : (r66 == r6 ? 0 : -1));
        if (r5 == 0) goto L_0x005b;
    L_0x2a4f:
        r0 = r163;
        r6 = r0.dialog_id;
        r12 = r106.getDialogId();
        r5 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r5 != 0) goto L_0x005b;
    L_0x2a5b:
        r0 = r163;
        r5 = r0.messagesDict;
        r6 = 0;
        r5 = r5[r6];
        r6 = r106.getId();
        r6 = java.lang.Integer.valueOf(r6);
        r52 = r5.get(r6);
        r52 = (org.telegram.messenger.MessageObject) r52;
        if (r52 == 0) goto L_0x005b;
    L_0x2a72:
        r0 = r52;
        r5 = r0.messageOwner;
        r5 = r5.media;
        r5 = r5.document;
        r0 = r66;
        r6 = (int) r0;
        r5.size = r6;
        r163.updateVisibleRows();
        goto L_0x005b;
    L_0x2a84:
        r5 = org.telegram.messenger.NotificationCenter.didCreatedNewDeleteTask;
        r0 = r164;
        if (r0 != r5) goto L_0x2b10;
    L_0x2a8a:
        r5 = 0;
        r109 = r165[r5];
        r109 = (android.util.SparseArray) r109;
        r39 = 0;
        r76 = 0;
    L_0x2a93:
        r5 = r109.size();
        r0 = r76;
        if (r0 >= r5) goto L_0x2b09;
    L_0x2a9b:
        r0 = r109;
        r1 = r76;
        r85 = r0.keyAt(r1);
        r0 = r109;
        r1 = r85;
        r25 = r0.get(r1);
        r25 = (java.util.ArrayList) r25;
        r4 = 0;
    L_0x2aae:
        r5 = r25.size();
        if (r4 >= r5) goto L_0x2b06;
    L_0x2ab4:
        r0 = r25;
        r5 = r0.get(r4);
        r5 = (java.lang.Long) r5;
        r110 = r5.longValue();
        if (r4 != 0) goto L_0x2ae1;
    L_0x2ac2:
        r5 = 32;
        r6 = r110 >> r5;
        r0 = (int) r6;
        r41 = r0;
        if (r41 >= 0) goto L_0x2acd;
    L_0x2acb:
        r41 = 0;
    L_0x2acd:
        r0 = r163;
        r5 = r0.currentChat;
        r5 = org.telegram.messenger.ChatObject.isChannel(r5);
        if (r5 == 0) goto L_0x2b04;
    L_0x2ad7:
        r0 = r163;
        r5 = r0.currentChat;
        r5 = r5.id;
    L_0x2add:
        r0 = r41;
        if (r0 != r5) goto L_0x005b;
    L_0x2ae1:
        r0 = r163;
        r5 = r0.messagesDict;
        r6 = 0;
        r5 = r5[r6];
        r0 = r110;
        r6 = (int) r0;
        r6 = java.lang.Integer.valueOf(r6);
        r106 = r5.get(r6);
        r106 = (org.telegram.messenger.MessageObject) r106;
        if (r106 == 0) goto L_0x2b01;
    L_0x2af7:
        r0 = r106;
        r5 = r0.messageOwner;
        r0 = r85;
        r5.destroyTime = r0;
        r39 = 1;
    L_0x2b01:
        r4 = r4 + 1;
        goto L_0x2aae;
    L_0x2b04:
        r5 = 0;
        goto L_0x2add;
    L_0x2b06:
        r76 = r76 + 1;
        goto L_0x2a93;
    L_0x2b09:
        if (r39 == 0) goto L_0x005b;
    L_0x2b0b:
        r163.updateVisibleRows();
        goto L_0x005b;
    L_0x2b10:
        r5 = org.telegram.messenger.NotificationCenter.messagePlayingDidStarted;
        r0 = r164;
        if (r0 != r5) goto L_0x2bd3;
    L_0x2b16:
        r5 = 0;
        r106 = r165[r5];
        r106 = (org.telegram.messenger.MessageObject) r106;
        r0 = r106;
        r6 = r0.eventId;
        r12 = 0;
        r5 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r5 != 0) goto L_0x005b;
    L_0x2b25:
        r0 = r163;
        r1 = r106;
        r0.sendSecretMessageRead(r1);
        r5 = r106.isRoundVideo();
        if (r5 == 0) goto L_0x2b4c;
    L_0x2b32:
        r5 = org.telegram.messenger.MediaController.getInstance();
        r6 = 1;
        r0 = r163;
        r6 = r0.createTextureView(r6);
        r0 = r163;
        r7 = r0.aspectRatioFrameLayout;
        r0 = r163;
        r8 = r0.roundVideoContainer;
        r11 = 1;
        r5.setTextureView(r6, r7, r8, r11);
        r163.updateTextureViewPosition();
    L_0x2b4c:
        r0 = r163;
        r5 = r0.chatListView;
        if (r5 == 0) goto L_0x005b;
    L_0x2b52:
        r0 = r163;
        r5 = r0.chatListView;
        r47 = r5.getChildCount();
        r4 = 0;
    L_0x2b5b:
        r0 = r47;
        if (r4 >= r0) goto L_0x2b99;
    L_0x2b5f:
        r0 = r163;
        r5 = r0.chatListView;
        r158 = r5.getChildAt(r4);
        r0 = r158;
        r5 = r0 instanceof org.telegram.ui.Cells.ChatMessageCell;
        if (r5 == 0) goto L_0x2b89;
    L_0x2b6d:
        r38 = r158;
        r38 = (org.telegram.ui.Cells.ChatMessageCell) r38;
        r107 = r38.getMessageObject();
        if (r107 == 0) goto L_0x2b89;
    L_0x2b77:
        r5 = r107.isVoice();
        if (r5 != 0) goto L_0x2b83;
    L_0x2b7d:
        r5 = r107.isMusic();
        if (r5 == 0) goto L_0x2b8c;
    L_0x2b83:
        r5 = 0;
        r0 = r38;
        r0.updateButtonState(r5);
    L_0x2b89:
        r4 = r4 + 1;
        goto L_0x2b5b;
    L_0x2b8c:
        r5 = r107.isRoundVideo();
        if (r5 == 0) goto L_0x2b89;
    L_0x2b92:
        r5 = 0;
        r0 = r38;
        r0.checkRoundVideoPlayback(r5);
        goto L_0x2b89;
    L_0x2b99:
        r0 = r163;
        r5 = r0.mentionListView;
        r47 = r5.getChildCount();
        r4 = 0;
    L_0x2ba2:
        r0 = r47;
        if (r4 >= r0) goto L_0x005b;
    L_0x2ba6:
        r0 = r163;
        r5 = r0.mentionListView;
        r158 = r5.getChildAt(r4);
        r0 = r158;
        r5 = r0 instanceof org.telegram.ui.Cells.ContextLinkCell;
        if (r5 == 0) goto L_0x2bd0;
    L_0x2bb4:
        r38 = r158;
        r38 = (org.telegram.ui.Cells.ContextLinkCell) r38;
        r107 = r38.getMessageObject();
        if (r107 == 0) goto L_0x2bd0;
    L_0x2bbe:
        r5 = r107.isVoice();
        if (r5 != 0) goto L_0x2bca;
    L_0x2bc4:
        r5 = r107.isMusic();
        if (r5 == 0) goto L_0x2bd0;
    L_0x2bca:
        r5 = 0;
        r0 = r38;
        r0.updateButtonState(r5);
    L_0x2bd0:
        r4 = r4 + 1;
        goto L_0x2ba2;
    L_0x2bd3:
        r5 = org.telegram.messenger.NotificationCenter.messagePlayingDidReset;
        r0 = r164;
        if (r0 == r5) goto L_0x2bdf;
    L_0x2bd9:
        r5 = org.telegram.messenger.NotificationCenter.messagePlayingPlayStateChanged;
        r0 = r164;
        if (r0 != r5) goto L_0x2c7b;
    L_0x2bdf:
        r5 = org.telegram.messenger.NotificationCenter.messagePlayingDidReset;
        r0 = r164;
        if (r0 != r5) goto L_0x2be8;
    L_0x2be5:
        r163.destroyTextureView();
    L_0x2be8:
        r0 = r163;
        r5 = r0.chatListView;
        if (r5 == 0) goto L_0x005b;
    L_0x2bee:
        r0 = r163;
        r5 = r0.chatListView;
        r47 = r5.getChildCount();
        r4 = 0;
    L_0x2bf7:
        r0 = r47;
        if (r4 >= r0) goto L_0x2c41;
    L_0x2bfb:
        r0 = r163;
        r5 = r0.chatListView;
        r158 = r5.getChildAt(r4);
        r0 = r158;
        r5 = r0 instanceof org.telegram.ui.Cells.ChatMessageCell;
        if (r5 == 0) goto L_0x2c25;
    L_0x2c09:
        r38 = r158;
        r38 = (org.telegram.ui.Cells.ChatMessageCell) r38;
        r106 = r38.getMessageObject();
        if (r106 == 0) goto L_0x2c25;
    L_0x2c13:
        r5 = r106.isVoice();
        if (r5 != 0) goto L_0x2c1f;
    L_0x2c19:
        r5 = r106.isMusic();
        if (r5 == 0) goto L_0x2c28;
    L_0x2c1f:
        r5 = 0;
        r0 = r38;
        r0.updateButtonState(r5);
    L_0x2c25:
        r4 = r4 + 1;
        goto L_0x2bf7;
    L_0x2c28:
        r5 = r106.isRoundVideo();
        if (r5 == 0) goto L_0x2c25;
    L_0x2c2e:
        r5 = org.telegram.messenger.MediaController.getInstance();
        r0 = r106;
        r5 = r5.isPlayingMessage(r0);
        if (r5 != 0) goto L_0x2c25;
    L_0x2c3a:
        r5 = 1;
        r0 = r38;
        r0.checkRoundVideoPlayback(r5);
        goto L_0x2c25;
    L_0x2c41:
        r0 = r163;
        r5 = r0.mentionListView;
        r47 = r5.getChildCount();
        r4 = 0;
    L_0x2c4a:
        r0 = r47;
        if (r4 >= r0) goto L_0x005b;
    L_0x2c4e:
        r0 = r163;
        r5 = r0.mentionListView;
        r158 = r5.getChildAt(r4);
        r0 = r158;
        r5 = r0 instanceof org.telegram.ui.Cells.ContextLinkCell;
        if (r5 == 0) goto L_0x2c78;
    L_0x2c5c:
        r38 = r158;
        r38 = (org.telegram.ui.Cells.ContextLinkCell) r38;
        r106 = r38.getMessageObject();
        if (r106 == 0) goto L_0x2c78;
    L_0x2c66:
        r5 = r106.isVoice();
        if (r5 != 0) goto L_0x2c72;
    L_0x2c6c:
        r5 = r106.isMusic();
        if (r5 == 0) goto L_0x2c78;
    L_0x2c72:
        r5 = 0;
        r0 = r38;
        r0.updateButtonState(r5);
    L_0x2c78:
        r4 = r4 + 1;
        goto L_0x2c4a;
    L_0x2c7b:
        r5 = org.telegram.messenger.NotificationCenter.messagePlayingProgressDidChanged;
        r0 = r164;
        if (r0 != r5) goto L_0x2cdd;
    L_0x2c81:
        r5 = 0;
        r110 = r165[r5];
        r110 = (java.lang.Integer) r110;
        r0 = r163;
        r5 = r0.chatListView;
        if (r5 == 0) goto L_0x005b;
    L_0x2c8c:
        r0 = r163;
        r5 = r0.chatListView;
        r47 = r5.getChildCount();
        r4 = 0;
    L_0x2c95:
        r0 = r47;
        if (r4 >= r0) goto L_0x005b;
    L_0x2c99:
        r0 = r163;
        r5 = r0.chatListView;
        r158 = r5.getChildAt(r4);
        r0 = r158;
        r5 = r0 instanceof org.telegram.ui.Cells.ChatMessageCell;
        if (r5 == 0) goto L_0x2cda;
    L_0x2ca7:
        r38 = r158;
        r38 = (org.telegram.ui.Cells.ChatMessageCell) r38;
        r132 = r38.getMessageObject();
        if (r132 == 0) goto L_0x2cda;
    L_0x2cb1:
        r5 = r132.getId();
        r6 = r110.intValue();
        if (r5 != r6) goto L_0x2cda;
    L_0x2cbb:
        r5 = org.telegram.messenger.MediaController.getInstance();
        r131 = r5.getPlayingMessageObject();
        if (r131 == 0) goto L_0x005b;
    L_0x2cc5:
        r0 = r131;
        r5 = r0.audioProgress;
        r0 = r132;
        r0.audioProgress = r5;
        r0 = r131;
        r5 = r0.audioProgressSec;
        r0 = r132;
        r0.audioProgressSec = r5;
        r38.updatePlayingMessageProgress();
        goto L_0x005b;
    L_0x2cda:
        r4 = r4 + 1;
        goto L_0x2c95;
    L_0x2cdd:
        r5 = org.telegram.messenger.NotificationCenter.updateMessageMedia;
        r0 = r164;
        if (r0 != r5) goto L_0x2e79;
    L_0x2ce3:
        r5 = 0;
        r103 = r165[r5];
        r103 = (org.telegram.tgnet.TLRPC$Message) r103;
        r0 = r163;
        r5 = r0.messagesDict;
        r6 = 0;
        r5 = r5[r6];
        r0 = r103;
        r6 = r0.id;
        r6 = java.lang.Integer.valueOf(r6);
        r65 = r5.get(r6);
        r65 = (org.telegram.messenger.MessageObject) r65;
        if (r65 == 0) goto L_0x005b;
    L_0x2cff:
        r0 = r65;
        r5 = r0.messageOwner;
        r0 = r103;
        r6 = r0.media;
        r5.media = r6;
        r0 = r65;
        r5 = r0.messageOwner;
        r0 = r103;
        r6 = r0.attachPath;
        r5.attachPath = r6;
        r5 = 0;
        r0 = r65;
        r0.generateThumbs(r5);
        r6 = r65.getGroupId();
        r12 = 0;
        r5 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r5 == 0) goto L_0x2e21;
    L_0x2d23:
        r0 = r65;
        r5 = r0.photoThumbs;
        if (r5 == 0) goto L_0x2d33;
    L_0x2d29:
        r0 = r65;
        r5 = r0.photoThumbs;
        r5 = r5.isEmpty();
        if (r5 == 0) goto L_0x2e21;
    L_0x2d33:
        r0 = r163;
        r5 = r0.groupedMessagesMap;
        r6 = r65.getGroupId();
        r6 = java.lang.Long.valueOf(r6);
        r72 = r5.get(r6);
        r72 = (org.telegram.messenger.MessageObject.GroupedMessages) r72;
        if (r72 == 0) goto L_0x2e21;
    L_0x2d47:
        r0 = r72;
        r5 = r0.messages;
        r0 = r65;
        r78 = r5.indexOf(r0);
        if (r78 < 0) goto L_0x2e21;
    L_0x2d53:
        r0 = r72;
        r5 = r0.messages;
        r151 = r5.size();
        r106 = 0;
        if (r78 <= 0) goto L_0x2de9;
    L_0x2d5f:
        r0 = r72;
        r5 = r0.messages;
        r5 = r5.size();
        r5 = r5 + -1;
        r0 = r78;
        if (r0 >= r5) goto L_0x2de9;
    L_0x2d6d:
        r144 = new org.telegram.messenger.MessageObject$GroupedMessages;
        r144.<init>();
        r5 = org.telegram.messenger.Utilities.random;
        r6 = r5.nextLong();
        r0 = r144;
        r0.groupId = r6;
        r0 = r144;
        r5 = r0.messages;
        r0 = r72;
        r6 = r0.messages;
        r7 = r78 + 1;
        r0 = r72;
        r8 = r0.messages;
        r8 = r8.size();
        r6 = r6.subList(r7, r8);
        r5.addAll(r6);
        r31 = 0;
    L_0x2d97:
        r0 = r144;
        r5 = r0.messages;
        r5 = r5.size();
        r0 = r31;
        if (r0 >= r5) goto L_0x2dc1;
    L_0x2da3:
        r0 = r144;
        r5 = r0.messages;
        r0 = r31;
        r5 = r5.get(r0);
        r5 = (org.telegram.messenger.MessageObject) r5;
        r0 = r144;
        r6 = r0.groupId;
        r5.localGroupId = r6;
        r0 = r72;
        r5 = r0.messages;
        r6 = r78 + 1;
        r5.remove(r6);
        r31 = r31 + 1;
        goto L_0x2d97;
    L_0x2dc1:
        r0 = r163;
        r5 = r0.groupedMessagesMap;
        r0 = r144;
        r6 = r0.groupId;
        r6 = java.lang.Long.valueOf(r6);
        r0 = r144;
        r5.put(r6, r0);
        r0 = r144;
        r5 = r0.messages;
        r0 = r144;
        r6 = r0.messages;
        r6 = r6.size();
        r6 = r6 + -1;
        r106 = r5.get(r6);
        r106 = (org.telegram.messenger.MessageObject) r106;
        r144.calculate();
    L_0x2de9:
        r0 = r72;
        r5 = r0.messages;
        r0 = r78;
        r5.remove(r0);
        if (r106 != 0) goto L_0x2e08;
    L_0x2df4:
        r0 = r72;
        r5 = r0.messages;
        r0 = r72;
        r6 = r0.messages;
        r6 = r6.size();
        r6 = r6 + -1;
        r106 = r5.get(r6);
        r106 = (org.telegram.messenger.MessageObject) r106;
    L_0x2e08:
        r0 = r72;
        r5 = r0.messages;
        r5 = r5.isEmpty();
        if (r5 == 0) goto L_0x2e4b;
    L_0x2e12:
        r0 = r163;
        r5 = r0.groupedMessagesMap;
        r0 = r72;
        r6 = r0.groupId;
        r6 = java.lang.Long.valueOf(r6);
        r5.remove(r6);
    L_0x2e21:
        r0 = r103;
        r5 = r0.media;
        r5 = r5.ttl_seconds;
        if (r5 == 0) goto L_0x2e74;
    L_0x2e29:
        r0 = r103;
        r5 = r0.media;
        r5 = r5.photo;
        r5 = r5 instanceof org.telegram.tgnet.TLRPC$TL_photoEmpty;
        if (r5 != 0) goto L_0x2e3d;
    L_0x2e33:
        r0 = r103;
        r5 = r0.media;
        r5 = r5.document;
        r5 = r5 instanceof org.telegram.tgnet.TLRPC$TL_documentEmpty;
        if (r5 == 0) goto L_0x2e74;
    L_0x2e3d:
        r65.setType();
        r0 = r163;
        r5 = r0.chatAdapter;
        r0 = r65;
        r5.updateRowWithMessageObject(r0);
        goto L_0x005b;
    L_0x2e4b:
        r72.calculate();
        r0 = r163;
        r5 = r0.messages;
        r0 = r106;
        r80 = r5.indexOf(r0);
        if (r80 < 0) goto L_0x2e21;
    L_0x2e5a:
        r0 = r163;
        r5 = r0.chatAdapter;
        if (r5 == 0) goto L_0x2e21;
    L_0x2e60:
        r0 = r163;
        r5 = r0.chatAdapter;
        r0 = r163;
        r6 = r0.chatAdapter;
        r6 = r6.messagesStartRow;
        r6 = r6 + r80;
        r0 = r151;
        r5.notifyItemRangeChanged(r6, r0);
        goto L_0x2e21;
    L_0x2e74:
        r163.updateVisibleRows();
        goto L_0x005b;
    L_0x2e79:
        r5 = org.telegram.messenger.NotificationCenter.replaceMessagesObjects;
        r0 = r164;
        if (r0 != r5) goto L_0x31dc;
    L_0x2e7f:
        r5 = 0;
        r5 = r165[r5];
        r5 = (java.lang.Long) r5;
        r60 = r5.longValue();
        r0 = r163;
        r6 = r0.dialog_id;
        r5 = (r60 > r6 ? 1 : (r60 == r6 ? 0 : -1));
        if (r5 == 0) goto L_0x2e98;
    L_0x2e90:
        r0 = r163;
        r6 = r0.mergeDialogId;
        r5 = (r60 > r6 ? 1 : (r60 == r6 ? 0 : -1));
        if (r5 != 0) goto L_0x005b;
    L_0x2e98:
        r0 = r163;
        r6 = r0.dialog_id;
        r5 = (r60 > r6 ? 1 : (r60 == r6 ? 0 : -1));
        if (r5 != 0) goto L_0x304a;
    L_0x2ea0:
        r94 = 0;
    L_0x2ea2:
        r39 = 0;
        r101 = 0;
        r5 = 1;
        r108 = r165[r5];
        r108 = (java.util.ArrayList) r108;
        r116 = 0;
        r4 = 0;
    L_0x2eae:
        r5 = r108.size();
        if (r4 >= r5) goto L_0x314d;
    L_0x2eb4:
        r0 = r108;
        r106 = r0.get(r4);
        r106 = (org.telegram.messenger.MessageObject) r106;
        r0 = r163;
        r5 = r0.messagesDict;
        r5 = r5[r94];
        r6 = r106.getId();
        r6 = java.lang.Integer.valueOf(r6);
        r123 = r5.get(r6);
        r123 = (org.telegram.messenger.MessageObject) r123;
        r0 = r163;
        r5 = r0.pinnedMessageObject;
        if (r5 == 0) goto L_0x2ef0;
    L_0x2ed6:
        r0 = r163;
        r5 = r0.pinnedMessageObject;
        r5 = r5.getId();
        r6 = r106.getId();
        if (r5 != r6) goto L_0x2ef0;
    L_0x2ee4:
        r0 = r106;
        r1 = r163;
        r1.pinnedMessageObject = r0;
        r5 = 1;
        r0 = r163;
        r0.updatePinnedMessageView(r5);
    L_0x2ef0:
        if (r123 == 0) goto L_0x30d1;
    L_0x2ef2:
        r0 = r106;
        r5 = r0.type;
        if (r5 < 0) goto L_0x3060;
    L_0x2ef8:
        if (r101 != 0) goto L_0x2f06;
    L_0x2efa:
        r0 = r106;
        r5 = r0.messageOwner;
        r5 = r5.media;
        r5 = r5 instanceof org.telegram.tgnet.TLRPC$TL_messageMediaWebPage;
        if (r5 == 0) goto L_0x2f06;
    L_0x2f04:
        r101 = 1;
    L_0x2f06:
        r0 = r123;
        r5 = r0.replyMessageObject;
        if (r5 == 0) goto L_0x2f24;
    L_0x2f0c:
        r0 = r123;
        r5 = r0.replyMessageObject;
        r0 = r106;
        r0.replyMessageObject = r5;
        r0 = r106;
        r5 = r0.messageOwner;
        r5 = r5.action;
        r5 = r5 instanceof org.telegram.tgnet.TLRPC$TL_messageActionGameScore;
        if (r5 == 0) goto L_0x304e;
    L_0x2f1e:
        r5 = 0;
        r0 = r106;
        r0.generateGameMessageText(r5);
    L_0x2f24:
        r0 = r106;
        r5 = r0.messageOwner;
        r0 = r123;
        r6 = r0.messageOwner;
        r6 = r6.attachPath;
        r5.attachPath = r6;
        r0 = r123;
        r5 = r0.attachPathExists;
        r0 = r106;
        r0.attachPathExists = r5;
        r0 = r123;
        r5 = r0.mediaExists;
        r0 = r106;
        r0.mediaExists = r5;
        r0 = r163;
        r5 = r0.messagesDict;
        r5 = r5[r94];
        r6 = r123.getId();
        r6 = java.lang.Integer.valueOf(r6);
        r0 = r106;
        r5.put(r6, r0);
    L_0x2f53:
        r0 = r163;
        r5 = r0.messages;
        r0 = r123;
        r80 = r5.indexOf(r0);
        if (r80 < 0) goto L_0x30d1;
    L_0x2f5f:
        r0 = r163;
        r5 = r0.messagesByDays;
        r0 = r123;
        r6 = r0.dateKey;
        r57 = r5.get(r6);
        r57 = (java.util.ArrayList) r57;
        r81 = -1;
        if (r57 == 0) goto L_0x2f79;
    L_0x2f71:
        r0 = r57;
        r1 = r123;
        r81 = r0.indexOf(r1);
    L_0x2f79:
        r6 = r123.getGroupId();
        r12 = 0;
        r5 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r5 == 0) goto L_0x309c;
    L_0x2f83:
        r0 = r163;
        r5 = r0.groupedMessagesMap;
        r6 = r123.getGroupId();
        r6 = java.lang.Long.valueOf(r6);
        r72 = r5.get(r6);
        r72 = (org.telegram.messenger.MessageObject.GroupedMessages) r72;
        if (r72 == 0) goto L_0x309c;
    L_0x2f97:
        r0 = r72;
        r5 = r0.messages;
        r0 = r123;
        r78 = r5.indexOf(r0);
        if (r78 < 0) goto L_0x309c;
    L_0x2fa3:
        r6 = r123.getGroupId();
        r12 = r106.getGroupId();
        r5 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r5 == 0) goto L_0x2fc0;
    L_0x2faf:
        r0 = r163;
        r5 = r0.groupedMessagesMap;
        r6 = r106.getGroupId();
        r6 = java.lang.Long.valueOf(r6);
        r0 = r72;
        r5.put(r6, r0);
    L_0x2fc0:
        r0 = r106;
        r5 = r0.photoThumbs;
        if (r5 == 0) goto L_0x2fd0;
    L_0x2fc6:
        r0 = r106;
        r5 = r0.photoThumbs;
        r5 = r5.isEmpty();
        if (r5 == 0) goto L_0x30d5;
    L_0x2fd0:
        if (r116 != 0) goto L_0x2fd7;
    L_0x2fd2:
        r116 = new java.util.HashMap;
        r116.<init>();
    L_0x2fd7:
        r0 = r72;
        r6 = r0.groupId;
        r5 = java.lang.Long.valueOf(r6);
        r0 = r116;
        r1 = r72;
        r0.put(r5, r1);
        if (r78 <= 0) goto L_0x3093;
    L_0x2fe8:
        r0 = r72;
        r5 = r0.messages;
        r5 = r5.size();
        r5 = r5 + -1;
        r0 = r78;
        if (r0 >= r5) goto L_0x3093;
    L_0x2ff6:
        r144 = new org.telegram.messenger.MessageObject$GroupedMessages;
        r144.<init>();
        r5 = org.telegram.messenger.Utilities.random;
        r6 = r5.nextLong();
        r0 = r144;
        r0.groupId = r6;
        r0 = r144;
        r5 = r0.messages;
        r0 = r72;
        r6 = r0.messages;
        r7 = r78 + 1;
        r0 = r72;
        r8 = r0.messages;
        r8 = r8.size();
        r6 = r6.subList(r7, r8);
        r5.addAll(r6);
        r31 = 0;
    L_0x3020:
        r0 = r144;
        r5 = r0.messages;
        r5 = r5.size();
        r0 = r31;
        if (r0 >= r5) goto L_0x3073;
    L_0x302c:
        r0 = r144;
        r5 = r0.messages;
        r0 = r31;
        r5 = r5.get(r0);
        r5 = (org.telegram.messenger.MessageObject) r5;
        r0 = r144;
        r6 = r0.groupId;
        r5.localGroupId = r6;
        r0 = r72;
        r5 = r0.messages;
        r6 = r78 + 1;
        r5.remove(r6);
        r31 = r31 + 1;
        goto L_0x3020;
    L_0x304a:
        r94 = 1;
        goto L_0x2ea2;
    L_0x304e:
        r0 = r106;
        r5 = r0.messageOwner;
        r5 = r5.action;
        r5 = r5 instanceof org.telegram.tgnet.TLRPC$TL_messageActionPaymentSent;
        if (r5 == 0) goto L_0x2f24;
    L_0x3058:
        r5 = 0;
        r0 = r106;
        r0.generatePaymentSentMessageText(r5);
        goto L_0x2f24;
    L_0x3060:
        r0 = r163;
        r5 = r0.messagesDict;
        r5 = r5[r94];
        r6 = r123.getId();
        r6 = java.lang.Integer.valueOf(r6);
        r5.remove(r6);
        goto L_0x2f53;
    L_0x3073:
        r0 = r144;
        r6 = r0.groupId;
        r5 = java.lang.Long.valueOf(r6);
        r0 = r116;
        r1 = r144;
        r0.put(r5, r1);
        r0 = r163;
        r5 = r0.groupedMessagesMap;
        r0 = r144;
        r6 = r0.groupId;
        r6 = java.lang.Long.valueOf(r6);
        r0 = r144;
        r5.put(r6, r0);
    L_0x3093:
        r0 = r72;
        r5 = r0.messages;
        r0 = r78;
        r5.remove(r0);
    L_0x309c:
        r0 = r106;
        r5 = r0.type;
        if (r5 < 0) goto L_0x30fa;
    L_0x30a2:
        r0 = r163;
        r5 = r0.messages;
        r0 = r80;
        r1 = r106;
        r5.set(r0, r1);
        r0 = r163;
        r5 = r0.chatAdapter;
        if (r5 == 0) goto L_0x30c4;
    L_0x30b3:
        r0 = r163;
        r5 = r0.chatAdapter;
        r0 = r163;
        r6 = r0.chatAdapter;
        r6 = r6.messagesStartRow;
        r6 = r6 + r80;
        r5.notifyItemChanged(r6);
    L_0x30c4:
        if (r81 < 0) goto L_0x30cf;
    L_0x30c6:
        r0 = r57;
        r1 = r81;
        r2 = r106;
        r0.set(r1, r2);
    L_0x30cf:
        r39 = 1;
    L_0x30d1:
        r4 = r4 + 1;
        goto L_0x2eae;
    L_0x30d5:
        r0 = r72;
        r5 = r0.messages;
        r0 = r78;
        r1 = r106;
        r5.set(r0, r1);
        r0 = r72;
        r5 = r0.positions;
        r0 = r123;
        r126 = r5.remove(r0);
        r126 = (org.telegram.messenger.MessageObject.GroupedMessagePosition) r126;
        if (r126 == 0) goto L_0x309c;
    L_0x30ee:
        r0 = r72;
        r5 = r0.positions;
        r0 = r106;
        r1 = r126;
        r5.put(r0, r1);
        goto L_0x309c;
    L_0x30fa:
        r0 = r163;
        r5 = r0.messages;
        r0 = r80;
        r5.remove(r0);
        r0 = r163;
        r5 = r0.chatAdapter;
        if (r5 == 0) goto L_0x311a;
    L_0x3109:
        r0 = r163;
        r5 = r0.chatAdapter;
        r0 = r163;
        r6 = r0.chatAdapter;
        r6 = r6.messagesStartRow;
        r6 = r6 + r80;
        r5.notifyItemRemoved(r6);
    L_0x311a:
        if (r81 < 0) goto L_0x30cf;
    L_0x311c:
        r0 = r57;
        r1 = r81;
        r0.remove(r1);
        r5 = r57.isEmpty();
        if (r5 == 0) goto L_0x30cf;
    L_0x3129:
        r0 = r163;
        r5 = r0.messagesByDays;
        r0 = r123;
        r6 = r0.dateKey;
        r5.remove(r6);
        r0 = r163;
        r5 = r0.messages;
        r0 = r80;
        r5.remove(r0);
        r0 = r163;
        r5 = r0.chatAdapter;
        r0 = r163;
        r6 = r0.chatAdapter;
        r6 = r6.messagesStartRow;
        r5.notifyItemRemoved(r6);
        goto L_0x30cf;
    L_0x314d:
        if (r116 == 0) goto L_0x31c6;
    L_0x314f:
        r5 = r116.entrySet();
        r5 = r5.iterator();
    L_0x3157:
        r6 = r5.hasNext();
        if (r6 == 0) goto L_0x31c6;
    L_0x315d:
        r64 = r5.next();
        r64 = (java.util.Map.Entry) r64;
        r72 = r64.getValue();
        r72 = (org.telegram.messenger.MessageObject.GroupedMessages) r72;
        r0 = r72;
        r6 = r0.messages;
        r6 = r6.isEmpty();
        if (r6 == 0) goto L_0x3183;
    L_0x3173:
        r0 = r163;
        r6 = r0.groupedMessagesMap;
        r0 = r72;
        r12 = r0.groupId;
        r7 = java.lang.Long.valueOf(r12);
        r6.remove(r7);
        goto L_0x3157;
    L_0x3183:
        r72.calculate();
        r0 = r72;
        r6 = r0.messages;
        r0 = r72;
        r7 = r0.messages;
        r7 = r7.size();
        r7 = r7 + -1;
        r106 = r6.get(r7);
        r106 = (org.telegram.messenger.MessageObject) r106;
        r0 = r163;
        r6 = r0.messages;
        r0 = r106;
        r80 = r6.indexOf(r0);
        if (r80 < 0) goto L_0x3157;
    L_0x31a6:
        r0 = r163;
        r6 = r0.chatAdapter;
        if (r6 == 0) goto L_0x3157;
    L_0x31ac:
        r0 = r163;
        r6 = r0.chatAdapter;
        r0 = r163;
        r7 = r0.chatAdapter;
        r7 = r7.messagesStartRow;
        r7 = r7 + r80;
        r0 = r72;
        r8 = r0.messages;
        r8 = r8.size();
        r6.notifyItemRangeChanged(r7, r8);
        goto L_0x3157;
    L_0x31c6:
        if (r39 == 0) goto L_0x005b;
    L_0x31c8:
        r0 = r163;
        r5 = r0.chatLayoutManager;
        if (r5 == 0) goto L_0x005b;
    L_0x31ce:
        if (r101 == 0) goto L_0x005b;
    L_0x31d0:
        r0 = r163;
        r5 = r0.chatLayoutManager;
        r5 = r5.findFirstVisibleItemPosition();
        if (r5 != 0) goto L_0x005b;
    L_0x31da:
        goto L_0x005b;
    L_0x31dc:
        r5 = org.telegram.messenger.NotificationCenter.notificationsSettingsUpdated;
        r0 = r164;
        if (r0 != r5) goto L_0x31f4;
    L_0x31e2:
        r163.updateTitleIcons();
        r0 = r163;
        r5 = r0.currentChat;
        r5 = org.telegram.messenger.ChatObject.isChannel(r5);
        if (r5 == 0) goto L_0x005b;
    L_0x31ef:
        r163.updateBottomOverlay();
        goto L_0x005b;
    L_0x31f4:
        r5 = org.telegram.messenger.NotificationCenter.didLoadedReplyMessages;
        r0 = r164;
        if (r0 != r5) goto L_0x3210;
    L_0x31fa:
        r5 = 0;
        r5 = r165[r5];
        r5 = (java.lang.Long) r5;
        r60 = r5.longValue();
        r0 = r163;
        r6 = r0.dialog_id;
        r5 = (r60 > r6 ? 1 : (r60 == r6 ? 0 : -1));
        if (r5 != 0) goto L_0x005b;
    L_0x320b:
        r163.updateVisibleRows();
        goto L_0x005b;
    L_0x3210:
        r5 = org.telegram.messenger.NotificationCenter.didLoadedPinnedMessage;
        r0 = r164;
        if (r0 != r5) goto L_0x324c;
    L_0x3216:
        r5 = 0;
        r103 = r165[r5];
        r103 = (org.telegram.messenger.MessageObject) r103;
        r6 = r103.getDialogId();
        r0 = r163;
        r12 = r0.dialog_id;
        r5 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r5 != 0) goto L_0x005b;
    L_0x3227:
        r0 = r163;
        r5 = r0.info;
        if (r5 == 0) goto L_0x005b;
    L_0x322d:
        r0 = r163;
        r5 = r0.info;
        r5 = r5.pinned_msg_id;
        r6 = r103.getId();
        if (r5 != r6) goto L_0x005b;
    L_0x3239:
        r0 = r103;
        r1 = r163;
        r1.pinnedMessageObject = r0;
        r5 = 0;
        r0 = r163;
        r0.loadingPinnedMessage = r5;
        r5 = 1;
        r0 = r163;
        r0.updatePinnedMessageView(r5);
        goto L_0x005b;
    L_0x324c:
        r5 = org.telegram.messenger.NotificationCenter.didReceivedWebpages;
        r0 = r164;
        if (r0 != r5) goto L_0x32dc;
    L_0x3252:
        r5 = 0;
        r30 = r165[r5];
        r30 = (java.util.ArrayList) r30;
        r154 = 0;
        r4 = 0;
    L_0x325a:
        r5 = r30.size();
        if (r4 >= r5) goto L_0x32c2;
    L_0x3260:
        r0 = r30;
        r103 = r0.get(r4);
        r103 = (org.telegram.tgnet.TLRPC$Message) r103;
        r60 = org.telegram.messenger.MessageObject.getDialogId(r103);
        r0 = r163;
        r6 = r0.dialog_id;
        r5 = (r60 > r6 ? 1 : (r60 == r6 ? 0 : -1));
        if (r5 == 0) goto L_0x327f;
    L_0x3274:
        r0 = r163;
        r6 = r0.mergeDialogId;
        r5 = (r60 > r6 ? 1 : (r60 == r6 ? 0 : -1));
        if (r5 == 0) goto L_0x327f;
    L_0x327c:
        r4 = r4 + 1;
        goto L_0x325a;
    L_0x327f:
        r0 = r163;
        r6 = r0.messagesDict;
        r0 = r163;
        r12 = r0.dialog_id;
        r5 = (r60 > r12 ? 1 : (r60 == r12 ? 0 : -1));
        if (r5 != 0) goto L_0x32c0;
    L_0x328b:
        r5 = 0;
    L_0x328c:
        r5 = r6[r5];
        r0 = r103;
        r6 = r0.id;
        r6 = java.lang.Integer.valueOf(r6);
        r51 = r5.get(r6);
        r51 = (org.telegram.messenger.MessageObject) r51;
        if (r51 == 0) goto L_0x327c;
    L_0x329e:
        r0 = r51;
        r5 = r0.messageOwner;
        r6 = new org.telegram.tgnet.TLRPC$TL_messageMediaWebPage;
        r6.<init>();
        r5.media = r6;
        r0 = r51;
        r5 = r0.messageOwner;
        r5 = r5.media;
        r0 = r103;
        r6 = r0.media;
        r6 = r6.webpage;
        r5.webpage = r6;
        r5 = 1;
        r0 = r51;
        r0.generateThumbs(r5);
        r154 = 1;
        goto L_0x327c;
    L_0x32c0:
        r5 = 1;
        goto L_0x328c;
    L_0x32c2:
        if (r154 == 0) goto L_0x005b;
    L_0x32c4:
        r163.updateVisibleRows();
        r0 = r163;
        r5 = r0.chatLayoutManager;
        if (r5 == 0) goto L_0x005b;
    L_0x32cd:
        r0 = r163;
        r5 = r0.chatLayoutManager;
        r5 = r5.findFirstVisibleItemPosition();
        if (r5 != 0) goto L_0x005b;
    L_0x32d7:
        r163.moveScrollToLastMessage();
        goto L_0x005b;
    L_0x32dc:
        r5 = org.telegram.messenger.NotificationCenter.didReceivedWebpagesInUpdates;
        r0 = r164;
        if (r0 != r5) goto L_0x331f;
    L_0x32e2:
        r0 = r163;
        r5 = r0.foundWebPage;
        if (r5 == 0) goto L_0x005b;
    L_0x32e8:
        r5 = 0;
        r75 = r165[r5];
        r75 = (java.util.HashMap) r75;
        r5 = r75.values();
        r5 = r5.iterator();
    L_0x32f5:
        r6 = r5.hasNext();
        if (r6 == 0) goto L_0x005b;
    L_0x32fb:
        r15 = r5.next();
        r15 = (org.telegram.tgnet.TLRPC$WebPage) r15;
        r6 = r15.id;
        r0 = r163;
        r8 = r0.foundWebPage;
        r12 = r8.id;
        r6 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r6 != 0) goto L_0x32f5;
    L_0x330d:
        r5 = r15 instanceof org.telegram.tgnet.TLRPC$TL_webPageEmpty;
        if (r5 != 0) goto L_0x331d;
    L_0x3311:
        r12 = 1;
    L_0x3312:
        r13 = 0;
        r14 = 0;
        r16 = 0;
        r11 = r163;
        r11.showReplyPanel(r12, r13, r14, r15, r16);
        goto L_0x005b;
    L_0x331d:
        r12 = 0;
        goto L_0x3312;
    L_0x331f:
        r5 = org.telegram.messenger.NotificationCenter.messagesReadContent;
        r0 = r164;
        if (r0 != r5) goto L_0x33d1;
    L_0x3325:
        r5 = 0;
        r28 = r165[r5];
        r28 = (java.util.ArrayList) r28;
        r154 = 0;
        r0 = r163;
        r5 = r0.currentChat;
        r5 = org.telegram.messenger.ChatObject.isChannel(r5);
        if (r5 == 0) goto L_0x3365;
    L_0x3336:
        r0 = r163;
        r5 = r0.currentChat;
        r0 = r5.id;
        r49 = r0;
    L_0x333e:
        r4 = 0;
    L_0x333f:
        r5 = r28.size();
        if (r4 >= r5) goto L_0x33ca;
    L_0x3345:
        r0 = r28;
        r5 = r0.get(r4);
        r5 = (java.lang.Long) r5;
        r110 = r5.longValue();
        r5 = 32;
        r6 = r110 >> r5;
        r0 = (int) r6;
        r41 = r0;
        if (r41 >= 0) goto L_0x335c;
    L_0x335a:
        r41 = 0;
    L_0x335c:
        r0 = r41;
        r1 = r49;
        if (r0 == r1) goto L_0x3368;
    L_0x3362:
        r4 = r4 + 1;
        goto L_0x333f;
    L_0x3365:
        r49 = 0;
        goto L_0x333e;
    L_0x3368:
        r0 = r163;
        r5 = r0.messagesDict;
        r6 = 0;
        r5 = r5[r6];
        r0 = r110;
        r6 = (int) r0;
        r6 = java.lang.Integer.valueOf(r6);
        r51 = r5.get(r6);
        r51 = (org.telegram.messenger.MessageObject) r51;
        if (r51 == 0) goto L_0x3362;
    L_0x337e:
        r51.setContentIsRead();
        r154 = 1;
        r0 = r51;
        r5 = r0.messageOwner;
        r5 = r5.mentioned;
        if (r5 == 0) goto L_0x3362;
    L_0x338b:
        r0 = r163;
        r5 = r0.newMentionsCount;
        r5 = r5 + -1;
        r0 = r163;
        r0.newMentionsCount = r5;
        r0 = r163;
        r5 = r0.newMentionsCount;
        if (r5 > 0) goto L_0x33ad;
    L_0x339b:
        r5 = 0;
        r0 = r163;
        r0.newMentionsCount = r5;
        r5 = 1;
        r0 = r163;
        r0.hasAllMentionsLocal = r5;
        r5 = 0;
        r6 = 1;
        r0 = r163;
        r0.showMentiondownButton(r5, r6);
        goto L_0x3362;
    L_0x33ad:
        r0 = r163;
        r5 = r0.mentiondownButtonCounter;
        r6 = "%d";
        r7 = 1;
        r7 = new java.lang.Object[r7];
        r8 = 0;
        r0 = r163;
        r11 = r0.newMentionsCount;
        r11 = java.lang.Integer.valueOf(r11);
        r7[r8] = r11;
        r6 = java.lang.String.format(r6, r7);
        r5.setText(r6);
        goto L_0x3362;
    L_0x33ca:
        if (r154 == 0) goto L_0x005b;
    L_0x33cc:
        r163.updateVisibleRows();
        goto L_0x005b;
    L_0x33d1:
        r5 = org.telegram.messenger.NotificationCenter.botInfoDidLoaded;
        r0 = r164;
        if (r0 != r5) goto L_0x3475;
    L_0x33d7:
        r5 = 1;
        r5 = r165[r5];
        r5 = (java.lang.Integer) r5;
        r73 = r5.intValue();
        r0 = r163;
        r5 = r0.classGuid;
        r0 = r73;
        if (r5 != r0) goto L_0x005b;
    L_0x33e8:
        r5 = 0;
        r82 = r165[r5];
        r82 = (org.telegram.tgnet.TLRPC$BotInfo) r82;
        r0 = r163;
        r5 = r0.currentEncryptedChat;
        if (r5 != 0) goto L_0x3470;
    L_0x33f3:
        r0 = r82;
        r5 = r0.commands;
        r5 = r5.isEmpty();
        if (r5 != 0) goto L_0x340c;
    L_0x33fd:
        r0 = r163;
        r5 = r0.currentChat;
        r5 = org.telegram.messenger.ChatObject.isChannel(r5);
        if (r5 != 0) goto L_0x340c;
    L_0x3407:
        r5 = 1;
        r0 = r163;
        r0.hasBotsCommands = r5;
    L_0x340c:
        r0 = r163;
        r5 = r0.botInfo;
        r0 = r82;
        r6 = r0.user_id;
        r6 = java.lang.Integer.valueOf(r6);
        r0 = r82;
        r5.put(r6, r0);
        r0 = r163;
        r5 = r0.chatAdapter;
        if (r5 == 0) goto L_0x3432;
    L_0x3423:
        r0 = r163;
        r5 = r0.chatAdapter;
        r0 = r163;
        r6 = r0.chatAdapter;
        r6 = r6.botInfoRow;
        r5.notifyItemChanged(r6);
    L_0x3432:
        r0 = r163;
        r5 = r0.mentionsAdapter;
        if (r5 == 0) goto L_0x345b;
    L_0x3438:
        r0 = r163;
        r5 = r0.currentChat;
        r5 = org.telegram.messenger.ChatObject.isChannel(r5);
        if (r5 == 0) goto L_0x3450;
    L_0x3442:
        r0 = r163;
        r5 = r0.currentChat;
        if (r5 == 0) goto L_0x345b;
    L_0x3448:
        r0 = r163;
        r5 = r0.currentChat;
        r5 = r5.megagroup;
        if (r5 == 0) goto L_0x345b;
    L_0x3450:
        r0 = r163;
        r5 = r0.mentionsAdapter;
        r0 = r163;
        r6 = r0.botInfo;
        r5.setBotInfo(r6);
    L_0x345b:
        r0 = r163;
        r5 = r0.chatActivityEnterView;
        if (r5 == 0) goto L_0x3470;
    L_0x3461:
        r0 = r163;
        r5 = r0.chatActivityEnterView;
        r0 = r163;
        r6 = r0.botsCount;
        r0 = r163;
        r7 = r0.hasBotsCommands;
        r5.setBotsCount(r6, r7);
    L_0x3470:
        r163.updateBotButtons();
        goto L_0x005b;
    L_0x3475:
        r5 = org.telegram.messenger.NotificationCenter.botKeyboardDidLoaded;
        r0 = r164;
        if (r0 != r5) goto L_0x3590;
    L_0x347b:
        r0 = r163;
        r6 = r0.dialog_id;
        r5 = 1;
        r5 = r165[r5];
        r5 = (java.lang.Long) r5;
        r12 = r5.longValue();
        r5 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r5 != 0) goto L_0x005b;
    L_0x348c:
        r5 = 0;
        r103 = r165[r5];
        r103 = (org.telegram.tgnet.TLRPC$Message) r103;
        if (r103 == 0) goto L_0x3554;
    L_0x3493:
        r0 = r163;
        r5 = r0.userBlocked;
        if (r5 != 0) goto L_0x3554;
    L_0x3499:
        r5 = new org.telegram.messenger.MessageObject;
        r6 = 0;
        r7 = 0;
        r0 = r103;
        r5.<init>(r0, r6, r7);
        r0 = r163;
        r0.botButtons = r5;
        r0 = r163;
        r5 = r0.chatActivityEnterView;
        if (r5 == 0) goto L_0x005b;
    L_0x34ac:
        r0 = r163;
        r5 = r0.botButtons;
        r5 = r5.messageOwner;
        r5 = r5.reply_markup;
        r5 = r5 instanceof org.telegram.tgnet.TLRPC$TL_replyKeyboardForceReply;
        if (r5 == 0) goto L_0x3523;
    L_0x34b8:
        r5 = org.telegram.messenger.ApplicationLoader.applicationContext;
        r6 = "mainconfig";
        r7 = 0;
        r133 = r5.getSharedPreferences(r6, r7);
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "answered_";
        r5 = r5.append(r6);
        r0 = r163;
        r6 = r0.dialog_id;
        r5 = r5.append(r6);
        r5 = r5.toString();
        r6 = 0;
        r0 = r133;
        r5 = r0.getInt(r5, r6);
        r0 = r163;
        r6 = r0.botButtons;
        r6 = r6.getId();
        if (r5 == r6) goto L_0x005b;
    L_0x34eb:
        r0 = r163;
        r5 = r0.replyingMessageObject;
        if (r5 == 0) goto L_0x34fb;
    L_0x34f1:
        r0 = r163;
        r5 = r0.chatActivityEnterView;
        r5 = r5.getFieldText();
        if (r5 != 0) goto L_0x005b;
    L_0x34fb:
        r0 = r163;
        r5 = r0.botButtons;
        r0 = r163;
        r0.botReplyButtons = r5;
        r0 = r163;
        r5 = r0.chatActivityEnterView;
        r0 = r163;
        r6 = r0.botButtons;
        r5.setButtons(r6);
        r17 = 1;
        r0 = r163;
        r0 = r0.botButtons;
        r18 = r0;
        r19 = 0;
        r20 = 0;
        r21 = 0;
        r16 = r163;
        r16.showReplyPanel(r17, r18, r19, r20, r21);
        goto L_0x005b;
    L_0x3523:
        r0 = r163;
        r5 = r0.replyingMessageObject;
        if (r5 == 0) goto L_0x3547;
    L_0x3529:
        r0 = r163;
        r5 = r0.botReplyButtons;
        r0 = r163;
        r6 = r0.replyingMessageObject;
        if (r5 != r6) goto L_0x3547;
    L_0x3533:
        r5 = 0;
        r0 = r163;
        r0.botReplyButtons = r5;
        r17 = 0;
        r18 = 0;
        r19 = 0;
        r20 = 0;
        r21 = 0;
        r16 = r163;
        r16.showReplyPanel(r17, r18, r19, r20, r21);
    L_0x3547:
        r0 = r163;
        r5 = r0.chatActivityEnterView;
        r0 = r163;
        r6 = r0.botButtons;
        r5.setButtons(r6);
        goto L_0x005b;
    L_0x3554:
        r5 = 0;
        r0 = r163;
        r0.botButtons = r5;
        r0 = r163;
        r5 = r0.chatActivityEnterView;
        if (r5 == 0) goto L_0x005b;
    L_0x355f:
        r0 = r163;
        r5 = r0.replyingMessageObject;
        if (r5 == 0) goto L_0x3583;
    L_0x3565:
        r0 = r163;
        r5 = r0.botReplyButtons;
        r0 = r163;
        r6 = r0.replyingMessageObject;
        if (r5 != r6) goto L_0x3583;
    L_0x356f:
        r5 = 0;
        r0 = r163;
        r0.botReplyButtons = r5;
        r17 = 0;
        r18 = 0;
        r19 = 0;
        r20 = 0;
        r21 = 0;
        r16 = r163;
        r16.showReplyPanel(r17, r18, r19, r20, r21);
    L_0x3583:
        r0 = r163;
        r5 = r0.chatActivityEnterView;
        r0 = r163;
        r6 = r0.botButtons;
        r5.setButtons(r6);
        goto L_0x005b;
    L_0x3590:
        r5 = org.telegram.messenger.NotificationCenter.chatSearchResultsAvailable;
        r0 = r164;
        if (r0 != r5) goto L_0x3601;
    L_0x3596:
        r0 = r163;
        r6 = r0.classGuid;
        r5 = 0;
        r5 = r165[r5];
        r5 = (java.lang.Integer) r5;
        r5 = r5.intValue();
        if (r6 != r5) goto L_0x005b;
    L_0x35a5:
        r5 = 1;
        r5 = r165[r5];
        r5 = (java.lang.Integer) r5;
        r17 = r5.intValue();
        r5 = 3;
        r5 = r165[r5];
        r5 = (java.lang.Long) r5;
        r60 = r5.longValue();
        if (r17 == 0) goto L_0x35ce;
    L_0x35b9:
        r18 = 0;
        r19 = 1;
        r0 = r163;
        r6 = r0.dialog_id;
        r5 = (r60 > r6 ? 1 : (r60 == r6 ? 0 : -1));
        if (r5 != 0) goto L_0x35fe;
    L_0x35c5:
        r20 = 0;
    L_0x35c7:
        r21 = 0;
        r16 = r163;
        r16.scrollToMessageId(r17, r18, r19, r20, r21);
    L_0x35ce:
        r5 = 2;
        r5 = r165[r5];
        r5 = (java.lang.Integer) r5;
        r6 = r5.intValue();
        r5 = 4;
        r5 = r165[r5];
        r5 = (java.lang.Integer) r5;
        r7 = r5.intValue();
        r5 = 5;
        r5 = r165[r5];
        r5 = (java.lang.Integer) r5;
        r5 = r5.intValue();
        r0 = r163;
        r0.updateSearchButtons(r6, r7, r5);
        r0 = r163;
        r5 = r0.searchItem;
        if (r5 == 0) goto L_0x005b;
    L_0x35f4:
        r0 = r163;
        r5 = r0.searchItem;
        r6 = 0;
        r5.setShowSearchProgress(r6);
        goto L_0x005b;
    L_0x35fe:
        r20 = 1;
        goto L_0x35c7;
    L_0x3601:
        r5 = org.telegram.messenger.NotificationCenter.chatSearchResultsLoading;
        r0 = r164;
        if (r0 != r5) goto L_0x3626;
    L_0x3607:
        r0 = r163;
        r6 = r0.classGuid;
        r5 = 0;
        r5 = r165[r5];
        r5 = (java.lang.Integer) r5;
        r5 = r5.intValue();
        if (r6 != r5) goto L_0x005b;
    L_0x3616:
        r0 = r163;
        r5 = r0.searchItem;
        if (r5 == 0) goto L_0x005b;
    L_0x361c:
        r0 = r163;
        r5 = r0.searchItem;
        r6 = 1;
        r5.setShowSearchProgress(r6);
        goto L_0x005b;
    L_0x3626:
        r5 = org.telegram.messenger.NotificationCenter.didUpdatedMessagesViews;
        r0 = r164;
        if (r0 != r5) goto L_0x3688;
    L_0x362c:
        r5 = 0;
        r42 = r165[r5];
        r42 = (android.util.SparseArray) r42;
        r0 = r163;
        r6 = r0.dialog_id;
        r5 = (int) r6;
        r0 = r42;
        r27 = r0.get(r5);
        r27 = (android.util.SparseIntArray) r27;
        if (r27 == 0) goto L_0x005b;
    L_0x3640:
        r154 = 0;
        r4 = 0;
    L_0x3643:
        r5 = r27.size();
        if (r4 >= r5) goto L_0x3681;
    L_0x3649:
        r0 = r27;
        r17 = r0.keyAt(r4);
        r0 = r163;
        r5 = r0.messagesDict;
        r6 = 0;
        r5 = r5[r6];
        r6 = java.lang.Integer.valueOf(r17);
        r106 = r5.get(r6);
        r106 = (org.telegram.messenger.MessageObject) r106;
        if (r106 == 0) goto L_0x367e;
    L_0x3662:
        r0 = r27;
        r1 = r17;
        r120 = r0.get(r1);
        r0 = r106;
        r5 = r0.messageOwner;
        r5 = r5.views;
        r0 = r120;
        if (r0 <= r5) goto L_0x367e;
    L_0x3674:
        r0 = r106;
        r5 = r0.messageOwner;
        r0 = r120;
        r5.views = r0;
        r154 = 1;
    L_0x367e:
        r4 = r4 + 1;
        goto L_0x3643;
    L_0x3681:
        if (r154 == 0) goto L_0x005b;
    L_0x3683:
        r163.updateVisibleRows();
        goto L_0x005b;
    L_0x3688:
        r5 = org.telegram.messenger.NotificationCenter.peerSettingsDidLoaded;
        r0 = r164;
        if (r0 != r5) goto L_0x36a4;
    L_0x368e:
        r5 = 0;
        r5 = r165[r5];
        r5 = (java.lang.Long) r5;
        r60 = r5.longValue();
        r0 = r163;
        r6 = r0.dialog_id;
        r5 = (r60 > r6 ? 1 : (r60 == r6 ? 0 : -1));
        if (r5 != 0) goto L_0x005b;
    L_0x369f:
        r163.updateSpamView();
        goto L_0x005b;
    L_0x36a4:
        r5 = org.telegram.messenger.NotificationCenter.newDraftReceived;
        r0 = r164;
        if (r0 != r5) goto L_0x36c3;
    L_0x36aa:
        r5 = 0;
        r5 = r165[r5];
        r5 = (java.lang.Long) r5;
        r60 = r5.longValue();
        r0 = r163;
        r6 = r0.dialog_id;
        r5 = (r60 > r6 ? 1 : (r60 == r6 ? 0 : -1));
        if (r5 != 0) goto L_0x005b;
    L_0x36bb:
        r5 = 1;
        r0 = r163;
        r0.applyDraftMaybe(r5);
        goto L_0x005b;
    L_0x36c3:
        r5 = org.telegram.messenger.NotificationCenter.userInfoDidLoaded;
        r0 = r164;
        if (r0 != r5) goto L_0x3707;
    L_0x36c9:
        r5 = 0;
        r147 = r165[r5];
        r147 = (java.lang.Integer) r147;
        r0 = r163;
        r5 = r0.currentUser;
        if (r5 == 0) goto L_0x005b;
    L_0x36d4:
        r0 = r163;
        r5 = r0.currentUser;
        r5 = r5.id;
        r6 = r147.intValue();
        if (r5 != r6) goto L_0x005b;
    L_0x36e0:
        r5 = 1;
        r157 = r165[r5];
        r157 = (org.telegram.tgnet.TLRPC$TL_userFull) r157;
        r0 = r163;
        r5 = r0.callItem;
        if (r5 == 0) goto L_0x005b;
    L_0x36eb:
        if (r157 == 0) goto L_0x36fd;
    L_0x36ed:
        r0 = r157;
        r5 = r0.phone_calls_available;
        if (r5 == 0) goto L_0x36fd;
    L_0x36f3:
        r0 = r163;
        r5 = r0.callItem;
        r6 = 0;
        r5.setVisibility(r6);
        goto L_0x005b;
    L_0x36fd:
        r0 = r163;
        r5 = r0.callItem;
        r6 = 4;
        r5.setVisibility(r6);
        goto L_0x005b;
    L_0x3707:
        r5 = org.telegram.messenger.NotificationCenter.didSetNewWallpapper;
        r0 = r164;
        if (r0 != r5) goto L_0x375c;
    L_0x370d:
        r0 = r163;
        r5 = r0.fragmentView;
        if (r5 == 0) goto L_0x005b;
    L_0x3713:
        r0 = r163;
        r5 = r0.fragmentView;
        r5 = (org.telegram.ui.Components.SizeNotifierFrameLayout) r5;
        r6 = org.telegram.ui.ActionBar.Theme.getCachedWallpaper();
        r5.setBackgroundImage(r6);
        r0 = r163;
        r5 = r0.progressView2;
        r5 = r5.getBackground();
        r6 = org.telegram.ui.ActionBar.Theme.colorFilter;
        r5.setColorFilter(r6);
        r0 = r163;
        r5 = r0.emptyView;
        if (r5 == 0) goto L_0x3740;
    L_0x3733:
        r0 = r163;
        r5 = r0.emptyView;
        r5 = r5.getBackground();
        r6 = org.telegram.ui.ActionBar.Theme.colorFilter;
        r5.setColorFilter(r6);
    L_0x3740:
        r0 = r163;
        r5 = r0.bigEmptyView;
        if (r5 == 0) goto L_0x3753;
    L_0x3746:
        r0 = r163;
        r5 = r0.bigEmptyView;
        r5 = r5.getBackground();
        r6 = org.telegram.ui.ActionBar.Theme.colorFilter;
        r5.setColorFilter(r6);
    L_0x3753:
        r0 = r163;
        r5 = r0.chatListView;
        r5.invalidateViews();
        goto L_0x005b;
    L_0x375c:
        r5 = org.telegram.messenger.NotificationCenter.channelRightsUpdated;
        r0 = r164;
        if (r0 != r5) goto L_0x3794;
    L_0x3762:
        r5 = 0;
        r44 = r165[r5];
        r44 = (org.telegram.tgnet.TLRPC$Chat) r44;
        r0 = r163;
        r5 = r0.currentChat;
        if (r5 == 0) goto L_0x005b;
    L_0x376d:
        r0 = r44;
        r5 = r0.id;
        r0 = r163;
        r6 = r0.currentChat;
        r6 = r6.id;
        if (r5 != r6) goto L_0x005b;
    L_0x3779:
        r0 = r163;
        r5 = r0.chatActivityEnterView;
        if (r5 == 0) goto L_0x005b;
    L_0x377f:
        r0 = r44;
        r1 = r163;
        r1.currentChat = r0;
        r0 = r163;
        r5 = r0.chatActivityEnterView;
        r5.checkChannelRights();
        r163.checkRaiseSensors();
        r163.updateSecretStatus();
        goto L_0x005b;
    L_0x3794:
        r5 = org.telegram.messenger.NotificationCenter.updateMentionsCount;
        r0 = r164;
        if (r0 != r5) goto L_0x005b;
    L_0x379a:
        r0 = r163;
        r6 = r0.dialog_id;
        r5 = 0;
        r5 = r165[r5];
        r5 = (java.lang.Long) r5;
        r12 = r5.longValue();
        r5 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r5 != 0) goto L_0x005b;
    L_0x37ab:
        r5 = 1;
        r5 = r165[r5];
        r5 = (java.lang.Integer) r5;
        r47 = r5.intValue();
        r0 = r163;
        r5 = r0.newMentionsCount;
        r0 = r47;
        if (r5 <= r0) goto L_0x005b;
    L_0x37bc:
        r0 = r47;
        r1 = r163;
        r1.newMentionsCount = r0;
        r0 = r163;
        r5 = r0.newMentionsCount;
        if (r5 > 0) goto L_0x37db;
    L_0x37c8:
        r5 = 0;
        r0 = r163;
        r0.newMentionsCount = r5;
        r5 = 1;
        r0 = r163;
        r0.hasAllMentionsLocal = r5;
        r5 = 0;
        r6 = 1;
        r0 = r163;
        r0.showMentiondownButton(r5, r6);
        goto L_0x005b;
    L_0x37db:
        r0 = r163;
        r5 = r0.mentiondownButtonCounter;
        r6 = "%d";
        r7 = 1;
        r7 = new java.lang.Object[r7];
        r8 = 0;
        r0 = r163;
        r11 = r0.newMentionsCount;
        r11 = java.lang.Integer.valueOf(r11);
        r7[r8] = r11;
        r6 = java.lang.String.format(r6, r7);
        r5.setText(r6);
        goto L_0x005b;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.telegram.ui.ChatActivity.didReceivedNotification(int, java.lang.Object[]):void");
    }

    public boolean processSwitchButton(TLRPC$TL_keyboardButtonSwitchInline button) {
        if (this.inlineReturn == 0 || button.same_peer || this.parentLayout == null) {
            return false;
        }
        String query = "@" + this.currentUser.username + " " + button.query;
        if (this.inlineReturn == this.dialog_id) {
            this.inlineReturn = 0;
            this.chatActivityEnterView.setFieldText(query);
        } else {
            DraftQuery.saveDraft(this.inlineReturn, query, null, null, false);
            if (this.parentLayout.fragmentsStack.size() > 1) {
                BaseFragment prevFragment = (BaseFragment) this.parentLayout.fragmentsStack.get(this.parentLayout.fragmentsStack.size() - 2);
                if ((prevFragment instanceof ChatActivity) && ((ChatActivity) prevFragment).dialog_id == this.inlineReturn) {
                    finishFragment();
                } else {
                    Bundle bundle = new Bundle();
                    int lower_part = (int) this.inlineReturn;
                    int high_part = (int) (this.inlineReturn >> 32);
                    if (lower_part == 0) {
                        bundle.putInt("enc_id", high_part);
                    } else if (lower_part > 0) {
                        bundle.putInt("user_id", lower_part);
                    } else if (lower_part < 0) {
                        bundle.putInt("chat_id", -lower_part);
                    }
                    presentFragment(new ChatActivity(bundle), true);
                }
            }
        }
        return true;
    }

    private void updateSearchButtons(int mask, int num, int count) {
        float f = 1.0f;
        if (this.searchUpButton != null) {
            boolean z;
            float f2;
            this.searchUpButton.setEnabled((mask & 1) != 0);
            ImageView imageView = this.searchDownButton;
            if ((mask & 2) != 0) {
                z = true;
            } else {
                z = false;
            }
            imageView.setEnabled(z);
            imageView = this.searchUpButton;
            if (this.searchUpButton.isEnabled()) {
                f2 = 1.0f;
            } else {
                f2 = 0.5f;
            }
            imageView.setAlpha(f2);
            ImageView imageView2 = this.searchDownButton;
            if (!this.searchDownButton.isEnabled()) {
                f = 0.5f;
            }
            imageView2.setAlpha(f);
            if (count < 0) {
                this.searchCountText.setText("");
            } else if (count == 0) {
                this.searchCountText.setText(LocaleController.getString("NoResult", R.string.NoResult));
            } else {
                this.searchCountText.setText(LocaleController.formatString("Of", R.string.Of, new Object[]{Integer.valueOf(num + 1), Integer.valueOf(count)}));
            }
        }
    }

    public boolean needDelayOpenAnimation() {
        return this.firstLoading;
    }

    public void onTransitionAnimationStart(boolean isOpen, boolean backward) {
        NotificationCenter.getInstance().setAllowedNotificationsDutingAnimation(new int[]{NotificationCenter.chatInfoDidLoaded, NotificationCenter.dialogsNeedReload, NotificationCenter.closeChats, NotificationCenter.messagesDidLoaded, NotificationCenter.botKeyboardDidLoaded});
        NotificationCenter.getInstance().setAnimationInProgress(true);
        if (isOpen) {
            this.openAnimationEnded = false;
        }
    }

    public void onTransitionAnimationEnd(boolean isOpen, boolean backward) {
        NotificationCenter.getInstance().setAnimationInProgress(false);
        if (isOpen) {
            this.openAnimationEnded = true;
            if (this.currentUser != null) {
                MessagesController.getInstance().loadFullUser(this.currentUser, this.classGuid, false);
            }
            if (VERSION.SDK_INT >= 21) {
                createChatAttachView();
            }
            if (this.chatActivityEnterView.hasRecordVideo() && !this.chatActivityEnterView.isSendButtonVisible()) {
                boolean isChannel = false;
                if (this.currentChat != null) {
                    if (!ChatObject.isChannel(this.currentChat) || this.currentChat.megagroup) {
                        isChannel = false;
                    } else {
                        isChannel = true;
                    }
                }
                SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
                String key = isChannel ? "needShowRoundHintChannel" : "needShowRoundHint";
                if (preferences.getBoolean(key, true) && Utilities.random.nextFloat() < 0.2f) {
                    showVoiceHint(false, this.chatActivityEnterView.isInVideoMode());
                    preferences.edit().putBoolean(key, false).commit();
                }
            }
        }
    }

    protected void onDialogDismiss(Dialog dialog) {
        if (this.closeChatDialog != null && dialog == this.closeChatDialog) {
            MessagesController.getInstance().deleteDialog(this.dialog_id, 0);
            if (this.parentLayout == null || this.parentLayout.fragmentsStack.isEmpty() || this.parentLayout.fragmentsStack.get(this.parentLayout.fragmentsStack.size() - 1) == this) {
                finishFragment();
                return;
            }
            BaseFragment fragment = (BaseFragment) this.parentLayout.fragmentsStack.get(this.parentLayout.fragmentsStack.size() - 1);
            removeSelfFromStack();
            fragment.finishFragment();
        }
    }

    public boolean extendActionMode(Menu menu) {
        if (!(this.chatActivityEnterView.getSelectionLength() == 0 || menu.findItem(16908321) == null)) {
            if (VERSION.SDK_INT >= 23) {
                menu.removeItem(16908341);
            }
            SpannableStringBuilder stringBuilder = new SpannableStringBuilder(LocaleController.getString("Bold", R.string.Bold));
            stringBuilder.setSpan(new TypefaceSpan(AndroidUtilities.getTypeface("fonts/rmedium.ttf")), 0, stringBuilder.length(), 33);
            menu.add(R.id.menu_groupbolditalic, R.id.menu_bold, 6, stringBuilder);
            stringBuilder = new SpannableStringBuilder(LocaleController.getString("Italic", R.string.Italic));
            stringBuilder.setSpan(new TypefaceSpan(AndroidUtilities.getTypeface("fonts/ritalic.ttf")), 0, stringBuilder.length(), 33);
            menu.add(R.id.menu_groupbolditalic, R.id.menu_italic, 7, stringBuilder);
            menu.add(R.id.menu_groupbolditalic, R.id.menu_regular, 8, LocaleController.getString("Regular", R.string.Regular));
        }
        return true;
    }

    private void updateBottomOverlay() {
        if (this.bottomOverlayChatText != null) {
            if (this.currentChat != null) {
                if (!ChatObject.isChannel(this.currentChat) || (this.currentChat instanceof TLRPC$TL_channelForbidden)) {
                    this.bottomOverlayChatText.setText(LocaleController.getString("DeleteThisGroup", R.string.DeleteThisGroup));
                } else if (ChatObject.isNotInChat(this.currentChat)) {
                    this.bottomOverlayChatText.setText(LocaleController.getString("ChannelJoin", R.string.ChannelJoin));
                } else if (MessagesController.getInstance().isDialogMuted(this.dialog_id)) {
                    this.bottomOverlayChatText.setText(LocaleController.getString("ChannelUnmute", R.string.ChannelUnmute));
                } else {
                    this.bottomOverlayChatText.setText(LocaleController.getString("ChannelMute", R.string.ChannelMute));
                }
            } else if (this.userBlocked) {
                if (this.currentUser.bot) {
                    this.bottomOverlayChatText.setText(LocaleController.getString("BotUnblock", R.string.BotUnblock));
                } else {
                    this.bottomOverlayChatText.setText(LocaleController.getString("Unblock", R.string.Unblock));
                }
                if (this.botButtons != null) {
                    this.botButtons = null;
                    if (this.chatActivityEnterView != null) {
                        if (this.replyingMessageObject != null && this.botReplyButtons == this.replyingMessageObject) {
                            this.botReplyButtons = null;
                            showReplyPanel(false, null, null, null, false);
                        }
                        this.chatActivityEnterView.setButtons(this.botButtons, false);
                    }
                }
            } else if (this.botUser == null || !this.currentUser.bot) {
                this.bottomOverlayChatText.setText(LocaleController.getString("DeleteThisChat", R.string.DeleteThisChat));
            } else {
                this.bottomOverlayChatText.setText(LocaleController.getString("BotStart", R.string.BotStart));
                this.chatActivityEnterView.hidePopup(false);
                if (getParentActivity() != null) {
                    AndroidUtilities.hideKeyboard(getParentActivity().getCurrentFocus());
                }
            }
            if (this.searchItem == null || this.searchItem.getVisibility() != 0) {
                this.searchContainer.setVisibility(4);
                if ((this.currentChat == null || (!ChatObject.isNotInChat(this.currentChat) && ChatObject.canWriteToChat(this.currentChat))) && (this.currentUser == null || !(UserObject.isDeleted(this.currentUser) || this.userBlocked))) {
                    if (this.botUser == null || !this.currentUser.bot) {
                        this.chatActivityEnterView.setVisibility(0);
                        this.bottomOverlayChat.setVisibility(4);
                    } else {
                        this.bottomOverlayChat.setVisibility(0);
                        this.chatActivityEnterView.setVisibility(4);
                    }
                    if (this.muteItem != null) {
                        this.muteItem.setVisibility(0);
                    }
                } else {
                    if (this.chatActivityEnterView.isEditingMessage()) {
                        this.chatActivityEnterView.setVisibility(0);
                        this.bottomOverlayChat.setVisibility(4);
                        this.chatActivityEnterView.setFieldFocused();
                        AndroidUtilities.runOnUIThread(new Runnable() {
                            public void run() {
                                ChatActivity.this.chatActivityEnterView.openKeyboard();
                            }
                        }, 100);
                    } else {
                        this.bottomOverlayChat.setVisibility(0);
                        this.chatActivityEnterView.setFieldFocused(false);
                        this.chatActivityEnterView.setVisibility(4);
                        this.chatActivityEnterView.closeKeyboard();
                    }
                    if (this.muteItem != null) {
                        this.muteItem.setVisibility(8);
                    }
                    this.attachItem.setVisibility(8);
                    this.headerItem.setVisibility(0);
                }
                if (this.topViewWasVisible == 1) {
                    this.chatActivityEnterView.showTopView(false, false);
                    this.topViewWasVisible = 0;
                }
            } else {
                this.searchContainer.setVisibility(0);
                this.bottomOverlayChat.setVisibility(4);
                this.chatActivityEnterView.setFieldFocused(false);
                this.chatActivityEnterView.setVisibility(4);
                if (this.chatActivityEnterView.isTopViewVisible()) {
                    this.topViewWasVisible = 1;
                    this.chatActivityEnterView.hideTopView(false);
                } else {
                    this.topViewWasVisible = 2;
                }
            }
            checkRaiseSensors();
        }
    }

    public void showAlert(String name, String message) {
        if (this.alertView != null && name != null && message != null) {
            if (this.alertView.getTag() != null) {
                this.alertView.setTag(null);
                if (this.alertViewAnimator != null) {
                    this.alertViewAnimator.cancel();
                    this.alertViewAnimator = null;
                }
                this.alertView.setVisibility(0);
                this.alertViewAnimator = new AnimatorSet();
                AnimatorSet animatorSet = this.alertViewAnimator;
                Animator[] animatorArr = new Animator[1];
                animatorArr[0] = ObjectAnimator.ofFloat(this.alertView, "translationY", new float[]{0.0f});
                animatorSet.playTogether(animatorArr);
                this.alertViewAnimator.setDuration(200);
                this.alertViewAnimator.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animation) {
                        if (ChatActivity.this.alertViewAnimator != null && ChatActivity.this.alertViewAnimator.equals(animation)) {
                            ChatActivity.this.alertViewAnimator = null;
                        }
                    }

                    public void onAnimationCancel(Animator animation) {
                        if (ChatActivity.this.alertViewAnimator != null && ChatActivity.this.alertViewAnimator.equals(animation)) {
                            ChatActivity.this.alertViewAnimator = null;
                        }
                    }
                });
                this.alertViewAnimator.start();
            }
            this.alertNameTextView.setText(name);
            this.alertTextView.setText(Emoji.replaceEmoji(message.replace('\n', ' '), this.alertTextView.getPaint().getFontMetricsInt(), AndroidUtilities.dp(14.0f), false));
            if (this.hideAlertViewRunnable != null) {
                AndroidUtilities.cancelRunOnUIThread(this.hideAlertViewRunnable);
            }
            Runnable anonymousClass90 = new Runnable() {

                class C59481 extends AnimatorListenerAdapter {
                    C59481() {
                    }

                    public void onAnimationEnd(Animator animation) {
                        if (ChatActivity.this.alertViewAnimator != null && ChatActivity.this.alertViewAnimator.equals(animation)) {
                            ChatActivity.this.alertView.setVisibility(8);
                            ChatActivity.this.alertViewAnimator = null;
                        }
                    }

                    public void onAnimationCancel(Animator animation) {
                        if (ChatActivity.this.alertViewAnimator != null && ChatActivity.this.alertViewAnimator.equals(animation)) {
                            ChatActivity.this.alertViewAnimator = null;
                        }
                    }
                }

                public void run() {
                    if (ChatActivity.this.hideAlertViewRunnable == this && ChatActivity.this.alertView.getTag() == null) {
                        ChatActivity.this.alertView.setTag(Integer.valueOf(1));
                        if (ChatActivity.this.alertViewAnimator != null) {
                            ChatActivity.this.alertViewAnimator.cancel();
                            ChatActivity.this.alertViewAnimator = null;
                        }
                        ChatActivity.this.alertViewAnimator = new AnimatorSet();
                        AnimatorSet access$18400 = ChatActivity.this.alertViewAnimator;
                        Animator[] animatorArr = new Animator[1];
                        animatorArr[0] = ObjectAnimator.ofFloat(ChatActivity.this.alertView, "translationY", new float[]{(float) (-AndroidUtilities.dp(50.0f))});
                        access$18400.playTogether(animatorArr);
                        ChatActivity.this.alertViewAnimator.setDuration(200);
                        ChatActivity.this.alertViewAnimator.addListener(new C59481());
                        ChatActivity.this.alertViewAnimator.start();
                    }
                }
            };
            this.hideAlertViewRunnable = anonymousClass90;
            AndroidUtilities.runOnUIThread(anonymousClass90, 3000);
        }
    }

    private void hidePinnedMessageView(boolean animated) {
        if (this.pinnedMessageView.getTag() == null) {
            this.pinnedMessageView.setTag(Integer.valueOf(1));
            if (this.pinnedMessageViewAnimator != null) {
                this.pinnedMessageViewAnimator.cancel();
                this.pinnedMessageViewAnimator = null;
            }
            if (animated) {
                this.pinnedMessageViewAnimator = new AnimatorSet();
                AnimatorSet animatorSet = this.pinnedMessageViewAnimator;
                Animator[] animatorArr = new Animator[1];
                animatorArr[0] = ObjectAnimator.ofFloat(this.pinnedMessageView, "translationY", new float[]{(float) (-AndroidUtilities.dp(50.0f))});
                animatorSet.playTogether(animatorArr);
                this.pinnedMessageViewAnimator.setDuration(200);
                this.pinnedMessageViewAnimator.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animation) {
                        if (ChatActivity.this.pinnedMessageViewAnimator != null && ChatActivity.this.pinnedMessageViewAnimator.equals(animation)) {
                            ChatActivity.this.pinnedMessageView.setVisibility(8);
                            ChatActivity.this.pinnedMessageViewAnimator = null;
                        }
                    }

                    public void onAnimationCancel(Animator animation) {
                        if (ChatActivity.this.pinnedMessageViewAnimator != null && ChatActivity.this.pinnedMessageViewAnimator.equals(animation)) {
                            ChatActivity.this.pinnedMessageViewAnimator = null;
                        }
                    }
                });
                this.pinnedMessageViewAnimator.start();
                return;
            }
            this.pinnedMessageView.setTranslationY((float) (-AndroidUtilities.dp(50.0f)));
            this.pinnedMessageView.setVisibility(8);
        }
    }

    private void updatePinnedMessageView(boolean animated) {
        if (this.pinnedMessageView != null) {
            if (this.info != null) {
                if (!(this.pinnedMessageObject == null || this.info.pinned_msg_id == this.pinnedMessageObject.getId())) {
                    this.pinnedMessageObject = null;
                }
                if (this.info.pinned_msg_id != 0 && this.pinnedMessageObject == null) {
                    this.pinnedMessageObject = (MessageObject) this.messagesDict[0].get(Integer.valueOf(this.info.pinned_msg_id));
                }
            }
            SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("Notifications", 0);
            if (this.info == null || this.info.pinned_msg_id == 0 || this.info.pinned_msg_id == preferences.getInt("pin_" + this.dialog_id, 0) || (this.actionBar != null && (this.actionBar.isActionModeShowed() || this.actionBar.isSearchFieldVisible()))) {
                hidePinnedMessageView(animated);
            } else if (this.pinnedMessageObject != null) {
                if (this.pinnedMessageView.getTag() != null) {
                    this.pinnedMessageView.setTag(null);
                    if (this.pinnedMessageViewAnimator != null) {
                        this.pinnedMessageViewAnimator.cancel();
                        this.pinnedMessageViewAnimator = null;
                    }
                    if (animated) {
                        this.pinnedMessageView.setVisibility(0);
                        this.pinnedMessageViewAnimator = new AnimatorSet();
                        AnimatorSet animatorSet = this.pinnedMessageViewAnimator;
                        Animator[] animatorArr = new Animator[1];
                        animatorArr[0] = ObjectAnimator.ofFloat(this.pinnedMessageView, "translationY", new float[]{0.0f});
                        animatorSet.playTogether(animatorArr);
                        this.pinnedMessageViewAnimator.setDuration(200);
                        this.pinnedMessageViewAnimator.addListener(new AnimatorListenerAdapter() {
                            public void onAnimationEnd(Animator animation) {
                                if (ChatActivity.this.pinnedMessageViewAnimator != null && ChatActivity.this.pinnedMessageViewAnimator.equals(animation)) {
                                    ChatActivity.this.pinnedMessageViewAnimator = null;
                                }
                            }

                            public void onAnimationCancel(Animator animation) {
                                if (ChatActivity.this.pinnedMessageViewAnimator != null && ChatActivity.this.pinnedMessageViewAnimator.equals(animation)) {
                                    ChatActivity.this.pinnedMessageViewAnimator = null;
                                }
                            }
                        });
                        this.pinnedMessageViewAnimator.start();
                    } else {
                        this.pinnedMessageView.setTranslationY(0.0f);
                        this.pinnedMessageView.setVisibility(0);
                    }
                }
                FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) this.pinnedMessageNameTextView.getLayoutParams();
                FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.pinnedMessageTextView.getLayoutParams();
                TLRPC$PhotoSize photoSize = FileLoader.getClosestPhotoSizeWithSize(this.pinnedMessageObject.photoThumbs2, AndroidUtilities.dp(50.0f));
                if (photoSize == null) {
                    photoSize = FileLoader.getClosestPhotoSizeWithSize(this.pinnedMessageObject.photoThumbs, AndroidUtilities.dp(50.0f));
                }
                int dp;
                if (photoSize == null || (photoSize instanceof TLRPC$TL_photoSizeEmpty) || (photoSize.location instanceof TLRPC$TL_fileLocationUnavailable) || this.pinnedMessageObject.type == 13) {
                    this.pinnedMessageImageView.setImageBitmap(null);
                    this.pinnedImageLocation = null;
                    this.pinnedMessageImageView.setVisibility(4);
                    dp = AndroidUtilities.dp(RadialCountdown.TEXT_SIZE_SP);
                    layoutParams2.leftMargin = dp;
                    layoutParams1.leftMargin = dp;
                } else {
                    if (this.pinnedMessageObject.isRoundVideo()) {
                        this.pinnedMessageImageView.setRoundRadius(AndroidUtilities.dp(16.0f));
                    } else {
                        this.pinnedMessageImageView.setRoundRadius(0);
                    }
                    this.pinnedImageLocation = photoSize.location;
                    this.pinnedMessageImageView.setImage(this.pinnedImageLocation, "50_50", (Drawable) null);
                    this.pinnedMessageImageView.setVisibility(0);
                    dp = AndroidUtilities.dp(55.0f);
                    layoutParams2.leftMargin = dp;
                    layoutParams1.leftMargin = dp;
                }
                this.pinnedMessageNameTextView.setLayoutParams(layoutParams1);
                this.pinnedMessageTextView.setLayoutParams(layoutParams2);
                this.pinnedMessageNameTextView.setText(LocaleController.getString("PinnedMessage", R.string.PinnedMessage));
                if (this.pinnedMessageObject.type == 14) {
                    this.pinnedMessageTextView.setText(String.format("%s - %s", new Object[]{this.pinnedMessageObject.getMusicAuthor(), this.pinnedMessageObject.getMusicTitle()}));
                } else if (this.pinnedMessageObject.messageOwner.media instanceof TLRPC$TL_messageMediaGame) {
                    this.pinnedMessageTextView.setText(Emoji.replaceEmoji(this.pinnedMessageObject.messageOwner.media.game.title, this.pinnedMessageTextView.getPaint().getFontMetricsInt(), AndroidUtilities.dp(14.0f), false));
                } else if (this.pinnedMessageObject.messageText != null) {
                    String mess = this.pinnedMessageObject.messageText.toString();
                    if (mess.length() > 150) {
                        mess = mess.substring(0, 150);
                    }
                    this.pinnedMessageTextView.setText(Emoji.replaceEmoji(mess.replace('\n', ' '), this.pinnedMessageTextView.getPaint().getFontMetricsInt(), AndroidUtilities.dp(14.0f), false));
                }
            } else {
                this.pinnedImageLocation = null;
                hidePinnedMessageView(animated);
                if (this.loadingPinnedMessage != this.info.pinned_msg_id) {
                    this.loadingPinnedMessage = this.info.pinned_msg_id;
                    MessagesQuery.loadPinnedMessage(this.currentChat.id, this.info.pinned_msg_id, true);
                }
            }
            checkListViewPaddings();
        }
    }

    private void updateSpamView() {
        if (this.reportSpamView == null) {
            FileLog.d("no spam view found");
            return;
        }
        boolean show;
        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("Notifications", 0);
        if (this.currentEncryptedChat != null) {
            if (this.currentEncryptedChat.admin_id == UserConfig.getClientUserId() || ContactsController.getInstance().isLoadingContacts() || ContactsController.getInstance().contactsDict.get(Integer.valueOf(this.currentUser.id)) != null) {
                show = false;
            } else {
                show = true;
            }
            if (show && preferences.getInt("spam3_" + this.dialog_id, 0) == 1) {
                show = false;
            }
        } else {
            show = preferences.getInt(new StringBuilder().append("spam3_").append(this.dialog_id).toString(), 0) == 2;
        }
        AnimatorSet animatorSet;
        Animator[] animatorArr;
        if (show) {
            if (this.reportSpamView.getTag() != null) {
                FileLog.d("show spam button");
                this.reportSpamView.setTag(null);
                this.reportSpamView.setVisibility(0);
                if (this.reportSpamViewAnimator != null) {
                    this.reportSpamViewAnimator.cancel();
                }
                this.reportSpamViewAnimator = new AnimatorSet();
                animatorSet = this.reportSpamViewAnimator;
                animatorArr = new Animator[1];
                animatorArr[0] = ObjectAnimator.ofFloat(this.reportSpamView, "translationY", new float[]{0.0f});
                animatorSet.playTogether(animatorArr);
                this.reportSpamViewAnimator.setDuration(200);
                this.reportSpamViewAnimator.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animation) {
                        if (ChatActivity.this.reportSpamViewAnimator != null && ChatActivity.this.reportSpamViewAnimator.equals(animation)) {
                            ChatActivity.this.reportSpamViewAnimator = null;
                        }
                    }

                    public void onAnimationCancel(Animator animation) {
                        if (ChatActivity.this.reportSpamViewAnimator != null && ChatActivity.this.reportSpamViewAnimator.equals(animation)) {
                            ChatActivity.this.reportSpamViewAnimator = null;
                        }
                    }
                });
                this.reportSpamViewAnimator.start();
            }
        } else if (this.reportSpamView.getTag() == null) {
            FileLog.d("hide spam button");
            this.reportSpamView.setTag(Integer.valueOf(1));
            if (this.reportSpamViewAnimator != null) {
                this.reportSpamViewAnimator.cancel();
            }
            this.reportSpamViewAnimator = new AnimatorSet();
            animatorSet = this.reportSpamViewAnimator;
            animatorArr = new Animator[1];
            animatorArr[0] = ObjectAnimator.ofFloat(this.reportSpamView, "translationY", new float[]{(float) (-AndroidUtilities.dp(50.0f))});
            animatorSet.playTogether(animatorArr);
            this.reportSpamViewAnimator.setDuration(200);
            this.reportSpamViewAnimator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {
                    if (ChatActivity.this.reportSpamViewAnimator != null && ChatActivity.this.reportSpamViewAnimator.equals(animation)) {
                        ChatActivity.this.reportSpamView.setVisibility(8);
                        ChatActivity.this.reportSpamViewAnimator = null;
                    }
                }

                public void onAnimationCancel(Animator animation) {
                    if (ChatActivity.this.reportSpamViewAnimator != null && ChatActivity.this.reportSpamViewAnimator.equals(animation)) {
                        ChatActivity.this.reportSpamViewAnimator = null;
                    }
                }
            });
            this.reportSpamViewAnimator.start();
        }
        checkListViewPaddings();
    }

    private void updateContactStatus() {
        if (this.addContactItem != null) {
            if (this.currentUser == null) {
                this.addContactItem.setVisibility(8);
            } else {
                User user = MessagesController.getInstance().getUser(Integer.valueOf(this.currentUser.id));
                if (user != null) {
                    this.currentUser = user;
                }
                if ((this.currentEncryptedChat != null && !(this.currentEncryptedChat instanceof TLRPC$TL_encryptedChat)) || MessagesController.isSupportId(this.currentUser.id) || UserObject.isDeleted(this.currentUser) || ContactsController.getInstance().isLoadingContacts() || (!TextUtils.isEmpty(this.currentUser.phone) && ContactsController.getInstance().contactsDict.get(Integer.valueOf(this.currentUser.id)) != null && (ContactsController.getInstance().contactsDict.size() != 0 || !ContactsController.getInstance().isLoadingContacts()))) {
                    this.addContactItem.setVisibility(8);
                } else {
                    this.addContactItem.setVisibility(0);
                    if (TextUtils.isEmpty(this.currentUser.phone)) {
                        this.addContactItem.setText(LocaleController.getString("ShareMyContactInfo", R.string.ShareMyContactInfo));
                        this.addToContactsButton.setVisibility(8);
                        this.reportSpamButton.setPadding(AndroidUtilities.dp(50.0f), 0, AndroidUtilities.dp(50.0f), 0);
                        this.reportSpamContainer.setLayoutParams(LayoutHelper.createLinear(-1, -1, 1.0f, 51, 0, 0, 0, AndroidUtilities.dp(1.0f)));
                    } else {
                        this.addContactItem.setText(LocaleController.getString("AddToContacts", R.string.AddToContacts));
                        this.reportSpamButton.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(50.0f), 0);
                        this.addToContactsButton.setVisibility(0);
                        this.reportSpamContainer.setLayoutParams(LayoutHelper.createLinear(-1, -1, 0.5f, 51, 0, 0, 0, AndroidUtilities.dp(1.0f)));
                    }
                }
            }
            checkListViewPaddings();
        }
    }

    private void checkListViewPaddings() {
        AndroidUtilities.runOnUIThread(new Runnable() {
            public void run() {
                try {
                    int firstVisPos = ChatActivity.this.chatLayoutManager.findFirstVisibleItemPosition();
                    int top = 0;
                    if (firstVisPos != -1) {
                        View firstVisView = ChatActivity.this.chatLayoutManager.findViewByPosition(firstVisPos);
                        top = firstVisView == null ? 0 : (ChatActivity.this.chatListView.getMeasuredHeight() - firstVisView.getBottom()) - ChatActivity.this.chatListView.getPaddingBottom();
                    }
                    FrameLayout.LayoutParams layoutParams;
                    if (ChatActivity.this.chatListView.getPaddingTop() != AndroidUtilities.dp(52.0f) && ((ChatActivity.this.pinnedMessageView != null && ChatActivity.this.pinnedMessageView.getTag() == null) || (ChatActivity.this.reportSpamView != null && ChatActivity.this.reportSpamView.getTag() == null))) {
                        ChatActivity.this.chatListView.setPadding(0, AndroidUtilities.dp(52.0f), 0, AndroidUtilities.dp(3.0f));
                        layoutParams = (FrameLayout.LayoutParams) ChatActivity.this.floatingDateView.getLayoutParams();
                        layoutParams.topMargin = AndroidUtilities.dp(52.0f);
                        ChatActivity.this.floatingDateView.setLayoutParams(layoutParams);
                        ChatActivity.this.chatListView.setTopGlowOffset(AndroidUtilities.dp(48.0f));
                    } else if (ChatActivity.this.chatListView.getPaddingTop() == AndroidUtilities.dp(4.0f) || ((ChatActivity.this.pinnedMessageView != null && ChatActivity.this.pinnedMessageView.getTag() == null) || (ChatActivity.this.reportSpamView != null && ChatActivity.this.reportSpamView.getTag() == null))) {
                        firstVisPos = -1;
                    } else {
                        ChatActivity.this.chatListView.setPadding(0, AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(3.0f));
                        layoutParams = (FrameLayout.LayoutParams) ChatActivity.this.floatingDateView.getLayoutParams();
                        layoutParams.topMargin = AndroidUtilities.dp(4.0f);
                        ChatActivity.this.floatingDateView.setLayoutParams(layoutParams);
                        ChatActivity.this.chatListView.setTopGlowOffset(0);
                    }
                    if (firstVisPos != -1) {
                        ChatActivity.this.chatLayoutManager.scrollToPositionWithOffset(firstVisPos, top);
                    }
                } catch (Exception e) {
                    FileLog.e(e);
                }
            }
        });
    }

    private void checkRaiseSensors() {
        if (ChatObject.isChannel(this.currentChat) && this.currentChat.banned_rights != null && this.currentChat.banned_rights.send_media) {
            MediaController.getInstance().setAllowStartRecord(false);
        } else if (ApplicationLoader.mainInterfacePaused || ((this.bottomOverlayChat != null && this.bottomOverlayChat.getVisibility() == 0) || ((this.bottomOverlay != null && this.bottomOverlay.getVisibility() == 0) || (this.searchContainer != null && this.searchContainer.getVisibility() == 0)))) {
            MediaController.getInstance().setAllowStartRecord(false);
        } else {
            MediaController.getInstance().setAllowStartRecord(true);
        }
    }

    public void dismissCurrentDialig() {
        if (this.chatAttachAlert == null || this.visibleDialog != this.chatAttachAlert) {
            super.dismissCurrentDialig();
            return;
        }
        this.chatAttachAlert.closeCamera(false);
        this.chatAttachAlert.dismissInternal();
        this.chatAttachAlert.hideCamera(true);
    }

    public void onResume() {
        super.onResume();
        AndroidUtilities.requestAdjustResize(getParentActivity(), this.classGuid);
        MediaController.getInstance().startRaiseToEarSensors(this);
        checkRaiseSensors();
        if (this.chatAttachAlert != null) {
            this.chatAttachAlert.onResume();
        }
        checkActionBarMenu();
        if (!(this.replyImageLocation == null || this.replyImageView == null)) {
            this.replyImageView.setImage(this.replyImageLocation, "50_50", (Drawable) null);
        }
        if (!(this.pinnedImageLocation == null || this.pinnedMessageImageView == null)) {
            this.pinnedMessageImageView.setImage(this.pinnedImageLocation, "50_50", (Drawable) null);
        }
        NotificationsController.getInstance().setOpenedDialogId(this.dialog_id);
        if (this.scrollToTopOnResume) {
            if (!this.scrollToTopUnReadOnResume || this.scrollToMessage == null) {
                moveScrollToLastMessage();
            } else if (this.chatListView != null) {
                int yOffset;
                boolean bottom = true;
                if (this.scrollToMessagePosition == -9000) {
                    yOffset = getScrollOffsetForMessage(this.scrollToMessage);
                    bottom = false;
                } else if (this.scrollToMessagePosition == -10000) {
                    yOffset = (-this.chatListView.getPaddingTop()) - AndroidUtilities.dp(7.0f);
                    bottom = false;
                } else {
                    yOffset = this.scrollToMessagePosition;
                }
                this.chatLayoutManager.scrollToPositionWithOffset(this.chatAdapter.messagesStartRow + this.messages.indexOf(this.scrollToMessage), yOffset, bottom);
            }
            this.scrollToTopUnReadOnResume = false;
            this.scrollToTopOnResume = false;
            this.scrollToMessage = null;
        }
        this.paused = false;
        this.pausedOnLastMessage = false;
        AndroidUtilities.runOnUIThread(this.readRunnable, 500);
        checkScrollForLoad(false);
        if (this.wasPaused) {
            this.wasPaused = false;
            if (this.chatAdapter != null) {
                this.chatAdapter.notifyDataSetChanged();
            }
        }
        fixLayout();
        applyDraftMaybe(false);
        if (!(this.bottomOverlayChat == null || this.bottomOverlayChat.getVisibility() == 0)) {
            this.chatActivityEnterView.setFieldFocused(true);
        }
        if (this.chatActivityEnterView != null) {
            this.chatActivityEnterView.onResume();
        }
        if (this.currentUser != null) {
            this.chatEnterTime = System.currentTimeMillis();
            this.chatLeaveTime = 0;
        }
        if (this.startVideoEdit != null) {
            AndroidUtilities.runOnUIThread(new Runnable() {
                public void run() {
                    ChatActivity.this.openVideoEditor(ChatActivity.this.startVideoEdit, null);
                    ChatActivity.this.startVideoEdit = null;
                }
            });
        }
        if (this.chatListView != null && (this.chatActivityEnterView == null || !this.chatActivityEnterView.isEditingMessage())) {
            this.chatListView.setOnItemLongClickListener(this.onItemLongClickListener);
            this.chatListView.setOnItemClickListener(this.onItemClickListener);
            this.chatListView.setLongClickable(true);
        }
        checkBotCommands();
    }

    public void onPause() {
        boolean z;
        super.onPause();
        AndroidUtilities.cancelRunOnUIThread(this.readRunnable);
        MediaController.getInstance().stopRaiseToEarSensors(this);
        this.paused = true;
        this.wasPaused = true;
        NotificationsController.getInstance().setOpenedDialogId(0);
        CharSequence draftMessage = null;
        MessageObject replyMessage = null;
        boolean searchWebpage = true;
        if (!(this.ignoreAttachOnPause || this.chatActivityEnterView == null || this.bottomOverlayChat.getVisibility() == 0)) {
            this.chatActivityEnterView.onPause();
            replyMessage = this.replyingMessageObject;
            if (!this.chatActivityEnterView.isEditingMessage()) {
                CharSequence text = AndroidUtilities.getTrimmedString(this.chatActivityEnterView.getFieldText());
                if (!TextUtils.isEmpty(text)) {
                    if (!TextUtils.equals(text, "@gif")) {
                        draftMessage = text;
                    }
                }
            }
            searchWebpage = this.chatActivityEnterView.isMessageWebPageSearchEnabled();
            this.chatActivityEnterView.setFieldFocused(false);
        }
        if (this.chatAttachAlert != null) {
            if (this.ignoreAttachOnPause) {
                this.ignoreAttachOnPause = false;
            } else {
                this.chatAttachAlert.onPause();
            }
        }
        CharSequence[] message = new CharSequence[]{draftMessage};
        ArrayList<TLRPC$MessageEntity> entities = MessagesQuery.getEntities(message);
        long j = this.dialog_id;
        CharSequence charSequence = message[0];
        TLRPC$Message tLRPC$Message = replyMessage != null ? replyMessage.messageOwner : null;
        if (searchWebpage) {
            z = false;
        } else {
            z = true;
        }
        DraftQuery.saveDraft(j, charSequence, entities, tLRPC$Message, z);
        MessagesController.getInstance().cancelTyping(0, this.dialog_id);
        if (!this.pausedOnLastMessage) {
            Editor editor = ApplicationLoader.applicationContext.getSharedPreferences("Notifications", 0).edit();
            int messageId = 0;
            int offset = 0;
            if (this.chatLayoutManager != null) {
                int position = this.chatLayoutManager.findFirstVisibleItemPosition();
                if (position != 0) {
                    Holder holder = (Holder) this.chatListView.findViewHolderForAdapterPosition(position);
                    if (holder != null) {
                        if (holder.itemView instanceof ChatMessageCell) {
                            messageId = ((ChatMessageCell) holder.itemView).getMessageObject().getId();
                        } else if (holder.itemView instanceof ChatActionCell) {
                            messageId = ((ChatActionCell) holder.itemView).getMessageObject().getId();
                        }
                        if (messageId != 0) {
                            offset = holder.itemView.getBottom() - this.chatListView.getMeasuredHeight();
                            FileLog.d("save offset = " + offset + " for mid " + messageId);
                        }
                    }
                }
            }
            if (messageId != 0) {
                editor.putInt("diditem" + this.dialog_id, messageId);
                editor.putInt("diditemo" + this.dialog_id, offset);
            } else {
                this.pausedOnLastMessage = true;
                editor.remove("diditem" + this.dialog_id);
                editor.remove("diditemo" + this.dialog_id);
            }
            editor.commit();
        }
        if (this.currentUser != null) {
            this.chatLeaveTime = System.currentTimeMillis();
            updateInformationForScreenshotDetector();
        }
    }

    private void applyDraftMaybe(boolean canClear) {
        if (this.chatActivityEnterView != null) {
            TLRPC$DraftMessage draftMessage = DraftQuery.getDraft(this.dialog_id);
            TLRPC$Message draftReplyMessage = (draftMessage == null || draftMessage.reply_to_msg_id == 0) ? null : DraftQuery.getDraftMessage(this.dialog_id);
            if (this.chatActivityEnterView.getFieldText() == null) {
                if (draftMessage != null) {
                    CharSequence message;
                    this.chatActivityEnterView.setWebPage(null, !draftMessage.no_webpage);
                    if (draftMessage.entities.isEmpty()) {
                        message = draftMessage.message;
                    } else {
                        SpannableStringBuilder stringBuilder = SpannableStringBuilder.valueOf(draftMessage.message);
                        MessagesQuery.sortEntities(draftMessage.entities);
                        int addToOffset = 0;
                        for (int a = 0; a < draftMessage.entities.size(); a++) {
                            TLRPC$MessageEntity entity = (TLRPC$MessageEntity) draftMessage.entities.get(a);
                            if ((entity instanceof TLRPC$TL_inputMessageEntityMentionName) || (entity instanceof TLRPC$TL_messageEntityMentionName)) {
                                int user_id;
                                if (entity instanceof TLRPC$TL_inputMessageEntityMentionName) {
                                    user_id = ((TLRPC$TL_inputMessageEntityMentionName) entity).user_id.user_id;
                                } else {
                                    user_id = ((TLRPC$TL_messageEntityMentionName) entity).user_id;
                                }
                                if ((entity.offset + addToOffset) + entity.length < stringBuilder.length() && stringBuilder.charAt((entity.offset + addToOffset) + entity.length) == ' ') {
                                    entity.length++;
                                }
                                stringBuilder.setSpan(new URLSpanUserMention("" + user_id, true), entity.offset + addToOffset, (entity.offset + addToOffset) + entity.length, 33);
                            } else if (entity instanceof TLRPC$TL_messageEntityCode) {
                                stringBuilder.insert((entity.offset + entity.length) + addToOffset, "`");
                                stringBuilder.insert(entity.offset + addToOffset, "`");
                                addToOffset += 2;
                            } else if (entity instanceof TLRPC$TL_messageEntityPre) {
                                stringBuilder.insert((entity.offset + entity.length) + addToOffset, "```");
                                stringBuilder.insert(entity.offset + addToOffset, "```");
                                addToOffset += 6;
                            } else if (entity instanceof TLRPC$TL_messageEntityBold) {
                                stringBuilder.setSpan(new TypefaceSpan(AndroidUtilities.getTypeface("fonts/rmedium.ttf")), entity.offset + addToOffset, (entity.offset + entity.length) + addToOffset, 33);
                            } else if (entity instanceof TLRPC$TL_messageEntityItalic) {
                                stringBuilder.setSpan(new TypefaceSpan(AndroidUtilities.getTypeface("fonts/ritalic.ttf")), entity.offset + addToOffset, (entity.offset + entity.length) + addToOffset, 33);
                            }
                        }
                        message = stringBuilder;
                    }
                    this.chatActivityEnterView.setFieldText(message);
                    if (getArguments().getBoolean("hasUrl", false)) {
                        this.chatActivityEnterView.setSelection(draftMessage.message.indexOf(10) + 1);
                        AndroidUtilities.runOnUIThread(new Runnable() {
                            public void run() {
                                if (ChatActivity.this.chatActivityEnterView != null) {
                                    ChatActivity.this.chatActivityEnterView.setFieldFocused(true);
                                    ChatActivity.this.chatActivityEnterView.openKeyboard();
                                }
                            }
                        }, 700);
                    }
                }
            } else if (canClear && draftMessage == null) {
                this.chatActivityEnterView.setFieldText("");
                showReplyPanel(false, null, null, null, false);
            }
            if (this.replyingMessageObject == null && draftReplyMessage != null) {
                this.replyingMessageObject = new MessageObject(draftReplyMessage, MessagesController.getInstance().getUsers(), false);
                showReplyPanel(true, this.replyingMessageObject, null, null, false);
            }
        }
    }

    private void updateInformationForScreenshotDetector() {
        if (this.currentUser != null) {
            if (this.currentEncryptedChat != null) {
                ArrayList<Long> visibleMessages = new ArrayList();
                if (this.chatListView != null) {
                    int count = this.chatListView.getChildCount();
                    for (int a = 0; a < count; a++) {
                        View view = this.chatListView.getChildAt(a);
                        MessageObject object = null;
                        if (view instanceof ChatMessageCell) {
                            object = ((ChatMessageCell) view).getMessageObject();
                        }
                        if (!(object == null || object.getId() >= 0 || object.messageOwner.random_id == 0)) {
                            visibleMessages.add(Long.valueOf(object.messageOwner.random_id));
                        }
                    }
                }
                MediaController.getInstance().setLastVisibleMessageIds(this.chatEnterTime, this.chatLeaveTime, this.currentUser, this.currentEncryptedChat, visibleMessages, 0);
                return;
            }
            SecretMediaViewer viewer = SecretMediaViewer.getInstance();
            MessageObject messageObject = viewer.getCurrentMessageObject();
            if (messageObject != null && !messageObject.isOut()) {
                MediaController.getInstance().setLastVisibleMessageIds(viewer.getOpenTime(), viewer.getCloseTime(), this.currentUser, null, null, messageObject.getId());
            }
        }
    }

    private boolean fixLayoutInternal() {
        if (AndroidUtilities.isTablet() || ApplicationLoader.applicationContext.getResources().getConfiguration().orientation != 2) {
            this.selectedMessagesCountTextView.setTextSize(20);
        } else {
            this.selectedMessagesCountTextView.setTextSize(18);
        }
        HashMap<Long, GroupedMessages> newGroups = null;
        int count = this.chatListView.getChildCount();
        for (int a = 0; a < count; a++) {
            View child = this.chatListView.getChildAt(a);
            if (child instanceof ChatMessageCell) {
                GroupedMessages groupedMessages = ((ChatMessageCell) child).getCurrentMessagesGroup();
                if (groupedMessages != null && groupedMessages.hasSibling) {
                    if (newGroups == null) {
                        newGroups = new HashMap();
                    }
                    if (!newGroups.containsKey(Long.valueOf(groupedMessages.groupId))) {
                        newGroups.put(Long.valueOf(groupedMessages.groupId), groupedMessages);
                        int idx = this.messages.indexOf((MessageObject) groupedMessages.messages.get(groupedMessages.messages.size() - 1));
                        if (idx >= 0) {
                            this.chatAdapter.notifyItemRangeChanged(this.chatAdapter.messagesStartRow + idx, groupedMessages.messages.size());
                        }
                    }
                }
            }
        }
        if (!AndroidUtilities.isTablet()) {
            return true;
        }
        if (AndroidUtilities.isSmallTablet() && ApplicationLoader.applicationContext.getResources().getConfiguration().orientation == 1) {
            this.actionBar.setBackButtonDrawable(new BackDrawable(false));
            if (this.fragmentContextView != null && this.fragmentContextView.getParent() == null) {
                ((ViewGroup) this.fragmentView).addView(this.fragmentContextView, LayoutHelper.createFrame(-1, 39.0f, 51, 0.0f, -36.0f, 0.0f, 0.0f));
            }
        } else {
            ActionBar actionBar = this.actionBar;
            boolean z = this.parentLayout == null || this.parentLayout.fragmentsStack.isEmpty() || this.parentLayout.fragmentsStack.get(0) == this || this.parentLayout.fragmentsStack.size() == 1;
            actionBar.setBackButtonDrawable(new BackDrawable(z));
            if (!(this.fragmentContextView == null || this.fragmentContextView.getParent() == null)) {
                this.fragmentView.setPadding(0, 0, 0, 0);
                ((ViewGroup) this.fragmentView).removeView(this.fragmentContextView);
            }
        }
        return false;
    }

    private void fixLayout() {
        if (this.avatarContainer != null) {
            this.avatarContainer.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
                public boolean onPreDraw() {
                    if (ChatActivity.this.avatarContainer != null) {
                        ChatActivity.this.avatarContainer.getViewTreeObserver().removeOnPreDrawListener(this);
                    }
                    return ChatActivity.this.fixLayoutInternal();
                }
            });
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        fixLayout();
        if (this.visibleDialog instanceof DatePickerDialog) {
            this.visibleDialog.dismiss();
        }
    }

    private void createDeleteMessagesAlert(MessageObject finalSelectedObject, GroupedMessages selectedGroup) {
        createDeleteMessagesAlert(finalSelectedObject, selectedGroup, 1);
    }

    private void createDeleteMessagesAlert(MessageObject finalSelectedObject, GroupedMessages finalSelectedGroup, int loadParticipant) {
        if (getParentActivity() != null) {
            int count;
            AlertDialog.Builder builder = new AlertDialog.Builder(getParentActivity());
            if (finalSelectedGroup != null) {
                count = finalSelectedGroup.messages.size();
            } else if (finalSelectedObject != null) {
                count = 1;
            } else {
                count = this.selectedMessagesIds[0].size() + this.selectedMessagesIds[1].size();
            }
            builder.setMessage(LocaleController.formatString("AreYouSureDeleteMessages", R.string.AreYouSureDeleteMessages, new Object[]{LocaleController.formatPluralString("messages", count)}));
            builder.setTitle(LocaleController.getString("Message", R.string.Message));
            boolean[] checks = new boolean[3];
            boolean[] deleteForAll = new boolean[1];
            User user = null;
            boolean z;
            int currentDate;
            int a;
            MessageObject msg;
            boolean exit;
            View frameLayout;
            CheckBoxCell cell;
            final boolean[] zArr;
            if (this.currentChat != null && this.currentChat.megagroup) {
                z = false;
                boolean canBan = ChatObject.canBlockUsers(this.currentChat);
                currentDate = ConnectionsManager.getInstance().getCurrentTime();
                if (finalSelectedObject != null) {
                    if (finalSelectedObject.messageOwner.action == null || (finalSelectedObject.messageOwner.action instanceof TLRPC$TL_messageActionEmpty) || (finalSelectedObject.messageOwner.action instanceof TLRPC$TL_messageActionChatDeleteUser)) {
                        user = MessagesController.getInstance().getUser(Integer.valueOf(finalSelectedObject.messageOwner.from_id));
                    }
                    if (!finalSelectedObject.isSendError() && finalSelectedObject.getDialogId() == this.mergeDialogId && ((finalSelectedObject.messageOwner.action == null || (finalSelectedObject.messageOwner.action instanceof TLRPC$TL_messageActionEmpty)) && finalSelectedObject.isOut() && currentDate - finalSelectedObject.messageOwner.date <= 172800)) {
                        z = true;
                    } else {
                        z = false;
                    }
                } else {
                    int from_id = -1;
                    for (a = 1; a >= 0; a--) {
                        for (Entry<Integer, MessageObject> entry : this.selectedMessagesIds[a].entrySet()) {
                            msg = (MessageObject) entry.getValue();
                            if (from_id == -1) {
                                from_id = msg.messageOwner.from_id;
                            }
                            if (from_id >= 0) {
                                if (from_id != msg.messageOwner.from_id) {
                                }
                            }
                            from_id = -2;
                        }
                        if (from_id == -2) {
                            break;
                        }
                    }
                    exit = false;
                    for (a = 1; a >= 0; a--) {
                        for (Entry<Integer, MessageObject> entry2 : this.selectedMessagesIds[a].entrySet()) {
                            msg = (MessageObject) entry2.getValue();
                            if (a != 1) {
                                if (a == 0 && !msg.isOut()) {
                                    z = false;
                                    exit = true;
                                    break;
                                }
                            } else if (!msg.isOut() || msg.messageOwner.action != null) {
                                z = false;
                                exit = true;
                                break;
                            } else if (currentDate - msg.messageOwner.date <= 172800) {
                                z = true;
                            }
                        }
                        if (exit) {
                            break;
                        }
                    }
                    if (from_id != -1) {
                        user = MessagesController.getInstance().getUser(Integer.valueOf(from_id));
                    }
                }
                int dp;
                int dp2;
                if (user == null || user.id == UserConfig.getClientUserId() || loadParticipant == 2) {
                    if (z) {
                        frameLayout = new FrameLayout(getParentActivity());
                        cell = new CheckBoxCell(getParentActivity(), true);
                        cell.setBackgroundDrawable(Theme.getSelectorDrawable(false));
                        if (this.currentChat != null) {
                            cell.setText(LocaleController.getString("DeleteForAll", R.string.DeleteForAll), "", false, false);
                        } else {
                            cell.setText(LocaleController.formatString("DeleteForUser", R.string.DeleteForUser, new Object[]{UserObject.getFirstName(this.currentUser)}), "", false, false);
                        }
                        if (LocaleController.isRTL) {
                            dp = AndroidUtilities.dp(16.0f);
                        } else {
                            dp = AndroidUtilities.dp(8.0f);
                        }
                        if (LocaleController.isRTL) {
                            dp2 = AndroidUtilities.dp(8.0f);
                        } else {
                            dp2 = AndroidUtilities.dp(16.0f);
                        }
                        cell.setPadding(dp, 0, dp2, 0);
                        frameLayout.addView(cell, LayoutHelper.createFrame(-1, 48.0f, 51, 0.0f, 0.0f, 0.0f, 0.0f));
                        zArr = deleteForAll;
                        cell.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                boolean z;
                                CheckBoxCell cell = (CheckBoxCell) v;
                                boolean[] zArr = zArr;
                                if (zArr[0]) {
                                    z = false;
                                } else {
                                    z = true;
                                }
                                zArr[0] = z;
                                cell.setChecked(zArr[0], true);
                            }
                        });
                        builder.setView(frameLayout);
                    } else {
                        user = null;
                    }
                } else if (loadParticipant != 1 || this.currentChat.creator) {
                    frameLayout = new FrameLayout(getParentActivity());
                    int num = 0;
                    a = 0;
                    while (a < 3) {
                        if (canBan || a != 0) {
                            cell = new CheckBoxCell(getParentActivity(), true);
                            cell.setBackgroundDrawable(Theme.getSelectorDrawable(false));
                            cell.setTag(Integer.valueOf(a));
                            if (a == 0) {
                                cell.setText(LocaleController.getString("DeleteBanUser", R.string.DeleteBanUser), "", false, false);
                            } else if (a == 1) {
                                cell.setText(LocaleController.getString("DeleteReportSpam", R.string.DeleteReportSpam), "", false, false);
                            } else if (a == 2) {
                                cell.setText(LocaleController.formatString("DeleteAllFrom", R.string.DeleteAllFrom, new Object[]{ContactsController.formatName(user.first_name, user.last_name)}), "", false, false);
                            }
                            if (LocaleController.isRTL) {
                                dp = AndroidUtilities.dp(16.0f);
                            } else {
                                dp = AndroidUtilities.dp(8.0f);
                            }
                            if (LocaleController.isRTL) {
                                dp2 = AndroidUtilities.dp(8.0f);
                            } else {
                                dp2 = AndroidUtilities.dp(16.0f);
                            }
                            cell.setPadding(dp, 0, dp2, 0);
                            frameLayout.addView(cell, LayoutHelper.createFrame(-1, 48.0f, 51, 0.0f, (float) (num * 48), 0.0f, 0.0f));
                            zArr = checks;
                            cell.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    if (v.isEnabled()) {
                                        CheckBoxCell cell = (CheckBoxCell) v;
                                        Integer num = (Integer) cell.getTag();
                                        zArr[num.intValue()] = !zArr[num.intValue()];
                                        cell.setChecked(zArr[num.intValue()], true);
                                    }
                                }
                            });
                            num++;
                        }
                        a++;
                    }
                    builder.setView(frameLayout);
                } else {
                    AlertDialog[] progressDialog = new AlertDialog[]{new AlertDialog(getParentActivity(), 1)};
                    TLObject req = new TLRPC$TL_channels_getParticipant();
                    req.channel = MessagesController.getInputChannel(this.currentChat);
                    req.user_id = MessagesController.getInputUser(user);
                    final AlertDialog[] alertDialogArr = progressDialog;
                    final MessageObject messageObject = finalSelectedObject;
                    final GroupedMessages groupedMessages = finalSelectedGroup;
                    int requestId = ConnectionsManager.getInstance().sendRequest(req, new RequestDelegate() {
                        public void run(final TLObject response, TLRPC$TL_error error) {
                            AndroidUtilities.runOnUIThread(new Runnable() {
                                public void run() {
                                    try {
                                        alertDialogArr[0].dismiss();
                                    } catch (Throwable th) {
                                    }
                                    alertDialogArr[0] = null;
                                    int loadType = 2;
                                    if (response != null) {
                                        TLRPC$TL_channels_channelParticipant participant = response;
                                        if (!((participant.participant instanceof TLRPC$TL_channelParticipantAdmin) || (participant.participant instanceof TLRPC$TL_channelParticipantCreator))) {
                                            loadType = 0;
                                        }
                                    }
                                    ChatActivity.this.createDeleteMessagesAlert(messageObject, groupedMessages, loadType);
                                }
                            });
                        }
                    });
                    if (requestId != 0) {
                        alertDialogArr = progressDialog;
                        final int i = requestId;
                        AndroidUtilities.runOnUIThread(new Runnable() {

                            class C59021 implements OnClickListener {
                                C59021() {
                                }

                                public void onClick(DialogInterface dialog, int which) {
                                    ConnectionsManager.getInstance().cancelRequest(i, true);
                                    try {
                                        dialog.dismiss();
                                    } catch (Exception e) {
                                        FileLog.e(e);
                                    }
                                }
                            }

                            public void run() {
                                if (alertDialogArr[0] != null) {
                                    alertDialogArr[0].setMessage(LocaleController.getString("Loading", R.string.Loading));
                                    alertDialogArr[0].setCanceledOnTouchOutside(false);
                                    alertDialogArr[0].setCancelable(false);
                                    alertDialogArr[0].setButton(-2, LocaleController.getString("Cancel", R.string.Cancel), new C59021());
                                    ChatActivity.this.showDialog(alertDialogArr[0]);
                                }
                            }
                        }, 1000);
                        return;
                    }
                    return;
                }
            } else if (!ChatObject.isChannel(this.currentChat) && this.currentEncryptedChat == null) {
                z = false;
                currentDate = ConnectionsManager.getInstance().getCurrentTime();
                if ((this.currentUser != null && this.currentUser.id != UserConfig.getClientUserId() && !this.currentUser.bot) || this.currentChat != null) {
                    if (finalSelectedObject == null) {
                        exit = false;
                        for (a = 1; a >= 0; a--) {
                            for (Entry<Integer, MessageObject> entry22 : this.selectedMessagesIds[a].entrySet()) {
                                msg = (MessageObject) entry22.getValue();
                                if (msg.messageOwner.action == null) {
                                    if (!msg.isOut() && (this.currentChat == null || (!this.currentChat.creator && (!this.currentChat.admin || !this.currentChat.admins_enabled)))) {
                                        exit = true;
                                        z = false;
                                        break;
                                    } else if (!z && currentDate - msg.messageOwner.date <= 172800) {
                                        z = true;
                                    }
                                }
                            }
                            if (exit) {
                                break;
                            }
                        }
                    } else if (finalSelectedObject.isSendError() || (!(finalSelectedObject.messageOwner.action == null || (finalSelectedObject.messageOwner.action instanceof TLRPC$TL_messageActionEmpty)) || ((!finalSelectedObject.isOut() && (this.currentChat == null || !(this.currentChat.creator || (this.currentChat.admin && this.currentChat.admins_enabled)))) || currentDate - finalSelectedObject.messageOwner.date > 172800))) {
                        z = false;
                    } else {
                        z = true;
                    }
                }
                if (z) {
                    frameLayout = new FrameLayout(getParentActivity());
                    cell = new CheckBoxCell(getParentActivity(), true);
                    cell.setBackgroundDrawable(Theme.getSelectorDrawable(false));
                    if (this.currentChat != null) {
                        cell.setText(LocaleController.getString("DeleteForAll", R.string.DeleteForAll), "", false, false);
                    } else {
                        cell.setText(LocaleController.formatString("DeleteForUser", R.string.DeleteForUser, new Object[]{UserObject.getFirstName(this.currentUser)}), "", false, false);
                    }
                    cell.setPadding(LocaleController.isRTL ? AndroidUtilities.dp(16.0f) : AndroidUtilities.dp(8.0f), 0, LocaleController.isRTL ? AndroidUtilities.dp(8.0f) : AndroidUtilities.dp(16.0f), 0);
                    frameLayout.addView(cell, LayoutHelper.createFrame(-1, 48.0f, 51, 0.0f, 0.0f, 0.0f, 0.0f));
                    zArr = deleteForAll;
                    cell.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            boolean z;
                            CheckBoxCell cell = (CheckBoxCell) v;
                            boolean[] zArr = zArr;
                            if (zArr[0]) {
                                z = false;
                            } else {
                                z = true;
                            }
                            zArr[0] = z;
                            cell.setChecked(zArr[0], true);
                        }
                    });
                    builder.setView(frameLayout);
                }
            }
            final User userFinal = user;
            final MessageObject messageObject2 = finalSelectedObject;
            final GroupedMessages groupedMessages2 = finalSelectedGroup;
            final boolean[] zArr2 = deleteForAll;
            final boolean[] zArr3 = checks;
            builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new OnClickListener() {

                class C59031 implements RequestDelegate {
                    C59031() {
                    }

                    public void run(TLObject response, TLRPC$TL_error error) {
                    }
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    ArrayList<Integer> ids = null;
                    ArrayList<Long> random_ids;
                    int a;
                    if (messageObject2 != null) {
                        ids = new ArrayList();
                        random_ids = null;
                        if (groupedMessages2 != null) {
                            for (a = 0; a < groupedMessages2.messages.size(); a++) {
                                MessageObject messageObject = (MessageObject) groupedMessages2.messages.get(a);
                                ids.add(Integer.valueOf(messageObject.getId()));
                                if (!(ChatActivity.this.currentEncryptedChat == null || messageObject.messageOwner.random_id == 0 || messageObject.type == 10)) {
                                    if (random_ids == null) {
                                        random_ids = new ArrayList();
                                    }
                                    random_ids.add(Long.valueOf(messageObject.messageOwner.random_id));
                                }
                            }
                        } else {
                            ids.add(Integer.valueOf(messageObject2.getId()));
                            if (!(ChatActivity.this.currentEncryptedChat == null || messageObject2.messageOwner.random_id == 0 || messageObject2.type == 10)) {
                                random_ids = new ArrayList();
                                random_ids.add(Long.valueOf(messageObject2.messageOwner.random_id));
                            }
                        }
                        MessagesController.getInstance().deleteMessages(ids, random_ids, ChatActivity.this.currentEncryptedChat, messageObject2.messageOwner.to_id.channel_id, zArr2[0]);
                    } else {
                        for (a = 1; a >= 0; a--) {
                            MessageObject msg;
                            ids = new ArrayList(ChatActivity.this.selectedMessagesIds[a].keySet());
                            random_ids = null;
                            int channelId = 0;
                            if (!ids.isEmpty()) {
                                msg = (MessageObject) ChatActivity.this.selectedMessagesIds[a].get(ids.get(0));
                                if (null == null && msg.messageOwner.to_id.channel_id != 0) {
                                    channelId = msg.messageOwner.to_id.channel_id;
                                }
                            }
                            if (ChatActivity.this.currentEncryptedChat != null) {
                                random_ids = new ArrayList();
                                for (Entry<Integer, MessageObject> entry : ChatActivity.this.selectedMessagesIds[a].entrySet()) {
                                    msg = (MessageObject) entry.getValue();
                                    if (!(msg.messageOwner.random_id == 0 || msg.type == 10)) {
                                        random_ids.add(Long.valueOf(msg.messageOwner.random_id));
                                    }
                                }
                            }
                            MessagesController.getInstance().deleteMessages(ids, random_ids, ChatActivity.this.currentEncryptedChat, channelId, zArr2[0]);
                        }
                        ChatActivity.this.actionBar.hideActionMode();
                        ChatActivity.this.updatePinnedMessageView(true);
                    }
                    if (userFinal != null) {
                        if (zArr3[0]) {
                            MessagesController.getInstance().deleteUserFromChat(ChatActivity.this.currentChat.id, userFinal, ChatActivity.this.info);
                        }
                        if (zArr3[1]) {
                            TLRPC$TL_channels_reportSpam req = new TLRPC$TL_channels_reportSpam();
                            req.channel = MessagesController.getInputChannel(ChatActivity.this.currentChat);
                            req.user_id = MessagesController.getInputUser(userFinal);
                            req.id = ids;
                            ConnectionsManager.getInstance().sendRequest(req, new C59031());
                        }
                        if (zArr3[2]) {
                            MessagesController.getInstance().deleteUserChannelHistory(ChatActivity.this.currentChat, userFinal, 0);
                        }
                    }
                }
            });
            builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
            showDialog(builder.create());
        }
    }

    private void createMenu(View v, boolean single, boolean listView) {
        createMenu(v, single, listView, true);
    }

    private void createMenu(View v, boolean single, boolean listView, boolean searchGroup) {
        if (!this.actionBar.isActionModeShowed()) {
            MessageObject message = null;
            if (v instanceof ChatMessageCell) {
                message = ((ChatMessageCell) v).getMessageObject();
            } else if (v instanceof ChatActionCell) {
                message = ((ChatActionCell) v).getMessageObject();
            }
            if (message != null) {
                int type = getMessageType(message);
                if (single && (message.messageOwner.action instanceof TLRPC$TL_messageActionPinMessage)) {
                    scrollToMessageId(message.messageOwner.reply_to_msg_id, message.messageOwner.id, true, 0, false);
                    return;
                }
                int a;
                GroupedMessages groupedMessages;
                boolean allowEdit;
                AlertDialog.Builder builder;
                ArrayList<CharSequence> items;
                ArrayList<Integer> options;
                TLRPC$TL_messageActionPhoneCall call;
                Object string;
                User user;
                final ArrayList<Integer> arrayList;
                this.selectedObject = null;
                this.selectedObjectGroup = null;
                this.forwardingMessage = null;
                this.forwardingMessageGroup = null;
                for (a = 1; a >= 0; a--) {
                    this.selectedMessagesCanCopyIds[a].clear();
                    this.selectedMessagesCanStarIds[a].clear();
                    this.selectedMessagesIds[a].clear();
                }
                this.cantDeleteMessagesCount = 0;
                this.canEditMessagesCount = 0;
                this.actionBar.hideActionMode();
                updatePinnedMessageView(true);
                if (searchGroup) {
                    groupedMessages = getValidGroupedMessage(message);
                } else {
                    groupedMessages = null;
                }
                boolean allowChatActions = true;
                boolean allowPin = message.getDialogId() != this.mergeDialogId && message.getId() > 0 && ChatObject.isChannel(this.currentChat) && ((this.currentChat.creator || (this.currentChat.admin_rights != null && ((this.currentChat.megagroup && this.currentChat.admin_rights.pin_messages) || (!this.currentChat.megagroup && this.currentChat.admin_rights.edit_messages)))) && (message.messageOwner.action == null || (message.messageOwner.action instanceof TLRPC$TL_messageActionEmpty)));
                boolean allowUnpin = message.getDialogId() != this.mergeDialogId && this.info != null && this.info.pinned_msg_id == message.getId() && (this.currentChat.creator || (this.currentChat.admin_rights != null && ((this.currentChat.megagroup && this.currentChat.admin_rights.pin_messages) || (!this.currentChat.megagroup && this.currentChat.admin_rights.edit_messages))));
                if (groupedMessages == null) {
                    if (!(!message.canEditMessage(this.currentChat) || this.chatActivityEnterView.hasAudioToSend() || message.getDialogId() == this.mergeDialogId)) {
                        allowEdit = true;
                        if ((this.currentEncryptedChat != null && AndroidUtilities.getPeerLayerVersion(this.currentEncryptedChat.layer) < 46) || ((type == 1 && (message.getDialogId() == this.mergeDialogId || message.isSecretPhoto())) || ((this.currentEncryptedChat == null && message.getId() < 0) || ((this.bottomOverlayChat != null && this.bottomOverlayChat.getVisibility() == 0) || this.isBroadcast || (this.currentChat != null && (ChatObject.isNotInChat(this.currentChat) || !((!ChatObject.isChannel(this.currentChat) || ChatObject.canPost(this.currentChat) || this.currentChat.megagroup) && ChatObject.canSendMessages(this.currentChat)))))))) {
                            allowChatActions = false;
                        }
                        if (single && type >= 2 && type != 20 && !message.isSecretPhoto() && !message.isLiveLocation()) {
                            ActionBarMenu actionMode = this.actionBar.createActionMode();
                            View item = actionMode.getItem(11);
                            if (item != null) {
                                item.setVisibility(0);
                            }
                            item = actionMode.getItem(12);
                            if (item != null) {
                                item.setVisibility(0);
                            }
                            this.actionBar.showActionMode();
                            updatePinnedMessageView(true);
                            AnimatorSet animatorSet = new AnimatorSet();
                            ArrayList<Animator> animators = new ArrayList();
                            for (a = 0; a < this.actionModeViews.size(); a++) {
                                View view = (View) this.actionModeViews.get(a);
                                view.setPivotY((float) (ActionBar.getCurrentActionBarHeight() / 2));
                                AndroidUtilities.clearDrawableAnimation(view);
                                animators.add(ObjectAnimator.ofFloat(view, "scaleY", new float[]{0.1f, 1.0f}));
                            }
                            animatorSet.playTogether(animators);
                            animatorSet.setDuration(250);
                            animatorSet.start();
                            addToSelectedMessages(message, listView);
                            this.selectedMessagesCountTextView.setNumber(this.selectedMessagesIds[0].size() + this.selectedMessagesIds[1].size(), false);
                            updateVisibleRows();
                            return;
                        } else if (type >= 0) {
                            this.selectedObject = message;
                            this.selectedObjectGroup = groupedMessages;
                            if (getParentActivity() != null) {
                                builder = new AlertDialog.Builder(getParentActivity());
                                items = new ArrayList();
                                options = new ArrayList();
                                if (type == 0) {
                                    items.add(LocaleController.getString("Retry", R.string.Retry));
                                    options.add(Integer.valueOf(0));
                                    items.add(LocaleController.getString("Delete", R.string.Delete));
                                    options.add(Integer.valueOf(1));
                                } else if (type == 1) {
                                    if (this.currentChat != null || this.isBroadcast) {
                                        if (message.messageOwner.action != null && (message.messageOwner.action instanceof TLRPC$TL_messageActionPhoneCall)) {
                                            call = (TLRPC$TL_messageActionPhoneCall) message.messageOwner.action;
                                            if ((!(call.reason instanceof TLRPC$TL_phoneCallDiscardReasonMissed) || (call.reason instanceof TLRPC$TL_phoneCallDiscardReasonBusy)) && !message.isOutOwner()) {
                                                string = LocaleController.getString("CallBack", R.string.CallBack);
                                            } else {
                                                string = LocaleController.getString("CallAgain", R.string.CallAgain);
                                            }
                                            items.add(string);
                                            options.add(Integer.valueOf(18));
                                            if (VoIPHelper.canRateCall(call)) {
                                                items.add(LocaleController.getString("CallMessageReportProblem", R.string.CallMessageReportProblem));
                                                options.add(Integer.valueOf(19));
                                            }
                                        }
                                        if (single && this.selectedObject.getId() > 0 && allowChatActions) {
                                            items.add(LocaleController.getString("Reply", R.string.Reply));
                                            options.add(Integer.valueOf(8));
                                        }
                                        if (message.canDeleteMessage(this.currentChat)) {
                                            items.add(LocaleController.getString("Delete", R.string.Delete));
                                            options.add(Integer.valueOf(1));
                                        }
                                    } else {
                                        if (allowChatActions) {
                                            items.add(LocaleController.getString("Reply", R.string.Reply));
                                            options.add(Integer.valueOf(8));
                                        }
                                        if (allowUnpin) {
                                            items.add(LocaleController.getString("UnpinMessage", R.string.UnpinMessage));
                                            options.add(Integer.valueOf(14));
                                        } else if (allowPin) {
                                            items.add(LocaleController.getString("PinMessage", R.string.PinMessage));
                                            options.add(Integer.valueOf(13));
                                        }
                                        if (allowEdit) {
                                            items.add(LocaleController.getString("Edit", R.string.Edit));
                                            options.add(Integer.valueOf(12));
                                        }
                                        if (message.canDeleteMessage(this.currentChat)) {
                                            items.add(LocaleController.getString("Delete", R.string.Delete));
                                            options.add(Integer.valueOf(1));
                                        }
                                    }
                                } else if (type == 20) {
                                    items.add(LocaleController.getString("Retry", R.string.Retry));
                                    options.add(Integer.valueOf(0));
                                    items.add(LocaleController.getString("Copy", R.string.Copy));
                                    options.add(Integer.valueOf(3));
                                    items.add(LocaleController.getString("Delete", R.string.Delete));
                                    options.add(Integer.valueOf(1));
                                } else if (this.currentEncryptedChat != null) {
                                    if (allowChatActions) {
                                        items.add(LocaleController.getString("Reply", R.string.Reply));
                                        options.add(Integer.valueOf(8));
                                    }
                                    if (this.selectedObject.type == 0 || this.selectedObject.caption != null) {
                                        items.add(LocaleController.getString("Copy", R.string.Copy));
                                        options.add(Integer.valueOf(3));
                                    }
                                    if (ChatObject.isChannel(this.currentChat) && this.currentChat.megagroup && !TextUtils.isEmpty(this.currentChat.username) && ChatObject.hasAdminRights(this.currentChat)) {
                                        items.add(LocaleController.getString("CopyLink", R.string.CopyLink));
                                        options.add(Integer.valueOf(22));
                                    }
                                    if (type == 3) {
                                        if ((this.selectedObject.messageOwner.media instanceof TLRPC$TL_messageMediaWebPage) && MessageObject.isNewGifDocument(this.selectedObject.messageOwner.media.webpage.document)) {
                                            items.add(LocaleController.getString("SaveToGIFs", R.string.SaveToGIFs));
                                            options.add(Integer.valueOf(11));
                                        }
                                    } else if (type != 4) {
                                        if (this.selectedObject.isVideo()) {
                                            if (this.selectedObject.isMusic()) {
                                                items.add(LocaleController.getString("SaveToMusic", R.string.SaveToMusic));
                                                options.add(Integer.valueOf(10));
                                                items.add(LocaleController.getString("ShareFile", R.string.ShareFile));
                                                options.add(Integer.valueOf(6));
                                            } else if (this.selectedObject.getDocument() != null) {
                                                if (MessageObject.isNewGifDocument(this.selectedObject.getDocument())) {
                                                    items.add(LocaleController.getString("SaveToGIFs", R.string.SaveToGIFs));
                                                    options.add(Integer.valueOf(11));
                                                }
                                                items.add(LocaleController.getString("SaveToDownloads", R.string.SaveToDownloads));
                                                options.add(Integer.valueOf(10));
                                                items.add(LocaleController.getString("ShareFile", R.string.ShareFile));
                                                options.add(Integer.valueOf(6));
                                            } else if (!this.selectedObject.isSecretPhoto()) {
                                                items.add(LocaleController.getString("SaveToGallery", R.string.SaveToGallery));
                                                options.add(Integer.valueOf(4));
                                            }
                                        } else if (!this.selectedObject.isSecretPhoto()) {
                                            items.add(LocaleController.getString("SaveToGallery", R.string.SaveToGallery));
                                            options.add(Integer.valueOf(4));
                                            items.add(LocaleController.getString("ShareFile", R.string.ShareFile));
                                            options.add(Integer.valueOf(6));
                                        }
                                    } else if (type == 5) {
                                        items.add(LocaleController.getString("ApplyLocalizationFile", R.string.ApplyLocalizationFile));
                                        options.add(Integer.valueOf(5));
                                        items.add(LocaleController.getString("SaveToDownloads", R.string.SaveToDownloads));
                                        options.add(Integer.valueOf(10));
                                        items.add(LocaleController.getString("ShareFile", R.string.ShareFile));
                                        options.add(Integer.valueOf(6));
                                    } else if (type == 10) {
                                        items.add(LocaleController.getString("ApplyThemeFile", R.string.ApplyThemeFile));
                                        options.add(Integer.valueOf(5));
                                        items.add(LocaleController.getString("SaveToDownloads", R.string.SaveToDownloads));
                                        options.add(Integer.valueOf(10));
                                        items.add(LocaleController.getString("ShareFile", R.string.ShareFile));
                                        options.add(Integer.valueOf(6));
                                    } else if (type == 6) {
                                        items.add(LocaleController.getString("SaveToGallery", R.string.SaveToGallery));
                                        options.add(Integer.valueOf(7));
                                        items.add(LocaleController.getString("SaveToDownloads", R.string.SaveToDownloads));
                                        options.add(Integer.valueOf(10));
                                        items.add(LocaleController.getString("ShareFile", R.string.ShareFile));
                                        options.add(Integer.valueOf(6));
                                    } else if (type != 7) {
                                        if (this.selectedObject.isMask()) {
                                            items.add(LocaleController.getString("AddToStickers", R.string.AddToStickers));
                                            options.add(Integer.valueOf(9));
                                            if (!StickersQuery.isStickerInFavorites(this.selectedObject.getDocument())) {
                                                items.add(LocaleController.getString("DeleteFromFavorites", R.string.DeleteFromFavorites));
                                                options.add(Integer.valueOf(21));
                                            } else if (StickersQuery.canAddStickerToFavorites()) {
                                                items.add(LocaleController.getString("AddToFavorites", R.string.AddToFavorites));
                                                options.add(Integer.valueOf(20));
                                            }
                                        } else {
                                            items.add(LocaleController.getString("AddToMasks", R.string.AddToMasks));
                                            options.add(Integer.valueOf(9));
                                        }
                                    } else if (type == 8) {
                                        user = MessagesController.getInstance().getUser(Integer.valueOf(this.selectedObject.messageOwner.media.user_id));
                                        if (!(user == null || user.id == UserConfig.getClientUserId() || ContactsController.getInstance().contactsDict.get(Integer.valueOf(user.id)) != null)) {
                                            items.add(LocaleController.getString("AddContactTitle", R.string.AddContactTitle));
                                            options.add(Integer.valueOf(15));
                                        }
                                        if (!TextUtils.isEmpty(this.selectedObject.messageOwner.media.phone_number)) {
                                            items.add(LocaleController.getString("Copy", R.string.Copy));
                                            options.add(Integer.valueOf(16));
                                            items.add(LocaleController.getString("Call", R.string.Call));
                                            options.add(Integer.valueOf(17));
                                        }
                                    } else if (type == 9) {
                                        if (StickersQuery.isStickerInFavorites(this.selectedObject.getDocument())) {
                                            items.add(LocaleController.getString("AddToFavorites", R.string.AddToFavorites));
                                            options.add(Integer.valueOf(20));
                                        } else {
                                            items.add(LocaleController.getString("DeleteFromFavorites", R.string.DeleteFromFavorites));
                                            options.add(Integer.valueOf(21));
                                        }
                                    }
                                    if (!(this.selectedObject.isSecretPhoto() || this.selectedObject.isLiveLocation())) {
                                        items.add(LocaleController.getString("Forward", R.string.Forward));
                                        options.add(Integer.valueOf(2));
                                    }
                                    if (allowUnpin) {
                                        items.add(LocaleController.getString("UnpinMessage", R.string.UnpinMessage));
                                        options.add(Integer.valueOf(14));
                                    } else if (allowPin) {
                                        items.add(LocaleController.getString("PinMessage", R.string.PinMessage));
                                        options.add(Integer.valueOf(13));
                                    }
                                    if (allowEdit) {
                                        items.add(LocaleController.getString("Edit", R.string.Edit));
                                        options.add(Integer.valueOf(12));
                                    }
                                    if (message.canDeleteMessage(this.currentChat)) {
                                        items.add(LocaleController.getString("Delete", R.string.Delete));
                                        options.add(Integer.valueOf(1));
                                    }
                                } else {
                                    if (allowChatActions) {
                                        items.add(LocaleController.getString("Reply", R.string.Reply));
                                        options.add(Integer.valueOf(8));
                                    }
                                    if (this.selectedObject.type == 0 || this.selectedObject.caption != null) {
                                        items.add(LocaleController.getString("Copy", R.string.Copy));
                                        options.add(Integer.valueOf(3));
                                    }
                                    if (type != 4) {
                                        if (this.selectedObject.isVideo()) {
                                            items.add(LocaleController.getString("SaveToGallery", R.string.SaveToGallery));
                                            options.add(Integer.valueOf(4));
                                            items.add(LocaleController.getString("ShareFile", R.string.ShareFile));
                                            options.add(Integer.valueOf(6));
                                        } else if (this.selectedObject.isMusic()) {
                                            items.add(LocaleController.getString("SaveToMusic", R.string.SaveToMusic));
                                            options.add(Integer.valueOf(10));
                                            items.add(LocaleController.getString("ShareFile", R.string.ShareFile));
                                            options.add(Integer.valueOf(6));
                                        } else if (!this.selectedObject.isVideo() || this.selectedObject.getDocument() == null) {
                                            items.add(LocaleController.getString("SaveToGallery", R.string.SaveToGallery));
                                            options.add(Integer.valueOf(4));
                                        } else {
                                            items.add(LocaleController.getString("SaveToDownloads", R.string.SaveToDownloads));
                                            options.add(Integer.valueOf(10));
                                            items.add(LocaleController.getString("ShareFile", R.string.ShareFile));
                                            options.add(Integer.valueOf(6));
                                        }
                                    } else if (type == 5) {
                                        items.add(LocaleController.getString("ApplyLocalizationFile", R.string.ApplyLocalizationFile));
                                        options.add(Integer.valueOf(5));
                                    } else if (type == 10) {
                                        items.add(LocaleController.getString("ApplyThemeFile", R.string.ApplyThemeFile));
                                        options.add(Integer.valueOf(5));
                                    } else if (type == 7) {
                                        items.add(LocaleController.getString("AddToStickers", R.string.AddToStickers));
                                        options.add(Integer.valueOf(9));
                                    }
                                    items.add(LocaleController.getString("Delete", R.string.Delete));
                                    options.add(Integer.valueOf(1));
                                }
                                if (!options.isEmpty()) {
                                    arrayList = options;
                                    builder.setItems((CharSequence[]) items.toArray(new CharSequence[items.size()]), new OnClickListener() {
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            if (ChatActivity.this.selectedObject != null && i >= 0 && i < arrayList.size()) {
                                                ChatActivity.this.processSelectedOption(((Integer) arrayList.get(i)).intValue());
                                            }
                                        }
                                    });
                                    builder.setTitle(LocaleController.getString("Message", R.string.Message));
                                    showDialog(builder.create());
                                }
                            }
                        }
                    }
                }
                allowEdit = false;
                allowChatActions = false;
                if (single) {
                }
                if (type >= 0) {
                    this.selectedObject = message;
                    this.selectedObjectGroup = groupedMessages;
                    if (getParentActivity() != null) {
                        builder = new AlertDialog.Builder(getParentActivity());
                        items = new ArrayList();
                        options = new ArrayList();
                        if (type == 0) {
                            items.add(LocaleController.getString("Retry", R.string.Retry));
                            options.add(Integer.valueOf(0));
                            items.add(LocaleController.getString("Delete", R.string.Delete));
                            options.add(Integer.valueOf(1));
                        } else if (type == 1) {
                            if (this.currentChat != null) {
                            }
                            call = (TLRPC$TL_messageActionPhoneCall) message.messageOwner.action;
                            if (call.reason instanceof TLRPC$TL_phoneCallDiscardReasonMissed) {
                            }
                            string = LocaleController.getString("CallBack", R.string.CallBack);
                            items.add(string);
                            options.add(Integer.valueOf(18));
                            if (VoIPHelper.canRateCall(call)) {
                                items.add(LocaleController.getString("CallMessageReportProblem", R.string.CallMessageReportProblem));
                                options.add(Integer.valueOf(19));
                            }
                            items.add(LocaleController.getString("Reply", R.string.Reply));
                            options.add(Integer.valueOf(8));
                            if (message.canDeleteMessage(this.currentChat)) {
                                items.add(LocaleController.getString("Delete", R.string.Delete));
                                options.add(Integer.valueOf(1));
                            }
                        } else if (type == 20) {
                            items.add(LocaleController.getString("Retry", R.string.Retry));
                            options.add(Integer.valueOf(0));
                            items.add(LocaleController.getString("Copy", R.string.Copy));
                            options.add(Integer.valueOf(3));
                            items.add(LocaleController.getString("Delete", R.string.Delete));
                            options.add(Integer.valueOf(1));
                        } else if (this.currentEncryptedChat != null) {
                            if (allowChatActions) {
                                items.add(LocaleController.getString("Reply", R.string.Reply));
                                options.add(Integer.valueOf(8));
                            }
                            items.add(LocaleController.getString("Copy", R.string.Copy));
                            options.add(Integer.valueOf(3));
                            if (type != 4) {
                                if (type == 5) {
                                    items.add(LocaleController.getString("ApplyLocalizationFile", R.string.ApplyLocalizationFile));
                                    options.add(Integer.valueOf(5));
                                } else if (type == 10) {
                                    items.add(LocaleController.getString("ApplyThemeFile", R.string.ApplyThemeFile));
                                    options.add(Integer.valueOf(5));
                                } else if (type == 7) {
                                    items.add(LocaleController.getString("AddToStickers", R.string.AddToStickers));
                                    options.add(Integer.valueOf(9));
                                }
                            } else if (this.selectedObject.isVideo()) {
                                items.add(LocaleController.getString("SaveToGallery", R.string.SaveToGallery));
                                options.add(Integer.valueOf(4));
                                items.add(LocaleController.getString("ShareFile", R.string.ShareFile));
                                options.add(Integer.valueOf(6));
                            } else if (this.selectedObject.isMusic()) {
                                items.add(LocaleController.getString("SaveToMusic", R.string.SaveToMusic));
                                options.add(Integer.valueOf(10));
                                items.add(LocaleController.getString("ShareFile", R.string.ShareFile));
                                options.add(Integer.valueOf(6));
                            } else {
                                if (this.selectedObject.isVideo()) {
                                }
                                items.add(LocaleController.getString("SaveToGallery", R.string.SaveToGallery));
                                options.add(Integer.valueOf(4));
                            }
                            items.add(LocaleController.getString("Delete", R.string.Delete));
                            options.add(Integer.valueOf(1));
                        } else {
                            if (allowChatActions) {
                                items.add(LocaleController.getString("Reply", R.string.Reply));
                                options.add(Integer.valueOf(8));
                            }
                            items.add(LocaleController.getString("Copy", R.string.Copy));
                            options.add(Integer.valueOf(3));
                            items.add(LocaleController.getString("CopyLink", R.string.CopyLink));
                            options.add(Integer.valueOf(22));
                            if (type == 3) {
                                items.add(LocaleController.getString("SaveToGIFs", R.string.SaveToGIFs));
                                options.add(Integer.valueOf(11));
                            } else if (type != 4) {
                                if (type == 5) {
                                    items.add(LocaleController.getString("ApplyLocalizationFile", R.string.ApplyLocalizationFile));
                                    options.add(Integer.valueOf(5));
                                    items.add(LocaleController.getString("SaveToDownloads", R.string.SaveToDownloads));
                                    options.add(Integer.valueOf(10));
                                    items.add(LocaleController.getString("ShareFile", R.string.ShareFile));
                                    options.add(Integer.valueOf(6));
                                } else if (type == 10) {
                                    items.add(LocaleController.getString("ApplyThemeFile", R.string.ApplyThemeFile));
                                    options.add(Integer.valueOf(5));
                                    items.add(LocaleController.getString("SaveToDownloads", R.string.SaveToDownloads));
                                    options.add(Integer.valueOf(10));
                                    items.add(LocaleController.getString("ShareFile", R.string.ShareFile));
                                    options.add(Integer.valueOf(6));
                                } else if (type == 6) {
                                    items.add(LocaleController.getString("SaveToGallery", R.string.SaveToGallery));
                                    options.add(Integer.valueOf(7));
                                    items.add(LocaleController.getString("SaveToDownloads", R.string.SaveToDownloads));
                                    options.add(Integer.valueOf(10));
                                    items.add(LocaleController.getString("ShareFile", R.string.ShareFile));
                                    options.add(Integer.valueOf(6));
                                } else if (type != 7) {
                                    if (type == 8) {
                                        user = MessagesController.getInstance().getUser(Integer.valueOf(this.selectedObject.messageOwner.media.user_id));
                                        items.add(LocaleController.getString("AddContactTitle", R.string.AddContactTitle));
                                        options.add(Integer.valueOf(15));
                                        if (TextUtils.isEmpty(this.selectedObject.messageOwner.media.phone_number)) {
                                            items.add(LocaleController.getString("Copy", R.string.Copy));
                                            options.add(Integer.valueOf(16));
                                            items.add(LocaleController.getString("Call", R.string.Call));
                                            options.add(Integer.valueOf(17));
                                        }
                                    } else if (type == 9) {
                                        if (StickersQuery.isStickerInFavorites(this.selectedObject.getDocument())) {
                                            items.add(LocaleController.getString("DeleteFromFavorites", R.string.DeleteFromFavorites));
                                            options.add(Integer.valueOf(21));
                                        } else {
                                            items.add(LocaleController.getString("AddToFavorites", R.string.AddToFavorites));
                                            options.add(Integer.valueOf(20));
                                        }
                                    }
                                } else if (this.selectedObject.isMask()) {
                                    items.add(LocaleController.getString("AddToStickers", R.string.AddToStickers));
                                    options.add(Integer.valueOf(9));
                                    if (!StickersQuery.isStickerInFavorites(this.selectedObject.getDocument())) {
                                        items.add(LocaleController.getString("DeleteFromFavorites", R.string.DeleteFromFavorites));
                                        options.add(Integer.valueOf(21));
                                    } else if (StickersQuery.canAddStickerToFavorites()) {
                                        items.add(LocaleController.getString("AddToFavorites", R.string.AddToFavorites));
                                        options.add(Integer.valueOf(20));
                                    }
                                } else {
                                    items.add(LocaleController.getString("AddToMasks", R.string.AddToMasks));
                                    options.add(Integer.valueOf(9));
                                }
                            } else if (this.selectedObject.isVideo()) {
                                if (this.selectedObject.isMusic()) {
                                    items.add(LocaleController.getString("SaveToMusic", R.string.SaveToMusic));
                                    options.add(Integer.valueOf(10));
                                    items.add(LocaleController.getString("ShareFile", R.string.ShareFile));
                                    options.add(Integer.valueOf(6));
                                } else if (this.selectedObject.getDocument() != null) {
                                    if (MessageObject.isNewGifDocument(this.selectedObject.getDocument())) {
                                        items.add(LocaleController.getString("SaveToGIFs", R.string.SaveToGIFs));
                                        options.add(Integer.valueOf(11));
                                    }
                                    items.add(LocaleController.getString("SaveToDownloads", R.string.SaveToDownloads));
                                    options.add(Integer.valueOf(10));
                                    items.add(LocaleController.getString("ShareFile", R.string.ShareFile));
                                    options.add(Integer.valueOf(6));
                                } else if (this.selectedObject.isSecretPhoto()) {
                                    items.add(LocaleController.getString("SaveToGallery", R.string.SaveToGallery));
                                    options.add(Integer.valueOf(4));
                                }
                            } else if (this.selectedObject.isSecretPhoto()) {
                                items.add(LocaleController.getString("SaveToGallery", R.string.SaveToGallery));
                                options.add(Integer.valueOf(4));
                                items.add(LocaleController.getString("ShareFile", R.string.ShareFile));
                                options.add(Integer.valueOf(6));
                            }
                            items.add(LocaleController.getString("Forward", R.string.Forward));
                            options.add(Integer.valueOf(2));
                            if (allowUnpin) {
                                items.add(LocaleController.getString("UnpinMessage", R.string.UnpinMessage));
                                options.add(Integer.valueOf(14));
                            } else if (allowPin) {
                                items.add(LocaleController.getString("PinMessage", R.string.PinMessage));
                                options.add(Integer.valueOf(13));
                            }
                            if (allowEdit) {
                                items.add(LocaleController.getString("Edit", R.string.Edit));
                                options.add(Integer.valueOf(12));
                            }
                            if (message.canDeleteMessage(this.currentChat)) {
                                items.add(LocaleController.getString("Delete", R.string.Delete));
                                options.add(Integer.valueOf(1));
                            }
                        }
                        if (!options.isEmpty()) {
                            arrayList = options;
                            builder.setItems((CharSequence[]) items.toArray(new CharSequence[items.size()]), /* anonymous class already generated */);
                            builder.setTitle(LocaleController.getString("Message", R.string.Message));
                            showDialog(builder.create());
                        }
                    }
                }
            }
        }
    }

    private void startEditingMessageObject(MessageObject messageObject) {
        if (messageObject != null && getParentActivity() != null) {
            boolean z;
            if (this.searchItem != null && this.actionBar.isSearchFieldVisible()) {
                this.actionBar.closeSearchField();
                this.chatActivityEnterView.setFieldFocused();
            }
            this.mentionsAdapter.setNeedBotContext(false);
            this.chatListView.setOnItemLongClickListener(null);
            this.chatListView.setOnItemClickListener((OnItemClickListenerExtended) null);
            this.chatListView.setClickable(false);
            this.chatListView.setLongClickable(false);
            ChatActivityEnterView chatActivityEnterView = this.chatActivityEnterView;
            if (messageObject.isMediaEmpty()) {
                z = false;
            } else {
                z = true;
            }
            chatActivityEnterView.setEditingMessageObject(messageObject, z);
            updateBottomOverlay();
            if (this.chatActivityEnterView.isEditingCaption()) {
                this.mentionsAdapter.setAllowNewMentions(false);
            }
            this.actionModeTitleContainer.setVisibility(0);
            this.selectedMessagesCountTextView.setVisibility(8);
            checkEditTimer();
            this.chatActivityEnterView.setAllowStickersAndGifs(false, false);
            ActionBarMenu actionMode = this.actionBar.createActionMode();
            actionMode.getItem(19).setVisibility(8);
            actionMode.getItem(10).setVisibility(8);
            actionMode.getItem(11).setVisibility(8);
            actionMode.getItem(12).setVisibility(8);
            actionMode.getItem(23).setVisibility(8);
            this.actionBar.showActionMode();
            updatePinnedMessageView(true);
            updateVisibleRows();
            TLRPC$TL_messages_getMessageEditData req = new TLRPC$TL_messages_getMessageEditData();
            req.peer = MessagesController.getInputPeer((int) this.dialog_id);
            req.id = messageObject.getId();
            this.editingMessageObjectReqId = ConnectionsManager.getInstance().sendRequest(req, new RequestDelegate() {
                public void run(final TLObject response, TLRPC$TL_error error) {
                    AndroidUtilities.runOnUIThread(new Runnable() {
                        public void run() {
                            ChatActivity.this.editingMessageObjectReqId = 0;
                            if (response == null) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ChatActivity.this.getParentActivity());
                                builder.setTitle(ApplicationLoader.getConfig().getAppName());
                                builder.setMessage(LocaleController.getString("EditMessageError", R.string.EditMessageError));
                                builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), null);
                                ChatActivity.this.showDialog(builder.create());
                                if (ChatActivity.this.chatActivityEnterView != null) {
                                    ChatActivity.this.chatActivityEnterView.setEditingMessageObject(null, false);
                                }
                            } else if (ChatActivity.this.chatActivityEnterView != null) {
                                ChatActivity.this.chatActivityEnterView.showEditDoneProgress(false, true);
                            }
                        }
                    });
                }
            });
        }
    }

    private String getMessageContent(MessageObject messageObject, int previousUid, boolean name) {
        String str = "";
        if (name && previousUid != messageObject.messageOwner.from_id) {
            if (messageObject.messageOwner.from_id > 0) {
                User user = MessagesController.getInstance().getUser(Integer.valueOf(messageObject.messageOwner.from_id));
                if (user != null) {
                    str = ContactsController.formatName(user.first_name, user.last_name) + ":\n";
                }
            } else if (messageObject.messageOwner.from_id < 0) {
                TLRPC$Chat chat = MessagesController.getInstance().getChat(Integer.valueOf(-messageObject.messageOwner.from_id));
                if (chat != null) {
                    str = chat.title + ":\n";
                }
            }
        }
        if (messageObject.type == 0 && messageObject.messageOwner.message != null) {
            return str + messageObject.messageOwner.message;
        }
        if (messageObject.messageOwner.media == null || messageObject.messageOwner.media.caption == null) {
            return str + messageObject.messageText;
        }
        return str + messageObject.messageOwner.media.caption;
    }

    private void saveMessageToGallery(MessageObject messageObject) {
        String path = messageObject.messageOwner.attachPath;
        if (!(TextUtils.isEmpty(path) || new File(path).exists())) {
            path = null;
        }
        if (TextUtils.isEmpty(path)) {
            path = FileLoader.getPathToMessage(messageObject.messageOwner).toString();
        }
        MediaController.saveFile(path, getParentActivity(), messageObject.type == 3 ? 1 : 0, null, null);
    }

    private void processSelectedOption(int option) {
        if (this.selectedObject != null) {
            int a;
            Bundle args;
            File file;
            AlertDialog.Builder builder;
            String path;
            Intent intent;
            switch (option) {
                case 0:
                    if (this.selectedObjectGroup == null) {
                        if (SendMessagesHelper.getInstance().retrySendMessage(this.selectedObject, false)) {
                            moveScrollToLastMessage();
                            break;
                        }
                    }
                    boolean success = true;
                    for (a = 0; a < this.selectedObjectGroup.messages.size(); a++) {
                        if (!SendMessagesHelper.getInstance().retrySendMessage((MessageObject) this.selectedObjectGroup.messages.get(a), false)) {
                            success = false;
                        }
                    }
                    if (success) {
                        moveScrollToLastMessage();
                        break;
                    }
                    break;
                case 1:
                    if (getParentActivity() != null) {
                        createDeleteMessagesAlert(this.selectedObject, this.selectedObjectGroup);
                        break;
                    }
                    this.selectedObject = null;
                    this.selectedObjectGroup = null;
                    return;
                case 2:
                    this.forwardingMessage = this.selectedObject;
                    this.forwardingMessageGroup = this.selectedObjectGroup;
                    args = new Bundle();
                    args.putBoolean("onlySelect", true);
                    args.putInt("dialogsType", 3);
                    BaseFragment dialogsActivity = new DialogsActivity(args);
                    dialogsActivity.setDelegate(this);
                    presentFragment(dialogsActivity);
                    break;
                case 3:
                    AndroidUtilities.addToClipboard(getMessageContent(this.selectedObject, 0, false));
                    break;
                case 4:
                    if (VERSION.SDK_INT < 23 || getParentActivity().checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
                        if (this.selectedObjectGroup == null) {
                            saveMessageToGallery(this.selectedObject);
                            break;
                        }
                        for (a = 0; a < this.selectedObjectGroup.messages.size(); a++) {
                            saveMessageToGallery((MessageObject) this.selectedObjectGroup.messages.get(a));
                        }
                        break;
                    }
                    getParentActivity().requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 4);
                    this.selectedObject = null;
                    this.selectedObjectGroup = null;
                    return;
                case 5:
                    File locFile = null;
                    if (!TextUtils.isEmpty(this.selectedObject.messageOwner.attachPath)) {
                        file = new File(this.selectedObject.messageOwner.attachPath);
                        if (file.exists()) {
                            locFile = file;
                        }
                    }
                    if (locFile == null) {
                        File f = FileLoader.getPathToMessage(this.selectedObject.messageOwner);
                        if (f.exists()) {
                            locFile = f;
                        }
                    }
                    if (locFile != null) {
                        if (!locFile.getName().endsWith("attheme")) {
                            if (!LocaleController.getInstance().applyLanguageFile(locFile)) {
                                if (getParentActivity() != null) {
                                    builder = new AlertDialog.Builder(getParentActivity());
                                    builder.setTitle(ApplicationLoader.getConfig().getAppName());
                                    builder.setMessage(LocaleController.getString("IncorrectLocalization", R.string.IncorrectLocalization));
                                    builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), null);
                                    showDialog(builder.create());
                                    break;
                                }
                                this.selectedObject = null;
                                this.selectedObjectGroup = null;
                                return;
                            }
                            presentFragment(new LanguageSelectActivity());
                            break;
                        }
                        if (this.chatLayoutManager != null) {
                            int lastPosition = this.chatLayoutManager.findFirstVisibleItemPosition();
                            if (lastPosition != 0) {
                                this.scrollToPositionOnRecreate = lastPosition;
                                Holder holder = (Holder) this.chatListView.findViewHolderForAdapterPosition(this.scrollToPositionOnRecreate);
                                if (holder != null) {
                                    this.scrollToOffsetOnRecreate = (this.chatListView.getMeasuredHeight() - holder.itemView.getBottom()) - this.chatListView.getPaddingBottom();
                                } else {
                                    this.scrollToPositionOnRecreate = -1;
                                }
                            } else {
                                this.scrollToPositionOnRecreate = -1;
                            }
                        }
                        ThemeInfo themeInfo = Theme.applyThemeFile(locFile, this.selectedObject.getDocumentName(), true);
                        if (themeInfo == null) {
                            this.scrollToPositionOnRecreate = -1;
                            if (getParentActivity() != null) {
                                builder = new AlertDialog.Builder(getParentActivity());
                                builder.setTitle(ApplicationLoader.getConfig().getAppName());
                                builder.setMessage(LocaleController.getString("IncorrectTheme", R.string.IncorrectTheme));
                                builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), null);
                                showDialog(builder.create());
                                break;
                            }
                            this.selectedObject = null;
                            this.selectedObjectGroup = null;
                            return;
                        }
                        presentFragment(new ThemePreviewActivity(locFile, themeInfo));
                        break;
                    }
                    break;
                case 6:
                    path = this.selectedObject.messageOwner.attachPath;
                    if (!(path == null || path.length() <= 0 || new File(path).exists())) {
                        path = null;
                    }
                    if (path == null || path.length() == 0) {
                        path = FileLoader.getPathToMessage(this.selectedObject.messageOwner).toString();
                    }
                    intent = new Intent("android.intent.action.SEND");
                    intent.setType(this.selectedObject.getDocument().mime_type);
                    file = new File(path);
                    if (VERSION.SDK_INT >= 24) {
                        try {
                            intent.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(getParentActivity(), "org.telegram.messenger.provider", file));
                            intent.setFlags(1);
                        } catch (Exception e) {
                            intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(file));
                        }
                    } else {
                        intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(file));
                    }
                    getParentActivity().startActivityForResult(Intent.createChooser(intent, LocaleController.getString("ShareFile", R.string.ShareFile)), HttpStatus.SC_INTERNAL_SERVER_ERROR);
                    break;
                case 7:
                    path = this.selectedObject.messageOwner.attachPath;
                    if (!(path == null || path.length() <= 0 || new File(path).exists())) {
                        path = null;
                    }
                    if (path == null || path.length() == 0) {
                        path = FileLoader.getPathToMessage(this.selectedObject.messageOwner).toString();
                    }
                    if (VERSION.SDK_INT < 23 || getParentActivity().checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
                        MediaController.saveFile(path, getParentActivity(), 0, null, null);
                        break;
                    }
                    getParentActivity().requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 4);
                    this.selectedObject = null;
                    this.selectedObjectGroup = null;
                    return;
                case 8:
                    showReplyPanel(true, this.selectedObject, null, null, false);
                    break;
                case 9:
                    Context parentActivity = getParentActivity();
                    TLRPC$InputStickerSet inputStickerSet = this.selectedObject.getInputStickerSet();
                    StickersAlertDelegate stickersAlertDelegate = (this.bottomOverlayChat.getVisibility() == 0 || !ChatObject.canSendStickers(this.currentChat)) ? null : this.chatActivityEnterView;
                    showDialog(new StickersAlert(parentActivity, this, inputStickerSet, null, stickersAlertDelegate));
                    break;
                case 10:
                    if (VERSION.SDK_INT < 23 || getParentActivity().checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
                        String fileName = FileLoader.getDocumentFileName(this.selectedObject.getDocument());
                        if (TextUtils.isEmpty(fileName)) {
                            fileName = this.selectedObject.getFileName();
                        }
                        path = this.selectedObject.messageOwner.attachPath;
                        if (!(path == null || path.length() <= 0 || new File(path).exists())) {
                            path = null;
                        }
                        if (path == null || path.length() == 0) {
                            path = FileLoader.getPathToMessage(this.selectedObject.messageOwner).toString();
                        }
                        MediaController.saveFile(path, getParentActivity(), this.selectedObject.isMusic() ? 3 : 2, fileName, this.selectedObject.getDocument() != null ? this.selectedObject.getDocument().mime_type : "");
                        break;
                    }
                    getParentActivity().requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 4);
                    this.selectedObject = null;
                    this.selectedObjectGroup = null;
                    return;
                    break;
                case 11:
                    TLRPC$Document document = this.selectedObject.getDocument();
                    MessagesController.getInstance().saveGif(document);
                    showGifHint();
                    this.chatActivityEnterView.addRecentGif(document);
                    break;
                case 12:
                    startEditingMessageObject(this.selectedObject);
                    this.selectedObject = null;
                    this.selectedObjectGroup = null;
                    break;
                case 13:
                    final boolean[] checks;
                    int mid = this.selectedObject.getId();
                    builder = new AlertDialog.Builder(getParentActivity());
                    if (ChatObject.isChannel(this.currentChat) && this.currentChat.megagroup) {
                        int i;
                        builder.setMessage(LocaleController.getString("PinMessageAlert", R.string.PinMessageAlert));
                        checks = new boolean[]{true};
                        View frameLayout = new FrameLayout(getParentActivity());
                        CheckBoxCell cell = new CheckBoxCell(getParentActivity(), true);
                        cell.setBackgroundDrawable(Theme.getSelectorDrawable(false));
                        cell.setText(LocaleController.getString("PinNotify", R.string.PinNotify), "", true, false);
                        int dp = LocaleController.isRTL ? AndroidUtilities.dp(8.0f) : 0;
                        if (LocaleController.isRTL) {
                            i = 0;
                        } else {
                            i = AndroidUtilities.dp(8.0f);
                        }
                        cell.setPadding(dp, 0, i, 0);
                        frameLayout.addView(cell, LayoutHelper.createFrame(-1, 48.0f, 51, 8.0f, 0.0f, 8.0f, 0.0f));
                        cell.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                boolean z;
                                CheckBoxCell cell = (CheckBoxCell) v;
                                boolean[] zArr = checks;
                                if (checks[0]) {
                                    z = false;
                                } else {
                                    z = true;
                                }
                                zArr[0] = z;
                                cell.setChecked(checks[0], true);
                            }
                        });
                        builder.setView(frameLayout);
                    } else {
                        builder.setMessage(LocaleController.getString("PinMessageAlertChannel", R.string.PinMessageAlertChannel));
                        checks = new boolean[]{false};
                    }
                    final int i2 = mid;
                    builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MessagesController.getInstance().pinChannelMessage(ChatActivity.this.currentChat, i2, checks[0]);
                        }
                    });
                    builder.setTitle(ApplicationLoader.getConfig().getAppName());
                    builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
                    showDialog(builder.create());
                    break;
                case 14:
                    builder = new AlertDialog.Builder(getParentActivity());
                    builder.setMessage(LocaleController.getString("UnpinMessageAlert", R.string.UnpinMessageAlert));
                    builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MessagesController.getInstance().pinChannelMessage(ChatActivity.this.currentChat, 0, false);
                        }
                    });
                    builder.setTitle(ApplicationLoader.getConfig().getAppName());
                    builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
                    showDialog(builder.create());
                    break;
                case 15:
                    args = new Bundle();
                    args.putInt("user_id", this.selectedObject.messageOwner.media.user_id);
                    args.putString("phone", this.selectedObject.messageOwner.media.phone_number);
                    args.putBoolean("addContact", true);
                    presentFragment(new ContactAddActivity(args));
                    break;
                case 16:
                    AndroidUtilities.addToClipboard(this.selectedObject.messageOwner.media.phone_number);
                    break;
                case 17:
                    try {
                        intent = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + this.selectedObject.messageOwner.media.phone_number));
                        intent.addFlags(ErrorDialogData.BINDER_CRASH);
                        getParentActivity().startActivityForResult(intent, HttpStatus.SC_INTERNAL_SERVER_ERROR);
                        break;
                    } catch (Exception e2) {
                        FileLog.e(e2);
                        break;
                    }
                case 18:
                    if (this.currentUser != null) {
                        VoIPHelper.startCall(this.currentUser, getParentActivity(), MessagesController.getInstance().getUserFull(this.currentUser.id));
                        break;
                    }
                    break;
                case 19:
                    VoIPHelper.showRateAlert(getParentActivity(), (TLRPC$TL_messageActionPhoneCall) this.selectedObject.messageOwner.action);
                    break;
                case 20:
                    StickersQuery.addRecentSticker(2, this.selectedObject.getDocument(), (int) (System.currentTimeMillis() / 1000), false);
                    break;
                case 21:
                    StickersQuery.addRecentSticker(2, this.selectedObject.getDocument(), (int) (System.currentTimeMillis() / 1000), true);
                    break;
                case 22:
                    TLObject req = new TLRPC$TL_channels_exportMessageLink();
                    req.id = this.selectedObject.getId();
                    req.channel = MessagesController.getInputChannel(this.currentChat);
                    ConnectionsManager.getInstance().sendRequest(req, new RequestDelegate() {
                        public void run(final TLObject response, TLRPC$TL_error error) {
                            AndroidUtilities.runOnUIThread(new Runnable() {
                                public void run() {
                                    if (response != null) {
                                        try {
                                            ((ClipboardManager) ApplicationLoader.applicationContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(PlusShare.KEY_CALL_TO_ACTION_LABEL, response.link));
                                            Toast.makeText(ApplicationLoader.applicationContext, LocaleController.getString("LinkCopied", R.string.LinkCopied), 0).show();
                                        } catch (Exception e) {
                                            FileLog.e(e);
                                        }
                                    }
                                }
                            });
                        }
                    });
                    break;
            }
            this.selectedObject = null;
            this.selectedObjectGroup = null;
        }
    }

    public void didSelectDialogs(DialogsActivity fragment, ArrayList<Long> dids, CharSequence message, boolean param) {
        if (this.forwardingMessage != null || !this.selectedMessagesIds[0].isEmpty() || !this.selectedMessagesIds[1].isEmpty()) {
            int a;
            ArrayList<MessageObject> fmessages = new ArrayList();
            if (this.forwardingMessage != null) {
                if (this.forwardingMessageGroup != null) {
                    fmessages.addAll(this.forwardingMessageGroup.messages);
                } else {
                    fmessages.add(this.forwardingMessage);
                }
                this.forwardingMessage = null;
                this.forwardingMessageGroup = null;
            } else {
                for (a = 1; a >= 0; a--) {
                    ArrayList<Integer> arrayList = new ArrayList(this.selectedMessagesIds[a].keySet());
                    Collections.sort(arrayList);
                    for (int b = 0; b < arrayList.size(); b++) {
                        Integer id = (Integer) arrayList.get(b);
                        MessageObject messageObject = (MessageObject) this.selectedMessagesIds[a].get(id);
                        if (messageObject != null && id.intValue() > 0) {
                            fmessages.add(messageObject);
                        }
                    }
                    this.selectedMessagesCanCopyIds[a].clear();
                    this.selectedMessagesCanStarIds[a].clear();
                    this.selectedMessagesIds[a].clear();
                }
                this.cantDeleteMessagesCount = 0;
                this.canEditMessagesCount = 0;
                this.actionBar.hideActionMode();
                updatePinnedMessageView(true);
            }
            long did;
            if (dids.size() > 1 || ((Long) dids.get(0)).longValue() == ((long) UserConfig.getClientUserId()) || message != null) {
                for (a = 0; a < dids.size(); a++) {
                    did = ((Long) dids.get(a)).longValue();
                    if (message != null) {
                        SendMessagesHelper.getInstance().sendMessage(message.toString(), did, null, null, true, null, null, null);
                    }
                    SendMessagesHelper.getInstance().sendMessage(fmessages, did);
                }
                fragment.finishFragment();
                return;
            }
            did = ((Long) dids.get(0)).longValue();
            if (did != this.dialog_id) {
                int lower_part = (int) did;
                int high_part = (int) (did >> 32);
                Bundle args = new Bundle();
                args.putBoolean("scrollToTopOnResume", this.scrollToTopOnResume);
                if (lower_part == 0) {
                    args.putInt("enc_id", high_part);
                } else if (lower_part > 0) {
                    args.putInt("user_id", lower_part);
                } else if (lower_part < 0) {
                    args.putInt("chat_id", -lower_part);
                }
                if (lower_part == 0 || MessagesController.checkCanOpenChat(args, fragment)) {
                    ChatActivity chatActivity = new ChatActivity(args);
                    if (presentFragment(chatActivity, true)) {
                        chatActivity.showReplyPanel(true, null, fmessages, null, false);
                        if (!AndroidUtilities.isTablet()) {
                            removeSelfFromStack();
                            return;
                        }
                        return;
                    }
                    fragment.finishFragment();
                    return;
                }
                return;
            }
            fragment.finishFragment();
            moveScrollToLastMessage();
            showReplyPanel(true, null, fmessages, null, false);
            if (AndroidUtilities.isTablet()) {
                this.actionBar.hideActionMode();
                updatePinnedMessageView(true);
            }
            updateVisibleRows();
        }
    }

    public boolean checkRecordLocked() {
        if (this.chatActivityEnterView == null || !this.chatActivityEnterView.isRecordLocked()) {
            return false;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getParentActivity());
        if (this.chatActivityEnterView.isInVideoMode()) {
            builder.setTitle(LocaleController.getString("DiscardVideoMessageTitle", R.string.DiscardVideoMessageTitle));
            builder.setMessage(LocaleController.getString("DiscardVideoMessageDescription", R.string.DiscardVideoMessageDescription));
        } else {
            builder.setTitle(LocaleController.getString("DiscardVoiceMessageTitle", R.string.DiscardVoiceMessageTitle));
            builder.setMessage(LocaleController.getString("DiscardVoiceMessageDescription", R.string.DiscardVoiceMessageDescription));
        }
        builder.setPositiveButton(LocaleController.getString("DiscardVoiceMessageAction", R.string.DiscardVoiceMessageAction), new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (ChatActivity.this.chatActivityEnterView != null) {
                    ChatActivity.this.chatActivityEnterView.cancelRecordingAudioVideo();
                }
            }
        });
        builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
        showDialog(builder.create());
        return true;
    }

    public boolean onBackPressed() {
        if (checkRecordLocked()) {
            return false;
        }
        if (this.actionBar != null && this.actionBar.isActionModeShowed()) {
            for (int a = 1; a >= 0; a--) {
                this.selectedMessagesIds[a].clear();
                this.selectedMessagesCanCopyIds[a].clear();
                this.selectedMessagesCanStarIds[a].clear();
            }
            this.chatActivityEnterView.setEditingMessageObject(null, false);
            this.actionBar.hideActionMode();
            updatePinnedMessageView(true);
            this.cantDeleteMessagesCount = 0;
            this.canEditMessagesCount = 0;
            updateVisibleRows();
            return false;
        } else if (this.chatActivityEnterView == null || !this.chatActivityEnterView.isPopupShowing()) {
            return true;
        } else {
            this.chatActivityEnterView.hidePopup(true);
            return false;
        }
    }

    private void updateVisibleRows() {
        if (this.chatListView != null) {
            int count = this.chatListView.getChildCount();
            MessageObject editingMessageObject = this.chatActivityEnterView != null ? this.chatActivityEnterView.getEditingMessageObject() : null;
            for (int a = 0; a < count; a++) {
                View view = this.chatListView.getChildAt(a);
                if (view instanceof ChatMessageCell) {
                    ChatMessageCell cell = (ChatMessageCell) view;
                    MessageObject messageObject = cell.getMessageObject();
                    boolean disableSelection = false;
                    boolean selected = false;
                    if (this.actionBar.isActionModeShowed()) {
                        int idx = messageObject.getDialogId() == this.dialog_id ? 0 : 1;
                        if (messageObject == editingMessageObject || this.selectedMessagesIds[idx].containsKey(Integer.valueOf(messageObject.getId()))) {
                            setCellSelectionBackground(messageObject, cell, idx);
                            selected = true;
                        } else {
                            view.setBackgroundDrawable(null);
                        }
                        disableSelection = true;
                    } else {
                        view.setBackgroundDrawable(null);
                    }
                    cell.setMessageObject(cell.getMessageObject(), cell.getCurrentMessagesGroup(), cell.isPinnedBottom(), cell.isPinnedTop());
                    boolean z = !disableSelection;
                    boolean z2 = disableSelection && selected;
                    cell.setCheckPressed(z, z2);
                    z2 = (this.highlightMessageId == Integer.MAX_VALUE || messageObject == null || messageObject.getId() != this.highlightMessageId) ? false : true;
                    cell.setHighlighted(z2);
                    if (this.searchContainer != null && this.searchContainer.getVisibility() == 0) {
                        if (MessagesSearchQuery.isMessageFound(messageObject.getId(), messageObject.getDialogId() == this.mergeDialogId) && MessagesSearchQuery.getLastSearchQuery() != null) {
                            cell.setHighlightedText(MessagesSearchQuery.getLastSearchQuery());
                        }
                    }
                    cell.setHighlightedText(null);
                } else if (view instanceof ChatActionCell) {
                    ChatActionCell cell2 = (ChatActionCell) view;
                    cell2.setMessageObject(cell2.getMessageObject());
                }
            }
            this.chatListView.invalidate();
        }
    }

    private void checkEditTimer() {
        if (this.chatActivityEnterView != null) {
            MessageObject messageObject = this.chatActivityEnterView.getEditingMessageObject();
            if (messageObject == null) {
                return;
            }
            if (this.currentUser == null || !this.currentUser.self) {
                int dt = messageObject.canEditMessageAnytime(this.currentChat) ? 360 : (MessagesController.getInstance().maxEditTime + HttpStatus.SC_MULTIPLE_CHOICES) - Math.abs(ConnectionsManager.getInstance().getCurrentTime() - messageObject.messageOwner.date);
                if (dt > 0) {
                    if (dt <= HttpStatus.SC_MULTIPLE_CHOICES) {
                        if (this.actionModeSubTextView.getVisibility() != 0) {
                            this.actionModeSubTextView.setVisibility(0);
                        }
                        SimpleTextView simpleTextView = this.actionModeSubTextView;
                        Object[] objArr = new Object[1];
                        objArr[0] = String.format("%d:%02d", new Object[]{Integer.valueOf(dt / 60), Integer.valueOf(dt % 60)});
                        simpleTextView.setText(LocaleController.formatString("TimeToEdit", R.string.TimeToEdit, objArr));
                    } else if (this.actionModeSubTextView.getVisibility() != 8) {
                        this.actionModeSubTextView.setVisibility(8);
                    }
                    AndroidUtilities.runOnUIThread(new Runnable() {
                        public void run() {
                            ChatActivity.this.checkEditTimer();
                        }
                    }, 1000);
                    return;
                }
                this.chatActivityEnterView.onEditTimeExpired();
                this.actionModeSubTextView.setText(LocaleController.formatString("TimeToEditExpired", R.string.TimeToEditExpired, new Object[0]));
            } else if (this.actionModeSubTextView.getVisibility() != 8) {
                this.actionModeSubTextView.setVisibility(8);
            }
        }
    }

    private ArrayList<MessageObject> createVoiceMessagesPlaylist(MessageObject startMessageObject, boolean playingUnreadMedia) {
        ArrayList<MessageObject> messageObjects = new ArrayList();
        messageObjects.add(startMessageObject);
        int messageId = startMessageObject.getId();
        long startDialogId = startMessageObject.getDialogId();
        if (messageId != 0) {
            for (int a = this.messages.size() - 1; a >= 0; a--) {
                MessageObject messageObject = (MessageObject) this.messages.get(a);
                if ((messageObject.getDialogId() != this.mergeDialogId || startMessageObject.getDialogId() == this.mergeDialogId) && (((this.currentEncryptedChat == null && messageObject.getId() > messageId) || (this.currentEncryptedChat != null && messageObject.getId() < messageId)) && ((messageObject.isVoice() || messageObject.isRoundVideo()) && (!playingUnreadMedia || (messageObject.isContentUnread() && !messageObject.isOut()))))) {
                    messageObjects.add(messageObject);
                }
            }
        }
        return messageObjects;
    }

    private void alertUserOpenError(MessageObject message) {
        if (getParentActivity() != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getParentActivity());
            builder.setTitle(ApplicationLoader.getConfig().getAppName());
            builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), null);
            if (message.type == 3) {
                builder.setMessage(LocaleController.getString("NoPlayerInstalled", R.string.NoPlayerInstalled));
            } else {
                builder.setMessage(LocaleController.formatString("NoHandleAppInstalled", R.string.NoHandleAppInstalled, new Object[]{message.getDocument().mime_type}));
            }
            showDialog(builder.create());
        }
    }

    private void openSearchWithText(String text) {
        this.avatarContainer.setVisibility(8);
        this.headerItem.setVisibility(8);
        this.attachItem.setVisibility(8);
        this.searchItem.setVisibility(0);
        updateSearchButtons(0, 0, -1);
        updateBottomOverlay();
        this.openSearchKeyboard = text == null;
        this.searchItem.openSearch(this.openSearchKeyboard);
        if (text != null) {
            this.searchItem.getSearchField().setText(text);
            this.searchItem.getSearchField().setSelection(this.searchItem.getSearchField().length());
            MessagesSearchQuery.searchMessagesInChat(text, this.dialog_id, this.mergeDialogId, this.classGuid, 0, this.searchingUserMessages);
        }
        updatePinnedMessageView(true);
    }

    public void didSelectLocation(TLRPC$MessageMedia location, int live) {
        SendMessagesHelper.getInstance().sendMessage(location, this.dialog_id, this.replyingMessageObject, null, null);
        moveScrollToLastMessage();
        if (live == 1) {
            showReplyPanel(false, null, null, null, false);
            DraftQuery.cleanDraft(this.dialog_id, true);
        }
        if (this.paused) {
            this.scrollToTopOnResume = true;
        }
    }

    public boolean isSecretChat() {
        return this.currentEncryptedChat != null;
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public TLRPC$Chat getCurrentChat() {
        return this.currentChat;
    }

    public boolean allowGroupPhotos() {
        return this.currentEncryptedChat == null || AndroidUtilities.getPeerLayerVersion(this.currentEncryptedChat.layer) >= 73;
    }

    public TLRPC$EncryptedChat getCurrentEncryptedChat() {
        return this.currentEncryptedChat;
    }

    public TLRPC$ChatFull getCurrentChatInfo() {
        return this.info;
    }

    public void sendMedia(MediaController$PhotoEntry photoEntry, VideoEditedInfo videoEditedInfo) {
        if (photoEntry.isVideo) {
            if (videoEditedInfo != null) {
                SendMessagesHelper.prepareSendingVideo(photoEntry.path, videoEditedInfo.estimatedSize, videoEditedInfo.estimatedDuration, videoEditedInfo.resultWidth, videoEditedInfo.resultHeight, videoEditedInfo, this.dialog_id, this.replyingMessageObject, photoEntry.caption != null ? photoEntry.caption.toString() : null, photoEntry.ttl);
            } else {
                SendMessagesHelper.prepareSendingVideo(photoEntry.path, 0, 0, 0, 0, null, this.dialog_id, this.replyingMessageObject, photoEntry.caption != null ? photoEntry.caption.toString() : null, photoEntry.ttl);
            }
            showReplyPanel(false, null, null, null, false);
            DraftQuery.cleanDraft(this.dialog_id, true);
        } else if (photoEntry.imagePath != null) {
            SendMessagesHelper.prepareSendingPhoto(photoEntry.imagePath, null, this.dialog_id, this.replyingMessageObject, photoEntry.caption, photoEntry.stickers, null, photoEntry.ttl);
            showReplyPanel(false, null, null, null, false);
            DraftQuery.cleanDraft(this.dialog_id, true);
        } else if (photoEntry.path != null) {
            SendMessagesHelper.prepareSendingPhoto(photoEntry.path, null, this.dialog_id, this.replyingMessageObject, photoEntry.caption, photoEntry.stickers, null, photoEntry.ttl);
            showReplyPanel(false, null, null, null, false);
            DraftQuery.cleanDraft(this.dialog_id, true);
        }
    }

    public void showOpenGameAlert(TLRPC$TL_game game, MessageObject messageObject, String urlStr, boolean ask, int uid) {
        User user = MessagesController.getInstance().getUser(Integer.valueOf(uid));
        if (ask) {
            String name;
            AlertDialog.Builder builder = new AlertDialog.Builder(getParentActivity());
            builder.setTitle(ApplicationLoader.getConfig().getAppName());
            if (user != null) {
                name = ContactsController.formatName(user.first_name, user.last_name);
            } else {
                name = "";
            }
            builder.setMessage(LocaleController.formatString("BotPermissionGameAlert", R.string.BotPermissionGameAlert, new Object[]{name, ApplicationLoader.getConfig().getAppName()}));
            final TLRPC$TL_game tLRPC$TL_game = game;
            final MessageObject messageObject2 = messageObject;
            final String str = urlStr;
            final int i = uid;
            builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ChatActivity.this.showOpenGameAlert(tLRPC$TL_game, messageObject2, str, false, i);
                    ApplicationLoader.applicationContext.getSharedPreferences("Notifications", 0).edit().putBoolean("askgame_" + i, false).commit();
                }
            });
            builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
            showDialog(builder.create());
        } else if (VERSION.SDK_INT < 21 || AndroidUtilities.isTablet() || !WebviewActivity.supportWebview()) {
            Activity parentActivity = getParentActivity();
            r2 = game.short_name;
            String str2 = (user == null || user.username == null) ? "" : user.username;
            WebviewActivity.openGameInBrowser(urlStr, messageObject, parentActivity, r2, str2);
        } else if (this.parentLayout.fragmentsStack.get(this.parentLayout.fragmentsStack.size() - 1) == this) {
            r2 = (user == null || TextUtils.isEmpty(user.username)) ? "" : user.username;
            presentFragment(new WebviewActivity(urlStr, r2, game.title, game.short_name, messageObject));
        }
    }

    public void showOpenUrlAlert(final String url, boolean ask) {
        boolean z = true;
        if (Browser.isInternalUrl(url, null) || !ask) {
            Context parentActivity = getParentActivity();
            if (this.inlineReturn != 0) {
                z = false;
            }
            Browser.openUrl(parentActivity, url, z);
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getParentActivity());
        builder.setTitle(ApplicationLoader.getConfig().getAppName());
        builder.setMessage(LocaleController.formatString("OpenUrlAlert", R.string.OpenUrlAlert, new Object[]{url}));
        builder.setPositiveButton(LocaleController.getString("Open", R.string.Open), new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Browser.openUrl(ChatActivity.this.getParentActivity(), url, ChatActivity.this.inlineReturn == 0);
            }
        });
        builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
        showDialog(builder.create());
    }

    private void removeMessageObject(MessageObject messageObject) {
        int index = this.messages.indexOf(messageObject);
        if (index != -1) {
            this.messages.remove(index);
            if (this.chatAdapter != null) {
                this.chatAdapter.notifyItemRemoved(this.chatAdapter.messagesStartRow + index);
            }
        }
    }

    private void setCellSelectionBackground(MessageObject message, ChatMessageCell messageCell, int idx) {
        GroupedMessages groupedMessages = getValidGroupedMessage(message);
        if (groupedMessages != null) {
            boolean hasUnselected = false;
            for (int a = 0; a < groupedMessages.messages.size(); a++) {
                if (!this.selectedMessagesIds[idx].containsKey(Integer.valueOf(((MessageObject) groupedMessages.messages.get(a)).getId()))) {
                    hasUnselected = true;
                    break;
                }
            }
            if (!hasUnselected) {
                groupedMessages = null;
            }
        }
        if (groupedMessages == null) {
            messageCell.setBackgroundColor(Theme.getColor(Theme.key_chat_selectedBackground));
        } else {
            messageCell.setBackground(null);
        }
    }

    public ThemeDescription[] getThemeDescriptions() {
        ThemeDescriptionDelegate selectedBackgroundDelegate = new ThemeDescriptionDelegate() {
            public void didSetColor(int color) {
                ChatActivity.this.updateVisibleRows();
            }
        };
        ThemeDescription[] themeDescriptionArr = new ThemeDescription[338];
        themeDescriptionArr[0] = new ThemeDescription(this.fragmentView, 0, null, null, null, null, Theme.key_chat_wallpaper);
        themeDescriptionArr[1] = new ThemeDescription(this.actionBar, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_actionBarDefault);
        themeDescriptionArr[2] = new ThemeDescription(this.chatListView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault);
        themeDescriptionArr[3] = new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon);
        themeDescriptionArr[4] = new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector);
        themeDescriptionArr[5] = new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SUBMENUBACKGROUND, null, null, null, null, Theme.key_actionBarDefaultSubmenuBackground);
        themeDescriptionArr[6] = new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SUBMENUITEM, null, null, null, null, Theme.key_actionBarDefaultSubmenuItem);
        themeDescriptionArr[7] = new ThemeDescription(this.actionBar, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_actionBarDefault);
        themeDescriptionArr[8] = new ThemeDescription(this.chatListView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault);
        themeDescriptionArr[9] = new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon);
        themeDescriptionArr[10] = new ThemeDescription(this.avatarContainer.getTitleTextView(), ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle);
        themeDescriptionArr[11] = new ThemeDescription(this.avatarContainer.getSubtitleTextView(), ThemeDescription.FLAG_TEXTCOLOR, null, new Paint[]{Theme.chat_statusPaint, Theme.chat_statusRecordPaint}, null, null, Theme.key_actionBarDefaultSubtitle, null);
        themeDescriptionArr[12] = new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector);
        themeDescriptionArr[13] = new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SEARCH, null, null, null, null, Theme.key_actionBarDefaultSearch);
        themeDescriptionArr[14] = new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SEARCHPLACEHOLDER, null, null, null, null, Theme.key_actionBarDefaultSearchPlaceholder);
        themeDescriptionArr[15] = new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_AM_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarActionModeDefaultIcon);
        themeDescriptionArr[16] = new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_AM_BACKGROUND, null, null, null, null, Theme.key_actionBarActionModeDefault);
        themeDescriptionArr[17] = new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_AM_TOPBACKGROUND, null, null, null, null, Theme.key_actionBarActionModeDefaultTop);
        themeDescriptionArr[18] = new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_AM_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarActionModeDefaultSelector);
        themeDescriptionArr[19] = new ThemeDescription(this.selectedMessagesCountTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_actionBarActionModeDefaultIcon);
        themeDescriptionArr[20] = new ThemeDescription(this.actionModeTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_actionBarActionModeDefaultIcon);
        themeDescriptionArr[21] = new ThemeDescription(this.actionModeSubTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_actionBarActionModeDefaultIcon);
        themeDescriptionArr[22] = new ThemeDescription(this.avatarContainer.getTitleTextView(), 0, null, null, new Drawable[]{Theme.chat_muteIconDrawable}, null, Theme.key_chat_muteIcon);
        themeDescriptionArr[23] = new ThemeDescription(this.avatarContainer.getTitleTextView(), 0, null, null, new Drawable[]{Theme.chat_lockIconDrawable}, null, Theme.key_chat_lockIcon);
        themeDescriptionArr[24] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.avatar_photoDrawable, Theme.avatar_broadcastDrawable, Theme.avatar_savedDrawable}, null, Theme.key_avatar_text);
        themeDescriptionArr[25] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_avatar_backgroundRed);
        themeDescriptionArr[26] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_avatar_backgroundOrange);
        themeDescriptionArr[27] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_avatar_backgroundViolet);
        themeDescriptionArr[28] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_avatar_backgroundGreen);
        themeDescriptionArr[29] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_avatar_backgroundCyan);
        themeDescriptionArr[30] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_avatar_backgroundBlue);
        themeDescriptionArr[31] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_avatar_backgroundPink);
        themeDescriptionArr[32] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_avatar_nameInMessageRed);
        themeDescriptionArr[33] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_avatar_nameInMessageOrange);
        themeDescriptionArr[34] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_avatar_nameInMessageViolet);
        themeDescriptionArr[35] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_avatar_nameInMessageGreen);
        themeDescriptionArr[36] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_avatar_nameInMessageCyan);
        themeDescriptionArr[37] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_avatar_nameInMessageBlue);
        themeDescriptionArr[38] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_avatar_nameInMessagePink);
        themeDescriptionArr[39] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgInDrawable, Theme.chat_msgInMediaDrawable}, null, Theme.key_chat_inBubble);
        themeDescriptionArr[40] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgInSelectedDrawable, Theme.chat_msgInMediaSelectedDrawable}, null, Theme.key_chat_inBubbleSelected);
        themeDescriptionArr[41] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgInShadowDrawable, Theme.chat_msgInMediaShadowDrawable}, null, Theme.key_chat_inBubbleShadow);
        themeDescriptionArr[42] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgOutDrawable, Theme.chat_msgOutMediaDrawable}, null, Theme.key_chat_outBubble);
        themeDescriptionArr[43] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgOutSelectedDrawable, Theme.chat_msgOutMediaSelectedDrawable}, null, Theme.key_chat_outBubbleSelected);
        themeDescriptionArr[44] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgOutShadowDrawable, Theme.chat_msgOutMediaShadowDrawable}, null, Theme.key_chat_outBubbleShadow);
        themeDescriptionArr[45] = new ThemeDescription(this.chatListView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{ChatActionCell.class}, Theme.chat_actionTextPaint, null, null, Theme.key_chat_serviceText);
        themeDescriptionArr[46] = new ThemeDescription(this.chatListView, ThemeDescription.FLAG_LINKCOLOR, new Class[]{ChatActionCell.class}, Theme.chat_actionTextPaint, null, null, Theme.key_chat_serviceLink);
        themeDescriptionArr[47] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_shareIconDrawable, Theme.chat_botInlineDrawable, Theme.chat_botLinkDrawalbe, Theme.chat_goIconDrawable}, null, Theme.key_chat_serviceIcon);
        themeDescriptionArr[48] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class, ChatActionCell.class}, null, null, null, Theme.key_chat_serviceBackground);
        themeDescriptionArr[49] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class, ChatActionCell.class}, null, null, null, Theme.key_chat_serviceBackgroundSelected);
        themeDescriptionArr[50] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_messageTextIn);
        themeDescriptionArr[51] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_messageTextOut);
        themeDescriptionArr[52] = new ThemeDescription(this.chatListView, ThemeDescription.FLAG_LINKCOLOR, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_messageLinkIn, null);
        themeDescriptionArr[53] = new ThemeDescription(this.chatListView, ThemeDescription.FLAG_LINKCOLOR, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_messageLinkOut, null);
        themeDescriptionArr[54] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgOutCheckDrawable, Theme.chat_msgOutHalfCheckDrawable}, null, Theme.key_chat_outSentCheck);
        themeDescriptionArr[55] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgOutCheckSelectedDrawable, Theme.chat_msgOutHalfCheckSelectedDrawable}, null, Theme.key_chat_outSentCheckSelected);
        themeDescriptionArr[56] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgOutClockDrawable}, null, Theme.key_chat_outSentClock);
        themeDescriptionArr[57] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgOutSelectedClockDrawable}, null, Theme.key_chat_outSentClockSelected);
        themeDescriptionArr[58] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgInClockDrawable}, null, Theme.key_chat_inSentClock);
        themeDescriptionArr[59] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgInSelectedClockDrawable}, null, Theme.key_chat_inSentClockSelected);
        themeDescriptionArr[60] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgMediaCheckDrawable, Theme.chat_msgMediaHalfCheckDrawable}, null, Theme.key_chat_mediaSentCheck);
        themeDescriptionArr[61] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgStickerHalfCheckDrawable, Theme.chat_msgStickerCheckDrawable, Theme.chat_msgStickerClockDrawable, Theme.chat_msgStickerViewsDrawable}, null, Theme.key_chat_serviceText);
        themeDescriptionArr[62] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgMediaClockDrawable}, null, Theme.key_chat_mediaSentClock);
        themeDescriptionArr[63] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgOutViewsDrawable}, null, Theme.key_chat_outViews);
        themeDescriptionArr[64] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgOutViewsSelectedDrawable}, null, Theme.key_chat_outViewsSelected);
        themeDescriptionArr[65] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgInViewsDrawable}, null, Theme.key_chat_inViews);
        themeDescriptionArr[66] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgInViewsSelectedDrawable}, null, Theme.key_chat_inViewsSelected);
        themeDescriptionArr[67] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgMediaViewsDrawable}, null, Theme.key_chat_mediaViews);
        themeDescriptionArr[68] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgOutMenuDrawable}, null, Theme.key_chat_outMenu);
        themeDescriptionArr[69] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgOutMenuSelectedDrawable}, null, Theme.key_chat_outMenuSelected);
        themeDescriptionArr[70] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgInMenuDrawable}, null, Theme.key_chat_inMenu);
        themeDescriptionArr[71] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgInMenuSelectedDrawable}, null, Theme.key_chat_inMenuSelected);
        themeDescriptionArr[72] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgMediaMenuDrawable}, null, Theme.key_chat_mediaMenu);
        themeDescriptionArr[73] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgOutInstantDrawable, Theme.chat_msgOutCallDrawable}, null, Theme.key_chat_outInstant);
        themeDescriptionArr[74] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgOutCallSelectedDrawable}, null, Theme.key_chat_outInstantSelected);
        themeDescriptionArr[75] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgInInstantDrawable, Theme.chat_msgInCallDrawable}, null, Theme.key_chat_inInstant);
        themeDescriptionArr[76] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgInCallSelectedDrawable}, null, Theme.key_chat_inInstantSelected);
        themeDescriptionArr[77] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgCallUpRedDrawable, Theme.chat_msgCallDownRedDrawable}, null, Theme.key_calls_callReceivedRedIcon);
        themeDescriptionArr[78] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgCallUpGreenDrawable, Theme.chat_msgCallDownGreenDrawable}, null, Theme.key_calls_callReceivedGreenIcon);
        themeDescriptionArr[79] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, Theme.chat_msgErrorPaint, null, null, Theme.key_chat_sentError);
        themeDescriptionArr[80] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgErrorDrawable}, null, Theme.key_chat_sentErrorIcon);
        themeDescriptionArr[81] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, selectedBackgroundDelegate, Theme.key_chat_selectedBackground);
        themeDescriptionArr[82] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, Theme.chat_durationPaint, null, null, Theme.key_chat_previewDurationText);
        themeDescriptionArr[83] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, Theme.chat_gamePaint, null, null, Theme.key_chat_previewGameText);
        themeDescriptionArr[84] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inPreviewInstantText);
        themeDescriptionArr[85] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outPreviewInstantText);
        themeDescriptionArr[86] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inPreviewInstantSelectedText);
        themeDescriptionArr[87] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outPreviewInstantSelectedText);
        themeDescriptionArr[88] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, Theme.chat_deleteProgressPaint, null, null, Theme.key_chat_secretTimeText);
        themeDescriptionArr[89] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_stickerNameText);
        themeDescriptionArr[90] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, Theme.chat_botButtonPaint, null, null, Theme.key_chat_botButtonText);
        themeDescriptionArr[91] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, Theme.chat_botProgressPaint, null, null, Theme.key_chat_botProgress);
        themeDescriptionArr[92] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inForwardedNameText);
        themeDescriptionArr[93] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outForwardedNameText);
        themeDescriptionArr[94] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inViaBotNameText);
        themeDescriptionArr[95] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outViaBotNameText);
        themeDescriptionArr[96] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_stickerViaBotNameText);
        themeDescriptionArr[97] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inReplyLine);
        themeDescriptionArr[98] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outReplyLine);
        themeDescriptionArr[99] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_stickerReplyLine);
        themeDescriptionArr[100] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inReplyNameText);
        themeDescriptionArr[101] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outReplyNameText);
        themeDescriptionArr[102] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_stickerReplyNameText);
        themeDescriptionArr[103] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inReplyMessageText);
        themeDescriptionArr[104] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outReplyMessageText);
        themeDescriptionArr[105] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inReplyMediaMessageText);
        themeDescriptionArr[106] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outReplyMediaMessageText);
        themeDescriptionArr[107] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inReplyMediaMessageSelectedText);
        themeDescriptionArr[108] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outReplyMediaMessageSelectedText);
        themeDescriptionArr[109] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_stickerReplyMessageText);
        themeDescriptionArr[110] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inPreviewLine);
        themeDescriptionArr[111] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outPreviewLine);
        themeDescriptionArr[112] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inSiteNameText);
        themeDescriptionArr[113] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outSiteNameText);
        themeDescriptionArr[114] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inContactNameText);
        themeDescriptionArr[115] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outContactNameText);
        themeDescriptionArr[116] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inContactPhoneText);
        themeDescriptionArr[117] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outContactPhoneText);
        themeDescriptionArr[118] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_mediaProgress);
        themeDescriptionArr[119] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inAudioProgress);
        themeDescriptionArr[120] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outAudioProgress);
        themeDescriptionArr[121] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inAudioSelectedProgress);
        themeDescriptionArr[122] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outAudioSelectedProgress);
        themeDescriptionArr[123] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_mediaTimeText);
        themeDescriptionArr[124] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inTimeText);
        themeDescriptionArr[125] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outTimeText);
        themeDescriptionArr[126] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inTimeSelectedText);
        themeDescriptionArr[127] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_adminText);
        themeDescriptionArr[128] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_adminSelectedText);
        themeDescriptionArr[129] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outTimeSelectedText);
        themeDescriptionArr[130] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inAudioPerfomerText);
        themeDescriptionArr[131] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outAudioPerfomerText);
        themeDescriptionArr[132] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inAudioTitleText);
        themeDescriptionArr[133] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outAudioTitleText);
        themeDescriptionArr[134] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inAudioDurationText);
        themeDescriptionArr[135] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outAudioDurationText);
        themeDescriptionArr[136] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inAudioDurationSelectedText);
        themeDescriptionArr[137] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outAudioDurationSelectedText);
        themeDescriptionArr[138] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inAudioSeekbar);
        themeDescriptionArr[139] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outAudioSeekbar);
        themeDescriptionArr[PDUiBeacon.Gimbal] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inAudioSeekbarSelected);
        themeDescriptionArr[141] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outAudioSeekbarSelected);
        themeDescriptionArr[142] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inAudioSeekbarFill);
        themeDescriptionArr[143] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outAudioSeekbarFill);
        themeDescriptionArr[144] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inVoiceSeekbar);
        themeDescriptionArr[145] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outVoiceSeekbar);
        themeDescriptionArr[146] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inVoiceSeekbarSelected);
        themeDescriptionArr[147] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outVoiceSeekbarSelected);
        themeDescriptionArr[148] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inVoiceSeekbarFill);
        themeDescriptionArr[149] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outVoiceSeekbarFill);
        themeDescriptionArr[150] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inFileProgress);
        themeDescriptionArr[151] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outFileProgress);
        themeDescriptionArr[152] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inFileProgressSelected);
        themeDescriptionArr[153] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outFileProgressSelected);
        themeDescriptionArr[154] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inFileNameText);
        themeDescriptionArr[155] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outFileNameText);
        themeDescriptionArr[156] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inFileInfoText);
        themeDescriptionArr[157] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outFileInfoText);
        themeDescriptionArr[158] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inFileInfoSelectedText);
        themeDescriptionArr[159] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outFileInfoSelectedText);
        themeDescriptionArr[160] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inFileBackground);
        themeDescriptionArr[161] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outFileBackground);
        themeDescriptionArr[162] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inFileBackgroundSelected);
        themeDescriptionArr[163] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outFileBackgroundSelected);
        themeDescriptionArr[164] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inVenueNameText);
        themeDescriptionArr[165] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outVenueNameText);
        themeDescriptionArr[166] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inVenueInfoText);
        themeDescriptionArr[167] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outVenueInfoText);
        themeDescriptionArr[168] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inVenueInfoSelectedText);
        themeDescriptionArr[169] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outVenueInfoSelectedText);
        themeDescriptionArr[170] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_mediaInfoText);
        themeDescriptionArr[171] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, Theme.chat_urlPaint, null, null, Theme.key_chat_linkSelectBackground);
        themeDescriptionArr[172] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, Theme.chat_textSearchSelectionPaint, null, null, Theme.key_chat_textSelectBackground);
        themeDescriptionArr[173] = new ThemeDescription(this.chatListView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_fileStatesDrawable[0][0], Theme.chat_fileStatesDrawable[1][0], Theme.chat_fileStatesDrawable[2][0], Theme.chat_fileStatesDrawable[3][0], Theme.chat_fileStatesDrawable[4][0]}, null, Theme.key_chat_outLoader);
        themeDescriptionArr[174] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_fileStatesDrawable[0][0], Theme.chat_fileStatesDrawable[1][0], Theme.chat_fileStatesDrawable[2][0], Theme.chat_fileStatesDrawable[3][0], Theme.chat_fileStatesDrawable[4][0]}, null, Theme.key_chat_outBubble);
        themeDescriptionArr[175] = new ThemeDescription(this.chatListView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_fileStatesDrawable[0][1], Theme.chat_fileStatesDrawable[1][1], Theme.chat_fileStatesDrawable[2][1], Theme.chat_fileStatesDrawable[3][1], Theme.chat_fileStatesDrawable[4][1]}, null, Theme.key_chat_outLoaderSelected);
        themeDescriptionArr[176] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_fileStatesDrawable[0][1], Theme.chat_fileStatesDrawable[1][1], Theme.chat_fileStatesDrawable[2][1], Theme.chat_fileStatesDrawable[3][1], Theme.chat_fileStatesDrawable[4][1]}, null, Theme.key_chat_outBubbleSelected);
        themeDescriptionArr[177] = new ThemeDescription(this.chatListView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_fileStatesDrawable[5][0], Theme.chat_fileStatesDrawable[6][0], Theme.chat_fileStatesDrawable[7][0], Theme.chat_fileStatesDrawable[8][0], Theme.chat_fileStatesDrawable[9][0]}, null, Theme.key_chat_inLoader);
        themeDescriptionArr[178] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_fileStatesDrawable[5][0], Theme.chat_fileStatesDrawable[6][0], Theme.chat_fileStatesDrawable[7][0], Theme.chat_fileStatesDrawable[8][0], Theme.chat_fileStatesDrawable[9][0]}, null, Theme.key_chat_inBubble);
        themeDescriptionArr[179] = new ThemeDescription(this.chatListView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_fileStatesDrawable[5][1], Theme.chat_fileStatesDrawable[6][1], Theme.chat_fileStatesDrawable[7][1], Theme.chat_fileStatesDrawable[8][1], Theme.chat_fileStatesDrawable[9][1]}, null, Theme.key_chat_inLoaderSelected);
        themeDescriptionArr[180] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_fileStatesDrawable[5][1], Theme.chat_fileStatesDrawable[6][1], Theme.chat_fileStatesDrawable[7][1], Theme.chat_fileStatesDrawable[8][1], Theme.chat_fileStatesDrawable[9][1]}, null, Theme.key_chat_inBubbleSelected);
        themeDescriptionArr[PDUiBeacon.Swirl] = new ThemeDescription(this.chatListView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_photoStatesDrawables[0][0], Theme.chat_photoStatesDrawables[1][0], Theme.chat_photoStatesDrawables[2][0], Theme.chat_photoStatesDrawables[3][0]}, null, Theme.key_chat_mediaLoaderPhoto);
        themeDescriptionArr[182] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_photoStatesDrawables[0][0], Theme.chat_photoStatesDrawables[1][0], Theme.chat_photoStatesDrawables[2][0], Theme.chat_photoStatesDrawables[3][0]}, null, Theme.key_chat_mediaLoaderPhotoIcon);
        themeDescriptionArr[183] = new ThemeDescription(this.chatListView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_photoStatesDrawables[0][1], Theme.chat_photoStatesDrawables[1][1], Theme.chat_photoStatesDrawables[2][1], Theme.chat_photoStatesDrawables[3][1]}, null, Theme.key_chat_mediaLoaderPhotoSelected);
        themeDescriptionArr[184] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_photoStatesDrawables[0][1], Theme.chat_photoStatesDrawables[1][1], Theme.chat_photoStatesDrawables[2][1], Theme.chat_photoStatesDrawables[3][1]}, null, Theme.key_chat_mediaLoaderPhotoIconSelected);
        themeDescriptionArr[185] = new ThemeDescription(this.chatListView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_photoStatesDrawables[7][0], Theme.chat_photoStatesDrawables[8][0]}, null, Theme.key_chat_outLoaderPhoto);
        themeDescriptionArr[186] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_photoStatesDrawables[7][0], Theme.chat_photoStatesDrawables[8][0]}, null, Theme.key_chat_outLoaderPhotoIcon);
        themeDescriptionArr[187] = new ThemeDescription(this.chatListView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_photoStatesDrawables[7][1], Theme.chat_photoStatesDrawables[8][1]}, null, Theme.key_chat_outLoaderPhotoSelected);
        themeDescriptionArr[188] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_photoStatesDrawables[7][1], Theme.chat_photoStatesDrawables[8][1]}, null, Theme.key_chat_outLoaderPhotoIconSelected);
        themeDescriptionArr[189] = new ThemeDescription(this.chatListView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_photoStatesDrawables[10][0], Theme.chat_photoStatesDrawables[11][0]}, null, Theme.key_chat_inLoaderPhoto);
        themeDescriptionArr[190] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_photoStatesDrawables[10][0], Theme.chat_photoStatesDrawables[11][0]}, null, Theme.key_chat_inLoaderPhotoIcon);
        themeDescriptionArr[191] = new ThemeDescription(this.chatListView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_photoStatesDrawables[10][1], Theme.chat_photoStatesDrawables[11][1]}, null, Theme.key_chat_inLoaderPhotoSelected);
        themeDescriptionArr[192] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_photoStatesDrawables[10][1], Theme.chat_photoStatesDrawables[11][1]}, null, Theme.key_chat_inLoaderPhotoIconSelected);
        themeDescriptionArr[193] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_photoStatesDrawables[9][0]}, null, Theme.key_chat_outFileIcon);
        themeDescriptionArr[194] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_photoStatesDrawables[9][1]}, null, Theme.key_chat_outFileSelectedIcon);
        themeDescriptionArr[195] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_photoStatesDrawables[12][0]}, null, Theme.key_chat_inFileIcon);
        themeDescriptionArr[196] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_photoStatesDrawables[12][1]}, null, Theme.key_chat_inFileSelectedIcon);
        themeDescriptionArr[197] = new ThemeDescription(this.chatListView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_contactDrawable[0]}, null, Theme.key_chat_inContactBackground);
        themeDescriptionArr[198] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_contactDrawable[0]}, null, Theme.key_chat_inContactIcon);
        themeDescriptionArr[199] = new ThemeDescription(this.chatListView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_contactDrawable[1]}, null, Theme.key_chat_outContactBackground);
        themeDescriptionArr[200] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_contactDrawable[1]}, null, Theme.key_chat_outContactIcon);
        themeDescriptionArr[201] = new ThemeDescription(this.chatListView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_locationDrawable[0]}, null, Theme.key_chat_inLocationBackground);
        themeDescriptionArr[HttpStatus.SC_ACCEPTED] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_locationDrawable[0]}, null, Theme.key_chat_inLocationIcon);
        themeDescriptionArr[HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION] = new ThemeDescription(this.chatListView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_locationDrawable[1]}, null, Theme.key_chat_outLocationBackground);
        themeDescriptionArr[HttpStatus.SC_NO_CONTENT] = new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_locationDrawable[1]}, null, Theme.key_chat_outLocationIcon);
        themeDescriptionArr[HttpStatus.SC_RESET_CONTENT] = new ThemeDescription(this.mentionContainer, 0, null, Theme.chat_composeBackgroundPaint, null, null, Theme.key_chat_messagePanelBackground);
        themeDescriptionArr[HttpStatus.SC_PARTIAL_CONTENT] = new ThemeDescription(this.mentionContainer, 0, null, null, new Drawable[]{Theme.chat_composeShadowDrawable}, null, Theme.key_chat_messagePanelShadow);
        themeDescriptionArr[HttpStatus.SC_MULTI_STATUS] = new ThemeDescription(this.searchContainer, 0, null, Theme.chat_composeBackgroundPaint, null, null, Theme.key_chat_messagePanelBackground);
        themeDescriptionArr[208] = new ThemeDescription(this.searchContainer, 0, null, null, new Drawable[]{Theme.chat_composeShadowDrawable}, null, Theme.key_chat_messagePanelShadow);
        themeDescriptionArr[209] = new ThemeDescription(this.bottomOverlay, 0, null, Theme.chat_composeBackgroundPaint, null, null, Theme.key_chat_messagePanelBackground);
        themeDescriptionArr[210] = new ThemeDescription(this.bottomOverlay, 0, null, null, new Drawable[]{Theme.chat_composeShadowDrawable}, null, Theme.key_chat_messagePanelShadow);
        themeDescriptionArr[211] = new ThemeDescription(this.bottomOverlayChat, 0, null, Theme.chat_composeBackgroundPaint, null, null, Theme.key_chat_messagePanelBackground);
        themeDescriptionArr[212] = new ThemeDescription(this.bottomOverlayChat, 0, null, null, new Drawable[]{Theme.chat_composeShadowDrawable}, null, Theme.key_chat_messagePanelShadow);
        themeDescriptionArr[213] = new ThemeDescription(this.chatActivityEnterView, 0, null, Theme.chat_composeBackgroundPaint, null, null, Theme.key_chat_messagePanelBackground);
        themeDescriptionArr[214] = new ThemeDescription(this.chatActivityEnterView, 0, null, null, new Drawable[]{Theme.chat_composeShadowDrawable}, null, Theme.key_chat_messagePanelShadow);
        themeDescriptionArr[215] = new ThemeDescription(this.chatActivityEnterView, ThemeDescription.FLAG_BACKGROUND, new Class[]{ChatActivityEnterView.class}, new String[]{"audioVideoButtonContainer"}, null, null, null, Theme.key_chat_messagePanelBackground);
        themeDescriptionArr[216] = new ThemeDescription(this.chatActivityEnterView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{ChatActivityEnterView.class}, new String[]{"messageEditText"}, null, null, null, Theme.key_chat_messagePanelText);
        themeDescriptionArr[217] = new ThemeDescription(this.chatActivityEnterView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{ChatActivityEnterView.class}, new String[]{"recordSendText"}, null, null, null, Theme.key_chat_fieldOverlayText);
        themeDescriptionArr[218] = new ThemeDescription(this.chatActivityEnterView, ThemeDescription.FLAG_HINTTEXTCOLOR, new Class[]{ChatActivityEnterView.class}, new String[]{"messageEditText"}, null, null, null, Theme.key_chat_messagePanelHint);
        themeDescriptionArr[219] = new ThemeDescription(this.chatActivityEnterView, 0, new Class[]{ChatActivityEnterView.class}, new String[]{"sendButton"}, null, null, null, Theme.key_chat_messagePanelSend);
        themeDescriptionArr[220] = new ThemeDescription(this.chatActivityEnterView, 0, new Class[]{ChatActivityEnterView.class}, new String[]{"emojiButton"}, null, null, null, Theme.key_chat_messagePanelIcons);
        themeDescriptionArr[221] = new ThemeDescription(this.chatActivityEnterView, 0, new Class[]{ChatActivityEnterView.class}, new String[]{"botButton"}, null, null, null, Theme.key_chat_messagePanelIcons);
        themeDescriptionArr[222] = new ThemeDescription(this.chatActivityEnterView, 0, new Class[]{ChatActivityEnterView.class}, new String[]{"notifyButton"}, null, null, null, Theme.key_chat_messagePanelIcons);
        themeDescriptionArr[223] = new ThemeDescription(this.chatActivityEnterView, 0, new Class[]{ChatActivityEnterView.class}, new String[]{"attachButton"}, null, null, null, Theme.key_chat_messagePanelIcons);
        themeDescriptionArr[224] = new ThemeDescription(this.chatActivityEnterView, 0, new Class[]{ChatActivityEnterView.class}, new String[]{"audioSendButton"}, null, null, null, Theme.key_chat_messagePanelIcons);
        themeDescriptionArr[225] = new ThemeDescription(this.chatActivityEnterView, 0, new Class[]{ChatActivityEnterView.class}, new String[]{"videoSendButton"}, null, null, null, Theme.key_chat_messagePanelIcons);
        themeDescriptionArr[ElephantLib.SDK_VERSIONCODE] = new ThemeDescription(this.chatActivityEnterView, ThemeDescription.FLAG_IMAGECOLOR, new Class[]{ChatActivityEnterView.class}, new String[]{"doneButtonImage"}, null, null, null, Theme.key_chat_editDoneIcon);
        themeDescriptionArr[227] = new ThemeDescription(this.chatActivityEnterView, ThemeDescription.FLAG_BACKGROUND, new Class[]{ChatActivityEnterView.class}, new String[]{"recordedAudioPanel"}, null, null, null, Theme.key_chat_messagePanelBackground);
        themeDescriptionArr[228] = new ThemeDescription(this.chatActivityEnterView, 0, new Class[]{ChatActivityEnterView.class}, new String[]{"micDrawable"}, null, null, null, Theme.key_chat_messagePanelVoicePressed);
        themeDescriptionArr[229] = new ThemeDescription(this.chatActivityEnterView, 0, new Class[]{ChatActivityEnterView.class}, new String[]{"cameraDrawable"}, null, null, null, Theme.key_chat_messagePanelVoicePressed);
        themeDescriptionArr[230] = new ThemeDescription(this.chatActivityEnterView, 0, new Class[]{ChatActivityEnterView.class}, new String[]{"sendDrawable"}, null, null, null, Theme.key_chat_messagePanelVoicePressed);
        themeDescriptionArr[231] = new ThemeDescription(this.chatActivityEnterView, 0, new Class[]{ChatActivityEnterView.class}, new String[]{"lockDrawable"}, null, null, null, Theme.key_chat_messagePanelVoiceLock);
        themeDescriptionArr[232] = new ThemeDescription(this.chatActivityEnterView, 0, new Class[]{ChatActivityEnterView.class}, new String[]{"lockTopDrawable"}, null, null, null, Theme.key_chat_messagePanelVoiceLock);
        themeDescriptionArr[233] = new ThemeDescription(this.chatActivityEnterView, 0, new Class[]{ChatActivityEnterView.class}, new String[]{"lockArrowDrawable"}, null, null, null, Theme.key_chat_messagePanelVoiceLock);
        themeDescriptionArr[234] = new ThemeDescription(this.chatActivityEnterView, 0, new Class[]{ChatActivityEnterView.class}, new String[]{"lockBackgroundDrawable"}, null, null, null, Theme.key_chat_messagePanelVoiceLockBackground);
        themeDescriptionArr[235] = new ThemeDescription(this.chatActivityEnterView, 0, new Class[]{ChatActivityEnterView.class}, new String[]{"lockShadowDrawable"}, null, null, null, Theme.key_chat_messagePanelVoiceLockShadow);
        themeDescriptionArr[236] = new ThemeDescription(this.chatActivityEnterView, ThemeDescription.FLAG_IMAGECOLOR, new Class[]{ChatActivityEnterView.class}, new String[]{"recordDeleteImageView"}, null, null, null, Theme.key_chat_messagePanelVoiceDelete);
        themeDescriptionArr[237] = new ThemeDescription(this.chatActivityEnterView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ChatActivityEnterView.class}, new String[]{"recordedAudioBackground"}, null, null, null, Theme.key_chat_recordedVoiceBackground);
        themeDescriptionArr[238] = new ThemeDescription(this.chatActivityEnterView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{ChatActivityEnterView.class}, new String[]{"recordTimeText"}, null, null, null, Theme.key_chat_recordTime);
        themeDescriptionArr[239] = new ThemeDescription(this.chatActivityEnterView, ThemeDescription.FLAG_BACKGROUND, new Class[]{ChatActivityEnterView.class}, new String[]{"recordTimeContainer"}, null, null, null, Theme.key_chat_messagePanelBackground);
        themeDescriptionArr[240] = new ThemeDescription(this.chatActivityEnterView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{ChatActivityEnterView.class}, new String[]{"recordCancelText"}, null, null, null, Theme.key_chat_recordVoiceCancel);
        themeDescriptionArr[241] = new ThemeDescription(this.chatActivityEnterView, ThemeDescription.FLAG_BACKGROUND, new Class[]{ChatActivityEnterView.class}, new String[]{"recordPanel"}, null, null, null, Theme.key_chat_messagePanelBackground);
        themeDescriptionArr[242] = new ThemeDescription(this.chatActivityEnterView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{ChatActivityEnterView.class}, new String[]{"recordedAudioTimeTextView"}, null, null, null, Theme.key_chat_messagePanelVoiceDuration);
        themeDescriptionArr[243] = new ThemeDescription(this.chatActivityEnterView, ThemeDescription.FLAG_IMAGECOLOR, new Class[]{ChatActivityEnterView.class}, new String[]{"recordCancelImage"}, null, null, null, Theme.key_chat_recordVoiceCancel);
        themeDescriptionArr[244] = new ThemeDescription(this.chatActivityEnterView, 0, new Class[]{ChatActivityEnterView.class}, new String[]{"doneButtonProgress"}, null, null, null, Theme.key_contextProgressInner1);
        themeDescriptionArr[245] = new ThemeDescription(this.chatActivityEnterView, 0, new Class[]{ChatActivityEnterView.class}, new String[]{"doneButtonProgress"}, null, null, null, Theme.key_contextProgressOuter1);
        themeDescriptionArr[246] = new ThemeDescription(this.chatActivityEnterView, ThemeDescription.FLAG_IMAGECOLOR, new Class[]{ChatActivityEnterView.class}, new String[]{"cancelBotButton"}, null, null, null, Theme.key_chat_messagePanelCancelInlineBot);
        themeDescriptionArr[247] = new ThemeDescription(this.chatActivityEnterView, 0, new Class[]{ChatActivityEnterView.class}, new String[]{"redDotPaint"}, null, null, null, Theme.key_chat_recordedVoiceDot);
        themeDescriptionArr[248] = new ThemeDescription(this.chatActivityEnterView, 0, new Class[]{ChatActivityEnterView.class}, new String[]{"paint"}, null, null, null, Theme.key_chat_messagePanelVoiceBackground);
        themeDescriptionArr[249] = new ThemeDescription(this.chatActivityEnterView, 0, new Class[]{ChatActivityEnterView.class}, new String[]{"paintRecord"}, null, null, null, Theme.key_chat_messagePanelVoiceShadow);
        themeDescriptionArr[250] = new ThemeDescription(this.chatActivityEnterView, 0, new Class[]{ChatActivityEnterView.class}, new String[]{"seekBarWaveform"}, null, null, null, Theme.key_chat_recordedVoiceProgress);
        themeDescriptionArr[251] = new ThemeDescription(this.chatActivityEnterView, 0, new Class[]{ChatActivityEnterView.class}, new String[]{"seekBarWaveform"}, null, null, null, Theme.key_chat_recordedVoiceProgressInner);
        themeDescriptionArr[252] = new ThemeDescription(this.chatActivityEnterView, 0, new Class[]{ChatActivityEnterView.class}, new String[]{"playDrawable"}, null, null, null, Theme.key_chat_recordedVoicePlayPause);
        themeDescriptionArr[253] = new ThemeDescription(this.chatActivityEnterView, 0, new Class[]{ChatActivityEnterView.class}, new String[]{"pauseDrawable"}, null, null, null, Theme.key_chat_recordedVoicePlayPause);
        themeDescriptionArr[254] = new ThemeDescription(this.chatActivityEnterView, 0, new Class[]{ChatActivityEnterView.class}, new String[]{"dotPaint"}, null, null, null, Theme.key_chat_emojiPanelNewTrending);
        themeDescriptionArr[255] = new ThemeDescription(this.chatActivityEnterView, ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, new Class[]{ChatActivityEnterView.class}, new String[]{"playDrawable"}, null, null, null, Theme.key_chat_recordedVoicePlayPausePressed);
        themeDescriptionArr[256] = new ThemeDescription(this.chatActivityEnterView, ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, new Class[]{ChatActivityEnterView.class}, new String[]{"pauseDrawable"}, null, null, null, Theme.key_chat_recordedVoicePlayPausePressed);
        themeDescriptionArr[InputDeviceCompat.SOURCE_KEYBOARD] = new ThemeDescription(this.chatActivityEnterView.getEmojiView(), 0, new Class[]{EmojiView.class}, new String[]{""}, null, null, null, Theme.key_chat_emojiPanelBackground);
        themeDescriptionArr[258] = new ThemeDescription(this.chatActivityEnterView.getEmojiView(), 0, new Class[]{EmojiView.class}, new String[]{""}, null, null, null, Theme.key_chat_emojiPanelShadowLine);
        themeDescriptionArr[259] = new ThemeDescription(this.chatActivityEnterView.getEmojiView(), 0, new Class[]{EmojiView.class}, new String[]{""}, null, null, null, Theme.key_chat_emojiPanelEmptyText);
        themeDescriptionArr[260] = new ThemeDescription(this.chatActivityEnterView.getEmojiView(), 0, new Class[]{EmojiView.class}, new String[]{""}, null, null, null, Theme.key_chat_emojiPanelIcon);
        themeDescriptionArr[261] = new ThemeDescription(this.chatActivityEnterView.getEmojiView(), 0, new Class[]{EmojiView.class}, new String[]{""}, null, null, null, Theme.key_chat_emojiPanelIconSelected);
        themeDescriptionArr[262] = new ThemeDescription(this.chatActivityEnterView.getEmojiView(), 0, new Class[]{EmojiView.class}, new String[]{""}, null, null, null, Theme.key_chat_emojiPanelStickerPackSelector);
        themeDescriptionArr[263] = new ThemeDescription(this.chatActivityEnterView.getEmojiView(), 0, new Class[]{EmojiView.class}, new String[]{""}, null, null, null, Theme.key_chat_emojiPanelIconSelector);
        themeDescriptionArr[264] = new ThemeDescription(this.chatActivityEnterView.getEmojiView(), 0, new Class[]{EmojiView.class}, new String[]{""}, null, null, null, Theme.key_chat_emojiPanelBackspace);
        themeDescriptionArr[265] = new ThemeDescription(this.chatActivityEnterView.getEmojiView(), 0, new Class[]{EmojiView.class}, new String[]{""}, null, null, null, Theme.key_chat_emojiPanelTrendingTitle);
        themeDescriptionArr[266] = new ThemeDescription(this.chatActivityEnterView.getEmojiView(), 0, new Class[]{EmojiView.class}, new String[]{""}, null, null, null, Theme.key_chat_emojiPanelTrendingDescription);
        themeDescriptionArr[267] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_chat_botKeyboardButtonText);
        themeDescriptionArr[268] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_chat_botKeyboardButtonBackground);
        themeDescriptionArr[269] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_chat_botKeyboardButtonBackgroundPressed);
        themeDescriptionArr[270] = new ThemeDescription(this.fragmentContextView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{FragmentContextView.class}, new String[]{"frameLayout"}, null, null, null, Theme.key_inappPlayerBackground);
        themeDescriptionArr[271] = new ThemeDescription(this.fragmentContextView, 0, new Class[]{FragmentContextView.class}, new String[]{"playButton"}, null, null, null, Theme.key_inappPlayerPlayPause);
        themeDescriptionArr[272] = new ThemeDescription(this.fragmentContextView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{FragmentContextView.class}, new String[]{"titleTextView"}, null, null, null, Theme.key_inappPlayerTitle);
        themeDescriptionArr[273] = new ThemeDescription(this.fragmentContextView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{FragmentContextView.class}, new String[]{"frameLayout"}, null, null, null, Theme.key_inappPlayerPerformer);
        themeDescriptionArr[274] = new ThemeDescription(this.fragmentContextView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{FragmentContextView.class}, new String[]{"closeButton"}, null, null, null, Theme.key_inappPlayerClose);
        themeDescriptionArr[275] = new ThemeDescription(this.fragmentContextView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{FragmentContextView.class}, new String[]{"frameLayout"}, null, null, null, Theme.key_returnToCallBackground);
        themeDescriptionArr[276] = new ThemeDescription(this.fragmentContextView, 0, new Class[]{FragmentContextView.class}, new String[]{"titleTextView"}, null, null, null, Theme.key_returnToCallText);
        themeDescriptionArr[277] = new ThemeDescription(this.pinnedLineView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_chat_topPanelLine);
        themeDescriptionArr[278] = new ThemeDescription(this.pinnedMessageNameTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_chat_topPanelTitle);
        themeDescriptionArr[ModuleDescriptor.MODULE_VERSION] = new ThemeDescription(this.pinnedMessageTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_chat_topPanelMessage);
        themeDescriptionArr[PDUiBeacon.Radius] = new ThemeDescription(this.alertNameTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_chat_topPanelTitle);
        themeDescriptionArr[281] = new ThemeDescription(this.alertTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_chat_topPanelMessage);
        themeDescriptionArr[282] = new ThemeDescription(this.closePinned, ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, Theme.key_chat_topPanelClose);
        themeDescriptionArr[283] = new ThemeDescription(this.closeReportSpam, ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, Theme.key_chat_topPanelClose);
        themeDescriptionArr[284] = new ThemeDescription(this.reportSpamView, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, Theme.key_chat_topPanelBackground);
        themeDescriptionArr[285] = new ThemeDescription(this.alertView, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, Theme.key_chat_topPanelBackground);
        themeDescriptionArr[286] = new ThemeDescription(this.pinnedMessageView, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, Theme.key_chat_topPanelBackground);
        themeDescriptionArr[287] = new ThemeDescription(this.addToContactsButton, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_chat_addContact);
        themeDescriptionArr[288] = new ThemeDescription(this.reportSpamButton, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_chat_reportSpam);
        themeDescriptionArr[289] = new ThemeDescription(this.replyLineView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_chat_replyPanelLine);
        themeDescriptionArr[290] = new ThemeDescription(this.replyNameTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_chat_replyPanelName);
        themeDescriptionArr[291] = new ThemeDescription(this.replyObjectTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_chat_replyPanelMessage);
        themeDescriptionArr[292] = new ThemeDescription(this.replyIconImageView, ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, Theme.key_chat_replyPanelIcons);
        themeDescriptionArr[293] = new ThemeDescription(this.replyCloseImageView, ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, Theme.key_chat_replyPanelClose);
        themeDescriptionArr[294] = new ThemeDescription(this.searchUpButton, ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, Theme.key_chat_searchPanelIcons);
        themeDescriptionArr[295] = new ThemeDescription(this.searchDownButton, ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, Theme.key_chat_searchPanelIcons);
        themeDescriptionArr[296] = new ThemeDescription(this.searchCalendarButton, ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, Theme.key_chat_searchPanelIcons);
        themeDescriptionArr[297] = new ThemeDescription(this.searchUserButton, ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, Theme.key_chat_searchPanelIcons);
        themeDescriptionArr[298] = new ThemeDescription(this.searchCountText, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_chat_searchPanelText);
        themeDescriptionArr[299] = new ThemeDescription(this.bottomOverlayText, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_chat_secretChatStatusText);
        themeDescriptionArr[HttpStatus.SC_MULTIPLE_CHOICES] = new ThemeDescription(this.bottomOverlayChatText, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_chat_fieldOverlayText);
        themeDescriptionArr[HttpStatus.SC_MOVED_PERMANENTLY] = new ThemeDescription(this.bigEmptyView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_chat_serviceText);
        themeDescriptionArr[HttpStatus.SC_MOVED_TEMPORARILY] = new ThemeDescription(this.emptyView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_chat_serviceText);
        themeDescriptionArr[HttpStatus.SC_SEE_OTHER] = new ThemeDescription(this.progressBar, ThemeDescription.FLAG_PROGRESSBAR, null, null, null, null, Theme.key_chat_serviceText);
        themeDescriptionArr[HttpStatus.SC_NOT_MODIFIED] = new ThemeDescription(this.stickersPanelArrow, ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, Theme.key_chat_stickersHintPanel);
        themeDescriptionArr[HttpStatus.SC_USE_PROXY] = new ThemeDescription(this.stickersListView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{StickerCell.class}, null, null, null, Theme.key_chat_stickersHintPanel);
        themeDescriptionArr[306] = new ThemeDescription(this.chatListView, ThemeDescription.FLAG_USEBACKGROUNDDRAWABLE, new Class[]{ChatUnreadCell.class}, new String[]{"backgroundLayout"}, null, null, null, Theme.key_chat_unreadMessagesStartBackground);
        themeDescriptionArr[307] = new ThemeDescription(this.chatListView, ThemeDescription.FLAG_IMAGECOLOR, new Class[]{ChatUnreadCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_chat_unreadMessagesStartArrowIcon);
        themeDescriptionArr[StatusLine.HTTP_PERM_REDIRECT] = new ThemeDescription(this.chatListView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{ChatUnreadCell.class}, new String[]{"textView"}, null, null, null, Theme.key_chat_unreadMessagesStartText);
        themeDescriptionArr[309] = new ThemeDescription(this.progressView2, ThemeDescription.FLAG_SERVICEBACKGROUND, null, null, null, null, Theme.key_chat_serviceBackground);
        themeDescriptionArr[310] = new ThemeDescription(this.emptyView, ThemeDescription.FLAG_SERVICEBACKGROUND, null, null, null, null, Theme.key_chat_serviceBackground);
        themeDescriptionArr[311] = new ThemeDescription(this.bigEmptyView, ThemeDescription.FLAG_SERVICEBACKGROUND, null, null, null, null, Theme.key_chat_serviceBackground);
        themeDescriptionArr[312] = new ThemeDescription(this.chatListView, ThemeDescription.FLAG_SERVICEBACKGROUND, new Class[]{ChatLoadingCell.class}, new String[]{"textView"}, null, null, null, Theme.key_chat_serviceBackground);
        themeDescriptionArr[313] = new ThemeDescription(this.chatListView, ThemeDescription.FLAG_PROGRESSBAR, new Class[]{ChatLoadingCell.class}, new String[]{"textView"}, null, null, null, Theme.key_chat_serviceText);
        themeDescriptionArr[314] = new ThemeDescription(this.mentionListView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{BotSwitchCell.class}, new String[]{"textView"}, null, null, null, Theme.key_chat_botSwitchToInlineText);
        themeDescriptionArr[315] = new ThemeDescription(this.mentionListView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{MentionCell.class}, new String[]{"nameTextView"}, null, null, null, Theme.key_windowBackgroundWhiteBlackText);
        themeDescriptionArr[316] = new ThemeDescription(this.mentionListView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{MentionCell.class}, new String[]{"usernameTextView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText3);
        themeDescriptionArr[317] = new ThemeDescription(this.mentionListView, 0, new Class[]{ContextLinkCell.class}, null, new Drawable[]{Theme.chat_inlineResultFile, Theme.chat_inlineResultAudio, Theme.chat_inlineResultLocation}, null, Theme.key_chat_inlineResultIcon);
        themeDescriptionArr[318] = new ThemeDescription(this.mentionListView, 0, new Class[]{ContextLinkCell.class}, null, null, null, Theme.key_windowBackgroundWhiteGrayText2);
        themeDescriptionArr[319] = new ThemeDescription(this.mentionListView, 0, new Class[]{ContextLinkCell.class}, null, null, null, Theme.key_windowBackgroundWhiteLinkText);
        themeDescriptionArr[320] = new ThemeDescription(this.mentionListView, 0, new Class[]{ContextLinkCell.class}, null, null, null, Theme.key_windowBackgroundWhiteBlackText);
        themeDescriptionArr[321] = new ThemeDescription(this.mentionListView, 0, new Class[]{ContextLinkCell.class}, null, null, null, Theme.key_chat_inAudioProgress);
        themeDescriptionArr[322] = new ThemeDescription(this.mentionListView, 0, new Class[]{ContextLinkCell.class}, null, null, null, Theme.key_chat_inAudioSelectedProgress);
        themeDescriptionArr[323] = new ThemeDescription(this.mentionListView, 0, new Class[]{ContextLinkCell.class}, null, null, null, Theme.key_divider);
        themeDescriptionArr[324] = new ThemeDescription(this.gifHintTextView, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, Theme.key_chat_gifSaveHintBackground);
        themeDescriptionArr[325] = new ThemeDescription(this.gifHintTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_chat_gifSaveHintText);
        themeDescriptionArr[326] = new ThemeDescription(this.pagedownButtonCounter, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, Theme.key_chat_goDownButtonCounterBackground);
        themeDescriptionArr[327] = new ThemeDescription(this.pagedownButtonCounter, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_chat_goDownButtonCounter);
        themeDescriptionArr[328] = new ThemeDescription(this.pagedownButtonImage, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, Theme.key_chat_goDownButton);
        themeDescriptionArr[329] = new ThemeDescription(this.pagedownButtonImage, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, null, null, null, null, Theme.key_chat_goDownButtonShadow);
        themeDescriptionArr[330] = new ThemeDescription(this.pagedownButtonImage, ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, Theme.key_chat_goDownButtonIcon);
        themeDescriptionArr[331] = new ThemeDescription(this.mentiondownButtonCounter, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, Theme.key_chat_goDownButtonCounterBackground);
        themeDescriptionArr[332] = new ThemeDescription(this.mentiondownButtonCounter, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_chat_goDownButtonCounter);
        themeDescriptionArr[333] = new ThemeDescription(this.mentiondownButtonImage, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, Theme.key_chat_goDownButton);
        themeDescriptionArr[334] = new ThemeDescription(this.mentiondownButtonImage, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, null, null, null, null, Theme.key_chat_goDownButtonShadow);
        themeDescriptionArr[335] = new ThemeDescription(this.mentiondownButtonImage, ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, Theme.key_chat_goDownButtonIcon);
        themeDescriptionArr[336] = new ThemeDescription(this.avatarContainer.getTimeItem(), 0, null, null, null, null, Theme.key_chat_secretTimerBackground);
        themeDescriptionArr[337] = new ThemeDescription(this.avatarContainer.getTimeItem(), 0, null, null, null, null, Theme.key_chat_secretTimerText);
        return themeDescriptionArr;
    }
}
