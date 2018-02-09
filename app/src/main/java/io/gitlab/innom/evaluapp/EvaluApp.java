package io.gitlab.innom.evaluapp;

import android.app.Activity;
import android.app.Application;

import com.facebook.stetho.Stetho;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.gitlab.innom.evaluapp.di.AppInjector;
import timber.log.Timber;

public class EvaluApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        AppInjector.init(this);
        Stetho.initializeWithDefaults(this);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

}
