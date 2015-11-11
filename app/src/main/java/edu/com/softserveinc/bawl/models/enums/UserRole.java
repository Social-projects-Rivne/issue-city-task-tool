package edu.com.softserveinc.bawl.models.enums;

public enum UserRole {

    USER_NOT_CONFIRMED(0, "User not confirmed"),
    USER(1,"User"),
    MANAGER(2, "Manager"),
    ADMIN(3,"Admin");

    public int id;
    public String caption;

    UserRole(int id, String caption) {
        this.id = id;
        this.caption = caption;
    }

   public static UserRole getById(int roleId) {
        switch (roleId) {
            case 0: return USER_NOT_CONFIRMED;
            case 1: return USER;
            case 2: return MANAGER;
            case 3: return ADMIN;
            default: return null;
        }
    }

}
