package org.telegram.messenger.config;

import android.graphics.drawable.Drawable;

public class IntroPage {
    private Drawable image;
    private String message;
    private String title;

    public IntroPage(Drawable image, String title, String message) {
        this.image = image;
        this.title = title;
        this.message = message;
    }

    public Drawable getImage() {
        return this.image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
