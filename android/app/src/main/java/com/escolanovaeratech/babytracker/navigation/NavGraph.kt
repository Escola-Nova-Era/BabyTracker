package com.escolanovaeratech.babytracker.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.escolanovaeratech.babytracker.home.ui.HomeScreenUI
import com.escolanovaeratech.babytracker.insights.ui.InsightsScreen
import com.escolanovaeratech.babytracker.onboarding.ui.OnboardingScreen
import com.escolanovaeratech.babytracker.profile.ui.ProfileScreen
import com.escolanovaeratech.babytracker.timeline.ui.TimelineScreen

enum class Routes(val route: String) {
    ONBOARDING("onboarding"),
    HOME("home"),
    TIMELINE("timeline"),
    INSIGHTS("insights"),
    PROFILE("profile")
}
@Composable
fun BabyTrackerAppNavGraph(
    navController: NavHostController,
    startDestination: Routes,
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = modifier
    ) {
        // 1. Onboarding (Introdução)
        composable(route = Routes.ONBOARDING.route) {
            OnboardingScreen(
                onFinish = {
                    navController.navigate(Routes.HOME.route) {
                        popUpTo(Routes.ONBOARDING.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // 2. Home (Quick Actions)
        composable(route = Routes.HOME.route) {
            HomeScreenUI()
        }
        // 3. Timeline (Histórico de Atividades)
        composable(route = Routes.TIMELINE.route) {
            TimelineScreen()
        }
        // 4. Insights (Métricas e Gráficos)
        composable(route = Routes.INSIGHTS.route) {
            InsightsScreen()
        }
        // 5. Profile (Perfil do Bebê e Configurações)
        composable(route = Routes.PROFILE.route) {
            ProfileScreen()
        }
    }
}
