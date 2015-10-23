package edu.com.softserveinc.bawl.query;

/**
 * Created by Illia on 10/22/2015.
 */
//TODO remove this class and use named query approach like
//TODO http://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-creating-database-queries-with-named-queries/
public final class HistoryQuery {

    public final static  String uniqueLastByDateHistories =
            "SELECT h.* FROM history AS h," +
            "( " +
            "    SELECT issue_id, MAX(id) AS ID " +
            "    FROM history " +
            "    GROUP BY issue_id " +
            ") AS hMaxId " +
            "WHERE hMaxId.id = h.id";

    public final static String lastIssueByIssueID =
            "SELECT h.* FROM history AS h , " +
            "( " +
            "    SELECT MAX(id) AS ID " +
            "    FROM history as h2 " +
            "    WHERE issue_id = :issueId " +
            "    GROUP BY issue_id " +
            ") AS hMaxId " +
            "WHERE h.id = hMaxId.ID";

}
