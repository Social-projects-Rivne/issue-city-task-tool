/******************************************************************************
 * Copyright © 2015 thinkstep AG.
 * All rights reserved.
 *****************************************************************************/

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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MapBanchmark {

    @org.openjdk.jmh.annotations.State(Scope.Benchmark)
    public static class State {
        Map map;

        public void init() {
            map = new HashMap();

            for (int i = 0; i < 1000000; i++) {
                final String key = String.valueOf(i);
                map.put(key, key);
            }
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void benchmarkMapForEach(State state) {
        state.init();
        state.map.forEach((key,value) -> {
            value.toString();
        });
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void benchmarkMapForKeySet(State state) {
        state.init();
        state.map.keySet().forEach(key -> {
            state.map.get(key);
        });
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void benchmarkMapForPure(State state) {
        state.init();
        for (Object key : state.map.keySet()) {
            state.map.get(key);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void benchmarkMapForEachEntrySet(State state) {
        state.init();
        final Set<Map.Entry> set = state.map.entrySet();
        set.forEach(entry -> {
            entry.getValue();
        });
    }

    public static void main(String... args) throws RunnerException, IOException {
        final Options opt = new OptionsBuilder()
                .jvmArgs("-XX:+UnlockCommercialFeatures")
                .include(MapBanchmark.class.getSimpleName())
                .warmupIterations(10)
                .measurementIterations(10)
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
