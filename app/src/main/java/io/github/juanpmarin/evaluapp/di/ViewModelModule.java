package io.github.juanpmarin.evaluapp.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import io.github.juanpmarin.evaluapp.ui.questions.EditQuestionViewModel;
import io.github.juanpmarin.evaluapp.ui.results.ResultsViewModel;
import io.github.juanpmarin.evaluapp.ui.results.ViewResultViewModel;
import io.github.juanpmarin.evaluapp.ui.solve.SolveTestViewModel;
import io.github.juanpmarin.evaluapp.ui.tests.EditTestViewModel;
import io.github.juanpmarin.evaluapp.ui.tests.TestsViewModel;
import io.github.juanpmarin.evaluapp.viewmodel.EvaluViewModelFactory;

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TestsViewModel.class)
    abstract ViewModel bindTestsViewModel(TestsViewModel testsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(EditTestViewModel.class)
    abstract ViewModel bindEditTestViewModel(EditTestViewModel editTestViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(EditQuestionViewModel.class)
    abstract ViewModel bindEditQuestionViewModel(EditQuestionViewModel editQuestionViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SolveTestViewModel.class)
    abstract ViewModel bindSolveTestViewModel(SolveTestViewModel solveTestViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ResultsViewModel.class)
    abstract ViewModel bindResultsViewModel(ResultsViewModel resultsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ViewResultViewModel.class)
    abstract ViewModel bindViewResultViewModel(ViewResultViewModel viewResultViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(EvaluViewModelFactory factory);
}
