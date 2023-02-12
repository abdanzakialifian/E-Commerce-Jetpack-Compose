package com.app.ecommere.presentation.splash.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.app.ecommere.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(modifier: Modifier = Modifier, onNavigate: () -> Unit) {

    val onDelayDone by rememberUpdatedState(onNavigate)

    LaunchedEffect(key1 = true) {
        delay(3000L)
        onDelayDone()
    }

    val isPlaying by remember {
        mutableStateOf(true)
    }
    val speed by remember {
        mutableStateOf(1F)
    }
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_animation))
    val progress by animateLottieCompositionAsState(
        composition = composition, isPlaying = isPlaying, speed = speed, restartOnPlay = false
    )

    Box(modifier = modifier.fillMaxSize()) {
        LottieAnimation(
            composition = composition,
            progress = progress,
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.Center)
        )
    }
}