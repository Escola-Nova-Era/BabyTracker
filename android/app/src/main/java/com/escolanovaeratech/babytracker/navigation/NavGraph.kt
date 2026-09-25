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

// Constantes centralizadas para evitar erros de digitação
object Routes {
    const val ONBOARDING = "onboarding"
    const val HOME = "home"
    const val TIMELINE = "timeline"
    const val INSIGHTS = "insights"
    const val PROFILE = "profile"
}
@Composable
fun BabyTrackerAppNavGraph(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier
){
    //val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        // 1. Onboarding (Introdução)
        composable(route = Routes.ONBOARDING) {
            OnboardingScreen(
                onFinish = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.ONBOARDING) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // 2. Home (Quick Actions)
        composable(route = Routes.HOME) {
            HomeScreenUI()
        }
        // 3. Timeline (Histórico de Atividades)
        composable(route = Routes.TIMELINE) {
            TimelineScreen()
        }
        // 4. Insights (Métricas e Gráficos)
        composable(route = Routes.INSIGHTS) {
            InsightsScreen()
        }
        // 5. Profile (Perfil do Bebê e Configurações)
        composable(route = Routes.PROFILE) {
            ProfileScreen()
        }
    }
}