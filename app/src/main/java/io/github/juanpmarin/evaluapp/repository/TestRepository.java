package io.github.juanpmarin.evaluapp.repository;

import android.arch.lifecycle.LiveData;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.github.juanpmarin.evaluapp.AppExecutors;
import io.github.juanpmarin.evaluapp.db.TestDao;
import io.github.juanpmarin.evaluapp.domain.Resource;
import io.github.juanpmarin.evaluapp.domain.Test;

import static android.arch.lifecycle.Transformations.map;

public class TestRepository {

    private final TestDao testDao;

    private AppExecutors appExecutors;

    @Inject
    public TestRepository(TestDao testDao, AppExecutors appExecutors) {
        this.testDao = testDao;
        this.appExecutors = appExecutors;
    }

    public LiveData<Resource<List<Test>>> findAll() {
        return map(this.testDao.findAll(), Resource::success);
    }

    public LiveData<Test> findById(String id) {
        return testDao.findById(id);
    }

    public void insert(Test test) {
        appExecutors.diskIO().execute(() -> this.testDao.insert(test));
    }

    public void update(Test test) {
        appExecutors.diskIO().execute(() -> this.testDao.update(test));
    }

}
