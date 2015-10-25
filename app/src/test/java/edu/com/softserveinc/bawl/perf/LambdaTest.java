package edu.com.softserveinc.bawl.perf;

import org.junit.Ignore;
import org.junit.Test;
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
import java.util.List;
import java.util.concurrent.TimeUnit;

@Ignore
public class LambdaTest {

    @org.openjdk.jmh.annotations.State(Scope.Benchmark)
    public static class State {
        private static final int N = 10000;
        final List<Double> list;
        public State() {
            list = new ArrayList<>(N);
            for (int i = 0; i < N; ++i)
                list.add((double)i);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public Double benchmarkA(State state) {
        return state.list.stream().min(Double::compare).get();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public double benchmarkB(State state) {
        double min = Double.MAX_VALUE;
        for (Double d : state.list ) {
            if ( d < min ) {
                min = d;
            }
        }
        return min;
    }

    @Test
    public void runJmh() throws RunnerException, IOException {

        final Options opt = new OptionsBuilder()
                .jvmArgs("-XX:+UnlockCommercialFeatures")
                .include(LambdaTest.class.getSimpleName())
                .warmupIterations(10)
                .measurementIterations(10)
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
