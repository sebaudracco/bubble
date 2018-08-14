package com.fyber.mediation.configs;

import com.fyber.mediation.annotations.ConfigKey;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@ConfigKey(name = "AdMob")
@Retention(RetentionPolicy.SOURCE)
public @interface AdMobConfigs {
    @ConfigKey(name = "ad.unit.id")
    String adUnitId() default "";

    @ConfigKey(name = "addTestDevice")
    String[] addTestDevice() default {};

    @ConfigKey(name = "app.id")
    String appId() default "";

    @ConfigKey(name = "banner.ad.unit.id")
    String bannerAdUnitId() default "";

    @ConfigKey(name = "isCOPPAcompliant")
    boolean isCOPPAcompliant() default false;

    @ConfigKey(name = "rewarded.video.ad.unit.id")
    String rvAdUnitId() default "";
}
