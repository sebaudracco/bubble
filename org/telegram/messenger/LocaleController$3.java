package org.telegram.messenger;

import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import java.util.HashMap;
import java.util.Locale;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.telegram.tgnet.TLRPC$TL_langPackDifference;

class LocaleController$3 implements Runnable {
    final /* synthetic */ LocaleController this$0;
    final /* synthetic */ TLRPC$TL_langPackDifference val$difference;
    final /* synthetic */ String val$langCode;
    final /* synthetic */ HashMap val$valuesToSet;

    LocaleController$3(LocaleController this$0, String str, TLRPC$TL_langPackDifference tLRPC$TL_langPackDifference, HashMap hashMap) {
        this.this$0 = this$0;
        this.val$langCode = str;
        this.val$difference = tLRPC$TL_langPackDifference;
        this.val$valuesToSet = hashMap;
    }

    public void run() {
        LocaleController$LocaleInfo localeInfo = LocaleController.access$100(this.this$0, this.val$langCode);
        if (localeInfo != null) {
            localeInfo.version = this.val$difference.version;
        }
        LocaleController.access$200(this.this$0);
        if (LocaleController.access$300(this.this$0) == null || !LocaleController.access$300(this.this$0).isLocal()) {
            try {
                Locale newLocale;
                String[] args = localeInfo.shortName.split(BridgeUtil.UNDERLINE_STR);
                if (args.length == 1) {
                    newLocale = new Locale(localeInfo.shortName);
                } else {
                    newLocale = new Locale(args[0], args[1]);
                }
                if (newLocale != null) {
                    LocaleController.access$402(this.this$0, localeInfo.shortName);
                    Editor editor = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).edit();
                    editor.putString(SchemaSymbols.ATTVAL_LANGUAGE, localeInfo.getKey());
                    editor.commit();
                }
                if (newLocale != null) {
                    LocaleController.access$502(this.this$0, this.val$valuesToSet);
                    LocaleController.access$602(this.this$0, newLocale);
                    LocaleController.access$302(this.this$0, localeInfo);
                    LocaleController.access$702(this.this$0, (LocaleController$PluralRules) LocaleController.access$800(this.this$0).get(LocaleController.access$600(this.this$0).getLanguage()));
                    if (LocaleController.access$700(this.this$0) == null) {
                        LocaleController.access$702(this.this$0, (LocaleController$PluralRules) LocaleController.access$800(this.this$0).get("en"));
                    }
                    LocaleController.access$902(this.this$0, true);
                    Locale.setDefault(LocaleController.access$600(this.this$0));
                    Configuration config = new Configuration();
                    config.locale = LocaleController.access$600(this.this$0);
                    ApplicationLoader.applicationContext.getResources().updateConfiguration(config, ApplicationLoader.applicationContext.getResources().getDisplayMetrics());
                    LocaleController.access$902(this.this$0, false);
                }
            } catch (Exception e) {
                FileLog.e(e);
                LocaleController.access$902(this.this$0, false);
            }
            this.this$0.recreateFormatters();
            NotificationCenter.getInstance().postNotificationName(NotificationCenter.reloadInterface, new Object[0]);
        }
    }
}
