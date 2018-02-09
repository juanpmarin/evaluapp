package io.gitlab.innom.evaluapp.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import io.gitlab.innom.evaluapp.ui.tests.TestsFragment;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract TestsFragment contributeRepoFragment();

}
