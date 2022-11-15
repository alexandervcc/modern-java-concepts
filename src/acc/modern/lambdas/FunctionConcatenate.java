package acc.modern.lambdas;

import java.util.function.Function;

public class FunctionConcatenate {
  Function<Integer, Integer> f = x -> x + 1;
  Function<Integer, Integer> g = x -> x * 2;
  Function<Integer, Integer> h = f.compose(g);
  int result = h.apply(1);
}
