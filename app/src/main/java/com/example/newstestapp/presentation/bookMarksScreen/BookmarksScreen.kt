package com.example.newstestapp.presentation.bookMarksScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newstestapp.domain.entities.News
import com.example.newstestapp.presentation.getApplicationComponent
import com.example.newstestapp.presentation.newsScreen.ArticleShortCard

@Composable
fun BookmarksScreen(
    onNewsClickedListener: (News) -> Unit
) {
    val viewModel: BookmarksViewModel =
        viewModel(factory = getApplicationComponent().getViewModuleFactory())

    val state = viewModel.favouriteNewsList.collectAsState(initial = BookmarksScreenState.Initial)
    when (val currentState = state.value) {
        BookmarksScreenState.Error -> {}
        BookmarksScreenState.Initial -> {}
        is BookmarksScreenState.Loaded -> {
            FavouriteNewsContent(
                newsList = currentState.newsList,
                onNewsClickedListener = onNewsClickedListener
            )
        }

        BookmarksScreenState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }

}

@Composable
fun FavouriteNewsContent(
    newsList: List<News>,
    onNewsClickedListener: (News) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(top = 30.dp, bottom = 90.dp, start = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(newsList, key = { it.id }) {

            ArticleShortCard(
                modifier = Modifier,
                news = it,
                onNewsClickedListener = onNewsClickedListener
            )
        }
    }
}
