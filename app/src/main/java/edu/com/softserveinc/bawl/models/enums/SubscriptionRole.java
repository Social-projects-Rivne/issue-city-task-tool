package edu.com.softserveinc.bawl.models.enums;

import com.google.common.collect.Maps;

import java.util.EnumMap;

public enum SubscriptionRole {

    ON ("on"),
    OFF("off");

    private static final EnumMap<SubscriptionRole, String> SUBSCRIPTION_STATUS__MAP = Maps.newEnumMap(SubscriptionRole.class);

    static {
        for (SubscriptionRole subscriptionStatus: values()){
            SUBSCRIPTION_STATUS__MAP.put(subscriptionStatus, subscriptionStatus.caption);
        }
    }
    private final String caption;

    SubscriptionRole(String caption) {
        this.caption = caption;
    }

    public static String get(SubscriptionRole subscriptionStatus){
        return SUBSCRIPTION_STATUS__MAP.get(subscriptionStatus);
    }
    //
}

