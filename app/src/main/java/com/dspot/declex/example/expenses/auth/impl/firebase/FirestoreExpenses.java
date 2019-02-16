package com.dspot.declex.example.expenses.auth.impl.firebase;

import com.dspot.declex.example.expenses.vo.Expense;
import com.dspot.declex.example.expenses.vo.Expense_;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;

public class FirestoreExpenses {
    static FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static final String EXPENSES = "expenses";

    static public Flowable<List<Expense>> getExpensesByUser(String userId) {

        return Flowable.create((FlowableOnSubscribe<QuerySnapshot>) emitter -> db.collection(FirestoreUser.USERS)
                .document(userId)
                .collection(EXPENSES)
                .addSnapshotListener((queryDocumentSnapshots, e) -> {
                    if (e != null)
                        emitter.tryOnError(e);
                    else
                        emitter.onNext(queryDocumentSnapshots);
                }), BackpressureStrategy.BUFFER)
                .map(documentSnapshot -> {
                    List<Expense> expenses = new ArrayList<>();

                    for (DocumentSnapshot document : documentSnapshot) {
                        Expense expense = document.toObject(Expense_.class);
                        expense.setId(document.getId());

                        expenses.add(expense);
                    }

                    return expenses;
                });
    }

    public static Completable createNewExpenseByUser(Expense expense, String userId) {
        return Completable.create(emitter -> db.collection(FirestoreUser.USERS)
                .document(userId)
                .collection(EXPENSES)
                .add(expense)
                .continueWith(task -> {
                    if (task.isSuccessful())
                        emitter.onComplete();
                    else
                        emitter.tryOnError(task.getException());
                    return null;
                }));
    }

    public static Completable removeExpense(String userId, String id) {
        return Completable.create(emitter -> db.collection(FirestoreUser.USERS)
                .document(userId)
                .collection(EXPENSES)
                .document(id)
                .delete()
                .continueWith(task -> {
                    if (task.isSuccessful())
                        emitter.onComplete();
                    else
                        emitter.tryOnError(task.getException());
                    return null;
                }));
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

    public static Observable<Expense> expenseById(String userId, String expenseId) {
        return Observable.create(emitter -> db.collection(FirestoreUser.USERS)
                .document(userId)
                .collection(EXPENSES)
                .document(expenseId)
                .get()
                .continueWith(task -> {
                    if (task.isSuccessful())
                        emitter.onNext(task.getResult().toObject(Expense_.class));
                    else
                        emitter.tryOnError(task.getException());
                    return null;
                }));
    }
}
