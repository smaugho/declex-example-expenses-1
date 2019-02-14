package com.dspot.declex.example.expenses.auth.impl.firebase;

import com.dspot.declex.example.expenses.vo.Expense;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.functions.Function;

public class FirestoreExpenses {
    static FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static final String EXPENSES = "expenses";


    static public Flowable<Expense> getExpensesByUser(String userId) {

        return Flowable.create(new FlowableOnSubscribe<DocumentSnapshot>() {
            @Override
            public void subscribe(FlowableEmitter<DocumentSnapshot> emitter) throws Exception {
                db.collection(EXPENSES)
                        .document(userId)
                        .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                                if (e != null)
                                    emitter.tryOnError(e);
                                else
                                    emitter.onNext(documentSnapshot);
                            }
                        });
            }
        }, BackpressureStrategy.BUFFER)
                .map(new Function<DocumentSnapshot, Expense>() {
                    @Override
                    public Expense apply(DocumentSnapshot documentSnapshot) throws Exception {
                        return null;
                    }
                });

    }
}
