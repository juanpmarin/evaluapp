package io.github.juanpmarin.evaluapp.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import io.github.juanpmarin.evaluapp.domain.Question;

@Dao
public interface QuestionDao {

    @Query("SELECT * FROM Question WHERE test_id = :testId")
    LiveData<List<Question>> findAllByTestId(String testId);

}
