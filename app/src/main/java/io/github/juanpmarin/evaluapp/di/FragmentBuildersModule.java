package io.github.juanpmarin.evaluapp.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import io.github.juanpmarin.evaluapp.ui.tests.TestsFragment;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract TestsFragment contributeRepoFragment();

}
