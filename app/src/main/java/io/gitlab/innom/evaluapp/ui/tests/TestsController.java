package io.gitlab.innom.evaluapp.ui.tests;

import android.content.Context;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.TypedEpoxyController;

import java.util.List;

import io.gitlab.innom.evaluapp.CommonHeaderBindingModel_;
import io.gitlab.innom.evaluapp.R;
import io.gitlab.innom.evaluapp.TestBindingModel_;
import io.gitlab.innom.evaluapp.domain.Resource;
import io.gitlab.innom.evaluapp.domain.Test;

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
