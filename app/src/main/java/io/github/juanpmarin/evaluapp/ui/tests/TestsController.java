package io.github.juanpmarin.evaluapp.ui.tests;

import android.content.Context;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.TypedEpoxyController;

import java.util.List;

import io.github.juanpmarin.evaluapp.CommonHeaderBindingModel_;
import io.github.juanpmarin.evaluapp.R;
import io.github.juanpmarin.evaluapp.TestBindingModel_;
import io.github.juanpmarin.evaluapp.domain.Resource;
import io.github.juanpmarin.evaluapp.domain.Test;

public class TestsController extends TypedEpoxyController<Resource<List<Test>>> {

    @AutoModel
    CommonHeaderBindingModel_ header;

    private Context context;

    TestsController(Context context) {
        this.context = context;
    }

    @Override
    protected void buildModels(Resource<List<Test>> testsResource) {
        header.title(context.getString(R.string.my_tests));

        if (testsResource.data != null) {
            for (Test test : testsResource.data) {
                new TestBindingModel_()
                        .id(test.getId())
                        .test(test)
                        .addTo(this);
            }
        }
    }

}
