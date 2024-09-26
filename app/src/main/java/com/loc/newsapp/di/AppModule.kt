package com.loc.newsapp.di

import android.app.Application
import androidx.room.Room
import com.loc.newsapp.data.local.MoviesDao
import com.loc.newsapp.data.local.MoviesDatabase
import com.loc.newsapp.data.local.NewsDao
import com.loc.newsapp.data.local.NewsDatabase
import com.loc.newsapp.data.local.NewsTypeConvertor
import com.loc.newsapp.data.manger.LocalUserMangerImpl
import com.loc.newsapp.data.remote.MovieApi
import com.loc.newsapp.data.remote.NewsApi
import com.loc.newsapp.data.repository.HomeRepositoryImpl
import com.loc.newsapp.data.repository.NewsRepositoryImpl
import com.loc.newsapp.data.repository.ProfileRepositoryImpl
import com.loc.newsapp.domain.manger.LocalUserManger
import com.loc.newsapp.domain.repository.HomeRepository
import com.loc.newsapp.domain.repository.NewsRepository
import com.loc.newsapp.domain.repository.ProfileRepository
import com.loc.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.loc.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.loc.newsapp.domain.usecases.app_entry.SaveAppEntry
import com.loc.newsapp.domain.usecases.movie.GetHome
import com.loc.newsapp.domain.usecases.movie.GetMovieDetail
import com.loc.newsapp.domain.usecases.movie.MoviesUseCase
import com.loc.newsapp.domain.usecases.movie.SearchMovie
import com.loc.newsapp.domain.usecases.news.DeleteArticle
import com.loc.newsapp.domain.usecases.news.GetNews
import com.loc.newsapp.domain.usecases.news.NewsUseCases
import com.loc.newsapp.domain.usecases.news.SearchNews
import com.loc.newsapp.domain.usecases.news.SelectArticle
import com.loc.newsapp.domain.usecases.news.SelectArticles
import com.loc.newsapp.domain.usecases.news.UpsertArticle
import com.loc.newsapp.domain.usecases.profile.GetDetailFavouriteMovieUseCases
import com.loc.newsapp.domain.usecases.profile.GetListFavouriteMovieUseCases
import com.loc.newsapp.domain.usecases.profile.ProfileUseCases
import com.loc.newsapp.domain.usecases.profile.UnFavouriteMovieUseCases
import com.loc.newsapp.domain.usecases.profile.UpsertFavouriteMovieUseCases
import com.loc.newsapp.util.Constants.BASE_URL
import com.loc.newsapp.util.Constants.MOVIE_API_BASE_URL
import com.loc.newsapp.util.Constants.NEWS_DATABASE_NAME
import com.loc.newsapp.util.Constants.MOIVES_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application
    ): LocalUserManger = LocalUserMangerImpl(application)


    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManger
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )


    val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieApi(): MovieApi {
        return Retrofit.Builder()
            .baseUrl(MOVIE_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ): NewsRepository = NewsRepositoryImpl(newsApi,newsDao)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideProfileRepository(
        moviesDao: MoviesDao
    ): ProfileRepository = ProfileRepositoryImpl(moviesDao)

    @Provides
    @Singleton
    fun provideProfileUseCases(
        profileRepository: ProfileRepository,
    ): ProfileUseCases {
        return ProfileUseCases(
         getListFavouriteMovieUseCases = GetListFavouriteMovieUseCases(profileRepository),
            getMovieDetailUseCases = GetDetailFavouriteMovieUseCases(profileRepository),
            unFavouriteMovieUseCases = UnFavouriteMovieUseCases(profileRepository),
            upsertFavouriteMovieUseCases = UpsertFavouriteMovieUseCases(profileRepository)
        )
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieApi: MovieApi
        // Add other dependencies if needed
    ): HomeRepository = HomeRepositoryImpl(movieApi)

    @Provides
    @Singleton
    fun provideMovieUseCases(
        movieRepository: HomeRepository
    ): MoviesUseCase {
        return MoviesUseCase(
            getHome = GetHome(movieRepository),
            getMovieDetails = GetMovieDetail(movieRepository),
            searchMovie = SearchMovie(movieRepository)
        )
    }


    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao


    @Provides
    @Singleton
    fun provideMoviesDatabase(
        application: Application
    ): MoviesDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = MoviesDatabase::class.java,
            name = MOIVES_DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideMoviesDao(
        moviesDatabase: MoviesDatabase
    ): MoviesDao = moviesDatabase.moiveDao

}