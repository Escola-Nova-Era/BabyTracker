package com.escolanovaeratech.babytracker.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.escolanovaeratech.babytracker.timeline.presentation.TimelineScreen
import com.escolanovaeratech.babytracker.ui.screens.Screen1
import com.escolanovaeratech.babytracker.ui.screens.Screen3
import com.escolanovaeratech.babytracker.ui.screens.Screen4

@Composable
fun BabyTrackerAppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    //val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Screen1",
        modifier = modifier
    ) {

        composable(route = "Screen1") { Screen1() }

        composable(route = "Timeline") { TimelineScreen() }

        composable(route = "Screen3") { Screen3() }

        composable(route = "Screen4") { Screen4() }
    }
}