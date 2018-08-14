package com.mopub.mraid;

class MraidController$6 implements Runnable {
    final /* synthetic */ MraidController this$0;

    MraidController$6(MraidController this$0) {
        this.this$0 = this$0;
    }

    public void run() {
        MraidBridge access$100 = MraidController.access$100(this.this$0);
        boolean isSmsAvailable = MraidController.access$700(this.this$0).isSmsAvailable(MraidController.access$600(this.this$0));
        boolean isTelAvailable = MraidController.access$700(this.this$0).isTelAvailable(MraidController.access$600(this.this$0));
        MraidController.access$700(this.this$0);
        boolean isCalendarAvailable = MraidNativeCommandHandler.isCalendarAvailable(MraidController.access$600(this.this$0));
        MraidController.access$700(this.this$0);
        access$100.notifySupports(isSmsAvailable, isTelAvailable, isCalendarAvailable, MraidNativeCommandHandler.isStorePictureSupported(MraidController.access$600(this.this$0)), MraidController.access$800(this.this$0));
        MraidController.access$100(this.this$0).notifyViewState(MraidController.access$1000(this.this$0));
        MraidController.access$100(this.this$0).notifyPlacementType(MraidController.access$900(this.this$0));
        MraidController.access$100(this.this$0).notifyViewability(MraidController.access$100(this.this$0).isVisible());
        MraidController.access$100(this.this$0).notifyReady();
    }
}
