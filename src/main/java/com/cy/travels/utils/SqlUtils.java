package com.cy.travels.utils;

public class SqlUtils {

    private static final String LIKE = "%";

    public static String like(String condition) {
        return LIKE + condition + LIKE;
    }

    public static String leftLike(String condition) {
        return LIKE + condition;
    }

    public static String rightLike(String condition) {
        return condition + LIKE;
    }
}
