package com.example.weathercompose.uitl

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.weathercompose.R
import com.example.weathercompose.data.WeatherIcon
import com.example.weathercompose.data.season
import com.example.weathercompose.data.season.Autumn
import com.example.weathercompose.data.season.Night
import com.example.weathercompose.data.season.Spring
import com.example.weathercompose.data.season.Summer
import com.example.weathercompose.data.season.Winter
import com.example.weathercompose.ui.theme.Pink80
import com.example.weathercompose.ui.theme.Purple40

fun toTime(date: String): String {
    val item = date.dropLast(6).replace("T", " ")
    return item
}

@DrawableRes
fun ImageReslut(): Int {
    val son: season = season.entries.random()
    return when (son) {
        Spring -> R.drawable.cloudy_bg
        Summer -> R.drawable.sunny_bg
        Autumn -> R.drawable.haze_bg
        Winter -> R.drawable.snow_bg
        Night -> R.drawable.night_bg
    }
}

@DrawableRes
fun logCard(coil: String): Int {
    for (i in WeatherIcon.entries) {
        if (i.int == coil.toInt()) {
            return when (i) {
                WeatherIcon.sun -> R.drawable.sun_bg
                WeatherIcon.partly_cloudy -> R.drawable.duyun
                WeatherIcon.partly_cloudys -> R.drawable.duyun
                WeatherIcon.Suns -> R.drawable.sunny_bg
                WeatherIcon.rain_and_snow_weather -> R.drawable.rain_bg
                WeatherIcon.fog -> R.drawable.duyun
                WeatherIcon.hot -> R.drawable.sun_bg
                WeatherIcon.cold -> R.drawable.duyun
                WeatherIcon.haze -> R.drawable.haze_bg
                WeatherIcon.mist -> R.drawable.haze_bg
                WeatherIcon.snow -> R.drawable.snow_bg
                WeatherIcon.sleet -> R.drawable.rain_bg
                WeatherIcon.shower -> R.drawable.snow_bg
                WeatherIcon.drizzle -> R.drawable.rain_bg
                WeatherIcon.Negative -> R.drawable.ying_bg
                WeatherIcon.blizzard -> R.drawable.snow_bg
                WeatherIcon.dense_fog -> R.drawable.ying_bg
                WeatherIcon.heavy_fog -> R.drawable.haze_bg
                WeatherIcon.rain_moon -> R.drawable.rain_bg
                WeatherIcon.rainstorm -> R.drawable.rain_bg
                WeatherIcon.sandstorm -> R.drawable.rain_bg
                WeatherIcon.few_clouds -> R.drawable.cloudy_bg
                WeatherIcon.heavy_rain -> R.drawable.rain_bg
                WeatherIcon.heavy_snow -> R.drawable.snow_bg
                WeatherIcon.light_rain -> R.drawable.rain_bg
                WeatherIcon.light_snow -> R.drawable.snow_bg
                WeatherIcon.as_big_as_a_blizzard -> R.drawable.snow_bg
                WeatherIcon.blowing_sand -> R.drawable.ying_bg
                WeatherIcon.extreme_rainfall -> R.drawable.rain_bg
                WeatherIcon.shower_moon -> R.drawable.night_bg
                WeatherIcon.extremely_dense_fog -> R.drawable.ying_bg
                WeatherIcon.heavy_rain_to_extremely_heavy_rain -> R.drawable.rain_bg
                WeatherIcon.freezing_rain -> R.drawable.rain_bg
                WeatherIcon.extremely_heavy_rain -> R.drawable.rain_bg
                WeatherIcon.moderate_haze -> R.drawable.ying_bg
                WeatherIcon.few_clouds_moon -> R.drawable.cloudy_bg
                WeatherIcon.moderate_to_heavy_snow -> R.drawable.snow_bg
                WeatherIcon.moderate_rain -> R.drawable.rain_bg
                WeatherIcon.moderate_to_heavy_rain -> R.drawable.rain_bg
                WeatherIcon.severe_hazes -> R.drawable.haze_bg
                WeatherIcon.thundershowers_with_hail -> R.drawable.rain_bg
                WeatherIcon.severe_thundershowers -> R.drawable.haze_bg
                WeatherIcon.thundershowers -> R.drawable.rain_bg
                WeatherIcon.light_to_moderate_rain -> R.drawable.rain_bg
                WeatherIcon.loating_dust -> R.drawable.haze_bg
                WeatherIcon.partly_cloudys_moon -> R.drawable.cloudy_bg
                WeatherIcon.light_to_moderate_snow -> R.drawable.snow_bg
                WeatherIcon.showers_of_sleet -> R.drawable.rain_bg
                WeatherIcon.heavy_rain_to_heavy_rain -> R.drawable.rain_bg
                WeatherIcon.showers_of_sleet_moon -> R.drawable.night_bg
                WeatherIcon.snow_showers_moon -> R.drawable.snow_bg
                WeatherIcon.strong_dense_fog -> R.drawable.ying_bg
                WeatherIcon.strong_sandstorm -> R.drawable.rain_bg
                WeatherIcon.strong_showers -> R.drawable.rain_bg
                WeatherIcon.snow_showers -> R.drawable.snow_bg
                WeatherIcon.moderate_snow -> R.drawable.snow_bg
                WeatherIcon.severe_haze -> R.drawable.haze_bg
                WeatherIcon.partly_cloudy_moon -> R.drawable.cloudy_bg
                WeatherIcon.strong_showers_moon -> R.drawable.rain_bg
                WeatherIcon.heavy_rains -> R.drawable.rain_bg
                else -> R.drawable.duyun
            }
        }
    }
    return R.drawable.rain_bg
}

@Composable
fun textColor(): Color {
    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
    val animatedColor by infiniteTransition.animateColor(
        initialValue = Purple40,
        targetValue = Pink80,
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        label = "color"
    )
    return animatedColor
}

@Composable
fun ImageCard(text: String) {
    Image(
        painter = painterResource(id = logCard(text)),
        contentDescription = stringResource(id = R.string.app_name),
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}