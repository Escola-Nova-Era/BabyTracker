package com.escolanovaeratech.babytracker

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.escolanovaeratech.babytracker.navigation.BabyTrackerAppNavGraph
import com.escolanovaeratech.babytracker.navigation.Routes
import com.escolanovaeratech.babytracker.onboarding.local.OnboardingPreferences
import com.escolanovaeratech.babytracker.theme.BabyTrackerTheme
import com.escolanovaeratech.babytracker.ui.components.NavigationBar
import kotlinx.coroutines.flow.first

class MainActivity : AppCompatActivity() {    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BabyTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BabyTrackerApp()
                }
            }
        }
    }
}


@Composable
fun BabyTrackerApp() {
    val context = LocalContext.current
    val onboardingPreferences = remember { OnboardingPreferences(context) }

    var onboardingCompleted by rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(onboardingPreferences) {
        onboardingCompleted = onboardingPreferences.onboardingCompleted.first()
    }

    val startDestination = if (onboardingCompleted) Routes.HOME else Routes.ONBOARDING

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val showBottomBar = currentRoute != Routes.ONBOARDING.route

    Scaffold(
        bottomBar = {
            if(showBottomBar) {
                NavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        BabyTrackerAppNavGraph(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BabyTrackerAppPreview() {
    BabyTrackerApp()
}
