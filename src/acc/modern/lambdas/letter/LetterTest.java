package acc.modern.lambdas.letter;

import java.util.function.Function;

public class LetterTest {
  private static String runLetter(String content) {
    Function<String, String> addHeader = Letter::addHeader;
    Function<String, String> transformationPipeline = addHeader.andThen(Letter::checkSpelling)
        .andThen(Letter::addFooter);
    return transformationPipeline.apply(content);
  }

  public static void runExample() {
    System.out.println("----------------Concatenating Letter Functions----------------");
    String mijotronLetter = LetterTest.runLetter("Mijotron");
    System.out.println(mijotronLetter);
  }
}
