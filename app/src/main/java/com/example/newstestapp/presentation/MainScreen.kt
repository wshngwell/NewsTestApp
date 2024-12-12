package com.example.newstestapp.presentation

import android.annotation.SuppressLint
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.newstestapp.domain.CategoriesEnum
import com.example.newstestapp.navigation.AppNavGraph
import com.example.newstestapp.navigation.Favourite
import com.example.newstestapp.navigation.Home
import com.example.newstestapp.navigation.Screen
import com.example.newstestapp.navigation.rememberNavigationState
import com.example.newstestapp.presentation.detailedScreen.DetailedScreen
import com.example.newstestapp.presentation.newScreen.NewsScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {

    val navigationState = rememberNavigationState()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()

                val items = listOf(Home, Favourite)

                items.forEach { navigationItem ->

                    val selected = navBackStackEntry?.destination?.hierarchy?.any {
                        it.route == navigationItem.screen.route
                    } ?: false

                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            if (!selected) {
                                navigationState.navigateTo(navigationItem.screen.route)
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = navigationItem.icon,
                                contentDescription = ""
                            )
                        },
                        label = { Text(text = stringResource(id = navigationItem.resStringId)) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                            unselectedTextColor = MaterialTheme.colorScheme.onSecondary,
                        )

                    )
                }
            }
        }
    ) {
        var tabRow by rememberSaveable {
            mutableStateOf(0)
        }

        AppNavGraph(
            navHostController = navigationState.navHostController,
            openBookMarksScreen = {

            },
            openNewsScreen = {
                NewsScreen(
                    tabRow = tabRow,
                    navigateToBusinessNewsScreen = {
                        navigationState.navigateTo(Screen.NewsScreen.route)
                        tabRow = it
                    },
                    onNewsClickedListener = {
                        navigationState.navigateToDetailedScreen(it)
                    }
                )
            },
            openDetailedScreen = {
                DetailedScreen(it)
            }
        )

    }
}