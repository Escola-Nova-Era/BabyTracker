package com.escolanovaeratech.babytracker.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.escolanovaeratech.babytracker.navigation.Home
import com.escolanovaeratech.babytracker.navigation.Insights
import com.escolanovaeratech.babytracker.navigation.Profile
import com.escolanovaeratech.babytracker.navigation.Timeline


@Composable
fun BarraInferior(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    BottomAppBar(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(onClick = { navController.navigate(Home)}) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home"
                )
            }
            IconButton(onClick = {navController.navigate(Insights)}) {
                Icon(
                    imageVector = Icons.Default.BarChart,
                    contentDescription = "Insights"
                )
            }
            IconButton(onClick = {navController.navigate(Timeline)}) {
                Icon(
                    imageVector = Icons.Default.History,
                    contentDescription = "Timeline"
                )
            }
            IconButton(onClick = {navController.navigate(Profile)}) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile"
                )
            }
        }
    }
}

