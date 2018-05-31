package io.github.juanpmarin.evaluapp.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import io.github.juanpmarin.evaluapp.ui.MainActivity;
import io.github.juanpmarin.evaluapp.ui.results.ViewResultActivity;
import io.github.juanpmarin.evaluapp.ui.solve.SolveTestActivity;
import io.github.juanpmarin.evaluapp.ui.tests.EditTestActivity;

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract EditTestActivity contributeEditTestActivity();

    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract SolveTestActivity contributeSolveTestActivity();

    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract ViewResultActivity contributeViewResultActivity();

}
