package io.github.juanpmarin.evaluapp.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import io.github.juanpmarin.evaluapp.ui.questions.EditQuestionViewModel;
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
    abstract ViewModelProvider.Factory bindViewModelFactory(EvaluViewModelFactory factory);
}
