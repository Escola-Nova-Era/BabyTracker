package com.escolanovaeratech.babytracker.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.escolanovaeratech.babytracker.home.ui.HomeScreenUI
import com.escolanovaeratech.babytracker.insights.ui.InsightsScreen
import com.escolanovaeratech.babytracker.profile.ui.ProfileScreen
import com.escolanovaeratech.babytracker.timeline.ui.TimelineScreen

// Constantes centralizadas para evitar erros de digitação
object Routes {
    const val HOME = "home"
    const val TIMELINE = "timeline"
    const val INSIGHTS = "insights"
    const val PROFILE = "profile"
}
@Composable
fun BabyTrackerAppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    //val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.HOME,
        modifier = modifier
    ) {
        // 1. Home (Quick Actions)
        composable(route = Routes.HOME) {
            HomeScreenUI()
        }
        // 2. Timeline (Histórico de Atividades)
        composable(route = Routes.TIMELINE) {
            TimelineScreen()
        }
        // 3. Insights (Métricas e Gráficos)
        composable(route = Routes.INSIGHTS) {
            InsightsScreen()
        }
        // 4. Profile (Perfil do Bebê e Configurações)
        composable(route = Routes.PROFILE) {
            ProfileScreen()
        }
    }
}