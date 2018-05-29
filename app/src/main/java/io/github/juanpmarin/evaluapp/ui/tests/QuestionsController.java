package io.github.juanpmarin.evaluapp.ui.tests;

import android.content.Context;
import android.support.annotation.NonNull;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.TypedEpoxyController;

import java.util.List;

import io.github.juanpmarin.evaluapp.CommonHeaderBindingModel_;
import io.github.juanpmarin.evaluapp.QuestionBindingModel_;
import io.github.juanpmarin.evaluapp.R;
import io.github.juanpmarin.evaluapp.domain.QuestionWithAnswer;

public class QuestionsController extends TypedEpoxyController<List<QuestionWithAnswer>> {

    @AutoModel
    CommonHeaderBindingModel_ header;

    @NonNull
    private AdapterCallbacks adapterCallbacks;

    @NonNull
    private Context context;

    QuestionsController(@NonNull AdapterCallbacks adapterCallbacks, @NonNull Context context) {
        this.adapterCallbacks = adapterCallbacks;
        this.context = context;
    }

    @Override
    protected void buildModels(List<QuestionWithAnswer> questions) {
        header.title(context.getString(R.string.questions))
                .addIf(!questions.isEmpty(), this);

        for (QuestionWithAnswer questionWithAnswer : questions) {
            new QuestionBindingModel_()
                    .id(questionWithAnswer.getId())
                    .bundle(questionWithAnswer)
                    .addTo(this);
        }
    }

    public interface AdapterCallbacks {


    }

}
