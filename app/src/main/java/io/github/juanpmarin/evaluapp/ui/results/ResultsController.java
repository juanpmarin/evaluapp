package io.github.juanpmarin.evaluapp.ui.results;

import android.content.Context;
import android.support.annotation.NonNull;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.TypedEpoxyController;

import java.util.List;

import io.github.juanpmarin.evaluapp.CommonHeaderBindingModel_;
import io.github.juanpmarin.evaluapp.R;
import io.github.juanpmarin.evaluapp.ResultBindingModel_;
import io.github.juanpmarin.evaluapp.domain.Result;

public class ResultsController extends TypedEpoxyController<List<Result>> {

    @AutoModel
    CommonHeaderBindingModel_ header;

    @NonNull
    private Context context;

    @NonNull
    private AdapterCallbacks callbacks;

    ResultsController(@NonNull Context context, @NonNull AdapterCallbacks callbacks) {
        this.context = context;
        this.callbacks = callbacks;
    }

    @Override
    protected void buildModels(List<Result> results) {
        header.title(context.getString(R.string.results));

        for (Result result : results) {
            new ResultBindingModel_()
                    .id(result.getId())
                    .resultData(result)
                    .onClickListener(v -> callbacks.resultClicked(result.getId()))
                    .addTo(this);
        }
    }

    public interface AdapterCallbacks {

        void resultClicked(String id);

    }

}
