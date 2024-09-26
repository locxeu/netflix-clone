package com.loc.newsapp.presentation.common
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.loc.newsapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String,
    lastIcon: Int?=null
    ) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 24.sp,
                color = colorResource(id = R.color.input_background)
            )
        },
        actions = {
            IconButton(onClick = { /* TODO: Handle click */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_cast),
                    contentDescription = null,
                    tint = Color.White
                )
            }
            IconButton(onClick = { /* TODO: Handle click */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    tint = Color.White
                )
            }
            IconButton(onClick = { /* TODO: Handle click */ }) {
                Icon(
                    painter = painterResource(id = lastIcon?: R.drawable.ic_preferences),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = colorResource(id = R.color.text_title),
        ),
    )
}