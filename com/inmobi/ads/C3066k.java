package com.inmobi.ads;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import com.inmobi.ads.InMobiBanner.AnimationType;

/* compiled from: AnimationController */
class C3066k {

    /* compiled from: AnimationController */
    static class C3064a extends Animation {
        private final float f7466a;
        private final float f7467b;
        private final float f7468c;
        private final float f7469d;
        private final float f7470e;
        private final boolean f7471f;
        private Camera f7472g;

        public C3064a(float f, float f2, float f3, float f4, float f5, boolean z) {
            this.f7466a = f;
            this.f7467b = f2;
            this.f7468c = f3;
            this.f7469d = f4;
            this.f7470e = f5;
            this.f7471f = z;
        }

        public void initialize(int i, int i2, int i3, int i4) {
            super.initialize(i, i2, i3, i4);
            this.f7472g = new Camera();
        }

        protected void applyTransformation(float f, Transformation transformation) {
            float f2 = this.f7466a;
            f2 += (this.f7467b - f2) * f;
            float f3 = this.f7468c;
            float f4 = this.f7469d;
            Camera camera = this.f7472g;
            Matrix matrix = transformation.getMatrix();
            camera.save();
            if (this.f7471f) {
                camera.translate(0.0f, 0.0f, this.f7470e * f);
            } else {
                camera.translate(0.0f, 0.0f, this.f7470e * (1.0f - f));
            }
            camera.rotateX(f2);
            camera.getMatrix(matrix);
            camera.restore();
            matrix.preTranslate(-f3, -f4);
            matrix.postTranslate(f3, f4);
        }
    }

    /* compiled from: AnimationController */
    static class C3065b extends Animation {
        private final float f7473a;
        private final float f7474b;
        private final float f7475c;
        private final float f7476d;
        private final float f7477e;
        private final boolean f7478f;
        private Camera f7479g;

        public C3065b(float f, float f2, float f3, float f4, float f5, boolean z) {
            this.f7473a = f;
            this.f7474b = f2;
            this.f7475c = f3;
            this.f7476d = f4;
            this.f7477e = f5;
            this.f7478f = z;
        }

        public void initialize(int i, int i2, int i3, int i4) {
            super.initialize(i, i2, i3, i4);
            this.f7479g = new Camera();
        }

        protected void applyTransformation(float f, Transformation transformation) {
            float f2 = this.f7473a;
            f2 += (this.f7474b - f2) * f;
            float f3 = this.f7475c;
            float f4 = this.f7476d;
            Camera camera = this.f7479g;
            Matrix matrix = transformation.getMatrix();
            camera.save();
            if (this.f7478f) {
                camera.translate(0.0f, 0.0f, this.f7477e * f);
            } else {
                camera.translate(0.0f, 0.0f, this.f7477e * (1.0f - f));
            }
            camera.rotateY(f2);
            camera.getMatrix(matrix);
            camera.restore();
            matrix.preTranslate(-f3, -f4);
            matrix.postTranslate(f3, f4);
        }
    }

    static Animation m9878a(AnimationType animationType, float f, float f2) {
        Animation alphaAnimation;
        if (animationType == AnimationType.ANIMATION_ALPHA) {
            alphaAnimation = new AlphaAnimation(0.0f, 0.5f);
            alphaAnimation.setDuration(1000);
            alphaAnimation.setFillAfter(false);
            alphaAnimation.setInterpolator(new DecelerateInterpolator());
            return alphaAnimation;
        } else if (animationType == AnimationType.ROTATE_HORIZONTAL_AXIS) {
            alphaAnimation = new C3064a(0.0f, 90.0f, f / 2.0f, f2 / 2.0f, 0.0f, true);
            alphaAnimation.setDuration(500);
            alphaAnimation.setFillAfter(false);
            alphaAnimation.setInterpolator(new AccelerateInterpolator());
            return alphaAnimation;
        } else if (animationType != AnimationType.ROTATE_VERTICAL_AXIS) {
            return null;
        } else {
            alphaAnimation = new C3065b(0.0f, 90.0f, f / 2.0f, f2 / 2.0f, 0.0f, true);
            alphaAnimation.setDuration(500);
            alphaAnimation.setFillAfter(false);
            alphaAnimation.setInterpolator(new AccelerateInterpolator());
            return alphaAnimation;
        }
    }
}
