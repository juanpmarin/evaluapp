package io.github.juanpmarin.evaluapp.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.google.firebase.firestore.FirebaseFirestore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.juanpmarin.evaluapp.db.EvaluDb;
import io.github.juanpmarin.evaluapp.db.QuestionDao;
import io.github.juanpmarin.evaluapp.db.TestDao;

@Module(includes = ViewModelModule.class)
class AppModule {

    @Singleton
    @Provides
    FirebaseFirestore provideFirestore() {
        return FirebaseFirestore.getInstance();
    }

    @Singleton
    @Provides
    EvaluDb provideDb(Application app) {
        return Room.databaseBuilder(app, EvaluDb.class, "evalu.db").build();
    }

    @Singleton
    @Provides
    TestDao provideTestDao(EvaluDb db) {
        return db.testDao();
    }

    @Singleton
    @Provides
    QuestionDao provideQuestionDao(EvaluDb db) {
        return db.questionDao();
    }

}
