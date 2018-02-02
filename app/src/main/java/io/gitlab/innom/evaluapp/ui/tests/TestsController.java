package io.gitlab.innom.evaluapp.ui.tests;

import android.content.Context;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.TypedEpoxyController;

import io.gitlab.innom.evaluapp.CommonHeaderBindingModel_;
import io.gitlab.innom.evaluapp.R;
import io.gitlab.innom.evaluapp.domain.Test;

public class TestsController extends TypedEpoxyController<Test> {

    @AutoModel
    CommonHeaderBindingModel_ header;

    private Context context;

    TestsController(Context context) {
        this.context = context;
    }

    @Override
    protected void buildModels(Test data) {
        header.title(context.getString(R.string.my_tests));

        new CommonHeaderBindingModel_().id("header").title("oeee").addTo(this);
    }

}
