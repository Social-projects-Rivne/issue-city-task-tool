package edu.com.softserveinc.bawl.dto;

import java.util.Date;

/**
 * Created by Illia on 10/9/2015.
 */
public class UserHistoryDto {

    private String username;
    private String issueName;

    private Date date;

    private String roleName;
    private int statusId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public Date getDate() {   return date;    }

    public void setDate(Date date) {   this.date = date;    }

    public String getRoleName() { return roleName;}

    public void setRoleName(String roleName) {  this.roleName = roleName;  }


}
