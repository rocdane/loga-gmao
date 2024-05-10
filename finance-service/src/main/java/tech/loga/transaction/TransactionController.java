package tech.loga.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("finance-service")
public class TransactionController {

    @Autowired
    private TransactionManagement transactionManagement;

    @PostMapping(path = "incomes",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Transaction> registerIncome(@RequestBody Income income){
        try {
            return ResponseEntity.ok(transactionManagement.createTransaction(income));
        }catch (Exception e){
            throw new TransactionRegistrationFailedException("Register new Income failed"+e.getMessage());
        }
    }

    @GetMapping(path = "incomes", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Income> getIncomeById(@PathVariable Long id){
        return ResponseEntity.ok((Income)transactionManagement.getTransactionById(id));
    }

    @GetMapping(path = "incomes", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Income>> getAllIncome(){
        List<Transaction> transactions = transactionManagement.getAllTransaction();
        List<Income> incomes = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if(transaction instanceof Income){
                incomes.add((Income)transaction);
            }
        }
        return ResponseEntity.ok(incomes);
    }

    @GetMapping(path = "incomes", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Income>> getIncomesByDate(@RequestParam Date date){
        List<Transaction> transactions = transactionManagement.getTransactionByDate(date);
        List<Income> incomes = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if(transaction instanceof Income){
                incomes.add((Income)transaction);
            }
        }
        return ResponseEntity.ok(incomes);
    }

    @GetMapping(path = "incomes", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Income>> getIncomesByPeriod(@RequestParam Date start, @RequestParam Date end){
        List<Transaction> transactions = transactionManagement.getTransactionByPeriod(start,end);
        List<Income> incomes = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if(transaction instanceof Income){
                incomes.add((Income)transaction);
            }
        }
        return ResponseEntity.ok(incomes);
    }

    @PostMapping(path = "expenses",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Transaction> registerExpense(@RequestBody Expense expense){
        try {
            return ResponseEntity.ok(transactionManagement.createTransaction(expense));
        }catch (Exception e){
            throw new TransactionRegistrationFailedException("Register new Expense failed \n"+e.getMessage());
        }
    }

    @GetMapping(path = "expenses", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Expense> getExpenseById(@PathVariable Long id){
        return ResponseEntity.ok((Expense) transactionManagement.getTransactionById(id));
    }

    @GetMapping(path = "expenses", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Expense>> getAllExpenses(){
        List<Transaction> transactions = transactionManagement.getAllTransaction();
        List<Expense> expenses = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if(transaction instanceof Expense){
                expenses.add((Expense) transaction);
            }
        }
        return ResponseEntity.ok(expenses);
    }

    @GetMapping(path = "expenses", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Expense>> getExpensesByDate(@RequestParam Date date){
        List<Transaction> transactions = transactionManagement.getTransactionByDate(date);
        List<Expense> expenses = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if(transaction instanceof Expense){
                expenses.add((Expense) transaction);
            }
        }
        return ResponseEntity.ok(expenses);
    }

    @GetMapping(path = "expenses", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Expense>> getExpensesByPeriod(@RequestParam Date start, @RequestParam Date end){
        List<Transaction> transactions = transactionManagement.getTransactionByPeriod(start,end);
        List<Expense> expenses = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if(transaction instanceof Expense){
                expenses.add((Expense) transaction);
            }
        }
        return ResponseEntity.ok(expenses);
    }

    @PutMapping(path = "transactions/{id}")
    void editTransaction(@RequestBody Transaction transaction, @PathVariable Long id){
        try {
            transactionManagement.editTransaction(transaction,id);
        }catch (Exception e){
            throw new TransactionNotFoundException("Update transaction failed:"+e.getMessage());
        }
    }

    @DeleteMapping(path = "transactions/{id}")
    void deleteTransaction(@PathVariable Long id){
        try {
            transactionManagement.deleteTransaction(id);
        }catch (Exception e){
            throw new TransactionNotFoundException("Delete transaction failed:"+e.getMessage());
        }
    }
}
