package edu.com.softserveinc.bawl.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by lubko on 09.10.15.
 */
public class UserIssuesHistoryDto {

    private String issueName ;

    private IssueHistoryDto issueHistoryDto;

    private int currentStatus;


    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public IssueHistoryDto getIssueHistoryDto() {
        return issueHistoryDto;
    }

    public void setIssueHistoryDto(IssueHistoryDto issueHistoryDto) {
        this.issueHistoryDto = issueHistoryDto;
    }

    public int getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(int currentStatus) {
        this.currentStatus = currentStatus;
    }






}
