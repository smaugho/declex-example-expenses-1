package com.dspot.declex.example.expenses.auth;

import com.dspot.declex.example.expenses.vo.Expense;
import com.dspot.declex.example.expenses.vo.User;

import java.util.Date;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;

public interface ExpensesUser extends User {
    void logout();

    Flowable<List<Expense>> expenses();

    Completable createNewExpense(String description, double amount, Date date, String comment);

    Completable removeExpense(String id);

    Completable editExpense(Expense id);

    Observable<Expense> getExpenseById(String id);
}
