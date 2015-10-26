package edu.com.softserveinc.bawl.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserHistoryDto implements Comparable<UserHistoryDto> {

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

    public String getDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.mm.yyyy");
        return simpleDateFormat.format(date);
    }

    public void setDate(Date date) {   this.date = date;    }

    public String getRoleName() { return roleName;}

    public void setRoleName(String roleName) {  this.roleName = roleName;  }

    public static int compare(UserHistoryDto o1, UserHistoryDto o2) {
        return o1.date.compareTo(o2.date);
    }

    @Override
    public int compareTo(UserHistoryDto o) {
        return compare(this, o);
    }
}
