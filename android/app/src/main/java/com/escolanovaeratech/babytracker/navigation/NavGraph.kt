package com.escolanovaeratech.babytracker.navigation

import TimelineScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.escolanovaeratech.babytracker.home.ui.HomeScreenUI
import com.escolanovaeratech.babytracker.profile.ui.ProfileScreen
import com.escolanovaeratech.babytracker.ui.screens.Screen3
import kotlinx.serialization.Serializable


@Serializable
object Home
@Serializable
object Insights
@Serializable
object Timeline
@Serializable
object Profile
@Composable
fun BabyTrackerAppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    //val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Home,
        modifier = modifier
    ) {

        composable<Home> { HomeScreenUI() }

        composable<Insights> { Screen3() }

        composable<Timeline> { TimelineScreen() }

        composable<Profile> { ProfileScreen() }
    }
}