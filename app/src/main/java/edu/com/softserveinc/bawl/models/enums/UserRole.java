package edu.com.softserveinc.bawl.models.enums;

import java.util.HashMap;
import java.util.Map;

public enum UserRole {

    USER_NOT_CONFIRMED("User not confirmed"),
    USER("User"),
    MANAGER("Manager"),
    ADMIN("Admin");

    private static final Map<Integer, UserRole> USER_ROLE_MAP = new HashMap<>();

    static {
        for(UserRole userRole : values()) {
            USER_ROLE_MAP.put(userRole.ordinal(), userRole);
        }
    }
    private final String caption;

    UserRole(String caption) {
        this.caption = caption;
    }

   public static UserRole getByRoleId(int roleId) {
       return USER_ROLE_MAP.get(roleId);
    }

    public int getId() {
        return ordinal();
    }

    public String getCaption() {
        return caption;
    }
}
