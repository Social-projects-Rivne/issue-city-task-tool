package edu.com.softserveinc.bawl.perf;

import edu.com.softserveinc.bawl.dto.UserHistoryDto;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Ignore
public class ComparatorTest {

    long comparator;
    long lambda;
    long stream;

    List<UserHistoryDto> historyDtoList;

    @Test
    public void testSmallListMaxCycles() {
        System.out.println("testSmallListMaxCycles");
        testAll(100000, 10);
    }

    @Test
    public void testMaxListSmallCycles() {
        System.out.println("testMaxListSmallCycles");
        testAll(10, 100000);
    }

    @Test
    public void testAvgListAvgCycles() {
        System.out.println("testAvgListAvgCycles");
        testAll(1000, 1000);
    }



    private void testAll(long cycles, long listSize) {
        for (int i=0; i < cycles; i++) {
            before(listSize);
            stream += sortWithStream();
            before(listSize);
            lambda += sortWithLambda();
            before(listSize);
            comparator += sortWithComparator();
        }
        System.out.println("lambda - " + lambda);
        System.out.println("comparator - " + comparator);
        System.out.println("stream - " + stream);
    }

    private void before(long listSize){
        historyDtoList = new ArrayList<>();
        for (int i=0; i < listSize; i++) {
            UserHistoryDto userHistoryDto = new UserHistoryDto();
            userHistoryDto.setDate(getTime());
            userHistoryDto.setIssueName(String.valueOf(i));
            userHistoryDto.setUsername(String.valueOf(i));
            historyDtoList.add(userHistoryDto);
        }
    }

    private long sortWithComparator(){
        final StopWatch stopWatch = new StopWatch("sortWithComparator");
        stopWatch.start();
        Collections.sort(historyDtoList);
        stopWatch.stop();
        return stopWatch.getTotalTimeMillis();

    }


    private long sortWithLambda(){
        final StopWatch stopWatch = new StopWatch("sortWithLambda");
        stopWatch.start();
        Collections.sort(historyDtoList, (p1, p2) -> p1.compareTo(p2) );
        stopWatch.stop();
        return stopWatch.getTotalTimeMillis();

    }

    private long sortWithStream(){
        final StopWatch stopWatch = new StopWatch("sortWithLambda");
        stopWatch.start();
        historyDtoList = historyDtoList.parallelStream().sorted(UserHistoryDto::compare).collect(Collectors.toList());;
        stopWatch.stop();
        return stopWatch.getTotalTimeMillis();

    }

    private Date getTime() {
        long offset = Timestamp.valueOf("2015-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2016-01-01 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp rand = new Timestamp(offset + (long)(Math.random() * diff));
        return new Date(rand.getTime());
    }
}
