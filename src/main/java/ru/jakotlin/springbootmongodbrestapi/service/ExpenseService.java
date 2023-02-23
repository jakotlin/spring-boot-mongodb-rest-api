package ru.jakotlin.springbootmongodbrestapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.jakotlin.springbootmongodbrestapi.model.Expense;
import ru.jakotlin.springbootmongodbrestapi.repository.ExpenseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public void addExpense(Expense expense) {
        expenseRepository.insert(expense);
    }

    public void updateExpense(Expense expense) {
        Expense savedExpense = expenseRepository.findById(expense.getId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot find expense by ID %s", expense.getId())));

        savedExpense.setExpenseName(expense.getExpenseName());
        savedExpense.setExpenseCategory(expense.getExpenseCategory());
        savedExpense.setExpenseAmount(expense.getExpenseAmount());

        expenseRepository.save(expense);
    }

    public List<Expense> getAllExpense() {
        return expenseRepository.findAll();
    }

    public Expense getExpenseByName(String name) {
        return expenseRepository.findByName(name).orElseThrow(() -> new RuntimeException(
                String.format("Cannot find expense by Name %s", name)));
    }

    public void deleteExpense(String id) {
        expenseRepository.deleteById(id);
    }
}
