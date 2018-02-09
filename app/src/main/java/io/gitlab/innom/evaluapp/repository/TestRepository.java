package io.gitlab.innom.evaluapp.repository;

import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import io.gitlab.innom.evaluapp.AppExecutors;
import io.gitlab.innom.evaluapp.db.EvaluDb;
import io.gitlab.innom.evaluapp.db.TestDao;
import io.gitlab.innom.evaluapp.domain.Resource;
import io.gitlab.innom.evaluapp.domain.Test;

import static android.arch.lifecycle.Transformations.map;

public class TestRepository {

    private final EvaluDb db;

    private final TestDao testDao;

    private final AppExecutors appExecutors;

    @Inject
    public TestRepository(EvaluDb db, TestDao testDao, AppExecutors appExecutors) {
        this.db = db;
        this.testDao = testDao;
        this.appExecutors = appExecutors;
    }

    public LiveData<Resource<List<Test>>> findAll() {
        return map(this.testDao.findAll(), Resource::success);
    }

    public void insert(Test test) {
        appExecutors.diskIO().execute(() -> this.testDao.insert(test));
    }

}
