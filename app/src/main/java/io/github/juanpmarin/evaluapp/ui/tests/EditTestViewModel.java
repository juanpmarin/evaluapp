package io.github.juanpmarin.evaluapp.ui.tests;

import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import io.github.juanpmarin.evaluapp.domain.Test;
import io.github.juanpmarin.evaluapp.repository.TestRepository;

public class EditTestViewModel extends ViewModel {

    private TestRepository testRepository;

    @Inject
    public EditTestViewModel(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public void saveTest(String title) {
        testRepository.insert(new Test(title));
    }

}
