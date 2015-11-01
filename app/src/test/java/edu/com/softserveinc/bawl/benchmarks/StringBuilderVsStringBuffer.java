package edu.com.softserveinc.bawl.benchmarks;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class StringBuilderVsStringBuffer {

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void stringBuilder() {
        StringBuilder stringBuilder =  new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            stringBuilder.append("hello!").append("\n");
        }
        stringBuilder.toString();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void stringBuffer() {
        StringBuffer stringBuffer =  new StringBuffer();
        for (int i = 0; i < 10000; i++) {
            stringBuffer.append("hello!").append("\n");
        }
        stringBuffer.toString();
    }




    public static void main(String... args) throws RunnerException, IOException {
        final Options opt = new OptionsBuilder()
                .jvmArgs("-XX:+UnlockCommercialFeatures")
                .include(StringBuilderVsStringBuffer.class.getSimpleName())
                .warmupIterations(10)
                .measurementIterations(10)
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
