package com.loc.newsapp.presentation.common


//import com.loc.newsapp.presentation.common.ArticlesList
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.loc.newsapp.R
import com.loc.newsapp.data.remote.dto.MovieInfo
import com.loc.newsapp.presentation.home.convertToImageUrl

@Composable
fun MovieItemRow(
    movieItem: MovieInfo,
    isShowIcon: Boolean = true,
    navigateToDetails: (movieId: String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                Log.d("MovieID", "Home: ${movieItem._id}")
                navigateToDetails(movieItem._id)
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(convertToImageUrl(movieItem.thumb_url))
                .crossfade(true)
                .build(),
            loading = {
                ShimmerBox()
            },
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width = 125.dp, height = 80.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Text(
            text = movieItem.name,
            color = Color.White,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        )
        if(isShowIcon){
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(color = Color.Black.copy(alpha = 0.5f))
                    .border(
                        BorderStroke(2.dp, Color.White),
                        shape = CircleShape
                    )
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