package mx.mariovaldez.fetchcodechallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import mx.mariovaldez.fetchcodechallenge.navigation.NavGraph
import mx.mariovaldez.fetchcodechallenge.ui.theme.FetchCodeChallengeTheme
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree()
            )
        }
        setContent {
            FetchCodeChallengeTheme {
                NavGraph(context = this)
            }
        }
    }
}
