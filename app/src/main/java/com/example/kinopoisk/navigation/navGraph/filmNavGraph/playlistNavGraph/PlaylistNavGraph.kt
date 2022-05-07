package com.example.kinopoisk.navigation.navGraph.filmNavGraph.playlistNavGraph

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenConstants.Argument.ADMIN_FILM_LIST_ITEM_ID
import com.example.core_utils.navigation.filmNavGraph.playlistNavGraph.FilmTopScreenConstants.Argument.FILM_ADMIN_LIST_ARGUMENT
import com.example.core_utils.navigation.filmNavGraph.playlistNavGraph.FilmTopScreenConstants.Argument.FILM_TOP_NAME_ARGUMENT
import com.example.core_utils.navigation.filmNavGraph.playlistNavGraph.FilmTopScreenConstants.Route.FILM_TOP_ROUTE
import com.example.core_utils.navigation.filmNavGraph.playlistNavGraph.PlaylistScreenRoute
import com.example.kinopoisk.screen.filmTop.FilmTopScreen
import com.example.kinopoisk.screen.filmTop.admin.FilmListAddScreen
import com.example.kinopoisk.screen.filmTop.admin.FilmListItemAddScreen
import com.example.kinopoisk.screen.filmTop.admin.FilmListItemScreen

fun NavGraphBuilder.filmTopNavGraph(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope
) {
    navigation(
        startDestination = PlaylistScreenRoute.Playlist.route,
        route = FILM_TOP_ROUTE,
        builder = {
            composable(
                PlaylistScreenRoute.Playlist.route,
                arguments = listOf(
                    navArgument(FILM_TOP_NAME_ARGUMENT){
                        type = NavType.StringType
                    }
                )
            ){
                FilmTopScreen(
                    navController = navController,
                    nameTopViewState = enumValueOf(
                        it.arguments?.getString(FILM_TOP_NAME_ARGUMENT).toString()
                    )
                )
            }

            composable(
                PlaylistScreenRoute.FilmListAdd.route,
                arguments = listOf(
                    navArgument(FILM_ADMIN_LIST_ARGUMENT){
                        nullable = true
                        type = NavType.StringType
                    }
                )
            ){
                val filmList = it.arguments?.getString(FILM_ADMIN_LIST_ARGUMENT)
                FilmListAddScreen(
                    navController = navController,
                    filmListString = filmList?.let { filmList }
                )
            }
            composable(PlaylistScreenRoute.FilmListItemAdd.route){
                FilmListItemAddScreen(
                    navController = navController
                )
            }
            composable(
                PlaylistScreenRoute.AdminListFilmItem.route,
                arguments = listOf(
                    navArgument(ADMIN_FILM_LIST_ITEM_ID){
                        type = NavType.StringType
                    }
                )
            ){
                FilmListItemScreen(
                    navController = navController,
                    lifecycleScope = lifecycleScope,
                    adminListFilmId = it.arguments?.getString(ADMIN_FILM_LIST_ITEM_ID)!!.toInt()
                )
            }
        }
    )
}