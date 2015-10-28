package edu.com.softserveinc.bawl.utils;

import edu.com.softserveinc.bawl.models.IssueModel;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Vasj on 21.10.2015.
 */
public class CsvIssueModelReaderWriter {
    private static final String PATH_ISSUE_MODEL_CSV = "src/test/resources/IssueModels.csv";

    private static CellProcessor[] getIssueModelWriterProcessors() {

        return new CellProcessor[]{
                new UniqueHashCode(), // id (must be unique)
                new Optional(), // attachments
                new NotNull(), // category_id
                new NotNull(), // description
                new NotNull(), // mapPointer
                new NotNull(), // name
                new NotNull(), // priority_id
                new NotNull(), // statusId
        };
    }

    private static CellProcessor[] getIssueModelReaderProcessors() {

        return new CellProcessor[]{
                new ParseInt(), // id (must be unique)
                new Optional(), // attachments
                new ParseInt(), // category_id
                new NotNull(), // description
                new NotNull(), // mapPointer
                new NotNull(), // name
                new ParseInt(), // priority_id
                new ParseInt(), // statusId
        };
    }

    public static void writeIssueModelCsv (List<IssueModel> issueModels) throws IOException {
        ICsvBeanWriter beanWriter = null;
        try{
            beanWriter = new CsvBeanWriter(new FileWriter(new File(PATH_ISSUE_MODEL_CSV)), CsvPreference.STANDARD_PREFERENCE);
            final String[] header = new String[] {"id", "attachments", "category_id", "description", "mapPointer", "name", "priority_id", "statusId"};
            final CellProcessor[] processors = getIssueModelWriterProcessors();

            beanWriter.writeHeader(header);
            for(IssueModel model : issueModels){
                beanWriter.write(model, header, processors);
            }
        }
        finally {
            if(beanWriter != null) {
                beanWriter.close();
            }
        }
    }

    public static List<IssueModel> readIssueModelCsv() throws IOException {
        ICsvBeanReader beanReader = null;
        List<IssueModel> issueModels = new ArrayList<IssueModel>();
        try {
            beanReader = new CsvBeanReader(new FileReader(PATH_ISSUE_MODEL_CSV), CsvPreference.STANDARD_PREFERENCE);
            final String[] header = beanReader.getHeader(true);
            final CellProcessor[] processors = getIssueModelReaderProcessors();
            IssueModel issueModel = null;
            while ((issueModel = beanReader.read(IssueModel.class, header, processors)) != null){
                issueModels.add(issueModel);
            }
        }
        finally {
            if (beanReader != null){
                beanReader.close();
            }
        }

        return issueModels;
    }
}
