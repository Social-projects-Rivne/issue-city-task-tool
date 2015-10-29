package edu.com.softserveinc.bawl.models.enums;

public enum IssueStatus {

	NEW(1),
    APPROVED(2),
    RESOLVED(3),
    DELETED(4),
    TO_RESOLVE(5);

	public int id;

	IssueStatus(int id) {
		this.id = id;
	}

    public static IssueStatus get(int id) {
        switch (id) {
            case 1: return NEW;
            case 2: return APPROVED;
            case 3: return RESOLVED;
            case 4: return DELETED;
            case 5: return TO_RESOLVE;
            default: return null;
        }
    }
    public static IssueStatus get(String name) {
        switch (name.toLowerCase()) {
            case "new" : return NEW;
            case "approved" : return APPROVED;
            case "deleted" : return DELETED;
            case "resolved" : return RESOLVED;
            case "to resolve" : return TO_RESOLVE;
            default: return null;
        }
    }
}
