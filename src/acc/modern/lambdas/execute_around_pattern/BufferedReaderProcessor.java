package acc.modern.lambdas.execute_around_pattern;

import java.io.BufferedReader;
import java.io.IOException;

public interface BufferedReaderProcessor {
  String process(BufferedReader b) throws IOException;
}