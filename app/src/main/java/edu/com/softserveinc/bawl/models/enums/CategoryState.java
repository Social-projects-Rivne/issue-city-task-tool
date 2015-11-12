package edu.com.softserveinc.bawl.models.enums;

public enum CategoryState {
    NEW(0, "New"),
    DELETED(1, "Deleted");

    public int id;
    public String caption;

    CategoryState(int id, String caption){
        this.id = id;
        this.caption = caption;
    }

    public static CategoryState getState(String state){
        switch (state){
            case "New":
                return CategoryState.NEW;
            case "Deleted":
                return CategoryState.DELETED;
            default:
                return null;
        }
    }
}
