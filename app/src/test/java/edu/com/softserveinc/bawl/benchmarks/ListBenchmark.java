package edu.com.softserveinc.bawl.benchmarks;

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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ListBenchmark {

    @org.openjdk.jmh.annotations.State(Scope.Benchmark)
    public static class State {
       List arrayList;
       List linkedList;

        public void init() {
             arrayList = new ArrayList();
             linkedList = new LinkedList();

            for (int i = 0; i < 10000; i++) {
                arrayList.add(new Object());
                linkedList.add(new Object());
            }
        }
    }


    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void benchmarkStartLinkedList(State state) {
        state.init();
        for (int i = 0; i < 500; i++) {
            state.linkedList.add(i, new Object());
        }
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void benchmarkMiddleLinkedList(State state) {
        state.init();
        final int start = new Integer(state.linkedList.size() / 2);
        final int end = start + 500;
        for (int i = start; i < end; i++) {
            state.linkedList.add(i, new Object());
        }
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void benchmarkEndLinkedList(State state) {
        state.init();
        final int start = new Integer(state.linkedList.size());
        final int end = start + 500;
        for (int i = start; i < end; i++) {
            state.linkedList.add(i, new Object());
        }
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void benchmarkSequenceLinked(State state) {
        state.init();
        state.linkedList.forEach(o -> {
            o.hashCode();
        });
    }
     @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void benchmarkStartArrayList(State state) {
         state.init();
         for (int i = 0; i < 500; i++) {
            state.arrayList.add(i, new Object());
        }
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void benchmarkMiddleArrayList(State state) {
        state.init();
        final int start = new Integer(state.arrayList.size() / 2);
        final int end = start + 500;
        for (int i = start; i < end; i++) {
            state.arrayList.add(i, new Object());
        }
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void benchmarkEndArrayList(State state) {
        state.init();
        final int start = new Integer(state.arrayList.size());
        final int end = start + 500;
        for (int i = start; i < end; i++) {
            state.arrayList.add(i, new Object());
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void benchmarkSequenceArrayList(State state) {
        state.init();
        state.arrayList.forEach(o -> {
            o.hashCode();
        });
    }

    public static void main(String... args) throws RunnerException, IOException {
        final Options opt = new OptionsBuilder()
                .jvmArgs("-XX:+UnlockCommercialFeatures")
                .include(ListBenchmark.class.getSimpleName())
                .warmupIterations(10)
                .measurementIterations(10)
                .forks(1)
                .build();
        new Runner(opt).run();
    }

}
