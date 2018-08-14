package com.fyber.mediation.configs;

import com.fyber.mediation.annotations.ConfigKey;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@ConfigKey(name = "FacebookAudienceNetwork")
@Retention(RetentionPolicy.SOURCE)
public @interface FacebookConfigs {
    @ConfigKey(name = "bannerPlacementId")
    String bannerPlacementId() default "";

    @ConfigKey(name = "placementId")
    String placementId() default "";

    @ConfigKey(name = "rewardedPlacementId")
    String rvPlacementId() default "";

    @ConfigKey(name = "testDeviceHash")
    String testDeviceHash() default "";
}
