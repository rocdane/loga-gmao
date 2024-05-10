package tech.loga.transaction;

import java.util.Date;
import java.util.List;

public interface TransactionManagement {
    Transaction createTransaction(Transaction transaction);
    Transaction getTransactionById(Long id);
    List<Transaction> getAllTransaction();
    List<Transaction> getTransactionByDate(Date date);
    List<Transaction> getTransactionByPeriod(Date start, Date end);
    void editTransaction(Transaction transaction, Long id);
    void deleteTransaction(Long id);
}
