package com.aar.app.wordsearch.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.aar.app.wordsearch.ViewModelFactory;
import com.aar.app.wordsearch.data.GameRoundDataSource;
import com.aar.app.wordsearch.data.WordDataSource;
import com.aar.app.wordsearch.gameover.GameOverViewModel;
import com.aar.app.wordsearch.gameplay.GamePlayViewModel;
import com.aar.app.wordsearch.mainmenu.MainMenuViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by abdularis on 18/07/17.
 */

@Module
public class AppModule {

    private Application mApp;

    public AppModule(Application application) {
        mApp = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mApp;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    ViewModelFactory provideViewModelFactory(GameRoundDataSource gameRoundDataSource,
                                             WordDataSource wordDataSource) {
        return new ViewModelFactory(
                new GameOverViewModel(gameRoundDataSource),
                new GamePlayViewModel(gameRoundDataSource, wordDataSource),
                new MainMenuViewModel(gameRoundDataSource)
        );
    }
}
