package io.github.juanpmarin.evaluapp.ui.results;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import io.github.juanpmarin.evaluapp.domain.Result;
import io.github.juanpmarin.evaluapp.repository.ResultRepository;

public class ResultsViewModel extends ViewModel {

    private ResultRepository resultRepository;

    @Inject
    public ResultsViewModel(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public LiveData<List<Result>> findAllResults() {
        return resultRepository.findAllResults();
    }

}
