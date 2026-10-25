package sa.edu.iau.cshj.studyplanner.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import sa.edu.iau.cshj.studyplanner.ui.theme.PlannerHomeRoute
import sa.edu.iau.cshj.studyplanner.ui.theme.TaskDetailRoute

@Composable
fun StudyPlannerApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = PlannerHome,
        modifier = modifier
    ) {
        composable<PlannerHome> {
            PlannerHomeRoute(
                onTaskClick = { title ->
                    navController.navigate(TaskDetail(title))
                }
            )
        }

        composable<TaskDetail> { backStackEntry ->
            val detail = backStackEntry.toRoute<TaskDetail>()

            TaskDetailRoute(
                title = detail.title,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

