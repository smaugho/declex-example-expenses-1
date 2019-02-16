package com.dspot.declex.example.expenses.auth.impl.firebase;

import android.support.annotation.NonNull;

import com.dspot.declex.example.expenses.vo.Expense;
import com.dspot.declex.example.expenses.vo.Expense_;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

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

    static public Flowable<List<Expense>> getExpensesByUser(String userId) {

        return Flowable.create(new FlowableOnSubscribe<QuerySnapshot>() {
            @Override
            public void subscribe(FlowableEmitter<QuerySnapshot> emitter) throws Exception {
                db.collection(FirestoreUser.USERS)
                        .document(userId)
                        .collection(EXPENSES)
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                                if (e != null)
                                    emitter.tryOnError(e);
                                else
                                    emitter.onNext(queryDocumentSnapshots);
                            }
                        })
                ;
            }
        }, BackpressureStrategy.BUFFER)
                .map(new Function<QuerySnapshot, List<Expense>>() {
                    @Override
                    public List<Expense> apply(QuerySnapshot documentSnapshot) throws Exception {
                        List<Expense> expenses = new ArrayList<>();

                        for (DocumentSnapshot document : documentSnapshot) {
                            Expense expense = document.toObject(Expense_.class);
                            expense.setId(document.getId());

                            expenses.add(expense);
                        }

                        return expenses;
                    }
                });
    }

    public static Completable createNewExpenseByUser(Expense expense, String userId) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                db.collection(FirestoreUser.USERS)
                        .document(userId)
                        .collection(EXPENSES)
                        .add(expense)
                        .continueWith(new Continuation<DocumentReference, Object>() {
                            @Override
                            public Object then(@NonNull Task<DocumentReference> task) throws Exception {
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

    public static Completable removeExpense(String userId, String id) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                db.collection(FirestoreUser.USERS)
                        .document(userId)
                        .collection(EXPENSES)
                        .document(id)
                        .delete()
                        .continueWith(new Continuation<Void, Object>() {
                            @Override
                            public Object then(@NonNull Task<Void> task) {
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

    public static Completable editExpense(String userId, Expense expense) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                /*db.collection(FirestoreUser.USERS)
                        .document(userId)
                        .collection(EXPENSES)
                        .document(expense.getId())
                        .update(expense)
                        .continueWith(new Continuation<Void, Object>() {
                            @Override
                            public Object then(@NonNull Task<Void> task) {
                                if (task.isSuccessful())
                                    emitter.onComplete();
                                else
                                    emitter.tryOnError(task.getException());
                                return null;
                            }
                        });*/
                emitter.onComplete();
            }
        });
    }
}
