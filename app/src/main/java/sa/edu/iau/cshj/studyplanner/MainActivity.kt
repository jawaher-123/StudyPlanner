package sa.edu.iau.cshj.studyplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import sa.edu.iau.cshj.studyplanner.navigation.StudyPlannerApp
import sa.edu.iau.cshj.studyplanner.ui.theme.StudyPlannerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            StudyPlannerTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    StudyPlannerApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}