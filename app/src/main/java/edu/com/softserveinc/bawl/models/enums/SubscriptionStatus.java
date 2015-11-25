package edu.com.softserveinc.bawl.models.enums;

import com.google.common.collect.Maps;

import java.util.EnumMap;

public enum SubscriptionStatus {

    ON ("on"),
    OFF("off");

    private static final EnumMap<SubscriptionStatus, String> SUBSCRIPTION_STATUS__MAP = Maps.newEnumMap(SubscriptionStatus.class);

    static {
        for (SubscriptionStatus subscriptionStatus: values()){
            SUBSCRIPTION_STATUS__MAP.put(subscriptionStatus, subscriptionStatus.caption);
        }
    }
    private final String caption;

    SubscriptionStatus(String caption) {
        this.caption = caption;
    }

    public static String get(SubscriptionStatus subscriptionStatus){
        return SUBSCRIPTION_STATUS__MAP.get(subscriptionStatus);
    }
    //
}

