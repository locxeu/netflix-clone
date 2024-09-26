package com.loc.newsapp.presentation.search

//import com.loc.newsapp.presentation.common.ArticlesList
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.loc.newsapp.R
import com.loc.newsapp.presentation.Dimens
import com.loc.newsapp.presentation.Dimens.MediumPadding1
import com.loc.newsapp.presentation.common.SearchBar
import com.loc.newsapp.presentation.common.ShimmerBox
import com.loc.newsapp.presentation.home.convertToImageUrl
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import com.loc.newsapp.presentation.common.MovieItemRow

@Composable
fun SearchScreen(
    event: (SearchEvent) -> Unit,
    navigateToDetails: (movieId: String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val viewModel: SearchViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    Box(
        modifier = Modifier.background(color = Color.Black)
    ) {
        Column(
            modifier = Modifier
                .padding(
                    top = MediumPadding1,
                    start = 8.dp,
                    end = 8.dp,
                )
                .statusBarsPadding()
                .fillMaxSize()
        ) {
            SearchBar(
                text = state.keyword,
                readOnly = false,
                onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
                onSearch = {
                    event(SearchEvent.SearchMovie)
                    focusManager.clearFocus()
                })
            Spacer(modifier = Modifier.height(MediumPadding1))
            state.movie?.let {
                val movie = it.collectAsLazyPagingItems()
                if (movie.loadState.refresh is LoadState.Loading) {

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator(
                            color = Color.Blue
                        )
                    }
                } else {
                    Log.d("MovieItem", "Search item: ${movie.itemCount}")
                    if (movie.itemCount != 0) {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            contentPadding = PaddingValues(all = Dimens.ExtraSmallPadding2)
                        ) {
                            items(count = movie.itemCount) { index ->
                                val movieItem = movie[index]
                                if (movieItem != null) {
//                                    Row(
//                                        modifier = Modifier
//                                            .fillMaxWidth()
//                                            .clickable {
//                                                Log.d("MovieID", "Home: ${movieItem._id}")
//                                                navigateToDetails(movieItem._id)
//                                            },
//                                        horizontalArrangement = Arrangement.SpaceBetween,
//                                        verticalAlignment = Alignment.CenterVertically
//                                    ) {
//                                        SubcomposeAsyncImage(
//                                            model = ImageRequest.Builder(LocalContext.current)
//                                                .data(convertToImageUrl(movieItem.thumb_url))
//                                                .crossfade(true)
//                                                .build(),
//                                            loading = {
//                                                ShimmerBox()
//                                            },
//                                            contentDescription = null,
//                                            contentScale = ContentScale.Crop,
//                                            modifier = Modifier
//                                                .size(width = 125.dp, height = 80.dp)
//                                                .clip(RoundedCornerShape(12.dp))
//                                        )
//                                        Text(
//                                            text = "${movieItem.name} $index",
//                                            color = Color.White,
//                                            maxLines = 2,
//                                            overflow = TextOverflow.Ellipsis,
//                                            modifier = Modifier
//                                                .weight(1f)
//                                                .padding(horizontal = 8.dp)
//                                        )
//                                        Box(
//                                            modifier = Modifier
//                                                .size(34.dp)
//                                                .clip(CircleShape)
//                                                .background(color = Color.Black.copy(alpha = 0.5f))
//                                                .border(
//                                                    BorderStroke(2.dp, Color.White),
//                                                    shape = CircleShape
//                                                )
//                                        ) {
//                                            Icon(
//                                                painter = painterResource(id = R.drawable.ic_play),
//                                                contentDescription = null,
//                                                modifier = Modifier
//                                                    .size(18.dp)
//                                                    .align(Alignment.Center),
//                                                tint = Color.White
//                                            )
//                                        }
//                                    }
                                    MovieItemRow(
                                        movieItem = movieItem,
                                        navigateToDetails = navigateToDetails
                                    )
                                } else {
                                    Text("Something went wrong", color = Color.White)
                                }
                            }
                            item {
                                if(movie.loadState.append is LoadState.Loading) {
                                    CircularProgressIndicator(
                                        color = Color.Blue,
                                    )
                                }
                            }
                        }
                    } else {
                        Text(
                            text = "Rất tiếc chúng tôi chưa có nội dung này",
                            color = Color.White,
                            fontSize = 24.sp,
                            modifier = Modifier
                                .padding(top = 200.dp)
                                .padding(horizontal = 12.dp)
                                .align(Alignment.CenterHorizontally),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }

}