package edu.com.softserveinc.bawl.models.enums;


import java.util.Arrays;
import java.util.List;

public class IssueStatus {


    public final static String NEW = "NEW";
    public final static String APPROVED = "APPROVED";
    public final static String RESOLVED = "RESOLVED";
    public final static String DELETED = "DELETED";
    public final static String TO_RESOLVE = "TO_RESOLVE";

	public int id;

    public static List<String> getStatuses(){
        return (Arrays.asList(new String[] {NEW, APPROVED, RESOLVED, DELETED, TO_RESOLVE}));
    }

	/*IssueStatus(int id) {
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
            case "to_resolve" : return TO_RESOLVE;
            default: return null;
        }
    }*/
}
