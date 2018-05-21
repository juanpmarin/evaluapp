package io.github.juanpmarin.evaluapp.ui.tests;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.text.TextUtils;

import javax.inject.Inject;

import io.github.juanpmarin.evaluapp.R;
import io.github.juanpmarin.evaluapp.domain.Test;
import io.github.juanpmarin.evaluapp.repository.TestRepository;

public class EditTestViewModel extends ViewModel {

    private TestRepository testRepository;

    private MutableLiveData<String> testId;

    private MutableLiveData<Integer> error;

    private LiveData<Test> test;

    @Inject
    EditTestViewModel(TestRepository testRepository) {
        this.testRepository = testRepository;

        this.testId = new MutableLiveData<>();
        this.error = new MutableLiveData<>();

        this.test = Transformations.switchMap(testId, testRepository::findById);
    }

    void setUp(String testId) {
        if (testId == null) {
            Test test = new Test();
            testId = test.getId();

            testRepository.insert(test);
        }

        this.testId.setValue(testId);
    }

    public LiveData<Test> getTest() {
        return test;
    }

    MutableLiveData<Integer> getError() {
        return error;
    }

    boolean saveTest(String name) {
        Test test = this.test.getValue();

        name = name.trim();

        if (TextUtils.isEmpty(name)) {
            error.setValue(R.string.error_must_insert_title);
            return false;
        }

        test.setName(name);

        testRepository.update(test);

        return true;
    }

}
