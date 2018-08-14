package com.mobfox.sdk.tagbanner;

import com.mobfox.sdk.services.MobFoxAdIdService.Listener;

class TagBanner$6 implements Listener {
    final /* synthetic */ TagBanner this$0;

    TagBanner$6(TagBanner this$0) {
        this.this$0 = this$0;
    }

    public void onFinish(String adv_id) {
        if (adv_id == null) {
            TagBanner.O_ANDADVID = "";
        } else {
            TagBanner.O_ANDADVID = adv_id;
        }
    }
}
