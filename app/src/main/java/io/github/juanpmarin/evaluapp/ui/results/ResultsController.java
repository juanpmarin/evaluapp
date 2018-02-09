package io.github.juanpmarin.evaluapp.ui.results;

import android.content.Context;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.TypedEpoxyController;

import io.github.juanpmarin.evaluapp.CommonHeaderBindingModel_;
import io.github.juanpmarin.evaluapp.R;

public class ResultsController extends TypedEpoxyController<Integer> {

    @AutoModel
    CommonHeaderBindingModel_ header;

    private Context context;

    public ResultsController(Context context) {
        this.context = context;
    }

    @Override
    protected void buildModels(Integer data) {
        header.title(context.getString(R.string.results));
    }

}
