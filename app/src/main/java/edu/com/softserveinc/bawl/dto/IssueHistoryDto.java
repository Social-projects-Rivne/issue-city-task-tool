package edu.com.softserveinc.bawl.dto;

import java.util.Date;

/**
 * Created by lubko on 12.10.15.
 */
public class IssueHistoryDto {

    private Date date;

    private String changedByUser;

    private int statusID;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getChangedByUser() {
        return changedByUser;
    }

    public void setChangedByUser(String changedByUser) {
        this.changedByUser = changedByUser;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatus(int statusID) {
        this.statusID = statusID;
    }



}
