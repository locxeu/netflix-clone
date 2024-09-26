package com.loc.newsapp.presentation.bookmark

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.loc.newsapp.R
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.presentation.Dimens.MediumPadding1
import com.loc.newsapp.presentation.common.CustomTopAppBar
import com.loc.newsapp.presentation.common.SetStatusBarColor
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.unit.Dp
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.ColorFilter
import com.loc.newsapp.presentation.bookmark.components.NotificationRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigateToDetails: (Article) -> Unit,
) {
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    var skipPartiallyExpanded by rememberSaveable {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(
                color = Color.Black
            )
    ) {
        SetStatusBarColor(Color.Black)
        CustomTopAppBar(
            title = "Netflix của tôi"
        )
        Box(
            modifier = Modifier
                .width(45.dp)
                .height(45.dp)
                .border(BorderStroke(1.dp, Color.White))
                .align(Alignment.CenterHorizontally)
                .clickable {
                    scope.launch {
                        openBottomSheet = !openBottomSheet
                    }
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_my_netflix),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                )
        }
      Spacer(modifier =Modifier.height(4.dp))
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.align(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "5",
                    color = colorResource(id = R.color.input_background),
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow),
                    contentDescription = null,
                    modifier = Modifier
                        .size(12.dp)
                        .rotate(90f),
                    tint = Color.White
                )
            }
        }
        NotificationRow(
            title = "Thông báo",
            color = Color.Red,
            icon = painterResource(id = R.drawable.ic_notification)
        )
        NotificationRow(
            title = "Tệp tải xuống",
            color = Color.Blue,
            icon = painterResource(id = R.drawable.ic_download)
        )

        Spacer(modifier = Modifier.height(12.dp))
//        ArticlesList(articles = state.moviesLiked, onClick = { navigateToDetails(it) })
        Text(
            text = "Tác phẩm bạn đã thích",
            fontSize = 24.sp,
            color = colorResource(id = R.color.input_background),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            items(count = state.moviesLiked.size) {
                val article = state.moviesLiked[it]
                Box(
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(article.thumbnail)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(width = 100.dp, height = 135.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
            }
        }
    }
    if (openBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { openBottomSheet = false },
            sheetState = bottomSheetState,
            containerColor = Color(0xFF2d2d2d),
        ) {
            //Sheet content
            Box(
                modifier = Modifier
                    .background(Color(0xFF2d2d2d))
                    .height(230.dp)
                    .padding(horizontal = 8.dp)
            ) {
                // Sheet content
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 4.dp)
                            .padding(bottom = 18.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Chuyển đổi tài khoản",
                            fontSize = 24.sp,
                            color = colorResource(id = R.color.input_background),
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_close),
                            contentDescription = null,
                            modifier = Modifier
                                .size(18.dp),
                            tint = Color.White
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp).padding(bottom = 18.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        for ((index,imageResId) in imageList.withIndex()) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(id = imageResId),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(60.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                )
                                Text(
                                    text = index.toString(),
                                    fontSize = 16.sp,
                                    color = colorResource(id = R.color.input_background),
                                    modifier = Modifier.padding(vertical = 4.dp)
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_lock),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(16.dp),
                                    tint = Color.White
                                )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_pencil),
                            contentDescription = null,
                            modifier = Modifier
                                .size(18.dp),
                            tint = Color.White
                        )
                        Text(
                            text = "Quản lý hồ sơ",
                            fontSize = 16.sp,
                            color = colorResource(id = R.color.input_background),
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )

                    }
                }
            }
        }
    }
}

val imageList = listOf(
    R.drawable.ic_netflix_profile_1,
    R.drawable.ic_netflix_profile_2,
    R.drawable.ic_netflix_profile_3,
    R.drawable.ic_netflix_profile_4,
    R.drawable.ic_my_netflix
)