package com.dspot.declex.example.expenses.auth;

import com.dspot.declex.example.expenses.vo.Expense;
import com.dspot.declex.example.expenses.vo.User;

import java.util.Date;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface ExpensesUser extends User {
    void logout();

    Flowable<Expense> expenses();

    Completable createNewExpense(String description, double amount, Date date, String comment);
}
