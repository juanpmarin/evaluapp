package io.github.juanpmarin.evaluapp.ui.results;

import android.content.Context;
import android.support.annotation.NonNull;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.TypedEpoxyController;

import io.github.juanpmarin.evaluapp.AnswerBindingModel_;
import io.github.juanpmarin.evaluapp.CommonHeaderBindingModel_;
import io.github.juanpmarin.evaluapp.R;
import io.github.juanpmarin.evaluapp.domain.Answer;
import io.github.juanpmarin.evaluapp.domain.Result;

public class ViewResultController extends TypedEpoxyController<Result> {

    @AutoModel
    CommonHeaderBindingModel_ header;

    @NonNull
    private Context context;

    public ViewResultController(@NonNull Context context) {
        this.context = context;
    }

    @Override
    protected void buildModels(Result data) {
        header.title(context.getString(R.string.student, data.getStudent()));

        for (int i = 0; i < data.getAnswers().size(); i++) {
            Answer answer = data.getAnswers().get(i);

            new AnswerBindingModel_()
                    .id(i)
                    .answer(answer)
                    .addTo(this);
        }
    }

}
