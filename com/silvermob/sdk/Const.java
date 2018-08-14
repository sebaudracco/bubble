package com.silvermob.sdk;

public interface Const {
    public static final String AD_RESPONSE = "AD_RESPONSE";
    public static final String AD_SKIP_TIMEOUT = "AD_SKIP_TIMEOUT";
    public static final int AD_SKIP_TIMEOUT_VAL = 5;
    public static final int BANNER_REFRESH_TIMEOUT = 20;
    public static final String ENCODING = "UTF-8";
    public static final String HIDE_BORDER = "<style>* { -webkit-tap-highlight-color: rgba(0,0,0,0);} body {margin: 0; padding: 0}</style>";
    public static final String IMAGE_BODY_TEMPLATE = "<body style='\"'margin: 0px; padding: 0px; text-align:center;'\"'><img src='\"'{0}'\"' width='\"'{1}'dp\"' height='\"'{2}'dp\"'/></body>";
    public static final String REDIRECT_URI = "REDIRECT_URI";

    public interface BannerFormat {
        public static final String BANNER = "small";
        public static final String INTERSTITIAL = "interstitial";
    }

    public interface BannerType {
        public static final String CODE = "html";
        public static final String IMAGE = "image";
    }

    public interface ClickType {
        public static final String BROWSER = "browser";
        public static final String IN_APP = "in_app";
    }
}
