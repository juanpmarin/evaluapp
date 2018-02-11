package io.github.juanpmarin.evaluapp.ui.tests;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.text.TextUtils;

import javax.inject.Inject;

import io.github.juanpmarin.evaluapp.R;
import io.github.juanpmarin.evaluapp.domain.Test;
import io.github.juanpmarin.evaluapp.repository.TestRepository;

public class EditTestViewModel extends ViewModel {

    private TestRepository testRepository;

    private MutableLiveData<Integer> error;

    @Inject
    EditTestViewModel(TestRepository testRepository) {
        this.testRepository = testRepository;

        this.error = new MutableLiveData<>();
    }

    MutableLiveData<Integer> getError() {
        return error;
    }

    boolean saveTest(String title) {
        title = title.trim();

        if (TextUtils.isEmpty(title)) {
            error.setValue(R.string.error_must_insert_title);
            return false;
        }

        testRepository.insert(new Test(title));

        return true;
    }


}
