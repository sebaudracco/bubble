package com.appsgeyser.sdk;

import java.util.UUID;

public class GuidGenerator {
    public static String generateNewGuid() {
        return UUID.randomUUID().toString();
    }
}
