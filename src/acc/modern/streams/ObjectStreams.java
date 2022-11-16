package acc.modern.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import acc.modern.model.Trader;
import acc.modern.model.Transaction;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.stream.Collectors.joining;
import static acc.modern.model.Data.transactions;
import static java.util.Comparator.comparing;

public class ObjectStreams {

  public List<Transaction> findIn2011AndSortedByValue() {
    return transactions.stream()
        .filter(transaction -> transaction.getYear() == 2011)
        .sorted(comparing(Transaction::getValue))
        .collect(toList());
  }

  public List<String> getCitiesOfWork() {
    return transactions.stream()
        .map(transaction -> transaction.getTrader().getCity())
        .distinct()
        .collect(toList());
  }

  public List<Trader> getTradersFromCambridgeSortedbyName() {
    return transactions.stream()
        .map(Transaction::getTrader)
        .filter(trader -> trader.getCity().equals("Cambridge"))
        .distinct()
        .sorted(comparing(Trader::getName))
        .collect(toList());
  }

  public String getStringOfAllTraderNameSortedByName_Inefficient() {
    // inefficient as Strings are being created when concatenating at each iteration
    return transactions.stream()
        .map(transaction -> transaction.getTrader().getName())
        .distinct()
        .sorted()
        .reduce("", (n1, n2) -> n1 + n2);
  }

  public String getStringOfAllTraderNameSortedByName() {
    // efficient
    return transactions.stream()
        .map(transaction -> transaction.getTrader().getName())
        .distinct()
        .sorted()
        .collect(joining());
  }

  public boolean thereAreTradersInMilan() {
    return transactions.stream()
        .anyMatch(transaction -> transaction.getTrader()
            .getCity()
            .equals("Milan"));
  }

  public void printTransactionValuesFromCambridgesTraders() {
    transactions.stream()
        .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
        .map(Transaction::getValue)
        .forEach(System.out::println);
  }

  public Optional<Integer> getMaxTransactionValue() {
    return transactions.stream()
        .map(Transaction::getValue)
        .reduce(Integer::max);
  }

  public void getPhytagorasTriplets() {
    IntStream.rangeClosed(1, 100)
        .boxed()
        .flatMap(a -> IntStream.rangeClosed(a, 100)
            .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
            .mapToObj(b -> new int[] { a, b, (int) Math.sqrt(a * a + b * b) }))
        .limit(5)
        .forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
  }

  public int getStreamFromArrays() {
    int[] numbers = { 2, 3, 5, 7, 11, 13 };
    return Arrays.stream(numbers).sum();
  }

  public long getWordInFile(String filename) {
    long uniqueWords = 0;
    try (Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
      uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
          .distinct()
          .count();
    } catch (IOException e) {
    }
    return uniqueWords;
  }

  public void functionStreamIterate() {
    Stream.iterate(0, n -> n + 2)
        .limit(10)
        .forEach(System.out::println);
  }

  // manage a inner state in order to supply next elements
  public void functionStreamGenerate() {
    IntSupplier fib = new IntSupplier() {
      private int previous = 0;
      private int current = 1;

      public int getAsInt() {
        int oldPrevious = this.previous;
        int nextValue = this.previous + this.current;
        this.previous = this.current;
        this.current = nextValue;
        return oldPrevious;
      }
    };
    IntStream.generate(fib).limit(10).forEach(System.out::println);
  }

}
