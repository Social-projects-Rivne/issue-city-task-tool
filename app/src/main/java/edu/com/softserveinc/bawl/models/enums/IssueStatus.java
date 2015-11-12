package edu.com.softserveinc.bawl.models.enums;

public enum IssueStatus {
    NEW,
    APPROVED,
    RESOLVED,
    DELETED,
    TO_RESOLVE;

    public static String get(IssueStatus issueStatus){
        switch(issueStatus) {
            case NEW: return "NEW";
            case APPROVED: return "APPROVED";
            case RESOLVED: return "RESOLVED";
            case DELETED: return "DELETED";
            case TO_RESOLVE: return "TO_RESOLVE";
            default: return null;
        }
    }
}
