package edu.com.softserveinc.bawl.models.enums;

public enum UserRole {

    USER_NOT_CONFIRMED(-1),
    USER(0),
    ADMIN(1),
    MANAGER(2);

    public int id;

    UserRole(int id) {
        this.id = id;
    }

    public static String get(int roleId) {
        switch (roleId) {
            case -1: return "User not confirmed";
            case 0: return "User";
            case 1: return "Admin";
            case 2: return "Manager";
            default: return "Not confirmed";
        }
    }

    public String get() {
        switch (this.id) {
            case -1: return "User not confirmed";
            case 0: return "User";
            case 1: return "Admin";
            case 2: return "Manager";
            default: return "Not confirmed";
        }
    }

   public static UserRole getById(int roleId) {
        switch (roleId) {
            case -1: return USER_NOT_CONFIRMED;
            case 0: return USER;
            case 1: return ADMIN;
            case 2: return MANAGER;
            default: return null;
        }
    }

}
