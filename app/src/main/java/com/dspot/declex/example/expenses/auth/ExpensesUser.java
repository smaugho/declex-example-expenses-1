package com.dspot.declex.example.expenses.auth;

import com.dspot.declex.example.expenses.vo.Expense;
import com.dspot.declex.example.expenses.vo.User;

import io.reactivex.Flowable;

public interface ExpensesUser extends User {
    void logout();

    Flowable<Expense> expenses();
}
