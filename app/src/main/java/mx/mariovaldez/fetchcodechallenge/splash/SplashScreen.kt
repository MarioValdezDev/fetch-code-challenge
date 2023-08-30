package mx.mariovaldez.fetchcodechallenge.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import mx.mariovaldez.fetchcodechallenge.navigation.NavigationActions

@Composable
fun SplashScreen(navController: NavController) {

    val scaleAnimation: Animatable<Float, AnimationVector1D> = remember {
        Animatable(0f)
    }

    AnimationSplashContent(
        scaleAnimation = scaleAnimation,
        durationMillisAnimation = 1500,
        delayScreen = 3000L,
        navigationActions = NavigationActions(navController = navController)
    )

    DesignSplashScreen(
        scaleAnimation = scaleAnimation
    )

}

@Composable
fun AnimationSplashContent(
    scaleAnimation: Animatable<Float, AnimationVector1D>,
    durationMillisAnimation: Int,
    delayScreen: Long,
    navigationActions: NavigationActions,
) {
    LaunchedEffect(key1 = true) {
        scaleAnimation.animateTo(
            targetValue = 1.5f,
            animationSpec = tween(
                durationMillis = durationMillisAnimation,
                easing = {
                    OvershootInterpolator(3F).getInterpolation(it)
                }
            )
        )

        delay(timeMillis = delayScreen)
        navigationActions.navigateToHome()
    }
}

@Composable
fun DesignSplashScreen(
    modifier: Modifier = Modifier,
    scaleAnimation: Animatable<Float, AnimationVector1D>
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Fetch \nChallenge",
                modifier = modifier
                    .scale(scaleAnimation.value),
                color = Color.Black,
                fontSize = 30.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(navController = NavController(LocalContext.current))
}
