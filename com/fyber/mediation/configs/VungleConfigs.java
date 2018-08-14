package com.fyber.mediation.configs;

import com.fyber.mediation.annotations.ConfigKey;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@ConfigKey(name = "Vungle")
@Retention(RetentionPolicy.SOURCE)
public @interface VungleConfigs {
    @ConfigKey(name = "app.id")
    String appId() default "";

    @ConfigKey(name = "auto.rotation.enabled")
    boolean autoRotationEnabled() default false;

    @ConfigKey(name = "back.button.enabled")
    boolean backButtonEnabled() default false;

    @ConfigKey(name = "cancel.dialog.button")
    String cancelDialogButton() default "";

    @ConfigKey(name = "cancel.dialog.text")
    String cancelDialogText() default "";

    @ConfigKey(name = "cancel.dialog.title")
    String cancelDialogTitle() default "";

    @ConfigKey(name = "incentivized.mode")
    boolean incentivizedMode() default true;

    @ConfigKey(name = "int.placement.id")
    String intPlacementId() default "";

    @ConfigKey(name = "keep.watching.text")
    String keepWatchingText() default "";

    @ConfigKey(name = "rv.placement.id")
    String rvPlacementId() default "";

    @ConfigKey(name = "sound.enabled")
    boolean soundEnabled() default true;
}
