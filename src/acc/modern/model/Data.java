package acc.modern.model;

import java.util.Arrays;
import java.util.Currency;
import java.util.List;

public class Data {
  static Trader raoul = new Trader("Raoul", "Cambridge");
  static Trader mario = new Trader("Mario", "Milan");
  static Trader alan = new Trader("Alan", "Cambridge");
  static Trader brian = new Trader("Brian", "Cambridge");
  public static List<Transaction> transactions = Arrays.asList(
      new Transaction(brian, 2011, 300, Currency.getInstance("USD")),
      new Transaction(raoul, 2012, 1000, Currency.getInstance("EUR")),
      new Transaction(raoul, 2011, 400, Currency.getInstance("USD")),
      new Transaction(mario, 2012, 710, Currency.getInstance("EUR")),
      new Transaction(mario, 2012, 700, Currency.getInstance("USD")),
      new Transaction(alan, 2012, 950, Currency.getInstance("USD")));
}
