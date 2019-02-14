package com.dspot.declex.example.expenses.auth.impl;

import com.dspot.declex.example.expenses.auth.ExpensesUser;
import com.dspot.declex.example.expenses.auth.impl.firebase.FirestoreExpenses;
import com.dspot.declex.example.expenses.auth.impl.firebase.FirestoreUser;
import com.dspot.declex.example.expenses.vo.Expense;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

public class ExpensesUserImpl implements ExpensesUser {

    FirebaseUser firebaseUser;


    public ExpensesUserImpl(FirebaseUser firebaseUser) {
        this.firebaseUser = firebaseUser;
    }

    @Override
    public String getId() {
        return null;
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
    public Flowable<Expense> expenses() {
        return FirestoreExpenses.getExpensesByUser(getId());
    }
}
