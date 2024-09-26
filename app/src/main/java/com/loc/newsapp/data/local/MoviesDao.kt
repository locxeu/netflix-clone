package com.loc.newsapp.data.local
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.model.MovieLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(movieLocal: MovieLocal)

    @Delete
    suspend fun delete(movieLocal: MovieLocal)

    @Query("SELECT * FROM MovieLocal")
    fun getMovies(): Flow<List<MovieLocal>>

    @Query("SELECT * FROM MovieLocal WHERE id=:id")
    suspend fun getMovie(id: String): MovieLocal?
}