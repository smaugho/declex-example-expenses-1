package com.dspot.declex.example.expenses.auth.impl;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.dspot.declex.example.expenses.auth.ExpensesApp;
import com.dspot.declex.example.expenses.auth.ExpensesAuth;
import com.dspot.declex.example.expenses.auth.ExpensesUser;
import com.dspot.declex.example.expenses.vo.User;
import com.dspot.declex.example.expenses.vo.impl.UserImpl;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.Single;

public class ExpensesAuthImpl implements ExpensesAuth {

    ExpensesApp expensesApp = ExpensesAppImpl.getInstance();

    private static ExpensesAuth instance = new ExpensesAuthImpl();

    public static ExpensesAuth getInstance() {
        return instance;
    }

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    public Single<User> signInWithEmailAndPassword(String email, String password) {
        return Single.create(emitter -> FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .continueWith(task -> {
                    if (task.getException() == null)
                        emitter.onSuccess(new UserImpl(task.getResult().getUser()));
                    else
                        emitter.tryOnError(task.getException());
                    return null;
                }));
    }

    @Override
    public Single<User> createUserWithEmailAndPassword(String name, String email, String password) {
        return Single.create(emitter -> FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .onSuccessTask(new SuccessContinuation<AuthResult, AuthResult>() {
                    @NonNull
                    @Override
                    public Task<AuthResult> then(@Nullable AuthResult authResult) throws Exception {


                        return null;
                    }
                })
                .continueWith(task -> {
                    if (task.getException() == null)
                        emitter.onSuccess(new UserImpl(task.getResult().getUser()));
                    else
                        emitter.tryOnError(task.getException());
                    return null;
                }));
    }

    @Override
    public ExpensesUser currentUser() {
        FirebaseUser current = firebaseAuth.getCurrentUser();
        if (firebaseAuth.getCurrentUser() != null)
            return new ExpensesUserImpl(current);
        return null;
    }
}
