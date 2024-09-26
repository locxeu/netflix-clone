package com.loc.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.loc.newsapp.domain.model.MovieLocal


@Database(entities = [MovieLocal::class], version = 2)

abstract class MoviesDatabase:RoomDatabase() {
    abstract val moiveDao: MoviesDao
}