package edu.com.softserveinc.bawl.models.enums;


public class IssueStatusHelper {

    private IssueStatusHelper() {
        throw new AssertionError();
    }

    public static IssueStatus getIssueStatusForAddIssue(UserRole userRole){
        if (userRole == UserRole.ADMIN || userRole == UserRole.MANAGER) {
            return IssueStatus.APPROVED;
        }
        return IssueStatus.NEW;
    }

    public static IssueStatus getIssueStatusForResolving(UserRole userRole){
        if (userRole == UserRole.ADMIN || userRole == UserRole.MANAGER) {
            return IssueStatus.RESOLVED;
        }
        return IssueStatus.TO_RESOLVE;
    }
}
