package com.dspot.declex.example.expenses.auth;

import com.dspot.declex.example.expenses.auth.impl.ExpensesAuthImpl;
import com.dspot.declex.example.expenses.vo.User;

import org.androidannotations.annotations.ImplementedBy;

import io.reactivex.Single;

@ImplementedBy(ExpensesAuthImpl.class)
public interface ExpensesAuth {
    Single<User> signInWithEmailAndPassword(String email, String password);

    Single<User> createUserWithEmailAndPassword(String email, String password);

    ExpensesUser currentUser();
}
