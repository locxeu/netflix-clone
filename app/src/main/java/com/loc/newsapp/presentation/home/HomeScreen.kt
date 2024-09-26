package com.loc.newsapp.presentation.home

//import com.loc.newsapp.presentation.common.ArticleCardShimmerEffect
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.paging.compose.LazyPagingItems
import com.loc.newsapp.R
import com.loc.newsapp.data.remote.dto.MovieInfo
import com.loc.newsapp.presentation.Dimens
import com.loc.newsapp.presentation.Dimens.MediumPadding1
import com.loc.newsapp.presentation.common.CustomTopAppBar
import com.loc.newsapp.presentation.common.MovieItemRow
import kotlinx.coroutines.delay
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.loc.newsapp.presentation.common.SetStatusBarColor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    state: HomeState,
    movie: LazyPagingItems<MovieInfo>,
    navigateToSearch: () -> Unit,
    navigateToDetails: (movieId: String) -> Unit,
    event: (HomeEvent) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .statusBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            SetStatusBarColor(Color.Black)
        }
        item {
            CustomTopAppBar(
                title = "Dành cho bạn13",
                lastIcon = R.drawable.ic_download
            )
        }
        item {
            Row {
                AssistChip(
                    onClick = {},
                    label = { Text("Phim thịnh hành", color = Color.White) },
                )
                Spacer(modifier = Modifier.width(8.dp))
                AssistChip(
                    onClick = {},
                    label = { Text("Phim", color = Color.White) },
                )
                Spacer(modifier = Modifier.width(8.dp))
                AssistChip(
                    onClick = {},
                    label = { Text("Thể loại", color = Color.White) },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow),
                            contentDescription = null,
                            modifier = Modifier
                                .size(12.dp)
                                .rotate(90f),
                            tint = Color.White
                        )
                    },
                )
            }
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(503.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                    .clip(RoundedCornerShape(12.dp))
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center // Center the inner Box
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://img.ophim.live/uploads/movies/xoay-so-phan-3-thumb.jpg")
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(color = Color.Black.copy(alpha = 0.5f))
                            .border(BorderStroke(1.dp, Color.White), shape = CircleShape)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_play),
                            contentDescription = null,
                            modifier = Modifier
                                .size(18.dp)
                                .align(Alignment.Center),
                            tint = Color.White
                        )
                    }
                }
            }
        }
        items(count = movie.itemCount) {
            movie[it]?.let {
                MovieItemRow(
                    movieItem = it,
                    isShowIcon = false,
                    navigateToDetails = navigateToDetails
                )
            }
        }
    }
}


fun convertToImageUrl(fileName: String): String {
    val baseUrl = "https://img.ophim.live/uploads/movies/"
    return "$baseUrl$fileName"
}
