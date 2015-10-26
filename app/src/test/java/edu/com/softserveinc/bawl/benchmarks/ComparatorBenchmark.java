package edu.com.softserveinc.bawl.benchmarks;

import edu.com.softserveinc.bawl.dto.UserHistoryDto;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ComparatorBenchmark {


    @org.openjdk.jmh.annotations.State(Scope.Benchmark)
    public static class State {
        private static int N;
        List<UserHistoryDto> historyDtoList;

        public void init() {
            historyDtoList = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                UserHistoryDto userHistoryDto = new UserHistoryDto();
                userHistoryDto.setDate(getTime());
                userHistoryDto.setIssueName(String.valueOf(i));
                userHistoryDto.setUsername(String.valueOf(i));
                historyDtoList.add(userHistoryDto);
            }
        }
    }


    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void benchmarkStreamList_10(State state) {
        state.N = 10;
        state.init();
        state.historyDtoList.parallelStream().sorted(UserHistoryDto::compare).collect(Collectors.toList());

    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void benchmarkLambdaList_10(State state) {
        state.N = 10;
        state.init();
        Collections.sort(state.historyDtoList, (p1, p2) -> p1.compareTo(p2));
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public List<UserHistoryDto> benchmarkComparatorList_10(State state) {
        state.N = 10;
        state.init();
        Collections.sort(state.historyDtoList);
        return state.historyDtoList;
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void benchmarkStreamList_100(State state) {
        state.N = 100;
        state.init();
        state.historyDtoList.parallelStream().sorted(UserHistoryDto::compare).collect(Collectors.toList());

    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void benchmarkLambdaList_100(State state) {
        state.N = 100;
        state.init();
        Collections.sort(state.historyDtoList, (p1, p2) -> p1.compareTo(p2));
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public List<UserHistoryDto> benchmarkComparatorList_100(State state) {
        state.N = 100;
        state.init();
        Collections.sort(state.historyDtoList);
        return state.historyDtoList;
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void benchmarkStreamList_1000(State state) {
        state.N = 1000;
        state.init();
        state.historyDtoList.parallelStream().sorted(UserHistoryDto::compare).collect(Collectors.toList());

    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void benchmarkLambdaList_1000(State state) {
        state.N = 1000;
        state.init();
        Collections.sort(state.historyDtoList, (p1, p2) -> p1.compareTo(p2));
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public List<UserHistoryDto> benchmarkComparatorList_1000(State state) {
        state.N = 1000;
        state.init();
        Collections.sort(state.historyDtoList);
        return state.historyDtoList;
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void benchmarkStreamList_100000(State state) {
        state.N = 100000;
        state.init();
        state.historyDtoList.parallelStream().sorted(UserHistoryDto::compare).collect(Collectors.toList());

    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void benchmarkLambdaList_100000(State state) {
        state.N = 100000;
        state.init();
        Collections.sort(state.historyDtoList, (p1, p2) -> p1.compareTo(p2));
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public List<UserHistoryDto> benchmarkComparatorList_100000(State state) {
        state.N = 100000;
        state.init();
        Collections.sort(state.historyDtoList);
        return state.historyDtoList;
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void benchmarkStreamList_1000000(State state) {
        state.N = 1000000;
        state.init();
        state.historyDtoList.parallelStream().sorted(UserHistoryDto::compare).collect(Collectors.toList());

    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void benchmarkLambdaList_1000000(State state) {
        state.N = 1000000;
        state.init();
        Collections.sort(state.historyDtoList, (p1, p2) -> p1.compareTo(p2));
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public List<UserHistoryDto> benchmarkComparatorList_1000000(State state) {
        state.N = 1000000;
        state.init();
        Collections.sort(state.historyDtoList);
        return state.historyDtoList;
    }


    public static void main(String... args) throws RunnerException, IOException {
        final Options opt = new OptionsBuilder()
                .jvmArgs("-XX:+UnlockCommercialFeatures")
                .include(ComparatorBenchmark.class.getSimpleName())
                .warmupIterations(10)
                .measurementIterations(10)
                .forks(1)
                .build();
        new Runner(opt).run();
    }

    private static Date getTime() {
        long offset = Timestamp.valueOf("2015-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2016-01-01 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp rand = new Timestamp(offset + (long)(Math.random() * diff));
        return new Date(rand.getTime());
    }
}
