package com.dspot.declex.example.expenses.vo.impl;

import com.dspot.declex.example.expenses.vo.User;
import com.google.firebase.auth.FirebaseUser;

public class UserImpl implements User {
    private FirebaseUser firebaseUser;

    public UserImpl(FirebaseUser firebaseUser) {
        this.firebaseUser = firebaseUser;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String displayName() {
        return null;
    }
}
