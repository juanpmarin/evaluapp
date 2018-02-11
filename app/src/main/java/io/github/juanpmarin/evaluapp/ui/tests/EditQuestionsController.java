package io.github.juanpmarin.evaluapp.ui.tests;

import android.content.Context;
import android.support.annotation.NonNull;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.TypedEpoxyController;

import java.util.List;

import io.github.juanpmarin.evaluapp.AddQuestionBindingModel_;
import io.github.juanpmarin.evaluapp.CommonHeaderBindingModel_;
import io.github.juanpmarin.evaluapp.R;
import io.github.juanpmarin.evaluapp.domain.Question;

public class EditQuestionsController extends TypedEpoxyController<List<Question>> {

    @AutoModel
    CommonHeaderBindingModel_ header;

    @AutoModel
    AddQuestionBindingModel_ addQuestion;

    @NonNull
    private AdapterCallbacks adapterCallbacks;

    @NonNull
    private Context context;

    EditQuestionsController(@NonNull AdapterCallbacks adapterCallbacks, @NonNull Context context) {
        this.adapterCallbacks = adapterCallbacks;
        this.context = context;
    }

    @Override
    protected void buildModels(List<Question> questions) {
        header.title(context.getString(R.string.questions));
        addQuestion.onClickListener(v -> this.adapterCallbacks.addQuestion());
    }

    public interface AdapterCallbacks {

        void addQuestion();

    }

}
