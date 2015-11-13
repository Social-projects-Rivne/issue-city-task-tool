package edu.com.softserveinc.bawl.models.enums;


public class IssueStatusHelper {

    private IssueStatusHelper() {
        throw new AssertionError();
    }

    public static IssueStatus getIssueStatusForAddIssue(UserRole userRole){
        IssueStatus issueStatus = IssueStatus.NEW;
        if (userRole == UserRole.ADMIN || userRole == UserRole.MANAGER) {
            issueStatus = IssueStatus.APPROVED;
        }
        return issueStatus;
    }

    public static IssueStatus getIssueStatusForResolving(UserRole userRole){
        IssueStatus issueStatus = IssueStatus.TO_RESOLVE;
        if (userRole == UserRole.ADMIN || userRole == UserRole.MANAGER) {
            issueStatus = IssueStatus.RESOLVED;
        }
        return issueStatus;
    }
}
