package com.dspot.declex.example.expenses.auth.impl;

import com.dspot.declex.example.expenses.auth.ExpensesApp;
import com.dspot.declex.example.expenses.auth.ExpensesAuth;
import com.dspot.declex.example.expenses.auth.ExpensesUser;
import com.dspot.declex.example.expenses.auth.impl.firebase.FirestoreUser;
import com.dspot.declex.example.expenses.vo.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import io.reactivex.Single;

@EBean(scope = EBean.Scope.Singleton)
public class ExpensesAuthImpl implements ExpensesAuth {

    @Bean
    ExpensesApp expensesApp;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    public Single<User> signInWithEmailAndPassword(String email, String password) {
        return Single.create(emitter -> FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .continueWith(task -> {
                    if (task.getException() == null)
                        emitter.onSuccess(new ExpensesUserImpl(task.getResult().getUser()));
                    else
                        emitter.tryOnError(task.getException());
                    return null;
                }));
    }

    @Override
    public Single<User> createUserWithEmailAndPassword(String name, String email, String password) {
        return Single.create(emitter -> FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .onSuccessTask(authResult -> FirestoreUser.createUser(authResult.getUser().getUid(), name)
                        .continueWith(task -> {
                            if (task.getException() == null)
                                emitter.onSuccess(new ExpensesUserImpl(authResult.getUser()));
                            else
                                emitter.tryOnError(task.getException());
                            return null;
                        })));
    }

    @Override
    public ExpensesUser currentUser() {
        FirebaseUser current = firebaseAuth.getCurrentUser();
        if (firebaseAuth.getCurrentUser() != null)
            return new ExpensesUserImpl(current);
        return null;
    }

}
