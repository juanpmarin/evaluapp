package io.github.juanpmarin.evaluapp.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import io.github.juanpmarin.evaluapp.ui.MainActivity;
import io.github.juanpmarin.evaluapp.ui.tests.EditTestActivity;

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract EditTestActivity contributeEditTestActivity();

}
