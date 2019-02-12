package com.dspot.declex.example.expenses.auth.impl.firebase;

import com.google.firebase.firestore.FirebaseFirestore;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;

public class FirestoreUser {

    static FirebaseFirestore db = FirebaseFirestore.getInstance();

    static public Completable createUser() {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {

            }
        });
    }
}
