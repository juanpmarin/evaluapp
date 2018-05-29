package io.github.juanpmarin.evaluapp.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.github.juanpmarin.evaluapp.domain.Test;

@Dao
public interface TestDao {

    @Query("SELECT * FROM Test WHERE `temp` = 0 ORDER BY created DESC")
    LiveData<List<Test>> findAll();

    @Query("SELECT * FROM Test WHERE id = :id")
    LiveData<Test> findById(String id);

    @Insert
    void insert(Test test);

    @Update
    void update(Test test);

    @Query("DELETE FROM Test WHERE `temp` = 1")
    void deleteTemp();

}
