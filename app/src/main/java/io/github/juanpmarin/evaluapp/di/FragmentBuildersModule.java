package io.github.juanpmarin.evaluapp.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import io.github.juanpmarin.evaluapp.ui.questions.EditQuestionFragment;
import io.github.juanpmarin.evaluapp.ui.tests.TestsFragment;

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract TestsFragment contributeTestsFragment();

    @ContributesAndroidInjector
    abstract EditQuestionFragment contributeEditQuestionFragment();

}
