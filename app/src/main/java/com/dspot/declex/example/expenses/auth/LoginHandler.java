package com.dspot.declex.example.expenses.auth;

import com.dspot.declex.example.expenses.vo.User;

import io.reactivex.Single;

public interface LoginHandler {

    public Single<User> signInWithEmailAndPassword(String email, String password);

    public Single<User> signUp(String email, String password);

}
