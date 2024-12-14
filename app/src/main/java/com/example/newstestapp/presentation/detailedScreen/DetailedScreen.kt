package com.example.newstestapp.presentation.detailedScreen

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.newstestapp.domain.entities.News
import com.example.newstestapp.presentation.getApplicationComponent


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailedScreen(
    news: News,
    onBackPressed: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "",
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { onBackPressed() },
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = ""

                        )
                    }


                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 80.dp, bottom = 90.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = news.title ?: "[Название отсутствует в api]",
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
            )

            Spacer(modifier = Modifier.height(10.dp))
            Log.d("DetailedScreen", "${news.imageUrl}")

        SubcomposeAsyncImage(
            model = news.imageUrl,
            loading = {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
            },
            contentDescription = null,
        )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = news.fullText ?: "[Полный текст отсутствует в api]",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 17.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = news.author ?: "[Автор отсутствует в api]",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 17.sp
            )

            Text(
                text = news.dateOfPublication ?: "[Дата публикации отсутствует в api]",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 17.sp
            )


            Spacer(modifier = Modifier.height(20.dp))
            news.title?.let {
                val component =
                    getApplicationComponent().createDetailedNewsComponent().create(news.title)
                val viewModel: DetailedScreenViewModel =
                    viewModel(factory = component.getViewModelFactory())
                ButtonConnectedWithDb(
                    news = news,
                    viewModel = viewModel
                )
            }


        }
    }

}

@Composable
fun ButtonConnectedWithDb(
    news: News,
    viewModel: DetailedScreenViewModel
) {

    val state = news.title?.let {
        viewModel.isNewsInDb.collectAsState(initial = IsFavouriteState.Loading)
    }



    when (state?.value) {
        IsFavouriteState.Error -> {}
        IsFavouriteState.Loading -> {}
        IsFavouriteState.Favourite -> {
            ButtonWithTextDb(
                color = Color.Magenta.copy(alpha = 0.3f),
                text = "Удалить из избранного",
                onButtonClicked = {
                    viewModel.removeNewsFromDb(news.title)

                }
            )
        }

        IsFavouriteState.NotFavourite -> {
            ButtonWithTextDb(
                color = Color.Cyan.copy(alpha = 0.3f),
                text = "Добавить в избранное",
                onButtonClicked = {
                    viewModel.addNewsToDb(news)
                }
            )
        }

        null -> {}
    }
}

@Composable
fun ButtonWithTextDb(
    color: Color,
    text: String,
    onButtonClicked: () -> Unit,
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonColors(
            color,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = Color.Unspecified
        ),
        onClick = { onButtonClicked() }
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 17.sp
        )
    }
}