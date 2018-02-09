package io.gitlab.innom.evaluapp.ui.tests;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import io.gitlab.innom.evaluapp.domain.Resource;
import io.gitlab.innom.evaluapp.domain.Test;
import io.gitlab.innom.evaluapp.repository.TestRepository;

public class TestsViewModel extends ViewModel {

    private TestRepository testRepository;

    private LiveData<Resource<List<Test>>> tests;

    @Inject
    TestsViewModel(TestRepository testRepository) {
        this.testRepository = testRepository;

        this.tests = testRepository.findAll();
    }

    public LiveData<Resource<List<Test>>> getTests() {
        return tests;
    }

    public void insert() {
        this.testRepository.insert(new Test("Test" + Math.random()));
    }

}
