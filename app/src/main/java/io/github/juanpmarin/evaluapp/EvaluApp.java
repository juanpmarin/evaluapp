package io.github.juanpmarin.evaluapp;

import android.app.Activity;
import android.support.multidex.MultiDexApplication;

import com.facebook.stetho.Stetho;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.github.juanpmarin.evaluapp.di.AppInjector;
import timber.log.Timber;

public class EvaluApp extends MultiDexApplication implements HasActivityInjector {

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
