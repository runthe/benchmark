package benchmark;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@BenchmarkMode({Mode.AverageTime})
public class BenchMark {

  @Benchmark
  public void testBefore() {
    ClientOSBefore.getCurrentClient();
  }

  @Benchmark
  public void testAfter() {
    ClientOSAfter.getCurrentClient();
  }

  @Benchmark
  public void testEnum() {
    ClientOSEnum.getClientOSByUserAgent("Android Mobile");
  }

  public static void main(String[] args) {
    Options opt =
      new OptionsBuilder().include(BenchMark.class.getSimpleName()).warmupIterations(3).measurementIterations(3)
        .threads(90).forks(1).build();

    try {
      new Runner(opt).run();
    } catch (RunnerException e) {
      e.printStackTrace();
    }
  }
}
