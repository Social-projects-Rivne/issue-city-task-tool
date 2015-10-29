package edu.com.softserveinc.bawl.dto;


import java.util.List;

public class CategoryDTO {

    private int id;

    private String name;

    private int state;

    private List<IssueDto> issueDtoList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<IssueDto> getIssueDtoList() {
        return issueDtoList;
    }

    public void setIssueDtoList(List<IssueDto> issueDtoList) {
        this.issueDtoList = issueDtoList;
    }
}
