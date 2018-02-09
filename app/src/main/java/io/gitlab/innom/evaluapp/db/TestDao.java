package io.gitlab.innom.evaluapp.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.gitlab.innom.evaluapp.domain.Test;

@Dao
public interface TestDao {

    @Query("SELECT * FROM Test")
    LiveData<List<Test>> findAll();

    @Insert()
    void insert(Test... test);

}
