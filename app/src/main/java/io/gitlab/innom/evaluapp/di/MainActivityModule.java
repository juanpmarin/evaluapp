package io.gitlab.innom.evaluapp.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import io.gitlab.innom.evaluapp.ui.MainActivity;

@Module
public abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract MainActivity contributeMainActivity();

}
