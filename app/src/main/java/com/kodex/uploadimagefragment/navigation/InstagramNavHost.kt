package com.kodex.uploadimagefragment.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kodex.uploadimagefragment.screens.AddScreen
import com.kodex.uploadimagefragment.screens.MainScreen
import com.kodex.uploadimagefragment.util.Constants

sealed class NavRoute(val route: String)
object Start: NavRoute(Constants.Screens.START_SCREEN)
object Main: NavRoute(Constants.Screens.MAIN_SCREEN)
object Add: NavRoute(Constants.Screens.ADD_SCREEN)
object Note: NavRoute(Constants.Screens.NOTE_SCREEN)

@Composable
fun InstagramNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "add_Screen"){
      //  composable("start_Screen"){ StartScreen(navController = navController) }
        composable("main_Screen"){ MainScreen(navController = navController) }
        composable("add_Screen"){ AddScreen(navController = navController)}
       /* composable("note_Screen" + "/{${Constants.Keys.ID}}"){ backStackEntry ->
            NoteScreen(navController = navController, noteId =  backStackEntry
                    .arguments?.getString(Constants.Keys
                        .ID))}
    */
    }
}


