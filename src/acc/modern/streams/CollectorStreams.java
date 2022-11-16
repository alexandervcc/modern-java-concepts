package acc.modern.streams;

import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import acc.modern.model.Transaction;

import static acc.modern.model.Data.transactions;
import static java.util.stream.Collectors.*;

import java.util.Comparator;

public class CollectorStreams {
  public Map<?, ?> groupTransactionByCurrency() {
    Map<Currency, List<Transaction>> transactionsByCurrencies = transactions.stream()
        .collect(groupingBy(Transaction::getCurrency));

    return transactionsByCurrencies;
  }

  public long countTransactions() {
    // return transactions.stream().collect(Collectors.counting());
    return transactions.stream().count();
  }

  public Optional<Transaction> getHighestTransaction() {
    Comparator<Transaction> comparator = Comparator.comparingInt(Transaction::getValue);
    return transactions.stream()
        .collect(maxBy(comparator));
  }
}


