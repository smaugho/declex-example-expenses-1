package com.dspot.declex.example.expenses.auth.impl;

import com.dspot.declex.example.expenses.auth.ExpensesApp;
import com.dspot.declex.example.expenses.auth.ExpensesAuth;
import com.dspot.declex.example.expenses.auth.ExpensesUser;
import com.dspot.declex.example.expenses.vo.User;
import com.dspot.declex.example.expenses.vo.impl.UserImpl;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.androidannotations.annotations.EBean;

import io.reactivex.Single;

@EBean(scope = EBean.Scope.Singleton)
public class ExpensesAuthImpl implements ExpensesAuth {

    ExpensesApp expensesApp = ExpensesAppImpl.getInstance();

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
    public Single<User> createUserWithEmailAndPassword(String email, String password) {
        return Single.create(emitter -> FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
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
