

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
import sa.edu.iau.cshj.studyplanner.ui.theme.StudyPlannerTheme

data class PlannerUiState(
    val greeting: String = "Hello, Jawaher!",
    val totalTasks: Int = 7,
    val doneTasks: Int = 0
)

@Composable
fun PlannerHomeScreen(
    uiState: PlannerUiState,
    onMarkTaskDone: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(uiState.greeting, style = MaterialTheme.typography.headlineSmall)
        Text("${uiState.doneTasks} of ${uiState.totalTasks} done")
        Button(onClick = onMarkTaskDone, enabled = uiState.doneTasks < uiState.totalTasks) {
            Text("Mark one task done")
        }
    }
}

@Composable
fun PlannerHomeRoute(modifier: Modifier = Modifier) {
    var uiState by remember { mutableStateOf(PlannerUiState()) }
    PlannerHomeScreen(
        uiState = uiState,
        onMarkTaskDone = {
            if (uiState.doneTasks < uiState.totalTasks) {
                uiState = uiState.copy(
                    doneTasks = uiState.doneTasks + 1
                )
            }
        },
        modifier = modifier
    )
}

@Preview(showBackground = true, name = "Nothing done yet")
@Composable
private fun PlannerHomeInitialPreview() {
    StudyPlannerTheme {
        PlannerHomeScreen(uiState = PlannerUiState("Hello, Jawaher!", 7, 0), onMarkTaskDone = {})
    }
}

@Preview(showBackground = true, name = "Everything done")
@Composable
private fun PlannerHomeCompletedPreview() {
    StudyPlannerTheme {
        PlannerHomeScreen(uiState = PlannerUiState("Hello, Jawaher!", 7, 7), onMarkTaskDone = {})
    }
}
