package tech.loga.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TransactionResource implements TransactionManagement {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    @Transactional
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction getTransactionById(Long id) {
        if(transactionRepository.findById(id).isPresent()){
            return transactionRepository.findById(id).get();
        }
        throw new TransactionNotFoundException(String.format("Failed to find transaction with id:%d",id));
    }

    @Override
    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getTransactionByDate(Date date) {
        return transactionRepository.findByDate(date);
    }

    @Override
    public List<Transaction> getTransactionByPeriod(Date start, Date end) {
        return transactionRepository.findByDateBetween(start,end);
    }

    @Override
    @Transactional
    public void editTransaction(Transaction transaction, Long id) {
        transactionRepository
                .findById(id)
                .ifPresentOrElse(up -> {
                    up.setAmount(transaction.getAmount());
                    up.setUpdatedAt(new Date(System.currentTimeMillis()));
                    transactionRepository.saveAndFlush(up);
                },() -> {
                    throw new TransactionRegistrationFailedException(String.format("Update transaction with id:%d failed.",id));
                });
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepository
                .findById(id)
                .ifPresentOrElse(
                        transaction -> transactionRepository.delete(transaction),
                        () -> {
                            throw new TransactionNotFoundException(String.format("Delete transaction with id:%d failed.",id));
                        }
                );
    }
}