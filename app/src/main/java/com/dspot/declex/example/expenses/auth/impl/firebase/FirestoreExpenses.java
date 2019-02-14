package com.dspot.declex.example.expenses.auth.impl.firebase;

import android.support.annotation.NonNull;

import com.dspot.declex.example.expenses.vo.Expense;
import com.dspot.declex.example.expenses.vo.Expense_;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
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
                        Expense expense = documentSnapshot.toObject(Expense_.class);
                        return expense;
                    }
                });
    }

    public static Completable createNewExpenseByUser(Expense expense, String userId) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                db.collection(EXPENSES)
                        .document(userId)
                        .set(expense)
                        .continueWith(new Continuation<Void, Object>() {
                            @Override
                            public Object then(@NonNull Task<Void> task) throws Exception {

                                if (task.isSuccessful())
                                    emitter.onComplete();
                                else
                                    emitter.tryOnError(task.getException());

                                return null;
                            }
                        });
            }
        });
    }
}
