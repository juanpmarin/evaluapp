package io.github.juanpmarin.evaluapp.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.github.juanpmarin.evaluapp.domain.Question;
import io.github.juanpmarin.evaluapp.domain.Test;

@Dao
public interface TestDao {

    @Query("SELECT * FROM Test ORDER BY created DESC")
    LiveData<List<Test>> findAll();

    @Insert
    void insert(Test test, List<Question> questions);

}
