package edu.com.softserveinc.bawl.query;

/**
 * Created by Illia on 10/22/2015.
 */
//TODO remove this class and use named query approach like
//TODO http://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-creating-database-queries-with-named-queries/
public final class HistoryQuery {

    public final static  String uniqueLastByDateHistories =
            "SELECT h.* FROM history AS h,(\n" +
            "    SELECT issue_id, date, MAX(id) AS ID\n" +
            "    FROM history\n" +
            "    GROUP BY issue_id, date\n" +
            ") AS hMaxId,\n" +
            "(\n" +
            "    SELECT issue_id, MAX(date) AS MaxDate\n" +
            "    FROM history\n" +
            "    GROUP BY issue_id\n" +
            ") AS hMaxDate\n" +
            "WHERE hMaxId.issue_id = hMaxDate.issue_id\n" +
            "AND hMaxId.date = hMaxDate.MaxDate\n" +
            "AND h.id = hMaxId.id";

    public final static String lastIssueByIssueID =
            "SELECT h.* FROM history AS h ,\n" +
            "(\n" +
            "    SELECT issue_id, date, MAX(id) AS ID\n" +
            "    FROM history\n" +
            "    where issue_id = :issueId\n" +
            "    GROUP BY issue_id, date\n" +
            ") AS hMaxId,\n" +
            "(\n" +
            "    SELECT issue_id, MAX(date) AS MaxDate\n" +
            "    FROM history\n" +
            "    GROUP BY issue_id\n" +
            ") AS hMaxDate\n" +
            "WHERE  \n" +
            "hMaxId.date = hMaxDate.MaxDate\n" +
            "AND h.id = hMaxId.id";

}
