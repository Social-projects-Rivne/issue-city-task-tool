package edu.com.softserveinc.bawl.models.enums;

import com.google.common.collect.Maps;

import java.util.EnumMap;

public enum IssueStatus {

    NEW("NEW"),
    APPROVED("APPROVED"),
    RESOLVED("RESOLVED"),
    DELETED("DELETED"),
    TO_RESOLVE("TO_RESOLVE");

    private static final EnumMap<IssueStatus, String> ISSUE_STATUS_MAP = Maps.newEnumMap(IssueStatus.class);

    static {
        for(IssueStatus issueStatus : values()) {
            ISSUE_STATUS_MAP.put(issueStatus, issueStatus.caption);

        }
    }

    private final String caption;

    IssueStatus(String caption) {
        this.caption = caption;
    }

    public static String get(IssueStatus issueStatus){
       return ISSUE_STATUS_MAP.get(issueStatus);
    }
}
