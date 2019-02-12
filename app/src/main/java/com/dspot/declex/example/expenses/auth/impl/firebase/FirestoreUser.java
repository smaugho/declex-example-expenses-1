package com.dspot.declex.example.expenses.auth.impl.firebase;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import org.androidannotations.annotations.EBean;

import java.util.HashMap;
import java.util.Map;

@EBean
public class FirestoreUser {

    private static final String USERS = "users";
    private static final String FIELD_DISPLAY_NAME = "display_name";

    static FirebaseFirestore db = FirebaseFirestore.getInstance();

    static public Task<Void> createUser(String userId, String userDisplayName) {
        Map<String, Object> data = new HashMap<>();
        data.put(FIELD_DISPLAY_NAME, userDisplayName);

        return db.collection(USERS).document(userId)
                .set(data);
    }
}
