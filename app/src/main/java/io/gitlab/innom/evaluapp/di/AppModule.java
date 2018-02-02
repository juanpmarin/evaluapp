package io.gitlab.innom.evaluapp.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.gitlab.innom.evaluapp.db.EvaluDb;

@Module(includes = ViewModelModule.class)
class AppModule {

    @Singleton
    @Provides
    EvaluDb provideDb(Application app) {
        return Room.databaseBuilder(app, EvaluDb.class, "evalu.db").build();
    }

}
