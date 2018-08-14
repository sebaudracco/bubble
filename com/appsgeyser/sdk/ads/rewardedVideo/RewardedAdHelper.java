package com.appsgeyser.sdk.ads.rewardedVideo;

import android.content.Context;
import android.util.Log;
import com.appsgeyser.sdk.ads.rewardedVideo.rewardedFacades.AbstractRewardedFacade;
import com.appsgeyser.sdk.ads.rewardedVideo.rewardedFacades.RewardedVideoFacade;
import com.appsgeyser.sdk.ads.rewardedVideo.rewardedFacades.RewardedVideoFacade.RewardedVideoListener;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.ui.AppsgeyserProgressDialog;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class RewardedAdHelper {
    private ConfigPhp configPhp;
    private Context context;
    private int currentlyLoadingSdkIndex = 0;
    private int indexOfLoadedSdk = -1;
    private boolean loadingProcess = false;
    private boolean noVideoAvailable = false;
    private RewardedVideoListener pendingListener;
    private AppsgeyserProgressDialog progressDialog;
    private ArrayList<RewardedVideoFacade> rewardedVideoFacades;

    class C12741 implements Comparator<RewardedVideoFacade> {
        C12741() {
        }

        public int compare(RewardedVideoFacade o1, RewardedVideoFacade o2) {
            return ((AbstractRewardedFacade) o2).getPriority() - ((AbstractRewardedFacade) o1).getPriority();
        }
    }

    public RewardedAdHelper(ConfigPhp configPhp, Context context) {
        this.configPhp = configPhp;
        this.context = context;
        this.rewardedVideoFacades = new ArrayList(5);
        init();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void init() {
        /*
        r7 = this;
        r3 = 1;
        r1 = r7.configPhp;
        r1 = r1.getRewardedVideoSdk();
        r1 = r1.entrySet();
        r4 = r1.iterator();
    L_0x000f:
        r1 = r4.hasNext();
        if (r1 == 0) goto L_0x00bf;
    L_0x0015:
        r0 = r4.next();
        r0 = (java.util.Map.Entry) r0;
        r1 = r0.getValue();
        r1 = (com.appsgeyser.sdk.configuration.models.AdNetworkSdkModel) r1;
        r1 = r1.isActive();
        if (r1 == 0) goto L_0x000f;
    L_0x0027:
        r1 = r0.getKey();
        r1 = (java.lang.String) r1;
        r2 = -1;
        r5 = r1.hashCode();
        switch(r5) {
            case -1143343796: goto L_0x005f;
            case -963943683: goto L_0x0054;
            case 294202302: goto L_0x006a;
            case 1111908937: goto L_0x0075;
            case 1314914054: goto L_0x0049;
            default: goto L_0x0035;
        };
    L_0x0035:
        r1 = r2;
    L_0x0036:
        switch(r1) {
            case 0: goto L_0x003a;
            case 1: goto L_0x0080;
            case 2: goto L_0x008f;
            case 3: goto L_0x009f;
            case 4: goto L_0x00af;
            default: goto L_0x0039;
        };
    L_0x0039:
        goto L_0x000f;
    L_0x003a:
        r1 = r7.rewardedVideoFacades;
        r2 = new com.appsgeyser.sdk.ads.rewardedVideo.rewardedFacades.AppnextRewardedFacade;
        r5 = r7.context;
        r6 = r7.configPhp;
        r2.<init>(r5, r6);
        r1.add(r2);
        goto L_0x000f;
    L_0x0049:
        r5 = "appnextSdk";
        r1 = r1.equals(r5);
        if (r1 == 0) goto L_0x0035;
    L_0x0052:
        r1 = 0;
        goto L_0x0036;
    L_0x0054:
        r5 = "admobSdk";
        r1 = r1.equals(r5);
        if (r1 == 0) goto L_0x0035;
    L_0x005d:
        r1 = r3;
        goto L_0x0036;
    L_0x005f:
        r5 = "inmobiSdk";
        r1 = r1.equals(r5);
        if (r1 == 0) goto L_0x0035;
    L_0x0068:
        r1 = 2;
        goto L_0x0036;
    L_0x006a:
        r5 = "fyberSdk";
        r1 = r1.equals(r5);
        if (r1 == 0) goto L_0x0035;
    L_0x0073:
        r1 = 3;
        goto L_0x0036;
    L_0x0075:
        r5 = "vungleSdk";
        r1 = r1.equals(r5);
        if (r1 == 0) goto L_0x0035;
    L_0x007e:
        r1 = 4;
        goto L_0x0036;
    L_0x0080:
        r1 = r7.rewardedVideoFacades;
        r2 = new com.appsgeyser.sdk.ads.rewardedVideo.rewardedFacades.AdmobRewardedFacade;
        r5 = r7.context;
        r6 = r7.configPhp;
        r2.<init>(r5, r6);
        r1.add(r2);
        goto L_0x000f;
    L_0x008f:
        r1 = r7.rewardedVideoFacades;
        r2 = new com.appsgeyser.sdk.ads.rewardedVideo.rewardedFacades.InmobiRewardedFacade;
        r5 = r7.context;
        r6 = r7.configPhp;
        r2.<init>(r5, r6);
        r1.add(r2);
        goto L_0x000f;
    L_0x009f:
        r1 = r7.rewardedVideoFacades;
        r2 = new com.appsgeyser.sdk.ads.rewardedVideo.rewardedFacades.FyberRewardedVideoFacade;
        r5 = r7.context;
        r6 = r7.configPhp;
        r2.<init>(r5, r6);
        r1.add(r2);
        goto L_0x000f;
    L_0x00af:
        r1 = r7.rewardedVideoFacades;
        r2 = new com.appsgeyser.sdk.ads.rewardedVideo.rewardedFacades.VungleRewardedFacade;
        r5 = r7.context;
        r6 = r7.configPhp;
        r2.<init>(r5, r6);
        r1.add(r2);
        goto L_0x000f;
    L_0x00bf:
        r1 = r7.rewardedVideoFacades;
        r1 = r1.size();
        if (r1 <= r3) goto L_0x00d1;
    L_0x00c7:
        r1 = r7.rewardedVideoFacades;
        r2 = new com.appsgeyser.sdk.ads.rewardedVideo.RewardedAdHelper$1;
        r2.<init>();
        java.util.Collections.sort(r1, r2);
    L_0x00d1:
        r7.preloadVideo();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsgeyser.sdk.ads.rewardedVideo.RewardedAdHelper.init():void");
    }

    private void preloadVideo() {
        if (this.rewardedVideoFacades.size() > 0) {
            this.loadingProcess = true;
            final RewardedVideoFacade facade = (RewardedVideoFacade) this.rewardedVideoFacades.get(this.currentlyLoadingSdkIndex);
            facade.setListener(new RewardedVideoListener() {
                public void onVideoLoaded() {
                    RewardedAdHelper.this.indexOfLoadedSdk = RewardedAdHelper.this.currentlyLoadingSdkIndex;
                    RewardedAdHelper.this.loadingProcess = false;
                    if (RewardedAdHelper.this.pendingListener != null && RewardedAdHelper.this.progressDialog != null) {
                        RewardedAdHelper.this.progressDialog.dismiss();
                        RewardedAdHelper.this.progressDialog = null;
                        facade.setListener(RewardedAdHelper.this.createDefaultListener(RewardedAdHelper.this.pendingListener));
                        RewardedAdHelper.this.pendingListener.onVideoLoaded();
                    }
                }

                public void onVideoOpened() {
                }

                public void onVideoClicked() {
                }

                public void onVideoClosed() {
                    RewardedAdHelper.this.currentlyLoadingSdkIndex = 0;
                    RewardedAdHelper.this.indexOfLoadedSdk = -1;
                    RewardedAdHelper.this.preloadVideo();
                }

                public void onVideoError(String message) {
                    if (RewardedAdHelper.this.currentlyLoadingSdkIndex == RewardedAdHelper.this.rewardedVideoFacades.size() - 1) {
                        RewardedAdHelper.this.noVideoAvailable = true;
                        if (RewardedAdHelper.this.pendingListener != null && RewardedAdHelper.this.progressDialog != null) {
                            RewardedAdHelper.this.progressDialog.dismiss();
                            RewardedAdHelper.this.progressDialog = null;
                            RewardedAdHelper.this.pendingListener.onVideoError("No video available");
                            return;
                        }
                        return;
                    }
                    RewardedAdHelper.this.currentlyLoadingSdkIndex = RewardedAdHelper.this.currentlyLoadingSdkIndex + 1;
                    RewardedAdHelper.this.preloadVideo();
                }

                public void onVideoFinished() {
                }
            });
            facade.loadRewardedVideo();
        }
    }

    private RewardedVideoListener createDefaultListener(final RewardedVideoListener listener) {
        return new RewardedVideoListener() {
            public void onVideoLoaded() {
            }

            public void onVideoOpened() {
                listener.onVideoOpened();
            }

            public void onVideoClicked() {
                listener.onVideoClicked();
            }

            public void onVideoClosed() {
                RewardedAdHelper.this.currentlyLoadingSdkIndex = 0;
                RewardedAdHelper.this.indexOfLoadedSdk = -1;
                RewardedAdHelper.this.preloadVideo();
                listener.onVideoClosed();
            }

            public void onVideoError(String message) {
                listener.onVideoError(message);
                RewardedAdHelper.this.preloadVideo();
            }

            public void onVideoFinished() {
                listener.onVideoFinished();
                RewardedAdHelper.this.indexOfLoadedSdk = -1;
            }
        };
    }

    public void loadRewardedVideo(RewardedVideoListener listener) {
        if (this.noVideoAvailable || (this.indexOfLoadedSdk == -1 && !this.loadingProcess)) {
            listener.onVideoError("No video available at the moment");
        } else if (this.loadingProcess) {
            this.pendingListener = listener;
            this.progressDialog = new AppsgeyserProgressDialog(this.context);
            this.progressDialog.show();
        } else {
            RewardedVideoFacade loadedFacade = (RewardedVideoFacade) this.rewardedVideoFacades.get(this.indexOfLoadedSdk);
            if (loadedFacade.isVideoLoaded()) {
                loadedFacade.setListener(createDefaultListener(listener));
                listener.onVideoLoaded();
                return;
            }
            preloadVideo();
            loadRewardedVideo(listener);
        }
    }

    public void showRewardedVideo() {
        Log.d("RewVideoCallback", "indexOfLoadedSdk = " + this.indexOfLoadedSdk + " rewardedVideoFacades.get(indexOfLoadedSdk).isVideoLoaded() = " + ((RewardedVideoFacade) this.rewardedVideoFacades.get(this.indexOfLoadedSdk)).isVideoLoaded());
        if (this.indexOfLoadedSdk != -1 && ((RewardedVideoFacade) this.rewardedVideoFacades.get(this.indexOfLoadedSdk)).isVideoLoaded()) {
            ((RewardedVideoFacade) this.rewardedVideoFacades.get(this.indexOfLoadedSdk)).showRewardedVideo();
        }
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void onPause() {
        Iterator it = this.rewardedVideoFacades.iterator();
        while (it.hasNext()) {
            ((RewardedVideoFacade) it.next()).onPause();
        }
    }

    public void onResume() {
        Iterator it = this.rewardedVideoFacades.iterator();
        while (it.hasNext()) {
            ((RewardedVideoFacade) it.next()).onResume();
        }
    }

    public void onDestroy() {
        Iterator it = this.rewardedVideoFacades.iterator();
        while (it.hasNext()) {
            ((RewardedVideoFacade) it.next()).onDestroy();
        }
    }
}
