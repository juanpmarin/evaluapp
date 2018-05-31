package io.github.juanpmarin.evaluapp.ui.results;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import io.github.juanpmarin.evaluapp.domain.Result;
import io.github.juanpmarin.evaluapp.repository.ResultRepository;

import static android.arch.lifecycle.Transformations.switchMap;

public class ViewResultViewModel extends ViewModel {

    private ResultRepository resultRepository;
    private MutableLiveData<String> resultId;
    private LiveData<Result> result;

    @Inject
    public ViewResultViewModel(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;

        this.resultId = new MutableLiveData<>();
        this.result = switchMap(resultId, resultRepository::findById);
    }

    public void setResultId(String resultId) {
        this.resultId.setValue(resultId);
    }

    public LiveData<Result> getResult() {
        return result;
    }
}
