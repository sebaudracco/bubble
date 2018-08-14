package org.telegram.ui;

import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.appsgeyser.sdk.AppsgeyserSDK;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;
import com.wBubble_7453037.R;
import java.util.Collections;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.LocaleController$LocaleInfo;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.NotificationCenter.NotificationCenterDelegate;
import org.telegram.messenger.config.IntroPage;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$LangPackString;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_langPackString;
import org.telegram.tgnet.TLRPC$TL_langpack_getStrings;
import org.telegram.tgnet.TLRPC$Vector;
import org.telegram.ui.Components.LayoutHelper;

public class IntroActivity extends Activity implements NotificationCenterDelegate {
    private BottomPagesView bottomPages;
    private Drawable[] icons;
    private boolean justCreated = false;
    private int lastPage = 0;
    private LocaleController$LocaleInfo localeInfo;
    private CharSequence[] messages;
    private boolean startPressed = false;
    private TextView textView;
    private String[] titles;
    private ImageView topImage1;
    private ImageView topImage2;
    private ViewPager viewPager;

    class C64671 implements OnPageChangeListener {
        C64671() {
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            IntroActivity.this.bottomPages.setPageOffset(position, positionOffset);
        }

        public void onPageSelected(int i) {
        }

        public void onPageScrollStateChanged(int i) {
            if ((i == 0 || i == 2) && IntroActivity.this.lastPage != IntroActivity.this.viewPager.getCurrentItem()) {
                ImageView fadeoutImage;
                ImageView fadeinImage;
                IntroActivity.this.lastPage = IntroActivity.this.viewPager.getCurrentItem();
                if (IntroActivity.this.topImage1.getVisibility() == 0) {
                    fadeoutImage = IntroActivity.this.topImage1;
                    fadeinImage = IntroActivity.this.topImage2;
                } else {
                    fadeoutImage = IntroActivity.this.topImage2;
                    fadeinImage = IntroActivity.this.topImage1;
                }
                fadeinImage.bringToFront();
                fadeinImage.setImageDrawable(IntroActivity.this.icons[IntroActivity.this.lastPage]);
                fadeinImage.clearAnimation();
                fadeoutImage.clearAnimation();
                Animation outAnimation = AnimationUtils.loadAnimation(IntroActivity.this, R.anim.icon_anim_fade_out);
                outAnimation.setAnimationListener(new AnimationListener() {
                    public void onAnimationStart(Animation animation) {
                    }

                    public void onAnimationEnd(Animation animation) {
                        fadeoutImage.setVisibility(8);
                    }

                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                Animation inAnimation = AnimationUtils.loadAnimation(IntroActivity.this, R.anim.icon_anim_fade_in);
                inAnimation.setAnimationListener(new AnimationListener() {
                    public void onAnimationStart(Animation animation) {
                        fadeinImage.setVisibility(0);
                    }

                    public void onAnimationEnd(Animation animation) {
                    }

                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                fadeoutImage.startAnimation(outAnimation);
                fadeinImage.startAnimation(inAnimation);
            }
        }
    }

    class C64682 implements OnClickListener {
        C64682() {
        }

        public void onClick(View view) {
            if (!IntroActivity.this.startPressed) {
                IntroActivity.this.startPressed = true;
                Intent intent2 = new Intent(IntroActivity.this, LaunchActivity.class);
                intent2.putExtra("fromIntro", true);
                IntroActivity.this.startActivity(intent2);
                IntroActivity.this.finish();
            }
        }
    }

    class C64693 implements OnLongClickListener {
        C64693() {
        }

        public boolean onLongClick(View v) {
            ConnectionsManager.getInstance().switchBackend();
            return true;
        }
    }

    class C64704 implements OnClickListener {
        C64704() {
        }

        public void onClick(View v) {
            if (!IntroActivity.this.startPressed && IntroActivity.this.localeInfo != null) {
                LocaleController.getInstance().applyLanguage(IntroActivity.this.localeInfo, true, false);
                IntroActivity.this.startPressed = true;
                Intent intent2 = new Intent(IntroActivity.this, LaunchActivity.class);
                intent2.putExtra("fromIntro", true);
                IntroActivity.this.startActivity(intent2);
                IntroActivity.this.finish();
            }
        }
    }

    class C64725 implements RequestDelegate {
        C64725() {
        }

        public void run(TLObject response, TLRPC$TL_error error) {
            if (response != null) {
                TLRPC$Vector vector = (TLRPC$Vector) response;
                if (!vector.objects.isEmpty()) {
                    final TLRPC$LangPackString string = (TLRPC$LangPackString) vector.objects.get(0);
                    if (string instanceof TLRPC$TL_langPackString) {
                        AndroidUtilities.runOnUIThread(new Runnable() {
                            public void run() {
                                IntroActivity.this.textView.setText(string.value);
                            }
                        });
                    }
                }
            }
        }
    }

    private class BottomPagesView extends View {
        private float animatedProgress;
        private int currentPage;
        private DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator();
        private Paint paint = new Paint(1);
        private float progress;
        private RectF rect = new RectF();
        private int scrollPosition;

        public BottomPagesView(Context context) {
            super(context);
        }

        public void setPageOffset(int position, float offset) {
            this.progress = offset;
            this.scrollPosition = position;
            invalidate();
        }

        public void setCurrentPage(int page) {
            this.currentPage = page;
            invalidate();
        }

        protected void onDraw(Canvas canvas) {
            int x;
            float d = (float) AndroidUtilities.dp(5.0f);
            this.paint.setColor(-4473925);
            this.currentPage = IntroActivity.this.viewPager.getCurrentItem();
            for (int a = 0; a < IntroActivity.this.icons.length; a++) {
                if (a != this.currentPage) {
                    x = a * AndroidUtilities.dp(11.0f);
                    this.rect.set((float) x, 0.0f, (float) (AndroidUtilities.dp(5.0f) + x), (float) AndroidUtilities.dp(5.0f));
                    canvas.drawRoundRect(this.rect, (float) AndroidUtilities.dp(2.5f), (float) AndroidUtilities.dp(2.5f), this.paint);
                }
            }
            this.paint.setColor(ApplicationLoader.getConfig().getDefaultTheme().getActionColor().intValue());
            x = this.currentPage * AndroidUtilities.dp(11.0f);
            if (this.progress == 0.0f) {
                this.rect.set((float) x, 0.0f, (float) (AndroidUtilities.dp(5.0f) + x), (float) AndroidUtilities.dp(5.0f));
            } else if (this.scrollPosition >= this.currentPage) {
                this.rect.set((float) x, 0.0f, ((float) (AndroidUtilities.dp(5.0f) + x)) + (((float) AndroidUtilities.dp(11.0f)) * this.progress), (float) AndroidUtilities.dp(5.0f));
            } else {
                this.rect.set(((float) x) - (((float) AndroidUtilities.dp(11.0f)) * (1.0f - this.progress)), 0.0f, (float) (AndroidUtilities.dp(5.0f) + x), (float) AndroidUtilities.dp(5.0f));
            }
            canvas.drawRoundRect(this.rect, (float) AndroidUtilities.dp(2.5f), (float) AndroidUtilities.dp(2.5f), this.paint);
        }
    }

    private class IntroAdapter extends PagerAdapter {
        private IntroAdapter() {
        }

        public int getCount() {
            return IntroActivity.this.icons.length;
        }

        public Object instantiateItem(ViewGroup container, int position) {
            FrameLayout frameLayout = new FrameLayout(container.getContext());
            TextView headerTextView = new TextView(container.getContext());
            headerTextView.setTextColor(ApplicationLoader.getConfig().getDefaultTheme().getTextColor().intValue());
            headerTextView.setTextSize(1, 26.0f);
            headerTextView.setGravity(17);
            frameLayout.addView(headerTextView, LayoutHelper.createFrame(-1, -2.0f, 51, RadialCountdown.TEXT_SIZE_SP, 244.0f, RadialCountdown.TEXT_SIZE_SP, 0.0f));
            TextView messageTextView = new TextView(container.getContext());
            messageTextView.setTextColor(-8355712);
            messageTextView.setTextSize(1, CtaButton.TEXT_SIZE_SP);
            messageTextView.setGravity(17);
            frameLayout.addView(messageTextView, LayoutHelper.createFrame(-1, -2.0f, 51, 16.0f, 286.0f, 16.0f, 0.0f));
            container.addView(frameLayout, 0);
            headerTextView.setText(IntroActivity.this.titles[position]);
            messageTextView.setText(IntroActivity.this.messages[position]);
            return frameLayout;
        }

        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);
            IntroActivity.this.bottomPages.setCurrentPage(position);
        }

        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }

        public Parcelable saveState() {
            return null;
        }

        public void unregisterDataSetObserver(DataSetObserver observer) {
            if (observer != null) {
                super.unregisterDataSetObserver(observer);
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme.TMessages);
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        String[] strArr;
        CharSequence[] charSequenceArr;
        List<IntroPage> introPages;
        int i;
        IntroPage page;
        if (LocaleController.isRTL) {
            if (ApplicationLoader.getConfig().isUseDefaultWelcome()) {
                this.icons = new Drawable[]{getResources().getDrawable(R.drawable.intro7), getResources().getDrawable(R.drawable.intro6), getResources().getDrawable(R.drawable.intro5), getResources().getDrawable(R.drawable.intro4), getResources().getDrawable(R.drawable.intro3), getResources().getDrawable(R.drawable.intro2), ApplicationLoader.getConfig().getIcon()};
                strArr = new String[7];
                strArr[0] = getString(R.string.Page7Title);
                strArr[1] = getString(R.string.Page6Title);
                strArr[2] = getString(R.string.Page5Title);
                strArr[3] = getString(R.string.Page4Title);
                strArr[4] = getString(R.string.Page3Title);
                strArr[5] = getString(R.string.Page2Title);
                strArr[6] = getString(R.string.Page1Title, new Object[]{ApplicationLoader.getConfig().getAppName()});
                this.titles = strArr;
                charSequenceArr = new CharSequence[7];
                charSequenceArr[0] = AndroidUtilities.replaceTags(LocaleController.formatString("Page7Message", R.string.Page7Message, new Object[]{ApplicationLoader.getConfig().getAppName()}));
                charSequenceArr[1] = AndroidUtilities.replaceTags(LocaleController.formatString("Page6Message", R.string.Page6Message, new Object[]{ApplicationLoader.getConfig().getAppName()}));
                charSequenceArr[2] = AndroidUtilities.replaceTags(LocaleController.formatString("Page5Message", R.string.Page5Message, new Object[]{ApplicationLoader.getConfig().getAppName()}));
                charSequenceArr[3] = AndroidUtilities.replaceTags(LocaleController.formatString("Page4Message", R.string.Page4Message, new Object[]{ApplicationLoader.getConfig().getAppName()}));
                charSequenceArr[4] = AndroidUtilities.replaceTags(LocaleController.formatString("Page3Message", R.string.Page3Message, new Object[]{ApplicationLoader.getConfig().getAppName()}));
                charSequenceArr[5] = AndroidUtilities.replaceTags(LocaleController.formatString("Page2Message", R.string.Page2Message, new Object[]{ApplicationLoader.getConfig().getAppName()}));
                charSequenceArr[6] = AndroidUtilities.replaceTags(LocaleController.getString("Page1Message", R.string.Page1Message));
                this.messages = charSequenceArr;
            } else {
                introPages = ApplicationLoader.getConfig().getIntroPages(this);
                Collections.reverse(introPages);
                this.icons = new Drawable[introPages.size()];
                this.titles = new String[introPages.size()];
                this.messages = new CharSequence[introPages.size()];
                for (i = 0; i < introPages.size(); i++) {
                    page = (IntroPage) introPages.get(i);
                    this.icons[i] = page.getImage();
                    this.titles[i] = page.getTitle();
                    this.messages[i] = page.getMessage();
                }
            }
        } else if (ApplicationLoader.getConfig().isUseDefaultWelcome()) {
            this.icons = new Drawable[]{ApplicationLoader.getConfig().getIcon(), getResources().getDrawable(R.drawable.intro2), getResources().getDrawable(R.drawable.intro3), getResources().getDrawable(R.drawable.intro4), getResources().getDrawable(R.drawable.intro5), getResources().getDrawable(R.drawable.intro6), getResources().getDrawable(R.drawable.intro7)};
            strArr = new String[7];
            strArr[0] = getString(R.string.Page1Title, new Object[]{ApplicationLoader.getConfig().getAppName()});
            strArr[1] = getString(R.string.Page2Title);
            strArr[2] = getString(R.string.Page3Title);
            strArr[3] = getString(R.string.Page4Title);
            strArr[4] = getString(R.string.Page5Title);
            strArr[5] = getString(R.string.Page6Title);
            strArr[6] = getString(R.string.Page7Title);
            this.titles = strArr;
            charSequenceArr = new CharSequence[7];
            charSequenceArr[1] = AndroidUtilities.replaceTags(LocaleController.formatString("Page2Message", R.string.Page2Message, new Object[]{ApplicationLoader.getConfig().getAppName()}));
            charSequenceArr[2] = AndroidUtilities.replaceTags(LocaleController.formatString("Page3Message", R.string.Page3Message, new Object[]{ApplicationLoader.getConfig().getAppName()}));
            charSequenceArr[3] = AndroidUtilities.replaceTags(LocaleController.formatString("Page4Message", R.string.Page4Message, new Object[]{ApplicationLoader.getConfig().getAppName()}));
            charSequenceArr[4] = AndroidUtilities.replaceTags(LocaleController.formatString("Page5Message", R.string.Page5Message, new Object[]{ApplicationLoader.getConfig().getAppName()}));
            charSequenceArr[5] = AndroidUtilities.replaceTags(LocaleController.formatString("Page6Message", R.string.Page6Message, new Object[]{ApplicationLoader.getConfig().getAppName()}));
            charSequenceArr[6] = AndroidUtilities.replaceTags(LocaleController.formatString("Page7Message", R.string.Page7Message, new Object[]{ApplicationLoader.getConfig().getAppName()}));
            this.messages = charSequenceArr;
        } else {
            introPages = ApplicationLoader.getConfig().getIntroPages(this);
            this.icons = new Drawable[introPages.size()];
            this.titles = new String[introPages.size()];
            this.messages = new CharSequence[introPages.size()];
            for (i = 0; i < introPages.size(); i++) {
                page = (IntroPage) introPages.get(i);
                this.icons[i] = page.getImage();
                this.titles[i] = page.getTitle();
                this.messages[i] = page.getMessage();
            }
        }
        View scrollView = new ScrollView(this);
        scrollView.setFillViewport(true);
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setBackgroundColor(ApplicationLoader.getConfig().getDefaultTheme().getBackgroundColor().intValue());
        scrollView.addView(frameLayout, LayoutHelper.createScroll(-1, -2, 51));
        FrameLayout frameLayout2 = new FrameLayout(this);
        frameLayout.addView(frameLayout2, LayoutHelper.createFrame(-1, -2.0f, 51, 0.0f, 88.0f, 0.0f, 0.0f));
        this.topImage1 = new ImageView(this);
        this.topImage1.setImageDrawable(this.icons[0]);
        frameLayout2.addView(this.topImage1, LayoutHelper.createFrame(120, 120, 17));
        this.topImage2 = new ImageView(this);
        this.topImage2.setVisibility(8);
        frameLayout2.addView(this.topImage2, LayoutHelper.createFrame(120, 120, 17));
        this.viewPager = new ViewPager(this);
        IntroActivity introActivity = this;
        this.viewPager.setAdapter(new IntroAdapter());
        this.viewPager.setPageMargin(0);
        this.viewPager.setOffscreenPageLimit(1);
        frameLayout.addView(this.viewPager, LayoutHelper.createFrame(-1, -1.0f));
        this.viewPager.addOnPageChangeListener(new C64671());
        scrollView = new Button(this);
        scrollView.setText(LocaleController.getString("StartMessaging", R.string.StartMessaging).toUpperCase());
        scrollView.setPadding(AndroidUtilities.dp(CloseButton.TEXT_SIZE_SP), AndroidUtilities.dp(Settings.LOCATION_REQUEST_SMALLEST_DISPLACEMENT), AndroidUtilities.dp(CloseButton.TEXT_SIZE_SP), AndroidUtilities.dp(Settings.LOCATION_REQUEST_SMALLEST_DISPLACEMENT));
        scrollView.setGravity(17);
        scrollView.setTextColor(-1);
        scrollView.setBackgroundColor(ApplicationLoader.getConfig().getDefaultTheme().getActionColor().intValue());
        scrollView.setTextSize(1, 16.0f);
        if (VERSION.SDK_INT >= 21) {
            StateListAnimator animator = new StateListAnimator();
            scrollView = scrollView;
            animator.addState(new int[]{16842919}, ObjectAnimator.ofFloat(scrollView, "translationZ", new float[]{(float) AndroidUtilities.dp(2.0f), (float) AndroidUtilities.dp(4.0f)}).setDuration(200));
            scrollView = scrollView;
            animator.addState(new int[0], ObjectAnimator.ofFloat(scrollView, "translationZ", new float[]{(float) AndroidUtilities.dp(4.0f), (float) AndroidUtilities.dp(2.0f)}).setDuration(200));
            scrollView.setStateListAnimator(animator);
        }
        frameLayout.addView(scrollView, LayoutHelper.createFrame(-2, -2.0f, 81, Settings.LOCATION_REQUEST_SMALLEST_DISPLACEMENT, 0.0f, Settings.LOCATION_REQUEST_SMALLEST_DISPLACEMENT, 76.0f));
        scrollView.setOnClickListener(new C64682());
        if (BuildVars.DEBUG_VERSION) {
            scrollView.setOnLongClickListener(new C64693());
        }
        this.bottomPages = new BottomPagesView(this);
        frameLayout.addView(this.bottomPages, LayoutHelper.createFrame(this.icons.length * 11, 5.0f, 49, 0.0f, 350.0f, 0.0f, 0.0f));
        this.textView = new TextView(this);
        this.textView.setTextColor(ApplicationLoader.getConfig().getDefaultTheme().getActionColor().intValue());
        this.textView.setGravity(17);
        this.textView.setTextSize(1, 16.0f);
        frameLayout.addView(this.textView, LayoutHelper.createFrame(-2, BitmapDescriptorFactory.HUE_ORANGE, 81, 0.0f, 0.0f, 0.0f, CloseButton.TEXT_SIZE_SP));
        this.textView.setOnClickListener(new C64704());
        if (AndroidUtilities.isTablet()) {
            FrameLayout frameLayout3 = new FrameLayout(this);
            setContentView(frameLayout3);
            scrollView = new ImageView(this);
            BitmapDrawable drawable = (BitmapDrawable) getResources().getDrawable(R.drawable.catstile);
            drawable.setTileModeXY(TileMode.REPEAT, TileMode.REPEAT);
            scrollView.setBackgroundDrawable(drawable);
            frameLayout3.addView(scrollView, LayoutHelper.createFrame(-1, -1.0f));
            FrameLayout frameLayout4 = new FrameLayout(this);
            frameLayout4.setBackgroundResource(R.drawable.btnshadow);
            frameLayout4.addView(scrollView, LayoutHelper.createFrame(-1, -1.0f));
            frameLayout3.addView(frameLayout4, LayoutHelper.createFrame(498, 528, 17));
        } else {
            setRequestedOrientation(1);
            setContentView(scrollView);
        }
        checkContinueText();
        this.justCreated = true;
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.suggestedLangpack);
        AndroidUtilities.handleProxyIntent(this, getIntent());
    }

