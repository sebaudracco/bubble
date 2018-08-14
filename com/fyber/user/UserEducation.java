package com.fyber.user;

public enum UserEducation {
    other("other"),
    none("none"),
    high_school("high school"),
    in_college("in college"),
    some_college("some college"),
    associates("associates"),
    bachelors("bachelors"),
    masters("masters"),
    doctorate("doctorate");
    
    public final String education;

    private UserEducation(String str) {
        this.education = str;
    }

    public final String toString() {
        return this.education;
    }
}
