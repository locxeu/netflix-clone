package com.loc.newsapp.presentation.details.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.loc.newsapp.R

@Composable
fun EpisodeMovie(imageUrl: String,duration:String, episode:String) {
    Column(
        modifier = Modifier.padding(horizontal = 12.dp)
    ) {
        Row {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.35f)
                    .height(80.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                    .clip(RoundedCornerShape(12.dp))
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center // Center the inner Box
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imageUrl)
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
            Spacer(modifier = Modifier.width(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column (
                    modifier = Modifier.align(Alignment.CenterVertically)
                ){
                    Text(
                        "Episode $episode", fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.White,
                    )
                    Text(
                        duration, fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = Color.LightGray,
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.ic_download),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.CenterVertically),
                    tint = Color.White
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            "A charming first encounter quickly turns into something more nefarious when bookstore manager Joe takes a very strong liking to grad student Beck.",
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = Color.LightGray,
            lineHeight = 16.sp
        )
    }
}