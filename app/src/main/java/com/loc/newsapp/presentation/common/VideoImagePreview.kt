package com.loc.newsapp.presentation.common
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.media.MediaMetadataRetriever
import android.util.Log
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import coil.ImageLoader
import coil.decode.VideoFrameDecoder
import coil.load
import coil.request.ImageRequest
import coil.request.SuccessResult

@Composable
fun VideoThumbnail(
    modifier: Modifier = Modifier,
    url: String = "",
) {
    val context = LocalContext.current
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }

    LaunchedEffect(url) {
        val imageLoader = ImageLoader.Builder(context)
            .components { add(VideoFrameDecoder.Factory()) }
            .build()

        val request = ImageRequest.Builder(context)
            .data(url)
            .build()

        val result = (imageLoader.execute(request) as? SuccessResult)?.drawable
        bitmap = (result as? BitmapDrawable)?.bitmap
    }

    bitmap?.let {
        Image(
            bitmap = it.asImageBitmap(),
            contentDescription = "Video Thumbnail",
            modifier = modifier
        )
    }
}
