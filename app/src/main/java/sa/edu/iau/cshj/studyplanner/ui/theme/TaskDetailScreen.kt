package sa.edu.iau.cshj.studyplanner.ui.theme

import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class TaskDetailUiState(
    val title: String = ""
)

@Composable
fun TaskDetailScreen(
    uiState: TaskDetailUiState,
    onShareClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = uiState.title,
            style = MaterialTheme.typography.headlineSmall
        )

        Button(onClick = onShareClick) {
            Text("Share")
        }

        OutlinedButton(onClick = onBackClick) {
            Text("Back")
        }
    }
}

@Composable
fun TaskDetailRoute(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    TaskDetailScreen(
        uiState = TaskDetailUiState(title = title),
        onShareClick = {
            val send = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(
                    Intent.EXTRA_TEXT,
                    "Today I'm studying: $title"
                )
            }

            try {
                context.startActivity(
                    Intent.createChooser(send, "Share via")
                )
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    context,
                    "No app available to share",
                    Toast.LENGTH_SHORT
                ).show()
            }
        },
        onBackClick = onBackClick,
        modifier = modifier
    )
}

@Preview(showBackground = true, name = "Lab report")
@Composable
private fun TaskDetailReportPreview() {
    StudyPlannerTheme {
        TaskDetailScreen(
            uiState = TaskDetailUiState("Lab 5 report"),
            onShareClick = {},
            onBackClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Kotlin practice")
@Composable
private fun TaskDetailKotlinPreview() {
    StudyPlannerTheme {
        TaskDetailScreen(
            uiState = TaskDetailUiState("Practice Kotlin"),
            onShareClick = {},
            onBackClick = {}
        )
    }
}

