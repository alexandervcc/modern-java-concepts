package acc.modern.lambdas.execute_around_pattern;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Processor {
  private static String processFile(BufferedReaderProcessor p)
      throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
      return p.process(br);
    }
  }

  public static void run() throws IOException {
    String oneLine = processFile((BufferedReader br) -> br.readLine());
    System.out.println("1 line buffer: " + oneLine);
    String twoLines = processFile((BufferedReader br) -> br.readLine() + br.readLine());
    System.out.println("2 line buffer: " + twoLines);
  }
}
