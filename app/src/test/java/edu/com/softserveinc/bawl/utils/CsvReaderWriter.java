package edu.com.softserveinc.bawl.utils;

import edu.com.softserveinc.bawl.models.HistoryModel;
import edu.com.softserveinc.bawl.models.IssueStatus;
import org.supercsv.cellprocessor.FmtDate;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReaderWriter {

    private static final String PATH_HISTORY_MODEL_CSV = "src/test/resources/HistoryModels.csv";
    private  static final String PATH_STATUS_MODEL_CSV = "src/test/resources/StatusModels.csv";

    private static CellProcessor[] getHistoryModelWriterProcessors() {

        return new CellProcessor[] {
                new UniqueHashCode(), // id (must be unique)
                new NotNull(), // issue_id
                new NotNull(), // user_id
                new NotNull(), // status_id
                new FmtDate("MM/dd/yy HH:mm:ss aaa"), // date
        };
    }

    private static CellProcessor[] getStatusModelWriterProcessors() {

        return new CellProcessor[] {
                new UniqueHashCode(), // id (must be unique)
                new NotNull(), // name

        };
    }

    private static CellProcessor[] getHistoryModelReaderProcessors() {

        return new CellProcessor[] {
                new ParseInt(), // id (must be unique)
                new ParseInt(), // issue_id
                new ParseInt(), // user_id
                new ParseInt(), // status_id
                new ParseDate("MM/dd/yy HH:mm:ss aaa") // date
        };
    }

    private static CellProcessor[] getStatusModelReaderProcessors() {

        return new CellProcessor[] {
                new UniqueHashCode(new ParseInt()), // id (must be unique)
                new ParseInt(), // name
        };
    }

    public static void writeHistoryModelCsv( List<HistoryModel> historyModels) throws IOException {
        ICsvBeanWriter beanWriter = null;
        try {
            beanWriter = new CsvBeanWriter(new FileWriter( new File(PATH_HISTORY_MODEL_CSV)),
                    CsvPreference.STANDARD_PREFERENCE);

            // the header elements are used to map the bean values to each column (names must match)
            final String[] header = new String[] { "id", "issueId", "userId", "statusId","date"};
            final CellProcessor[] processors = getHistoryModelWriterProcessors();

            // write the header
            beanWriter.writeHeader(header);
            // write the beans
            for( final HistoryModel model : historyModels ) {
                beanWriter.write(model, header, processors);
            }
        }
        finally {
            if( beanWriter != null ) {
                beanWriter.close();
            }
        }
    }

    public static void writeStatusModelCsv( List<IssueStatus> statusModels) throws IOException {
        ICsvBeanWriter beanWriter = null;
        try {
            beanWriter = new CsvBeanWriter(new FileWriter( new File(PATH_STATUS_MODEL_CSV)),
                    CsvPreference.STANDARD_PREFERENCE);

            // the header elements are used to map the bean values to each column (names must match)
            final String[] header = new String[] { "id", "name"};
            final CellProcessor[] processors = getStatusModelWriterProcessors();

            // write the header
            beanWriter.writeHeader(header);
            // write the beans
            for( final IssueStatus model : statusModels ) {
                beanWriter.write(model, header, processors);
            }
        }
        finally {
            if( beanWriter != null ) {
                beanWriter.close();
            }
        }
    }

    public static List<IssueStatus> readStatusModelCsv() throws IOException {

        ICsvBeanReader beanReader = null;
        List<IssueStatus> statusModels = new ArrayList<IssueStatus>();
        try {
            beanReader = new CsvBeanReader(new FileReader(PATH_STATUS_MODEL_CSV), CsvPreference.STANDARD_PREFERENCE);

            // the header elements are used to map the values to the bean (names must match)
            final String[] header = beanReader.getHeader(true);
            final CellProcessor[] processors = getStatusModelReaderProcessors();
            IssueStatus statusModel = null;
            while( (statusModel = beanReader.read(IssueStatus.class, header, processors)) != null ) {
                statusModels.add(statusModel);
            }
        }
        finally {
            if( beanReader != null ) {
                beanReader.close();
            }
        }
        return statusModels;
    }

    public static List<HistoryModel> readHistoryModelCsv() throws IOException {

        ICsvBeanReader beanReader = null;
        List<HistoryModel> historyModels = new ArrayList<HistoryModel>();
        try {
            beanReader = new CsvBeanReader(new FileReader(PATH_HISTORY_MODEL_CSV), CsvPreference.STANDARD_PREFERENCE);

            // the header elements are used to map the values to the bean (names must match)
            final String[] header = beanReader.getHeader(true);
            final CellProcessor[] processors = getHistoryModelReaderProcessors();
            HistoryModel historyModel = null;
            while( (historyModel = beanReader.read(HistoryModel.class, header, processors)) != null ) {
                historyModels.add(historyModel);
            }
        }
        finally {
            if( beanReader != null ) {
                beanReader.close();
            }
        }
        return historyModels;
    }

}
