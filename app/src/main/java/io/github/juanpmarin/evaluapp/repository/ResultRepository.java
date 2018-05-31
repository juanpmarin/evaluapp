package io.github.juanpmarin.evaluapp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.github.juanpmarin.evaluapp.domain.Result;

public class ResultRepository {

    private FirebaseFirestore firebaseFirestore;

    @Inject
    public ResultRepository(FirebaseFirestore firebaseFirestore) {
        this.firebaseFirestore = firebaseFirestore;
    }

    public String saveAnswers(Result result) {
        DocumentReference document = firebaseFirestore.collection("results")
                .document();

        document.set(result);

        return document.getId();
    }

    public LiveData<List<Result>> findAllResults() {
        MutableLiveData<List<Result>> results = new MutableLiveData<>();

        firebaseFirestore.collection("results")
                .addSnapshotListener((queryDocumentSnapshots, e) -> {
                    List<Result> objects = new ArrayList<>();

                    if (queryDocumentSnapshots != null) {
                        for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                            Result result = snapshot.toObject(Result.class);
                            result.setId(snapshot.getId());

                            objects.add(result);
                        }
                    }

                    results.setValue(objects);
                });

        return results;
    }

    public LiveData<Result> findById(String id) {
        MutableLiveData<Result> result = new MutableLiveData<>();

        firebaseFirestore.collection("results").document(id)
                .get()
                .addOnCompleteListener(task -> result.setValue(task.getResult().toObject(Result.class)));

        return result;
    }

}
