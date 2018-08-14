package com.mopub.mraid;

class MraidController$5 implements Runnable {
    final /* synthetic */ MraidController this$0;

    MraidController$5(MraidController this$0) {
        this.this$0 = this$0;
    }

    public void run() {
        MraidController.access$200(this.this$0).notifySupports(MraidController.access$700(this.this$0).isSmsAvailable(MraidController.access$600(this.this$0)), MraidController.access$700(this.this$0).isTelAvailable(MraidController.access$600(this.this$0)), MraidNativeCommandHandler.isCalendarAvailable(MraidController.access$600(this.this$0)), MraidNativeCommandHandler.isStorePictureSupported(MraidController.access$600(this.this$0)), MraidController.access$800(this.this$0));
        MraidController.access$200(this.this$0).notifyPlacementType(MraidController.access$900(this.this$0));
        MraidController.access$200(this.this$0).notifyViewability(MraidController.access$200(this.this$0).isVisible());
        MraidController.access$200(this.this$0).notifyReady();
    }
}
