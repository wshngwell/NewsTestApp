package com.example.newstestapp.presentation.newScreen


import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newstestapp.domain.CategoriesEnum
import com.example.newstestapp.domain.entities.News
import com.example.newstestapp.navigation.NavigationState
import com.example.newstestapp.navigation.Screen
import com.example.newstestapp.presentation.getApplicationComponent

@Composable
fun NewsScreen(
    tabRow: Int,
    navigateToBusinessNewsScreen: (Int) -> Unit,
    onNewsClickedListener: (News) -> Unit
) {

    val component = getApplicationComponent()


    val viewModel: NewsScreenViewModel = viewModel(factory = component.getViewModuleFactory())

    val state = when (tabRow) {
        0 -> viewModel.loadListOfNews(CategoriesEnum.BUSINESS)
            .collectAsState(initial = NewsScreenState.Initial)

        1 -> viewModel.loadListOfNews(CategoriesEnum.HEALTH)
            .collectAsState(initial = NewsScreenState.Initial)

        2 -> viewModel.loadListOfNews(CategoriesEnum.SPORTS)
            .collectAsState(initial = NewsScreenState.Initial)

        else -> {
            viewModel.loadListOfNews(CategoriesEnum.BUSINESS)
                .collectAsState(initial = NewsScreenState.Initial)
        }
    }



    NewsScreenHandlingStates(
        state = state,
        navigateToBusinessNewsScreen = navigateToBusinessNewsScreen,
        tabRow = tabRow,
        onNewsClickedListener = onNewsClickedListener
    )
}


@Composable
fun NewsScreenHandlingStates(
    tabRow: Int,
    state: State<NewsScreenState>,
    navigateToBusinessNewsScreen: (Int) -> Unit,
    onNewsClickedListener: (News) -> Unit
) {
    when (val currentState = state.value) {
        is NewsScreenState.Error -> {
            Log.d("NewsScreen", "Ошибка")
        }

        NewsScreenState.Initial -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.Blue)
            }
        }

        is NewsScreenState.LoadedNews -> {
            NewsScreenContent(
                newsList = currentState.newsList,
                navigateToBusinessNewsScreen = navigateToBusinessNewsScreen,
                tabRow = tabRow,
                onNewsClickedListener = onNewsClickedListener
            )

        }

        NewsScreenState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreenContent(
    tabRow: Int,
    newsList: List<News>,
    navigateToBusinessNewsScreen: (Int) -> Unit,
    onNewsClickedListener: (News) -> Unit
) {
    Column {

        var tabRowState by rememberSaveable {
            mutableStateOf(tabRow)
        }

        val titles = listOf("Business", "Health", "Sports")
        PrimaryTabRow(selectedTabIndex = tabRowState) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = tabRowState == index,
                    onClick = {
                        tabRowState = index
                        when (tabRowState) {
                            0 -> navigateToBusinessNewsScreen(0)
                            1 -> navigateToBusinessNewsScreen(1)
                            2 -> navigateToBusinessNewsScreen(2)
                        }
                    },
                    text = {
                        Text(
                            modifier = Modifier.padding(15.dp),
                            text = title,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    selectedContentColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedContentColor = MaterialTheme.colorScheme.onSecondary

                )
            }
        }
        LazyColumn(
            contentPadding = PaddingValues(
                start = 20.dp, top = 16.dp, end = 20.dp, bottom = 90.dp,

                ),
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
}

@Composable
fun ArticleShortCard(
    modifier: Modifier,
    news: News,
    onNewsClickedListener: (News) -> Unit
) {
    Card(
        elevation = CardDefaults.elevatedCardElevation(5.dp),
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onNewsClickedListener(news)
            }
    ) {


        Column(
            modifier = modifier.padding(10.dp)
        ) {

            news.title?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier


                )
            }
            news.shortDescr?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(bottom = 3.dp)

                )
            }


        }


    }
}