    protected void onResume() {
        super.onResume();
        AppsgeyserSDK.onResume(this);
        if (this.justCreated) {
            if (LocaleController.isRTL) {
                this.viewPager.setCurrentItem(6);
                this.lastPage = 6;
            } else {
                this.viewPager.setCurrentItem(0);
                this.lastPage = 0;
            }
            this.justCreated = false;
        }
        AndroidUtilities.checkForCrashes(this);
        AndroidUtilities.checkForUpdates(this);
        ConnectionsManager.getInstance().setAppPaused(false, false);
    }

    protected void onPause() {
        AppsgeyserSDK.onPause(this);
        super.onPause();
        AndroidUtilities.unregisterUpdates();
        ConnectionsManager.getInstance().setAppPaused(true, false);
    }

    protected void onDestroy() {
        super.onDestroy();
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.suggestedLangpack);
    }

    private void checkContinueText() {
        LocaleController$LocaleInfo englishInfo = null;
        LocaleController$LocaleInfo systemInfo = null;
        LocaleController$LocaleInfo currentLocaleInfo = LocaleController.getInstance().getCurrentLocaleInfo();
        String systemLang = LocaleController.getSystemLocaleStringIso639().toLowerCase();
        String arg;
        if (systemLang.contains("-")) {
            arg = systemLang.split("-")[0];
        } else {
            arg = systemLang;
        }
        ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).edit().putString("language_showed2", LocaleController.getSystemLocaleStringIso639().toLowerCase()).commit();
        for (int a = 0; a < LocaleController.getInstance().languages.size(); a++) {
            LocaleController$LocaleInfo info = (LocaleController$LocaleInfo) LocaleController.getInstance().languages.get(a);
            if (info.shortName.equals("en")) {
                englishInfo = info;
            }
            if (info.shortName.replace(BridgeUtil.UNDERLINE_STR, "-").equals(systemLang) || info.shortName.equals(arg)) {
                systemInfo = info;
            }
            if (englishInfo != null && systemInfo != null) {
                break;
            }
        }
        if (englishInfo != null && systemInfo != null && englishInfo != systemInfo) {
            TLRPC$TL_langpack_getStrings req = new TLRPC$TL_langpack_getStrings();
            if (systemInfo != currentLocaleInfo) {
                req.lang_code = systemInfo.shortName.replace(BridgeUtil.UNDERLINE_STR, "-");
                this.localeInfo = systemInfo;
            } else {
                req.lang_code = englishInfo.shortName.replace(BridgeUtil.UNDERLINE_STR, "-");
                this.localeInfo = englishInfo;
            }
            req.keys.add("ContinueOnThisLanguage");
            ConnectionsManager.getInstance().sendRequest(req, new C64725(), 8);
        }
    }

    public void didReceivedNotification(int id, Object... args) {
        if (id == NotificationCenter.suggestedLangpack) {
            checkContinueText();
        }
    }
}
