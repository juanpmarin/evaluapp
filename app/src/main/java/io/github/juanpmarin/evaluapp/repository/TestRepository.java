package io.github.juanpmarin.evaluapp.repository;

import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import io.github.juanpmarin.evaluapp.AppExecutors;
import io.github.juanpmarin.evaluapp.db.TestDao;
import io.github.juanpmarin.evaluapp.domain.Test;

public class TestRepository {

    private final TestDao testDao;

    private final AppExecutors appExecutors;

    @Inject
    public TestRepository(TestDao testDao, AppExecutors appExecutors) {
        this.testDao = testDao;
        this.appExecutors = appExecutors;
    }

    public LiveData<List<Test>> findAll() {
        return this.testDao.findAll();
    }

    public LiveData<Test> findById(String id) {
        return testDao.findById(id);
    }

    public void insert(Test test) {
        appExecutors.diskIO().execute(() -> {
            this.testDao.deleteTemp();
            this.testDao.insert(test);
        });
    }

    public void update(Test test) {
        appExecutors.diskIO().execute(() -> this.testDao.update(test));
    }

}
