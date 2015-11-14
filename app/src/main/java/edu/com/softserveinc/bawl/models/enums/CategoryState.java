package edu.com.softserveinc.bawl.models.enums;

import java.util.HashMap;
import java.util.Map;

public enum CategoryState {

    NEW("New"),
    DELETED("Deleted");

    private static final Map<String, CategoryState> CATEGORY_STATE_MAP = new HashMap<>();

    static {
        for(CategoryState categoryState : values()) {
            CATEGORY_STATE_MAP.put(categoryState.caption, categoryState);

        }
    }

    private final String caption;

    CategoryState(String caption){
        this.caption = caption;
    }

    public int getId() {
        return ordinal();
    }

    public String getCaption() {
        return caption;
    }

    public static CategoryState getState(String state){
        return CATEGORY_STATE_MAP.get(state);
    }


}
