package io.gitlab.innom.evaluapp.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import io.gitlab.innom.evaluapp.ui.tests.TestsViewModel;
import io.gitlab.innom.evaluapp.viewmodel.EvaluViewModelFactory;

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(TestsViewModel.class)
    abstract ViewModel bindTestsViewModel(TestsViewModel testsViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(EvaluViewModelFactory factory);
}
