package sa.edu.iau.cshj.studyplanner.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class PlannerUiState(
    val greeting: String = "Hello, Jawaher!",
    val totalTasks: Int = 0,
    val doneTasks: Int = 0,
    val tasks: List<String> = emptyList()
)

@Composable
fun PlannerHomeScreen(
    uiState: PlannerUiState,
    onMarkTaskDone: () -> Unit,
    onTaskClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = uiState.greeting,
            style = MaterialTheme.typography.headlineSmall
        )

        Text("${uiState.doneTasks} of ${uiState.totalTasks} done")

        Button(
            onClick = onMarkTaskDone,
            enabled = uiState.doneTasks < uiState.totalTasks
        ) {
            Text("Mark one task done")
        }

        uiState.tasks.forEach { title ->
            Button(onClick = { onTaskClick(title) }) {
                Text(title)
            }
        }
    }
}

@Composable
fun PlannerHomeRoute(
    onTaskClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var uiState by remember {
        mutableStateOf(
            PlannerUiState(
                totalTasks = 3,
                tasks = listOf(
                    "Lab 5 report",
                    "Review CSC 402 lecture",
                    "Practice Kotlin"
                )
            )
        )
    }

    PlannerHomeScreen(
        uiState = uiState,
        onMarkTaskDone = {
            if (uiState.doneTasks < uiState.totalTasks) {
                uiState = uiState.copy(
                    doneTasks = uiState.doneTasks + 1
                )
            }
        },
        onTaskClick = onTaskClick,
        modifier = modifier
    )
}

@Preview(showBackground = true, name = "Nothing done yet")
@Composable
private fun PlannerHomeInitialPreview() {
    StudyPlannerTheme {
        PlannerHomeScreen(
            uiState = PlannerUiState(
                totalTasks = 3,
                tasks = listOf(
                    "Lab 5 report",
                    "Review CSC 402 lecture",
                    "Practice Kotlin"
                )
            ),
            onMarkTaskDone = {},
            onTaskClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Everything done")
@Composable
private fun PlannerHomeCompletedPreview() {
    StudyPlannerTheme {
        PlannerHomeScreen(
            uiState = PlannerUiState(
                totalTasks = 3,
                doneTasks = 3,
                tasks = listOf(
                    "Lab 5 report",
                    "Review CSC 402 lecture",
                    "Practice Kotlin"
                )
            ),
            onMarkTaskDone = {},
            onTaskClick = {}
        )
    }
}