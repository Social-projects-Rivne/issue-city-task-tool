package edu.com.softserveinc.bawl.models.enums;

public enum UserRole {

    USER_NOT_CONFIRMED(0),
    USER(1),
    MANAGER(2),
    ADMIN(3);

    public int id;

    UserRole(int id) {
        this.id = id;
    }

    public static String get(int roleId) {
        switch (roleId) {
            case 0: return "User not confirmed";
            case 1: return "User";
            case 2: return "Manager";
            case 3: return "Admin";
            default: return "Not confirmed";
        }
    }

    public String get() {
        switch (this.id) {
            case 0: return "User not confirmed";
            case 1: return "User";
            case 2: return "Manager";
            case 3: return "Admin";
            default: return "Not confirmed";
        }
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
