package com.dspot.declex.example.expenses.auth.impl;

import com.dspot.declex.example.expenses.auth.LoginHandler;
import com.dspot.declex.example.expenses.vo.User;
import com.dspot.declex.example.expenses.vo.impl.UserImpl;
import com.google.firebase.auth.FirebaseAuth;

import io.reactivex.Single;

public class LoginHandlerImpl implements LoginHandler {
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
    public Single<User> signUp(String email, String password) {
        return Single.create(emitter -> FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .continueWith(task -> {
                    if (task.getException() == null)
                        emitter.onSuccess(new UserImpl(task.getResult().getUser()));
                    else
                        emitter.tryOnError(task.getException());
                    return null;
                }));
    }
}
