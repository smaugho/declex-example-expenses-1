package com.dspot.declex.example.expenses.auth.impl;

import com.dspot.declex.example.expenses.auth.ExpensesUser;
import com.dspot.declex.example.expenses.auth.impl.firebase.FirestoreExpenses;
import com.dspot.declex.example.expenses.auth.impl.firebase.FirestoreUser;
import com.dspot.declex.example.expenses.vo.Expense;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Date;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;

public class ExpensesUserImpl implements ExpensesUser {

    private FirebaseUser firebaseUser;

    public ExpensesUserImpl(FirebaseUser firebaseUser) {
        this.firebaseUser = firebaseUser;
    }

    @Override
    public String getId() {
        return firebaseUser.getUid();
    }

    @Override
    public String email() {
        return firebaseUser.getEmail();
    }

    @Override
    public String displayName() {
        return FirestoreUser.getUserById(getId());
    }

    @Override
    public void logout() {
        FirebaseAuth.getInstance().signOut();
    }

    @Override
    public Flowable<List<Expense>> expenses() {
        return FirestoreExpenses.getExpensesByUser(getId());
    }

    @Override
    public Completable createNewExpense(String description, double amount, Date date, String comment) {

        Expense expense = new Expense();
        expense.setAmount(amount);
        expense.setComment(comment);
        expense.setDescription(comment);
        expense.setDate(date);

        return FirestoreExpenses.createNewExpenseByUser(expense, getId());
    }

    @Override
    public Completable removeExpense(String id) {
        return FirestoreExpenses.removeExpense(getId(), id);
    }

    @Override
    public Completable editExpense(Expense expense) {
        return FirestoreExpenses.editExpense(getId(), expense);
    }

    @Override
    public Observable<Expense> getExpenseById(String id) {
        return FirestoreExpenses.expenseById(getId(), id);
    }
}
