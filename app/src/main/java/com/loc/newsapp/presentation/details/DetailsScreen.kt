package com.loc.newsapp.presentation.details

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.loc.newsapp.R
import com.loc.newsapp.domain.model.MovieLocal
import com.loc.newsapp.presentation.details.components.EpisodeMovie
import com.loc.newsapp.presentation.details.components.MovieOption

@Composable
fun DetailsScreen(
    movieId: String,
    event: (DetailsEvent) -> Unit,
    navigateToMoviePlayer: (movieUrl: String) -> Unit,
) {

    val viewModel: DetailsViewModel = hiltViewModel()
    val movieDetails by viewModel.movieDetails.collectAsState()
    val context = LocalContext.current
    val isFavorite by viewModel.isFavorite.collectAsState()

    LaunchedEffect(movieId) {
        Log.d("MovieID", "DetailsScreen: $movieId")

        event(DetailsEvent.FetchMovieDetails(movieId))
    }

    Box(
        modifier = Modifier.fillMaxSize().background(color = Color.Black),
        contentAlignment = Alignment.Center
    ) {
        if (movieDetails == null) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = Color.Blue
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black),
            ) {
                item {
                    movieDetails?.let { movie ->
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(movie.movie.poster_url)
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(190.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = movie.movie.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                                .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                                .clip(RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                modifier = Modifier.padding(vertical = 8.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_play),
                                    contentDescription = null,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Play",
                                    color = Color.Black
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                                .background(color = Color.Gray, shape = RoundedCornerShape(8.dp))
                                .clip(RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                modifier = Modifier.padding(vertical = 8.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_download),
                                    contentDescription = null,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Download",
                                    color = Color.White
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = movie.movie.content,
                            fontWeight = FontWeight.W500,
                            fontSize = 16.sp,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Row() {
                            MovieOption(
                                iconResId = if (isFavorite) R.drawable.ic_check else R.drawable.ic_plus,

                                text = "My Playlist",
                                onFavouriteClick = {
                                    Log.d("MovieID", "DetailsScreen: $movieId")
                                    event(
                                        DetailsEvent.AddRemoveFavorite(
                                            MovieLocal(
                                                thumbnail = movie.movie.thumb_url,
                                                title = movie.movie.name,
                                                source = movie.movie.content,
                                                id = movieId,
                                            )
                                        )
                                    )
                                }
                            )

                            MovieOption(
                                iconResId = R.drawable.ic_thumsup,
                                text = "Rate",
                                onFavouriteClick = {
                                    event(
                                        DetailsEvent.AddRemoveFavorite(
                                            MovieLocal(
                                                thumbnail = movie.movie.thumb_url,
                                                title = movie.movie.name,
                                                source = movie.movie.content,
                                                id = movieId,
                                            )
                                        )
                                    )
                                }
                            )
                            MovieOption(
                                iconResId = R.drawable.ic_send,
                                text = "Share",
                                onFavouriteClick = {
                                    shareContent(context, movie.movie.name)
                                }
                            )
                        }

                        if (renderActor(movie.movie.actor).isNotEmpty()) {
                            Text(
                                text = "Diễn viên: ${renderActor(movie.movie.actor)}",
                                fontWeight = FontWeight.W500,
                                fontSize = 12.sp,
                                color = Color.LightGray,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                        }
                        if (renderActor(movie.movie.director).isNotEmpty()) {
                            Text(
                                text = "Đạo diễn: ${renderActor(movie.movie.director)}",
                                fontWeight = FontWeight.W500,
                                fontSize = 12.sp,
                                color = Color.LightGray,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                        }

                        Column(
                            modifier = Modifier.padding(vertical = 12.dp)
                        ) {
                            movie.episodes.first().server_data.forEach { episode ->
                                Box(modifier = Modifier.clickable {
                                    navigateToMoviePlayer(episode.link_m3u8)
                                }) {
                                    EpisodeMovie(
                                        imageUrl = movie.movie.poster_url,
                                        duration = movie.movie.time,
                                        episode = episode.name
                                    )
                                }
                                Spacer(modifier = Modifier.height(12.dp))
                            }
                        }
                    }
                }
            }
        }
    }

}

fun renderActor(array: List<String>): String {
    return array.joinToString(", ")
}


fun shareContent(context: Context, text: String) {
    val intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    context.startActivity(Intent.createChooser(intent, "Share via"))
}





