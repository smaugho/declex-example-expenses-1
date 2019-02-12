package com.dspot.declex.example.expenses.auth.impl;

import com.dspot.declex.example.expenses.auth.ExpensesUser;
import com.dspot.declex.example.expenses.auth.impl.firebase.FirestoreUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
}
